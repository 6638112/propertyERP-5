package com.cnfantasia.server.ms.revenue.task;


public class RevenueTask implements IRevenueTask{
	
	private ISynTask propertyRealPayAmoutSynTask;
	
	private ISynTask propertySubsidyAmoutSynTask;
	
	private ISynTask propertyOtherFeeSynTask;
	
	private ISynTask ebuySynTaskSupply;
	
	private ISynTask dredgeBillSynTask;
	
	private ISynTask financeSynTask;
	
	private ISynTask carFinanceSynTask; //停车宝
	
	private ISynTask carSynTask; //停车费
	
	private ISynTask financeDeduSynTask;

	
	private ISynTask carDeduSynTask;
	
	
	@Override
	public void synDredgePayAmoutForMaster() {
		dredgeBillSynTask.execTask();
	}
	
	@Override
	public void synPropertyRealPayAmoutForPropertyCompany() {
		propertyRealPayAmoutSynTask.execTask();
	}
	
	@Override
	public void synPropertySubsidyAmoutForPropertyCompany() {
		propertySubsidyAmoutSynTask.execTask();
	}
	
	@Override
	public void synPropertyOtherFeeForPropertyCompany(){
		propertyOtherFeeSynTask.execTask();
	}
	
//	@Override
//	public void synRoomValidateForPropertyCompany() {
//		roomValidateSynTaskCompany.execTask();
//	}
//
//	@Override
//	public void synRoomValidateForPropertyAgent() {
//		roomValidateSynTaskAgent.execTask();
//	}
	
	@Override
	public void synEbuyForSupply() {
		ebuySynTaskSupply.execTask();
	}
	
	@Override
	public void synFinance() {
		financeSynTask.execTask();
	}
	
	/**
	 * 停车宝代收费用明细
	 */
	@Override
	public void synCarFinance() {
		carFinanceSynTask.execTask();
	}
	
	@Override
	public void synCar() {
		carSynTask.execTask();
	}
	
	@Override
	public void synFianceDedu() {
		financeDeduSynTask.execTask();
	}
	
	/**
	 * 停车宝抵扣明细
	 */
	@Override
	public void synCarDedu() {
		carDeduSynTask.execTask();
	}

	public void setPropertyRealPayAmoutSynTask(ISynTask propertyRealPayAmoutSynTask) {
		this.propertyRealPayAmoutSynTask = propertyRealPayAmoutSynTask;
	}

	public void setPropertySubsidyAmoutSynTask(ISynTask propertySubsidyAmoutSynTask) {
		this.propertySubsidyAmoutSynTask = propertySubsidyAmoutSynTask;
	}

	public void setPropertyOtherFeeSynTask(ISynTask propertyOtherFeeSynTask) {
		this.propertyOtherFeeSynTask = propertyOtherFeeSynTask;
	}

	public void setEbuySynTaskSupply(ISynTask ebuySynTaskSupply) {
		this.ebuySynTaskSupply = ebuySynTaskSupply;
	}

	public void setDredgeBillSynTask(ISynTask dredgeBillSynTask) {
		this.dredgeBillSynTask = dredgeBillSynTask;
	}

	public void setFinanceSynTask(ISynTask financeSynTask) {
		this.financeSynTask = financeSynTask;
	}

	public void setCarSynTask(ISynTask carSynTask) {
		this.carSynTask = carSynTask;
	}

	public void setFinanceDeduSynTask(ISynTask financeDeduSynTask) {
		this.financeDeduSynTask = financeDeduSynTask;
	}

	public void setCarDeduSynTask(ISynTask carDeduSynTask) {
		this.carDeduSynTask = carDeduSynTask;
	}

	public void setCarFinanceSynTask(ISynTask carFinanceSynTask) {
		this.carFinanceSynTask = carFinanceSynTask;
	}

}
