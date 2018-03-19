package com.cnfantasia.server.domainbase.addressBlockSelfDefined.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.addressBlockSelfDefined.dao.IAddressBlockSelfDefinedBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.addressBlockSelfDefined.entity.AddressBlockSelfDefined;

/**
 * 描述:(自定义片区) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class AddressBlockSelfDefinedBaseService implements IAddressBlockSelfDefinedBaseService{
	
	private IAddressBlockSelfDefinedBaseDao addressBlockSelfDefinedBaseDao;
	public void setAddressBlockSelfDefinedBaseDao(IAddressBlockSelfDefinedBaseDao addressBlockSelfDefinedBaseDao) {
		this.addressBlockSelfDefinedBaseDao = addressBlockSelfDefinedBaseDao;
	}
	/**
	 * 根据条件查询(自定义片区)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<AddressBlockSelfDefined> getAddressBlockSelfDefinedByCondition(Map<String,Object> paramMap){
		return addressBlockSelfDefinedBaseDao.selectAddressBlockSelfDefinedByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(自定义片区)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<AddressBlockSelfDefined> getAddressBlockSelfDefinedByConditionDim(Map<String,Object> paramMap){
		return addressBlockSelfDefinedBaseDao.selectAddressBlockSelfDefinedByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(自定义片区)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<AddressBlockSelfDefined> getAddressBlockSelfDefinedByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return addressBlockSelfDefinedBaseDao.selectAddressBlockSelfDefinedByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(自定义片区)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<AddressBlockSelfDefined> getAddressBlockSelfDefinedByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return addressBlockSelfDefinedBaseDao.selectAddressBlockSelfDefinedByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(自定义片区)信息
	 * @param id
	 * @return
	 */
	@Override
	public AddressBlockSelfDefined getAddressBlockSelfDefinedBySeqId(java.math.BigInteger id){
		return addressBlockSelfDefinedBaseDao.selectAddressBlockSelfDefinedBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(自定义片区)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getAddressBlockSelfDefinedCount(Map<String,Object> paramMap){
		return addressBlockSelfDefinedBaseDao.selectAddressBlockSelfDefinedCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(自定义片区)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getAddressBlockSelfDefinedCountDim(Map<String,Object> paramMap){
		return addressBlockSelfDefinedBaseDao.selectAddressBlockSelfDefinedCount(paramMap,true);
	}
	/**
	 * 往(自定义片区)新增一条记录
	 * @param addressBlockSelfDefined
	 * @return
	 */
	@Override
	public int insertAddressBlockSelfDefined(AddressBlockSelfDefined addressBlockSelfDefined){
		return addressBlockSelfDefinedBaseDao.insertAddressBlockSelfDefined(addressBlockSelfDefined);
	}
	/**
	 * 批量新增(自定义片区)
	 * @param addressBlockSelfDefinedList
	 * @return
	 */
	@Override
	public int insertAddressBlockSelfDefinedBatch(List<AddressBlockSelfDefined> addressBlockSelfDefinedList){
		return addressBlockSelfDefinedBaseDao.insertAddressBlockSelfDefinedBatch(addressBlockSelfDefinedList);
	}
	/**
	 * 更新(自定义片区)信息
	 * @param addressBlockSelfDefined
	 * @return
	 */
	@Override
	public int updateAddressBlockSelfDefined(AddressBlockSelfDefined addressBlockSelfDefined){
		return addressBlockSelfDefinedBaseDao.updateAddressBlockSelfDefined(addressBlockSelfDefined);
	}
	/**
	 * 批量更新(自定义片区)信息
	 * @param addressBlockSelfDefinedList
	 * @return
	 */
	@Override
	public int updateAddressBlockSelfDefinedBatch(List<AddressBlockSelfDefined> addressBlockSelfDefinedList){
		return addressBlockSelfDefinedBaseDao.updateAddressBlockSelfDefinedBatch(addressBlockSelfDefinedList);
	}
	/**
	 * 根据序列号删除(自定义片区)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteAddressBlockSelfDefinedLogic(java.math.BigInteger id){
		return addressBlockSelfDefinedBaseDao.deleteAddressBlockSelfDefinedLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(自定义片区)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteAddressBlockSelfDefinedLogicBatch(List<java.math.BigInteger> idList){
		return addressBlockSelfDefinedBaseDao.deleteAddressBlockSelfDefinedLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(自定义片区)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteAddressBlockSelfDefined(java.math.BigInteger id){
//		return addressBlockSelfDefinedBaseDao.deleteAddressBlockSelfDefined(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(自定义片区)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteAddressBlockSelfDefinedBatch(List<java.math.BigInteger> idList){
//		return addressBlockSelfDefinedBaseDao.deleteAddressBlockSelfDefinedBatch(idList);
//	}
	
}
