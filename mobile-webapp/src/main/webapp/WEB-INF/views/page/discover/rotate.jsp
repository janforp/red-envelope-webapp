<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>幸运大转盘</title>
        <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
        <meta name="format-detection" content="telephone=no">
        <link rel="stylesheet" href="/plugins/rotate/css/base.css">
        <link rel="stylesheet" href="/plugins/weui/css/weui.min.css"/>
        <link rel="stylesheet" type="text/css" href="/client/css/rotate/rotateAlert.css">

        <script type="text/javascript" src="/plugins/rotate/js/jquery.min.js"></script>
        <!--滚动插件 -->
        <script type="text/javascript" src="/plugins/vTicker/vticker.js"></script>

        <script type="text/javascript" src="/plugins/rotate/js/awardRotate.js"></script>
        <script type="text/javascript" src="/plugins/weui/js/weui.js"></script>

        <c:if test="${isIos == true}">
            <script type="text/javascript" src="/plugins/rotate/js/base_ios.js"></script>
        </c:if>
        <c:if test="${isIos == false}">
            <script type="text/javascript" src="/plugins/rotate/js/base_andriod.js"></script>
        </c:if>

        <script>
            var times = ${times};


            function showAlert() {
                //弹出
                $(".content").append("<div id='mask'></div>");
                $("#mask").addClass("mask").fadeIn("fast");
                $("#LoginBox").fadeIn("fast");
                $("body").css("overflow","hidden");
            }
            function closeAlert() {
                $("#LoginBox").hide();
                $("#mask").removeClass("mask")
                $("body").css("overflow","");
            }




            $(function () {

                setInterval(function () {

                    $.ajax({
                        url:"/c/p/rotate/getNews",
                        type:"POST",
                        dataType:"JSON",
                        success:function (data) {
                            var ul = $("#ul");
                            var awards = data.data.awards;
                            var news = "";
                            for (var i=0;i<awards.length;i++){

                                news +=
                                        "   <li style='width: 100%;overflow: auto;display: block'>"+
                                        "       <div style='float:left;width: 40%'>"+
                                        "           <span >"+awards[i].nickname+"</span>"+
                                        "       </div>"+
                                        "       <div style='float:left;width: 30%'>"+
                                        "           <span >"+awards[i].time+"</span>"+
                                        "       </div>"+
                                        "       <div style='float:left;width: 30%;text-align: left'>"+
                                        "           <span"+awards[i].award+"</span>"+
                                        "       </div>"+
                                        "   </li>";
                            }
                            ul.append(news);

                        },

                    });
                },10000);
            })

            $(function() {

                $('.downbok').vTicker({

                    speed: 1000,     //滚动速度，单位毫秒。

                    pause: 0,        //暂停时间，就是滚动一条之后停留的时间，单位毫秒。

//                    showItems: 5,      //显示内容的条数。

                    animation: '',  //动画效果，默认是fade，淡出。

                    mousePause: false,   //鼠标移动到内容上是否暂停滚动，默认为true。

                    height: 150,        //滚动内容的高度。

                    direction: 'up'     //滚动的方向，默认为up向上，down则为向下滚动。

                });
            });

        </script>

    </head>

    <body>

        <div class="container content">

            <div class="banner">
                <img src="/plugins/rotate/img/banner.png" width="100%">
            </div>

            <div style="text-align: center; font-size: 1.1rem;">
                <c:choose>
                    <c:when test="${times == 0}">
                        <p style="color: #F42A3B;">今日免费次数已用完，您还可以继续玩，每玩一次消耗5金币哦～</p>
                    </c:when>
                    <c:otherwise>
                        <p style="color: #F42A3B;">今天还剩<span style="color: #FB5964; font-size: 2rem;"> ${times} </span>次免费机会</p>
                    </c:otherwise>
                </c:choose>
            </div>

            <div class="turntable">
                <img class="btn" src="/plugins/rotate/img/button.png" width="30%">
                <img class="rotate" id="rotate" src="/plugins/rotate/img/rotate.png" width="86%">
                <img src="/plugins/rotate/img/rotate_bg2.png" width="100%">
            </div>


            <div id="LoginBox"><!--上榜规则框开始-->
                <div class="row" style="text-align: right;padding-right: 1rem;margin-top: 0">
                    <img src="/plugins/rotate/img/close.png" style="height: 25px;" onclick="closeAlert();">
                </div>
                <div class="row">
                    <span style="font-size: 0.8rem;line-height: 1.3rem">1.每日登录全民红包,均可获取3次免费抽奖机会,仅限当天使用,不可累加;</span>
                </div>

                <div class="row">
                    <span style="font-size: 0.8rem;line-height: 1.3rem">2.参加某些全部红包发起的活动时可以获取若干次抽奖机会,具体以活动为主;</span>
                </div>

                <div class="row">
                    <span style="font-size: 0.8rem;line-height: 1.3rem">3.本活动最终解释权归全民红包所有;</span>
                </div>
            </div><!--上榜规则框结束-->


            <%--<div style="height: 30px"></div>--%>

            <div class="awards_cover">
                <div class="awards_out">
                    <div class="awards_in">
                        <img src="/plugins/rotate/img/tip_bg.png" width="100%">
                        <div class="awards_img">
                            <img src="" width="100%">
                        </div>
                        <p class="awards_name"></p>
                        <div class="awards_btn">
                            <a href="javascript:window.location.reload();">再来一次</a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="fail_cover">
                <div class="fail_out">
                    <div class="fail_in">
                        <img src="/plugins/rotate/img/tip_bg2.png" width="100%">
                        <div class="fail_btn">
                            <a href="javascript:window.location.reload();">再来一次</a>
                        </div>
                    </div>
                </div>
            </div>
            <!--审核的时候隐藏 -->
            <%--<div style="text-align: right;padding-right: 1.5rem">--%>
                <%--<img src="/plugins/rotate/img/desc.png" style="width: 70px;height: auto" onclick="showAlert();">--%>
            <%--</div>--%>

            <%--<div style="padding: 1.5rem">--%>
                <%--<img src="/plugins/rotate/img/rankingHeader.png" style="width:100%;display: block">--%>
                <%--<div class="downbok" style="background-color:#F36C00;overflow: auto;padding:0 1rem;color:#FFF8F1;font-size: 1rem;width: 100% ">--%>
                    <%--<ul id="ul" style="width: 100%;overflow: auto;display: block">--%>
                        <%--<c:forEach items="${awards}" var="award">--%>
                            <%--<li style="width: 100%;overflow: auto;display: block">--%>
                                <%--<div style="float:left;width: 40%">--%>
                                    <%--<span >${award.nickname}</span>--%>
                                <%--</div>--%>
                                <%--<div style="float:left;width: 30%">--%>
                                    <%--<span >${award.time}</span>--%>
                                <%--</div>--%>
                                <%--<div style="float:left;width: 30%;text-align: left">--%>
                                    <%--<span>${award.award}</span>--%>
                                <%--</div>--%>
                            <%--</li>--%>
                        <%--</c:forEach>--%>
                    <%--</ul>--%>
                <%--</div>--%>
                <%--<img src="/plugins/rotate/img/rankingTail.png"  style="width:100%;display: block">--%>
            <%--</div>--%>
            <!--审核的时候隐藏 -->


            <!--审核的时候用 -->
            <div style="padding: 1.5rem">
                <div class="row" style="text-align: center">
                    <span style="font-size: 1rem;line-height: 1.3rem">玩法说明</span>
                </div>
                <div class="row">
                    <span style="font-size: 0.8rem;line-height: 1.3rem">1.每日登录全民红包,均可获取3次免费抽奖机会,仅限当天使用,不可累加;</span>
                </div>
                <div class="row">
                    <span style="font-size: 0.8rem;line-height: 1.3rem">2.当天免费抽奖机会用完后,可以花费金币继续,每次消耗5金币;</span>
                </div>
                <div class="row">
                    <span style="font-size: 0.8rem;line-height: 1.3rem">3.参加某些全部红包发起的活动时可以获取若干次抽奖机会,具体以活动为主;</span>
                </div>

                <div class="row">
                    <span style="font-size: 0.8rem;line-height: 1.3rem">3.本活动最终解释权归全民红包所有;</span>
                </div>
            </div>
            <!--审核的时候用 -->

            <div style="text-align: center;padding:0 0 1.5rem 0">
                <span style="color:#E84A25;font-size: 1rem ">本活动与苹果公司无关</span>
            </div>



            <div class="template-code">

                <div id="global-toast" style="display: none;">
                    <div class="weui_mask_transparent"></div>
                    <div class="weui_toast">
                        <i class="weui_icon_toast"></i>
                        <p class="weui_toast_content">&nbsp;</p>
                    </div>
                </div>

                <div id="global-toast-loading" class="weui_loading_toast" style="display: none;">
                    <div class="weui_mask_transparent"></div>
                    <div class="weui_toast">
                        <div class="weui_loading">
                            <div class="weui_loading_leaf weui_loading_leaf_0"></div>
                            <div class="weui_loading_leaf weui_loading_leaf_1"></div>
                            <div class="weui_loading_leaf weui_loading_leaf_2"></div>
                            <div class="weui_loading_leaf weui_loading_leaf_3"></div>
                            <div class="weui_loading_leaf weui_loading_leaf_4"></div>
                            <div class="weui_loading_leaf weui_loading_leaf_5"></div>
                            <div class="weui_loading_leaf weui_loading_leaf_6"></div>
                            <div class="weui_loading_leaf weui_loading_leaf_7"></div>
                            <div class="weui_loading_leaf weui_loading_leaf_8"></div>
                            <div class="weui_loading_leaf weui_loading_leaf_9"></div>
                            <div class="weui_loading_leaf weui_loading_leaf_10"></div>
                            <div class="weui_loading_leaf weui_loading_leaf_11"></div>
                        </div>
                        <p class="weui_toast_content">&nbsp;</p>
                    </div>
                </div>

                <div id="global-dialog-confirm" class="weui_dialog_confirm" style="display: none;">
                    <div class="weui_mask"></div>
                    <div class="weui_dialog">
                        <div class="weui_dialog_hd"><strong class="weui_dialog_title">&nbsp;</strong></div>
                        <div class="weui_dialog_bd">&nbsp;</div>
                        <div class="weui_dialog_ft">
                            <a href="javascript:;" class="weui_btn_dialog default btn_dialog_cancel">取消</a>
                            <a href="javascript:;" class="weui_btn_dialog primary btn_dialog_ok">确定</a>
                        </div>
                    </div>
                </div>

                <div id="global-dialog-alert" class="weui_dialog_alert" style="display: none;">
                    <div class="weui_mask"></div>
                    <div class="weui_dialog">
                        <div class="weui_dialog_hd"><strong class="weui_dialog_title">&nbsp;</strong></div>
                        <div class="weui_dialog_bd">&nbsp;</div>
                        <div class="weui_dialog_ft">
                            <a href="javascript:;" class="weui_btn_dialog primary">确定</a>
                        </div>
                    </div>
                </div>

            </div>

        </div>

        <script>



        </script>

    </body>
</html>
