package com.cnfantasia.server.api.lateFee.calculateMethod;

import com.cnfantasia.server.api.lateFee.dao.CalculateLateFeeDao;
import com.cnfantasia.server.api.lateFee.entity.FeeItemHasLateFee;
import com.cnfantasia.server.api.lateFee.entity.PayBillForLateFee;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

/**
 * @className: CalculateLateFeeByGbImp
 * @date: 2017-11-06 15:42
 * @author: yanghua
 * @description:(根据小区计算)
 */
public class CalculateLateFeeByGbImp implements ICalculateLateFee{
    @Resource
    private CalculateLateFeeDao calculateLateFeeDao;

    @Override
    public List<PayBillForLateFee> getCalculateLateFeeData(BigInteger gbId) {
        return calculateLateFeeDao.getCalculateLateFeeDataByGb(gbId);
    }

    @Override
    public FeeItemHasLateFee getHasCalculateLateFeeItems(BigInteger gbId) {
        return calculateLateFeeDao.getHasCalculateLateFeeItems(gbId);
    }
}
