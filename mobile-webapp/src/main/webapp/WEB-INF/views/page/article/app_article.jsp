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
    <link rel="stylesheet" type="text/css" href="/plugins/weui-1.0.2/css/weui.min.css">
    <script src="/plugins/weui-1.0.2/js/weui.js"></script>

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
            overflow: hidden;
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



    </style>

    <script>
        var money = '${money}';
        var isApp = ${isApp};
        $(function () {
            if (isApp && money != 0 && money != 0.00){
                dialog.alert('提示','恭喜您获得阅读奖励+'+money+"元！");
            }
        });

    </script>
</head>
<body>
    <img src="${article.articleIcon}" style="width: 0;height: 0;">
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

                ${article.articleContent}

            </div>
        </div>
    </div>


    <div id="global-toast" style="display: none;">
        <div class="weui-mask_transparent"></div>
        <div class="weui-toast">
            <i class="weui-icon-success-no-circle weui-icon_toast"></i>
            <p class="weui-toast__content">&nbsp;</p>
        </div>
    </div>

    <div id="global-toast-loading" style="display: none;">
        <div class="weui-mask_transparent"></div>
        <div class="weui-toast">
            <i class="weui-loading weui-icon_toast"></i>
            <p class="weui-toast__content">&nbsp;</p>
        </div>
    </div>

    <div id="global-dialog-confirm" style="display: none;">
        <div class="weui-mask"></div>
        <div class="weui-dialog">
            <div class="weui-dialog__hd"><strong class="weui-dialog__title">&nbsp;</strong></div>
            <div class="weui-dialog__bd">&nbsp;</div>
            <div class="weui-dialog__ft">
                <a href="javascript:;" class="weui-dialog__btn weui-dialog__btn_default">取消</a>
                <a href="javascript:;" class="weui-dialog__btn weui-dialog__btn_primary">确定</a>
            </div>
        </div>
    </div>

    <div id="global-dialog-alert" style="display: none;">
        <div class="weui-mask"></div>
        <div class="weui-dialog">
            <div class="weui-dialog__hd"><strong class="weui-dialog__title">&nbsp;</strong></div>
            <div class="weui-dialog__bd">&nbsp;</div>
            <div class="weui-dialog__ft">
                <a href="javascript:;" class="weui-dialog__btn weui-dialog__btn_primary">确定</a>
            </div>
        </div>
    </div>


</body>
</html>
