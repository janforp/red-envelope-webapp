<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>成为高级赚客</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <c:if test="${isIos == true}">
        <script type="text/javascript" src="/plugins/ios-jsBridge/ios-jsBridge.js"></script>
    </c:if>

    <style>

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-size: 62.5%;
            background-color: #FFFFFF;
            width: 100%;
            overflow-x: hidden;
        }

        .content{
            width: 100%;
            height: auto;
            background-color: #ffffff;
            margin-left: auto;
            margin-right: auto;
            max-width: 414px;
            position: relative;
        }

        .banner {
            width: 100%;
            text-align: center;
            margin-bottom: 0.5rem;
        }

        .banner img {
            width: 100%;
        }

        .text {
            margin: 1.1rem;
        }

        .text span{
            font-size: 1.0rem;
            line-height: 1.4rem;
        }

        .qq {
            margin: 1.1rem 1.1rem 0 1.1rem;
            text-align: center;
        }

        .qq div{
            margin-top: 0.9rem;
        }

        .qq a{
            width: 40%;
            height: 30px;
            color: white;
            background-color: #F5591F;
            border-radius: 45px;
            font-size: 0.9rem;
            display: inline-block;
            text-decoration: none;
            text-align: center;
            line-height: 30px;
        }

    </style>

    <script>

        var isApp = ${isApp};
        var isIos = ${isIos};

        function openQQGroup(url) {

            if(isApp) {

                if(isIos) {

                    var map = {
                        'url': url
                    };
                    var str = JSON.stringify(map);
                    WebViewJavascriptBridge.callHandler('openQQGroup', str, function () {
                    });

                }else {
                    userSign.openSystemBrowser(url);
                }

            }else {
                window.location.href = url;
            }

        }

    </script>

</head>
<body>
<div class="content">
    <div class="banner">
        <img src="/client/img/zhuanke_banner.png">
    </div>
    <div class="text">
        <span>
            嫌任务赚钱少？立即加群成为高级赚客，任务多奖励高，完成任务后客服直接发放现金，更多福利等你来抢，赶快加入吧~（点击按钮即可加入）
        </span>
    </div>
    <div class="qq">
        <div>
            <a href="javaScript:openQQGroup('https://jq.qq.com/?_wv=1027&k=42fNQyr');">加入QQ1群</a>
            &nbsp;&nbsp;
            <a href="javaScript:openQQGroup('https://jq.qq.com/?_wv=1027&k=42fNJTg');">加入QQ2群</a>
        </div>
        <div>
            <a href="javaScript:openQQGroup('https://jq.qq.com/?_wv=1027&k=42fNKR6');">加入QQ3群</a>
            &nbsp;&nbsp;
            <a href="javaScript:openQQGroup('https://jq.qq.com/?_wv=1027&k=42fN42q');">加入QQ4群</a>
        </div>
        <div>
            <a href="javaScript:openQQGroup('https://jq.qq.com/?_wv=1027&k=42fN59n');">加入QQ5群</a>
            &nbsp;&nbsp;
            <a href="javaScript:openQQGroup('https://jq.qq.com/?_wv=1027&k=42fNMwG');">加入QQ6群</a>
        </div>
        <div>
            <a href="javaScript:openQQGroup('https://jq.qq.com/?_wv=1027&k=42fNNh1');">加入QQ7群</a>
            &nbsp;&nbsp;
            <a href="javaScript:openQQGroup('https://jq.qq.com/?_wv=1027&k=42fNVkG');">加入QQ8群</a>
        </div>
    </div>
    <div class="text">
        <span>
            入群注意事项：<br>
            1.请选择群加入，如果群已满的话请尝试别的群。<br>
            2.加入群后请联系管理员提供您的手机号认证，没有注册的用户请先注册。<br>
            3.长时间潜水或者不做任务的用户将会被移出群。<br>
            4.入群后请不要灌水或者发布与任务无关的内容，否则移除群。<br>
        </span>
    </div>
</div>
</body>
</html>

