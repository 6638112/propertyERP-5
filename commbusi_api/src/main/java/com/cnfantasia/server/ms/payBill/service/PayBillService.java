package com.cnfantasia.server.ms.payBill.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import com.cnfantasia.server.api.groupBuildingCycleCfg.entity.UnPaidPayBillEntity;
import com.cnfantasia.server.api.meterReading.constant.FeeTypeDict;
import com.cnfantasia.server.api.meterReading.dao.MeterReadingDao;
import com.cnfantasia.server.api.meterReading.entity.MrFeeItemWithFormula;
import com.cnfantasia.server.api.paybill.constant.PropertyFeeDetailTypeDict;
import com.cnfantasia.server.business.pub.utils.BigDecimalUtil;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.domainbase.ebuyOrder.service.IEbuyOrderBaseService;
import com.cnfantasia.server.domainbase.ebuyOrderHasTPayBill.entity.EbuyOrderHasTPayBill;
import com.cnfantasia.server.domainbase.ebuyOrderHasTPayBill.service.IEbuyOrderHasTPayBillBaseService;
import com.cnfantasia.server.domainbase.fixedFeeItemHasRoom.entity.FixedFeeItemHasRoom;
import com.cnfantasia.server.domainbase.fixedFeeItemHasRoom.service.IFixedFeeItemHasRoomBaseService;
import com.cnfantasia.server.domainbase.mrFeeItemFormula.entity.MrFeeItemFormula;
import com.cnfantasia.server.domainbase.mrPayBillRecord.dao.IMrPayBillRecordBaseDao;
import com.cnfantasia.server.domainbase.mrPayBillRecord.entity.MrPayBillRecord;
import com.cnfantasia.server.domainbase.realRoomHasMrLastRecord.dao.IRealRoomHasMrLastRecordBaseDao;
import com.cnfantasia.server.domainbase.realRoomHasMrLastRecord.entity.RealRoomHasMrLastRecord;
import com.cnfantasia.server.ms.payBill.entity.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cnfantasia.msg.common.util.ShortMsgUtil;
import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.groupBuildingCycleCfg.entity.UnPaidPayBillEntity;
import com.cnfantasia.server.api.meterReading.constant.FeeTypeDict;
import com.cnfantasia.server.api.meterReading.dao.MeterReadingDao;
import com.cnfantasia.server.api.meterReading.entity.MrFeeItemWithFormula;
import com.cnfantasia.server.api.paybill.constant.PropertyFeeDetailTypeDict;
import com.cnfantasia.server.api.plotproperty.constant.PlotpropertyConstant;
import com.cnfantasia.server.api.plotproperty.constant.PlotpropertyDict;
import com.cnfantasia.server.api.pub.utils.ApplicationContextBothUtil;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.utils.BigDecimalUtil;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.business.pub.uuidMaker.IUuidManager;
import com.cnfantasia.server.commbusi.plotproperty.service.IPlotpropertyCfgService;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.DateUtils;
import com.cnfantasia.server.common.utils.ParamUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.fixedFeeItemHasRoom.entity.FixedFeeItemHasRoom;
import com.cnfantasia.server.domainbase.fixedFeeItemHasRoom.service.IFixedFeeItemHasRoomBaseService;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.mrFeeItemFormula.entity.MrFeeItemFormula;
import com.cnfantasia.server.domainbase.mrPayBillRecord.dao.IMrPayBillRecordBaseDao;
import com.cnfantasia.server.domainbase.mrPayBillRecord.entity.MrPayBillRecord;
import com.cnfantasia.server.domainbase.payBill.entity.PayBill;
import com.cnfantasia.server.domainbase.payBill.service.PayBillBaseService;
import com.cnfantasia.server.domainbase.payBillMarkLog.dao.IPayBillMarkLogBaseDao;
import com.cnfantasia.server.domainbase.payBillMarkLog.entity.PayBillMarkLog;
import com.cnfantasia.server.domainbase.payBillSplit.entity.PayBillSplit;
import com.cnfantasia.server.domainbase.payBillSplit.service.PayBillSplitBaseService;
import com.cnfantasia.server.domainbase.payBillTimeCfg.dao.PayBillTimeCfgBaseDao;
import com.cnfantasia.server.domainbase.payBillTimeCfg.entity.PayBillTimeCfg;
import com.cnfantasia.server.domainbase.payBillType.entity.PayBillType;
import com.cnfantasia.server.domainbase.propertyFeeDetail.dao.PropertyFeeDetailBaseDao;
import com.cnfantasia.server.domainbase.propertyFeeDetail.entity.PropertyFeeDetail;
import com.cnfantasia.server.domainbase.propertyGbAd.entity.PropertyGbAd;
import com.cnfantasia.server.domainbase.realRoomHasMrLastRecord.dao.IRealRoomHasMrLastRecordBaseDao;
import com.cnfantasia.server.domainbase.realRoomHasMrLastRecord.entity.RealRoomHasMrLastRecord;
import com.cnfantasia.server.ms.payBill.constant.PayBillDict;
import com.cnfantasia.server.ms.payBill.dao.IPayBillDao;
import com.cnfantasia.server.ms.payBill.entity.BillMrDetail;
import com.cnfantasia.server.ms.payBill.entity.ExportBillDetailHead;
import com.cnfantasia.server.ms.payBill.entity.MarkReq;
import com.cnfantasia.server.ms.payBill.entity.MsgForPaybill;
import com.cnfantasia.server.ms.payBill.entity.PayBillEntity;
import com.cnfantasia.server.ms.payBill.entity.PayBillPeriod4Export;
import com.cnfantasia.server.ms.payBill.entity.PayBillTotalData;
import com.cnfantasia.server.ms.payBill.entity.PayBillWithFeeDetailEntity;


public class PayBillService extends PayBillBaseService implements IPayBillService {
	private Log logger = LogFactory.getLog(getClass());
	
	private IPayBillDao payBillDao;
	private IUuidManager uuidManager;
	private PropertyFeeDetailBaseDao propertyFeeDetailBaseDao;
	private IPayBillMarkLogBaseDao payBillMarkLogBaseDao;
	@Resource
	private IMrPayBillRecordBaseDao mrPayBillRecordBaseDao;
	@Resource
	private MeterReadingDao meterReadingDao;
    @Resource
    private IRealRoomHasMrLastRecordBaseDao realRoomHasMrLastRecordBaseDao;
	@Resource
	private IFixedFeeItemHasRoomBaseService fixedFeeItemHasRoomBaseService;
	@Resource
	private IEbuyOrderHasTPayBillBaseService ebuyOrderHasTPayBillBaseService;
	@Resource
	private IEbuyOrderBaseService ebuyOrderBaseService;

	
	public void setPropertyFeeDetailBaseDao(PropertyFeeDetailBaseDao propertyFeeDetailBaseDao) {
		this.propertyFeeDetailBaseDao = propertyFeeDetailBaseDao;
	}

	public void setPayBillDao(IPayBillDao payBillDao) {
		this.payBillDao = payBillDao;
	}

	public void setUuidManager(IUuidManager uuidManager) {
		this.uuidManager = uuidManager;
	}

	public void setPayBillMarkLogBaseDao(
			IPayBillMarkLogBaseDao payBillMarkLogBaseDao) {
		this.payBillMarkLogBaseDao = payBillMarkLogBaseDao;
	}

	@Override
	public String verifyImportPayBill(List<PayBillWithFeeDetailEntity> payBills) {
		return payBillDao.verifyImportPayBill(payBills);
	}
	
	@Override
	public void saveImportPayBill(List<PayBillWithFeeDetailEntity> payBills) {
		long currentTimeStart = System.currentTimeMillis();
	
		List<PropertyFeeDetail> propertyFeeDetailList = new ArrayList<PropertyFeeDetail>();
		List<PayBill> payBillList = new ArrayList<PayBill>();
		List<BigInteger> payBillIdList = uuidManager.getNextUuidBigInteger(SEQConstants.t_pay_bill, payBills.size());
		
		for (int i = 0; i < payBills.size(); i++) {
			PayBillWithFeeDetailEntity paybill = payBills.get(i);
			paybill.setId(payBillIdList.get(i));

			{//增加周期缴费后，增加的字段，需要填充默认值
				paybill.setBillTimeCfgId(new BigInteger("1"));
				paybill.setBillTypeId(PlotpropertyConstant.DEFAULT_PAYBILL_FEE_TYPEID);
				paybill.setBillTypeName("物业费");
				paybill.setIsPropFee(PlotpropertyDict.PayBillType_IsPropFee.YES);
				paybill.setBillMonthSize(1L);
				paybill.setPaytimeType(PlotpropertyDict.PayBillType_PaytimeType.MONTH);
			}
			
			paybill.setBankCollectionStatus(0);
			
			payBillList.add(paybill);
			for (int j = 0; j < paybill.getPropertyFeeDetails().size(); j++) {
				paybill.getPropertyFeeDetails().get(j).settPayBillFId(paybill.getId());
			}
			propertyFeeDetailList.addAll(paybill.getPropertyFeeDetails());
		}

		List<BigInteger> propertyFeeDetailIdList = uuidManager.getNextUuidBigInteger(SEQConstants.t_property_fee_detail, propertyFeeDetailList.size());
		for (int i = 0; i < propertyFeeDetailList.size(); i++) {
			propertyFeeDetailList.get(i).setId(propertyFeeDetailIdList.get(i));
		}
		//payBillDao.insertPayBillBatch(payBillList);
		//propertyFeeDetailBaseDao.insertPropertyFeeDetailBatch(propertyFeeDetailList);
		
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
		logger.info(" total use time is: " + (System.currentTimeMillis() - currentTimeStart));
	}

	@Override
	public List<PayBillWithFeeDetailEntity> getExportedPayBill(List<BigInteger> payBillIdList,boolean isRevenue, HashMap<String, Object> paramMap) {
		return payBillDao.getExportedPayBill(payBillIdList,isRevenue,paramMap);
	}

	@Override
	public List<PayBillWithFeeDetailEntity> getExportedPayBillFroReven(List<BigInteger> payBillIdList, boolean isRevenue, HashMap<String, Object> paramMap) {
		return payBillDao.getExportedPayBillFroReven(payBillIdList,isRevenue,paramMap);
	}

	@Override
	public List<PayBillWithFeeDetailEntity> getExportedPayBillWy(List<BigInteger> payBillIdList,boolean isRevenue) {
		return payBillDao.getExportedPayBillWy(payBillIdList,isRevenue);
	}

	@Override
	public PayBillWithFeeDetailEntity getPayBillForDetail(BigInteger id) {
		PayBill payBill = payBillDao.selectPayBillBySeqId(id);
		return payBillDao.getPayBillForDetail(payBill);
	}

	@Override
	public List<BillMrDetail> selectBillDetailForMr(BigInteger payBillId){
		return payBillDao.selectBillDetailForMr(payBillId);
	}
	
	@Override
	public List select_payBill_with_payRecord(String id) {
		return payBillDao.select_payBill_with_payRecord(id);
	}

	@Override
	@Transactional(propagation=Propagation.NESTED)
	public int markByManual(String id, BigInteger omsUserId, MarkReq markReq) {
		BigInteger payBillId = new BigInteger(id);
		PayBill payBill = payBillDao.selectPayBillBySeqId(payBillId);
		if(markReq!=null && org.apache.commons.lang.StringUtils.isNotBlank(markReq.getMobile())){
			String room = "";
			if(StringUtils.isNotBlank(markReq.getBuildingName())){
				room = markReq.getBuildingName();
				if(StringUtils.isNotBlank(markReq.getUnitName())){
					room = room + "-"+ markReq.getUnitName() + "-"+ markReq.getRoomName();
				} else {
					room = room + "-"+ markReq.getRoomName();
				}
			} else {
				if(StringUtils.isNotBlank(markReq.getUnitName())){
					room = markReq.getUnitName() + "-"+ markReq.getRoomName();
				} else {
					room = markReq.getRoomName();
				}
			}
			//<小区><门牌号><月份><账单名称><金额>
			String[] args = {markReq.getGbName(), room, markReq.getStartMonth(), markReq.getBillName(), markReq.getAmount()+"元"};
			ShortMsgUtil.sendMessage(markReq.getMobile(), "paybill.msg", args);
			
			PayBillMarkLog payBillMarkLog = new PayBillMarkLog();
			payBillMarkLog.setId(uuidManager.getNextUuidBigInteger(SEQConstants.t_pay_bill_mark_log));
			payBillMarkLog.setMobile(markReq.getMobile());
			payBillMarkLog.settPayBillFId(payBillId);
			payBillMarkLog.settRealRoomFId(payBill.gettRealRoomFId());
			
			payBillMarkLogBaseDao.insertPayBillMarkLog(payBillMarkLog);
		}
		//更新收费项起始时间
		updateAlterPeriodDataDetailAfterMarkSuccess(payBill);

		return payBillDao.markByManual(id, omsUserId);
	}

	/**
	 * 更新收费项的起始时间
	 * @param payBill
	 * @return
     */
	private int updateAlterPeriodDataDetailAfterMarkSuccess(PayBill payBill) {
		int i = 0;
		//查询需要更新的数据
		Map<String, Object> paraMap = new HashMap<>();
		paraMap.put("tPayBillFId", payBill.getId());
		paraMap.put("feeType", FeeTypeDict.Gu_Ding);
		List<PropertyFeeDetail> feeDetails = propertyFeeDetailBaseDao.selectPropertyFeeDetailByCondition(paraMap, false);
		//更新数据
		if(!DataUtil.isEmpty(feeDetails)) {
			List<FixedFeeItemHasRoom> fixedFeeItemHasRooms = new ArrayList<FixedFeeItemHasRoom>();
			for (PropertyFeeDetail feeDetail : feeDetails) {
				if(DataUtil.isEmpty(feeDetail.getItemHasRoomId())) {
					continue;
				}
				FixedFeeItemHasRoom fixedFeeItemHasRoom = new FixedFeeItemHasRoom();
				BigInteger itemHasRoomId = feeDetail.getItemHasRoomId();
				String billMonthEnd = payBill.getBillMonthEnd();
				Date date = DateUtils.addMonths(DateUtils.convertStrToDate(billMonthEnd), 1);
				String billMonth = DateUtils.convertDateToStr(date, "yyyy-MM-dd");
				fixedFeeItemHasRoom.setId(itemHasRoomId);
				fixedFeeItemHasRoom.setBillMonthStart(billMonth);
				fixedFeeItemHasRoom.setSys0UpdTime(DateUtils.getCurrentDate());
				fixedFeeItemHasRooms.add(fixedFeeItemHasRoom);
				logger.info("==end==itemHasRoomId==="+itemHasRoomId+"====billMonthEnd==="+billMonthEnd);
			}
			i = fixedFeeItemHasRoomBaseService.updateFixedFeeItemHasRoomBatch(fixedFeeItemHasRooms);
			logger.info("==updated success size==="+i);
		}
		return i;
	}


	@Override
	public int getPayBill_byUserId_forCount(Map<String, Object> paramMap) {
		return payBillDao.getPayBill_byUserId_forCount(paramMap);
	}

	@Override
	public int getPayBill_byUserId_forCount_Revenue(Map<String, Object> paramMap) {
		return payBillDao.getPayBill_byUserId_forCount_Revenue(paramMap);
	}

	@Override
	public List<PayBillEntity> getPayBillList_byUserId_forPage(Map<String, Object> paramMap) {
		return payBillDao.getPayBill_byUserId_forPage(paramMap);
	}

	@Override
	public List<PayBillEntity> getPayBillList_byUserId_forRevenuePage(Map<String, Object> paramMap) {
		return payBillDao.getPayBill_byUserId_forRevenuePage(paramMap);
	}

	@Override
	public PayBillTotalData getTotalData(Map<String, Object> paramMap) {
		return payBillDao.selectTotalData(paramMap);
	}
	
	public com.cnfantasia.server.api.plotproperty.entity.PayBillEntity selectPayBillEntity(Map<String, Object> paramMap) {
		return payBillDao.selectPayBillEntity(paramMap);
	}
	
	public List<GroupBuilding> getGroupBuildingByOmsId (Map<String, Object> paramMap) {
		return payBillDao.getGroupBuildingByOmsId(paramMap);
	}
	
	public String getPropertyCompanyName(Long groupBuildingId) {
		return payBillDao.getPropertyCompanyName(groupBuildingId);
	}

	@Override
	public String getConfigAdUrl(BigInteger gbId) {
		PropertyGbAd ads = payBillDao.selectConfigAdUrl(gbId);
		String icon = ads==null?null:ads.getIcon();
		if(ads==null||StringUtils.isEmpty(icon)){return null;}
		String resIconUrl = ApplicationContextBothUtil.getAbsolutePath(icon, SysParamKey.PROPERTY_GBAD_BASEURL, ads.getLastUpdTime());
		return resIconUrl;
	}

	@Resource
	PayBillTimeCfgBaseDao payBillTimeCfgBaseDao;
	@Resource
	IPlotpropertyCfgService plotpropertyCfgService;
	
	@Resource
	PayBillSplitBaseService payBillSplitBaseService;
	
	@Override
	@Transactional
	public String saveImportPayBillPeriod(List<PayBillWithFeeDetailEntity> payBills) {
		long currentTimeStart = System.currentTimeMillis();
		
		String resultInfo = payBillDao.verifyImportPayBillPeriod(payBills);
		if (payBills.size() == 0)//没有要导入的数据
			return resultInfo;
		
		String billTypeName = payBills.get(0).getBillTypeName().trim();
		BigInteger gbId = new BigInteger(payBills.get(0).getGroupBuildingId());
		PayBillType payBillType = plotpropertyCfgService.getPayBillTypeForImport(gbId, payBills.get(0).getBillTypeName(), billTypeName.equals("物业费"));
		if(payBillType==null){
			String resultMsg = payBills.get(0).getGroupBuildingName() + "中没有找到账单类型：" + payBills.get(0).getBillTypeName() ;
			resultMsg = resultMsg + "，请管理员在正式小区参数配置中进行配置";
			return resultMsg;
		}
		
		PayBillTimeCfg payBillTimeCfg = new PayBillTimeCfg();
		payBillTimeCfg.setBillTypeId(payBillType.getId());
		payBillTimeCfg.setGbId(payBillType.getGbId());
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
		}else {
			payBillTimeCfg.setId(payBillTimeCfgList.get(0).getId());
		}

		List<PropertyFeeDetail> propertyFeeDetailList = new ArrayList<PropertyFeeDetail>();
		List<PayBill> payBillList = new ArrayList<PayBill>();
		List<BigInteger> payBillIdList = uuidManager.getNextUuidBigInteger(SEQConstants.t_pay_bill, payBills.size());
		List<PayBillSplit> payBillSplitList = new ArrayList<PayBillSplit>();
		
		
		for (int i = 0; i < payBills.size(); i++) {
			PayBillWithFeeDetailEntity paybill = payBills.get(i);
			paybill.setId(payBillIdList.get(i));
//			paybill.setBillTimeCfgId(payBillTimeCfg.getId());
//			paybill.setPaytimeType(payBillType.getPaytimeType());
//			paybill.setIsPropFee(PlotpropertyDict.PayBillType_IsPropFee.NO);
			paybill.setIsPropFee(payBillType.getIsPropFee());//syl-upd-2015-12-23 19:01:01
			
			paybill.setBillTypeId(payBillType.getId());
			paybill.setBillTypeName(payBillType.getName());
			paybill.setBankCollectionStatus(0);
			
			payBillList.add(paybill);
			for (int j = 0; j < paybill.getPropertyFeeDetails().size(); j++) {
				paybill.getPropertyFeeDetails().get(j).settPayBillFId(paybill.getId());
				if (payBillType.getIsPropFee().intValue() == PlotpropertyDict.PayBillType_IsPropFee.YES.intValue()
						&& paybill.getPropertyFeeDetails().get(j).getName().equals("管理费")) {
					paybill.getPropertyFeeDetails().get(j).setType(PayBillDict.PropertyFeeDetailDict.FeeType_Property);
				}
			}
			
			propertyFeeDetailList.addAll(paybill.getPropertyFeeDetails());
			
			payBillSplitList.addAll(generatePayBillSplit(paybill));
		}

		List<BigInteger> propertyFeeDetailIdList = uuidManager.getNextUuidBigInteger(SEQConstants.t_property_fee_detail, propertyFeeDetailList.size());
		for (int i = 0; i < propertyFeeDetailList.size(); i++) {
			propertyFeeDetailList.get(i).setId(propertyFeeDetailIdList.get(i));
		}
//		payBillDao.insertPayBillBatch(payBillList);
//		propertyFeeDetailBaseDao.insertPropertyFeeDetailBatch(propertyFeeDetailList);

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
		logger.info(" total use time is: " + (System.currentTimeMillis()-currentTimeStart));

		return resultInfo;
	}

	/**
	 * 产生拆分单
	 * @param paybill 账单
	 * @return
	 */
	private List<PayBillSplit> generatePayBillSplit(PayBillWithFeeDetailEntity paybill) {
		return plotpropertyCfgService.generatePayBillSplit(paybill.getId());
	}

	@Override
	public List<PayBillPeriod4Export> getExportedPayBillPeriod(List<BigInteger> payBillIdList) {
		return payBillDao.getExportedPayBillPeriod(payBillIdList);
	}
	
	protected List<PayBillEntity> fetchExportPayBillList(HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		HashMap<String, Object> paramMap = (HashMap<String, Object>) request.getSession().getAttribute("payBillListQueryParamMap");
		boolean isRevenue = ParamUtils.getBoolean(request, "isRevenue", false);//syl-add
		if(request.getAttribute("isBatch")!=null){
			isRevenue = (Boolean) request.getAttribute("isRevenue");
		}
		{
			paramMap.put("isRevenue", isRevenue);//syl-add
			request.setAttribute("isRevenue", isRevenue);
		}
		//int resultSize = payBillService.getPayBill_byUserId_forCount(paramMap);
		paramMap.remove(PageModel.BEGIN);
		paramMap.remove(PageModel.LENGTH);
		List<PayBillEntity> searchRestList = getPayBillList_byUserId_forPage(paramMap);
		return searchRestList;
	}

	protected List<PayBillEntity> fetchExportPayBillListRevenue(HttpServletRequest request) {
		@SuppressWarnings("unchecked")
		HashMap<String, Object> paramMap = (HashMap<String, Object>) request.getSession().getAttribute("payBillListQueryParamMap");
		boolean isRevenue = ParamUtils.getBoolean(request, "isRevenue", false);//syl-add
		if(request.getAttribute("isBatch")!=null){
			isRevenue = (Boolean) request.getAttribute("isRevenue");
		}
		{
			paramMap.put("isRevenue", isRevenue);//syl-add
			request.setAttribute("isRevenue", isRevenue);
		}
		//int resultSize = payBillService.getPayBill_byUserId_forCount(paramMap);
		paramMap.remove(PageModel.BEGIN);
		paramMap.remove(PageModel.LENGTH);
		List<PayBillEntity> searchRestList = getPayBillList_byUserId_forRevenuePage(paramMap);
		return searchRestList;
	}

	public List<PayBillWithFeeDetailEntity> fetchExportList(HttpServletRequest request) {
		Boolean isRevenue = ParamUtils.getBoolean(request, "isRevenue", false);//syl-add
		if(request.getAttribute("isBatch")!=null){
			isRevenue = (Boolean) request.getAttribute("isRevenue");
		}
		
		List<PayBillEntity> searchRestList = fetchExportPayBillList(request);
		List<BigInteger> payBillIdList = new ArrayList<BigInteger>();
		for (int i = 0; i < searchRestList.size(); i++) {
			payBillIdList.add(searchRestList.get(i).getId());
		}
		HashMap<String, Object> paramMap = (HashMap<String, Object>) request.getSession().getAttribute("payBillListQueryParamMap");
		List<PayBillWithFeeDetailEntity> payBillImportedEntyList = getExportedPayBill(payBillIdList,isRevenue,paramMap);
		return payBillImportedEntyList;
	}

	/**
	 * 模板方式导出对账单信息
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	public HSSFWorkbook createRport(List<PayBillWithFeeDetailEntity> payBillImportedEntyList){
		if (payBillImportedEntyList.size() > 0) {
			String fileServiceBasePath = CnfantasiaCommbusiUtil.getFileServiceBasePath();
			String filePath = fileServiceBasePath + ("docs/jfq_revenue_pay_bill_template.xls");
			FileInputStream fin = null;
			try {
				fin = new FileInputStream(new File(filePath));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
			HSSFWorkbook wb = null;
			try {
				wb = new HSSFWorkbook(fin);
			} catch (IOException e) {
				e.printStackTrace();
			}
			HSSFSheet sheet = wb.getSheetAt(0);
			sheet.setForceFormulaRecalculation(true);
			//excel边框样式
			HSSFCellStyle style = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
			style.setBorderTop(HSSFCellStyle.BORDER_THIN);
			style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style.setBorderRight(HSSFCellStyle.BORDER_THIN);
			style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			//excel人民币格式
			DecimalFormat df = new DecimalFormat("0.00");

			//计算表头信息
			Map<String, Integer> headerIndex = new HashMap<String, Integer>();
			boolean isHasLastUnPaid = false;
			int g = 0;
			for (PayBillWithFeeDetailEntity payBillWithFeeDetailEntity : payBillImportedEntyList) {
				for (PropertyFeeDetail propertyFeeDetail : payBillWithFeeDetailEntity.getPropertyFeeDetails()) {
					if(DataUtil.isEmpty(propertyFeeDetail.getTotalPrice()) || (!DataUtil.isEmpty(propertyFeeDetail.getTotalPrice()) && propertyFeeDetail.getTotalPrice() <= 0)) continue;
					String key = propertyFeeDetail.getName()+"@"+propertyFeeDetail.getFeeType();
					if(!headerIndex.containsKey(key)) {
						headerIndex.put(key, g);
						g ++;
					}
				}
				if(!DataUtil.isEmpty(payBillWithFeeDetailEntity.getLastUnpaid()) && payBillWithFeeDetailEntity.getLastUnpaid() > 0) {
					isHasLastUnPaid = true;
				}
			}
			//如果存在往月欠费 新增往月欠费 v509抄表往月欠费存储在t_property_fee_detail中，不在存储在t_pay_bil中了
			/*if(isHasLastUnPaid) {
				headerIndex.put("往月欠费", g);
			}*/

			//表头样式
			HSSFFont font  = wb.createFont();
			font.setFontHeightInPoints((short) 10);//字号
			font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗
			font.setFontName("微软雅黑");
			HSSFCellStyle styleDetailName = wb.createCellStyle();
			styleDetailName.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			styleDetailName.setBorderTop(HSSFCellStyle.BORDER_THIN);
			styleDetailName.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			styleDetailName.setBorderRight(HSSFCellStyle.BORDER_THIN);
			styleDetailName.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			styleDetailName.setVerticalAlignment(HSSFCellStyle.ALIGN_CENTER);
			styleDetailName.setFillForegroundColor(IndexedColors.SEA_GREEN.getIndex());
			styleDetailName.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			styleDetailName.setFont(font);
			HSSFCellStyle styleDetailTitle = wb.createCellStyle();
			styleDetailTitle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			styleDetailTitle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			styleDetailTitle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			styleDetailTitle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			styleDetailTitle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			styleDetailTitle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			styleDetailTitle.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
			styleDetailTitle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			styleDetailTitle.setFont(font);
			//增加表头信息
			HSSFCell cell = sheet.getRow(0).createCell(13);
			cell.setCellValue("费用清单");
			cell.setCellStyle(styleDetailName);

			int detailStartIndex = 13;
			//合并“费用清单”
			sheet.addMergedRegion(new CellRangeAddress(0, 0, detailStartIndex, detailStartIndex + headerIndex.size() - 1));//index从零开始(13,size-1)
			try {
				setMergedRegionBorder(new CellRangeAddress(0, 0, detailStartIndex, detailStartIndex + headerIndex.size() - 1), sheet, wb);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//创建费用明细--名称 ---表头
			for(Map.Entry<String, Integer> entry : headerIndex.entrySet()) {
				String key = entry.getKey();
				int feeDetailIndex = entry.getValue() + detailStartIndex;
				HSSFCell cell1 = sheet.getRow(1).createCell(feeDetailIndex);
				cell1.setCellStyle(styleDetailTitle);
				if(key.indexOf("@") != -1) {
					cell1.setCellValue(key.substring(0, key.indexOf("@")));
					//设置当前列的宽度  setColumnWidth(列index, 宽度值)
					sheet.setColumnWidth((short) feeDetailIndex, (short) key.substring(0, key.indexOf("@")).getBytes().length*256);
				} else {
					cell1.setCellValue(key);
					//设置当前列的宽度  setColumnWidth(列index, 宽度值)
					sheet.setColumnWidth((short) feeDetailIndex, key.getBytes().length*256);
				}
			}

			double totalSuccAmt = 0.00;//用户实缴合计
			double totalAllowancePrice = 0.00;//物业宝抵扣合计
			double totalDeDuAmount = 0.00;//解放区补贴合计
			double totalAmt = 0.00;//费用总合计

			int dataFromRowIndex = 2; //从第3行开始是数据行
			int index = 2;//标记到哪一行了
			for (int i = 0, k = 0; k < payBillImportedEntyList.size(); k++) {
				PayBillWithFeeDetailEntity pb = payBillImportedEntyList.get(k);
				HSSFRow row = sheet.getRow(k + dataFromRowIndex);
				if (row == null) {
					row = sheet.createRow(k + dataFromRowIndex);
				}
				i = 0;
				index ++;
				
				if(pb.getMonth() != null && !"".equals(pb.getMonth())) {//1月度缴费 2周期缴费
					fillCellData(row, i++, pb.getMonth(), style);//月份
				} else {
					String billMonthStr = StringUtils.EMPTY;
					if(StringUtils.isNotBlank(pb.getBillMonthStart()) || StringUtils.isNotBlank(pb.getBillMonthEnd())) {
						billMonthStr = pb.getBillMonthStart().trim() +"/"+ pb.getBillMonthEnd().trim();
					}
					fillCellData(row, i++, billMonthStr, style);//月份
				}
				
				fillCellData(row, i++, pb.getGroupBuildingName(), style);//小区
				fillCellData(row, i++, pb.getBuildingName(), style);//楼栋
				fillCellData(row, i++, pb.getRealRoomUnitName(), style);//单元	
				fillCellData(row, i++, pb.getRealRoomNum(), style);//房号	
				fillCellData(row, i++, pb.getPropertyProprietorName(), style);//业主姓名

				double succPayAmt = 0.00;//用户实际缴费
				double allowancePrice = 0.00;//物业宝抵扣金额
				allowancePrice = pb.getAmountFinance() / 100.0;
				double deDuAmount = 0.00;//计算解放区补贴金额
				if(pb.getIsPay() == 1 && pb.getFinanceStatus() == 1) {
					deDuAmount = (pb.getAmount() - pb.getDeduPrice() - pb.getSuccPayAmount()) / 100.0;
				}else if(pb.getIsPay() == 1 && pb.getFinanceStatus() == 0) {
					deDuAmount = (pb.getAmount() - pb.getSuccPayAmount()) / 100.0;
				} else if ("".equals(pb.getFinanceStatus()) && !"".equals(pb.getSuccPayAmount())) {
					deDuAmount = (pb.getAmount() - pb.getSuccPayAmount()) / 100.0;
				}
				if (pb.getSuccPayAmount() != null) {
					if(pb.getPaymentWay() == PayBillDict.PaymentWay.Property_Card_Deduction){
						//若为物业代扣卡，补贴金额取cardSubsidyAmt
						succPayAmt = pb.getCardRealPayAmt();
						deDuAmount = pb.getCardSubsidyAmt();
					}else{
						succPayAmt = pb.getSuccPayAmount() / 100.0;
					}
				} else {
					succPayAmt = 0.00;
					allowancePrice = 0.00;
					deDuAmount = 0.00;
				}

				fillCellData(row, i++, df.format(succPayAmt), style);
				fillCellData(row, i++, df.format(allowancePrice), style);
				fillCellData(row, i++, df.format(deDuAmount), style);

				//缴费合计
				double totalAmoutByRow = 0.00;
				if(pb.getPaymentWay() == PayBillDict.PaymentWay.Property_Card_Deduction && pb.getFinanceStatus() != null && pb.getFinanceStatus() != 1){
					fillCellData(row, i++, df.format(pb.getCardRealPayAmt()+pb.getCardSubsidyAmt()), style);
					totalAmoutByRow = pb.getCardRealPayAmt()+pb.getCardSubsidyAmt();
				}else{
					fillCellData(row, i++, df.format(pb.getAmount() / 100.0), style);
					totalAmoutByRow = pb.getAmount() / 100.0;
				}

				//费用各项合计
				totalSuccAmt += succPayAmt;
				totalAllowancePrice += allowancePrice;
				totalDeDuAmount += deDuAmount;
				totalAmt += totalAmoutByRow;
				
				if (pb.getIsPay() != null && pb.getIsPay() == 1) {
					fillCellData(row, i++, pb.getSys0UpdTime(), style);
				} else {
					fillCellData(row, i++, "", style);
				}

				if(pb.getPaymentWay() == PayBillDict.PaymentWay.Property_Card_Deduction){
					fillCellData(row, i++, pb.getCardOrderNo(), style);
				}else{
					fillCellData(row, i++, pb.getOrderNo(), style);
				}
				int payMethod = pb.getPayMethod();
				String orderNo = pb.getOrderNo();
				if(pb.getPayMethod() == 0) {
					if(pb.getPaymentWay() == PayBillDict.PaymentWay.Property_Card_Deduction){
						fillCellData(row, i++, "代扣卡续费", style);
					} else if(pb.getFinanceStatus() == 1) {
						fillCellData(row, i++, "物业宝", style);
					} else {
						fillCellData(row, i++, getPayMethodString(pb.getPayMethod()), style);
					}
				} else {
					fillCellData(row, i++, getPayMethodString(pb.getPayMethod()), style);
				}

				List<Integer> containIndex = new ArrayList<Integer>();
				for (int j = 0; j < pb.getPropertyFeeDetails().size(); j++) {
					PropertyFeeDetail detail = pb.getPropertyFeeDetails().get(j);
					if(!DataUtil.isEmpty(detail.getTotalPrice()) && detail.getTotalPrice() > 0) {
						//填充明细信息
						Integer integer = headerIndex.get(detail.getName() + "@" + detail.getFeeType());
						fillCellData(row, (i + integer), df.format(detail.getTotalPrice() / 100.0), style);
						containIndex.add(integer);
					}
				}
				for(Map.Entry<String, Integer> entry : headerIndex.entrySet()) {
					HSSFCell cell1 = row.getCell(i + entry.getValue());
					if(DataUtil.isEmpty(cell1)) {
						fillCellData(row, i + entry.getValue(), "", style);
					}
				}
				//填充往月欠费 v509抄表往月欠费存储在t_property_fee_detail中，不在存储在t_pay_bil中了
				/*if(!DataUtil.isEmpty(pb.getLastUnpaid()) && pb.getLastUnpaid() > 0) {
					fillCellData(row, row.getLastCellNum() - 1, pb.getLastUnpaid() == 0 ? "" : df.format(pb.getLastUnpaid() / 100.0), style);
				}*/
			}

			HSSFRow row = sheet.createRow(index);
			CellRangeAddress cra=new CellRangeAddress(index, index, 0, 5);
			sheet.addMergedRegion(cra);
			try {
				setMergedRegionBorder(cra, sheet, wb);
			} catch (Exception e) {
				e.printStackTrace();
			}
			RegionUtil.setBorderBottom(1, cra, sheet, wb);
			RegionUtil.setBorderRight(1, cra, sheet, wb);
			RegionUtil.setBorderTop(1, cra, sheet, wb);

			fillCellData(row, 0, "合计", style);
			fillCellData(row, 6, df.format(totalSuccAmt), style);
			fillCellData(row, 7, df.format(totalAllowancePrice), style);
			fillCellData(row, 8, df.format(totalDeDuAmount), style);
			fillCellData(row, 9, df.format(totalAmt), style);
			fillCellData(row, 10, "", style);
			fillCellData(row, 11, "", style);
			fillCellData(row, 12, "", style);

			// 求和
			double[] totals = new double[headerIndex.size()];
			for(int k = 2; k <= sheet.getLastRowNum() - 1; k ++){
				HSSFRow row1 = sheet.getRow(k);
				for(int j = 0; j < totals.length; j ++){
					if(DataUtil.isEmpty(row1) || DataUtil.isEmpty(row1.getCell(13 + j))) continue;
					String priceStr = row1.getCell(13 + j).getStringCellValue();
					if(DataUtil.isEmpty(priceStr)) continue;
					totals[j] += Double.parseDouble(priceStr.trim());
				}
			}
			for (int i = 0; i < totals.length; i++) {
				fillCellData(row, 13 + i, df.format(totals[i]), style);
			}
			return wb;
		}
		
		return null;
	
	}

	public static void main(String[] args) {
        System.out.println(DateUtils.convertDateToStr(DateUtils.convertStrToDate("2018-04-01 00:00:00"), "yyyy年MM月"));
	}

	/**
	 * 设置合并单元格区域的边框
	 * @param cellRangeAddress
	 * @param sheet
	 * @param wb
	 * @throws Exception
     */
	private void setMergedRegionBorder(CellRangeAddress cellRangeAddress, HSSFSheet sheet, HSSFWorkbook wb) throws Exception {
		RegionUtil.setBorderLeft(1, cellRangeAddress, sheet, wb);
		RegionUtil.setBorderBottom(1, cellRangeAddress, sheet, wb);
		RegionUtil.setBorderRight(1, cellRangeAddress, sheet, wb);
		RegionUtil.setBorderTop(1, cellRangeAddress, sheet, wb);

	}

	private void fillCellData(HSSFRow row,int index,String value,HSSFCellStyle style){
		HSSFCell cell = row.createCell(index);
		cell.setCellStyle(style);
		cell.setCellValue(value);
	}
	
	private void fillCellData(HSSFRow row,int index,Integer value,HSSFCellStyle style){
		HSSFCell cell = row.createCell(index);
		cell.setCellStyle(style);
		cell.setCellValue(value);
	}
	
	/**
	 * 返回支付方式 <br>
	 * 支付方式=={"1":"微信支付","2":"支付宝","3":"银联支付","4":"纯粮票支付","5":"纯积分支付","6":
	 * "微信轻应用支付"}
	 * 
	 * @param pb
	 * @return
	 */
	protected String getPayMethodString(int payMethod) {
		String payMethodString = "";
		switch (payMethod) {
		case 1:
			payMethodString = "微信支付";
			break;
		case 2:
			payMethodString = "支付宝";
			break;
		case 3:
			payMethodString = "银联支付";
			break;
		case 4:
			payMethodString = "纯粮票支付";
			break;
		case 5:
			payMethodString = "纯积分支付";
			break;
		case 6:
			payMethodString = "微信轻应用支付";
			break;
		case 7:
			payMethodString = "纯优惠券支付";
			break;
		case 8:
			payMethodString = "纯折扣支付";
			break;
		case 9:
			payMethodString = "双乾支付";
			break;
		case 0:
			payMethodString = "0";
			break;
		default:
			break;
		}
		return payMethodString;
	}

	@Override
	public List<PayBillWithFeeDetailEntity> fetchExportListWy(HttpServletRequest request) {
		Boolean isRevenue = ParamUtils.getBoolean(request, "isRevenue", false);//syl-add
		if(request.getAttribute("isBatch")!=null){
			isRevenue = (Boolean) request.getAttribute("isRevenue");
		}
		
		List<PayBillEntity> searchRestList = fetchExportPayBillListRevenue(request);
		List<BigInteger> payBillIdList = new ArrayList<BigInteger>();
		for (int i = 0; i < searchRestList.size(); i++) {
			payBillIdList.add(searchRestList.get(i).getId());
		}

		List<PayBillWithFeeDetailEntity> payBillImportedEntyList = getExportedPayBillWy(payBillIdList,isRevenue);
		return payBillImportedEntyList;
	}
	
	@Override
	public boolean isHasPayBillByGbId(String gbId, String billCycleId) {
		int count = payBillDao.getPayBillByGbIdForCount(BigInteger.valueOf(Long.parseLong(gbId.trim())), BigInteger.valueOf(Long.parseLong(billCycleId)));
		return count > 0;
	}

	@Override
	public int delPayBill(String id, BigInteger delUserId) {
		BigInteger payBillId = BigInteger.valueOf(Long.parseLong(id));
		return payBillDao.delPayBill(payBillId, delUserId);
	}

	/**
	 * 更新选择周期基础数据信息
	 * @param orderId
	 * @param billCycleId
	 */
	private int updateAlterPeriodDataDetailAfterPaySuccess(BigInteger payBillId) {
		int i = 0;
		//查询需要更新的数据
		List<Map<String, Object>> list = payBillDao.getNeedFixedUpdate(payBillId);
		//更新数据
		if(!DataUtil.isEmpty(list)) {
			logger.info("==need to update size==="+list.size());
			List<FixedFeeItemHasRoom> fixedFeeItemHasRooms = new ArrayList<FixedFeeItemHasRoom>();
			for (Map<String, Object> stringObjectMap : list) {
				FixedFeeItemHasRoom fixedFeeItemHasRoom = new FixedFeeItemHasRoom();
				Object itemHasRoomId1 = stringObjectMap.get("itemHasRoomId");
				Object billMonthEnd = stringObjectMap.get("billMonthEnd");
				logger.info("==start==itemHasRoomId==="+itemHasRoomId1+"====billMonthEnd==="+billMonthEnd);
				if(DataUtil.isEmpty(itemHasRoomId1)) continue;
				BigInteger itemHasRoomId = BigInteger.valueOf(Long.parseLong(itemHasRoomId1.toString()));
				String billMonth = DateUtils.convertDateToStr(DateUtils.convertStrToDate(billMonthEnd.toString()), "yyyy-MM-dd");
				fixedFeeItemHasRoom.setId(itemHasRoomId);
				fixedFeeItemHasRoom.setBillMonthStart(billMonth);
				fixedFeeItemHasRoom.setSys0UpdTime(DateUtils.getCurrentDate());
				fixedFeeItemHasRooms.add(fixedFeeItemHasRoom);
				logger.info("==end==itemHasRoomId==="+itemHasRoomId1+"====billMonthEnd==="+billMonthEnd);
			}
			i = fixedFeeItemHasRoomBaseService.updateFixedFeeItemHasRoomBatch(fixedFeeItemHasRooms);
			logger.info("==updated success size==="+i);
		}
		return i;
	}

	@Override
	public List<Map<String, Object>> getGroupbuildingAvgAmount(HashMap<String, Object> paraMap) {
		return payBillDao.getGroupbuildingAvgAmount(paraMap);
	}

	@Override
	public int updateBillCycle(BigInteger gbId, BigInteger payBillTypeId, int operateStatus) {
		return payBillDao.updateBillCycle(gbId, payBillTypeId, operateStatus);
	}

	@Override
	public List<BigInteger> getGroupBuildingBillCycleByTypeId(BigInteger gbId, BigInteger payBillTypeId) {
		return payBillDao.getGroupBuildingBillCycleByTypeId(gbId, payBillTypeId);
	}

	@Override
	public int updatePayBillByCycleIds(List<BigInteger> billCycleIds) {
		return payBillDao.updatePayBillByCycleIds(billCycleIds);
	}

	@Override
	public List<BigInteger> getCompayIdUserManageByOmsUser(BigInteger omsUserId) {
		if (omsUserId == null) {
			return null;
		}
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("omsUserId", omsUserId);
		return payBillDao.getCompayIdUserManageByOmsUser(param);
	}

	@Override
	public List<MsgForPaybill> getMsgForPaybillEnd() {
		return payBillDao.getMsgForPaybillEnd();
	}

	@Override
	public List<MsgForPaybill> getMsgForPaybillStartUnRegister() {
		return payBillDao.getMsgForPaybillStartUnRegister();
	}

	@Override
	public List<MsgForPaybill> getMsgForPaybillBeforeBank() {
		return payBillDao.getMsgForPaybillBeforeBank();
	}

	@Override
	public List<MsgForPaybill> getMsgForPaybillAfterBank() {
		return payBillDao.getMsgForPaybillAfterBank();
	}
	
	@Override
	public List<MsgForPaybill> getMsgForPaybillAfterBankSuccess() {
		return payBillDao.getMsgForPaybillAfterBankSuccess();
	}

	@Override
	public List<MsgForPaybill> getMsgForPaybillStart() {
		return payBillDao.getMsgForPaybillStart();
	}

	@Override
	public List<ExportBillDetailHead> selectFeeDetailNameByGbId(BigInteger gbId, Integer feeType, List<BigInteger> billIds) {
		return payBillDao.selectFeeDetailNameByGbId(gbId, feeType, billIds);
	}

	@Override
	@Transactional
	public void savePayBillAmount(List<List> fixedList, List<List> mrList, List<List> tmpList, BigInteger payBillId, BigInteger userId) throws Exception{
		PayBillWithFeeDetailEntity payBillImportedEntity = getPayBillForDetail(payBillId);
		List<PropertyFeeDetail> feeDetails = new ArrayList<>();
		List<MrPayBillRecord> mrPayBillRecords = new ArrayList<MrPayBillRecord>();
        List<RealRoomHasMrLastRecord> mrLastRecordList = new ArrayList<RealRoomHasMrLastRecord>();
		Long payBillEditAmount = 0L;

		for (PropertyFeeDetail propertyFeeDetail : payBillImportedEntity.getPropertyFeeDetails()) {
			if(propertyFeeDetail.getFeeType().equals(FeeTypeDict.Gu_Ding) && !DataUtil.isEmpty(fixedList)) {
				for (List fixeds : fixedList) {
					if(!fixeds.get(0).equals(Integer.valueOf(propertyFeeDetail.getId().toString()))) {
						continue;
					}

					Double editAmount = amountDiv100(fixeds.get(1).toString(), 2).doubleValue();
					propertyFeeDetail.setTotalPrice(editAmount);
					propertyFeeDetail.setSys0UpdTime(DateUtils.getCurrentDate());
					propertyFeeDetail.setSys0UpdUser(userId);
					feeDetails.add(propertyFeeDetail);
					BigDecimal detailAmt = BigDecimal.valueOf(editAmount).multiply(new BigDecimal(propertyFeeDetail.getBillMonthSize())).setScale(0, BigDecimal.ROUND_HALF_UP);
					payBillEditAmount += detailAmt.longValue();
				}
			}

			if(propertyFeeDetail.getFeeType().equals(FeeTypeDict.Lin_Shi) && !DataUtil.isEmpty(tmpList)) {
				for (List tmps : tmpList) {
					if(!tmps.get(0).equals(Integer.valueOf(propertyFeeDetail.getId().toString()))) {
						continue;
					}

					Double editAmount = amountDiv100(tmps.get(1).toString(), 2).doubleValue();
					propertyFeeDetail.setTotalPrice(editAmount);
					//propertyFeeDetail.setTotalAmount(editAmount);
					propertyFeeDetail.setPriceUnitValue(amountDiv100(tmps.get(2).toString()).longValue());
					propertyFeeDetail.setSignalPrice(amountDiv100(tmps.get(3).toString()).doubleValue());
					propertyFeeDetail.setSys0UpdTime(DateUtils.getCurrentDate());
					propertyFeeDetail.setSys0UpdUser(userId);
					feeDetails.add(propertyFeeDetail);
					payBillEditAmount += editAmount.longValue();
				}
			}

			if(propertyFeeDetail.getFeeType().equals(FeeTypeDict.Chao_Biao) && !DataUtil.isEmpty(mrList)) {
				for (List mrs : mrList) {
					if (!mrs.get(0).equals(Integer.valueOf(propertyFeeDetail.getId().toString()))) {
						continue;
					}

					//仅仅单独保存金额和读数，读数修改不按照计算公式计算--wangchong 2017-11-01 17:08:00
					Double editAmount = amountDiv100(mrs.get(1).toString(), 2).doubleValue();
					payBillEditAmount += editAmount.longValue();
					Double startValue = Double.parseDouble(mrs.get(2).toString());
					Double endValue = Double.parseDouble(mrs.get(3).toString());
					Map<String, Object> paraMap = new HashMap<String, Object>();
					paraMap.put("tPropertyFeeDetailFId", propertyFeeDetail.getId());
					List<MrPayBillRecord> mrPayBillRecordsList = mrPayBillRecordBaseDao.selectMrPayBillRecordByCondition(paraMap, false);
					MrPayBillRecord mrPayBillRecord = mrPayBillRecordsList.get(0);
					if (DataUtil.isEmpty(mrPayBillRecord)) {
						continue;
					}

					//金额及读数没有改变的不做任何处理
					if (propertyFeeDetail.getTotalPrice().equals(editAmount) && mrPayBillRecord.getStartValue().equals(startValue) &&
							mrPayBillRecord.getEndValue().equals(endValue)) {
						continue;
					}

					//金额修改
					propertyFeeDetail.setTotalPrice(editAmount);
					propertyFeeDetail.setSys0UpdTime(DateUtils.getCurrentDate());
					propertyFeeDetail.setSys0UpdUser(userId);
					mrPayBillRecord.setStartValue(startValue);
					mrPayBillRecord.setEndValue(endValue);
					mrPayBillRecords.add(mrPayBillRecord);
					feeDetails.add(propertyFeeDetail);

					Map<String, Object> paramMap = new HashMap<String, Object>();
					paramMap.put("tRealRoomFId", payBillImportedEntity.gettRealRoomFId());
					paramMap.put("tMrFeeItemFId", mrPayBillRecord.gettMrFeeItemFId());
					List<RealRoomHasMrLastRecord> mrLastRecords = realRoomHasMrLastRecordBaseDao.selectRealRoomHasMrLastRecordByCondition(paramMap, false);
					if (mrLastRecords != null && mrLastRecords.size() > 0) {
						RealRoomHasMrLastRecord mrLastRecord = mrLastRecords.get(0);
						mrLastRecord.setLastRecord(endValue);
						mrLastRecord.setLastUpdTime(DateUtils.getCurrentDate());
						mrLastRecordList.add(mrLastRecord);
					}
				}
			}
		}

		//抄表需要增加欠费信息
		Long lastUnpaid = (payBillImportedEntity.getLastUnpaid() == null ? 0L : payBillImportedEntity.getLastUnpaid());
		payBillEditAmount += lastUnpaid;

		//获取账单的滞纳金金额
		Map<String, Object> paraMap01 = new HashMap<>();
		paraMap01.put("tPayBillFId", payBillId);
		paraMap01.put("feeType", FeeTypeDict.LATEFEE);
		List<PropertyFeeDetail> feeDetails1 = propertyFeeDetailBaseDao.selectPropertyFeeDetailByCondition(paraMap01, false);
		for (PropertyFeeDetail propertyFeeDetail : feeDetails1) {
			payBillEditAmount += propertyFeeDetail.getTotalPrice().longValue();
		}

		//更新临时费用的合计金额
        List<Integer> detailIndx = new ArrayList<Integer>();
        BigDecimal totalAmount = BigDecimal.ZERO;
        for (int i = 0; i < feeDetails.size(); i++) {
            PropertyFeeDetail detail = feeDetails.get(i);
            if(detail.getFeeType() != null && detail.getFeeType().equals(FeeTypeDict.Lin_Shi)) {
                detailIndx.add(i);
                totalAmount = totalAmount.add(new BigDecimal(detail.getTotalPrice()));
            }
        }
        //替换totalAmount
        for (Integer integer : detailIndx) {
            feeDetails.get(integer).setTotalAmount(totalAmount.doubleValue());
        }

		//更新费用明细
		if(!DataUtil.isEmpty(feeDetails)) {
			propertyFeeDetailBaseDao.updatePropertyFeeDetailBatch(feeDetails);
		}
		//更新账单金额
		if(payBillImportedEntity.getAmount().compareTo(payBillEditAmount) != 0) {
			PayBill payBill = payBillDao.selectPayBillBySeqId(payBillImportedEntity.getId());
			payBill.setAmount(payBillEditAmount);//需要增加一个往月欠费金额
			payBillDao.updatePayBill(payBill);
		}
		//更新抄表读数
		if(!DataUtil.isEmpty(mrPayBillRecords)) {
			mrPayBillRecordBaseDao.updateMrPayBillRecordBatch(mrPayBillRecords);
		}
        if(!DataUtil.isEmpty(mrLastRecordList)) {
            realRoomHasMrLastRecordBaseDao.updateRealRoomHasMrLastRecordBatch(mrLastRecordList);
        }

		//删除已经生成的订单
		Map<String, Object> paraMap = new HashMap<>();
		paraMap.put("tPayBillFId", payBillId);
		List<EbuyOrderHasTPayBill> ebuyOrderHasTPayBillByCondition = ebuyOrderHasTPayBillBaseService.getEbuyOrderHasTPayBillByCondition(paraMap);
		for (EbuyOrderHasTPayBill ebuyOrderHasTPayBill : ebuyOrderHasTPayBillByCondition) {
			ebuyOrderHasTPayBillBaseService.deleteEbuyOrderHasTPayBillLogic(ebuyOrderHasTPayBill.getId());
			ebuyOrderBaseService.deleteEbuyOrderLogic(ebuyOrderHasTPayBill.gettEbuyOrderFId());
		}
	}

	@Override
	public List<Map<String, List>> getUnpaidListById(BigInteger payBillId) {
		List<UnPaidPayBillEntity> unpaidListById = payBillDao.getUnpaidListById(payBillId);
		List<Map<String, List>> list = new ArrayList<Map<String, List>>();

		List<String> feeTitle = new ArrayList<String>();
		for (UnPaidPayBillEntity unPaidPayBillEntity : unpaidListById) {
			for (PropertyFeeDetail propertyFeeDetail : unPaidPayBillEntity.getPropertyFeeDetailList()) {
				if(!feeTitle.contains(propertyFeeDetail.getName()) && !FeeTypeDict.LATEFEE.equals(propertyFeeDetail.getFeeType())) {
					feeTitle.add(propertyFeeDetail.getName());
				}

			}
		}
		Map<String, List> mapTitle = new HashMap<String, List>();
		mapTitle.put("title", feeTitle);
		list.add(0, mapTitle);

		for (UnPaidPayBillEntity unPaidPayBillEntity : unpaidListById) {
			Map<String, List> map = new HashMap<String, List>();
			/**顺序必须是：费用的项金额 滞纳金  费用合计  账单id =>方便前端显示*/
			List<Object> amounts = new ArrayList<Object>();
            LinkedHashMap<String, BigDecimal> map1 = new LinkedHashMap<String, BigDecimal>();
			BigDecimal totalAmt = BigDecimal.ZERO;
            for (int i = 0; i < feeTitle.size(); i++) {
                boolean isExit = false;//明细项是否存在于当前账单对应的明细项，不存需要补位
                for (PropertyFeeDetail propertyFeeDetail : unPaidPayBillEntity.getPropertyFeeDetailList()) {
                    if(!feeTitle.get(i).equals(propertyFeeDetail.getName())) continue;
                    //扣除部分抵扣的金额
                    Double needPay = propertyFeeDetail.getTotalPrice() - (propertyFeeDetail.getAllowancePrice() == null ? 0 : propertyFeeDetail.getAllowancePrice().doubleValue());
                    BigDecimal amt = new BigDecimal(needPay).divide(new BigDecimal(100), 2, RoundingMode.HALF_UP);
                    totalAmt = totalAmt.add(amt);//在此计算 防止计算多了
                    if(map1.containsKey(propertyFeeDetail.getName())) {
                        amt = amt.add(map1.get(propertyFeeDetail.getName()));
                    }
                    map1.put(propertyFeeDetail.getName(), amt);
                    isExit = true;

                }
                //补位
                if(!isExit) {
                    map1.put(feeTitle.get(i), BigDecimal.ZERO);
                }
            }

            for(String key : map1.keySet()) {
                amounts.add(map1.get(key));//按顺序增加
            }

			amounts.add(totalAmt);
			amounts.add(unPaidPayBillEntity.getId());

			String billMonthStart = unPaidPayBillEntity.getBillMonthStart();
			String billMonthend = unPaidPayBillEntity.getBillMonthEnd();
			if(org.apache.commons.lang.StringUtils.isNotBlank(billMonthStart) && org.apache.commons.lang.StringUtils.isNotBlank(billMonthend)){
				billMonthStart = DateUtils.convertDateToStr(DateUtils.convertStrToDate(billMonthStart), "yyyy年MM月");
				billMonthend = DateUtils.convertDateToStr(DateUtils.convertStrToDate(billMonthend), "yyyy年MM月");
				map.put(billMonthStart + "~" + billMonthend, amounts);
				list.add(map);
			}
		}

		return list;
	}

	@Override
	public BigDecimal getUnpaidAmtById(BigInteger id) {
		Long amt = payBillDao.getUnpaidAmtById(id);
		return BigDecimalUtil.div100(amt, 2, BigDecimal.ROUND_HALF_UP);
	}

	@Override
	public int unpaidSettle(String id, BigInteger omsUserId) {
		return payBillDao.markByManual(id, omsUserId);
	}

	private static BigDecimal amountDiv100(String obj) {
		BigDecimal dbAmount = new BigDecimal(obj);
		BigDecimal chengshu = new BigDecimal("100");
		BigDecimal resout = dbAmount.multiply(chengshu);
		return resout;
	}

    private static BigDecimal amountDiv100(String obj, int scal) {
        BigDecimal dbAmount = new BigDecimal(obj);
        dbAmount = dbAmount.setScale(scal, RoundingMode.HALF_UP);
        BigDecimal chengshu = new BigDecimal("100");
        BigDecimal resout = dbAmount.multiply(chengshu);
        return resout;
    }

	/**
	 * 获取“抄水表”合计值、单价
	 *
	 * @param mfiWithFormula
	 * @param startValue
	 * @param endValue
	 * @return
	 */
	private Map<String, Double> getPriceForMr(MrFeeItemWithFormula mfiWithFormula, double startValue, double endValue){
		Map<String, Double> map = new HashMap<String, Double>();
		double totalPrice = 0;
		double signalPrice = 0;
		if(mfiWithFormula.getMfifList().size() == 1){//单一计价规则
			double d = endValue - startValue;
			signalPrice = mfiWithFormula.getMfifList().get(0).getUnitValue();
			totalPrice = d*signalPrice;
			map.put("signalPrice", signalPrice);// 只有“单一计价规则”需要存储
		}else{//按阶梯规则来计算
			double d = endValue - startValue;//用量
			for(int k = 0; k < mfiWithFormula.getMfifList().size(); k++){
				MrFeeItemFormula mrFeeItemFormula = mfiWithFormula.getMfifList().get(k);
				signalPrice = mrFeeItemFormula.getUnitValue();
				if(d>mrFeeItemFormula.getEndValue()){
					totalPrice += (mrFeeItemFormula.getEndValue()-mrFeeItemFormula.getStartValue()) * signalPrice;
				} else {
					totalPrice += (d - mrFeeItemFormula.getStartValue())* signalPrice;
					break;
				}
			}
		}
		map.put("totalPrice", totalPrice);
		return map;
	}

	@Override
	public Map<String, BigDecimal> getPayBillsAmounts(Map<String, Object> paramMap) {
		return payBillDao.getPayBillsAmounts(paramMap);
	}

	@Override
	public boolean isBankCollectionByPayBillId(BigInteger payBillId) {
		int status = payBillDao.isBankCollectionByPayBillId(payBillId);
		return status != 0;
	}
}
