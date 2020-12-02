package com.micuser.common;

public class ResponseData {
    private int code = 0;
    private String desc = "";
    private String msg = "";
    private Object data = null;

    public ResponseData(){
        code = 0;
        desc = "";
        msg = "";
        data = null;
    }

    public static ResponseData getResponseJson(){
        return new ResponseData();
    }

    public ResponseData(int code, String msg){
        this.msg = msg;
        this.code = code;
    }

    public void setCode(int code) {
        this.code = code;
    }
    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public String getMsg() {
        return msg;
    }

    public void setData(Object data) {
        this.data = data;
    }
    public Object getData() {
        return data;
    }
}
