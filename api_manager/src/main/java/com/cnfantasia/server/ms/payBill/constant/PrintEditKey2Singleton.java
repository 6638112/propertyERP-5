package com.cnfantasia.server.ms.payBill.constant;

/**
 * 打印配置2编辑
 * 
 * @author liyulin
 * @version 1.0 2017年5月3日 下午4:59:04
 */
public final class PrintEditKey2Singleton extends PrintEditKeyBase {

	private static PrintEditKey2Singleton instance;

	private PrintEditKey2Singleton() {
	}

	public static synchronized PrintEditKey2Singleton getInstance() {
		if (instance == null) {
			instance = new PrintEditKey2Singleton();
		}
		return instance;
	}

	/** 房屋面积 */
	public String $roomSize$ = "房屋面积";
	
	@Override
	public String getTableContent(String s) {
		return "<tr><td colspan=\"4\">" + s + "</td></tr>";
	}

}
