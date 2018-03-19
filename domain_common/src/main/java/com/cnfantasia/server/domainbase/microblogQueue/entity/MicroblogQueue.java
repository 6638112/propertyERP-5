package com.cnfantasia.server.domainbase.microblogQueue.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.String;import java.lang.Integer;import java.lang.Long;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(小区博客队列表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class MicroblogQueue implements Serializable{
*/


public class MicroblogQueue extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/** 消息内容 */	private String text;	/** 消息来源 */	private Integer sourceType;	/** 是否定时消息 */	private Integer isTiming;	/** 归属小区 */	private BigInteger groupBuildingId;	/** 消息创建人 */	private BigInteger userId;	/** 消息有效时间 */	private String validTime;	/** 推送状态 */	private Integer pushStatus;	/** 是否可被推送到微博表 */	private Integer pushAble;	/** 推送的消息类别编码 */	private Long levelCode;	/** 服务范围Id */	private BigInteger saId;	/** 创建时间 */	private String createTime;	/** 链接详情数据 */	private String linkDetailJson;
	public MicroblogQueue(){
	}
	public MicroblogQueue(MicroblogQueue arg){
		this.id = arg.getId();		this.text = arg.getText();		this.sourceType = arg.getSourceType();		this.isTiming = arg.getIsTiming();		this.groupBuildingId = arg.getGroupBuildingId();		this.userId = arg.getUserId();		this.validTime = arg.getValidTime();		this.pushStatus = arg.getPushStatus();		this.pushAble = arg.getPushAble();		this.levelCode = arg.getLevelCode();		this.saId = arg.getSaId();		this.createTime = arg.getCreateTime();		this.linkDetailJson = arg.getLinkDetailJson();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param text 消息内容	 * @param sourceType 消息来源	 * @param isTiming 是否定时消息	 * @param groupBuildingId 归属小区	 * @param userId 消息创建人	 * @param validTime 消息有效时间	 * @param pushStatus 推送状态	 * @param pushAble 是否可被推送到微博表	 * @param levelCode 推送的消息类别编码	 * @param saId 服务范围Id	 * @param createTime 创建时间	 * @param linkDetailJson 链接详情数据	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public MicroblogQueue(BigInteger id,String text,Integer sourceType,Integer isTiming,BigInteger groupBuildingId,BigInteger userId,String validTime,Integer pushStatus,Integer pushAble,Long levelCode,BigInteger saId,String createTime,String linkDetailJson,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.text = text;		this.sourceType = sourceType;		this.isTiming = isTiming;		this.groupBuildingId = groupBuildingId;		this.userId = userId;		this.validTime = validTime;		this.pushStatus = pushStatus;		this.pushAble = pushAble;		this.levelCode = levelCode;		this.saId = saId;		this.createTime = createTime;		this.linkDetailJson = linkDetailJson;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("text=").append(text).append(";");		sbf.append("sourceType=").append(sourceType).append(";");		sbf.append("isTiming=").append(isTiming).append(";");		sbf.append("groupBuildingId=").append(groupBuildingId).append(";");		sbf.append("userId=").append(userId).append(";");		sbf.append("validTime=").append(validTime).append(";");		sbf.append("pushStatus=").append(pushStatus).append(";");		sbf.append("pushAble=").append(pushAble).append(";");		sbf.append("levelCode=").append(levelCode).append(";");		sbf.append("saId=").append(saId).append(";");		sbf.append("createTime=").append(createTime).append(";");		sbf.append("linkDetailJson=").append(linkDetailJson).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public String getText() {		return text;	}	public void setText(String text) {		this.text = text;	}	public Integer getSourceType() {		return sourceType;	}	public void setSourceType(Integer sourceType) {		this.sourceType = sourceType;	}	public Integer getIsTiming() {		return isTiming;	}	public void setIsTiming(Integer isTiming) {		this.isTiming = isTiming;	}	public BigInteger getGroupBuildingId() {		return groupBuildingId;	}	public void setGroupBuildingId(BigInteger groupBuildingId) {		this.groupBuildingId = groupBuildingId;	}	public BigInteger getUserId() {		return userId;	}	public void setUserId(BigInteger userId) {		this.userId = userId;	}	public String getValidTime() {		return validTime;	}	public void setValidTime(String validTime) {		this.validTime = validTime;	}	public Integer getPushStatus() {		return pushStatus;	}	public void setPushStatus(Integer pushStatus) {		this.pushStatus = pushStatus;	}	public Integer getPushAble() {		return pushAble;	}	public void setPushAble(Integer pushAble) {		this.pushAble = pushAble;	}	public Long getLevelCode() {		return levelCode;	}	public void setLevelCode(Long levelCode) {		this.levelCode = levelCode;	}	public BigInteger getSaId() {		return saId;	}	public void setSaId(BigInteger saId) {		this.saId = saId;	}	public String getCreateTime() {		return createTime;	}	public void setCreateTime(String createTime) {		this.createTime = createTime;	}	public String getLinkDetailJson() {		return linkDetailJson;	}	public void setLinkDetailJson(String linkDetailJson) {		this.linkDetailJson = linkDetailJson;	}
	
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
		MicroblogQueue other = (MicroblogQueue) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
