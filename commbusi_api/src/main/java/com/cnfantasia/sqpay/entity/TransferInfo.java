package com.cnfantasia.sqpay.entity;//

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

import com.cnfantasia.server.business.pub.utils.Md5Util;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {})
public class TransferInfo {
	@XmlAttribute(name="OrderNO")
	private String OrderNO;//就是你的该提现的订单号，注意订单号不能重复，和你以前上传的也不能重复 ，要保证唯一；
	@XmlAttribute
	private String bankName;//银行名
	@XmlAttribute
	private String province;//开户的省
	@XmlAttribute
	private String city;//开户的市
	@XmlAttribute
	private String branchName;//支行地址（务必填写正确）
	@XmlAttribute
	private String accountName;//开户名
	@XmlAttribute
	private String bankCardNo;//银行卡号
	@XmlAttribute
	private String transAmount;//转账金额
	@XmlAttribute
	private String phone;//转账人的号码（选填）
	@XmlAttribute
	private String remark;//备注（选填）
	@XmlAttribute
	private String username;//用户名（部分用户需求）（选填）
	@XmlAttribute
	private String encryptionstr;//加密字符串加密的例子为;//

	/**
	 * 分配给解放区的MD5Key
	 */
	public static final String MD5Key = "Hadsd234";
	
	public TransferInfo() {
		// TODO Auto-generated constructor stub
	}
	public TransferInfo(String orderNO, String bankName, String province, String city, String branchName, String accountName, String bankCardNo, String transAmount, String phone,
			String remark, String username, String encryptionstr, String mD5Info) {
		super();
		OrderNO = orderNO;
		this.bankName = bankName;
		this.province = province;
		this.city = city;
		this.branchName = branchName;
		this.accountName = accountName;
		this.bankCardNo = bankCardNo;
		this.transAmount = transAmount;
		this.phone = phone;
		this.remark = remark;
		this.username = username;
		this.encryptionstr = encryptionstr;
	}

	public String getOrderNO() {
		return OrderNO;
	}

	public void setOrderNO(String orderNO) {
		OrderNO = orderNO;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getBankCardNo() {
		return bankCardNo;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}

	public String getTransAmount() {
		return transAmount;
	}

	public void setTransAmount(String transAmount) {
		this.transAmount = transAmount;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEncryptionstr() {
		return this.encryptionstr;
	}
	
	/**
	 * 提现接口所需加密串
	 */
	public void generatePushWithdrawEncryptionstr() {
		StringBuffer sb = new StringBuffer();
		sb.append("OrderNO=").append(OrderNO);
		sb.append("&bankCardNo=").append(bankCardNo);
		sb.append("&transAmount=").append(transAmount);
		sb.append("&"+ Md5Util.MD5(MD5Key));
		this.encryptionstr = Md5Util.MD5(sb.toString()).toUpperCase();
	}
	
	/**
	 * 查询提现接口所需加密串
	 */
	public void generateQryWithdrawEncryptionstr() {
		StringBuffer sb = new StringBuffer();
		sb.append("OrderNO=").append(OrderNO);
		sb.append("&"+ Md5Util.MD5(MD5Key));
		this.encryptionstr = Md5Util.MD5(sb.toString()).toUpperCase();
	}

	public void setEncryptionstr(String encryptionstr) {
		this.encryptionstr = encryptionstr;
	}
	@Override
	public String toString() {
		return "TransferInfo [OrderNO=" + OrderNO + ", bankName=" + bankName + ", province=" + province + ", city=" + city + ", branchName=" + branchName + ", accountName="
				+ accountName + ", bankCardNo=" + bankCardNo + ", transAmount=" + transAmount + ", phone=" + phone + ", remark=" + remark + ", username=" + username
				+ ", encryptionstr=" + encryptionstr + "]";
	}

}
