/**   
* Filename:    BasicSourceEntity.java   
* @version:    1.0  
* Create at:   2015年3月26日 上午8:01:09   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年3月26日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.redpoint.entity;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * Filename:    BasicSourceEntity.java
 * @version:    1.0.0
 * Create at:   2015年3月26日 上午8:01:09
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年3月26日       shiyl             1.0             1.0 Version
 */
public class BasicSourceEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/** 来源类型 */
	private Integer sourceType;
	/** 来源Id */
	private BigInteger sourceId;
	/** 记录操作描述,CUD */
	private String operationType;
	/**
	 * 截止日期
	 */
	private String expireTime;
	
	public BasicSourceEntity(Integer sourceType,BigInteger sourceId,String operationType){
		this.sourceType = sourceType;
		this.sourceId = sourceId;
		this.operationType = operationType;
	}
	
	public Integer getSourceType() {
		return sourceType;
	}
	public void setSourceType(Integer sourceType) {
		this.sourceType = sourceType;
	}
	public BigInteger getSourceId() {
		return sourceId;
	}
	public void setSourceId(BigInteger sourceId) {
		this.sourceId = sourceId;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}
}
