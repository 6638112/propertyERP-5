package com.cnfantasia.server.domainbase.bcGroupBuildingCycle.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.bcGroupBuildingCycle.dao.IBcGroupBuildingCycleBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.bcGroupBuildingCycle.entity.BcGroupBuildingCycle;

/**
 * 描述:(托收账期记录) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class BcGroupBuildingCycleBaseService implements IBcGroupBuildingCycleBaseService{
	
	private IBcGroupBuildingCycleBaseDao bcGroupBuildingCycleBaseDao;
	public void setBcGroupBuildingCycleBaseDao(IBcGroupBuildingCycleBaseDao bcGroupBuildingCycleBaseDao) {
		this.bcGroupBuildingCycleBaseDao = bcGroupBuildingCycleBaseDao;
	}
	/**
	 * 根据条件查询(托收账期记录)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<BcGroupBuildingCycle> getBcGroupBuildingCycleByCondition(Map<String,Object> paramMap){
		return bcGroupBuildingCycleBaseDao.selectBcGroupBuildingCycleByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(托收账期记录)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<BcGroupBuildingCycle> getBcGroupBuildingCycleByConditionDim(Map<String,Object> paramMap){
		return bcGroupBuildingCycleBaseDao.selectBcGroupBuildingCycleByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(托收账期记录)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<BcGroupBuildingCycle> getBcGroupBuildingCycleByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return bcGroupBuildingCycleBaseDao.selectBcGroupBuildingCycleByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(托收账期记录)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<BcGroupBuildingCycle> getBcGroupBuildingCycleByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return bcGroupBuildingCycleBaseDao.selectBcGroupBuildingCycleByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(托收账期记录)信息
	 * @param id
	 * @return
	 */
	@Override
	public BcGroupBuildingCycle getBcGroupBuildingCycleBySeqId(java.math.BigInteger id){
		return bcGroupBuildingCycleBaseDao.selectBcGroupBuildingCycleBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(托收账期记录)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getBcGroupBuildingCycleCount(Map<String,Object> paramMap){
		return bcGroupBuildingCycleBaseDao.selectBcGroupBuildingCycleCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(托收账期记录)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getBcGroupBuildingCycleCountDim(Map<String,Object> paramMap){
		return bcGroupBuildingCycleBaseDao.selectBcGroupBuildingCycleCount(paramMap,true);
	}
	/**
	 * 往(托收账期记录)新增一条记录
	 * @param bcGroupBuildingCycle
	 * @return
	 */
	@Override
	public int insertBcGroupBuildingCycle(BcGroupBuildingCycle bcGroupBuildingCycle){
		return bcGroupBuildingCycleBaseDao.insertBcGroupBuildingCycle(bcGroupBuildingCycle);
	}
	/**
	 * 批量新增(托收账期记录)
	 * @param bcGroupBuildingCycleList
	 * @return
	 */
	@Override
	public int insertBcGroupBuildingCycleBatch(List<BcGroupBuildingCycle> bcGroupBuildingCycleList){
		return bcGroupBuildingCycleBaseDao.insertBcGroupBuildingCycleBatch(bcGroupBuildingCycleList);
	}
	/**
	 * 更新(托收账期记录)信息
	 * @param bcGroupBuildingCycle
	 * @return
	 */
	@Override
	public int updateBcGroupBuildingCycle(BcGroupBuildingCycle bcGroupBuildingCycle){
		return bcGroupBuildingCycleBaseDao.updateBcGroupBuildingCycle(bcGroupBuildingCycle);
	}
	/**
	 * 批量更新(托收账期记录)信息
	 * @param bcGroupBuildingCycleList
	 * @return
	 */
	@Override
	public int updateBcGroupBuildingCycleBatch(List<BcGroupBuildingCycle> bcGroupBuildingCycleList){
		return bcGroupBuildingCycleBaseDao.updateBcGroupBuildingCycleBatch(bcGroupBuildingCycleList);
	}
	/**
	 * 根据序列号删除(托收账期记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteBcGroupBuildingCycleLogic(java.math.BigInteger id){
		return bcGroupBuildingCycleBaseDao.deleteBcGroupBuildingCycleLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(托收账期记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteBcGroupBuildingCycleLogicBatch(List<java.math.BigInteger> idList){
		return bcGroupBuildingCycleBaseDao.deleteBcGroupBuildingCycleLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(托收账期记录)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteBcGroupBuildingCycle(java.math.BigInteger id){
//		return bcGroupBuildingCycleBaseDao.deleteBcGroupBuildingCycle(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(托收账期记录)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteBcGroupBuildingCycleBatch(List<java.math.BigInteger> idList){
//		return bcGroupBuildingCycleBaseDao.deleteBcGroupBuildingCycleBatch(idList);
//	}
	
}
