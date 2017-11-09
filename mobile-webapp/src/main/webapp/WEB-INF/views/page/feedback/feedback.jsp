<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>意见反馈</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta name="format-detection" content="telephone=no">

    <link rel="stylesheet" href="/plugins/weui/css/weui.min.css"/>
    <link rel="stylesheet" href="/client/css/feedback.css"/>

    <script type="text/javascript" src="/plugins/jquery/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="/plugins/weui/js/weui.js"></script>

    <c:if test="${isIos == true}">
        <script type="text/javascript" src="/client/js/feedback_ios.js"></script>
    </c:if>
    <c:if test="${isIos == false}">
        <script type="text/javascript" src="/client/js/feedback_andriod.js"></script>
    </c:if>

</head>
<body>
<div class="container">
    <div class="content">
        <div class="title">
            <span>您的建议:</span>
        </div>
        <div class="detail">
            <textarea placeholder="请填写您的建议(必填)" id="feedbackDetail"></textarea>
        </div>
        <div class="title">
            <span>联系方式:</span>
        </div>
        <div class="detail">
            <input type="text" placeholder="请填写您的联系方式(可不填)" id="userContact">
        </div>
        <div class="btn">
            <a href="javascript:;" id="save">提交</a>
        </div>
    </div>

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

</div>
</body>
</html>
