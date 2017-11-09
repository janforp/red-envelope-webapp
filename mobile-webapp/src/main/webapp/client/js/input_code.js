
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
$(document).on("click","#exchange",function () {



    var code = $("#code").val();

    var codeId = $("#codeId").val();


    if (code == null || code == "" || code == undefined) {

        $("#alert").show();

        setTimeout(function () {
            $("#alert").hide();
        },1000);

        return;
    }

    $.ajax({
        url:"/c/p/codeRed/exchange",
        type:"POST",
        dataType:"JSON",
        data:{code:code,codeId:codeId},
        success:function (data) {

            // if (data.code == 0) {
            //
            //     $("#alert").val(data.msg);
            //
            //     $("#alert").show();
            //
            //     setTimeout(function () {
            //         $("#alert").hide();
            //     },1000);
            //
            //
            // }else {
            //
            //     $("#alert").val(data.msg);
            //
            //     $("#alert").show();
            //
            //     setTimeout(function () {
            //         $("#alert").hide();
            //     },1000);
            // }

            // $("#alertMsg").html(data.msg);
            $("#desc").html(data.msg)

            $("#alertLogin").show();

            $("#exchange").css("background-color","#D9D9D9");
            $("#code").css("border","none");
            

        },
        error:function () {

        }
    });

})



/**
 * 点击 '上榜规则' 按钮 弹出规则
 */
$(document).on("click","#ruleA",function () {

    //弹出
    $(".content").append("<div id='mask'></div>");
    $("#mask").addClass("mask").fadeIn("fast");
    $("#LoginBox").fadeIn("fast");
});

/**
 * 点击其他地方弹框 消失
 */
$(document).ready(function () {

    $(".content").click(function () {

        $("#LoginBox").hide();
        $("#mask").removeClass("mask")
    })
});



function hideAlertLogin() {

    $("#alertMsg").text("");
    $("#desc").text("")

    $("#alertLogin").hide();
}