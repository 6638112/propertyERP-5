package com.cnfantasia.server.domainbase.userHasTAchievement.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(用户成就关系表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class UserHasTAchievement implements Serializable{
*/


public class UserHasTAchievement extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/**  */	private BigInteger id;	/**  */	private BigInteger tUserFId;	/**  */	private BigInteger tAchievementFId;	/**  */	private BigInteger roomId;	/** 成就信息跟门牌相关 */	private Integer isAboutRoom;
	public UserHasTAchievement(){
	}
	public UserHasTAchievement(UserHasTAchievement arg){
		this.id = arg.getId();		this.tUserFId = arg.gettUserFId();		this.tAchievementFId = arg.gettAchievementFId();		this.roomId = arg.getRoomId();		this.isAboutRoom = arg.getIsAboutRoom();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id 	 * @param tUserFId 	 * @param tAchievementFId 	 * @param roomId 	 * @param isAboutRoom 成就信息跟门牌相关	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public UserHasTAchievement(BigInteger id,BigInteger tUserFId,BigInteger tAchievementFId,BigInteger roomId,Integer isAboutRoom,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.tUserFId = tUserFId;		this.tAchievementFId = tAchievementFId;		this.roomId = roomId;		this.isAboutRoom = isAboutRoom;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("tUserFId=").append(tUserFId).append(";");		sbf.append("tAchievementFId=").append(tAchievementFId).append(";");		sbf.append("roomId=").append(roomId).append(";");		sbf.append("isAboutRoom=").append(isAboutRoom).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger gettUserFId() {		return tUserFId;	}	public void settUserFId(BigInteger tUserFId) {		this.tUserFId = tUserFId;	}	public BigInteger gettAchievementFId() {		return tAchievementFId;	}	public void settAchievementFId(BigInteger tAchievementFId) {		this.tAchievementFId = tAchievementFId;	}	public BigInteger getRoomId() {		return roomId;	}	public void setRoomId(BigInteger roomId) {		this.roomId = roomId;	}	public Integer getIsAboutRoom() {		return isAboutRoom;	}	public void setIsAboutRoom(Integer isAboutRoom) {		this.isAboutRoom = isAboutRoom;	}
	
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
		UserHasTAchievement other = (UserHasTAchievement) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
