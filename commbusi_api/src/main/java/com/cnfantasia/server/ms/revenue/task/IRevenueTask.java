package com.cnfantasia.server.ms.revenue.task;


/**
 * 收益额计算定时任务
* Filename:    IRevenueTask.java
* @version:    1.0.0
* Create at:   2015年11月18日 下午5:57:35
* Description:
*
* Modification History:
* Date           Author           Version           Description
* ------------------------------------------------------------------
* 2015年11月18日       shiyl             1.0             1.0 Version
 */
public interface IRevenueTask {
	/**
	 * 物业公司的收益的物业费实收金额==>同步到单个收益额表
	 * @param companyId
	 * @return
	 */
	public void synPropertyRealPayAmoutForPropertyCompany();
	
	/**
	 * 物业公司的收益的物业费补贴金额==>同步到单个收益额表
	 * @param companyId
	 * @return
	 */
	public void synPropertySubsidyAmoutForPropertyCompany();
	
	/**
	 * 物业公司的收益的其他代收费用==>同步到单个收益额表
	 * @param companyId
	 * @return
	 */
	public void synPropertyOtherFeeForPropertyCompany();
	
//	/**
//	 * 物业公司门牌认证的收益==>同步到单个收益额表
//	 * @param companyId
//	 */
//	public void synRoomValidateForPropertyCompany();
//	/**
//	 * 代理商门牌认证的收益==>同步到单个收益额表
//	 * @param companyId
//	 */
//	public void synRoomValidateForPropertyAgent();

	/**
	 *　电商收益==>同步运单金额到单个收益额表
	 */
	public void synEbuyForSupply();

	/**
	 * 维修师傅收益同步
	 */
	public void synDredgePayAmoutForMaster();
	
	/**
	 * 物业宝收益生成
	 */
	public void synFinance();
	
	/**
	 * 车禁收益
	 */
	public void synCar();
	
	/**
	 * 物业宝抵扣收益
	 */
	public void synFianceDedu();

	/**
	 * 停车宝收益生成
	 */
	void synCarFinance();
	
	/**
	 * 停车宝抵扣收益
	 */
	public void synCarDedu();
	
}
