package com.cnfantasia.server.api.livingPay.constant;

/**
 * @Author: wenfq
 * @Date: 2017-11-14 18:35
 */
public class LivingPayDict {
    /**
     * 生活缴费状态-未缴费
     */
    public static int LivingPayRecordStatus_UnPay = -1;

    /**
     * 生活缴费状态-未充值
     */
    public static int LivingPayRecordStatus_UnCharge = 0;
    /**
     * 生活缴费状态-已充值
     */
    public static int LivingPayRecordStatus_HasCharge = 1;

    /**
     * 生活缴费提款状态-未提
     */
    public static int LivingPayRecordTKStatus_WeiTi = 0;
    /**
     * 生活缴费提款状态-提款中
     */
    public static int LivingPayRecordTKStatus_Doing = 1;
    /**
     * 生活缴费提款状态-已结算
     */
    public static int LivingPayRecordTKStatus_Done = 2;

}

