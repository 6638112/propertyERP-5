package com.cnfantasia.server.ms.payBill.service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.payBill.service.IPayBillBaseService;
import com.cnfantasia.server.ms.payBill.entity.PayBillEntity;
import com.cnfantasia.server.ms.payBill.entity.PayBillWithFeeDetailEntity;

public interface IPayBillService extends IPayBillBaseService {
	
	public String saveImportPayBill(List<PayBillWithFeeDetailEntity> payBills);

	List<PayBillWithFeeDetailEntity> getExportedPayBill(List<BigInteger> payBillIdList);

	/**
	 * 查看单个账单详情
	 * 
	 * @param id
	 */
	PayBillWithFeeDetailEntity getPayBillForDetail(BigInteger id);

	public List select_payBill_with_payRecord(String id);

	public int markByManual(String id);

	int getPayBill_byUserId_forCount(Map<String, Object> paramMap);

	List<PayBillEntity> getPayBillList_byUserId_forPage(int curPageIndex, int pageSize, Map<String, Object> paramMap);
}
