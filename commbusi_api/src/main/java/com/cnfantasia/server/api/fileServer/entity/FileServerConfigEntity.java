/**   
* Filename:    FileServerConfigEntity.java   
* @version:    1.0  
* Create at:   2014年5月29日 上午2:23:53   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月29日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.fileServer.entity;

import com.alibaba.fastjson.JSON;

/**
 * Filename:    FileServerConfigEntity.java
 * @version:    1.0.0
 * Create at:   2014年5月29日 上午2:23:53
 * Description:文件服务器配置信息
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月29日       shiyl             1.0             1.0 Version
 */
public class FileServerConfigEntity {
	/**url访问根路径*/
	private String accessBaseUrl;
	/**协议类型*/
	private String protocolType;
	/**ip地址*/
	private String ip;
	/**端口*/
	private String port;
	/**用户名*/
	private String userName;
	/**密码*/
	private String password;
	/**本地文件夹根路径*/
	private String localFileDir;
	
	/**
	 * 构造方法
	 * @param accessBaseUrl url访问根路径
	 * @param protocolType 协议类型
	 * @param ip ip地址
	 * @param port 端口
	 * @param userName 用户名
	 * @param password 密码
	 */
	public FileServerConfigEntity(String accessBaseUrl,String protocolType,String ip, String port,String userName,String password
			,String localFileDir){
		this.accessBaseUrl = accessBaseUrl;
		this.protocolType = protocolType;
		this.ip = ip;
		this.port = port;
		this.userName = userName;
		this.password = password;
		this.localFileDir = localFileDir;
	}
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	public String getAccessBaseUrl() {
		return accessBaseUrl;
	}
	public void setAccessBaseUrl(String accessBaseUrl) {
		this.accessBaseUrl = accessBaseUrl;
	}
	public String getProtocolType() {
		return protocolType;
	}
	public void setProtocolType(String protocolType) {
		this.protocolType = protocolType;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLocalFileDir() {
		return localFileDir;
	}
	public void setLocalFileDir(String localFileDir) {
		this.localFileDir = localFileDir;
	}
	
	
	
}
