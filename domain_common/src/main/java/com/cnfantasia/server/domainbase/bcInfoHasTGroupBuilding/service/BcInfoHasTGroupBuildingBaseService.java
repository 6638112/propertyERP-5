package com.cnfantasia.server.domainbase.bcInfoHasTGroupBuilding.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.bcInfoHasTGroupBuilding.dao.IBcInfoHasTGroupBuildingBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.bcInfoHasTGroupBuilding.entity.BcInfoHasTGroupBuilding;

/**
 * 描述:(物业公司托收银行信息包含的小区) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class BcInfoHasTGroupBuildingBaseService implements IBcInfoHasTGroupBuildingBaseService{
	
	private IBcInfoHasTGroupBuildingBaseDao bcInfoHasTGroupBuildingBaseDao;
	public void setBcInfoHasTGroupBuildingBaseDao(IBcInfoHasTGroupBuildingBaseDao bcInfoHasTGroupBuildingBaseDao) {
		this.bcInfoHasTGroupBuildingBaseDao = bcInfoHasTGroupBuildingBaseDao;
	}
	/**
	 * 根据条件查询(物业公司托收银行信息包含的小区)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<BcInfoHasTGroupBuilding> getBcInfoHasTGroupBuildingByCondition(Map<String,Object> paramMap){
		return bcInfoHasTGroupBuildingBaseDao.selectBcInfoHasTGroupBuildingByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(物业公司托收银行信息包含的小区)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<BcInfoHasTGroupBuilding> getBcInfoHasTGroupBuildingByConditionDim(Map<String,Object> paramMap){
		return bcInfoHasTGroupBuildingBaseDao.selectBcInfoHasTGroupBuildingByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(物业公司托收银行信息包含的小区)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<BcInfoHasTGroupBuilding> getBcInfoHasTGroupBuildingByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return bcInfoHasTGroupBuildingBaseDao.selectBcInfoHasTGroupBuildingByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(物业公司托收银行信息包含的小区)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<BcInfoHasTGroupBuilding> getBcInfoHasTGroupBuildingByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return bcInfoHasTGroupBuildingBaseDao.selectBcInfoHasTGroupBuildingByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(物业公司托收银行信息包含的小区)信息
	 * @param id
	 * @return
	 */
	@Override
	public BcInfoHasTGroupBuilding getBcInfoHasTGroupBuildingBySeqId(java.math.BigInteger id){
		return bcInfoHasTGroupBuildingBaseDao.selectBcInfoHasTGroupBuildingBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(物业公司托收银行信息包含的小区)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getBcInfoHasTGroupBuildingCount(Map<String,Object> paramMap){
		return bcInfoHasTGroupBuildingBaseDao.selectBcInfoHasTGroupBuildingCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(物业公司托收银行信息包含的小区)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getBcInfoHasTGroupBuildingCountDim(Map<String,Object> paramMap){
		return bcInfoHasTGroupBuildingBaseDao.selectBcInfoHasTGroupBuildingCount(paramMap,true);
	}
	/**
	 * 往(物业公司托收银行信息包含的小区)新增一条记录
	 * @param bcInfoHasTGroupBuilding
	 * @return
	 */
	@Override
	public int insertBcInfoHasTGroupBuilding(BcInfoHasTGroupBuilding bcInfoHasTGroupBuilding){
		return bcInfoHasTGroupBuildingBaseDao.insertBcInfoHasTGroupBuilding(bcInfoHasTGroupBuilding);
	}
	/**
	 * 批量新增(物业公司托收银行信息包含的小区)
	 * @param bcInfoHasTGroupBuildingList
	 * @return
	 */
	@Override
	public int insertBcInfoHasTGroupBuildingBatch(List<BcInfoHasTGroupBuilding> bcInfoHasTGroupBuildingList){
		return bcInfoHasTGroupBuildingBaseDao.insertBcInfoHasTGroupBuildingBatch(bcInfoHasTGroupBuildingList);
	}
	/**
	 * 更新(物业公司托收银行信息包含的小区)信息
	 * @param bcInfoHasTGroupBuilding
	 * @return
	 */
	@Override
	public int updateBcInfoHasTGroupBuilding(BcInfoHasTGroupBuilding bcInfoHasTGroupBuilding){
		return bcInfoHasTGroupBuildingBaseDao.updateBcInfoHasTGroupBuilding(bcInfoHasTGroupBuilding);
	}
	/**
	 * 批量更新(物业公司托收银行信息包含的小区)信息
	 * @param bcInfoHasTGroupBuildingList
	 * @return
	 */
	@Override
	public int updateBcInfoHasTGroupBuildingBatch(List<BcInfoHasTGroupBuilding> bcInfoHasTGroupBuildingList){
		return bcInfoHasTGroupBuildingBaseDao.updateBcInfoHasTGroupBuildingBatch(bcInfoHasTGroupBuildingList);
	}
	/**
	 * 根据序列号删除(物业公司托收银行信息包含的小区)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteBcInfoHasTGroupBuildingLogic(java.math.BigInteger id){
		return bcInfoHasTGroupBuildingBaseDao.deleteBcInfoHasTGroupBuildingLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(物业公司托收银行信息包含的小区)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteBcInfoHasTGroupBuildingLogicBatch(List<java.math.BigInteger> idList){
		return bcInfoHasTGroupBuildingBaseDao.deleteBcInfoHasTGroupBuildingLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(物业公司托收银行信息包含的小区)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteBcInfoHasTGroupBuilding(java.math.BigInteger id){
//		return bcInfoHasTGroupBuildingBaseDao.deleteBcInfoHasTGroupBuilding(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业公司托收银行信息包含的小区)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteBcInfoHasTGroupBuildingBatch(List<java.math.BigInteger> idList){
//		return bcInfoHasTGroupBuildingBaseDao.deleteBcInfoHasTGroupBuildingBatch(idList);
//	}
	
}
