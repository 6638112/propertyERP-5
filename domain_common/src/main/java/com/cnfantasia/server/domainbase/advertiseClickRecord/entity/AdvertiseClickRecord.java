package com.cnfantasia.server.domainbase.advertiseClickRecord.entity;

/* import java.io.Serializable;*/
import java.math.BigInteger;import java.lang.Integer;import java.lang.String;
 import com.cnfantasia.server.domain.pub.BaseEntity;
/**
 * 描述:(广告点击表) 实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/*
public class AdvertiseClickRecord implements Serializable{
*/


public class AdvertiseClickRecord extends BaseEntity{

	private static final long serialVersionUID = 1L;
	/** id */	private BigInteger id;	/** 广告ID */	private BigInteger advertiseId;	/** 用户ID */	private BigInteger userId;	/** 点击时间 */	private String clickDate;	/** 位置，1首页，2街坊，3社区店到家 */	private Integer positionType;	/** 点击时的房间号 */	private BigInteger roomId;	/** 广告标题 */	private String advTitle;	/** 广告链接 */	private String advLink;
	public AdvertiseClickRecord(){
	}
	public AdvertiseClickRecord(AdvertiseClickRecord arg){
		this.id = arg.getId();		this.advertiseId = arg.getAdvertiseId();		this.userId = arg.getUserId();		this.clickDate = arg.getClickDate();		this.positionType = arg.getPositionType();		this.roomId = arg.getRoomId();		this.advTitle = arg.getAdvTitle();		this.advLink = arg.getAdvLink();		this.sys0AddTime = arg.getSys0AddTime();		this.sys0UpdTime = arg.getSys0UpdTime();		this.sys0DelTime = arg.getSys0DelTime();		this.sys0AddUser = arg.getSys0AddUser();		this.sys0UpdUser = arg.getSys0UpdUser();		this.sys0DelUser = arg.getSys0DelUser();		this.sys0DelState = arg.getSys0DelState();
	}
	/**	 * 	 * @param id id	 * @param advertiseId 广告ID	 * @param userId 用户ID	 * @param clickDate 点击时间	 * @param positionType 位置，1首页，2街坊，3社区店到家	 * @param roomId 点击时的房间号	 * @param advTitle 广告标题	 * @param advLink 广告链接	 * @param sys0AddTime 新增时间	 * @param sys0UpdTime 更新时间	 * @param sys0DelTime 删除时间	 * @param sys0AddUser 新增者	 * @param sys0UpdUser 修改者	 * @param sys0DelUser 删除者	 * @param sys0DelState 记录状态	 */
	public AdvertiseClickRecord(BigInteger id,BigInteger advertiseId,BigInteger userId,String clickDate,Integer positionType,BigInteger roomId,String advTitle,String advLink,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;		this.advertiseId = advertiseId;		this.userId = userId;		this.clickDate = clickDate;		this.positionType = positionType;		this.roomId = roomId;		this.advTitle = advTitle;		this.advLink = advLink;		this.sys0AddTime = sys0AddTime;		this.sys0UpdTime = sys0UpdTime;		this.sys0DelTime = sys0DelTime;		this.sys0AddUser = sys0AddUser;		this.sys0UpdUser = sys0UpdUser;		this.sys0DelUser = sys0DelUser;		this.sys0DelState = sys0DelState;
	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();		sbf.append("id=").append(id).append(";");		sbf.append("advertiseId=").append(advertiseId).append(";");		sbf.append("userId=").append(userId).append(";");		sbf.append("clickDate=").append(clickDate).append(";");		sbf.append("positionType=").append(positionType).append(";");		sbf.append("roomId=").append(roomId).append(";");		sbf.append("advTitle=").append(advTitle).append(";");		sbf.append("advLink=").append(advLink).append(";");		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");		sbf.append("sys0DelState=").append(sys0DelState).append(";");		return sbf.toString();
	}
	
	public BigInteger getId() {		return id;	}	public void setId(BigInteger id) {		this.id = id;	}	public BigInteger getAdvertiseId() {		return advertiseId;	}	public void setAdvertiseId(BigInteger advertiseId) {		this.advertiseId = advertiseId;	}	public BigInteger getUserId() {		return userId;	}	public void setUserId(BigInteger userId) {		this.userId = userId;	}	public String getClickDate() {		return clickDate;	}	public void setClickDate(String clickDate) {		this.clickDate = clickDate;	}	public Integer getPositionType() {		return positionType;	}	public void setPositionType(Integer positionType) {		this.positionType = positionType;	}	public BigInteger getRoomId() {		return roomId;	}	public void setRoomId(BigInteger roomId) {		this.roomId = roomId;	}	public String getAdvTitle() {		return advTitle;	}	public void setAdvTitle(String advTitle) {		this.advTitle = advTitle;	}	public String getAdvLink() {		return advLink;	}	public void setAdvLink(String advLink) {		this.advLink = advLink;	}
	
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
		AdvertiseClickRecord other = (AdvertiseClickRecord) obj;
		if (id == null) {			if (other.id != null)				return false;		} else if (!id.equals(other.id))			return false;
		return true;
	}
	
}
