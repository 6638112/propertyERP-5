package com.cnfantasia.server.api.room.entity;

import java.math.BigInteger;

import com.cnfantasia.server.api.room.util.RoomStatusCheckUtil;
import com.cnfantasia.server.domainbase.propertyProprietor.entity.PropertyProprietor;
import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;
/**
 * 描述:(真实房间) 具体业务实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
public class RealRoomEntity extends RealRoom{
	private static final long serialVersionUID = 1L;
	/**所属单元*/
	private BuildingEntity buildingEntity;
	public BuildingEntity getBuildingEntity() {
		return buildingEntity;
	}
	public void setBuildingEntity(BuildingEntity buildingEntity) {
		this.buildingEntity = buildingEntity;
	}
	@Override
	public BigInteger gettBuildingFId() {
		if(buildingEntity==null){return null;}
		return buildingEntity.getId();
	}
	@Override
	public void settBuildingFId(BigInteger tBuildingFId) {
		if(buildingEntity==null){
			buildingEntity = new BuildingEntity();
		}
		buildingEntity.setId(tBuildingFId);
	}
	
	/**真实门牌所属业主信息*/
	private PropertyProprietor propertyProprietor;
	public PropertyProprietor getPropertyProprietor() {
		return propertyProprietor;
	}
	public void setPropertyProprietor(PropertyProprietor propertyProprietor) {
		this.propertyProprietor = propertyProprietor;
	}
	@Override
	public BigInteger gettPropertyProprietorFId() {
		if(propertyProprietor==null){
			return null;
		}
		return propertyProprietor.getId();
	}
	@Override
	public void settPropertyProprietorFId(BigInteger tPropertyProprietorFId) {
		if(propertyProprietor==null){
			propertyProprietor = new PropertyProprietor();
		}
		propertyProprietor.setId(tPropertyProprietorFId);
	}
	
	/**
	 * 真实门牌是否已经被验证
	 * @return
	 */
	public boolean checkIsValidated(){
//		if(this.getValidateStatus()!=null&&this.getValidateStatus().compareTo(RoomDict.RealRoom_ValidateStatus.IS_VALIDATED)==0){
		if(RoomStatusCheckUtil.checkIsRealRoomValidated(this)){
			return true;
		}else{
			return false;
		}
	}
	
	/**门牌管理员信息*/
	
}
