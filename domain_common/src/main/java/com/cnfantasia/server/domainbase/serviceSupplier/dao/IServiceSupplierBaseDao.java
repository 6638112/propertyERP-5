package com.cnfantasia.server.domainbase.serviceSupplier.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.serviceSupplier.entity.ServiceSupplier;

/**
 * 描述:(上门维修第三方服务供应商) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IServiceSupplierBaseDao {
	/**
	 * 根据条件查询(上门维修第三方服务供应商)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<ServiceSupplier> selectServiceSupplierByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(上门维修第三方服务供应商)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<ServiceSupplier> selectServiceSupplierByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(上门维修第三方服务供应商)信息
	 * @param id
	 * @return
	 */
	public ServiceSupplier selectServiceSupplierBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(上门维修第三方服务供应商)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectServiceSupplierCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(上门维修第三方服务供应商)新增一条记录
	 * @param serviceSupplier
	 * @return
	 */
	public int insertServiceSupplier(ServiceSupplier serviceSupplier);
	
	/**
	 * 批量新增(上门维修第三方服务供应商)信息
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
	 * 根据Id 批量删除(上门维修第三方服务供应商)信息_逻辑删除
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
