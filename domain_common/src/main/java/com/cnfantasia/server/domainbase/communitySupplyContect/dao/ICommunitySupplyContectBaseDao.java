package com.cnfantasia.server.domainbase.communitySupplyContect.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communitySupplyContect.entity.CommunitySupplyContect;

/**
 * 描述:(社区商家联系方式) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommunitySupplyContectBaseDao {
	/**
	 * 根据条件查询(社区商家联系方式)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommunitySupplyContect> selectCommunitySupplyContectByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(社区商家联系方式)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommunitySupplyContect> selectCommunitySupplyContectByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(社区商家联系方式)信息
	 * @param id
	 * @return
	 */
	public CommunitySupplyContect selectCommunitySupplyContectBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(社区商家联系方式)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectCommunitySupplyContectCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(社区商家联系方式)新增一条记录
	 * @param communitySupplyContect
	 * @return
	 */
	public int insertCommunitySupplyContect(CommunitySupplyContect communitySupplyContect);
	
	/**
	 * 批量新增(社区商家联系方式)信息
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
	 * 根据Id 批量删除(社区商家联系方式)信息_逻辑删除
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
