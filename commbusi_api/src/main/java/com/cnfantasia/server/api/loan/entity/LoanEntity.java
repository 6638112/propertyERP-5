package com.cnfantasia.server.api.loan.entity;

import java.math.BigInteger;
import java.util.List;

/**
 * 借贷首页信息
 * 
 * @author liyulin
 * @version 1.0 2017年6月5日 下午3:26:51
 */
public class LoanEntity {
	private BigInteger ltId;
	private String typeName;
	private String typeDesc;
	List<LoanProduct> loanProductList;

	public BigInteger getLtId() {
		return ltId;
	}

	public void setLtId(BigInteger ltId) {
		this.ltId = ltId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public List<LoanProduct> getLoanProductList() {
		return loanProductList;
	}

	public void setLoanProductList(List<LoanProduct> loanProductList) {
		this.loanProductList = loanProductList;
	}

}
