package com.cnfantasia.server.commbusi.plotproperty.service;

import com.cnfantasia.server.api.commSysPara.constant.SysParamKey;
import com.cnfantasia.server.api.commonBusiness.util.PropertyPeriodCalculateUtil;
import com.cnfantasia.server.api.plotproperty.constant.PlotpropertyConstant;
import com.cnfantasia.server.api.plotproperty.constant.PlotpropertyDict;
import com.cnfantasia.server.api.plotproperty.entity.PropertyBillInfo;
import com.cnfantasia.server.api.pub.utils.ApplicationContextBothUtil;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.entity.RequestFileEntity;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.commbusi.plotproperty.dao.IPlotpropertyCfgDao;
import com.cnfantasia.server.commbusi.plotproperty.entity.DataType;
import com.cnfantasia.server.commbusi.plotproperty.entity.EditPropPayBillTypeEntity;
import com.cnfantasia.server.commbusi.plotproperty.entity.GroupBuildingCfgEntity;
import com.cnfantasia.server.commbusi.plotproperty.entity.IBusinessMonthYear;
import com.cnfantasia.server.commbusi.plotproperty.entity.PayBillInfo;
import com.cnfantasia.server.commbusi.plotproperty.entity.PropFeeDiscount;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.common.utils.DataUtil;
import com.cnfantasia.server.common.utils.PinyinUtil;
import com.cnfantasia.server.common.utils.StringUtils;
import com.cnfantasia.server.domain.pub.constant.SEQConstants;
import com.cnfantasia.server.domainbase.groupBuilding.dao.IGroupBuildingBaseDao;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.groupBuildingBillCycle.dao.IGroupBuildingBillCycleBaseDao;
import com.cnfantasia.server.domainbase.groupBuildingBillCycle.entity.GroupBuildingBillCycle;
import com.cnfantasia.server.domainbase.groupBuildingBillCycle.service.IGroupBuildingBillCycleBaseService;
import com.cnfantasia.server.domainbase.groupBuildingExt.dao.IGroupBuildingExtBaseDao;
import com.cnfantasia.server.domainbase.groupBuildingExt.entity.GroupBuildingExt;
import com.cnfantasia.server.domainbase.payBillSplit.dao.IPayBillSplitBaseDao;
import com.cnfantasia.server.domainbase.payBillSplit.entity.PayBillSplit;
import com.cnfantasia.server.domainbase.payBillType.dao.IPayBillTypeBaseDao;
import com.cnfantasia.server.domainbase.payBillType.entity.PayBillType;
import com.cnfantasia.server.domainbase.propertyFeeDetail.entity.PropertyFeeDetail;
import com.cnfantasia.server.ms.payBill.service.IPayBillService;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 收益配置服务类
* Filename:    PlotpropertyCfgService.java
* @version:    1.0.0
* Create at:   2015年12月10日 下午6:34:03
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2015年12月10日       shiyl             1.0             1.0 Version
 */
public class PlotpropertyCfgService implements IPlotpropertyCfgService{
	private Log logger = LogFactory.getLog(getClass());
	
	private IPlotpropertyCfgDao plotpropertyCfgDao;
	public void setPlotpropertyCfgDao(IPlotpropertyCfgDao plotpropertyCfgDao) {
		this.plotpropertyCfgDao = plotpropertyCfgDao;
	}
	
	private IPayBillTypeBaseDao payBillTypeBaseDao;
	public void setPayBillTypeBaseDao(IPayBillTypeBaseDao payBillTypeBaseDao) {
		this.payBillTypeBaseDao = payBillTypeBaseDao;
	}
	
	private IGroupBuildingBaseDao groupBuildingBaseDao;
	public void setGroupBuildingBaseDao(IGroupBuildingBaseDao groupBuildingBaseDao) {
		this.groupBuildingBaseDao = groupBuildingBaseDao;
	}
	
	private IPayBillSplitBaseDao payBillSplitBaseDao;
	public void setPayBillSplitBaseDao(IPayBillSplitBaseDao payBillSplitBaseDao) {
		this.payBillSplitBaseDao = payBillSplitBaseDao;
	}

	@Resource
	private IPayBillService payBillService;
	@Resource
	private IGroupBuildingBillCycleBaseService groupBuildingBillCycleBaseService;
	@Resource
	private IGroupBuildingExtBaseDao groupBuildingExtBaseDao;
	@Resource
	private IGroupBuildingBillCycleBaseDao groupBuildingBillCycleBaseDao;

	@Override
	public IBusinessMonthYear getBusinessMonthYearByPayTime(BigInteger userId,
			Date date, BigInteger billTypeId) {
		BigInteger gbId = getGroupBuildingIdByUserId(userId);
		return plotpropertyCfgDao.selectBusinessMonthYearByPayTime(gbId,date,billTypeId);
	}

	@Override
	public BigInteger getGroupBuildingIdByUserId(BigInteger userId){
		return plotpropertyCfgDao.selectGroupBuildingIdByUserId(userId);
	}
	
	@Override
	public IBusinessMonthYear getLastPayBillMonthYear(BigInteger userId, BigInteger billTypeId) {
//		GroupBuilding currGroupBuilding = null;//commonRoomService.getGroupBuildingByUserId(userId);
//		return new BusinessMonthYearWithGB(dualDao.getNowTime(), currGroupBuilding,MonthOrTime.time);
		Date date = ApplicationContextBothUtil.getDualDao().getNowDate();
		BigInteger gbId = getGroupBuildingIdByUserId(userId);
		GroupBuilding groupBuilding = groupBuildingBaseDao.selectGroupBuildingBySeqId(gbId);
		if(billTypeId!=null&&billTypeId.compareTo(PlotpropertyConstant.DEFAULT_PAYBILL_FEE_TYPEID)==0){
			date = PropertyPeriodCalculateUtil.getDiscountMonthByNowTime(date, groupBuilding.getPayPeriodEnd());
		}
		return plotpropertyCfgDao.selectBusinessMonthYearByPayTime(gbId,date,billTypeId);
	}

	@Override
	public PayBillInfo getPayBillSimpleInfoByMonth(BigInteger userId,IBusinessMonthYear monthYearWithGB,BigInteger billTypeId) {
		DataType.ISectionDate billMonth = monthYearWithGB==null?null:monthYearWithGB.getBillMonth();
		PayBillInfo payBillEntity = null;
		if(billMonth!=null){
			payBillEntity = plotpropertyCfgDao.selectPayBillByBillMonth(userId, billTypeId,billMonth);
		}
		return payBillEntity;
	}
	
	@Override
	public PayBillInfo getPayBillSimpleInfoById(BigInteger billId) {
		return plotpropertyCfgDao.selectPayBillByBillId(billId);
	}
	
	@Override
	public List<PayBillInfo> getIsPayBillList(BigInteger userId,
			PageModel pageModel, BigInteger billTypeId) {
		boolean isPay = true;
		return plotpropertyCfgDao.selectPayBillList(userId, isPay, pageModel, billTypeId);
	}
	
	@Override
	public List<PropertyBillInfo> getIsPayBillList(BigInteger userId, PageModel pageModel) {
		return plotpropertyCfgDao.selectPayBillList(userId, pageModel);
	}

	@Override
	public List<PayBillInfo> getNotPayBillList(BigInteger userId,
			PageModel pageModel, BigInteger billTypeId) {
		boolean isPay = false;
		return plotpropertyCfgDao.selectPayBillList(userId, isPay, pageModel, billTypeId);
	}

	@Override
	public List<PayBillType> getPayBillTypeListAll(BigInteger gbId) {
		boolean useDefaultIfNull = true;
		return plotpropertyCfgDao.selectPayBillTypeListAll(gbId,useDefaultIfNull);
	}
	
	@Override
	public List<PayBillType> getPayBillTypeListAll(BigInteger gbId, boolean useDefaultIfNull) {
		return plotpropertyCfgDao.selectPayBillTypeListAll(gbId,useDefaultIfNull);
	}
	
	@Override
	public IBusinessMonthYear getBusinessMonthYearByBillMonth(
			BigInteger gbId, Date billDate, BigInteger billTypeId) {
		return plotpropertyCfgDao.selectBusinessMonthYearByBillMonth(gbId, billDate, billTypeId);
	}

	@Override
	public PayBillType getPropPayBillTypeInfoByCondition(BigInteger userId,BigInteger gbId,boolean useDefaultIfNull) {
		if(gbId==null){gbId = getGroupBuildingIdByUserId(userId);}
		BigInteger billTypeId = plotpropertyCfgDao.selectPropPayBillTypeByGbId(gbId,useDefaultIfNull);
		return payBillTypeBaseDao.selectPayBillTypeBySeqId(billTypeId);
	}
	
//	@Override
//	public BigInteger getPropPayBillTypeByUserId(BigInteger userId,boolean useDefaultIfNull) {
//		BigInteger gbId = getGroupBuildingIdByUserId(userId);
//		return getPropPayBillTypeByGbId(gbId,useDefaultIfNull);
//	}
//	
//	@Override
//	public BigInteger getPropPayBillTypeByGbId(BigInteger gbId,boolean useDefaultIfNull) {
//		return plotpropertyCfgDao.selectPropPayBillTypeByGbId(gbId,useDefaultIfNull);
//	}
	
	@Override
	public List<PropFeeDiscount> getLeastPrizeRecordListByBillId(BigInteger billId,BigInteger userId) {
		generatePayBillSplit(billId);//尝试创建数据
		return plotpropertyCfgDao.selectLeastPrizeRecordListByBillId(billId,userId);
	}

	@Override
	public PayBillType getPropBillTypeInfoByTypeIdAndPropIfNull(
			BigInteger typeId, BigInteger gbId,boolean useDefaultIfNull) {
		if(typeId==null){
			typeId = plotpropertyCfgDao.selectPropPayBillTypeByGbId(gbId,useDefaultIfNull);
		}
		return payBillTypeBaseDao.selectPayBillTypeBySeqId(typeId);
	}
	
	/**
	 * 校验名称是否有重复
	 * @param gbId
	 * @param typeName
	 * @param ignoreTypeId
	 */
	private void checkRepeatName(BigInteger gbId,String typeName,BigInteger ignoreTypeId,boolean isProp){
		if(StringUtils.isEmpty(typeName)){
			throw new BusinessRuntimeException("PlotpropertyCfgService.checkRepeatName.empty");
		}else if(!isProp && typeName.equals(PlotpropertyConstant.DEFAULT_PAYBILL_FEE_NAME)){//不能等于默认配置的 name
			throw new BusinessRuntimeException("PlotpropertyCfgService.checkRepeatName.isSys");
		}else{
			Integer count = plotpropertyCfgDao.selectExistBillTypeByCondition(gbId,typeName,ignoreTypeId);
			if(count!=null&&count>0){
				throw new BusinessRuntimeException("PlotpropertyCfgService.checkRepeatName.exist");
			}
		}
	}
	
	/**
	 * 对应类别若已存在账单则提示错误不可更改
	 * @param gbId
	 * @param billTypeId
	 */
	public void checkExistBillCount(BigInteger gbId, BigInteger billTypeId){
		Integer count = plotpropertyCfgDao.selectExistBillCountByTypeId(gbId,billTypeId);
		if(count!=null&&count>0){
			throw new BusinessRuntimeException("PlotpropertyCfgService.checkExistBillCount.exist");
		}
	}
	
	private String getIcon(BigInteger gbId,String typeName,RequestFileEntity shareIconImage,String defaultStr){
		String shareIcon = defaultStr;
		if(shareIconImage!=null&&shareIconImage.getFileContent()!=null){//生成文件名
			try {
				String pinyinName = PinyinUtil.hanyuToPinyinSimple(typeName, true);
				shareIcon = new StringBuffer().append("feeType")
						.append("_").append(pinyinName)
						.append("_").append(gbId)
						.append(".").append("png"/*shareIconImage.getFileType()*/).toString();
				String actBasePath = ApplicationContextBothUtil.getSysParamManager().getSysParaValue(SysParamKey.COMMUNITY_SUPPLY_TYPE_ICO_BASEPATH);
				String serverPath = actBasePath+shareIcon;
				ApplicationContextBothUtil.getFileServerService().uploadFile(serverPath, shareIconImage.getFileContent());
			} catch (Exception e) {
				logger.info("upload addPayBillTypeBase file cause exception:"+e.getMessage(), e);
				throw new BusinessRuntimeException("PlotpropertyCfgService.uploadIcon.error",new Object[]{shareIconImage.getFileName()});
			}
		}
		return shareIcon;
	}
	
	private BigInteger addPayBillTypeBase(BigInteger gbId, String typeName,Integer activeStatus,Integer isPropFee,Integer paytimeType,RequestFileEntity shareIconImage,Integer preferStatus){
		boolean isProp = isPropFee.compareTo(PlotpropertyDict.PayBillType_IsPropFee.YES) == 0;
		checkRepeatName(gbId, typeName, null,isProp);//校验名称唯一性
		//准备新增的数据
		String lastUpdTime = ApplicationContextBothUtil.getDualDao().getNowTime();
		PayBillType toAdd = new PayBillType();
		toAdd.setActiveStatus(activeStatus);
		toAdd.setGbId(gbId);
		toAdd.setIcon(getIcon(gbId, typeName, shareIconImage,PlotpropertyConstant.DEFAULT_PAYBILL_FEE_TYPEICON));
		toAdd.setIsPropFee(isPropFee);
		toAdd.setLastUpdTime(lastUpdTime);
		toAdd.setName(typeName);
		toAdd.setPaytimeType(paytimeType);
		toAdd.setPreferStatus(preferStatus);
		
		//提交新增
		BigInteger addId = ApplicationContextBothUtil.getUuidManager().getNextUuidBigInteger(SEQConstants.t_pay_bill_type);
		toAdd.setId(addId);
		Integer resCount = payBillTypeBaseDao.insertPayBillType(toAdd);
		if(resCount==null||resCount<=0){
			throw new BusinessRuntimeException("PlotpropertyCfgService.addPayBillTypeBase.count0");
		}
		return addId;
	}
	
	@Override
	@Transactional
	public BigInteger addPayBillType(BigInteger gbId, String typeName,Integer activeStatus,RequestFileEntity shareIconImage,Integer preferStatus) {
		Integer isPropFee = PlotpropertyDict.PayBillType_IsPropFee.NO_notMR;//默认非物业费
		Integer paytimeType = PlotpropertyDict.PayBillType_PaytimeType.PEORID;//默认周期缴费
		BigInteger addId = addPayBillTypeBase(gbId, typeName, activeStatus, isPropFee, paytimeType,shareIconImage,preferStatus);
		return addId;
	}
	
	private void updPayBillTypeBase(BigInteger gbId, BigInteger billTypeId,
			String typeName, Integer activeStatus,boolean isProp,Integer paytimeType,RequestFileEntity shareIconImage,Integer preferStatus) {
		if(PlotpropertyConstant.DEFAULT_PAYBILL_FEE_TYPEID.compareTo(billTypeId)==0){
			throw new BusinessRuntimeException("PlotpropertyCfgService.updPayBillTypeBase.defaultId.error");
		}
		//已开启的不可编辑
		if(!StringUtils.isEmpty(typeName)){
			checkRepeatName(gbId, typeName, billTypeId,isProp);
//			checkExistBillCount(gbId, billTypeId);
		}
		//准备更新数据
		String lastUpdTime = ApplicationContextBothUtil.getDualDao().getNowTime();
		PayBillType toUpd = new PayBillType();
		toUpd.setActiveStatus(activeStatus);
		toUpd.setId(billTypeId);
		toUpd.setGbId(gbId);
		toUpd.setIcon(getIcon(gbId, typeName, shareIconImage,null));
		toUpd.setLastUpdTime(lastUpdTime);
		toUpd.setName(typeName);
		toUpd.setPaytimeType(paytimeType);
		toUpd.setPreferStatus(preferStatus);
		//提交更新
		Integer resCount = payBillTypeBaseDao.updatePayBillType(toUpd);
		if(resCount==null||resCount<=0){
			throw new BusinessRuntimeException("PlotpropertyCfgService.updPayBillType.count0");
		}
	
	}
	@Override
	@Transactional
	public void updPayBillType(BigInteger gbId, BigInteger billTypeId,
			String typeName, Integer activeStatus,boolean isProp,RequestFileEntity shareIconImage,Integer preferStatus) {
		Integer paytimeType = PlotpropertyDict.PayBillType_PaytimeType.PEORID;
		updPayBillTypeBase(gbId, billTypeId, typeName, activeStatus, isProp, paytimeType,shareIconImage,preferStatus);
	}

	@Override
	@Transactional
	public void delPayBillType(BigInteger gbId, BigInteger billTypeId) {
		//不是默认类别Id
		if(billTypeId.compareTo(PlotpropertyConstant.DEFAULT_PAYBILL_FEE_TYPEID)==0){
			throw new BusinessRuntimeException("PlotpropertyCfgService.delPayBillType.defaultTypeId");
		}
		//不存在该类别的历史账单
		checkExistBillCount(gbId, billTypeId);
		//执行删除
		Integer resCount = payBillTypeBaseDao.deletePayBillTypeLogic(billTypeId);
		if(resCount==null||resCount<=0){
			throw new BusinessRuntimeException("PlotpropertyCfgService.delPayBillType.count0");
		}
	}
	
	/**
	 * 还原为周期缴费默认配置
	 * @param gbId
	 * @param activeStatus
	 * @param isPrefer 
	 * @param verificationStatus 
	 */
	private void updPropPayBillTypeHistoryMonthDefault(EditPropPayBillTypeEntity editPropPayBillTypeEntity){
		updPropPayBillTypeHistoryMonth(editPropPayBillTypeEntity);
	}
	/**
	 * 更新历史月缴配置信息
	 * @param isPrefer 
	 * @param verificationStatus 
	 */
	private void updPropPayBillTypeHistoryMonth(EditPropPayBillTypeEntity editPropPayBillTypeEntity){
		GroupBuilding gbUpd = new GroupBuilding();
		gbUpd.setId(editPropPayBillTypeEntity.getGbId());
		gbUpd.setPayPeriodStart(editPropPayBillTypeEntity.getPayPeriodStart());
		gbUpd.setPayPeriodEnd(editPropPayBillTypeEntity.getPayPeriodEnd());
		gbUpd.setPropertyMonthChange(editPropPayBillTypeEntity.getPropertyMonthChange());
		Integer propfeeCanpay = editPropPayBillTypeEntity.getActiveStatus().compareTo(PlotpropertyDict.PayBillType_ActiveStatus.OPEN)==0?PlotpropertyDict.GroupBuilding_PropfeeCanpay.YES:PlotpropertyDict.GroupBuilding_PropfeeCanpay.NO;
		gbUpd.setPropfeeCanpay(propfeeCanpay);
		if(editPropPayBillTypeEntity.getIsPrefer() == null) {
			gbUpd.setIsPrefer(0);
		} else {
			gbUpd.setIsPrefer(editPropPayBillTypeEntity.getIsPrefer());
		}
		gbUpd.setPreferName("随机立减");
		gbUpd.setVerificationStatus(editPropPayBillTypeEntity.getVerificationStatus());
		gbUpd.setPropertyPeriodType(editPropPayBillTypeEntity.getPropertyPeriodType());
		//判断是否为选择周期,才需要填充月份信息
		if (editPropPayBillTypeEntity.getPropertyPeriodType() != null && editPropPayBillTypeEntity.getPropertyPeriodType().equals(2)) {
			gbUpd.setPeriodMonths(editPropPayBillTypeEntity.getPeriodMonths());
		}

		Integer resCount = groupBuildingBaseDao.updateGroupBuilding(gbUpd);
		if(resCount==null||resCount<=0){
			throw new BusinessRuntimeException("PlotpropertyCfgService.updPropPayBillTypeHistoryMonth.count0");
		}
	}
	
	@Override
	@Transactional
	public void editPropPayBillType(EditPropPayBillTypeEntity editPropPayBillTypeEntity) {
		PayBillType existPayBillType = getPayBillTypeForImport(editPropPayBillTypeEntity.getGbId(), PlotpropertyConstant.DEFAULT_PAYBILL_FEE_NAME, true);
		if(existPayBillType!=null){
			if(existPayBillType.getPaytimeType().compareTo(editPropPayBillTypeEntity.getPaytimeType())!=0){//已存在类别且待修改的类别与改类别不一致
				checkExistBillCount(editPropPayBillTypeEntity.getGbId(), existPayBillType.getId());//已存在改类别账单,则不可修改
			}
		}
		//不存在类别 or 已存在相同PaytimeType的类别配置 or 已存在不同类别的配置 但是没有历史数据
		
		//获取小区已存在的物业费配置- 无配置，历史月缴的配置，已添加的周期缴费配置
		if(editPropPayBillTypeEntity.getPaytimeType().compareTo(PlotpropertyDict.PayBillType_PaytimeType.MONTH)==0){
			//为空或者为默认月缴
			if(existPayBillType==null || existPayBillType.getId().compareTo(PlotpropertyConstant.DEFAULT_PAYBILL_FEE_TYPEID)==0){
				//历史月缴配置新增
				updPropPayBillTypeHistoryMonth(editPropPayBillTypeEntity);
			}else{
				//删除历史周期缴配置信息
				delPayBillType(editPropPayBillTypeEntity.getGbId(), existPayBillType.getId());
				//历史月缴配置更新
				updPropPayBillTypeHistoryMonth(editPropPayBillTypeEntity);
				//还原月度缴费信息（周期 -- 月度）
				payBillService.updateBillCycle(editPropPayBillTypeEntity.getGbId(), PlotpropertyConstant.DEFAULT_PAYBILL_FEE_TYPEID, 0);
			}
		}else if(editPropPayBillTypeEntity.getPaytimeType().compareTo(PlotpropertyDict.PayBillType_PaytimeType.PEORID)==0){

			editPropPayBillTypeEntity.setPayPeriodStart(PlotpropertyConstant.PERID_GBCFG_LIMITSTAT_DEFAULT);
			editPropPayBillTypeEntity.setPayPeriodEnd(PlotpropertyConstant.PERID_GBCFG_LIMITEND_DEFAULT);
			editPropPayBillTypeEntity.setPropertyMonthChange(PlotpropertyConstant.PERID_GBCFG_PROPERTYMONTHCHANGE_DEFAULT);

			if(existPayBillType==null || existPayBillType.getId().compareTo(PlotpropertyConstant.DEFAULT_PAYBILL_FEE_TYPEID)==0){
				//小区start end canPay 1,0,activeStatus
				updPropPayBillTypeHistoryMonthDefault(editPropPayBillTypeEntity);
				//新增周期缴,更新小区是否可缴费状态信息;物业费周期缴费的是否开启随机立减需要增加pay_bill_type的随机立减状态
				addPayBillTypeBase(editPropPayBillTypeEntity.getGbId(), PlotpropertyConstant.DEFAULT_PAYBILL_FEE_NAME, editPropPayBillTypeEntity.getActiveStatus(), PlotpropertyDict.PayBillType_IsPropFee.YES, editPropPayBillTypeEntity.getPaytimeType(),null,editPropPayBillTypeEntity.getIsPrefer());
				//冻结月度账单信息（月度 -- 周期）
				updateBillCycleAndPayBill(editPropPayBillTypeEntity.getGbId(), PlotpropertyConstant.DEFAULT_PAYBILL_FEE_TYPEID,1);
			}else{
				//小区start end canPay 1,0,activeStatus,删除历史月缴配置信息
				updPropPayBillTypeHistoryMonthDefault(editPropPayBillTypeEntity);
				//修改周期缴,更新小区是否可缴费状态信息;物业费周期缴费的是否开启随机立减需要更新pay_bill_type的随机立减状态
				boolean isProp = true;
				updPayBillTypeBase(editPropPayBillTypeEntity.getGbId(), existPayBillType.getId(), PlotpropertyConstant.DEFAULT_PAYBILL_FEE_NAME, editPropPayBillTypeEntity.getActiveStatus(),isProp,PlotpropertyDict.PayBillType_PaytimeType.PEORID,null,editPropPayBillTypeEntity.getIsPrefer());
				//处理账单（删除未缴费账单）和账期信息（冻结账期操作）
				//固定周期到选择周期
				if(editPropPayBillTypeEntity.getPropertyPeriodType() != null && editPropPayBillTypeEntity.getPropertyPeriodType().equals(2)) {
					updateBillCycleAndPayBill(editPropPayBillTypeEntity.getGbId(), existPayBillType.getId(),1);
				} else {//选择周期到固定周期  恢复选择周期的数据
					payBillService.updateBillCycle(editPropPayBillTypeEntity.getGbId(), existPayBillType.getId(), 0);
				}
			}
		}

		//如果物业费关闭随机立减，则把其他所有的费用类型的随机立减关闭
		if (editPropPayBillTypeEntity.getIsPrefer() != null && editPropPayBillTypeEntity.getIsPrefer() == 0) {
			List<PayBillType> otherTypeList = getPayBillTypeList(editPropPayBillTypeEntity.getGbId(), false);
			List<Map<String,Object>> tmpList = new ArrayList<Map<String,Object>>();
			if(otherTypeList!=null&&otherTypeList.size()>0){
				for(PayBillType tmp:otherTypeList){
					tmp.setPreferStatus(0);
					payBillTypeBaseDao.updatePayBillType(tmp);
				}
			}
		}
	}

	/**
	 * 更新账期和账单 冻结账期
	 * @param gbId
	 * @param payBillTypeId
     */
	private void updateBillCycleAndPayBill(BigInteger gbId, BigInteger payBillTypeId, int operateStatus) {
		//锁定账期
		payBillService.updateBillCycle(gbId, payBillTypeId, operateStatus);
		//清空账单
		List<BigInteger> billCycleIds = payBillService.getGroupBuildingBillCycleByTypeId(gbId, payBillTypeId);
		if(billCycleIds != null && billCycleIds.size() > 0) {
			payBillService.updatePayBillByCycleIds(billCycleIds);
		}
	}

	@Override
	public List<PayBillType> getPayBillTypeList(BigInteger gbId, boolean isProp) {
		List<PayBillType> payBillTypeList = plotpropertyCfgDao.selectPayBillTypeListByCondition(gbId, isProp);
		return payBillTypeList;
	}
	
	@Override
	public PayBillType getPayBillTypeForImport(BigInteger gbId,
			String typeName, boolean isProp) {
		PayBillType payBillType = null;
		if(!StringUtils.isEmpty(typeName)){
			Integer isPropFee = isProp?PlotpropertyDict.PayBillType_IsPropFee.YES:PlotpropertyDict.PayBillType_IsPropFee.NO_notMR;
			payBillType = plotpropertyCfgDao.selectPayBillTypeNewByCondition(gbId,typeName,isPropFee);
		}
		if(payBillType==null && isProp){//是物业费尝试获取历史月缴配置
			BigInteger billTypeId = plotpropertyCfgDao.selectPropPayBillTypeHistoryMonth(gbId);
			if(billTypeId!=null){
				payBillType = payBillTypeBaseDao.selectPayBillTypeBySeqId(billTypeId);
			}
		}
		return payBillType;
	}

	@Override
	public Integer markDiscountAsUsed(List<PayBillSplit> toUpdPayBillSplitList, BigInteger billId,Integer usedType) {
		return plotpropertyCfgDao.markDiscountAsUsed(toUpdPayBillSplitList,billId,usedType);
	}

	@Override
	public List<PayBillSplit> generatePayBillSplit(BigInteger paybillId) {
		List<PayBillSplit> resList = new ArrayList<PayBillSplit>();
		PayBillInfo payBill = plotpropertyCfgDao.selectPayBillByBillId(paybillId);
		if(payBill!=null){
			//检查是否已拆分
			List<PayBillSplit> existList = plotpropertyCfgDao.selectPayBillSplitListByBillId(paybillId);
			if(existList!=null&&existList.size()>0){//已拆分则返回拆分的
				resList = existList;
			}else if(payBill.getIsPropFee().compareTo(PlotpropertyDict.PayBillType_IsPropFee.YES)==0
					&&payBill.getPaytimeType().compareTo(PlotpropertyDict.PayBillType_PaytimeType.PEORID)==0){//未拆分则判断是否为物业费且周期账单
				Double manageFee = 0.0;//查询管理费总额
				List<PropertyFeeDetail> propertyFeeDetailList = payBill.getPropertyFeeDetailList();
				for(PropertyFeeDetail tmpPropertyFeeDetail:propertyFeeDetailList){
					if(PlotpropertyDict.PropertyFeeDetail_Type.ManagerFee.compareTo(tmpPropertyFeeDetail.getType())==0){
						manageFee = tmpPropertyFeeDetail.getTotalPrice();
						break;
					}
				}
				if(manageFee!=null&&manageFee>0){//管理费大于0
					int splitSize = payBill.getBillMonthSize().intValue();
					
					Date payTimeEndDate = null;
					Date billMonthEndDate = null;
					try {
						payTimeEndDate = DateUtil.formatSecond.get().parse(payBill.getPayDayEnd());
						billMonthEndDate = DateUtil.formatSecond.get().parse(payBill.getBillMonthEnd());
					} catch (ParseException e) {
						e.printStackTrace();
					}

					Calendar billCa = Calendar.getInstance();
					billCa.setTime(billMonthEndDate);
					Calendar payCa = Calendar.getInstance();
					payCa.setTime(payTimeEndDate);
					billCa.add(Calendar.MONTH, -splitSize);
					payCa.add(Calendar.MONTH, -splitSize);
					
					BigInteger parentBillId = payBill.getId();
					String nameDesc = payBill.getBillTypeName();
					Double manageAmount = manageFee/splitSize;
					Double sum = 0.0;
					for(int i=0;i<splitSize;i++){
						//计算各个月份及金额
						billCa.add(Calendar.MONTH, 1);
						payCa.add(Calendar.MONTH, 1);
						String billSubMonth = DateUtil.formatSecond.get().format(billCa.getTime());
						String discountMonth = DateUtil.formatSecond.get().format(payCa.getTime());
						PayBillSplit tmpSplit = new PayBillSplit();
						tmpSplit.setBillSubMonth(billSubMonth);
						tmpSplit.setDiscountMonth(discountMonth);
						if(i==(splitSize-1)){//最后一个月
							tmpSplit.setManageAmount((BigDecimal.valueOf(manageFee).subtract(BigDecimal.valueOf(sum))).longValue());
						}else{
							tmpSplit.setManageAmount(manageAmount.longValue());
							sum+=manageAmount;
						}
						
						tmpSplit.setNameDesc(nameDesc);
						tmpSplit.setParentBillId(parentBillId);
//						tmpSplit.setDiscount(discount);
//						tmpSplit.setId(id);
//						tmpSplit.setPrizeRecordId(prizeRecordId);
//						tmpSplit.setSuccPayAmount(succPayAmount);
						resList.add(tmpSplit);
					}
					if(resList!=null&&resList.size()>0){
						List<BigInteger> splitAddIdList = ApplicationContextBothUtil.getUuidManager().getNextUuidBigInteger(SEQConstants.t_pay_bill_split, splitSize);
						for(int i=0;i<resList.size();i++){
							resList.get(i).setId(splitAddIdList.get(i));
						}
						payBillSplitBaseDao.insertPayBillSplitBatch(resList);//批量新增
					}
				}
			}
		}
		return resList;
	}

	@Override
	public String getPayBillPayTime(BigInteger billTypeId) {
		return plotpropertyCfgDao.selectPayBillTimeCfg(billTypeId);
	}

	@Override
	public void editPropPayBillOpenAttribute(BigInteger gbId, Integer activeStatus, Integer verificationStatus,
			Integer isPrefer) {
		GroupBuilding gbUpd = new GroupBuilding();
		gbUpd.setId(gbId);
		Integer propfeeCanpay = activeStatus.compareTo(PlotpropertyDict.PayBillType_ActiveStatus.OPEN)==0?PlotpropertyDict.GroupBuilding_PropfeeCanpay.YES:PlotpropertyDict.GroupBuilding_PropfeeCanpay.NO;
		gbUpd.setPropfeeCanpay(propfeeCanpay);
		if(isPrefer == null) {
			gbUpd.setIsPrefer(0);
		} else {
			gbUpd.setIsPrefer(isPrefer);
		}
		gbUpd.setPreferName("随机立减");
		gbUpd.setVerificationStatus(verificationStatus);
		Integer resCount = groupBuildingBaseDao.updateGroupBuilding(gbUpd);
		if(resCount==null||resCount<=0){
			throw new BusinessRuntimeException("PlotpropertyCfgService.updPropPayBillTypeHistoryMonth.count0");
		}
	}

	@Override
	public BigInteger addMRPayBillType(BigInteger gbId, String typeName, Integer activeStatus, Integer preferStatus) {
		PayBillType payBillTypeQry = new PayBillType();
		payBillTypeQry.setGbId(gbId);
		payBillTypeQry.setIsPropFee(PlotpropertyDict.PayBillType_IsPropFee.NO_MR);
		payBillTypeQry.setPaytimeType(PlotpropertyDict.PayBillType_PaytimeType.PEORID);
		List<PayBillType> payBillTypeList = payBillTypeBaseDao.selectPayBillTypeByCondition(MapConverter.toMap(payBillTypeQry), false);
		if (payBillTypeList.isEmpty()) {
			checkRepeatName(gbId, typeName, null,false);//校验名称唯一性
			Integer isPropFee = PlotpropertyDict.PayBillType_IsPropFee.NO_MR;// 默认非物业费
			Integer paytimeType = PlotpropertyDict.PayBillType_PaytimeType.PEORID;// 默认周期缴费
			BigInteger addId = addPayBillTypeBase(gbId, typeName, activeStatus, isPropFee, paytimeType, null, preferStatus);
			return addId;
		} else {
			checkRepeatName(gbId, typeName, payBillTypeList.get(0).getId(),false);//校验名称唯一性
			PayBillType pbt = new PayBillType();
			pbt.setId(payBillTypeList.get(0).getId());
			pbt.setName(typeName);
			pbt.setActiveStatus(activeStatus);
			pbt.setPreferStatus(preferStatus);
			payBillTypeBaseDao.updatePayBillType(pbt);
			return pbt.getId();
		}
	}

	@Override
	@Transactional(propagation = Propagation.NESTED)
	public void saveGbConfig(GroupBuildingCfgEntity groupBuildingCfgEntity) {
		GroupBuilding groupBuilding = groupBuildingBaseDao.selectGroupBuildingBySeqId(groupBuildingCfgEntity.getId());
		GroupBuildingExt groupBuildingExt = groupBuildingExtBaseDao.selectGroupBuildingExtBySeqId(groupBuildingCfgEntity.getId());

		groupBuilding.setPropfeeCanpay(groupBuildingCfgEntity.getPropfeeCanpay());
		groupBuilding.setVerificationStatus(groupBuildingCfgEntity.getVerificationStatus());
		groupBuilding.setIsPrefer(groupBuildingCfgEntity.getIsPrefer());
		groupBuilding.setPreferName("随机立减");
		groupBuilding.setPropertyPeriodType(groupBuildingCfgEntity.getPropertyPeriodType());
		groupBuildingBaseDao.updateGroupBuilding(groupBuilding);

		if(groupBuildingExt == null) {//保证 扩展表和主表的id是一致的
			groupBuildingExt = new GroupBuildingExt();
			groupBuildingExt.setId(groupBuilding.getId());
			
			groupBuildingExt.setFixedFeeStatus(groupBuildingCfgEntity.getFixedFeeStatus());
			groupBuildingExt.setMeterFeeStatus(groupBuildingCfgEntity.getMeterFeeStatus());
			groupBuildingExt.setTempFeeStatus(groupBuildingCfgEntity.getTempFeeStatus());
			groupBuildingExt.setOpenBankCollection(groupBuildingCfgEntity.getOpenBankCollection());
			groupBuildingExt.setPayToPc(groupBuildingCfgEntity.getIsPayToPc());
			groupBuildingExtBaseDao.insertGroupBuildingExt(groupBuildingExt);
		} else {
			groupBuildingExt.setFixedFeeStatus(groupBuildingCfgEntity.getFixedFeeStatus());
			groupBuildingExt.setMeterFeeStatus(groupBuildingCfgEntity.getMeterFeeStatus());
			groupBuildingExt.setTempFeeStatus(groupBuildingCfgEntity.getTempFeeStatus());
			groupBuildingExt.setOpenBankCollection(groupBuildingCfgEntity.getOpenBankCollection());
			groupBuildingExt.setPayToPc(groupBuildingCfgEntity.getIsPayToPc());
			groupBuildingExtBaseDao.updateGroupBuildingExt(groupBuildingExt);
		}

		//更新小区对应的账期包含的费用类型的信息
		String modelStr = "";
		if(groupBuildingCfgEntity.getMeterFeeStatus() != null && groupBuildingCfgEntity.getMeterFeeStatus() == 1) {
			modelStr +="1,";
		}
		if(groupBuildingCfgEntity.getMeterFeeStatus() != null && groupBuildingCfgEntity.getFixedFeeStatus() == 1) {
			modelStr +="2,";
		}
		if(groupBuildingCfgEntity.getMeterFeeStatus() != null && groupBuildingCfgEntity.getTempFeeStatus() == 1) {
			modelStr +="3,";
		}
		if(modelStr.length() == 0) {
			modelStr = ",";
		}
		Map<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("tGroupBuildingId",groupBuildingCfgEntity.getId());
		List<GroupBuildingBillCycle> buildingBillCycleList = groupBuildingBillCycleBaseDao.selectGroupBuildingBillCycleByCondition(paraMap, false);
		if(!DataUtil.isEmpty(buildingBillCycleList)) {
			for (GroupBuildingBillCycle groupBuildingBillCycle : buildingBillCycleList) {
				GroupBuildingBillCycle groupBuildingBillCycle1 = new GroupBuildingBillCycle();
				groupBuildingBillCycle1.setId(groupBuildingBillCycle.getId());
				groupBuildingBillCycle1.setFeeType(modelStr);
				groupBuildingBillCycle = groupBuildingBillCycle1;
			}
			groupBuildingBillCycleBaseDao.updateGroupBuildingBillCycleBatch(buildingBillCycleList);
		}
	}
}
