package com.cnfantasia.server.api.meterReading.entity;

import java.math.BigInteger;

import com.cnfantasia.server.domainbase.realRoom.entity.RealRoom;
import com.cnfantasia.server.domainbase.realRoomHasMrLastRecord.entity.RealRoomHasMrLastRecord;

/**
 * 房间最后一次抄表读数
 * @author wenfq  2016年12月28日
 *
 */
public class RealRoomHasMrLastRecordEntity extends RealRoomHasMrLastRecord {
	BigInteger gbId;
	String gbName;
	
	BigInteger buildingId;
	String buildingName;
	
	RealRoom rr;
	
	public RealRoom getRr() {
		return rr;
	}
	
	public void setRr(RealRoom rr) {
		this.rr = rr;
	}
	public BigInteger getGbId() {
		return gbId;
	}
	public void setGbId(BigInteger gbId) {
		this.gbId = gbId;
	}
	public String getGbName() {
		return gbName;
	}
	public void setGbName(String gbName) {
		this.gbName = gbName;
	}
	public BigInteger getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(BigInteger buildingId) {
		this.buildingId = buildingId;
	}
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	
	/**
	 * 获得房间的上一次读数标识：楼栋+单元+房间号 
	 * @return
	 */
	public String getFullName() {
		String unitName = rr.getUnitName() == null ? "" : rr.getUnitName();
		return gbName + "_" + buildingName + "_" + unitName + "_" + rr.getNumTail();
	}
}
