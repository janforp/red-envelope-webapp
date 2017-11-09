/**
 * 点击'立即邀请好友'
 */
function alertInvite() {
    $("#inviteDiv").show();
    $(".fixFootButtonDiv").show();
    $("#cover").show();
}

/**
 * 点击取消
 */
function cancelInvite() {
    $("#inviteDiv").hide();
    $(".fixFootButtonDiv").hide();
    $("#cover").hide();
}