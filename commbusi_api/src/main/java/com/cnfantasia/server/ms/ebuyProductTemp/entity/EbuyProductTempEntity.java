package com.cnfantasia.server.ms.ebuyProductTemp.entity;

import java.util.List;

import com.cnfantasia.server.domainbase.ebuyProductParametersTemp.entity.EbuyProductParametersTemp;
import com.cnfantasia.server.domainbase.ebuyProductTemp.entity.EbuyProductTemp;

public class EbuyProductTempEntity extends EbuyProductTemp {
	/**
	 * 商品参数
	 */
	private List<EbuyProductParametersTemp> prdtParamter;

	public void setPrdtParamter(List<EbuyProductParametersTemp> prdtParamter) {
		this.prdtParamter = prdtParamter;
	}

	public List<EbuyProductParametersTemp> getPrdtParamter() {
		return prdtParamter;
	}

}
