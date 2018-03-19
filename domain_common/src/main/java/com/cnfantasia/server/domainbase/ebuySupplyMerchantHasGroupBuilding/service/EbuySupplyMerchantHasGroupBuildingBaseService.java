package com.cnfantasia.server.domainbase.ebuySupplyMerchantHasGroupBuilding.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuySupplyMerchantHasGroupBuilding.dao.IEbuySupplyMerchantHasGroupBuildingBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuySupplyMerchantHasGroupBuilding.entity.EbuySupplyMerchantHasGroupBuilding;

/**
 * 描述:(供应商与小区关联表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuySupplyMerchantHasGroupBuildingBaseService implements IEbuySupplyMerchantHasGroupBuildingBaseService{
	
	private IEbuySupplyMerchantHasGroupBuildingBaseDao ebuySupplyMerchantHasGroupBuildingBaseDao;
	public void setEbuySupplyMerchantHasGroupBuildingBaseDao(IEbuySupplyMerchantHasGroupBuildingBaseDao ebuySupplyMerchantHasGroupBuildingBaseDao) {
		this.ebuySupplyMerchantHasGroupBuildingBaseDao = ebuySupplyMerchantHasGroupBuildingBaseDao;
	}
	/**
	 * 根据条件查询(供应商与小区关联表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuySupplyMerchantHasGroupBuilding> getEbuySupplyMerchantHasGroupBuildingByCondition(Map<String,Object> paramMap){
		return ebuySupplyMerchantHasGroupBuildingBaseDao.selectEbuySupplyMerchantHasGroupBuildingByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(供应商与小区关联表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuySupplyMerchantHasGroupBuilding> getEbuySupplyMerchantHasGroupBuildingByConditionDim(Map<String,Object> paramMap){
		return ebuySupplyMerchantHasGroupBuildingBaseDao.selectEbuySupplyMerchantHasGroupBuildingByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(供应商与小区关联表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuySupplyMerchantHasGroupBuilding> getEbuySupplyMerchantHasGroupBuildingByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuySupplyMerchantHasGroupBuildingBaseDao.selectEbuySupplyMerchantHasGroupBuildingByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(供应商与小区关联表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuySupplyMerchantHasGroupBuilding> getEbuySupplyMerchantHasGroupBuildingByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuySupplyMerchantHasGroupBuildingBaseDao.selectEbuySupplyMerchantHasGroupBuildingByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(供应商与小区关联表)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuySupplyMerchantHasGroupBuilding getEbuySupplyMerchantHasGroupBuildingBySeqId(java.math.BigInteger id){
		return ebuySupplyMerchantHasGroupBuildingBaseDao.selectEbuySupplyMerchantHasGroupBuildingBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(供应商与小区关联表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuySupplyMerchantHasGroupBuildingCount(Map<String,Object> paramMap){
		return ebuySupplyMerchantHasGroupBuildingBaseDao.selectEbuySupplyMerchantHasGroupBuildingCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(供应商与小区关联表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuySupplyMerchantHasGroupBuildingCountDim(Map<String,Object> paramMap){
		return ebuySupplyMerchantHasGroupBuildingBaseDao.selectEbuySupplyMerchantHasGroupBuildingCount(paramMap,true);
	}
	/**
	 * 往(供应商与小区关联表)新增一条记录
	 * @param ebuySupplyMerchantHasGroupBuilding
	 * @return
	 */
	@Override
	public int insertEbuySupplyMerchantHasGroupBuilding(EbuySupplyMerchantHasGroupBuilding ebuySupplyMerchantHasGroupBuilding){
		return ebuySupplyMerchantHasGroupBuildingBaseDao.insertEbuySupplyMerchantHasGroupBuilding(ebuySupplyMerchantHasGroupBuilding);
	}
	/**
	 * 批量新增(供应商与小区关联表)
	 * @param ebuySupplyMerchantHasGroupBuildingList
	 * @return
	 */
	@Override
	public int insertEbuySupplyMerchantHasGroupBuildingBatch(List<EbuySupplyMerchantHasGroupBuilding> ebuySupplyMerchantHasGroupBuildingList){
		return ebuySupplyMerchantHasGroupBuildingBaseDao.insertEbuySupplyMerchantHasGroupBuildingBatch(ebuySupplyMerchantHasGroupBuildingList);
	}
	/**
	 * 更新(供应商与小区关联表)信息
	 * @param ebuySupplyMerchantHasGroupBuilding
	 * @return
	 */
	@Override
	public int updateEbuySupplyMerchantHasGroupBuilding(EbuySupplyMerchantHasGroupBuilding ebuySupplyMerchantHasGroupBuilding){
		return ebuySupplyMerchantHasGroupBuildingBaseDao.updateEbuySupplyMerchantHasGroupBuilding(ebuySupplyMerchantHasGroupBuilding);
	}
	/**
	 * 批量更新(供应商与小区关联表)信息
	 * @param ebuySupplyMerchantHasGroupBuildingList
	 * @return
	 */
	@Override
	public int updateEbuySupplyMerchantHasGroupBuildingBatch(List<EbuySupplyMerchantHasGroupBuilding> ebuySupplyMerchantHasGroupBuildingList){
		return ebuySupplyMerchantHasGroupBuildingBaseDao.updateEbuySupplyMerchantHasGroupBuildingBatch(ebuySupplyMerchantHasGroupBuildingList);
	}
	/**
	 * 根据序列号删除(供应商与小区关联表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuySupplyMerchantHasGroupBuildingLogic(java.math.BigInteger id){
		return ebuySupplyMerchantHasGroupBuildingBaseDao.deleteEbuySupplyMerchantHasGroupBuildingLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(供应商与小区关联表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuySupplyMerchantHasGroupBuildingLogicBatch(List<java.math.BigInteger> idList){
		return ebuySupplyMerchantHasGroupBuildingBaseDao.deleteEbuySupplyMerchantHasGroupBuildingLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(供应商与小区关联表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuySupplyMerchantHasGroupBuilding(java.math.BigInteger id){
//		return ebuySupplyMerchantHasGroupBuildingBaseDao.deleteEbuySupplyMerchantHasGroupBuilding(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(供应商与小区关联表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuySupplyMerchantHasGroupBuildingBatch(List<java.math.BigInteger> idList){
//		return ebuySupplyMerchantHasGroupBuildingBaseDao.deleteEbuySupplyMerchantHasGroupBuildingBatch(idList);
//	}
	
}
