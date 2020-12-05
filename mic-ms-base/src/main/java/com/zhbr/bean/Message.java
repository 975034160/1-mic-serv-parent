package com.zhbr.bean;

import java.util.Date;

public class Message {
	
	private int id;
	private String title;
	private String body;
	private String date;
	
	public Message() {
		
	}
	
	public Message(int id,String title,String body,String date) {
		this.id=id;
		this.title=title;
		this.body=body;
		this.date=date;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "["+ id+", "+ this.title+", "+ this.date +"]";
	}

}
