package com.cnfantasia.server.domainbase.ebuyHandleAddress.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuyHandleAddress.dao.IEbuyHandleAddressBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyHandleAddress.entity.EbuyHandleAddress;

/**
 * 描述:(手动输入的收货地址) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuyHandleAddressBaseService implements IEbuyHandleAddressBaseService{
	
	private IEbuyHandleAddressBaseDao ebuyHandleAddressBaseDao;
	public void setEbuyHandleAddressBaseDao(IEbuyHandleAddressBaseDao ebuyHandleAddressBaseDao) {
		this.ebuyHandleAddressBaseDao = ebuyHandleAddressBaseDao;
	}
	/**
	 * 根据条件查询(手动输入的收货地址)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyHandleAddress> getEbuyHandleAddressByCondition(Map<String,Object> paramMap){
		return ebuyHandleAddressBaseDao.selectEbuyHandleAddressByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(手动输入的收货地址)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyHandleAddress> getEbuyHandleAddressByConditionDim(Map<String,Object> paramMap){
		return ebuyHandleAddressBaseDao.selectEbuyHandleAddressByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(手动输入的收货地址)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyHandleAddress> getEbuyHandleAddressByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyHandleAddressBaseDao.selectEbuyHandleAddressByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(手动输入的收货地址)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyHandleAddress> getEbuyHandleAddressByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyHandleAddressBaseDao.selectEbuyHandleAddressByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(手动输入的收货地址)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyHandleAddress getEbuyHandleAddressBySeqId(java.math.BigInteger id){
		return ebuyHandleAddressBaseDao.selectEbuyHandleAddressBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(手动输入的收货地址)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyHandleAddressCount(Map<String,Object> paramMap){
		return ebuyHandleAddressBaseDao.selectEbuyHandleAddressCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(手动输入的收货地址)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyHandleAddressCountDim(Map<String,Object> paramMap){
		return ebuyHandleAddressBaseDao.selectEbuyHandleAddressCount(paramMap,true);
	}
	/**
	 * 往(手动输入的收货地址)新增一条记录
	 * @param ebuyHandleAddress
	 * @return
	 */
	@Override
	public int insertEbuyHandleAddress(EbuyHandleAddress ebuyHandleAddress){
		return ebuyHandleAddressBaseDao.insertEbuyHandleAddress(ebuyHandleAddress);
	}
	/**
	 * 批量新增(手动输入的收货地址)
	 * @param ebuyHandleAddressList
	 * @return
	 */
	@Override
	public int insertEbuyHandleAddressBatch(List<EbuyHandleAddress> ebuyHandleAddressList){
		return ebuyHandleAddressBaseDao.insertEbuyHandleAddressBatch(ebuyHandleAddressList);
	}
	/**
	 * 更新(手动输入的收货地址)信息
	 * @param ebuyHandleAddress
	 * @return
	 */
	@Override
	public int updateEbuyHandleAddress(EbuyHandleAddress ebuyHandleAddress){
		return ebuyHandleAddressBaseDao.updateEbuyHandleAddress(ebuyHandleAddress);
	}
	/**
	 * 批量更新(手动输入的收货地址)信息
	 * @param ebuyHandleAddressList
	 * @return
	 */
	@Override
	public int updateEbuyHandleAddressBatch(List<EbuyHandleAddress> ebuyHandleAddressList){
		return ebuyHandleAddressBaseDao.updateEbuyHandleAddressBatch(ebuyHandleAddressList);
	}
	/**
	 * 根据序列号删除(手动输入的收货地址)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyHandleAddressLogic(java.math.BigInteger id){
		return ebuyHandleAddressBaseDao.deleteEbuyHandleAddressLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(手动输入的收货地址)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyHandleAddressLogicBatch(List<java.math.BigInteger> idList){
		return ebuyHandleAddressBaseDao.deleteEbuyHandleAddressLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(手动输入的收货地址)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyHandleAddress(java.math.BigInteger id){
//		return ebuyHandleAddressBaseDao.deleteEbuyHandleAddress(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(手动输入的收货地址)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyHandleAddressBatch(List<java.math.BigInteger> idList){
//		return ebuyHandleAddressBaseDao.deleteEbuyHandleAddressBatch(idList);
//	}
	
}
