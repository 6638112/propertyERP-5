package com.cnfantasia.server.domainbase.revenueGeneration.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.revenueGeneration.entity.RevenueGeneration;

/**
 * 描述:() DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IRevenueGenerationBaseDao {
	/**
	 * 根据条件查询()信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<RevenueGeneration> selectRevenueGenerationByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询()信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<RevenueGeneration> selectRevenueGenerationByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询()信息
	 * @param id
	 * @return
	 */
	public RevenueGeneration selectRevenueGenerationBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的()记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectRevenueGenerationCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往()新增一条记录
	 * @param revenueGeneration
	 * @return
	 */
	public int insertRevenueGeneration(RevenueGeneration revenueGeneration);
	
	/**
	 * 批量新增()信息
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
	 * 根据Id 批量删除()信息_逻辑删除
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
