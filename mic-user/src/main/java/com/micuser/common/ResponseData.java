package com.micuser.common;

public class ResponseData {
    private int status = 0;
    private boolean play = false;
    private String message = "";
    private Object data = null;

    public ResponseData(){
        status = 0;
        play = false;
        message = "";
        data = null;
    }

    public static ResponseData getResponseJson(){
        return new ResponseData();
    }

    public ResponseData(int status, String message){
        this.message = message;
        this.status = status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    public int getStatus() {
        return status;
    }

    public boolean isPlay() {
        return play;
    }

    public void setPlay(boolean play) {
        this.play = play;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

    public void setData(Object data) {
        this.data = data;
    }
    public Object getData() {
        return data;
    }
}
