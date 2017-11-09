<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>任务列表</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" type="text/css" href="/client/css/sort_list.css">
    <link rel="stylesheet" type="text/css" href="/client/css/page/mission.css">

    <script src="/plugins/jquery/jquery-1.9.1.js" type="text/javascript"></script>
    <script src="/client/js/sort_list.js" type="text/javascript"></script>
</head>
<body>
<%-- Fixed navbar --%>
<%--<div class="header">--%>
    <%--红包抢不停--%>
    <%--<a href="${BASE_PATH}/c/p/user/index" class="geren">--%>
        <%--<i class="iconfont">--%>
            <%--&lt;%&ndash;<img src="${BASE_PATH}/img/iconfont-geren.png">&ndash;%&gt;--%>
        <%--</i>--%>
    <%--</a>--%>
<%--</div>--%>
<div class="main">
    <c:if test="${missions != null}">
        <div class="hd_list">
            <ul class="cf">
                <c:forEach items="${missions}" var="e">
                    <li class="ing">
                        <a href="/c/p/red/missionDetail/${e.missionId}">
                        <span class="over_span">
                            <img class="pic" src="${e.missionImg}">
                        </span>
                            <h4>${e.missionName}</h4>
                            <h5><span style="color:#999;">活动奖励:</span>${e.missionReward}</h5>
                            <h6><span style="color:#999;">参加人数:</span>${e.participantsNum}</h6>
                            <h6><span style="color:#999;">结束时间:</span> ${e.showEndTime}</h6>
                        </a>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <div class="page_list">
            <p>${pageNow}/${totalPage}</p>
            <a class="prevs" href="${pageNow==1?"#":lastPage}">${pageNow==1?"首页":"上一页"}</a>
            <a class="nexts" href="${pageNow==totalPage?"#":nextPage}">${pageNow==totalPage?"没有了":"下一页"}</a>
        </div>
    </c:if>
</div>
</body>

</html>
