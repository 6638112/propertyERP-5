package com.cnfantasia.server.ms.payBill.constant;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;

/**
 * <p>
 * 打印配置编辑
 * </p>
 * <p>
 * 注：public变量名与数据库中配置的名一一对应
 * </p>
 * 
 * @author liyulin
 * @version 1.0 2017年2月12日 下午2:31:32
 */
public abstract class PrintEditKeyBase {
	/** 物业公司logo */
	// 编辑时，暂时不处理
	//public String $companyIcon$ = "物业公司logo";
	/** 物业公司 */
	public String $pc$ = "物业公司名称";
	/** 小区名称 */
	public String $gbName$ = "小区名称";
	/** 房号（格式：楼栋单元房间号） */
	public String $roomAddress$ = "房号";
	/** 业主姓名 */
	public String $realName$ = "业主姓名";
	/** 账单table中的内容 */
	public String $tableContent$ = "账单表格 ";
	/** 账单名称 */
	public String $feeName$ = "账单名称";
	/** 物业缴费账单开始年 */
	public String $wyPayStartYear$ = "账期起始年";
	/** 物业缴费账单开始月 */
	public String $wyPayStartMonth$ = "账期起始月";
	/** 物业缴费账单截止年 */
	public String $wyPayEndYear$ = "账期结束年";
	/** 物业缴费账单截止月 */
	public String $wyPayEndMonth$ = "账期结束月";
	/** 物业缴费账单截止最后一天 */
	public String $monthLastDay$ = "物业缴费账单截止最后一天 ";
	/** 水电缴费账单开始年 */
	public String $sdPayStartYear$ = "水电缴费账单开始年";
	/** 水电缴费账单开始月 */
	public String $sdPayStartMonth$ = "水电缴费账单开始月";
	/** 水电缴费账单截止年 */
	public String $sdPayEndYear$ = "水电缴费账单截止年";
	/** 水电缴费账单截止月 */
	public String $sdPayEndMonth$ = "水电缴费账单截止月";
	/** 总费用 */
	public String $totalFee$ = "账单总金额";
	/** 现在的时间（<u>yyyy</u>年<u>MM</u>月<u>dd</u>日） */
	public String $now$ = "系统时间";
	/** 账单年 */
	public String $feeDateYear$ = "账单年";
	/** 账单月 */
	public String $feeDateMonth$ = "账单月";
	/** 缴费窗口截止年 */
	public String $payEndYear$ = "缴费结束年";
	/** 缴费窗口截止月 */
	public String $payEndMonth$ = "缴费结束月";
	/** 账单月份 */
	public String $billYYYYMM$ = "账单月份";
	/** code-文字 */
	protected Map<String, String> chromeMap = new HashMap<String, String>();
	protected Map<String, String> firefoxMap = new HashMap<String, String>();
	protected Map<String, String> ieMap = new HashMap<String, String>();
	/** 文字-text */
	protected Map<String, String> letterTextMap = new LinkedHashMap<String, String>();
	
	protected void init(PrintEditKeyBase o) {
		Field[] fields = o.getClass().getFields();// 所有公有
		try {
			for (Field field:fields) {
				field.setAccessible(true);
				String code = field.getName();
				String text = String.valueOf(field.get(o));
				/**
				 * 由于 兼容各个浏览器的原因，letter中的input属性顺序初始化后会不同，要分开处理，否则编辑时会识别不了（各个浏览器的初始化机制可能不一致）
				 */
				String firefoxLetter = "<input class=\"tmp_red\" value=\"" + text + "\" disable=\"true\" type=\"button\" />";// firefox
				String chromeLetter = "<input class=\"tmp_red\" type=\"button\" value=\"" + text + "\" disable=\"true\" />";// chrome
				String ieLetter = "<input class=\"tmp_red\" value=\"" + text + "\" type=\"button\" disable=\"true\" />";// IE
				if ("$tableContent$".equals(code)) {// “$tableContent$”单独处理
					chromeLetter  = getTableContent(chromeLetter);
					firefoxLetter = getTableContent(firefoxLetter);
					ieLetter      = getTableContent(ieLetter);
				}
				
				code = Matcher.quoteReplacement(code);
				chromeLetter = Matcher.quoteReplacement(chromeLetter);
				firefoxLetter = Matcher.quoteReplacement(firefoxLetter);

				chromeMap.put(code, chromeLetter);
				firefoxMap.put(code, firefoxLetter);
				ieMap.put(code, ieLetter);
				
				letterTextMap.put(chromeLetter, text);
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	public abstract String getTableContent(String s);

	public Map<String, String> getChromeMap() {
		return chromeMap;
	}

	public Map<String, String> getFirefoxMap() {
		return firefoxMap;
	}
	
	public Map<String, String> getIEMap() {
		return ieMap;
	}
	
	public Map<String, String> getLetterTextMap() {
		return letterTextMap;
	}
}
