<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${red.customerName}推广</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta name="format-detection" content="telephone=no">

    <link rel="stylesheet" type="text/css" href="/client/css/extend_detail.css">

    <script src="/plugins/jquery/jquery-1.9.1.js" type="text/javascript"></script>

    <script src=" https://res.wx.qq.com/open/js/jweixin-1.0.0.js "></script>
    <script src="/plugins/wxNotShare/wxNotShare.js"></script>

</head>
<body>

<input type="hidden" id="appId" value="${share.appId}">
<input type="hidden" id="timestamp" value="${share.timestamp}">
<input type="hidden" id="nonceStr" value="${share.nonceStr}">
<input type="hidden" id="signature" value="${share.signature}">


<div class="content">
    <img src="/client/img/extendBackgroundImg.png" class="backgroundImg">
    <div class="topImg">
        <%--<img src="${red.customerImg}">--%>
        <img src="/client/img/test/header.png">
    </div>
    <div class="red">

        <div class="greyEnvelopeDown">
            <span>红包总数:${red.redNumDayTotal},剩余:${red.redNumDayLeft}</span>
        </div>
        <div class="words">
            ${red.stepRule}
        </div>
        <c:if test="${red.redNumDayLeft > 0}">
            <c:if test="${isIos == false}">
                <div class="grabRedButton">
                    <a href="javaScript:;" onclick="userSign.openWX('com.tencent.mm');"><button>去抢红包</button></a>
                </div>
            </c:if>
            <c:if test="${isIos == true}">
                <div class="grabRedButton">
                    <a href="weixin://"><button>去抢红包</button></a>
                </div>
            </c:if>
        </c:if>
        <c:if test="${red.redNumDayLeft == 0}">
            <div class="canNotGrabRedButton">
                <button>红包已抢完</button>
            </div>
        </c:if>
    </div>
</div><!--content结束 -->
</body>
</html>
