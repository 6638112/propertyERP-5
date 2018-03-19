package com.cnfantasia.server.domainbase.ebuyDeliveryPushRecorder.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(配送单推送记录) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class EbuyDeliveryPushRecorder implements Serializable{
*/


public class EbuyDeliveryPushRecorder extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 推送内容 */	private String pushContent;	/** 推送返回结果 */	private String pushResult;	/** 推送返回的依谷网订单号(状态码为0时存在) */	private String pushEguorderNo;	/** 是否推送成功 */	private Integer ispushSuccess;	/** 供应商ID */	private BigInteger tEbuySupplyMerchantFId;	/** 配送单ID */	private BigInteger tEbuyDeliveryOrderFId;
	public EbuyDeliveryPushRecorder(){
	}
	public EbuyDeliveryPushRecorder(EbuyDeliveryPushRecorder arg){
		this.id = arg.getId();		this.pushContent = arg.getPushContent();		this.pushResult = arg.getPushResult();		this.pushEguorderNo = arg.getPushEguorderNo();		this.ispushSuccess = arg.getIspushSuccess();		this.tEbuySupplyMerchantFId = arg.gettEbuySupplyMerchantFId();		this.tEbuyDeliveryOrderFId = arg.gettEbuyDeliveryOrderFId();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param pushContent 推送内容	 * @param pushResult 推送返回结果	 * @param pushEguorderNo 推送返回的依谷网订单号(状态码为0时存在)	 * @param ispushSuccess 是否推送成功	 * @param tEbuySupplyMerchantFId 供应商ID	 * @param tEbuyDeliveryOrderFId 配送单ID	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public EbuyDeliveryPushRecorder(BigInteger id,String pushContent,String pushResult,String pushEguorderNo,Integer ispushSuccess,BigInteger tEbuySupplyMerchantFId,BigInteger tEbuyDeliveryOrderFId,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.pushContent = pushContent;		this.pushResult = pushResult;		this.pushEguorderNo = pushEguorderNo;		this.ispushSuccess = ispushSuccess;		this.tEbuySupplyMerchantFId = tEbuySupplyMerchantFId;		this.tEbuyDeliveryOrderFId = tEbuyDeliveryOrderFId;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("pushContent=").append(pushContent).append(";");		sbf.append("pushResult=").append(pushResult).append(";");		sbf.append("pushEguorderNo=").append(pushEguorderNo).append(";");		sbf.append("ispushSuccess=").append(ispushSuccess).append(";");		sbf.append("tEbuySupplyMerchantFId=").append(tEbuySupplyMerchantFId).append(";");		sbf.append("tEbuyDeliveryOrderFId=").append(tEbuyDeliveryOrderFId).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getPushContent() {		return pushContent;	}	public void setPushContent(String pushContent) {		this.pushContent = pushContent;	}	public String getPushResult() {		return pushResult;	}	public void setPushResult(String pushResult) {		this.pushResult = pushResult;	}	public String getPushEguorderNo() {		return pushEguorderNo;	}	public void setPushEguorderNo(String pushEguorderNo) {		this.pushEguorderNo = pushEguorderNo;	}	public Integer getIspushSuccess() {		return ispushSuccess;	}	public void setIspushSuccess(Integer ispushSuccess) {		this.ispushSuccess = ispushSuccess;	}	public BigInteger gettEbuySupplyMerchantFId() {		return tEbuySupplyMerchantFId;	}	public void settEbuySupplyMerchantFId(BigInteger tEbuySupplyMerchantFId) {		this.tEbuySupplyMerchantFId = tEbuySupplyMerchantFId;	}	public BigInteger gettEbuyDeliveryOrderFId() {		return tEbuyDeliveryOrderFId;	}	public void settEbuyDeliveryOrderFId(BigInteger tEbuyDeliveryOrderFId) {		this.tEbuyDeliveryOrderFId = tEbuyDeliveryOrderFId;	}
	
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
		EbuyDeliveryPushRecorder other = (EbuyDeliveryPushRecorder) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
