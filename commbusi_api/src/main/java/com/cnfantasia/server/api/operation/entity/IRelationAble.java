/**   
* Filename:    IRelationAble.java   
* @version:    1.0  
* Create at:   2015年8月18日 下午4:36:32   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年8月18日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.operation.entity;

import java.math.BigInteger;

/**
 * Filename:    IRelationAble.java
 * @version:    1.0.0
 * Create at:   2015年8月18日 下午4:36:32
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年8月18日       shiyl             1.0             1.0 Version
 */
public interface IRelationAble {
	/**获取唯一Id*/
	public BigInteger getUniqueId();
	
	/**获取关系*/
	public Integer getRelationValue();
}
