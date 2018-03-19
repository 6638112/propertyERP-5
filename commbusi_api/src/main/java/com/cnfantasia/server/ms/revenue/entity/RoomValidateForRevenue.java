package com.cnfantasia.server.ms.revenue.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;


public class RoomValidateForRevenue extends AbstractRevSrcEntity<Integer>{
	private RealRoom realRoom;
	public void setRealRoom(RealRoom realRoom) {
		this.realRoom = realRoom;
	}

	@Override
	public BigInteger getUniqueId() {
		return realRoom.getId();
	}

	@Override
	public Integer getPayAmount() {
		return 1;
	}

	@Override
	public String getRevActiveTime() {
		return realRoom.getValidateTime();
	}

	@Override
	public BigDecimal getSrcMoney() {
		return BigDecimal.ZERO;
	}

	@Override
	public BigDecimal getAmountPtbt() {
		return BigDecimal.ZERO;
	}

	@Override
	public BigDecimal getAmountUsrReal() {
		return BigDecimal.ZERO;
	}
	
}
