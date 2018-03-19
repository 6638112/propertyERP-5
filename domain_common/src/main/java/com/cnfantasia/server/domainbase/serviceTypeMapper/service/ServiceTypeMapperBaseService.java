package com.cnfantasia.server.domainbase.serviceTypeMapper.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.serviceTypeMapper.dao.IServiceTypeMapperBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.serviceTypeMapper.entity.ServiceTypeMapper;

/**
 * 描述:(与第三方供应商类型对应关系) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class ServiceTypeMapperBaseService implements IServiceTypeMapperBaseService{
	
	private IServiceTypeMapperBaseDao serviceTypeMapperBaseDao;
	public void setServiceTypeMapperBaseDao(IServiceTypeMapperBaseDao serviceTypeMapperBaseDao) {
		this.serviceTypeMapperBaseDao = serviceTypeMapperBaseDao;
	}
	/**
	 * 根据条件查询(与第三方供应商类型对应关系)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<ServiceTypeMapper> getServiceTypeMapperByCondition(Map<String,Object> paramMap){
		return serviceTypeMapperBaseDao.selectServiceTypeMapperByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(与第三方供应商类型对应关系)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<ServiceTypeMapper> getServiceTypeMapperByConditionDim(Map<String,Object> paramMap){
		return serviceTypeMapperBaseDao.selectServiceTypeMapperByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(与第三方供应商类型对应关系)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<ServiceTypeMapper> getServiceTypeMapperByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return serviceTypeMapperBaseDao.selectServiceTypeMapperByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(与第三方供应商类型对应关系)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<ServiceTypeMapper> getServiceTypeMapperByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return serviceTypeMapperBaseDao.selectServiceTypeMapperByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(与第三方供应商类型对应关系)信息
	 * @param id
	 * @return
	 */
	@Override
	public ServiceTypeMapper getServiceTypeMapperBySeqId(java.math.BigInteger id){
		return serviceTypeMapperBaseDao.selectServiceTypeMapperBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(与第三方供应商类型对应关系)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getServiceTypeMapperCount(Map<String,Object> paramMap){
		return serviceTypeMapperBaseDao.selectServiceTypeMapperCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(与第三方供应商类型对应关系)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getServiceTypeMapperCountDim(Map<String,Object> paramMap){
		return serviceTypeMapperBaseDao.selectServiceTypeMapperCount(paramMap,true);
	}
	/**
	 * 往(与第三方供应商类型对应关系)新增一条记录
	 * @param serviceTypeMapper
	 * @return
	 */
	@Override
	public int insertServiceTypeMapper(ServiceTypeMapper serviceTypeMapper){
		return serviceTypeMapperBaseDao.insertServiceTypeMapper(serviceTypeMapper);
	}
	/**
	 * 批量新增(与第三方供应商类型对应关系)
	 * @param serviceTypeMapperList
	 * @return
	 */
	@Override
	public int insertServiceTypeMapperBatch(List<ServiceTypeMapper> serviceTypeMapperList){
		return serviceTypeMapperBaseDao.insertServiceTypeMapperBatch(serviceTypeMapperList);
	}
	/**
	 * 更新(与第三方供应商类型对应关系)信息
	 * @param serviceTypeMapper
	 * @return
	 */
	@Override
	public int updateServiceTypeMapper(ServiceTypeMapper serviceTypeMapper){
		return serviceTypeMapperBaseDao.updateServiceTypeMapper(serviceTypeMapper);
	}
	/**
	 * 批量更新(与第三方供应商类型对应关系)信息
	 * @param serviceTypeMapperList
	 * @return
	 */
	@Override
	public int updateServiceTypeMapperBatch(List<ServiceTypeMapper> serviceTypeMapperList){
		return serviceTypeMapperBaseDao.updateServiceTypeMapperBatch(serviceTypeMapperList);
	}
	/**
	 * 根据序列号删除(与第三方供应商类型对应关系)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteServiceTypeMapperLogic(java.math.BigInteger id){
		return serviceTypeMapperBaseDao.deleteServiceTypeMapperLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(与第三方供应商类型对应关系)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteServiceTypeMapperLogicBatch(List<java.math.BigInteger> idList){
		return serviceTypeMapperBaseDao.deleteServiceTypeMapperLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(与第三方供应商类型对应关系)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteServiceTypeMapper(java.math.BigInteger id){
//		return serviceTypeMapperBaseDao.deleteServiceTypeMapper(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(与第三方供应商类型对应关系)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteServiceTypeMapperBatch(List<java.math.BigInteger> idList){
//		return serviceTypeMapperBaseDao.deleteServiceTypeMapperBatch(idList);
//	}
	
}
