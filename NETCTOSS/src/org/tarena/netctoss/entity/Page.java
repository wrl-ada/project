package org.tarena.netctoss.entity;

import java.io.Serializable;

public class Page implements Serializable{
	private Integer page = 1;
	private Integer pagesize = 5;
	private Integer totalPage = 1;
	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public Integer getbegin(){
		return (page-1)*pagesize+1;
	}
	
	public Integer getend(){
		return page*pagesize;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getPagesize() {
		return pagesize;
	}
	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}
	
}
