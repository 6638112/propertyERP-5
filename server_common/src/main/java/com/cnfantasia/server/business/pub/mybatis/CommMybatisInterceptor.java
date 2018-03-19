/**   
 * Filename:    CommMybatisInterceptor.java   
 * @version:    1.0  
 * Create at:   2014年6月24日 上午1:50:00   
 * Description:  
 *   
 * Modification History:   
 * Date        Author      Version     Description   
 * ----------------------------------------------------------------- 
 * 2014年6月24日    shiyl      1.0         1.0 Version   
 */
package com.cnfantasia.server.business.pub.mybatis;

/**
 * Filename:    CommMybatisInterceptor.java
 * @version:    1.0.0
 * Create at:   2014年6月24日 上午1:50:00
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月24日       shiyl             1.0             1.0 Version
 */
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandlerRegistry;

import com.cnfantasia.server.business.pub.CommBaseEntity;

public abstract class CommMybatisInterceptor implements Interceptor {
	private static Log logger = LogFactory.getLog(CommMybatisInterceptor.class);

	@SuppressWarnings("rawtypes")
	public abstract void doMap(Map tmp, String sqlStrUpperCaseTrim);

	public abstract void doCommBaseEntity(CommBaseEntity tmp, String sqlStrUpperCaseTrim);

	@SuppressWarnings("rawtypes")
	private void doList(List tmpList, String sqlStrUpperCaseTrim) {
		Object data0 = tmpList.get(0);
		if (data0 != null) {
			if (data0 instanceof Map) {
				for (Object obj : tmpList) {
					Map tmp = (Map) obj;
					doMap(tmp, sqlStrUpperCaseTrim);
				}
			} else if (data0 instanceof CommBaseEntity) {
				for (Object obj : tmpList) {
					CommBaseEntity tmp = (CommBaseEntity) obj;
					doCommBaseEntity(tmp, sqlStrUpperCaseTrim);
				}
			}
		}
	}

	@SuppressWarnings({ "rawtypes" })
	public Object intercept(Invocation invocation) throws Throwable {
		// invocation.getMethod().getName();
		MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
		Object parameter = null;
		if (invocation.getArgs().length > 1) {
			parameter = invocation.getArgs()[1];
			if (parameter != null) {
				BoundSql boundSql = mappedStatement.getBoundSql(parameter);
				String sqlStrUpperCaseTrim = boundSql.getSql().trim().toUpperCase();
				if (parameter instanceof Map) {
					Map tmp = (Map) parameter;
					doMap(tmp, sqlStrUpperCaseTrim);
					Object listBatis = tmp.get("list");
					if (listBatis != null && listBatis instanceof List) {
						List tmpList = (List) listBatis;
						doList(tmpList, sqlStrUpperCaseTrim);
					}
				} else if (parameter instanceof CommBaseEntity) {
					CommBaseEntity tmp = (CommBaseEntity) parameter;
					doCommBaseEntity(tmp, sqlStrUpperCaseTrim);
				} else if (parameter instanceof List) {
					List tmpList = (List) parameter;
					doList(tmpList, sqlStrUpperCaseTrim);
				}
			}

		}
		//
		String sqlId = mappedStatement.getId();
		/***
		 * TODO:插入正则表达式时，showSql(configuration, boundSql)会报错！！！
		 */
		
		try {
			BoundSql boundSql = mappedStatement.getBoundSql(parameter);
			Configuration configuration = mappedStatement.getConfiguration();
			if (logger.isDebugEnabled()) {
				logger.debug(sqlId + " " + showSql(configuration, boundSql));
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		long start = System.currentTimeMillis();
		Object returnValue = null;
		returnValue = invocation.proceed();
		if (logger.isDebugEnabled()) {
			long end = System.currentTimeMillis();
			long time = (end - start);
			if (time > 1) {
				// String sql = getSql(configuration, boundSql, sqlId, time);
				logger.debug(sqlId + ":" + time + "ms");
			}
		}
		return returnValue;
	}

	// public static String getSql(Configuration configuration, BoundSql boundSql,
	// String sqlId, long time) {
	// String sql = showSql(configuration, boundSql);
	// StringBuilder str = new StringBuilder(100);
	// str.append(sqlId);
	// str.append(":");
	// str.append(sql);
	// str.append(":");
	// str.append(time);
	// str.append("ms");
	// return str.toString();
	// }

	private static String getParameterValue(Object obj) {
		String value = null;
		if (obj instanceof String) {
			value = "'" + obj.toString() + "'";
		} else if (obj instanceof Date) {
			DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
			value = "'" + formatter.format(obj) + "'";
		} else {
			if (obj != null) {
				value = obj.toString();
			} else {
				value = "null";
			}

		}
		return value;
	}

	public static String showSql(Configuration configuration, BoundSql boundSql) {
		Object parameterObject = boundSql.getParameterObject();
		List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
		String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
		if (parameterMappings.size() > 0 && parameterObject != null) {
			TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
			if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
				sql = sql.replaceFirst("\\?", getParameterValue(parameterObject));

			} else {
				MetaObject metaObject = configuration.newMetaObject(parameterObject);
				for (ParameterMapping parameterMapping : parameterMappings) {
					String propertyName = parameterMapping.getProperty();
					if (metaObject.hasGetter(propertyName)) {
						Object obj = metaObject.getValue(propertyName);
						sql = sql.replaceFirst("\\?", getParameterValue(obj).replaceAll("\\$", "\\\\\\$"));
					} else if (boundSql.hasAdditionalParameter(propertyName)) {
						Object obj = boundSql.getAdditionalParameter(propertyName);
						sql = sql.replaceFirst("\\?", getParameterValue(obj));
					}
				}
			}
		}
		return sql;
	}
	
	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}
	
}