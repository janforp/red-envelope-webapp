
//全局变量
var keywordId;
var marketUrl;
var marketPackage;
var appPackage;


/**
 * 点击列表中任何一个任务
 */
function getMission(keyId, desc, marPackage, marUrl, aPackage) {

    if (desc == '0'){
        $("#toastDesc").text("加载中...")
    }else {
        $("#toastDesc").text("抢任务中...")
    }
    $("#loadingToast").show();
    setTimeout(function () {

        doGetMission(keyId, desc, marPackage, marUrl, aPackage);

    },10);
}


/**
 * 点击列表中任何一个任务
 */
function doGetMission(keyId, desc, marPackage, marUrl, aPackage) {

    keywordId = keyId;
    marketUrl = marUrl;
    marketPackage = marPackage;
    appPackage = aPackage ;

    if (desc == '0'){//若果desc为0,则点击进行中的任务

        //先判断此任务是否已经超时(1hour)
        var timestamp = Date.parse(new Date());
        var map = {
            't':timestamp,
            'keywordId':keyId
        };
        var str = JSON.stringify(map);
        var sign = userSign.getSign(str);

        //需要判断该任务是否超时,若超时,则不能进入详情页面,弹框提示
        $.ajax({
            url:"/c/p/a/task/isOvertime",
            type:"POST",
            dataType:"JSON",
            data:{
                keywordId:keyId,
                t: timestamp,
                sign: sign
            },
            success:function (data) {

                if (data.code == '0'){//没有超时
                    //直接跳至详情页面
                    window.location.href = '/c/p/a/task/timedDetail?keywordId='+keyId;
                    return;
                }else {//弹框提示
                    //弹框
                    showOneButton("通知","任务已超时");
                    //此时点击确定的时候后退页面
                    $("#singleButton").attr("onclick","closeOneButton(2)");

                }
            },
            error:function () {

            }
        });


    }else {//若果desc不为0,则需要判断各种情况

        var isAppPackageExist = userSign.isPackageExist(appPackage);

        if (isAppPackageExist){//如果app已经存在,则直接提示,方法结束

            $("#loadingToast").hide();

            showOneButton("通知","APP已存在!");
            //弹框确定按钮:刷新列表
            $("#singleButton").attr("onclick","closeOneButton(1)");

            return ;
        }

        var isMarketPackageExist = userSign.isPackageExist(marketPackage);

        if (!isMarketPackageExist){//如果市场不存在,则直接提示,该方法结束,去弹框

            $("#loadingToast").hide();

            showTwoButton("下载应用市场?")
            //弹框的'确定'按钮应该链接到应用市场下载地址
            $("#sureGetAnotherMission").attr("onclick","twoButtonSure(1)");

            return;
        }


        //看当前列表中是否有进行中的任务
        var totalLines = $(".lineDiv");

        for (var i=0;i<totalLines.length;i++){

            var $oneLine = $(totalLines[i]);

            var oneDesc = $oneLine.attr("d");
            //若有,则弹框
            if (oneDesc == '0'){

                $("#loadingToast").hide();

                //弹框方法:是否要放弃之前的任务
                showTwoButton("不能同时做多个任务哦!要放弃上一个任务接受该任务吗?");
                //确定按钮事件绑定
                $("#sureGetAnotherMission").attr("onclick","twoButtonSure(2)");
                return;
            }
        }

        var timestamp = Date.parse(new Date());
        var map = {
            't':timestamp,
            'keywordId':keywordId
        };
        var str = JSON.stringify(map);
        var sign = userSign.getSign(str);
        $.ajax({
            url:"/c/p/a/task/getMission",
            type:"POST",
            dataType:"JSON",
            data:{
                keywordId:keywordId,
                t: timestamp,
                sign: sign
            },
            success:function (data) {

                if (data.code == '0'){//成功抢到任务
                    //直接去详情页面
                    window.location.href = '/c/p/a/task/timedDetail?keywordId='+keywordId;
                }else {//抢任务失败

                    $("#loadingToast").hide();

                    //弹框提示:任务已被抢光
                    showOneButton("通知","任务已被抢完!");
                    //弹框按钮单击事件:刷新页面
                    $("#singleButton").attr("onclick","closeOneButton(1)");
                }
            },
            error:function () {

            }
        });
    }
}