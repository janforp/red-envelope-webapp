<html>
    <head>

        <title>安卓专享任务</title>

        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="format-detection" content="telephone=no">

        <link rel="stylesheet" type="text/css" href="/client/css/timed-mission/timed-list.css">
        <link rel="stylesheet" type="text/css" href="/plugins/weui/css/weui.min.css">
        <script src="/plugins/jquery/jquery-1.9.1.js" type="text/javascript"></script>
        <script src="/client/js/task/time-list.js" type="text/javascript" rel="script"></script>
        <script src="/plugins/weui/js/weui.js" type="text/javascript"></script>

        <script>

            window.onload = function(){
                tips.loading('加载中...');
                appData();
            };

            function appData() {

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
                            getDataList();
                        },
                        error:function () {

                        }
                    });

                }else {
                    tips.hideLoading();
                }

            }

            function getDataList() {

                var timestamp = Date.parse(new Date());
                var map = {
                    't': timestamp
                };

                var str = JSON.stringify(map);
                var sign = userSign.getSign(str);

                $.ajax({
                    url:"/c/p/a/task/dataList",
                    type:"POST",
                    dataType:"JSON",
                    data:{
                        t: timestamp,
                        sign: sign
                    },
                    success:function (data) {

                        if(data.code == '0') {

                            var list = data.data;

                            var body = $("#appList");
                            var newTbody = "";

                            if(list.length <= 0) {
                                tips.hideLoading();
                                dialog.alert("提示", "暂时没有任务");
                            }else {
                                for(var i = 0; i < list.length; i++) {
                                    var v = list[i];
                                    newTbody +=
                                            "<div class='lineDiv' onclick=\"getMission('"+v.keywordId+"','"+v.desc+"','"+v.marketPackage+"','"+v.marketUrl+"','"+v.appPackage+"');\" d='"+v.desc+"'>" +
                                            "<div class='imgDiv'><img src='"+v.appIcon+"'></div>" +
                                            "<div class='keyDiv'>" +
                                            "<div class='keyWord'>" +
                                            "<span>"+v.keyword+"</span>" +
                                            "</div>" +
                                            "<div class=\"market\">" +
                                            "<span>"+v.appMarket+"</span><span>"+v.appLabel+"</span><span>剩"+v.leftNum+"份</span>" +
                                            "</div>" +
                                            "</div>" +
                                            "<div class=\"moneyDiv\">";

                                    if(v.desc == '0') {
                                        newTbody += "<span>进行中</span>";
                                    }else {
                                        newTbody += "+<span class=\"money\">"+v.desc+"</span><span class=\"unit\">元</span>";
                                    }
                                    newTbody += "</div></div>";
                                }
                                tips.hideLoading();
                                body.html(newTbody);
                            }

                        }

                    },
                    error:function () {

                    }
                });
            }

        </script>

    </head>
    <body>

        <!--content start -->
        <div id="appList" class="content"></div>
        <!--content over -->

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

        <%@include file="/WEB-INF/views/tpl/oneButtonFrame.jsp" %>
        <%@include file="/WEB-INF/views/tpl/twoButtonFrame.jsp" %>
        <%@include file="/WEB-INF/views/tpl/wuiTplInDetail.jsp" %>
    </body>
</html>