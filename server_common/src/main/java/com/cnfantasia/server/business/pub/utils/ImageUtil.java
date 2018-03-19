package com.cnfantasia.server.business.pub.utils;

import org.apache.commons.lang.StringUtils;

public class ImageUtil {
	
	public static String getImageListURL(String picBase, String size) {
		if (StringUtils.isEmpty(picBase)) {
			return "#";
		}

		int dotIndex = picBase.lastIndexOf(".");
		String picSuffix = picBase.substring(dotIndex);
		
		return picBase.replace(picSuffix, "/" + size + picSuffix);
	}
	
	public static void main(String[] args) {
		String test = getImageListURL("http://image.linlile.com.cn:8086/productPic/102522_14415270048950.jpg?2015-09-06_16_10_05", "640x467");
		System.out.println(test);
	}

}
