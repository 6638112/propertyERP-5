package com.cnfantasia.server.api.cloudKeyUserData.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.api.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.domainbase.cloudKeyUserData.entity.CloudKeyUserData;

/**
 * 业主门禁认证（可配置）信息Dao
 *
 * @author Liyl
 * @version 1.0 2016年3月24日 下午6:41:28
 */
public class CloudKeyUserDataDao extends AbstractBaseDao implements ICloudKeyUserDataDao {
	
	/**
	 * 根据条件查询(业主门禁认证（可配置）信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CloudKeyUserData> selectCloudKeyUserDataByCondition(Map<String,Object> paramMap, boolean isDim){
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("cloudKeyUserDataBase.select_cloudKeyUserDatas",paramMap);
	}
	
	/**
	 * 批量新增(业主门禁认证（可配置）信息)信息
	 * @param cloudKeyUserDataList
	 * @return
	 */
	@Override
	public int insertCloudKeyUserDataBatch(List<CloudKeyUserData> cloudKeyUserDataList) {
		return sqlSession.insert("cloudKeyUserDataBase.insert_cloudKeyUserData_Batch",cloudKeyUserDataList);
	}
	
	/**
	 * 根据cloudKeyUserAuditFId删除CloudKeyUserData
	 * @param cloudKeyUserAuditFId
	 * @return
	 */
	@Override
	public int deleteCloudKeyUserDataByCloudKeyUserAuditFId(BigInteger cloudKeyUserAuditFId){
		return sqlSession.insert("cloudKeyUserDataBase.delete_cloudKeyUserData_by_t_cloud_key_user_audit_f_id",cloudKeyUserAuditFId);
	}

	/**
	 * 根据userId和realRoomId查询t_cloud_key_user_audit的f_id
	 * @param userId
	 * @param realRoomId
	 * @return
	 */
	@Override
	public BigInteger queryCloudKeyUserAuditId(BigInteger userId,
			BigInteger realRoomId) {
		Map<String, Object> parameter = new HashMap<String, Object>();
		parameter.put("userId", userId);
		parameter.put("realRoomId", realRoomId);
		return sqlSession.selectOne("cloudKeyUserDataBase.select_cloudKeyUserAudit_id", parameter);
	}
	
}
