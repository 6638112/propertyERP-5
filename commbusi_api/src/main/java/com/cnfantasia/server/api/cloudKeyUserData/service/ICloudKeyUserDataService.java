package com.cnfantasia.server.api.cloudKeyUserData.service;

import java.math.BigInteger;
import java.util.List;

import com.cnfantasia.server.domainbase.cloudKeyUserAudit.entity.CloudKeyUserAudit;
import com.cnfantasia.server.domainbase.cloudKeyUserData.entity.CloudKeyUserData;

/**
 * 业主门禁认证（可配置）信息Service接口
 *
 * @author Liyl
 * @version 1.0 2016年3月24日 下午6:41:28
 */
public interface ICloudKeyUserDataService {

	/**
	 * 根据条件查询(业主门禁认证（可配置）信息)信息
	 * @param tCloudKeyUserAuditFId
	 * @return
	 */
	public List<CloudKeyUserData> queryCloudKeyUserDataByCondition(BigInteger tCloudKeyUserAuditFId);
	
	/**
	 * 新增业主门禁认证信息
	 * @param cloudKeyUserAudit
	 * @param cloudKeyUserDataList
	 * @return
	 */
	public boolean insertCloudKeyUserAudit(CloudKeyUserAudit cloudKeyUserAudit, List<CloudKeyUserData> cloudKeyUserDataList);
	
	/**
	 * 修改业主门禁认证信息
	 * @param cloudKeyUserAudit
	 * @param cloudKeyUserDataList
	 * @return
	 */
	public boolean updateCloudKeyUserAudit(CloudKeyUserAudit cloudKeyUserAudit, List<CloudKeyUserData> cloudKeyUserDataList);
	
	/**
	 * 根据userId和realRoomId查询t_cloud_key_user_audit的f_id
	 * @param userId
	 * @param realRoomId
	 * @return
	 */
	public BigInteger queryCloudKeyUserAuditId(BigInteger userId, BigInteger realRoomId);
}
