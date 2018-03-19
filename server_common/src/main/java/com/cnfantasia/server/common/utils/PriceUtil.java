/**   
 * Filename:    PriceUtil.java   
 * @version:    1.0  
 * Create at:   2014年6月10日 上午7:35:00   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年6月10日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Filename: PriceUtil.java
 * 
 * @version: 1.0.0 Create at: 2014年6月10日 上午7:35:00 Description:
 * 
 *           Modification History: Date Author Version Description
 *           ------------------------------------------------------------------
 *           2014年6月10日 shiyl 1.0 1.0 Version
 */
public class PriceUtil {
	public static void main(String[] args){
//		System.out.println(div100("12121"));
		Double prize = 1.139;
		System.out.println(div100(311L));
		System.out.println(multiply100(prize));
	}
	/**
	 * 舍弃小数位后两位后面的数据
	 * @param prize
	 * @return
	 */
	public static Long multiply100(Double prize){
		if(prize==null){return null;}

		prize += 0.0001;

		/*double d = 75.6;  TestCase 由于精度问题丢失一分钱的问题
		System.out.println(PriceUtil.multiply100(d))   == 7559 ;*/

		Double tmpPrize=prize*100;
		return tmpPrize.longValue();
	}
	
	public static BigDecimal div100(Long srcPrice) {
		if(srcPrice==null){return null;}
		BigDecimal tmpDeci = new BigDecimal(srcPrice);
		BigDecimal resBig = tmpDeci.divide(new BigDecimal("100"), 2, RoundingMode.UP);
		return resBig;
	}
	
	public static String div100s(Long srcPrice) {
		NumberFormat nf = new DecimalFormat("#0.00");
		if(srcPrice==null){return null;}
		BigDecimal tmpDeci = new BigDecimal(srcPrice);
		BigDecimal resBig = tmpDeci.divide(new BigDecimal("100"), 2, RoundingMode.UP);
		return nf.format(resBig);
	}

	public static String div100sHalfUp(Long srcPrice) {
		NumberFormat nf = new DecimalFormat("#0.00");
		if(srcPrice==null){return null;}
		BigDecimal tmpDeci = new BigDecimal(srcPrice);
		BigDecimal resBig = tmpDeci.divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
		return nf.format(resBig);
	}
	
	
	public static BigDecimal div100(BigDecimal tmpDeci) {
		if(tmpDeci==null){return null;}
		BigDecimal resBig = tmpDeci.divide(new BigDecimal("100"), 2, RoundingMode.UP);
		return resBig;
	}
	
	public static BigDecimal divByDivNum(Long srcPrice,Long toDivNum) {
		if(srcPrice==null){return null;}
		BigDecimal tmpDeci = new BigDecimal(srcPrice);
		BigDecimal resBig = tmpDeci.divide(new BigDecimal(toDivNum), 2, RoundingMode.UP);
		return resBig;
	}
	
	public static String div100(String srcPrice){
		if(srcPrice==null){return null;}
		BigDecimal tmpDeci = new BigDecimal(srcPrice);
		BigDecimal resBig = tmpDeci.divide(new BigDecimal("100"), 2, RoundingMode.UP);
		return resBig.toString();
	}

	/**
	 * 去除尾部没必要的0
	 * @param price 需要去0的值
	 * @return
     */
	public static BigDecimal removeTailZero(BigDecimal price) {
		if (price == null) {
			return null;
		}
		String s = price.toString();
		BigDecimal bd;
		if (!s.contains(".")) {
			return price;
		}
		int i, len = s.length();
		for (i = 0; i < len; i++){
			if (s.charAt(len - 1 - i) != '0')
				break;
		}

		if (s.charAt(len - i - 1) == '.') {
			bd = new BigDecimal(s.substring(0, len - i - 1));
		} else {
			bd = new BigDecimal(s.substring(0, len - i));
		}
		return bd;
	}
	
//	/**
//	 * @param srcDeci
//	 * @param payPercent
//	 * @param floadSize 结果保留的小数位
//	 * @return
//	 */
//	public static BigDecimal calculateMaxCouponAmount(BigDecimal srcDeci,Double payPercent,int floadSize){
//		BigDecimal tmpDeci =null;
//		if(srcDeci!=null&&payPercent!=null){
//			tmpDeci = srcDeci.multiply(new BigDecimal(payPercent*100)).divide(new BigDecimal(100), floadSize, RoundingMode.FLOOR);//只舍不入,不保留小数
//		}
//		return tmpDeci;
//	}
	
}
