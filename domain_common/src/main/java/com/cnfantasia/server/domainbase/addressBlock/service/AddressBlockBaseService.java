package com.cnfantasia.server.domainbase.addressBlock.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.addressBlock.dao.IAddressBlockBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.addressBlock.entity.AddressBlock;

/**
 * 描述:(区) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class AddressBlockBaseService implements IAddressBlockBaseService{
	
	private IAddressBlockBaseDao addressBlockBaseDao;
	public void setAddressBlockBaseDao(IAddressBlockBaseDao addressBlockBaseDao) {
		this.addressBlockBaseDao = addressBlockBaseDao;
	}
	/**
	 * 根据条件查询(区)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<AddressBlock> getAddressBlockByCondition(Map<String,Object> paramMap){
		return addressBlockBaseDao.selectAddressBlockByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(区)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<AddressBlock> getAddressBlockByConditionDim(Map<String,Object> paramMap){
		return addressBlockBaseDao.selectAddressBlockByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(区)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<AddressBlock> getAddressBlockByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return addressBlockBaseDao.selectAddressBlockByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(区)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<AddressBlock> getAddressBlockByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return addressBlockBaseDao.selectAddressBlockByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(区)信息
	 * @param id
	 * @return
	 */
	@Override
	public AddressBlock getAddressBlockBySeqId(java.math.BigInteger id){
		return addressBlockBaseDao.selectAddressBlockBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(区)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getAddressBlockCount(Map<String,Object> paramMap){
		return addressBlockBaseDao.selectAddressBlockCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(区)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getAddressBlockCountDim(Map<String,Object> paramMap){
		return addressBlockBaseDao.selectAddressBlockCount(paramMap,true);
	}
	/**
	 * 往(区)新增一条记录
	 * @param addressBlock
	 * @return
	 */
	@Override
	public int insertAddressBlock(AddressBlock addressBlock){
		return addressBlockBaseDao.insertAddressBlock(addressBlock);
	}
	/**
	 * 批量新增(区)
	 * @param addressBlockList
	 * @return
	 */
	@Override
	public int insertAddressBlockBatch(List<AddressBlock> addressBlockList){
		return addressBlockBaseDao.insertAddressBlockBatch(addressBlockList);
	}
	/**
	 * 更新(区)信息
	 * @param addressBlock
	 * @return
	 */
	@Override
	public int updateAddressBlock(AddressBlock addressBlock){
		return addressBlockBaseDao.updateAddressBlock(addressBlock);
	}
	/**
	 * 批量更新(区)信息
	 * @param addressBlockList
	 * @return
	 */
	@Override
	public int updateAddressBlockBatch(List<AddressBlock> addressBlockList){
		return addressBlockBaseDao.updateAddressBlockBatch(addressBlockList);
	}
	/**
	 * 根据序列号删除(区)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteAddressBlockLogic(java.math.BigInteger id){
		return addressBlockBaseDao.deleteAddressBlockLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(区)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteAddressBlockLogicBatch(List<java.math.BigInteger> idList){
		return addressBlockBaseDao.deleteAddressBlockLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(区)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteAddressBlock(java.math.BigInteger id){
//		return addressBlockBaseDao.deleteAddressBlock(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(区)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteAddressBlockBatch(List<java.math.BigInteger> idList){
//		return addressBlockBaseDao.deleteAddressBlockBatch(idList);
//	}
	
}
