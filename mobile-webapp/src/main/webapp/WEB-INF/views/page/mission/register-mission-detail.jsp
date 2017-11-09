<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${detail.missionTitle}</title>
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
    <link rel="stylesheet" type="text/css" href="/client/css/recommend-mission/recommend-mission.css">
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
        var isLogin = '${isLogin}';
        var taskId = '${detail.taskId}';
        var text = '${detail.verifyText}';
        var imgText = '${detail.verifyImg}';
        var imgNum = '${detail.imgNum}';
        var missionId = '${detail.missionId}';
        var leftTime = '${detail.leftTime}';
        var verifyRequire = '${detail.verifyRequire}';
        var taskStatus = '${detail.taskStatus}'; // 状态 0-进行中 1-审核中 2-审核通过 3-未通过 4-已过期
        var missionType = '${detail.missionType}';//'任务分类,0:推荐任务, 1:兑换码红包,2:注册任务',
        /*安卓,IOS公共方法  start*/

        /**
         * 点击复制
         * @param word
         */
        function copyWord(word) {
            if(missionType != 1 && taskStatus == ''){
                dialog.alert('提示','请先领取任务');
                return;
            }
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
        function gotoAnotherUrl() {
            window.location.href='/c/p/a/transit/go';
        }
        /**
         * 下载app
         * @param appName       名字
         * @param appPackageUrl 下载链接
         * @param flag          0:手动    1:自动
         */
        function downloadApp(appName,appPackageUrl,flag) {
            if(missionType != 1 && taskStatus == ''){
                dialog.alert('提示','请先领取任务');
                return;
            }
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
            if(missionType != 1 && taskStatus == ''){
                dialog.alert('提示','请先领取任务');
                return;
            }
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

    </script>

</head>
<body>
<div class="content"><!--content start-->
    <div class="headDiv">
        <div class="imgDiv">
            <img src="${detail.missionIcon}" class="openImg">
        </div>
        <div class="titleDiv">
            <span>${detail.missionTitle}</span>
            <span>截止日期: ${detail.endTime}</span>
        </div>
        <div class="moneyDiv">
            <c:choose>
                <c:when test="${detail.moneyStatus == '0'}">
                    +<span class="money">${detail.minMoney}</span><span class="unit">元</span>
                </c:when>
                <c:otherwise>
                    至少<span class="money">${detail.minMoney}</span><span class="unit">元</span>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <div class="labelDiv">
        <c:forEach items="${detail.missionLabel}" var="label">
            <span class="label">${label}</span>
        </c:forEach>
    </div>
    <div class="title">
        <span>任务简介</span>
    </div>
    <div class="descDiv">
        <span>${detail.missionDesc}</span>
    </div>

    <div class="title">
        <span>任务步骤</span><span style="font-size: 0.8rem">图片点击可放大</span>
    </div>
    <div class="stepDiv">
        <c:forEach items="${stepList}" var="step">
            <div class="oneStepDiv">
                <div class="steps">
                    <span>第${step.stepNum}步:</span>
                </div>
                <div class="stepContent">
                    <span class="stepDesc">${step.stepContent}${step.stepBtn}</span>
                    <div class="stepImg" style="display: flex">
                        <c:if test="${step.stepImgs != null}">
                            <c:forEach items="${step.stepImgs}" var="imgUrl">
                                <div style="width: 55px;height: 55px;overflow: hidden;display: flex;justify-content: center;align-items: center;border: 1px solid #EFEFEF;margin-right: 5px;">
                                    <img src="${imgUrl}" class="openImg" style="width: 55px;height: auto">
                                </div>
                            </c:forEach>
                        </c:if>
                    </div>
                </div>
            </div>
        </c:forEach>
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