/**   
* Filename:    AddAndDelIdsEntity.java   
* @version:    1.0  
* Create at:   2014年9月16日 上午11:13:42   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年9月16日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Set;

/**
 * Filename:    AddAndDelIdsEntity.java
 * @version:    1.0.0
 * Create at:   2014年9月16日 上午11:13:42
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年9月16日       shiyl             1.0             1.0 Version
 */
public class AddAndDelIdsEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	private Set<BigInteger> toAddIds;
	private Set<BigInteger> toDelIds;
	private Set<BigInteger> noNeedDoneIds;
	
	public AddAndDelIdsEntity(Set<BigInteger> toAddIds,Set<BigInteger> toDelIds,Set<BigInteger> noNeedDoneIds){
		this.toAddIds = toAddIds;
		this.toDelIds = toDelIds;
		this.noNeedDoneIds = noNeedDoneIds;
	}

	public Set<BigInteger> getToAddIds() {
		return toAddIds;
	}

	public void setToAddIds(Set<BigInteger> toAddIds) {
		this.toAddIds = toAddIds;
	}

	public Set<BigInteger> getToDelIds() {
		return toDelIds;
	}

	public void setToDelIds(Set<BigInteger> toDelIds) {
		this.toDelIds = toDelIds;
	}

	public Set<BigInteger> getNoNeedDoneIds() {
		return noNeedDoneIds;
	}

	public void setNoNeedDoneIds(Set<BigInteger> noNeedDoneIds) {
		this.noNeedDoneIds = noNeedDoneIds;
	}
	
	
}
