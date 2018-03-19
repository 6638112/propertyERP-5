/**   
* Filename:    PrizeActivityDict.java   
* @version:    1.0  
* Create at:   2015年9月10日 上午11:47:50   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年9月10日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.commbusi.prizeActivity.constant;


/**
 * Filename:    PrizeActivityDict.java
 * @version:    1.0.0
 * Create at:   2015年9月10日 上午11:47:50
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年9月10日       shiyl             1.0             1.0 Version
 */
public class PrizeActivityDict {
	/**
	 * 奖项范围 1全国 2城市
	 */
	public static class MsPrizeOption_AdressType{
		/**全国*/
		public static final Integer GLOBAL=1;
		/**城市*/
		public static final Integer CITY=2;
	}
	/**
	 * 奖品查询状态
	 */
	public static class Qry_PrizeGiftStatus{
		/**全部*/
		public static final Integer ALL=0;
		/**未使用*/
		public static final Integer NotUsed=1;
		/**已锁定*/
		public static final Integer Locked=2;
		/**已分配*/
		public static final Integer IsUsed=3;
	}
	
	/**
	 * 奖项查询状态
	 */
	public static class Qry_PrizeOptionStatus{
		/**全部*/
		public static final Integer ALL=0;
		/**已开启*/
		public static final Integer OPEN=1;
		/**未开启*/
		public static final Integer CLOSED=2;
		/**使用中*/
		public static final Integer USEING=3;
		/**已停用*/
		public static final Integer TIMEOUT=4;
	}
	
	/**
	 * 奖项状态=={"1":"关闭","2":"开启"}
	 */
	public static class MsPrizeOption_Status{
		public static final Integer CLOSED = 1;
		public static final Integer OPEN = 2;
		public static boolean isExist(Integer tmp){
			if(tmp!=null){
				if(tmp.compareTo(CLOSED)==0){return true;}
				if(tmp.compareTo(OPEN)==0){return true;}
			}
			return false;
		}
		public static Integer getDefault(){
			return CLOSED;
		}
	}
	
	/**
	 * 活动查询状态
	 */
	public static class Qry_ActivityStatus{
		/**全部*/
		public static final Integer ALL=0;
		/**已开启*/
		public static final Integer OPEN=1;
		/**未开启*/
		public static final Integer CLOSED=2;
		/**进行中*/
		public static final Integer DOING=3;
		/**已结束*/
		public static final Integer FINISHED=4;
	}
	
	/**
	 * 活动状态=={"1":"关闭","2":"开启"}
	 */
	public static class MsPrizeActivity_ActivityStatus{
		public static final Integer CLOSED = 1;
		public static final Integer OPEN = 2;
		public static boolean isExist(Integer tmp){
			if(tmp!=null){
				if(tmp.compareTo(CLOSED)==0){return true;}
				if(tmp.compareTo(OPEN)==0){return true;}
			}
			return false;
		}
		public static Integer getDefault(){
			return CLOSED;
		}
	}
	
	
	/**
	 * 兑换状态=={"0":"未使用","1":"已锁定","2":"已分配"}
	 */
	public static class MsPrizeGift_ConvertStatus{
		public static final Integer NotUse = 0;
	}
}
