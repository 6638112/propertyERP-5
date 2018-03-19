/**   
* Filename:    PlotpropertyConstant.java   
* @version:    1.0  
* Create at:   2014年11月21日 上午2:54:39   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年11月21日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.plotproperty.constant;

import java.math.BigInteger;

/**
 * Filename:    PlotpropertyConstant.java
 * @version:    1.0.0
 * Create at:   2014年11月21日 上午2:54:39
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年11月21日       shiyl             1.0             1.0 Version
 */
public class PlotpropertyConstant {
	public static final String Plotproperty_validateDefaultRoomCheckInfo_ExcepCode = "PlotpropertyService.validateDefaultRoomCheckInfo.failed";
	/**物业服务-物业管理费Id*/
	public static final BigInteger Plotproperty_Service_ManagerFee = new BigInteger("101");
	
	public static class  PlotpropertyMap_PayStatus{
		public static final Integer notPay = 1;
		public static final Integer waitPay = 2;
		public static final Integer successPay = 3;
	}
	
	/**默认物业缴费类别Id*/
	public static final BigInteger DEFAULT_PAYBILL_FEE_TYPEID = new BigInteger("1");
	/**默认物业缴费类别图标*/
	public static final String DEFAULT_PAYBILL_FEE_TYPEICON = "bill_1.jpg";
	/**默认物业缴费类别名称*/
	public static final String DEFAULT_PAYBILL_FEE_NAME = "物业费";
	
	/**首页物业图标*/
	public static final String HOME_CODE_PAYBILL = "wuyejiaofei";
	/**其它费用首页code*/
	public static final String HOME_CODE_FEE_OTHER = "feeOther";
	/**物业费用首页code*/
	public static final String HOME_CODE_FEE_PROP = "feeProp";
	
	/**周期缴费默认开始日期配置*/
	public static final Integer PERID_GBCFG_LIMITSTAT_DEFAULT = 1;
	/**周期缴费默认截止日期配置*/
	public static final Integer PERID_GBCFG_LIMITEND_DEFAULT = 0;
	/**周期缴费默认跨月配置*/
	public static final Integer PERID_GBCFG_PROPERTYMONTHCHANGE_DEFAULT = 0;
}
