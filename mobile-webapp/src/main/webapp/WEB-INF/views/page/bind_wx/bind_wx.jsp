<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>绑定微信</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta name="format-detection" content="telephone=no">

    <link rel="stylesheet" type="text/css" href="/plugins/weui-1.0.2/css/weui.min.css">
    <link rel="stylesheet" type="text/css" href="/client/css/bind_wx.css">

    <script src="/plugins/jquery/jquery-1.9.1.js" type="text/javascript"></script>
    <script src="/plugins/weui-1.0.2/js/weui.js"></script>

    <script src=" https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <script src="/plugins/wxNotShare/wxNotShare.js"></script>

    <script>

        var openId = "${openId}";
        var nickname = "${nickname}";
        var icon = "${icon}";

        function sendSms(){

            var phone = $("#phone").val().trim();

            if (isNaN(phone) || phone.length != 11){

                dialog.alert("提示", "请输入11位手机号", null);

            }else {

                $.ajax({
                    url: "/c/p/login/getSmsCode",
                    type: "POST",
                    dataType: "JSON",
                    data:{
                        phone: phone
                    },
                    success:function (data) {

                        if(data.code == 0){ // 验证码发送成功

                            dialog.confirm("提示", "短信验证码已经发送");

                            //倒计时
                            var t = 90 ;
                            var a = setInterval(function () {
                                t--;
                                $("#getCode").text(t+"s");
                                if (t == 0) {
                                    clearInterval(a);
                                    $("#getCode").text("获取验证码");
                                }
                            },1000);

                        }else if (data.code == 10){ // 该手机号未注册

                            dialog.confirm("提示", data.msg, function(){
                                window.location.href = "/c/p/invitation/qrCode";
                            }, null);

                        }else{  //其他异常

                            dialog.alert("提示", data.msg);

                        }

                    },
                    error:function () {

                    }
                });

            }
        }


        /**
         * 点击提交
         * @param openId
         * @param nickname
         * @param icon
         */
        function bindSubmit(openId,nickname,icon) {

            var phone = $("#phone").val().trim();

            if (isNaN(phone) || phone.length != 11){

                dialog.alert("提示", "请输入11位手机号", null);

            }else {

                var smsCode = $("#smsCode").val().trim();

                if (smsCode == ""){

                    dialog.alert("提示", "请输入短信验证码", null);

                }else {

                    $.ajax({
                        url: "/c/p/bind/bindSubmit",
                        type: "POST",
                        dataType: "JSON",
                        data:{
                            phone: phone,
                            smsCode: smsCode,
                            openId: openId,
                            nickname: nickname,
                            icon: icon},
                        success:function (data) {

                            if (data.code == 0){ //登录成功

                                dialog.alert("提示", data.msg, function(){
                                    window.location.href="/c/p/bind/bindSuccess?msg="+data.msg;
                                });

                            }else {

                                dialog.alert("提示", data.msg, function(){
                                    window.location.href="/c/p/bind/bindSuccess?msg="+data.msg;
                                });

                            }
                        },
                        error:function () {

                        }
                    });

                }

            }

        }

    </script>

</head>

<body>
    <div class="content">

        <input type="hidden" id="appId" value="${share.appId}">
        <input type="hidden" id="timestamp" value="${share.timestamp}">
        <input type="hidden" id="nonceStr" value="${share.nonceStr}">
        <input type="hidden" id="signature" value="${share.signature}">

        <div class="cellphoneDiv">
            <div class="phoneImg">
                <img src="/client/img/login/cellphone.png">
            </div>
            <div class="phoneInput">
                <input type="number" id="phone" placeholder="请输入您的手机号">
            </div>
        </div>

        <div class="smsCodeDiv">
            <div class="codeImg">
                <img src="/client/img/login/password.png">
            </div>
            <div class="codeInput">
                <input type="number" id="smsCode" placeholder="请输入验证码">
            </div>
            <div class="getImgCode">
                <button id="getCode" onclick="sendSms();"><span id="closeSpan">获取验证码</span></button>
            </div>
        </div>
        <div class="userAgreement">
            <a href="http://image.lswuyou.cn/agreement.html">
                <span>点击提交表示您已经同意</span><span class="blueSpan">&lt;&lt;用户协议&gt;&gt;</span>
            </a>
        </div>

        <div class="loginButtonDiv" id="submit">
            <button onclick="bindSubmit(openId, nickname, icon);">提交</button>
        </div>

        <%@include file="/plugins/weui-1.0.2/jsp/weui.jsp"%>

    </div>

</body>
</html>
