<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${item.itemTitle}</title>
    <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" type="text/css" href="/client/css/detail.css">
    <link rel="stylesheet" type="text/css" href="/client/css/loginDialog.css">
    <link rel="stylesheet" type="text/css" href="/client/css/tpl/alertAddress.css">
    <script src="/plugins/jquery/jquery-1.9.1.js" type="text/javascript"></script>
    <script src="/client/js/sign_index.js" type="text/javascript"></script>
    <c:if test="${isIos == true}">
        <script src="/client/js/login_ios.js" type="text/javascript"></script>
    </c:if>
    <c:if test="${isIos == false}">
        <script src="/client/js/login_andriod.js" type="text/javascript"></script>
    </c:if>
    <script src="/client/js/tpl/alertAddress.js"></script>
    <link rel="stylesheet" type="text/css" href="/plugins/weui/css/weui.css">
</head>
<body>
<!--此4个隐藏框,用于存当前商品及兑换所需金币,商品名字-->
<input type="hidden" id="score" value=${item.itemCoin}>
<input type="hidden" id="goodsNum" value=${item.itemId}>
<input type="hidden" id="goodsName" value='${item.itemTitle}'>
<input type="hidden" id="isWx" value="${isWx}">

<div class="content">
    <div class="ball">
        <div class="detailImg">
            <img src=${item.itemImg}>
        </div>
        <div class="detailSep">
        </div>
        <div class="descDiv">
            <span class="blackTitle">商品介绍</span>
            <span class="descDetail">
                ${item.itemDesc}
            </span>
        </div>
        <c:if test="${item.type == '0'}">
            <div class="descDiv">
                <span class="blackTitle">兑换流程</span>
                <span class="descDetail">1.数量有限！手慢无!</span>
                <span class="descDetail">2.兑换后详细填写名字，电话，收件人信息；</span>
                <span class="descDetail">3.三个工作日内邮寄出中奖货品；</span>
                <span class="descDetail">4.虚拟商品以卡号卡密的方式发放，可在兑换记录中查询;</span>
                <span class="descDetail">5.兑换后，别忘了发照片和使用心得，么么哒！</span>
            </div>
            <div class="descDiv">
                <span class="blackTitle">注意事项</span>
                <span class="descDetail">1.每位用户不限制兑换!</span>
                <span class="descDetail">2.对于出现的作弊情况，一旦认定奖品取消；</span>
                <span class="descDetail">3.客服投诉QQ：800160354 ；</span>
                <span class="descDetail">4.本活动最终解释权归商家所有！</span>
            </div>
        </c:if>

        <c:if test="${item.type == '1'}">
            <div class="descDiv">
                <span class="blackTitle">兑换流程</span>
                <span class="descDetail">1.数量有限！手慢无!</span>
                <span class="descDetail">2.兑换后详细填写名字，电话，收件人信息；</span>
                <span class="descDetail">3.三个工作日内邮寄出中奖货品；</span>
                <span class="descDetail">4.虚拟商品以卡号卡密的方式发放，可在兑换记录中查询;</span>
                <span class="descDetail">5.兑换后，别忘了发照片和使用心得，么么哒！</span>
            </div>
            <div class="descDiv">
                <span class="blackTitle">注意事项</span>
                <span class="descDetail">1.每位用户不限制兑换!</span>
                <span class="descDetail">2.对于出现的作弊情况，一旦认定奖品取消；</span>
                <span class="descDetail">3.客服投诉QQ：800160354 ；</span>
                <span class="descDetail">4.本活动最终解释权归商家所有！</span>
            </div>
        </c:if>


        <div style="background-color: #F0F0F0;">
            <span style="font-size: 1rem;display: block;margin-bottom: 0.4rem;padding-top: 0.5rem">免责说明</span>
            <span style="font-size: 0.8rem;display: block;margin-bottom: 0.4rem;text-align: left;margin-left: 1rem;margin-right: 1rem;padding-bottom: 0.5rem;line-height: 1.2rem;">
                商品兑换流程请仔细参照商品介绍,注意事项,兑换流程与活动时间,除商品本省不能正常兑换外,商品一经兑换,一律不退换积分。
            </span>
            <span style="font-size:1rem;color: #E84A25;display: block;margin-bottom: 0.4rem;text-align: center;margin-left: 1rem;margin-right: 1rem;padding-bottom: 0.5rem;">
                本活动与苹果公司无关
            </span>
        </div>

        <div style="background-color:white;width: 400px;height: 60px;">
        </div>

        <div class="footer">
            <c:if test="${isLogin == true}">
                <div>
                    <div style="float: left;width: 60%;text-align: left;">
                        <span style="margin-left: 2rem;display: block;line-height: 3rem;color: darkgoldenrod;margin-top: 0.5rem;font-size: 1.5rem">${item.itemCoin}金币</span>
                    </div>
                    <div style="float: left;width: 6%;text-align: right">
                        <span style="margin-left: 1rem;display: block;line-height: 4rem;color: darkgoldenrod">&nbsp;</span>
                    </div>
                    <c:if test="${exchange == true}">
                        <div class="aDiv" style="float: left;width: 30%;">
                            <a onclick="showAddress();" href="javaScript:;"><span style="color: white;line-height: 3rem;font-size: 1rem;">立即兑换</span></a>
                        </div>
                    </c:if>
                    <c:if test="${exchange == false}">
                        <div class="aDiv" style="float: left;width: 30%;background-color: grey;">
                            <a><span style="color: white;line-height: 3rem;font-size: 1rem">金币不足</span></a>
                        </div>
                    </c:if>
                </div>
            </c:if>
            <c:if test="${isLogin == false}">
                <a onclick="alertLogin();">
                    <div class="login">
                        <span style="color: white;line-height: 3rem"><span style="font-size: 1rem;">请登录</span></span>
                    </div>
                </a>
            </c:if>
        </div>
    </div>
    <%@include file="/WEB-INF/views/tpl/loginTpl.jsp"%>
    <!--输入收货地址的框-->
    <%@include file="/WEB-INF/views/tpl/addressTpl.jsp"%>
    <%@include file="/WEB-INF/views/tpl/wuiTplInDetail.jsp"%>
</div>

</body>
</html>
