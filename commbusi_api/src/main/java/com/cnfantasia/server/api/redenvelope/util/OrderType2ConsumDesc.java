/**   
* Filename:    OrderType2ConsumDesc.java   
* @version:    1.0  
* Create at:   2014年6月26日 上午9:36:20   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月26日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.redenvelope.util;

import com.cnfantasia.server.api.payment.constant.EbuyDict;

/**
 * Filename:    OrderType2ConsumDesc.java
 * @version:    1.0.0
 * Create at:   2014年6月26日 上午9:36:20
 * Description:根据订单类型得到消费描述
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月26日       shiyl             1.0             1.0 Version
 */
public class OrderType2ConsumDesc {
	
	public static String getConsumInfoByOrderType(Integer orderType){//TODO 待国际化处理
		if(orderType==null){
			return "空";
		}else if(EbuyDict.EbuyOrder_Type.EBuy_Product.compareTo(orderType)==0){
			return "购物";
		}else if(EbuyDict.EbuyOrder_Type.Property_Bill.compareTo(orderType)==0){
			return "缴物业费";
		}else if(EbuyDict.EbuyOrder_Type.Flash_Deal_Activity.compareTo(orderType)==0){
			return "购物";
		}else{
			return "其它";
		}
	}
	
}
