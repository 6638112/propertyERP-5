package com.cnfantasia.server.api.payment.entity;

/**
 * 双乾支付支付成功回调接口参数
 * 
 * @author liyulin
 * @version 1.0 2016年9月8日 下午8:51:35
 */
public class SqPayNotifyParam {

	private String merNo;
	private String billNo;
	private String amount;
	/** 优惠补贴 */
	private String amountBt;
	private String succeed;
	private String result;
	private String mD5info;
	private String merRemark;
	private String mD5key;
	private String paramAll;
	private String flowNo;

	public SqPayNotifyParam() {
		super();
	}

	public SqPayNotifyParam(String merNo, String billNo, String amount,
			String amountBt, String succeed, String result, String mD5info,
			String merRemark, String mD5key, String paramAll, String flowNo) {
		super();
		this.merNo = merNo;
		this.billNo = billNo;
		this.amount = amount;
		this.amountBt = amountBt;
		this.succeed = succeed;
		this.result = result;
		this.mD5info = mD5info;
		this.merRemark = merRemark;
		this.mD5key = mD5key;
		this.paramAll = paramAll;
		this.flowNo = flowNo;
	}

	public String getMerNo() {
		return merNo;
	}

	public void setMerNo(String merNo) {
		this.merNo = merNo;
	}

	public String getBillNo() {
		return billNo;
	}

	public void setBillNo(String billNo) {
		this.billNo = billNo;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getAmountBt() {
		return amountBt;
	}

	public void setAmountBt(String amountBt) {
		this.amountBt = amountBt;
	}

	public String getSucceed() {
		return succeed;
	}

	public void setSucceed(String succeed) {
		this.succeed = succeed;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getmD5info() {
		return mD5info;
	}

	public void setmD5info(String mD5info) {
		this.mD5info = mD5info;
	}

	public String getMerRemark() {
		return merRemark;
	}

	public void setMerRemark(String merRemark) {
		this.merRemark = merRemark;
	}

	public String getmD5key() {
		return mD5key;
	}

	public void setmD5key(String mD5key) {
		this.mD5key = mD5key;
	}

	public String getParamAll() {
		return paramAll;
	}

	public void setParamAll(String paramAll) {
		this.paramAll = paramAll;
	}

	public String getFlowNo() {
		return flowNo;
	}

	public void setFlowNo(String flowNo) {
		this.flowNo = flowNo;
	}

	@Override
	public String toString() {
		return "SqPayNotifyParam [merNo=" + merNo + ", billNo=" + billNo
				+ ", amount=" + amount + ", amountBt=" + amountBt
				+ ", succeed=" + succeed + ", result=" + result + ", mD5info="
				+ mD5info + ", merRemark=" + merRemark + ", mD5key=" + mD5key
				+ ", paramAll=" + paramAll + ", flowNo=" + flowNo + "]";
	}

}
