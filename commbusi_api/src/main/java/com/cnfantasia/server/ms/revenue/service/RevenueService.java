package com.cnfantasia.server.ms.revenue.service;

import com.cnfantasia.server.api.common.constant.Lock;
import com.cnfantasia.server.api.common.dao.ICommonLockDao;
import com.cnfantasia.server.api.livingPay.service.LivingPayService;
import com.cnfantasia.server.api.pub.utils.ApplicationContextBothUtil;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.api.revenueApplyPush.bean.ResultInfo;
import com.cnfantasia.server.api.revenueApplyPush.service.RevenueApplyPushService;
import com.cnfantasia.server.api.revenueApplyPush.util.EASPushUtils;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.utils.BigDecimalUtil;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.utils.ExcelUtil;
import com.cnfantasia.server.common.utils.PayUtils;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.groupBuilding.service.IGroupBuildingBaseService;
import com.cnfantasia.server.domainbase.propertyCompany.entity.PropertyCompany;
import com.cnfantasia.server.domainbase.revenueApply.dao.IRevenueApplyBaseDao;
import com.cnfantasia.server.domainbase.revenueApply.entity.RevenueApply;
import com.cnfantasia.server.domainbase.revenueConfig.dao.IRevenueConfigBaseDao;
import com.cnfantasia.server.domainbase.revenueConfig.entity.RevenueConfig;
import com.cnfantasia.server.domainbase.revenueSignalAmount.dao.IRevenueSignalAmountBaseDao;
import com.cnfantasia.server.domainbase.revenueSignalAmount.entity.RevenueSignalAmount;
import com.cnfantasia.server.domainbase.revenueSignalAmountEbuy.dao.IRevenueSignalAmountEbuyBaseDao;
import com.cnfantasia.server.domainbase.revenueSignalAmountEbuy.entity.RevenueSignalAmountEbuy;
import com.cnfantasia.server.ms.ebuyProductSettle.entity.EbuyProductSettleDetailAdminDto;
import com.cnfantasia.server.ms.ebuyProductSettle.service.IEbuyProductSettleService;
import com.cnfantasia.server.ms.payBill.entity.PayBillWithFeeDetailEntity;
import com.cnfantasia.server.ms.payBill.service.IPayBillService;
import com.cnfantasia.server.ms.revenue.constant.RevenueConstant;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict.UserRole;
import com.cnfantasia.server.ms.revenue.dao.IRevenueDao;
import com.cnfantasia.server.ms.revenue.entity.AreaTime;
import com.cnfantasia.server.ms.revenue.entity.BeginEndDate;
import com.cnfantasia.server.ms.revenue.entity.CarDeduRevenueSignalAmount;
import com.cnfantasia.server.ms.revenue.entity.CarRevenueSignalAmount;
import com.cnfantasia.server.ms.revenue.entity.EbuyDelivDiscount;
import com.cnfantasia.server.ms.revenue.entity.EbuyOrderRevenue;
import com.cnfantasia.server.ms.revenue.entity.EbuyRevenueProd;
import com.cnfantasia.server.ms.revenue.entity.EbuyRevenueSignalAmount;
import com.cnfantasia.server.ms.revenue.entity.EbuyRevenueTotal;
import com.cnfantasia.server.ms.revenue.entity.FinanceDeduRevenueSignalAmount;
import com.cnfantasia.server.ms.revenue.entity.FinanceRevenueSignalAmount;
import com.cnfantasia.server.ms.revenue.entity.LeftRightTime;
import com.cnfantasia.server.ms.revenue.entity.MinMaxDate;
import com.cnfantasia.server.ms.revenue.entity.MinMaxDate.Location;
import com.cnfantasia.server.ms.revenue.entity.PayBillForRevenue;
import com.cnfantasia.server.ms.revenue.entity.PropCompanyWithRevenue;
import com.cnfantasia.server.ms.revenue.entity.RevenueAmountPrevEntity;
import com.cnfantasia.server.ms.revenue.entity.RevenueApply4Export;
import com.cnfantasia.server.ms.revenue.entity.RevenueApplyEntity;
import com.cnfantasia.server.ms.revenue.entity.RevenueApplyParam;
import com.cnfantasia.server.ms.revenue.entity.RevenueBatchParam;
import com.cnfantasia.server.ms.revenue.entity.RevenueConfigDetail;
import com.cnfantasia.server.ms.revenue.entity.RevenueConfigForList;
import com.cnfantasia.server.ms.revenue.entity.RevenueMoneyShow;
import com.cnfantasia.server.ms.revenue.entity.RevenueRole;
import com.cnfantasia.server.ms.revenue.entity.RevenueRoleMultiId;
import com.cnfantasia.server.ms.revenue.entity.RevenueSettlement;
import com.cnfantasia.server.ms.revenue.entity.UserWithPropCompany;
import com.cnfantasia.server.ms.revenue.task.ISyn2RevSignal;
import com.cnfantasia.server.ms.revenue.util.RevenueTkNoGenerator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class RevenueService implements IRevenueService {
	
	private IRevenueDao revenueDao;
	
	private IRevenueConfigBaseDao revenueConfigBaseDao;
	
	private IRevenueSignalAmountBaseDao revenueSignalAmountBaseDao;
	
	private IRevenueSignalAmountEbuyBaseDao revenueSignalAmountEbuyBaseDao;
	
	private ICommonLockDao commonLockDao;
	
	@Resource
	private RevenueCarService revenueCarService;
	@Resource
	private RevenueFinanceDeduService revenueFinanceDeduService;
	@Resource
	private RevenueFinanceService revenueFinanceService;
	@Resource
	private RevenueDredgeService revenueDredgeService;
	@Resource
	private RevenueCarDeduService revenueCarDeduService;
	@Resource
	private IPayBillService payBillService;
	@Resource
	private IGroupBuildingBaseService groupBuildingBaseService;
	@Resource
	private RevenueApplyPushService revenueApplyPushService;
	@Resource
	private IEbuyProductSettleService ebuyProductSettleService;

	@Resource
	private LivingPayService livingPayService;
	
	public void setRevenueConfigBaseDao(IRevenueConfigBaseDao revenueConfigBaseDao) {
		this.revenueConfigBaseDao = revenueConfigBaseDao;
	}
	
	public void setRevenueDao(IRevenueDao revenueDao) {
		this.revenueDao = revenueDao;
	}
	
	private IRevenueApplyBaseDao revenueApplyBaseDao;
	public void setRevenueApplyBaseDao(IRevenueApplyBaseDao revenueApplyBaseDao) {
		this.revenueApplyBaseDao = revenueApplyBaseDao;
	}
	
	private ISyn2RevSignal<PayBillForRevenue,BigDecimal> propertyRealPayAmout2RevSignal4BillMonth;
	public void setPropertyRealPayAmout2RevSignal4BillMonth(
			ISyn2RevSignal<PayBillForRevenue, BigDecimal> propertyRealPayAmout2RevSignal4BillMonth) {
		this.propertyRealPayAmout2RevSignal4BillMonth = propertyRealPayAmout2RevSignal4BillMonth;
	}

	@Override
	public List<Map<String, Object>> select_revenueList(Map<String, Object> paramMap) {
		return revenueDao.select_revenueList(paramMap);
	}

	@Override
	public int select_revenueList_forCount(Map<String, Object> paramMap) {
		return revenueDao.select_revenueList_forCount(paramMap);
	}

	@Override
	public List<PropCompanyWithRevenue> getPropCompanyWithRevenueList(BigInteger companyId,String companyName, PageModel pageModel) {
		return revenueDao.selectPropCompanyWithRevenueList(companyId,companyName,pageModel);
	}
	
	@Override
	public List<RevenueConfigForList> getRevenueConfigList(BigInteger companyId, String companyName, Integer projectType, PageModel pageModel) {
		return revenueDao.selectRevenueConfigList(companyId, companyName, projectType, pageModel);
	}
	
	@Override
	public UserWithPropCompany getUserWithPropCompanyByUserId(BigInteger omsUserId) {
		return revenueDao.selectUserWithPropCompanyByUserId(omsUserId);
	}

	@Override
	public List<PropertyCompany> getPropCompanyList(String companyName) {
		return revenueDao.selectPropCompanyList(companyName);
	}
	
	/**
	 * 校验起止时间
	 * @param startDate
	 * @param endDate
	 * @return 是否使用开始时间 true使用开始时间,false使用截止时间,null都使用
	 */
	public Boolean validateStartEndDate(MinMaxDate minMaxDate,String startDate, String endDate){
		boolean startDateEmpty = StringUtils.isEmpty(startDate);
		boolean endDateEmpty = StringUtils.isEmpty(endDate);
		if(startDateEmpty&&endDateEmpty){
			throw new BusinessRuntimeException("RevenueService.validateStartEndDate.bothEmpty");
		}else{
			if(minMaxDate!=null){
				if(!startDateEmpty&&!endDateEmpty){
					throw new BusinessRuntimeException("RevenueService.validateStartEndDate.canOnlyOne");
				}else{
					if(!startDateEmpty){
						if(DateUtil.distance(startDate, DateUtil.formatDay.get(), minMaxDate.getMinStartDate(), DateUtil.formatDay.get())>=0){
							throw new BusinessRuntimeException("RevenueService.validateStartEndDate.startDate.tooBig");
						}
						return true;//startDate<minMaxDate.getMinStartDate()
					}else{
						if(DateUtil.distance(endDate, DateUtil.formatDay.get(), minMaxDate.getMaxEndDate(), DateUtil.formatDay.get())<=0){
							throw new BusinessRuntimeException("RevenueService.validateStartEndDate.endDate.tooSmall");
						}
						return false;//endDate > minMaxDate.getMaxEndDate()
					}
				}
			}else{
				if(!startDateEmpty&&!endDateEmpty){
					//开始不能大于结束
					try {
						Date oldTime = DateUtil.formatDay.get().parse(startDate);
						Date today = DateUtil.formatDay.get().parse(endDate);
						if(oldTime.getTime()>today.getTime()){
							throw new BusinessRuntimeException("RevenueService.validateStartEndDate.compareError");
						}
					} catch (Exception e) {
						throw new BusinessRuntimeException("RevenueService.validateStartEndDate.formateError");
					}
					return null;
				}else{
					if(startDateEmpty){
						throw new BusinessRuntimeException("RevenueService.validateStartEndDate.startDateEmpty");
					}else{
						throw new BusinessRuntimeException("RevenueService.validateStartEndDate.endDateEmpty");
					}
				}
			}
		}
	}
	
	@Override
	public void addRevenueConfig(BigInteger companyId, Integer projectType, Integer ruleType, Double totalValue, Double companyValue, Double agentValue, Double recommenderValue,
			Double platformValue, Double repairValue, String startDate, String endDate,Integer activeStatus) {
		MinMaxDate minMaxDate = revenueDao.selectMinMaxDate(companyId, projectType);
		Boolean userStartBool = validateStartEndDate(minMaxDate, startDate, endDate);//时间校验
		
		BigInteger addId = ApplicationContextBothUtil.getUuidManager().getNextUuidBigInteger(SEQConstants.t_revenue_config);
		RevenueConfig toAddRC = new RevenueConfig();
		toAddRC.setActiveStatus(activeStatus);
		toAddRC.setAgentValue(agentValue);
		toAddRC.setRecommenderValue(recommenderValue);
		toAddRC.setCompanyId(companyId);
		toAddRC.setCompanyValue(companyValue);
		toAddRC.setId(addId);
		toAddRC.setPlatformValue(platformValue);
		toAddRC.setRepairValue(repairValue);
		toAddRC.setProjectType(projectType);
		toAddRC.setRuleType(ruleType);
		{
			if(userStartBool==null){
				toAddRC.setStartDate(startDate);
				toAddRC.setEndDate(endDate);
			}else if(userStartBool){
				toAddRC.setStartDate(startDate);
				toAddRC.setEndDate(DateUtil.getNextDate(minMaxDate.getMinStartDate(), DateUtil.formatDay.get(), Calendar.DATE, -1));//减一天
			}else{
				toAddRC.setStartDate(DateUtil.getNextDate(minMaxDate.getMaxEndDate(), DateUtil.formatDay.get(), Calendar.DATE, 1));//加一天
				toAddRC.setEndDate(endDate);
			}
		}
		toAddRC.setTotalValue(totalValue);
		toAddRC.setUsedRecLasttime(RevenueConstant.DEFAULT_USED_REC_LASTTIME);
		Integer resCount = revenueConfigBaseDao.insertRevenueConfig(toAddRC);
		if(resCount==null||resCount<=0){
			throw new BusinessRuntimeException("RevenueService.addRevenueConfig.failed");
		}
	}

	@Override
	public void delRevenueConfig(BigInteger dataId,LeftRightTime areaInfo) {
		Location usedLocat = areaInfo.getUsedLocat();
		if(Location.MIDDLE.compareTo(usedLocat)==0){
			throw new BusinessRuntimeException("RevenueService.delRevenueConfig.used");
		}
		if(!areaInfo.isFirst()&&!areaInfo.isLast()){
			throw new BusinessRuntimeException("RevenueService.delRevenueConfig.between");
		}
		Integer resCount = revenueConfigBaseDao.deleteRevenueConfigLogic(dataId);
		if(resCount==null||resCount<=0){
			throw new BusinessRuntimeException("RevenueService.delRevenueConfig.failed");
		}
	}
	
	@Override
	public void updRevenueConfig(BigInteger rcId,Integer ruleType, Double totalValue, Double companyValue, Double agentValue, Double recommenderValue,
			Double platformValue, Double repairValue, String startDate, String endDate,Integer activeStatus,LeftRightTime areaInfo,RevenueConfigDetail detail) {
		//校验日期 校验是否有权限 ok
		//修改当前,同时新增新的配置
		RevenueConfig baseInfoForUpd = new RevenueConfig();
		{
			baseInfoForUpd.setActiveStatus(activeStatus);
			baseInfoForUpd.setAgentValue(agentValue);
			baseInfoForUpd.setCompanyValue(companyValue);
			baseInfoForUpd.setRecommenderValue(recommenderValue);
			baseInfoForUpd.setPlatformValue(platformValue);
			baseInfoForUpd.setRepairValue(repairValue);
			baseInfoForUpd.setRuleType(ruleType);
			baseInfoForUpd.setTotalValue(totalValue);
			baseInfoForUpd.setId(rcId);
//			baseInfoForUpd.setCompanyId(companyId);
//			baseInfoForUpd.setProjectType(projectType);
			/*{
				baseInfoForUpd.setStartDate(startDate);
				baseInfoForUpd.setEndDate(endDate);
			}*/
		}
		RevenueConfig baseInfoForAdd = new RevenueConfig(detail);
		{
			baseInfoForAdd.setActiveStatus(activeStatus);
			baseInfoForAdd.setAgentValue(agentValue);
			baseInfoForAdd.setCompanyValue(companyValue);
			baseInfoForAdd.setRecommenderValue(recommenderValue);
			baseInfoForAdd.setPlatformValue(platformValue);
			baseInfoForAdd.setRepairValue(repairValue);
			baseInfoForAdd.setRuleType(ruleType);
			baseInfoForAdd.setTotalValue(totalValue);
//			baseInfoForAdd.setCompanyId(companyId);
//			baseInfoForAdd.setProjectType(projectType);
			/*{
				baseInfoForAdd.setId(rcId);
				baseInfoForAdd.setStartDate(startDate);
				baseInfoForAdd.setEndDate(endDate);
			}*/
		}
		
		RevenueConfig toUpdRc = null;
		RevenueConfig toAddRc = null;
		{
			boolean isFirst = areaInfo.isFirst();
			boolean isLast = areaInfo.isLast();
			Location usedLocat = areaInfo.getUsedLocat();
			if(isFirst&&isLast){
				switch (usedLocat) {
				case LEFT:
					toUpdRc = new RevenueConfig(baseInfoForUpd);
					toUpdRc.setStartDate(startDate);
					toUpdRc.setEndDate(endDate);
//					leftTime = new AreaTime(detail.getUsedRecLasttime(), "");
//					rightTime = new AreaTime(detail.getUsedRecLasttime(), "");//无限制
					break;
				case MIDDLE:
					toUpdRc = new RevenueConfig();
					toUpdRc.setId(rcId);
					toUpdRc.setEndDate(detail.getUsedRecLasttime());
					//需要新增的
					String toAddStartDate = DateUtil.getNextDate(detail.getUsedRecLasttime(), DateUtil.formatDay.get(), Calendar.DATE, 1);
					if(DateUtil.distance(endDate, DateUtil.formatDay.get(), toAddStartDate, DateUtil.formatDay.get())>=0){
						toAddRc = new RevenueConfig(baseInfoForAdd);
						toAddRc.setStartDate(toAddStartDate);
						toAddRc.setEndDate(endDate);
					}
//					leftTime = new AreaTime(detail.getUsedRecLasttime(), detail.getUsedRecLasttime());
//					rightTime = new AreaTime(detail.getUsedRecLasttime(), "");//无限制
					break;
				case RIGHT:configUpdNotEditAble();break;
				}
			}else if(isFirst&&!isLast){//第一个
				switch (usedLocat) {
				case LEFT:
					toUpdRc = new RevenueConfig(baseInfoForUpd);
					toUpdRc.setStartDate(startDate);
//					leftTime = new AreaTime(detail.getUsedRecLasttime(), detail.getEndDate());
//					rightTime = new AreaTime(detail.getEndDate(),detail.getEndDate());
					break;
				case MIDDLE:
					toUpdRc = new RevenueConfig();
					toUpdRc.setId(rcId);
					toUpdRc.setEndDate(DateUtil.getNextDate(startDate, DateUtil.formatDay.get(), Calendar.DATE, -1));
					//需要新增的
					String toAddStartDate = startDate;
					if(DateUtil.distance(detail.getEndDate(), DateUtil.formatDay.get(), toAddStartDate, DateUtil.formatDay.get())>=0){
						toAddRc = new RevenueConfig(baseInfoForAdd);
						toAddRc.setStartDate(toAddStartDate);
						toAddRc.setEndDate(detail.getEndDate());
					}
//					leftTime = new AreaTime(detail.getUsedRecLasttime(), detail.getEndDate());
//					rightTime = new AreaTime(detail.getEndDate(),detail.getEndDate());
					break;
				case RIGHT:configUpdNotEditAble();break;
				}
			}else if(!isFirst&&isLast){//最后一个
				switch (usedLocat) {
				case LEFT:
					toUpdRc = new RevenueConfig(baseInfoForUpd);
					toUpdRc.setEndDate(endDate);
//					leftTime = new AreaTime(detail.getStartDate(), detail.getStartDate());
//					rightTime = new AreaTime(detail.getStartDate(), "");
					break;
				case MIDDLE:
					toUpdRc = new RevenueConfig();
					toUpdRc.setId(rcId);
					toUpdRc.setEndDate(DateUtil.getNextDate(startDate, DateUtil.formatDay.get(), Calendar.DATE, -1));
					//需要新增的
					String toAddStartDate = startDate;
					if(DateUtil.distance(endDate, DateUtil.formatDay.get(), toAddStartDate, DateUtil.formatDay.get())>=0){
						toAddRc = new RevenueConfig(baseInfoForAdd);
						toAddRc.setStartDate(toAddStartDate);
						toAddRc.setEndDate(endDate);
					}
//					leftTime = new AreaTime(detail.getUsedRecLasttime(), detail.getEndDate());
//					leftTime = new AreaTime(detail.getUsedRecLasttime(), "");
					break;
				case RIGHT:configUpdNotEditAble();break;
				}
			}else{//中间的
				switch (usedLocat) {
				case LEFT:
					toUpdRc = new RevenueConfig(baseInfoForUpd);
//					leftTime = new AreaTime(detail.getStartDate(), detail.getStartDate());
//					rightTime = new AreaTime(detail.getEndDate(), detail.getEndDate());
					break;
				case MIDDLE:
					toUpdRc = new RevenueConfig();
					toUpdRc.setId(rcId);
					toUpdRc.setEndDate(DateUtil.getNextDate(startDate, DateUtil.formatDay.get(), Calendar.DATE, -1));//detail.getUsedRecLasttime()
					//需要新增的
					String toAddStartDate = startDate;
					if(DateUtil.distance(detail.getEndDate(), DateUtil.formatDay.get(), toAddStartDate, DateUtil.formatDay.get())>=0){
						toAddRc = new RevenueConfig(baseInfoForAdd);
						toAddRc.setStartDate(toAddStartDate);
						toAddRc.setEndDate(detail.getEndDate());
					}
//					leftTime = new AreaTime(detail.getUsedRecLasttime(), detail.getEndDate());
//					rightTime = new AreaTime(detail.getEndDate(), detail.getEndDate());
					break;
				case RIGHT:configUpdNotEditAble();break;
				}
			}
		}
		if(toUpdRc!=null){
			Integer resCount = revenueConfigBaseDao.updateRevenueConfig(toUpdRc);
			if(resCount==null||resCount<=0){
				throw new BusinessRuntimeException("RevenueService.updRevenueConfig.failed");
			}
		}
		if(toAddRc!=null){
			BigInteger addId = ApplicationContextBothUtil.getUuidManager().getNextUuidBigInteger(SEQConstants.t_revenue_config);
			toAddRc.setId(addId);
			toAddRc.setUsedRecLasttime(RevenueConstant.DEFAULT_USED_REC_LASTTIME);
			Integer resCount = revenueConfigBaseDao.insertRevenueConfig(toAddRc);
			if(resCount==null||resCount<=0){
				throw new BusinessRuntimeException("RevenueService.updRevenueConfig.add.failed");
			}
		}
	}

	@Override
	public RevenueConfigDetail getRevenueConfigDetail(BigInteger dataId) {
		RevenueConfigDetail detail = revenueDao.selectRevenueConfigDetail(dataId);
		if(detail!=null){
			MinMaxDate minMaxDate = revenueDao.selectMinMaxDate(detail.getCompanyId(), detail.getProjectType());
			detail.setMinMaxDate(minMaxDate);
		}
		return detail;
	}

	@Override
	public MinMaxDate getMinMaxDate(BigInteger companyId, Integer projectType) {
		return revenueDao.selectMinMaxDate(companyId, projectType);
	}
	
	@Override
	public LeftRightTime getLeftRightTime(BigInteger detailCompanyId,Integer detailProjectType,RevenueConfigDetail detail){
		AreaTime leftTime = null;
		AreaTime rightTime = null;
		if(detail==null){
			throw new BusinessRuntimeException("revenueConfig.getLeftRightTime.detailNull");
		}
		long last2Start = DateUtil.distance(detail.getUsedRecLasttime(),DateUtil.formatDay.get(), detail.getStartDate(),DateUtil.formatSecond.get());
		long last2End = DateUtil.distance(detail.getUsedRecLasttime(),DateUtil.formatDay.get(), detail.getEndDate(),DateUtil.formatSecond.get());
		Location usedLocat = (last2Start<0)?Location.LEFT:(last2End>0?Location.RIGHT:Location.MIDDLE);
		String usedRecLasttimeAddOne = DateUtil.getNextDate(detail.getUsedRecLasttime(), DateUtil.formatDay.get(), Calendar.DATE, 1);
		if(Location.RIGHT.compareTo(usedLocat)==0){configUpdNotEditAble();}//以下只需处理LEFT 和MIDDLE
		if(Location.MIDDLE.compareTo(usedLocat)==0&&DateUtil.distance(detail.getStartDate(), DateUtil.formatDay.get(), detail.getEndDate(), DateUtil.formatDay.get())==0){//1.在中间且起止时间相同
			configUpdNotEditAble();
		}
		if(DateUtil.distance(detail.getEndDate(), DateUtil.formatDay.get(), usedRecLasttimeAddOne, DateUtil.formatDay.get())<0){//2.可编辑的开始时间大于截止时间,原则上经过1判断不会出现2的情况
			configUpdNotEditAble();
		}
		MinMaxDate minMaxDate = this.getMinMaxDate(detailCompanyId, detailProjectType);
		boolean isFirst = MinMaxDate.isFirst(minMaxDate, detail);
		boolean isLast = MinMaxDate.isLast(minMaxDate, detail);
		if(isFirst&&isLast){
			switch (usedLocat) {
				case LEFT:
					leftTime = new AreaTime(usedRecLasttimeAddOne, "");
					rightTime = new AreaTime(usedRecLasttimeAddOne, "");//无限制
					break;
				case MIDDLE:
					leftTime = new AreaTime(usedRecLasttimeAddOne, usedRecLasttimeAddOne);
					rightTime = new AreaTime(usedRecLasttimeAddOne, "");//无限制
					break;
				case RIGHT:configUpdNotEditAble();break;
			}
		}else if(isFirst&&!isLast){//第一个
			switch (usedLocat) {
			case LEFT:
			case MIDDLE:
				leftTime = new AreaTime(usedRecLasttimeAddOne, detail.getEndDate());
				rightTime = new AreaTime(detail.getEndDate(),detail.getEndDate());
				break;
			case RIGHT:configUpdNotEditAble();break;
			}
		}else if(!isFirst&&isLast){//最后一个
			switch (usedLocat) {
			case LEFT:
				leftTime = new AreaTime(detail.getStartDate(), detail.getStartDate());
				rightTime = new AreaTime(detail.getStartDate(), "");
				break;
			case MIDDLE:
				leftTime = new AreaTime(usedRecLasttimeAddOne, detail.getEndDate());
				rightTime = new AreaTime(usedRecLasttimeAddOne, "");
				break;
			case RIGHT:configUpdNotEditAble();break;
			}
		}else{//中间的
			switch (usedLocat) {
			case LEFT:
				leftTime = new AreaTime(detail.getStartDate(), detail.getStartDate());
				rightTime = new AreaTime(detail.getEndDate(), detail.getEndDate());
				break;
			case MIDDLE:
				leftTime = new AreaTime(usedRecLasttimeAddOne, detail.getEndDate());
				rightTime = new AreaTime(detail.getEndDate(), detail.getEndDate());
				break;
			case RIGHT:configUpdNotEditAble();break;
			}
		}
		return new LeftRightTime(leftTime, rightTime, usedLocat, isFirst, isLast);
	}
	
	private void configUpdNotEditAble(){
		throw new BusinessRuntimeException("revenueConfig.configUpd.hasUsed");
	}

	@Override
	public List<RevenueAmountPrevEntity> getRevenueAmountPrevList(BigInteger omsUserId,UserRole expectedRole, String searchName, PageModel pageModel) {
		List<RevenueAmountPrevEntity> resList = new ArrayList<RevenueAmountPrevEntity>();
/*		if(expectedRole == UserRole.PCManagement) {
			pcList = getRevenueAmountManagementPrevList(omsUserId, searchName, pageModel);
		} else {
			RevenueRole revenueRole = createRevenueRoleExcep(omsUserId, expectedRole);
			pcList =  revenueDao.selectRevenueAmountPrevList(revenueRole, searchName, pageModel);
		}*/
		RevenueRole revenueRole = createRevenueRoleExcep(omsUserId, expectedRole);
		resList =  revenueDao.selectRevenueAmountPrevList(revenueRole, searchName, pageModel);
		return resList;
	}
	
	
	@Override
	public Set<UserRole> getOmsUserRoleListByUserId(BigInteger omsUserId) {
		if(omsUserId==null){return null;}
		Set<UserRole> resList = new HashSet<UserRole>();
		List<Integer> codeList = revenueDao.selectOmsUserRoleListByUserId(omsUserId);
		if(codeList!=null&&codeList.size()>0){
			for(Integer code:codeList){
				UserRole tmpRole = UserRole.createUserRole(code);
				resList.add(tmpRole);
			}
		}
		return resList;
	}

	@Override
	public List<RevenueSettlement> select_revenueSettleList(BigInteger omsUserId,UserRole expectedRole,Map<String, Object> paramMap) {
		List<RevenueSettlement> pcList = new ArrayList<RevenueSettlement>();
		if(expectedRole == UserRole.PCManagement) {
			RevenueRole revenueRole = createRevenueRoleExcep(omsUserId, expectedRole);
			pcList =  revenueDao.select_revenueSettleList(revenueRole,paramMap);
//			paramMap.put("omsUserId", omsUserId);
//			paramMap.put("miniRoleType",RevenueDict.MiniRoleType.PCManagement);
//			paramMap.put("miniRoleId", revenueRole.getRoleId());
//			paramMap.put("queryType", 3);
//			pcList = revenueDao.select_revenueSettleManagementList(paramMap);
		} else {
			RevenueRole revenueRole = createRevenueRoleExcep(omsUserId, expectedRole);
			pcList =  revenueDao.select_revenueSettleList(revenueRole,paramMap);
		}
		return pcList;
	}
	
	@Override
	public List<RevenueApply> getRevenueApplyListFinance(Map<String, Object> paramMap,PageModel pageModel) {
		return revenueDao.selectRevenueApplyListFinance(paramMap,pageModel);
	}
	
	public List<RevenueApply> getRevenueApplyListFinance(Map<String, Object> paramMap) {
		return revenueDao.selectRevenueApplyListFinance(paramMap);
	}
	
	@Override
	public Double getTotalAmountAll(Map<String, Object> paramMap) {
		return revenueDao.selectTotalAmountAllFinance(paramMap);
	}
	
	@Override
	public int select_revenueSettleList_forCount(Map<String, Object> paramMap) {
		return revenueDao.select_revenueSettleList_forCount(paramMap);
	}
	
	//楼下店收益中心start
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public List<EbuyOrderRevenue> updateAndGetEbuyRevenueList(Map<String, Object> paramMap) {
		commonLockDao.getLock(Lock.EBUY_SYN_TASK_SUPPLY_EXEC_TASK);
		revenueDao.updateForEbuyRevenue(paramMap);
		List<EbuyOrderRevenue> ebuyOrderRevenueList =  revenueDao.selectEbuyRevenueList(paramMap);
		ebuyOrderRevenueList = getEbuyDelivDiscountList(ebuyOrderRevenueList);
		
		paramMap.clear();
		paramMap.put("revenueStatusTo",  9);
		paramMap.put("revenueStatus",  1);
		revenueDao.updateDeliveryRevenueStatus(paramMap);
		return ebuyOrderRevenueList;
	}
	
	public Integer updateDeliveryRevenueStatus(Map<String, Object> paramMap) {
		return revenueDao.updateDeliveryRevenueStatus(paramMap);
	}
	
	/**
	 * 根据粮票等优惠金额，按运单从金额大到小排序后，先分配给大的运单所有金额，多余的依次往下分配
	 * @param ebuyOrderRevenueList
	 * @return
	 */
	private List<EbuyOrderRevenue> getEbuyDelivDiscountList(List<EbuyOrderRevenue> ebuyOrderRevenueList) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		for(EbuyOrderRevenue ebuyOrderRevenue : ebuyOrderRevenueList) {
			BigInteger delivId = ebuyOrderRevenue.getDelivOrderId();
			paramMap.put("delivId", delivId);
			List<EbuyDelivDiscount> delivDiscountList = revenueDao.getEbuyDelivDiscountList(paramMap);
			
			if(delivDiscountList.size() > 0) {
				Long remaindCoupon = delivDiscountList.get(0).getTotalCouponAmount();
				if(remaindCoupon != null) {
					for(EbuyDelivDiscount delivDiscount : delivDiscountList) {
						if(remaindCoupon >= delivDiscount.getProdPrice()) {
							remaindCoupon -= delivDiscount.getProdPrice();
							delivDiscount.setThisDeliveCoupon(delivDiscount.getProdPrice());
						} else if(remaindCoupon < delivDiscount.getProdPrice()) {
							delivDiscount.setThisDeliveCoupon(remaindCoupon);
							remaindCoupon = 0L;
						}
						if(delivId.equals(delivDiscount.getDeliverId())) {
							ebuyOrderRevenue.setDiscountMoney(delivDiscount.getThisDeliveCoupon());
						}
					}
				}
			}
		}
		
		return ebuyOrderRevenueList;
	}
	
	public List<EbuyRevenueSignalAmount> selectEbuyRevenueSignalAmountList(Map<String, Object> paramMap) {
		return revenueDao.selectEbuyRevenueSignalAmountList(paramMap);
	}
	
	public List<EbuyRevenueSignalAmount> selectEbuyRevenueSignalAmountExportList(Map<String, Object> paramMap) {
		return revenueDao.selectEbuyRevenueSignalAmountExportList(paramMap);
	}
	
	public int selectEbuyRevenueSignalAmountCount(Map<String, Object> paramMap) {
		return revenueDao.selectEbuyRevenueSignalAmountCount(paramMap);
	}
	
	public boolean isPropertyManagerRevenuet(Map<String, Object> paramMap) {
		return revenueDao.isPropertyManagerRevenuet(paramMap);
	}
	
	public int selectRevenueSignalAmountCount(Map<String, Object> paramMap) {
		return revenueDao.selectRevenueSignalAmountCount(paramMap);
	}
	
	public EbuyRevenueTotal selectEbuyRevenueTotal(Map<String, Object> paramMap) {
		return revenueDao.selectEbuyRevenueTotal(paramMap);
	}
	
	public void saveBatch(List<RevenueSignalAmount> revenueSignalAmountList, List<RevenueSignalAmountEbuy> revenueSignalAmountEbuyList) {
		revenueSignalAmountBaseDao.insertRevenueSignalAmountBatch(revenueSignalAmountList);
		revenueSignalAmountEbuyBaseDao.insertRevenueSignalAmountEbuyBatch(revenueSignalAmountEbuyList);
	}
	
	public void saveRevenueEbuy(List<RevenueSignalAmount> revenueSignalAmountList, List<RevenueSignalAmountEbuy> revenueSignalAmountEbuyList) {
		revenueSignalAmountBaseDao.insertRevenueSignalAmountBatch(revenueSignalAmountList);
		revenueSignalAmountEbuyBaseDao.insertRevenueSignalAmountEbuyBatch(revenueSignalAmountEbuyList);
		if(revenueSignalAmountList.size() > 0) {
			revenueDao.updateForEbuyRevenueProccessed(revenueSignalAmountList.get(0).getGoalId());
		}
	}
	
	//end

	public IRevenueSignalAmountBaseDao getRevenueSignalAmountBaseDao() {
		return revenueSignalAmountBaseDao;
	}

	public void setRevenueSignalAmountBaseDao(IRevenueSignalAmountBaseDao revenueSignalAmountBaseDao) {
		this.revenueSignalAmountBaseDao = revenueSignalAmountBaseDao;
	}

	public IRevenueSignalAmountEbuyBaseDao getRevenueSignalAmountEbuyBaseDao() {
		return revenueSignalAmountEbuyBaseDao;
	}

	public void setRevenueSignalAmountEbuyBaseDao(IRevenueSignalAmountEbuyBaseDao revenueSignalAmountEbuyBaseDao) {
		this.revenueSignalAmountEbuyBaseDao = revenueSignalAmountEbuyBaseDao;
	}
	
	@Override
	public RevenueApplyParam getRevenueApplyParam(BigInteger miniRoleId,Integer miniRoleType, Integer projectType){
		Integer goalType = projectType;
		String goalStartTime = revenueDao.selectLastApplyGoalEndTime(miniRoleId,miniRoleType,goalType);//获取提款开始时间：为空或者最新的time
		String goalEndTime = ApplicationContextBothUtil.getDualDao().getNowTime();//获取当前时间为提款结束时间
		Double totalAmount = revenueDao.selectRevenueApplyTotalByTime(miniRoleId,miniRoleType,goalType,goalStartTime,goalEndTime);
		return new RevenueApplyParam(miniRoleId, miniRoleType, goalType, goalStartTime, goalEndTime, totalAmount);
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public BigInteger applyRevenue(BigInteger omsUserId,UserRole expectedRole,RevenueApplyParam applyParam) {
		BigInteger applyUserId = omsUserId;
		
		Integer goalType = applyParam.getGoalType();
		BigInteger miniRoleId = applyParam.getMiniRoleId();
		Integer miniRoleType = applyParam.getMiniRoleType();
		String goalStartTime = applyParam.getGoalStartTime();
		String goalEndTime = applyParam.getGoalEndTime();
		{//校验
			RevenueRole revenueRole = createRevenueRoleExcep(omsUserId, expectedRole);
			if(revenueRole instanceof RevenueRoleMultiId){
				RevenueRoleMultiId tmpRole = (RevenueRoleMultiId)revenueRole;
				if(!tmpRole.getRoleIdList().contains(miniRoleId)){
					throw new BusinessRuntimeException("RevenueService.applyRevenue.miniRoleIdMulti.notValid");
				}
			}else{
				if(revenueRole.getRoleId().compareTo(miniRoleId)!=0){
					throw new BusinessRuntimeException("RevenueService.applyRevenue.miniRoleId.notValid");
				}
			}
			miniRoleType = revenueRole.getRole().getCode();
			goalStartTime = revenueDao.selectLastApplyGoalEndTime(miniRoleId,miniRoleType,goalType);//获取提款开始时间：为空或者最新的time
			goalEndTime = ApplicationContextBothUtil.getDualDao().getNowTime();//获取当前时间为提款结束时间
		}
		Integer optType = RevenueDict.TkOptType.User;
		return applyRevenueBase(goalType, miniRoleId, miniRoleType, goalStartTime, goalEndTime, optType, applyUserId);
	}
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public BigInteger applyRevenueSystem(RevenueApplyParam applyParam,Integer optType,BigInteger applyUserId) {
		Integer goalType = applyParam.getGoalType();
		BigInteger miniRoleId = applyParam.getMiniRoleId();
		Integer miniRoleType = applyParam.getMiniRoleType();
		String goalStartTime = applyParam.getGoalStartTime();
		String goalEndTime = applyParam.getGoalEndTime();
		return applyRevenueBase(goalType, miniRoleId, miniRoleType, goalStartTime, goalEndTime, optType, applyUserId);
	}
	
	private BigInteger applyRevenueBase(Integer goalType,BigInteger miniRoleId,Integer miniRoleType,String goalStartTime,String goalEndTime,Integer optType,BigInteger applyUserId){
		BigInteger applyAddId = ApplicationContextBothUtil.getUuidManager().getNextUuidBigInteger(SEQConstants.t_revenue_apply);
		//标记sig为结算中的状态 //更新提款明细的状态
		BigInteger oropertyManagementFId = null;
		if(applyUserId.equals(RevenueConstant.System) && miniRoleType.equals(RevenueDict.MiniRoleType.PCManagement)) {
			goalStartTime = null;
			oropertyManagementFId = groupBuildingBaseService.getGroupBuildingBySeqId(miniRoleId).gettPropertyManagementFId();
			revenueDao.updateRevenueSignalAmountAsDoing(applyAddId,oropertyManagementFId,miniRoleType,goalType,goalStartTime,goalEndTime,optType, miniRoleId);
		} else {
			revenueDao.updateRevenueSignalAmountAsDoing(applyAddId,miniRoleId,miniRoleType,goalType,goalStartTime,goalEndTime,optType);
		}
		RevenueMoneyShow revenueMoneyShow = revenueDao.selectRevenueApplyTotalFromDetail(applyAddId);
		Double totalAmount = revenueMoneyShow.getTotalAmount();
		Double amountPtbt = revenueMoneyShow.getAmountPtbt();
		Double amountUsrReal = revenueMoneyShow.getAmountUsrReal();
		if(totalAmount==null||totalAmount<0.01||(totalAmount== 0 && goalType.compareTo(RevenueDict.MiniRoleType.DownstairStore) != 0 )){
			throw new BusinessRuntimeException("RevenueService.applyRevenue.amount0");
		}
		RevenueApply toAddRevenueApply = new RevenueApply();
		{//新增提款记录
			String applyNo = RevenueTkNoGenerator.getOrderNo(applyAddId);//设置提款单号
			Integer tkStatus = RevenueDict.TkStatus.Doing;
			String applyTime = ApplicationContextBothUtil.getDualDao().getNowTime();
			String tkSuccTime = null;
			toAddRevenueApply.setApplyNo(applyNo);
			toAddRevenueApply.setApplyTime(applyTime);
			toAddRevenueApply.setApplyUserId(applyUserId);
			toAddRevenueApply.setGoalEndTime(goalEndTime);
			toAddRevenueApply.setGoalStartTime(goalStartTime);
			toAddRevenueApply.setGoalType(goalType);
			toAddRevenueApply.setId(applyAddId);
			toAddRevenueApply.setMiniRoleId(miniRoleId);
			toAddRevenueApply.setMiniRoleType(miniRoleType);
			toAddRevenueApply.setOptType(optType);
			toAddRevenueApply.setTkStatus(tkStatus);
			toAddRevenueApply.setTkSuccTime(tkSuccTime);
			toAddRevenueApply.setVisibleType(RevenueDict.VisibleType.UserVisible);
			
			toAddRevenueApply.setTotalAmount(totalAmount);
			toAddRevenueApply.setAmountPtbt(amountPtbt);
			toAddRevenueApply.setAmountUsrReal(amountUsrReal);
			if(goalType.equals(RevenueDict.RevenueProject.ServiceOrder)) {
				toAddRevenueApply.setAmountUsrReal(totalAmount - (amountPtbt == null ? 0 : amountPtbt));
			} else if(goalType.equals(RevenueDict.RevenueProject.MarketAmout)) {
				if(amountPtbt >= revenueMoneyShow.getRefundAmount()) {
					toAddRevenueApply.setAmountPtbt(amountPtbt - revenueMoneyShow.getRefundAmount());
				} else {
					toAddRevenueApply.setAmountPtbt(0.0);
				}
				toAddRevenueApply.setAmountUsrReal(totalAmount - toAddRevenueApply.getAmountPtbt());
			}
			
			toAddRevenueApply.setRoleName("");
			if(RevenueDict.MiniRoleType.PCManagement.equals(miniRoleType)) {
				if(applyUserId.equals(RevenueConstant.System)) {
					toAddRevenueApply.setMiniRoleId(oropertyManagementFId);
					toAddRevenueApply.settPropertyManagementFId(oropertyManagementFId);
					toAddRevenueApply.settGbId(miniRoleId);
				} else {
					toAddRevenueApply.settPropertyManagementFId(miniRoleId);
					toAddRevenueApply.settGbId(null);
				}
			}
			
			Integer resCount = revenueApplyBaseDao.insertRevenueApply(toAddRevenueApply);
			if(resCount!=null&&resCount>0){
				//syl-add-2015-11-22 16:13:18备份信息
				resCount = revenueDao.updateRevenueApplyBakInfo(applyAddId, miniRoleType);
//				toAddRevenueApply.setRoleName(roleName);
//				toAddRevenueApply.setbBankName(bBankName);
//				toAddRevenueApply.setbBankNo(bBankNo);
//				toAddRevenueApply.setbConnectName(bConnectName);
//				toAddRevenueApply.setbPhone(bPhone);
			}
			if(resCount==null||resCount<=0){
				throw new BusinessRuntimeException("RevenueService.applyRevenue.addApply.count0");
			}
		}
		//返回本次提款的Id
		return applyAddId;
	}
	
	@Override
	public void confirmRevenueApply(BigInteger applyId,BigInteger operUserId){
		Integer res = revenueDao.updateRevenueApplyAsFinished(applyId,operUserId);
		if(res==null||res<=0){
			throw new BusinessRuntimeException("RevenueService.confirmRevenueApply.count0");
		}
	}
	
	@Override
	public RevenueRole createRevenueRoleExcep(BigInteger omsUserId, UserRole expectedRole){
		RevenueRole revenueRole = revenueDao.createRevenueRole(omsUserId, expectedRole);
		if (revenueRole == null) {
			throw new BusinessRuntimeException("RevenueService.createRevenueRole.null");
		}
		return revenueRole;
	}

	public void setCommonLockDao(ICommonLockDao commonLockDao) {
		this.commonLockDao = commonLockDao;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void confirmRevenueApplyBatch(Set<BigInteger> applyIdList, List<BigInteger> apply16Ids, BigInteger operUserId) {
		Integer res = 0;
		if(applyIdList!=null && applyIdList.size()>0){
			res = revenueDao.updateRevenueApplyAsFinishedBatch(applyIdList,operUserId);
		}
		
		Integer affectedNum = 0;
		if(apply16Ids!=null && apply16Ids.size()>0){
			affectedNum = revenueDao.updateRevenueApplyWithMerchant(apply16Ids, operUserId);
		}
		if (res + affectedNum < applyIdList.size()) {
			throw new BusinessRuntimeException("RevenueService.confirmRevenueApplyBatch.countError");
		}
	}
	
	@Override
	public BeginEndDate getPropertyRealPayHistoryMonth(BigInteger companyId) {
		return revenueDao.selectPropertyRealPayHistoryMonth(companyId);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public void markPropertyRealPayAsFinished(BigInteger companyId,
			BeginEndDate beginEndDate, boolean isAll) {
		BeginEndDate dbBeginEndDate = getPropertyRealPayHistoryMonth(companyId);
		//校验时间范围,beginEndDate不能为空，end不能为空，且包含起止时间,最大为上个月
		if(isAll){
			beginEndDate = dbBeginEndDate;
		}
		if(!BeginEndDate.isAvailable(beginEndDate)){
			throw new BusinessRuntimeException("RevenueService.markPRealPayHistory.peridTime.error");
		}
		if(beginEndDate.getEnd().getTime()>dbBeginEndDate.getEnd().getTime()){
			throw new BusinessRuntimeException("RevenueService.markPRealPayHistory.peridTime.toBigEnd");
		}
		
		{//截止月份增加1,为后续查询处理
			Date end = beginEndDate.getEnd();
			if(beginEndDate.getEnd()!=null){
				Date endNew = DateUtil.getNextMonth(end, 1);
				beginEndDate.setEnd(endNew);
			}
		}
		{//TODO 账单月份转为实际缴费月份
//			Integer monthChange = revenueDao.selectGbMonthChangeByCompanyId(companyId);
		}
		BigInteger miniRoleId = companyId;
		Integer optType = RevenueDict.TkOptType.SystemAuto;
		UserRole userRole = UserRole.PropertyCompany;
		Integer miniRoleType = userRole.getCode();
		Integer projectType = RevenueDict.RevenueProject.PropertyRealPayAmout;
		Integer goalType = projectType;
		RevenueRole revenueRole = new RevenueRole(userRole, companyId);
		Integer resCount = propertyRealPayAmout2RevSignal4BillMonth.syn2RegSignal(projectType, revenueRole, beginEndDate);//获取时间范围内的物业账单 将数据同步到sig
		if(resCount!=null&&resCount>0){
			//发起提款申请
			String goalStartTime = beginEndDate.getBegin(DateUtil.formatSecond.get());
			String goalEndTime =  beginEndDate.getEnd(DateUtil.formatSecond.get());
			RevenueApplyParam applyParam =  new RevenueApplyParam(miniRoleId, miniRoleType, goalType, goalStartTime, goalEndTime);
			BigInteger applyId = this.applyRevenueSystem(applyParam, optType, RevenueConstant.System);
			//标记为已结算
			confirmRevenueApply(applyId,RevenueConstant.System);
		}
		
	}
	
	/**
	 * 以模板的形式导出数据
	 * @return
	 */
	public HSSFWorkbook createReport(List<EbuyRevenueSignalAmount> ebuyRevenueSignalAmountList) {
		if (ebuyRevenueSignalAmountList.size() > 0) {
			String fileServiceBasePath = CnfantasiaCommbusiUtil.getFileServiceBasePath();
			String filePath = fileServiceBasePath + ("docs/jfq_revenue_ebuy_template.xls");
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
			HSSFCellStyle style = wb.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
			style.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
			style.setBorderTop(HSSFCellStyle.BORDER_THIN);
			style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			style.setBorderRight(HSSFCellStyle.BORDER_THIN);
			style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			style.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框
			
			HSSFCellStyle styleStr = wb.createCellStyle();
			styleStr.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			styleStr.setBorderTop(HSSFCellStyle.BORDER_THIN);
			styleStr.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			styleStr.setBorderRight(HSSFCellStyle.BORDER_THIN);
			styleStr.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			
			HSSFCellStyle styleRate = wb.createCellStyle();//百分比样式
			HSSFDataFormat format = wb.createDataFormat();  
			styleRate.setDataFormat(format.getFormat("0.00%"));
			styleRate.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
			styleRate.setBorderTop(HSSFCellStyle.BORDER_THIN);
			styleRate.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			styleRate.setBorderRight(HSSFCellStyle.BORDER_THIN);
			styleRate.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			
			NumberFormat nf = new DecimalFormat("#0.00##");
			int count = 0;
			double totalFee = 0.00;//总计结算价小计
			double totalDeliveFee = 0.00;//总计运费
			double totalAmountTotal = 0.00;//总计运单总额
			double totalAmountReal = 0.00;//总计运单实付
			double totalAmountDiscount = 0.00;//总计优惠金额
			double totalAmoutDeduct = 0.00;//总计平台收入
			double totalAmoutApir = 0.00;//总计代收金额
			double totalAmountRefund = 0.00;//总计退款金额
			double totalAmountJs = 0.00;//总计结算金额
			//double totalAmountSy = 0.00;//总计收益金额
			double totalTotalWy = 0.00;//总计物业收入
			double totalTotalAgent = 0.00;//总计代理收入
					
			for (int i = 0, j = 0;  j < ebuyRevenueSignalAmountList.size(); j++) {
				EbuyRevenueSignalAmount bean = ebuyRevenueSignalAmountList.get(j);
				int prodIndex = 0;
				for(EbuyRevenueProd prod : bean.getEbuyRevenueProdList()) {
						HSSFRow row = sheet.createRow(count + 1);
						count ++;
						i = 0;
						fillCellData(row, i++, bean.getOrderNo(), styleStr);
						//支付时间
						if(bean.getRevenueSignalAmountEbuy().getPayMethod() == null) {
							fillCellData(row, i++, "", styleStr);
						} else {
							fillCellData(row, i++, com.cnfantasia.server.common.utils.DateUtils.formatTime(bean.getPayTm()), styleStr);
						}
						fillCellData(row, i++, bean.getSupplyName(), styleStr); //店铺名称
						fillCellData(row, i++, prod.getProductName(), styleStr); //商品名称
						fillCellData(row, i++, String.valueOf(prod.getQty()), styleStr); 
						//结算价
						fillCellData(row, i++, nf.format(prod.getSettlementPrice()),style);
						
						if(prodIndex == 0) {
							//结算价小计
							fillCellData(row, i++, nf.format(bean.getDeliveTotalFee()),style);
							
							//运费
							fillCellData(row, i++, nf.format(prod.getDeliveFee()),style);
							
							//运单总额
//							fillCellData(row, i++, nf.format(BigDecimalUtil.add(bean.getDeliveTotalFee(), prod.getDeliveFee())),style);
							fillCellData(row, i++, bean.getRevenueSignalAmountEbuy().getAmountTotal(), style);
							fillCellData(row, i++, bean.getRevenueSignalAmountEbuy().getAmountReal(),style); //运单实付
							fillCellData(row, i++, bean.getRevenueSignalAmountEbuy().getAmountDiscount(),style); //优惠金额
							if(bean.getRevenueSignalAmountEbuy().getAmoutDeduct() != null && bean.getRevenueSignalAmountEbuy().getAmountTotal() != null && bean.getRevenueSignalAmountEbuy().getAmoutDeduct() != 0.00) {
								//抽成比例= 平台收入/(运单总额-退款金额)
								//fillCellData(row, i++, BigDecimalUtil.div(bean.getRevenueSignalAmountEbuy().getAmoutDeduct(), bean.getRevenueSignalAmountEbuy().getAmountTotal()==null ? 0.00 : bean.getRevenueSignalAmountEbuy().getAmountTotal() , 2),styleRate); //抽成比例
								fillCellData(row, i++, BigDecimalUtil.div(bean.getRevenueSignalAmountEbuy().getAmoutDeduct(), bean.getRevenueSignalAmountEbuy().getAmountTotal()==null ? 0.00 : bean.getRevenueSignalAmountEbuy().getAmountTotal() - bean.getRevenueSignalAmountEbuy().getAmountRefund() , 2),styleRate); //抽成比例
							} else {
								fillCellData(row, i++, "0.00%",styleRate); //抽成比例
							}
							
							//平台使用费
							if(bean.getRevenueSignalAmountEbuy().getAmoutDeduct() != null && bean.getRevenueSignalAmountEbuy().getAmoutDeduct() > 0) {
								fillCellData(row, i++, nf.format(bean.getRevenueSignalAmountEbuy().getAmoutDeduct()),style);
							} else {
								bean.getRevenueSignalAmountEbuy().setAmoutDeduct(0.0);
								fillCellData(row, i++, "0.00",style);
							}
							//代收金额
							fillCellData(row, i++, BigDecimalUtil.sub(bean.getRevenueSignalAmountEbuy().getAmountReal(), bean.getRevenueSignalAmountEbuy().getAmoutDeduct()),style); //代收金额
							//退款金额
							fillCellData(row, i++, bean.getRevenueSignalAmountEbuy().getAmountRefund(),style);
							
							fillCellData(row, i++, nf.format(bean.getSrcMoney() - bean.getRevenueSignalAmountEbuy().getAmoutDeduct()),style);//结算金额
							//fillCellData(row, i++, bean.getAmountPlatform() == null ? 0.00 : bean.getAmountPlatform(),style);//收益金额
							
							fillCellData(row, i++, PayUtils.getPayMethodStr(bean.getRevenueSignalAmountEbuy().getPayMethod()), styleStr);
							
							fillCellData(row, i++, bean.getRevenueSignalAmountEbuy().getFlowNo(), styleStr); //流水号
							if(bean.getRevenueSignalAmountEbuy().getAmoutDeduct()!=null && bean.getAmountWy() != null && bean.getRevenueSignalAmountEbuy().getAmoutDeduct() != 0.00) {
								fillCellData(row, i++, BigDecimalUtil.div(bean.getAmountWy() == null ? 0.00 : bean.getAmountWy(), bean.getRevenueSignalAmountEbuy().getAmoutDeduct(), 4),styleRate); //占平台收入比物业
							} else {
								fillCellData(row, i++, "0.00%",styleRate); //占平台收入比物业
							}
							
							fillCellData(row, i++, bean.getAmountWy() == null ? 0.00 : bean.getAmountWy(),style); //物业收入
							
							if(bean.getRevenueSignalAmountEbuy().getAmoutDeduct()!=null && bean.getAmountAgent() != null && bean.getRevenueSignalAmountEbuy().getAmoutDeduct() != 0.00) {
								fillCellData(row, i++, BigDecimalUtil.div(bean.getAmountAgent() == null ? 0.00 : bean.getAmountAgent(), bean.getRevenueSignalAmountEbuy().getAmoutDeduct(), 4),styleRate); //占平台收入比代理
							} else {
								fillCellData(row, i++, "0.00%",styleRate); //占平台收入比代理
							}
							
							fillCellData(row, i++, bean.getAmountAgent() == null ? 0.00 : bean.getAmountAgent(),style); //代理收入
							
							totalFee += bean.getDeliveTotalFee();//总计结算价小计
							totalDeliveFee += prod.getDeliveFee();//总计运费
							totalAmountTotal += bean.getRevenueSignalAmountEbuy().getAmountTotal();//总计运单总额
							totalAmountReal += bean.getRevenueSignalAmountEbuy().getAmountReal();//总计运单实付
							totalAmountDiscount += bean.getRevenueSignalAmountEbuy().getAmountDiscount();//总计优惠金额
							totalAmoutDeduct += bean.getRevenueSignalAmountEbuy().getAmoutDeduct()==null ? 0.00 : bean.getRevenueSignalAmountEbuy().getAmoutDeduct();//总计平台收入
							totalAmoutApir += BigDecimalUtil.sub(bean.getRevenueSignalAmountEbuy().getAmountReal(), bean.getRevenueSignalAmountEbuy().getAmoutDeduct());//总计代收金额
							totalAmountRefund += bean.getRevenueSignalAmountEbuy().getAmountRefund();//总计退款金额
							totalAmountJs += (bean.getSrcMoney() - bean.getRevenueSignalAmountEbuy().getAmoutDeduct());//总计结算金额
							//totalAmountSy += bean.getAmountPlatform() == null ? 0.00 : bean.getAmountPlatform();//总计收益金额
							totalTotalWy += bean.getAmountWy()== null ? 0.00 : bean.getAmountWy();//总计物业收入
							totalTotalAgent += bean.getAmountAgent()== null ? 0.00 : bean.getAmountAgent();//总计代理收入
							prodIndex ++;
						} else {//为空缺的单元格添加边框属性
							for (int k = 0; k < 16; k++) {
								fillCellData(row, k+6, "",styleStr);
							}
						}
				}
			}
		
			HSSFRow row = sheet.createRow((count + 1));
			fillCellData(row, 0, "", styleStr);
			fillCellData(row, 1, "", styleStr);
			fillCellData(row, 2, "", styleStr);
			fillCellData(row, 3, "总计", styleStr);
			fillCellData(row, 4, "", styleStr);
			fillCellData(row, 5, "", styleStr);
			fillCellData(row, 6, totalFee,style);
			fillCellData(row, 7, totalDeliveFee,style);
			fillCellData(row, 8, totalAmountTotal,style);
			fillCellData(row, 9, totalAmountReal,style);
			fillCellData(row, 10, totalAmountDiscount,style);
			fillCellData(row, 11, "", styleStr);
			fillCellData(row, 12, totalAmoutDeduct,style);
			fillCellData(row, 13, totalAmoutApir,style);
			fillCellData(row, 14, totalAmountRefund,style);
			fillCellData(row, 15, totalAmountJs,style);
			//fillCellData(row, 16, totalAmountSy,style);
			fillCellData(row, 16, "", styleStr);
			fillCellData(row, 17, "", styleStr);
			fillCellData(row, 18, "", styleStr);
			fillCellData(row, 19, totalTotalWy,style);
			fillCellData(row, 20, "", styleStr);
			fillCellData(row, 21, totalTotalAgent,style);
			
			return wb;
		}
		return null;
	
	}
	
	@SuppressWarnings("deprecation")
	private void fillCellData(HSSFRow row,int index,double value,HSSFCellStyle style){
		HSSFCell cell = row.createCell(index);
		cell.setCellStyle(style);
		cell.setCellValue(value);
	}
	
	@SuppressWarnings("deprecation")
	private void fillCellData(HSSFRow row,int index,String value,HSSFCellStyle style){
		HSSFCell cell = row.createCell(index);
		cell.setCellStyle(style);
		cell.setCellValue(value);
	}

	@Override
	public List<BigInteger> selectPayBill_idList_byRevenueApply(BigInteger raId) {
		return revenueDao.selectPayBill_idList_byRevenueApply(raId);
	}
	
	/**
	 * 获取不同收益明细的HSSFWorkbook
	 * @param request 
	 */
	@Override
	public HSSFWorkbook getRevenueHSSFWorkbook(RevenueBatchParam revenueBatchParam) {
		HSSFWorkbook workbook = null;
		
		String projectType = revenueBatchParam.getGoalType();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("projectType", projectType);
		paramMap.put("applyId", revenueBatchParam.getApplyId());
		paramMap.put("roleId", revenueBatchParam.getMiniRoleId());
		paramMap.put("roleType", revenueBatchParam.getMiniRoleType());
		
		if(RevenueDict.RevenueProject.FinanceDeduAmount.toString().equals(projectType)) {//物业宝抵扣明细
			List<FinanceDeduRevenueSignalAmount> financeDeduRevenueSignalAmountList = revenueFinanceDeduService.selectRevenueSaList(paramMap);
			if(!financeDeduRevenueSignalAmountList.isEmpty()) {
				return revenueFinanceDeduService.createReport(financeDeduRevenueSignalAmountList);
			} 
			return null;
		}
		
		if(RevenueDict.RevenueProject.CarFinanceBaoAmout.equals(projectType)) {//停车宝佣金
			paramMap.put("revenueProject", projectType);
			List<FinanceRevenueSignalAmount> financeRevenueSignalAmountList = revenueFinanceService.selectFinanceRevenueSaList(paramMap);
			if(!financeRevenueSignalAmountList.isEmpty()) {
				return revenueFinanceService.createReport(financeRevenueSignalAmountList);
			}
			return null;
		}
		
		
		if(RevenueDict.RevenueProject.CarFinanceBaoAmout.toString().equals(projectType)) {//停车宝佣金
			paramMap.put("revenueProject", projectType);
			List<FinanceRevenueSignalAmount> financeRevenueSignalAmountList = revenueFinanceService.selectFinanceRevenueSaList(paramMap);
			if(!financeRevenueSignalAmountList.isEmpty()) {
				return revenueFinanceService.createReport(financeRevenueSignalAmountList);
			}
			return null;
		}
		
		
		if(RevenueDict.RevenueProject.CarDeduAmount.toString().equals(projectType)) {//停车宝抵扣明细
			List<CarDeduRevenueSignalAmount> carDeduRevenueSignalAmountList = revenueCarDeduService.selectRevenueSaList(paramMap);
			if(!carDeduRevenueSignalAmountList.isEmpty()) {
				return revenueCarDeduService.createReport(carDeduRevenueSignalAmountList);
			} 
			return null;
		}
		
		if(RevenueDict.RevenueProject.WuyebaoAmount.toString().equals(projectType)) {//物业宝收益明细
			paramMap.put("revenueProject", projectType);//批量导出，物业宝佣金不能生成Excel问题
			List<FinanceRevenueSignalAmount> financeRevenueSignalAmountList = revenueFinanceService.selectFinanceRevenueSaList(paramMap);
			if(!financeRevenueSignalAmountList.isEmpty()) {
				return revenueFinanceService.createReport(financeRevenueSignalAmountList);
			}
			return null;
		}
		
		if(RevenueDict.RevenueProject.ServiceOrder.toString().equals(projectType)) {//上门维修明细
			List<EbuyRevenueSignalAmount> ebuyRevenueSignalAmountList = revenueDredgeService.selectDredgeRevenueSaList(paramMap);
			if(!ebuyRevenueSignalAmountList.isEmpty()) {
				return revenueDredgeService.createReport(ebuyRevenueSignalAmountList);
			}
			return null;
		}
		
		if(RevenueDict.RevenueProject.MarketAmout.toString().equals(projectType)) {//店铺/超市收益明细
			List<EbuyRevenueSignalAmount> ebuyRevenueSignalAmountList = selectEbuyRevenueSignalAmountExportList(paramMap);
			if(!ebuyRevenueSignalAmountList.isEmpty()) {
				return createReport(ebuyRevenueSignalAmountList);
			}
			return null;
		}
		
		if(RevenueDict.RevenueProject.CarAmount.toString().equals(projectType) ||       // 停车费代收（实缴）明细
				RevenueDict.RevenueProject.CarAmountBt.toString().equals(projectType)) {// 停车费代收（补贴）明细
			paramMap.put("goalType", projectType);
			List<CarRevenueSignalAmount> carRevenueSignalAmountList = revenueCarService.selectCarRevenueSaList(paramMap);
			if(!carRevenueSignalAmountList.isEmpty()) {
				return revenueCarService.createReport(carRevenueSignalAmountList);
			}
			return null;
		}

		if (RevenueDict.RevenueProject.LivingPayAmount.toString().equals(projectType)) {// 生活缴费明细
			paramMap.put("revenueApplyId", revenueBatchParam.getApplyId());
			return livingPayService.createRevenueReport(paramMap);
		}
		
		if (RevenueDict.RevenueProject.PropertyRealPayAmout.toString().equals(projectType)
				|| RevenueDict.RevenueProject.PropertyOtherFee.toString().equals(projectType)
				|| RevenueDict.RevenueProject.PropertySubsidyAmout.toString().equals(projectType)) {// 物业费代收明细
			List<BigInteger> payBillIdList = selectPayBill_idList_byRevenueApply(new BigInteger(revenueBatchParam.getApplyId()));
			if(payBillIdList.isEmpty())
				return null;
			
			HashMap<String, Object> paraMap = new HashMap<String, Object>();//增加已缴费条件，和部分已缴区分开
			paraMap.put("isPay", "1");
			List<PayBillWithFeeDetailEntity> payBillImportedEntyList = payBillService.getExportedPayBillFroReven(payBillIdList, true, paraMap);
			if(!payBillImportedEntyList.isEmpty()) {
				return payBillService.createRport(payBillImportedEntyList);
			}
			return null;
		}
		
		if(RevenueDict.RevenueProject.DirectPurchase.toString().equals(revenueBatchParam.getGoalType())){// 导出“电商供应商明细”
			BigInteger revenueApplyId = new BigInteger(revenueBatchParam.getApplyId());
			RevenueApply apply = revenueApplyBaseDao.selectRevenueApplyBySeqId(revenueApplyId);
			
			String revApplyFinanceId = apply.getRevApplyFinanceId() == null ? "" : apply.getRevApplyFinanceId().toString();
			String visibleType = apply.getVisibleType() == null ? "" : apply.getVisibleType().toString();
			if (apply.getRevenueType().compareTo(1) == 0) {
				workbook = ebuyProductSettleService.selectRevenueDetailAdminExportList(revenueApplyId, revApplyFinanceId, visibleType);
			} else {
				ExcelUtil<EbuyProductSettleDetailAdminDto> excelUtil = new ExcelUtil<EbuyProductSettleDetailAdminDto>();
				List<EbuyProductSettleDetailAdminDto> data = ebuyProductSettleService.getEbuyProductSettleDetailExport(revenueApplyId, revApplyFinanceId, visibleType);
				try {
					workbook = excelUtil.getExportExcelForXSSFWorkbook("sheet", data, EbuyProductSettleDetailAdminDto.class);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return workbook;
	}

	public List<RevenueAmountPrevEntity> getRevenueAmountManagementPrevList(BigInteger omsUserId, String searchName,
			PageModel pageModel) {
		List<RevenueAmountPrevEntity> pcList =  revenueDao.selectRevenueAmountManagementPrevList(omsUserId, searchName, pageModel);
		return pcList;
	}
	
	/**
	 * 获取当前查询的所有数据的HSSFWorkbook
	 * 
	 * @param paramMap
	 * @return HSSFWorkbook
	 */
	@Override
	public HSSFWorkbook getRevenueApplyHSSFWorkbook(Map<String, Object> paramMap) {
		List<RevenueApply4Export> exportList = revenueDao.selectRevenueApplyListFinance4Export(paramMap);
		String[] sheetTitle = {"提款单号", "申请发起时间", "申请对象名称", "申请对象类型", "结算项目", "结算金额", "平台补贴额", "用户实缴", "关联单号","合并单号", "账户名称", "银行卡号", "开卡支行", "开户银行", "结算状态"};
		int[]    cellWidth  = {6000,    6000,      10000,     4000,       5000,    3000,    3000,     3000,    12000, 12000,  10000,   8000,   4000,   4000,    3000};
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("提款申请记录");
		HSSFFont font = wb.createFont();
		font.setFontName("微软雅黑");
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// head加粗
		// 创建单元格样式
		HSSFCellStyle styleLeft = wb.createCellStyle();
		{
			styleLeft.setAlignment(HSSFCellStyle.ALIGN_LEFT);
			styleLeft.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			styleLeft.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
			// 设置字体
			styleLeft.setFont(font);
		}
		
		// 创建单元格样式
		HSSFCellStyle styleRight = wb.createCellStyle();
		{
			styleRight.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
			styleRight.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			styleRight.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
			// 设置字体
			styleRight.setFont(font);
		}
		// 创建Excel的sheet的一行
		HSSFRow row = sheet.createRow(0);
		// 设定行的高度
		row.setHeight((short)300);
		// title row
		for (int i = 0; i < sheetTitle.length; i++) {
			// 设置excel每列宽度
			sheet.setColumnWidth(i, cellWidth[i]);
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(styleLeft);
			cell.setCellValue(sheetTitle[i]);
		}
		// content row
		if(exportList!=null && exportList.size()>0){
			font.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
			styleLeft.setFont(font);
			for(int i=0, size=exportList.size(); i<size; i++){
				RevenueApply4Export bean = exportList.get(i);
				row = sheet.createRow((i+1));
				row.setHeight((short)400);
				for(int k=0, cellSize=sheetTitle.length; k<cellSize; k++){
					sheet.setColumnWidth(k, cellWidth[k]);
					HSSFCell cell = row.createCell(k);
					if(k>=5 && k<=7){
						cell.setCellStyle(styleRight);
					} else {
						cell.setCellStyle(styleLeft);
					}
					switch (k) {
						case 0:
							cell.setCellValue(bean.getApplyNo());
							break;
						case 1:
							cell.setCellValue(bean.getApplyTime());
							break;
						case 2:
							cell.setCellValue(bean.getRoleName());
							break;
						case 3:
							cell.setCellValue(getRoleType(bean.getMiniRoleType()));
							break;
						case 4:
							cell.setCellValue(getGoalType(bean.getGoalType()));
							break;
						case 5:
							cell.setCellValue(bean.getTotalAmount());
							break;
						case 6:
//							if(bean.getMiniRoleType().intValue()==6 && bean.getDredgeAmountPtbt()!=null){// "bean.getDredgeAmountPtbt()!=null"处理老数据						
//								cell.setCellValue(bean.getDredgeAmountPtbt());
//							} else {
								cell.setCellValue(bean.getAmountPtbt());
//							}
							break;
						case 7:
//							if(bean.getMiniRoleType().intValue()==6 && bean.getDredgeAmountUsrReal()!=null){// "bean.getDredgeAmountUsrReal()!=null"处理老数据				
//								cell.setCellValue(bean.getDredgeAmountUsrReal());
//							} else {								
								cell.setCellValue(bean.getAmountUsrReal());
//							}
							break;
						case 8:
							cell.setCellValue(bean.getEasBillNumbers());
							break;
						case 9:
							String mergedRevenueNos = bean.getMergedRevenueNOs().toString();
							cell.setCellValue(mergedRevenueNos.substring(1, mergedRevenueNos.length()-1));
							break;
						case 10:
							cell.setCellValue(bean.getAccountName());
							break;
						case 11:
							cell.setCellValue(bean.getbBankNo());
							break;
						case 12:
							cell.setCellValue(bean.getBankBranch());
							break;
						case 13:
							cell.setCellValue(bean.getbBankName());
							break;
						case 14:
							cell.setCellValue(getTkStatus(bean.getTkStatus()));
							break;
	
						default:
							cell.setCellValue("");
					}
				}
			}
		}
		return wb;
	}
	
	/**
	 * 获取申请对象类型
	 * 
	 * @param miniRoleType
	 * @return
	 */
	private final String getRoleType(int miniRoleType){
		switch (miniRoleType) {
			case 1:
				return "平台";
			case 2:
				return "物业";
			case 3:
				return "代理";
			case 4:
				return "财务";
			case 5:
				return "店铺";
			case 6:
				return "师傅";
			case 7:
				return "推荐人";
			case 12:
				return "店铺"; // "购销供应商结算";
			case 13:
				return "物业"; // "物业管理处";
			case 15:
				return "生活缴费运营";
			default:
				return "";
		}
	}
	
	/**
	 * 结算项目
	 * 
	 * @param goalType
	 * @return
	 */
	private final String getGoalType(int goalType){
		switch(goalType){
			case 1:
				return "认证门牌";
			case 2:
				return "维修收益";
			case 3:
				return "物业宝佣金";
			case 4:
				return "超市收益";
			case 5:
				return "物业费实收";
			case 6:
				return "停车费实收";
			case 7:
				return "其他代收费用";
			case 8:
				return "物业宝抵扣";
			case 9:
				return "停车宝佣金";
			case 10:
				return "停车宝抵扣";
			case 15:
				return "物业费补贴";
			case 16:
				return "供应商结算";
			case 17:
				return "停车费补贴";
			case 18:
				return "生活缴费代收";
			default:
				return "";
		}
	}
	
	/**
	 * 获取结算状态
	 * 
	 * @param tkStatus
	 * @return
	 */
	private final String getTkStatus(int tkStatus){
		switch(tkStatus){
			case 2:
				return "申请中";
			case 3:
				return "已转账";
			default:
				return "未定义";
		}
	}
	
	/*=====================================================生成单据start=====================================================*/

	/**
	 * 多对一推送类型
	 */
	private static final Integer[] OTMRoleTypes = {RevenueDict.MiniRoleType.PropertyCompany,
												  RevenueDict.MiniRoleType.PCManagement,
												  RevenueDict.MiniRoleType.RepairMaster,
												  RevenueDict.MiniRoleType.DownstairStore,
												  RevenueDict.MiniRoleType.DirectPurchase};
	private static final Integer[] OTMGoalTypes = {RevenueDict.RevenueProject.PropertyOtherFee,
												  RevenueDict.RevenueProject.PropertyOtherFee,
												  RevenueDict.RevenueProject.ServiceOrder,
												  RevenueDict.RevenueProject.MarketAmout,
												  RevenueDict.RevenueProject.DirectPurchase};
	
	/**
	 * 生成单据
	 * 
	 * @param applyId
	 * @param goalType
	 * @param miniRoleType
	 * @return
	 */
	@Override
	public ResultInfo pushBill2EAS(String applyId, String goalType, String miniRoleType){
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", applyId);
		
		List<RevenueApplyEntity> revenueApplys = revenueApplyPushService.selectRevenueApplyList4Push(paramMap);
		if (revenueApplys.size() > 0 && EASPushUtils.isBankInfoIsNull(revenueApplys.get(0))) {
			return new ResultInfo("0001", "银行账号信息不完整");
		}
		
		// one to multiply
		for(int i=0, size=OTMRoleTypes.length; i<size; i++){
			if(OTMRoleTypes[i].toString().equals(miniRoleType) && OTMGoalTypes[i].toString().equals(goalType)){
				return EASPushUtils.pushBill2EAS_OneToMultiplyBill(revenueApplys);
			}
		}
		
		//其余情况就是 one to one 
		return EASPushUtils.pushBill2EAS_OneByOneBill(revenueApplys);
	}
	/*=====================================================生成单据end=====================================================*/
}