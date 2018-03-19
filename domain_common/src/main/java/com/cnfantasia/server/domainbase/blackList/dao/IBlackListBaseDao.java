package com.cnfantasia.server.domainbase.blackList.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.blackList.entity.BlackList;

/**
 * 描述:(黑名单) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IBlackListBaseDao {
	/**
	 * 根据条件查询(黑名单)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<BlackList> selectBlackListByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(黑名单)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<BlackList> selectBlackListByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(黑名单)信息
	 * @param id
	 * @return
	 */
	public BlackList selectBlackListBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(黑名单)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectBlackListCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(黑名单)新增一条记录
	 * @param blackList
	 * @return
	 */
	public int insertBlackList(BlackList blackList);
	
	/**
	 * 批量新增(黑名单)信息
	 * @param blackListList
	 * @return
	 */
	public int insertBlackListBatch(List<BlackList> blackListList);
	
	/**
	 * 更新(黑名单)信息
	 * @param blackList
	 * @return
	 */
	public int updateBlackList(BlackList blackList);
	
	/**
	 * 批量更新(黑名单)信息
	 * @param blackListList
	 * @return
	 */
	public int updateBlackListBatch(List<BlackList> blackListList);
	
	/**
	 * 根据序列号删除(黑名单)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteBlackListLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(黑名单)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteBlackListLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(黑名单)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteBlackList(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(黑名单)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteBlackListBatch(List<java.math.BigInteger> idList);
	
	
}
