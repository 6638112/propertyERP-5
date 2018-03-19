/**   
* Filename:    IOperationService.java   
* @version:    1.0  
* Create at:   2015年4月23日 上午9:13:43   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年4月23日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.operation.service;

import java.util.List;

import com.cnfantasia.server.api.operation.entity.OperationConstantDataEntity;
import com.cnfantasia.server.api.operation.entity.OperationSignalWithDealEntity;

/**
 * Filename:    IOperationService.java
 * @version:    1.0.0
 * Create at:   2015年4月23日 上午9:13:43
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年4月23日       shiyl             1.0             1.0 Version
 */
public interface IOperationService {

	/**
	 * 根据单个编码获取对应信息
	 * @param code
	 * @return
	 */
	public OperationConstantDataEntity getOperationSignal(String code);
	/**
	 * 根据单个编码获取对应信息
	 * @param code
	 * @return 返回处理后的数据，例如图片信息则返回图片全路径
	 */
	public OperationSignalWithDealEntity getOperationSignalWithDeal(String code);

	/**
	 * 根据多个编码获取对应信息
	 * @param codeList
	 * @return
	 */
	public List<OperationConstantDataEntity> getOperationMulti(List<String> codeList);
	
	/**
	 * 获取编码最终文本信息
	 * @param tmpEntity
	 * @return
	 */
	public String getOperationConstantFinalContent(OperationConstantDataEntity tmpEntity);
	
	public List<String> getOperationConstantFinalContents(OperationConstantDataEntity tmpEntity);
	
}
