<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <meta name="format-detection" content="black">
    <title>APP下载</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-size: 62.5%;
            background-color: #FF4D55;
        }

        :focus {
            outline: none;
        }

        .content {
            width: 100%;
            height: auto;
            max-width: 414px;
            margin-left: auto;
            margin-right: auto;
        }

        .headerDiv {
            background-color: #F0F0F0;
            overflow: auto;
            padding-left: 1rem;
            padding-right: 1rem;
        }

        .qrRedDiv {
            width: 50%;
            float: left;
            text-align: center;
            margin: auto 0;
            padding-top: 0.5rem;
            padding-bottom: 0.5rem;
        }

        .qrRedDiv img {
            width: 90%;
        }

        .buttonDiv {
            width: 50%;
            float: left;
            text-align: right;
            padding: 0.6rem 0 0.5rem 0;
        }

        .buttonDiv a {
            display: inline-block;
        }

        .buttonDiv button {

            background-color: #FF4850;
            margin-top: auto;
            margin-bottom: auto;
            border: none;
            border-radius: 4px;
            padding: 0.5rem 1rem;
            color: white;
            font-size: 1rem;
            letter-spacing: 1px;
        }

    </style>
</head>
<body>
<div class="content">
    <div class="headerDiv">
        <div class="qrRedDiv">
            <img src="/client/img/commission/qred.png">
        </div>
        <div class="buttonDiv">
            <a
                <c:if test="${ios == true}">
                    href="https://itunes.apple.com/cn/app/quan-min-hong-bao-yi-da-bo/id1160713685?mt=8"
                </c:if>
                <c:if test="${ios == false}">
                    href="http://shouji.baidu.com/software/10332783.html"
                </c:if>
            >
                <button>点击下载</button>
            </a>
        </div>
    </div>
    <div class="imgDiv">
        <img src="/client/img/commission/invitationStep.png" style="width: 100%;">
    </div>
</div>
</body>
</html>