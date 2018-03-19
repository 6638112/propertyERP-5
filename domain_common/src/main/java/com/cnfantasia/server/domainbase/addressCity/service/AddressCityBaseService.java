package com.cnfantasia.server.domainbase.addressCity.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.addressCity.dao.IAddressCityBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.addressCity.entity.AddressCity;

/**
 * 描述:(市) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class AddressCityBaseService implements IAddressCityBaseService{
	
	private IAddressCityBaseDao addressCityBaseDao;
	public void setAddressCityBaseDao(IAddressCityBaseDao addressCityBaseDao) {
		this.addressCityBaseDao = addressCityBaseDao;
	}
	/**
	 * 根据条件查询(市)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<AddressCity> getAddressCityByCondition(Map<String,Object> paramMap){
		return addressCityBaseDao.selectAddressCityByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(市)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<AddressCity> getAddressCityByConditionDim(Map<String,Object> paramMap){
		return addressCityBaseDao.selectAddressCityByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(市)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<AddressCity> getAddressCityByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return addressCityBaseDao.selectAddressCityByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(市)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<AddressCity> getAddressCityByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return addressCityBaseDao.selectAddressCityByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(市)信息
	 * @param id
	 * @return
	 */
	@Override
	public AddressCity getAddressCityBySeqId(java.math.BigInteger id){
		return addressCityBaseDao.selectAddressCityBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(市)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getAddressCityCount(Map<String,Object> paramMap){
		return addressCityBaseDao.selectAddressCityCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(市)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getAddressCityCountDim(Map<String,Object> paramMap){
		return addressCityBaseDao.selectAddressCityCount(paramMap,true);
	}
	/**
	 * 往(市)新增一条记录
	 * @param addressCity
	 * @return
	 */
	@Override
	public int insertAddressCity(AddressCity addressCity){
		return addressCityBaseDao.insertAddressCity(addressCity);
	}
	/**
	 * 批量新增(市)
	 * @param addressCityList
	 * @return
	 */
	@Override
	public int insertAddressCityBatch(List<AddressCity> addressCityList){
		return addressCityBaseDao.insertAddressCityBatch(addressCityList);
	}
	/**
	 * 更新(市)信息
	 * @param addressCity
	 * @return
	 */
	@Override
	public int updateAddressCity(AddressCity addressCity){
		return addressCityBaseDao.updateAddressCity(addressCity);
	}
	/**
	 * 批量更新(市)信息
	 * @param addressCityList
	 * @return
	 */
	@Override
	public int updateAddressCityBatch(List<AddressCity> addressCityList){
		return addressCityBaseDao.updateAddressCityBatch(addressCityList);
	}
	/**
	 * 根据序列号删除(市)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteAddressCityLogic(java.math.BigInteger id){
		return addressCityBaseDao.deleteAddressCityLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(市)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteAddressCityLogicBatch(List<java.math.BigInteger> idList){
		return addressCityBaseDao.deleteAddressCityLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(市)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteAddressCity(java.math.BigInteger id){
//		return addressCityBaseDao.deleteAddressCity(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(市)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteAddressCityBatch(List<java.math.BigInteger> idList){
//		return addressCityBaseDao.deleteAddressCityBatch(idList);
//	}
	
}
