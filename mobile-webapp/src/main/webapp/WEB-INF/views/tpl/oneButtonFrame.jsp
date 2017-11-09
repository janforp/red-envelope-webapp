<style>
    .hideOneButton{
        display: none;
    }
    a{
        text-decoration: none;
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

    #oneButton{
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
    #oneButton .money{

        font-size: 1.8rem;
        display: inline-block;
    }
    #oneButton .desc{
        display: block;
        font-size: 1.1rem;
    }
    #oneButton a{
        display: block;
        font-size: 1.4rem;
        color:#FFFFFF;
        background-color:#00A8F8 ;
        width: 99%;
        padding: 0.6rem 0;
        border-radius: 5px;
        letter-spacing: 1px;
    }

    @media  (max-width: 414px){

        #oneButton a{
            display: block;
            font-size: 1.2rem;
            color:#FFFFFF;
            background-color:#00A8F8 ;
            width: 99%;
            padding: 0.6rem 0;
            border-radius: 5px;
            letter-spacing: 1px;
        }
    }
</style>
<script>
    /*1个按钮*/
    function showOneButton(title,content,buttonName) {

        var $tipsTitle = $("#notify");
        var $desc = $("#notifyContent");
        $tipsTitle.text(title);
        $desc.text(content);

        if(buttonName == null || buttonName ==""||buttonName ==undefined){
            buttonName = "确定";
        }
        $("#singleButton").text(buttonName);

        $(".hideOneButton").show();
        $("body").css("overflow","hidden");
    }

    function closeOneButton(type) {

        $(".hideOneButton").hide();
        $("body").css("overflow","");
        if(type == '1'){//关闭后刷新页面
            window.location.reload();
        }else if(type == '2'){//关闭后,回到任务列表页面
            history.go(-1);
        }else if(type == '0'){
            $(".hideOneButton").hide();
            $("body").css("overflow","");
        }else if(type == '3'){ //抢推荐任务

            sureGetRecommendMission(missionId);
        }else if(type == '4'){ //关闭当前webview，并跳转到指定activity

            try{
                userSign.closeWebActAndTurnoverOther('');
            }catch (e){
                history.go(-1);
            }
        }
    }
</script>

<!--单个按钮框 -->
<div class="hideOneButton">
    <div class="mask">
        <div id='oneButton'>
            <span id="notify" class='money'>通知</span>
            <span id="notifyContent" class='desc'>~</span>
            <a href='javaScript:;' id="singleButton" onclick='closeOneButton(type);'>确定</a>
        </div>
    </div>
</div>

