package com.cnfantasia.server.domainbase.addressCountry.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.addressCountry.dao.IAddressCountryBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.addressCountry.entity.AddressCountry;

/**
 * 描述:(国家表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class AddressCountryBaseService implements IAddressCountryBaseService{
	
	private IAddressCountryBaseDao addressCountryBaseDao;
	public void setAddressCountryBaseDao(IAddressCountryBaseDao addressCountryBaseDao) {
		this.addressCountryBaseDao = addressCountryBaseDao;
	}
	/**
	 * 根据条件查询(国家表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<AddressCountry> getAddressCountryByCondition(Map<String,Object> paramMap){
		return addressCountryBaseDao.selectAddressCountryByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(国家表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<AddressCountry> getAddressCountryByConditionDim(Map<String,Object> paramMap){
		return addressCountryBaseDao.selectAddressCountryByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(国家表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<AddressCountry> getAddressCountryByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return addressCountryBaseDao.selectAddressCountryByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(国家表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<AddressCountry> getAddressCountryByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return addressCountryBaseDao.selectAddressCountryByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(国家表)信息
	 * @param id
	 * @return
	 */
	@Override
	public AddressCountry getAddressCountryBySeqId(java.math.BigInteger id){
		return addressCountryBaseDao.selectAddressCountryBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(国家表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getAddressCountryCount(Map<String,Object> paramMap){
		return addressCountryBaseDao.selectAddressCountryCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(国家表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getAddressCountryCountDim(Map<String,Object> paramMap){
		return addressCountryBaseDao.selectAddressCountryCount(paramMap,true);
	}
	/**
	 * 往(国家表)新增一条记录
	 * @param addressCountry
	 * @return
	 */
	@Override
	public int insertAddressCountry(AddressCountry addressCountry){
		return addressCountryBaseDao.insertAddressCountry(addressCountry);
	}
	/**
	 * 批量新增(国家表)
	 * @param addressCountryList
	 * @return
	 */
	@Override
	public int insertAddressCountryBatch(List<AddressCountry> addressCountryList){
		return addressCountryBaseDao.insertAddressCountryBatch(addressCountryList);
	}
	/**
	 * 更新(国家表)信息
	 * @param addressCountry
	 * @return
	 */
	@Override
	public int updateAddressCountry(AddressCountry addressCountry){
		return addressCountryBaseDao.updateAddressCountry(addressCountry);
	}
	/**
	 * 批量更新(国家表)信息
	 * @param addressCountryList
	 * @return
	 */
	@Override
	public int updateAddressCountryBatch(List<AddressCountry> addressCountryList){
		return addressCountryBaseDao.updateAddressCountryBatch(addressCountryList);
	}
	/**
	 * 根据序列号删除(国家表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteAddressCountryLogic(java.math.BigInteger id){
		return addressCountryBaseDao.deleteAddressCountryLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(国家表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteAddressCountryLogicBatch(List<java.math.BigInteger> idList){
		return addressCountryBaseDao.deleteAddressCountryLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(国家表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteAddressCountry(java.math.BigInteger id){
//		return addressCountryBaseDao.deleteAddressCountry(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(国家表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteAddressCountryBatch(List<java.math.BigInteger> idList){
//		return addressCountryBaseDao.deleteAddressCountryBatch(idList);
//	}
	
}
