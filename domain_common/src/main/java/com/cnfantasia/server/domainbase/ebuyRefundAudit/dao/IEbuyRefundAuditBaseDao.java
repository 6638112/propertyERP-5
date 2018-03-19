package com.cnfantasia.server.domainbase.ebuyRefundAudit.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyRefundAudit.entity.EbuyRefundAudit;

/**
 * 描述:(商品审核不通过原因) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEbuyRefundAuditBaseDao {
	/**
	 * 根据条件查询(商品审核不通过原因)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyRefundAudit> selectEbuyRefundAuditByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(商品审核不通过原因)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EbuyRefundAudit> selectEbuyRefundAuditByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(商品审核不通过原因)信息
	 * @param id
	 * @return
	 */
	public EbuyRefundAudit selectEbuyRefundAuditBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(商品审核不通过原因)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEbuyRefundAuditCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(商品审核不通过原因)新增一条记录
	 * @param ebuyRefundAudit
	 * @return
	 */
	public int insertEbuyRefundAudit(EbuyRefundAudit ebuyRefundAudit);
	
	/**
	 * 批量新增(商品审核不通过原因)信息
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
	 * 根据Id 批量删除(商品审核不通过原因)信息_逻辑删除
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
