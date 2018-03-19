package com.cnfantasia.server.ms.payBill.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.payBill.entity.PayBill;
import com.cnfantasia.server.domainbase.payBill.service.PayBillBaseService;
import com.cnfantasia.server.domainbase.propertyFeeDetail.dao.PropertyFeeDetailBaseDao;
import com.cnfantasia.server.domainbase.propertyFeeDetail.entity.PropertyFeeDetail;
import com.cnfantasia.server.ms.payBill.dao.IPayBillDao;
import com.cnfantasia.server.ms.payBill.entity.PayBillEntity;
import com.cnfantasia.server.ms.payBill.entity.PayBillWithFeeDetailEntity;
import com.cnfantasia.server.ms.pub.session.UserContext;

public class PayBillService extends PayBillBaseService implements IPayBillService {
	private IPayBillDao payBillDao;
	private IUuidManager uuidManager;
	private PropertyFeeDetailBaseDao propertyFeeDetailBaseDao;

	public void setPropertyFeeDetailBaseDao(PropertyFeeDetailBaseDao propertyFeeDetailBaseDao) {
		this.propertyFeeDetailBaseDao = propertyFeeDetailBaseDao;
	}

	public void setPayBillDao(IPayBillDao payBillDao) {
		this.payBillDao = payBillDao;
	}

	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}

	@Override
	@Transactional
	public String saveImportPayBill(List<PayBillWithFeeDetailEntity> payBills) {
		String resultInfo = payBillDao.vierfyImportPayBill(payBills);
		if (payBills.size() == 0)//没有要导入的数据
			return resultInfo;

		List<PropertyFeeDetail> propertyFeeDetailList = new ArrayList<PropertyFeeDetail>();
		List<PayBill> payBillList = new ArrayList<PayBill>();
		List<BigInteger> payBillIdList = uuidManager.getNextUuidBigInteger(SEQConstants.t_pay_bill, payBills.size());
	
		for (int i = 0; i < payBills.size(); i++) {
			payBills.get(i).setId(payBillIdList.get(i));
			payBills.get(i).setSys0AddUser(UserContext.getCurrUser().getId());

			payBillList.add(payBills.get(i));
			for (int j = 0; j < payBills.get(i).getPropertyFeeDetails().size(); j++) {
				payBills.get(i).getPropertyFeeDetails().get(j).settPayBillFId(payBills.get(i).getId());
			}
			propertyFeeDetailList.addAll(payBills.get(i).getPropertyFeeDetails());
		}

		List<BigInteger> propertyFeeDetailIdList = uuidManager.getNextUuidBigInteger(SEQConstants.t_property_fee_detail, propertyFeeDetailList.size());
		for (int i = 0; i < propertyFeeDetailList.size(); i++) {
			propertyFeeDetailList.get(i).setId(propertyFeeDetailIdList.get(i));
		}
		payBillDao.insertPayBillBatch(payBillList);
		propertyFeeDetailBaseDao.insertPropertyFeeDetailBatch(propertyFeeDetailList);

		return resultInfo;
	}

	@Override
	public List<PayBillWithFeeDetailEntity> getExportedPayBill(List<BigInteger> payBillIdList) {
		return payBillDao.getExportedPayBill(payBillIdList);
	}

	@Override
	public PayBillWithFeeDetailEntity getPayBillForDetail(BigInteger id) {
		return payBillDao.getPayBillForDetail(id);
	}

	@Override
	public List select_payBill_with_payRecord(String id) {
		return payBillDao.select_payBill_with_payRecord(id);
	}

	@Override
	public int markByManual(String id) {
		return payBillDao.markByManual(id);
	}

	@Override
	public int getPayBill_byUserId_forCount(Map<String, Object> paramMap) {
		return payBillDao.getPayBill_byUserId_forCount(paramMap);
	}

	@Override
	public List<PayBillEntity> getPayBillList_byUserId_forPage(int curPageIndex, int pageSize, Map<String, Object> paramMap) {
		return payBillDao.getPayBill_byUserId_forPage(curPageIndex, pageSize, paramMap);
	}

}
