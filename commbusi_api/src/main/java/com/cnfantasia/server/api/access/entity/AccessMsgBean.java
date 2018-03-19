package com.cnfantasia.server.api.access.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 消息实体类
 * 
 * @author liyulin
 * @version 1.0 2016年6月27日 下午3:51:50
 */
public class AccessMsgBean implements Serializable {

	private static final long serialVersionUID = -431019632672893665L;
	private long ioSessionId;
	private BigInteger gbId;
	private String carNum;
	private String payDate;
	private BigDecimal fee;
	private String uuid;
	/** 充值时间 */
	private String chargeTime;
	/** 实付（单位：元） */
	private double actualFee;
	/** 应收（单位：元） */
	private double receivableFee;
	/** 月卡缴费月数 */
	private int payNum;
	/** 有效开始时间 */
	private String starttime;
	/** 有效截至时间 */
	private String endtime;
	/** 客户端ip */
	private String remoteIp;
	/** 消息类型==>{1：“查询费用”；2：“发送缴费成功”；3：“查询所有连接”；4：“断开连接”；5：“加入黑名单”；6：“移除黑名单”} */
	private String msgType;
	/** websocket消息 */
	private String message;
	/** 停车场id */
	private Integer parkId;
	/** websocket 命令 */
	private int command;
	private BigInteger cnplId;

	public AccessMsgBean() {
		super();
	}

	public String getRemoteIp() {
		return remoteIp;
	}

	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}

	public long getIoSessionId() {
		return ioSessionId;
	}

	public void setIoSessionId(long ioSessionId) {
		this.ioSessionId = ioSessionId;
	}

	/**
	 * 消息类型==>{1：“查询费用”；2：“发送缴费成功”；3：“查询所有连接”；4：“断开连接”；5：“加入黑名单”；6：“移除黑名单”}
	 * 
	 * @return
	 */
	public String getMsgType() {
		return msgType;
	}

	/**
	 * 消息类型==>{1：“查询费用”；2：“发送缴费成功”；3：“查询所有连接”；4：“断开连接”；5：“加入黑名单”；6：“移除黑名单”}
	 * 
	 * @param msgType
	 */
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public BigInteger getGbId() {
		return gbId;
	}

	public void setGbId(BigInteger gbId) {
		this.gbId = gbId;
	}

	public String getCarNum() {
		return carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	public String getPayDate() {
		return payDate;
	}

	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public int getPayNum() {
		return payNum;
	}

	public void setPayNum(int payNum) {
		this.payNum = payNum;
	}

	public String getChargeTime() {
		return chargeTime;
	}

	public void setChargeTime(String chargeTime) {
		this.chargeTime = chargeTime;
	}

	public double getActualFee() {
		return actualFee;
	}

	public void setActualFee(double actualFee) {
		this.actualFee = actualFee;
	}

	public double getReceivableFee() {
		return receivableFee;
	}

	public void setReceivableFee(double receivableFee) {
		this.receivableFee = receivableFee;
	}

	public String getStarttime() {
		return starttime;
	}

	public void setStarttime(String starttime) {
		this.starttime = starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public void setEndtime(String endtime) {
		this.endtime = endtime;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getParkId() {
		return parkId;
	}

	public void setParkId(Integer parkId) {
		this.parkId = parkId;
	}

	public int getCommand() {
		return command;
	}

	public void setCommand(int command) {
		this.command = command;
	}

	public BigInteger getCnplId() {
		return cnplId;
	}

	public void setCnplId(BigInteger cnplId) {
		this.cnplId = cnplId;
	}

	@Override
	public String toString() {
		return "AccessMsgBean [ioSessionId=" + ioSessionId + ", gbId=" + gbId
				+ ", carNum=" + carNum + ", payDate=" + payDate + ", fee="
				+ fee + ", uuid=" + uuid + ", chargeTime=" + chargeTime
				+ ", actualFee=" + actualFee + ", receivableFee="
				+ receivableFee + ", payNum=" + payNum + ", starttime="
				+ starttime + ", endtime=" + endtime + ", remoteIp=" + remoteIp
				+ ", msgType=" + msgType + ", message=" + message + ", parkId="
				+ parkId + ", command=" + command + ", cnplId=" + cnplId + "]";
	}

}
