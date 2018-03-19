/**   
 * Filename:    PrizeDict.java   
 * @version:    1.0  
 * Create at:   2014年8月11日 上午8:57:21   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年8月11日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.api.prize.constant;

/**
 * Filename: PrizeDict.java
 * 
 * @version: 1.0.0 Create at: 2014年8月11日 上午8:57:21 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年8月11日 shiyl 1.0 1.0 Version
 */
public class PrizeDict {
	
	/**
	 * 处理状态=={"1":"未处理","2":"成功","3":"失败"}
	 */
	public static class PrizeRecordTmpData_DealStatus {
		/** "1":"未处理"*/
		public static final Integer UNDO = 1;
		/** "2":"成功" */
		public static final Integer SUCCESS = 2;
		/** "3":"失败"} */
		public static final Integer FAILED = 3;
	}
	
	/**
	 * 中奖记录使用状态标识
	 */
	public static class PrizeRecord_Status {
		/** 未使用 */
		public static final Integer NOT_USE = 0;
		/** 已使用 */
		public static final Integer IS_USED = 1;
	}
	
	/**
	 * 中奖记录退回标识
	 */
	public static class PrizeRecord_BackPoolStatus {
		/** 1：已退回 */
		public static final Integer Has_Back = 1;
	}
	
	
	/**
	 * 折扣已使用
	 * {"1":"兑粮票","2":"缴物业"}
	 */
	public static class PrizeRecord_UsedType {
		/** 兑粮票*/
		public static final Integer Redenvelope = 1;
		/** 缴物业 */
		public static final Integer Plotproperty = 2;
	}
	
	public static class PrizePool_State{
		/** 未开启 */
		public static final Integer NOT_START = 0;
		/** 可抽奖 */
		public static final Integer DOING = 1;
		/** 已暂停 */
		public static final Integer STOPPED = 2;
		/** 已结束 */
		public static final Integer FINISHED = 3;
	}
	
}
