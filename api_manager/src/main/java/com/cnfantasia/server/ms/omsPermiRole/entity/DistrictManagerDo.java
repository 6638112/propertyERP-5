package com.cnfantasia.server.ms.omsPermiRole.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Set;

public class DistrictManagerDo implements Serializable {
	
	private static final long serialVersionUID = -4505376073754336562L;

	private BigInteger id;
	
	private Set<BigInteger> userIds;
	
	private Set<BigInteger> gbIds;
	
	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public Set<BigInteger> getUserIds() {
		return userIds;
	}

	public void setUserIds(Set<BigInteger> userIds) {
		this.userIds = userIds;
	}

	public Set<BigInteger> getGbIds() {
		return gbIds;
	}

	public void setGbIds(Set<BigInteger> gbIds) {
		this.gbIds = gbIds;
	}

}
