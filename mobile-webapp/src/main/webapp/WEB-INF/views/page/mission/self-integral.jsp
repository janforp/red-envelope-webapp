<!--需审核任务详情页面-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${wall.appName}</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <script type="text/javascript" src="/plugins/jquery/jquery-1.9.1.min.js"></script>
    <c:if test="${isIos == true}">
        <script type="text/javascript" src="/plugins/ios-jsBridge/ios-jsBridge.js"></script>
    </c:if>
    <link rel="stylesheet" type="text/css" href="/client/css/self_integral/self_integral.css">
    <link rel="stylesheet" type="text/css" href="/client/css/base/red-envelope-base.css">
    <script type="text/javascript" src="/client/js/self_integral/self-integral.js"></script>
    <script>

        var appLabel = '${wall.appLabel}';
        var appImg = '${wall.appImg}';
        var totalMoney = '${wall.totalMoney}';

        $(function () {
            var labels = appLabel.split(",");
            for (var i=0;i<labels.length;i++){

                var newLabel = "<span class='label'>"+labels[i]+"</span>";
                $("#labelDiv").append(newLabel);
            }

            var imgs = appImg.split(";");
            for (var i=0;i<imgs.length;i++){

                var newImg = "<img src='"+imgs[i]+"'>";
                $("#appImgDiv").append(newImg);
            }

            if (totalMoney.indexOf('.00')>0){
                totalMoney = totalMoney.replace('.00','');
            }
            $("#totalMoney").text(totalMoney+'元');
        });
    </script>
</head>
<body>
<div class="content"><!--content start-->

    <div class="header">
        <div class="img">
            <img src="${wall.appIcon}">
        </div>
        <div class="title-size">
            <span>${wall.appName}</span>
            <span>大小：${wall.appSize}MB</span>
        </div>
        <div class="reward">
            <span>总奖励</span>
            <span id="totalMoney"></span>
        </div>
    </div>

    <div class="labelDiv" id="labelDiv">
    </div>
    <div class='red-model-separator-line red-bg-color'></div>
    <div class="title">
        <span>任务步骤</span>
    </div>
    <div class="stepDiv">
        <div class="right">
            <div class="stepOne">
                1.首次安装注册<span class="moneyTips">+${wall.stepOneMoney}</span>元<br>
                <span class="stepOneDesc">${wall.stepOneDesc}</span>
            </div>
            <div class="stepTwo">
                2.<span class="moneyTips">${wall.stepTwoDay}日内</span>每日再次打开体验即可获得<span class="moneyTips">${wall.stepTwoMoney}</span>元。
            </div>
        </div>
    </div>
    <div class='red-model-separator-line red-bg-color'></div>
    <div class="title">
        <span>APP介绍</span>
    </div>
    <div class="appImgDiv" id="appImgDiv">
    </div>
    <div class="introduceDiv">${wall.appIntroduce}</div>
</div><!--content over-->
</body>
</html>