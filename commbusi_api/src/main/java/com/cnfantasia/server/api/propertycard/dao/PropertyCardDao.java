package com.cnfantasia.server.api.propertycard.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.propertycard.entity.RealRoomConfig;
import com.cnfantasia.server.api.propertycard.entity.RealRoomShowNameAndPayEnd;
import com.cnfantasia.server.api.propertycard.entity.TransLog;
import com.cnfantasia.server.api.propertycard.entity.UserHasCardWithGroupBuildingNames;
import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.domainbase.payBill.entity.PayBill;
import com.cnfantasia.server.domainbase.userHasPropertyCard.entity.UserHasPropertyCard;
import com.cnfantasia.server.domainbase.userSetRealroomDeductionConfig.entity.UserSetRealroomDeductionConfig;

public class PropertyCardDao extends AbstractBaseDao  {

	/**
	 * 根据realRoomId找到当期未缴费的  账单
	 * 
	 * @param realRoomId
	 *            真实房间id
	 * @return 账单信息
	 */
	public List<PayBill> qryPayBillByRealRoomId(BigInteger realRoomId, boolean checkBankCollection) {
		Map<String, Object> param = new HashMap<>();
		param.put("realRoomId", realRoomId);
		param.put("checkBankCollection", checkBankCollection);
		return sqlSession.selectList("propertycard.qryPayBillByRealRoomId", param);
	}
	
	/**
	 * 根据realRoomId找到当期未缴费的 周期 账单
	 * 
	 * @param gbId
	 *            小区id
	 * @return 该小区下所有开户划扣配置房间
	 */
	public List<UserSetRealroomDeductionConfig> qryDeductionConfigList(BigInteger gbId) {
		return sqlSession.selectList("propertycard.qryDeductionConfigList", gbId);
	}
	
	public List<UserSetRealroomDeductionConfig> qryDeductionConfigAllList(Map<String, Object> paramMap) {
		return sqlSession.selectList("propertycard.qryDeductionConfigAllList", paramMap);
	}
	
    /**
     * 查询指定用户的代扣卡交易流水
     * @param userId 用户ID
     * @param pageModel 分页信息
     * @return
     */
    public List<TransLog> getTransLogListByUserId(BigInteger userId, PageModel pageModel) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("userId", userId);
        if (pageModel != null) {
            param.put("begin", pageModel.begin);
            param.put("length", pageModel.length);
        }
        return sqlSession.selectList("propertycard.getTransLogListByUserId", param);
    }

    /**
     *  根据orderId查询物业代扣卡的订单详情 
     * @param orderId 订单Id
     * @return
     */
	public TransLog getTransLogByOrderId(BigInteger orderId) {
		return sqlSession.selectOne("propertycard.getTransLogByOrderId", orderId);
	}

	public List<UserHasCardWithGroupBuildingNames> qryUserHasPropertyCardList(Map<String, Object> paramMap) {
		return sqlSession.selectList("propertycard.qryUserHasPropertyCardList", paramMap);
	}
	
	public Integer qryUserHasPropertyCardCount(Map<String, Object> paramMap) {
		return sqlSession.selectOne("propertycard.qryUserHasPropertyCardCount", paramMap);
	}
	
	public RealRoomShowNameAndPayEnd select_realRoom_showName_byRealRoomId(BigInteger realRoomId){
		return sqlSession.selectOne("propertycard.select_realRoom_showName_byRealRoomId", realRoomId);
	}

	public UserHasPropertyCard qrySumAmtByUserId(BigInteger userId) {
		return sqlSession.selectOne("propertycard.qryUserHasPropertyCardAmtSum", userId);
	}

	public List<RealRoomConfig> getPropfeeCanPayRoomByUserId(BigInteger userId) {
		return sqlSession.selectList("propertycard.qryPropfeeCanPayRoomByUserId", userId);
	}

	public int updatePropCardStatus(BigInteger userId) {
		return sqlSession.update("propertycard.updatePropCardStatusClose", userId);
	}

	public List<Map<String, Object>> getGroupBuildingPayBillAvgAmt(BigInteger userId) {
		return sqlSession.selectList("propertycard.qryGroupBuildingBillAmtAveByUserId", userId);
	}

	/**
	 * 查询历史未缴欠费
	 * @param payBillMonthList 当前可缴账单
	 * @param checkBankCollection 是否要排除银行托收中的
	 * @return
	 */
	public List<PayBill> qryUnPaidPayBillByPayBillList(List<PayBill> payBillMonthList, boolean checkBankCollection) {
		Map<String, Object> param = new HashMap<>();
		param.put("payBillMonthList", payBillMonthList);
		param.put("checkBankCollection", checkBankCollection);
		return sqlSession.selectList("propertycard.qryUnPaidPayBillByPayBillList", param);
	}

}