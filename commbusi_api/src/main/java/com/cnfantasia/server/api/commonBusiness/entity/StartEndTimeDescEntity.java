/**   
* Filename:    StartEndTimeDescEntity.java   
* @version:    1.0  
* Create at:   2014年12月2日 下午2:19:25   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年12月2日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.commonBusiness.entity;

/**
 * Filename:    StartEndTimeDescEntity.java
 * @version:    1.0.0
 * Create at:   2014年12月2日 下午2:19:25
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年12月2日       shiyl             1.0             1.0 Version
 */
public class StartEndTimeDescEntity {
	private String startDesc;
	private String endDesc;
	public StartEndTimeDescEntity(String startDesc,String endDesc){
		this.startDesc = startDesc;
		this.endDesc = endDesc;
	}
	public String getStartDesc() {
		return startDesc;
	}
	public void setStartDesc(String startDesc) {
		this.startDesc = startDesc;
	}
	public String getEndDesc() {
		return endDesc;
	}
	public void setEndDesc(String endDesc) {
		this.endDesc = endDesc;
	}
	
}
