package com.cnfantasia.server.domainbase.communitySupplyBelong.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.communitySupplyBelong.entity.CommunitySupplyBelong;

/**
 * 描述:(商家认领) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommunitySupplyBelongBaseService {
	
	/**
	 * 根据条件查询(商家认领)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommunitySupplyBelong> getCommunitySupplyBelongByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(商家认领)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommunitySupplyBelong> getCommunitySupplyBelongByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(商家认领)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommunitySupplyBelong> getCommunitySupplyBelongByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(商家认领)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommunitySupplyBelong> getCommunitySupplyBelongByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(商家认领)信息
	 * @param id
	 * @return
	 */
	public CommunitySupplyBelong getCommunitySupplyBelongBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(商家认领)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCommunitySupplyBelongCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(商家认领)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCommunitySupplyBelongCountDim(Map<String,Object> paramMap);
	/**
	 * 往(商家认领)新增一条记录
	 * @param communitySupplyBelong
	 * @return
	 */
	public int insertCommunitySupplyBelong(CommunitySupplyBelong communitySupplyBelong);
	/**
	 * 批量新增(商家认领)
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
	 * 根据序列号批量删除(商家认领)信息_逻辑删除
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
