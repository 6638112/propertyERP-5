package com.cnfantasia.server.domainbase.serviceSupplier.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.serviceSupplier.entity.ServiceSupplier;

/**
 * 描述:(上门维修第三方服务供应商) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IServiceSupplierBaseService {
	
	/**
	 * 根据条件查询(上门维修第三方服务供应商)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<ServiceSupplier> getServiceSupplierByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(上门维修第三方服务供应商)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<ServiceSupplier> getServiceSupplierByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(上门维修第三方服务供应商)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<ServiceSupplier> getServiceSupplierByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(上门维修第三方服务供应商)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<ServiceSupplier> getServiceSupplierByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(上门维修第三方服务供应商)信息
	 * @param id
	 * @return
	 */
	public ServiceSupplier getServiceSupplierBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(上门维修第三方服务供应商)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getServiceSupplierCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(上门维修第三方服务供应商)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getServiceSupplierCountDim(Map<String,Object> paramMap);
	/**
	 * 往(上门维修第三方服务供应商)新增一条记录
	 * @param serviceSupplier
	 * @return
	 */
	public int insertServiceSupplier(ServiceSupplier serviceSupplier);
	/**
	 * 批量新增(上门维修第三方服务供应商)
	 * @param serviceSupplierList
	 * @return
	 */
	public int insertServiceSupplierBatch(List<ServiceSupplier> serviceSupplierList);
	/**
	 * 更新(上门维修第三方服务供应商)信息
	 * @param serviceSupplier
	 * @return
	 */
	public int updateServiceSupplier(ServiceSupplier serviceSupplier);
	/**
	 * 批量更新(上门维修第三方服务供应商)信息
	 * @param serviceSupplierList
	 * @return
	 */
	public int updateServiceSupplierBatch(List<ServiceSupplier> serviceSupplierList);
	/**
	 * 根据序列号删除(上门维修第三方服务供应商)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteServiceSupplierLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(上门维修第三方服务供应商)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteServiceSupplierLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(上门维修第三方服务供应商)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteServiceSupplier(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(上门维修第三方服务供应商)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteServiceSupplierBatch(List<java.math.BigInteger> idList);
	
}
