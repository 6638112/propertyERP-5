/**   
* Filename:    QQApplyEntity.java   
* @version:    1.0  
* Create at:   2014年5月23日 上午12:52:05   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月23日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.login.entity;

import java.net.URL;

import com.cnfantasia.server.common.exception.ValidateRuntimeException;

/**
 * Filename:    QQApplyEntity.java
 * @version:    1.0.0
 * Create at:   2014年5月23日 上午12:52:05
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月23日       shiyl             1.0             1.0 Version
 */
public class QQApplyEntity extends ApplyBaseEntity{
	private static final long serialVersionUID = 1L;
	public static final String DEFAULT_PF = "qzone";
	public static final String DEFAULT_FORMAT = "json";
//	public static final String API_NAME_GET_USERINFO = "/v3/user/get_info";
//	public static final String API_NAME_GET_USERINFO = "/user/get_info";
	public static final String API_NAME_GET_USERINFO = "/user/get_user_info";
	/**协议*/
	private String protocol;
	/**主机地址*/
	private String serverName;
	/**平台*/
	private String pf;
	/**返回数据格式*/
	private String format;
	
	/**
	 * QQ应用配置信息的构造方法
	 * @param url 验证服务的url
	 * @param appKey 申请的应用Key
	 * @param secret 申请的key对应的密钥
	 */
	public QQApplyEntity(String url,String appKey,String secret){
		super(url, appKey, secret);
		this.pf = DEFAULT_PF;
		this.format = DEFAULT_FORMAT;
		try {
			URL hp = new URL(url);
			this.protocol = hp.getProtocol();
			if(-1!=hp.getPort()){
				this.serverName = hp.getHost()+":"+hp.getPort();
			}else{
				this.serverName = hp.getHost();
			}
		} catch (Exception e) {
			throw new ValidateRuntimeException("QQApplyEntity.construct.urlParse.error");
		}
	}
	
	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("protocol=").append(protocol).append(";");
		sbf.append("serverName=").append(serverName).append(";");
		sbf.append("pf=").append(pf).append(";");
		sbf.append("format=").append(format).append(";");
		sbf.append("url=").append(url).append(";");
		sbf.append("appKey=").append(appKey).append(";");
		sbf.append("secret=").append(secret).append(";");
		return sbf.toString();
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getPf() {
		return pf;
	}

	public void setPf(String pf) {
		this.pf = pf;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
	
//public static void main(String[] args) throws MalformedURLException, URISyntaxException {
//QQApplyEntity aaApplyEntity = new QQApplyEntity("http://yuncode.net:8080", "123123", "fwewtgwewq");
//System.out.println(aaApplyEntity);
//URL hp = new URL("http://yuncode.net");
//System.out.println("Protocol: " + hp.getProtocol()); // 协议
//System.out.println("Port: " + hp.getPort()); // 端口
//System.out.println("Host: " + hp.getHost()); // 主机
//System.out.println("File: " + hp.getFile()); // url对应的文件名
//System.out.println("Ext:" + hp.toExternalForm());
//System.out.println("toURI:" + hp.toURI());
//}
	
}
