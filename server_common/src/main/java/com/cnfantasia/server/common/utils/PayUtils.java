package com.cnfantasia.server.common.utils;

public class PayUtils {
	
	public static String getPayMethodStr(Integer payMethod) {
		if(payMethod == null) {
			return "";
		} else if(payMethod == 1) {
			return "微信支付";
		} else if(payMethod == 2) {
			return "支付宝";
		} else if(payMethod == 3) {
			return "银联支付";
		} else if(payMethod == 4) {
			return "纯粮票支付";
		} else if(payMethod == 5) {
			return "纯积分支付";
		} else if(payMethod == 6) {
			return "纯积分支付";
		} else if(payMethod == 7) {
			return "纯优惠券支付";
		}  else if(payMethod == 9) {
			return "银行卡支付";
		} else {
			return "未定义支付";
		}
	}

}
