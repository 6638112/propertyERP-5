package com.cnfantasia.server.domainbase.devicePayCount.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.devicePayCount.dao.IDevicePayCountBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.devicePayCount.entity.DevicePayCount;

/**
 * 描述:() 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class DevicePayCountBaseService implements IDevicePayCountBaseService{
	
	private IDevicePayCountBaseDao devicePayCountBaseDao;
	public void setDevicePayCountBaseDao(IDevicePayCountBaseDao devicePayCountBaseDao) {
		this.devicePayCountBaseDao = devicePayCountBaseDao;
	}
	/**
	 * 根据条件查询()信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<DevicePayCount> getDevicePayCountByCondition(Map<String,Object> paramMap){
		return devicePayCountBaseDao.selectDevicePayCountByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询()信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<DevicePayCount> getDevicePayCountByConditionDim(Map<String,Object> paramMap){
		return devicePayCountBaseDao.selectDevicePayCountByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询()信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<DevicePayCount> getDevicePayCountByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return devicePayCountBaseDao.selectDevicePayCountByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询()信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<DevicePayCount> getDevicePayCountByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return devicePayCountBaseDao.selectDevicePayCountByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询()信息
	 * @param id
	 * @return
	 */
	@Override
	public DevicePayCount getDevicePayCountBySeqId(java.math.BigInteger id){
		return devicePayCountBaseDao.selectDevicePayCountBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的()记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getDevicePayCountCount(Map<String,Object> paramMap){
		return devicePayCountBaseDao.selectDevicePayCountCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的()记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getDevicePayCountCountDim(Map<String,Object> paramMap){
		return devicePayCountBaseDao.selectDevicePayCountCount(paramMap,true);
	}
	/**
	 * 往()新增一条记录
	 * @param devicePayCount
	 * @return
	 */
	@Override
	public int insertDevicePayCount(DevicePayCount devicePayCount){
		return devicePayCountBaseDao.insertDevicePayCount(devicePayCount);
	}
	/**
	 * 批量新增()
	 * @param devicePayCountList
	 * @return
	 */
	@Override
	public int insertDevicePayCountBatch(List<DevicePayCount> devicePayCountList){
		return devicePayCountBaseDao.insertDevicePayCountBatch(devicePayCountList);
	}
	/**
	 * 更新()信息
	 * @param devicePayCount
	 * @return
	 */
	@Override
	public int updateDevicePayCount(DevicePayCount devicePayCount){
		return devicePayCountBaseDao.updateDevicePayCount(devicePayCount);
	}
	/**
	 * 批量更新()信息
	 * @param devicePayCountList
	 * @return
	 */
	@Override
	public int updateDevicePayCountBatch(List<DevicePayCount> devicePayCountList){
		return devicePayCountBaseDao.updateDevicePayCountBatch(devicePayCountList);
	}
	/**
	 * 根据序列号删除()信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteDevicePayCountLogic(java.math.BigInteger id){
		return devicePayCountBaseDao.deleteDevicePayCountLogic(id);
	}
	
	/**
	 * 根据序列号批量删除()信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteDevicePayCountLogicBatch(List<java.math.BigInteger> idList){
		return devicePayCountBaseDao.deleteDevicePayCountLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除()信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteDevicePayCount(java.math.BigInteger id){
//		return devicePayCountBaseDao.deleteDevicePayCount(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除()信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteDevicePayCountBatch(List<java.math.BigInteger> idList){
//		return devicePayCountBaseDao.deleteDevicePayCountBatch(idList);
//	}
	
}
