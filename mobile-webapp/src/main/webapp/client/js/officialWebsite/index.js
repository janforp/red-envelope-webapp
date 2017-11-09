/**
 * 点击下载按钮
 */
function downloadApp() {
    var ua = navigator.userAgent.toLowerCase();

    if (/iphone|ipad|ipod|mac/.test(ua)) {
        alert("iphone下载");
    } else if (/android/.test(ua)) {
        alert("android下载");
    }else {
        alert("windows下载");
    }

}