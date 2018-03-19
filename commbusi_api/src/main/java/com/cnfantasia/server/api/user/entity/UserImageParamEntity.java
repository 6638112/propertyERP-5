/**   
* Filename:    UserImageParamEntity.java   
* @version:    1.0  
* Create at:   2014年5月26日 上午8:36:33   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月26日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.user.entity;

import java.util.List;

import com.alibaba.fastjson.JSON;

/**
 * Filename:    UserImageParamEntity.java
 * @version:    1.0.0
 * Create at:   2014年5月26日 上午8:36:33
 * Description:用户图片上传的参数配置
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月26日       shiyl             1.0             1.0 Version
 */
public class UserImageParamEntity {
	/**图片存放对应文件服务器的根路径*/
	private String basePath;
	/**图片大小,单位:字节*/
	private Long maxSize;
	/**支持的类型*/
	private List<String> supportFormates;
	/**
	 * 构造方法
	 * @param basePath 图片存放对应文件服务器的根路径
	 * @param maxSize 图片大小,单位:K 
	 * @param supportFormates 支持的类型
	 */
	public UserImageParamEntity(String basePath,Long maxSize,List<String> supportFormates){
		this.basePath = basePath;
		this.maxSize = maxSize;
		this.supportFormates = supportFormates;
	}
	
	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
	public String getBasePath() {
		return basePath;
	}
	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}
	public Long getMaxSize() {
		return maxSize;
	}
	public void setMaxSize(Long maxSize) {
		this.maxSize = maxSize;
	}
	public List<String> getSupportFormates() {
		return supportFormates;
	}
	public void setSupportFormates(List<String> supportFormates) {
		this.supportFormates = supportFormates;
	}
	
}
