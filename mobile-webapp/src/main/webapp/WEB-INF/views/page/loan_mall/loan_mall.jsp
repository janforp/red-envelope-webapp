<!--需审核任务详情页面-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>贷款中心</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <script type="text/javascript" src="/plugins/jquery/jquery-1.9.1.min.js"></script>
    <link rel="stylesheet" type="text/css" href="/client/css/base/red-envelope-base.css">
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
            background-color: #f5f5f5;
            font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
        }
        .content{
            width: 100%;
            margin:0 auto;
            max-width: 414px;
        }

        .banner{
            width: 100%;
            display: flex;
            justify-content: center;
            align-items: center;
            background-color: #FFFFFF;
        }
        .banner img{
            /*max-height: 200px;*/
            width: 100%;
        }

        a{
            text-decoration: none;
            -webkit-tap-highlight-color: rgba(0,0,0,0);
        }

        .nav-bar{
            width: 100%;
            display: flex;
            justify-content: space-between;
            align-items: center;
            background-color: #FFFFFF;
        }
        .nav-bar span{
            display: block;
            font-size: 1rem;
        }
        .nav-bar a{
            text-decoration: none;
            -webkit-tap-highlight-color: rgba(0,0,0,0);
            color: black;
            display: inline-block;
            width: 100%;
            text-align: center;
        }

        .nav{
            width: 33.33%;
            padding: 1rem 0;
            text-align: center;
        }

        .active{
            color:#3ccaff;
            border-bottom: 2px solid #3ccaff;
        }
        .items{
            background-color: #FFFFFF;
            width: 100%;
        }
        .item{
            width: 100%;
            padding: 0.5rem 0;
        }
        .itemTop{
            padding: 0.5rem;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .imgDiv{
            width: 60px;
            margin-right: 10px;
        }
        .imgDiv img{
            width: 60px;
            height: 60px;
        }
        .descDiv{
            width: 50%;
            height: 66px;
            display: flex;
            justify-content: space-between;
            flex-direction: column;
        }
        .descDiv span{
            margin-bottom: 2px;
        }
        .title{
            display: block;
            font-size: 1.1rem;
        }
        .text{
            width: 100%;
            padding: 0.5rem;
        }
        .desc{
            display: block;
            font-size: 0.8rem;

        }
        .label{
            display: inline-block;
            border: 1px solid #3ccaff;
            color: #3ccaff;
            margin-right: 2px;
            border-radius: 4px;
            padding: 2px 3px;
            font-size: 0.4rem;
            letter-spacing: 1px;
            font-weight: 500;
        }
        .btnDiv{
            width: 100px;
            text-align: right;
            margin-left: 10px;
            display: flex;
            justify-content: flex-end;
            flex-direction: column;
        }
        .num{
            display: block;
            width: 100%;
            text-align: center;
            margin-top: 2px;
        }
        .btnDiv a{
            text-align: center;
            padding: 7px 4px;
            border-radius: 4px;
            font-size: 0.8rem;
            color: white;
            display: block;
            /*z-index: 999;*/
        }
        .itemBottom{
            width: 100%;
            padding: 0.5rem;
            display: flex;
            justify-content: space-around;
            align-items: center;
        }
        .earning{
            width: 33.3%;
            text-align: center;
            border-right: 1px solid #efefef;
        }
        .itemBottom .earning:last-child{
            border-right: none;
        }
        .earning span{
            display: block;
        }
        .earning span:nth-child(1){
            font-size: 1.5rem;
            color:#f96301;
            font-weight: 300;
        }
        .earning span:nth-child(2){
            margin-top: 7px;
            font-size: 0.8rem;
            color: #3c3c3c;
        }

        .bottomDiv{
            width:100%;
        }
        .bottomDiv img{
            width: 100%;
        }
    </style>
    <script>
        var jsonData;
        $(function () {
            $.ajax({
                url:'/c/p/loan/list',
                type:'POST',
                dataType:'JSON',
                data:{orderType:0},
                success:function (data) {
                    if (data.code == 0){
                        var vos = data.data;
                        window.jsonData = vos;
                    }
                }
            });
        });
        /**
         * 点击更换排序规则
         * @param type
         */
        function getFinData(type){

            var data = window.jsonData;
            financial.init(type,data);
            financial.reset();
            financial.orderDate();
            financial.addActive();
            financial.appendAll();
        }
    </script>
    <script>
        /**
         * @type {{type: null, data: null, reverse: boolean, field: string, init: financial.init, reset: financial.reset, addActive: financial.addActive, orderDate: financial.orderDate, orderJson: financial.orderJson, printData: financial.printData, fillData: financial.fillData, appendAll: financial.appendAll, appendItem: financial.appendItem}}
         */
        var financial = {

            type:null,
            data:null,
            desc:false,
            field:'',
            /**
             * 初始化，数据及排序类型
             * @param type：排序类型
             * @param data：数据
             */
            init:function (type,data) {
                this.type = type;
                this.data = data;

                if (type == 0){
                    this.field = 'displayMoney';
                    this.desc = true;
                }else if(type == 1){
                    this.field = 'monthInterestRate';
                    this.desc = true;
                }else if(type == 2){
                    this.field = 'toAccountTime';
                    this.desc = false;
                }
            },
            /**
             * 每次点击不同排序规则的时候，先清空页面上的信息
             * 每次都要重置是否倒序
             */
            reset:function () {

                $("#items").empty();

            },
            /**
             * 排序按钮下方的激活样式
             */
            addActive:function () {
                $('.nav').removeClass('active');
                $("#type"+this.type).addClass('active');
            },

            /**
             * 根据排序字段的不同，给json排序
             */
            orderDate:function () {

                this.data = this.orderJson(this.data,this.field);

            },
            /**
             * json排序方法
             * @param array：需要排序的json数据
             * @param field：排序的字段
             * @returns {*} 排序之后的json
             */
            orderJson:function (array, field) {

                //数组长度小于2 或 没有指定排序字段 或 不是json格式数据
                if(array.length < 2 || !field || typeof array[0] !== "object") return array;
                //数字类型排序
                if(typeof array[0][field] === "number") {

                    array.sort(function(x, y) {

                        if (x[field] != y[field]){

                            return parseFloat(x[field]) - parseFloat(y[field]);
                        }else {
                            return x['id'] - y['id'];
                        }
                    });
                }
                //字符串类型排序
                if(typeof array[0][field] === "string") {
                    // array.sort(function(x, y) { return x[field].localeCompare(y[field])});
                    array.sort(function(x, y) {

                        if (x[field] != y[field]){

                            return parseFloat(x[field]) - parseFloat(y[field]);
                        }else {
                            return x['id'] - y['id'];
                        }
                    });
                }
                if(this.desc){
                    array.reverse();
                }
                return array;
            },
            /**
             * 测试时打印数据方法
             * @param data
             */
            printData:function (data) {

                console.log(data);
            },
            /**
             * 往页面添加数据
             * @param data
             */
            fillData:function (data) {

                $("#items").append(this.appendItem(data));
            },
            /**
             * 循环便利添加数据
             */
            appendAll:function () {

                for(var i in this.data){

                    this.fillData(this.data[i]);
                }
            },
            /**
             * 添加一个任务的戴输入框
             */
            appendItem:function (mission) {

                var icon = mission.icon;
                var title = mission.title;
                var displayMoney = mission.displayMoney;
                var clickUrl = mission.clickUrl;
                var id = mission.id;
                var toAccountTime = mission.toAccountTime;
                if (toAccountTime == 0){
                    toAccountTime = '当天到账';
                }

                var labels = mission.label;
                var participantsNum = mission.participantsNum;
                var monthInterestRate = mission.monthInterestRate;

                var newMission =

                        "<div class='item'>"+
                        "   <div class='itemTop'>"+
                        "       <div class='imgDiv'><img src='"+icon+"'></div>"+
                        "       <div class='descDiv'>"+
                        "       <span class='title' id='title'>"+title+"</span>"+
                        "<span class='desc red-comment'>可贷额度: "+displayMoney+"</span>"+
               "<span class='desc red-comment'>参考利息: 月利率"+monthInterestRate+"</span>"+
                        "       <div class='labels'>";
                for(var i in labels){
                    newMission +=  "<span class='label'>"+labels[i]+"</span> ";
                }
                newMission +=
                        "       </div>"+
                        "       </div>"+
                        "       <div class='btnDiv'>"+
                        "           <a href='"+clickUrl+"' class='red-main-bg-color-first'>立即申请</a>"+
                        "           <span class='num red-comment'>已有"+participantsNum+"人申请</span>"+
                        "       </div>"+
                        "       </div>"+
                        "</div>"+
                        "<div class='red-model-separator-line red-bg-color'></div>";

                return newMission;
            }
        };
    </script>
</head>
<body>
<div class="content"><!--content start-->
    <div class="banner">
        <%--<a href="http://www.baidu.com">--%>
        <%--<img src="/client/img/coinmall/detail1.jpg">--%>
        <%--</a>--%>
    </div>
    <div class="nav-bar">
        <a class="nav active" id="type0" href="javaScript:getFinData(0);"><div type="0"><span>贷款额度</span></div></a>
        <a class="nav" id="type1" href="javaScript:getFinData(1);"><div   type="1"><span>贷款利息</span></div></a>
        <a class="nav" id="type2" href="javaScript:getFinData(2);"><div type="2"><span>放款时间</span></div></a>
    </div>
    <div class="red-model-separator-line red-bg-color"></div>
    <div class="items" id="items">
        <c:forEach items="${vos}" var="v">
            <div class='item'>
                <div class='itemTop'>
                    <div class='imgDiv'><img src='${v.icon}'></div>
                    <div class='descDiv'>
                        <span class='title'>${v.title}</span>
                        <span class='desc red-comment'>可贷额度: ${v.displayMoney}</span>
                        <span class='desc red-comment'>参考利息: 月利率${v.monthInterestRate}</span>
                        <div class='labels'>
                            <c:forEach items='${v.label}' var='la'>
                                <span class='label'>${la}</span>
                            </c:forEach>
                        </div>
                    </div>
                    <div class='btnDiv'>
                        <a href='${v.clickUrl}' class='red-main-bg-color-first'>立即申请</a>
                        <span class="num red-comment">已有${v.participantsNum}人申请</span>
                    </div>
                </div>
            </div>
            <div class='red-model-separator-line red-bg-color'></div>
        </c:forEach>
    </div>
</div><!--content over-->
</body>
</html>