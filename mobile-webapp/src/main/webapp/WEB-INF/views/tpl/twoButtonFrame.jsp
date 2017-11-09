<style>
    .hideTwoButton{
        display: none;
    }
    .mask{
        margin:0;
        padding:0;
        border:none;
        width:100%;
        height:100%;
        background-color: rgba(0,0,0,0.3);
        position:fixed;
        top:0;
        left:0;

        display: flex;
        align-items: center;
        justify-content: center;
    }
    /*不能同时做多个任务哦!要放弃上一个任务接受该任务吗?*/
    .twoButton{
        z-index:9999;
        margin: 0 5%;
        height: 200px;
        background-color: #FFFFFF;

        width: 90%;
        color: #080808;
        text-align: center;
        padding: 1rem;
        border-radius: 5px;

        display: flex;
        flex-direction: column;
        justify-content: space-around;
        align-items: center;
    }
    .twoButton .desc{
        display: block;
        font-size: 1.1rem;
        text-align: left;
        line-height:30px;
    }
    .aDiv{
        display: flex;
        justify-content: space-around;
        width: 100%;
    }
    .twoButton a:nth-child(1){
        display:inline-block;
        font-size: 1.4rem;
        padding: 0.6rem 0;
        width: 40%;
        border-radius: 5px;
        letter-spacing: 2px;
        color: #00A8F8;
        border:1px solid #00A8F8 ;
    }

    .twoButton a:nth-child(2){
        display:inline-block;
        font-size: 1.4rem;
        color:#FFFFFF;
        background-color:#00A8F8 ;
        padding: 0.6rem 0;
        width: 40%;
        border-radius: 5px;
        letter-spacing: 2px;
    }
</style>
<script>

    function showTwoButton(content,button1,button2) {

        $("#twoButtonDesc").text(content);
        if(button1 == null || button1 == "" || button1 == undefined){
            button1 = "确定";
        }
        if(button2 == null || button2 == "" || button2 == undefined){
            button2 = "取消";
        }
        $("#sureGetAnotherMission").text(button1);
        $("#notGetAnotherMission").text(button2);

        $(".hideTwoButton").show();
        $("body").css("overflow","hidden");
    }

    function closeTwoButton(type) {

        $(".hideTwoButton").hide();
        $("body").css("overflow","");
        if(type == '1'){    //关闭当前页面

            alert('关闭当前页面');
            window.close();
        }
    }

    /**
     * 点击2个按钮
     * @param type
     */
    function twoButtonSure(type) {

        //1.关闭弹框
        $(".hideTwoButton").hide();
        $("body").css("overflow","");

        //2.判断type
        if(type == '1'){ //下载应用市场链接

            userSign.openSystemBrowser(marketUrl);

        }else if (type == '2'){//放弃一个任务接受另一个任务

            var timestamp = Date.parse(new Date());
            var map = {
                't':timestamp,
                'keywordId':keywordId
            };
            var str = JSON.stringify(map);
            var sign = userSign.getSign(str);

            //TODO 放弃之前的任务的时候,是否要把旧任务的步骤置为空

            $.ajax({
                url:"/c/p/a/task/anotherMission",
                type:"POST",
                dataType:"JSON",
                data:{
                    keywordId:keywordId,
                    t: timestamp,
                    sign: sign
                },
                success:function (data) {

                    if (data.code == '0'){//更改任务成功
                        //直接去详情页面
                        window.location.href = '/c/p/a/task/timedDetail?keywordId='+keywordId;
                    }else {
                        //弹框
                        showOneButton("通知",data.msg);//更改任务失败,弹框,此时框的确定按钮为刷新页面
                        //更改单击按钮单击事件:此时
                        $("#singleButton").attr("onclick","closeOneButton(1)");
                    }
                },
                error:function () {

                }
            });
        }
    }

</script>

<!--2个按钮框 -->
<div class="hideTwoButton">
    <div class="mask">
        <div class='twoButton'>
            <span class='desc' id="twoButtonDesc">不能同时做多个任务哦!要放弃上一个任务接受该任务吗?</span>
            <div class='aDiv'>
                <a id='sureGetAnotherMission' onclick="twoButtonSure(type);" href='javaScript:;'>确定</a><a id="notGetAnotherMission" href='javaScript:;' onclick='closeTwoButton();'>取消</a>
            </div>
        </div>
    </div>
</div>

