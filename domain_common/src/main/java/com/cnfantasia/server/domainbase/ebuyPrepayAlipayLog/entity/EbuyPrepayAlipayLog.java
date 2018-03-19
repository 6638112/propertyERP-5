package com.cnfantasia.server.domainbase.ebuyPrepayAlipayLog.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(淘宝预支付日志表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class EbuyPrepayAlipayLog implements Serializable{
*/


public class EbuyPrepayAlipayLog extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/**  */	private BigInteger orderId;	/**  */	private BigInteger userId;	/**  */	private String notifyUrl;	/** 是否为调用银行卡支付 */	private Integer isExpressGateway;	/** 商家对外唯一订单号 */	private String outTradeNo;	/** 待支付金额 */	private String amount;	/** 产品信息 */	private String productInfo;	/** 产品详细息 */	private String productDetail;	/** 预支付的参数信息 */	private String payLinkMap;	/** 预支付的参数信息转String后 */	private String payLinkStr;	/** 错误信息 */	private String errorInfo;
	public EbuyPrepayAlipayLog(){
	}
	/**	 * 	 * @param id 	 * @param orderId 	 * @param userId 	 * @param notifyUrl 	 * @param isExpressGateway 是否为调用银行卡支付	 * @param outTradeNo 商家对外唯一订单号	 * @param amount 待支付金额	 * @param productInfo 产品信息	 * @param productDetail 产品详细息	 * @param payLinkMap 预支付的参数信息	 * @param payLinkStr 预支付的参数信息转String后	 * @param errorInfo 错误信息	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public EbuyPrepayAlipayLog(BigInteger id,BigInteger orderId,BigInteger userId,String notifyUrl,Integer isExpressGateway,String outTradeNo,String amount,String productInfo,String productDetail,String payLinkMap,String payLinkStr,String errorInfo,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.orderId = orderId;		this.userId = userId;		this.notifyUrl = notifyUrl;		this.isExpressGateway = isExpressGateway;		this.outTradeNo = outTradeNo;		this.amount = amount;		this.productInfo = productInfo;		this.productDetail = productDetail;		this.payLinkMap = payLinkMap;		this.payLinkStr = payLinkStr;		this.errorInfo = errorInfo;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("orderId=").append(orderId).append(";");		sbf.append("userId=").append(userId).append(";");		sbf.append("notifyUrl=").append(notifyUrl).append(";");		sbf.append("isExpressGateway=").append(isExpressGateway).append(";");		sbf.append("outTradeNo=").append(outTradeNo).append(";");		sbf.append("amount=").append(amount).append(";");		sbf.append("productInfo=").append(productInfo).append(";");		sbf.append("productDetail=").append(productDetail).append(";");		sbf.append("payLinkMap=").append(payLinkMap).append(";");		sbf.append("payLinkStr=").append(payLinkStr).append(";");		sbf.append("errorInfo=").append(errorInfo).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger getOrderId() {		return orderId;	}	public void setOrderId(BigInteger orderId) {		this.orderId = orderId;	}	public BigInteger getUserId() {		return userId;	}	public void setUserId(BigInteger userId) {		this.userId = userId;	}	public String getNotifyUrl() {		return notifyUrl;	}	public void setNotifyUrl(String notifyUrl) {		this.notifyUrl = notifyUrl;	}	public Integer getIsExpressGateway() {		return isExpressGateway;	}	public void setIsExpressGateway(Integer isExpressGateway) {		this.isExpressGateway = isExpressGateway;	}	public String getOutTradeNo() {		return outTradeNo;	}	public void setOutTradeNo(String outTradeNo) {		this.outTradeNo = outTradeNo;	}	public String getAmount() {		return amount;	}	public void setAmount(String amount) {		this.amount = amount;	}	public String getProductInfo() {		return productInfo;	}	public void setProductInfo(String productInfo) {		this.productInfo = productInfo;	}	public String getProductDetail() {		return productDetail;	}	public void setProductDetail(String productDetail) {		this.productDetail = productDetail;	}	public String getPayLinkMap() {		return payLinkMap;	}	public void setPayLinkMap(String payLinkMap) {		this.payLinkMap = payLinkMap;	}	public String getPayLinkStr() {		return payLinkStr;	}	public void setPayLinkStr(String payLinkStr) {		this.payLinkStr = payLinkStr;	}	public String getErrorInfo() {		return errorInfo;	}	public void setErrorInfo(String errorInfo) {		this.errorInfo = errorInfo;	}
	
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
		EbuyPrepayAlipayLog other = (EbuyPrepayAlipayLog) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
