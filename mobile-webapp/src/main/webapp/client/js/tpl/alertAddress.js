/**
 * 点击'立即兑换'
 * 弹出个人收获地址信息
 */
function showAddress() {

    $.ajax({
        url:"/c/p/address/showAddress",
        type:"POST",
        dataType:"JSON",
        data:{},

        success:function (data) {

            var address = data.data ;
            var province = address.province;
            var name = address.realName;
            var city = address.city;
            var detailAddress = address.detailAddress;
            var mobile = address.mobile;

            $("#name").val(name);
            $("#cellphone").val();
            $("#province").val(province);
            $("#city").val(city);
            $("#detailAddress1").val(detailAddress);
            $("#cellphone").val(mobile);

            $("#addressDiv").show();

        },
        error:function () {
            
        }
    });
}

/**
 * 点击关闭
 */
$(document).on("click","#closeAddress",function () {
    $("#addressDiv").hide();
});

/**
 * 点击'提交地址'
 */
$(document).on("click","#saveAddress",function () {

    var name = $("#name").val().trim();
    if(name == "" || name == null || name == undefined) {
        $("#warnAddress").text("收货人不能空")
        $("#warnAddress").css("color","red");

        setTimeout(function () {
            $("#warnAddress").text("确认地址")
            $("#warnAddress").css("color","white");
        },3000);

        return;
    }
    var cellphone = $("#cellphone").val().trim();
    if(cellphone == "" || cellphone == null || cellphone == undefined) {
        $("#warnAddress").text("手机不能空")
        $("#warnAddress").css("color","red");

        setTimeout(function () {
            $("#warnAddress").text("确认地址")
            $("#warnAddress").css("color","white");
        },3000);

        return;
    }
    var province = $("#province").val().trim();
    if(province == "" || province == null || province == undefined) {
        $("#warnAddress").text("省不能空")
        $("#warnAddress").css("color","red");

        setTimeout(function () {
            $("#warnAddress").text("确认地址")
            $("#warnAddress").css("color","white");
        },3000);

        return;
    }
    var city = $("#city").val().trim();
    if(city == "" || city == null || city == undefined) {
        $("#warnAddress").text("市不能空")
        $("#warnAddress").css("color","red");

        setTimeout(function () {
            $("#warnAddress").text("确认地址")
            $("#warnAddress").css("color","white");
        },3000);

        return;
    }
    var detailAddress1 = $("#detailAddress1").val().trim();
    if(detailAddress1 == "" || detailAddress1 == null || detailAddress1 == undefined) {
        $("#warnAddress").text("详情地址不能空")
        $("#warnAddress").css("color","red");

        setTimeout(function () {
            $("#warnAddress").text("确认地址")
            $("#warnAddress").css("color","white");
        },3000);

        return;
    }
    var detailAddress2 = $("#detailAddress2").val().trim();
    if(detailAddress2 == "" || detailAddress2 == null || detailAddress2 == undefined) {
        detailAddress2 = "";
    }
    var detailAddress = detailAddress1 + detailAddress2 ;

    $("#loadingToast").show();

    $.ajax({
        url:"/c/p/address/save",
        type:"POST",
        dataType:"JSON",
        data:{realName:name,mobile:cellphone,province:province,city:city,detailAddress:detailAddress},

        success:function (data) {

            if (data.code == 9) { //失败

                $("#warnAddress").text(data.msg);
                $("#warnAddress").css("color","red");
                setTimeout(function () {
                    $("#warnAddress").text("确认地址")
                    $("#warnAddress").css("color","white");
                },3000);

            }else { //成功

                $("#warnAddress").text("操作成功");
                $("#warnAddress").css("color","red");
                $("#exchange").show();
                setTimeout(function () {
                    $("#warnAddress").text("确认地址")
                    $("#warnAddress").css("color","white");
                },3000);
            }

            $("#loadingToast").hide();

        },
        error:function () {

        }
    });

});



/**
 * 点击'确认兑换'按钮
 * score:所需金币
 * goods:兑换的商品
 */
function exchange(){
    var score = $("#score").val();
    var goodsNum = $("#goodsNum").val();
    var goodsName = $("#goodsName").val();

    $("#loadingToast").show();

    $.ajax({
        url:"/c/p/coin/exchange",
        type:"POST",
        dataType:"JSON",
        data:{score:score,goodsNum:goodsNum,goodsName:goodsName},
        success:function (data) {

            if(data.code == 11) { //无收货地址,则弹出输入框
                $("#loadingToast").hide();
                $("#addressDiv").show();
                return;
            }

            $("#loadingToast").hide();

            var msg = data.msg;
            $("#msg").text(msg)
            $("#toast").show();
            $("#addressDiv").hide();
            setTimeout(function () {

                $("#msg").text("")
                $("#toast").hide();

                window.location.reload();

            },1000);

        },
        error:function () {

        }
    });
}



