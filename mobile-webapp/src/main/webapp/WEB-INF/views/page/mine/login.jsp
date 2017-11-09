<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta name="format-detection" content="telephone=no">

    <script src="/plugins/jquery/jquery-1.9.1.js" type="text/javascript"></script>

    <style>

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body {
            -moz-user-select: none;
            -webkit-user-select: none;
            -ms-user-select: none;
            -khtml-user-select: none;
            user-select: none;
            font-size: 62.5%;
        }
        a {
            display:inline-block;
            text-decoration: none;
            width: 100%;
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

        .login{
            margin-top: 10rem;
            width: 100%;
            background-color: white;
            text-align: center;
        }

        .login a{
            display:inline-block;
            text-decoration: none;
            text-align: center;
            color: #FFFFFF;
            font-size: 1.0rem;
            line-height: 2.8rem;
            width: 7rem;
            height: 2.8rem;
            margin-top: 0.5rem;
            background-color: #FF4850;
            border-radius: 5px;
        }

        .login span {
            display:inline-block;
            text-decoration: none;
            text-align: center;
            color: #FFFFFF;
            font-size: 1.0rem;
            line-height: 2.8rem;
            width: 7rem;
            height: 2.8rem;
            margin-top: 0.5rem;
            background-color: #C3C3C3;
            border-radius: 5px;
        }

    </style>

    <c:if test="${isIos == true}">
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

            $(function(){
                login();
            });

            function login(){
                var map = {
                    't':1
                };
                var str = JSON.stringify(map);
                WebViewJavascriptBridge.callHandler('getSign', str, function (response) {

                });
            }

        </script>
    </c:if>

    <c:if test="${isIos == false}">
        <script>

            $(function(){
                login();
            });

            function login(){
                var map = {
                    't': 1
                };
                var str = JSON.stringify(map);
                userSign.getSign(str);
            }

        </script>
    </c:if>

</head>
<body>
<div class="content">
    <div class="login">
        <a href="javaScript:login();">请登录</a>
    </div>
</div>
</body>
</html>
