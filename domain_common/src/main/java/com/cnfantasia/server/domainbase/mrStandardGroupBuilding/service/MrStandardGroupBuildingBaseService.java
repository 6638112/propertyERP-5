package com.cnfantasia.server.domainbase.mrStandardGroupBuilding.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.mrStandardGroupBuilding.dao.IMrStandardGroupBuildingBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.mrStandardGroupBuilding.entity.MrStandardGroupBuilding;

/**
 * 描述:(抄表收费标准(小区)) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class MrStandardGroupBuildingBaseService implements IMrStandardGroupBuildingBaseService{
	
	private IMrStandardGroupBuildingBaseDao mrStandardGroupBuildingBaseDao;
	public void setMrStandardGroupBuildingBaseDao(IMrStandardGroupBuildingBaseDao mrStandardGroupBuildingBaseDao) {
		this.mrStandardGroupBuildingBaseDao = mrStandardGroupBuildingBaseDao;
	}
	/**
	 * 根据条件查询(抄表收费标准(小区))信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<MrStandardGroupBuilding> getMrStandardGroupBuildingByCondition(Map<String,Object> paramMap){
		return mrStandardGroupBuildingBaseDao.selectMrStandardGroupBuildingByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(抄表收费标准(小区))信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<MrStandardGroupBuilding> getMrStandardGroupBuildingByConditionDim(Map<String,Object> paramMap){
		return mrStandardGroupBuildingBaseDao.selectMrStandardGroupBuildingByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(抄表收费标准(小区))信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<MrStandardGroupBuilding> getMrStandardGroupBuildingByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return mrStandardGroupBuildingBaseDao.selectMrStandardGroupBuildingByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(抄表收费标准(小区))信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<MrStandardGroupBuilding> getMrStandardGroupBuildingByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return mrStandardGroupBuildingBaseDao.selectMrStandardGroupBuildingByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(抄表收费标准(小区))信息
	 * @param id
	 * @return
	 */
	@Override
	public MrStandardGroupBuilding getMrStandardGroupBuildingBySeqId(java.math.BigInteger id){
		return mrStandardGroupBuildingBaseDao.selectMrStandardGroupBuildingBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(抄表收费标准(小区))记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getMrStandardGroupBuildingCount(Map<String,Object> paramMap){
		return mrStandardGroupBuildingBaseDao.selectMrStandardGroupBuildingCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(抄表收费标准(小区))记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getMrStandardGroupBuildingCountDim(Map<String,Object> paramMap){
		return mrStandardGroupBuildingBaseDao.selectMrStandardGroupBuildingCount(paramMap,true);
	}
	/**
	 * 往(抄表收费标准(小区))新增一条记录
	 * @param mrStandardGroupBuilding
	 * @return
	 */
	@Override
	public int insertMrStandardGroupBuilding(MrStandardGroupBuilding mrStandardGroupBuilding){
		return mrStandardGroupBuildingBaseDao.insertMrStandardGroupBuilding(mrStandardGroupBuilding);
	}
	/**
	 * 批量新增(抄表收费标准(小区))
	 * @param mrStandardGroupBuildingList
	 * @return
	 */
	@Override
	public int insertMrStandardGroupBuildingBatch(List<MrStandardGroupBuilding> mrStandardGroupBuildingList){
		return mrStandardGroupBuildingBaseDao.insertMrStandardGroupBuildingBatch(mrStandardGroupBuildingList);
	}
	/**
	 * 更新(抄表收费标准(小区))信息
	 * @param mrStandardGroupBuilding
	 * @return
	 */
	@Override
	public int updateMrStandardGroupBuilding(MrStandardGroupBuilding mrStandardGroupBuilding){
		return mrStandardGroupBuildingBaseDao.updateMrStandardGroupBuilding(mrStandardGroupBuilding);
	}
	/**
	 * 批量更新(抄表收费标准(小区))信息
	 * @param mrStandardGroupBuildingList
	 * @return
	 */
	@Override
	public int updateMrStandardGroupBuildingBatch(List<MrStandardGroupBuilding> mrStandardGroupBuildingList){
		return mrStandardGroupBuildingBaseDao.updateMrStandardGroupBuildingBatch(mrStandardGroupBuildingList);
	}
	/**
	 * 根据序列号删除(抄表收费标准(小区))信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteMrStandardGroupBuildingLogic(java.math.BigInteger id){
		return mrStandardGroupBuildingBaseDao.deleteMrStandardGroupBuildingLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(抄表收费标准(小区))信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteMrStandardGroupBuildingLogicBatch(List<java.math.BigInteger> idList){
		return mrStandardGroupBuildingBaseDao.deleteMrStandardGroupBuildingLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(抄表收费标准(小区))信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteMrStandardGroupBuilding(java.math.BigInteger id){
//		return mrStandardGroupBuildingBaseDao.deleteMrStandardGroupBuilding(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(抄表收费标准(小区))信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteMrStandardGroupBuildingBatch(List<java.math.BigInteger> idList){
//		return mrStandardGroupBuildingBaseDao.deleteMrStandardGroupBuildingBatch(idList);
//	}
	
}
