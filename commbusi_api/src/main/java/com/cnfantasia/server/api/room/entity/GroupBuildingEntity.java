package com.cnfantasia.server.api.room.entity;

import java.math.BigInteger;

import com.cnfantasia.server.api.room.constant.GroupBuildingDict;
import com.cnfantasia.server.api.room.constant.RoomDict;
import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;
/**
 * 描述:(小区信息) 具体业务实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
public class GroupBuildingEntity extends GroupBuilding{
	private static final long serialVersionUID = 1L;
	/**所属管理处*/
	private PropertyManagementEntity propertyManagementEntity;
	public PropertyManagementEntity getPropertyManagementEntity() {
		return propertyManagementEntity;
	}
	public void setPropertyManagementEntity(PropertyManagementEntity propertyManagementEntity) {
		this.propertyManagementEntity = propertyManagementEntity;
	}
	@Override
	public BigInteger gettPropertyManagementFId() {
		if(propertyManagementEntity==null){return null;}
		return propertyManagementEntity.getId();
	}
	@Override
	public void settPropertyManagementFId(BigInteger tPropertyManagementFId) {
		if(propertyManagementEntity==null){
			propertyManagementEntity = new PropertyManagementEntity();
		}
		propertyManagementEntity.setId(tPropertyManagementFId);
	}
	
	/**所属小区*/
	private AddressBlockEntity addressBlockEntity;
	public AddressBlockEntity getAddressBlockEntity() {
		return addressBlockEntity;
	}
	public void setAddressBlockEntity(AddressBlockEntity addressBlockEntity) {
		this.addressBlockEntity = addressBlockEntity;
	}
	@Override
	public BigInteger gettBlockFId() {
		if(addressBlockEntity==null){return null;}
		return addressBlockEntity.getId();
	}
	@Override
	public void settBlockFId(BigInteger tBlockFId) {
		if(addressBlockEntity==null){
			addressBlockEntity = new AddressBlockEntity();
		}
		addressBlockEntity.setId(tBlockFId);
	}
	
	public boolean isSign(){
		Integer signStatus = this.getSignStatus();
		if(signStatus!=null&&signStatus.compareTo(RoomDict.GroupBuilding_SignStatus.IS_SIGN)==0){
			return true;
		}else{
			return false;
		}
	}
	
	/**用户对小区是否已点击支持*/
	public Integer haveSupport;
	public Integer getHaveSupport() {
		return haveSupport;
	}
	public void setHaveSupport(Integer haveSupport) {
		this.haveSupport = haveSupport;
	}
	
	public boolean getIsSupport() {
		if(haveSupport!=null&&GroupBuildingDict.GroupBuilding_IsSupport.YES.compareTo(haveSupport)==0){
			return true;
		}else{
			return false;
		}
	}
	
	/**小区当前被支持的数量*/
	public Integer totalSupportCount;
	public Integer getTotalSupportCount() {
		return totalSupportCount;
	}

	public void setTotalSupportCount(Integer totalSupportCount) {
		this.totalSupportCount = totalSupportCount;
	}
	
	/**用户对小区是否已点击召唤*/
	public Integer haveSummon;
	public Integer getHaveSummon() {
		return haveSummon;
	}
	public void setHaveSummon(Integer haveSummon) {
		this.haveSummon = haveSummon;
	}
	public Boolean getIsSummon() {
		if(haveSummon!=null&&GroupBuildingDict.GroupBuilding_IsSummon.YES.compareTo(haveSummon)==0){
			return true;
		}else{
			return false;
		}
	}
	
	
	
}
