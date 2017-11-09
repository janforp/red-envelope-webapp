function setupWebViewJavascriptBridge(callback) {
    if (window.WebViewJavascriptBridge) {
        return callback(WebViewJavascriptBridge);
    }
    if (window.WVJBCallbacks) {
        return window.WVJBCallbacks.push(callback);
    }
    window.WVJBCallbacks = [callback];
    var WVJBIframe = document.createElement('iframe');
    WVJBIframe.style.display = 'none';
    WVJBIframe.src = 'wvjbscheme://__BRIDGE_LOADED__';
    document.documentElement.appendChild(WVJBIframe);
    setTimeout(function () {
        document.documentElement.removeChild(WVJBIframe)
    }, 0)
}

setupWebViewJavascriptBridge(function (bridge) {
    bridge.registerHandler('testJSFunction', function (data, responseCallback) {
        alert('JS方法被调用:' + data);
        responseCallback('js执行过了');
    })
});

/**
 * 点击'请登陆' 弹出 登录 页面
 */
function alertLogin() {
    var isWx = $("#isWx").val();
    if (isWx == "false") {
        var timestamp = Date.parse(new Date());
        var map = {
            't':timestamp
        };
        var str = JSON.stringify(map);
        WebViewJavascriptBridge.callHandler('getSign', str, function (response) {

        });
    }else {
        $.ajax({
            url:"/c/p/login/alertLogin",
            type:"POST",
            dataType:"JSON",
            data:{},
            success:function (data) {
                $(".captcha").click();
                //弹出
                $(".content").append("<div id='mask'></div>");
                $("#mask").addClass("mask").fadeIn("fast");
                $("#LoginBox").fadeIn("fast");

                $("body").css("overflow","hidden");
                $("body").css("position","fixed");
                $("body").css("height","800px");
                $("body").css("width","100%");
            },
            error:function () {

            }
        });
    }
}

/**
 * 点击 'X'   关闭 登录框
 */
$(document).on("click","#close",function () {
    
    $("#LoginBox").hide();
    $("#mask").removeClass("mask");
    $("#mask").remove();

    $("body").css("overflow","");
    $("body").css("position","");
    $("body").css("height","");

})

// /**
//  * 点击验证码图片 更换
//  */
// $(document).on("click",".captcha",function () {
//     $(".captcha").attr("src","/c/p/login/captcha.png?_v="+ new Date().getTime());
//     //清空之前输入的验证码
//     $("#vCode").val("");
// });

/**
 * 点击 '取消'  按钮
 */
$(document).on("click","#close",function () {

    $("#LoginBox").fadeOut("slow");
    $("#alert").val("");
    $("#alert").hide();
    $("#mask").remove();

    $("#download").hide();
    $("#login").show();
});

/**
 * 点击 '获取验证码' 按钮
 */
$(document).on("click",".getSmsCode",function () {

    $("#alert").val("");

    var phone = $("#phone").val().trim();
    if (isNaN(phone) || phone.length != 11){

        $("#alert").show();
        $("#alert").val("请输入11位手机号");
        setTimeout(function () {
            $("#alert").val("");
            $("#alert").hide();
        },2000);
        return;
    }

    // var vCode = $("#vCode").val().trim();
    //
    // if (vCode==""){
    //
    //     $("#alert").show();
    //     $("#alert").val("请输入图形验证码");
    //     setTimeout(function () {
    //         $("#alert").val("");
    //         $("#alert").hide();
    //     },2000);
    //     return;
    // }

    $.ajax({
        url:"/c/p/login/getSmsCode",
        type:"POST",
        dataType:"JSON",
        data:{phone:phone},
        success:function (data) {

            if(data.code == 0){ //验证码发送成功

                $(".buttonSpan").text("发送成功");
                //倒计时
                var t = 90 ;
                var a = setInterval(function () {
                    t--;
                    $("#buttonSpan").text(t+"s");
                    if (t == 0) {
                        clearInterval(a);

                        $("#buttonSpan").text("获取验证码");
                    }

                },1000);


            }else if (data.code == 10){ //该手机号未注册

                $("#alert").show();
                $("#alert").val(data.msg);
                //隐藏登录按钮,下载按钮显示
                $("#login").hide();
                $("#download").show();

                setTimeout(function () {
                    $("#alert").val("");
                    $("#alert").hide();
                },4000);

            }else{  //其他异常

                $("#alert").show();
                $("#alert").val(data.msg);

                setTimeout(function () {
                    $("#alert").val("");
                    $("#alert").hide();
                },4000);
            }

        },
        error:function () {

        }
    });

});


/**
 * 点击 '登录' 按钮
 */
$(document).on("click","#login",function () {

    var phone = $("#phone").val().trim();
    var openId = $("#openId").val().trim();
    var nickName = $("#nickName").val().trim();
    var codeId = $("#codeId").val().trim();

    if (isNaN(phone) || phone.length != 11){

        $("#alert").show();
        $("#alert").val("请输入11位手机号");
        setTimeout(function () {
            $("#alert").val("");
            $("#alert").hide();
        },2000);
        return;
    }

    var smsCode = $("#smsCode").val().trim();

    if (smsCode == ""){

        $("#alert").show();
        $("#alert").val("请输入短信验证码");
        setTimeout(function () {
            $("#alert").val("");
            $("#alert").hide();
        },2000);
        return;

    }
    
    $.ajax({
        url:"/c/p/login/login.do",
        type:"POST",
        dataType:"JSON",
        data:{
            phone: phone,
            openId: openId,
            nickName: nickName,
            smsCode: smsCode
        },
        success:function (data) {
            if (data.code == 0){ //登录成功

                $("#alert").show();
                $("#alert").val(data.msg);
                setTimeout(function () {
                    $("#alert").val("");
                    $("#alert").hide();
                },1000);

                //登录成功之后的操作
                $("#close").click();
                //重新加载此页面,会带着已经登录的状态,下面显示的是金币,并可以点击兑换
                window.location.href = "/c/p/coin/coinMall/"+codeId;

            }else {

                $("#alert").show();
                $("#alert").val(data.msg);

                setTimeout(function () {
                    $("#alert").val("");
                    $("#alert").hide();
                },4000);
            }
        },
        error:function () {
            
        }
    });

});

/**
 * 点击确定按钮
 */
function hideAlertLogin() {

    $("#alertMsg").text("");
    $("#desc").text("")

    $("#alertLogin").hide();
}

/**
 * 点击 金币商城 页面的 '签到按钮'
 */

function signUp() {

    var isWx = $("#isWx").val();
    var codeId = $("#codeId").val().trim();
    var sign;

    if (isWx == "false") {
        var timestamp = Date.parse(new Date());
        var map = {
            't':timestamp
        };
        var str = JSON.stringify(map);
        WebViewJavascriptBridge.callHandler('getSign', str, function (response) {
            sign = response;
            $.ajax({
                url:"/c/p/a/sign/sign",
                type:"POST",
                dataType:"JSON",
                data:{t: timestamp, sign: sign},
                success:function (data) {
                    $("#sign").text(data.msg);
                    setTimeout(function () {
                        $("#sign").text("已签到");
                    },2500);
                    //重新加载此页面,会带着已经登录的状态,下面显示的是金币,并可以点击兑换
                    window.location.href = "/c/p/coin/coinMall/"+codeId;

                },
                error:function () {

                }
            });
        });

    }else {
        $.ajax({
            url:"/c/p/a/sign/sign",
            type:"POST",
            dataType:"JSON",
            data:{},
            success:function (data) {
                $("#sign").text(data.msg);
                setTimeout(function () {
                    $("#sign").text("已签到");
                },2500);
                //重新加载此页面,会带着已经登录的状态,下面显示的是金币,并可以点击兑换
                window.location.href = "/c/p/coin/coinMall/"+codeId;
            },
            error:function () {

            }
        });
    }

}