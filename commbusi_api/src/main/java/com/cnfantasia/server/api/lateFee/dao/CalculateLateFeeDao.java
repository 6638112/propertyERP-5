package com.cnfantasia.server.api.lateFee.dao;

import com.cnfantasia.server.api.lateFee.entity.FeeItemHasLateFee;
import com.cnfantasia.server.api.lateFee.entity.PayBillForLateFee;
import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.domainbase.fixedFeeItem.dao.IFixedFeeItemBaseDao;
import com.cnfantasia.server.domainbase.fixedFeeItem.entity.FixedFeeItem;
import com.cnfantasia.server.domainbase.mrFeeItem.dao.IMrFeeItemBaseDao;
import com.cnfantasia.server.domainbase.mrFeeItem.entity.MrFeeItem;
import com.cnfantasia.server.domainbase.tmpFeeItem.dao.ITmpFeeItemBaseDao;
import com.cnfantasia.server.domainbase.tmpFeeItem.entity.TmpFeeItem;

import java.math.BigInteger;
import java.util.*;

/**
 * @className: CalculateLateFeeDao
 * @date: 2017-11-08 13:33
 * @author: yanghua
 * @description:(滞纳金计算)
 */
public class CalculateLateFeeDao extends AbstractBaseDao {
    private ITmpFeeItemBaseDao tmpFeeItemBaseDao;
    private IMrFeeItemBaseDao mrFeeItemBaseDao;
    private IFixedFeeItemBaseDao fixedFeeItemBaseDao;

    public void setFixedFeeItemBaseDao(IFixedFeeItemBaseDao fixedFeeItemBaseDao) {
        this.fixedFeeItemBaseDao = fixedFeeItemBaseDao;
    }

    public void setMrFeeItemBaseDao(IMrFeeItemBaseDao mrFeeItemBaseDao) {
        this.mrFeeItemBaseDao = mrFeeItemBaseDao;
    }

    public void setTmpFeeItemBaseDao(ITmpFeeItemBaseDao tmpFeeItemBaseDao) {
        this.tmpFeeItemBaseDao = tmpFeeItemBaseDao;
    }

    /**
     *根据房间号查询需要计算滞纳金的数据
     * @param realRoomId
     * @return
     */
    public List<PayBillForLateFee> getCalculateLateFeeDataByRoom(BigInteger realRoomId) {
        return sqlSession.selectList("latefee.getCalculateLateFeeDataByRoom", realRoomId);
    }

    /**
     * 根据小区ID查询需要计算滞纳金的数据
     * @param gbId
     * @return
     */
    public List<PayBillForLateFee> getCalculateLateFeeDataByGb(BigInteger gbId) {
        return sqlSession.selectList("latefee.getCalculateLateFeeDataByGb", gbId);
    }

    /**
     * 根据payBillId查询需要计算滞纳金的数据
     * @param payBillId
     * @return
     */
    public List<PayBillForLateFee> getCalculateLateFeeDataByPayBill(BigInteger payBillId) {
        return sqlSession.selectList("latefee.getCalculateLateFeeDataByPayBill", payBillId);
    }

    /**
     * 清除上次计算的滞纳金金额
     * @param payBillIds
     * @return
     */
    public int deleteOldLateFeeDetailByPayBillIds(List<BigInteger> payBillIds) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("payBillIds", payBillIds);
        return sqlSession.update("latefee.deleteOldLateFeeDetailByPayBillIds", param);
    }

    public BigInteger getGbIdByPayBillId(BigInteger payBillId) {
        return sqlSession.selectOne("latefee.getGbIdByPayBillId", payBillId);
    }

    public BigInteger getGbIdByRoomId(BigInteger realRoomId) {
        return sqlSession.selectOne("latefee.getGbIdByRoomId", realRoomId);
    }

    /**根据小区ID查询需要计算滞纳金的费用项
     *
     * @param gbId
     * @return
     */
    public FeeItemHasLateFee getHasCalculateLateFeeItems(BigInteger gbId) {
        Set<String> mrFeeItemNames = new HashSet<>();
        Set<String> fixedFeeItemNames = new HashSet<>();
        Set<String> tmpFeeItemNames = new HashSet<>();

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("tGbId", gbId);
        paramMap.put("isCalculateLatefee", 1);
        //常规收费项设置
        List<FixedFeeItem> itemList = fixedFeeItemBaseDao.selectFixedFeeItemByCondition(paramMap, false);
        for (FixedFeeItem fixedFeeItem : itemList) {
            fixedFeeItemNames.add(fixedFeeItem.getName().trim());
        }
        //临时收费
        List<TmpFeeItem> tmpFeeItemList = tmpFeeItemBaseDao.selectTmpFeeItemByCondition(paramMap, false);
        for (TmpFeeItem tmpFeeItem : tmpFeeItemList) {
            tmpFeeItemNames.add(tmpFeeItem.getName().trim());
        }
        //抄表收费配置
        Map<String, Object> paramMap1 = new HashMap<String, Object>();
        paramMap1.put("gbId", gbId);
        paramMap1.put("isCalculateLatefee", 1);
        List<MrFeeItem> mriList = mrFeeItemBaseDao.selectMrFeeItemByCondition(paramMap1, false);
        for (MrFeeItem mrFeeItem : mriList) {
            mrFeeItemNames.add(mrFeeItem.getName().trim());
        }

        FeeItemHasLateFee feeItemHasLateFee = new FeeItemHasLateFee();
        feeItemHasLateFee.setFixedFeeItemNames(fixedFeeItemNames);
        feeItemHasLateFee.setMrFeeItemNames(mrFeeItemNames);
        feeItemHasLateFee.setTmpFeeItemNames(tmpFeeItemNames);

        return feeItemHasLateFee;
    }

    /**
     * 更新账单的滞纳金金额
     * @param payBills
     * @param userId
     * @return
     */
    public Integer updatePayBillLateFeeBatch(List<BigInteger> payBills, BigInteger userId) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("payBills", payBills);
        param.put("userId", userId);
        return sqlSession.update("latefee.updatePayBillLateFeeBatch", param);
    }

    /**
     * 清除已经逻辑删除的滞纳金明细数据
     * @param paraMap
     * @return
     */
    public int deleteLogicLateFeeDetails(Map<String, Object> paraMap) {
        return sqlSession.update("latefee.deleteLogicLateFeeDetails", paraMap);
    }
}
