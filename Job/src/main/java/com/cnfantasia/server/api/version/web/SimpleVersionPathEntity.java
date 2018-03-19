package com.cnfantasia.server.api.version.web;
/**
 * 
 * @author shiyl
 *
 */
public class SimpleVersionPathEntity {
	/**版本*/
	private String version;
	/**下载地址*/
	private String downPath;
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getDownPath() {
		return downPath;
	}
	public void setDownPath(String downPath) {
		this.downPath = downPath;
	}
	
	public SimpleVersionPathEntity(){}
	public SimpleVersionPathEntity(String version,String downPath){
		this.version = version;
		this.downPath = downPath;
	}
	
}
