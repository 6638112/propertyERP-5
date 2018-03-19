package com.cnfantasia.server.common.utils;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * 描述:数据校验及处理
 * 
 */
public class DataUtil {
	/**
	 * 判断8位日期的表达式
	 */
	public final static String dateRegEx = "^((((1[0-9]|[2-9]\\d)\\d{2})(0[13578]|1[02])(0[1-9]|[12]\\d|3[01]))|(((1[0-9]|[2-9]\\d)\\d{2})(0[13456789]|1[012])(0[1-9]|[12]\\d|30))|(((1[0-9]|[2-9]\\d)\\d{2})02(0[1-9]|1\\d|2[0-8]))|(((1[0-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))0229))$";

	/**
	 * 判断是否金额格式（非负数字） by laich
	 */
	// public final static String amountRegEx =
	// "(?!^0*(\\.0{1,2})?$)^\\d{1,11}(\\.\\d{1,2})?$";
	public final static String amountRegEx = "^(\\d{0,11})?+(\\.)?+(\\d{1,11})?$";

	/**
	 * 数字格式，负数、小数、整数 by laich
	 */
	public final static String dataRegEx = "^-?(\\d{0,11})?+(\\.)?+(\\d{0,11})?$";

	/**
	 * 判断是否全部为可见字符
	 */
	public final static String visibleCharRegEx = "^[^\\s]{6,20}$";

	/**
	 * 判断是否是8到20位的数字
	 */
	public final static String numberRegEx = "^[\\d]{8,20}$";

	/**
	 * 验证非0的正整数
	 */
	public final static String intNumberRegEx = "^\\+?[1-9][0-9]*$";

	/**
	 * 验证整数（0和正整数）
	 */
	public final static String int0NumberRegEX = "^\\d+$";

	/**
	 * 判断是否是字母
	 */
	public final static String lettersRegEx = "^[a-zA-Z]{8,20}$";

	/**
	 * 判断是否含有一个数字或字母
	 */
	public final static String containsNuOrLetRegEx = "[\\da-zA-Z]";

	/**
	 * 匹配重复字符串密码的前缀
	 */
	private final static String pre_repeatRegEx = "([^\\s])\\1{";

	/**
	 * 匹配重复字符串密码的后缀
	 */
	private final static String suffix_repeatRegEx = "}";

	/**
	 * 字典序
	 */
	public final static String[] DICTIONARY = { "abcdefghijklmnopqrstuvwxyz",
	    "ABCDEFGHIJKLMNOPQRSTUVWXYZ", "zyxwvutsrqponmlkgihgfedcba",
	    "ZYXWVUTSRQPONMLKJIHGFEDCBA" };

	/**
	 * 数字序
	 */
	public final static String[] NUSEQENCE = { "0123456789", "1234567890",
	    "9876543210", "0987654321" };

	/**
	 * 判断数字2个或4个连续重复
	 */
	public final static String seriecRepeatRegEx = "0{2}1{2}2{2}3{2}|1{2}2{2}3{2}4{2}|2{2}3{2}4{2}5{2}|3{2}4{2}5{2}6{2}|4{2}5{2}6{2}7{2}|5{2}6{2}7{2}8{2}|6{2}7{2}8{2}9{2}|0{4}1{4}|1{4}2{4}|2{4}3{4}|3{4}4{4}|4{4}5{4}|5{4}6{4}|6{4}7{4}|7{4}8{4}|8{4}9{4}";

	/**
	 * 判断身份证的正则表达式
	 */
	public static final String idRegEx = "^(\\d{15}|\\d{17}[\\dx])$";

	/**
	 * 信用卡有效期验证，格式是01/09,表示09年01月份
	 */
	public static final String creValidityDate = "^([0][1-9]|[1][0-2]){1}/{1}([0-9][0-9]){1}$";

	public static final int remarksLength = 60;

	/**
	 * 判断不为汉字的正则表达式
	 */
	public final static String NOChina = "[^\\u4e00-\\u9fa5]";

	/**
	 * 判断15位身份证正则表达式
	 */
	public static String id15RegEx = "^(\\d){6}(((\\d{2})(0[13578]|1[02])(0[1-9]|[12]\\d|3[01]))|((\\d{2})(0[13456789]|1[012])(0[1-9]|[12]\\d|30))|((\\d{2})02(0[1-9]|1\\d|2[0-8]))|(((0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))0229))(\\d{3})$";

	/**
	 * 判断18位身份证正则表达式
	 */
	public static String id18RegEx = "^(\\d){6}((((1[0-9]|[2-9]\\d)\\d{2})(0[13578]|1[02])(0[1-9]|[12]\\d|3[01]))|(((1[0-9]|[2-9]\\d)\\d{2})(0[13456789]|1[012])(0[1-9]|[12]\\d|30))|(((1[0-9]|[2-9]\\d)\\d{2})02(0[1-9]|1\\d|2[0-8]))|(((1[0-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))0229))(\\d{4}|\\d{3}x)$";

	/**
	 * 校验邮箱格式
	 */
	public final static String EmailRegEx = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";

	/**
	 * 如果对象为null,或空串，或全为空格，返回true;否则返回false
	 * 判断字符串，数组，集合
	 * @param Object
	 *          obj 对象
	 * 
	 * @return boolean
	 */
	@SuppressWarnings("rawtypes")
  public static boolean isEmpty(Object obj) {
		if (obj == null) {
			return true;
		}
		if (obj instanceof String) {// String
			String tmp = obj.toString();
			return tmp.trim().length() <= 0;
		} else if (obj.getClass().isArray()) {// 数组
			return Array.getLength(obj) <= 0;
		} else if (obj instanceof List) {// List
			List list = (List) obj;
			return list.isEmpty();
		} else if (obj instanceof Map) {// Map
			Map map = (Map) obj;
			return map.isEmpty();
		} else if (obj instanceof Set) {
			Set set = (Set) obj;
			return set.isEmpty();
		}
		return false;
	}

	/**
	 * 对double数据进行取精度. 201105514
	 * <p>
	 * For example: <br>
	 * double value = 100.345678; <br>
	 * double ret = round(value,4,BigDecimal.ROUND_HALF_UP); <br>
	 * ret为100.3457 <br>
	 * 
	 * @param value
	 *          double数据.
	 * @param scale
	 *          精度位数(保留的小数位数).
	 * @param roundingMode
	 *          精度取值方式.
	 * @return 精度计算后的数据.
	 */
	public static String round(String value, int scale, int roundingMode) {
		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(scale, roundingMode);
		return bd + "";
	}

	/**
	 * 对double数据进行取精度. 201105514
	 * <p>
	 * For example: <br>
	 * double value = 100.345678; <br>
	 * double ret = round(value,4,BigDecimal.ROUND_HALF_UP); <br>
	 * ret为100.3457 <br>
	 * 
	 * @param value
	 *          double数据.
	 * @param scale
	 *          精度位数(保留的小数位数).
	 * @param roundingMode
	 *          精度取值方式.
	 * @return 精度计算后的数据.
	 */
	public static BigDecimal round(BigDecimal value, int scale, int roundingMode) {
		// BigDecimal bd = new BigDecimal(value);
		value = value.setScale(scale, roundingMode);
		return value;
	}

	/**
	 * 判断字符串是否为空
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isStrEmpty(String str) {
		return isEmpty(str);
	}

	/**
	 * 字符串转日期
	 * 
	 * @param str
	 * @return
	 */
	public static Date strToDate(String str) {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = format.parse(str);
		} catch (Exception e) {
//			Logger.log(MBSConstants.PUB_METHOD, EMPLog.ERROR, 0, "格式化日期出错", e);
		}
		return date;
	}

	

	/**
	 * 手机号段检查（不验证号段）
	 * 
	 * @param MobileNo
	 *          手机号
	 * @return -4＝手机号为空； -2＝手机号含有非数字； -1＝手机号不是11位； -3=第1位不是1；0=表示正常
	 */
	public static int checkMobileNo(String MobileNo) {
		if (isStrEmpty(MobileNo)) {
			Logger.getLogger(DataUtil.class).debug("-- 手机号为空------");
			return -4;
		}
		if (checkString(MobileNo, int0NumberRegEX, false) != 1) {
			Logger.getLogger(DataUtil.class).debug("-- 手机号含有非数字字符------");    
			return -2;
		}
		if (MobileNo.length() != 11) {
			Logger.getLogger(DataUtil.class).debug("-- 手机号不是11位------") ;
			return -1;
		}

		if (!"1".equals(MobileNo.trim().substring(0, 1))) {
			return -3;
		}

		return 0;
	}

	/**
	 * 得到匹配重复字符串的正则表达式
	 * 
	 * @param length
	 *          int 要匹配的字符串长度
	 * @return String 成功时返回匹配重复字符串的正则表达式，返回null表示输入参数不合法
	 */
	public static String getrepeatRegEx(int length) {
		if (length <= 1) {
			return null;
		}
		return pre_repeatRegEx + (length - 1) + suffix_repeatRegEx;
	}

	/**
	 * 校验邮箱格式
	 * 
	 * @param Email
	 *          邮箱地址
	 * @return true:正常
	 */
	public static boolean checkEmail(String Email) {
		return checkString(Email, EmailRegEx, false) == 1;

	}

	/**
	 * 数据校验方式
	 * 
	 * @param source
	 *          String 需要校验的数据
	 * @param regEx
	 *          String 校验规则(正则表达式)
	 * @param isSentive
	 *          boolean 大小写是否敏感 true-大小写敏感 false-大小写不敏感
	 * @return int
	 */
	public static int checkString(String source, String regEx, boolean isSentive) {
		if (source == null) {
			return -1;
		}
		if (regEx == null || regEx.equals("")) {
			return -2;
		}
		Pattern p = null;
		if (isSentive) { // 大小写敏感
			p = Pattern.compile(regEx);
		} else { // 不区分大小写
			p = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
		}

		Matcher m = p.matcher(source);
		boolean rs = m.find();
		if (rs) {
			return 1;
		}
		return -3;
	}

	/**
	 * 根据身份证规则判断身份证是否合法 1-表示身份证合法；-1-表示输入不合法；-2表示身份证不合法
	 * 
	 * @param id
	 *          需要校验的身份证号,对于x大小写不敏感
	 * @return 1-表示身份证合法
	 */
	public static int checkIdentityCard(String id) {
		if (isStrEmpty(id)) {
			return -1;
		}
		if (checkString(id, idRegEx, false) == 1) {
			return 1;
		}
		return -2;

	}

	/**
	 * 1.全角字符转半角字符,中文空格转英文空格；2.去掉字符串前后空格；
	 * 
	 * @param source
	 *          String
	 * @return String
	 */
	public static String wTranHalf(String source) {
		if (isEmpty(source)) {
			return source;
		}
		// 去掉前后空白
		source = source.trim();
		// 使用正则表达式替换全角空格
		source = source.replaceAll("　", " ");
		source = source.replaceAll("，", ",");
		String outStr = "";
		String Tstr = "";
		byte[] b = null;
		// 全角转换为半角
		for (int i = 0; i < source.length(); i++) {
			try {
				Tstr = source.substring(i, i + 1);
				b = Tstr.getBytes("unicode");
			} catch (java.io.UnsupportedEncodingException ex) {
				// logger.error("java.io.UnsupportedEncodingException：",ex);
			}
			if (b[3] == -1) {
				b[2] = (byte) (b[2] + 32);
				b[3] = 0;
				try {
					outStr = outStr + new String(b, "unicode");
				} catch (java.io.UnsupportedEncodingException e) {
					// logger.error("java.io.UnsupportedEncodingException：",e);
				}
			} else {
				outStr = outStr + Tstr;
			}
		}

		return outStr;
	}

	/**
	 * 半角字符转全角字符,英文空格转中文空格；
	 * 
	 * @param source
	 *          String
	 * @return String
	 * @author hm
	 */
	public static String toSBC(String source) {

		if (isEmpty(source)) {
			return source;

		}
		// 半角转全角：
		char[] input = source.toCharArray();
		for (int i = 0; i < input.length; i++) {
			// 半角空格特殊处理
			if (input[i] == 32) {
				input[i] = (char) 12288;
				continue;
			}
			if (input[i] < 127)
				input[i] = (char) (input[i] + 65248);
		}
		return String.valueOf(input);
	}

	/**
	 * 根据length的要求格式化amount的小数点后面的长度
	 * 
	 * @param amount
	 *          需要格式化的amount
	 * @param length
	 *          小数点后要求的精度
	 * @return
	 */
	public static BigDecimal getFormatDecimal(BigDecimal amount, int length) {
		if (amount == null) {
			return null;
		}
		if (length < 0) {
			return amount;
		}
		String src = amount.toString();
		int point_place = src.indexOf(".");
		if (point_place == -1) {
			src = formatPointNumber(src + ".", src.length() + length + 1);// 总长度需要加上小数点的长度
			return new BigDecimal(src);
		}
		src = formatPointNumber(src, point_place + 1 + length);// point_place表示从0开始的位置，但是我们要求的是需要的总长度，所以需要加1再加小数点后的长度
		return new BigDecimal(src);
	}

	/**
	 * 根据sumLength的要求维护src的精度，不足的位数补0，超过的位数删除
	 * 
	 * @param src
	 * @param sumLength
	 * @return
	 */
	private static String formatPointNumber(String src, int sumLength) {
		int nowLength = src.length();
		if (nowLength > sumLength) {
			src = src.substring(0, sumLength);
			return src;
		}
		if (nowLength == sumLength) {
			return src;
		}
		if (nowLength < sumLength) {
			for (int i = nowLength; i < sumLength; i++) {
				src += "0";
			}
			return src;
		}
		return src;
	}

	/**
	 * 判断时间是否正确格式
	 * 
	 * @param date
	 *          时间的字符串
	 * @param format
	 *          时间的格式
	 * @return
	 */
	public static boolean checkDate(String date, String format) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		Date changedDate = null;
		try {
			changedDate = dateFormat.parse(date);
		} catch (Exception e) {
			// 如果不能转换,肯定是错误格式
			return false;
		}

		String changedStr = dateFormat.format(changedDate);
		// 转换后的日期再转换回String,如果不等,逻辑错误.如format为"yyyy-MM-dd",date为
		// "2006-02-31",转换为日期后再转换回字符串为"2006-03-03",说明格式虽然对,但日期
		// 逻辑上不对.

		return date.equals(changedStr);
	}
	/**
	 * 去掉数字后面多余的0
	 * @param s
	 * @return
	 */
	public static final String delDotZero(String s) {
		if (org.apache.commons.lang.StringUtils.isNotBlank(s)) {
			if (s.indexOf(".") > 0) {
				s = s.replaceAll("0+?$", "");//去掉多余的0
				s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
			}
		}
		return s;
	}

	/**
	 * 去掉字符串前面的零  如“000000201.01” >> "201.01",提供给金额转换使用
	 * @param s
	 * @return
     */
	public static final String delBeforeZero(String s) {
		if (org.apache.commons.lang.StringUtils.isNotBlank(s)) {
			if (s.indexOf(".") > 0) {
				s = s.replaceAll("^[0]+", "");//去掉数字字符串前面的零
			}
		}
		return s;
	}

	/**
	 * 去除字符串中的空格
	 */
	public static String delStrSpace(String str) {
		if(str == null || "".equals(str)) {
			return str;
		}
		String regEx_space = "\\s";
		Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);
		Matcher m_space = p_space.matcher(str);
		str = m_space.replaceAll("");
		return str;
	}
}
