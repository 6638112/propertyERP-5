package com.cnfantasia.server.domainbase.cloudKeyUserAudit.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.cloudKeyUserAudit.entity.CloudKeyUserAudit;

/**
 * 描述:(业主门禁认证信息) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICloudKeyUserAuditBaseService {
	
	/**
	 * 根据条件查询(业主门禁认证信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<CloudKeyUserAudit> getCloudKeyUserAuditByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(业主门禁认证信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<CloudKeyUserAudit> getCloudKeyUserAuditByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(业主门禁认证信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CloudKeyUserAudit> getCloudKeyUserAuditByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(业主门禁认证信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CloudKeyUserAudit> getCloudKeyUserAuditByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(业主门禁认证信息)信息
	 * @param id
	 * @return
	 */
	public CloudKeyUserAudit getCloudKeyUserAuditBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(业主门禁认证信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCloudKeyUserAuditCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(业主门禁认证信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCloudKeyUserAuditCountDim(Map<String,Object> paramMap);
	/**
	 * 往(业主门禁认证信息)新增一条记录
	 * @param cloudKeyUserAudit
	 * @return
	 */
	public int insertCloudKeyUserAudit(CloudKeyUserAudit cloudKeyUserAudit);
	/**
	 * 批量新增(业主门禁认证信息)
	 * @param cloudKeyUserAuditList
	 * @return
	 */
	public int insertCloudKeyUserAuditBatch(List<CloudKeyUserAudit> cloudKeyUserAuditList);
	/**
	 * 更新(业主门禁认证信息)信息
	 * @param cloudKeyUserAudit
	 * @return
	 */
	public int updateCloudKeyUserAudit(CloudKeyUserAudit cloudKeyUserAudit);
	/**
	 * 批量更新(业主门禁认证信息)信息
	 * @param cloudKeyUserAuditList
	 * @return
	 */
	public int updateCloudKeyUserAuditBatch(List<CloudKeyUserAudit> cloudKeyUserAuditList);
	/**
	 * 根据序列号删除(业主门禁认证信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCloudKeyUserAuditLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(业主门禁认证信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCloudKeyUserAuditLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(业主门禁认证信息)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCloudKeyUserAudit(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(业主门禁认证信息)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCloudKeyUserAuditBatch(List<java.math.BigInteger> idList);
	
}
