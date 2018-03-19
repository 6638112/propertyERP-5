package com.cnfantasia.server.domainbase.serviceSupplier.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.serviceSupplier.dao.IServiceSupplierBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.serviceSupplier.entity.ServiceSupplier;

/**
 * 描述:(上门维修第三方服务供应商) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class ServiceSupplierBaseService implements IServiceSupplierBaseService{
	
	private IServiceSupplierBaseDao serviceSupplierBaseDao;
	public void setServiceSupplierBaseDao(IServiceSupplierBaseDao serviceSupplierBaseDao) {
		this.serviceSupplierBaseDao = serviceSupplierBaseDao;
	}
	/**
	 * 根据条件查询(上门维修第三方服务供应商)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<ServiceSupplier> getServiceSupplierByCondition(Map<String,Object> paramMap){
		return serviceSupplierBaseDao.selectServiceSupplierByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(上门维修第三方服务供应商)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<ServiceSupplier> getServiceSupplierByConditionDim(Map<String,Object> paramMap){
		return serviceSupplierBaseDao.selectServiceSupplierByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(上门维修第三方服务供应商)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<ServiceSupplier> getServiceSupplierByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return serviceSupplierBaseDao.selectServiceSupplierByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(上门维修第三方服务供应商)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<ServiceSupplier> getServiceSupplierByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return serviceSupplierBaseDao.selectServiceSupplierByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(上门维修第三方服务供应商)信息
	 * @param id
	 * @return
	 */
	@Override
	public ServiceSupplier getServiceSupplierBySeqId(java.math.BigInteger id){
		return serviceSupplierBaseDao.selectServiceSupplierBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(上门维修第三方服务供应商)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getServiceSupplierCount(Map<String,Object> paramMap){
		return serviceSupplierBaseDao.selectServiceSupplierCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(上门维修第三方服务供应商)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getServiceSupplierCountDim(Map<String,Object> paramMap){
		return serviceSupplierBaseDao.selectServiceSupplierCount(paramMap,true);
	}
	/**
	 * 往(上门维修第三方服务供应商)新增一条记录
	 * @param serviceSupplier
	 * @return
	 */
	@Override
	public int insertServiceSupplier(ServiceSupplier serviceSupplier){
		return serviceSupplierBaseDao.insertServiceSupplier(serviceSupplier);
	}
	/**
	 * 批量新增(上门维修第三方服务供应商)
	 * @param serviceSupplierList
	 * @return
	 */
	@Override
	public int insertServiceSupplierBatch(List<ServiceSupplier> serviceSupplierList){
		return serviceSupplierBaseDao.insertServiceSupplierBatch(serviceSupplierList);
	}
	/**
	 * 更新(上门维修第三方服务供应商)信息
	 * @param serviceSupplier
	 * @return
	 */
	@Override
	public int updateServiceSupplier(ServiceSupplier serviceSupplier){
		return serviceSupplierBaseDao.updateServiceSupplier(serviceSupplier);
	}
	/**
	 * 批量更新(上门维修第三方服务供应商)信息
	 * @param serviceSupplierList
	 * @return
	 */
	@Override
	public int updateServiceSupplierBatch(List<ServiceSupplier> serviceSupplierList){
		return serviceSupplierBaseDao.updateServiceSupplierBatch(serviceSupplierList);
	}
	/**
	 * 根据序列号删除(上门维修第三方服务供应商)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteServiceSupplierLogic(java.math.BigInteger id){
		return serviceSupplierBaseDao.deleteServiceSupplierLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(上门维修第三方服务供应商)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteServiceSupplierLogicBatch(List<java.math.BigInteger> idList){
		return serviceSupplierBaseDao.deleteServiceSupplierLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(上门维修第三方服务供应商)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteServiceSupplier(java.math.BigInteger id){
//		return serviceSupplierBaseDao.deleteServiceSupplier(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(上门维修第三方服务供应商)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteServiceSupplierBatch(List<java.math.BigInteger> idList){
//		return serviceSupplierBaseDao.deleteServiceSupplierBatch(idList);
//	}
	
}
