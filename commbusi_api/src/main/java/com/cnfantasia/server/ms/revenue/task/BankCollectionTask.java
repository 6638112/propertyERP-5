package com.cnfantasia.server.ms.revenue.task;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.api.bankCollection.constant.BankCollectionConstants;
import com.cnfantasia.server.api.bankCollection.entity.BillCycleAndTypeName;
import com.cnfantasia.server.api.bankCollection.entity.PPBankCollectionWithPayBill;
import com.cnfantasia.server.api.bankCollection.entity.PropertyCompanyBCInfo;
import com.cnfantasia.server.api.bankCollection.service.BankCollectionService;
import com.cnfantasia.server.api.bankCollection.transfer.IBankCollectionTransfer;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.bankCollectionDate.entity.BankCollectionDate;
import com.cnfantasia.server.domainbase.bankCollectionDate.service.BankCollectionDateBaseService;
import com.cnfantasia.server.domainbase.bcFileDefine.entity.BcFileDefine;
import com.cnfantasia.server.domainbase.bcFileDefine.service.BcFileDefineBaseService;
import com.cnfantasia.server.domainbase.bcFinanceOrg.entity.BcFinanceOrg;
import com.cnfantasia.server.domainbase.bcFinanceOrg.service.BcFinanceOrgBaseService;
import com.cnfantasia.server.domainbase.bcGroupBuildingCycle.dao.BcGroupBuildingCycleBaseDao;
import com.cnfantasia.server.domainbase.bcGroupBuildingCycle.entity.BcGroupBuildingCycle;
import com.cnfantasia.server.domainbase.bcGroupBuildingCycleEntry.dao.BcGroupBuildingCycleEntryBaseDao;
import com.cnfantasia.server.domainbase.bcGroupBuildingCycleEntry.entity.BcGroupBuildingCycleEntry;
import com.cnfantasia.server.domainbase.bcOfferRecord.entity.BcOfferRecord;
import com.cnfantasia.server.domainbase.bcOfferRecord.service.BcOfferRecordBaseService;

/**
 * 出盘回盘功能
 * @author wenfq 2017-04-11
 *
 */
public class BankCollectionTask implements ISynTask {
	
	@Resource
	BankCollectionService bankCollectionService;
	
	@Resource
	IUuidManager uuidManager;
	
	@Resource
	BcGroupBuildingCycleEntryBaseDao bcGroupBuildingCycleEntryDao;

	@Resource
	BcGroupBuildingCycleBaseDao BcGroupBuildingCycleBaseDao;
	
	@Resource
	BcFileDefineBaseService bcFileDefineBaseService;
	
	@Resource
	BankCollectionDateBaseService bankCollectionDateBaseService;
	
	@Resource
	BcOfferRecordBaseService bcOfferRecordBaseService;
	
	@Resource
	BcFinanceOrgBaseService bcFinanceOrgBaseService;
	
	private Log logger = LogFactory.getLog(getClass());
	
	@Override
	public Integer execTask() {
		List<PropertyCompanyBCInfo> bcInfoList = bankCollectionService.selectAllBankCollectionInfoList();
		logger.info("bcInfoList：" + bcInfoList);
		for(PropertyCompanyBCInfo bcInfo: bcInfoList){//循环每一个物业公司下的托收配置
			List<BigInteger> gbIdList = bcInfo.getGbIdList();
			if(gbIdList.isEmpty()){
				continue;
			}
			
			List<BillCycleAndTypeName> billCycleAndTypeNameList = bankCollectionService.selectGBBCListByGbIDList(gbIdList);
			logger.info("billCycleAndTypeNameList：" + billCycleAndTypeNameList);
			if(billCycleAndTypeNameList.isEmpty())
				continue;
			
			List<BcGroupBuildingCycleEntry> bcGBCycleEntryList = new ArrayList<BcGroupBuildingCycleEntry>();
			List<BigInteger> entryIdList = uuidManager.getNextUuidBigInteger(SEQConstants.t_bc_group_building_cycle_entry, billCycleAndTypeNameList.size());
			List<BcGroupBuildingCycle> bcGBCycleList = new ArrayList<BcGroupBuildingCycle>();
			BankCollectionDate bcdQry = new  BankCollectionDate();
			bcdQry.settPropertyCompanyFId(bcInfo.gettPropertyCompanyFId());
			List<BankCollectionDate> bcdList = bankCollectionDateBaseService.getBankCollectionDateByCondition(MapConverter.toMap(bcdQry));
			
			for(int i = 0; i < billCycleAndTypeNameList.size(); i++){
				BillCycleAndTypeName billCycleAndTypeName = billCycleAndTypeNameList.get(i);
				BcGroupBuildingCycleEntry bcGBCycleEntry = new BcGroupBuildingCycleEntry();
				bcGBCycleEntry.setId(entryIdList.get(i));
				bcGBCycleEntry.setGbbcId(billCycleAndTypeName.getGbbcId());
				bcGBCycleEntryList.add(bcGBCycleEntry);
				
				if(bcGBCycleList.isEmpty()){
					BcGroupBuildingCycle bcGBCycle = new BcGroupBuildingCycle();
					bcGBCycle.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_bc_group_building_cycle));
					bcGBCycle.settPropertyCompanyBankCollectionInfoFId(bcInfo.getId());
					bcGBCycle.setPayBillTypeName(billCycleAndTypeName.getPbtName());
					bcGBCycle.setBillMonthStart(billCycleAndTypeName.getBillMonthStart());
					bcGBCycle.setBillMonthEnd(billCycleAndTypeName.getBillMonthEnd());
					bcGBCycle.setGbNames(billCycleAndTypeName.getGbName());
					bcGBCycle.setRebackStatus(0);
					for(int j = 0; j<bcdList.size(); j++){
						if(Calendar.getInstance().get(Calendar.DAY_OF_MONTH) == bcdList.get(j).getBankCollectionDate())
							bcGBCycle.settBankCollectionDateFId(bcdList.get(j).getId());
					}
					bcGBCycleEntry.settBcGroupBuildingCycleFId(bcGBCycle.getId());
					bcGBCycleList.add(bcGBCycle);
					continue;
				}
				
				BcGroupBuildingCycle bcGBCycle = bcGBCycleList.get(bcGBCycleList.size()-1);
				if(billCycleAndTypeName.getBillMonthStart().equals(bcGBCycle.getBillMonthStart())
						&& billCycleAndTypeName.getBillMonthEnd().equals(bcGBCycle.getBillMonthEnd())
						&& billCycleAndTypeName.getPbtName().equals(bcGBCycle.getPayBillTypeName())){//需要合并进去
					bcGBCycleEntry.settBcGroupBuildingCycleFId(bcGBCycle.getId());
					bcGBCycle.setGbNames(bcGBCycle.getGbNames() + "," + billCycleAndTypeName.getGbName());
				}else{//不能再合并，要单独新建一份
					bcGBCycle = new BcGroupBuildingCycle();
					bcGBCycle.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_bc_group_building_cycle));
					bcGBCycle.settPropertyCompanyBankCollectionInfoFId(bcInfo.getId());
					bcGBCycle.setPayBillTypeName(billCycleAndTypeName.getPbtName());
					bcGBCycle.setBillMonthStart(billCycleAndTypeName.getBillMonthStart());
					bcGBCycle.setBillMonthEnd(billCycleAndTypeName.getBillMonthEnd());
					bcGBCycle.setGbNames(billCycleAndTypeName.getGbName());
					bcGBCycle.setRebackStatus(0);
					for(int j = 0; j<bcdList.size(); j++){
						if(Calendar.getInstance().get(Calendar.DAY_OF_MONTH) == bcdList.get(j).getBankCollectionDate())
							bcGBCycle.settBankCollectionDateFId(bcdList.get(j).getId());
					}
					bcGBCycleEntry.settBcGroupBuildingCycleFId(bcGBCycle.getId());
					bcGBCycleList.add(bcGBCycle);
				}
			}
			
			//2 生成出盘文件， 没有银行账号的也不需要出盘 
			List<BcFileDefine> bcDetailFileDefineList = bankCollectionService.selectFileDefineList(bcInfo.getId(), 0);
			List<BcFileDefine> bcSumFileDefineList = bankCollectionService.selectFileDefineList(bcInfo.getId(), 1);
			
			logger.info("bcFileDefineList：" + bcDetailFileDefineList);
			for(int i = 0; i < bcGBCycleList.size(); i++){
				BcGroupBuildingCycle bcGBCycle = bcGBCycleList.get(i);
				List<BcGroupBuildingCycleEntry> bcGBCycleEntryAddList = new ArrayList<BcGroupBuildingCycleEntry>();
				for(int j = 0; j < bcGBCycleEntryList.size(); j++){
					if(bcGBCycle.getId().equals(bcGBCycleEntryList.get(j).gettBcGroupBuildingCycleFId())){
						bcGBCycleEntryAddList.add(bcGBCycleEntryList.get(j));
					}
				}
				List<PPBankCollectionWithPayBill> bcPayBillList = bcInfo.getCollectionRange() == BankCollectionConstants.CollectionRange.By_GB
								? bankCollectionService.selectPayBllWithBCCycleIdForGBRange(bcGBCycleEntryAddList)
								: bankCollectionService.selectPayBllWithBCCycleIdForPPRange(bcInfo.getId(), bcGBCycleEntryAddList);
				logger.info("bcPayBillList：" + bcPayBillList);
				if(bcPayBillList.isEmpty())
					continue;
				
	 		/* 	往月欠费已经加入到账单金额中， 所以注释如下代码
	 		 *  	List<PPBankCollectionWithPayBill> bcUnPaidPayBillList = bankCollectionService.selectUnPaidPayBllWithPayBillList(bcPayBillList);
					if(bcUnPaidPayBillList.size() > 0){
					bcPayBillList.addAll(bcUnPaidPayBillList);
					Collections.reverse(bcPayBillList);
				}*/
				
				// 生成托收账期， 没有符合条件的账单，不需要出盘
				BcGroupBuildingCycleBaseDao.insertBcGroupBuildingCycle(bcGBCycle);
				bcGroupBuildingCycleEntryDao.insertBcGroupBuildingCycleEntryBatch(bcGBCycleEntryAddList);
				
				BcFinanceOrg bcFinanceOrg = bcFinanceOrgBaseService.getBcFinanceOrgBySeqId(bcInfo.gettBankCollectionFinanceOrgFId());
				try {
					IBankCollectionTransfer bcTransfer = (IBankCollectionTransfer) Class.forName(bcFinanceOrg.getClassName()).newInstance();
					List<BcOfferRecord> bcOfferRecordList = new ArrayList<BcOfferRecord>();
					String fileDetailFullPath = bcTransfer.exportDetailFile(bcDetailFileDefineList, bcPayBillList,bcGBCycle, bcOfferRecordList);
					logger.info("---has generate the detailFile: " + fileDetailFullPath);
					CnfantasiaCommbusiUtil.newStandardListForId(bcOfferRecordList, SEQConstants.t_bc_offer_record); 
					bcOfferRecordBaseService.insertBcOfferRecordBatch(bcOfferRecordList);
					if(bcOfferRecordList.size()>0)
						bankCollectionService.updatePayBillAfterOffer(bcOfferRecordList);
					
					int timesInThisMonth = 1;//本月第几次出盘
					if(bcdList.size() == 2){
						int day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
						if(day == Math.min(bcdList.get(0).getBankCollectionDate(), bcdList.get(1).getBankCollectionDate())){
							timesInThisMonth = 1;
						}else{
							timesInThisMonth = 2;
						}
					}
					
					String fileSumFullPath = bcTransfer.exportSumFile(timesInThisMonth, bcInfo, bcSumFileDefineList, bcPayBillList);
					
					BcGroupBuildingCycle bcGBCycleUpd = new BcGroupBuildingCycle();
					bcGBCycleUpd.setId(bcGBCycle.getId());					
					bcGBCycleUpd.setDetailFileUrl(fileDetailFullPath);
					bcGBCycleUpd.setSumFileUrl(fileSumFullPath);
					BcGroupBuildingCycleBaseDao.updateBcGroupBuildingCycle(bcGBCycleUpd);
				} catch (IOException e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
					e.printStackTrace();
				} 
			}
		}
		
		return null;
	}
}
