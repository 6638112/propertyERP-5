/**   
* Filename:    CommonRoomUtil.java   
* @version:    1.0  
* Create at:   2014年5月31日 上午5:49:26   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月31日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.util;

import java.math.BigInteger;
import java.util.Map;

import com.cnfantasia.server.api.room.constant.RoomDict;
import com.cnfantasia.server.api.room.entity.BuildingEntity;
import com.cnfantasia.server.api.room.entity.GroupBuildingEntity;
import com.cnfantasia.server.api.room.entity.RealRoomEntity;
import com.cnfantasia.server.api.room.entity.RoomBaseInfo;
import com.cnfantasia.server.api.room.entity.RoomEntity;
import com.cnfantasia.server.api.room.util.RoomEntityConvertUtil;
import com.cnfantasia.server.business.pub.utils.MapConverter;
import com.cnfantasia.server.domainbase.roomValidate.entity.RoomValidate;

/**
 * Filename:    CommonRoomUtil.java
 * @version:    1.0.0
 * Create at:   2014年5月31日 上午5:49:26
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月31日       shiyl             1.0             1.0 Version
 */
public class CommonRoomUtil {
	/**
	 * 从虚拟roomEntity中获取房间的基本信息
	 * @param roomEntity
	 * @return
	 */
	public static RoomBaseInfo getRoomBaseInfo(RoomEntity roomEntity){
			//如果虚拟门牌或者对应的真实门牌为空，则返回null
			if(roomEntity==null||roomEntity.getRealRoomEntity()==null){return null;}
			
			String id = roomEntity.getId().toString();
//			String roomNum = roomEntity.getRealRoomEntity().getNum();
//			String roomNum = roomEntity.getRealRoomEntity().getNumShow();//syl-upd-2015-2-10 19:15:00 
			String roomNum = RoomEntityConvertUtil.getRealRoomShowName(roomEntity.getRealRoomEntity());
			RoomBaseInfo roomBaseInfo = new RoomBaseInfo();
			roomBaseInfo.setId(id);
			roomBaseInfo.setRoomNum(roomNum);
			roomBaseInfo.setRoomHuaId(roomEntity.getHuaId());
			BuildingEntity buildingEntity = roomEntity.getRealRoomEntity().getBuildingEntity();
			if(buildingEntity!=null){
//				String building = buildingEntity.getName();
//				if(building!=null&&!building.endsWith("栋")){//syl-add 2014-11-24 11:50:04
//					building = building+"栋";
//				}
				String building = RoomEntityConvertUtil.getBuildingShowName(buildingEntity);
				String groupBuilding = buildingEntity.getGroupBuildingEntity().getName();
				String block =  buildingEntity.getGroupBuildingEntity().getAddressBlockEntity().getName();
				String city = buildingEntity.getGroupBuildingEntity().getAddressBlockEntity().getAddressCityEntity().getName();
				String province = buildingEntity.getGroupBuildingEntity().getAddressBlockEntity().getAddressCityEntity().getAddressProvinceEntity().getName();
				String totalAddress = new StringBuffer().append(province).append(city).append(block).append(groupBuilding).append(building).append("-").append(roomNum).toString();
				roomBaseInfo.setBuilding(building+"-");
				roomBaseInfo.setGroupBuilding(groupBuilding);
				roomBaseInfo.setBlock(block);
				roomBaseInfo.setCity(city);
				roomBaseInfo.setProvince(province);
				roomBaseInfo.setTotalAddress(totalAddress);
			}else{
				String totalAddress = new StringBuffer().append(roomNum).toString();
				roomBaseInfo.setTotalAddress(totalAddress);
			}
			return roomBaseInfo;
	}
	
	public static RoomBaseInfo getRoomBaseInfo(RealRoomEntity realRoomEntity){
		//如果虚拟门牌或者对应的真实门牌为空，则返回null
		if(realRoomEntity==null){return null;}
		
//		String id = roomEntity.getId().toString();
//		String roomNum = roomEntity.getRealRoomEntity().getNum();
//		String roomNum = roomEntity.getRealRoomEntity().getNumShow();//syl-upd-2015-2-10 19:15:00 
		String roomNum = RoomEntityConvertUtil.getRealRoomShowName(realRoomEntity);
		RoomBaseInfo roomBaseInfo = new RoomBaseInfo();
//		roomBaseInfo.setId(id);
		roomBaseInfo.setRoomNum(roomNum);
//		roomBaseInfo.setRoomHuaId(roomEntity.getHuaId());
		BuildingEntity buildingEntity = realRoomEntity.getBuildingEntity();
		if(buildingEntity!=null){
//			String building = buildingEntity.getName();
//			if(building!=null&&!building.endsWith("栋")){//syl-add 2014-11-24 11:50:04
//				building = building+"栋";
//			}
			String building = RoomEntityConvertUtil.getBuildingShowName(buildingEntity);
			String groupBuilding = buildingEntity.getGroupBuildingEntity().getName();
			String block =  buildingEntity.getGroupBuildingEntity().getAddressBlockEntity().getName();
			String city = buildingEntity.getGroupBuildingEntity().getAddressBlockEntity().getAddressCityEntity().getName();
			String province = buildingEntity.getGroupBuildingEntity().getAddressBlockEntity().getAddressCityEntity().getAddressProvinceEntity().getName();
			String totalAddress = new StringBuffer().append(province).append(city).append(block).append(groupBuilding).append(building).append("-").append(roomNum).toString();
			roomBaseInfo.setBuilding(building);
			roomBaseInfo.setGroupBuilding(groupBuilding);
			roomBaseInfo.setBlock(block);
			roomBaseInfo.setCity(city);
			roomBaseInfo.setProvince(province);
			roomBaseInfo.setTotalAddress(totalAddress);
		}else{
			String totalAddress = new StringBuffer().append(roomNum).toString();
			roomBaseInfo.setTotalAddress(totalAddress);
		}
		return roomBaseInfo;
}
	
	/**
	 * 获取门牌的省市区信息
	 * @param roomEntity
	 * @return
	 */
	public static String getAddressArea(RoomEntity roomEntity){
		String addressArea=null;
		RoomBaseInfo roomBaseInfo = CommonRoomUtil.getRoomBaseInfo(roomEntity);
		if(roomBaseInfo!=null){
			addressArea=new StringBuffer().append(roomBaseInfo.getProvince()).append(roomBaseInfo.getCity()).append(roomBaseInfo.getBlock()).toString();
		}
		return addressArea;
	}
	
	/**
	 * 获取门牌的市、区、小区信息
	 * @param roomEntity
	 * @return
	 */
	public static String getAddressArea02(RoomEntity roomEntity){
		String addressArea=null;
		RoomBaseInfo roomBaseInfo = CommonRoomUtil.getRoomBaseInfo(roomEntity);
		if(roomBaseInfo!=null){
			addressArea=new StringBuffer().append(roomBaseInfo.getCity()).append(roomBaseInfo.getBlock()).append(roomBaseInfo.getGroupBuilding()).toString();
		}
		return addressArea;
	}
	
	/**
	 * 获取门牌的市、区、小区信息
	 * @param roomEntity
	 * @return
	 */
	public static String getAddressArea02(RealRoomEntity realRoomEntity){
		String addressArea=null;
		RoomBaseInfo roomBaseInfo = CommonRoomUtil.getRoomBaseInfo(realRoomEntity);
		if(roomBaseInfo!=null){
			addressArea=new StringBuffer().append(roomBaseInfo.getCity()).append(roomBaseInfo.getBlock()).append(roomBaseInfo.getGroupBuilding()).toString();
		}
		return addressArea;
	}
	
	/**
	 * 获取门牌的 小区建筑门牌号的详细信息
	 * @param roomEntity
	 * @return
	 */
	public static String getAddressDetail(RoomEntity roomEntity){
		String addressDetail=null;
		RoomBaseInfo roomBaseInfo = CommonRoomUtil.getRoomBaseInfo(roomEntity);
		if(roomBaseInfo!=null){
			addressDetail = new StringBuffer().append(roomBaseInfo.getGroupBuilding()).append(roomBaseInfo.getBuilding()).append(roomBaseInfo.getRoomNum()).toString();
		}
		return addressDetail;
	}
	
	/**
	 * 获取门牌的 小区建筑门牌号的详细信息
	 * @param roomEntity
	 * @return
	 */
	public static String getAddressDetail(RealRoomEntity realRoomEntity){
		String addressDetail=null;
		RoomBaseInfo roomBaseInfo = CommonRoomUtil.getRoomBaseInfo(realRoomEntity);
		if(roomBaseInfo!=null){
			addressDetail = new StringBuffer().append(roomBaseInfo.getGroupBuilding()).append(roomBaseInfo.getBuilding()).append("-").append(roomBaseInfo.getRoomNum()).toString();
		}
		return addressDetail;
	}
	/**
	 * 获取门牌的 楼栋、单元、门牌号的详细信息
	 * @param roomEntity
	 * @return
	 */
	public static String getAddressDetail02(RoomEntity roomEntity){
		String addressDetail=null;
		RoomBaseInfo roomBaseInfo = CommonRoomUtil.getRoomBaseInfo(roomEntity);
		if(roomBaseInfo!=null){
			addressDetail = new StringBuffer().append(roomBaseInfo.getBuilding()).append(roomBaseInfo.getRoomNum()).toString();
		}
		return addressDetail;
	}
	/**
	 * 获取门牌的 楼栋、单元、门牌号的详细信息
	 * @param roomEntity
	 * @return
	 */
	public static String getAddressDetail02(RealRoomEntity realRoomEntity){
		String addressDetail=null;
		RoomBaseInfo roomBaseInfo = CommonRoomUtil.getRoomBaseInfo(realRoomEntity);
		if(roomBaseInfo!=null){
			addressDetail = new StringBuffer().append(roomBaseInfo.getBuilding()).append("-").append(roomBaseInfo.getRoomNum()).toString();
		}
		return addressDetail;
	}
	/**
	 * 获取门牌的省市区小区建筑门牌号的详细信息
	 * @param roomEntity
	 * @return
	 */
	public static String getAddressTotalDetail(RoomEntity roomEntity){
		String roomDetail = null;
		RoomBaseInfo roomBaseInfo=CommonRoomUtil.getRoomBaseInfo(roomEntity);
		if(roomBaseInfo!=null){
			roomDetail=new StringBuffer()
			.append(roomBaseInfo.getProvince()).append(roomBaseInfo.getCity()).append(roomBaseInfo.getBlock())
			.append(roomBaseInfo.getGroupBuilding()).append(roomBaseInfo.getBuilding()).append(roomBaseInfo.getRoomNum()).toString();
		}
		return roomDetail;
	}
	
	/**
	 * 解析门牌信息得到Map
	 * @param roomEntity 门牌信息
	 * @param roomValidate 门牌校验信息
	 * @param defaultRoomId 默认门牌Id
	 * @return
	 */
	public static Map<String,Object> getRoomInfo(BigInteger userId,RoomEntity roomEntity,RoomValidate roomValidate,BigInteger defaultRoomId){
		//如果虚拟门牌或者对应的真实门牌为空，则返回null
		if(roomEntity==null||roomEntity.getRealRoomEntity()==null){return null;}
		
		RoomBaseInfo roomBaseInfo = getRoomBaseInfo(roomEntity);
		Map<String,Object> tmpMap = MapConverter.toMap(roomBaseInfo);
		//syl-add-2015-2-5 15:24:15 真实门牌Id
		tmpMap.put("realRoomId", roomEntity==null?null:roomEntity.gettRealRoomFId());
		tmpMap.put("blockId", roomEntity.getRealRoomEntity().getBuildingEntity().getGroupBuildingEntity().getAddressBlockEntity().getId());
		tmpMap.put("cityId", roomEntity.getRealRoomEntity().getBuildingEntity().getGroupBuildingEntity().getAddressBlockEntity().gettCityFId());
		if(roomValidate!=null){
			tmpMap.put("verifyStatus", roomValidate.getVerifyStatus());
		}else{
			tmpMap.put("verifyStatus",RoomDict.RoomValidte_VerifyStatus.UNDO);//默认未验证
		}
		//设置是否为默认门牌
		if(roomEntity!=null&&defaultRoomId!=null&&roomEntity.getId().compareTo(defaultRoomId)==0){
			tmpMap.put("isDefault",true);
		}else{
			tmpMap.put("isDefault",false);
		}
		//用户是否为门牌管理员
		if(userId!=null&&roomEntity.getRealRoomEntity().getValidateUserId()!=null&&roomEntity.getRealRoomEntity().getValidateUserId().compareTo(userId)==0){
			tmpMap.put("ext_room_isAdmin",true);
		}else{
			tmpMap.put("ext_room_isAdmin",false);
		}
		//小区是否签约
		if(roomEntity.getRealRoomEntity().getBuildingEntity()!=null){
			boolean ext_groupBuilding_isSign = false;
			GroupBuildingEntity groupBuildingEntity = roomEntity.getRealRoomEntity().getBuildingEntity().getGroupBuildingEntity();
			if(groupBuildingEntity!=null&&groupBuildingEntity.getSignStatus()!=null
					&&groupBuildingEntity.getSignStatus().compareTo(RoomDict.GroupBuilding_SignStatus.IS_SIGN)==0){
				ext_groupBuilding_isSign = true;
			}
			tmpMap.put("ext_groupBuilding_isSign",ext_groupBuilding_isSign);
		}
		return tmpMap;
	}
	
}
