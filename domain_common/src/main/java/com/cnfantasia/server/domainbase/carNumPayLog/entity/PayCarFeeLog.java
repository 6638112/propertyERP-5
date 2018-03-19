package com.cnfantasia.server.domainbase.carNumPayLog.entity;

/**
 * 月卡续费
 *
 * @author Liyl
 * @version 1.0 2016年3月21日 下午1:39:44
 */
public class PayCarFeeLog extends CarNumPayLog {
	private static final long serialVersionUID = 1L;
	/** 车牌号 */
	private String tCarNum;
	
	public PayCarFeeLog() {
		super();
	}

	public PayCarFeeLog(String tCarNum) {
		super();
		this.tCarNum = tCarNum;
	}

	public String gettCarNum() {
		return tCarNum;
	}

	public void settCarNum(String tCarNum) {
		this.tCarNum = tCarNum;
	}
	
}
