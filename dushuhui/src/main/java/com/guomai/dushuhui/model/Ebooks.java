package com.guomai.dushuhui.model;

/**
 * Created by Administrator on 2018/9/20.
 */

public class Ebooks {

    /**
     *  电子书ID
     * */
    private String id;

    /**
     *  书名
     * */
    private String name;

    /**
     *  封面图片地址
     * */
    private String cover_url;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setCover_url(String cover_url){
        this.cover_url = cover_url;
    }
    public String getCover_url(){
        return this.cover_url;
    }

}