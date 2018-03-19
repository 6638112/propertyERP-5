/**   
* Filename:    PlotpropertyDict.java   
* @version:    1.0  
* Create at:   2014年8月13日 上午8:42:53   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月13日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.plotproperty.constant;

/**
 * Filename:    PlotpropertyDict.java
 * @version:    1.0.0
 * Create at:   2014年8月13日 上午8:42:53
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月13日       shiyl             1.0             1.0 Version
 */
public class PlotpropertyDict {
	
	/**
	 * 是否已经支付
	 */
	public static class PayBill_IsPay{
		public static final Integer TRUE = 1;
		public static final Integer FALSE = 2;
	}
	
	/**
	 * 物业账单详情-费用类型
	 * {"1":"管理费","2":"主体金","3":"垃圾处理费","4":"水费","5":"污水处理费"}
	 */
	public static class PropertyFeeDetail_Type{
		public static final Integer ManagerFee = 1;
		public static final Integer MainGold = 2;
		public static final Integer GarbageFee = 3;
		public static final Integer WaterFee= 4;
		public static final Integer SewageFee = 5;
	}
	
	/**
	 * 缴费时间方式=={"1":"月度缴费","2":"周期缴费"}
	 */
	public static class PayBillType_PaytimeType{
		public static final Integer MONTH = 1;
		public static final Integer PEORID = 2;
	}
	
	/**
	 * 是否为物业费=={"1":"是","2":"非物业费_非抄表","3":"非物业费_抄表"}
	 */
	public static class PayBillType_IsPropFee{
		public static final Integer YES = 1;
		public static final Integer NO_notMR = 2;
		public static final Integer NO_MR = 3;
	}
	
	/**
	 * 小区是否可缴纳物业费=={"1":"是","2":"否"}
	 */
	public static class GroupBuilding_PropfeeCanpay{
		public static final Integer YES = 1;
		public static final Integer NO = 2;
	}
	
	/**
	 * 缴费类别有效状态=={"1":"开启","2":"关闭"}
	 */
	public static class PayBillType_ActiveStatus{
		public static final Integer OPEN = 1;
		public static final Integer CLOSE = 2;
	}
}
