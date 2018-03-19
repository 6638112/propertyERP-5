package com.cnfantasia.server.domainbase.cloudKeyUserAudit.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.cloudKeyUserAudit.entity.CloudKeyUserAudit;

/**
 * 描述:(业主门禁认证信息) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICloudKeyUserAuditBaseDao {
	/**
	 * 根据条件查询(业主门禁认证信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CloudKeyUserAudit> selectCloudKeyUserAuditByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(业主门禁认证信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CloudKeyUserAudit> selectCloudKeyUserAuditByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(业主门禁认证信息)信息
	 * @param id
	 * @return
	 */
	public CloudKeyUserAudit selectCloudKeyUserAuditBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(业主门禁认证信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectCloudKeyUserAuditCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(业主门禁认证信息)新增一条记录
	 * @param cloudKeyUserAudit
	 * @return
	 */
	public int insertCloudKeyUserAudit(CloudKeyUserAudit cloudKeyUserAudit);
	
	/**
	 * 批量新增(业主门禁认证信息)信息
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
	 * 根据Id 批量删除(业主门禁认证信息)信息_逻辑删除
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
