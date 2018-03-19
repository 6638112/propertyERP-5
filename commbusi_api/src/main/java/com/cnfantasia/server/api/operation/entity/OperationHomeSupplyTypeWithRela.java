/**   
* Filename:    OperationHomeSupplyTypeWithRela.java   
* @version:    1.0  
* Create at:   2015年8月18日 下午5:38:25   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年8月18日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.operation.entity;

import java.math.BigInteger;

import com.cnfantasia.server.domainbase.operationHomeSupplyType.entity.OperationHomeSupplyType;

/**
 * Filename:    OperationHomeSupplyTypeWithRela.java
 * @version:    1.0.0
 * Create at:   2015年8月18日 下午5:38:25
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年8月18日       shiyl             1.0             1.0 Version
 */
public class OperationHomeSupplyTypeWithRela extends OperationHomeSupplyType implements IRelationAble {
	private static final long serialVersionUID = 1L;
	/**关联关系(去除优先)=={"1":"包含","2":"去除"}*/
	private Integer relation;
	public Integer getRelation() {
		return relation;
	}

	public void setRelation(Integer relation) {
		this.relation = relation;
	}

	@Override
	public BigInteger getUniqueId() {
		return getId();
	}

	@Override
	public Integer getRelationValue() {
		return getRelation();
	}

}
