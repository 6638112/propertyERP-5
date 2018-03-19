package com.cnfantasia.server.ms.bankCollection.entity;

import com.cnfantasia.server.api.bankCollection.entity.BcPrintDetail;

public class BcPrintDetailDto {
	private BcPrintDetail bcPrintDetail;
	/** 分页开始 */
	private boolean pageStart;
	/** 分页结束 */
	private boolean pageEnd;
	/** 页码 */
	private int pageNum;
	/** 总页码 */
	private int totalPageNum;

	public BcPrintDetailDto(BcPrintDetail bcPrintDetail, boolean pageStart, boolean pageEnd, int pageNum) {
		super();
		this.bcPrintDetail = bcPrintDetail;
		this.pageStart = pageStart;
		this.pageEnd = pageEnd;
		this.pageNum = pageNum;
	}

	public BcPrintDetail getBcPrintDetail() {
		return bcPrintDetail;
	}

	public void setBcPrintDetail(BcPrintDetail bcPrintDetail) {
		this.bcPrintDetail = bcPrintDetail;
	}

	public boolean isPageStart() {
		return pageStart;
	}

	public void setPageStart(boolean pageStart) {
		this.pageStart = pageStart;
	}

	public boolean isPageEnd() {
		return pageEnd;
	}

	public void setPageEnd(boolean pageEnd) {
		this.pageEnd = pageEnd;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getTotalPageNum() {
		return totalPageNum;
	}

	public void setTotalPageNum(int totalPageNum) {
		this.totalPageNum = totalPageNum;
	}

}
