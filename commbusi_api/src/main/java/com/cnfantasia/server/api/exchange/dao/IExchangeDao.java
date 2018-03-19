/**   
 * Filename:    IExchangeDao.java   
* @version:    1.0  
* Create at:   2014年10月15日 上午9:26:20   
* Description:  
*   
* Modification History:   
* Date        Author      Version     Description   
* ----------------------------------------------------------------- 
* 2014年10月15日    shiyl      1.0         1.0 Version   
*/
package com.cnfantasia.server.api.exchange.dao;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.api.exchange.entity.ExchangeBaseEntity;
import com.cnfantasia.server.api.exchange.entity.ExchangeEntity;
import com.cnfantasia.server.api.exchange.entity.ExchangeRelationGoalEntity;
import com.cnfantasia.server.business.pub.entity.PageModel;

/**
 * Filename:    IExchangeDao.java
 * @version:    1.0.0
 * Create at:   2014年10月15日 上午9:26:20
 * Description:
 *
 * Modification History:
 * Date           Author           Version           Description
 * ------------------------------------------------------------------
 * 2014年10月15日       shiyl             1.0             1.0 Version
 */
public interface IExchangeDao {
	/**
	 * 查询最热的一个换物
	 * @param userId 用户Id
	 * @param groupBuildingId 所属小区Id
	 * @return
	 */
	public ExchangeEntity selectMostHotContent(BigInteger userId,BigInteger groupBuildingId);
	
	/**
	 * 查询热门拼单换物
	 * @param userId
	 * @param groupBuildingId 所属小区Id
	 * @param pageModel
	 * @return
	 */
	public List<ExchangeBaseEntity> selectHotContentList(BigInteger userId,BigInteger groupBuildingId,PageModel pageModel);
	
	/**
	 * 查询我发起的换物列表
	 * @param userId
	 * @param groupBuildingId 所属小区Id
	 * @param pageModel
	 * @return
	 */
	public List<ExchangeBaseEntity> selectLaunchContentList(BigInteger userId,BigInteger groupBuildingId,PageModel pageModel);
	
	/**
	 * 查询我参与的换物列表
	 * @param userId
	 * @param groupBuildingId 所属小区Id
	 * @param pageModel
	 * @return
	 */
	public List<ExchangeBaseEntity> selectJoinContentList(BigInteger userId,BigInteger groupBuildingId,PageModel pageModel);
	
	/**
	 * 查询我发起的和我参与的换物列表
	 * @param userId
	 * @param groupBuildingId
	 * @param pageModel
	 * @return
	 */
	public List<ExchangeBaseEntity> selectLaunchAndJoinContentList(BigInteger userId, BigInteger groupBuildingId,
			PageModel pageModel);
	
	/**
	 * 查询换物详情
	 * @param userId
	 * @param exchangeContentId
	 * @return
	 */
	public ExchangeEntity selectExchangeContentDetail(BigInteger userId,BigInteger exchangeContentId);
	
	/**
	 * 删除换物
	 * @param userId
	 * @param contentId
	 * @return
	 */
	public Integer deleteLaunchContent(BigInteger userId, BigInteger contentId);
	
	/**
	 * 查询参与换物的用户物品列表,包含对应关系的状态
	 * @param userId
	 * @param exchangeContentId
	 * @return
	 */
	public List<ExchangeRelationGoalEntity> selectSubJoinContentListWithRelation(BigInteger userId,BigInteger exchangeContentId);
	public ExchangeRelationGoalEntity selectSubJoinContentWithRelationSucc(BigInteger userId,BigInteger exchangeContentId);
	
	/**
	 * 查询参与换物的用户物品列表
	 * @param userId
	 * @param exchangeContentId
	 * @return
	 */
	public List<ExchangeBaseEntity> selectSubJoinContentList(BigInteger userId,BigInteger exchangeContentId);
	
	/**
	 * 发起者确认交换换物
	 * @param userId
	 * @param exchangeRelationId
	 */
	public Integer updateExchangeRelation2Finished(BigInteger userId,BigInteger exchangeRelationId);

	/**
	 * 查询换物当前参与的记录数
	 * @param updContentId
	 * @return
	 */
	public Integer selectJoinTotalCount(BigInteger updContentId);
	
	/**
	 * 查询是否可以跪求换
	 * @param userId
	 * @param exchangeRelationId
	 * @return
	 */
	public Boolean selectCanWantedBoolean(BigInteger userId,BigInteger exchangeRelationId);
	/**
	 * 查询跪求换数量
	 * @param userId
	 * @param exchangeRelationId
	 * @return
	 */
	public Integer selectCanWantedCount(BigInteger userId,BigInteger exchangeRelationId);
	
	/**
	 * 查询所有热门换物列表最新更新时间
	 * @param userId
	 * @return
	 */
	public String selectAllHotContentListLastUpdTime(BigInteger userId);

	/**
	 * 查询所有我发起的和我参与的换物最新更新时间
	 * @param userId
	 * @return
	 */
	public String selectAllLaunchAndJoinContentListLastUpdTime(BigInteger userId);
	
}
