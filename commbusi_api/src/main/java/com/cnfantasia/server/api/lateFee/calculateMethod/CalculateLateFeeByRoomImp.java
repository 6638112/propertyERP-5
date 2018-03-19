package com.cnfantasia.server.api.lateFee.calculateMethod;

import com.cnfantasia.server.api.lateFee.dao.CalculateLateFeeDao;
import com.cnfantasia.server.api.lateFee.entity.FeeItemHasLateFee;
import com.cnfantasia.server.api.lateFee.entity.PayBillForLateFee;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.List;

/**
 * @className: CalculateLateFeeByRoomImp
 * @date: 2017-11-06 15:43
 * @author: yanghua
 * @description:(根据房间计算)
 */
public class CalculateLateFeeByRoomImp implements ICalculateLateFee{
    @Resource
    private CalculateLateFeeDao calculateLateFeeDao;

    @Override
    public List<PayBillForLateFee> getCalculateLateFeeData(BigInteger realRoomId) {
        return calculateLateFeeDao.getCalculateLateFeeDataByRoom(realRoomId);
    }

    @Override
    public FeeItemHasLateFee getHasCalculateLateFeeItems(BigInteger realRoomId) {
        BigInteger gbId = calculateLateFeeDao.getGbIdByRoomId(realRoomId);
        if(gbId != null) {
            return calculateLateFeeDao.getHasCalculateLateFeeItems(gbId);
        }
        return null;
    }
}
