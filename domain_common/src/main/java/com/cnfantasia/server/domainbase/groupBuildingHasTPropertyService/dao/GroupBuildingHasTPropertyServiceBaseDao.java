package com.cnfantasia.server.domainbase.groupBuildingHasTPropertyService.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.groupBuildingHasTPropertyService.entity.GroupBuildingHasTPropertyService;

/**
 * 描述:(小区服务关系表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class GroupBuildingHasTPropertyServiceBaseDao extends AbstractBaseDao implements IGroupBuildingHasTPropertyServiceBaseDao{
	/**
	 * 根据条件查询(小区服务关系表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<GroupBuildingHasTPropertyService> selectGroupBuildingHasTPropertyServiceByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("groupBuildingHasTPropertyServiceBase.select_groupBuildingHasTPropertyService",paramMap);
	}
	/**
	 * 按条件分页查询(小区服务关系表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<GroupBuildingHasTPropertyService> selectGroupBuildingHasTPropertyServiceByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectGroupBuildingHasTPropertyServiceCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<GroupBuildingHasTPropertyService> resMap= sqlSession.selectList("groupBuildingHasTPropertyServiceBase.select_groupBuildingHasTPropertyService_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(小区服务关系表)信息
	 * @param id
	 * @return
	 */
	@Override
	public GroupBuildingHasTPropertyService selectGroupBuildingHasTPropertyServiceBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("groupBuildingHasTPropertyServiceBase.select_groupBuildingHasTPropertyService_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(小区服务关系表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectGroupBuildingHasTPropertyServiceCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("groupBuildingHasTPropertyServiceBase.select_groupBuildingHasTPropertyService_count", paramMap);
	}
	/**
	 * 往(小区服务关系表)新增一条记录
	 * @param groupBuildingHasTPropertyService
	 * @return
	 */
	@Override
	public int insertGroupBuildingHasTPropertyService(GroupBuildingHasTPropertyService groupBuildingHasTPropertyService){
		return sqlSession.insert("groupBuildingHasTPropertyServiceBase.insert_groupBuildingHasTPropertyService",groupBuildingHasTPropertyService);
	}
	/**
	 * 批量新增(小区服务关系表)信息
	 * @param groupBuildingHasTPropertyServiceList
	 * @return
	 */
	@Override
	public int insertGroupBuildingHasTPropertyServiceBatch(List<GroupBuildingHasTPropertyService> groupBuildingHasTPropertyServiceList) {
		return sqlSession.insert("groupBuildingHasTPropertyServiceBase.insert_groupBuildingHasTPropertyService_Batch",groupBuildingHasTPropertyServiceList);
	}
	
	/**
	 * 更新(小区服务关系表)信息
	 * @param groupBuildingHasTPropertyService
	 * @return
	 */
	@Override
	public int updateGroupBuildingHasTPropertyService(GroupBuildingHasTPropertyService groupBuildingHasTPropertyService){
		return sqlSession.update("groupBuildingHasTPropertyServiceBase.update_groupBuildingHasTPropertyService", groupBuildingHasTPropertyService);
	}
	/**
	 * 批量更新(小区服务关系表)信息
	 * @param groupBuildingHasTPropertyServiceList
	 * @return
	 */
	@Override
	public int updateGroupBuildingHasTPropertyServiceBatch(List<GroupBuildingHasTPropertyService> groupBuildingHasTPropertyServiceList) {
		return sqlSession.update("groupBuildingHasTPropertyServiceBase.update_groupBuildingHasTPropertyService_Batch", groupBuildingHasTPropertyServiceList);
	}
	
	/**
	 * 根据序列号删除(小区服务关系表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteGroupBuildingHasTPropertyServiceLogic(java.math.BigInteger id){
		GroupBuildingHasTPropertyService groupBuildingHasTPropertyService = new GroupBuildingHasTPropertyService();
		groupBuildingHasTPropertyService.setId(id);
		groupBuildingHasTPropertyService.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("groupBuildingHasTPropertyServiceBase.delete_groupBuildingHasTPropertyService_Logic",groupBuildingHasTPropertyService);
	}
	
	/**
	 * 根据Id 批量删除(小区服务关系表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteGroupBuildingHasTPropertyServiceLogicBatch(List<java.math.BigInteger> idList) {
		List<GroupBuildingHasTPropertyService> delList = new java.util.ArrayList<GroupBuildingHasTPropertyService>();
		for(java.math.BigInteger id:idList){
			GroupBuildingHasTPropertyService groupBuildingHasTPropertyService = new GroupBuildingHasTPropertyService();
			groupBuildingHasTPropertyService.setId(id);
			groupBuildingHasTPropertyService.setSys0DelState(RecordState_DELETED);
			delList.add(groupBuildingHasTPropertyService);
		}
		return sqlSession.update("groupBuildingHasTPropertyServiceBase.delete_groupBuildingHasTPropertyService_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(小区服务关系表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteGroupBuildingHasTPropertyService(java.math.BigInteger id){
//		return sqlSession.delete("groupBuildingHasTPropertyServiceBase.delete_groupBuildingHasTPropertyService", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(小区服务关系表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteGroupBuildingHasTPropertyServiceBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("groupBuildingHasTPropertyServiceBase.delete_groupBuildingHasTPropertyService_Batch", idList);
//	}
	
	
}
