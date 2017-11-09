<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>幸运大转盘</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="format-detection" content="telephone=no">
    <script type="text/javascript" src="/plugins/jquery/jquery-1.9.1.min.js"></script>
    <style>
        * {
            margin: 0;
            padding: 0;
            border:0;
            box-sizing: border-box;
        }
        body {
            -moz-user-select: none;
            -webkit-user-select: none;
            -ms-user-select: none;
            -khtml-user-select: none;
            user-select: none;
            font-size: 62.5%;
            height: auto;
            background-color:#342C4F ;
        }
        .content {
            width: 100%;
            height: auto;
            margin: 0 auto;
            max-width: 414px;
            background-color: #342C4F;
        }

        #rotateDiv{
            padding: 1rem;
            width: 100%;
            background-color:#342C4F;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .rotateContainer{
            width: 360px;
            height: 300px;
            background-color: red;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .column{
            width: 33.33%;
            height: 300px;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;
        }

        .prize{
            width: 100%;
            height: 100px;
            border-radius: 10px;
            border: 1.5px solid red;
            background-color: white;
            display: flex;
            justify-content: center;
            align-items: center;
            flex-direction: column;

        }
        .prize img{
            max-width: 90px;
            max-height: 90px;
        }

        .button{
            background-color: #84BE60;
        }

        .active{background-color:#FFE43C;}
    </style>
    <script>
        var click = false ;
        $(document).ready(
                function(){
                    lottery.init('lottery');
                    $("#lottery a").click(function(){
                        if(click) {
                            return false;
                        }
                        else{
                            lottery.speed=100;
                            roll();
                            click=true;
                            return false;
                        }
                    });
                });
        function roll(){
            lottery.times += 1;
            lottery.roll();
            if (lottery.times > lottery.cycle+10 && lottery.index==${lotteryIndex}) {
                clearTimeout(lottery.timer);
                lottery.prize=-1;
                lottery.times=0;
                click=false;
            }else{
                if (lottery.times<lottery.cycle) {
                    lottery.speed -= 10;
                }else if(lottery.times==lottery.cycle) {
                    var index = Math.random()*(lottery.count)|0;
                    lottery.prize = index;
                }else{
                    if (lottery.times > lottery.cycle+10 && ((lottery.prize==0 && lottery.index==7) || lottery.prize==lottery.index+1)) {
                        lottery.speed += 110;
                    }else{
                        lottery.speed += 20;
                    }
                }
                if (lottery.speed<40) {
                    lottery.speed=40;
                };
                //console.log(lottery.times+'^^^^^^'+lottery.speed+'^^^^^^^'+lottery.prize);
                lottery.timer = setTimeout(roll,lottery.speed);
            }
            return false;
        }
    </script>


</head>
<body>
<div class="content">
    <div id="rotateDiv">
        <div class="rotateContainer" id="lottery">
            <div class="column">
                <div class="prize lottery-unit lottery-unit-0"><img src="/client/img/top1.png"></div>
                <div class="prize lottery-unit lottery-unit-8"><img src="/client/img/top1.png"></div>
                <div class="prize lottery-unit lottery-unit-7"><img src="/client/img/top1.png"></div>
            </div>
            <div class="column">
                <div class="prize lottery-unit lottery-unit-2"><img src="/client/img/top1.png"></div>
                <div class="prize button"><a>朱晨剑在测试</a></div>
                <div class="prize lottery-unit lottery-unit-6"><img src="/client/img/top1.png"></div>
            </div>
            <div class="column">
                <div class="prize lottery-unit lottery-unit-3"><img src="/client/img/top1.png"></div>
                <div class="prize lottery-unit lottery-unit-4"><img src="/client/img/top1.png"></div>
                <div class="prize lottery-unit lottery-unit-5"><img src="/client/img/top1.png"></div>
            </div>
        </div>
    </div>
</div>

<script>
    var lottery={
        index:-1,	//当前转动到哪个位置，起点位置
        count:0,	//总共有多少个位置
        timer:0,	//setTimeout的ID，用clearTimeout清除
        speed:20,	//初始转动速度
        times:0,	//转动次数
        cycle:70,	//转动基本次数：即至少需要转动多少次再进入抽奖环节
        prize:-1,	//中奖位置
        init:function(id){
            if ($("#"+id).find(".lottery-unit").length>0) {
                $lottery = $("#"+id);
                $units = $lottery.find(".lottery-unit");
                this.obj = $lottery;
                this.count = $units.length;
                $lottery.find(".lottery-unit-"+this.index).addClass("active");
            };
        },
        roll:function(){
            var index = this.index;
            var count = this.count;
            var lottery = this.obj;
            $(lottery).find(".lottery-unit-"+index).removeClass("active");
            index += 1;
            if (index>count-1) {
                index = 0;
            };
            $(lottery).find(".lottery-unit-"+index).addClass("active");
            this.index=index;
            return false;
        },
        stop:function(index){
            this.prize=index;
            return false;
        }
    };

</script>
</body>
</html>
