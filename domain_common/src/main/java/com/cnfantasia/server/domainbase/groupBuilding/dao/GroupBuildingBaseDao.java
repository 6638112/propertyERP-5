package com.cnfantasia.server.domainbase.groupBuilding.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.groupBuilding.entity.GroupBuilding;

/**
 * 描述:(小区信息) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class GroupBuildingBaseDao extends AbstractBaseDao implements IGroupBuildingBaseDao{
	/**
	 * 根据条件查询(小区信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<GroupBuilding> selectGroupBuildingByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("groupBuildingBase.select_groupBuilding",paramMap);
	}
	/**
	 * 按条件分页查询(小区信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<GroupBuilding> selectGroupBuildingByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectGroupBuildingCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<GroupBuilding> resMap= sqlSession.selectList("groupBuildingBase.select_groupBuilding_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(小区信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public GroupBuilding selectGroupBuildingBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("groupBuildingBase.select_groupBuilding_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(小区信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectGroupBuildingCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("groupBuildingBase.select_groupBuilding_count", paramMap);
	}
	/**
	 * 往(小区信息)新增一条记录
	 * @param groupBuilding
	 * @return
	 */
	@Override
	public int insertGroupBuilding(GroupBuilding groupBuilding){
		return sqlSession.insert("groupBuildingBase.insert_groupBuilding",groupBuilding);
	}
	/**
	 * 批量新增(小区信息)信息
	 * @param groupBuildingList
	 * @return
	 */
	@Override
	public int insertGroupBuildingBatch(List<GroupBuilding> groupBuildingList) {
		if (groupBuildingList == null || groupBuildingList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = groupBuildingList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<GroupBuilding> batchList = groupBuildingList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("groupBuildingBase.insert_groupBuilding_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(小区信息)信息
	 * @param groupBuilding
	 * @return
	 */
	@Override
	public int updateGroupBuilding(GroupBuilding groupBuilding){
		return sqlSession.update("groupBuildingBase.update_groupBuilding", groupBuilding);
	}
	/**
	 * 批量更新(小区信息)信息
	 * @param groupBuildingList
	 * @return
	 */
	@Override
	public int updateGroupBuildingBatch(List<GroupBuilding> groupBuildingList) {
		if (groupBuildingList == null || groupBuildingList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("groupBuildingBase.update_groupBuilding_Batch", groupBuildingList);
	}
	
	/**
	 * 根据序列号删除(小区信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteGroupBuildingLogic(java.math.BigInteger id){
		GroupBuilding groupBuilding = new GroupBuilding();
		groupBuilding.setId(id);
		groupBuilding.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("groupBuildingBase.delete_groupBuilding_Logic",groupBuilding);
	}
	
	/**
	 * 根据Id 批量删除(小区信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteGroupBuildingLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<GroupBuilding> delList = new java.util.ArrayList<GroupBuilding>();
		for(java.math.BigInteger id:idList){
			GroupBuilding groupBuilding = new GroupBuilding();
			groupBuilding.setId(id);
			groupBuilding.setSys0DelState(RecordState_DELETED);
			delList.add(groupBuilding);
		}
		return sqlSession.update("groupBuildingBase.delete_groupBuilding_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(小区信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteGroupBuilding(java.math.BigInteger id){
//		return sqlSession.delete("groupBuildingBase.delete_groupBuilding", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(小区信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteGroupBuildingBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("groupBuildingBase.delete_groupBuilding_Batch", idList);
//	}
	
	
}
