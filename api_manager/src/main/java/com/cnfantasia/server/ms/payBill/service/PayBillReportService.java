package com.cnfantasia.server.ms.payBill.service;

import com.cnfantasia.server.ms.payBill.dao.PayBillReportDao;
import com.cnfantasia.server.ms.payBill.entity.FeeCollectionViewEntity;
import com.cnfantasia.server.ms.payBill.entity.GroupBuildingReportEntity;
import com.cnfantasia.server.ms.payBill.entity.PropertyFeeReportEntity;
import com.cnfantasia.server.ms.payBill.entity.PropertyManagementReportEntity;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: PayBillReportService
 * @Date: 2017-06-08 16:06
 * @Auther: yanghua
 * @Description:(收费报表)
 */
public class PayBillReportService {
    private PayBillReportDao payBillReportDao;
    /**
     * 收费情况汇总报表
     * @param paraMap
     * @return
     */
    public List<FeeCollectionViewEntity> getFeeCollectionList(Map<String, Object> paraMap) {
        //小区缴费合计信息
        List<FeeCollectionViewEntity> feeCollectionList = payBillReportDao.getFeeCollectionList(paraMap);
        return feeCollectionList;
    }

    /**
     * 查询小区列表
     * @param paraMap
     * @return
     */
    public List<Map<BigInteger, String>> getGroupBuildingList(Map<String, Object> paraMap) {
        return payBillReportDao.getGroupBuildingList(paraMap);
    }

    /**
     * 查询管理处列表
     * @param paraMap
     * @return
     */
    public List<Map<BigInteger, String>> getPmList(Map<String, Object> paraMap) {
        return payBillReportDao.getPmList(paraMap);
    }

    /**
     * 查询楼栋列表
     * @param paraMap
     * @return
     */
    public List<Map<BigInteger, String>> getBuildingList(Map<String, Object> paraMap) {
        return payBillReportDao.getBuildingList(paraMap);
    }

    public void setPayBillReportDao(PayBillReportDao payBillReportDao) {
        this.payBillReportDao = payBillReportDao;
    }
}
