package org.tarena.netctoss.entity;

import java.util.List;
/**
 * 根据Privilege.xml定义的实体类
 * @author Admin
 *
 */
public class Privilege {

	private String id;
	
	private String name;
	
	private List<String> urls;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
  	return name;
  }
	public void setName(String name) {
  	this.name = name;
  }
	public List<String> getUrls() {
		return urls;
	}
	public void setUrls(List<String> urls) {
		this.urls = urls;
	}

}
