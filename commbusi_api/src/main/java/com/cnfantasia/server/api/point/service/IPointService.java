/**   
* Filename:    IPointService.java   
* @version:    1.0  
* Create at:   2014年12月29日 上午8:36:44   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年12月29日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.point.service;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.pointCostRecord.entity.PointCostRecord;
import com.cnfantasia.server.domainbase.pointData.entity.PointData;
import com.cnfantasia.server.domainbase.pointSourceRecord.entity.PointSourceRecord;

/**
 * Filename:    IPointService.java
 * @version:    1.0.0
 * Create at:   2014年12月29日 上午8:36:44
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年12月29日       shiyl             1.0             1.0 Version
 */
public interface IPointService {
	
	/**
	 * 增加积分信息
	 * @param userId 用户Id
	 * @param type 积分来源类别
	 * @param value 积分值
	 * @param dayCount 签到天数
	 * @return
	 */
	public PointData addPoint(BigInteger userId,Integer type,Long value,Long dayCount);
	
	/**
	 * 消费积分信息
	 * @param userId 用户Id
	 * @param type 积分消费方式
	 * @param value 积分值
	 * @return
	 */
	public PointData costPoint(BigInteger userId,Integer type,Long value);
	
	/**
	 * 查询积分来源列表
	 * @param userId
	 * @return
	 */
	public List<PointSourceRecord> qryPointSourceList(BigInteger userId,PageModel pageModel);
	
	/**
	 * 查询积分消费列表
	 * @param userId
	 * @return
	 */
	public List<PointCostRecord> qryPointCostList(BigInteger userId,PageModel pageModel);
	
	/**
	 * 检查并新增积分信息
	 * @param userId
	 * @param type
	 * @return
	 */
	public void checkAndAddPoint(BigInteger userId,Integer type);
	
}
