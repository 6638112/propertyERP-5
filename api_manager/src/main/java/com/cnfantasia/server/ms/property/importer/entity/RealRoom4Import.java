package com.cnfantasia.server.ms.property.importer.entity;

import java.math.BigInteger;

public class RealRoom4Import {
	BigInteger rrId;
	String rrNum;
	String rrNumTail;
	String rrUnitName;
	int propMoney;
	double roomSize = 0;
	double roomManagerPrice = 0; //管理费单价

	int checkStatus;

	BigInteger bId; //楼栋id
	String bName;//楼栋名称

	public int getCheckStatus() {
		return checkStatus;
	}

	public void setCheckStatus(int checkStatus) {
		this.checkStatus = checkStatus;
	}

	public String getbName() {
		return bName;
	}

	public void setbName(String bName) {
		this.bName = bName;
	}

	public BigInteger getbId() {
		return bId;
	}

	public void setbId(BigInteger bId) {
		this.bId = bId;
	}

	public BigInteger getRrId() {
		return rrId;
	}

	public void setRrId(BigInteger rrId) {
		this.rrId = rrId;
	}

	public String getRrNum() {
		return rrNum;
	}

	public void setRrNum(String rrNum) {
		this.rrNum = rrNum;
	}

	public String getRrNumTail() {
		return rrNumTail;
	}

	public void setRrNumTail(String rrNumTail) {
		this.rrNumTail = rrNumTail;
	}

	public String getRrUnitName() {
		return rrUnitName;
	}

	public void setRrUnitName(String rrUnitName) {
		this.rrUnitName = rrUnitName;
	}

	public int getPropMoney() {
		return propMoney;
	}

	public void setPropMoney(int propMoney) {
		this.propMoney = propMoney;
	}

	public double getRoomSize() {
		return roomSize;
	}

	public void setRoomSize(double roomSize) {
		this.roomSize = roomSize;
	}

	public double getRoomManagerPrice() {
		return roomManagerPrice;
	}

	public void setRoomManagerPrice(double roomManagerPrice) {
		this.roomManagerPrice = roomManagerPrice;
	}

}
