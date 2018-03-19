package com.cnfantasia.server.domainbase.communitySupplyTypeCanAdd.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.communitySupplyTypeCanAdd.entity.CommunitySupplyTypeCanAdd;

/**
 * 描述:(新增商家时的可选类别) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommunitySupplyTypeCanAddBaseService {
	
	/**
	 * 根据条件查询(新增商家时的可选类别)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommunitySupplyTypeCanAdd> getCommunitySupplyTypeCanAddByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(新增商家时的可选类别)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommunitySupplyTypeCanAdd> getCommunitySupplyTypeCanAddByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(新增商家时的可选类别)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommunitySupplyTypeCanAdd> getCommunitySupplyTypeCanAddByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(新增商家时的可选类别)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommunitySupplyTypeCanAdd> getCommunitySupplyTypeCanAddByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(新增商家时的可选类别)信息
	 * @param id
	 * @return
	 */
	public CommunitySupplyTypeCanAdd getCommunitySupplyTypeCanAddBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(新增商家时的可选类别)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCommunitySupplyTypeCanAddCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(新增商家时的可选类别)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCommunitySupplyTypeCanAddCountDim(Map<String,Object> paramMap);
	/**
	 * 往(新增商家时的可选类别)新增一条记录
	 * @param communitySupplyTypeCanAdd
	 * @return
	 */
	public int insertCommunitySupplyTypeCanAdd(CommunitySupplyTypeCanAdd communitySupplyTypeCanAdd);
	/**
	 * 批量新增(新增商家时的可选类别)
	 * @param communitySupplyTypeCanAddList
	 * @return
	 */
	public int insertCommunitySupplyTypeCanAddBatch(List<CommunitySupplyTypeCanAdd> communitySupplyTypeCanAddList);
	/**
	 * 更新(新增商家时的可选类别)信息
	 * @param communitySupplyTypeCanAdd
	 * @return
	 */
	public int updateCommunitySupplyTypeCanAdd(CommunitySupplyTypeCanAdd communitySupplyTypeCanAdd);
	/**
	 * 批量更新(新增商家时的可选类别)信息
	 * @param communitySupplyTypeCanAddList
	 * @return
	 */
	public int updateCommunitySupplyTypeCanAddBatch(List<CommunitySupplyTypeCanAdd> communitySupplyTypeCanAddList);
	/**
	 * 根据序列号删除(新增商家时的可选类别)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCommunitySupplyTypeCanAddLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(新增商家时的可选类别)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCommunitySupplyTypeCanAddLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(新增商家时的可选类别)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCommunitySupplyTypeCanAdd(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(新增商家时的可选类别)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCommunitySupplyTypeCanAddBatch(List<java.math.BigInteger> idList);
	
}
