/**   
 * Filename:    EbuyConstant.java   
 * @version:    1.0  
 * Create at:   2014年8月8日 上午7:20:21   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年8月8日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.api.payment.constant;

import java.math.BigInteger;

/**
 * Filename: EbuyConstant.java
 * 
 * @version: 1.0.0 Create at: 2014年8月8日 上午7:20:21 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年8月8日 shiyl 1.0 1.0 Version
 */
public class EbuyConstant {
	/** 配送方式Id */
	public static class EbuyDelivery_Id {
//		/** 满59免运费 */
//		public static final BigInteger No_Fee_MoreThan59_Id = new BigInteger("3");
//		/** 59以内需收费 */
//		public static final BigInteger Have_Fee_LessThan59_Id = new BigInteger("4");
		
		/** 积分商品默认配送方式 */
		public static final BigInteger PointProduct_FreeFee_Supply = new BigInteger("99");
		
		public static final BigInteger FREE_FEE_NEW_USER = new BigInteger("100");
	}

	public static class EbuySupplyMerchant_Id {
		/** 海吉星 */
		public static final BigInteger Spl_Merchant_HJX_Id = new BigInteger("200");
		/** 依谷网 */
		public static final BigInteger Spl_Merchant_EGU_Id = new BigInteger("204");
	}
}
