package com.guomai.dushuhui.model;

import java.util.List;

/**
 * Created by Administrator on 2018/9/20.
 */

public class CustomClassification {
    private int code;

    private String msg;

    private List<Data> data ;

    public void setCode(int code){
        this.code = code;
    }
    public int getCode(){
        return this.code;
    }
    public void setMsg(String msg){
        this.msg = msg;
    }
    public String getMsg(){
        return this.msg;
    }
    public void setData(List<Data> data){
        this.data = data;
    }
    public List<Data> getData(){
        return this.data;
    }

}
