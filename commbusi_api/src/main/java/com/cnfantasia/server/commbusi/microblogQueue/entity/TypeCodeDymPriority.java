/**   
* Filename:    TypeCodeDymPriority.java   
* @version:    1.0  
* Create at:   2015年8月31日 下午5:21:52   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年8月31日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.commbusi.microblogQueue.entity;

import com.cnfantasia.server.commbusi.microblogQueue.util.TypePriorityUtil;

/**
 * Filename:    TypeCodeDymPriority.java
 * @version:    1.0.0
 * Create at:   2015年8月31日 下午5:21:52
 * Description:编码及优先级实体类别
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年8月31日       shiyl             1.0             1.0 Version
 */
public class TypeCodeDymPriority {
	/**编码*/
	private String code;
	/**优先级*/
	private Long priority;
	/**已发送次数*/
	private Long sendCount;
	
	public static TypeCodeDymPriority newInstance(String typeCode,Long defaultPriority,Long sendCount){
		TypeCodeDymPriority resTypeCodeDymPriority = new TypeCodeDymPriority(typeCode, defaultPriority, sendCount);
		return resTypeCodeDymPriority;
	}
	
//	public static TypeCodeDymPriority newInstance(TypeCodeDefaultPriority obj,Long sendCount){
//		String typeCode = obj.getCode();
//		Long defaultPriority = obj.getDefaultPriority();
//		TypeCodeDymPriority resTypeCodeDymPriority = new TypeCodeDymPriority(typeCode, defaultPriority, sendCount);
//		return resTypeCodeDymPriority;
//	}
	
	private TypeCodeDymPriority(String typeCode,Long defaultPriority,Long sendCount){
		Long tmpSendCount = sendCount==null?0L:sendCount;
		this.code = typeCode;
		this.priority = TypePriorityUtil.getDymPriority(typeCode, defaultPriority, tmpSendCount);
		this.sendCount = tmpSendCount;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Long getPriority() {
		return priority;
	}
	public void setPriority(Long priority) {
		this.priority = priority;
	}

	public Long getSendCount() {
		return sendCount;
	}

	public void setSendCount(Long sendCount) {
		this.sendCount = sendCount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((priority == null) ? 0 : priority.hashCode());
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
		TypeCodeDymPriority other = (TypeCodeDymPriority) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (priority == null) {
			if (other.priority != null)
				return false;
		} else if (!priority.equals(other.priority))
			return false;
		return true;
	}
	
	
}
