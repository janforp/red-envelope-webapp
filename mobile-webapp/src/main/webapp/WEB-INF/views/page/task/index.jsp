<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>安卓专享任务</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="/plugins/weui/css/weui.min.css"/>
    <script src="/plugins/jquery/jquery-1.9.1.js" type="text/javascript"></script>
    <script src="/plugins/weui/js/weui.js" type="text/javascript"></script>

    <script>

        window.onload = function(){

            tips.loading('正在加载中');

            listPage();

        };

        function listPage() {

            var list = userSign.getAppList();

            if(list.length > 0) {

                var map = {
                    'app':list
                };

                var str = JSON.stringify(map);
                var sign = userSign.getSign(str);

                $.ajax({
                    url:"/c/p/a/task/app",
                    type:"POST",
                    dataType:"JSON",
                    data:{
                        app: list,
                        sign: sign
                    },
                    success:function (data) {
                        if(data.code == 0) {
                            window.location.href = "/c/p/a/task/timedList";
                        }
                    },
                    error:function () {

                    }
                });


            }else {

                tips.hideLoading();

            }

        }

    </script>

</head>
<body>

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
