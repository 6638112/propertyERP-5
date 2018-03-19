package com.cnfantasia.server.api.bankCollection.entity;

import com.cnfantasia.server.domainbase.propertyProprietorBankCollectionInfo.entity.PropertyProprietorBankCollectionInfo;

public class PPBCInfo4List extends PropertyProprietorBankCollectionInfo {
	/** 业主姓名 */
	private String ppName;
	/** 业主联系电话 */
	private String ppPhone;
	
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
}
