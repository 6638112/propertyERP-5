package com.cnfantasia.server.domainbase.ebuyPrepayWeixinLog.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(微信预支付日志表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class EbuyPrepayWeixinLog implements Serializable{
*/


public class EbuyPrepayWeixinLog extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 订单Id */	private BigInteger orderId;	/** 用户Id */	private BigInteger userId;	/**  */	private String accessToken;	/** 通知的url */	private String notifyUrl;	/** package订单参数 */	private String packageParams;	/** package包 */	private String packageValue;	/**  */	private String prepayId;	/** prepayid支付参数 */	private String prepayIdParams;	/** 返回给客户端的参数 */	private String clientBackParams;	/** 返回码 */	private String retcode;	/** 打印debug信息 */	private String debugInfo;	/** 错误信息 */	private String errInfo;
	public EbuyPrepayWeixinLog(){
	}
	/**	 * 	 * @param id 	 * @param orderId 订单Id	 * @param userId 用户Id	 * @param accessToken 	 * @param notifyUrl 通知的url	 * @param packageParams package订单参数	 * @param packageValue package包	 * @param prepayId 	 * @param prepayIdParams prepayid支付参数	 * @param clientBackParams 返回给客户端的参数	 * @param retcode 返回码	 * @param debugInfo 打印debug信息	 * @param errInfo 错误信息	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public EbuyPrepayWeixinLog(BigInteger id,BigInteger orderId,BigInteger userId,String accessToken,String notifyUrl,String packageParams,String packageValue,String prepayId,String prepayIdParams,String clientBackParams,String retcode,String debugInfo,String errInfo,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.orderId = orderId;		this.userId = userId;		this.accessToken = accessToken;		this.notifyUrl = notifyUrl;		this.packageParams = packageParams;		this.packageValue = packageValue;		this.prepayId = prepayId;		this.prepayIdParams = prepayIdParams;		this.clientBackParams = clientBackParams;		this.retcode = retcode;		this.debugInfo = debugInfo;		this.errInfo = errInfo;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("orderId=").append(orderId).append(";");		sbf.append("userId=").append(userId).append(";");		sbf.append("accessToken=").append(accessToken).append(";");		sbf.append("notifyUrl=").append(notifyUrl).append(";");		sbf.append("packageParams=").append(packageParams).append(";");		sbf.append("packageValue=").append(packageValue).append(";");		sbf.append("prepayId=").append(prepayId).append(";");		sbf.append("prepayIdParams=").append(prepayIdParams).append(";");		sbf.append("clientBackParams=").append(clientBackParams).append(";");		sbf.append("retcode=").append(retcode).append(";");		sbf.append("debugInfo=").append(debugInfo).append(";");		sbf.append("errInfo=").append(errInfo).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger getOrderId() {		return orderId;	}	public void setOrderId(BigInteger orderId) {		this.orderId = orderId;	}	public BigInteger getUserId() {		return userId;	}	public void setUserId(BigInteger userId) {		this.userId = userId;	}	public String getAccessToken() {		return accessToken;	}	public void setAccessToken(String accessToken) {		this.accessToken = accessToken;	}	public String getNotifyUrl() {		return notifyUrl;	}	public void setNotifyUrl(String notifyUrl) {		this.notifyUrl = notifyUrl;	}	public String getPackageParams() {		return packageParams;	}	public void setPackageParams(String packageParams) {		this.packageParams = packageParams;	}	public String getPackageValue() {		return packageValue;	}	public void setPackageValue(String packageValue) {		this.packageValue = packageValue;	}	public String getPrepayId() {		return prepayId;	}	public void setPrepayId(String prepayId) {		this.prepayId = prepayId;	}	public String getPrepayIdParams() {		return prepayIdParams;	}	public void setPrepayIdParams(String prepayIdParams) {		this.prepayIdParams = prepayIdParams;	}	public String getClientBackParams() {		return clientBackParams;	}	public void setClientBackParams(String clientBackParams) {		this.clientBackParams = clientBackParams;	}	public String getRetcode() {		return retcode;	}	public void setRetcode(String retcode) {		this.retcode = retcode;	}	public String getDebugInfo() {		return debugInfo;	}	public void setDebugInfo(String debugInfo) {		this.debugInfo = debugInfo;	}	public String getErrInfo() {		return errInfo;	}	public void setErrInfo(String errInfo) {		this.errInfo = errInfo;	}
	
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
		EbuyPrepayWeixinLog other = (EbuyPrepayWeixinLog) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
