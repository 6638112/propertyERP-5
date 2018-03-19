package com.cnfantasia.server.api.payment.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.cnfantasia.server.common.utils.PriceUtil;


public class PayConfigUtil {
	
//	public static void main(String[] args) {
//		System.out.println(calculateMaxCouponAmount(655.4677, 0.84));
//	}
	
	public static BigDecimal calculateMaxCouponAmount(Double totalAmount,Double payPercent){
		BigDecimal srcDeci = null;
		if(totalAmount!=null){
//			srcDeci = new BigDecimal(Double.toString(totalAmount));
			srcDeci = new BigDecimal(totalAmount+"");
		}
		BigDecimal tmpDeci =calculateMaxCouponAmount(srcDeci, payPercent,2);
		
		return tmpDeci==null?new BigDecimal("0"):tmpDeci;
	}
	
	public static Double calculateMaxCouponAmountDiv100(Long totalAmount,Double payPercent){
		BigDecimal srcDeci = null;
		if(totalAmount!=null){
			srcDeci = new BigDecimal(totalAmount);
		}
		BigDecimal tmpDeci =calculateMaxCouponAmount(srcDeci, payPercent,0);
		return tmpDeci==null?0:PriceUtil.div100(tmpDeci).doubleValue();
	}
	
	public static Long calculateMaxCouponAmount(Long totalAmount,Double payPercent){
		BigDecimal srcDeci = null;
		if(totalAmount!=null){
			srcDeci = new BigDecimal(totalAmount);
		}
		BigDecimal tmpDeci =calculateMaxCouponAmount(srcDeci, payPercent,0);
		
		return tmpDeci==null?0:tmpDeci.longValue();
	}
	
	/**
	 * @param srcDeci
	 * @param payPercent
	 * @param floadSize 结果保留的小数位
	 * @return
	 */
	private static BigDecimal calculateMaxCouponAmount(BigDecimal srcDeci,Double payPercent,int floadSize){
		BigDecimal tmpDeci =null;
		if(srcDeci!=null&&payPercent!=null){
			tmpDeci = srcDeci.multiply(new BigDecimal(payPercent*100)).divide(new BigDecimal(100), floadSize, RoundingMode.FLOOR);//只舍不入,不保留小数
		}
		return tmpDeci;
	}
}
