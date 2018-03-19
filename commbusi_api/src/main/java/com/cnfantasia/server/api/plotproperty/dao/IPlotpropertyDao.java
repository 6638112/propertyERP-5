/**   
* Filename:    IPlotpropertyDao.java   
* @version:    1.0  
* Create at:   2014年8月13日 上午8:23:33   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月13日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.plotproperty.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.plotproperty.entity.*;
import com.cnfantasia.server.api.property.dto.PayBillDetailDto;
import com.cnfantasia.server.api.property.dto.PropertyBillTailDto;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.gbSoftwareConfig.entity.GbSoftwareConfig;
import com.cnfantasia.server.domainbase.propertyService.entity.PropertyService;

/**
 * Filename:    IPlotpropertyDao.java
 * @version:    1.0.0
 * Create at:   2014年8月13日 上午8:23:33
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月13日       shiyl             1.0             1.0 Version
 */
public interface IPlotpropertyDao {
	
	/**
	 * 查询最近一个有账单的月份
	 * @param userId
	 * @return
	 */
	String selectLastPayBillMonth111(BigInteger userId);
	
	/**
	 * 查询对应月份的物业账单信息
	 * @param userId
	 * @param yearMonth
	 * @return
	 */
	List<PayBillEntity> selectPayBillByMonth111(BigInteger userId, String yearMonth);
	
	/**
	 * 更换当前用户默认门牌的真实门牌Id为指定的真实门牌，并标记为已确认
	 * @param userId
	 * @param realRoomId
	 * @return 
	 */
	Integer updateAndConfirmRealRoomInfo(BigInteger userId, BigInteger realRoomId);
	
	/**
	 * 查询用户有账单记录的年份列表
	 * @param userId
	 * @param payStatus
	 * @param pageModel
	 * @return
	 */
	List<String> selectExistPayBillYearList111(BigInteger userId, Integer payStatus, PageModel pageModel);
	List<String> selectExistPayBillYearList111(BigInteger userId, Integer payStatus);
	
	/**
	 * 查询已支付账单
	 * @param userId
	 * @param yearList
	 * @return
	 */
	List<PayBillEntity> selectIsPayBillList111(BigInteger userId, List<String> yearList);

	/**
	 * 查询未支付账单
	 * @param userId
	 * @param yearList
	 * @return
	 */
	List<PayBillEntity> selectNotPayBillList111(BigInteger userId, List<String> yearList);
	
	/**
	 * 根据账单Id查询对应详情
	 * @param userId
	 * @param payBillId
	 * @return
	 */
	PayBillEntity selectPayBillDetailById(BigInteger userId, BigInteger payBillId);
	
	/**
	 * 查询包含当前账单的所有订单列表信息
	 * @param userId
	 * @param payBillId
	 * @return
	 */
	List<PlotpropertyOrderEntity> selectPlotpropertyOrderAllListByPayBillId(BigInteger userId, BigInteger payBillId);
	/**
	 * 根据物业订单Id查询订单详情
	 * @param userId
	 * @param orderId
	 * @return
	 */
	PlotpropertyOrderEntity selectPlotpropertyOrderDetail(BigInteger userId, BigInteger orderId);
	
	List<String> selectPlotpropertyTotalOrderDetail(BigInteger orderId);
	
	String selectOrderAddress(BigInteger orderId);
	
	PlotpropertyOrderEntity selectPlotpropertyOrderDetail(Map<String, Object> paramMap);
	
	/**
	 * @param orderId
	 * @param payBillId
	 * @return
	 */
	PlotpropertyOrderEntity getPlotpropertyOrderDetailByOrderIdAndBillId(BigInteger orderId, BigInteger payBillId);
	/**
	 * 支付成功之后刷新物业账单信息
	 * @param payBillId
	 * @param succPayAmount
	 * @return
	 */
	Integer updatePayBillInfoSuccessByApp(BigInteger payBillId, Long succPayAmount, Double discount, BigInteger prizeRecordId, Long hbAmount, BigInteger preferType, Integer cycleType);
	/**
	 * 删除未成功支付的物业订单及相关信息，并退回粮票
	 * @param orderId
	 */
	Integer delLogicPlotpropertyOrderAndBackDiscount(BigInteger orderId);
	Integer delLogicPlotpropertyOrderAndBackDiscountAll();
	
	/**
	 * 查询当前用户指定真实门牌下已经确认过的门牌数量
	 * @param userId
	 * @param realRoomId
	 * @return
	 */
	Integer selectCheckRoomCountByRealRoomId(BigInteger userId, BigInteger realRoomId);
	
	/**
	 * 查询物业服务列表
	 * @param groupBuildingId
	 * @return
	 */
	List<PropertyService> selectPlotpropertyServiceList(BigInteger groupBuildingId);
	
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
	 * 更具userid获取月账单
	 * @param userId
	 * @return
	 */
	List<PropertyBillEntity> getInCycleBillByMonth(BigInteger userId);

	/**
	 * 更具userid获取周期账单
	 * @param userId
	 * @return
	 */
	PropertyBillEntity getInCycleBillByDate(BigInteger userId, BigInteger billType);

	/**
	 * 获取未支付账单信息
	 * @param paramMap
	 * @return
	 */
	PlotpropertyOrderEntity selectPlotpropertyNotPayOrderDetail(Map<String, Object> paramMap);
	
	String selectLastPayBillMonth(BigInteger userId);

	/**
	 * 抽取随机金额
	 * @param userId
	 * @param billId
	 * @return
	 */
	Long getPreferentialRandom(BigInteger userId, BigInteger billId);

	/**
	 * 获取物业宝，自动划扣的账单信息
	 * @param paramMap
	 * @return
	 */
	PlotpropertyOrderEntity getPlotpropertyNoOrderDetail(Map<String, Object> paramMap);

	/**
	 * 获取用户可缴费账单列表
	 * @param userId
	 * @return
	 */
	List<PropertyBillEntity> getInCycleBillByUserId(Map<String, Object> paraMap);

	/**
	 * 获取优惠期限
	 * @param id
	 * @param billMonth 
	 * @return
	 */
	Map<String, String> selectPreferentialCycle(BigInteger id, String billMonth);

	/**
	 * 该用户是否存在缴费记录
	 * @param userId
	 * @param realRoomId
	 * @return
	 */
	int isHasPayBillRecordByUserIdAndRealRoomId(BigInteger userId, BigInteger realRoomId);

	/**
	 * 查询现在需要缴的费用总和
	 * @param userId
	 * @return
	 */
	Long getTotalAmtNow(BigInteger userId);

	/**
	 * 查询门牌的业主信息
	 * @param realRoomId
	 * @return
	 */
	String getRealRoomProprietorName(BigInteger realRoomId);

	GbSoftwareConfig select_gbSoftwareConfig_by_realRoom(BigInteger realRoomId);

	/**
	 * 选择周期账单
	 * @param paraMap
	 * @return
     */
	List<AlterCyclesEntity> getAlterPayBillEntity(Map<String, Object> paraMap);

	List<PayBillDetailDto> getPayBillDetailByIds(List<BigInteger> idsList);

	/**
	 * 回调查询订单对应的账单（固定收费项）的费用起始时间
	 * @param orderId
	 * @return
     */
	List<Map<String,Object>> getNeedAlterUpdate(BigInteger orderId);

	/**
	 * 根据门牌id查询门牌地址和门牌业主信息
	 * @param realRoomId
	 * @return
     */
	PropertyBillTailDto getRoomAddressAndPPName(BigInteger realRoomId);

	Long getTotalUnPaidAmtNow(BigInteger userId);
}
