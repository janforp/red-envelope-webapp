<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta name="format-detection" content="telephone=no">

    <title>${mission.missionName}</title>
    <link rel="stylesheet" href="/client/css/page/mission_detail.css"/>
    <script src="/plugins/jquery/jquery-1.9.1.js" type="text/javascript"></script>
    <script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js" type="text/javascript"></script>
    <script src="/plugins/wxNotShare/wxNotShare.js" type="text/javascript"></script>
</head>
<body>
    <input type="hidden" id="appId" value="${share.appId}">
    <input type="hidden" id="timestamp" value="${share.timestamp}">
    <input type="hidden" id="nonceStr" value="${share.nonceStr}">
    <input type="hidden" id="signature" value="${share.signature}">
    <div class="content">
        <div class="headImg">
            <a href="${mission.missionUrl}"><img src="${mission.missionAdImg}"></a>
        </div>
        <div class="headTitle">
            <span>${mission.missionName}</span>
        </div>
        <div class="merchant">
            <div class="merchantLeft">
                <span class="mer">商家:&nbsp;&nbsp;</span><span class="name">${mission.merchantName}&nbsp;&nbsp;</span>
                <span class="numBack"><span class="mer">${mission.participantsNum}&nbsp;&nbsp;</span>人参加</span>
            </div>
            <div class="merchantRight">
                <c:if test="${mission.missionStatus == 1}">
                    <span class="statusIng">${mission.showMissionStatus}</span>
                </c:if>
                <c:if test="${mission.missionStatus == 0}">
                    <span class="statusEd">${mission.showMissionStatus}</span>
                </c:if>
            </div>
        </div>

        <div class="timeAndRewardImg">
            <div class="timeRewardDiv">
                <span><img src="/client/img/missionDetail/activeTime.png">&nbsp;&nbsp;活动时间:</span>&nbsp;&nbsp;<span class="redColor">${mission.showStartTime}&nbsp;到&nbsp;${mission.showEndTime}</span><br>
                <span><img src="/client/img/missionDetail/overtime.png">&nbsp;&nbsp;具体时间:</span>&nbsp;&nbsp;<span class="redColor">${mission.showEndTime}结束</span><br>
                <span><img src="/client/img/missionDetail/reward.png">&nbsp;&nbsp;活动奖励:</span>&nbsp;&nbsp;<span class="redColor">${mission.missionReward}</span><br>
                <span><img src="/client/img/missionDetail/gift.png">&nbsp;&nbsp;额外奖励:</span>&nbsp;&nbsp;<span class="redColor">${mission.missionExtraReward}</span>
            </div>
            <%--<div class="imgDiv">--%>
                <%--<img src="${mission.missionImg}">--%>
            <%--</div>--%>
        </div>
        <div class="descHeader">
            <span>亲,按照以下步骤参加活动吧</span>
        </div>
        <%--<div class="descHeader">--%>
            <%--<span>活动规则</span>--%>
        <%--</div>--%>
        <div class="rule">
            ${mission.missionRule}
        </div>
        <div class="right">
            <span class="merDesc">商家概述</span><br>
            <span class="desc">本活动解释权归${mission.merchantName}所有,如有任何疑问请联系官方客服.</span>
        </div>
        <div class="rightDiv">
            <span>本活动由全民红包提供,&nbsp;&nbsp;与设备提供商苹果公司无关</span>
        </div>
    </div>
</body>
</html>

