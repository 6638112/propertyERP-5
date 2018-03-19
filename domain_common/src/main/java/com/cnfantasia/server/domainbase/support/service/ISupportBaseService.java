package com.cnfantasia.server.domainbase.support.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.support.entity.Support;

/**
 * 描述:(点赞信息) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ISupportBaseService {
	
	/**
	 * 根据条件查询(点赞信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<Support> getSupportByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(点赞信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<Support> getSupportByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(点赞信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<Support> getSupportByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(点赞信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<Support> getSupportByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(点赞信息)信息
	 * @param id
	 * @return
	 */
	public Support getSupportBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(点赞信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getSupportCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(点赞信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getSupportCountDim(Map<String,Object> paramMap);
	/**
	 * 往(点赞信息)新增一条记录
	 * @param support
	 * @return
	 */
	public int insertSupport(Support support);
	/**
	 * 批量新增(点赞信息)
	 * @param supportList
	 * @return
	 */
	public int insertSupportBatch(List<Support> supportList);
	/**
	 * 更新(点赞信息)信息
	 * @param support
	 * @return
	 */
	public int updateSupport(Support support);
	/**
	 * 批量更新(点赞信息)信息
	 * @param supportList
	 * @return
	 */
	public int updateSupportBatch(List<Support> supportList);
	/**
	 * 根据序列号删除(点赞信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteSupportLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(点赞信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteSupportLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(点赞信息)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteSupport(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(点赞信息)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteSupportBatch(List<java.math.BigInteger> idList);
	
}
