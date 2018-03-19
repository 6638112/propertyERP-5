/**   
* Filename:    MultiSaIdEntity.java   
* @version:    1.0  
* Create at:   2015年8月25日 下午7:44:29   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年8月25日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.operation.entity;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Filename:    MultiSaIdEntity.java
 * @version:    1.0.0
 * Create at:   2015年8月25日 下午7:44:29
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年8月25日       shiyl             1.0             1.0 Version
 */
public class MultiSaIdEntity {
	/**国家编码*/
	private BigInteger countrySaId;
	/**省编码*/
	private BigInteger provinceSaId;
	/**市编码*/
	private BigInteger citySaId;
	/**行政区编码*/
	private BigInteger blockSaId;
	/**小区编码*/
	private BigInteger gbSaId;
	
	public MultiSaIdEntity(){}
	
	public List<BigInteger> getTotalList(){
		List<BigInteger> resList = new ArrayList<BigInteger>();
		if(countrySaId!=null){resList.add(countrySaId);}
		if(provinceSaId!=null){resList.add(provinceSaId);}
		if(citySaId!=null){resList.add(citySaId);}
		if(blockSaId!=null){resList.add(blockSaId);}
		if(gbSaId!=null){resList.add(gbSaId);}
		return resList;
	}
	
	public BigInteger getCountrySaId() {
		return countrySaId;
	}
	public void setCountrySaId(BigInteger countrySaId) {
		this.countrySaId = countrySaId;
	}
	public BigInteger getProvinceSaId() {
		return provinceSaId;
	}
	public void setProvinceSaId(BigInteger provinceSaId) {
		this.provinceSaId = provinceSaId;
	}
	public BigInteger getCitySaId() {
		return citySaId;
	}
	public void setCitySaId(BigInteger citySaId) {
		this.citySaId = citySaId;
	}
	public BigInteger getBlockSaId() {
		return blockSaId;
	}
	public void setBlockSaId(BigInteger blockSaId) {
		this.blockSaId = blockSaId;
	}
	public BigInteger getGbSaId() {
		return gbSaId;
	}
	public void setGbSaId(BigInteger gbSaId) {
		this.gbSaId = gbSaId;
	}
	
	
}
