/**
 * 安卓提交审核
 */
function submitToVerify(){
    userSign.jumpCommitCheckActivity(taskId, verifyRequire, imgText, imgNum);
}


/**
 * 安卓查看图片
 */
function zoomImg(url){
    userSign.shoWebviewImage(url);
}

/**
 * 安卓抢任务
 * @param timeStamp
 * @param missionId
 */
function getMissionByAndroid(timeStamp, missionId) {

    //调用app代码。传回sign
    var map = {
        't':timeStamp,
        'id':missionId
    };
    var str = JSON.stringify(map);
    var sign = userSign.getSign(str);
    getRecommendMissionByDifferentPlatform(timeStamp,missionId,sign);
}