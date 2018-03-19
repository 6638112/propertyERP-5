package com.cnfantasia.server.domainbase.communitySupplyTmp.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communitySupplyTmp.entity.CommunitySupplyTmp;

/**
 * 描述:(店铺申请审核表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommunitySupplyTmpBaseDao {
	/**
	 * 根据条件查询(店铺申请审核表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommunitySupplyTmp> selectCommunitySupplyTmpByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(店铺申请审核表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommunitySupplyTmp> selectCommunitySupplyTmpByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(店铺申请审核表)信息
	 * @param id
	 * @return
	 */
	public CommunitySupplyTmp selectCommunitySupplyTmpBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(店铺申请审核表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectCommunitySupplyTmpCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(店铺申请审核表)新增一条记录
	 * @param communitySupplyTmp
	 * @return
	 */
	public int insertCommunitySupplyTmp(CommunitySupplyTmp communitySupplyTmp);
	
	/**
	 * 批量新增(店铺申请审核表)信息
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
	 * 根据Id 批量删除(店铺申请审核表)信息_逻辑删除
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
