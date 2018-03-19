package com.cnfantasia.server.api.property.dto;

import com.cnfantasia.server.api.plotproperty.entity.AlterPayBillDto;
import com.cnfantasia.server.api.plotproperty.entity.AlterPayBillEntity;

import java.util.List;

/**
 * 待缴费账单界面信息
 * 
 * @author liyulin
 * @version 1.0 2017年3月10日 下午2:35:02
 */
public class RemainBillInfoDto {
	/** 物业宝信息 */
	private FinanceDto finance;
	/** 物业代扣卡账户金额 */
	private RemainBillOtherInfoDto otherInfo;
	/** 所有账单信息 */
	private List<RemainBillDto> remainBills;
	/** 选择周期账单 */
	private List<AlterPayBillDto> alterPayBillDtos;

	public FinanceDto getFinance() {
		return finance;
	}

	public void setFinance(FinanceDto finance) {
		this.finance = finance;
	}

	public RemainBillOtherInfoDto getOtherInfo() {
		return otherInfo;
	}

	public void setOtherInfo(RemainBillOtherInfoDto otherInfo) {
		this.otherInfo = otherInfo;
	}

	public List<RemainBillDto> getRemainBills() {
		return remainBills;
	}

	public void setRemainBills(List<RemainBillDto> remainBills) {
		this.remainBills = remainBills;
	}

	public List<AlterPayBillDto> getAlterPayBillDtos() {
		return alterPayBillDtos;
	}

	public void setAlterPayBillDtos(List<AlterPayBillDto> alterPayBillDtos) {
		this.alterPayBillDtos = alterPayBillDtos;
	}

}
