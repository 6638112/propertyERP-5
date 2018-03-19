package com.cnfantasia.server.api.revenueApplyPush.bean;

import java.util.ArrayList;
import java.util.List;

//{"detailList":[
//{"easCode":"F-20160704-2384","srcCode":"源单编码0"},
//{"easCode":"F-20160704-2385","srcCode":"源单编码0"},
//{"easCode":"FK-20160704-003","srcCode":"20160527"}],"code":"0000","message":"调用成功"}
public class ResultInfo {
	String code;
	String message;
	List<ResultDetail> detailList = new ArrayList<ResultDetail>();
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<ResultDetail> getDetailList() {
		return detailList;
	}
	public void setDetailList(List<ResultDetail> detailList) {
		this.detailList = detailList;
	}
	public ResultInfo(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}	 
	
	public ResultInfo() {
		this.code = "0000";
		this.message = "操作成功";
	}
}
