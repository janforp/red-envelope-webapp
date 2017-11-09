$(document).on("click","#save",function () {

    // 建议
    var feedbackDetail = $("#feedbackDetail").val().trim();
    // 联系方式
    var userContact = $("#userContact").val().trim();

    if(feedbackDetail.length == 0) {
        tips.err("请填写您的建议!", 1500);
        return;
    }

    //调用app代码。传回sign
    var map = {
        'userContact': userContact,
        'feedbackDetail': feedbackDetail
    };
    var str = JSON.stringify(map);
    var sign = userSign.getSign(str);

    tips.loading("请等待");

    $.ajax({
        url: '/c/p/a/feedback/save',
        type: 'POST',
        dataType: 'JSON',
        data: {
            userContact: userContact,
            feedbackDetail: feedbackDetail,
            sign: sign
        },
        success: function(data){
            if(data.code == 0) {
                tips.suc("提交成功", 2000);
                $("#feedbackDetail").val('');
                $("#userContact").val('');
            }else {
                tips.err("提交失败", 2000);
            }
        },
        error: function(){
            tips.hideLoading();
        },
        complete: function(){
            tips.hideLoading();
        }

    });


});