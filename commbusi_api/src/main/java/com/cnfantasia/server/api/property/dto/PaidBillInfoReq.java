package com.cnfantasia.server.api.property.dto;

import java.math.BigInteger;

/**
 * 已缴账单信息请求参数
 * 
 * @author liyulin
 * @version 1.0 2017年3月10日 上午10:58:47
 */
public class PaidBillInfoReq {

	private BigInteger userId;
	/** 页码 */
	private Integer page;
	/** 每页显示条数 */
	private Integer pageNum;

	public BigInteger getUserId() {
		return userId;
	}

	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

}
