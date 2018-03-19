/**   
* Filename:    CommonModuleDict.java   
* @version:    1.0  
* Create at:   2014年10月21日 上午6:33:44   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年10月21日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.constant;

/**
 * Filename:    CommonModuleDict.java
 * @version:    1.0.0
 * Create at:   2014年10月21日 上午6:33:44
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年10月21日       shiyl             1.0             1.0 Version
 */
public class CommonModuleDict {
	/**
	 * 评论信息所属类别
	 */
	public static class CommonModule_TargetType{
		/**电商的商品*/
		public static final Integer T_EBUY_PRODUCT = 1;
		/**今晚吃什么的食品*/
		public static final Integer T_FOOD = 2;
		/**微博主体内容*/
		public static final Integer T_MICRO_BLOG = 11;
		/**厨房菜谱*/
		public static final Integer T_KITCHEN_COOKBOOK = 21;
		/**社区论坛*/
		public static final Integer T_COMMUNITY_FORUM_CONTENT = 31;
		
		/**小区支持*/
		public static final Integer T_GROUPBUILD_SUPPORT = 41;
		
		/**社区商家*/
		public static final Integer T_COMMUNITY_SUPPLY = 51;
		
		/**拼单信息*/
		public static final Integer T_COMMUNITY_PINYIPIN = 61;
		/**换物信息*/
		public static final Integer T_COMMUNITY_EXCHANGE = 71;
		/**发起的换物与参与的换物关系*/
		public static final Integer T_COMMUNITY_EXCHANGE_RELATION = 81;
		
		/**物业公告召唤*/
		public static final Integer T_COMMUNITY_NOTICE_SUMMON = 91;
		
		/**家庭留言板*/
		public static final Integer T_FAMILY_MESSAGE = 101;

		/** 物业报修召唤 （goalId需传入小区ID） */
		public static final Integer T_PROPERTY_REPAIR_SUMMON = 111;

		/** 物业保修评论 */
		public static final Integer T_PROPERTY_REPAIR_COMMENT = 121;
	}
	
	/**
	 * 数据来源类型
	 * 数据来源类型=={"1":"运维平台录入","2":"手工搜集","11":"百度地图API","12":"深圳国土局一手房","13":"深圳国土局二手房","21":"美丽加"}
	 */
	public static class Data_SourceType{
		/**运维平台录入*/
		public static final Integer BACK_GROUND = 1;
		/**手工搜集*/
		public static final Integer HANDEL = 2;
		/**百度地图API*/
		public static final Integer BAIDU_MAP_API = 11;
		/**深圳国土局一手房*/
		public static final Integer SZ_GuoTu_01 = 12;
		/**深圳国土局二手房*/
		public static final Integer SZ_GuoTu_02 = 13;
		
		/**高德地图API*/
		public static final Integer GAODE_MAP_API = 14;
		
		
		/**美丽加*/
		public static final Integer Mljia = 21;
	}
	
}
