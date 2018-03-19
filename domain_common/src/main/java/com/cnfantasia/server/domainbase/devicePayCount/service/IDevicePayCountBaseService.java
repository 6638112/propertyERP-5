package com.cnfantasia.server.domainbase.devicePayCount.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.devicePayCount.entity.DevicePayCount;

/**
 * 描述:() 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IDevicePayCountBaseService {
	
	/**
	 * 根据条件查询()信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	List<DevicePayCount> getDevicePayCountByCondition(Map<String, Object> paramMap);
	/**
	 * 根据条件查询()信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	List<DevicePayCount> getDevicePayCountByConditionDim(Map<String, Object> paramMap);
	/**
	 * 按条件分页查询()信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<DevicePayCount> getDevicePayCountByCondition(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 按条件分页查询()信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	List<DevicePayCount> getDevicePayCountByConditionDim(Map<String, Object> paramMap, PageModel pageModel);
	/**
	 * 根据序列号查询()信息
	 * @param id
	 * @return
	 */
	DevicePayCount getDevicePayCountBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的()记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	int getDevicePayCountCount(Map<String, Object> paramMap);
	/**
	 * 根据条件查询满足条件的()记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	int getDevicePayCountCountDim(Map<String, Object> paramMap);
	/**
	 * 往()新增一条记录
	 * @param devicePayCount
	 * @return
	 */
	int insertDevicePayCount(DevicePayCount devicePayCount);
	/**
	 * 批量新增()
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
	 * 根据序列号批量删除()信息_逻辑删除
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
