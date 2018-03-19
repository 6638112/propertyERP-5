package com.cnfantasia.server.domainbase.groupBuildingBillCycle.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.groupBuildingBillCycle.entity.GroupBuildingBillCycle;

/**
 * 描述:(账期管理) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class GroupBuildingBillCycleBaseDao extends AbstractBaseDao implements IGroupBuildingBillCycleBaseDao{
	/**
	 * 根据条件查询(账期管理)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<GroupBuildingBillCycle> selectGroupBuildingBillCycleByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("groupBuildingBillCycleBase.select_groupBuildingBillCycle",paramMap);
	}
	/**
	 * 按条件分页查询(账期管理)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<GroupBuildingBillCycle> selectGroupBuildingBillCycleByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectGroupBuildingBillCycleCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<GroupBuildingBillCycle> resMap= sqlSession.selectList("groupBuildingBillCycleBase.select_groupBuildingBillCycle_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(账期管理)信息
	 * @param id
	 * @return
	 */
	@Override
	public GroupBuildingBillCycle selectGroupBuildingBillCycleBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("groupBuildingBillCycleBase.select_groupBuildingBillCycle_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(账期管理)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectGroupBuildingBillCycleCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("groupBuildingBillCycleBase.select_groupBuildingBillCycle_count", paramMap);
	}
	/**
	 * 往(账期管理)新增一条记录
	 * @param groupBuildingBillCycle
	 * @return
	 */
	@Override
	public int insertGroupBuildingBillCycle(GroupBuildingBillCycle groupBuildingBillCycle){
		return sqlSession.insert("groupBuildingBillCycleBase.insert_groupBuildingBillCycle",groupBuildingBillCycle);
	}
	/**
	 * 批量新增(账期管理)信息
	 * @param groupBuildingBillCycleList
	 * @return
	 */
	@Override
	public int insertGroupBuildingBillCycleBatch(List<GroupBuildingBillCycle> groupBuildingBillCycleList) {
		if (groupBuildingBillCycleList == null || groupBuildingBillCycleList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = groupBuildingBillCycleList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<GroupBuildingBillCycle> batchList = groupBuildingBillCycleList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("groupBuildingBillCycleBase.insert_groupBuildingBillCycle_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(账期管理)信息
	 * @param groupBuildingBillCycle
	 * @return
	 */
	@Override
	public int updateGroupBuildingBillCycle(GroupBuildingBillCycle groupBuildingBillCycle){
		return sqlSession.update("groupBuildingBillCycleBase.update_groupBuildingBillCycle", groupBuildingBillCycle);
	}
	/**
	 * 批量更新(账期管理)信息
	 * @param groupBuildingBillCycleList
	 * @return
	 */
	@Override
	public int updateGroupBuildingBillCycleBatch(List<GroupBuildingBillCycle> groupBuildingBillCycleList) {
		if (groupBuildingBillCycleList == null || groupBuildingBillCycleList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("groupBuildingBillCycleBase.update_groupBuildingBillCycle_Batch", groupBuildingBillCycleList);
	}
	
	/**
	 * 根据序列号删除(账期管理)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteGroupBuildingBillCycleLogic(java.math.BigInteger id){
		GroupBuildingBillCycle groupBuildingBillCycle = new GroupBuildingBillCycle();
		groupBuildingBillCycle.setId(id);
		groupBuildingBillCycle.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("groupBuildingBillCycleBase.delete_groupBuildingBillCycle_Logic",groupBuildingBillCycle);
	}
	
	/**
	 * 根据Id 批量删除(账期管理)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteGroupBuildingBillCycleLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<GroupBuildingBillCycle> delList = new java.util.ArrayList<GroupBuildingBillCycle>();
		for(java.math.BigInteger id:idList){
			GroupBuildingBillCycle groupBuildingBillCycle = new GroupBuildingBillCycle();
			groupBuildingBillCycle.setId(id);
			groupBuildingBillCycle.setSys0DelState(RecordState_DELETED);
			delList.add(groupBuildingBillCycle);
		}
		return sqlSession.update("groupBuildingBillCycleBase.delete_groupBuildingBillCycle_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(账期管理)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteGroupBuildingBillCycle(java.math.BigInteger id){
//		return sqlSession.delete("groupBuildingBillCycleBase.delete_groupBuildingBillCycle", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(账期管理)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteGroupBuildingBillCycleBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("groupBuildingBillCycleBase.delete_groupBuildingBillCycle_Batch", idList);
//	}
	
	
}
