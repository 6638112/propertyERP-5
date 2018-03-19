package com.cnfantasia.server.ms.revenue.entity;

import java.text.DateFormat;

import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.common.utils.StringUtils;

public class AreaTime {
	private String start;
	private String end;
	
	public AreaTime(String start,String end){
		this.start = start;
		this.end = end;
	}
	
//	public String show(){
//		if(StringUtils.isEmpty(start)){
//			if(StringUtils.isEmpty(end)){
//				return "无限制";
//			}else{
//				return DateUtil.formatDay.get().f""+"及之前";
//			}
//		}
//	}
	
	public boolean isInArea(String time,DateFormat dateFormat){
		if(StringUtils.isEmpty(time)){return false;}
		if(!StringUtils.isEmpty(start)){
			long distance = DateUtil.distance(time, dateFormat, start, DateUtil.formatDay.get());
			if(distance<0){
				return false;
			}
		}
		if(!StringUtils.isEmpty(end)){
			long distance = DateUtil.distance(time, dateFormat, end, DateUtil.formatDay.get());
			if(distance>0){
				return false;
			}
		}
		return true;
	}
	
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	
}
