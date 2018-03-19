/**   
* Filename:    IPlotpropertyService.java   
* @version:    1.0  
* Create at:   2014年8月13日 上午7:29:39   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月13日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.plotproperty.service;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.plotproperty.entity.*;
import com.cnfantasia.server.api.prize.entity.PrizeRecordSimpleEntity;
import com.cnfantasia.server.api.property.dto.PayBillDetailDto;
import com.cnfantasia.server.api.property.dto.PreOrderDto;
import com.cnfantasia.server.api.property.dto.PropertyBillTailDto;
import com.cnfantasia.server.api.room.entity.GroupBuildingEntity;
import com.cnfantasia.server.api.room.entity.RoomEntityWithValidate;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.commbusi.plotproperty.entity.IBusinessMonthYear;
import com.cnfantasia.server.commbusi.plotproperty.entity.PayBillInfo;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
import com.cnfantasia.server.domainbase.payBill.entity.PayBill;
import com.cnfantasia.server.domainbase.payBillType.entity.PayBillType;
import com.cnfantasia.server.domainbase.propertyFeeDetail.entity.PropertyFeeDetail;
import com.cnfantasia.server.domainbase.propertyService.entity.PropertyService;

/**
 * Filename:    IPlotpropertyService.java
 * @version:    1.0.0
 * Create at:   2014年8月13日 上午7:29:39
 * Description:小区物业service
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月13日       shiyl             1.0             1.0 Version
 */
public interface IPlotpropertyService {

	/**
	 * 根据小区Id查询对应的小区信息
	 *
	 * @param groupBuildingId
	 * @param userId
	 * @return
	 */
	GroupBuildingEntity getGroupBuildingEntityById(BigInteger groupBuildingId, BigInteger userId);

	/**
	 * 查询最新的一个物业账单
	 *
	 * @return
	 */
	PlotpropertyCombEntity getLastPayBill(BigInteger userId, BigInteger billTypeId);

	/**
	 * 根据账单Id查询对应详情
	 *
	 * @param userId
	 * @param billId
	 * @return
	 */
	PlotpropertyCombEntity getPayBillDetailById(BigInteger userId, BigInteger billId, boolean needValidate);

	/**
	 * 查询对应月份的物业账单信息
	 *
	 * @param userId
	 * @param yearMonth
	 * @return
	 */
	PlotpropertyCombEntity getPayBillByMonth(BigInteger userId, IBusinessMonthYear yearMonth, boolean needValidate, BigInteger billTypeId);

	/**
	 * 用户确认门牌信息
	 *
	 * @param userId
	 * @param realRoomId
	 * @param proprietorName
	 */
	RoomEntityWithValidate confirmRealRoomInfo(BigInteger userId, BigInteger realRoomId, String proprietorName, String payUserName);

	/**
	 * 确认订单信息
	 *
	 * @param userId
	 * @param payBillId
	 * @param useDiscount
	 * @return
	 */
	ConfirmPayBillRusultEntity confirmPayBill(BigInteger userId, BigInteger payBillId, Boolean useDiscount, Long subChannelId, Long hbAmount);

	/**
	 * 查询已支付的账单列表
	 *
	 * @param userId
	 * @param pageModel
	 * @return
	 */
	List<PayBillInfo> getIsPayBillList(BigInteger userId, PageModel pageModel, BigInteger billTypeId);

	/**
	 * 查询未支付的账单列表
	 *
	 * @param userId
	 * @param pageModel
	 * @return
	 */
	List<PayBillInfo> getNotPayBillList(BigInteger userId, PageModel pageModel, BigInteger billTypeId);

	List<PayBillInfo> getNotPayBillList(BigInteger userId, BigInteger billTypeId);

	/**
	 * 查询物业单对应的账单信息
	 *
	 * @param userId
	 * @param payBillId
	 * @return
	 */
	PlotpropertyOrderEntity getPlotpropertyOrderByPayBillId(BigInteger userId, BigInteger payBillId);

	/**
	 * 根据物业订单Id查询订单详情
	 *
	 * @param userId
	 * @param orderId
	 * @return
	 */
	PlotpropertyOrderEntity getPlotpropertyOrderDetail(BigInteger userId, BigInteger orderId);
	
	List<String> getPlotpropertyTotalOrderDetail(BigInteger orderId);
	
	String getOrderAddress(BigInteger roomId);

	PlotpropertyOrderEntity getPlotpropertyOrderDetail(Map<String, Object> paramMap);
	
	/**
	 * 根据物业订单Id查询订单和账单id详情
	 *
	 * @param orderId
	 * @param payBillId
	 * @return
	 */
	PlotpropertyOrderEntity getPlotpropertyOrderDetailByOrderIdAndBillId(BigInteger orderId, BigInteger payBillId);

	/**
	 * 支付成功之后刷新物业账单信息
	 *
	 * @param payBillId
	 * @param succPayAmount
	 * @param discount
	 * @param prizeRecordId
	 * @param hbAmount      粮票补贴金额
	 * @param cycleType     周期缴费类型 1 固定 2选择
	 * @return
	 */
	Integer updatePayBillInfoSuccessByApp(BigInteger payBillId, Long succPayAmount, Double discount, BigInteger prizeRecordId, Long hbAmount, BigInteger userId, Integer cycleType);


	Map<String, Object> plotpropertyCombEntity2Map02(BigInteger userId, PlotpropertyCombEntity plotpropertyCombEntity);

	Map<String, Object> plotpropertyCombEntity2Map02(PayBill payBill, PrizeRecordSimpleEntity prizeRecordSimpleEntity
			, List<PropertyFeeDetail> propertyFeeDetailList, PayBillType payBillType, IBusinessMonthYear businessMonthYear);


	/**
	 * 删除未成功支付的物业订单及相关信息，并退回粮票
	 *
	 * @param orderId
	 */
	void deletePlotpropertyOrderAndBackDiscount(BigInteger orderId);

	void deletePlotpropertyOrderAndBackDiscountAll();

	/**
	 * 查询当前用户指定真实门牌下已经确认过的门牌数量
	 *
	 * @param userId
	 * @param realRoomId
	 * @return
	 */
	Integer getCheckRoomCountByRealRoomId(BigInteger userId, BigInteger realRoomId);


	/**
	 * 查询物业服务列表
	 *
	 * @param userId
	 * @return
	 */
	List<PropertyService> getPlotpropertyServiceList(BigInteger userId);

	/**
	 * 获取最新的物业账单月份
	 *
	 * @param userId
	 * @return
	 */
	IBusinessMonthYear getLastPayBillMonthYear(BigInteger userId, BigInteger billTypeId);

	/**
	 * 根据月份查询对应的账单信息
	 *
	 * @param userId
	 * @param yearMonth
	 * @return
	 */
	PayBillInfo getPayBillSimpleInfoByMonth(BigInteger userId, IBusinessMonthYear yearMonth, BigInteger billTypeId);

	Propfee getPropertyFeeAndCount(Map<String, Object> paramMap);

	/**
	 * 查询账单信息
	 *
	 * @param realRoomId
	 * @param isPay
	 * @return
	 */
	String getBillInfo(BigInteger realRoomId, String isPay);

	/**
	 * 是否用物业宝免缴本月账单
	 *
	 * @param realRoomId
	 * @return
	 */
	Integer getWYBPayStatus(BigInteger realRoomId);

	/**
	 * 根据用户Id,查询当前可支付的收费项目,包含默认的物业费类别
	 *
	 * @param userId
	 * @return
	 */
	List<PayBillType> getPayBillTypeList(BigInteger userId);

	/**
	 * 根据账单时间查询对应月份信息
	 *
	 * @param userId
	 * @param billMonth
	 * @param billTypeId
	 * @return
	 */
	IBusinessMonthYear getBusinessMonthYearByBillMonth(BigInteger userId,
													   Date billMonth, BigInteger billTypeId);

	/**
	 * 根据缴费时间查询小区对应的日期信息
	 *
	 * @param userId
	 * @param date
	 * @return
	 */
	IBusinessMonthYear getBusinessMonthYearByPayTime(BigInteger userId, Date date, BigInteger billTypeId);

	/**
	 * 获取小区当前的物业费类别配置Id
	 *
	 * @param gbId
	 * @return
	 */
	BigInteger getPropPayBillTypeByGbId(BigInteger gbId, boolean useDefaultIfNull);

	BigInteger getPropPayBillTypeByUserId(BigInteger userId, boolean useDefaultIfNull);

	/**
	 * 查询账单类型信息,若类型为空则返回默认物业账单类型信息
	 *
	 * @param typeId
	 * @param userId
	 * @return
	 */
	PayBillType getPropBillTypeInfoByTypeIdAndPropIfNull(BigInteger typeId, BigInteger userId, boolean useDefaultIfNull);

	/**
	 * *组装物业账单信息
	 *
	 * @param inCycleBillList
	 * @param inCycleNotBillList
	 * @param notInCycleBillList
	 * @return
	 */
	Map<String, Object> convertPropertyBillList(PropertyBillEntity propertyBillEntity);

	/**
	 * 获取账单信息
	 *
	 * @param userId
	 * @return
	 */
	List<PropertyBillEntity> getBillList(BigInteger userId);

	/**
	 * 抽取账单优惠金额
	 *
	 * @param userId
	 * @param billId
	 * @return
	 */
	Map<String, Object> getPreferential(BigInteger userId, BigInteger billId);

	/**
	 * 获取未支付账单信息
	 *
	 * @param paramMap
	 * @return
	 */
	PlotpropertyOrderEntity getPlotpropertyNotPayOrderDetail(Map<String, Object> paramMap);

	/**
	 * 获取支付完成的账单 不区分账单类型
	 *
	 * @param userId
	 * @param pageModel
	 * @return
	 */
	List<PropertyBillInfo> getIsPayBillList(BigInteger userId, PageModel pageModel);

	/**
	 * 获取物业宝，自动划扣的账单详情
	 *
	 * @param paramMap
	 * @return
	 */
	PlotpropertyOrderEntity getPlotpropertyNoOrderDetail(Map<String, Object> paramMap);

	/**
	 * 获取账单列表
	 *
	 * @param userId
	 * @param realRoomId
	 *@param groupBuilding  @return
	 */
	List<PropertyBillEntity> getBillList02(BigInteger userId, BigInteger realRoomId, GroupBuilding groupBuilding);

	/**
	 * 是否存在该用户的缴费记录
	 *
	 * @param userId
	 * @param realRoomId
	 * @return
	 */
	Boolean isHasPayBillRecordByUserIdAndRealRoomId(BigInteger userId, BigInteger realRoomId);

	/**
	 * 验证业主姓名
	 *
	 * @param userId
	 * @param roomId
	 * @return
	 */
	Boolean isConfirmPass(BigInteger userId, BigInteger roomId);

	/**
	 * 查询当期需缴费用总和
	 *
	 * @param userId
	 * @return
	 */
	Double getTotalAmtNow(BigInteger userId);

	/**
	 * 业主姓名验证
	 *
	 * @param realRoomId
	 * @param name
	 * @return
	 */
	Boolean checkProprietorName(BigInteger realRoomId, String name);

	/**
	 * 更新业主 门牌验证是否通过
	 *
	 * @param userId
	 * @param roomId
	 * @return
	 */
	int updateConfirmStatus(BigInteger userId, BigInteger roomId);

	/**
	 * 判断缴费次数是否超过限制
	 *
	 * @param deviceId
	 * @param userId
	 * @return
	 */
	boolean isCanPayBill(String deviceId, BigInteger userId);

	/**
	 * 选择周期缴费支付详情
	 *
	 * @param realRoomId
	 * @return
	 */
	PropertyAlterBillInfo getAlterPeriodDetail(BigInteger realRoomId);

	/**
	 * 组装选择周期信息
	 *
	 * @param propertyAlterBillInfo
	 * @return
	 */
	Map<String, Object> convertAlterPeriodDetail(PropertyAlterBillInfo propertyAlterBillInfo);

	/**
	 * 获取选择周期缴费随机立减
	 *
	 * @param userId
	 * @param realRoomId
	 * @param month
	 * @return
	 */
	Map<String, Object> getAlterPeriodPreferential(BigInteger userId, BigInteger realRoomId, Integer month);

	/**
	 * 回调完成  更新选择周期基础数据信息
	 *
	 * @param orderId
	 */
	int updateAlterPeriodDataDetailAfterPaySuccess(BigInteger orderId);
	
	/**
	 * 校验选择周期 月份是否存在
	 * @param userId
	 * @param month
	 * @return
	 */
	boolean checkAlterPeriodMonth(BigInteger userId, Integer month);

	/**
	 * 根据真实房间id获取滞纳金
	 * @param realRoomId
	 * @return
     */
	Long getLateFeeByRealRoomId(BigInteger realRoomId);

	/**
	 * 第三方 极致物业账单对接
	 * @return
     */
	PropertyDetail getPlotpropertyNotPayOrderDetailFrom3rd(BigInteger userId);

	/**
	 * 物业缴费确认账单 生成订单
	 * @param userId
	 * @param payBillId 账单id
	 * @param subChannelId
	 * @param deviceId 设备id
	 * @param hbAmountL 粮票使用额
	 * @param month 缴费月数--选择周期缴费
     * @param dataFromType 数据来源类型 --第三方物业 极致
     * @param isFromTotalOrder 是否来自一键缴费
     * @return
     */
	PreOrderDto confirmBill(BigInteger userId, BigInteger payBillId, Long subChannelId, String deviceId, Long hbAmountL, Integer month, Integer dataFromType, boolean isFromTotalOrder);

	PayBillEntity get3rdPayBillEntity(BigInteger realRoomId);
	
	PropertyDetail convertPropertyDetail2(PlotpropertyOrderEntity plotpropertyOrder);
	
	/**
	 *  隐藏部分业主姓名
	 * @param userName
	 * @return
	 */
	String replaceNameToStar(String userName);

	/**
	 * 查询选择周期账单信息
	 * @param paraMap
	 * @return
     */
	List<AlterCyclesEntity> getAlterPayBillEntity(Map<String, Object> paraMap);

	/***
	 * 选择周期账单查询
	 * @param idsList
	 * @return
     */
	List<PayBillDetailDto> getPayBillDetailByIds(List<BigInteger> idsList);

	/**
	 * 查询当前用户的门牌地址和业主信息
	 * @param realRoomId
	 * @return
     */
	PropertyBillTailDto getRoomAddressAndPPName(BigInteger realRoomId);
}