package com.cnfantasia.sqpay.entity;

public class TransferResult {
	private String OrderNO;//该笔订单号
	private String resultStr;//上传结果，对照表为

	//	sccess_up	成功上传
	//	sccess_transfer	成功转账
	//	error_01	Encryptionstr加密字符串验证失败
	//	error_02	支行地址为空
	//	error_03	省为空
	//	error_04	市为空
	//	error_05	Encryptionstr为空
	//	error_06	OrderNO和你以前上传的单号相同
	//	error_07	开户名错误
	//	error_08	银行卡号错误
	//	error_09	金额错误
	//	error_10	余额不足

	public String getOrderNO() {
		return OrderNO;
	}

	public void setOrderNO(String orderNO) {
		OrderNO = orderNO;
	}

	public String getResultStr() {
		return resultStr;
	}

	public void setResultStr(String resultStr) {
		this.resultStr = resultStr;
	}

}
