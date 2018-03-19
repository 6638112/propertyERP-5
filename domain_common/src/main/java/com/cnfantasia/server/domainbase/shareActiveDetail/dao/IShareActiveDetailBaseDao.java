package com.cnfantasia.server.domainbase.shareActiveDetail.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.shareActiveDetail.entity.ShareActiveDetail;

/**
 * 描述:(分享活动详情表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IShareActiveDetailBaseDao {
	/**
	 * 根据条件查询(分享活动详情表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<ShareActiveDetail> selectShareActiveDetailByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(分享活动详情表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<ShareActiveDetail> selectShareActiveDetailByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(分享活动详情表)信息
	 * @param id
	 * @return
	 */
	public ShareActiveDetail selectShareActiveDetailBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(分享活动详情表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectShareActiveDetailCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(分享活动详情表)新增一条记录
	 * @param shareActiveDetail
	 * @return
	 */
	public int insertShareActiveDetail(ShareActiveDetail shareActiveDetail);
	
	/**
	 * 批量新增(分享活动详情表)信息
	 * @param shareActiveDetailList
	 * @return
	 */
	public int insertShareActiveDetailBatch(List<ShareActiveDetail> shareActiveDetailList);
	
	/**
	 * 更新(分享活动详情表)信息
	 * @param shareActiveDetail
	 * @return
	 */
	public int updateShareActiveDetail(ShareActiveDetail shareActiveDetail);
	
	/**
	 * 批量更新(分享活动详情表)信息
	 * @param shareActiveDetailList
	 * @return
	 */
	public int updateShareActiveDetailBatch(List<ShareActiveDetail> shareActiveDetailList);
	
	/**
	 * 根据序列号删除(分享活动详情表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteShareActiveDetailLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(分享活动详情表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteShareActiveDetailLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(分享活动详情表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteShareActiveDetail(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(分享活动详情表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteShareActiveDetailBatch(List<java.math.BigInteger> idList);
	
	
}
