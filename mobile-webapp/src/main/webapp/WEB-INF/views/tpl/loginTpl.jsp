<link rel="stylesheet" type="text/css" href="/client/css/loginDialog.css">
<div id="LoginBox" style="background-color: white;margin: 5% 5% 20% 5%; width: 90% ;-moz-opacity: 1;opacity: 1;filter: alpha(opacity=100); ">
    <div>
        <a href="javaScript:;"  id="close"><span style="display: block;text-align: right;margin-right: 1rem;color: black"><img src="/client/img/coinmall/close.png" style="width: 20px;"></span></a>
    </div>
    <div class="row">
        <input type="text" onfocus="this.type='number'" onblur="this.type='text'" id="phone" placeholder="手机号" style="border: 1px solid #EFEFEF" />
    </div>
    <%--<div class="row">--%>
        <%--<div style="float: left;width: 50%;">--%>
            <%--<input type="text" id="vCode" placeholder="图形验证码" style="border: 1px solid #EFEFEF;padding-right: 0.2rem" />--%>
        <%--</div>--%>
        <%--<div style="float: left;width: 50%;">--%>
            <%--<a href="javaScript:;"><img class="captcha" style="width: 100px;height: 30px;background-color: #00AA88" src=""/></a>--%>
        <%--</div>--%>
    <%--</div>--%>
    <div class="row">
        <div style="float: left;width: 50%;">
            <input type="number" id="smsCode" placeholder="验证码" style="border: 1px solid #EFEFEF;padding-right: 0.2rem" />
        </div>
        <div style="float: left;width: 50%;">
            <button class="getSmsCode">
                <span id="buttonSpan">获取验证码</span>
            </button>
        </div>
    </div>
    <div class="row">
        <a id="download"  class="downloadBtn" href="http://app.qq.com/#id=detail&appid=1105711400" style="display: none">下载</a>
        <a id="login" href="javaScript:;" class="loginBtn"> 登录 </a>
    </div>
    <div class="row">
        <input disabled type="text" id="alert">
    </div>
</div>