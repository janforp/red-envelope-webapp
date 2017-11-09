<%--
  Created by IntelliJ IDEA.
  User: wujie5
  Date: 2016/12/8
  Time: 下午6:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <script src="/plugins/jquery/jquery-all.min.js"></script>

    <title>${article.articleTitle}</title>

    <style>
        html {
            -ms-text-size-adjust: 100%;
            -webkit-text-size-adjust: 100%;
            line-height: 1.6;
        }
        *{
            margin: 0;
            padding: 0;
        }
        body {
            -webkit-touch-callout: none;
            font-family: -apple-system-font,"Helvetica Neue","PingFang SC","Hiragino Sans GB","Microsoft YaHei",sans-serif;
            background-color: #f3f3f3;
            line-height: inherit;
        }
        h1,h2,h3,h4,h5,h6{
            font-weight:400;
            font-size: 16px;
        }
        .rich_media_area_primary {
            position: relative;
            padding: 20px 15px 15px;
            background-color: #fff;
        }
        .rich_media_title {
            margin-bottom: 10px;
            line-height: 1.4;
            font-weight: 400;
            font-size: 24px;
        }

        div {
            display: block;
        }
        .rich_media_meta_list {
            position: relative;
            z-index: 1;
            margin-bottom: 18px;
            line-height: 20px;
            font-size: 0;
        }
        .rich_media_meta_list em {
            font-style: normal;
        }
        .rich_media_meta_text {
            color: #8c8c8c;
        }
        .rich_media_meta {
            display: inline-block;
            vertical-align: middle;
            margin-right: 8px;
            margin-bottom: 10px;
            font-size: 16px;
        }
        .rich_media_content {
            position: relative;
            overflow: auto;
            color: #3e3e3e;
        }

        .rich_media_content p {
            clear: both;
            min-height: 1em;
            white-space: pre-wrap;
        }
        p {
            display: block;
            -webkit-margin-before: 1em;
            -webkit-margin-after: 1em;
            -webkit-margin-start: 0px;
            -webkit-margin-end: 0px;
            margin-top: 0;
            margin-bottom: 0;
        }
        .rich_media_content * {
            max-width: 100%!important;
            box-sizing: border-box!important;
            -webkit-box-sizing: border-box!important;
            word-wrap: break-word!important;
        }
        .rich_media_tool {
            overflow: hidden;
            padding-top: 15px;
            line-height: 32px;
        }
        .rich_media_tool .meta_praise {
            margin-right: 0;
            margin-left: 8px;
        }

        .rich_media_tool .meta_primary {
            float: left;
            margin-right: 10px;
        }
        .meta_praise {
            -webkit-tap-highlight-color: rgba(0,0,0,0);
            outline: 0;
            min-width: 3.5em;
        }

        .icon_praise_gray {
            background: transparent url('/client/img/article/praise.png') no-repeat 0 0;
            width: 13px;
            height: 13px;
            vertical-align: middle;
            display: inline-block;
            -webkit-background-size: 100% auto;
            background-size: 100% auto;
        }
        .rich_media_tool .meta_extra {
            float: right;
            margin-left: 10px;
        }
        .tips_global {
            color: #8c8c8c;
        }
        a {
            color: #607fa6;
            text-decoration: none;
        }

        a{
            text-decoration: none;
            -webkit-tap-highlight-color: rgba(0,0,0,0);
        }
        .ad-div img {
            margin: 10px 0;
        }

    </style>
    <script>

        var isPraised = false;
        var articleId = '${article.articleId}';
        var openId = '${openId}';
        //是否已经真正的点赞
        var isAlreadyPraised = ${isPraised};
        $(function () {

            if (isAlreadyPraised){
                $("#praise-bg").css('background-position','0px -18px');
                isPraised = true;
            }
            var praiseNum = $("#likeNum").text();
            if(praiseNum == "" || praiseNum == null || praiseNum == undefined){
                $("#likeNum").text(0);
            }

            var trueReadTimes = '${article.readTimes}';
            if(parseInt(trueReadTimes)+30000 > 100000){

                $("#readNum").text("100000+");
            }else{
                $("#readNum").text(parseInt(trueReadTimes)+30000);
            }

            var truePraiseTimes = '${article.praiseTimes}';

                $("#likeNum").text(parseInt(truePraiseTimes)+600);
        });
        
        function praise() {

            var praiseNum = $("#likeNum").text();
            praiseNum = parseInt(praiseNum);

            if(! isPraised){

                $("#praise-bg").css('background-position','0px -18px');
                $("#likeNum").text(praiseNum + 1);
                isPraised = true;

                if (! isAlreadyPraised){
                    $.post(
                            '/c/p/article/praise',
                            {articleId:articleId,openId:openId},
                            function (data) {

                                isAlreadyPraised = true;
                            });
                }
            }else {
                $("#praise-bg").css('background-position','0px 0px');
                isPraised = false;
                if(praiseNum > 0){
                    $("#likeNum").text(praiseNum - 1);
                }
            }
        }
    </script>
</head>
<body>
<div id="page-content">
    <div class="rich_media_area_primary">

        <h2 class="rich_media_title" id="activity-name">
            ${article.articleTitle}
        </h2>

        <div class="rich_media_meta_list">
            <em id="post-date" class="rich_media_meta rich_media_meta_text"> ${article.articleDisplayTime}</em>
            <span class="rich_media_meta rich_media_meta_text rich_media_meta_nickname">${article.articleAuthor}</span>
        </div>

        <div class="rich_media_content ">

            <div class="ad-div">
                <c:forEach items="${ads}" var="ad" begin="0" end="0">
                    <a href=${ad.adUrl}><img src='${ad.adIcon}'></a>
                </c:forEach>
            </div>

            ${article.articleContent}

            <div class="ad-div">
                <c:forEach items="${ads}" var="ad" begin="1" end="1">
                    <a href=${ad.adUrl}><img src='${ad.adIcon}'></a>
                </c:forEach>
            </div>

        </div>

        <div class="rich_media_tool">
            <div class="media_tool_meta tips_global meta_primary">阅读<span id="readNum"></span></div>
            <span class="media_tool_meta meta_primary tips_global meta_praise">
                <a href="javaScript:praise();">
                    <i class="icon_praise_gray" id="praise-bg"></i>
                </a>
                <span class="praise_num" id="likeNum"></span>
            </span>
            <a id="js_report_article3" class="media_tool_meta tips_global meta_extra" href="/page/article/complainPage.html">投诉</a>
        </div>
    </div>
</div>

</body>
</html>
