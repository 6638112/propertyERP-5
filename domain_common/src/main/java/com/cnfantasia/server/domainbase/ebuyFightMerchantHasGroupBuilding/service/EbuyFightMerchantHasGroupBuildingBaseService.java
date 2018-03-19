package com.cnfantasia.server.domainbase.ebuyFightMerchantHasGroupBuilding.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuyFightMerchantHasGroupBuilding.dao.IEbuyFightMerchantHasGroupBuildingBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyFightMerchantHasGroupBuilding.entity.EbuyFightMerchantHasGroupBuilding;

/**
 * 描述:(自提点服务小区关系表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuyFightMerchantHasGroupBuildingBaseService implements IEbuyFightMerchantHasGroupBuildingBaseService{
	
	private IEbuyFightMerchantHasGroupBuildingBaseDao ebuyFightMerchantHasGroupBuildingBaseDao;
	public void setEbuyFightMerchantHasGroupBuildingBaseDao(IEbuyFightMerchantHasGroupBuildingBaseDao ebuyFightMerchantHasGroupBuildingBaseDao) {
		this.ebuyFightMerchantHasGroupBuildingBaseDao = ebuyFightMerchantHasGroupBuildingBaseDao;
	}
	/**
	 * 根据条件查询(自提点服务小区关系表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyFightMerchantHasGroupBuilding> getEbuyFightMerchantHasGroupBuildingByCondition(Map<String,Object> paramMap){
		return ebuyFightMerchantHasGroupBuildingBaseDao.selectEbuyFightMerchantHasGroupBuildingByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(自提点服务小区关系表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyFightMerchantHasGroupBuilding> getEbuyFightMerchantHasGroupBuildingByConditionDim(Map<String,Object> paramMap){
		return ebuyFightMerchantHasGroupBuildingBaseDao.selectEbuyFightMerchantHasGroupBuildingByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(自提点服务小区关系表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyFightMerchantHasGroupBuilding> getEbuyFightMerchantHasGroupBuildingByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyFightMerchantHasGroupBuildingBaseDao.selectEbuyFightMerchantHasGroupBuildingByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(自提点服务小区关系表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyFightMerchantHasGroupBuilding> getEbuyFightMerchantHasGroupBuildingByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyFightMerchantHasGroupBuildingBaseDao.selectEbuyFightMerchantHasGroupBuildingByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(自提点服务小区关系表)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyFightMerchantHasGroupBuilding getEbuyFightMerchantHasGroupBuildingBySeqId(java.math.BigInteger id){
		return ebuyFightMerchantHasGroupBuildingBaseDao.selectEbuyFightMerchantHasGroupBuildingBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(自提点服务小区关系表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyFightMerchantHasGroupBuildingCount(Map<String,Object> paramMap){
		return ebuyFightMerchantHasGroupBuildingBaseDao.selectEbuyFightMerchantHasGroupBuildingCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(自提点服务小区关系表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyFightMerchantHasGroupBuildingCountDim(Map<String,Object> paramMap){
		return ebuyFightMerchantHasGroupBuildingBaseDao.selectEbuyFightMerchantHasGroupBuildingCount(paramMap,true);
	}
	/**
	 * 往(自提点服务小区关系表)新增一条记录
	 * @param ebuyFightMerchantHasGroupBuilding
	 * @return
	 */
	@Override
	public int insertEbuyFightMerchantHasGroupBuilding(EbuyFightMerchantHasGroupBuilding ebuyFightMerchantHasGroupBuilding){
		return ebuyFightMerchantHasGroupBuildingBaseDao.insertEbuyFightMerchantHasGroupBuilding(ebuyFightMerchantHasGroupBuilding);
	}
	/**
	 * 批量新增(自提点服务小区关系表)
	 * @param ebuyFightMerchantHasGroupBuildingList
	 * @return
	 */
	@Override
	public int insertEbuyFightMerchantHasGroupBuildingBatch(List<EbuyFightMerchantHasGroupBuilding> ebuyFightMerchantHasGroupBuildingList){
		return ebuyFightMerchantHasGroupBuildingBaseDao.insertEbuyFightMerchantHasGroupBuildingBatch(ebuyFightMerchantHasGroupBuildingList);
	}
	/**
	 * 更新(自提点服务小区关系表)信息
	 * @param ebuyFightMerchantHasGroupBuilding
	 * @return
	 */
	@Override
	public int updateEbuyFightMerchantHasGroupBuilding(EbuyFightMerchantHasGroupBuilding ebuyFightMerchantHasGroupBuilding){
		return ebuyFightMerchantHasGroupBuildingBaseDao.updateEbuyFightMerchantHasGroupBuilding(ebuyFightMerchantHasGroupBuilding);
	}
	/**
	 * 批量更新(自提点服务小区关系表)信息
	 * @param ebuyFightMerchantHasGroupBuildingList
	 * @return
	 */
	@Override
	public int updateEbuyFightMerchantHasGroupBuildingBatch(List<EbuyFightMerchantHasGroupBuilding> ebuyFightMerchantHasGroupBuildingList){
		return ebuyFightMerchantHasGroupBuildingBaseDao.updateEbuyFightMerchantHasGroupBuildingBatch(ebuyFightMerchantHasGroupBuildingList);
	}
	/**
	 * 根据序列号删除(自提点服务小区关系表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyFightMerchantHasGroupBuildingLogic(java.math.BigInteger id){
		return ebuyFightMerchantHasGroupBuildingBaseDao.deleteEbuyFightMerchantHasGroupBuildingLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(自提点服务小区关系表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyFightMerchantHasGroupBuildingLogicBatch(List<java.math.BigInteger> idList){
		return ebuyFightMerchantHasGroupBuildingBaseDao.deleteEbuyFightMerchantHasGroupBuildingLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(自提点服务小区关系表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyFightMerchantHasGroupBuilding(java.math.BigInteger id){
//		return ebuyFightMerchantHasGroupBuildingBaseDao.deleteEbuyFightMerchantHasGroupBuilding(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(自提点服务小区关系表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyFightMerchantHasGroupBuildingBatch(List<java.math.BigInteger> idList){
//		return ebuyFightMerchantHasGroupBuildingBaseDao.deleteEbuyFightMerchantHasGroupBuildingBatch(idList);
//	}
	
}
