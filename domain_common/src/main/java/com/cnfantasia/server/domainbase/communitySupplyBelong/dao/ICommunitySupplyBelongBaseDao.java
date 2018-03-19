package com.cnfantasia.server.domainbase.communitySupplyBelong.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communitySupplyBelong.entity.CommunitySupplyBelong;

/**
 * 描述:(商家认领) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommunitySupplyBelongBaseDao {
	/**
	 * 根据条件查询(商家认领)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommunitySupplyBelong> selectCommunitySupplyBelongByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(商家认领)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommunitySupplyBelong> selectCommunitySupplyBelongByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(商家认领)信息
	 * @param id
	 * @return
	 */
	public CommunitySupplyBelong selectCommunitySupplyBelongBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(商家认领)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectCommunitySupplyBelongCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(商家认领)新增一条记录
	 * @param communitySupplyBelong
	 * @return
	 */
	public int insertCommunitySupplyBelong(CommunitySupplyBelong communitySupplyBelong);
	
	/**
	 * 批量新增(商家认领)信息
	 * @param communitySupplyBelongList
	 * @return
	 */
	public int insertCommunitySupplyBelongBatch(List<CommunitySupplyBelong> communitySupplyBelongList);
	
	/**
	 * 更新(商家认领)信息
	 * @param communitySupplyBelong
	 * @return
	 */
	public int updateCommunitySupplyBelong(CommunitySupplyBelong communitySupplyBelong);
	
	/**
	 * 批量更新(商家认领)信息
	 * @param communitySupplyBelongList
	 * @return
	 */
	public int updateCommunitySupplyBelongBatch(List<CommunitySupplyBelong> communitySupplyBelongList);
	
	/**
	 * 根据序列号删除(商家认领)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCommunitySupplyBelongLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(商家认领)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCommunitySupplyBelongLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(商家认领)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCommunitySupplyBelong(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(商家认领)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCommunitySupplyBelongBatch(List<java.math.BigInteger> idList);
	
	
}
