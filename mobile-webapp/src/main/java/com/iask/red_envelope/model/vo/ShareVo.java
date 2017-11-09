package com.iask.red_envelope.model.vo;

import java.io.Serializable;

/**
 * Created by Jan on 16/10/26.
 * 分享任务
 */
public class ShareVo implements Serializable{

    // 商家名字
    private String merchantName;
    // 分享链接(按客户要求)
    private String shareUrl;
    // 分享标题(按客户要求)
    private String shareTitle;
    // 分享图片(按客户要求)
    private String shareImg;
    // 分享描述(按客户要求)
    private String shareDesc;
    // 分享类型:music、video或link，不填默认为link
    private String shareType;
    // 分享数据链接,如果type是music或video，则要提供数据链接，默认为空
    private String shareDataurl;

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getShareTitle() {
        return shareTitle;
    }

    public void setShareTitle(String shareTitle) {
        this.shareTitle = shareTitle;
    }

    public String getShareImg() {
        return shareImg;
    }

    public void setShareImg(String shareImg) {
        this.shareImg = shareImg;
    }

    public String getShareDesc() {
        return shareDesc;
    }

    public void setShareDesc(String shareDesc) {
        this.shareDesc = shareDesc;
    }

    public String getShareType() {
        return shareType;
    }

    public void setShareType(String shareType) {
        this.shareType = shareType;
    }

    public String getShareDataurl() {
        return shareDataurl;
    }

    public void setShareDataurl(String shareDataurl) {
        this.shareDataurl = shareDataurl;
    }
}
