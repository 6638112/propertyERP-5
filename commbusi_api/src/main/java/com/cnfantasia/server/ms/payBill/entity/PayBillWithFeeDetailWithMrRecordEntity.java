package com.cnfantasia.server.ms.payBill.entity;

import java.util.ArrayList;
import java.util.List;

import com.cnfantasia.server.domainbase.mrPayBillRecord.entity.MrPayBillRecord;


/**
 * 导入的物业缴费单POJO对象, 包含抄表记录
 * 
 * @author wenfq
 * 
 */
public class PayBillWithFeeDetailWithMrRecordEntity extends PayBillWithFeeDetailEntity {
	/**
	 * 抄表明细
	 */
	private List<MrPayBillRecord> MrPayBillRecordList = new ArrayList<MrPayBillRecord>();

	public List<MrPayBillRecord> getMrPayBillRecordList() {
		return MrPayBillRecordList;
	}

	public void setMrPayBillRecordList(List<MrPayBillRecord> mrPayBillRecordList) {
		MrPayBillRecordList = mrPayBillRecordList;
	}
	
}
