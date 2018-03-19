package com.cnfantasia.server.domainbase.shareActive.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.shareActive.entity.ShareActive;

/**
 * 描述:(分享活动) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IShareActiveBaseDao {
	/**
	 * 根据条件查询(分享活动)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<ShareActive> selectShareActiveByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(分享活动)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<ShareActive> selectShareActiveByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(分享活动)信息
	 * @param id
	 * @return
	 */
	public ShareActive selectShareActiveBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(分享活动)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectShareActiveCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(分享活动)新增一条记录
	 * @param shareActive
	 * @return
	 */
	public int insertShareActive(ShareActive shareActive);
	
	/**
	 * 批量新增(分享活动)信息
	 * @param shareActiveList
	 * @return
	 */
	public int insertShareActiveBatch(List<ShareActive> shareActiveList);
	
	/**
	 * 更新(分享活动)信息
	 * @param shareActive
	 * @return
	 */
	public int updateShareActive(ShareActive shareActive);
	
	/**
	 * 批量更新(分享活动)信息
	 * @param shareActiveList
	 * @return
	 */
	public int updateShareActiveBatch(List<ShareActive> shareActiveList);
	
	/**
	 * 根据序列号删除(分享活动)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteShareActiveLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(分享活动)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteShareActiveLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(分享活动)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteShareActive(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(分享活动)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteShareActiveBatch(List<java.math.BigInteger> idList);
	
	
}
