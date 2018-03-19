/**   
* Filename:    OrderPayInfo.java   
* @version:    1.0  
* Create at:   2014年12月9日 上午3:03:51   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年12月9日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.payment.entity;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * Filename:    OrderPayInfo.java
 * @version:    1.0.0
 * Create at:   2014年12月9日 上午3:03:51
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年12月9日       shiyl             1.0             1.0 Version
 */
public class OrderPayInfo implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**订单Id*/
	private BigInteger orderId;
	/**产品信息*/
	private String productInfo;
	/**产品详细息*/
	private String productDetail;
	/**待支付金额*/
	private Long totalAmount;
	/**商家对外唯一订单号*/
	private String outTradeNo;
	/**订单类型*/
	private Integer type;
	
	public OrderPayInfo(){}
	public OrderPayInfo(BigInteger orderId,String productInfo,Long totalAmount,String outTradeNo,String productDetail, Integer type){
		this.orderId = orderId;
		this.productInfo = productInfo;
		this.totalAmount = totalAmount;
		this.outTradeNo = outTradeNo;
		this.productDetail = productDetail;
		this.type = type;
	}
	
	public BigInteger getOrderId() {
		return orderId;
	}
	public void setOrderId(BigInteger orderId) {
		this.orderId = orderId;
	}
	public String getProductInfo() {
		return productInfo;
	}
	public void setProductInfo(String productInfo) {
		this.productInfo = productInfo;
	}
	public Long getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getOutTradeNo() {
		return outTradeNo;
	}
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
	public String getProductDetail() {
		return productDetail;
	}
	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
