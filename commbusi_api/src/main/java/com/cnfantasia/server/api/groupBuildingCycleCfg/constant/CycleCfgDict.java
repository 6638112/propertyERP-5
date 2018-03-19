package com.cnfantasia.server.api.groupBuildingCycleCfg.constant;

/**
 * @ClassName: CycleCfgDict
 * @Date: 2017-07-11 10:04
 * @Auther: yanghua
 * @Description:(账期配置信息)
 */
public final class CycleCfgDict {
	
	public static final class RechargeMode{
		/**收费模式（1定期）*/
	    public static final Integer FIXED_CYCLE = 1;
	    /**收费模式（2选择周期）*/
	    public static final Integer ALTER_CYCLE = 2;
	    /**收费模式（3预存收费）*/
	    public static final Integer PROPERTY_RECHARGE = 3;
	}
    
    /**银行托收（1托收）*/
    public static final Integer BANK_COLLECTION = 1;
    /**银行托收（2不托收）*/
    public static final Integer NOT_BANK_COLLECTION = 2;
    /**是否收取欠费（2收取）*/
    public static final Integer ARREARS_TRANSFER = 2;
    /**是否收取欠费（1暂时不做处理）*/
    public static final Integer NOT_ARREARS_TRANSFER = 1;

}
