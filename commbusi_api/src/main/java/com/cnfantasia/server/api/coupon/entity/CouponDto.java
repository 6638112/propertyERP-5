package com.cnfantasia.server.api.coupon.entity;

import com.cnfantasia.server.domainbase.coupon.entity.Coupon;

/**
 * 消费券列表
 * 
 * @author liyulin
 * @version 1.0 2016年8月10日 下午7:25:45
 */
public class CouponDto extends Coupon {
	/** 添加人 */
	private String addMan;
	/** 修改人 */
	private String updateMan;

	public CouponDto() {
		super();
	}

	public CouponDto(String addMan, String updateMan) {
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

}
