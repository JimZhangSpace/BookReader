package com.guomai.dushuhui.model;

/**
 * Created by Administrator on 2018/9/20.
 */

import java.util.List;
public class Data {

    /**
     *  分类ID
     * */
    public String id;

    /**
     *  自定义分类名称
     * */
    public  String name;

    /**
     *  顺序号 数字越小越靠前 1-1000
     * */
    public  int sequence;

    public  List<Ebooks> ebooks ;

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
    public void setSequence(int sequence){
        this.sequence = sequence;
    }
    public int getSequence(){
        return this.sequence;
    }
    public void setEbooks(List<Ebooks> ebooks){
        this.ebooks = ebooks;
    }
    public List<Ebooks> getEbooks(){
        return this.ebooks;
    }

}