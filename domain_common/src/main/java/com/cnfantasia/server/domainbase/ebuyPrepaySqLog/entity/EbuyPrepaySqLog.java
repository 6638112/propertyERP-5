package com.cnfantasia.server.domainbase.ebuyPrepaySqLog.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;
import java.lang.String;
import java.lang.Integer;

 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(双乾预支付记录表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class EbuyPrepaySqLog implements Serializable{
*/


public class EbuyPrepaySqLog extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */
	private BigInteger id;
	/**  */
	private BigInteger orderId;
	/**  */
	private BigInteger userId;
	/**  */
	private String terminalKind;
	/**  */
	private String merNo;
	/**  */
	private String billNo;
	/**  */
	private String amout;
	/**  */
	private String paymentType;
	/**  */
	private String payType;
	/**  */
	private String returnUrl;
	/**  */
	private String notifyUrl;
	/**  */
	private String md5Info;
	/**  */
	private String merRemark;
	/**  */
	private String products;
	/** 订单类型 */
	private Integer orderType;

	public EbuyPrepaySqLog(){
	}
	public EbuyPrepaySqLog(EbuyPrepaySqLog arg){
		this.id = arg.getId();
		this.orderId = arg.getOrderId();
		this.userId = arg.getUserId();
		this.terminalKind = arg.getTerminalKind();
		this.merNo = arg.getMerNo();
		this.billNo = arg.getBillNo();
		this.amout = arg.getAmout();
		this.paymentType = arg.getPaymentType();
		this.payType = arg.getPayType();
		this.returnUrl = arg.getReturnUrl();
		this.notifyUrl = arg.getNotifyUrl();
		this.md5Info = arg.getMd5Info();
		this.merRemark = arg.getMerRemark();
		this.products = arg.getProducts();
		this.orderType = arg.getOrderType();
		this.sys0AddTime = arg.getSys0AddTime();
		this.sys0UpdTime = arg.getSys0UpdTime();
		this.sys0DelTime = arg.getSys0DelTime();
		this.sys0AddUser = arg.getSys0AddUser();
		this.sys0UpdUser = arg.getSys0UpdUser();
		this.sys0DelUser = arg.getSys0DelUser();
		this.sys0DelState = arg.getSys0DelState();

	}
	/**
	 * 
	 * @param id 
	 * @param orderId 
	 * @param userId 
	 * @param terminalKind 
	 * @param merNo 
	 * @param billNo 
	 * @param amout 
	 * @param paymentType 
	 * @param payType 
	 * @param returnUrl 
	 * @param notifyUrl 
	 * @param md5Info 
	 * @param merRemark 
	 * @param products 
	 * @param orderType 订单类型
	 * @param sys0AddTime 新增时间
	 * @param sys0UpdTime 更新时间
	 * @param sys0DelTime 删除时间
	 * @param sys0AddUser 新增者
	 * @param sys0UpdUser 修改者
	 * @param sys0DelUser 删除者
	 * @param sys0DelState 记录状态
	 */

	public EbuyPrepaySqLog(BigInteger id,BigInteger orderId,BigInteger userId,String terminalKind,String merNo,String billNo,String amout,String paymentType,String payType,String returnUrl,String notifyUrl,String md5Info,String merRemark,String products,Integer orderType,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;
		this.orderId = orderId;
		this.userId = userId;
		this.terminalKind = terminalKind;
		this.merNo = merNo;
		this.billNo = billNo;
		this.amout = amout;
		this.paymentType = paymentType;
		this.payType = payType;
		this.returnUrl = returnUrl;
		this.notifyUrl = notifyUrl;
		this.md5Info = md5Info;
		this.merRemark = merRemark;
		this.products = products;
		this.orderType = orderType;
		this.sys0AddTime = sys0AddTime;
		this.sys0UpdTime = sys0UpdTime;
		this.sys0DelTime = sys0DelTime;
		this.sys0AddUser = sys0AddUser;
		this.sys0UpdUser = sys0UpdUser;
		this.sys0DelUser = sys0DelUser;
		this.sys0DelState = sys0DelState;

	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("id=").append(id).append(";");
		sbf.append("orderId=").append(orderId).append(";");
		sbf.append("userId=").append(userId).append(";");
		sbf.append("terminalKind=").append(terminalKind).append(";");
		sbf.append("merNo=").append(merNo).append(";");
		sbf.append("billNo=").append(billNo).append(";");
		sbf.append("amout=").append(amout).append(";");
		sbf.append("paymentType=").append(paymentType).append(";");
		sbf.append("payType=").append(payType).append(";");
		sbf.append("returnUrl=").append(returnUrl).append(";");
		sbf.append("notifyUrl=").append(notifyUrl).append(";");
		sbf.append("md5Info=").append(md5Info).append(";");
		sbf.append("merRemark=").append(merRemark).append(";");
		sbf.append("products=").append(products).append(";");
		sbf.append("orderType=").append(orderType).append(";");
		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");
		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");
		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");
		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");
		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");
		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");
		sbf.append("sys0DelState=").append(sys0DelState).append(";");
		return sbf.toString();

	}
	
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public BigInteger getOrderId() {
		return orderId;
	}
	public void setOrderId(BigInteger orderId) {
		this.orderId = orderId;
	}
	public BigInteger getUserId() {
		return userId;
	}
	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}
	public String getTerminalKind() {
		return terminalKind;
	}
	public void setTerminalKind(String terminalKind) {
		this.terminalKind = terminalKind;
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
	public String getAmout() {
		return amout;
	}
	public void setAmout(String amout) {
		this.amout = amout;
	}
	public String getPaymentType() {
		return paymentType;
	}
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	public String getPayType() {
		return payType;
	}
	public void setPayType(String payType) {
		this.payType = payType;
	}
	public String getReturnUrl() {
		return returnUrl;
	}
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
	public String getNotifyUrl() {
		return notifyUrl;
	}
	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}
	public String getMd5Info() {
		return md5Info;
	}
	public void setMd5Info(String md5Info) {
		this.md5Info = md5Info;
	}
	public String getMerRemark() {
		return merRemark;
	}
	public void setMerRemark(String merRemark) {
		this.merRemark = merRemark;
	}
	public String getProducts() {
		return products;
	}
	public void setProducts(String products) {
		this.products = products;
	}
	public Integer getOrderType() {
		return orderType;
	}
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());

		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EbuyPrepaySqLog other = (EbuyPrepaySqLog) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		return true;
	}
	
}
