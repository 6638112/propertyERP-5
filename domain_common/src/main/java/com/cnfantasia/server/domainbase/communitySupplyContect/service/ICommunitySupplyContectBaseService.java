package com.cnfantasia.server.domainbase.communitySupplyContect.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.communitySupplyContect.entity.CommunitySupplyContect;

/**
 * 描述:(社区商家联系方式) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommunitySupplyContectBaseService {
	
	/**
	 * 根据条件查询(社区商家联系方式)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommunitySupplyContect> getCommunitySupplyContectByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(社区商家联系方式)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommunitySupplyContect> getCommunitySupplyContectByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(社区商家联系方式)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommunitySupplyContect> getCommunitySupplyContectByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(社区商家联系方式)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommunitySupplyContect> getCommunitySupplyContectByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(社区商家联系方式)信息
	 * @param id
	 * @return
	 */
	public CommunitySupplyContect getCommunitySupplyContectBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(社区商家联系方式)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCommunitySupplyContectCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(社区商家联系方式)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCommunitySupplyContectCountDim(Map<String,Object> paramMap);
	/**
	 * 往(社区商家联系方式)新增一条记录
	 * @param communitySupplyContect
	 * @return
	 */
	public int insertCommunitySupplyContect(CommunitySupplyContect communitySupplyContect);
	/**
	 * 批量新增(社区商家联系方式)
	 * @param communitySupplyContectList
	 * @return
	 */
	public int insertCommunitySupplyContectBatch(List<CommunitySupplyContect> communitySupplyContectList);
	/**
	 * 更新(社区商家联系方式)信息
	 * @param communitySupplyContect
	 * @return
	 */
	public int updateCommunitySupplyContect(CommunitySupplyContect communitySupplyContect);
	/**
	 * 批量更新(社区商家联系方式)信息
	 * @param communitySupplyContectList
	 * @return
	 */
	public int updateCommunitySupplyContectBatch(List<CommunitySupplyContect> communitySupplyContectList);
	/**
	 * 根据序列号删除(社区商家联系方式)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCommunitySupplyContectLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(社区商家联系方式)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCommunitySupplyContectLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(社区商家联系方式)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCommunitySupplyContect(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(社区商家联系方式)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCommunitySupplyContectBatch(List<java.math.BigInteger> idList);
	
}
