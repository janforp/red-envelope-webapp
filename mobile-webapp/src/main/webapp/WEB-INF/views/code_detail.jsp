<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>${red.customerName}红包</title>
        <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
        <meta name="format-detection" content="telephone=no">
        <link rel="stylesheet" type="text/css" href="/client/css/code_detail.css">
        <script src="/plugins/jquery/jquery-1.9.1.js" type="text/javascript"></script>
        <script src="/client/js/code_detail.js" type="text/javascript"></script>
        <script src=" https://res.wx.qq.com/open/js/jweixin-1.0.0.js "></script>
        <script src="/plugins/wxNotShare/wxNotShare.js"></script>

    </head>
    <body>
    <input type="hidden" id="appId" value="${share.appId}">
    <input type="hidden" id="timestamp" value="${share.timestamp}">
    <input type="hidden" id="nonceStr" value="${share.nonceStr}">
    <input type="hidden" id="signature" value="${share.signature}">

    <div class="content">
        <div class="headImgDiv">
            <img src="${red.customerImg}">
        </div>
        <div class="topImg">

            <img src="/client/img/codeRed/codeDetail1.png" class="backgroundImg">
            <div class="maxDiv">
                <span class="maxMoney">${red.redMax}</span><span class="yuan">元</span>
            </div>
        </div>
        <div class="words">
            ${red.redDesc}
        </div>

        <div class="copyCodeDiv">
            <div class="dashBorder">
                <span class="copyDesc">长按复制兑换码</span>
                <span class="code">${red.redCode}</span>
            </div>
        </div>

        <c:if test="${red.redNumDayLeft > 0}">
        <c:if test="${isIos == false}">
            <div class="grabRedButton">
                <a href="javaScript:;" onclick="userSign.openWX('com.tencent.mm');"><button>去抢红包</button></a>
                <!--weixin://qr/gh_34bd692a9835-->
            </div>
        </c:if>
        <c:if test="${isIos == true}">
        <div class="grabRedButton">
            <a href="weixin://" ><button>去抢红包</button></a>
        </div>
        </c:if>

        </c:if>
        <c:if test="${red.redNumDayLeft == 0}">
            <div class="canNotGrabRedButton">
                <button>红包已抢完</button>
            </div>
        </c:if>
    </div><!--content结束 -->
    </body>
</html>
