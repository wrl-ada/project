package org.tarena.note.entity;

import java.sql.Date;
import java.util.Calendar;

public class SearchNote {
	private String title;//����
	private String status;//״̬
	private String beginDate;//input_html
	private String endDate;//input_html
	
	//��SQL��#{beginTime}��ȡ
	public Long getBeginTime() {
		if(beginDate != null && !"".equals(beginDate)){
			return Date.valueOf(beginDate).getTime();
		}else{
			return null;
		}
	}
//	��SQL��#{endTime}��ȡ
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
