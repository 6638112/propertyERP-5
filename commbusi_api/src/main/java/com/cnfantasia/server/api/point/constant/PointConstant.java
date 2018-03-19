/**   
* Filename:    PointConstant.java   
* @version:    1.0  
* Create at:   2014年12月29日 上午10:21:15   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年12月29日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.point.constant;

import com.cnfantasia.server.common.exception.BusinessRuntimeException;


/**
 * Filename:    PointConstant.java
 * @version:    1.0.0
 * Create at:   2014年12月29日 上午10:21:15
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年12月29日       shiyl             1.0             1.0 Version
 */
public class PointConstant {
	
		public static String getDescByPointSourceType(Integer sourceType){
			if(sourceType==null){return "";}
			else if(sourceType.compareTo(PointSourceType.Share)==0){return "当天首次分享";}
			else if(sourceType.compareTo(PointSourceType.PublishExchange)==0){return "当天首次发布换一换";}
			else if(sourceType.compareTo(PointSourceType.Sign)==0){return "签到";}
			else if(sourceType.compareTo(PointSourceType.FirstSetUserImage)==0){return "首次设置头像";}
			else if(sourceType.compareTo(PointSourceType.FirstSetNickName)==0){return "首次设置昵称";}
			else throw new BusinessRuntimeException("PointConstant.PointSourceType.unknown");
		}
		
		public static Long getPointValueByPointSourceType(Integer sourceType){
			if(sourceType==null){return null;}
			else if(sourceType.compareTo(PointSourceType.Share)==0){return 10L;}
			else if(sourceType.compareTo(PointSourceType.PublishExchange)==0){return 10L;}
			else if(sourceType.compareTo(PointSourceType.Sign)==0){throw new BusinessRuntimeException();}
			else if(sourceType.compareTo(PointSourceType.FirstSetUserImage)==0){return 5L;}
			else if(sourceType.compareTo(PointSourceType.FirstSetNickName)==0){return 5L;}
			else throw new BusinessRuntimeException("PointConstant.PointSourceType.unknown");
		}
		
		public static Long getPointValueBySignDays(Long dayCount){
			if(dayCount==null||dayCount<=1){return 3L;}
			else if(dayCount==2){return 5L;}
			else if(dayCount==3){return 8L;}
			else if(dayCount==4){return 10L;}
			else /*if(dayCount>=5)*/{return 15L;}
		}
		
		/**
		 * 来源类型=={"1":"当天首次分享","2":"当天首次发布换一换","3":"签到","4":"首次设置头像","5":"首次设置昵称"}
		 */
		public static class PointSourceType{
			/**当天首次分享*/
			public static final Integer Share = 1;
			/**当天首次发布换一换*/
			public static final Integer PublishExchange = 2;
			/**签到*/
			public static final Integer Sign = 3;
			/**首次设置头像*/
			public static final Integer FirstSetUserImage = 4;
			/**首次设置昵称*/
			public static final Integer FirstSetNickName = 5;
		}
	
		/**
		 * 消费类型=={"1":"购买普通礼品","2":"手机话费","3":"购买优惠券"}
		 */
		public static class PointCostType{
			/**购买普通礼品*/
			public static final Integer BuyPointProduct_Comm = 1;
			/**手机话费*/
			public static final Integer BuyPointProduct_PhoneFee = 2;
			/**购买优惠券*/
			public static final Integer BuyPointProduct_Coupon  = 3;
		}
		
//	/**
//	 * 积分来源类型
//	 */
//	public enum PointSourceType{
//		Sign(1) //签到
//		,UpdNickName(2)//修改昵称
//		;
//		
//		private Integer value;
//		public Integer getValue() {
//			return value;
//		}
//		private PointSourceType(Integer value){
//			this.value = value;
//		}
//	}
	
}
