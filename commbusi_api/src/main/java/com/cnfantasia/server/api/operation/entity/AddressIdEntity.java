/**   
* Filename:    AddressIdEntity.java   
* @version:    1.0  
* Create at:   2015年7月7日 上午7:48:27   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年7月7日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.operation.entity;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * Filename:    AddressIdEntity.java
 * @version:    1.0.0
 * Create at:   2015年7月7日 上午7:48:27
 * Description:地址Id实体类
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年7月7日       shiyl             1.0             1.0 Version
 */
public class AddressIdEntity implements Serializable{
	private static final long serialVersionUID = -4051212467565450195L;
	private BigInteger countryId;
	private BigInteger provinceId;
	private BigInteger cityId;
	private BigInteger blockId;
	private BigInteger gbId;
	
	public AddressIdEntity(){}
	
	public BigInteger getCountryId() {
		return countryId;
	}
	public void setCountryId(BigInteger countryId) {
		this.countryId = countryId;
	}
	public BigInteger getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(BigInteger provinceId) {
		this.provinceId = provinceId;
	}
	public BigInteger getCityId() {
		return cityId;
	}
	public void setCityId(BigInteger cityId) {
		this.cityId = cityId;
	}
	public BigInteger getBlockId() {
		return blockId;
	}
	public void setBlockId(BigInteger blockId) {
		this.blockId = blockId;
	}
	public BigInteger getGbId() {
		return gbId;
	}
	public void setGbId(BigInteger gbId) {
		this.gbId = gbId;
	}
	
}
