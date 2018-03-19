package com.cnfantasia.server.ms.pub.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PageData {
	private List<Map<String,Object>> list;
	private boolean hasNext;
	public PageData(){
		list = new ArrayList<Map<String,Object>>();
		hasNext = false;
	}
	
	public void add(Map<String,Object> data){
		list.add(data);
	}
	public void del(Map<String,Object> data){
		list.remove(data);
	}
	
	public List<Map<String, Object>> getList() {
		return list;
	}
	/*public void setList(List<Map<String, Object>> list) {
		this.list = list;
	}*/
	public boolean isHasNext() {
		return hasNext;
	}
	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}
	
}
