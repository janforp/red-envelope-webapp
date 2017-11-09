<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <title>${title}</title>
    <link rel="stylesheet" type="text/css" href="/client/css/fix_detail.css">
    <link rel="stylesheet" type="text/css" href="/plugins/weui/css/weui.css">
    <!--上榜规则样式-->
    <link rel="stylesheet" type="text/css" href="/client/css/fix_detail_alertRule.css">
    <script src="/plugins/jquery/jquery-1.9.1.js" type="text/javascript"></script>
    <script src="/client/js/fix_detail.js"></script>

    <script type="application/javascript" src="/client/js/test/iscroll.js"></script>
    <script src="/plugins/iscroll/fix_detail_slipe.js"></script>
    <link rel="stylesheet" type="text/css" href="/plugins/iscroll/slipe.css">
饭ix
</head>
<body>
<div id="wrapper">
    <div id="scroller">
        <div class="contentParent">
            <div id="backGround" class="content"><!--content开始-->
                <div class="rule">
                    <div>
                        <img src="/client/img/phb.png">
                    </div>
                    <div>
                        <a href="javaScript:;" id="ruleA">上榜规则</a>
                    </div>
                </div>
                <div class="top">
                    <div class="topN">
                        <img src="${top2.icon}">
                        <span>${top2.nickname}</span>
                        <span>${top2.money}元</span>
                    </div>
                    <div class="top1">
                        <img  src="${top1.icon}">
                        <span style="display: block;margin-top:-0.5rem; ">${top1.nickname}</span>
                        <span>${top1.money}元</span>
                    </div>
                    <div class="topN">
                        <img src="${top3.icon}">
                        <span>${top3.nickname}</span>
                        <span>${top3.money}元</span>
                    </div>
                </div>
                <div class="sep">
                    <c:if test="${separate.status == '0'}">
                        <span class ='separate'>${separate.amount}个红包, ${separate.time}秒抢光 !</span>
                    </c:if>

                    <c:if test="${separate.status == '1'}">
                        <span class ='separate'>${separate.amount}个红包, 剩余${separate.remaind}个 !</span>
                    </c:if>
                </div>


                <div id="detail" class="detail">
                    <div id="content">
                        <div id="tbody">
                            <c:forEach items="${details}" var="detail">
                                <div class='oneLine'>
                                    <div class='icon'>
                                        <img src='${detail.icon}'>
                                    </div>
                                    <div class='nickname'>
                                        <span>${detail.nickname}</span>
                                    </div>
                                    <div class='time'>
                                        <span>${detail.time}</span>
                                    </div>
                                    <div class='price'>
                                        <span>${detail.money}元</span>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>


                <input type="hidden" id="pageNow" value="${pageNum}">
                <input type="hidden" id="totalPage" value="${totalPage}">
                <input type="hidden" id="redId" value="${redId}">


                <div id="LoginBox"><!--上榜规则框开始-->
                    <div class="row" style="text-align: center">
                        <span style="font-size: 1.5rem">上榜规则</span>
                    </div>
                    <div class="row">
                        <span style="display: block;background-color: yellow;font-size: 1rem;width: 80px;text-align: center">冠军</span>
                        <span style="font-size: 0.8rem;line-height: 1.3rem">金额最大的用户将额外获得1.00元奖励。</span>
                    </div>

                    <div class="row">
                        <span style="display: block;background-color: grey;font-size: 1rem;width: 80px;text-align: center">亚军</span>
                        <span style="font-size: 0.8rem;line-height: 1.3rem">金额第二的用户将额外获得0.6元奖励。</span>
                    </div>

                    <div class="row">
                        <span style="display: block;background-color: darkorange;font-size: 1rem;width: 80px;text-align: center">季军</span>
                        <span style="font-size: 0.8rem;line-height: 1.3rem">金额第三的用户将额外获得0.3元奖励。</span>
                    </div>
                    <div class="row">
                        <span style="display: block;background-color: darkorange;font-size: 1rem;width: 80px;text-align: center">说明</span>
                        <span style="font-size: 0.8rem;line-height: 1.3rem">金额排名相同的则奖励给抢红包时间最早的用户。</span>
                    </div>
                </div><!--上榜规则框结束-->

            </div><!--content结束-->
        </div>

        <div id="pullUp" style="padding-left: 45%">
            <span class="pullUpIcon"></span><span class="pullUpLabel">&nbsp;</span>
        </div>

    </div>
    </div>
    </div>
</body>
</html>