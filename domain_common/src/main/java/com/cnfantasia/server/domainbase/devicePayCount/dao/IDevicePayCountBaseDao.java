package com.cnfantasia.server.domainbase.devicePayCount.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.devicePayCount.entity.DevicePayCount;

/**
 * 描述:() DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IDevicePayCountBaseDao {
	/**
	 * 根据条件查询()信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	List<DevicePayCount> selectDevicePayCountByCondition(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 按条件分页查询()信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	List<DevicePayCount> selectDevicePayCountByCondition(Map<String, Object> paramMap, PageModel pageModel, boolean isDim);
	/**
	 * 根据序列号查询()信息
	 * @param id
	 * @return
	 */
	DevicePayCount selectDevicePayCountBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的()记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	int selectDevicePayCountCount(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 往()新增一条记录
	 * @param devicePayCount
	 * @return
	 */
	int insertDevicePayCount(DevicePayCount devicePayCount);
	
	/**
	 * 批量新增()信息
	 * @param devicePayCountList
	 * @return
	 */
	int insertDevicePayCountBatch(List<DevicePayCount> devicePayCountList);
	
	/**
	 * 更新()信息
	 * @param devicePayCount
	 * @return
	 */
	int updateDevicePayCount(DevicePayCount devicePayCount);
	
	/**
	 * 批量更新()信息
	 * @param devicePayCountList
	 * @return
	 */
	int updateDevicePayCountBatch(List<DevicePayCount> devicePayCountList);
	
	/**
	 * 根据序列号删除()信息_逻辑删除
	 * @param id
	 * @return
	 */

	int deleteDevicePayCountLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除()信息_逻辑删除
	 * @param idList
	 * @return
	 */

	int deleteDevicePayCountLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除()信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteDevicePayCount(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除()信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteDevicePayCountBatch(List<java.math.BigInteger> idList);
	
	
}
