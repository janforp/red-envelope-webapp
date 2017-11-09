<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <title>邀请有礼</title>
    <link rel="stylesheet" type="text/css" href="/client/css/commission/commission_invitation_menu.css">
    <script src="/plugins/jquery/jquery-1.9.1.js" type="text/javascript"></script>
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
                window.location.href = "/c/p/invitation/step";
            });
        }

    </script>
</head>
<body>
    <div class="content">
        <div id="cover" style="width: 100%;height: 100%;background-color: #7F7F7F;position: absolute;top: 0;alpha(opacity=70); opacity: 0.7;display: none"></div>
        <div class="headerDiv">
            <span class="headTitle">累计收益(元)</span>
            <span id="totalMoney">${commission}</span>
        </div>
        <div class="menuDiv">
            <div class="menu">
                <a href="/c/p/invitation/invitationList">
                    <img src="/client/img/commission/myInvitation.png">
                    <span>我的邀请</span>
                </a>
            </div>
            <div class="menu">
                <a href="/c/p/commission/commissionList">
                    <img src="/client/img/commission/myCommissionRecord.png">
                    <span>收益记录</span>
                </a>
            </div>
            <div class="menu">
                <a href="/c/p/commission/withdrawPage">
                    <img src="/client/img/commission/invaIcon.png">
                    <span>申请提现</span>
                </a>
            </div>
            <div class="menu">
                <a href="/c/p/invitation/strategy">
                    <img src="/client/img/commission/invitationMethod.png">
                    <span>邀请攻略</span>
                </a>
            </div>
        </div>
        <div class="backgroundImg">
            <span>${invitationCode}</span>
        </div>
        <div class="desc">
            <span class="descText">成功邀请好友,即可获得<span>0.1元现金</span>奖励,</span>
            <%--<span class="descText">好友完成任务,还可获得<span>额外奖励</span>哦 !</span>--%>
        </div>
        <div class="footButtonDiv">
            <button onclick="inviteHtml();" id="invite"><span>立即邀请好友</span></button>
        </div>
        <div class="ruleTitle">
            <div class="ruleTitleLeft">
                <span><hr></span>
            </div>
            <div class="ruleTitleMid">
                <span>活动规则</span>
            </div>
            <div class="ruleTitleRight">
                <span><hr></span>
            </div>
        </div>
        <div class="ruleTextDiv">
            <span class="ruleText">1.您的好友必须通过您的专属邀请链接注册登录全民红包；</span>
            <span class="ruleText">2.您的好友必须首次登录全民红包（之前登录过的无效）；</span>
            <span class="ruleText">3.您的好友需要下载并安装全民红包App，然后用之前注册的手机号登录全民红包App；</span>
            <span class="ruleText">4.您的好友需要完成现金红包任务您才能获取额外任务奖励。</span>
            <span class="ruleText">5.定时红包，大转盘等不计入邀请的任务奖励；</span>
            <span class="ruleText">6.本活动最终解释权归全民红包所有。</span>
            <span class="ruleText">温馨提示：全民红包会对设备信息，ip信息等进行限制，切勿使用虚假手段获取邀请收入，一经发现，直接封号。如有疑问，请咨询客服，QQ：800160354。</span>
        </div>
    </div>
</body>
</html>