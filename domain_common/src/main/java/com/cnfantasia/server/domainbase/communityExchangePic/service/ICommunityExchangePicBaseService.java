package com.cnfantasia.server.domainbase.communityExchangePic.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.communityExchangePic.entity.CommunityExchangePic;

/**
 * 描述:(换一换图片表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommunityExchangePicBaseService {
	
	/**
	 * 根据条件查询(换一换图片表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommunityExchangePic> getCommunityExchangePicByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(换一换图片表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommunityExchangePic> getCommunityExchangePicByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(换一换图片表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommunityExchangePic> getCommunityExchangePicByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(换一换图片表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommunityExchangePic> getCommunityExchangePicByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(换一换图片表)信息
	 * @param id
	 * @return
	 */
	public CommunityExchangePic getCommunityExchangePicBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(换一换图片表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCommunityExchangePicCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(换一换图片表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCommunityExchangePicCountDim(Map<String,Object> paramMap);
	/**
	 * 往(换一换图片表)新增一条记录
	 * @param communityExchangePic
	 * @return
	 */
	public int insertCommunityExchangePic(CommunityExchangePic communityExchangePic);
	/**
	 * 批量新增(换一换图片表)
	 * @param communityExchangePicList
	 * @return
	 */
	public int insertCommunityExchangePicBatch(List<CommunityExchangePic> communityExchangePicList);
	/**
	 * 更新(换一换图片表)信息
	 * @param communityExchangePic
	 * @return
	 */
	public int updateCommunityExchangePic(CommunityExchangePic communityExchangePic);
	/**
	 * 批量更新(换一换图片表)信息
	 * @param communityExchangePicList
	 * @return
	 */
	public int updateCommunityExchangePicBatch(List<CommunityExchangePic> communityExchangePicList);
	/**
	 * 根据序列号删除(换一换图片表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCommunityExchangePicLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(换一换图片表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCommunityExchangePicLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(换一换图片表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCommunityExchangePic(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(换一换图片表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCommunityExchangePicBatch(List<java.math.BigInteger> idList);
	
}
