/**   
* Filename:    IRedpointDao.java   
* @version:    1.0  
* Create at:   2015年3月25日 上午8:06:55   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2015年3月25日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.redpoint.dao;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.redpoint.entity.RedpointContentEntity;
import com.cnfantasia.server.api.redpoint.entity.RedpointModelcodeConfigEntity;
import com.cnfantasia.server.domainbase.redpointContent.entity.RedpointContent;

/**
 * Filename:    IRedpointDao.java
 * @version:    1.0.0
 * Create at:   2015年3月25日 上午8:06:55
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2015年3月25日       shiyl             1.0             1.0 Version
 */
public interface IRedpointDao {
	
	/**
	 * 查询模块及子模块编码列表
	 * @param modelCode
	 * @return
	 */
	public List<RedpointModelcodeConfigEntity> selectRedpointModelcodeWithSubList(String modelCode);
	
	/**
	 * 查询单个模块的红点详情 关联用户当前门牌
	 * @param userId
	 * @param userType
	 * @param modelCode
	 * @return
	 */
	public RedpointContentEntity selectRedpointContentByModelCode(BigInteger userId, Integer userType,BigInteger roomId, String modelCode);

	/**
	 * 批量查询模块的红点详情
	 * @param userId
	 * @param userType
	 * @param modelCodeList
	 * @return
	 */
	public List<RedpointContentEntity> selectRedpointContentByModelCodeList(BigInteger userId, Integer userType,BigInteger roomId,
			List<String> modelCodeList);

	/**
	 * 标记红点为已点
	 * @param userId
	 * @param userType
	 * @param modelCode
	 * @return
	 */
	public Integer updateRedpointContentClicked(BigInteger userId, Integer userType,BigInteger roomId, String modelCode);
	
	/**
	 * 刷新红点信息表的更新时间和信息
	 * @return
	 */
	public Integer freshRedpointContentClickStatus(BigInteger userId, Integer userType,BigInteger roomId, String modelCode);

	/**
	 * 刷新红点信息表的更新时间和信息
	 * @return
	 */
	Integer freshRedpointContentClickStatus2(RedpointContent rc);
	
}
