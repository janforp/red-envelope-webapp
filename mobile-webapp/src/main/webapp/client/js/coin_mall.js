/**
 *   点击 '兑换码红包' 按钮
 *   
 *   跳转到输入兑换码页面
 */
$(document).on("click","#codeRedButton",function () {
    var codeId = $("#codeId").val();
    var isLogin = $("#isLogin").val();
    if (isLogin == "false") {
        $("#alertMsg").html("请登录");
        $("#desc").html("登录后才能兑换红包")
        $("#alertLogin").show();
        return;
    }
    window.location.href="/c/p/codeRed/goInputCodePage?codeId="+codeId;
});

/**
 * 到兑换记录页面
 */
$(document).on("click","#exchangeRecords",function () {
    
    var isLogin = $("#isLogin").val();
    if (isLogin == "false") {
        $("#alertMsg").html("请登录");
        $("#desc").html("登录后才能查看兑换记录")
        $("#alertLogin").show();
        return;
    }
    window.location.href="/c/p/exchange/exchangeList";
});

function exchangeRecords() {

    var isLogin = $("#isLogin").val();
    if (isLogin == "false") {
        $("#alertMsg").html("请登录");
        $("#desc").html("登录后才能查看兑换记录")
        $("#alertLogin").show();
        return;
    }
    window.location.href="/c/p/exchange/exchangeList";
}


function hideAlertLogin() {
    $("#alertMsg").text("");
    $("#desc").text("")
    $("#alertLogin").hide();
}