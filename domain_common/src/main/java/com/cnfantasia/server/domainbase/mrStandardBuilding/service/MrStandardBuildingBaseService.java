package com.cnfantasia.server.domainbase.mrStandardBuilding.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.mrStandardBuilding.dao.IMrStandardBuildingBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.mrStandardBuilding.entity.MrStandardBuilding;

/**
 * 描述:(抄表收费标准(楼栋)) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class MrStandardBuildingBaseService implements IMrStandardBuildingBaseService{
	
	private IMrStandardBuildingBaseDao mrStandardBuildingBaseDao;
	public void setMrStandardBuildingBaseDao(IMrStandardBuildingBaseDao mrStandardBuildingBaseDao) {
		this.mrStandardBuildingBaseDao = mrStandardBuildingBaseDao;
	}
	/**
	 * 根据条件查询(抄表收费标准(楼栋))信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<MrStandardBuilding> getMrStandardBuildingByCondition(Map<String,Object> paramMap){
		return mrStandardBuildingBaseDao.selectMrStandardBuildingByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(抄表收费标准(楼栋))信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<MrStandardBuilding> getMrStandardBuildingByConditionDim(Map<String,Object> paramMap){
		return mrStandardBuildingBaseDao.selectMrStandardBuildingByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(抄表收费标准(楼栋))信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<MrStandardBuilding> getMrStandardBuildingByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return mrStandardBuildingBaseDao.selectMrStandardBuildingByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(抄表收费标准(楼栋))信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<MrStandardBuilding> getMrStandardBuildingByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return mrStandardBuildingBaseDao.selectMrStandardBuildingByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(抄表收费标准(楼栋))信息
	 * @param id
	 * @return
	 */
	@Override
	public MrStandardBuilding getMrStandardBuildingBySeqId(java.math.BigInteger id){
		return mrStandardBuildingBaseDao.selectMrStandardBuildingBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(抄表收费标准(楼栋))记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getMrStandardBuildingCount(Map<String,Object> paramMap){
		return mrStandardBuildingBaseDao.selectMrStandardBuildingCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(抄表收费标准(楼栋))记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getMrStandardBuildingCountDim(Map<String,Object> paramMap){
		return mrStandardBuildingBaseDao.selectMrStandardBuildingCount(paramMap,true);
	}
	/**
	 * 往(抄表收费标准(楼栋))新增一条记录
	 * @param mrStandardBuilding
	 * @return
	 */
	@Override
	public int insertMrStandardBuilding(MrStandardBuilding mrStandardBuilding){
		return mrStandardBuildingBaseDao.insertMrStandardBuilding(mrStandardBuilding);
	}
	/**
	 * 批量新增(抄表收费标准(楼栋))
	 * @param mrStandardBuildingList
	 * @return
	 */
	@Override
	public int insertMrStandardBuildingBatch(List<MrStandardBuilding> mrStandardBuildingList){
		return mrStandardBuildingBaseDao.insertMrStandardBuildingBatch(mrStandardBuildingList);
	}
	/**
	 * 更新(抄表收费标准(楼栋))信息
	 * @param mrStandardBuilding
	 * @return
	 */
	@Override
	public int updateMrStandardBuilding(MrStandardBuilding mrStandardBuilding){
		return mrStandardBuildingBaseDao.updateMrStandardBuilding(mrStandardBuilding);
	}
	/**
	 * 批量更新(抄表收费标准(楼栋))信息
	 * @param mrStandardBuildingList
	 * @return
	 */
	@Override
	public int updateMrStandardBuildingBatch(List<MrStandardBuilding> mrStandardBuildingList){
		return mrStandardBuildingBaseDao.updateMrStandardBuildingBatch(mrStandardBuildingList);
	}
	/**
	 * 根据序列号删除(抄表收费标准(楼栋))信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteMrStandardBuildingLogic(java.math.BigInteger id){
		return mrStandardBuildingBaseDao.deleteMrStandardBuildingLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(抄表收费标准(楼栋))信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteMrStandardBuildingLogicBatch(List<java.math.BigInteger> idList){
		return mrStandardBuildingBaseDao.deleteMrStandardBuildingLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(抄表收费标准(楼栋))信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteMrStandardBuilding(java.math.BigInteger id){
//		return mrStandardBuildingBaseDao.deleteMrStandardBuilding(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(抄表收费标准(楼栋))信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteMrStandardBuildingBatch(List<java.math.BigInteger> idList){
//		return mrStandardBuildingBaseDao.deleteMrStandardBuildingBatch(idList);
//	}
	
}
