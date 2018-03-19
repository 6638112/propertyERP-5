package com.cnfantasia.server.domainbase.communitySupplyTmp.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.communitySupplyTmp.entity.CommunitySupplyTmp;

/**
 * 描述:(店铺申请审核表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommunitySupplyTmpBaseService {
	
	/**
	 * 根据条件查询(店铺申请审核表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommunitySupplyTmp> getCommunitySupplyTmpByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(店铺申请审核表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommunitySupplyTmp> getCommunitySupplyTmpByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(店铺申请审核表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommunitySupplyTmp> getCommunitySupplyTmpByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(店铺申请审核表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommunitySupplyTmp> getCommunitySupplyTmpByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(店铺申请审核表)信息
	 * @param id
	 * @return
	 */
	public CommunitySupplyTmp getCommunitySupplyTmpBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(店铺申请审核表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCommunitySupplyTmpCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(店铺申请审核表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCommunitySupplyTmpCountDim(Map<String,Object> paramMap);
	/**
	 * 往(店铺申请审核表)新增一条记录
	 * @param communitySupplyTmp
	 * @return
	 */
	public int insertCommunitySupplyTmp(CommunitySupplyTmp communitySupplyTmp);
	/**
	 * 批量新增(店铺申请审核表)
	 * @param communitySupplyTmpList
	 * @return
	 */
	public int insertCommunitySupplyTmpBatch(List<CommunitySupplyTmp> communitySupplyTmpList);
	/**
	 * 更新(店铺申请审核表)信息
	 * @param communitySupplyTmp
	 * @return
	 */
	public int updateCommunitySupplyTmp(CommunitySupplyTmp communitySupplyTmp);
	/**
	 * 批量更新(店铺申请审核表)信息
	 * @param communitySupplyTmpList
	 * @return
	 */
	public int updateCommunitySupplyTmpBatch(List<CommunitySupplyTmp> communitySupplyTmpList);
	/**
	 * 根据序列号删除(店铺申请审核表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCommunitySupplyTmpLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(店铺申请审核表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCommunitySupplyTmpLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(店铺申请审核表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCommunitySupplyTmp(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(店铺申请审核表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCommunitySupplyTmpBatch(List<java.math.BigInteger> idList);
	
}
