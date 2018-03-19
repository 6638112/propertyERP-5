package com.cnfantasia.server.commbusi.plotproperty.service;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import com.cnfantasia.server.api.plotproperty.entity.PropertyBillInfo;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.entity.RequestFileEntity;
import com.cnfantasia.server.commbusi.plotproperty.entity.*;
import com.cnfantasia.server.domainbase.payBillSplit.entity.PayBillSplit;
import com.cnfantasia.server.domainbase.payBillType.entity.PayBillType;

/**
 * 物业缴费配置服务类
* Filename:    IPlotpropertyCfgService.java
* @version:    1.0.0
* Create at:   2015年12月10日 下午6:30:58
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2015年12月10日       shiyl             1.0             1.0 Version
 */
public interface IPlotpropertyCfgService {
	
	/**
	 * 根据缴费时间查询小区对应的日期信息
	 * @param userId
	 * @param date
	 * @return
	 */
	IBusinessMonthYear getBusinessMonthYearByPayTime(BigInteger userId, Date date, BigInteger billTypeId);
	
	/**
	 * 获取最新的物业账单月份
	 * @param userId
	 * @return
	 */
	IBusinessMonthYear getLastPayBillMonthYear(BigInteger userId, BigInteger billTypeId);
	
	/**
	 * 根据条件查询账单信息
	 * @param userId
	 * @param monthYearWithGB
	 * @return
	 */
	PayBillInfo getPayBillSimpleInfoByMonth(BigInteger userId, IBusinessMonthYear monthYearWithGB, BigInteger billTypeId);
	PayBillInfo getPayBillSimpleInfoById(BigInteger billId);
	
	/**
	 * 查询已缴账单列表
	 * @param userId
	 * @param pageModel
	 * @param billTypeId
	 * @return
	 */
	List<PayBillInfo> getIsPayBillList(BigInteger userId,
									   PageModel pageModel, BigInteger billTypeId);
	
	/**
	 * 查询未缴账单列表
	 * @param userId
	 * @param pageModel
	 * @param billTypeId
	 * @return
	 */
	List<PayBillInfo> getNotPayBillList(BigInteger userId,
										PageModel pageModel, BigInteger billTypeId);
	
	/**
	 * 查询首页图标展示的缴费类别
	 * @param gbId
	 * @return
	 */
	List<PayBillType> getPayBillTypeListAll(BigInteger gbId);
	List<PayBillType> getPayBillTypeListAll(BigInteger gbId, boolean useDefaultIfNull);
	
	/**
	 * 根据账单时间查询对应的账单月份信息
	 * @param gbId
	 * @param billDate
	 * @param billTypeId
	 * @return
	 */
	IBusinessMonthYear getBusinessMonthYearByBillMonth(
			BigInteger gbId, Date billDate, BigInteger billTypeId);
	
	/**
	 * 获取小区当前的物业费类别配置Id
	 * @param gbId
	 * @return
	 */
//	public BigInteger getPropPayBillTypeByGbId(BigInteger gbId,boolean useDefaultIfNull);
//	public BigInteger getPropPayBillTypeByUserId(BigInteger userId,boolean useDefaultIfNull);
	PayBillType getPropPayBillTypeInfoByCondition(BigInteger userId, BigInteger gbId, boolean useDefaultIfNull);
	
	/**
	 * 根据账单Id查询对应折扣月份最低折扣列表
	 * 根据物业账单Id,查询对应的缴费区间内各个月份的最低折扣
	 * @param billId
	 * @return
	 */
	List<PropFeeDiscount> getLeastPrizeRecordListByBillId(BigInteger billId, BigInteger userId);
	
	/**
	 * 查询账单类型信息,若类型为空则返回默认物业账单类型信息
	 * @param typeId
	 * @param gbId
	 * @return
	 */
	PayBillType getPropBillTypeInfoByTypeIdAndPropIfNull(
			BigInteger typeId, BigInteger gbId, boolean useDefaultIfNull);
	
	/**
	 * 新增缴费类别 TODO 图标
	 * @param gbId 小区Id
	 * @param typeName 类别名称
	 * @param activeStatus 开启状态
	 * @param preferStatus 随机立减开启状态
	 * @return 返回新增的Id
	 */
	BigInteger addPayBillType(BigInteger gbId, String typeName, Integer activeStatus, RequestFileEntity shareIconImage, Integer preferStatus);
	
	/**
	 * 修改缴费类别
	 * @param billTypeId
	 * @param gbId 小区Id用于校验
	 * @param typeName
	 * @param activeStatus
	 * @param preferStatus 随机立减开启状态
	 */
	void updPayBillType(BigInteger gbId, BigInteger billTypeId, String typeName, Integer activeStatus, boolean isProp, RequestFileEntity shareIconImage, Integer preferStatus);

	/**
	 * 删除缴费类别
	 * @param billTypeId
	 * @param gbId
	 */
	void delPayBillType(BigInteger gbId, BigInteger billTypeId);

	/**
	 * 编辑物业缴费类别
	 * @param editPropPayBillTypeEntity
	 */
	void editPropPayBillType(EditPropPayBillTypeEntity editPropPayBillTypeEntity);

	/**
	 * 查询所有缴费类别列表
	 * @param gbId
	 * @param isProp 是否为物业费
	 * @return
	 */
	List<PayBillType> getPayBillTypeList(BigInteger gbId, boolean isProp);

	/**
	 * 导入账单时查询账单类别
	 * @param gbId
	 * @param typeName
	 * @param isPropFee 是否为物业费类别
	 * @return 若为物业费类别,则尝试获取历史月缴配置
	 */
	PayBillType getPayBillTypeForImport(BigInteger gbId, String typeName, boolean isPropFee);

	/**
	 * 标记折扣为已使用
	 * @param toUpdPayBillSplitList
	 * @param billId
	 * @return
	 */
	Integer markDiscountAsUsed(List<PayBillSplit> toUpdPayBillSplitList, BigInteger billId, Integer usedType);
	
	/**
	 * 根据账单按月份拆分
	 * @param paybillId
	 * @return
	 */
	List<PayBillSplit> generatePayBillSplit(BigInteger paybillId);
	
	/**
	 * 获取该类型账单的缴费时间
	 * @param billTypeId
	 * @return
	 */
	String getPayBillPayTime(BigInteger billTypeId);

	/**
	 * 获取支付完成账单  不区分账单类型
	 * @param userId
	 * @param pageModel
	 * @return
	 */
	List<PropertyBillInfo> getIsPayBillList(BigInteger userId, PageModel pageModel);

	/**
	 * 小区属性状态开关
	 * @param gbId
	 * @param activeStatus 是否开启缴费
	 * @param verificationStatus 是否开启缴费校验
	 * @param isPrefer 是否开启随机立减
	 */
	void editPropPayBillOpenAttribute(BigInteger gbId, Integer activeStatus, Integer verificationStatus,
									  Integer isPrefer);

	/**
	 * 添加或更新抄表配置
	 * @param gbId
	 * @param typeName
	 * @param activeStatus
	 * @param preferStatus
	 * @return
	 */
	BigInteger addMRPayBillType(BigInteger gbId, String typeName, Integer activeStatus, Integer preferStatus);

	/**
	 * 小区缴费权限配置
	 * @param groupBuildingCfgEntity
     */
	void saveGbConfig(GroupBuildingCfgEntity groupBuildingCfgEntity);

	/**
	 * 根据用户ID查询小区ID
	 * @param userId
	 * @return
     */
	BigInteger getGroupBuildingIdByUserId(BigInteger userId);
}
