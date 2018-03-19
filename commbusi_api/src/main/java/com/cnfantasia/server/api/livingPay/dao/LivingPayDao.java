package com.cnfantasia.server.api.livingPay.dao;

import com.cnfantasia.server.api.livingPay.entity.Info4AppleRevenue;
import com.cnfantasia.server.api.livingPay.entity.Info4Revenue;
import com.cnfantasia.server.api.livingPay.entity.PayItem;
import com.cnfantasia.server.api.livingPay.entity.PayRecord;
import com.cnfantasia.server.api.livingPay.entity.PayRecord4RevenueExport;
import com.cnfantasia.server.api.livingPay.entity.PayRecordRevenue;
import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.business.pub.page.TotalCountGetter;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 生活缴费Dao
 * @Author: wenfq
 * @Date: 2017-11-13 11:14
 */
public class LivingPayDao extends AbstractBaseDao {

    public List<PayItem> qryPayItemList() {
        return sqlSession.selectList("livingPay.selectLivingPayItemList");
    }

    public List<PayRecord> qryLivingPayRecordList(Map<String, Object> paramMap) {
        return sqlSession.selectList("livingPay.qryLivingPayRecordList", paramMap);
    }

    public List<PayRecordRevenue> qryLivingPayRevenueList(Map<String, Object> paramMap) {
        return sqlSession.selectList("livingPay.qryLivingPayRevenueList", paramMap);
    }

    public int qryLivingPayRevenueListTotalCount(Map<String, Object> paramMap) {
       return TotalCountGetter.getTotalCount(sqlSession, "livingPay.qryLivingPayRevenueList", paramMap);
    }

    public String qryProductInfoByOrderId(BigInteger orderId) {
        return sqlSession.selectOne("livingPay.qryProductInfoByOrderId", orderId);
    }

    /**
     * 查询待产生收益明细的缴费单
     * @return
     */
    public List<Info4Revenue> selectInfo4RevenueList() {
        return sqlSession.selectList("livingPay.selectInfo4RevenueList");
    }

    public List<Info4AppleRevenue> selectInfo4ApplyRevenue() {
        return sqlSession.selectList("livingPay.selectInfo4ApplyRevenue");
    }

    /**
     * 更新提款单的银行账号信息
     * @param applyId
     */
    public void updBankInfo(BigInteger applyId) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("applyId", applyId);
        sqlSession.update("revenue.update_RevenueApply_BakInfo_LivingFeePay", paramMap);
    }

    public List<PayRecord4RevenueExport> qrypayRecord4RevenueExportList(Map<String, Object> paramMap) {
        return sqlSession.selectList("livingPay.qrypayRecord4RevenueExportList", paramMap);
    }
}
