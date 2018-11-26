package com.guomai.dushuhui.model;

/**
 * Created by Administrator on 2018/9/21.
 */

public class YijiListData {

    /**
     * 电子书ID
     * */
    private String id;

    /**
     * 书号
     * */
    private String book_no;

    /**
     * 书名
     * */
    private String name;

    /**
     *  作者
     * */
    private String author;

    /**
     *  译者
     * */
    private String translator;

    /**
     *  简介
     * */
    private String introduction;

    /**
     *  封面图片地址
     * */
    private String cover_url;

    /**
     *  现价，单位：书币
     * */
    private int price;

    /**
     *  原价，单位：书币
     * */
    private int original_price;

    /**
     *  折扣,为0时表示免费
     * */
    private double discount;

    /**
     *  折扣或免费开始时间
     * */
    private String discount_start_time;

    /**
     *  折扣或免费结束时间
     * */
    private String discount_end_time;

    /**
     *  当前用户是否购买过 y-是 n-否
     * */
    private String is_buy;

    /**
     *  一级分类名称
     * */
    private String category_name;

    /**
     *  在读人数
     * */
    private int read_num;

    /**
     *  更新时间
     * */
    private String updated_at;

    /**
     *  创建时间
     * */
    private String created_at;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setBook_no(String book_no){
        this.book_no = book_no;
    }
    public String getBook_no(){
        return this.book_no;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setAuthor(String author){
        this.author = author;
    }
    public String getAuthor(){
        return this.author;
    }
    public void setTranslator(String translator){
        this.translator = translator;
    }
    public String getTranslator(){
        return this.translator;
    }
    public void setIntroduction(String introduction){
        this.introduction = introduction;
    }
    public String getIntroduction(){
        return this.introduction;
    }
    public void setCover_url(String cover_url){
        this.cover_url = cover_url;
    }
    public String getCover_url(){
        return this.cover_url;
    }
    public void setPrice(int price){
        this.price = price;
    }
    public int getPrice(){
        return this.price;
    }
    public void setOriginal_price(int original_price){
        this.original_price = original_price;
    }
    public int getOriginal_price(){
        return this.original_price;
    }
    public void setDiscount(double discount){
        this.discount = discount;
    }
    public double getDiscount(){
        return this.discount;
    }
    public void setDiscount_start_time(String discount_start_time){
        this.discount_start_time = discount_start_time;
    }
    public String getDiscount_start_time(){
        return this.discount_start_time;
    }
    public void setDiscount_end_time(String discount_end_time){
        this.discount_end_time = discount_end_time;
    }
    public String getDiscount_end_time(){
        return this.discount_end_time;
    }
    public void setIs_buy(String is_buy){
        this.is_buy = is_buy;
    }
    public String getIs_buy(){
        return this.is_buy;
    }
    public void setCategory_name(String category_name){
        this.category_name = category_name;
    }
    public String getCategory_name(){
        return this.category_name;
    }
    public void setRead_num(int read_num){
        this.read_num = read_num;
    }
    public int getRead_num(){
        return this.read_num;
    }
    public void setUpdated_at(String updated_at){
        this.updated_at = updated_at;
    }
    public String getUpdated_at(){
        return this.updated_at;
    }
    public void setCreated_at(String created_at){
        this.created_at = created_at;
    }
    public String getCreated_at(){
        return this.created_at;
    }
}
