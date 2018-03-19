package com.cnfantasia.server.domainbase.serviceTypeMapper.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.serviceTypeMapper.entity.ServiceTypeMapper;

/**
 * 描述:(与第三方供应商类型对应关系) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IServiceTypeMapperBaseDao {
	/**
	 * 根据条件查询(与第三方供应商类型对应关系)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<ServiceTypeMapper> selectServiceTypeMapperByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(与第三方供应商类型对应关系)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<ServiceTypeMapper> selectServiceTypeMapperByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(与第三方供应商类型对应关系)信息
	 * @param id
	 * @return
	 */
	public ServiceTypeMapper selectServiceTypeMapperBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(与第三方供应商类型对应关系)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectServiceTypeMapperCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(与第三方供应商类型对应关系)新增一条记录
	 * @param serviceTypeMapper
	 * @return
	 */
	public int insertServiceTypeMapper(ServiceTypeMapper serviceTypeMapper);
	
	/**
	 * 批量新增(与第三方供应商类型对应关系)信息
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
	 * 根据Id 批量删除(与第三方供应商类型对应关系)信息_逻辑删除
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
