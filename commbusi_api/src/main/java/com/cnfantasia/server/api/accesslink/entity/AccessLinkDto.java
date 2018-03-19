package com.cnfantasia.server.api.accesslink.entity;

/**
 * 客户端连接Dto
 * 
 * @author liyulin
 * @version 1.0 2016年6月28日 下午5:29:31
 */
public class AccessLinkDto {

	/** session id */
	private long ioSessionId;
	/** 小区名称 */
	private String gbName;
	/** 连接时间 */
	private String linkTime;
	/** 服务端ip地址 */
	private String localIp;
	/** 客户端ip地址 */
	private String remoteIp;
	/** 版本号 */
	private Object version;

	public AccessLinkDto() {
		super();
	}

	public AccessLinkDto(long ioSessionId, String gbName, String linkTime,
			String localIp, String remoteIp, Object version) {
		super();
		this.ioSessionId = ioSessionId;
		this.gbName = gbName;
		this.linkTime = linkTime;
		this.localIp = localIp;
		this.remoteIp = remoteIp;
		this.version = version;
	}

	public long getIoSessionId() {
		return ioSessionId;
	}

	public void setIoSessionId(long ioSessionId) {
		this.ioSessionId = ioSessionId;
	}

	public String getGbName() {
		return gbName;
	}

	public void setGbName(String gbName) {
		this.gbName = gbName;
	}

	public String getLinkTime() {
		return linkTime;
	}

	public void setLinkTime(String linkTime) {
		this.linkTime = linkTime;
	}

	public String getLocalIp() {
		return localIp;
	}

	public void setLocalIp(String localIp) {
		this.localIp = localIp;
	}

	public String getRemoteIp() {
		return remoteIp;
	}

	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}

	public Object getVersion() {
		return version;
	}

	public void setVersion(Object version) {
		this.version = version;
	}

}