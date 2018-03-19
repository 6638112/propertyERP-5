/**   
* Filename:    PlotpropertyDao.java   
* @version:    1.0  
* Create at:   2014年8月13日 上午8:24:04   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月13日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.plotproperty.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.plotproperty.entity.*;
import com.cnfantasia.server.api.property.dto.PayBillDetailDto;
import com.cnfantasia.server.api.property.dto.PropertyBillTailDto;
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.business.pub.page.PageQueryUtil;
import com.cnfantasia.server.domainbase.gbSoftwareConfig.entity.GbSoftwareConfig;
import com.cnfantasia.server.domainbase.payBill.dao.PayBillBaseDao;
import com.cnfantasia.server.domainbase.propertyService.entity.PropertyService;

/**
 * Filename:    PlotpropertyDao.java
 * @version:    1.0.0
 * Create at:   2014年8月13日 上午8:24:04
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月13日       shiyl             1.0             1.0 Version
 */
public class PlotpropertyDao extends AbstractBaseDao implements IPlotpropertyDao{
	
	private PayBillBaseDao payBillBaseDao;
	
	@Override
	public String selectLastPayBillMonth111(BigInteger userId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		return sqlSession.selectOne("plotproperty.select_Last_PayBill_Month", tmpMap);
	}
	
	@Override
	public String selectLastPayBillMonth(BigInteger userId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		return sqlSession.selectOne("plotproperty.select_Last_PayBill_Month_new", tmpMap);
	}
	
	@Override
	public List<PayBillEntity> selectPayBillByMonth111(BigInteger userId, String yearMonth) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("yearMonth", yearMonth);
		List<PayBillEntity> resPayBillEntityList = sqlSession.selectList("plotproperty.select_PayBill_ByMonth", tmpMap);
		return resPayBillEntityList;
	}

	@Override
	public Integer updateAndConfirmRealRoomInfo(BigInteger userId, BigInteger realRoomId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("realRoomId", realRoomId);
		return sqlSession.update("plotproperty.update_AndConfirm_RealRoomInfo", tmpMap);
	}

	@Override
	public List<String> selectExistPayBillYearList111(BigInteger userId,Integer payStatus, PageModel pageModel) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("payStatus", payStatus);
		String pageSqlKey = "plotproperty.select_Exist_PayBill_Year_List_page";
		String countSqlKey = "plotproperty.select_Exist_PayBill_Year_List_count";
		return PageQueryUtil.selectPageList(sqlSession, tmpMap, pageModel, pageSqlKey, countSqlKey);
	}
	@Override
	public List<String> selectExistPayBillYearList111(BigInteger userId,Integer payStatus) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("payStatus", payStatus);
		return sqlSession.selectList("plotproperty.select_Exist_PayBill_Year_List_All", tmpMap);
	}

	@Override
	public List<PayBillEntity> selectIsPayBillList111(BigInteger userId, List<String> yearList) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("yearList", yearList);
		return sqlSession.selectList("plotproperty.select_IsPay_Bill_List", tmpMap);
	}

	@Override
	public List<PayBillEntity> selectNotPayBillList111(BigInteger userId, List<String> yearList) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("yearList", yearList);
		return sqlSession.selectList("plotproperty.select_NotPay_Bill_List", tmpMap);
	}

	@Override
	public PayBillEntity selectPayBillDetailById(BigInteger userId, BigInteger payBillId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("payBillId", payBillId);
		return sqlSession.selectOne("plotproperty.select_PayBill_Detail_ById", tmpMap);
	}

	@Override
	public List<PlotpropertyOrderEntity> selectPlotpropertyOrderAllListByPayBillId(BigInteger userId, BigInteger payBillId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("payBillId", payBillId);
		return sqlSession.selectList("plotproperty.select_PlotpropertyOrder_AllList_By_PayBillId", tmpMap);
	}

	@Override
	public PlotpropertyOrderEntity selectPlotpropertyOrderDetail(BigInteger userId, BigInteger orderId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("orderId", orderId);
		return sqlSession.selectOne("plotproperty.select_PlotpropertyOrder_Detail", tmpMap);
	}
	
	@Override
	public List<String> selectPlotpropertyTotalOrderDetail(BigInteger orderId) {
		return sqlSession.selectList("plotproperty.select_PlotpropertyTotalOrder_Detail", orderId);
	}
	
	@Override
	public String selectOrderAddress(BigInteger roomId){
		return sqlSession.selectOne("plotproperty.select_order_address", roomId);
	}
	
	@Override
	public PlotpropertyOrderEntity selectPlotpropertyOrderDetail(Map<String, Object> paramMap) {
		List<PlotpropertyOrderEntity> plotpropertyList = sqlSession.selectList("plotproperty.select_PlotpropertyOrder_Detail_OnlyId02", paramMap);
		if(plotpropertyList != null && plotpropertyList.size() > 0) {
			return plotpropertyList.get(0);
		}
		return null;		 
	}
	
	@Override
	public PlotpropertyOrderEntity getPlotpropertyOrderDetailByOrderIdAndBillId(BigInteger orderId, BigInteger payBillId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("orderId", orderId);
		tmpMap.put("payBillId", payBillId);
		return sqlSession.selectOne("plotproperty.select_PlotpropertyOrder_Detail_OnlyId", tmpMap);
	}
	
	@Override
	public Integer updatePayBillInfoSuccessByApp(BigInteger payBillId,Long succPayAmount,Double discount,BigInteger prizeRecordId,Long hbAmount, BigInteger preferType,Integer cycleType) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("payBillId", payBillId);
		tmpMap.put("succPayAmount", succPayAmount);
		tmpMap.put("discount", discount);
		tmpMap.put("prizeRecordId", prizeRecordId);
		tmpMap.put("hbAmount", hbAmount);
		tmpMap.put("preferType", preferType);
		if(cycleType != null && cycleType == 2) {
			tmpMap.put("sys0DelState", 0);//将账单的删除状态更改为”正常“ V340 选择周期的账单删除状态为1，只有支付成功后才改为0（防止后台出现未支付数据）
		}
		return sqlSession.update("plotproperty.update_PayBillInfo_Success_ByApp", tmpMap);
	}

	@Override
	public Integer delLogicPlotpropertyOrderAndBackDiscount(BigInteger orderId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("orderId", orderId);
		return sqlSession.update("plotproperty.delLogic_PlotpropertyOrder_And_BackDiscount", tmpMap);
	}
	@Override
	public Integer delLogicPlotpropertyOrderAndBackDiscountAll() {
		sqlSession.update("plotproperty.delLogic_PlotpropertyOrder_And_BackDiscount_All02");
		return sqlSession.update("plotproperty.delLogic_PlotpropertyOrder_And_BackDiscount_All");
	}

	@Override
	public Integer selectCheckRoomCountByRealRoomId(BigInteger userId, BigInteger realRoomId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("userId", userId);
		tmpMap.put("realRoomId", realRoomId);
		return sqlSession.selectOne("plotproperty.select_CheckRoom_Count_By_RealRoomId", tmpMap);
	}

	@Override
	public List<PropertyService> selectPlotpropertyServiceList(BigInteger groupBuildingId) {
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("groupBuildingId", groupBuildingId);
		return sqlSession.selectList("plotproperty.select_PlotpropertyService_List", tmpMap);
	}
	
	public Propfee getPropertyFeeAndCount(Map<String, Object> paramMap) {
		Propfee propfee = sqlSession.selectOne("plotproperty.getPropertyFeeAndCount", paramMap);
		return propfee;
	}

	/**
	 * 查询账单信息
	 * 
	 * @param realRoomId
	 * @param isPay
	 * @return
	 */
	@Override
	public String getBillInfo(BigInteger realRoomId, String isPay){
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("realRoomId", realRoomId);
		tmpMap.put("isPay", isPay);
		return sqlSession.selectOne("plotproperty.getBillInfo", tmpMap);
	}
	
	/**
	 * 是否用物业宝免缴本月账单
	 * 
	 * @param realRoomId
	 * @return
	 */
	@Override
	public Integer getWYBPayStatus(BigInteger realRoomId){
		Map<String,Object> tmpMap = new HashMap<String, Object>();
		tmpMap.put("realRoomId", realRoomId);
		return sqlSession.selectOne("plotproperty.getWYBPayStatus", tmpMap);
	}
	
	@Override
	public List<PropertyBillEntity> getInCycleBillByMonth(BigInteger userId) {
		return sqlSession.selectList("plotproperty.qryNotPayPayBillMonth", userId);
	}

	@Override
	public PropertyBillEntity getInCycleBillByDate(BigInteger userId, BigInteger billType) {
		Map<String, BigInteger> map = new HashMap<String, BigInteger>();
		map.put("billType", billType);
		map.put("userId", userId);
		return sqlSession.selectOne("plotproperty.qryBillByDate", map);
	}

	@Override
	public PlotpropertyOrderEntity selectPlotpropertyNotPayOrderDetail(Map<String, Object> paramMap) {
		List<PlotpropertyOrderEntity> plotpropertyList = sqlSession.selectList("plotproperty.select_PlotpropertyNotPayOrder_Detail_OnlyId", paramMap);
		if(plotpropertyList != null && plotpropertyList.size() > 0) {
			return plotpropertyList.get(0);
		}
		return null;	
	}

	public void setPayBillBaseDao(PayBillBaseDao payBillBaseDao) {
		this.payBillBaseDao = payBillBaseDao;
	}

	@Override
	public Long getPreferentialRandom(BigInteger userId, BigInteger billId) {
		Map<String, BigInteger> parMap = new HashMap<String, BigInteger>();
		parMap.put("payBillId", billId);
		parMap.put("userId", userId);
		Map<String, Object> resMap = sqlSession.selectOne("plotproperty.select_preferential_random", parMap);
		return (Long) resMap.get("f_amount");
	}

	@Override
	public PlotpropertyOrderEntity getPlotpropertyNoOrderDetail(Map<String, Object> paramMap) {
		List<PlotpropertyOrderEntity> plotpropertyList = sqlSession.selectList("plotproperty.select_PlotpropertyNoOrder_Detail_OnlyId", paramMap);
		if(plotpropertyList != null && plotpropertyList.size() > 0) {
			return plotpropertyList.get(0);
		}
		return null;
	}

	@Override
	public List<PropertyBillEntity> getInCycleBillByUserId(Map<String, Object> paraMap) {
		return sqlSession.selectList("plotproperty.select_plotpropertyService_List_by_userId", paraMap);
	}

	@Override
	public Map<String, String> selectPreferentialCycle(BigInteger payBillId, String billMonth) {
		Map<String, Object> parMap = new HashMap<String, Object>();
		parMap.put("payBillId", payBillId);
		parMap.put("billMonth", billMonth);
		return sqlSession.selectOne("plotproperty.select_plotpropertyService_month_by_billId", parMap);
	}

	@Override
	public int isHasPayBillRecordByUserIdAndRealRoomId(BigInteger userId, BigInteger realRoomId) {
		Map<String, BigInteger> parMap = new HashMap<String, BigInteger>();
		parMap.put("userId", userId);
		parMap.put("realRoomId", realRoomId);
		return sqlSession.selectOne("plotproperty.select_is_has_pay_bill_record_by_userId", parMap);
	}

	@Override
	public Long getTotalAmtNow(BigInteger userId) {
		return sqlSession.selectOne("plotproperty.select_totalamount_by_userId", userId);
	}

	@Override
	public String getRealRoomProprietorName(BigInteger realRoomId) {
		return sqlSession.selectOne("plotproperty.select_real_room_proprietor_by_realRooma", realRoomId);
	}

	@Override
	public GbSoftwareConfig select_gbSoftwareConfig_by_realRoom(BigInteger realRoomId) {
		return sqlSession.selectOne("plotproperty.select_gbSoftwareConfig_by_realRoom", realRoomId);
	}

	@Override
	public List<AlterCyclesEntity> getAlterPayBillEntity(Map<String, Object> paraMap) {
		return sqlSession.selectList("plotproperty.getAlterPayBillEntity", paraMap);
	}
	
	@Override
	public List<PayBillDetailDto> getPayBillDetailByIds(List<BigInteger> idsList) {
		Map<String, Object> parMap = new HashMap<String, Object>();
		parMap.put("idsList", idsList);
		return sqlSession.selectList("plotproperty.getPayBillDetailByIds", parMap);
	}

	@Override
	public List<Map<String, Object>> getNeedAlterUpdate(BigInteger orderId) {
		return sqlSession.selectList("plotproperty.getNeedAlterUpdate", orderId);
	}

	@Override
	public PropertyBillTailDto getRoomAddressAndPPName(BigInteger realRoomId) {
		return sqlSession.selectOne("plotproperty.getRoomAddressAndPPName", realRoomId);
	}

	@Override
	public Long getTotalUnPaidAmtNow(BigInteger userId) {
		return sqlSession.selectOne("plotproperty.getTotalUnPaidAmtNow", userId);
	}
}
