package com.cnfantasia.server.domainbase.dredgeWorkerServiceAddressBlock.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.dredgeWorkerServiceAddressBlock.entity.DredgeWorkerServiceAddressBlock;

/**
 * 描述:(疏通师傅服务行政区) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IDredgeWorkerServiceAddressBlockBaseService {
	
	/**
	 * 根据条件查询(疏通师傅服务行政区)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<DredgeWorkerServiceAddressBlock> getDredgeWorkerServiceAddressBlockByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(疏通师傅服务行政区)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<DredgeWorkerServiceAddressBlock> getDredgeWorkerServiceAddressBlockByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(疏通师傅服务行政区)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<DredgeWorkerServiceAddressBlock> getDredgeWorkerServiceAddressBlockByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(疏通师傅服务行政区)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<DredgeWorkerServiceAddressBlock> getDredgeWorkerServiceAddressBlockByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(疏通师傅服务行政区)信息
	 * @param id
	 * @return
	 */
	public DredgeWorkerServiceAddressBlock getDredgeWorkerServiceAddressBlockBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(疏通师傅服务行政区)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getDredgeWorkerServiceAddressBlockCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(疏通师傅服务行政区)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getDredgeWorkerServiceAddressBlockCountDim(Map<String,Object> paramMap);
	/**
	 * 往(疏通师傅服务行政区)新增一条记录
	 * @param dredgeWorkerServiceAddressBlock
	 * @return
	 */
	public int insertDredgeWorkerServiceAddressBlock(DredgeWorkerServiceAddressBlock dredgeWorkerServiceAddressBlock);
	/**
	 * 批量新增(疏通师傅服务行政区)
	 * @param dredgeWorkerServiceAddressBlockList
	 * @return
	 */
	public int insertDredgeWorkerServiceAddressBlockBatch(List<DredgeWorkerServiceAddressBlock> dredgeWorkerServiceAddressBlockList);
	/**
	 * 更新(疏通师傅服务行政区)信息
	 * @param dredgeWorkerServiceAddressBlock
	 * @return
	 */
	public int updateDredgeWorkerServiceAddressBlock(DredgeWorkerServiceAddressBlock dredgeWorkerServiceAddressBlock);
	/**
	 * 批量更新(疏通师傅服务行政区)信息
	 * @param dredgeWorkerServiceAddressBlockList
	 * @return
	 */
	public int updateDredgeWorkerServiceAddressBlockBatch(List<DredgeWorkerServiceAddressBlock> dredgeWorkerServiceAddressBlockList);
	/**
	 * 根据序列号删除(疏通师傅服务行政区)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	public int deleteDredgeWorkerServiceAddressBlockLogic(java.math.BigInteger id);
	 */
	/**
	 * 根据序列号批量删除(疏通师傅服务行政区)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	public int deleteDredgeWorkerServiceAddressBlockLogicBatch(List<java.math.BigInteger> idList);
	 */
//	/**
//	 * 根据序列号删除(疏通师傅服务行政区)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteDredgeWorkerServiceAddressBlock(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(疏通师傅服务行政区)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteDredgeWorkerServiceAddressBlockBatch(List<java.math.BigInteger> idList);
	
}
