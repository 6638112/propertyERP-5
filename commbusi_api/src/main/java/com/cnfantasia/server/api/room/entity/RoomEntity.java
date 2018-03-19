package com.cnfantasia.server.api.room.entity;

import java.math.BigInteger;

import com.cnfantasia.server.api.room.util.RoomStatusCheckUtil;
import com.cnfantasia.server.domainbase.room.entity.Room;
/**
 * 描述:(门牌信息) 具体业务实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
/**
 * 描述:(门牌信息) 具体业务实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
public class RoomEntity extends Room{
	private static final long serialVersionUID = 1L;
	/**所属真实房间信息*/
	private RealRoomEntity realRoomEntity;
	
	public RealRoomEntity getRealRoomEntity() {
		return realRoomEntity;
	}
	public void setRealRoomEntity(RealRoomEntity realRoomEntity) {
		this.realRoomEntity = realRoomEntity;
	}
	
	@Override
	public BigInteger gettRealRoomFId() {
		if(realRoomEntity ==null){return null;}
		return realRoomEntity.getId();
	}
	@Override
	public void settRealRoomFId(BigInteger tRealRoomFId) {
		if(realRoomEntity ==null){
			realRoomEntity = new RealRoomEntity();
		}
		realRoomEntity.setId(tRealRoomFId);
	}
	
	/**
	 * 判断真实门牌是否是默认的空门牌
	 * @return
	 */
	public boolean checkIsEmptyRoom(){
		return RoomStatusCheckUtil.checkIsRealRoomEmpty(this.gettRealRoomFId());
	}
	
//	/**房间管理员Ids*/
//	private List<BigInteger> adminIdsList;
//
//	public List<BigInteger> getAdminIdsList() {
//		return adminIdsList;
//	}
//	public void setAdminIdsList(List<BigInteger> adminIdsList) {
//		this.adminIdsList = adminIdsList;
//	}
//	/**
//	 * 判断房间管理员是否包含指定userId
//	 * @param userId
//	 * @return
//	 */
//	public boolean containsAdmin(BigInteger userId){
//		if(adminIdsList == null){return false;}
//		return adminIdsList.contains(userId);
//	}
//	public static void main(String[] args) {
//		BigInteger bi01 = new BigInteger("12");
//		BigInteger bi02 = new BigInteger("12");
//		List<BigInteger> adminIdsList = new ArrayList<BigInteger>();
//		adminIdsList.add(bi01);
//		System.out.println(adminIdsList.contains(bi02));
//		System.out.println(adminIdsList.contains(bi01));
//	}
	
//	public boolean checkIsFirstHbconvertState(){
//		if(this.getFirstHbconvertState()!=null&&RoomDict.FirstHbconvertState.YES.compareTo(this.getFirstHbconvertState())==0){
//			return true;
//		}else{
//			return false;
//		}
//	}
	
}
