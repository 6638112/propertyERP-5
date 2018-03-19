package com.cnfantasia.server.ms.advertise.entity;

import com.cnfantasia.server.domainbase.ebuyAdvertise.entity.EbuyAdvertise;
import com.cnfantasia.server.ms.advertise.constants.AdvConstant;

/**
 * 广告列表
 * 
 * @author liyulin
 * @version 1.0 2016年8月10日 下午7:11:02
 */
public class EbuyAdvertiseDto extends EbuyAdvertise {
	/** 添加人 */
	private String addMan;
	/** 修改人 */
	private String updateMan;

	private Integer status;
	
	private String advType;

	public EbuyAdvertiseDto() {
		super();
	}

	public EbuyAdvertiseDto(String addMan, String updateMan) {
		super();
		this.addMan = addMan;
		this.updateMan = updateMan;
	}

	public String getAddMan() {
		return addMan;
	}

	public void setAddMan(String addMan) {
		this.addMan = addMan;
	}

	public String getUpdateMan() {
		return updateMan;
	}

	public void setUpdateMan(String updateMan) {
		this.updateMan = updateMan;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAdvType() {
		return advType;
	}

	public void setAdvType(String advType) {
		this.advType = advType;
	}

	@Override
	public String getTittle() {
		return super.getTittle();
	}
}
