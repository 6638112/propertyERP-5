package com.cnfantasia.server.domainbase.ebuyDeliveryAddress.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuyDeliveryAddress.dao.IEbuyDeliveryAddressBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyDeliveryAddress.entity.EbuyDeliveryAddress;

/**
 * 描述:(收货地址) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuyDeliveryAddressBaseService implements IEbuyDeliveryAddressBaseService{
	
	private IEbuyDeliveryAddressBaseDao ebuyDeliveryAddressBaseDao;
	public void setEbuyDeliveryAddressBaseDao(IEbuyDeliveryAddressBaseDao ebuyDeliveryAddressBaseDao) {
		this.ebuyDeliveryAddressBaseDao = ebuyDeliveryAddressBaseDao;
	}
	/**
	 * 根据条件查询(收货地址)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyDeliveryAddress> getEbuyDeliveryAddressByCondition(Map<String,Object> paramMap){
		return ebuyDeliveryAddressBaseDao.selectEbuyDeliveryAddressByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(收货地址)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyDeliveryAddress> getEbuyDeliveryAddressByConditionDim(Map<String,Object> paramMap){
		return ebuyDeliveryAddressBaseDao.selectEbuyDeliveryAddressByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(收货地址)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyDeliveryAddress> getEbuyDeliveryAddressByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyDeliveryAddressBaseDao.selectEbuyDeliveryAddressByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(收货地址)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyDeliveryAddress> getEbuyDeliveryAddressByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyDeliveryAddressBaseDao.selectEbuyDeliveryAddressByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(收货地址)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyDeliveryAddress getEbuyDeliveryAddressBySeqId(java.math.BigInteger id){
		return ebuyDeliveryAddressBaseDao.selectEbuyDeliveryAddressBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(收货地址)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyDeliveryAddressCount(Map<String,Object> paramMap){
		return ebuyDeliveryAddressBaseDao.selectEbuyDeliveryAddressCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(收货地址)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyDeliveryAddressCountDim(Map<String,Object> paramMap){
		return ebuyDeliveryAddressBaseDao.selectEbuyDeliveryAddressCount(paramMap,true);
	}
	/**
	 * 往(收货地址)新增一条记录
	 * @param ebuyDeliveryAddress
	 * @return
	 */
	@Override
	public int insertEbuyDeliveryAddress(EbuyDeliveryAddress ebuyDeliveryAddress){
		return ebuyDeliveryAddressBaseDao.insertEbuyDeliveryAddress(ebuyDeliveryAddress);
	}
	/**
	 * 批量新增(收货地址)
	 * @param ebuyDeliveryAddressList
	 * @return
	 */
	@Override
	public int insertEbuyDeliveryAddressBatch(List<EbuyDeliveryAddress> ebuyDeliveryAddressList){
		return ebuyDeliveryAddressBaseDao.insertEbuyDeliveryAddressBatch(ebuyDeliveryAddressList);
	}
	/**
	 * 更新(收货地址)信息
	 * @param ebuyDeliveryAddress
	 * @return
	 */
	@Override
	public int updateEbuyDeliveryAddress(EbuyDeliveryAddress ebuyDeliveryAddress){
		return ebuyDeliveryAddressBaseDao.updateEbuyDeliveryAddress(ebuyDeliveryAddress);
	}
	/**
	 * 批量更新(收货地址)信息
	 * @param ebuyDeliveryAddressList
	 * @return
	 */
	@Override
	public int updateEbuyDeliveryAddressBatch(List<EbuyDeliveryAddress> ebuyDeliveryAddressList){
		return ebuyDeliveryAddressBaseDao.updateEbuyDeliveryAddressBatch(ebuyDeliveryAddressList);
	}
	/**
	 * 根据序列号删除(收货地址)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyDeliveryAddressLogic(java.math.BigInteger id){
		return ebuyDeliveryAddressBaseDao.deleteEbuyDeliveryAddressLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(收货地址)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyDeliveryAddressLogicBatch(List<java.math.BigInteger> idList){
		return ebuyDeliveryAddressBaseDao.deleteEbuyDeliveryAddressLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(收货地址)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyDeliveryAddress(java.math.BigInteger id){
//		return ebuyDeliveryAddressBaseDao.deleteEbuyDeliveryAddress(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(收货地址)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyDeliveryAddressBatch(List<java.math.BigInteger> idList){
//		return ebuyDeliveryAddressBaseDao.deleteEbuyDeliveryAddressBatch(idList);
//	}
	
}
