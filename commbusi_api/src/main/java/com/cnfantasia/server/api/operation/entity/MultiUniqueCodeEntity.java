/**   
* Filename:    MultiUniqueCodeEntity.java   
* @version:    1.0  
* Create at:   2015年8月25日 下午7:32:22   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年8月25日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.operation.entity;

import java.util.ArrayList;
import java.util.List;

import com.cnfantasia.server.common.utils.StringUtils;


/**
 * Filename:    MultiUniqueCodeEntity.java
 * @version:    1.0.0
 * Create at:   2015年8月25日 下午7:32:22
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年8月25日       shiyl             1.0             1.0 Version
 */
public class MultiUniqueCodeEntity {
	/**国家编码*/
	private String countryUniqueCode;
	/**省编码*/
	private String provinceUniqueCode;
	/**市编码*/
	private String cityUniqueCode;
	/**行政区编码*/
	private String blockUniqueCode;
	/**小区编码*/
	private String gbUniqueCode;
	
	public MultiUniqueCodeEntity(){}
	
	public List<String> getTotalList(){
		List<String> paramList = new ArrayList<String>();
		if(!StringUtils.isEmpty(countryUniqueCode)){paramList.add(countryUniqueCode);}
		if(!StringUtils.isEmpty(provinceUniqueCode)){paramList.add(provinceUniqueCode);}
		if(!StringUtils.isEmpty(cityUniqueCode)){paramList.add(cityUniqueCode);}
		if(!StringUtils.isEmpty(blockUniqueCode)){paramList.add(blockUniqueCode);}
		if(!StringUtils.isEmpty(gbUniqueCode)){paramList.add(gbUniqueCode);}
		return paramList;
	}
	
	public String getCountryUniqueCode() {
		return countryUniqueCode;
	}
	public void setCountryUniqueCode(String countryUniqueCode) {
		this.countryUniqueCode = countryUniqueCode;
	}
	public String getProvinceUniqueCode() {
		return provinceUniqueCode;
	}
	public void setProvinceUniqueCode(String provinceUniqueCode) {
		this.provinceUniqueCode = provinceUniqueCode;
	}
	public String getCityUniqueCode() {
		return cityUniqueCode;
	}
	public void setCityUniqueCode(String cityUniqueCode) {
		this.cityUniqueCode = cityUniqueCode;
	}
	public String getBlockUniqueCode() {
		return blockUniqueCode;
	}
	public void setBlockUniqueCode(String blockUniqueCode) {
		this.blockUniqueCode = blockUniqueCode;
	}
	public String getGbUniqueCode() {
		return gbUniqueCode;
	}
	public void setGbUniqueCode(String gbUniqueCode) {
		this.gbUniqueCode = gbUniqueCode;
	}
	
	
}
