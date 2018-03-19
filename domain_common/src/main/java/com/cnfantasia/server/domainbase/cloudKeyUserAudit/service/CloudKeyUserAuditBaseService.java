package com.cnfantasia.server.domainbase.cloudKeyUserAudit.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.cloudKeyUserAudit.dao.ICloudKeyUserAuditBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.cloudKeyUserAudit.entity.CloudKeyUserAudit;

/**
 * 描述:(业主门禁认证信息) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CloudKeyUserAuditBaseService implements ICloudKeyUserAuditBaseService{
	
	private ICloudKeyUserAuditBaseDao cloudKeyUserAuditBaseDao;
	public void setCloudKeyUserAuditBaseDao(ICloudKeyUserAuditBaseDao cloudKeyUserAuditBaseDao) {
		this.cloudKeyUserAuditBaseDao = cloudKeyUserAuditBaseDao;
	}
	/**
	 * 根据条件查询(业主门禁认证信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CloudKeyUserAudit> getCloudKeyUserAuditByCondition(Map<String,Object> paramMap){
		return cloudKeyUserAuditBaseDao.selectCloudKeyUserAuditByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(业主门禁认证信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CloudKeyUserAudit> getCloudKeyUserAuditByConditionDim(Map<String,Object> paramMap){
		return cloudKeyUserAuditBaseDao.selectCloudKeyUserAuditByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(业主门禁认证信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CloudKeyUserAudit> getCloudKeyUserAuditByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return cloudKeyUserAuditBaseDao.selectCloudKeyUserAuditByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(业主门禁认证信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CloudKeyUserAudit> getCloudKeyUserAuditByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return cloudKeyUserAuditBaseDao.selectCloudKeyUserAuditByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(业主门禁认证信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public CloudKeyUserAudit getCloudKeyUserAuditBySeqId(java.math.BigInteger id){
		return cloudKeyUserAuditBaseDao.selectCloudKeyUserAuditBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(业主门禁认证信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCloudKeyUserAuditCount(Map<String,Object> paramMap){
		return cloudKeyUserAuditBaseDao.selectCloudKeyUserAuditCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(业主门禁认证信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCloudKeyUserAuditCountDim(Map<String,Object> paramMap){
		return cloudKeyUserAuditBaseDao.selectCloudKeyUserAuditCount(paramMap,true);
	}
	/**
	 * 往(业主门禁认证信息)新增一条记录
	 * @param cloudKeyUserAudit
	 * @return
	 */
	@Override
	public int insertCloudKeyUserAudit(CloudKeyUserAudit cloudKeyUserAudit){
		return cloudKeyUserAuditBaseDao.insertCloudKeyUserAudit(cloudKeyUserAudit);
	}
	/**
	 * 批量新增(业主门禁认证信息)
	 * @param cloudKeyUserAuditList
	 * @return
	 */
	@Override
	public int insertCloudKeyUserAuditBatch(List<CloudKeyUserAudit> cloudKeyUserAuditList){
		return cloudKeyUserAuditBaseDao.insertCloudKeyUserAuditBatch(cloudKeyUserAuditList);
	}
	/**
	 * 更新(业主门禁认证信息)信息
	 * @param cloudKeyUserAudit
	 * @return
	 */
	@Override
	public int updateCloudKeyUserAudit(CloudKeyUserAudit cloudKeyUserAudit){
		return cloudKeyUserAuditBaseDao.updateCloudKeyUserAudit(cloudKeyUserAudit);
	}
	/**
	 * 批量更新(业主门禁认证信息)信息
	 * @param cloudKeyUserAuditList
	 * @return
	 */
	@Override
	public int updateCloudKeyUserAuditBatch(List<CloudKeyUserAudit> cloudKeyUserAuditList){
		return cloudKeyUserAuditBaseDao.updateCloudKeyUserAuditBatch(cloudKeyUserAuditList);
	}
	/**
	 * 根据序列号删除(业主门禁认证信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCloudKeyUserAuditLogic(java.math.BigInteger id){
		return cloudKeyUserAuditBaseDao.deleteCloudKeyUserAuditLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(业主门禁认证信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCloudKeyUserAuditLogicBatch(List<java.math.BigInteger> idList){
		return cloudKeyUserAuditBaseDao.deleteCloudKeyUserAuditLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(业主门禁认证信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCloudKeyUserAudit(java.math.BigInteger id){
//		return cloudKeyUserAuditBaseDao.deleteCloudKeyUserAudit(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(业主门禁认证信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCloudKeyUserAuditBatch(List<java.math.BigInteger> idList){
//		return cloudKeyUserAuditBaseDao.deleteCloudKeyUserAuditBatch(idList);
//	}
	
}
