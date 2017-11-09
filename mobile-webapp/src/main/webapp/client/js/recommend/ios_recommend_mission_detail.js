/**
 * 苹果提交审核
 */
function submitToVerify(){
    var map = {
        'taskId':taskId,
        'verifyRequire': verifyRequire,
        'imgRemarks': imgText,
        'imgNum':imgNum
    };
    var str = JSON.stringify(map);
    WebViewJavascriptBridge.callHandler('submitExamine', str, function (response) {
    });
}


/**
 * 苹果查看图片
 */
function zoomImg(url){
    var map = {
        'img': url
    };
    var str = JSON.stringify(map);
    WebViewJavascriptBridge.callHandler('zoomOutImage', str, function (response) {
    });
}


/**
 * 苹果抢任务
 * @param timeStamp
 * @param missionId
 */
function getMissionByIos(timeStamp, missionId){
    var map = {
        id: missionId,
        t: timeStamp
    };
    var str = JSON.stringify(map);
    WebViewJavascriptBridge.callHandler('getSign', str, function (sign) {
        getRecommendMissionByDifferentPlatform(timeStamp, missionId, sign)
    });
}


/**
 * 苹果 判断包 是否存在
 * @param url
 */
function isAppExistIos(appPackage){
    var map = {
        'appPackage': appPackage
    };
    var str = JSON.stringify(map);
    WebViewJavascriptBridge.callHandler('isHadBuild', str, function (isExist) {
    });
}




