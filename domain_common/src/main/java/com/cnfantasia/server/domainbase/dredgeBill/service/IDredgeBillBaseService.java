package com.cnfantasia.server.domainbase.dredgeBill.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.dredgeBill.entity.DredgeBill;

/**
 * 描述:(疏通工单) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IDredgeBillBaseService {
	
	/**
	 * 根据条件查询(疏通工单)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<DredgeBill> getDredgeBillByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(疏通工单)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<DredgeBill> getDredgeBillByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(疏通工单)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<DredgeBill> getDredgeBillByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(疏通工单)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<DredgeBill> getDredgeBillByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(疏通工单)信息
	 * @param id
	 * @return
	 */
	public DredgeBill getDredgeBillBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(疏通工单)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getDredgeBillCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(疏通工单)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getDredgeBillCountDim(Map<String,Object> paramMap);
	/**
	 * 往(疏通工单)新增一条记录
	 * @param dredgeBill
	 * @return
	 */
	public int insertDredgeBill(DredgeBill dredgeBill);
	/**
	 * 批量新增(疏通工单)
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
	 * 根据序列号批量删除(疏通工单)信息_逻辑删除
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
