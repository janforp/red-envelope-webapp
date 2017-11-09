
/**
 * 翻页
 */
function turnPage(pageNum) {
    $.ajax({
        url     :   "/c/p/invitation/turnInvitation",
        type    :   "POST",
        dataType:   "JSON",
        data    :   {pageNum:pageNum},
        success :   function(data){

            if (data.code == 0) {

                var vos = data.data.details;
                $("#pageNow").val(data.data.pageNum);
                var body = $("#list");
                var newTbody = "";
                for (var i= 0;i<vos.length;i ++) {
                    newTbody +=

                "<div class='oneDetailDiv'>"+
                    "        <div class='left'>"+
                    "   <img src='"+vos[i].icon+"'>"+
                    "   </div>"+
                    "   <div class='right'>"+
                    "   <span class='name'>"+vos[i].name+"</span>"+
                    "   <span class='time'>"+vos[i].time+"</span>"+
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
 * 向上滑动加载(即:下一页)
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
 * 点击'立即邀请好友'
 */
function alertInvite() {

    $("#inviteDiv").show();
    $(".footButtonDiv").css("height","auto");
    $(".footButtonDiv").css("background-color",": #FFFFFF");
    $(".footButtonDiv").css("padding","0");
    $(".cover").show();
    $("#invite").hide();

}
/**
 * 点击取消
 */
function cancelInvite() {
    $("#inviteDiv").hide();
    $(".footButtonDiv").css("height","65px");
    $(".footButtonDiv").css("background-color",": #F0F0F0")
    $(".footButtonDiv").css("padding","0.5rem");
    $(".cover").hide();
    $("#invite").show();
}