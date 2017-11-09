<div id="inviteDiv" class="inviteTpl" style="padding-top: 2rem;background-color: #FFFFFF;width: 100%;display: none ">
    <div style="width: 100%;overflow: auto">
        <div class="inviteLeft" style="float: left;width: 40%;padding-top: 6px;padding-left: 1.2rem;padding-right: 1.2rem"><hr style="border-top: none;border-bottom: 1px solid #999999;"></div>
        <div class="inviteMid"  style="float: left;width: 20%;text-align: center"><span style="font-size: 1rem;color: #999999">分享到</span></div>
        <div class="inviteRight"  style="float: left;width: 40%;padding-top: 6px;padding-left: 1.2rem;padding-right: 1.2rem"><hr style="border-top: none;border-bottom: 1px solid #999999;"></div>
    </div>
    <div style="width: 100%;overflow: auto;border-bottom: 1px solid #999999;padding-bottom: 1.5rem;padding-top: 2rem">
        <div class="friendsCircle" style="width: 50%;float: left;text-align: center">
            <a href="javaScript:;" onclick="userSign.shareWX_ToFriendCircle();" style="text-decoration: none;-webkit-tap-highlight-color: rgba(0,0,0,0)">
                <img src="/client/img/commission/friendCircle.png" style="width: 60px;margin-bottom: 0.5rem">
                <span style="display: block;margin-top: 0.5rem;font-size: 1.2rem;color:#313131 ">朋友圈</span>
            </a>
        </div>
        <div class="sendToFriend" style="width: 50%;float: left;text-align: center">
            <a href="javaScript:;" onclick="userSign.shareWX_ToFriend();" style="text-decoration: none;-webkit-tap-highlight-color: rgba(0,0,0,0)">
                <img src="/client/img/commission/sendFriend.png" style="width: 60px;margin-bottom: 0.5rem">
                <span style="display: block;margin-top: 0.5rem;font-size: 1.2rem;color:#313131">微信好友</span>
            </a>
        </div>
    </div>
    <div class="cancelDiv" style="padding: 1.2rem 2rem 1.5rem 2rem;text-align: center;height: 60px;" onclick="cancelInvite();">
        <a href="javaScript:;" onclick="cancelInvite();" style="text-decoration: none"><span style="font-size: 1.2rem;color: #999999">取消</span></a>
    </div>
</div>

