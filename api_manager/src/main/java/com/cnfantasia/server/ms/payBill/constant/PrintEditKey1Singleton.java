package com.cnfantasia.server.ms.payBill.constant;

/**
 * 打印配置1编辑
 * 
 * @author liyulin
 * @version 1.0 2017年5月3日 下午4:59:04
 */
public class PrintEditKey1Singleton extends PrintEditKeyBase {
	private static PrintEditKey1Singleton instance;

	private PrintEditKey1Singleton() {
	}

	public static synchronized PrintEditKey1Singleton getInstance() {
		if (instance == null) {
			instance = new PrintEditKey1Singleton();
		}
		return instance;
	}

	@Override
	public String getTableContent(String s) {
		return "<tr><td colspan=\"6\">" + s + "</td></tr>";
	}

}