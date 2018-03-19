package com.cnfantasia.server.api.lateFee.calculateMethod;

import com.cnfantasia.server.api.lateFee.dao.CalculateLateFeeDao;
import com.cnfantasia.server.api.lateFee.entity.FeeItemHasLateFee;
import com.cnfantasia.server.api.lateFee.entity.PayBillForLateFee;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

/**
 * @className: CalculateLateFeeByPayBillImp
 * @date: 2017-11-06 15:43
 * @author: yanghua
 * @description:(根据账单计算)：注意 此类没有完善，只能在当期账单中使用，欠费无法使用该方法
 */
public class CalculateLateFeeByPayBillImp implements ICalculateLateFee{
    @Resource
    private CalculateLateFeeDao calculateLateFeeDao;

    @Override
    public List<PayBillForLateFee> getCalculateLateFeeData(BigInteger payBillId) {
        return calculateLateFeeDao.getCalculateLateFeeDataByPayBill(payBillId);
    }

    @Override
    public FeeItemHasLateFee getHasCalculateLateFeeItems(BigInteger payBillId) {
        BigInteger gbId = calculateLateFeeDao.getGbIdByPayBillId(payBillId);
        if(gbId != null) {
            return calculateLateFeeDao.getHasCalculateLateFeeItems(gbId);
        }
        return null;
    }
}
