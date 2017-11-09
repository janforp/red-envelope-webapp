<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <title>收益记录1</title>
    <link rel="stylesheet" type="text/css" href="/client/css/commission/commission_list.css">
    <c:if test="${total != 0}">
        <script src="/plugins/jquery/jquery-1.9.1.js" type="text/javascript"></script>
        <script src="/client/js/commission_list.js"></script>
        <script type="application/javascript" src="/client/js/test/iscroll.js"></script>
        <link rel="stylesheet" type="text/css" href="/plugins/iscroll/slipe.css">
        <script src="/plugins/iscroll/coin_list_slipe.js"></script>
    </c:if>

    <script>

        function setupWebViewJavascriptBridge(callback) {
            if (window.WebViewJavascriptBridge) {
                return callback(WebViewJavascriptBridge);
            }
            if (window.WVJBCallbacks) {
                return window.WVJBCallbacks.push(callback);
            }
            window.WVJBCallbacks = [callback];
            var WVJBIframe = document.createElement('iframe');
            WVJBIframe.style.display = 'none';
            WVJBIframe.src = 'wvjbscheme://__BRIDGE_LOADED__';
            document.documentElement.appendChild(WVJBIframe);
            setTimeout(function () {
                document.documentElement.removeChild(WVJBIframe)
            }, 0)
        }

        setupWebViewJavascriptBridge(function (bridge) {
            bridge.registerHandler('testJSFunction', function (data, responseCallback) {
                alert('JS方法被调用:' + data);
                responseCallback('js执行过了');
            })
        });

        function inviteHtml() {
            WebViewJavascriptBridge.callHandler('inviteHtml', null, function (response) {
            });
            window.location.href = "http://www.baidu.com";
        }

    </script>

</head>
<body>
    <div class="hiddenDiv">
        <input type="hidden" id="pageNow" value="${pageNum}">
        <input type="hidden" id="totalPage" value="${totalPage}">
    </div>
    <c:if test="${total == 0}">
        <div style="background-color: #F0F0F0;height: 100%; width: 100%;margin-left: auto;margin-right: auto;max-width: 414px;">
            <div style="text-align: center;">
                <img src="/client/img/mine/noCommission.png" style="max-width: 316px;" >
            </div>
        </div>
    </c:if>
<c:if test="${total != 0}">
    <div id="wrapper">
        <div id="scroller"><!--scroller start-->
            <div class="cover" style="width: 100%;height: 100%;background-color: #7F7F7F;position: absolute;top: 0;alpha(opacity=70); opacity: 0.7;display: none"></div>
            <div class="content"><!--content start-->
                <div class="headerDiv">
                    <%--<span class="headTitle">收益记录</span>--%>
                    <span class="headDesc">总收益<span id="totalMoney">${commission}</span>元</span>
                </div>
                <!--列表内容-->
                <div id="list">
                    <c:forEach items="${details}" var="detail">
                        <div class='oneDetailDiv'>
                            <div class='left'>
                                <span class='detailContent'>${detail.detailContent}</span>
                                <span class='detailTime'>${detail.detailTime}</span>
                            </div>
                            <div class='right'>
                                <c:if test="${detail.detailType == 1}">
                                    <span class='detailMoney'>+${detail.accountMoney}元</span>
                                </c:if>
                                <c:if test="${detail.detailType == 0}">
                                    <span class='detailMoney'>-${detail.accountMoney}元</span>
                                </c:if>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div><!--content over-->

            <div id="pullUp" style="padding-left: 45%"><!--向上滑动不可少的div-->
                <span class="pullUpIcon"></span><span class="pullUpLabel">&nbsp;</span>
            </div>

        </div><!--scroller over-->

        <div class="footButtonDiv">
            <button onclick="inviteHtml();" id="invite"><span>立即邀请好友</span></button>
        </div>
    </div><!--wrapper over-->
</c:if>
</body>
</html>