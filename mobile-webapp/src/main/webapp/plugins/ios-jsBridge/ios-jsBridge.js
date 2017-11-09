/**
 * 苹果
 * @param callback
 * @returns {*}
 */
function setupWebViewJavascriptBridge(callback) {
    if (window.WebViewJavascriptBridge) {
        return callback(WebViewJavascriptBridge);
    }
    if (window.WVJBCallbacks) {
        return window.WVJBCallbacks.push(callback);
    }
    window.WVJBCallbacks = [callback];
    var WVJBIframe = document.createElement('iframe');
    WVJBIframe.style.display = 'none';
    WVJBIframe.src = 'wvjbscheme://__BRIDGE_LOADED__';
    document.documentElement.appendChild(WVJBIframe);
    setTimeout(function () {
        document.documentElement.removeChild(WVJBIframe)
    }, 0)
}

setupWebViewJavascriptBridge(function (bridge) {
    bridge.registerHandler('testJSFunction', function (data, responseCallback) {
        alert('JS方法被调用:' + data);
        responseCallback('js执行过了');
    })
});
