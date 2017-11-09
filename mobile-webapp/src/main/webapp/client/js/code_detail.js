/**
 * 点击 '复制红包密令' 按钮
 */
function getCode() {

    var code = $("#code").val();

    $("#getCode").text(code);

    setTimeout(function () {

        $("#getCode").text("获取红包密令");
        
    },10000);
}