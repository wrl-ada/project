package org.tarena.note.entity;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable{
	private String  cn_user_id;//主键ID
	private String  cn_user_name;//用户名
	private String  cn_user_password;//密码
	private String  cn_user_token;//身份令牌
	private String cn_user_desc;//昵称
	
	//追加关联属性，关联到NoteBook
	private List<NoteBook> books;
	
	public List<NoteBook> getBooks() {
		return books;
	}
	public void setBooks(List<NoteBook> books) {
		this.books = books;
	}
	public String getCn_user_desc() {
		return cn_user_desc;
	}
	public void setCn_user_desc(String cn_user_desc) {
		this.cn_user_desc = cn_user_desc;
	}
	public String getCn_user_id() {
		return cn_user_id;
	}
	public void setCn_user_id(String cn_user_id) {
		this.cn_user_id = cn_user_id;
	}
	public String getCn_user_name() {
		return cn_user_name;
	}
	public void setCn_user_name(String cn_user_name) {
		this.cn_user_name = cn_user_name;
	}
	public String getCn_user_password() {
		return cn_user_password;
	}
	public void setCn_user_password(String cn_user_password) {
		this.cn_user_password = cn_user_password;
	}
	public String getCn_user_token() {
		return cn_user_token;
	}
	public void setCn_user_token(String cn_user_token) {
		this.cn_user_token = cn_user_token;
	}
	
}
