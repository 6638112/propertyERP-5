/**   
* Filename:    CodeIdLevelEntity.java   
* @version:    1.0  
* Create at:   2015年8月13日 下午3:22:29   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年8月13日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.operation.entity;

import java.math.BigInteger;

/**
 * Filename:    CodeIdLevelEntity.java
 * @version:    1.0.0
 * Create at:   2015年8月13日 下午3:22:29
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年8月13日       shiyl             1.0             1.0 Version
 */
public class CodeIdLevelEntity {
	/**运营地址Id*/
	private BigInteger codeId;
	/**运营地址Level*/
	private Integer level;
	
	public CodeIdLevelEntity(BigInteger codeId,Integer level){
		this.codeId = codeId;
		this.level = level;
	}
	
	public BigInteger getCodeId() {
		return codeId;
	}
	public void setCodeId(BigInteger codeId) {
		this.codeId = codeId;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	
	
}
