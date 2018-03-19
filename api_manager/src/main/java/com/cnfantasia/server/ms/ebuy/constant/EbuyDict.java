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
package com.cnfantasia.server.ms.ebuy.constant;

import java.util.HashMap;
import java.util.Map;

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
	
	public static Map<Integer,String> STATUS_MAP = new HashMap<Integer,String>();
	
	public static Map<String, String> PAYMETHOD_MAP = new HashMap<String, String>();
	
	public static Map<Integer, String> PAYSTATUS_MAP = new HashMap<Integer, String>();
	
	static {
		STATUS_MAP.put(EbuyOrder_Status.DaiFuKuan, "待付款");  
		STATUS_MAP.put(EbuyOrder_Status.YiQuXiao, "已取消");  
		STATUS_MAP.put(EbuyOrder_Status.DaiFaHuo, "待发货");
		STATUS_MAP.put(EbuyOrder_Status.DaiShouHuo, "待收货");  
		STATUS_MAP.put(EbuyOrder_Status.DaiPingJia, "待评价");  
		STATUS_MAP.put(EbuyOrder_Status.YiWanCheng, "已完成");
		STATUS_MAP.put(EbuyOrder_Status.YiShanChu, "已删除");
		
		PAYMETHOD_MAP.put(PayMethod.WEIZHIFU, "未支付");
		PAYMETHOD_MAP.put(PayMethod.WeiXin, "微信支付");
		PAYMETHOD_MAP.put(PayMethod.ZhiFuBao, "支付宝");
		PAYMETHOD_MAP.put(PayMethod.ShuangQianZhiFu, "双乾支付");
		PAYMETHOD_MAP.put(PayMethod.YinLian, "银联支付");
		PAYMETHOD_MAP.put(PayMethod.ChunHongBao, "粮票支付");
		PAYMETHOD_MAP.put(PayMethod.ChunJiFen, "积分支付");
		PAYMETHOD_MAP.put(PayMethod.WeiXinQingApp, "微信轻应用支付");
		PAYMETHOD_MAP.put(PayMethod.ChunYouHuiQian, "优惠券支付");
		
		PAYSTATUS_MAP.put(PayStatus.PayNo, "未支付");
		PAYSTATUS_MAP.put(PayStatus.PayFinished, "支付完成");
		PAYSTATUS_MAP.put(PayStatus.PayFailed, "支付失败");
		PAYSTATUS_MAP.put(PayStatus.PayBack, "退款");
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
	 * 付款方式
	 */
	public static class PayMethod {
		//支付方式=={"1":"微信支付","2":"支付宝","3":"银联支付","4":"纯粮票支付","5":"纯积分支付","6":"微信轻应用支付","7":"纯优惠券支付","9":"双乾支付"}
		public static final String WEIZHIFU = "0";
		public static final String WeiXin = "1";
		public static final String ZhiFuBao = "2";
		public static final String YinLian = "3";
		public static final String ChunHongBao = "4";
		public static final String ChunJiFen = "5";
		public static final String WeiXinQingApp = "6";
		public static final String ChunYouHuiQian = "7";
		public static final String ChunZheKou = "8";
		public static final String ShuangQianZhiFu = "9";
		public static final String Free = "10"; //免费
	}
	
	/**
	 * 支付状态
	 */
	public static class PayStatus {
		//支付状态=={"1":"未支付","2":"支付完成","3":"支付失败","4":"退款"}
		public static final Integer PayNo = 1;
		public static final Integer PayFinished = 2;
		public static final Integer PayFailed = 3;
		public static final Integer PayBack = 4;
	}

}

