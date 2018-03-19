package com.cnfantasia.server.api.bankCollection.entity;

import java.math.BigInteger;
import java.util.Set;

/**
 * 托收银行维护列表信息
 * 
 * @author liyulin
 * @version 1.0 2017年4月13日 下午2:08:22
 */
public class BcBankInfoDto {

	/** t_property_company_bank_collection_info表f_id */
	private BigInteger pcbciId;
	/** 编号 */
	private String no;
	/** 托收范围={1:按小区; 2按业主} */
	private Integer collectionRange;
	/** 包含小区 */
	private Set<String> gbNameSet;
	/** 包含小区 */
	private String gbNames;
	/** 托收银行 */
	private String bankName;

	public BigInteger getPcbciId() {
		return pcbciId;
	}

	public void setPcbciId(BigInteger pcbciId) {
		this.pcbciId = pcbciId;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public Integer getCollectionRange() {
		return collectionRange;
	}

	public void setCollectionRange(Integer collectionRange) {
		this.collectionRange = collectionRange;
	}

	public Set<String> getGbNameSet() {
		return gbNameSet;
	}

	public void setGbNameSet(Set<String> gbNameSet) {
		this.gbNameSet = gbNameSet;
	}

	public String getGbNames() {
		return gbNames;
	}

	public void setGbNames(String gbNames) {
		this.gbNames = gbNames;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

}
