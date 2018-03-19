package com.cnfantasia.server.ms.logger.entity;

import java.math.BigInteger;

import com.cnfantasia.server.domain.pub.BaseEntity;


public class ManagerLogger extends BaseEntity{
	private static final long serialVersionUID = 1L;
	/**  */
	private BigInteger id;
	/** 用户Id */
	private BigInteger userId;
	/** 登录账号 */
	private String userAccNo;
	/** 登录账号类型 */
	private Long userAccType;
	/** 请求的action */
	private String actionUrlTail;
	/** 请求的action全路径 */
	private String actionUrlAll;
	/** 请求的参数信息 */
	private String requestParams;
	/** 操作描述 */
	private String actionDesc;
	/** 操作结果状态 */
	private String actionResStatus;
	/** 返回结果码 */
	private String actionResCode;
	/** 返回结果信息 */
	private String actionResMsg;
	/** 是否为未处理的异常 */
	private Integer isUndefinedException;
	/** 异常堆栈信息 */
	private String excepStackInfo;
	/** 客户IP地址 */
	private String ip;
	/** 设备Id */
	private String deviceId;
	/** 操作系统相关信息 */
	private String os;
	/** 操作系统版本 */
	private String osVer;
	/**  */
	private BigInteger tCommSysUrlFId;
	/** 请求发起时间 */
	private String reqStartTime;
	/** 请求处理时间,毫秒 */
	private Long reqDealTime;
	/** 请求结束时间 */
	private String reqEndTime;
	/** 当前交易唯一标识 */
	private String transNo;
	/** 交易结果数据 */
	private String responseData;
	/** 渠道Id */
	private String channel;
	/** 版本号 */
	private String version;

	public ManagerLogger(){
	}
	public ManagerLogger(ManagerLogger arg){
		this.id = arg.getId();
		this.userId = arg.getUserId();
		this.userAccNo = arg.getUserAccNo();
		this.userAccType = arg.getUserAccType();
		this.actionUrlTail = arg.getActionUrlTail();
		this.actionUrlAll = arg.getActionUrlAll();
		this.requestParams = arg.getRequestParams();
		this.actionDesc = arg.getActionDesc();
		this.actionResStatus = arg.getActionResStatus();
		this.actionResCode = arg.getActionResCode();
		this.actionResMsg = arg.getActionResMsg();
		this.isUndefinedException = arg.getIsUndefinedException();
		this.excepStackInfo = arg.getExcepStackInfo();
		this.ip = arg.getIp();
		this.deviceId = arg.getDeviceId();
		this.os = arg.getOs();
		this.osVer = arg.getOsVer();
		this.tCommSysUrlFId = arg.gettCommSysUrlFId();
		this.reqStartTime = arg.getReqStartTime();
		this.reqDealTime = arg.getReqDealTime();
		this.reqEndTime = arg.getReqEndTime();
		this.transNo = arg.getTransNo();
		this.responseData = arg.getResponseData();
		this.channel = arg.getChannel();
		this.version = arg.getVersion();
		this.sys0AddTime = arg.getSys0AddTime();
		this.sys0UpdTime = arg.getSys0UpdTime();
		this.sys0DelTime = arg.getSys0DelTime();
		this.sys0AddUser = arg.getSys0AddUser();
		this.sys0UpdUser = arg.getSys0UpdUser();
		this.sys0DelUser = arg.getSys0DelUser();
		this.sys0DelState = arg.getSys0DelState();

	}
	/**
	 * 
	 * @param id 
	 * @param userId 用户Id
	 * @param defaultRoomId 用户当前默认门牌Id
	 * @param userAccNo 登录账号
	 * @param userAccType 登录账号类型
	 * @param actionUrlTail 请求的action
	 * @param actionUrlAll 请求的action全路径
	 * @param requestParams 请求的参数信息
	 * @param actionDesc 操作描述
	 * @param actionResStatus 操作结果状态
	 * @param actionResCode 返回结果码
	 * @param actionResMsg 返回结果信息
	 * @param isUndefinedException 是否为未处理的异常
	 * @param excepStackInfo 异常堆栈信息
	 * @param ip 客户IP地址
	 * @param deviceId 设备Id
	 * @param os 操作系统相关信息
	 * @param osVer 操作系统版本
	 * @param tCommSysUrlFId 
	 * @param reqStartTime 请求发起时间
	 * @param reqDealTime 请求处理时间,毫秒
	 * @param reqEndTime 请求结束时间
	 * @param transNo 当前交易唯一标识
	 * @param responseData 交易结果数据
	 * @param channel 渠道Id
	 * @param version 版本号
	 * @param sys0AddTime 新增时间
	 * @param sys0UpdTime 更新时间
	 * @param sys0DelTime 删除时间
	 * @param sys0AddUser 新增者
	 * @param sys0UpdUser 修改者
	 * @param sys0DelUser 删除者
	 * @param sys0DelState 记录状态
	 */

	public ManagerLogger(BigInteger id,BigInteger userId,BigInteger defaultRoomId,String userAccNo,Long userAccType,String actionUrlTail,String actionUrlAll,String requestParams,String actionDesc,String actionResStatus,String actionResCode,String actionResMsg,Integer isUndefinedException,String excepStackInfo,String ip,String deviceId,String os,String osVer,BigInteger tCommSysUrlFId,String reqStartTime,Long reqDealTime,String reqEndTime,String transNo,String responseData,String channel,String version,String sys0AddTime,String sys0UpdTime,String sys0DelTime,BigInteger sys0AddUser,BigInteger sys0UpdUser,BigInteger sys0DelUser,Integer sys0DelState){
		this.id = id;
		this.userId = userId;
		this.userAccNo = userAccNo;
		this.userAccType = userAccType;
		this.actionUrlTail = actionUrlTail;
		this.actionUrlAll = actionUrlAll;
		this.requestParams = requestParams;
		this.actionDesc = actionDesc;
		this.actionResStatus = actionResStatus;
		this.actionResCode = actionResCode;
		this.actionResMsg = actionResMsg;
		this.isUndefinedException = isUndefinedException;
		this.excepStackInfo = excepStackInfo;
		this.ip = ip;
		this.deviceId = deviceId;
		this.os = os;
		this.osVer = osVer;
		this.tCommSysUrlFId = tCommSysUrlFId;
		this.reqStartTime = reqStartTime;
		this.reqDealTime = reqDealTime;
		this.reqEndTime = reqEndTime;
		this.transNo = transNo;
		this.responseData = responseData;
		this.channel = channel;
		this.version = version;
		this.sys0AddTime = sys0AddTime;
		this.sys0UpdTime = sys0UpdTime;
		this.sys0DelTime = sys0DelTime;
		this.sys0AddUser = sys0AddUser;
		this.sys0UpdUser = sys0UpdUser;
		this.sys0DelUser = sys0DelUser;
		this.sys0DelState = sys0DelState;

	}

	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("id=").append(id).append(";");
		sbf.append("userId=").append(userId).append(";");
		sbf.append("userAccNo=").append(userAccNo).append(";");
		sbf.append("userAccType=").append(userAccType).append(";");
		sbf.append("actionUrlTail=").append(actionUrlTail).append(";");
		sbf.append("actionUrlAll=").append(actionUrlAll).append(";");
		sbf.append("requestParams=").append(requestParams).append(";");
		sbf.append("actionDesc=").append(actionDesc).append(";");
		sbf.append("actionResStatus=").append(actionResStatus).append(";");
		sbf.append("actionResCode=").append(actionResCode).append(";");
		sbf.append("actionResMsg=").append(actionResMsg).append(";");
		sbf.append("isUndefinedException=").append(isUndefinedException).append(";");
		sbf.append("excepStackInfo=").append(excepStackInfo).append(";");
		sbf.append("ip=").append(ip).append(";");
		sbf.append("deviceId=").append(deviceId).append(";");
		sbf.append("os=").append(os).append(";");
		sbf.append("osVer=").append(osVer).append(";");
		sbf.append("tCommSysUrlFId=").append(tCommSysUrlFId).append(";");
		sbf.append("reqStartTime=").append(reqStartTime).append(";");
		sbf.append("reqDealTime=").append(reqDealTime).append(";");
		sbf.append("reqEndTime=").append(reqEndTime).append(";");
		sbf.append("transNo=").append(transNo).append(";");
		sbf.append("responseData=").append(responseData).append(";");
		sbf.append("channel=").append(channel).append(";");
		sbf.append("version=").append(version).append(";");
		sbf.append("sys0AddTime=").append(sys0AddTime).append(";");
		sbf.append("sys0UpdTime=").append(sys0UpdTime).append(";");
		sbf.append("sys0DelTime=").append(sys0DelTime).append(";");
		sbf.append("sys0AddUser=").append(sys0AddUser).append(";");
		sbf.append("sys0UpdUser=").append(sys0UpdUser).append(";");
		sbf.append("sys0DelUser=").append(sys0DelUser).append(";");
		sbf.append("sys0DelState=").append(sys0DelState).append(";");
		return sbf.toString();

	}
	
	public BigInteger getId() {
		return id;
	}
	public void setId(BigInteger id) {
		this.id = id;
	}
	public BigInteger getUserId() {
		return userId;
	}
	public void setUserId(BigInteger userId) {
		this.userId = userId;
	}
	public String getUserAccNo() {
		return userAccNo;
	}
	public void setUserAccNo(String userAccNo) {
		this.userAccNo = userAccNo;
	}
	public Long getUserAccType() {
		return userAccType;
	}
	public void setUserAccType(Long userAccType) {
		this.userAccType = userAccType;
	}
	public String getActionUrlTail() {
		return actionUrlTail;
	}
	public void setActionUrlTail(String actionUrlTail) {
		this.actionUrlTail = actionUrlTail;
	}
	public String getActionUrlAll() {
		return actionUrlAll;
	}
	public void setActionUrlAll(String actionUrlAll) {
		this.actionUrlAll = actionUrlAll;
	}
	public String getRequestParams() {
		return requestParams;
	}
	public void setRequestParams(String requestParams) {
		this.requestParams = requestParams;
	}
	public String getActionDesc() {
		return actionDesc;
	}
	public void setActionDesc(String actionDesc) {
		this.actionDesc = actionDesc;
	}
	public String getActionResStatus() {
		return actionResStatus;
	}
	public void setActionResStatus(String actionResStatus) {
		this.actionResStatus = actionResStatus;
	}
	public String getActionResCode() {
		return actionResCode;
	}
	public void setActionResCode(String actionResCode) {
		this.actionResCode = actionResCode;
	}
	public String getActionResMsg() {
		return actionResMsg;
	}
	public void setActionResMsg(String actionResMsg) {
		this.actionResMsg = actionResMsg;
	}
	public Integer getIsUndefinedException() {
		return isUndefinedException;
	}
	public void setIsUndefinedException(Integer isUndefinedException) {
		this.isUndefinedException = isUndefinedException;
	}
	public String getExcepStackInfo() {
		return excepStackInfo;
	}
	public void setExcepStackInfo(String excepStackInfo) {
		this.excepStackInfo = excepStackInfo;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getOs() {
		return os;
	}
	public void setOs(String os) {
		this.os = os;
	}
	public String getOsVer() {
		if(osVer != null && osVer.length() > 45) {
			return osVer.substring(0, 45);
		}
		return osVer;
	}
	public void setOsVer(String osVer) {
		this.osVer = osVer;
	}
	public BigInteger gettCommSysUrlFId() {
		return tCommSysUrlFId;
	}
	public void settCommSysUrlFId(BigInteger tCommSysUrlFId) {
		this.tCommSysUrlFId = tCommSysUrlFId;
	}
	public String getReqStartTime() {
		return reqStartTime;
	}
	public void setReqStartTime(String reqStartTime) {
		this.reqStartTime = reqStartTime;
	}
	public Long getReqDealTime() {
		return reqDealTime;
	}
	public void setReqDealTime(Long reqDealTime) {
		this.reqDealTime = reqDealTime;
	}
	public String getReqEndTime() {
		return reqEndTime;
	}
	public void setReqEndTime(String reqEndTime) {
		this.reqEndTime = reqEndTime;
	}
	public String getTransNo() {
		return transNo;
	}
	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}
	public String getResponseData() {
		return responseData;
	}
	public void setResponseData(String responseData) {
		this.responseData = responseData;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());

		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ManagerLogger other = (ManagerLogger) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;

		return true;
	}
}
