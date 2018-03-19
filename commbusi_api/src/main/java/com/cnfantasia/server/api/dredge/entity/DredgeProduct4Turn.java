package com.cnfantasia.server.api.dredge.entity;

import java.util.ArrayList;
import java.util.List;

import com.cnfantasia.server.domainbase.dredgeProduct.entity.DredgeProduct;
import com.cnfantasia.server.domainbase.dredgeProductSpecification.entity.DredgeProductSpecification;

/**
 * 内转外所需服务商品
 * @author wenfq 2017-05-13
 *
 */
public class DredgeProduct4Turn extends DredgeProduct {
	String fullName;// 类型全称, 名称+供应商名称
	List<DredgeProductSpecification> prdtSpecList = new ArrayList<DredgeProductSpecification>();

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public List<DredgeProductSpecification> getPrdtSpecList() {
		return prdtSpecList;
	}

	public void setPrdtSpecList(List<DredgeProductSpecification> prdtSpecList) {
		this.prdtSpecList = prdtSpecList;
	}
	
}
