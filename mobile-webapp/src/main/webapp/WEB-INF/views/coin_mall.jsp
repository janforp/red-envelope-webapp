<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>金币商城</title>
        <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
        <meta name="format-detection" content="telephone=no">
        <link rel="stylesheet" type="text/css" href="/client/css/coin_mall.css">
        <link rel="stylesheet" type="text/css" href="/client/css/loginDialog.css">
        <link rel="stylesheet" type="text/css" href="/plugins/weui/css/weui.css">
        <script src="/plugins/jquery/jquery-1.9.1.js" type="text/javascript"></script>
        <c:if test="${isIos == true}">
            <script src="/client/js/login_ios.js" type="text/javascript"></script>
        </c:if>
        <c:if test="${isIos == false}">
            <script src="/client/js/login_andriod.js" type="text/javascript"></script>
        </c:if>
        <script src="/client/js/coin_mall.js" type="text/javascript"></script>
        <c:if test="${isWx == true}">
            <script src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js" type="text/javascript"></script>
            <script src="/plugins/wxNotShare/wxNotShare.js" type="text/javascript"></script>
        </c:if>
    </head>
    <body>
        <div>
            <input type="hidden" id="isIos" value="${isIos}">
            <input type="hidden" id="codeId" value="${codeId}">
            <input type="hidden" id="isLogin" value="${isLogin}">
            <input type="hidden" id="isWx" value="${isWx}">
            <c:if test="${isWx == true}">
                <input type="hidden" id="appId" value="${share.appId}">
                <input type="hidden" id="timestamp" value="${share.timestamp}">
                <input type="hidden" id="nonceStr" value="${share.nonceStr}">
                <input type="hidden" id="signature" value="${share.signature}">
                <input type="hidden" id="openId" value="${openId}">
                <input type="hidden" id="nickName" value="${nickName}">
            </c:if>
        </div>
        <div class="content">
            <c:if test="${isLogin == true}">
                <div class="headDiv">
                    <div class="iconDiv"> <img src="${user.portrait}" class="headImg" ></div>
                    <div class="nameAndSignCountDiv">
                        <span class="name">${user.nickname}</span>
                        <span class="signCount"> 我的金币:<span style="color: #f96301">${user.userScore}</span></span>
                    </div>
                    <div class="signButtonDiv">
                        <div class="signDiv" >
                            <a href="javaScript:;" id="sign" onclick="exchangeRecords();"><span>兑换记录</span></a>
                        </div>
                    </div>
                </div>
            </c:if>
            <c:if test="${isLogin == false}">
                <div class="plzLogin">
                    <a onclick="alertLogin();">请登录</a>
                </div>
            </c:if>

            <div class="separateLine">
                <span>虚拟商品</span>
            </div>

            <div class="goodsDiv">
                <c:forEach var="i" items="${items}" begin="0" end="1">
                    <div class="goods2">
                        <a href="/c/p/coin/item/${i.itemId}" class="goods1A" >
                            <div class="nameAndCoin">
                                <span class="goods1Name">${i.itemTitle}</span><br>
                                <img src="/client/img/coinmall/coin.png" class="coinIcon"><span class="priceSpan" >${i.itemCoin}</span><br>
                            </div>
                            <img src=${i.itemImg} class="goods1Icon">
                        </a>
                    </div>
                </c:forEach>
            </div>
            <div class="goodsDiv">
                <c:forEach var="i" items="${items}" begin="2" end="3">
                    <div class="goods2">
                        <a href="/c/p/coin/item/${i.itemId}" class="goods1A" >
                            <div class="nameAndCoin">
                                <span class="goods1Name">${i.itemTitle}</span><br>
                                <img src="/client/img/coinmall/coin.png" class="coinIcon"><span class="priceSpan" >${i.itemCoin}</span><br>
                            </div>
                            <img src=${i.itemImg} class="goods1Icon">
                        </a>
                    </div>
                </c:forEach>
            </div>


            <div class="separateLine">
                <span>热门商品</span>
            </div>
            <div class="goodsDiv">
                <c:forEach var="i" items="${real}" begin="0" end="1">
                    <div class="goods2">
                        <a href="/c/p/coin/item/${i.itemId}" class="goods1A" >
                            <div class="nameAndCoin">
                                <span class="goods1Name">${i.itemTitle}</span><br>
                                <img src="/client/img/coinmall/coin.png" class="coinIcon"><span class="priceSpan" >${i.itemCoin}</span><br>
                            </div>
                            <img src=${i.itemImg} class="goods1Icon">
                        </a>
                    </div>
                </c:forEach>
            </div>
            <div class="goodsDiv">
                <c:forEach var="i" items="${real}" begin="2" end="3">
                    <div class="goods2">
                        <a href="/c/p/coin/item/${i.itemId}" class="goods1A" >
                            <div class="nameAndCoin">
                                <span class="goods1Name">${i.itemTitle}</span><br>
                                <img src="/client/img/coinmall/coin.png" class="coinIcon"><span class="priceSpan" >${i.itemCoin}</span><br>
                            </div>
                            <img src=${i.itemImg} class="goods1Icon">
                        </a>
                    </div>
                </c:forEach>
            </div>
            <div class="goodsDiv">
                <c:forEach var="i" items="${real}" begin="4" end="5">
                    <div class="goods2">
                        <a href="/c/p/coin/item/${i.itemId}" class="goods1A" >
                            <div class="nameAndCoin">
                                <span class="goods1Name">${i.itemTitle}</span><br>
                                <img src="/client/img/coinmall/coin.png" class="coinIcon"><span class="priceSpan" >${i.itemCoin}</span><br>
                            </div>
                            <img src=${i.itemImg} class="goods1Icon">
                        </a>
                    </div>
                </c:forEach>
            </div>



            <div class="rightDiv">
                <span>全民红包&nbsp;&nbsp;版权所有</span>
                <span>本活动由全民红包提供,&nbsp;&nbsp;与设备提供商苹果公司无关</span>
                <span>合作请联系:&nbsp;&nbsp;jiangpeng90@dingtalk.com</span>
            </div>
            <%@include file="tpl/loginTpl.jsp"%>

        </div><!--content结束 -->

        <div class="weui_dialog_confirm" id="alertLogin" style="display: none">
            <div class="weui_mask"></div>
            <div class="weui_dialog">
                <div class="weui_dialog_hd"><strong class="weui_dialog_title" id="alertMsg"></strong></div>
                <div class="weui_dialog_bd" id="desc" ></div>
                <div class="weui_dialog_ft">
                    <a href="#" onclick="hideAlertLogin();" class="weui_btn_dialog primary">确定</a>
                </div>
            </div>
        </div>

    </body>
</html>
