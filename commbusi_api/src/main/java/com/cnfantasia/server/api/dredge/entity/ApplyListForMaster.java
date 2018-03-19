package com.cnfantasia.server.api.dredge.entity;

import java.math.BigDecimal;

/**
 * 提现申请列表（师傅）
 * @author wenfq
 *
 */
public class ApplyListForMaster {


//		withdrawMap.put("applyStatus", "申请成功");
//		withdrawMap.put("applyAmount", "300");
//		withdrawMap.put("billCount", "3笔");
//		withdrawMap.put("tkStatus", "提款中");
//		withdrawMap.put("tkTime", "2015-11-19 15:54");
		long id;
		String applyStatus = "申请成功";
		String billCount;
		BigDecimal applyAmount;
		String tkStatus;
		String tkTime ;
		
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getApplyStatus() {
			return applyStatus;
		}
		public void setApplyStatus(String applyStatus) {
			this.applyStatus = applyStatus;
		}
		public String getBillCount() {
			return billCount;
		}
		public void setBillCount(String billCount) {
			this.billCount = billCount;
		}
		public BigDecimal getApplyAmount() {
			return applyAmount.setScale(2, BigDecimal.ROUND_HALF_UP);
		}
		public void setApplyAmount(BigDecimal applyAmount) {
			this.applyAmount = applyAmount;
		}
		public String getTkStatus() {
			return tkStatus;
		}
		public void setTkStatus(String tkStatus) {
			this.tkStatus = tkStatus;
		}
		public String getTkTime() {
			return tkTime;
		}
		public void setTkTime(String tkTime) {
			this.tkTime = tkTime;
		}
}
