package com.cnfantasia.server.api.dredge.entity;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * 疏通类型，解放区app端使用
 * @author wenfq
 *
 */
public class DredgeTypeForUser {
	private BigInteger id;
	private String picUrl;
	private String picUrlGrey; //灰度图
	private String parentName; //父级分类名称
	private String name;
	private int isOpenService;
	private String priceDesc;
	private String desc;
	private String serviceProcessUrl; //服务流程说明URL
	private int isHasNum;
	
	//二级子类型
	private List<DredgeType2ndForUser> subTypeList = new ArrayList<DredgeType2ndForUser>();
	
	public String getServiceProcessUrl() {
		return serviceProcessUrl;
	}
	public void setServiceProcessUrl(String serviceProcessUrl) {
		this.serviceProcessUrl = serviceProcessUrl;
	}
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getPicUrlGrey() {
		return picUrlGrey;
	}
	public void setPicUrlGrey(String picUrlGrey) {
		this.picUrlGrey = picUrlGrey;
	}
	public String getParentName() {
		return parentName;
	}
	public void setParentName(String parentName) {
		this.parentName = parentName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIsOpenService() {
		return isOpenService;
	}
	public void setIsOpenService(int isOpenService) {
		this.isOpenService = isOpenService;
	}
	public String getPriceDesc() {
		return priceDesc;
	}
	public void setPriceDesc(String priceDesc) {
		this.priceDesc = priceDesc;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public List<DredgeType2ndForUser> getSubTypeList() {
		return subTypeList;
	}
	public void setSubTypeList(List<DredgeType2ndForUser> subTypeList) {
		this.subTypeList = subTypeList;
	}
	public int getIsHasNum() {
		return isHasNum;
	}
	public void setIsHasNum(int isHasNum) {
		this.isHasNum = isHasNum;
	}
}
