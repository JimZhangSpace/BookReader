package com.guomai.dushuhui.model;

/**
 * Created by Administrator on 2018/9/20.
 */

public class Banners{
    /**
     *  banner ID
     * */
    private String id;

    /**
     *  图片地址
     * */
    private String img_url;

    /**
     *  链接类型 ebook-电子书 abook-听书 web-网页 ["ebook", "abook", "web"]
     * */
    private String link_type;

    /**
     *  跳转地址,可能是电子书、听书id，或者网页链接，也可能为空 ["@url('http')", 5, null]
     * */
    private String link;

    public void setId(String id){
        this.id = id;
    }
    public String getId(){
        return this.id;
    }
    public void setImg_url(String img_url){
        this.img_url = img_url;
    }
    public String getImg_url(){
        return this.img_url;
    }
    public void setLink_type(String link_type){
        this.link_type = link_type;
    }
    public String getLink_type(){
        return this.link_type;
    }
    public void setLink(String link){
        this.link = link;
    }
    public String getLink(){
        return this.link;
    }

}
