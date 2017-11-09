<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>金币商城</title>
        <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
        <meta name="format-detection" content="telephone=no">
        <link rel="stylesheet" type="text/css" href="/client/css/input_code.css">
        <link rel="stylesheet" type="text/css" href="/client/css/inputCode_alertRule.css">
        <link rel="stylesheet" type="text/css" href="/plugins/weui/css/weui.css">

        <script src="/plugins/jquery/jquery-1.9.1.js" type="text/javascript"></script>
        <script src="/client/js/input_code.js" type="text/javascript"></script>
    </head>
    <body>

        <div class="content">
            <div class="rule">
                <a href="javaScript:;" id="ruleA">
                    <img src="/client/img/coinmall/rule.png">
                    <span>兑换规则</span>
                </a>
            </div>
            <div class="codeInput">
                <img src="/client/img/coinmall/rmb.png" >
                <span class="inputSpan">兑换红包码</span>
                <input type="text" id="code" onfocus="exchangeCss();" placeholder="请输入兑换码">
            </div>
            <button id="exchange"><span class="buttonSpan" >兑&nbsp;&nbsp;&nbsp;&nbsp;换</span></button><br>
            <input type="text" id="alert" value="兑换码有误">
            <input type="hidden" id="codeId" value="${codeId}">
            <div class="back">
                <a href="javaScript:;" id="backToCoinMall" onclick="history.go(-1);">
                    <img src="/client/img/coinmall/home.png">
                </a>
            </div>

            <div id="LoginBox"><!--兑换规则开始-->
                <div class="row" style="text-align: center;margin-bottom: 1rem">
                    <span style="font-size: 1.5rem">兑换规则</span>
                </div>
                <div class="row">
                    <span style="display: block;font-size: 1rem;text-align: left;color: #FA8240;margin-bottom: 1rem;margin-top: 1rem">Q: 什么是红包码?</span>
                    <span style="font-size: 0.8rem;line-height: 1.3rem">红包码是由全民红包或与全民红包合作的商家发放给用户的,作为兑换全民红包现金红包的凭证,成功兑换后,将以余额的形式保存至用户的财富中。</span>
                </div>

                <div class="row">
                    <span style="display: block;font-size: 1rem;text-align: left;color: #FA8240;margin-bottom: 1rem;margin-top: 1rem">Q: 红包码是什么样的?</span>
                    <span style="font-size: 0.8rem;line-height: 1.3rem">红包码是一串数字或字母组成的,长度为10位</span>
                </div>
                <div class="row">
                    <span style="display: block;font-size: 1rem;text-align: left;color: #FA8240;margin-bottom: 1rem;margin-top: 1rem">Q: 怎么获得红包码</span>
                    <span style="font-size: 0.8rem;line-height: 1.3rem">1,合作商家的官网,微信公众号;</span><br>
                    <span style="font-size: 0.8rem;line-height: 1.3rem">2,全民红包,金币商城的微信公众号;</span><br>
                    <span style="font-size: 0.8rem;line-height: 1.3rem">3,其他任何途径;</span><br>
                    <span style="font-size: 0.8rem;line-height: 1.3rem">温馨提示:每一种红包码的名额有限,领取后第一时间兑换哦!</span>
                </div>

                <div class="row">
                    <span style="display: block;font-size: 1rem;text-align: left;color: #FA8240;margin-bottom: 1rem;margin-top: 1rem">Q: 一个用户一天内最多能兑换几次红包码?</span>
                    <span style="font-size: 0.8rem;line-height: 1.3rem">A: 一个用户一天内不限兑换次数,除个别商家要求,每个用户只能领取,兑换一个红包码外。</span>
                </div>

                <div class="row">
                    <span style="display: block;font-size: 1rem;text-align: left;color: #FA8240;margin-bottom: 1rem;margin-top: 1rem">Q: 红包码可以重复兑换吗?</span>
                    <span style="font-size: 0.8rem;line-height: 1.3rem">A: 不能,一个兑换码只能兑换一次。</span>
                </div>
                <div class="row">
                    <span style="display: block;font-size: 1rem;text-align: left;color: #FA8240;margin-bottom: 1rem;margin-top: 1rem">Q: 红包码可以找零或者兑换吗?</span>
                    <span style="font-size: 0.8rem;line-height: 1.3rem">A: 不可以。</span>
                </div>
                <div class="row">
                    <span style="display: block;font-size: 1rem;text-align: left;color: #FA8240;margin-bottom: 1rem;margin-top: 1rem">特别提醒</span>
                    <span style="font-size: 0.8rem;line-height: 1.3rem">如有任何疑问请联系客服QQ:800160354</span>
                </div>
            </div><!--兑换规则框结束-->



            <div class="weui_dialog_confirm" id="alertLogin" style="display: none">
                <div class="weui_mask"></div>
                <div class="weui_dialog">
                    <div class="weui_dialog_hd"><strong class="weui_dialog_title" id="alertMsg">提示</strong></div>
                    <div class="weui_dialog_bd" id="desc" ></div>
                    <div class="weui_dialog_ft">
                        <a href="#" onclick="hideAlertLogin();" class="weui_btn_dialog primary">确定</a>
                    </div>
                </div>
            </div>


        </div><!--content结束 -->
    </body>
</html>
