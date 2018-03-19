package com.cnfantasia.server.domainbase.dredgeRefund.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeRefund.entity.DredgeRefund;

/**
 * 描述:(到家服务退款申请) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IDredgeRefundBaseDao {
	/**
	 * 根据条件查询(到家服务退款申请)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<DredgeRefund> selectDredgeRefundByCondition(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 按条件分页查询(到家服务退款申请)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<DredgeRefund> selectDredgeRefundByCondition(Map<String, Object> paramMap, PageModel pageModel, boolean isDim);
	/**
	 * 根据序列号查询(到家服务退款申请)信息
	 * @param id
	 * @return
	 */
	public DredgeRefund selectDredgeRefundBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(到家服务退款申请)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectDredgeRefundCount(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 往(到家服务退款申请)新增一条记录
	 * @param dredgeRefund
	 * @return
	 */
	public int insertDredgeRefund(DredgeRefund dredgeRefund);
	
	/**
	 * 批量新增(到家服务退款申请)信息
	 * @param dredgeRefundList
	 * @return
	 */
	public int insertDredgeRefundBatch(List<DredgeRefund> dredgeRefundList);
	
	/**
	 * 更新(到家服务退款申请)信息
	 * @param dredgeRefund
	 * @return
	 */
	public int updateDredgeRefund(DredgeRefund dredgeRefund);
	
	/**
	 * 批量更新(到家服务退款申请)信息
	 * @param dredgeRefundList
	 * @return
	 */
	public int updateDredgeRefundBatch(List<DredgeRefund> dredgeRefundList);
	
	/**
	 * 根据序列号删除(到家服务退款申请)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteDredgeRefundLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(到家服务退款申请)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteDredgeRefundLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(到家服务退款申请)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteDredgeRefund(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(到家服务退款申请)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteDredgeRefundBatch(List<java.math.BigInteger> idList);
	
	
}
