/**   
* Filename:    OperationSignalWithDealEntity.java   
* @version:    1.0  
* Create at:   2015年5月6日 上午9:24:38   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年5月6日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.operation.entity;

import java.io.Serializable;

import com.cnfantasia.server.domainbase.operationConstantData.entity.OperationConstantData;

/**
 * Filename:    OperationSignalWithDealEntity.java
 * @version:    1.0.0
 * Create at:   2015年5月6日 上午9:24:38
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年5月6日       shiyl             1.0             1.0 Version
 */
public class OperationSignalWithDealEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private OperationConstantData operationConstantData;
	
	private String finalContent;
	
	public OperationSignalWithDealEntity(OperationConstantData operationConstantData,String finalContent){
		this.operationConstantData = operationConstantData;
		this.finalContent = finalContent;
	}
	
	public String getFinalContent() {
		return finalContent;
	}
	public void setFinalContent(String finalContent) {
		this.finalContent = finalContent;
	}

	public OperationConstantData getOperationConstantData() {
		return operationConstantData;
	}

	public void setOperationConstantData(OperationConstantData operationConstantData) {
		this.operationConstantData = operationConstantData;
	}
	
	
}
