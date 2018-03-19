package com.cnfantasia.server.ms.revenue.constant;

import java.math.BigInteger;

public class RevenueConstant {
	public static final String DEFAULT_USED_REC_LASTTIME = "1970-01-01";
	
	/**
	 * 物业费实收及物业费补贴使用的默认物业公司的Id
	 */
	public static final BigInteger DEFAULT_PROPERTY_COMPANY_ID = new BigInteger("-1");
	
	public static final BigInteger DEFAULT_REVENUE_COMPANY_ID = new BigInteger("-2");
	
	/**
	 * 系统Id
	 */
	public static final BigInteger System = new BigInteger("-1");
	
	public static final BigInteger PLATFORM = new BigInteger("-1");
}
