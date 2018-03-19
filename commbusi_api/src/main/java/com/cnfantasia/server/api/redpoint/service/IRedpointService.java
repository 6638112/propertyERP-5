/**   
* Filename:    IRedpointService.java   
* @version:    1.0  
* Create at:   2015年3月25日 上午7:56:50   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年3月25日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.redpoint.service;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.commonBusiness.entity.CommUserDataEntity;
import com.cnfantasia.server.api.redpoint.entity.BasicSourceEntity;
import com.cnfantasia.server.api.redpoint.entity.RedpointContentEntity;

/**
 * Filename:    IRedpointService.java
 * @version:    1.0.0
 * Create at:   2015年3月25日 上午7:56:50
 * Description:红点提示Service
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年3月25日       shiyl             1.0             1.0 Version
 */
public interface IRedpointService {

	/**
	 * 查询单个模块红点信息
	 * @param userId
	 * @param userType
	 * @param modelCode
	 * @return
	 */
	public RedpointContentEntity qryRedpointInfo(BigInteger userId, Integer userType, String modelCode);

	/**
	 * 查询多个模块红点信息
	 * @param userId
	 * @param userType
	 * @param modelCodeList
	 * @return
	 */
	public List<RedpointContentEntity> qryRedpointInfoMulti(BigInteger userId, Integer userType, List<String> modelCodeList);

	/**
	 * 用户点击红点
	 * @param userId
	 * @param userType
	 * @param modelCode
	 * @return
	 */
	public RedpointContentEntity clickRedpointInfo(BigInteger userId, Integer userType, String modelCode);
	
	/**
	 * 增加红点提示
	 * @param userId
	 * @param userType
	 * @param modelCode
	 * @param sourceList
	 * @return
	 */
	public void addRedpointContent(BigInteger userId, Integer userType,BigInteger currRoomId, String modelCode,List<BasicSourceEntity> sourceList);
	/**
	 * 同时给多个用户增加红点提示
	 * @param commUserDataEntityList
	 * @param modelCode
	 * @param sourceList
	 * @return
	 */
	public void addRedpointContentMulti(List<CommUserDataEntity> commUserDataEntityList, String modelCode,List<BasicSourceEntity> sourceList);
	
	/**
	 * 已登录用户增加家人的红点提示，不包含自己
	 * @param userId
	 * @param userType
	 * @param modelCode
	 * @param sourceList
	 * @return
	 */
	public void addRedpointContentForFamily_HasLogin(BigInteger userId, String modelCode,List<BasicSourceEntity> sourceList);

	/**
	 * 添加未读账单红点
	 * @param userId
	 * @return true 表示需要增加或更新红点
	 */
	public boolean addPropertyBillRedPoint(BigInteger userId);
	
}
