<!--需审核任务详情页面-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>理财大厅</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <script type="text/javascript" src="/plugins/jquery/jquery-1.9.1.min.js"></script>
    <c:if test="${isIos == true}">
        <script type="text/javascript" src="/plugins/ios-jsBridge/ios-jsBridge.js"></script>
    </c:if>
    <link rel="stylesheet" type="text/css" href="/client/css/financial_mall/financial_mall.css">
    <link rel="stylesheet" type="text/css" href="/client/css/base/red-envelope-base.css">
    <script type="text/javascript" src="/client/js/financial_mall/financial_mall.js"></script>
    <script>
        var isIos = ${isIos};
    </script>

</head>
<body>
<div class="content"><!--content start-->
    <div class="banner">
        <%--<a href="http://www.baidu.com">--%>
            <%--<img src="/client/img/coinmall/detail1.jpg">--%>
        <%--</a>--%>
    </div>
    <div class="nav-bar">
        <a class="nav active" id="type0" href="javaScript:getFinData(0);"><div type="0"><span>综合排序</span></div></a>
        <a class="nav" id="type1" href="javaScript:getFinData(1);"><div   type="1"><span>按收益排序</span></div></a>
        <a class="nav" id="type2" href="javaScript:getFinData(2);"><div type="2"><span>按期限排序</span></div></a>
    </div>
    <div class="red-model-separator-line red-bg-color"></div>
    <div class="items" id="items">
        <c:forEach items="${vos}" var="v">
            <div class='item'>
                <div class='itemTop'>
                    <div class='imgDiv'><img src='${v.icon}'></div>
                    <div class='descDiv'>
                        <span class='title'>${v.title}</span>
                        <div class='labels'>
                            <c:forEach items='${v.label}' var='la'>
                                <span class='label'>${la}</span>
                            </c:forEach>
                        </div>
                    </div>
                    <div class='btnDiv'>
                        <a href='${v.clickUrl}' class='red-main-bg-color-first'>点击赚钱</a>
                    </div>
                </div>
                <div class='text'>
                    <span class='desc red-content'>${v.desc}</span>
                </div>
                <div class='itemBottom'>
                    <div class='earning'>
                        <span>${v.earning}%</span>
                        <span>年化收益</span>
                    </div>
                    <div class='earning'>
                        <c:if test="${v.investmentTime != '0'}"><span>${v.investmentTime}天</span></c:if>
                        <c:if test="${v.investmentTime == '0'}"><span>随存随取</span></c:if>
                        <span>投资期限</span>
                    </div>
                    <div class='earning'>
                        <span>${v.money}元</span>
                        <span>起投金额</span>
                    </div>
                </div>
            </div>
            <div class='red-model-separator-line red-bg-color'></div>
        </c:forEach>
    </div>

</div><!--content over-->



</body>
</html>