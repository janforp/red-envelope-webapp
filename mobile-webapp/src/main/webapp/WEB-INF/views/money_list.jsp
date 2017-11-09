<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <title>金币商城</title>
    <link rel="stylesheet" type="text/css" href="/client/css/coin_list.css">
    <link rel="stylesheet" type="text/css" href="/plugins/weui/css/weui.css">
    <!--上榜规则样式-->
    <script src="/plugins/jquery/jquery-1.9.1.js" type="text/javascript"></script>
    <script src="/client/js/money_list.js"></script>
    <!--无法分享组件-->
    <script src=" https://res.wx.qq.com/open/js/jweixin-1.0.0.js "></script>
    <script src="/plugins/wxNotShare/wxNotShare.js"></script>

    <script type="application/javascript" src="/client/js/test/iscroll.js"></script>
    <link rel="stylesheet" type="text/css" href="/plugins/iscroll/slipe.css">

    <script src="/plugins/iscroll/coin_list_slipe.js"></script>

</head>
<body>

<input type="hidden" id="appId" value="${share.appId}">
<input type="hidden" id="timestamp" value="${share.timestamp}">
<input type="hidden" id="nonceStr" value="${share.nonceStr}">
<input type="hidden" id="signature" value="${share.signature}">

<input type="hidden" id="pageNow" value="${pageNum}">
<input type="hidden" id="totalPage" value="${totalPage}">

<div id="wrapper">
    <div id="scroller">
        <div class="content">
            <div class="myCoin">
                <span>我的余额</span>
            </div>
            <div class="header">
                <img src="/client/img/coinmall/rmb.png">
                <span>${score}</span>
            </div>
            <%--<div class="recordMoney">--%>
                <%--<div class="recordMoney1">--%>
                    <%--<a><img src="/client/img/coinmall/makeMoneyMission.png"><span>任务赚钱</span></a>--%>
                <%--</div>--%>
                <%--<div class="recordMoney2">--%>
                    <%--<a><img src="/client/img/coinmall/record.png"><span>兑换记录</span></a>--%>
                <%--</div>--%>
                <%--<div class="recordMoney3">--%>
                    <%--<a><img src="/client/img/coinmall/order.png"><span>金币排名</span></a>--%>
                <%--</div>--%>
            <%--</div>--%>
            <div class="sep">
            </div>
            <!--列表内容-->

            <div id="list">
                <c:forEach items="${scores}" var="score">
                    <div class="oneLine">
                        <div class ="lineLeft">
                            <span class="leftSpan1">红包APP</span>
                            <span class="leftSpan2">${score.detailContent}</span>
                        </div>
                        <div class="lineRight">
                            <span class="rightSpan1">${score.detailTime}</span>
                            <span class="rightSpan2">
                                <c:if test="${score.detailType == 0}">
                                    - ${score.accountMoney}
                                </c:if>
                                <c:if test="${score.detailType == 1}">
                                    + ${score.accountMoney}
                                </c:if>
                            </span>
                        </div>
                    </div>
                </c:forEach>
            </div>

        </div>
        <div id="pullUp" style="padding-left: 45%">
            <span class="pullUpIcon"></span><span class="pullUpLabel">&nbsp;</span>
        </div>
    </div>
    <div class="back">
        <a href="javaScript:;" id="backToCoinMall" onclick="history.go(-1);">
            <img src="/client/img/coinmall/home.png">
        </a>
    </div>
</div>
</body>
</html>