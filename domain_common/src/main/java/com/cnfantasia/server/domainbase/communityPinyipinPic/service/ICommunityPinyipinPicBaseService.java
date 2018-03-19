package com.cnfantasia.server.domainbase.communityPinyipinPic.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.communityPinyipinPic.entity.CommunityPinyipinPic;

/**
 * 描述:(拼一拼图片列表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommunityPinyipinPicBaseService {
	
	/**
	 * 根据条件查询(拼一拼图片列表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommunityPinyipinPic> getCommunityPinyipinPicByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(拼一拼图片列表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommunityPinyipinPic> getCommunityPinyipinPicByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(拼一拼图片列表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommunityPinyipinPic> getCommunityPinyipinPicByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(拼一拼图片列表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommunityPinyipinPic> getCommunityPinyipinPicByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(拼一拼图片列表)信息
	 * @param id
	 * @return
	 */
	public CommunityPinyipinPic getCommunityPinyipinPicBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(拼一拼图片列表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCommunityPinyipinPicCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(拼一拼图片列表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCommunityPinyipinPicCountDim(Map<String,Object> paramMap);
	/**
	 * 往(拼一拼图片列表)新增一条记录
	 * @param communityPinyipinPic
	 * @return
	 */
	public int insertCommunityPinyipinPic(CommunityPinyipinPic communityPinyipinPic);
	/**
	 * 批量新增(拼一拼图片列表)
	 * @param communityPinyipinPicList
	 * @return
	 */
	public int insertCommunityPinyipinPicBatch(List<CommunityPinyipinPic> communityPinyipinPicList);
	/**
	 * 更新(拼一拼图片列表)信息
	 * @param communityPinyipinPic
	 * @return
	 */
	public int updateCommunityPinyipinPic(CommunityPinyipinPic communityPinyipinPic);
	/**
	 * 批量更新(拼一拼图片列表)信息
	 * @param communityPinyipinPicList
	 * @return
	 */
	public int updateCommunityPinyipinPicBatch(List<CommunityPinyipinPic> communityPinyipinPicList);
	/**
	 * 根据序列号删除(拼一拼图片列表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCommunityPinyipinPicLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(拼一拼图片列表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCommunityPinyipinPicLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(拼一拼图片列表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCommunityPinyipinPic(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(拼一拼图片列表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCommunityPinyipinPicBatch(List<java.math.BigInteger> idList);
	
}
