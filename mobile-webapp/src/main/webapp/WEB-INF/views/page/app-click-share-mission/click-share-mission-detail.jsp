<!--需审核任务详情页面-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>分享任务</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <script type="text/javascript" src="/plugins/jquery/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="/plugins/weui/js/weui.js"></script>
    <link rel="stylesheet" type="text/css" href="/plugins/weui/css/weui.css">
    <c:if test="${isIos == true}">
        <script type="text/javascript" src="/plugins/ios-jsBridge/ios-jsBridge.js"></script>
    </c:if>
    <link rel="stylesheet" type="text/css" href="/client/css/app-click-share-mission/app-click-share-mission.css">
    <link rel="stylesheet" type="text/css" href="/client/css/base/red-envelope-base.css">
    <script>
        var isIos = '${isIos}';
        var imgUrl = '${vo.missionImg}';

        /*安卓,IOS公共方法  start*/
        /**
         * 点击复制
         * @param word
         */
        function copyWord(word) {
            if(isIos == 'true'){//苹果复制
                var map = {
                    'word': word
                };
                var str = JSON.stringify(map);
                WebViewJavascriptBridge.callHandler('copyScrapbook', str, function (isCopySuccess) {

                    if (isCopySuccess){
                        //提示框:复制成功
                        tips.suc("操作成功",2000);
                    }else{
                        //提示框:复制失败
                        tips.err("操作失败",3000)
                    }
                });
            }else {//安卓复制
                var isCopySuccess = userSign.copyToClipboard(word);

                if (isCopySuccess){
                    //提示框:复制成功
                    tips.suc("操作成功",2000);
                }else{
                    //提示框:复制失败
                    tips.err("操作失败",3000)
                }
            }
        };
        /**
         * 打开APP
         */
        function openApp(appPackage) {
            if(isIos == 'true'){//苹果:打开APP
            }else{//安卓:打开APP
                //1.先判断是否存在
                var isAppExist = userSign.isPackageExist(appPackage);

                if(isAppExist){//存在,打开APP

                    userSign.shareWX_ToFriendCircleWithImg(imgUrl)

                }else{//不存在,则调用下载app方法

                    dialog.alert("提示","请先下载APP");
                }
            }
        };
        /**
         * 打开放大图片
         */
        function zoomImg(url) {
            if(isIos == 'true'){
                var map = {
                    'img': url
                };
                var str = JSON.stringify(map);
                WebViewJavascriptBridge.callHandler('zoomOutImage', str, function (response) {
                });
            }else {
                userSign.shoWebviewImage(url);
            }
        }

        /**
         * 点击按钮直接保存图片到相册
         */
        function saveImg(url) {
            if(isIos == 'true'){
                var map = {
                    'img': url
                };
                var str = JSON.stringify(map);
                WebViewJavascriptBridge.callHandler('saveImg', str, function (response) {
                });
            }else {
                var isSuccess = userSign.preserveWebviewImage(url);
                if(isSuccess){
                    tips.suc("操作成功",2000);
                }else{
                    tips.err("操作失败",3000)
                }
            }
        }

        /*安卓,IOS公共方法  over*/

        function openWx() {
            var app = 'com.tencent.mm';
            if(isIos == 'true'){
                window.location.href='weixin://'
            }else {
                openApp(app);
            }
        }
    </script>

</head>
<body>
<div class="content"><!--content start-->
    <c:if test="${vo.totalClickTimes != 0}">
        <div class="tips red-bg-color">
            累积点击<span class="red-main-color">${vo.totalClickTimes}</span>次,累积收益<span class='red-main-color'>${vo.totalClickTimes*vo.money}</span>元
        </div>
    </c:if>
    <div class="headDiv">
        <div class="icon">
            <img src="${vo.missionIcon}">
        </div>
        <div class='titleMoney'>
            <div><span class="missionTitle" >${vo.missionTitle}</span></div>
            <div><span class="award">阅读奖励:&nbsp;<span style="color: #f96301">${vo.money}</span>元/次&nbsp;&nbsp;&nbsp;总奖励:&nbsp;<span style="color: #f96301">${vo.totalMoney}</span>元</span></div>
            <div><span class="partIn">剩余次数:&nbsp;${vo.leftClickTimes}次&nbsp;&nbsp;&nbsp;参与人数:&nbsp;${vo.partInNum}人</span></div>
        </div>
    </div>
    <div class="red-model-separator-line red-bg-color"></div>
    <div class="title">任务说明:</div>
    <div class="desc">
        ${vo.missionDesc}&nbsp;&nbsp;&nbsp;&nbsp;(可以将链接发送给好友或者群里)
        <a href="javaScript:zoomImg('${vo.exampleImg}');" class="clickA red-secondary-color">点击查看示例图片</a>
    </div>
    <div class="red-model-separator-line red-bg-color"></div>
    <div class='textAndShareImg'>
        <div class="textDiv">
            <span class="subTitle">需分享的文字</span>
            <div class="text">
                ${vo.missionText}
            </div>
            <div class="btnDiv">
                <a href="javaScript:copyWord('${vo.missionText}${vo.shortUrl}');" class="funBtn">复制</a>
            </div>

        </div>
        <div style="width: 4%"></div>
        <div class="shareImgDiv">
            <span class="subTitle">需分享的图片</span>
            <div class="img">
                <img src="${vo.missionImg}" onclick="zoomImg('${vo.missionImg}')">
            </div>
            <div class="btnDiv">
                <a href="javaScript:saveImg('${vo.missionImg}');" class="funBtn">保存</a>
            </div>
        </div>
    </div>
    <div style="width: 100%;padding-top: 60px;">
        <button id="openWX" style="position: fixed;bottom: 0;width: 100%;height: 50px;background-color: #10d11e;border: none" onclick='openWx();'>
            <span style="letter-spacing: 2px;color: white;font-size:1.5rem;">去微信发布</span>
        </button>
    </div>

</div><!--content over-->

<div class="template-code">

    <div id="global-toast" style="display: none;">
        <div class="weui_mask_transparent"></div>
        <div class="weui_toast">
            <i class="weui_icon_toast"></i>
            <p class="weui_toast_content">&nbsp;</p>
        </div>
    </div>

    <div id="global-toast-loading" class="weui_loading_toast" style="display: none;">
        <div class="weui_mask_transparent"></div>
        <div class="weui_toast">
            <div class="weui_loading">
                <div class="weui_loading_leaf weui_loading_leaf_0"></div>
                <div class="weui_loading_leaf weui_loading_leaf_1"></div>
                <div class="weui_loading_leaf weui_loading_leaf_2"></div>
                <div class="weui_loading_leaf weui_loading_leaf_3"></div>
                <div class="weui_loading_leaf weui_loading_leaf_4"></div>
                <div class="weui_loading_leaf weui_loading_leaf_5"></div>
                <div class="weui_loading_leaf weui_loading_leaf_6"></div>
                <div class="weui_loading_leaf weui_loading_leaf_7"></div>
                <div class="weui_loading_leaf weui_loading_leaf_8"></div>
                <div class="weui_loading_leaf weui_loading_leaf_9"></div>
                <div class="weui_loading_leaf weui_loading_leaf_10"></div>
                <div class="weui_loading_leaf weui_loading_leaf_11"></div>
            </div>
            <p class="weui_toast_content">&nbsp;</p>
        </div>
    </div>

    <div id="global-dialog-confirm" class="weui_dialog_confirm" style="display: none;">
        <div class="weui_mask"></div>
        <div class="weui_dialog">
            <div class="weui_dialog_hd"><strong class="weui_dialog_title">&nbsp;</strong></div>
            <div class="weui_dialog_bd">&nbsp;</div>
            <div class="weui_dialog_ft">
                <a href="javascript:;" class="weui_btn_dialog default btn_dialog_cancel">取消</a>
                <a href="javascript:;" class="weui_btn_dialog primary btn_dialog_ok">确定</a>
            </div>
        </div>
    </div>

    <div id="global-dialog-alert" class="weui_dialog_alert" style="display: none;">
        <div class="weui_mask"></div>
        <div class="weui_dialog">
            <div class="weui_dialog_hd"><strong class="weui_dialog_title">&nbsp;</strong></div>
            <div class="weui_dialog_bd">&nbsp;</div>
            <div class="weui_dialog_ft">
                <a href="javascript:;" class="weui_btn_dialog primary">确定</a>
            </div>
        </div>
    </div>

</div>



</body>
</html>