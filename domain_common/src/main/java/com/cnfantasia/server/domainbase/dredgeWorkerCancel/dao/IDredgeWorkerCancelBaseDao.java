package com.cnfantasia.server.domainbase.dredgeWorkerCancel.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeWorkerCancel.entity.DredgeWorkerCancel;

/**
 * 描述:(疏通师傅取消订单) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IDredgeWorkerCancelBaseDao {
	/**
	 * 根据条件查询(疏通师傅取消订单)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<DredgeWorkerCancel> selectDredgeWorkerCancelByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(疏通师傅取消订单)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<DredgeWorkerCancel> selectDredgeWorkerCancelByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(疏通师傅取消订单)信息
	 * @param id
	 * @return
	 */
	public DredgeWorkerCancel selectDredgeWorkerCancelBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(疏通师傅取消订单)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectDredgeWorkerCancelCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(疏通师傅取消订单)新增一条记录
	 * @param dredgeWorkerCancel
	 * @return
	 */
	public int insertDredgeWorkerCancel(DredgeWorkerCancel dredgeWorkerCancel);
	
	/**
	 * 批量新增(疏通师傅取消订单)信息
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
	 * 根据Id 批量删除(疏通师傅取消订单)信息_逻辑删除
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
