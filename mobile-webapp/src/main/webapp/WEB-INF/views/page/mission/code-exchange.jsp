<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>兑换</title>
        <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
        <meta name="format-detection" content="telephone=no">

        <link rel="stylesheet" type="text/css" href="/plugins/weui-1.0.2/css/weui.min.css">

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
                background-color: #F0F0F0;
            }
            .content{
                width: 100%;
                height: auto;
                background-color: #F0F0F0;
                margin-left: auto;
                margin-right: auto;
                max-width: 414px;
                text-align: center;
            }
            .codeInput {
                width: 100%;
                margin-top: 3rem;
                margin-bottom: 2rem;
            }
            .codeInput img {
                width: 100px;
                height: 100px;
            }
            .codeInput input{
                background-color: #FFFFFF;
                width: 230px;
                text-align: center;
                height: 2.5rem;
                border-radius: 1px;
                border:none;
                font-size: 1.2rem;
            }
            button{
                width: 230px;
                background-color: #D9D9D9;
                height:2.5rem;
                border-radius: 2px;
                border:none;
            }
            .buttonSpan {
                font-size: 1.5rem;line-height: 2rem;color: white
            }

        </style>

        <script src="/plugins/jquery/jquery-1.9.1.js" type="text/javascript"></script>
        <script src="/plugins/weui-1.0.2/js/weui.js" type="text/javascript"></script>

        <script>

            function exchangeCss() {
                var code = $("#code").val();
                if (code != null || code != "" || code != undefined) {
                    $("#exchange").css("background-color","#FF7333");
                    $("#code").css("border","1px solid #FF7333");
                }
            }

            /**
             * 点击 '兑换' 按钮
             */
            $(document).on("click", "#exchange", function () {

                var code = $("#code").val();
                var codeId = $("#codeId").val();

                if (code == null || code == "" || code == undefined) {
                    dialog.alert("提示", "请输入兑换码");
                    return;
                }

                tips.loading("兑换中...");

                $.ajax({
                    url: "/c/p/code/exchange",
                    type: "POST",
                    dataType: "JSON",
                    data: {
                        code: code,
                        codeId: codeId
                    },
                    success:function (data) {
                        tips.hideLoading();
                        dialog.alert("提示", data.msg);
                    },
                    error:function () {

                    }
                });

            })

        </script>

    </head>

    <body>
        <div class="content">
            <div class="codeInput">
                <img src="/client/img/coinmall/rmb.png" >
            </div>
            <div class="codeInput">
                <input type="text" id="code" onfocus="exchangeCss();" placeholder="请输入兑换码">
            </div>
            <button id="exchange"><span class="buttonSpan" >兑&nbsp;&nbsp;&nbsp;&nbsp;换</span></button><br>
            <input type="hidden" id="codeId" value="${codeId}">
        </div><!--content结束 -->
        <%@include file="/plugins/weui-1.0.2/jsp/weui.jsp" %>
    </body>
</html>
