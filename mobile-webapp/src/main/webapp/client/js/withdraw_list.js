
/**
 * 翻页
 */
function turnPage(pageNum) {
    $.ajax({
        url     :   "/c/p/withdraw/turnWithdraw",
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
                "<div class='tableBody'>"+
                    "        <div class='tBody'>"+
                    "   <span class='money'>"+vos[i].money+"</span>"+
                    "   </div>"+
                    "   <div class='tBody'>"+
                    "   <span class='date'>"+vos[i].date+"</span>"+
                    "   <span class='time'>"+vos[i].time+"</span>"+
                    "   </div>"+
                    "   <div class='tBody'>"+
                    "   <span class='status'>"+vos[i].status+"</span>"+
                    "   </div>"+
                    "   <div class='tBody'>"+
                    "   <span class='type'>"+vos[i].type+"</span>"+
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