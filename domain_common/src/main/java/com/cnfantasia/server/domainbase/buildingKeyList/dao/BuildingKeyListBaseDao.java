package com.cnfantasia.server.domainbase.buildingKeyList.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.buildingKeyList.entity.BuildingKeyList;

/**
 * 描述:(开通门禁的小区楼栋列表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class BuildingKeyListBaseDao extends AbstractBaseDao implements IBuildingKeyListBaseDao{
	/**
	 * 根据条件查询(开通门禁的小区楼栋列表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<BuildingKeyList> selectBuildingKeyListByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("buildingKeyListBase.select_buildingKeyList",paramMap);
	}
	/**
	 * 按条件分页查询(开通门禁的小区楼栋列表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<BuildingKeyList> selectBuildingKeyListByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectBuildingKeyListCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<BuildingKeyList> resMap= sqlSession.selectList("buildingKeyListBase.select_buildingKeyList_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(开通门禁的小区楼栋列表)信息
	 * @param id
	 * @return
	 */
	@Override
	public BuildingKeyList selectBuildingKeyListBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("buildingKeyListBase.select_buildingKeyList_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(开通门禁的小区楼栋列表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectBuildingKeyListCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("buildingKeyListBase.select_buildingKeyList_count", paramMap);
	}
	/**
	 * 往(开通门禁的小区楼栋列表)新增一条记录
	 * @param buildingKeyList
	 * @return
	 */
	@Override
	public int insertBuildingKeyList(BuildingKeyList buildingKeyList){
		return sqlSession.insert("buildingKeyListBase.insert_buildingKeyList",buildingKeyList);
	}
	/**
	 * 批量新增(开通门禁的小区楼栋列表)信息
	 * @param buildingKeyListList
	 * @return
	 */
	@Override
	public int insertBuildingKeyListBatch(List<BuildingKeyList> buildingKeyListList) {
		return sqlSession.insert("buildingKeyListBase.insert_buildingKeyList_Batch",buildingKeyListList);
	}
	
	/**
	 * 更新(开通门禁的小区楼栋列表)信息
	 * @param buildingKeyList
	 * @return
	 */
	@Override
	public int updateBuildingKeyList(BuildingKeyList buildingKeyList){
		return sqlSession.update("buildingKeyListBase.update_buildingKeyList", buildingKeyList);
	}
	/**
	 * 批量更新(开通门禁的小区楼栋列表)信息
	 * @param buildingKeyListList
	 * @return
	 */
	@Override
	public int updateBuildingKeyListBatch(List<BuildingKeyList> buildingKeyListList) {
		return sqlSession.update("buildingKeyListBase.update_buildingKeyList_Batch", buildingKeyListList);
	}
	
	/**
	 * 根据序列号删除(开通门禁的小区楼栋列表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteBuildingKeyListLogic(java.math.BigInteger id){
		BuildingKeyList buildingKeyList = new BuildingKeyList();
		buildingKeyList.setId(id);
		buildingKeyList.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("buildingKeyListBase.delete_buildingKeyList_Logic",buildingKeyList);
	}
	
	/**
	 * 根据Id 批量删除(开通门禁的小区楼栋列表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteBuildingKeyListLogicBatch(List<java.math.BigInteger> idList) {
		List<BuildingKeyList> delList = new java.util.ArrayList<BuildingKeyList>();
		for(java.math.BigInteger id:idList){
			BuildingKeyList buildingKeyList = new BuildingKeyList();
			buildingKeyList.setId(id);
			buildingKeyList.setSys0DelState(RecordState_DELETED);
			delList.add(buildingKeyList);
		}
		return sqlSession.update("buildingKeyListBase.delete_buildingKeyList_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(开通门禁的小区楼栋列表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteBuildingKeyList(java.math.BigInteger id){
//		return sqlSession.delete("buildingKeyListBase.delete_buildingKeyList", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(开通门禁的小区楼栋列表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteBuildingKeyListBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("buildingKeyListBase.delete_buildingKeyList_Batch", idList);
//	}
	
	
}
