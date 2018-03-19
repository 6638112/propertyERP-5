package com.cnfantasia.server.api.user.entity;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.room.entity.RoomEntityWithValidate;
import com.cnfantasia.server.domainbase.achievement.entity.Achievement;
import com.cnfantasia.server.domainbase.hobby.entity.Hobby;
import com.cnfantasia.server.domainbase.remoteUser.entity.RemoteUser;
/**
 * 描述:() 具体业务实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/**
 * 描述:() 具体业务实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
public class UserEntity extends UserSimpleEntity{
	private static final long serialVersionUID = 1L;
//	private Log logger = LogFactory.getLog(getClass());
	
//	/**用户门牌列表*/
//	private List<RoomEntity> roomEntityList;
//	public List<RoomEntity> getRoomEntityList() {
//		return roomEntityList;
//	}
//	public void setRoomEntityList(List<RoomEntity> roomEntityList) {
//		this.roomEntityList = roomEntityList;
//	}
	
	/**默认房间*/
	private RoomEntityWithValidate defaultRoomEntity;
	@Override
	public BigInteger getDefaultRoomId() {
		if(defaultRoomEntity==null){return null;}
		return defaultRoomEntity.getId();
	}
	@Override
	public void setDefaultRoomId(BigInteger defaultRoomId) {
		if(defaultRoomEntity==null){defaultRoomEntity = new RoomEntityWithValidate();}
		defaultRoomEntity.setId(defaultRoomId);
	}
	public RoomEntityWithValidate getDefaultRoomEntity() {
		return defaultRoomEntity;
	}
	public void setDefaultRoomEntity(RoomEntityWithValidate defaultRoomEntity) {
		this.defaultRoomEntity = defaultRoomEntity;
	}
	/**
	 * 远程用户信息
	 */
	private RemoteUser remoteUser;
	public RemoteUser getRemoteUser() {
		return remoteUser;
	}
	public void setRemoteUser(RemoteUser remoteUser) {
		this.remoteUser = remoteUser;
	}
	/**
	 * 个人成就列表
	 */
	private List<Achievement> achievementList;
	public List<Achievement> getAchievementList() {
		return achievementList;
	}
	public void setAchievementList(List<Achievement> achievementList) {
		this.achievementList = achievementList;
	}
	
	/**
	 * 个人兴趣爱好列表
	 */
	private List<Hobby> hobbyList;
	public List<Hobby> getHobbyList() {
		return hobbyList;
	}
	public void setHobbyList(List<Hobby> hobbyList) {
		this.hobbyList = hobbyList;
	}
	
}
