package com.cnfantasia.server.api.ebuyorder.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class OwnerInfo {
	String ownerName;
	
	String ownerPhone;
	/**
	 * 店主身份证照片
	 */
	String owenerIDPhotoes;
	
	List<String> owenerIDPhotoList = new ArrayList<String>();
	
	public List<String> getOwenerIDPhotoList() {
		return owenerIDPhotoList;
	}

	public void setOwenerIDPhotoList(List<String> owenerIDPhotoList) {
		this.owenerIDPhotoList = owenerIDPhotoList;
	}

	public String getOwenerIDPhotoes() {
		return owenerIDPhotoes;
	}

	public void setOwenerIDPhotoes(String owenerIDPhotoes) {
		this.owenerIDPhotoes = owenerIDPhotoes;
		
		if(owenerIDPhotoList==null){
			owenerIDPhotoList = new ArrayList<String>();
		}
		
		if(StringUtils.isNotEmpty(owenerIDPhotoes)){
			for(String s: owenerIDPhotoes.split(";")){
				owenerIDPhotoList.add(s);
			}
		}
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getOwnerPhone() {
		return ownerPhone;
	}

	public void setOwnerPhone(String ownerPhone) {
		this.ownerPhone = ownerPhone;
	}
}