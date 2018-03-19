package com.cnfantasia.server.domainbase.shareActiveDetail.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.shareActiveDetail.entity.ShareActiveDetail;

/**
 * 描述:(分享活动详情表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IShareActiveDetailBaseService {
	
	/**
	 * 根据条件查询(分享活动详情表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<ShareActiveDetail> getShareActiveDetailByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(分享活动详情表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<ShareActiveDetail> getShareActiveDetailByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(分享活动详情表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<ShareActiveDetail> getShareActiveDetailByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(分享活动详情表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<ShareActiveDetail> getShareActiveDetailByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(分享活动详情表)信息
	 * @param id
	 * @return
	 */
	public ShareActiveDetail getShareActiveDetailBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(分享活动详情表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getShareActiveDetailCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(分享活动详情表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getShareActiveDetailCountDim(Map<String,Object> paramMap);
	/**
	 * 往(分享活动详情表)新增一条记录
	 * @param shareActiveDetail
	 * @return
	 */
	public int insertShareActiveDetail(ShareActiveDetail shareActiveDetail);
	/**
	 * 批量新增(分享活动详情表)
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
	 * 根据序列号批量删除(分享活动详情表)信息_逻辑删除
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
