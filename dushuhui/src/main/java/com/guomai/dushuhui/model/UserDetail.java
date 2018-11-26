package com.guomai.dushuhui.model;

public class UserDetail {

    /**
     * 主键, 用户ID号
     */
    public String uid;     //id
    /**
     * 主键, 昵称
     */
    public String nickname;
    /**
     * 性别:{0-未设置;1-男性;2-女性;3-中性}
     */
    public int sex;
    /**
     * 头象url地址
     */
    public String avatar;
    /**
     * 微信unionid,如果为null表示还未绑定微信
     */
    public String unionid;

    /**
     * 签名
     */
    public String signature;
    /**
     * 粉丝个数
     */
    public int fansCount;
    /**
     * 关注个数
     */
    public int followCount;
    /**
     * 国家编号
     */
    public String countryCode;
    /**
     * 国家
     */
    public String country;
    /**
     * 洲/省编号
     */
    public String stateCode;
    /**
     * 洲/省
     */
    public String state;
    /**
     * 城市编号
     */
    public String cityCode;
    /**
     * 城市
     */
    public String city;
    /**
     * 公司ID号(此处为0，则表没有没有公司)
     */
    public int companyID;

    /**
     * 公司名称
     */
    public String companyTitle;
    /**
     * 职务
     */
    public String positionTitle;
    /**
     * 姓名
     */
    public String nameTitle;


}
