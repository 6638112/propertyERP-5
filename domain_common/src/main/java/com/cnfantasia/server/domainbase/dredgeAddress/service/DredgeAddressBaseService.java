package com.cnfantasia.server.domainbase.dredgeAddress.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.dredgeAddress.dao.IDredgeAddressBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeAddress.entity.DredgeAddress;

/**
 * 描述:(维修地址) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class DredgeAddressBaseService implements IDredgeAddressBaseService{
	
	private IDredgeAddressBaseDao dredgeAddressBaseDao;
	public void setDredgeAddressBaseDao(IDredgeAddressBaseDao dredgeAddressBaseDao) {
		this.dredgeAddressBaseDao = dredgeAddressBaseDao;
	}
	/**
	 * 根据条件查询(维修地址)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<DredgeAddress> getDredgeAddressByCondition(Map<String,Object> paramMap){
		return dredgeAddressBaseDao.selectDredgeAddressByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(维修地址)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<DredgeAddress> getDredgeAddressByConditionDim(Map<String,Object> paramMap){
		return dredgeAddressBaseDao.selectDredgeAddressByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(维修地址)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<DredgeAddress> getDredgeAddressByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return dredgeAddressBaseDao.selectDredgeAddressByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(维修地址)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<DredgeAddress> getDredgeAddressByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return dredgeAddressBaseDao.selectDredgeAddressByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(维修地址)信息
	 * @param id
	 * @return
	 */
	@Override
	public DredgeAddress getDredgeAddressBySeqId(java.math.BigInteger id){
		return dredgeAddressBaseDao.selectDredgeAddressBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(维修地址)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getDredgeAddressCount(Map<String,Object> paramMap){
		return dredgeAddressBaseDao.selectDredgeAddressCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(维修地址)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getDredgeAddressCountDim(Map<String,Object> paramMap){
		return dredgeAddressBaseDao.selectDredgeAddressCount(paramMap,true);
	}
	/**
	 * 往(维修地址)新增一条记录
	 * @param dredgeAddress
	 * @return
	 */
	@Override
	public int insertDredgeAddress(DredgeAddress dredgeAddress){
		return dredgeAddressBaseDao.insertDredgeAddress(dredgeAddress);
	}
	/**
	 * 批量新增(维修地址)
	 * @param dredgeAddressList
	 * @return
	 */
	@Override
	public int insertDredgeAddressBatch(List<DredgeAddress> dredgeAddressList){
		return dredgeAddressBaseDao.insertDredgeAddressBatch(dredgeAddressList);
	}
	/**
	 * 更新(维修地址)信息
	 * @param dredgeAddress
	 * @return
	 */
	@Override
	public int updateDredgeAddress(DredgeAddress dredgeAddress){
		return dredgeAddressBaseDao.updateDredgeAddress(dredgeAddress);
	}
	/**
	 * 批量更新(维修地址)信息
	 * @param dredgeAddressList
	 * @return
	 */
	@Override
	public int updateDredgeAddressBatch(List<DredgeAddress> dredgeAddressList){
		return dredgeAddressBaseDao.updateDredgeAddressBatch(dredgeAddressList);
	}
	/**
	 * 根据序列号删除(维修地址)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteDredgeAddressLogic(java.math.BigInteger id){
		return dredgeAddressBaseDao.deleteDredgeAddressLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(维修地址)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteDredgeAddressLogicBatch(List<java.math.BigInteger> idList){
		return dredgeAddressBaseDao.deleteDredgeAddressLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(维修地址)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeAddress(java.math.BigInteger id){
//		return dredgeAddressBaseDao.deleteDredgeAddress(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(维修地址)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeAddressBatch(List<java.math.BigInteger> idList){
//		return dredgeAddressBaseDao.deleteDredgeAddressBatch(idList);
//	}
	
}
