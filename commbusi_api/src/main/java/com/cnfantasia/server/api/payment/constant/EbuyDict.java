/**   
 * Filename:    EbuyDict.java   
 * @version:    1.0  
 * Create at:   2014年8月11日 上午8:39:10   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年8月11日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.api.payment.constant;

/**
 * Filename: EbuyDict.java
 * 
 * @version: 1.0.0 Create at: 2014年8月11日 上午8:39:10 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年8月11日 shiyl 1.0 1.0 Version
 */
public class EbuyDict {
	/**
	 * 商品爆款热销状态
	 */
	public static class Product_Hot_Sale_Status{
		public static final int Not_Hot_Sale = 0;//非爆款热销
		public static final int Hot_Sale = 1;//是爆款热销
	}
	/**
	 * 产品状态=={"0":"上架","1":"下架"}
	 */
	public static class Product_Status {
		/** "0":"上架" */
		public static final Integer ON_SELL = 0;
		/** "1":"下架" */
		public static final Integer OFF_DOWN = 1;
	}

	/**
	 * 特殊商品类别=={"1":"普通商品","2":"手机话费","3":"现金券"}
	 */
	public static class Product_SpecialType {
		/** "1":"普通商品" */
		public static final Integer COMMON = 1;
		/** "2":"手机话费", */
		public static final Integer PhoneFee = 2;
		/** "3":"现金券" */
		public static final Integer MoneyTicket = 3;
	}

	/**
	 * 商品相关归类=={"1":"电商商品","2":"积分商品"}
	 */
	public static class PointType {
		/** 电商商品 */
		public static final Integer EBUY_PRODUCT = 1;
		/** 积分商品 */
		public static final Integer POINT_PRODUCT = 2;
	}

	/**
	 * 商品归类=={"1":"解放区商品","2":"文旅商品"}
	 */
	public static class WlAppType {
		/** 解放区App端商品 */
		public static final Long Jfq = 1L;
		/** 文旅商品 */
		public static final Long Wl_Light_App = 2L;
		/** 解放区轻应用商品 */
		public static final Long Jfq_Light_App = 3L;
	}

	/** 解放区来源，细分 */
	public static class JfqApp_SubType {
		/** 解放区App端商品 */
		public static final Integer Jfq_Client = 1;
		/** 解放区轻应用商品 */
		public static final Integer Jfq_Light_App = 3;
	}

	/**
	 * 收货地址类型
	 */
	public static class DELIVERY_ADDRESS_TYPE {
		/** 门牌方式 */
		public static final Integer ROOM = 1;
		/** 普通输入 */
		public static final Integer HANDLE_ADDRESS = 2;
	}

	/**
	 * 是否默认门牌标识
	 */
	public static class EbuyDeliveryAddress_ISDEFAULT {
		/** 否 */
		public static final Integer FALSE = 0;
		/** 是 */
		public static final Integer TRUE = 1;
	}

	/**
	 * 订单状态
	 */
	public static class EbuyOrder_Status {
		/** "1":"待付款" */
		public static final Integer DaiFuKuan = 1;
		/** "2":"已取消" */
		public static final Integer YiQuXiao = 2;
		/** "3":"待发货" */
		public static final Integer DaiFaHuo = 3;
		/** "4":"待收货" */
		public static final Integer DaiShouHuo = 4;
		/** "5":"待评价" */
		public static final Integer DaiPingJia = 5;
		/** "6":"已完成" */
		public static final Integer YiWanCheng = 6;
		/** "99":"已删除" */
		public static final Integer YiShanChu = 99;
	}

	/**
	 * 支付态
	 */
	public static class EbuyOrder_PayStatus {
		/** "1":"未支付" */
		public static final Integer Not_Pay = 1;
		/** "2":"支付完成" */
		public static final Integer Pay_Success = 2;
		/** "3":"支付失败" */
		public static final Integer Pay_Failed = 3;
	}

	/**
	 * 客户端支付状态=={"2":"返回支付成功"}
	 */
	public static class EbuyOrder_ClientPayStatus {
		/** "2":"返回支付成功" */
		public static final Integer Client_Pay_Success = 2;
	}

	/**
	 * 发货状态
	 */
	public static class EbuyOrder_DelivStatus {
		/** "1":"未发货" */
		public static final Integer Not_Deliv = 1;
		/** "2":"部分发货" */
		public static final Integer Deliv_PART = 2;
		/** "3":"全部发货" */
		public static final Integer Deliv_ALL = 3;
	}

	/**
	 * 订单类型
	 */
	public static class EbuyOrder_Type {
		/** 电商商品 */
		public static final Integer EBuy_Product = 1;
		/** 物业费账单 */
		public static final Integer Property_Bill = 2;
		/** 疏通服务账单 */
		public static final Integer Dredge_Bill = 3;
		/** 车禁账单 */
		public static final Integer CarKey_Bill = 4;
		/** 门禁账单 */
		public static final Integer DoorKey_Bill = 5;
		/** 物业代扣卡账单 */
		public static final Integer Property_Card_Bill = 6;
		/** 一元夺宝订单 */
		public static final Integer Flash_Deal_Activity = 7;
		/** 限时抢购 */
		public static final Integer Limit_Buy_Activity = 8;
		/** 生活缴费 */
		public static final Integer Living_Fee_Bill = 9;
		/** 物业缴费总账单 */
		public static final Integer Total_Property_Bill = 99;
	}

	/**
	 * 支付态
	 */
	public static class EbuyPayRecord_PayStatus {
		/** "1":"未支付" */
		public static final Integer Not_Pay = 1;
		/** "2":"支付完成" */
		public static final Integer Pay_Success = 2;
		/** "3":"支付失败" */
		public static final Integer Pay_Failed = 3;
	}

	/**
	 * 电商模块 支付方式
	 */
	public static class EbuyPay_PayMethod {
		/** "1":"微信支付" */
		public static final Integer WeiXin = 1;
		/** "2":"支付宝" */
		public static final Integer Alipay = 2;
		/** "3":"银联支付" */
		public static final Integer UnionPay = 3;
		/** "4":"纯粮票支付" */
		public static final Integer PureRedEnvelope = 4;
		/** "5":"纯积分支付" */
		public static final Integer PurePoint = 5;
		/** "6":"微信轻应用支付" */
		public static final Integer WeiXin_Light = 6;
		/** "7":"纯优惠券支付" */
		public static final Integer PureSupriseGiftList = 7;

		/** "8":"纯折扣支付" */
		public static final Integer PureDiscount = 8;

		/** "9":"双乾支付" */
		public static final Integer SqPay = 9;

		//免费
		public static final Integer Free = 10;
	}

	/**
	 * 订单优惠信息_优惠类别=={"1":"粮票","2":"优惠券"}
	 */
	public static class PayCoupon_Type {
		/** 粮票 */
		public static final Integer Red_Envelope = 1;
		/** 优惠券 */
		public static final Integer CouponTicket = 2;
	}

	/**
	 * 配送状态
	 */
	public static class EbuyDeliveryOrder_Status {
		/** 未启动 */
		public static final Integer NotStart = 0;
		/** 待发货 */
		public static final Integer DaiFaHuo = 1;
		/** 待收货 */
		public static final Integer DaiShouHuo = 2;
		/** 确认收货 */
		public static final Integer QueRenShowHuo = 3;
	}

	/**
	 * 是否为调用银行卡支付=={"1":"是","2":"否"}
	 */
	public static class EbuyPrepayAlipayLog_IsExpressGateway {
		/** 是 */
		public static final Integer YES = 1;
		/** 否 */
		public static final Integer NO = 2;
	}

	/**
	 * 配送单推送状态
	 */
	public static class DeliveryOrderpush_Status {
		/** 未推送 */
		public static final Integer NotStart = 0;
		/** 推送中 */
		public static final Integer TuisongZhong = 1;
		/** 推送成功 */
		public static final Integer TuiSongChengGong = 2;
		/** 推送失败 */
		public static final Integer TuiSongShiBai = 9;
	}

	public static class EbuyProdTypeSpec {
		// 限时促销
		public static final Long LIMIT_BUY = -1L;
	}
	
	/**
	 * 配置类型
	 * @author wenfq 2017-03-13
	 */
	public static class EbuyDeliveryMethodType {
		//配送类型=={"1":"快递","2":"定点自提","3":"上门"}
		public static final int Express  = 1;
		public static final int SelfGet  = 2;
		public static final int SendToDoor  = 3;
	}
}
