package com.cnfantasia.server.api.user.entity;

import com.cnfantasia.server.api.room.entity.RoomEntityWithValidate;
/**
 * 描述:(用户门牌关系) 具体业务实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
public class UserHasTRoomEntity extends RoomEntityWithValidate{
	private static final long serialVersionUID = 1L;
	
//	/**房间信息*/
//	private RoomEntityWithValidate roomEntity;
//	public RoomEntityWithValidate getRoomEntity() {
//		return roomEntity;
//	}
//	public void setRoomEntity(RoomEntityWithValidate roomEntity) {
//		this.roomEntity = roomEntity;
//	}
	
//	/**用户信息*/
//	private UserEntity userEntity;
//	public UserEntity getUserEntity() {
//		return userEntity;
//	}
//	public void setUserEntity(UserEntity userEntity) {
//		this.userEntity = userEntity;
//	}
	
	/**真实门牌管理员名称*/
	private String realRoomAdminName;
	public String getRealRoomAdminName() {
		return realRoomAdminName;
	}
	public void setRealRoomAdminName(String realRoomAdminName) {
		this.realRoomAdminName = realRoomAdminName;
	}
	
	
}
