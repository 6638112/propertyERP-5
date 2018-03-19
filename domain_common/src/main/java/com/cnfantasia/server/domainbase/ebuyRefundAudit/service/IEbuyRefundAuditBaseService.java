package com.cnfantasia.server.domainbase.ebuyRefundAudit.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.ebuyRefundAudit.entity.EbuyRefundAudit;

/**
 * 描述:(商品审核不通过原因) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyRefundAuditBaseService {
	
	/**
	 * 根据条件查询(商品审核不通过原因)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyRefundAudit> getEbuyRefundAuditByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(商品审核不通过原因)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EbuyRefundAudit> getEbuyRefundAuditByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(商品审核不通过原因)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyRefundAudit> getEbuyRefundAuditByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(商品审核不通过原因)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EbuyRefundAudit> getEbuyRefundAuditByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(商品审核不通过原因)信息
	 * @param id
	 * @return
	 */
	public EbuyRefundAudit getEbuyRefundAuditBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(商品审核不通过原因)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyRefundAuditCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(商品审核不通过原因)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEbuyRefundAuditCountDim(Map<String,Object> paramMap);
	/**
	 * 往(商品审核不通过原因)新增一条记录
	 * @param ebuyRefundAudit
	 * @return
	 */
	public int insertEbuyRefundAudit(EbuyRefundAudit ebuyRefundAudit);
	/**
	 * 批量新增(商品审核不通过原因)
	 * @param ebuyRefundAuditList
	 * @return
	 */
	public int insertEbuyRefundAuditBatch(List<EbuyRefundAudit> ebuyRefundAuditList);
	/**
	 * 更新(商品审核不通过原因)信息
	 * @param ebuyRefundAudit
	 * @return
	 */
	public int updateEbuyRefundAudit(EbuyRefundAudit ebuyRefundAudit);
	/**
	 * 批量更新(商品审核不通过原因)信息
	 * @param ebuyRefundAuditList
	 * @return
	 */
	public int updateEbuyRefundAuditBatch(List<EbuyRefundAudit> ebuyRefundAuditList);
	/**
	 * 根据序列号删除(商品审核不通过原因)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEbuyRefundAuditLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(商品审核不通过原因)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEbuyRefundAuditLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(商品审核不通过原因)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEbuyRefundAudit(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(商品审核不通过原因)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEbuyRefundAuditBatch(List<java.math.BigInteger> idList);
	
}
