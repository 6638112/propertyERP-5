package com.cnfantasia.server.ms.provinceCityBlock.entity;

import java.util.List;

import com.cnfantasia.server.domainbase.addressBlock.entity.AddressBlock;
import com.cnfantasia.server.domainbase.addressCity.entity.AddressCity;

/**
 * 城市，有区（县）属性
 * 
 * @author wenfq
 * 
 */
public class CityWithBlock extends AddressCity {
	List<AddressBlock> blockList;

	public List<AddressBlock> getBlockList() {
		return blockList;
	}

	public void setBlockList(List<AddressBlock> blockList) {
		this.blockList = blockList;
	}
}
