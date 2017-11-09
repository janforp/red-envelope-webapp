package com.iask.red_envelope.model.vo;

/**
 * Created by Jan on 16/10/10.
 * 虚拟商品
 */
public class ItemVo {

    // 商品id
    private Long itemId;
    // 标题
    private String itemTitle;
    // 图片
    private String itemImg;
    // 金币
    private Integer itemCoin;
    private String itemDesc;
    private Integer type;
    //是否能兑换
    private boolean flag;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemImg() {
        return itemImg;
    }

    public void setItemImg(String itemImg) {
        this.itemImg = itemImg;
    }

    public Integer getItemCoin() {
        return itemCoin;
    }

    public void setItemCoin(Integer itemCoin) {
        this.itemCoin = itemCoin;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
