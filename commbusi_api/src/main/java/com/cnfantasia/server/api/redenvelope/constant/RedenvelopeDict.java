/**   
 * Filename:    RedenvelopeDict.java   
 * @version:    1.0  
 * Create at:   2014年8月11日 上午8:54:54   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年8月11日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.api.redenvelope.constant;

import java.util.HashMap;
import java.util.Map;

import com.cnfantasia.server.api.room.constant.RoomConstants;

/**
 * Filename: RedenvelopeDict.java
 * 
 * @version: 1.0.0 Create at: 2014年8月11日 上午8:54:54 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年8月11日 shiyl 1.0 1.0 Version
 */
public class RedenvelopeDict {
	/**
	 * 粮票来源类型={"1":"抽奖记录","2":"意外惊喜", "3":"神码行动", "4":"申请退款"},
	 * 只有种类为1,3,4的才统计到家庭财富的粮票金额中
	 */
	public static class PayRedEnvelope_FromType {
		/** 抽奖记录 */
		public static final Integer PRIZE_RECORD = 1;
		/** 意外惊喜 */
		public static final Integer SURPRISE_GIFT = 2;
		/** 神码行动(以后市场活动都用这个) */
		public static final Integer SM_ACTION = 3;
		
		/** 申请退款 */
		public static final Integer REFUND = 4;

		/**
		 * 体验店充值赠送红包
		 */
		public static final Integer Experience_Store = 6;
	}

	/**
	 * 粮票状态
	 */
	public static class PayRedEnvelope_Status {
		/** 可使用 */
		public static final Integer CAN_USE = 1;
		/** 已锁定 */
		public static final Integer IS_LOCKED = 2;
	}

	/**
	 * 粮票不能兑换的原因flag,Msgkey
	 */
	public static class Redenvelope_UnConvert_Reason{
		/** 验证通过 */
		public static final Integer SUCCESS = 0;
		
		/** 用户默认门牌为空 */
		public static final Integer DEFAULT_ROOM_EMPTY = 1;
		/** 用户不是门牌的创建者 */
		public static final Integer NOT_ROOM_CREATER = 2;
		/** 门牌需要被校验 */
		public static final Integer ROOM_NEED_VALIDATE = 3;
		/** 不在可兑换的时间范围 */
		public static final Integer NOT_AVAILABLE_TIME = 4;
		/** 当月没有折扣 */
		public static final Integer NO_RECORD = 5;
		/** 当月折扣已被使用*/
		public static final Integer IS_USED = 6;
		
		/** 只有管理员才可以兑换粮票*/
		public static final Integer MUST_BE_ADMIN = 7;
		/** 折扣已失效*/
		public static final Integer IS_TIME_OUT = 8;
		/**
		 * 存储错误标识对应的错误码
		 */
		private static final Map<Integer,String> MsgKeyMap = new HashMap<Integer, String>();
		static{
			MsgKeyMap.put(SUCCESS, "");
			MsgKeyMap.put(DEFAULT_ROOM_EMPTY, RoomConstants.DEFALT_ROOM_ISEMPTY_AND_NEEDSET_EXCEPTION_CODE);//抛出异常
			MsgKeyMap.put(NOT_ROOM_CREATER, "RedenvelopeService.validateDiscount2hb.notCreater");
			MsgKeyMap.put(ROOM_NEED_VALIDATE, "RedenvelopeService.validateDiscount2hb.notValided");
			MsgKeyMap.put(NOT_AVAILABLE_TIME, "RedenvelopeService.getLastDiscountByMonth.validateMonth.tooBig.failed");
			MsgKeyMap.put(NO_RECORD, "RedenvelopeService.getLastDiscountByMonth.noPrizeRecord");
			MsgKeyMap.put(IS_USED, "RedenvelopeService.getLastDiscountByMonth.isUsed");
			MsgKeyMap.put(MUST_BE_ADMIN, "RedenvelopeService.validateDiscount2hb.mustBeAdmin");
			MsgKeyMap.put(IS_TIME_OUT, "RedenvelopeService.discount.timeOut");
		}
		
		public static String getMsgKey(Integer flag){
			return MsgKeyMap.get(flag);
		}
		
	}
	
}
