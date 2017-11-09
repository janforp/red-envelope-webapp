
/**
 * 翻页
 */
function turnPage(pageNum,redId) {

    $.ajax({
        url     :   "/c/p/fixRed/list",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {redId:redId,pageNum:pageNum},
        success :   function(data){

            if (data.code == 0) {

                var vos = data.data.details;
                $("#pageNow").val(data.data.pageNum);
                $("#totalPage").val(data.data.totalPage);
                $("#redId").val(data.data.redId);

                var body = $("#tbody");
                var newTbody = "";
                for (var i= 0;i<vos.length ;i ++) {
                    newTbody +=
                        "<div class='oneLine'>"+
                        "   <div class='icon'>"+
                        "       <img src='"+vos[i].icon+"'>"+
                        "   </div>"+
                        "   <div class='nickname'>"+
                            "   <span>"+vos[i].nickname+"</span>"+
                        "   </div>"+
                        "   <div class='time'>"+
                            "   <span>"+vos[i].time+"</span>"+
                        "   </div>"+
                        "   <div class='price'>"+
                            "   <span>"+vos[i].money+"元</span>"+
                        "   </div>"+
                        "   </div>";
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
function pageAfter(redId) {

    var pageNow = parseInt($("#pageNow").val());
    var totalPage = parseInt($("#totalPage").val());
    if (pageNow == totalPage) {



        return;
    }

    turnPage(pageNow+1,redId);
    
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