/**
 * 点击更换排序规则
 * @param type
 */
function getFinData(type) {

    $('.nav').removeClass('active');
    $("#type"+type).addClass('active');

    $.ajax({
        url:'/c/p/financial/list',
        type:'POST',
        dataType:'JSON',
        data:{orderType:type},
        success:function (data) {
            if (data.code == 0){
                $("#items").empty();
                var vos = data.data;
                var newItems = "";
                for (var i=0;i<vos.length;i++){
                    newItems +=
                        "<div class='item'>"+
                        "<div class='itemTop'>"+
                        "<div class='imgDiv'><img src='"+vos[i].icon+"'></div>"+
                        "<div class='descDiv'>"+
                        "   <span class='title'>"+vos[i].title+"</span>"+
                        // "   <span class='desc red-content'>"+vos[i].desc+"</span>"+
                        "   <div class='labels'>";
                    var labels = vos[i].label;
                    if (labels != undefined){
                        for(var j=0;j<labels.length;j++){
                            newItems +=
                                "<span class='label'>"+labels[j]+"</span>";
                        }
                    }
                    newItems+=
                        "   </div>"+
                        "</div>"+
                        "<div class='btnDiv'>"+
                        "    <a href='"+vos[i].clickUrl+"' class='red-main-bg-color-first'>点击赚钱</a>"+
                        "   </div>"+
                        "   </div>"+
                        "<div class='text'>"+
                        "<span class='desc red-content'>"+vos[i].desc+"</span>"+
                    "</div>"+
                        "   <div class='itemBottom'>"+
                        "   <div class='earning'>"+
                        "   <span>"+vos[i].earning+"%</span>"+
                        "   <span>年化收益</span>"+
                        "   </div>"+
                        "   <div class='earning'>";
                    var time = vos[i].investmentTime;
                    if (time == 0){
                        time="随存随取"
                    }
                    newItems +=
                        "   <span>"+time+"天</span>"+
                        "   <span>投资期限</span>"+
                        "   </div>"+
                        "   <div class='earning'>"+
                        "   <span>"+vos[i].money+"元</span>"+
                        "<span>起投金额</span>"+
                        "</div>"+
                        "</div>"+
                        "</div>"+
                        "<div class='red-model-separator-line red-bg-color'></div>";
                }

                $("#items").append(newItems)
            }
        }
    });
}