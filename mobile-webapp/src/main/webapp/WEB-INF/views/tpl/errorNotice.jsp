<style>
    .hideErrorFrame{
        display: none;
    }
    .mask{
        margin:0;
        padding:0;
        border:none;
        width:100%;
        height:100%;
        background-color: rgba(0,0,0,0.3);
        position:fixed;
        top:0;
        left:0;

        display: flex;
        align-items: center;
        justify-content: center;
    }

    #error{
        z-index:9999;
        margin: 0 5%;
        height: 150px;
        background-color: #FFFFFF;

        width: 90%;
        color: #080808;
        text-align: center;
        padding: 1rem;
        border-radius: 5px;

        display: flex;
        flex-direction: column;
        justify-content: space-around;
        align-items: center;
    }

    .hideErrorFrame .notice{
        font-size: 1rem;
    }
</style>
<script>
    /*1个按钮*/
    function showErrorNotice(content) {

        var $noticeSpan = $(".hideErrorFrame .notice");
        $noticeSpan.text(content);

        $(".hideErrorFrame").show();
        $("body").css("overflow","hidden");
    }

    /**
     * 关闭提示
     */
    function closeErrorNotice() {
        $(".hideErrorFrame").hide();
        $("body").css("overflow","");
    }

</script>

<!--领取现金失败弹框 -->
<div class="hideErrorFrame">
    <div class="mask">
        <div id='error'>
            <span class="notice"></span>
        </div>
    </div>
</div>

