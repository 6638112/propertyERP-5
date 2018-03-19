package com.cnfantasia.server.api.ebuyorder.entity;

import java.util.List;

import com.cnfantasia.server.domainbase.ebuyProduct.entity.EbuyProduct;
import com.cnfantasia.server.domainbase.ebuyProductParameters.entity.EbuyProductParameters;
import com.cnfantasia.server.domainbase.ebuyProductPic.entity.EbuyProductPic;
import com.cnfantasia.server.domainbase.ebuyProductShelf.entity.EbuyProductShelf;

public class MerchantEbuyProduct extends EbuyProduct {
	
	private static final long serialVersionUID = -5329882998471346511L;

	private List<EbuyProductPic> prodPic;
	
	private List<EbuyProductParameters> prodParams;
	
	private EbuyProductShelf prodShelf;

	public List<EbuyProductPic> getProdPic() {
		return prodPic;
	}

	public void setProdPic(List<EbuyProductPic> prodPic) {
		this.prodPic = prodPic;
	}

	public List<EbuyProductParameters> getProdParams() {
		return prodParams;
	}

	public void setProdParams(List<EbuyProductParameters> prodParams) {
		this.prodParams = prodParams;
	}

	public EbuyProductShelf getProdShelf() {
		return prodShelf;
	}

	public void setProdShelf(EbuyProductShelf prodShelf) {
		this.prodShelf = prodShelf;
	}

}
