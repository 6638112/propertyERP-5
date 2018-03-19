package com.cnfantasia.server.common.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;


/**
 * @author yewj
 */
public class ParamUtils {
	protected final static Logger LOG = Logger.getLogger(ParamUtils.class);

	public static String getString(HttpServletRequest request, String paramName, String defaultValue) {
		String result = request.getParameter(paramName);

		if (result == null || "".equals(result)) result = defaultValue;

		result = ScriptFilter.escape(result);

		if(!DataUtil.isEmpty(result)) {
			result = result.trim();
		}

		return result;
	}

	public static Date getDate(HttpServletRequest request, String paramName, Date defaultValue) {
		String ori = request.getParameter(paramName);
		if (org.apache.commons.lang.StringUtils.isBlank(ori)) return defaultValue;

		try {
			return DateUtils.convertStrToDate(ori);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	public static Date getDate(HttpServletRequest request, String paramName, Date defaultValue, String format) {
		String ori = request.getParameter(paramName);
		if (org.apache.commons.lang.StringUtils.isBlank(ori)) return defaultValue;

		try {
			return DateUtils.convertStrToDate(ori, format);
		} catch (Exception e) {
			return defaultValue;
		}
	}

	public static String getParameterIgnoreCase(HttpServletRequest request, String paramName) {
		for (Enumeration em = request.getParameterNames(); em.hasMoreElements();) {
			String name = (String) em.nextElement();
			if (name.equalsIgnoreCase(paramName)) return request.getParameter(name);
		}
		return null;
	}

	/**
	 * 千万要注意引号会被清除掉！！！！！
	 * @param request
	 * @param paramName
	 * @return
	 */
	public static String getString(HttpServletRequest request, String paramName) {
		return getString(request, paramName, "");
	}
	public static String getStringTrim(HttpServletRequest request, String paramName) {
		String res = getString(request, paramName, "");
		if(res!=null){res = res.trim();}
		return res;
	}

	public static String[] getStrings(HttpServletRequest request, String paramName) {
		return request.getParameterValues(paramName);
	}

	public static Integer getInteger(HttpServletRequest request, String paramName, Integer defaultValue) {
		Integer result = null;
		if (paramName == null || paramName.trim().length() == 0) 
			return defaultValue;
		
		String paramValue = request.getParameter(paramName);		
		if(org.apache.commons.lang.StringUtils.isBlank(paramValue))
			return defaultValue;
		
		result = Integer.valueOf(paramValue);
		return result;
	}

	public static int getInt(HttpServletRequest request, String paramName, int defaultValue) {
		if (paramName == null || paramName.trim().length() == 0) 
			return defaultValue;
		
		String paramValue = request.getParameter(paramName);
		if(org.apache.commons.lang.StringUtils.isBlank(paramValue))
			return defaultValue;
		
		return Integer.parseInt(request.getParameter(paramName));
	}

	public static float getFloat(HttpServletRequest request, String paramName, int defaultValue) {
		if (paramName == null || paramName.trim().length() == 0) 
			return defaultValue;
		
		String paramValue = request.getParameter(paramName);
		if(org.apache.commons.lang.StringUtils.isBlank(paramValue))
			return defaultValue;
		
		return Float.parseFloat(request.getParameter(paramName));
	}
	
	public static double getDouble(HttpServletRequest request, String paramName) {
		try {
			if (paramName == null || paramName.trim().length() == 0)
				return 0;
			return Double.parseDouble(request.getParameter(paramName));
		} catch (RuntimeException e) {
			return 0L;
		}
	}

	public static double getDouble(HttpServletRequest request, String paramName, double defaultValue) {
		try {
			if (paramName == null || paramName.trim().length() == 0)
				return defaultValue;
			if(request.getParameter(paramName)!=null){
				return Double.parseDouble(request.getParameter(paramName));
			}
			return defaultValue;
		} catch (RuntimeException e) {
			return defaultValue;
		}
	}
	
	public static Double getDouble(HttpServletRequest request, String paramName, Double defaultValue) {
		try {
			if (paramName == null || paramName.trim().length() == 0)
				return defaultValue;
			return Double.parseDouble(request.getParameter(paramName));
		} catch (Exception e) {
			return defaultValue;
		}
	}
	public static long getLong(HttpServletRequest request, String paramName) {
		try {
			if (paramName == null || paramName.trim().length() == 0) return 0L;			
			return Long.parseLong(request.getParameter(paramName));
		} catch (NumberFormatException e) {
			return 0L;
		}
	}
	
	public static Long getLong(HttpServletRequest request, String paramName, Long defaultValue) {
		try {
			if (paramName == null || paramName.trim().length() == 0) return defaultValue;
			if(request.getParameter(paramName) != null) {
				return Long.parseLong(request.getParameter(paramName).trim());
			}
			return defaultValue;
		} catch (NumberFormatException e) {
			return defaultValue;
		}
	}

	public static Integer[] getIntegers(HttpServletRequest request, String paramName, Integer defaultValue) {
		String[] temp = request.getParameterValues(paramName);
		if (temp != null) {
			Integer[] result = new Integer[temp.length];
			for (int i = 0; i < result.length; i++)
				try {

					result[i] = Integer.valueOf(temp[i]);
				} catch (NumberFormatException e) {
					result[i] = defaultValue;
				}
			return result;
		} else
			return new Integer[0];
	}

	public static BigInteger[] getBigIntegerArray(HttpServletRequest request, String paramName, Integer defaultValue) {
		String[] temp = request.getParameterValues(paramName);
		if (temp != null) {
			BigInteger[] result = new BigInteger[temp.length];
			for (int i = 0; i < result.length; i++)
				try {
					result[i] = new BigInteger(temp[i]);
				} catch (NumberFormatException e) {
					String de = String.valueOf(defaultValue);
					result[i] = new BigInteger(de);
				}
			return result;
		} else
			return new BigInteger[0];
	}
	
	public static List<BigInteger> getBigIntegerList(HttpServletRequest request, String paramName, Integer defaultValue) {
		List<BigInteger> tempList = new ArrayList<BigInteger>();
		tempList = Arrays.asList(getBigIntegerArray(request, paramName, defaultValue));
		
		return tempList;
	}
	
	public static Long[] getLongs(HttpServletRequest request, String paramName, Integer defaultValue) {
		String[] temp = request.getParameterValues(paramName);
		if (temp != null) {
			Long[] result = new Long[temp.length];
			for (int i = 0; i < result.length; i++)
				try {
					result[i] = Long.valueOf(temp[i]);
				} catch (NumberFormatException e) {
					String de = String.valueOf(defaultValue);
					long dd = Long.valueOf(de);
					result[i] = dd;
				}
			return result;
		} else
			return new Long[0];
	}
	
	public static Long[] getLongs(HttpServletRequest request, String paramName, String split) {
		String temp = request.getParameter(paramName);
		if (temp != null) {
			String[] a = temp.split(split);
			if(a == null || a.length == 0)
				return new Long[0];
			
			Long[] res = new Long[a.length];
			for(int i = 0; i < a.length; i++){
				res[i] = Long.parseLong(a[i]);
			}
			
			return res;
		} else
			return new Long[0];
	}

	public static BigDecimal getBigDecimal(HttpServletRequest request, String paramName, BigDecimal defaultValue) {
		BigDecimal result = null;
		try {
			result = new BigDecimal(request.getParameter(paramName));
		} catch (RuntimeException e) {
			result = defaultValue;
		}
		return result;
	}
	
	public static BigInteger getBigInteger(HttpServletRequest request, String paramName, BigInteger defaultValue) {
		BigInteger result = null;
		try {
			result = new BigInteger(request.getParameter(paramName));
		} catch (RuntimeException e) {
			result = defaultValue;
		}
		return result;
	}

	public static Boolean getBoolean(HttpServletRequest request, String paramName, Boolean defaultValue) {
		Boolean result = null;
		try {
			result = Boolean.valueOf(request.getParameter(paramName));
		} catch (NumberFormatException e) {
			result = defaultValue;
		}
		return result;
	}
	
	public static Boolean getBooleanNotNull(HttpServletRequest request, String paramName, Boolean defaultValue) {
		Boolean result = null;
		try {
			String parmStr = request.getParameter(paramName);
			if(!StringUtils.isEmpty(parmStr)&&parmStr.trim().equalsIgnoreCase("false")){
				result = false;
			}else if(!StringUtils.isEmpty(parmStr)&&parmStr.trim().equalsIgnoreCase("true")){
				result = true;
			}else{
				result = defaultValue;
			}
		} catch (NumberFormatException e) {
			result = defaultValue;
		}
		return result;
	}
	
	public static String getCookieValue(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) for (Cookie cookie : cookies)
			if (cookie.getName().equals(name)) return cookie.getValue();
		return null;
	}

	public static void setCookieValue(HttpServletRequest request, String name, String value) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) for (Cookie cookie : cookies)
			if (cookie.getName().equals(name)) cookie.setValue(value);
	}
	
	public static String[] getStrings(HttpServletRequest request, String param, String split){
		String raw = request.getParameter(param);
		if(raw != null){
			String[] strs = raw.split(split);
			
			if(strs == null)
				return new String[0];
			
			return strs;
		}else
			return new String[0];
	}
	
	public static String getRequestBodyJson(HttpServletRequest request) {
		try {
			InputStream inputStream = request.getInputStream();
			int defaultBufferSize = 1024 * 4;
	        Reader input = new InputStreamReader(inputStream);
	        Writer output = new StringWriter();
	        char[] buffer = new char[defaultBufferSize];
	        int n = 0;
	        while(-1 != (n = input.read(buffer))) {
	            output.write(buffer, 0, n);
	        }
	        return output.toString();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
    }
	
	public static Long getLongPrice(HttpServletRequest request, String paramName, Long defaultValue) {
		Long ret = defaultValue;
		BigDecimal result = null;
		try {
			result = new BigDecimal(request.getParameter(paramName));
			result = result.multiply(BigDecimal.valueOf(100));
			ret = result.longValue();
		} catch (RuntimeException e) {
		}
		return ret;
	}
}
