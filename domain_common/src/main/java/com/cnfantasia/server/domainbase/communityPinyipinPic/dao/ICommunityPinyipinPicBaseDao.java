package com.cnfantasia.server.domainbase.communityPinyipinPic.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communityPinyipinPic.entity.CommunityPinyipinPic;

/**
 * 描述:(拼一拼图片列表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommunityPinyipinPicBaseDao {
	/**
	 * 根据条件查询(拼一拼图片列表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommunityPinyipinPic> selectCommunityPinyipinPicByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(拼一拼图片列表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommunityPinyipinPic> selectCommunityPinyipinPicByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(拼一拼图片列表)信息
	 * @param id
	 * @return
	 */
	public CommunityPinyipinPic selectCommunityPinyipinPicBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(拼一拼图片列表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectCommunityPinyipinPicCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(拼一拼图片列表)新增一条记录
	 * @param communityPinyipinPic
	 * @return
	 */
	public int insertCommunityPinyipinPic(CommunityPinyipinPic communityPinyipinPic);
	
	/**
	 * 批量新增(拼一拼图片列表)信息
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
	 * 根据Id 批量删除(拼一拼图片列表)信息_逻辑删除
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
