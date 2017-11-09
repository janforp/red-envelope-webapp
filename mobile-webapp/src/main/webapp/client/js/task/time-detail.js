function stepOne(keywordId, keyword) {


    var isCopySuccess = userSign.copyToClipboard(keyword);

    if (isCopySuccess){

        var isSaveStepSuccess = userSign.setMissionStep(keywordId, 1);

        if (isSaveStepSuccess){//保存步骤

            //按钮2亮起
            $("#stepTwo").attr("onclick","return true;");
            $("#stepTwo").css("background-color" ,"#00A8F8");

            //弹框
            showOneButton("提示","复制成功");

        }else {

            //弹框:提示保存步骤失败
            showOneButton("提示","操作失败,请重试");
        }
    }else {

        //弹框:提示复制失败
        showOneButton("提示","操作失败,请重试");
    }

}

/**
 * 步骤2
 * @param marketPackage
 * @param keywordId
 */
function stepTwo(marketPackage,keywordId,marketUrl) {

    //判断应用市场包是否在
    var isMarketPackage = userSign.isPackageExist(marketPackage);

    if (isMarketPackage){//存在

        //存步骤
        var isSaveStepSuccess = userSign.setMissionStep(keywordId, 2);

        if (isSaveStepSuccess){
            //开起第3步
            $("#stepThree").attr("onclick","return true;");
            $("#stepThree").css("background-color" ,"#00A8F8");
            //打开应用市场
            userSign.turnOtherApp(marketPackage);
        }else{
            //弹框:提示失败
            showOneButton("提示","操作失败,请重试");
        }
    }else {//下载应用市场
        //弹框
        showTwoButton("下载应用市场?")
        //弹框的'确定'按钮应该链接到应用市场下载地址
        $("#sureGetAnotherMission").attr("onclick","twoButtonSure(1)");



    }
}

function stepThree(keywordId,appPackage) {

    //判断app包是否在
    var isAppPackage = userSign.isPackageExist(appPackage);
    if (isAppPackage){//存在

        //存步骤
        var isSaveStepSuccess = userSign.setMissionStep(keywordId, 3);

        if (isSaveStepSuccess){

            //传一个当前的时间戳给安卓

            //开起第4步
            $("#stepFour").attr("onclick","return true;");
            $("#stepFour").css("background-color", "#00A8F8");


            var timestampNow = (Date.parse(new Date()) / 1000).toString().substring(3);

            var isTimestampOpenAppSaveSuccess = userSign.setTimeStamp(timestampNow);


            if (isTimestampOpenAppSaveSuccess) {

                //打开app
                userSign.turnOtherApp(appPackage);
            }else {

                //弹框:提示失败
                showOneButton("提示","操作失败,请重试");
            }


        }else {
            //弹框:提示失败
            showOneButton("提示","操作失败,请重试");
        }
    }else {
        //弹框:提示去下载app
        showOneButton("提示","请先下载并安装对应APP");
    }
}



function stepFour(keywordId) {

    var timestampOpenApp = userSign.getTimeStamp();

    if(timestampOpenApp == '0'){
        //弹框:提示失败
        showOneButton("提示","操作失败,请重试");
        return;
    }

    var timestampNow = (Date.parse(new Date()) / 1000).toString().substring(3);

    if( (timestampNow - timestampOpenApp) >= 30){//打开30s,则完成任务
        //完成任务,发奖励

        var timestamp = Date.parse(new Date());
        var map = {
            't':timestamp,
            'keywordId':keywordId
        };
        var str = JSON.stringify(map);
        var sign = userSign.getSign(str);

        $.ajax({
            //奖励
            url:"/c/p/a/task/getMoney",
            type:"POST",
            dataType:"JSON",
            data:{
                keywordId:keywordId,
                t: timestamp,
                sign: sign},
            success:function (data) {

                if (data.code == '0'){  //成功
                    userSign.setMissionStep("", "");
                    //弹框:提示去下载app
                    showOneButton("+"+data.msg+"元","恭喜您成功领取现金,已存入您的余额");
                    //弹框确定按钮点击事件:退回到列表页面
                    $("#singleButton").attr("onclick","closeOneButton(4)");

                }else {
                    //领取现金失败
                    showOneButton("提示","领取现金失败,请截图,请联系客服");
                    $("#singleButton").attr("onclick","closeOneButton(0)");
                }
                
            },
            error:function () {
                
            }
        });

    }else {

        //领取现金失败
        showOneButton("提示","领取现金失败,请确保按照步骤打开APP使用3分钟~");
        $("#singleButton").attr("onclick","closeOneButton(0)");
    }


}





