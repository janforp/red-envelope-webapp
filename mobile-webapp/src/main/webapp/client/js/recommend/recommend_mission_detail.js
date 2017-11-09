/**
 * 点击图片,放大查看,并且可以下载
 */
$(document).on('click',".openImg",function () {
    var imgUrl = $(this).attr("src");
    zoomImg(imgUrl);
});



/**
 * 领取任务 :弹框
 */
function getRecommendMission() {
    showOneButton('重要说明','请仔细阅读任务详情页底部审核要求,否则可能无法获得奖励','已理解任务要求,去抢任务');
    //给确定按钮绑定事件
    $("#singleButton").attr("onclick",'closeOneButton(3)');
    return;
};


/**
 *  点击弹出框确定按钮
 */
function sureGetRecommendMission(missionId) {
    var timeStamp = Date.parse(new Date());
    if (isIos == 'false'){
        getMissionByAndroid(timeStamp, missionId);
    }else{
        getMissionByIos(timeStamp, missionId);
    }
}

/**
 * 抢任务,之前的平台问题已经处理
 * @param timeStamp
 * @param missionId
 * @param sign
 */
function getRecommendMissionByDifferentPlatform(timeStamp, missionId, sign) {
    $.ajax({
        url: "/c/p/a/recommend/getMission",
        type: "POST",
        dataType: "JSON",
        data: {
            id: missionId,
            t: timeStamp,
            sign: sign
        },
        success: function (data) {
            if (data.code == '0'){//抢任务成功
                window.location.reload();
            }else {
                showOneButton('', data.msg,'知道了');
                //给确定按钮绑定事件
                $("#singleButton").attr("onclick",'closeOneButton()');
                return;
            }
        },
        error: function () {

        }
    });
};




