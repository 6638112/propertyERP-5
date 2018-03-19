/**   
* Filename:    PlotpropertyOrderEntity.java   
* @version:    1.0  
* Create at:   2014年11月19日 上午10:13:39   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年11月19日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.plotproperty.entity;

import java.math.BigDecimal;
import java.math.BigInteger;

import com.cnfantasia.server.domainbase.ebuyOrder.entity.EbuyOrder;
import com.cnfantasia.server.domainbase.ebuyOrderHasTPayBill.entity.EbuyOrderHasTPayBill;

/**
 * Filename:    PlotpropertyOrderEntity.java
 * @version:    1.0.0
 * Create at:   2014年11月19日 上午10:13:39
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年11月19日       shiyl             1.0             1.0 Version
 */
public class PlotpropertyOrderEntity extends EbuyOrder{

	private static final long serialVersionUID = 1L;
	/**
	 * 订单包含的账单
	 */
	private EbuyOrderHasTPayBill ebuyOrderHasTPayBill;
	public EbuyOrderHasTPayBill getEbuyOrderHasTPayBill() {
		return ebuyOrderHasTPayBill;
	}
	public void setEbuyOrderHasTPayBill(EbuyOrderHasTPayBill ebuyOrderHasTPayBill) {
		this.ebuyOrderHasTPayBill = ebuyOrderHasTPayBill;
	}
	
//	/**
//	 * 订单包含的账单列表
//	 */
//	private List<EbuyOrderHasTPayBill> ebuyOrderHasTPayBillList;
//	public List<EbuyOrderHasTPayBill> getEbuyOrderHasTPayBillList() {
//		return ebuyOrderHasTPayBillList;
//	}
//	public void setEbuyOrderHasTPayBillList(List<EbuyOrderHasTPayBill> ebuyOrderHasTPayBillList) {
//		this.ebuyOrderHasTPayBillList = ebuyOrderHasTPayBillList;
//	}
	
	/**
	 * 物业单详细
	 */
	private PayBillEntity payBillEntity;
	public PayBillEntity getPayBillEntity() {
		return payBillEntity;
	}
	public void setPayBillEntity(PayBillEntity payBillEntity) {
		this.payBillEntity = payBillEntity;
	}
	
	/**
	 * 账单优惠金额
	 */
	private long preferentialAmt;
	public long getPreferentialAmt() {
		return preferentialAmt;
	}
	public void setPreferentialAmt(long preferentialAmt) {
		this.preferentialAmt = preferentialAmt;
	}
	
	/**
	 * 管理处电话
	 */
	private String managerTel;
	public String getManagerTel() {
		return managerTel;
	}
	public void setManagerTel(String managerTel) {
		this.managerTel = managerTel;
	}
	
	/**
	 * 客户端支付状态
	 */
	private Integer clientPayStatus;
	public Integer getClientPayStatus() {
		return clientPayStatus;
	}
	public void setClientPayStatus(Integer clientPayStatus) {
		this.clientPayStatus = clientPayStatus;
	}
	
	/**物业宝抵扣，是否已经抵扣*/
	private int financeStatus;
	public int getFinanceStatus() {
		return financeStatus;
	}
	public void setFinanceStatus(int financeStatus) {
		this.financeStatus = financeStatus;
	}
	
	/**
	 * 订单id
	 */
	private BigInteger ebuyOrderId;
	public BigInteger getEbuyOrderId() {
		return ebuyOrderId;
	}
	public void setEbuyOrderId(BigInteger ebuyOrderId) {
		this.ebuyOrderId = ebuyOrderId;
	}

	/**粮票金额*/
	private long JFBAmount;

	public long getJFBAmount() {
		return JFBAmount;
	}

	public void setJFBAmount(long JFBAmount) {
		this.JFBAmount = JFBAmount;
	}
}
