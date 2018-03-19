package com.cnfantasia.server.domainbase.communitySupplyTypeCanAdd.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communitySupplyTypeCanAdd.entity.CommunitySupplyTypeCanAdd;

/**
 * 描述:(新增商家时的可选类别) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommunitySupplyTypeCanAddBaseDao {
	/**
	 * 根据条件查询(新增商家时的可选类别)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommunitySupplyTypeCanAdd> selectCommunitySupplyTypeCanAddByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(新增商家时的可选类别)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommunitySupplyTypeCanAdd> selectCommunitySupplyTypeCanAddByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(新增商家时的可选类别)信息
	 * @param id
	 * @return
	 */
	public CommunitySupplyTypeCanAdd selectCommunitySupplyTypeCanAddBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(新增商家时的可选类别)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectCommunitySupplyTypeCanAddCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(新增商家时的可选类别)新增一条记录
	 * @param communitySupplyTypeCanAdd
	 * @return
	 */
	public int insertCommunitySupplyTypeCanAdd(CommunitySupplyTypeCanAdd communitySupplyTypeCanAdd);
	
	/**
	 * 批量新增(新增商家时的可选类别)信息
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
	 * 根据Id 批量删除(新增商家时的可选类别)信息_逻辑删除
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
