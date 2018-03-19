package com.cnfantasia.server.api.operateMsg.entity;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.domainbase.messageToBeSend.entity.MessageToBeSend;

public class MessageAddNewParamter extends MessageToBeSend{
	List<BigInteger> cityIds;
	List<BigInteger> blockIds;
	List<BigInteger> gbIds;
	
	public List<BigInteger> getCityIds() {
		return cityIds;
	}
	public void setCityIds(List<BigInteger> cityIds) {
		this.cityIds = cityIds;
	}
	public List<BigInteger> getBlockIds() {
		return blockIds;
	}
	public void setBlockIds(List<BigInteger> blockIds) {
		this.blockIds = blockIds;
	}
	public List<BigInteger> getGbIds() {
		return gbIds;
	}
	public void setGbIds(List<BigInteger> gbIds) {
		this.gbIds = gbIds;
	}
}
