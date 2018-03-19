package com.cnfantasia.server.domainbase.communitySupplyPic.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.communitySupplyPic.entity.CommunitySupplyPic;

/**
 * 描述:(商家图片) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommunitySupplyPicBaseService {
	
	/**
	 * 根据条件查询(商家图片)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommunitySupplyPic> getCommunitySupplyPicByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(商家图片)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommunitySupplyPic> getCommunitySupplyPicByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(商家图片)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommunitySupplyPic> getCommunitySupplyPicByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(商家图片)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommunitySupplyPic> getCommunitySupplyPicByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(商家图片)信息
	 * @param id
	 * @return
	 */
	public CommunitySupplyPic getCommunitySupplyPicBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(商家图片)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCommunitySupplyPicCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(商家图片)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCommunitySupplyPicCountDim(Map<String,Object> paramMap);
	/**
	 * 往(商家图片)新增一条记录
	 * @param communitySupplyPic
	 * @return
	 */
	public int insertCommunitySupplyPic(CommunitySupplyPic communitySupplyPic);
	/**
	 * 批量新增(商家图片)
	 * @param communitySupplyPicList
	 * @return
	 */
	public int insertCommunitySupplyPicBatch(List<CommunitySupplyPic> communitySupplyPicList);
	/**
	 * 更新(商家图片)信息
	 * @param communitySupplyPic
	 * @return
	 */
	public int updateCommunitySupplyPic(CommunitySupplyPic communitySupplyPic);
	/**
	 * 批量更新(商家图片)信息
	 * @param communitySupplyPicList
	 * @return
	 */
	public int updateCommunitySupplyPicBatch(List<CommunitySupplyPic> communitySupplyPicList);
	/**
	 * 根据序列号删除(商家图片)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCommunitySupplyPicLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(商家图片)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCommunitySupplyPicLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(商家图片)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCommunitySupplyPic(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(商家图片)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCommunitySupplyPicBatch(List<java.math.BigInteger> idList);
	
}
