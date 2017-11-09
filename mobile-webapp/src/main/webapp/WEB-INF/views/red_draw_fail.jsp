<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>${title}</title>

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
            .redTop {
                width: 100%;
                height: 5rem;
                border: 1px solid #D4324B;
                background-color: #D4324B;
                border-radius: 0 0 50% 50%;
                text-align: center;
            }
            .headImg {
                text-align: center;
                width: 100%;
                height: 10%;
                margin-top: 0;
            }
            .headImg img{
                width: 20%;
                border: 1px solid #F5F4F5;
                border-radius: 50%;
                display: inline-block;
                margin-top: -10%;
            }

            .content {
                margin: 0 1rem 0 1rem;
                height: auto;
                background-color: #ffffff;
            }

            .line {
                /*margin: 0rem -1rem 0 -1rem;*/
                border-top: 1px solid #EFEFEF;
            }

            .list {
                margin-top: 2rem;
                border: 1px solid #F1F1F1;
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

        </style>

    </head>

    <body>
        <div class="container" id="container">
            <div class="redTop">
                <div style="margin-top: 1rem;">
                    <%--<span style="color: #FFBC3C; font-size: 1.2rem;">领取失败!</span>--%>
                </div>
            </div>
            <div class="headImg">
                <img src="${customerImg}">
            </div>
            <div class="content">
                <div style="text-align: center">
                    <span style="color: #000000; font-size: 1.2rem;">${msg}</span><br>
                </div>
                <div style="text-align: center; margin-top: 1.5rem">
                    <span style="color: #9e9e9e; font-size: 0.8rem;">领取更多红包</span><br>
                </div>
                <div class="list">
                    <ul>
                        <c:forEach items="${redList}" var="red" varStatus="loop">
                            <li>
                                <a href="${red.url}">
                                    <div style="margin-left: 0.5rem; margin-right: 0.5rem">
                                        <img class="pic" src="${red.img}">
                                        <div style="margin-top: -3rem; margin-left: 4rem; width: 9rem; height: 3rem; line-height: 3rem;">
                                            <span style="font-size: 1.0rem; color: #000000">${red.name}的红包</span>
                                        </div>
                                        <div style="margin-top: -3rem; margin-left: 13rem; height: 3rem; line-height: 3rem; text-align: right">
                                            <span style="font-size: 1.0rem; color: red; ">前往领取</span>
                                        </div>
                                    </div>
                                </a>
                            </li>
                            <div class="line"></div>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </body>

</html>
