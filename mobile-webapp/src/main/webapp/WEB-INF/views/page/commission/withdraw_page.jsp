<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <title>邀请有礼</title>
    <link rel="stylesheet" type="text/css" href="/client/css/commission/withdraw_paeg.css">
    <script src="/plugins/jquery/jquery-1.9.1.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="/plugins/weui/css/weui.css">
    <script>
        function withdraw() {
            var money = $("#money").val().trim();
            if (money == ""||money == null || money == undefined){
                $("#msg").html("请输入提现额度");
                $("#alertLogin").show();
                return;
            }
            if(money < 10){
                $("#msg").html("提现最低额度为10元");
                $("#alertLogin").show();
                return;
            }
            if (money > ${money}){
                $("#msg").html("佣金余额不足");
                $("#alertLogin").show();
                return;
            }
            $.ajax({
                url:"/c/p/commission/withdraw",
                type:"POST",
                dataType:"JSON",
                data:{money:money},
                success:function (data) {
                    $("#msg").html(data.msg);
                    $("#alertLogin").show();
                },
                error:function () {
                    
                }
            });

        }


        function hideAlertLogin() {

            $("#alertMsg").text("");
            $("#desc").text("")

            $("#alertLogin").hide();
            window.location.reload();
        }
    </script>
</head>
<body>
    <div class="content">
        <div class="headerDiv">
            <span class="headTitle">可提收益</span>
            <span id="totalMoney">${money}</span>
        </div>
        <div class="withdrawDiv">
            <span class="withdrawSpan">提现金额</span>
            <span id="withdrawMoney">¥<input type="number" id="money" placeholder="0.00"></span>
        </div>
        <div class="footButtonDiv">
            <button onclick="withdraw();"><span>提交</span></button>
        </div>
        <div class="ruleTitle">
            <div class="ruleTitleLeft">
                <span><hr></span>
            </div>
            <div class="ruleTitleMid">
                <span>活动规则</span>
            </div>
            <div class="ruleTitleRight">
                <span><hr></span>
            </div>
        </div>
        <div class="ruleTextDiv">
            <span class="ruleText">1.该次申请是提现到您的账户余额,之后您可以选择将余额里面的钱提现到支付宝,微信或者充话费；</span>
            <span class="ruleText">2.提现的最低额度为10元；</span>
            <span class="ruleText">3.提现申请会在3-5个工作日内审核完成,同时提现金额转入您的账户余额；</span>
            <span class="ruleText">4.节假日提现时间顺眼;</span>
            <span class="ruleText">5.若发现使用违规手段获取收益作封号处理；</span>
            <span class="ruleText">6.最终解释权归全民红包所有。</span>
            <span class="right">本活动与苹果公司无关</span>
        </div>
    </div>


    <div class="weui_dialog_confirm" id="alertLogin" style="display: none">
        <div class="weui_mask"></div>
        <div class="weui_dialog">
            <div class="weui_dialog_hd"><strong class="weui_dialog_title">提示</strong></div>
            <div class="weui_dialog_bd" id="msg"></div>
            <div class="weui_dialog_ft">
                <a href="#" onclick="hideAlertLogin();" class="weui_btn_dialog primary">确定</a>
            </div>
        </div>
    </div>

</body>
</html>