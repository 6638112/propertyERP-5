package com.cnfantasia.server.api.access.entity;

import java.math.BigDecimal;

/**
 * 月卡缴费成功后通知参数
 * 
 * @author liyulin
 * @version 1.0 2017年8月14日 下午1:55:31
 */
public class MFMonthCarNotifyParam {

	/** 小蜜蜂月卡ID */
	private String cardid;
	/** 购买月数 */
	private String cardnum;
	/** 物业ID */
	private String comid;
	/** 有效期开始时间（long） */
	private long startdate;
	/** 有效期结束时间（long） */
	private long enddate;
	/** 付费方式 (1-现金 ,2-刷卡, 3-转账) */
	private String paymode;
	/** 续费类型(1-充值续费 2-封存延期) */
	private String paytype;
	/** 购买金额（单位：元） */
	private BigDecimal totalprice;
	
	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}

	public String getCardnum() {
		return cardnum;
	}

	public void setCardnum(String cardnum) {
		this.cardnum = cardnum;
	}

	public String getComid() {
		return comid;
	}

	public void setComid(String comid) {
		this.comid = comid;
	}

	public long getStartdate() {
		return startdate;
	}

	public void setStartdate(long startdate) {
		this.startdate = startdate;
	}

	public long getEnddate() {
		return enddate;
	}

	public void setEnddate(long enddate) {
		this.enddate = enddate;
	}

	public String getPaymode() {
		return paymode;
	}

	public void setPaymode(String paymode) {
		this.paymode = paymode;
	}

	public String getPaytype() {
		return paytype;
	}

	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}

	public BigDecimal getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(BigDecimal totalprice) {
		this.totalprice = totalprice;
	}

}
