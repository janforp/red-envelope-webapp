<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <title>任务详情</title>
    <!--上榜规则样式-->
    <script src="/plugins/jquery/jquery-1.9.1.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="/client/css/timed-mission/timed-detail.css">
    <link rel="stylesheet" type="text/css" href="/client/css/timed-mission/timed-detail-header.css">
    <link rel="stylesheet" type="text/css" href="/client/css/timed-mission/frame.css">
    <script src="/client/js/task/time-detail.js" type="text/javascript"></script>
    <script>

        var leftTime = '${detail.leftTime}';
        var keywordId = '${detail.keywordId}';
        var marketUrl = '${detail.marketUrl}';
        var interval;

        $(function(){

            getCountDown(leftTime);

        });

        $(document).ready(

            function setStep() {

                var step = userSign.getMissionStep(keywordId);

                if (step == '1'){

                    //第二步亮起
                    $("#stepTwo").attr("onclick","return true;");
                    $("#stepTwo").css("background-color" ,"#00A8F8");

                }else if (step == '2'){

                    //第二,第三步都亮
                    $("#stepTwo").attr("onclick","return true;");
                    $("#stepTwo").css("background-color" ,"#00A8F8");

                    $("#stepThree").attr("onclick","return true;");
                    $("#stepThree").css("background-color" ,"#00A8F8");

                }else if (step == '3'){

                    //全部亮
                    $("#stepTwo").attr("onclick","return true;");
                    $("#stepTwo").css("background-color", "#00A8F8");

                    $("#stepThree").attr("onclick","return true;");
                    $("#stepThree").css("background-color" ,"#00A8F8");

                    $("#stepFour").attr("onclick","return true;");
                    $("#stepFour").css("background-color" ,"#00A8F8");
                }
            }
        );

        function getCountDown(leftTime) {

            interval = setInterval(function () {

                if (leftTime <= 1000) {

                    overtime(interval, keywordId);

                    return;
                }

                leftTime = leftTime - 1000;

                var min = Math.floor(leftTime / 1000 / 60 % 60);
                var sec = Math.floor(leftTime / 1000 % 60);

                if (min < 10) {
                    min = "0" + min;
                }
                if (sec < 10) {
                    sec = "0" + sec;
                }

                var countDownTime = min + ":" + sec;

                $("#left").html(countDownTime);

            }, 1000);
        };

        //倒计时结束,则视为放弃该任务
        function overtime(interval, keywordId) {

            clearInterval(interval);
            $("#left").text("00:00");

            var timestamp = Date.parse(new Date());
            var map = {
                't': timestamp,
                'keywordId': keywordId
            };
            var str = JSON.stringify(map);
            var sign = userSign.getSign(str);
            $.ajax({
                url: "/c/p/a/task/overtimeToGiveUp",
                type: "POST",
                dataType: "JSON",
                data: {
                    keywordId: keywordId,
                    t: timestamp,
                    sign: sign
                },
                success: function (data) {
                    //直接弹框:任务超时
                    showOneButton("通知","任务已超时");
                    //弹框按钮单击事件:关闭webview
                    $("#singleButton").attr("onclick","closeOneButton(4)");
                },
                error: function () {}
            });
        }
    </script>
</head>
<body>
<div class="content"><!--content start -->
    <div class="lineDiv">
        <div class="imgDiv">
            <img src=${detail.appIcon}>
        </div>
        <div class="keyDiv">
            <div class="keyWord">
                <span>${detail.keyword}</span>
            </div>
            <div class="market">
                <span>大小${detail.size}M</span>
            </div>
        </div>
        <div class="moneyDiv">
            +<span class="money">${detail.money}</span><span class="unit">元</span>
        </div>
    </div>
    <div class="step">
        <span class="stepTitle">任务步骤</span><span class="leftTime"> ( 剩余时间 <a id="left"></a> ) </span>
        <span class="stepOne">1.点击"复制关键词"按钮;</span>
        <span class="stepOne">2.点击"点击此处前往应用商店"按钮;</span>
        <span class="stepOne">3.粘贴关键词并搜索,找到该图标应用,下载并安装;</span>
        <span class="stepOne">4.点击'安装后点此打开应用'按钮,前台运行,真实体验3分钟!</span>
    </div>
    <div class="btnDiv">
        <a href="javaScript:stepOne('${detail.keywordId}','${detail.keyword}');">
            <span>第一步</span><span>复制关键词:${detail.keyword}</span>
        </a>
        <a href="javaScript:stepTwo('${detail.marketPackage}','${detail.keywordId}','${detail.marketUrl}');" onclick="return false;"
           id="stepTwo">
            <span>第二步</span><span>点击此处前往应用商店</span>
        </a>
        <a href="javaScript:stepThree('${detail.keywordId}','${detail.appPackage}');" id="stepThree"
           onclick="return false;">
            <span>第三步</span><span>安装后点此打开应用</span>
        </a>
        <a href="javaScript:stepFour('${detail.keywordId}');" id="stepFour"
           onclick="return false;">
            <span>第四步</span><span>完成后领取奖励</span>
        </a>
    </div>

    <div class="foot">
        <span>注:请按照步骤进行任务,否则无法进行下一步;若应用市场搜索出现异常请按步骤重新打开.</span>
    </div>
</div><!--content over -->

<%@include file="/WEB-INF/views/tpl/oneButtonFrame.jsp" %>
<%@include file="/WEB-INF/views/tpl/twoButtonFrame.jsp" %>
<%@include file="/WEB-INF/views/tpl/errorNotice.jsp"%>

</body>
</html>