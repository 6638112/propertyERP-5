/**   
* Filename:    AdvertiseModel.java   
* @version:    1.0  
* Create at:   2014年10月30日 上午2:48:33   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年10月30日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.advertise.entity;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * Filename:    AdvertiseModel.java
 * @version:    1.0.0
 * Create at:   2014年10月30日 上午2:48:33
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年10月30日       shiyl             1.0             1.0 Version
 */
public class AdvertiseModel implements Serializable{
	private static final long serialVersionUID = 1L;
	/**数据唯一标识*/
	private BigInteger id;
	/**数据类型*/
	private Integer dataType;
	/**图片地址*/
	private String picUrl;
	/**实体转json详情*/
	private String jsonEntity;
	
	public AdvertiseModel(){}
	public AdvertiseModel(BigInteger id,Integer dataType,String picUrl,String jsonEntity){
		this.id = id;
		this.dataType = dataType;
		this.picUrl = picUrl;
		this.jsonEntity = jsonEntity;
	}
	
	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("id=").append(id).append(";");
		sbf.append("dataType=").append(dataType).append(";");
		sbf.append("picUrl=").append(picUrl).append(";");
		sbf.append("jsonEntity=").append(jsonEntity).append(";");
		return sbf.toString();
	}
	
	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public Integer getDataType() {
		return dataType;
	}
	public void setDataType(Integer dataType) {
		this.dataType = dataType;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public String getJsonEntity() {
		return jsonEntity;
	}
	public void setJsonEntity(String jsonEntity) {
		this.jsonEntity = jsonEntity;
	}
	
}
