package com.cnfantasia.server.domainbase.communitySupplyType.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.communitySupplyType.entity.CommunitySupplyType;

/**
 * 描述:(社区商家类别) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommunitySupplyTypeBaseService {
	
	/**
	 * 根据条件查询(社区商家类别)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommunitySupplyType> getCommunitySupplyTypeByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(社区商家类别)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommunitySupplyType> getCommunitySupplyTypeByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(社区商家类别)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommunitySupplyType> getCommunitySupplyTypeByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(社区商家类别)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommunitySupplyType> getCommunitySupplyTypeByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(社区商家类别)信息
	 * @param id
	 * @return
	 */
	public CommunitySupplyType getCommunitySupplyTypeBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(社区商家类别)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCommunitySupplyTypeCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(社区商家类别)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCommunitySupplyTypeCountDim(Map<String,Object> paramMap);
	/**
	 * 往(社区商家类别)新增一条记录
	 * @param communitySupplyType
	 * @return
	 */
	public int insertCommunitySupplyType(CommunitySupplyType communitySupplyType);
	/**
	 * 批量新增(社区商家类别)
	 * @param communitySupplyTypeList
	 * @return
	 */
	public int insertCommunitySupplyTypeBatch(List<CommunitySupplyType> communitySupplyTypeList);
	/**
	 * 更新(社区商家类别)信息
	 * @param communitySupplyType
	 * @return
	 */
	public int updateCommunitySupplyType(CommunitySupplyType communitySupplyType);
	/**
	 * 批量更新(社区商家类别)信息
	 * @param communitySupplyTypeList
	 * @return
	 */
	public int updateCommunitySupplyTypeBatch(List<CommunitySupplyType> communitySupplyTypeList);
	/**
	 * 根据序列号删除(社区商家类别)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCommunitySupplyTypeLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(社区商家类别)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCommunitySupplyTypeLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(社区商家类别)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCommunitySupplyType(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(社区商家类别)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCommunitySupplyTypeBatch(List<java.math.BigInteger> idList);
	
}
