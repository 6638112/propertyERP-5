package com.cnfantasia.server.commbusi.microblogQueue.entity;

import java.math.BigInteger;

/**
 * 小区Id及距离缴费时间的实体类
 * @author shiyl
 *
 */
public class GbIdAndPayDistance {
	/**小区Id*/
	private BigInteger gbId;
	/**物业缴费截止时间对应的天数减去当前时间的天数，结果*/
	private Integer dayCountForPayEnd2Now;
	public BigInteger getGbId() {
		return gbId;
	}
	public void setGbId(BigInteger gbId) {
		this.gbId = gbId;
	}
	public Integer getDayCountForPayEnd2Now() {
		return dayCountForPayEnd2Now;
	}
	public void setDayCountForPayEnd2Now(Integer dayCountForPayEnd2Now) {
		this.dayCountForPayEnd2Now = dayCountForPayEnd2Now;
	}
	
	
}
