/**   
* Filename:    CommunitySupplyConstant.java   
* @version:    1.0  
* Create at:   2014年8月26日 上午3:48:13   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年8月26日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.communitySupply.constant;

import java.math.BigInteger;

/**
 * Filename:    CommunitySupplyConstant.java
 * @version:    1.0.0
 * Create at:   2014年8月26日 上午3:48:13
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年8月26日       shiyl             1.0             1.0 Version
 */
public class CommunitySupplyConstant {
	
	public static class SupplyTypeId{
		/**找服务信息类别的顶级父类Id*/
		public static final BigInteger BASE_FindService_SupplyTypeId=new BigInteger("100");
		/**找实惠信息类别的顶级父类Id*/
		public static final BigInteger BASE_FindBenefits_SupplyTypeId=new BigInteger("101");
		/**美容的商家类别数据*/
		public static final BigInteger Meirong_SuppplyTypeId = new BigInteger("304");
		/**快递的商家类别数据*/
		public static final BigInteger Kuaidi_SuppplyTypeId = new BigInteger("207");
	}
	
	public static final Double DEFAULT_MAX_LOCAT_DISTANCE = 3000.0;//米
	
}
