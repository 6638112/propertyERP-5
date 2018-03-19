package com.cnfantasia.server.ms.pd4ml.util;

import java.awt.Dimension;
import java.lang.reflect.Field;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 纸张类型
 * 
 * @author liyulin
 * @version 1.0 2017年1月5日 下午2:12:49
 */
public class PdfDimension {
	public static final Dimension A5 = new Dimension(421, 595);
	public static final Dimension A4 = new Dimension(595, 842);
	public static final Dimension A4_Half = new Dimension(595, 421);

	/** 所有纸张类型 */
	private static Map<String, Dimension> pageSizeMap = new LinkedHashMap<String, Dimension>();
	static {
		Field[] fields = PdfDimension.class.getFields();// 所有公有
		try {
			for (int i = 0; i < fields.length; i++) {
				Field field = fields[i];
				pageSizeMap.put(field.getName(), (Dimension) field.get(field));
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public final static Map<String, Dimension> getAllPageSizes() {
		return pageSizeMap;
	}

	public static final Dimension getDimension(String pageSize) {
		Dimension dimension = pageSizeMap.get(pageSize);
		return dimension == null ? A4 : dimension;
	}
}
