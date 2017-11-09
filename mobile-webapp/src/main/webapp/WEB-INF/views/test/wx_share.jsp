<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>分享</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta name="format-detection" content="telephone=no">
    <script src="/plugins/jquery/jquery-1.9.1.js" type="text/javascript"></script>
    <script src=" https://res.wx.qq.com/open/js/jweixin-1.0.0.js "></script>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body {
            font-size: 62.5%;
            background-color: #FA494B;
            border: none;
            margin: 0;
            padding: 0;
        }
        .content{
            width: 100%;
            height: auto;
            margin-left: auto;
            margin-right: auto;
            max-width: 414px;
            background-color: #A0C5E8;
        }

        img{
            width: 100px;
            height: 100px;
        }
    </style>
    <script>
        var targetUrl = location.href.split('#')[0];
        $.ajax({
            url:"/c/p/wxSign/getSignVo",
            type:"POST",
            dataType:"JSON",
            data:{url:targetUrl},
            success:function (data) {
                wx.config({
                    debug:false,
                    appId:data.data.appId,
                    timestamp:data.data.timestamp,
                    nonceStr:data.data.nonceStr,
                    signature:data.data.signature,
                    jsApiList:[ 'checkJsApi', 'onMenuShareTimeline','onMenuShareAppMessage','hideMenuItems']

                });
            }
        });

        wx.ready(function(){

            wx.hideMenuItems({
                menuList: [
                    'menuItem:share:qq',
                    'menuItem:share:weiboApp',
                    'menuItem:favorite',
                    'menuItem:share:facebook',
                    'menuItem:share:QZone'], // 要隐藏的菜单项，只能隐藏“传播类”和“保护类”按钮，所有menu项见附录3
                success:function(){}
            });
        });

        $(document).ready(

            function () {

                var isFirst = '${firstTime}'
            }
        );

    </script>
</head>
<body>
<div class="content">

    <img src="/client/img/appPraise/baidu.png">
</div><!--content结束 -->
</body>
</html>
