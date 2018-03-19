package com.cnfantasia.server.ms.property.importer.entity;

import java.math.BigInteger;

public class PropertyProprietor4Import {
	BigInteger firstPpId;//第一个业主id
	BigInteger ppId;
	BigInteger rrId;
	String ppName;
	String ppPhone;
	String ppIdNumber;

	public BigInteger getFirstPpId() {
		return firstPpId;
	}

	public void setFirstPpId(BigInteger firstPpId) {
		this.firstPpId = firstPpId;
	}

	public BigInteger getPpId() {
		return ppId;
	}

	public void setPpId(BigInteger ppId) {
		this.ppId = ppId;
	}

	public BigInteger getRrId() {
		return rrId;
	}

	public void setRrId(BigInteger rrId) {
		this.rrId = rrId;
	}

	public String getPpName() {
		return ppName;
	}

	public void setPpName(String ppName) {
		this.ppName = ppName;
	}

	public String getPpPhone() {
		return ppPhone;
	}

	public void setPpPhone(String ppPhone) {
		this.ppPhone = ppPhone;
	}

	public String getPpIdNumber() {
		return ppIdNumber;
	}

	public void setPpIdNumber(String ppIdNumber) {
		this.ppIdNumber = ppIdNumber;
	}

}
