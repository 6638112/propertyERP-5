package com.cnfantasia.server.api.bankCollection.utils;

import org.springframework.util.StringUtils;

import com.cnfantasia.server.domainbase.bcFileDefine.entity.BcFileDefine;

/**
 * 出盘回盘工具类
 * 
 * @author wenfq
 *
 */
public class BankCollectionUtils {

	/**
	 * 得到一列域值
	 * 
	 * @param value
	 *            传入的值
	 * @param bcFileDefine
	 *            列定义
	 * @return 组装后列定义所对应的值，按对齐方式，填充字段组装
	 */
	public static String getOneFieldValue(String value, BcFileDefine bcFileDefine) {
		if(value == null)//业主名是可以为空的
			value ="";
		
		String fileValue = value;

		int width = bcFileDefine.getWidth().intValue();
		if (value.length() >= width) {
			return value.substring(0, width);
		} else {
			int d = width - value.length();//要补齐长度
			for(int i = 0; i < d; i++){
				if(!StringUtils.isEmpty(bcFileDefine.getFillChar())){
					if(bcFileDefine.getAlignment().equals("left"))
						fileValue = fileValue + bcFileDefine.getFillChar();
					else if(bcFileDefine.getAlignment().equals("right"))
						fileValue = bcFileDefine.getFillChar() + fileValue;
				}
			}
		}

		return fileValue;
	}

}
