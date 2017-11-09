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

var bRotate = false;

var rotateY = function (awards, angles, txt) {
    $("#rotate").stopRotate();
    $("#rotate").rotate({
        angle: 0,
        animateTo: angles + 3600,
        duration: 5000,
        callback: function () {
            $(".awards_img img").attr("src", "/plugins/rotate/img/" + awards + ".png");
            $(".awards_name").html(txt);
            $(".awards_cover").addClass("show");
            $(".awards_cover").show();
        }
    })
};

var rotateN = function (angles) {
    $("#rotate").stopRotate();
    $("#rotate").rotate({
        angle: 0,
        animateTo: angles + 3600,
        duration: 5000,
        callback: function () {
            $(".fail_cover").addClass("show");
            $(".fail_cover").show();
        }
    })
};


$(document).on("click", ".btn", function () {

    //调用app代码。传回sign
    var timestamp = Date.parse(new Date());
    var map = {
        't':timestamp
    };
    var str = JSON.stringify(map);

    WebViewJavascriptBridge.callHandler('getSign', str, function (response) {

        var sign = response;

        if(times == 0) {
            dialog.confirm("提示", "确定消耗5个金币进行抽奖?", function doRotate() {

                if (bRotate) {
                    return;
                }

                bRotate = !bRotate;

                $.ajax({
                    url: "/c/p/a/rotate/draw",
                    type: "POST",
                    dataType: "JSON",
                    data: {
                        t: timestamp,
                        sign: sign
                    },
                    success: function(data){
                        if(data.code == 0) {
                            switch (data.data.num) {
                                case 0:
                                    var numb0 = rnd(310, 350);
                                    rotateN(numb0);
                                    break;
                                case 1:
                                    var numb1 = rnd(250, 290);
                                    rotateY(1, numb1, data.data.text);
                                    break;
                                case 2:
                                    var numb2 = rnd(190, 230);
                                    rotateY(2, numb2, data.data.text);
                                    break;
                                case 3:
                                    var numb3 = rnd(130, 170);
                                    rotateY(3, numb3, data.data.text);
                                    break;
                                case 4:
                                    var numb4 = rnd(70, 110);
                                    rotateY(4, numb4, data.data.text);
                                    break;
                                case 5:
                                    var numb5 = rnd(10, 50);
                                    rotateY(5, numb5, data.data.text);
                                    break;
                            }
                        }else {
                            tips.err(data.msg, 2000);
                        }
                    },
                    error: function(){
                    },
                    complete: function(){
                    }
                });

            }, function cancel(){
                return;
            });
        }else {

            if (bRotate) {
                return;
            }

            bRotate = !bRotate;

            $.ajax({
                url: "/c/p/a/rotate/draw",
                type: "POST",
                dataType: "JSON",
                data: {
                    t: timestamp,
                    sign: sign
                },
                success: function(data){
                    if(data.code == 0) {
                        switch (data.data.num) {
                            case 0:
                                var numb0 = rnd(310, 350);
                                rotateN(numb0);
                                break;
                            case 1:
                                var numb1 = rnd(250, 290);
                                rotateY(1, numb1, data.data.text);
                                break;
                            case 2:
                                var numb2 = rnd(190, 230);
                                rotateY(2, numb2, data.data.text);
                                break;
                            case 3:
                                var numb3 = rnd(130, 170);
                                rotateY(3, numb3, data.data.text);
                                break;
                            case 4:
                                var numb4 = rnd(70, 110);
                                rotateY(4, numb4, data.data.text);
                                break;
                            case 5:
                                var numb5 = rnd(10, 50);
                                rotateY(5, numb5, data.data.text);
                                break;
                        }
                    }else {
                        tips.err(data.msg, 2000);
                    }
                },
                error: function(){
                },
                complete: function(){
                }
            });
        }

    });
});

function rnd(n, m) {
    return Math.floor(Math.random() * (m - n + 1) + n)
}