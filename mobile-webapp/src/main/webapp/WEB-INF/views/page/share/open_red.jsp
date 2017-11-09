<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <title>红包来了</title>
    <script src="/plugins/jquery/jquery-1.9.1.js" type="text/javascript"></script>
    <!--微信JS-SDK-->
    <script src=" https://res.wx.qq.com/open/js/jweixin-1.0.0.js "></script>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        body {
            font-size: 62.5%;
            border: none;
            margin: 0;
            padding: 0;
        }
        .content{
            width: 100%;
            height: 100%;
            margin-left: auto;
            margin-right: auto;
            max-width: 414px;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }

        .textDiv{

            width: 100%;
            padding: 1rem;
            text-align: center;
            font-size: 1.5rem;
            /*中奖的时候才显示*/
            display: none;
            /*固定在最顶部*/
            position: fixed;
            top: 0;
        }
        .imgDiv{
            padding: 1rem;
            text-align: center;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .imgDiv img{
            width: 100%;
            height: auto;
        }

        #failImg{
            display: none;
        }

        #successImg{
            display: none;
        }

        a {
            text-decoration: none;
        }

    </style>
    <script>

        var isAjax = true;

        $(function(){

            var isFirst = '${firstTime}';
            var shareTitle = '${vo.shareTitle}';
            var shareUrl = '${vo.shareUrl}';
            var shareImg = '${vo.shareImg}';
            var shareDesc = '${vo.shareDesc}';
            var shareType = '${vo.shareType}';
            var shareDataurl = '${vo.shareDataurl}';
            var userId = '${userId}';
            var missionId = '${missionId}';

            wx.config({
                debug: false,
                appId: '${signVo.appId}',
                timestamp: '${signVo.timestamp}',
                nonceStr: '${signVo.nonceStr}',
                signature: '${signVo.signature}',
                jsApiList:['onMenuShareTimeline','hideAllNonBaseMenuItem','onMenuShareAppMessage','showMenuItems']

            });

            wx.ready(function(){

                wx.hideAllNonBaseMenuItem();

                wx.showMenuItems({
                    menuList: ['menuItem:share:timeline','menuItem:share:appMessage']
                });

                // 获取“分享到朋友圈”按钮点击状态及自定义分享内容接口
                wx.onMenuShareTimeline({
                    title: shareTitle,
                    link: shareUrl,
                    imgUrl: shareImg,
                    success: function () {//分享成功之后
                        //1.隐藏提示分享图片
                        $("#failImg").hide();
                        //2.隐藏成功图片及获得多少钱
                        $(".textDiv").hide();
                        $("#successSpan").text("");
                        $("#successImg").hide();

                        //3.显示点击打开红包图片
                        $("#openImg").show();
                    },
                    cancel: function () {}
                });
                // 获取“分享给朋友”按钮点击状态及自定义分享内容接口
                wx.onMenuShareAppMessage({
                    title: shareTitle,
                    desc: shareDesc,
                    link:shareUrl,
                    imgUrl: shareImg,
                    type: 'link',
                    success:function () {//发送成功之后
                        //1.隐藏提示分享图片
                        $("#failImg").hide();
                        //2.隐藏成功图片及获得多少钱
                        $(".textDiv").hide();
                        $("#successSpan").text("");
                        $("#successImg").hide();

                        //3.显示点击打开红包图片
                        $("#openImg").show();
                    },
                    cancel:function () {}
                });

            });
            //发生异常时调用
            wx.error(function () {
            });

            if (isFirst == '0') {//第二次进入,则需要先分享,否则可以一只退出,一只抽奖

                //1.隐藏打开红包图片
                $("#openImg").hide();
                //2.隐藏成功图片及获得多少钱
                $(".textDiv").hide();
                $("#successSpan").text("");
                $("#successImg").hide();

                //2.显示提示分享图片
                $("#failImg").show();
            }

        });

        //验证通过时调用

        /**
         * 抽奖
         */
        function getMoney(missionId, userId) {

            if(isAjax){
                isAjax = false;
            }else {
                return;
            }

            $.ajax({
                url:"/c/p/share/getMoney/"+missionId+"/"+userId,
                type:"POST",
                dataType:"JSON",
                data:{},
                success:function (data) {

                    if (data.code == '0') { //成功
                        //1.隐藏打开红包图片
                        $("#openImg").hide();
                        //2.隐藏没有获得红包图片
                        $("#failImg").hide();

                        //3.显示成功图片及获得多少钱
                        $("#successSpan").text("恭喜您获得"+data.msg+"元现金,稍后将发入您的微信中");
                        $(".textDiv").show();
                        $("#successImg").show();

                    }else if(data.code == '12'){//弹框:活动已经结束

                        showOneButton("提示","本活动已结束");

                    } else {//没有领到红包
                        //1.隐藏打开红包图片
                        $("#openImg").hide();
                        //2.隐藏成功图片及获得多少钱
                        $(".textDiv").hide();
                        $("#successSpan").text("");
                        $("#successImg").hide();

                        //2.显示提示分享图片
                        $("#failImg").show();
                    }
                },
                complete:function(){
                    isAjax = true;
                }
            });
        };
    </script>
</head>
</head>
<body>
<div class="content">
    <!--成功获得红包时显示的话 -->
    <div class="textDiv">
        <span id="successSpan"></span>
    </div>
    <!--各种情况下隐藏或显示的图片 -->
    <div class="imgDiv">
        <!--成功获得红包时 -->
        <img id="successImg" src="${successImg}">
        <!--没有获得红包时:图片会提现用户分享获得机会 -->
        <img id="failImg" src="${failImg}">
        <!--可以点击领取红包时 -->
        <img id="openImg" href="javaScript:;" onclick="getMoney('${missionId}','${userId}');" src="${openImg}">
    </div>
</div>
<%@include file="/WEB-INF/views/tpl/oneButtonFrame.jsp" %>
</body>
</html>