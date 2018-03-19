package com.cnfantasia.server.domainbase.addressProvince.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.addressProvince.dao.IAddressProvinceBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.addressProvince.entity.AddressProvince;

/**
 * 描述:(省) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class AddressProvinceBaseService implements IAddressProvinceBaseService{
	
	private IAddressProvinceBaseDao addressProvinceBaseDao;
	public void setAddressProvinceBaseDao(IAddressProvinceBaseDao addressProvinceBaseDao) {
		this.addressProvinceBaseDao = addressProvinceBaseDao;
	}
	/**
	 * 根据条件查询(省)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<AddressProvince> getAddressProvinceByCondition(Map<String,Object> paramMap){
		return addressProvinceBaseDao.selectAddressProvinceByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(省)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<AddressProvince> getAddressProvinceByConditionDim(Map<String,Object> paramMap){
		return addressProvinceBaseDao.selectAddressProvinceByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(省)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<AddressProvince> getAddressProvinceByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return addressProvinceBaseDao.selectAddressProvinceByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(省)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<AddressProvince> getAddressProvinceByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return addressProvinceBaseDao.selectAddressProvinceByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(省)信息
	 * @param id
	 * @return
	 */
	@Override
	public AddressProvince getAddressProvinceBySeqId(java.math.BigInteger id){
		return addressProvinceBaseDao.selectAddressProvinceBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(省)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getAddressProvinceCount(Map<String,Object> paramMap){
		return addressProvinceBaseDao.selectAddressProvinceCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(省)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getAddressProvinceCountDim(Map<String,Object> paramMap){
		return addressProvinceBaseDao.selectAddressProvinceCount(paramMap,true);
	}
	/**
	 * 往(省)新增一条记录
	 * @param addressProvince
	 * @return
	 */
	@Override
	public int insertAddressProvince(AddressProvince addressProvince){
		return addressProvinceBaseDao.insertAddressProvince(addressProvince);
	}
	/**
	 * 批量新增(省)
	 * @param addressProvinceList
	 * @return
	 */
	@Override
	public int insertAddressProvinceBatch(List<AddressProvince> addressProvinceList){
		return addressProvinceBaseDao.insertAddressProvinceBatch(addressProvinceList);
	}
	/**
	 * 更新(省)信息
	 * @param addressProvince
	 * @return
	 */
	@Override
	public int updateAddressProvince(AddressProvince addressProvince){
		return addressProvinceBaseDao.updateAddressProvince(addressProvince);
	}
	/**
	 * 批量更新(省)信息
	 * @param addressProvinceList
	 * @return
	 */
	@Override
	public int updateAddressProvinceBatch(List<AddressProvince> addressProvinceList){
		return addressProvinceBaseDao.updateAddressProvinceBatch(addressProvinceList);
	}
	/**
	 * 根据序列号删除(省)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteAddressProvinceLogic(java.math.BigInteger id){
		return addressProvinceBaseDao.deleteAddressProvinceLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(省)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteAddressProvinceLogicBatch(List<java.math.BigInteger> idList){
		return addressProvinceBaseDao.deleteAddressProvinceLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(省)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteAddressProvince(java.math.BigInteger id){
//		return addressProvinceBaseDao.deleteAddressProvince(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(省)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteAddressProvinceBatch(List<java.math.BigInteger> idList){
//		return addressProvinceBaseDao.deleteAddressProvinceBatch(idList);
//	}
	
}
