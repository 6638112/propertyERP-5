package com.cnfantasia.server.domainbase.serviceTypeMapper.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.serviceTypeMapper.entity.ServiceTypeMapper;

/**
 * 描述:(与第三方供应商类型对应关系) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IServiceTypeMapperBaseService {
	
	/**
	 * 根据条件查询(与第三方供应商类型对应关系)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<ServiceTypeMapper> getServiceTypeMapperByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(与第三方供应商类型对应关系)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<ServiceTypeMapper> getServiceTypeMapperByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(与第三方供应商类型对应关系)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<ServiceTypeMapper> getServiceTypeMapperByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(与第三方供应商类型对应关系)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<ServiceTypeMapper> getServiceTypeMapperByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(与第三方供应商类型对应关系)信息
	 * @param id
	 * @return
	 */
	public ServiceTypeMapper getServiceTypeMapperBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(与第三方供应商类型对应关系)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getServiceTypeMapperCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(与第三方供应商类型对应关系)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getServiceTypeMapperCountDim(Map<String,Object> paramMap);
	/**
	 * 往(与第三方供应商类型对应关系)新增一条记录
	 * @param serviceTypeMapper
	 * @return
	 */
	public int insertServiceTypeMapper(ServiceTypeMapper serviceTypeMapper);
	/**
	 * 批量新增(与第三方供应商类型对应关系)
	 * @param serviceTypeMapperList
	 * @return
	 */
	public int insertServiceTypeMapperBatch(List<ServiceTypeMapper> serviceTypeMapperList);
	/**
	 * 更新(与第三方供应商类型对应关系)信息
	 * @param serviceTypeMapper
	 * @return
	 */
	public int updateServiceTypeMapper(ServiceTypeMapper serviceTypeMapper);
	/**
	 * 批量更新(与第三方供应商类型对应关系)信息
	 * @param serviceTypeMapperList
	 * @return
	 */
	public int updateServiceTypeMapperBatch(List<ServiceTypeMapper> serviceTypeMapperList);
	/**
	 * 根据序列号删除(与第三方供应商类型对应关系)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteServiceTypeMapperLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(与第三方供应商类型对应关系)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteServiceTypeMapperLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(与第三方供应商类型对应关系)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteServiceTypeMapper(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(与第三方供应商类型对应关系)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteServiceTypeMapperBatch(List<java.math.BigInteger> idList);
	
}
