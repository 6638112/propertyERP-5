package com.cnfantasia.server.domainbase.dredgeWorkerServiceAddressBlock.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeWorkerServiceAddressBlock.entity.DredgeWorkerServiceAddressBlock;

/**
 * 描述:(疏通师傅服务行政区) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IDredgeWorkerServiceAddressBlockBaseDao {
	/**
	 * 根据条件查询(疏通师傅服务行政区)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<DredgeWorkerServiceAddressBlock> selectDredgeWorkerServiceAddressBlockByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(疏通师傅服务行政区)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<DredgeWorkerServiceAddressBlock> selectDredgeWorkerServiceAddressBlockByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(疏通师傅服务行政区)信息
	 * @param id
	 * @return
	 */
	public DredgeWorkerServiceAddressBlock selectDredgeWorkerServiceAddressBlockBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(疏通师傅服务行政区)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectDredgeWorkerServiceAddressBlockCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(疏通师傅服务行政区)新增一条记录
	 * @param dredgeWorkerServiceAddressBlock
	 * @return
	 */
	public int insertDredgeWorkerServiceAddressBlock(DredgeWorkerServiceAddressBlock dredgeWorkerServiceAddressBlock);
	
	/**
	 * 批量新增(疏通师傅服务行政区)信息
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
	 * 根据Id 批量删除(疏通师傅服务行政区)信息_逻辑删除
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
