package com.cnfantasia.server.api.meterReading.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.cnfantasia.server.api.pub.session.UserContext;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.CommControllerUtils;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.domainbase.building.entity.Building;
import com.cnfantasia.server.domainbase.building.service.BuildingBaseService;
import com.cnfantasia.server.domainbase.building.service.IBuildingBaseService;
import com.cnfantasia.server.domainbase.mrStandardBuilding.entity.MrStandardBuilding;
import com.cnfantasia.server.domainbase.mrStandardBuilding.service.IMrStandardBuildingBaseService;
import com.cnfantasia.server.domainbase.mrStandardGroupBuilding.entity.MrStandardGroupBuilding;
import com.cnfantasia.server.domainbase.mrStandardGroupBuilding.service.IMrStandardGroupBuildingBaseService;
import com.cnfantasia.server.domainbase.mrStandardRoom.entity.MrStandardRoom;
import com.cnfantasia.server.domainbase.mrStandardRoom.service.IMrStandardRoomBaseService;
import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;
import com.cnfantasia.server.domainbase.realRoom.service.IRealRoomBaseService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.meterReading.dao.MeterReadingDao;
import com.cnfantasia.server.api.meterReading.entity.MrFeeItemWithFormula;
import com.cnfantasia.server.api.meterReading.entity.RealRoomHasMrLastRecordEntity;
import com.cnfantasia.server.api.plotproperty.constant.PlotpropertyDict;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.commbusi.plotproperty.service.IPlotpropertyCfgService;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.mrFeeItem.entity.MrFeeItem;
import com.cnfantasia.server.domainbase.mrFeeItem.service.MrFeeItemBaseService;
import com.cnfantasia.server.domainbase.mrFeeItemFormula.entity.MrFeeItemFormula;
import com.cnfantasia.server.domainbase.mrFeeItemFormula.service.MrFeeItemFormulaBaseService;
import com.cnfantasia.server.domainbase.mrPayBillRecord.dao.MrPayBillRecordBaseDao;
import com.cnfantasia.server.domainbase.mrPayBillRecord.entity.MrPayBillRecord;
import com.cnfantasia.server.domainbase.payBill.entity.PayBill;
import com.cnfantasia.server.domainbase.payBillSplit.service.PayBillSplitBaseService;
import com.cnfantasia.server.domainbase.payBillTimeCfg.dao.PayBillTimeCfgBaseDao;
import com.cnfantasia.server.domainbase.payBillTimeCfg.entity.PayBillTimeCfg;
import com.cnfantasia.server.domainbase.payBillType.entity.PayBillType;
import com.cnfantasia.server.domainbase.payBillType.service.PayBillTypeBaseService;
import com.cnfantasia.server.domainbase.propertyFeeDetail.dao.PropertyFeeDetailBaseDao;
import com.cnfantasia.server.domainbase.propertyFeeDetail.entity.PropertyFeeDetail;
import com.cnfantasia.server.domainbase.realRoomHasMrLastRecord.dao.RealRoomHasMrLastRecordBaseDao;
import com.cnfantasia.server.domainbase.realRoomHasMrLastRecord.entity.RealRoomHasMrLastRecord;
import com.cnfantasia.server.ms.groupBuildingBillCycle.entity.GroupBuildingBillCycleEntity;
import com.cnfantasia.server.ms.payBill.dao.IPayBillDao;
import com.cnfantasia.server.ms.payBill.entity.PayBillWithFeeDetailWithMrRecordEntity;

public class MeterReadingService {
	private Log logger = LogFactory.getLog(getClass());
	
	@Resource
	private MeterReadingDao meterReadingDao;
	
	@Resource
	private MrFeeItemBaseService mrFeeItemBaseService;
	
	@Resource
	IUuidManager uuidManager;
	
	@Resource
	private MrFeeItemFormulaBaseService mrFeeItemFormulaBaseService;
	
	@Resource
	private PayBillTypeBaseService payBillTypeBaseService;
	@Resource
	IPlotpropertyCfgService plotpropertyCfgService;
	@Resource
	private IPayBillDao payBillDao;

	@Resource
	PayBillTimeCfgBaseDao payBillTimeCfgBaseDao;
	
	@Resource
	PayBillSplitBaseService payBillSplitBaseService;
	@Resource
	private PropertyFeeDetailBaseDao propertyFeeDetailBaseDao;
	
	@Resource
	private RealRoomHasMrLastRecordBaseDao realRoomHasMrLastRecordBaseDao;
	@Resource
	private MrPayBillRecordBaseDao mrPayBillRecordBaseDao;

	public int selectGroupBuildingForCount(Map<String, Object> paramMap) {
		return meterReadingDao.selectGroupBuildingForCount(paramMap);
	}

	public List<GroupBuilding> selectGroupBuildingForList(int curPageIndex, int pageSize, Map<String, Object> paramMap) {
		return meterReadingDao.selectGroupBuildingForList(curPageIndex, pageSize, paramMap);
	}

	public int queryBillCycleCount(Map<String, Object> paramMap) {
		return meterReadingDao.queryBillCycleCount(paramMap);
	}

	public List<GroupBuildingBillCycleEntity> queryBillCycleForList(int curPageIndex, int pageSize, Map<String, Object> paramMap, boolean isPage) {
		if(isPage){
			paramMap.put("_begin", pageSize * curPageIndex);
			paramMap.put("_length", pageSize);
		}
		return meterReadingDao.queryBillCycleForList(paramMap);
	}

	/**
	 * 保存配置项
	 * @param feeItemJson
	 * @param gbId
	 */
	public void saveMrFeeItem(String feeItemJson, BigInteger gbId) {
		List<MrFeeItem> mrFeeItemList = JSON.parseArray(feeItemJson, MrFeeItem.class);
		List<MrFeeItem> mrFeeItemAddNewList = new ArrayList<MrFeeItem>();
		List<MrFeeItem> mrFeeItemUpdList = new ArrayList<MrFeeItem>();
		for(MrFeeItem mfi : mrFeeItemList){
			if(StringUtils.isEmpty(mfi.getId())){
				mrFeeItemAddNewList.add(mfi);
				mfi.setGbId(gbId);
				mfi.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_mr_fee_item));
				mfi.setLastUpdTime(DateUtils.getCurrentDate());
			}else{
				mrFeeItemUpdList.add(mfi);
			}
		}
		if(!mrFeeItemAddNewList.isEmpty()) {
			mrFeeItemBaseService.insertMrFeeItemBatch(mrFeeItemAddNewList);
		}

		if(!mrFeeItemUpdList.isEmpty())
			mrFeeItemBaseService.updateMrFeeItemBatch(mrFeeItemUpdList);
	}

	/**
	 * 保存计算规则 
	 * @param feeItemFormulaJson
	 * @param mfiId
	 */
	public void saveMrFeeItemFormula(String feeItemFormulaJson, BigInteger mfiId) {
		List<MrFeeItemFormula> mrFeeItemFormulaList = JSON.parseArray(feeItemFormulaJson, MrFeeItemFormula.class);
		List<MrFeeItemFormula> mrFeeItemFormulaAddNewList = new ArrayList<MrFeeItemFormula>();
		List<MrFeeItemFormula> mrFeeItemFormulaUpdList = new ArrayList<MrFeeItemFormula>();
		
		for(int i = 0; i< mrFeeItemFormulaList.size(); i++){
			MrFeeItemFormula mfif = mrFeeItemFormulaList.get(i);
			if(StringUtils.isEmpty(mfif.getId())){
				mfif.settMrFeeItemFId(mfiId);
				mfif.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_mr_fee_item_formula));
				mrFeeItemFormulaAddNewList.add(mfif);
			}
			else 
				mrFeeItemFormulaUpdList.add(mfif);
		}
		
		MrFeeItemFormula mfifQry = new MrFeeItemFormula();
		mfifQry.settMrFeeItemFId(mfiId);
		List<MrFeeItemFormula> mrFeeItemFormulaExistList = mrFeeItemFormulaBaseService.getMrFeeItemFormulaByCondition(MapConverter.toMap(mfifQry));
		
		if(!mrFeeItemFormulaAddNewList.isEmpty())
			mrFeeItemFormulaBaseService.insertMrFeeItemFormulaBatch(mrFeeItemFormulaAddNewList);
		
		if(!mrFeeItemFormulaUpdList.isEmpty())
			mrFeeItemFormulaBaseService.updateMrFeeItemFormulaBatch(mrFeeItemFormulaUpdList);
		
		
		ArrayList<BigInteger> idList = new ArrayList<BigInteger>();
		mrFeeItemFormulaUpdList.addAll(mrFeeItemFormulaAddNewList);
		for(MrFeeItemFormula mfifExist: mrFeeItemFormulaExistList){
			for(int i = 0; i< mrFeeItemFormulaUpdList.size(); i++){
				MrFeeItemFormula mrFeeItemFormulaUdp = mrFeeItemFormulaUpdList.get(i);
				if(mfifExist.getId().equals(mrFeeItemFormulaUdp.getId()))
					break;//已经找到是待更新或刚插入的的记录
				if(i == mrFeeItemFormulaUpdList.size()-1)//不是待更新或刚插入的记录，即要删除的记录
					idList.add(mfifExist.getId());
			}
		}
		if(!idList.isEmpty()){
			mrFeeItemFormulaBaseService.deleteMrFeeItemFormulaLogicBatch(idList);
		}
	}
	
	/**
	 * 保存导入的数据
	 * @param payBills 待插入的数据 
	 * @param realRoomLastRecordAddNewList 新插入的上期读数
	 * @param realRoomLastRecordList 待更新的上期读数
	 * @return
	 */
	@Transactional
	public String saveImportPayBillPeriod(List<PayBillWithFeeDetailWithMrRecordEntity> payBills, List<RealRoomHasMrLastRecordEntity> realRoomLastRecordList, List<RealRoomHasMrLastRecordEntity> realRoomLastRecordAddNewList) {
		long currentTimeStart = System.currentTimeMillis();
		
		String resultInfo = meterReadingDao.verifyImportPayBill(payBills, realRoomLastRecordAddNewList);
		if (payBills.size() == 0)//没有要导入的数据
			return resultInfo;

		PayBillTimeCfg payBillTimeCfg = new PayBillTimeCfg();
		payBillTimeCfg.setBillTypeId(payBills.get(0).getBillTypeId());
		payBillTimeCfg.setGbId(new BigInteger(payBills.get(0).getGroupBuildingId()));
		payBillTimeCfg.setBillMonthSize(payBills.get(0).getBillMonthSize());
		payBillTimeCfg.setBillMonthStart(payBills.get(0).getBillMonthStart());
		payBillTimeCfg.setBillMonthEnd(payBills.get(0).getBillMonthEnd());
		payBillTimeCfg.setPayDayStart(payBills.get(0).getPayDayStart());
		payBillTimeCfg.setPayDayEnd(payBills.get(0).getPayDayEnd());
		
		if(payBillDao.selectCountCrossTimeConfig(payBillTimeCfg)>0){
			return "账单日期跟账单日期不能重叠，缴费日期跟缴费日期不能重叠";
		}
		
		List<PayBillTimeCfg> payBillTimeCfgList = payBillTimeCfgBaseDao.selectPayBillTimeCfgByCondition(MapConverter.toMap(payBillTimeCfg), false);
		if(payBillTimeCfgList.size()==0){
			payBillTimeCfg.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_pay_bill_time_cfg));
			payBillTimeCfgBaseDao.insertPayBillTimeCfg(payBillTimeCfg);
		}/*else {
			payBillTimeCfg.setId(payBillTimeCfgList.get(0).getId());
		}*/

		List<PropertyFeeDetail> propertyFeeDetailList = new ArrayList<PropertyFeeDetail>();
		List<MrPayBillRecord> mrPayBillRecordList = new ArrayList<MrPayBillRecord>();//mrPayBillRecordList与propertyFeeDetailList的顺序应该是对应的
		List<PayBill> payBillList = new ArrayList<PayBill>();
		List<BigInteger> payBillIdList = uuidManager.getNextUuidBigInteger(SEQConstants.t_pay_bill, payBills.size());
		
		for (int i = 0; i < payBills.size(); i++) {
			PayBillWithFeeDetailWithMrRecordEntity paybill = payBills.get(i);
			paybill.setId(payBillIdList.get(i));
			paybill.setIsPropFee(2);
			paybill.setBankCollectionStatus(0);
			
			payBillList.add(paybill);
			for (int j = 0; j < paybill.getPropertyFeeDetails().size(); j++) {
				paybill.getPropertyFeeDetails().get(j).settPayBillFId(paybill.getId());
				paybill.getMrPayBillRecordList().get(j).settPayBillFId(paybill.getId());
			}
			
			propertyFeeDetailList.addAll(paybill.getPropertyFeeDetails());
			mrPayBillRecordList.addAll(paybill.getMrPayBillRecordList());
		}

		List<BigInteger> propertyFeeDetailIdList = uuidManager.getNextUuidBigInteger(SEQConstants.t_property_fee_detail, propertyFeeDetailList.size());
		List<BigInteger> mrPayRecordIdList = uuidManager.getNextUuidBigInteger(SEQConstants.t_mr_pay_bill_record, mrPayBillRecordList.size());
		for (int i = 0; i < propertyFeeDetailList.size(); i++) {
			propertyFeeDetailList.get(i).setId(propertyFeeDetailIdList.get(i));
			mrPayBillRecordList.get(i).setId(mrPayRecordIdList.get(i));
			mrPayBillRecordList.get(i).settPropertyFeeDetailFId(propertyFeeDetailIdList.get(i));
		}

		int batchStepSize = 100; //一次插入100条，分批插入能提高性能
		
		for (int i = 0; i < (payBillList.size() / batchStepSize) + 1; i++) {
			long currentTime = System.currentTimeMillis();
			int endIndex = (i + 1) * batchStepSize > payBillList.size() ? payBillList.size() : (i + 1) * batchStepSize;

			List<PayBill> subList = payBillList.subList(i * batchStepSize, endIndex);
			if (subList.size() > 0) {
				payBillDao.insertPayBillBatch(subList);
			}
			logger.info(i + " use time is: " + (System.currentTimeMillis()-currentTime));
		}

		for (int i = 0; i < (propertyFeeDetailList.size() / batchStepSize) + 1; i++) {
			long currentTime = System.currentTimeMillis();
			int endIndex = (i + 1) * batchStepSize > propertyFeeDetailList.size() ? propertyFeeDetailList.size() : (i + 1) * batchStepSize;
			List<PropertyFeeDetail> subList = propertyFeeDetailList.subList(i * batchStepSize, endIndex);
			if (subList.size() > 0) {
				propertyFeeDetailBaseDao.insertPropertyFeeDetailBatch(subList);
			}
			logger.info(i + " use time is: " + (System.currentTimeMillis()-currentTime));
		}
		
		if(mrPayBillRecordList.size()>0)
			mrPayBillRecordBaseDao.insertMrPayBillRecordBatch(mrPayBillRecordList);

		List<RealRoomHasMrLastRecord> rrLastRecordAddNewList = new ArrayList<RealRoomHasMrLastRecord>();
		for (int i = 0; i < realRoomLastRecordAddNewList.size(); i++) {
			RealRoomHasMrLastRecordEntity e = realRoomLastRecordAddNewList.get(i);
			if (e.gettRealRoomFId() != null) 
				//处理异常情况：第一次导入时只有一个配置项，第二次导入同一房间的重复账单，且配置了第二个缴费项，没有t_real_room_id是不能插入的
				rrLastRecordAddNewList.add(e);
		}
		
		if (rrLastRecordAddNewList.size() > 0)
			realRoomHasMrLastRecordBaseDao.insertRealRoomHasMrLastRecordBatch(rrLastRecordAddNewList);
		
		if(realRoomLastRecordList.size()>0){
			List<RealRoomHasMrLastRecord> rrLastRecordList = new ArrayList<RealRoomHasMrLastRecord>();
			rrLastRecordList.addAll(realRoomLastRecordList);
			realRoomHasMrLastRecordBaseDao.updateRealRoomHasMrLastRecordBatch(rrLastRecordList);
		}
		
		
		logger.info(" total use time is: " + (System.currentTimeMillis()-currentTimeStart));

		return resultInfo;
	}

	public PayBillType getPayBillType(String gbId) {
		return this.getPayBillType(new BigInteger(gbId));
	}
	
	public PayBillType getPayBillType(BigInteger gbId) {
		PayBillType pbtQry = new PayBillType();
		pbtQry.setGbId((gbId));
		pbtQry.setIsPropFee(PlotpropertyDict.PayBillType_IsPropFee.NO_MR);
		List<PayBillType> pbtList = payBillTypeBaseService.getPayBillTypeByCondition(MapConverter.convertBean(pbtQry));
		
		return pbtList.isEmpty() ? null: pbtList.get(0);
	}
	
	public List<MrFeeItemWithFormula> getMrFeeItemWithFormulaByGbId(String gbId){
		return meterReadingDao.getMrFeeItemWithFormulaByGbId(gbId);
	}

	public List<RealRoomHasMrLastRecordEntity> getRealRoomLastRecordByGbId(String gbId) {
		return meterReadingDao.getRealRoomLastRecordByGbId(gbId);
	}

	/**
	 * 删除收费规则，收费标准
	 * @param feeItemId
	 * @param userId
	 * @return
     */
	public int deleteMrCalculateRulAndStandardByFeeItemId(BigInteger feeItemId, BigInteger userId) {
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("feeItemId", feeItemId);
		paraMap.put("userId", userId);
		return meterReadingDao.deleteMrCalculateRulAndStandardByFeeItemId(paraMap);
	}
}
