package com.cnfantasia.server.ms.revenue.entity;

import java.util.List;

import com.cnfantasia.server.domainbase.revenueApply.entity.RevenueApply;

/**
 * 提款单（导出用）
 * @author wenfq 2016-10-20
 *
 */
public class RevenueApply4Export extends RevenueApply {
	List<String> mergedRevenueNOs;//合并的用户提款单号

	public List<String> getMergedRevenueNOs() {
		return mergedRevenueNOs;
	}

	public void setMergedRevenueNOs(List<String> mergedRevenueNOs) {
		this.mergedRevenueNOs = mergedRevenueNOs;
	}
}
