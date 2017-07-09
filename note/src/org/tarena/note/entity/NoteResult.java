package org.tarena.note.entity;

import java.io.Serializable;

public class NoteResult implements Serializable{
	private int status;//状态值
	private Object data;//数据
	private String msg;//消息
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}
