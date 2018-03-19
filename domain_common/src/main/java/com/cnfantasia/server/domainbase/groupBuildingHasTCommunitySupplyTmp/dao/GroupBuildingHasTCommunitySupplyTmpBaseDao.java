package com.cnfantasia.server.domainbase.groupBuildingHasTCommunitySupplyTmp.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.groupBuildingHasTCommunitySupplyTmp.entity.GroupBuildingHasTCommunitySupplyTmp;

/**
 * 描述:(小区临时商家关系表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class GroupBuildingHasTCommunitySupplyTmpBaseDao extends AbstractBaseDao implements IGroupBuildingHasTCommunitySupplyTmpBaseDao{
	/**
	 * 根据条件查询(小区临时商家关系表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<GroupBuildingHasTCommunitySupplyTmp> selectGroupBuildingHasTCommunitySupplyTmpByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("groupBuildingHasTCommunitySupplyTmpBase.select_groupBuildingHasTCommunitySupplyTmp",paramMap);
	}
	/**
	 * 按条件分页查询(小区临时商家关系表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<GroupBuildingHasTCommunitySupplyTmp> selectGroupBuildingHasTCommunitySupplyTmpByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectGroupBuildingHasTCommunitySupplyTmpCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<GroupBuildingHasTCommunitySupplyTmp> resMap= sqlSession.selectList("groupBuildingHasTCommunitySupplyTmpBase.select_groupBuildingHasTCommunitySupplyTmp_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(小区临时商家关系表)信息
	 * @param id
	 * @return
	 */
	@Override
	public GroupBuildingHasTCommunitySupplyTmp selectGroupBuildingHasTCommunitySupplyTmpBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("groupBuildingHasTCommunitySupplyTmpBase.select_groupBuildingHasTCommunitySupplyTmp_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(小区临时商家关系表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectGroupBuildingHasTCommunitySupplyTmpCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("groupBuildingHasTCommunitySupplyTmpBase.select_groupBuildingHasTCommunitySupplyTmp_count", paramMap);
	}
	/**
	 * 往(小区临时商家关系表)新增一条记录
	 * @param groupBuildingHasTCommunitySupplyTmp
	 * @return
	 */
	@Override
	public int insertGroupBuildingHasTCommunitySupplyTmp(GroupBuildingHasTCommunitySupplyTmp groupBuildingHasTCommunitySupplyTmp){
		return sqlSession.insert("groupBuildingHasTCommunitySupplyTmpBase.insert_groupBuildingHasTCommunitySupplyTmp",groupBuildingHasTCommunitySupplyTmp);
	}
	/**
	 * 批量新增(小区临时商家关系表)信息
	 * @param groupBuildingHasTCommunitySupplyTmpList
	 * @return
	 */
	@Override
	public int insertGroupBuildingHasTCommunitySupplyTmpBatch(List<GroupBuildingHasTCommunitySupplyTmp> groupBuildingHasTCommunitySupplyTmpList) {
		return sqlSession.insert("groupBuildingHasTCommunitySupplyTmpBase.insert_groupBuildingHasTCommunitySupplyTmp_Batch",groupBuildingHasTCommunitySupplyTmpList);
	}
	
	/**
	 * 更新(小区临时商家关系表)信息
	 * @param groupBuildingHasTCommunitySupplyTmp
	 * @return
	 */
	@Override
	public int updateGroupBuildingHasTCommunitySupplyTmp(GroupBuildingHasTCommunitySupplyTmp groupBuildingHasTCommunitySupplyTmp){
		return sqlSession.update("groupBuildingHasTCommunitySupplyTmpBase.update_groupBuildingHasTCommunitySupplyTmp", groupBuildingHasTCommunitySupplyTmp);
	}
	/**
	 * 批量更新(小区临时商家关系表)信息
	 * @param groupBuildingHasTCommunitySupplyTmpList
	 * @return
	 */
	@Override
	public int updateGroupBuildingHasTCommunitySupplyTmpBatch(List<GroupBuildingHasTCommunitySupplyTmp> groupBuildingHasTCommunitySupplyTmpList) {
		return sqlSession.update("groupBuildingHasTCommunitySupplyTmpBase.update_groupBuildingHasTCommunitySupplyTmp_Batch", groupBuildingHasTCommunitySupplyTmpList);
	}
	
	/**
	 * 根据序列号删除(小区临时商家关系表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteGroupBuildingHasTCommunitySupplyTmpLogic(java.math.BigInteger id){
		GroupBuildingHasTCommunitySupplyTmp groupBuildingHasTCommunitySupplyTmp = new GroupBuildingHasTCommunitySupplyTmp();
		groupBuildingHasTCommunitySupplyTmp.setId(id);
		groupBuildingHasTCommunitySupplyTmp.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("groupBuildingHasTCommunitySupplyTmpBase.delete_groupBuildingHasTCommunitySupplyTmp_Logic",groupBuildingHasTCommunitySupplyTmp);
	}
	
	/**
	 * 根据Id 批量删除(小区临时商家关系表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteGroupBuildingHasTCommunitySupplyTmpLogicBatch(List<java.math.BigInteger> idList) {
		List<GroupBuildingHasTCommunitySupplyTmp> delList = new java.util.ArrayList<GroupBuildingHasTCommunitySupplyTmp>();
		for(java.math.BigInteger id:idList){
			GroupBuildingHasTCommunitySupplyTmp groupBuildingHasTCommunitySupplyTmp = new GroupBuildingHasTCommunitySupplyTmp();
			groupBuildingHasTCommunitySupplyTmp.setId(id);
			groupBuildingHasTCommunitySupplyTmp.setSys0DelState(RecordState_DELETED);
			delList.add(groupBuildingHasTCommunitySupplyTmp);
		}
		return sqlSession.update("groupBuildingHasTCommunitySupplyTmpBase.delete_groupBuildingHasTCommunitySupplyTmp_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(小区临时商家关系表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteGroupBuildingHasTCommunitySupplyTmp(java.math.BigInteger id){
//		return sqlSession.delete("groupBuildingHasTCommunitySupplyTmpBase.delete_groupBuildingHasTCommunitySupplyTmp", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(小区临时商家关系表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteGroupBuildingHasTCommunitySupplyTmpBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("groupBuildingHasTCommunitySupplyTmpBase.delete_groupBuildingHasTCommunitySupplyTmp_Batch", idList);
//	}
	
	
}
