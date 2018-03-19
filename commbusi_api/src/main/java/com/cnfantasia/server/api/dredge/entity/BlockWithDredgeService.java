package com.cnfantasia.server.api.dredge.entity;


/**
 * 行政区的疏通开通情况
 * 
 * @author wenfq
 *
 */
public class BlockWithDredgeService {
	long id; //区域id
	String name; //区域名称
	long cityId; //城市id
	int isOpenDredgeService = 0; //是否已开通

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCityId() {
		return cityId;
	}

	public void setCityId(long cityId) {
		this.cityId = cityId;
	}

	public int getIsOpenDredgeService() {
		return isOpenDredgeService;
	}
	public void setIsOpenDredgeService(int isOpenDredgeService) {
		this.isOpenDredgeService = isOpenDredgeService;
	}
}
