package com.cnfantasia.server.domainbase.wechatDredgebillMq.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;import java.lang.String;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(微信公众号维修单消息队列) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class WechatDredgebillMq implements Serializable{
*/


public class WechatDredgebillMq extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 维修单的id */	private BigInteger tDredgeBillFId;	/** 消息类型 */	private Integer type;	/** 消息发送状态 */	private Integer sendStatus;	/** 推送结果串 */	private String sendRespone;
	public WechatDredgebillMq(){
	}
	public WechatDredgebillMq(WechatDredgebillMq arg){
		this.id = arg.getId();		this.tDredgeBillFId = arg.gettDredgeBillFId();		this.type = arg.getType();		this.sendStatus = arg.getSendStatus();		this.sendRespone = arg.getSendRespone();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tDredgeBillFId 维修单的id	 * @param type 消息类型	 * @param sendStatus 消息发送状态	 * @param sendRespone 推送结果串	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public WechatDredgebillMq(BigInteger id,BigInteger tDredgeBillFId,Integer type,Integer sendStatus,String sendRespone,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tDredgeBillFId = tDredgeBillFId;		this.type = type;		this.sendStatus = sendStatus;		this.sendRespone = sendRespone;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tDredgeBillFId=").append(tDredgeBillFId).append(";");		sbf.append("type=").append(type).append(";");		sbf.append("sendStatus=").append(sendStatus).append(";");		sbf.append("sendRespone=").append(sendRespone).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettDredgeBillFId() {		return tDredgeBillFId;	}	public void settDredgeBillFId(BigInteger tDredgeBillFId) {		this.tDredgeBillFId = tDredgeBillFId;	}	public Integer getType() {		return type;	}	public void setType(Integer type) {		this.type = type;	}	public Integer getSendStatus() {		return sendStatus;	}	public void setSendStatus(Integer sendStatus) {		this.sendStatus = sendStatus;	}	public String getSendRespone() {		return sendRespone;	}	public void setSendRespone(String sendRespone) {		this.sendRespone = sendRespone;	}
	
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
		WechatDredgebillMq other = (WechatDredgebillMq) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
