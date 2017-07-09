package org.tarena.note.entity;

import java.sql.Date;
import java.util.Calendar;

public class SearchNote {
	private String title;//标题
	private String status;//状态
	private String beginDate;//input_html
	private String endDate;//input_html
	
	//在SQL中#{beginTime}获取
	public Long getBeginTime() {
		if(beginDate != null && !"".equals(beginDate)){
			return Date.valueOf(beginDate).getTime();
		}else{
			return null;
		}
	}
//	在SQL中#{endTime}获取
	public Long getEndTime() {
		if(endDate != null && !"".equals(endDate)){
			Date date = Date.valueOf(endDate);
			Calendar c1 = Calendar.getInstance();
			c1.setTime(date);
			c1.add(Calendar.DAY_OF_MONTH, 1);
			return c1.getTimeInMillis();
		}else{
			return null;
		}
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
}
