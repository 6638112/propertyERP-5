package com.cnfantasia.server.api.cloudKeyUserData.service;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.cnfantasia.server.api.cloudKeyUserData.dao.ICloudKeyUserDataDao;
import com.cnfantasia.server.domainbase.cloudKeyUserAudit.dao.ICloudKeyUserAuditBaseDao;
import com.cnfantasia.server.domainbase.cloudKeyUserAudit.entity.CloudKeyUserAudit;
import com.cnfantasia.server.domainbase.cloudKeyUserData.entity.CloudKeyUserData;

/**
 * 业主门禁认证（可配置）信息Service
 *
 * @author Liyl
 * @version 1.0 2016年3月24日 下午6:41:28
 */
public class CloudKeyUserDataService implements ICloudKeyUserDataService {
	
	private ICloudKeyUserDataDao cloudKeyUserDataDao;
	private ICloudKeyUserAuditBaseDao cloudKeyUserAuditBaseDao;

	public void setCloudKeyUserDataDao(ICloudKeyUserDataDao cloudKeyUserDataDao) {
		this.cloudKeyUserDataDao = cloudKeyUserDataDao;
	}

	public void setCloudKeyUserAuditBaseDao(
			ICloudKeyUserAuditBaseDao cloudKeyUserAuditBaseDao) {
		this.cloudKeyUserAuditBaseDao = cloudKeyUserAuditBaseDao;
	}

	/**
	 * 根据条件查询(业主门禁认证（可配置）信息)信息
	 * @param tCloudKeyUserAuditFId
	 * @return
	 */
	@Override
	public List<CloudKeyUserData> queryCloudKeyUserDataByCondition(BigInteger tCloudKeyUserAuditFId){
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tCloudKeyUserAuditFId", tCloudKeyUserAuditFId);
		return cloudKeyUserDataDao.selectCloudKeyUserDataByCondition(paramMap, true);
	}
	
	/**
	 * 新增业主门禁认证信息
	 * @param cloudKeyUserAudit
	 * @param cloudKeyUserDataList
	 * @return
	 */
	@Override
	@Transactional
	public boolean insertCloudKeyUserAudit(CloudKeyUserAudit cloudKeyUserAudit, List<CloudKeyUserData> cloudKeyUserDataList){
		int affectedAuditCount = cloudKeyUserAuditBaseDao.insertCloudKeyUserAudit(cloudKeyUserAudit);
		if(affectedAuditCount>0){
			if(cloudKeyUserDataDao.insertCloudKeyUserDataBatch(cloudKeyUserDataList)>0){
				return true;
			};
		} 
		return false;
	}
	
	/**
	 * 修改业主门禁认证信息
	 * @param cloudKeyUserAudit
	 * @param cloudKeyUserDataList
	 * @return
	 */
	@Override
	@Transactional
	public boolean updateCloudKeyUserAudit(CloudKeyUserAudit cloudKeyUserAudit, List<CloudKeyUserData> cloudKeyUserDataList){
		if(cloudKeyUserAuditBaseDao.updateCloudKeyUserAudit(cloudKeyUserAudit)>0){
			if(cloudKeyUserDataDao.deleteCloudKeyUserDataByCloudKeyUserAuditFId(cloudKeyUserAudit.getId())>0){
				if(cloudKeyUserDataDao.insertCloudKeyUserDataBatch(cloudKeyUserDataList)>0){
					return true;
				}
			}
		}
		return true;
	}

	/**
	 * 根据userId和realRoomId查询t_cloud_key_user_audit的f_id
	 * @param userId
	 * @param realRoomId
	 * @return
	 */
	@Override
	public BigInteger queryCloudKeyUserAuditId(BigInteger userId, BigInteger realRoomId) {
		return cloudKeyUserDataDao.queryCloudKeyUserAuditId(userId, realRoomId);
	}
	
}
