/**   
* Filename:    OperationConstantDataEntity.java   
* @version:    1.0  
* Create at:   2015年9月17日 下午3:22:55   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年9月17日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.operation.entity;

import com.cnfantasia.server.api.operation.constant.OperationDict;
import com.cnfantasia.server.domainbase.msPrizeActivity.entity.MsPrizeActivity;
import com.cnfantasia.server.domainbase.operationConstantData.entity.OperationConstantData;

/**
 * Filename:    OperationConstantDataEntity.java
 * @version:    1.0.0
 * Create at:   2015年9月17日 下午3:22:55
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年9月17日       shiyl             1.0             1.0 Version
 */
public class OperationConstantDataEntity extends OperationConstantData{
	private static final long serialVersionUID = 1L;
	
	public void freshData(MsPrizeActivity prizeActivity){
		if(prizeActivity!=null){
			if(OperationDict.OperationConstantData_dataType.TEXT.compareTo(this.getDataType())==0){
				this.prizeContent = prizeActivity.getShareText();
			}else if(OperationDict.OperationConstantData_dataType.PIC.compareTo(this.getDataType())==0){
				this.prizeContent = prizeActivity.getShareIcon();
				this.setSys0UpdTime(prizeActivity.getLastUpdTime());
			}
		}
	}
	
	/**抽奖总内容*/
	private String prizeContent;
	public String getPrizeContent() {
		return prizeContent;
	}
	
}
