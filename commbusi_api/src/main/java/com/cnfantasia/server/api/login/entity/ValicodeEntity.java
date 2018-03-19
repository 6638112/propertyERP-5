/**   
* Filename:    ValicodeEntity.java   
* @version:    1.0  
* Create at:   2014年5月24日 上午9:48:22   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年5月24日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.login.entity;

import java.io.Serializable;


/**
 * Filename:    ValicodeEntity.java
 * @version:    1.0.0
 * Create at:   2014年5月24日 上午9:48:22
 * Description:验证码实体类
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年5月24日       shiyl             1.0             1.0 Version
 */
public class ValicodeEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	/**手机号*/
	private String phone;
	/**类型*/
	private Integer type;
	/**取值*/
	private String value;
	/**创建时间*/
	private Long createTime;
	/**错误次数*/
	private Integer errCount;
	
	public ValicodeEntity(Integer type,String phone,String value){
		this.type = type;
		this.phone = phone;
		this.value = value;
		this.createTime = System.currentTimeMillis();
		this.errCount = 0;
	}
	public void addErrCount(){
		this.errCount = errCount + 1;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}
	
	public Integer getErrCount() {
		return errCount;
	}

	public void setErrCount(Integer errCount) {
		this.errCount = errCount;
	}
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((phone == null) ? 0 : phone.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		ValicodeEntity other = (ValicodeEntity) obj;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.equals(other.phone))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	
}
