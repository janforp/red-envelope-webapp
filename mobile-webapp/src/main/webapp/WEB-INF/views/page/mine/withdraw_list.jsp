<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <title>提现记录</title>
    <link rel="stylesheet" type="text/css" href="/client/css/mine/withdraw_list.css">
    <c:if test="${total != 0}">
        <script src="/plugins/jquery/jquery-1.9.1.js" type="text/javascript"></script>
        <script src="/client/js/withdraw_list.js"></script>
        <script type="application/javascript" src="/client/js/test/iscroll.js"></script>
        <link rel="stylesheet" type="text/css" href="/plugins/iscroll/slipe.css">
        <script src="/plugins/iscroll/coin_list_slipe.js"></script>
    </c:if>
</head>
<body>
    <div class="hiddenDiv">
        <input type="hidden" id="pageNow" value="${pageNum}">
        <input type="hidden" id="totalPage" value="${totalPage}">
    </div>
    <c:if test="${total == 0}"><!--显示暂无提现记录图片-->
        <div style="background-color: #F0F0F0;height: 100%; width: 100%;margin-left: auto;margin-right: auto;max-width: 414px;">
            <div style="text-align: center;">
                <img src="/client/img/mine/noWithdrawRecord.png" style="max-width:316px;">
                <span style="display: block;font-size: 1.3rem;color: #999999;letter-spacing: 2px;">暂无提现记录</span>
            </div>
        </div>
    </c:if>
    <c:if test="${total != 0}"><!--显示记录-->
        <div id="wrapper">
            <div id="scroller"><!--scroller start-->
                <div class="content"><!--content start-->
                    <div class="tableHeader">
                        <div class="tHead">
                            <span>金额</span>
                        </div>
                        <div class="tHead">
                            <span>时间</span>
                        </div>
                        <div class="tHead">
                            <span>状态</span>
                        </div>
                        <div class="tHead">
                            <span>方式</span>
                        </div>
                    </div>
                    <c:forEach items="${details}" var="detail">
                        <div id="list">
                            <div class="tableBody">
                                <div class="tBody">
                                    <span class="money">${detail.money}</span>
                                </div>
                                <div class="tBody">
                                    <span class="date">${detail.date}</span>
                                    <span class="time">${detail.time}</span>
                                </div>
                                <div class="tBody">
                                    <span class="status">${detail.status}</span>
                                </div>
                                <div class="tBody">
                                    <span class="type">${detail.type}</span>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div><!--content over-->

                <div id="pullUp" style="padding-left: 45%"><!--向上滑动不可少的div-->
                    <span class="pullUpIcon"></span><span class="pullUpLabel">&nbsp;</span>
                </div>

            </div><!--scroller over-->
        </div><!--wrapper over-->
    </c:if>
</body>
</html>