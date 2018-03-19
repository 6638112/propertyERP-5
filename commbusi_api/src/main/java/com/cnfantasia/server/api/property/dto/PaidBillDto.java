package com.cnfantasia.server.api.property.dto;

import java.util.List;

/**
 * 已缴账单列表信息
 * 
 * @author liyulin
 * @version 1.0 2017年3月10日 上午11:06:05
 */
public class PaidBillDto {

	/** 已缴账单列表每项信息 */
	private List<PaidBillItemDto> list;
	/** 是否是最后一页 */
	private boolean isLast;

	public List<PaidBillItemDto> getList() {
		return list;
	}

	public void setList(List<PaidBillItemDto> list) {
		this.list = list;
	}

	public boolean isLast() {
		return isLast;
	}

	public void setLast(boolean isLast) {
		this.isLast = isLast;
	}

}
