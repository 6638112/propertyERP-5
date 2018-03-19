package com.cnfantasia.server.ms.revenue.entity;

import java.io.Serializable;
import java.math.BigInteger;

public class EbuyProdRevenue implements Serializable {
	
	private static final long serialVersionUID = 429075953706127197L;

	private BigInteger prodId; //订单商品ID
	
	private Long prodPrice; // f_product_price;
	
	private Long prodQty;

	public BigInteger getProdId() {
		return prodId;
	}

	public void setProdId(BigInteger prodId) {
		this.prodId = prodId;
	}

	public Long getProdPrice() {
		return prodPrice;
	}

	public void setProdPrice(Long prodPrice) {
		this.prodPrice = prodPrice;
	}

	public Long getProdQty() {
		return prodQty;
	}

	public void setProdQty(Long prodQty) {
		this.prodQty = prodQty;
	}
	
	public Long getPrice() {
		if(prodPrice == null || prodQty == null) {
			return 0L;
		}
		return (prodPrice * prodQty);
	}

}
