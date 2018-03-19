package com.cnfantasia.server.commbusi.plotproperty.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.commbusi.plotproperty.entity.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.cnfantasia.server.api.plotproperty.constant.PlotpropertyConstant;
import com.cnfantasia.server.api.plotproperty.constant.PlotpropertyDict;
import com.cnfantasia.server.api.plotproperty.entity.PropertyBillInfo;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.page.PageQueryUtil;
import com.cnfantasia.server.business.pub.utils.DateUtil;
import com.cnfantasia.server.common.exception.BusinessRuntimeException;
import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.domainbase.groupBuilding.dao.IGroupBuildingBaseDao;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.payBillSplit.dao.IPayBillSplitBaseDao;
import com.cnfantasia.server.domainbase.payBillSplit.entity.PayBillSplit;
import com.cnfantasia.server.domainbase.payBillTimeCfg.entity.PayBillTimeCfg;
import com.cnfantasia.server.domainbase.payBillType.dao.IPayBillTypeBaseDao;
import com.cnfantasia.server.domainbase.payBillType.entity.PayBillType;


public class PlotpropertyCfgDao extends AbstractBaseDao implements IPlotpropertyCfgDao{
	private Log logger = LogFactory.getLog(getClass());
	private IGroupBuildingBaseDao groupBuildingBaseDao;
	public void setGroupBuildingBaseDao(IGroupBuildingBaseDao groupBuildingBaseDao) {
		this.groupBuildingBaseDao = groupBuildingBaseDao;
	}
	
	private IPayBillTypeBaseDao payBillTypeBaseDao;
	public void setPayBillTypeBaseDao(IPayBillTypeBaseDao payBillTypeBaseDao) {
		this.payBillTypeBaseDao = payBillTypeBaseDao;
	}
	
	private IPayBillSplitBaseDao payBillSplitBaseDao;
	public void setPayBillSplitBaseDao(IPayBillSplitBaseDao payBillSplitBaseDao) {
		this.payBillSplitBaseDao = payBillSplitBaseDao;
	}

	@Override
	public PayBillInfo selectPayBillByBillMonth(BigInteger userId,BigInteger billTypeId, DataType.ISectionDate billMonth) {
		PayBillInfo payBillEntity = null;
		List<PayBillInfo> tmpPayBillEntityList = null;
		if(billMonth instanceof ISectionDateSignal){//月缴账单
			ISectionDateSignal tmpSds = (ISectionDateSignal)billMonth;
			Map<String,Object> tmpMap = new HashMap<String, Object>();
			tmpMap.put("userId", userId);
			tmpMap.put("billTypeId", billTypeId);
			tmpMap.put("yearMonth", tmpSds.getTimeStr(DateUtil.formatMonth.get()));
			tmpPayBillEntityList = sqlSession.selectList("plotpropertyCfg.select_PayBill_Signal_ByMonth", tmpMap);
		}else if(billMonth instanceof ISectionDateMulti){//周期账单
			ISectionDateMulti tmpSds = (ISectionDateMulti)billMonth;
			BigInteger gbId = selectGroupBuildingIdByUserId(userId);
			PayBillTimeCfg cfg = selectPayBillTimeCfgByStartEndBillMonth(gbId, tmpSds.getStartDate(), tmpSds.getEndDate(), billTypeId);
			if(cfg==null){
				return null;
//				throw new BusinessRuntimeException("PlotpropertyCfgDao.selectPayBillByBillMonth.cfg.null",new Object[]{gbId,billTypeId,tmpSds.getPeriodDesc()});
			}
			Map<String,Object> tmpMap = new HashMap<String, Object>();
			tmpMap.put("userId", userId);
			tmpMap.put("billTypeId", billTypeId);
			tmpMap.put("cfgId", cfg.getId());
			tmpPayBillEntityList = sqlSession.selectList("plotpropertyCfg.select_PayBill_Multi_ByBillMonth", tmpMap);
		}

		if (tmpPayBillEntityList != null && tmpPayBillEntityList.size() > 0) {
			if (tmpPayBillEntityList.size() == 1) {
				payBillEntity = tmpPayBillEntityList.get(0);
			} else {
				String timeStr = billMonth.getPeriodDesc();
				throw new BusinessRuntimeException("PlotpropertyCfgDao.selectPayBillByBillMonth.multi.error", new Object[]{billTypeId, timeStr});
			}
		}
		return payBillEntity;
	}
	
	@Override
	public PayBillInfo selectPayBillByBillId(BigInteger billId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("billId", billId);
		return sqlSession.selectOne("plotpropertyCfg.select_PayBill_By_BillId", tmpMap);
	}
	
	@Override
	public BigInteger selectGroupBuildingIdByUserId(BigInteger userId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		return sqlSession.selectOne("plotpropertyCfg.select_GroupBuilding_By_UserId", tmpMap);
	}
	
	@Override
	public IBusinessMonthYear selectBusinessMonthYearByPayTime(BigInteger gbId,Date payTime, BigInteger billTypeId) {
		IBusinessMonthYear resBMY = null;
		GroupBuilding groupBuilding = groupBuildingBaseDao.selectGroupBuildingBySeqId(gbId);
		PayBillType payBillType = payBillTypeBaseDao.selectPayBillTypeBySeqId(billTypeId);
		if(PlotpropertyDict.PayBillType_PaytimeType.MONTH.compareTo(payBillType.getPaytimeType())==0){//如果为月缴
			resBMY = BusinessMonthYearFactory.newInstanceByPayTime(payTime, groupBuilding);//根据缴费时间
		}else{//如果为周期缴费
			PayBillTimeCfg payBillTimeCfg = selectPayBillTimeCfgByPayDate(gbId,payTime,billTypeId);
			if(payBillTimeCfg==null){
				return null;
//				throw new BusinessRuntimeException("PlotpropertyCfgDao.selectPayBillTimeCfgByPayDate.null",new Object[]{gbId,billTypeId,payTime});
			}
			try {
				Date billStart = DateUtil.formatSecond.get().parse(payBillTimeCfg.getBillMonthStart());
				Date billEnd = DateUtil.formatSecond.get().parse(payBillTimeCfg.getBillMonthEnd());
				Date payStart = DateUtil.formatSecond.get().parse(payBillTimeCfg.getPayDayStart());
				Date payEnd = DateUtil.formatSecond.get().parse(payBillTimeCfg.getPayDayEnd());
				resBMY = BusinessMonthYearFactory.newInstance(billStart, billEnd, payStart, payEnd, groupBuilding,payTime);
			} catch (Exception e) {logger.error("PlotpropertyCfgDao.selectBusinessMonthYearByPayTime failed;;gbId:"+gbId+",date:"+payTime+",billTypeId:"+billTypeId,e);}
		}
		return resBMY;
	}
	
	@Override
	public IBusinessMonthYear selectBusinessMonthYearByBillMonth(BigInteger gbId,Date date, BigInteger billTypeId) {
		IBusinessMonthYear resBMY = null;
		GroupBuilding groupBuilding = groupBuildingBaseDao.selectGroupBuildingBySeqId(gbId);
		PayBillType payBillType = payBillTypeBaseDao.selectPayBillTypeBySeqId(billTypeId);
		if(PlotpropertyDict.PayBillType_PaytimeType.MONTH.compareTo(payBillType.getPaytimeType())==0){//如果为月缴
			resBMY = BusinessMonthYearFactory.newInstanceByBillMonth(date, groupBuilding);//根据账单月份
		}else{//如果为周期缴费
			PayBillTimeCfg payBillTimeCfg = selectPayBillTimeCfgByBillMonth(gbId,date, billTypeId);
			try {
				Date billStart = DateUtil.formatSecond.get().parse(payBillTimeCfg.getBillMonthStart());
				Date billEnd = DateUtil.formatSecond.get().parse(payBillTimeCfg.getBillMonthEnd());
				Date payStart = DateUtil.formatSecond.get().parse(payBillTimeCfg.getPayDayStart());
				Date payEnd = DateUtil.formatSecond.get().parse(payBillTimeCfg.getPayDayEnd());
				resBMY = BusinessMonthYearFactory.newInstance(billStart, billEnd, payStart, payEnd, groupBuilding);
			} catch (Exception e) {logger.error("PlotpropertyCfgDao.selectBusinessMonthYearByBillMonth failed;gbId:"+gbId+",date:"+date+",billTypeId:"+billTypeId,e);}
		}
		return resBMY;
	}
	
	/**
	 * 根据缴费时间查询对应的账单月份信息
	 * @param gbId
	 * @param payDate
	 * @param billTypeId
	 * @return
	 */
	private PayBillTimeCfg selectPayBillTimeCfgByPayDate(BigInteger gbId, Date payDate,
			BigInteger billTypeId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("gbId", gbId);
		tmpMap.put("billTypeId", billTypeId);
		tmpMap.put("payDate", payDate);
		List<PayBillTimeCfg> resList = sqlSession.selectList("plotpropertyCfg.select_PayBillTimeCfg_ByPayDate", tmpMap);
		PayBillTimeCfg res = null;
		if(resList!=null&&resList.size()>0){
			if(resList.size()==1){
				res = resList.get(0);
			}else{//小区gbId,账单类型billTypeId,账单月份payDate,包含多个账单时间！
				throw new BusinessRuntimeException("PlotpropertyCfgDao.selectPayBillTimeCfgByPayDate.multi.error",new Object[]{gbId,billTypeId,payDate});
			}
		}
		return res;
	}

	/**
	 * 根据账单起止月份查询对应的账单月份信息
	 * @param gbId
	 * @param billStartDate
	 * @param billEndDate
	 * @param billTypeId
	 * @return
	 */
	private PayBillTimeCfg selectPayBillTimeCfgByStartEndBillMonth(BigInteger gbId, Date billStartDate, Date billEndDate,
			BigInteger billTypeId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("gbId", gbId);
		tmpMap.put("billTypeId", billTypeId);
		tmpMap.put("billStartDate", DateUtil.formatSecond.get().format(billStartDate));
		tmpMap.put("billEndDate", DateUtil.formatSecond.get().format(billEndDate));
//		tmpMap.put("billStartDate", billStartDate);
//		tmpMap.put("billEndDate", billEndDate);
		List<PayBillTimeCfg> resList =  sqlSession.selectList("plotpropertyCfg.select_PayBillTimeCfg_ByStartEndBillMonth", tmpMap);
		PayBillTimeCfg res = null;
		if(resList!=null&&resList.size()>0){
			if(resList.size()==1){
				res = resList.get(0);
			}else{//小区gbId,账单类型billTypeId,账单月份billStartDate-billEndDate,包含多个账单时间！
				throw new BusinessRuntimeException("PlotpropertyCfgDao.selectPayBillTimeCfgByStartEndBillMonth.multi.error",new Object[]{gbId,billTypeId,billStartDate,billEndDate});
			}
		}
		return res;
	}
	
	/**
	 * 根据账单月份查询对应的账单月份信息
	 * @param gbId
	 * @param billStartDate
	 * @param billEndDate
	 * @param billTypeId
	 * @return
	 */
	private PayBillTimeCfg selectPayBillTimeCfgByBillMonth(BigInteger gbId, Date billMonth,
			BigInteger billTypeId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("gbId", gbId);
		tmpMap.put("billTypeId", billTypeId);
		tmpMap.put("billMonth", billMonth);
		List<PayBillTimeCfg> resList = sqlSession.selectList("plotpropertyCfg.select_PayBillTimeCfg_ByBillMonth", tmpMap);
		PayBillTimeCfg res = null;
		if(resList!=null&&resList.size()>0){
			if(resList.size()==1){
				res = resList.get(0);
			}else{//小区gbId,账单类型billTypeId,账单月份billMonth,包含多个账单时间！
				throw new BusinessRuntimeException("PlotpropertyCfgDao.selectPayBillTimeCfgByBillMonth.multi.error",new Object[]{gbId,billTypeId,billMonth});
			}
		}
		return res;
	}
	
	@Override
	public List<PayBillInfo> selectPayBillList(BigInteger userId,
			boolean isPay, PageModel pageModel, BigInteger billTypeId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("isPay", isPay);
		tmpMap.put("billTypeId", billTypeId);
		String pageSqlKey = "plotpropertyCfg.select_PayBill_List_page";
		String countSqlKey = "plotpropertyCfg.select_PayBill_List_count";
		return PageQueryUtil.selectPageList(sqlSession, tmpMap, pageModel, pageSqlKey, countSqlKey);
	}
	
	@Override
	public List<PropertyBillInfo> selectPayBillList(BigInteger userId, PageModel pageModel) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		String pageSqlKey = "plotpropertyCfg.select_PayBill_List_page_notbill_type";
		String countSqlKey = "plotpropertyCfg.select_PayBill_List_count_notbill_type";
		return PageQueryUtil.selectPageList(sqlSession, tmpMap, pageModel, pageSqlKey, countSqlKey);
	}
	
	@Override
	public List<PayBillType> selectPayBillTypeListAll(BigInteger gbId, boolean useDefaultIfNull) {
		List<PayBillType> resList = new ArrayList<PayBillType>();
//		Map<String,Object> tmpMap = new HashMap<String, Object>();
//		tmpMap.put("gbId", gbId);
		List<PayBillType> tmpList =  sqlSession.selectList("plotpropertyCfg.select_PayBillType_List_All", gbId);
		
		{//若未包含默认,则增加默认类别数据
			if(tmpList==null){
				tmpList = new ArrayList<PayBillType>();
			}
			boolean hasProp = false;
			for(PayBillType tmp:tmpList){
				if(tmp.getIsPropFee().compareTo(PlotpropertyDict.PayBillType_IsPropFee.YES)==0){//已经包含物业费类型的配置
					hasProp = true;
					break;
				}
//				if(tmp.getId().compareTo(PlotpropertyConstant.DEFAULT_PAYBILL_FEE_TYPEID)==0){
//					hasDefult = true;
//					break;
//				}
			}
			if(!hasProp&&useDefaultIfNull){
				PayBillType defaultType = payBillTypeBaseDao.selectPayBillTypeBySeqId(PlotpropertyConstant.DEFAULT_PAYBILL_FEE_TYPEID);
				resList.add(defaultType);
			}
		}
		resList.addAll(tmpList);
		return resList;
	}

	@Override
	public BigInteger selectPropPayBillTypeHistoryMonth(BigInteger gbId){
		BigInteger resId = null;
		GroupBuilding gbInfo = groupBuildingBaseDao.selectGroupBuildingBySeqId(gbId);
		
		if(gbInfo!=null&&gbInfo.getPayPeriodEnd()!=null&&PlotpropertyConstant.PERID_GBCFG_LIMITEND_DEFAULT.compareTo(gbInfo.getPayPeriodEnd())!=0){
			resId = PlotpropertyConstant.DEFAULT_PAYBILL_FEE_TYPEID;
		}
		return resId;
	}
	
	@Override
	public BigInteger selectPropPayBillTypeByGbId(BigInteger gbId,boolean useDefaultIfNull) {
//		Map<String,Object> tmpMap = new HashMap<String, Object>();
//		tmpMap.put("gbId", gbId);
		List<BigInteger> idList = sqlSession.selectList("plotpropertyCfg.select_PropPayBillType_ByGbId", gbId);
		BigInteger resId = null;
		if(idList!=null&&idList.size()>0){
			if(idList.size()==1){
				resId = idList.get(0);
			}else{
				throw new BusinessRuntimeException("PlotpropertyCfgDao.selectPropPayBillTypeByGbId.multi.error",new Object[]{gbId});
			}
		}
		if(resId==null){
			resId = selectPropPayBillTypeHistoryMonth(gbId);//尝试获取历史月缴配置
		}
		if(resId==null && useDefaultIfNull){
			resId = PlotpropertyConstant.DEFAULT_PAYBILL_FEE_TYPEID;
		}
		return resId;
	}

	@Override
	public List<PropFeeDiscount> selectLeastPrizeRecordListByBillId(BigInteger billId,BigInteger userId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("billId", billId);
		tmpMap.put("userId", userId);
		List<PropFeeDiscount> splitList = sqlSession.selectList("plotpropertyCfg.select_PrizeRecordList_Split_ByBillId", tmpMap);
		return splitList;
	}

	
	@Override
	public List<PayBillType> selectPayBillTypeListByCondition(BigInteger gbId,
			boolean isProp) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("gbId", gbId);
		//tmpMap.put("isPropFee", isProp?PlotpropertyDict.PayBillType_IsPropFee.YES:PlotpropertyDict.PayBillType_IsPropFee.NO);
		return sqlSession.selectList("plotpropertyCfg.select_PayBillTypeList_ByCondition", tmpMap);
	}

	@Override
	public PayBillType selectPayBillTypeNewByCondition(BigInteger gbId,
			String typeName,Integer isPropFee) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("gbId", gbId);
		tmpMap.put("typeName", typeName);
		tmpMap.put("isPropFee", isPropFee);
		List<PayBillType> tmpList = sqlSession.selectList("plotpropertyCfg.select_PayBillTypeNew_ByCondition", tmpMap);
		PayBillType res = null;
		if(tmpList!=null&&tmpList.size()>0){
			if(tmpList.size()==1){
				res = tmpList.get(0);
			}else{//小区{0}存在多个缴费类别{1}的配置！
				throw new BusinessRuntimeException("PlotpropertyCfgDao.selectPayBillTypeNewByCondition.multi.error",new Object[]{gbId,typeName});
			}
		}
		return res;
	}

	@Override
	public Integer selectExistBillCountByTypeId(BigInteger gbId,
			BigInteger billTypeId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("gbId", gbId);
		tmpMap.put("billTypeId", billTypeId);
		return sqlSession.selectOne("plotpropertyCfg.select_ExistBillCount_ByTypeId", tmpMap);
	}

	@Override
	public Integer selectExistBillTypeByCondition(BigInteger gbId,
			String typeName, BigInteger ignoreTypeId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("gbId", gbId);
		tmpMap.put("typeName", typeName);
		tmpMap.put("ignoreTypeId", ignoreTypeId);
		return sqlSession.selectOne("plotpropertyCfg.select_ExistBillType_ByCondition", tmpMap);
	}

	@Override
	public Integer markDiscountAsUsed(List<PayBillSplit> toUpdPayBillSplitList, BigInteger billId,Integer usedType) {
		if(toUpdPayBillSplitList==null || toUpdPayBillSplitList.size()<=0){
			return 0;
		}
		//根据billId还原数据
//		sqlSession.update("plotpropertyCfg.update_PayBillSplit_revert_ByBillId", billId);
		//更新payBillSplit
		payBillSplitBaseDao.updatePayBillSplitBatch(toUpdPayBillSplitList);
		//更新折扣
		List<Map<String,Object>> prizeUpdList = new ArrayList<Map<String,Object>>();
		for(PayBillSplit tmp:toUpdPayBillSplitList){
			if(tmp.getPrizeRecordId()==null){continue;}
			Map<String,Object> tmpMap = new HashMap<String, Object>();
			tmpMap.put("prizeRecordId", tmp.getPrizeRecordId());
			tmpMap.put("usedType", usedType);
			tmpMap.put("savedMoney", tmp.getManageAmount()-tmp.getSuccPayAmount());
			prizeUpdList.add(tmpMap);
		}
		return sqlSession.update("plotpropertyCfg.update_Discount_As_Used_Batch", prizeUpdList);
	}

	@Override
	public List<PayBillSplit> selectPayBillSplitListByBillId(BigInteger billId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("billId", billId);
		return sqlSession.selectList("plotpropertyCfg.select_PayBillSplitList_ByBillId", tmpMap);
	}

	@Override
	public String selectPayBillTimeCfg(BigInteger billTypeId) {
		return sqlSession.selectOne("plotpropertyCfg.select_PayBillTimeCfg_BytypeId", billTypeId);
	}

}
