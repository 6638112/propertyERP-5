package com.cnfantasia.server.ms.payBill.constant;

/**
 * 打印配置编辑
 * 
 * @author liyulin
 * @version 1.0 2017年5月3日 下午4:58:54
 */
public enum PrintEditKeyEnum {
	INSTANCE;

	private static PrintEditKey1Singleton printEditKey1 = PrintEditKey1Singleton.getInstance();
	private static PrintEditKey2Singleton printEditKey2 = PrintEditKey2Singleton.getInstance();

	static {
		printEditKey1.init(printEditKey1);
		printEditKey2.init(printEditKey2);
	}

	public PrintEditKeyBase getPrintEditKey(String code) {
		if (PrintTemplateCode.template1.equals(code)) {
			return printEditKey1;
		} else if (PrintTemplateCode.template2.equals(code)) {
			return printEditKey2;
		}
		return null;
	}

	public PrintEditKey1Singleton getPrintEditKey1() {
		return printEditKey1;
	}

	public PrintEditKey2Singleton getPrintEditKey2() {
		return printEditKey2;
	}
}
