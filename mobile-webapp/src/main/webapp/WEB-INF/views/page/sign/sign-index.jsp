<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>签到</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="/plugins/weui-1.0.2/css/weui.min.css"/>
    <link rel="stylesheet" type="text/css" href="/client/css/base/red-envelope-base.css">
    <link rel="stylesheet" type="text/css" href="/client/css/sign/sign_index_v4.css">
    <script src="/plugins/jquery/jquery-1.9.1.js" type="text/javascript"></script>
    <script src="/plugins/weui-1.0.2/js/weui.js" type="text/javascript"></script>
    <c:if test="${isIos == true}">
        <script type="text/javascript" src="/plugins/ios-jsBridge/ios-jsBridge.js"></script>
    </c:if>

    <script type="text/javascript">

        var isIos = '${isIos}';

        /**
         * 平台共用方法
         */
        function sign(){

            tips.loading("请等待");

            //调用app代码。传回sign
            var timestamp = Date.parse(new Date());
            var map = {
                't':timestamp
            };
            var str = JSON.stringify(map);

            if(isIos == 'true'){
                WebViewJavascriptBridge.callHandler('getSign', str, function (response) {
                    var sign = response;
                    doSign(sign, timestamp);
                });
            }else{
                var sign = userSign.getSign(str);
                doSign(sign, timestamp);
            }
        }

        /**
         * 签到方法
         * @param sign
         * @param timestamp
         */
        function doSign(sign, timestamp) {

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
                        dialog.alert("签到成功", data.msg, function(){
                            window.location.reload();
                        });
                    }else {
                        dialog.alert("签到失败", data.msg, null);
                        setTimeout(function () {
                            window.location.reload();
                        },2000);

                    }
                },
                error: function(){
                    tips.hideLoading();
                },
                complete: function(){
                    tips.hideLoading();
                }

            });

        }

    </script>

</head>
<body>

    <div class="content">
        <%--<div class="red-model-separator-line red-bg-color"></div>--%>
        <div class="head">
            <span class="continuous">已经连续签到<span style="color: #f96301;">${vo.count}</span>天</span>
            <c:if test="${isSign == false}">
                <a href="javaScript:sign();">
                    <div class="btnDiv">
                        <div class="sign">
                            <span class="signBtn">签到</span>
                        </div>
                    </div>
                </a>
            </c:if>
            <c:if test="${isSign == true}">
                <div class="btnDiv">
                    <div class="sign">
                        <span class="signedBtn">已签到</span>
                    </div>
                </div>
            </c:if>

        </div>
        <div class="scoreDiv red-bg-color">
            <span>今日获得金币<span style="color: #f96301;">${vo.scoreToday}</span>个,累计获得<span style="color: #f96301;">${vo.totalScore}</span>个</span>
            <div>
                <a href="/c/p/coin/coinMall/0"><span class="red-secondary-color">金币兑换 ></span></a>
            </div>
        </div>
        <div class="daysDiv">
            <div class="days4 first">
                <span class="line">
                    <div class="oneDiv">
                        <c:if test="${isSign == false}">
                            <div class="day red-bg-color bg-blue"><span class="add">+</span>${signVo1.score}</div>
                        </c:if>
                        <c:if test="${isSign == true}">
                            <div class="day red-bg-color bg-blue"><a id="ok"></a></div>
                        </c:if>
                    </div>
                     <div class="oneDiv">
                        <div class="day red-bg-color"><span class="add">+</span>${signVo2.score}</div>
                    </div>
                     <div class="oneDiv">
                        <div class="day red-bg-color"><span class="add">+</span>${signVo3.score}</div>
                    </div>
                     <div class="oneDiv">
                        <div class="day red-bg-color"><span class="add">+</span>${signVo4.score}</div>
                    </div>
                </span>
                <div class="dateDiv">
                    <span class="date">今日</span>
                    <span class="date">明日</span>
                    <span class="date">${signVo3.date}</span>
                    <span class="date">${signVo4.date}</span>
                </div>
            </div>
            <div class="days4">
                <span class="line">
                    <div class="oneDiv">
                        <div class="day red-bg-color"><span class="add">+</span>${signVo5.score}</div>
                    </div>
                     <div class="oneDiv">
                        <div class="day red-bg-color"><span class="add">+</span>${signVo6.score}</div>
                    </div>
                     <div class="oneDiv">
                        <div class="day red-bg-color"><span class="add">+</span>${signVo7.score}</div>
                    </div>
                     <div class="oneDiv">
                        <div class="day red-bg-color"><span class="add">+</span>${signVo8.score}</div>
                    </div>
                </span>
                <div class="dateDiv">
                    <span class="date">${signVo5.date}</span>
                    <span class="date">${signVo6.date}</span>
                    <span class="date">${signVo7.date}</span>
                    <span class="date">${signVo8.date}</span>
                </div>
            </div>
        </div>

        <div class="foot red-bg-color">
            <span class="title red-lg-title">签到说明:</span>
            <span class="tip">1.签到可获取金币金币;</span>
            <span class="tip">2.持续签到可获得丰厚奖励,漏前进度重置;</span>
            <span class="tip">3.连续签到30天可以获得签到大礼包,最高888金币;</span>
            <span class="tip">4.本活动最终解释权归全民红包所有。</span>
            <span class="alert">温馨提示: 金币可兑换商品以及参加抽奖。</span>
        </div>
    </div>

    <%@include file="/plugins/weui-1.0.2/jsp/weui.jsp" %>

</body>
</html>
