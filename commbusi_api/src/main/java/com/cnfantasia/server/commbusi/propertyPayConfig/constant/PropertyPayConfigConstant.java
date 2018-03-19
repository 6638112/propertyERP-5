package com.cnfantasia.server.commbusi.propertyPayConfig.constant;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

/**
 * 物业收费配置
 * 
 * @author liyulin
 * @version 1.0 2016年10月20日 上午11:23:29
 */
public final class PropertyPayConfigConstant {
	public static final Map<String, CalExpItem> CAL_EXP_SELEMENTS = new LinkedHashMap<String, CalExpItem>();
	static {
		Random rand = new Random();
		CAL_EXP_SELEMENTS.put(CalElement.PAYMENT_PER_MONTH,       new CalExpItem("每月应缴费用",        true,  rand.nextInt(10000)));
		CAL_EXP_SELEMENTS.put(CalElement.ACCRUAL_LATE_PAY,        new CalExpItem("滞纳金利率",         true,  rand.nextInt(10000))); 
		CAL_EXP_SELEMENTS.put(CalElement.WY_START_TIME_BY_MONTH,  new CalExpItem("物业费开始时间（按月计）", true,  rand.nextInt(10000)));
		CAL_EXP_SELEMENTS.put(CalElement.WY_END_TIME_BY_MONTH,    new CalExpItem("物业费截止时间（按月计）", false, rand.nextInt(10000)));
		CAL_EXP_SELEMENTS.put(CalElement.ZNJ_START_TIME_BY_DAY,   new CalExpItem("滞纳金开始时间（按天计）", true,  rand.nextInt(10000)));
		CAL_EXP_SELEMENTS.put(CalElement.ZNJ_START_TIME_BY_MONTH, new CalExpItem("滞纳金开始时间（按月计）", true,  rand.nextInt(10000)));
		CAL_EXP_SELEMENTS.put(CalElement.USER_PAY_TIME_PER_DAY,   new CalExpItem("用户缴费时间（按天计）",  true,  rand.nextInt(10000)));
		CAL_EXP_SELEMENTS.put(CalElement.USER_PAY_TIME_PER_MONTH, new CalExpItem("用户缴费时间（按月计）",  true,  rand.nextInt(10000)));
	}

	/**
	 * 计算元素
	 * 
	 * @author liyulin
	 * @version 1.0 2016年10月20日 上午11:23:13
	 */
	public interface CalElement {
		/** 每月应缴费用 */
		String PAYMENT_PER_MONTH = "A";
		/** 滞纳金利率 */
		String ACCRUAL_LATE_PAY = "B";
		/** 物业费开始时间（按月计） */
		String WY_START_TIME_BY_MONTH = "C";
		/** 物业费截止时间（按月计） */
		public final String WY_END_TIME_BY_MONTH = "D";
		/** 滞纳金开始时间（按天计） */
		String ZNJ_START_TIME_BY_DAY = "E";
		/** 滞纳金开始时间（按月计） */
		String ZNJ_START_TIME_BY_MONTH = "F";
		/** 用户缴费时间（按天计） */
		String USER_PAY_TIME_PER_DAY = "G";
		/** 用户缴费时间（按月计） */
		String USER_PAY_TIME_PER_MONTH = "H";
	}
}
