package com.cnfantasia.server.api.cloudKeyUserData.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.cloudKeyUserData.entity.CloudKeyUserData;

/**
 * 业主门禁认证（可配置）信息Dao接口
 *
 * @author Liyl
 * @version 1.0 2016年3月24日 下午6:41:28
 */
public interface ICloudKeyUserDataDao {

	/**
	 * 根据条件查询(业主门禁认证（可配置）信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CloudKeyUserData> selectCloudKeyUserDataByCondition(Map<String,Object> paramMap, boolean isDim);
	
	/**
	 * 批量新增(业主门禁认证（可配置）信息)信息
	 * @param cloudKeyUserDataList
	 * @return
	 */
	public int insertCloudKeyUserDataBatch(List<CloudKeyUserData> cloudKeyUserDataList);
	
	/**
	 * 根据cloudKeyUserAuditFId删除CloudKeyUserData
	 * @param cloudKeyUserAuditFId
	 * @return
	 */
	public int deleteCloudKeyUserDataByCloudKeyUserAuditFId(BigInteger cloudKeyUserAuditFId);
	
	/**
	 * 根据userId和realRoomId查询t_cloud_key_user_audit的f_id
	 * @param userId
	 * @param realRoomId
	 * @return
	 */
	public BigInteger queryCloudKeyUserAuditId(BigInteger userId, BigInteger realRoomId);
}
