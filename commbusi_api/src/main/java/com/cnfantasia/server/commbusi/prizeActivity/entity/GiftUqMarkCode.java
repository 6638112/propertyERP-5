/**   
* Filename:    GiftUqMarkCode.java   
* @version:    1.0  
* Create at:   2015年9月16日 上午9:50:32   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年9月16日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.commbusi.prizeActivity.entity;

import java.io.Serializable;

/**
 * Filename:    GiftUqMarkCode.java
 * @version:    1.0.0
 * Create at:   2015年9月16日 上午9:50:32
 * Description:唯一标识
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年9月16日       shiyl             1.0             1.0 Version
 */
public class GiftUqMarkCode implements Serializable{
	private static final long serialVersionUID = 5944256681244941749L;
	/**唯一编码*/
	private String uqMark;
	/**兑换码*/
	private String valueCode;
	
	@Override
	public String toString() {
		StringBuffer sbf = new StringBuffer();
		sbf.append("奖品编码:");
		sbf.append(uqMark);
		sbf.append(",奖品兑换码:");
		sbf.append(valueCode);
		return sbf.toString();
	}
	public GiftUqMarkCode(){}
	public GiftUqMarkCode(String uqMark,String valueCode){
		this.uqMark = uqMark;
		this.valueCode = valueCode;
	}
	public String getUqMark() {
		return uqMark;
	}
	public void setUqMark(String uqMark) {
		this.uqMark = uqMark;
	}
	public String getValueCode() {
		return valueCode;
	}
	public void setValueCode(String valueCode) {
		this.valueCode = valueCode;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uqMark == null) ? 0 : uqMark.hashCode());
		result = prime * result
				+ ((valueCode == null) ? 0 : valueCode.hashCode());
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
		GiftUqMarkCode other = (GiftUqMarkCode) obj;
		if (uqMark == null) {
			if (other.uqMark != null)
				return false;
		} else if (!uqMark.equals(other.uqMark))
			return false;
		if (valueCode == null) {
			if (other.valueCode != null)
				return false;
		} else if (!valueCode.equals(other.valueCode))
			return false;
		return true;
	}
	
}
