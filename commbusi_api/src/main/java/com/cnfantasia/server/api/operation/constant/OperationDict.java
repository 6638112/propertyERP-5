/**   
* Filename:    OperationDict.java   
* @version:    1.0  
* Create at:   2015年4月23日 上午9:15:19   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年4月23日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.operation.constant;

/**
 * Filename:    OperationDict.java
 * @version:    1.0.0
 * Create at:   2015年4月23日 上午9:15:19
 * Description:运营相关字典
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年4月23日       shiyl             1.0             1.0 Version
 */
public class OperationDict {
	
	/**
	 * 首页显示位置=={"1":"一级数据","2":"二级数据","3":"三级数据"}
	 */
	public static class OperationHomeSupplyType_HomePlace{
		/**"1":"一级数据"*/
		public static final Integer place_1 = 1;
		/**"2":"二级数据"*/
		public static final Integer place_2 = 2;
		/**"3":"三级数据"*/
		public static final Integer place_3 = 3;
	}
	
	/**
	 * 数据层级
	 */
	public static class OperationHomeSupplyType_DataLevel{
		/**"1":"第一层"*/
		public static final Integer level_1 = 1;
		/**"2":"第二层"*/
		public static final Integer level_2 = 2;
	}
	
	/**
	 * 类别=={"1":"普通商家类别","2":"超链接","3":"内部跳转"}
	 */
	public static class OperationHomeSupplyType_DataType{
		public static final Integer common_Type = 1;
		public static final Integer isLink = 2;
		public static final Integer innerAppUrl = 3;
	}
	
	/**
	 * 是否火热开启=={"1":"是","2":"否"}
	 */
	public static class OperationHomeSupplyType_IsHot{
		public static final Integer TRUE = 1;
		public static final Integer FALSE = 2;
	}
	
	/**
	 * 数据类型=={"1":"文本","2":"图片"}
	 */
	public static class OperationConstantData_dataType{
		public static final Integer TEXT = 1;
		public static final Integer PIC = 2;
	}
	
	/**运维配置编码描述*/
	public static class  OperationCode{
		/**分享，意外惊喜，文本*/
		public static final String SHARE_SUPRISE_TXT = "SHARE_SUPRISE_TXT";
		/**分享，意外惊喜，图标地址*/
		public static final String SHARE_SUPRISE_PIC = "SHARE_SUPRISE_PIC";
		
		/**粮票使用说明，文本*/
		public static final String HB_USE_DESC_TXT = "HB_USE_DESC_TXT";
		/**消费券使用说明，文本*/
		public static final String ConsumTicket_USE_DESC_TXT = "ConsumTicket_USE_DESC_TXT";
	}
	
	/**
	 * 关联关系(去除优先)=={"1":"包含","2":"去除"}
	 */
	public static class OperationSaHasEbSupply_relation{
		/**"1":"包含"*/
		public static final Integer INCLUDE = 1;
		/**"2":"去除"*/
		public static final Integer UNCLUDE = 2;
	}


	/**
	 * type字段对应不同的表
	 */
	public static class OperationSaHasEbSupply_type {
		public static final Integer t_community_ads = 2;
		public static final Integer t_operation_home_supply_type = 3;
		public static final Integer t_ebuy_advertise = 4;
		public static final Integer t_flash_deal_activity = 5;//新的不用这个了，用楼下店的服务范围了
		public static final Integer t_home_message_type = 6;
		public static final Integer t_app_function_bar = 7;
		public static final Integer t_dredge_product = 8;
		public static final Integer t_community_supply_type = 9;
		public static final Integer t_dredge_type = 10;
		
		public static final Integer t_message_to_be_send = 11;//运营待推送消息或短信
	}
	
	/***
	 * 运营消息
	 */
	public static class OperationMsgDict{
		public static final int MsgType_ShortMsg = 1;//短信
		public static final int MsgType_PushMsg = 2;//push消息	
		
		public static final int SendStatus_NotSend = 0; //未发送
		public static final int SendStatus_Success = 1;//发送成功
		public static final int SendStatus_Fail = 2;//发送失败
		
		public static final int SendRange_Country = 1;//发送范围_全国
		public static final int SendRange_Block = 4;//发送范围——区县
		public static final int SendRange_GroupBuilding = 3 ;//发送范围_小区
	}
}
