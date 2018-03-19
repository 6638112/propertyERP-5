package com.cnfantasia.server.domainbase.selfActivityDetail.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.selfActivityDetail.entity.SelfActivityDetail;

/**
 * 描述:(自定义活动详情) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ISelfActivityDetailBaseService {
	
	/**
	 * 根据条件查询(自定义活动详情)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<SelfActivityDetail> getSelfActivityDetailByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(自定义活动详情)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<SelfActivityDetail> getSelfActivityDetailByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(自定义活动详情)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<SelfActivityDetail> getSelfActivityDetailByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(自定义活动详情)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<SelfActivityDetail> getSelfActivityDetailByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(自定义活动详情)信息
	 * @param id
	 * @return
	 */
	public SelfActivityDetail getSelfActivityDetailBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(自定义活动详情)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getSelfActivityDetailCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(自定义活动详情)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getSelfActivityDetailCountDim(Map<String,Object> paramMap);
	/**
	 * 往(自定义活动详情)新增一条记录
	 * @param selfActivityDetail
	 * @return
	 */
	public int insertSelfActivityDetail(SelfActivityDetail selfActivityDetail);
	/**
	 * 批量新增(自定义活动详情)
	 * @param selfActivityDetailList
	 * @return
	 */
	public int insertSelfActivityDetailBatch(List<SelfActivityDetail> selfActivityDetailList);
	/**
	 * 更新(自定义活动详情)信息
	 * @param selfActivityDetail
	 * @return
	 */
	public int updateSelfActivityDetail(SelfActivityDetail selfActivityDetail);
	/**
	 * 批量更新(自定义活动详情)信息
	 * @param selfActivityDetailList
	 * @return
	 */
	public int updateSelfActivityDetailBatch(List<SelfActivityDetail> selfActivityDetailList);
	/**
	 * 根据序列号删除(自定义活动详情)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteSelfActivityDetailLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(自定义活动详情)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteSelfActivityDetailLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(自定义活动详情)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteSelfActivityDetail(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(自定义活动详情)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteSelfActivityDetailBatch(List<java.math.BigInteger> idList);
	
}
