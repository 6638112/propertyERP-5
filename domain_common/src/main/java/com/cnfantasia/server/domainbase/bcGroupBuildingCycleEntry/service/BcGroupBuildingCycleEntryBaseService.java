package com.cnfantasia.server.domainbase.bcGroupBuildingCycleEntry.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.bcGroupBuildingCycleEntry.dao.IBcGroupBuildingCycleEntryBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.bcGroupBuildingCycleEntry.entity.BcGroupBuildingCycleEntry;

/**
 * 描述:(银行托收账期明细) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class BcGroupBuildingCycleEntryBaseService implements IBcGroupBuildingCycleEntryBaseService{
	
	private IBcGroupBuildingCycleEntryBaseDao bcGroupBuildingCycleEntryBaseDao;
	public void setBcGroupBuildingCycleEntryBaseDao(IBcGroupBuildingCycleEntryBaseDao bcGroupBuildingCycleEntryBaseDao) {
		this.bcGroupBuildingCycleEntryBaseDao = bcGroupBuildingCycleEntryBaseDao;
	}
	/**
	 * 根据条件查询(银行托收账期明细)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<BcGroupBuildingCycleEntry> getBcGroupBuildingCycleEntryByCondition(Map<String,Object> paramMap){
		return bcGroupBuildingCycleEntryBaseDao.selectBcGroupBuildingCycleEntryByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(银行托收账期明细)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<BcGroupBuildingCycleEntry> getBcGroupBuildingCycleEntryByConditionDim(Map<String,Object> paramMap){
		return bcGroupBuildingCycleEntryBaseDao.selectBcGroupBuildingCycleEntryByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(银行托收账期明细)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<BcGroupBuildingCycleEntry> getBcGroupBuildingCycleEntryByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return bcGroupBuildingCycleEntryBaseDao.selectBcGroupBuildingCycleEntryByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(银行托收账期明细)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<BcGroupBuildingCycleEntry> getBcGroupBuildingCycleEntryByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return bcGroupBuildingCycleEntryBaseDao.selectBcGroupBuildingCycleEntryByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(银行托收账期明细)信息
	 * @param id
	 * @return
	 */
	@Override
	public BcGroupBuildingCycleEntry getBcGroupBuildingCycleEntryBySeqId(java.math.BigInteger id){
		return bcGroupBuildingCycleEntryBaseDao.selectBcGroupBuildingCycleEntryBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(银行托收账期明细)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getBcGroupBuildingCycleEntryCount(Map<String,Object> paramMap){
		return bcGroupBuildingCycleEntryBaseDao.selectBcGroupBuildingCycleEntryCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(银行托收账期明细)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getBcGroupBuildingCycleEntryCountDim(Map<String,Object> paramMap){
		return bcGroupBuildingCycleEntryBaseDao.selectBcGroupBuildingCycleEntryCount(paramMap,true);
	}
	/**
	 * 往(银行托收账期明细)新增一条记录
	 * @param bcGroupBuildingCycleEntry
	 * @return
	 */
	@Override
	public int insertBcGroupBuildingCycleEntry(BcGroupBuildingCycleEntry bcGroupBuildingCycleEntry){
		return bcGroupBuildingCycleEntryBaseDao.insertBcGroupBuildingCycleEntry(bcGroupBuildingCycleEntry);
	}
	/**
	 * 批量新增(银行托收账期明细)
	 * @param bcGroupBuildingCycleEntryList
	 * @return
	 */
	@Override
	public int insertBcGroupBuildingCycleEntryBatch(List<BcGroupBuildingCycleEntry> bcGroupBuildingCycleEntryList){
		return bcGroupBuildingCycleEntryBaseDao.insertBcGroupBuildingCycleEntryBatch(bcGroupBuildingCycleEntryList);
	}
	/**
	 * 更新(银行托收账期明细)信息
	 * @param bcGroupBuildingCycleEntry
	 * @return
	 */
	@Override
	public int updateBcGroupBuildingCycleEntry(BcGroupBuildingCycleEntry bcGroupBuildingCycleEntry){
		return bcGroupBuildingCycleEntryBaseDao.updateBcGroupBuildingCycleEntry(bcGroupBuildingCycleEntry);
	}
	/**
	 * 批量更新(银行托收账期明细)信息
	 * @param bcGroupBuildingCycleEntryList
	 * @return
	 */
	@Override
	public int updateBcGroupBuildingCycleEntryBatch(List<BcGroupBuildingCycleEntry> bcGroupBuildingCycleEntryList){
		return bcGroupBuildingCycleEntryBaseDao.updateBcGroupBuildingCycleEntryBatch(bcGroupBuildingCycleEntryList);
	}
	/**
	 * 根据序列号删除(银行托收账期明细)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteBcGroupBuildingCycleEntryLogic(java.math.BigInteger id){
		return bcGroupBuildingCycleEntryBaseDao.deleteBcGroupBuildingCycleEntryLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(银行托收账期明细)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteBcGroupBuildingCycleEntryLogicBatch(List<java.math.BigInteger> idList){
		return bcGroupBuildingCycleEntryBaseDao.deleteBcGroupBuildingCycleEntryLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(银行托收账期明细)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteBcGroupBuildingCycleEntry(java.math.BigInteger id){
//		return bcGroupBuildingCycleEntryBaseDao.deleteBcGroupBuildingCycleEntry(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(银行托收账期明细)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteBcGroupBuildingCycleEntryBatch(List<java.math.BigInteger> idList){
//		return bcGroupBuildingCycleEntryBaseDao.deleteBcGroupBuildingCycleEntryBatch(idList);
//	}
	
}
