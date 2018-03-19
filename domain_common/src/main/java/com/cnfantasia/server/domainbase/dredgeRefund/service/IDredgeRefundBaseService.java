package com.cnfantasia.server.domainbase.dredgeRefund.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.dredgeRefund.entity.DredgeRefund;

/**
 * 描述:(到家服务退款申请) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IDredgeRefundBaseService {
	
	/**
	 * 根据条件查询(到家服务退款申请)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<DredgeRefund> getDredgeRefundByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询(到家服务退款申请)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<DredgeRefund> getDredgeRefundByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询(到家服务退款申请)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<DredgeRefund> getDredgeRefundByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询(到家服务退款申请)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<DredgeRefund> getDredgeRefundByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询(到家服务退款申请)信息
	 * @param id
	 * @return
	 */
	public DredgeRefund getDredgeRefundBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(到家服务退款申请)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getDredgeRefundCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的(到家服务退款申请)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getDredgeRefundCountDim(Map<String, Object> paramMap);
	/**
	 * 往(到家服务退款申请)新增一条记录
	 * @param dredgeRefund
	 * @return
	 */
	public int insertDredgeRefund(DredgeRefund dredgeRefund);
	/**
	 * 批量新增(到家服务退款申请)
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
	 * 根据序列号批量删除(到家服务退款申请)信息_逻辑删除
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
