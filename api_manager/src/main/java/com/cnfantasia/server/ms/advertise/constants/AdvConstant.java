package com.cnfantasia.server.ms.advertise.constants;

public final class AdvConstant {

	/**
	 * 广告类型
	 */
	public static final class AdvType {
		/** 首页弹窗广告 */
		public static final String Shou_Ye_Tan_Chuang = "1";
		/** 首页拦腰广告 */
		public static final String Shou_Ye_Lan_Yao = "2";
		/** 到家广告 */
		public static final String Dao_Jia = "3";
		/** 街坊广告 */
		public static final String Jie_Fang = "4";
		/** 到家底部广告 */
		public static final String DAO_JIA_BOTTOM = "5";
		/** 微信购物小票 */
		public static final String WX_STORE_ORDER_PUSH = "6";
		/** 车禁缴费 */
		public static final String CAR_FEE = "7";
		/**
		 * 体验店
		 */
		public static final String EXPERIENCE_STORE = "8";
	}

	/**
	 * 广告对应的t_operation_sa_has_eb_supply表类型
	 * @see com.cnfantasia.server.api.operation.constant.OperationDict.OperationSaHasEbSupply_type
	 */
	public static final class EbSupplyType {
		/** 首页弹窗广告 */
		public static final Integer Shou_Ye_Tan_Chuang = 4;
		/** 首页拦腰广告 */
		public static final Integer Shou_Ye_Lan_Yao = 3;
		/** 到家广告 */
		public static final Integer Dao_Jia = 4;
		/** 街坊广告 */
		public static final Integer Jie_Fang = 2;
		
		/**
		 * 幸运购（抢购）
		 */
		public static final Integer Yi_Yuan_Go = 5;
		
		
		public static final Integer t_home_message_type = 6;
		public static final Integer t_app_function_bar = 7;
		/**
		 * 服务商品
		 */
		public static final Integer t_dredge_product = 8;
	}

	/**
	 * 跳转类型
	 */
	public static final class JumpType {
		/** 产品(APP) */
		public static final Integer App = 2;
		/** H5页面 */
		public static final Integer H5 = 1;
		/** 不跳 */
		public static final Integer No_Jump = 4;
	}
}
