package com.cnfantasia.server.api.lateFee.util;

import com.alibaba.fastjson.JSON;
import com.cnfantasia.server.api.lateFee.calculateMethod.ICalculateLateFee;
import com.cnfantasia.server.api.lateFee.entity.FeeItemHasLateFee;
import com.cnfantasia.server.api.lateFee.entity.PayBillForLateFee;
import com.cnfantasia.server.api.lateFee.service.CalculateLateFeeService;
import com.cnfantasia.server.api.pub.utils.CnfantasiaCommbusiUtil;
import com.cnfantasia.server.common.utils.DataUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.Callable;

/**
 * @className: CalculateLateFeeRunnable
 * @date: 2017-11-06 15:28
 * @author: yanghua
 * @description:(滞纳金计算)
 */
public class CalculateLateFeeRunnable implements Callable<Boolean> {
    private static Log logger = LogFactory.getLog(CalculateLateFeeRunnable.class);
    private CalculateLateFeeService calculateLateFeeService;
    private ICalculateLateFee calculateLateFee;
    private BigInteger id;
    private BigInteger userId;

    /**
     * @param calculateLateFee 对应：CalculateLateFeeByRoomImp房间，CalculateLateFeeByPayBillImp账单，CalculateLateFeeByGbImp小区...
     * @param id 不同实现对应不同ID：小区ID，房间ID，账单ID...
     * @param userId 用户ID
     */
    public CalculateLateFeeRunnable(ICalculateLateFee calculateLateFee, BigInteger id, BigInteger userId) {
        this.calculateLateFee = calculateLateFee;
        this.id = id;
        this.userId = userId;

        calculateLateFeeService = (CalculateLateFeeService) CnfantasiaCommbusiUtil.getBeanManager("calculateLateFeeService");
    }

    @Override
    public Boolean call() throws Exception {
        //获取数据
        List<PayBillForLateFee> calculateLateFeeData = calculateLateFee.getCalculateLateFeeData(id);
        logger.debug("[calculateLateFeeData]" + JSON.toJSONString(calculateLateFeeData));
        if(!DataUtil.isEmpty(calculateLateFeeData)) {
            //查询需要进行计算的费用项
            FeeItemHasLateFee feeItemHasLateFee = calculateLateFee.getHasCalculateLateFeeItems(id);
            logger.debug("[feeItemHasLateFee]" + JSON.toJSONString(feeItemHasLateFee));
            if(!DataUtil.isEmpty(feeItemHasLateFee)) {
                //计算保存
                Boolean calculate = calculateLateFeeService.calculate(calculateLateFeeData, feeItemHasLateFee, userId);

                return calculate;
            }
        }

        return false;
    }
}
