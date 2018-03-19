package com.cnfantasia.server.api.lateFee.constant;

/**
 * @className: LateFeeDict
 * @date: 2017-11-02 13:46
 * @author: yanghua
 * @description:(滞纳金)
 */
public final class LateFeeDict {
    /**滞纳金按月计算*/
    public static final Integer CALCULATE_BY_MONTH = 1;
    /**滞纳金按天计算*/
    public static final Integer CALCULATE_BY_DAY = 2;
    /**不收取滞纳金*/
    public static final Integer CALCULATE_LATEFEE_FALSE = 2;
    /**收取滞纳金*/
    public static final Integer CALCULATE_LATEFEE_TRUE = 1;
}
