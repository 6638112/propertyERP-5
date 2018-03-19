package com.cnfantasia.server.api.room.entity;

import java.math.BigInteger;

import com.cnfantasia.server.domainbase.addressCity.entity.AddressCity;
/**
 * 描述:(市) 具体业务实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
public class AddressCityEntity extends AddressCity{
	private static final long serialVersionUID = 1L;
	private AddressProvinceEntity addressProvinceEntity;
	public AddressProvinceEntity getAddressProvinceEntity() {
		return addressProvinceEntity;
	}
	public void setAddressProvinceEntity(AddressProvinceEntity addressProvinceEntity) {
		this.addressProvinceEntity = addressProvinceEntity;
	}
	@Override
	public BigInteger gettProvinceFId() {
		if(addressProvinceEntity==null){return null;}
		return addressProvinceEntity.getId();
	}
	@Override
	public void settProvinceFId(BigInteger tProvinceFId) {
		if(addressProvinceEntity==null){addressProvinceEntity = new AddressProvinceEntity();}
		addressProvinceEntity.setId(tProvinceFId);
	}
	
}
