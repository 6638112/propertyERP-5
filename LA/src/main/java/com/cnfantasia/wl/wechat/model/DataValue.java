package com.cnfantasia.wl.wechat.model;

import java.util.List;

import com.alibaba.fastjson.JSONObject;

public class DataValue {
	List<JSONObject> list;
	boolean hasNext;
	int count;

	public List<JSONObject> getList() {
		return list;
	}

	public void setList(List<JSONObject> list) {
		this.list = list;
	}

	public boolean isHasNext() {
		return hasNext;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
