package com.cnfantasia.server.ms.buildingKeyConfig.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.cnfantasia.server.common.json.JsonResponse;
import com.cnfantasia.server.domainbase.buildingKeyConfig.entity.BuildingKeyConfig;
import com.cnfantasia.server.ms.buildingKeyConfig.dao.IBuildingKeyConfigDao;

/**
 * 门禁认证选项配置管理Service
 *
 * @author Liyl
 * @version 1.0 2016年3月22日 下午5:57:52
 */
public class BuildingKeyConfigService implements IBuildingKeyConfigService {
	
	private IBuildingKeyConfigDao buildingKeyConfigDao;
	
	public IBuildingKeyConfigDao getBuildingKeyConfigDao() {
		return buildingKeyConfigDao;
	}

	public void setBuildingKeyConfigDao(IBuildingKeyConfigDao buildingKeyConfigDao) {
		this.buildingKeyConfigDao = buildingKeyConfigDao;
	}
	
	/**
	 * 新增(修改)配置信息
	 * @param groupBuildingId
	 * @param buildingKeyConfigList
	 * @return
	 */
	@Override
	@Transactional
	public int insertBuildingKeyConfigBatch(String groupBuildingId, List<BuildingKeyConfig> buildingKeyConfigList){
		if(buildingKeyConfigDao.selectBuildingKeyConfigCount(groupBuildingId)>0){
			// 不是第一次新增，则删除旧数据
			List<BuildingKeyConfig> buildingKeyConfigs = buildingKeyConfigDao.selectBuildingKeyConfigByCondition(groupBuildingId);
			List<BigInteger> idList = new ArrayList<BigInteger>();
			for(BuildingKeyConfig buildingKeyConfig:buildingKeyConfigs){
				idList.add(buildingKeyConfig.getId());
			}
			if(buildingKeyConfigDao.deleteBuildingKeyConfigBatch(idList)<=0){
				return 0;
			}
		}
		
		// 插入新数据
		return buildingKeyConfigDao.insertBuildingKeyConfigBatch(buildingKeyConfigList);
	}
	
	/**
	 * 根据小区id查询配置
	 * @param groupBuildingId
	 * @return
	 */
	@Override
	public List<BuildingKeyConfig> selectBuildingKeyConfigByCondition(String groupBuildingId){
		return buildingKeyConfigDao.selectBuildingKeyConfigByCondition(groupBuildingId);
	}
	
	/**
	 * 根据id删除配置信息
	 * @param id
	 * @return
	 */
	@Override
	@Transactional
	public int deleteBuildingKeyConfig(java.math.BigInteger id){
		return buildingKeyConfigDao.deleteBuildingKeyConfig(id);
	}
	
	/**
	 * 配置项上移
	 * @param id
	 * @param order
	 * @return
	 */
	@Override
	@Transactional
	public JsonResponse upOrder(BigInteger id, BigInteger order){
		return buildingKeyConfigDao.upOrder(id, order);
	}

	/**
	 * 配置项下移
	 * @param id
	 * @param order
	 * @return
	 */
	@Override
	@Transactional
	public JsonResponse downOrder(BigInteger id, BigInteger order) {
		return buildingKeyConfigDao.downOrder(id, order);
	}

}
