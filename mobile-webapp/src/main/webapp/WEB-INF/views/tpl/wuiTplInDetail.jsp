
<!--金币商城,商品需求下的微信ui-->
<link rel="stylesheet" type="text/css" href="/plugins/weui/css/weui.css">

<div id="toast" style="display: none">
    <div class="weui_mask_transparent"></div>
    <div class="weui_toast">
        <i class="weui_icon_toast"></i>
        <p class="weui_toast_content" id="msg"></p>
    </div>
</div>

<div class="weui_dialog_confirm" id="alertLogin" style="display: none">
    <div class="weui_mask"></div>
    <div class="weui_dialog">
        <div class="weui_dialog_hd"><strong class="weui_dialog_title">提示</strong></div>
        <div class="weui_dialog_bd">请在微信中打开</div>
        <div class="weui_dialog_ft">
            <a href="#" onclick="hideAlertLogin();" class="weui_btn_dialog primary">确定</a>
        </div>
    </div>
</div>


<div id="loadingToast" class="weui_loading_toast" style="display: none;">
    <div class="weui_mask_transparent"></div>
    <div class="weui_toast">
        <div class="weui_loading">
            <!-- :) -->
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
        <p id="toastDesc" class="weui_toast_content">数据处理中</p>
    </div>
</div>
