package com.cnfantasia.server.api.lateFee.calculateMethod;

import com.cnfantasia.server.api.lateFee.entity.FeeItemHasLateFee;
import com.cnfantasia.server.api.lateFee.entity.PayBillForLateFee;

import java.math.BigInteger;
import java.util.List;

/**
 * @className: ICalculateLateFee
 * @date: 2017-11-06 15:34
 * @author: yanghua
 * @description:(获取计算滞纳金需要的数据)
 */
public interface ICalculateLateFee {
    /**
     * 查询需要计算的数据
     * @param id 小区、房间、账单ID
     * @return
     */
    List<PayBillForLateFee> getCalculateLateFeeData(BigInteger id);

    /**
     * 查询需要计算的费用项
     * @param id 小区、房间、账单ID
     * @return
     */
    FeeItemHasLateFee getHasCalculateLateFeeItems(BigInteger id);
}
