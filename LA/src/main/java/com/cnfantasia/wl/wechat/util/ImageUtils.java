package com.cnfantasia.wl.wechat.util;

import org.apache.commons.lang.StringUtils;

/**
 * 图片相关的工具类
 * 
 * @author wenfq 2015-01-16
 * 
 */
public class ImageUtils {

	/**
	 * 获得列表图片URL
	 * 
	 * @param picBase
	 *            大图的URL
	 * @return
	 */
	public static String getImageListURL(String picBase) {
		if (StringUtils.isEmpty(picBase)) {
			return "#";
		}

		int dotIndex = picBase.lastIndexOf(".");
		int questionIndex = picBase.lastIndexOf("?");
		String picSuffix = picBase.substring(dotIndex, questionIndex);
		return picBase.subSequence(0, dotIndex) + "/226x166" + picSuffix;
	}
}
