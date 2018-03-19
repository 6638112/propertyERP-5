package com.cnfantasia.server.ms.accesslink.dto;

import com.cnfantasia.server.domainbase.parkingRecord.entity.ParkingRecord;

/**
 * 停车记录Dto
 * 
 * @author liyulin
 * @version 1.0 2016年6月29日 下午9:02:40
 */
public class ParkingRecordDto extends ParkingRecord {
	/** 车牌 */
	private String carNum;
	/** 小区 */
	private String gbName;

	public ParkingRecordDto() {
		super();
	}

	public ParkingRecordDto(String carNum, String gbName) {
		super();
		this.carNum = carNum;
		this.gbName = gbName;
	}

	public String getCarNum() {
		return carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	public String getGbName() {
		return gbName;
	}

	public void setGbName(String gbName) {
		this.gbName = gbName;
	}

}
