package com.cnfantasia.server.api.room.entity;

import java.math.BigInteger;

import com.cnfantasia.server.domainbase.addressBlock.entity.AddressBlock;
/**
 * 描述:(小区) 具体业务实体类
 * 
 * @version 1.00
 * @author null
 * 
 */
public class AddressBlockEntity extends AddressBlock{
	private static final long serialVersionUID = 1L;
	private AddressCityEntity addressCityEntity;
	public AddressCityEntity getAddressCityEntity() {
		return addressCityEntity;
	}
	public void setAddressCityEntity(AddressCityEntity addressCityEntity) {
		this.addressCityEntity = addressCityEntity;
	}
	@Override
	public BigInteger gettCityFId() {
		if(addressCityEntity==null){return null;}
		return addressCityEntity.getId();
	}
	@Override
	public void settCityFId(BigInteger tCityFId) {
		if(addressCityEntity==null){
			addressCityEntity = new AddressCityEntity();
		}
		addressCityEntity.setId(tCityFId);
	}
	
}
