package com.lmc.bean;

import java.io.Serializable;

public class JsonData implements Serializable {

    public static final long serialVersionUID = 1L;

    private Integer code;   //状态码0：成功，1：失败

    private Object data;    //数据

    private String msg;     //描述

    public JsonData() {
    }

    public JsonData(Integer code, Object data, String msg) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public static JsonData buildSuccess(){
        return new JsonData(0,null,null);
    }

    public static JsonData buildSuccess(Object data){
        return new JsonData(0,data,null);
    }

    public static JsonData buildSuccess(String msg){
        return new JsonData(0,null,msg);
    }

    public static JsonData buildSuccess(Object data, String msg){
        return new JsonData(0,data,msg);
    }

    public static JsonData buildError(){
        return new JsonData(1,null,null);
    }

    public static JsonData buildError(Object data){
        return new JsonData(1,data,null);
    }

    public static JsonData buildError(String msg){
        return new JsonData(1,null,msg);
    }

    public static JsonData buildError(Object data, String msg){
        return new JsonData(1,data,msg);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "JsonData{" +
                "code=" + code +
                ", data=" + data +
                ", msg='" + msg + '\'' +
                '}';
    }
}
