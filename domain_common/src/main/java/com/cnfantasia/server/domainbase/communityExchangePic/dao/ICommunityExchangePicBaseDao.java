package com.cnfantasia.server.domainbase.communityExchangePic.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communityExchangePic.entity.CommunityExchangePic;

/**
 * 描述:(换一换图片表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommunityExchangePicBaseDao {
	/**
	 * 根据条件查询(换一换图片表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommunityExchangePic> selectCommunityExchangePicByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(换一换图片表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommunityExchangePic> selectCommunityExchangePicByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(换一换图片表)信息
	 * @param id
	 * @return
	 */
	public CommunityExchangePic selectCommunityExchangePicBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(换一换图片表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectCommunityExchangePicCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(换一换图片表)新增一条记录
	 * @param communityExchangePic
	 * @return
	 */
	public int insertCommunityExchangePic(CommunityExchangePic communityExchangePic);
	
	/**
	 * 批量新增(换一换图片表)信息
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
	 * 根据Id 批量删除(换一换图片表)信息_逻辑删除
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
