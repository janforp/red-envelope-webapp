<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>系统消息</title>
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
            background-color: #FFFFFF;
        }
        .container {
            width: 100%;
            height: auto;
            margin-left: auto;
            margin-right: auto;
            max-width: 414px;
            position: relative;
        }
        .content {
            margin: 0 1rem 0 1rem;
            height: auto;
        }
        .img {
            margin-top: 2rem;
            text-align: center;
        }
        .span {
            font-size: 1.0rem;
            color: #969696;
            text-align: center;
        }
    </style>

</head>
<body>
    <div class="container">
        <div class="content">
            <div class="img">
                <img src="/client/img/system.png" width="200px">
            </div>
            <div class="span">
                <span>暂无消息</span>
            </div>
        </div>
    </div>
</body>
</html>
