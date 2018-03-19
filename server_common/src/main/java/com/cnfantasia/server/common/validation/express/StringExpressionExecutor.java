package com.cnfantasia.server.common.validation.express;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cnfantasia.server.common.exception.SystemException;

/**
 * 类名：StringExpressionExecutor  <br />
 *
 * 功能：字符串格式配置的表达式执行器
 */
public class StringExpressionExecutor extends AbstractExpressionExecutor {
	
	// 匹配器
	private Pattern pattern = Pattern.compile("([^{};]+[{][^}]+[}][;])|([^{};]+[{][^}]+[}])|([^{};]+[;])|([^{};]+)");
	
	// 组分隔符，默认为";"
	//private String groupSeparator = ";";
	
	// 参数配置起始符，默认为"{"
	//private String paramStart = "{";
	
	// 参数配置结束符，默认为"}"
	//private String paramEnd = "}";
	
	// 配置分隔符
	//private String configSeparator = ",";
	
	//private String entrySeparator = "=";

	/**
	 * 
	 */
	@Override
	protected Map<String, Map<String, String>> parseExpression(Object expression) throws Exception {
		if (! (expression instanceof String)) {
			throw new SystemException("system.error.expression.not.support.type", new Object[] { expression.getClass().getName() });
		}
		
		// 执行解析
		return doParse((String) expression);
	}
	
	/**
	 * 功能：将表达式解析为map格式的配置 <br/>
	 */
	protected Map<String, Map<String, String>> doParse(String expression) throws Exception {
		
		//aaa{length=1,2;option=false};bbb{pattern=dddd,xxxx=bbb}
		Matcher matcher = pattern.matcher(expression);
		
		// 表达式容器
		Map<String, Map<String, String>> expMap = new HashMap<String, Map<String, String>>();
		
		int lastEnd = 0;
		
		while (matcher.find()) {
			if (matcher.start() != lastEnd) {
				// 表达式无效
				throw new SystemException("expression.is.invalid", new Object[] { expression });
			}
			
			String group = matcher.group();
			
			if (group.endsWith(";")) {
				group = group.substring(0, group.length() - 1);
			}
			
			String name = group;
			
			// 表达式配置容器
			Map<String, String> configMap = null;
			
			int index = group.indexOf('{');
			if (index != -1) {
				// 处理配置信息
				configMap = new HashMap<String, String>();
				
				// 取出名称
				name = group.substring(0, index);
				
				// 截取出
				for (String config : group.substring(index + 1, group.lastIndexOf("}")).split(";")) {
					String key = config;
					String value = null;
					int tmp = config.indexOf("=");
					
					if (tmp != -1) {
						key = config.substring(0, tmp);
						
						value = config.substring(tmp + 1);
					}
					
					configMap.put(key, value);
				}
			}
			
			// 放入解析出的配置信息
			expMap.put(name, configMap);
			
			// 置为最后索引，供下次检查
			lastEnd = matcher.end();
		}
		
		if (lastEnd != expression.length()) {
			// 表达式无效
			throw new SystemException("expression.is.invalid", new Object[] { expression });
		}
		
		return expMap;
	}
	/*protected Map<String, Map<String, String>> doParse(String expression) throws Exception {
		
		//<entry key="name" value="aaa{length=1:2,option=false}" />
		// 表达式容器
		Map<String, Map<String, String>> expMap = new HashMap<String, Map<String, String>>();
		
		// 表达式以";"进行分组配置
		for (String exp : expression.split(groupSeparator)) {
			int index = exp.indexOf(paramStart);
			
			String rname = exp;
			
			// 表达式配置容器
			Map<String, String> configMap = null;
			
			if (index != -1) {
				configMap = new HashMap<String, String>();
				
				rname = exp.substring(0, index);
				
				for (String config : exp.substring(index + paramStart.length(), exp.indexOf(paramEnd, index + paramStart.length())).split(configSeparator)) {
					String key = config;
					String value = null;
					int tmp = config.indexOf(entrySeparator);
					
					if (tmp != -1) {
						key = config.substring(0, tmp);
						
						value = config.substring(tmp + entrySeparator.length());
					}
					
					configMap.put(key, value);
				}
			}
			
			// 放入缓存
			expMap.put(rname, configMap);
		}
		
		return expMap;
	}*/

//	/**
//	 */
//	public void setGroupSeparator(String groupSeparator) {
//		this.groupSeparator = groupSeparator;
//	}
//
//	/**
//	 */
//	public String getGroupSeparator() {
//		return groupSeparator;
//	}
}