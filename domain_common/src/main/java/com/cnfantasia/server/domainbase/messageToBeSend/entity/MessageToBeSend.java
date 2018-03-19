package com.cnfantasia.server.domainbase.messageToBeSend.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;import java.lang.String;import java.lang.Long;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(待发送短信或待推送消息) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class MessageToBeSend implements Serializable{
*/


public class MessageToBeSend extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 消息类型 */	private Integer msgType;	/** 消息标题 */	private String title;	/** 消息内容 */	private String content;	/** 发送范围 */	private Integer sendRange;	/** 发送时间 */	private String sendTime;	/** 发送状态 */	private Integer sendStatus;	/** 操作人 */	private String operatorUser;	/** 发送信息条数 */	private Long sendCount;	/** 短链接 */	private String shortUrl;	/** 短信白名单 */	private String whiteList;	/** 短信黑名单 */	private String blackList;	/** 跳转目标 */	private String jumpTarget;	/** 商品详情 */	private String ebuyProductDetail;
	public MessageToBeSend(){
	}
	public MessageToBeSend(MessageToBeSend arg){
		this.id = arg.getId();		this.msgType = arg.getMsgType();		this.title = arg.getTitle();		this.content = arg.getContent();		this.sendRange = arg.getSendRange();		this.sendTime = arg.getSendTime();		this.sendStatus = arg.getSendStatus();		this.operatorUser = arg.getOperatorUser();		this.sendCount = arg.getSendCount();		this.shortUrl = arg.getShortUrl();		this.whiteList = arg.getWhiteList();		this.blackList = arg.getBlackList();		this.jumpTarget = arg.getJumpTarget();		this.ebuyProductDetail = arg.getEbuyProductDetail();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param msgType 消息类型	 * @param title 消息标题	 * @param content 消息内容	 * @param sendRange 发送范围	 * @param sendTime 发送时间	 * @param sendStatus 发送状态	 * @param operatorUser 操作人	 * @param sendCount 发送信息条数	 * @param shortUrl 短链接	 * @param whiteList 短信白名单	 * @param blackList 短信黑名单	 * @param jumpTarget 跳转目标	 * @param ebuyProductDetail 商品详情	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public MessageToBeSend(BigInteger id,Integer msgType,String title,String content,Integer sendRange,String sendTime,Integer sendStatus,String operatorUser,Long sendCount,String shortUrl,String whiteList,String blackList,String jumpTarget,String ebuyProductDetail,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.msgType = msgType;		this.title = title;		this.content = content;		this.sendRange = sendRange;		this.sendTime = sendTime;		this.sendStatus = sendStatus;		this.operatorUser = operatorUser;		this.sendCount = sendCount;		this.shortUrl = shortUrl;		this.whiteList = whiteList;		this.blackList = blackList;		this.jumpTarget = jumpTarget;		this.ebuyProductDetail = ebuyProductDetail;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("msgType=").append(msgType).append(";");		sbf.append("title=").append(title).append(";");		sbf.append("content=").append(content).append(";");		sbf.append("sendRange=").append(sendRange).append(";");		sbf.append("sendTime=").append(sendTime).append(";");		sbf.append("sendStatus=").append(sendStatus).append(";");		sbf.append("operatorUser=").append(operatorUser).append(";");		sbf.append("sendCount=").append(sendCount).append(";");		sbf.append("shortUrl=").append(shortUrl).append(";");		sbf.append("whiteList=").append(whiteList).append(";");		sbf.append("blackList=").append(blackList).append(";");		sbf.append("jumpTarget=").append(jumpTarget).append(";");		sbf.append("ebuyProductDetail=").append(ebuyProductDetail).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public Integer getMsgType() {		return msgType;	}	public void setMsgType(Integer msgType) {		this.msgType = msgType;	}	public String getTitle() {		return title;	}	public void setTitle(String title) {		this.title = title;	}	public String getContent() {		return content;	}	public void setContent(String content) {		this.content = content;	}	public Integer getSendRange() {		return sendRange;	}	public void setSendRange(Integer sendRange) {		this.sendRange = sendRange;	}	public String getSendTime() {		return sendTime;	}	public void setSendTime(String sendTime) {		this.sendTime = sendTime;	}	public Integer getSendStatus() {		return sendStatus;	}	public void setSendStatus(Integer sendStatus) {		this.sendStatus = sendStatus;	}	public String getOperatorUser() {		return operatorUser;	}	public void setOperatorUser(String operatorUser) {		this.operatorUser = operatorUser;	}	public Long getSendCount() {		return sendCount;	}	public void setSendCount(Long sendCount) {		this.sendCount = sendCount;	}	public String getShortUrl() {		return shortUrl;	}	public void setShortUrl(String shortUrl) {		this.shortUrl = shortUrl;	}	public String getWhiteList() {		return whiteList;	}	public void setWhiteList(String whiteList) {		this.whiteList = whiteList;	}	public String getBlackList() {		return blackList;	}	public void setBlackList(String blackList) {		this.blackList = blackList;	}	public String getJumpTarget() {		return jumpTarget;	}	public void setJumpTarget(String jumpTarget) {		this.jumpTarget = jumpTarget;	}	public String getEbuyProductDetail() {		return ebuyProductDetail;	}	public void setEbuyProductDetail(String ebuyProductDetail) {		this.ebuyProductDetail = ebuyProductDetail;	}
	
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
		MessageToBeSend other = (MessageToBeSend) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
