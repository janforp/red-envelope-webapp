<!DOCTYPE html>
<html>
    <head>
        <title>评价App</title>
        <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
        <meta name="format-detection" content="telephone=no">
        <link rel="stylesheet" type="text/css" href="/client/css/appraise.css">
    </head>
    <body>
        <c:if test="${isIos == true}">
            <div class="content" style="text-align: center;">
                <div style="margin-top: 8rem">
                    <span style="color:#000; font-size: 1rem;">
                        请前往AppStore给我们一个好评吧!
                    </span>
                </div>
            </div>
        </c:if>
        <c:if test="${isIos == false}">
            <div class="content">
                <div class="oneMarket">
                    <div class="iconDiv">
                        <img src="/client/img/appPraise/yingyongbao.png">
                    </div>
                    <div class="nameDiv">
                        <span>应用宝</span>
                    </div>
                    <div class="buttonDiv"><!--通过-->
                        <a href="javaScript:;" onclick="userSign.appraise('com.tencent.android.qqdownloader','com.tencent.pangu.link.LinkProxyActivity');"><span>去评价</span></a>
                    </div>
                </div>

                <div class="oneMarket">
                    <div class="iconDiv">
                        <img src="/client/img/appPraise/wandoujia.png">
                    </div>
                    <div class="nameDiv">
                        <span>豌豆荚</span>
                    </div>
                    <div class="buttonDiv"><!--通过-->
                        <a href="javaScript:;" onclick="userSign.appraise('com.wandoujia.phoenix2','com.wandoujia.jupiter.activity.DetailActivity');"><span>去评价</span></a>
                    </div>
                </div>

                <div class="oneMarket">
                    <div class="iconDiv">
                        <img src="/client/img/appPraise/flyme.png">
                    </div>
                    <div class="nameDiv">
                        <span>魅族</span>
                    </div>
                    <div class="buttonDiv"><!--通过-->
                        <a href="javaScript:;" onclick="userSign.appraise('com.meizu.mstore','com.meizu.flyme.appcenter.activitys.AppMainTabActivity');"><span>去评价</span></a>
                    </div>
                </div>

                <div class="oneMarket">
                    <div class="iconDiv">
                        <img src="/client/img/appPraise/360.png">
                    </div>
                    <div class="nameDiv">
                        <span>360</span>
                    </div>
                    <div class="buttonDiv">
                        <a href="javaScript:;" onclick="userSign.appraise('com.qihoo.appstore','com.qihoo.appstore.appinfopage.AppInfoActivity');"><span>去评价</span></a>
                    </div>
                </div>

                <div class="oneMarket">
                    <div class="iconDiv">
                        <img src="/client/img/appPraise/baidu.png">
                    </div>
                    <div class="nameDiv">
                        <span>百度</span>
                    </div>
                    <div class="buttonDiv">
                        <a href="javaScript:;" onclick="userSign.appraise('com.baidu.appsearch','com.baidu.appsearch.appcontent.AppDetailsActivity');"><span>去评价</span></a>
                    </div>
                </div>
            </div>
        </c:if>
    </body>
</html>
