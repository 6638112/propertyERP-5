package com.cnfantasia.server.domainbase.dredgeBill.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeBill.entity.DredgeBill;

/**
 * 描述:(疏通工单) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IDredgeBillBaseDao {
	/**
	 * 根据条件查询(疏通工单)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<DredgeBill> selectDredgeBillByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(疏通工单)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<DredgeBill> selectDredgeBillByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(疏通工单)信息
	 * @param id
	 * @return
	 */
	public DredgeBill selectDredgeBillBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(疏通工单)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectDredgeBillCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(疏通工单)新增一条记录
	 * @param dredgeBill
	 * @return
	 */
	public int insertDredgeBill(DredgeBill dredgeBill);
	
	/**
	 * 批量新增(疏通工单)信息
	 * @param dredgeBillList
	 * @return
	 */
	public int insertDredgeBillBatch(List<DredgeBill> dredgeBillList);
	
	/**
	 * 更新(疏通工单)信息
	 * @param dredgeBill
	 * @return
	 */
	public int updateDredgeBill(DredgeBill dredgeBill);
	
	/**
	 * 批量更新(疏通工单)信息
	 * @param dredgeBillList
	 * @return
	 */
	public int updateDredgeBillBatch(List<DredgeBill> dredgeBillList);
	
	/**
	 * 根据序列号删除(疏通工单)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteDredgeBillLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(疏通工单)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteDredgeBillLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(疏通工单)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteDredgeBill(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(疏通工单)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteDredgeBillBatch(List<java.math.BigInteger> idList);
	
	
}
