package com.cnfantasia.server.domainbase.buildingKeyList.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.buildingKeyList.dao.IBuildingKeyListBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.buildingKeyList.entity.BuildingKeyList;

/**
 * 描述:(开通门禁的小区楼栋列表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class BuildingKeyListBaseService implements IBuildingKeyListBaseService{
	
	private IBuildingKeyListBaseDao buildingKeyListBaseDao;
	public void setBuildingKeyListBaseDao(IBuildingKeyListBaseDao buildingKeyListBaseDao) {
		this.buildingKeyListBaseDao = buildingKeyListBaseDao;
	}
	/**
	 * 根据条件查询(开通门禁的小区楼栋列表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<BuildingKeyList> getBuildingKeyListByCondition(Map<String,Object> paramMap){
		return buildingKeyListBaseDao.selectBuildingKeyListByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(开通门禁的小区楼栋列表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<BuildingKeyList> getBuildingKeyListByConditionDim(Map<String,Object> paramMap){
		return buildingKeyListBaseDao.selectBuildingKeyListByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(开通门禁的小区楼栋列表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<BuildingKeyList> getBuildingKeyListByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return buildingKeyListBaseDao.selectBuildingKeyListByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(开通门禁的小区楼栋列表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<BuildingKeyList> getBuildingKeyListByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return buildingKeyListBaseDao.selectBuildingKeyListByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(开通门禁的小区楼栋列表)信息
	 * @param id
	 * @return
	 */
	@Override
	public BuildingKeyList getBuildingKeyListBySeqId(java.math.BigInteger id){
		return buildingKeyListBaseDao.selectBuildingKeyListBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(开通门禁的小区楼栋列表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getBuildingKeyListCount(Map<String,Object> paramMap){
		return buildingKeyListBaseDao.selectBuildingKeyListCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(开通门禁的小区楼栋列表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getBuildingKeyListCountDim(Map<String,Object> paramMap){
		return buildingKeyListBaseDao.selectBuildingKeyListCount(paramMap,true);
	}
	/**
	 * 往(开通门禁的小区楼栋列表)新增一条记录
	 * @param buildingKeyList
	 * @return
	 */
	@Override
	public int insertBuildingKeyList(BuildingKeyList buildingKeyList){
		return buildingKeyListBaseDao.insertBuildingKeyList(buildingKeyList);
	}
	/**
	 * 批量新增(开通门禁的小区楼栋列表)
	 * @param buildingKeyListList
	 * @return
	 */
	@Override
	public int insertBuildingKeyListBatch(List<BuildingKeyList> buildingKeyListList){
		return buildingKeyListBaseDao.insertBuildingKeyListBatch(buildingKeyListList);
	}
	/**
	 * 更新(开通门禁的小区楼栋列表)信息
	 * @param buildingKeyList
	 * @return
	 */
	@Override
	public int updateBuildingKeyList(BuildingKeyList buildingKeyList){
		return buildingKeyListBaseDao.updateBuildingKeyList(buildingKeyList);
	}
	/**
	 * 批量更新(开通门禁的小区楼栋列表)信息
	 * @param buildingKeyListList
	 * @return
	 */
	@Override
	public int updateBuildingKeyListBatch(List<BuildingKeyList> buildingKeyListList){
		return buildingKeyListBaseDao.updateBuildingKeyListBatch(buildingKeyListList);
	}
	/**
	 * 根据序列号删除(开通门禁的小区楼栋列表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteBuildingKeyListLogic(java.math.BigInteger id){
		return buildingKeyListBaseDao.deleteBuildingKeyListLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(开通门禁的小区楼栋列表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteBuildingKeyListLogicBatch(List<java.math.BigInteger> idList){
		return buildingKeyListBaseDao.deleteBuildingKeyListLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(开通门禁的小区楼栋列表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteBuildingKeyList(java.math.BigInteger id){
//		return buildingKeyListBaseDao.deleteBuildingKeyList(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(开通门禁的小区楼栋列表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteBuildingKeyListBatch(List<java.math.BigInteger> idList){
//		return buildingKeyListBaseDao.deleteBuildingKeyListBatch(idList);
//	}
	
}
