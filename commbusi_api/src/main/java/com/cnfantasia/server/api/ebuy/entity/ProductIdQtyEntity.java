/**   
* Filename:    ProductIdQtyEntity.java   
* @version:    1.0  
* Create at:   2014年6月28日 上午2:46:59   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年6月28日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.ebuy.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Filename:    ProductIdQtyEntity.java
 * @version:    1.0.0
 * Create at:   2014年6月28日 上午2:46:59
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年6月28日       shiyl             1.0             1.0 Version
 */
public class ProductIdQtyEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	//参数名和get set方法不可随便更改
	private BigInteger productId;
	private BigInteger productSpecId;
	private Long productQty;
	
	private String flow;
	private String phone;
	private BigDecimal price;
	/**
	 * 商品购买时的扩展信息
	 */
	private EbuyExtandBuyParam ebuyExtandBuyParam; 
	
	public ProductIdQtyEntity(){}
	/**
	 * 构造方法
	 * @param productId 产品Id
	 * @param productQty 购买的数量
	 */
	public ProductIdQtyEntity(BigInteger productId,Long productQty,BigInteger productSpecId,EbuyExtandBuyParam ebuyExtandBuyParam){
		this.productId = productId;
		this.productQty = productQty;
		this.productSpecId = productSpecId;
		this.ebuyExtandBuyParam = ebuyExtandBuyParam;
	}
	
	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("productId=").append(productId).append(";");
		sbf.append("productSpecId=").append(productSpecId).append(";");
		sbf.append("productQty=").append(productQty).append(";");
		return sbf.toString();
	}
	public BigInteger getProductId() {
		return productId;
	}
	public void setProductId(BigInteger productId) {
		this.productId = productId;
	}
	public Long getProductQty() {
		return productQty;
	}
	public void setProductQty(Long productQty) {
		this.productQty = productQty;
	}
	
	public BigInteger getProductSpecId() {
		return productSpecId;
	}
	public void setProductSpecId(BigInteger productSpecId) {
		this.productSpecId = productSpecId;
	}
	
	public EbuyExtandBuyParam getEbuyExtandBuyParam() {
		return ebuyExtandBuyParam;
	}
	public void setEbuyExtandBuyParam(EbuyExtandBuyParam ebuyExtandBuyParam) {
		this.ebuyExtandBuyParam = ebuyExtandBuyParam;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		result = prime * result + ((productQty == null) ? 0 : productQty.hashCode());
		result = prime * result + ((productSpecId == null) ? 0 : productSpecId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductIdQtyEntity other = (ProductIdQtyEntity) obj;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		if (productQty == null) {
			if (other.productQty != null)
				return false;
		} else if (!productQty.equals(other.productQty))
			return false;
		if (productSpecId == null) {
			if (other.productSpecId != null)
				return false;
		} else if (!productSpecId.equals(other.productSpecId))
			return false;
		return true;
	}
	public String getFlow() {
		return flow;
	}
	public void setFlow(String flow) {
		this.flow = flow;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	
	
	
}
