package com.cnfantasia.server.domainbase.dredgeWorkerCancel.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.dredgeWorkerCancel.entity.DredgeWorkerCancel;

/**
 * 描述:(疏通师傅取消订单) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IDredgeWorkerCancelBaseService {
	
	/**
	 * 根据条件查询(疏通师傅取消订单)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<DredgeWorkerCancel> getDredgeWorkerCancelByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(疏通师傅取消订单)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<DredgeWorkerCancel> getDredgeWorkerCancelByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(疏通师傅取消订单)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<DredgeWorkerCancel> getDredgeWorkerCancelByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(疏通师傅取消订单)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<DredgeWorkerCancel> getDredgeWorkerCancelByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(疏通师傅取消订单)信息
	 * @param id
	 * @return
	 */
	public DredgeWorkerCancel getDredgeWorkerCancelBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(疏通师傅取消订单)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getDredgeWorkerCancelCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(疏通师傅取消订单)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getDredgeWorkerCancelCountDim(Map<String,Object> paramMap);
	/**
	 * 往(疏通师傅取消订单)新增一条记录
	 * @param dredgeWorkerCancel
	 * @return
	 */
	public int insertDredgeWorkerCancel(DredgeWorkerCancel dredgeWorkerCancel);
	/**
	 * 批量新增(疏通师傅取消订单)
	 * @param dredgeWorkerCancelList
	 * @return
	 */
	public int insertDredgeWorkerCancelBatch(List<DredgeWorkerCancel> dredgeWorkerCancelList);
	/**
	 * 更新(疏通师傅取消订单)信息
	 * @param dredgeWorkerCancel
	 * @return
	 */
	public int updateDredgeWorkerCancel(DredgeWorkerCancel dredgeWorkerCancel);
	/**
	 * 批量更新(疏通师傅取消订单)信息
	 * @param dredgeWorkerCancelList
	 * @return
	 */
	public int updateDredgeWorkerCancelBatch(List<DredgeWorkerCancel> dredgeWorkerCancelList);
	/**
	 * 根据序列号删除(疏通师傅取消订单)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	public int deleteDredgeWorkerCancelLogic(java.math.BigInteger id);
	 */
	/**
	 * 根据序列号批量删除(疏通师傅取消订单)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	public int deleteDredgeWorkerCancelLogicBatch(List<java.math.BigInteger> idList);
	 */
//	/**
//	 * 根据序列号删除(疏通师傅取消订单)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteDredgeWorkerCancel(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(疏通师傅取消订单)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteDredgeWorkerCancelBatch(List<java.math.BigInteger> idList);
	
}
