package com.cnfantasia.server.domainbase.revenueGeneration.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.revenueGeneration.entity.RevenueGeneration;

/**
 * 描述:() 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IRevenueGenerationBaseService {
	
	/**
	 * 根据条件查询()信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<RevenueGeneration> getRevenueGenerationByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询()信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<RevenueGeneration> getRevenueGenerationByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询()信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<RevenueGeneration> getRevenueGenerationByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询()信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<RevenueGeneration> getRevenueGenerationByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询()信息
	 * @param id
	 * @return
	 */
	public RevenueGeneration getRevenueGenerationBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的()记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getRevenueGenerationCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的()记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getRevenueGenerationCountDim(Map<String,Object> paramMap);
	/**
	 * 往()新增一条记录
	 * @param revenueGeneration
	 * @return
	 */
	public int insertRevenueGeneration(RevenueGeneration revenueGeneration);
	/**
	 * 批量新增()
	 * @param revenueGenerationList
	 * @return
	 */
	public int insertRevenueGenerationBatch(List<RevenueGeneration> revenueGenerationList);
	/**
	 * 更新()信息
	 * @param revenueGeneration
	 * @return
	 */
	public int updateRevenueGeneration(RevenueGeneration revenueGeneration);
	/**
	 * 批量更新()信息
	 * @param revenueGenerationList
	 * @return
	 */
	public int updateRevenueGenerationBatch(List<RevenueGeneration> revenueGenerationList);
	/**
	 * 根据序列号删除()信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	public int deleteRevenueGenerationLogic(java.math.BigInteger id);
	 */
	/**
	 * 根据序列号批量删除()信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	public int deleteRevenueGenerationLogicBatch(List<java.math.BigInteger> idList);
	 */
//	/**
//	 * 根据序列号删除()信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteRevenueGeneration(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除()信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteRevenueGenerationBatch(List<java.math.BigInteger> idList);
	
}
