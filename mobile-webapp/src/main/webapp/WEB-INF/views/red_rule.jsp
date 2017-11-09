<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>红包规则</title>

        <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
        <meta name="format-detection" content="telephone=no">

        <style>
            * {
                margin: 0;
                padding: 0;
                box-sizing: border-box;
            }
            body {
                -moz-user-select: none;
                -webkit-user-select: none;
                -ms-user-select: none;
                -khtml-user-select: none;
                user-select: none;
                font-size: 62.5%;
            }
            a {
                display:inline-block;
                text-decoration: none;
                width: 100%;
            }
            .container {
                width: 100%;
                height: auto;
                background-color: #ffffff;
                margin-left: auto;
                margin-right: auto;
                max-width: 414px;
                position: relative;
            }
            .banner {
                width: 100%;
                margin: 0;
            }
            .banner img {
                width: 100%;
            }
            .content {
                margin: 0 1rem 0 1rem;
                height: auto;
            }
            .title {
                width: 100%;
                height: auto;
                margin-top: 1rem;
                text-align: center;
            }
            .explain {
                width: 100%;
                height: auto;
            }
            .separator_line {
                margin: 1rem -1rem 0 -1rem;
                border-top: 0.5rem solid #EFEFEF;
            }

            .title_line {
                margin: 1rem -1rem 0 -1rem;
                border-top: 1px solid #EFEFEF;
            }

            .line {
                margin: 0rem -1rem 0 -1rem;
                border-top: 1px solid #EFEFEF;
            }

            .list ul {
                padding:0 0 0;
            }
            .list ul li {
                padding-top: 0.5rem;
                padding-bottom: 0.5rem;
                width: 100%;
                margin-bottom: 0.1rem;
                position: relative;
                display: inline-block;
            }

            .list ul li img {
                width: 3rem;
                height: 3rem;
                border: 1px solid #f7f7f7;
                border-radius: 50%;
            }

            /*底部悬浮栏*/
            .footer {
                display: inline;
                position: fixed;
                width: 100%;
                height: 3.5rem;
                padding: 1rem auto 1rem auto;
                left: 0;
                bottom: 0;
                z-index: 100;
                text-align: center;
                background-color: #EFEFEF;
                -moz-opacity: 0.9;
                opacity: .90;
                filter: alpha(opacity=90);
            }

            .footer a {
                display:inline-block;
                text-decoration: none;
                text-align: center;
                color: #FFFFFF;
                font-size: 1.0rem;
                line-height: 2.5rem;
                width: 13rem;
                height: 2.5rem;
                margin-top: 0.5rem;
                background-color: #D93A55;
                border-radius: 5px;
            }

            .footer span {
                display:inline-block;
                text-decoration: none;
                text-align: center;
                color: #FFFFFF;
                font-size: 1.0rem;
                line-height: 2.5rem;
                width: 13rem;
                height: 2.5rem;
                margin-top: 0.5rem;
                background-color: #A2A2A2;
                border-radius: 5px;
            }

        </style>

    </head>

    <body>
        <div class="container">
            <c:if test="${banner.customerBanner != null}">
                <div class="banner">
                    <a href="${banner.customerBannerUrl}">
                        <img src="${banner.customerBanner}">
                    </a>
                </div>
            </c:if>
            <div class="content">
                <div class="title" style="text-align: left;">
                    <span style="color: #FB7516; font-size: 0.8rem;">注意：请在微信中打开该页面，否则无法领取红包。</span>
                </div>
                <div class="title" style="text-align: left;">
                    <span style="color: #A2A2A2; font-size: 1.2rem;">领取步骤:</span>
                </div>
                <div class="explain">
                    <c:forEach items="${steps}" var="step" varStatus="loop">
                        <div style="color: #000000; font-size: 1rem; margin-top: 0.8rem">
                            <span>${step.stepNum}.${step.stepContent}</span>
                        </div>
                    </c:forEach>
                    <div class="title" style="text-align: left;">
                        <span style="color: #FB7516; font-size: 0.8rem;">红包将直接打入微信钱包。</span>
                    </div>
                    <div class="separator_line"></div>
                </div>
                <div class="title">
                    <span style="color: #D73C57; font-size: 1.2rem; ">土豪榜</span>
                </div>
                <div class="title_line"></div>
                <div class="list">
                    <ul>
                        <c:forEach items="${records}" var="record" varStatus="loop">
                            <li>
                                <img class="pic" src="${record.userImg}">
                                <div style="margin-top: -3rem; margin-left: 4rem; width: 10rem; height: 3rem; line-height: 3rem;">
                                    <span style="font-size: 1.0rem;">${record.userNickname}</span>
                                </div>
                                <div style="margin-top: -3rem; margin-left: 14.5rem; height: 3rem; line-height: 3rem; text-align: right">
                                    <span style="font-size: 1.0rem; color: red; ">+${record.envelopeMoney}元</span>
                                </div>
                            </li>
                            <div class="line"></div>
                        </c:forEach>
                    </ul>
                </div>
                <c:choose>
                    <c:when test="${over == 1}">
                        <div style="height: 3.5rem"></div>
                        <div class="footer">
                            <span>已抢完</span>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <c:if test="${inWx == 1}">
                            <div style="height: 3.5rem"></div>
                            <div class="footer">
                                <a href="javascript:alert('请打开微信');">快抢红包</a>
                            </div>
                        </c:if>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </body>
</html>
