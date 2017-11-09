<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${welfare.welfareTitle}</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <script type="text/javascript" src="/plugins/jquery/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="/plugins/weui/js/weui.js"></script>
    <c:if test="${isIos == true}">
        <script type="text/javascript" src="/plugins/ios-jsBridge/ios-jsBridge.js"></script>
    </c:if>
    <link rel="stylesheet" type="text/css" href="/client/css/welfare/welfare.css">
    <link rel="stylesheet" type="text/css" href="/plugins/weui/css/weui.css">
    <script type="text/javascript" src="/client/js/recommend/recommend_mission_detail.js"></script>
    <c:if test="${isIos == false}">
        <script type="text/javascript" src="/client/js/recommend/android_recommend_mission_detail.js"></script>
    </c:if>
    <c:if test="${isIos == true}">
        <script type="text/javascript" src="/client/js/recommend/ios_recommend_mission_detail.js"></script>
    </c:if>
    <script>
        var isIos = '${isIos}';

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
         * 点击前往某一个链接
         * @param word
         */
        function gotoAnotherUrl(url) {
            window.location.href=url;
        }
        /**
         * 下载app
         * @param appName       名字
         * @param appPackageUrl 下载链接
         * @param flag          0:手动    1:自动
         */
        function downloadApp(appName,appPackageUrl,flag) {
            if(isIos == 'true'){//苹果下载APP

            }else {//安卓下载APP

                userSign.downloadAPK(appName,appPackageUrl,flag)
            }
        };
        /**
         * 盘点APP是否存在
         */
        function isAppExist(appPackage) {

            if(isIos == 'true'){//IOS:判断app是否存在

            }else{//安卓:判断app是否存在
                var isAppExist = userSign.isPackageExist(appPackage);
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

                    userSign.turnOtherApp(appPackage);

                }else{//不存在,则调用下载app方法

                    dialog.alert("提示","请先下载APP");
                }
            }
        };
        /*安卓,IOS公共方法  over*/
        function goUrl() {
            window.location.href='${welfare.buttonUrl}'
        }

        /**
         * 打开放大图片
         */
        function zoomImg(url) {

            alert(url);
            return;

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


    </script>

</head>
<body>
<div class="content"><!--content start-->
    <img class="bannerImg" src="${welfare.welfareBanner}">
    <span class="welfareTitle">${welfare.welfareTitle}</span>
    <span class="welfareReward">${welfare.welfareReward}</span>
    <img class="merchantIcon" src="${welfare.merchantIcon}"><span class="merchantName">${welfare.merchantName}</span>

    <p class="separatorLine"></p>

    <div class="ueditorContent" id="ueditorContent">
        ${welfare.welfareDetail}
    </div>
    <div style="height:100px;background-color:  #f5f5f5"></div>
    <div class="footButtonDiv">
        <span class="participantsNum">${welfare.participantsNum}<span>人已参与</span></span><button style="background-color: #F96301;" onclick="goUrl();"><span>${welfare.buttonName}</span></button><!--未通过-->
    </div>

</div><!--content over-->
<%@include file="/WEB-INF/views/tpl/oneButtonFrame.jsp" %>
<%@include file="/WEB-INF/views/tpl/twoButtonFrame.jsp" %>

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