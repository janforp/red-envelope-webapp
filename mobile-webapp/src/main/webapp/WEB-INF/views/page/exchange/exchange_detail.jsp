<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="format-detection" content="telephone=no">
        <link rel="stylesheet" type="text/css" href="/client/css/coinMall/exchange_detail.css">
        <title>金币兑换记录详情</title>
        <style>

        </style>
    </head>
    <body>
        <div class="content"><!--content start -->
            <div class="header">
                <div class="imgDiv">
                    <img src=${vo.icon}>
                </div>
                <div class="spanDiv">
                    <span>商品:&nbsp;&nbsp;${vo.title}</span>
                    <span>金币:&nbsp;&nbsp;${vo.score}</span>
                    <c:if test="${vo.status == '0'}">
                        <span>状态:&nbsp;&nbsp;审核中</span>
                    </c:if>
                    <c:if test="${vo.status == '1'}">
                        <span>状态:&nbsp;&nbsp;已发货</span>
                    </c:if>
                    <c:if test="${vo.status == '2'}">
                        <span>状态:&nbsp;&nbsp;<span style="color: red;display: inline-block">已作废</span></span>
                    </c:if>
                    <span>时间:&nbsp;&nbsp;${vo.sendTime}</span>
                </div>
            </div>
            <c:if test="${vo.status == '1'}">
                <div class="cardNumDiv">
                    <c:if test="${vo.type == '0'}"><!--实物-->
                    <span class="blockSpan">快递信息:&nbsp;&nbsp;<span class="num">${vo.expressNumber}</span></span>
                    </c:if>
                    <c:if test="${vo.type == '1'}"><!--虚拟-->
                    <span class="blockSpan">卡号:&nbsp;&nbsp;<span class="num">${vo.cardId}</span></span>
                    <span class="blockSpan">卡密:&nbsp;&nbsp;<span class="num">${vo.cardPassword}</span></span>
                    <span class="blockSpan">逾期时间:&nbsp;&nbsp;<span style=" color:black ;">${vo.invalidTime}</span></span>
                    </c:if>
                </div>
            </c:if>
            <div class="desc">
                <hr style="margin: 1rem 0">
                <span class="alert">说明事项</span>
                <span class="descSpan">1.在金币商城中点击[立即兑换],工作人员会进行审核;</span>
                <span class="descSpan">2.若是实物,则审核通过后,会在3个工作日内发货;</span>
                <span class="descSpan">2.若是虚拟商品,则会通过卡号及卡密的方式发放,在此页面可以查看您的卡号及卡密;</span>
                <span class="descSpan">3.虚拟商品在发货后,请尽快兑换,如逾期未兑换造成的损失,本平台概不负责;</span>
                <span class="descSpan">4.若存在违规行为,则兑换将被作废。</span>
            </div>



        </div><!--content over -->

        <div class="back">
            <a href="javaScript:;" id="backToCoinMall" onclick="history.go(-1);">
                <img src="/client/img/coinmall/home.png">
            </a>
        </div>
    </body>
</html>