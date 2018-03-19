package com.cnfantasia.server.domainbase.message.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;
import java.lang.Long;
import java.lang.Integer;
import java.lang.String;

 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(消息表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class Message implements Serializable{
*/


public class Message extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/** 消息编号 */
	private BigInteger id;
	/** 消息类型 */
	private Long type;
	/** 是否和门牌有关（1 是 0 不是） */
	private Integer isRelateRoom;
	/** 标题 */
	private String title;
	/** 消息内容 */
	private String content;
	/** 发布时间 */
	private String time;
	/** 有效开始时间 */
	private String expiryDateStart;
	/** 有效截止时间 */
	private String expiryDateEnd;
	/** 创建人 */
	private BigInteger creater;
	/** 消息来源ID，跟type字段共同确定 */
	private BigInteger sourceId;
	/** 图片路径 */
	private String picUrl;
	/**  */
	private BigInteger msggroupFid;
	/** 安卓跳转页面 */
	private String androidTargetView;
	/** IOS跳转页面 */
	private String iosTargetView;
	/** 可见范围标识 */
	private Integer visibleType;
	/** 物业公告发送状态 */
	private Integer sendState;

	public Message(){
	}
	public Message(Message arg){
		this.id = arg.getId();
		this.type = arg.getType();
		this.isRelateRoom = arg.getIsRelateRoom();
		this.title = arg.getTitle();
		this.content = arg.getContent();
		this.time = arg.getTime();
		this.expiryDateStart = arg.getExpiryDateStart();
		this.expiryDateEnd = arg.getExpiryDateEnd();
		this.creater = arg.getCreater();
		this.sourceId = arg.getSourceId();
		this.picUrl = arg.getPicUrl();
		this.msggroupFid = arg.getMsggroupFid();
		this.androidTargetView = arg.getAndroidTargetView();
		this.iosTargetView = arg.getIosTargetView();
		this.sys0AddTime = arg.getSys0AddTime();
		this.sys0UpdTime = arg.getSys0UpdTime();
		this.sys0DelTime = arg.getSys0DelTime();
		this.sys0AddUser = arg.getSys0AddUser();
		this.sys0UpdUser = arg.getSys0UpdUser();
		this.sys0DelUser = arg.getSys0DelUser();
		this.sys0DelState = arg.getSys0DelState();
		this.visibleType = arg.getVisibleType();
		this.sendState = arg.getSendState();

	}
	/**
	 * 
	 * @param id 消息编号
	 * @param type 消息类型
	 * @param isRelateRoom 是否和门牌有关（1 是 0 不是）
	 * @param title 标题
	 * @param content 消息内容
	 * @param time 发布时间
	 * @param expiryDateStart 有效开始时间
	 * @param expiryDateEnd 有效截止时间
	 * @param creater 创建人
	 * @param sourceId 消息来源ID，跟type字段共同确定
	 * @param picUrl 图片路径
	 * @param msggroupFid 
	 * @param androidTargetView 安卓跳转页面
	 * @param iosTargetView IOS跳转页面
	 * @param sys0AddTime 新增时间
	 * @param sys0UpdTime 更新时间
	 * @param sys0DelTime 删除时间
	 * @param sys0AddUser 新增者
	 * @param sys0UpdUser 修改者
	 * @param sys0DelUser 删除者
	 * @param sys0DelState 记录状态
	 * @param visibleType 可见范围标识
	 * @param sendState 物业公告发送状态
	 */

	public Message(BigInteger id,Long type,Integer isRelateRoom,String title,String content,String time,String expiryDateStart,String expiryDateEnd,BigInteger creater,BigInteger sourceId,String picUrl,BigInteger msggroupFid,String androidTargetView,String iosTargetView,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState,Integer visibleType,Integer sendState){
		this.id = id;
		this.type = type;
		this.isRelateRoom = isRelateRoom;
		this.title = title;
		this.content = content;
		this.time = time;
		this.expiryDateStart = expiryDateStart;
		this.expiryDateEnd = expiryDateEnd;
		this.creater = creater;
		this.sourceId = sourceId;
		this.picUrl = picUrl;
		this.msggroupFid = msggroupFid;
		this.androidTargetView = androidTargetView;
		this.iosTargetView = iosTargetView;
		this.sys0AddTime = sys0AddTime;
		this.sys0UpdTime = sys0UpdTime;
		this.sys0DelTime = sys0DelTime;
		this.sys0AddUser = sys0AddUser;
		this.sys0UpdUser = sys0UpdUser;
		this.sys0DelUser = sys0DelUser;
		this.sys0DelState = sys0DelState;
		this.visibleType = visibleType;
		this.sendState = sendState;

	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("id=").append(id).append(";");
		sbf.append("type=").append(type).append(";");
		sbf.append("isRelateRoom=").append(isRelateRoom).append(";");
		sbf.append("title=").append(title).append(";");
		sbf.append("content=").append(content).append(";");
		sbf.append("time=").append(time).append(";");
		sbf.append("expiryDateStart=").append(expiryDateStart).append(";");
		sbf.append("expiryDateEnd=").append(expiryDateEnd).append(";");
		sbf.append("creater=").append(creater).append(";");
		sbf.append("sourceId=").append(sourceId).append(";");
		sbf.append("picUrl=").append(picUrl).append(";");
		sbf.append("msggroupFid=").append(msggroupFid).append(";");
		sbf.append("androidTargetView=").append(androidTargetView).append(";");
		sbf.append("iosTargetView=").append(iosTargetView).append(";");
		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");
		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");
		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");
		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");
		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");
		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");
		sbf.append("sys0DelState=").append(sys0DelState).append(";");
		sbf.append("visibleType=").append(visibleType).append(";");
		sbf.append("sendState=").append(sendState).append(";");
		return sbf.toString();

	}
	
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public Long getType() {
		return type;
	}
	public void setType(Long type) {
		this.type = type;
	}
	public Integer getIsRelateRoom() {
		return isRelateRoom;
	}
	public void setIsRelateRoom(Integer isRelateRoom) {
		this.isRelateRoom = isRelateRoom;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getExpiryDateStart() {
		return expiryDateStart;
	}
	public void setExpiryDateStart(String expiryDateStart) {
		this.expiryDateStart = expiryDateStart;
	}
	public String getExpiryDateEnd() {
		return expiryDateEnd;
	}
	public void setExpiryDateEnd(String expiryDateEnd) {
		this.expiryDateEnd = expiryDateEnd;
	}
	public BigInteger getCreater() {
		return creater;
	}
	public void setCreater(BigInteger creater) {
		this.creater = creater;
	}
	public BigInteger getSourceId() {
		return sourceId;
	}
	public void setSourceId(BigInteger sourceId) {
		this.sourceId = sourceId;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public BigInteger getMsggroupFid() {
		return msggroupFid;
	}
	public void setMsggroupFid(BigInteger msggroupFid) {
		this.msggroupFid = msggroupFid;
	}
	public String getAndroidTargetView() {
		return androidTargetView;
	}
	public void setAndroidTargetView(String androidTargetView) {
		this.androidTargetView = androidTargetView;
	}
	public String getIosTargetView() {
		return iosTargetView;
	}
	public void setIosTargetView(String iosTargetView) {
		this.iosTargetView = iosTargetView;
	}
	public Integer getVisibleType() {
		return visibleType;
	}
	public void setVisibleType(Integer visibleType) {
		this.visibleType = visibleType;
	}
	public Integer getSendState() {
		return sendState;
	}
	public void setSendState(Integer sendState) {
		this.sendState = sendState;
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
		Message other = (Message) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		return true;
	}
	
}
