package com.cnfantasia.server.domainbase.building.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.building.entity.Building;

/**
 * 描述:(建筑单元) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class BuildingBaseDao extends AbstractBaseDao implements IBuildingBaseDao{
	/**
	 * 根据条件查询(建筑单元)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<Building> selectBuildingByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("buildingBase.select_building",paramMap);
	}
	/**
	 * 按条件分页查询(建筑单元)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<Building> selectBuildingByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectBuildingCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<Building> resMap= sqlSession.selectList("buildingBase.select_building_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(建筑单元)信息
	 * @param id
	 * @return
	 */
	@Override
	public Building selectBuildingBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("buildingBase.select_building_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(建筑单元)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectBuildingCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("buildingBase.select_building_count", paramMap);
	}
	/**
	 * 往(建筑单元)新增一条记录
	 * @param building
	 * @return
	 */
	@Override
	public int insertBuilding(Building building){
		return sqlSession.insert("buildingBase.insert_building",building);
	}
	/**
	 * 批量新增(建筑单元)信息
	 * @param buildingList
	 * @return
	 */
	@Override
	public int insertBuildingBatch(List<Building> buildingList) {
		return sqlSession.insert("buildingBase.insert_building_Batch",buildingList);
	}
	
	/**
	 * 更新(建筑单元)信息
	 * @param building
	 * @return
	 */
	@Override
	public int updateBuilding(Building building){
		return sqlSession.update("buildingBase.update_building", building);
	}
	/**
	 * 批量更新(建筑单元)信息
	 * @param buildingList
	 * @return
	 */
	@Override
	public int updateBuildingBatch(List<Building> buildingList) {
		return sqlSession.update("buildingBase.update_building_Batch", buildingList);
	}
	
	/**
	 * 根据序列号删除(建筑单元)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteBuildingLogic(java.math.BigInteger id){
		Building building = new Building();
		building.setId(id);
		building.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("buildingBase.delete_building_Logic",building);
	}
	
	/**
	 * 根据Id 批量删除(建筑单元)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteBuildingLogicBatch(List<java.math.BigInteger> idList) {
		List<Building> delList = new java.util.ArrayList<Building>();
		for(java.math.BigInteger id:idList){
			Building building = new Building();
			building.setId(id);
			building.setSys0DelState(RecordState_DELETED);
			delList.add(building);
		}
		return sqlSession.update("buildingBase.delete_building_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(建筑单元)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteBuilding(java.math.BigInteger id){
//		return sqlSession.delete("buildingBase.delete_building", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(建筑单元)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteBuildingBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("buildingBase.delete_building_Batch", idList);
//	}
	
	
}
