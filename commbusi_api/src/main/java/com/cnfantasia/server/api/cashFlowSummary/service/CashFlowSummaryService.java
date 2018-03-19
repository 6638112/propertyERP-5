package com.cnfantasia.server.api.cashFlowSummary.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cnfantasia.server.api.cashFlowSummary.constant.CashFlowSummaryDict;
import com.cnfantasia.server.api.cashFlowSummary.dao.ICashFlowSummaryDao;
import com.cnfantasia.server.api.common.dao.ICommonLockDao;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.cashFlowSummary.entity.CashFlowSummary;
import com.cnfantasia.server.domainbase.cashFlowSummary.service.CashFlowSummaryBaseService;

/**
 * 现金流量汇总表
 * 
 * @author liyulin
 * @version 1.0 2016年7月18日 下午5:39:58
 */
public class CashFlowSummaryService extends CashFlowSummaryBaseService implements ICashFlowSummaryService {
	private ICashFlowSummaryDao cashFlowSummaryDao;
	public void setCashFlowSummaryDao(ICashFlowSummaryDao cashFlowSummaryDao) {
		this.cashFlowSummaryDao = cashFlowSummaryDao;
	}
	
	private ICommonLockDao commonLockDao;
	public void setCommonLockDao(ICommonLockDao commonLockDao) {
		this.commonLockDao = commonLockDao;
	}
	
	private IUuidManager uuidManager;
	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}
	
	/**
	 * 更新字段f_cash_status
	 * 
	 * @param type 现金流类型
	 * @param srcIds
	 * @return
	 */
	private int updateCashStatus(int type, List<String> srcIds){
		switch (type) {
		case CashFlowSummaryDict.BillType.WY_FEE:
			return cashFlowSummaryDao.updatePayBillCashStatusBatch(srcIds);
		case CashFlowSummaryDict.BillType.OTHER_FEE:
			return cashFlowSummaryDao.updatePayBillCashStatusBatch(srcIds);
		case CashFlowSummaryDict.BillType.PARKINGB_FEE:
			return cashFlowSummaryDao.updateFinanceBuyCashStatusBatch(srcIds);
		case CashFlowSummaryDict.BillType.WYB_FEE:
			return cashFlowSummaryDao.updateFinanceBuyCashStatusBatch(srcIds);
		case CashFlowSummaryDict.BillType.REPAIR_FEE:
			return cashFlowSummaryDao.updateDredgeBillCashStatusBatch(srcIds);
		case CashFlowSummaryDict.BillType.DOWN_FEE:
			return cashFlowSummaryDao.updateEbuyOrderCashStatusBatch(srcIds);
		case CashFlowSummaryDict.BillType.SELF_RUN_FEE:
			return cashFlowSummaryDao.updateEbuyOrderCashStatusBatch(srcIds);
		case CashFlowSummaryDict.BillType.PARKING_FEE:
			return cashFlowSummaryDao.updateCarNumPayLogCashStatusBatch(srcIds);
		default:
			return 0;
		}
	}
	
	/**
	 * 查询待生成的现金流数据
	 * 
	 * @param type 现金流类型
	 * @param limitCount 每次查询数据条数
	 * @return
	 */
	private List<CashFlowSummary> selectCashFlowSummarys(int type, int limitCount){
		switch (type) {
		case CashFlowSummaryDict.BillType.WY_FEE:
			return cashFlowSummaryDao.selectForWY(limitCount);
		case CashFlowSummaryDict.BillType.OTHER_FEE:
			return cashFlowSummaryDao.selectForOther(limitCount);
		case CashFlowSummaryDict.BillType.PARKINGB_FEE:
			return cashFlowSummaryDao.selectForParkingB(limitCount);
		case CashFlowSummaryDict.BillType.WYB_FEE:
			return cashFlowSummaryDao.selectForWYB(limitCount);
		case CashFlowSummaryDict.BillType.REPAIR_FEE:
			return cashFlowSummaryDao.selectForRepair(limitCount);
		case CashFlowSummaryDict.BillType.DOWN_FEE:
			return cashFlowSummaryDao.selectForDown(limitCount);
		case CashFlowSummaryDict.BillType.SELF_RUN_FEE:
			return cashFlowSummaryDao.selectForSelfRun(limitCount);
		case CashFlowSummaryDict.BillType.PARKING_FEE:
			return cashFlowSummaryDao.selectForParking(limitCount);
		default:
			return null;
		}
	}
	
	/**
	 * 表t_cash_flow_summary数据生成
	 * 
	 * @param lockTable 锁定的表
	 * @param type 现金流类型
	 * @param limitCount 每次查询数据条数
	 */
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private int insertCashFlowSummaryBatch(String lockTable, int type, int limitCount){
		commonLockDao.getLock(lockTable);
		List<CashFlowSummary> cashFlowSummarys = selectCashFlowSummarys(type, limitCount);

		int insertCount =  0;
		if (cashFlowSummarys != null && cashFlowSummarys.size() > 0) {
			int total = cashFlowSummarys.size();
			List<String> srcIds = new ArrayList<String>(total);
			List<BigInteger> cashIds = uuidManager.getNextUuidBigInteger(SEQConstants.t_cash_flow_summary, total);
			for (int i=0; i<total; i++) {
				CashFlowSummary cashFlowSummary = cashFlowSummarys.get(i);
				cashFlowSummary.setId(cashIds.get(i));
				srcIds.add(cashFlowSummary.getSrcBillId());
			}
			
			insertCount = cashFlowSummaryDao.insertCashFlowSummaryBatch(cashFlowSummarys);
			if (insertCount >= total) {
				updateCashStatus(type, srcIds);
			} else {
				throw new BusinessRuntimeException("现金流数据生成异常！CashFlowSummaryDict.BillType==>"+type+"==>updateCount<total");
			}
		}
		
		return insertCount;
	}
	
	/**
	 * 表t_cash_flow_summary数据自动生成
	 * 
	 * @param lockTable 锁定的表
	 * @param billType 现金流类型
	 * @param limitCount while循环每次查询的数据条数
	 */
	@Override
	public void autoInsertCashFlowSummaryBatch(String lockTable, int billType, int limitCount){
		int affectCount = limitCount;
		while(affectCount>=limitCount){// 避免极端情况下大量数据一次性处理。每次最多查询并处理100（默认）条，当某次少于100（默认）时，表示已经处理完成
			affectCount = insertCashFlowSummaryBatch(lockTable, billType, limitCount);
		}
	}
	
}
