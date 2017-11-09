<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>红包拿不停</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" type="text/css" href="/client/css/banner_red.css">
    <link rel="stylesheet" href="/plugins/swiper/swiper-3.3.1.min.css"/>
    <script src="/plugins/jquery/jquery-1.9.1.js" type="text/javascript"></script>
    <script src="/client/js/mission_index.js" type="text/javascript"></script>
    <script src="/plugins/swiper/swiper-3.3.1.min.js"></script>
</head>
<body>
<%--<div class="header">--%>
    <%--蚂蚁乐赚--%>
    <%--<a href="${BASE_PATH}/c/p/user/index" class="geren">--%>
        <%--<i class="iconfont">--%>
            <%--&lt;%&ndash;<img src="${BASE_PATH}/img/iconfont-geren.png">&ndash;%&gt;--%>
        <%--</i>--%>
    <%--</a>--%>
<%--</div>--%>
<div class="main">
    <div class="index_banner swiper-container-horizontal">
        <div class="swiper-wrapper" style="max-height: 150px;">
            <c:forEach items="${banners}" var="banner" varStatus="loop">
                <div class="swiper-slide" >
                    <a href="${banner.bannerUrl}">
                        <img src="${banner.bannerImg}" >
                    </a>
                </div>
            </c:forEach>
        </div>
    </div>
    <%--<div class="index_gold">--%>
        <%--我的金币：<font id="js_mygold" style="color: #e95847; font-size:1rem;">${coin}</font>--%>
                <%--<span>--%>
                    <%--<a href="javascript:;" class="must_login sign_in" style="text-decoration: none;">签到</a>--%>
                <%--</span>--%>
    <%--</div>--%>
    <div class="index_box">
        <div class="index_menu">
            <ul class="menu_list cf">
                <c:forEach items="${sorts}" var="sort" >
                    <li>
                        <a href="/c/p/red/sortList/${sort.sortId}">
                            <i class="iconfont" style="background:#02c751; font-size:1.2rem;">
                                <img src="${sort.sortImg}">
                            </i><br>
                            ${sort.sortName}
                        </a>
                    </li>
                </c:forEach>
            </ul>
        </div>

        <%--<div class="index_activity">--%>
            <%--<ul class="cf">--%>
                <%--&lt;%&ndash;<li>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<a href="">&ndash;%&gt;--%>
                <%--&lt;%&ndash;新手必读&ndash;%&gt;--%>
                <%--&lt;%&ndash;</a>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</li>&ndash;%&gt;--%>
                <%--<li>--%>
                    <%--<a href="/c/p/user/redbag">--%>
                        <%--领取红包--%>
                    <%--</a>--%>
                <%--</li>--%>
            <%--</ul>--%>
        <%--</div>--%>
    </div>


    <div class="index_list_title">
        <span>热门活动</span>
        <b></b>
    </div>

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
</div>
<script>


    var swiper = new Swiper('.index_banner', {
        pagination: '.swiper-pagination',
        paginationClickable: true,
        spaceBetween: 0,
        centeredSlides: true,
        autoplay: 2000,
        autoplayDisableOnInteraction: false,
        loop:true,
    });

</script>
</body>

</html>
