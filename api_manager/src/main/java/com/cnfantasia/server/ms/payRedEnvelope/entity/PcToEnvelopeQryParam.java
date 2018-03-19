package com.cnfantasia.server.ms.payRedEnvelope.entity;

/**
 * 代扣卡转粮票一览表搜索参数
 * 
 * @author liyulin
 * @version 1.0 2016年11月7日 下午1:48:56
 */
public class PcToEnvelopeQryParam {
	private String userId;
	private String inTimeStart;
	private String inIimeEnd;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getInTimeStart() {
		return inTimeStart;
	}

	public void setInTimeStart(String inTimeStart) {
		this.inTimeStart = inTimeStart;
	}

	public String getInIimeEnd() {
		return inIimeEnd;
	}

	public void setInIimeEnd(String inIimeEnd) {
		this.inIimeEnd = inIimeEnd;
	}

}
