/**
 * andriod点击签到按钮
 */
$(document).on("click", "#doSign", function () {

    tips.loading("请等待");

    // 调用app代码。传回sign
    var timestamp = Date.parse(new Date());
    var map = {
        't':timestamp
    };
    var str = JSON.stringify(map);
    var sign = userSign.getSign(str);
    $.ajax({
        url: '/c/p/a/sign/sign',
        type: 'POST',
        dataType: 'JSON',
        data: {
            t: timestamp,
            sign: sign
        },
        success: function(data){
            if(data.code == 0) {
                dialog.alert("签到成功", data.msg, null);
                window.location.reload();
            }else {
                dialog.alert("签到失败", data.msg, null);
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

