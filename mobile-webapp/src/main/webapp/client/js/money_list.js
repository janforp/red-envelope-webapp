
/**
 * 翻页
 */
function turnPage(pageNum) {
    $.ajax({
        url     :   "/c/p/coinMoney/listMoney",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {pageNum:pageNum},
        success :   function(data){

            if (data.code == 0) {

                var vos = data.data.scores;
                $("#pageNow").val(data.data.pageNum);
                $("#totalPage").val(data.data.totalPage);
                var body = $("#list");
                var newTbody = "";
                for (var i= 0;i<vos.length;i ++) {
                    newTbody +=
                        "<div class='oneLine'>"+
                        "<div class ='lineLeft'>"+
                        "<span class='leftSpan1'>红包APP</span>"+
                        "<span class='leftSpan2'>"+vos[i].detailContent+"</span>"+
                        "</div>"+
                        "<div class='lineRight'>"+
                        "<span class='rightSpan1'>"+vos[i].detailTime+"</span>"+
                        "<span class='rightSpan2'>";

                    if(vos.detailType == 0) {

                        newTbody = newTbody + "-" +vos[i].accountMoney;
                    } else {
                        newTbody = newTbody + "+" +vos[i].accountMoney;
                    }
                    newTbody +=
                        "</span>"+
                        "</div>"+
                        "</div>";
                }
                body.append(newTbody);
            }else{
                tips.loading("操作失败");
            }
        },
        error   :   function () {

        }
    });
}

/**
 * 下一页
 */
function pageAfter() {

    var pageNow = parseInt($("#pageNow").val());
    var totalPage = parseInt($("#totalPage").val());
    if (pageNow == totalPage) {

        return;
    }
    turnPage(pageNow+1);

}

/**
 * 上一页
 */
function pageBefore(redId) {



    var pageNow = parseInt($("#pageNow").val());
    if (pageNow == 1) {
        return;
    }

    turnPage(pageNow-1,redId);
}

/**
 * 点击 '上榜规则' 按钮 弹出规则
 */
$(document).on("click","#ruleA",function () {

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