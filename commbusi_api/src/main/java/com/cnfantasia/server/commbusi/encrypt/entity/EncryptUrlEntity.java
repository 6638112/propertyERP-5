/**   
* Filename:    EncryptUrlEntity.java   
* @version:    1.0  
* Create at:   2016年2月19日 上午10:46:41   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2016年2月19日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.commbusi.encrypt.entity;

import java.util.List;

import com.cnfantasia.server.domainbase.encryptUrl.entity.EncryptUrl;

/**
 * Filename:    EncryptUrlEntity.java
 * @version:    1.0.0
 * Create at:   2016年2月19日 上午10:46:41
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2016年2月19日       shiyl             1.0             1.0 Version
 */
public class EncryptUrlEntity extends EncryptUrl{
	private static final long serialVersionUID = 1L;
	
	/**需要加密的列*/
	private List<String> columList;

	public List<String> getColumList() {
		return columList;
	}

	public void setColumList(List<String> columList) {
		this.columList = columList;
	}
	
	
	
}
