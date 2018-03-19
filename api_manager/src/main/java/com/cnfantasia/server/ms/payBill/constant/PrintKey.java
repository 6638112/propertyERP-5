package com.cnfantasia.server.ms.payBill.constant;

import java.lang.reflect.Field;
import java.util.regex.Matcher;

/**
 * 物业账单打印模板中的key，前后以“$”包裹，中间为字母。<br>
 * 注：String.replaceAll替换时，“$”要转义
 * 
 * @author liyulin
 * @version 1.0 2016年12月7日 下午4:55:22
 */
public final class PrintKey {
	/** 物业公司logo */
	public static final String $companyIcon$     = "$companyIcon$";
	/** 房号（格式：楼栋单元房间号） */
	public static final String $roomAddress$     = "$roomAddress$";
	/** 业主姓名 */
	public static final String $realName$        = "$realName$";
	/** 房屋面积 */
	public static final String $roomSize$        = "$roomSize$";
	/** 小区名称 */
	public static final String $gbName$          = "$gbName$";
	/** 账单年 */
	public static final String $feeDateYear$     = "$feeDateYear$";
	/** 账单月 */
	public static final String $feeDateMonth$    = "$feeDateMonth$";
	/** 账单名称 */
	public static final String $feeName$         = "$feeName$";
	/** 物业缴费账单开始年 */
	public static final String $wyPayStartYear$  = "$wyPayStartYear$";
	/** 物业缴费账单开始月 */
	public static final String $wyPayStartMonth$ = "$wyPayStartMonth$";
	/** 物业缴费账单截至年 */
	public static final String $wyPayEndYear$    = "$wyPayEndYear$";
	/** 物业缴费账单截至月 */
	public static final String $wyPayEndMonth$   = "$wyPayEndMonth$";
	/** 物业缴费账单截止最后一天 */
	public static final String $monthLastDay$    = "$monthLastDay$";
	/** 水电缴费账单开始年 */
	public static final String $sdPayStartYear$  = "$sdPayStartYear$";
	/** 水电缴费账单开始月 */
	public static final String $sdPayStartMonth$ = "$sdPayStartMonth$";
	/** 水电缴费账单截至年  */
	public static final String $sdPayEndYear$    = "$sdPayEndYear$";
	/** 水电缴费账单截至月 */
	public static final String $sdPayEndMonth$   = "$sdPayEndMonth$";
	/** 总费用 */
	public static final String $totalFee$        = "$totalFee$";
	/** 缴费窗口截止年 */
	public static final String $payEndYear$      = "$payEndYear$";
	/** 缴费窗口截止月 */
	public static final String $payEndMonth$     = "$payEndMonth$";
	/** 账单月份 */
	public static final String $billYYYYMM$      = "$billYYYYMM$";
	/** 账单table中的内容 */
	public static final String $tableContent$    = "$tableContent$";
	/** 物业公司 */
	public static final String $pc$              = "$pc$";
	/** 现在的时间（<u>yyyy</u>年<u>MM</u>月<u>dd</u>日） */
	public static final String $now$             = "$now$";
	/** 所有key */
	private static String[] allKeys              = null;
	/** 所有正则转义后的key */
	private static String[] allEscapeKeys        = null;
	
	static{
		Field[] fields = PrintKey.class.getFields();// 所有公有
		allKeys        = new String[fields.length];
		allEscapeKeys  = allKeys.clone();
		try {
			for (int i=0; i<fields.length; i++) {
				Field field      = fields[i];
				allKeys[i]       = String.valueOf(field.get(field));
				// “$”为正则表达式特殊符号，要转义；否则，PayBillController.getInitPrintTemplate的replaceAll()会替换不了!!!
				//allEscapeKeys[i] = allKeys[i].replaceAll("\\$", "\\\\\\$");
				allEscapeKeys[i] = Matcher.quoteReplacement(allKeys[i]);
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取所有正则转义后的key
	 * 
	 * @return
	 */
	public static final String[] getAllEscapeKeys(){
		return allEscapeKeys;
	}
	
	/**
	 * 获取所有的key
	 * 
	 * @return
	 */
	public static final String[] getAllKeys(){
		return allKeys;
	}
	
}



