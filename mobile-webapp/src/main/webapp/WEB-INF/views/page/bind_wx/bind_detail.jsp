<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>绑定微信</title>
    <meta name="viewport"
          content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
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
            background-color: #ffffff;
        }

        .container {
            width: 100%;
            height: auto;
            margin-left: auto;
            margin-right: auto;
            max-width: 414px;
            position: relative;
            text-align: left;
        }

        .content {
            margin: 20px 20px 0 20px;
            height: auto;
            font-size: 1rem;
        }

        .content .span {
            margin-top: 2rem;
            margin-bottom: 1rem;
            line-height: 1.5rem;
        }

        .btn {
            margin-top: 2rem;
            width: 100%;
            margin-bottom: 2rem;
        }
        .btn a {
            display:inline-block;
            text-decoration: none;
            text-align: center;
            color: #FFFFFF;
            font-size: 1.0rem;
            line-height: 2.5rem;
            width: 100%;
            height: 2.5rem;
            margin-top: 0.5rem;
            background-color: #07B703;
            border-radius: 5px;
        }

    </style>

</head>
<body>
<div class="container">
    <div class="content">
        <div style="margin-top: 1rem; margin-bottom: 1rem; line-height: 1.5rem;">
            <span>第一步：打开微信，点击首页右上角“+”号，选择“添加朋友”。</span>
        </div>
        <div>
            <img src="/client/img/wxbind/1.jpg" width="100%">
        </div>
        <div class="span">
            <span>第二步：选择搜索“公众号”，如下图所示（某些较老版本微信可以直接在输入框中搜索公众号，公众号名称为：快抢钱）。</span>
        </div>
        <div>
            <img src="/client/img/wxbind/2.jpg" width="100%">
        </div>
        <div class="span">
            <span>第三步：在搜索框中输入“快抢钱”并搜索，搜索结果中选择下图所示的公众号并点击，然后选择“关注”。</span></div>

        <div>
            <img src="/client/img/wxbind/3.jpg" width="100%">
        </div>
        <div class="span">
            <span>第四步：点击公众号底部“领红包”，然后再点击“绑定微信”，即可进入绑定流程，按照提示操作即可。</span>
        </div>
        <div>
            <img src="/client/img/wxbind/4.jpg" width="100%">
        </div>

        <div class="btn">
            <c:if test="${isIos == false}">
                <a href="javaScript:;" onclick="userSign.openWX('com.tencent.mm');">前往微信</a>
            </c:if>
            <c:if test="${isIos == true}">
                <a href="weixin://" >前往微信</a>
            </c:if>
        </div>

    </div>
</div>
</body>
</html>
