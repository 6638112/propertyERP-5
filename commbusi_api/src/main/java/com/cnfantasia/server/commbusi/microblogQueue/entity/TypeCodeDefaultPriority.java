/**   
* Filename:    TypeCodeDefaultPriority.java   
* @version:    1.0  
* Create at:   2015年8月31日 下午5:56:46   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年8月31日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.commbusi.microblogQueue.entity;

/**
 * Filename:    TypeCodeDefaultPriority.java
 * @version:    1.0.0
 * Create at:   2015年8月31日 下午5:56:46
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年8月31日       shiyl             1.0             1.0 Version
 */
public class TypeCodeDefaultPriority {
	/**编码*/
	private String code;
	/**优先级*/
	private Long defaultPriority;
	
	public TypeCodeDefaultPriority(String code,Long defaultPriority){
		this.code = code;
		this.defaultPriority = defaultPriority;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Long getDefaultPriority() {
		return defaultPriority;
	}
	public void setDefaultPriority(Long defaultPriority) {
		this.defaultPriority = defaultPriority;
	}
	
	
}
