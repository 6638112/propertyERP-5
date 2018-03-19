package com.cnfantasia.server.ms.payBill.dao;

import com.cnfantasia.server.ms.payBill.entity.FeeCollectionViewEntity;
import com.cnfantasia.server.ms.payBill.entity.PropertyFeeReportEntity;
import com.cnfantasia.server.ms.pub.dao.AbstractBaseDao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: PayBillReportDao
 * @Date: 2017-06-08 16:07
 * @Auther: yanghua
 * @Description:(收费报表)
 */
public class PayBillReportDao extends AbstractBaseDao{
    public List<FeeCollectionViewEntity> getFeeCollectionList(Map<String, Object> paraMap) {
        return sqlSession.selectList("payBillReport.getFeeCollectionList", paraMap);
    }

    public List<PropertyFeeReportEntity> getFeeReportEntityList(Map<String, Object> paraMap) {
        return sqlSession.selectList("payBillReport.getFeeReportEntityList", paraMap);
    }

    public List<Map<BigInteger,String>> getGroupBuildingList(Map<String, Object> paraMap) {
        return sqlSession.selectList("payBillReport.getGroupBuildingList", paraMap);
    }

    public List<Map<BigInteger, String>> getPmList(Map<String, Object> paraMap) {
        return sqlSession.selectList("payBillReport.getPmList", paraMap);
    }

    public List<Map<BigInteger, String>> getBuildingList(Map<String, Object> paraMap) {
        return sqlSession.selectList("payBillReport.getBuildingList", paraMap);
    }
}
