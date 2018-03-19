package com.cnfantasia.server.ms.revenue.util;

import com.cnfantasia.server.business.pub.utils.BigDecimalUtil;
import com.cnfantasia.server.domainbase.revenueConfig.entity.RevenueConfig;
import com.cnfantasia.server.ms.revenue.constant.RevenueDict;

public class RevenueConfigUtil {
	
	public static Double getAgentProfit(RevenueConfig rc, double total) {
		if(rc == null || rc.getRuleType() == null) {
			return 0.0;
		}
		if(rc.getRuleType().equals(RevenueDict.RuleType.ByCount)) {
			return rc.getAgentValue();
		} else {
			double tempV = BigDecimalUtil.div(rc.getAgentValue(),rc.getTotalValue());
			return BigDecimalUtil.mul(tempV, total);
		}
	}
	
	public static Double getAgentProfit2(RevenueConfig rc, double total) {
		if(rc == null || rc.getRuleType() == null) {
			return 0.0;
		}
		if(rc.getRuleType().equals(RevenueDict.RuleType.ByCount)) {
			return rc.getAgentValue();
		} else {
			double tempV = BigDecimalUtil.div(rc.getAgentValue(),rc.getTotalValue());
			return BigDecimalUtil.mul(tempV, total, 2);
		}
	}
	
	public static Double getPropertyProfit(RevenueConfig rc, double total) {
		if(rc == null || rc.getRuleType() == null) {
			return 0.0;
		}
		if(rc.getRuleType().equals(RevenueDict.RuleType.ByCount)) {
			return rc.getCompanyValue() == null ? Double.valueOf(0.0) : rc.getCompanyValue();
		} else {
			double tempV = BigDecimalUtil.div(rc.getCompanyValue(),rc.getTotalValue());
			return BigDecimalUtil.mul(tempV, total);
		}
	}
	
	public static Double getPropertyProfit2(RevenueConfig rc, double total) {
		if(rc == null || rc.getRuleType() == null) {
			return 0.0;
		}
		if(rc.getRuleType().equals(RevenueDict.RuleType.ByCount)) {
			return rc.getCompanyValue() == null ? Double.valueOf(0.0) : rc.getCompanyValue();
		} else {
			double tempV = BigDecimalUtil.div(rc.getCompanyValue(),rc.getTotalValue());
			return BigDecimalUtil.mul(tempV, total, 2);
		}
	}

	public static Double getPlatformProfit(RevenueConfig rc, double total) {
		if(rc == null || rc.getRuleType() == null) {
			return 0.0;
		}
		if(rc.getRuleType().equals(RevenueDict.RuleType.ByCount)) {
			return rc.getPlatformValue();
		} else {
			double tempV = BigDecimalUtil.div(rc.getPlatformValue(),rc.getTotalValue());
			return BigDecimalUtil.mul(tempV, total);
		}
	}
	
	public static Double getPlatformProfit2(RevenueConfig rc, double total) {
		if(rc == null || rc.getRuleType() == null) {
			return 0.0;
		}
		if(rc.getRuleType().equals(RevenueDict.RuleType.ByCount)) {
			return rc.getPlatformValue();
		} else {
			//因为师傅的收益在t_dredge_worker_revenue_config配置了，师傅的收益在那边计算，这里rc.getRepairValue()要分配给平台了 Added by wenfq 2016-11-28
			double tempV = BigDecimalUtil.div(rc.getPlatformValue()+rc.getRepairValue(),rc.getTotalValue());
			return BigDecimalUtil.mul(tempV, total, 2);
		}
	}
	
	public static Double getRecommenderProfit(RevenueConfig rc, double total) {
		if(rc == null || rc.getRuleType() == null) {
			return 0.0;
		}
		if(rc.getRuleType().equals(RevenueDict.RuleType.ByCount)) {
			return rc.getRecommenderValue();
		} else {
			double tempV = BigDecimalUtil.div(rc.getRecommenderValue(),rc.getTotalValue());
			return BigDecimalUtil.mul(tempV, total);
		}
	}
	
	public static Double getRecommenderProfit2(RevenueConfig rc, double total) {
		if(rc == null || rc.getRuleType() == null) {
			return 0.0;
		}
		if(rc.getRuleType().equals(RevenueDict.RuleType.ByCount)) {
			return rc.getRecommenderValue();
		} else {
			double tempV = BigDecimalUtil.div(rc.getRecommenderValue(),rc.getTotalValue());
			return BigDecimalUtil.mul(tempV, total, 2);
		}
	}
	
	public static Double getOtherProfit(RevenueConfig rc, double total) {
		if(rc == null || rc.getRuleType() == null) {
			return total;
		}
		if(rc.getRuleType().equals(RevenueDict.RuleType.ByCount)) {
			double tempV = BigDecimalUtil.sub(total, rc.getAgentValue());
			tempV = BigDecimalUtil.sub(tempV, rc.getCompanyValue());
			tempV = BigDecimalUtil.sub(tempV, rc.getPlatformValue());
			return tempV;
		} else {
			double temp = BigDecimalUtil.sub(total, getAgentProfit(rc, total));
			temp = BigDecimalUtil.sub(temp, getPropertyProfit(rc, total));
			temp = BigDecimalUtil.sub(temp, getPlatformProfit(rc, total));
			
			return temp;
		}
	}
	
//	public static Double getOtherProfit(RevenueConfig rc, double total) {
//		if(rc == null || rc.getRuleType() == null) {
//			return total;
//		}
//		if(rc.getRuleType() == 1) {
//			double tempV = total - rc.getAgentValue() - rc.getCompanyValue() - rc.getPlatformValue();
//			return tempV;
//		} else {
//			double temp = rc.getTotalValue() - rc.getAgentValue() - rc.getCompanyValue() - rc.getPlatformValue();
//			double tempV = BigDecimalUtil.div(temp, rc.getTotalValue());
//			return BigDecimalUtil.mul(tempV, total);
//		}
//	}

}
