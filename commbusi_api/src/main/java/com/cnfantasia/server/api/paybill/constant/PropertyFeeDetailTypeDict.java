package com.cnfantasia.server.api.paybill.constant;

/**
 * @ClassName: PropertyFeeDetailTypeDict
 * @Date: 2017-05-09 10:54
 * @Auther: yanghua
 * @Description:(表t_property_fee_detail字段f_type的值)
 */
public final class PropertyFeeDetailTypeDict {
    //"1":"管理费"
    public static final Integer GUAN_LI = 1;
    //"2":"主体金"
    public static final Integer ZHU_TI_JIN = 2;
    //"3":"垃圾处理费"
    public static final Integer LA_JI_CHU_LI = 3;
    //"4":"水费"
    public static final Integer SHUI = 4;
    //"5":"污水处理费"
    public static final Integer WU_SHUI_CHU_LI = 5;
    //"8":"往月欠费"
    public static final Integer LAST_UNPAID = 8;
    //"9":"其它"
    public static final Integer OTHER = 9;
    //"10": 选择周期
    public static final Integer ALTER = 10;
    //"11":滞纳金
    public static final Integer LATEFEE = 11;
}
