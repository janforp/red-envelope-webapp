<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${article.articleTitle}</title>
    <style>
        .share{
            width: 100%;
        }
    </style>
</head>
<body>
<img src="${article.articleIcon}" style="width: 0;height: 0;">
<img src="/client/img/article/share.png" class="share">
</body>
</html>
