package com.cnfantasia.server.commbusi.plotproperty.dao;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import com.cnfantasia.server.api.plotproperty.entity.PropertyBillInfo;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.commbusi.plotproperty.entity.*;
import com.cnfantasia.server.domainbase.payBillSplit.entity.PayBillSplit;
import com.cnfantasia.server.domainbase.payBillType.entity.PayBillType;

/**
 * 收益配置Dao
* Filename:    IPlotpropertyCfgDao.java
* @version:    1.0.0
* Create at:   2015年12月10日 下午6:34:54
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2015年12月10日       shiyl             1.0             1.0 Version
 */
public interface IPlotpropertyCfgDao {
	
	/**
	 * 根据小区Id查询所有收费类别列表
	 * @param gbId
	 * @return
	 */
	List<PayBillType> selectPayBillTypeListAll(BigInteger gbId, boolean useDefaultIfNull);
	
	/**
	 * 按条件查询收费单
	 * @param userId
	 * @param billTypeId 收费项基础类别Id
	 * @param billMonth 目标时间类型
	 * @return
	 */
	PayBillInfo selectPayBillByBillMonth(BigInteger userId, BigInteger billTypeId, DataType.ISectionDate billMonth);
	
	/**
	 * 根据账单Id查询详情
	 * @param billId
	 * @return
	 */
	PayBillInfo selectPayBillByBillId(BigInteger billId);
	
	/**
	 * 根据用户Id查询小区Id
	 * @param userId
	 * @return
	 */
	BigInteger selectGroupBuildingIdByUserId(BigInteger userId);
	
	/**
	 * 根据小区Id,缴费时间查询对应的账单月份信息
	 * @param gbId
	 * @param date
	 * @param billTypeId
	 * @return
	 */
	IBusinessMonthYear selectBusinessMonthYearByPayTime(BigInteger gbId,
														Date date, BigInteger billTypeId);
	
	/**
	 * 根据小区Id,账单时间查询对应的账单月份信息
	 * @param gbId
	 * @param date
	 * @param billTypeId
	 * @return
	 */
	IBusinessMonthYear selectBusinessMonthYearByBillMonth(BigInteger gbId,
														  Date date, BigInteger billTypeId);
	
	/**
	 * 查询账单列表
	 * @param userId
	 * @param isPay
	 * @param pageModel
	 * @return
	 */
	List<PayBillInfo> selectPayBillList(BigInteger userId, boolean isPay, PageModel pageModel, BigInteger billTypeId);
	
	/**
	 * 根据小区查询是否有历史月缴的配置
	 * @param gbId
	 * @return
	 */
	BigInteger selectPropPayBillTypeHistoryMonth(BigInteger gbId);
	/**
	 * 根据小区Id查询物业费类别配置Id
	 * @param gbId
	 * @return
	 */
	BigInteger selectPropPayBillTypeByGbId(BigInteger gbId, boolean useDefaultIfNull);
	
	/**
	 * 根据账单Id查询对应折扣月份最低折扣列表
	 * @param billId
	 * @return
	 */
	List<PropFeeDiscount> selectLeastPrizeRecordListByBillId(BigInteger billId, BigInteger userId);
	
	/**
	 * 查询账单类别列表
	 * @param gbId
	 * @param isProp
	 * @return
	 */
	List<PayBillType> selectPayBillTypeListByCondition(BigInteger gbId,
													   boolean isProp);
	
	/**
	 * 从新配置表中查询账单类别
	 * @param gbId
	 * @param typeName
	 * @return
	 */
	PayBillType selectPayBillTypeNewByCondition(BigInteger gbId,
												String typeName, Integer isPropFee);
	
	/**
	 * 根据类别查询已存在账单数量
	 * @param gbId
	 * @param billTypeId
	 * @return
	 */
	Integer selectExistBillCountByTypeId(BigInteger gbId,
										 BigInteger billTypeId);
	
	/**
	 * 查询已存在的账单类别数量
	 * @param gbId
	 * @param typeName
	 * @param ignoreTypeId
	 * @return
	 */
	Integer selectExistBillTypeByCondition(BigInteger gbId,
										   String typeName, BigInteger ignoreTypeId);

	/**
	 * 标记折扣为已使用
	 * @param toUpdPayBillSplitList
	 * @param billId
	 * @return
	 */
	Integer markDiscountAsUsed(List<PayBillSplit> toUpdPayBillSplitList, BigInteger billId, Integer usedType);

	/**
	 * 根据账单Id查询拆分的子账单列表
	 * @param billId
	 * @return
	 */
	List<PayBillSplit> selectPayBillSplitListByBillId(BigInteger billId);

	/**
	 * 获取该类型费用可缴时间
	 * @param billTypeId
	 * @return
	 */
	String selectPayBillTimeCfg(BigInteger billTypeId);

	/**
	 * 获取支付完成订单  不区分账单类型
	 * @param userId
	 * @param pageModel
	 * @return
	 */
	List<PropertyBillInfo> selectPayBillList(BigInteger userId, PageModel pageModel);
	
	
}
