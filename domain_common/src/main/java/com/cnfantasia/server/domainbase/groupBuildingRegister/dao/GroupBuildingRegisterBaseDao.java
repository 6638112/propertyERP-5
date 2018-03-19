package com.cnfantasia.server.domainbase.groupBuildingRegister.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.groupBuildingRegister.entity.GroupBuildingRegister;

/**
 * 描述:(注册的小区信息) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class GroupBuildingRegisterBaseDao extends AbstractBaseDao implements IGroupBuildingRegisterBaseDao{
	/**
	 * 根据条件查询(注册的小区信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<GroupBuildingRegister> selectGroupBuildingRegisterByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("groupBuildingRegisterBase.select_groupBuildingRegister",paramMap);
	}
	/**
	 * 按条件分页查询(注册的小区信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<GroupBuildingRegister> selectGroupBuildingRegisterByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectGroupBuildingRegisterCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<GroupBuildingRegister> resMap= sqlSession.selectList("groupBuildingRegisterBase.select_groupBuildingRegister_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(注册的小区信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public GroupBuildingRegister selectGroupBuildingRegisterBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("groupBuildingRegisterBase.select_groupBuildingRegister_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(注册的小区信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectGroupBuildingRegisterCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("groupBuildingRegisterBase.select_groupBuildingRegister_count", paramMap);
	}
	/**
	 * 往(注册的小区信息)新增一条记录
	 * @param groupBuildingRegister
	 * @return
	 */
	@Override
	public int insertGroupBuildingRegister(GroupBuildingRegister groupBuildingRegister){
		return sqlSession.insert("groupBuildingRegisterBase.insert_groupBuildingRegister",groupBuildingRegister);
	}
	/**
	 * 批量新增(注册的小区信息)信息
	 * @param groupBuildingRegisterList
	 * @return
	 */
	@Override
	public int insertGroupBuildingRegisterBatch(List<GroupBuildingRegister> groupBuildingRegisterList) {
		return sqlSession.insert("groupBuildingRegisterBase.insert_groupBuildingRegister_Batch",groupBuildingRegisterList);
	}
	
	/**
	 * 更新(注册的小区信息)信息
	 * @param groupBuildingRegister
	 * @return
	 */
	@Override
	public int updateGroupBuildingRegister(GroupBuildingRegister groupBuildingRegister){
		return sqlSession.update("groupBuildingRegisterBase.update_groupBuildingRegister", groupBuildingRegister);
	}
	/**
	 * 批量更新(注册的小区信息)信息
	 * @param groupBuildingRegisterList
	 * @return
	 */
	@Override
	public int updateGroupBuildingRegisterBatch(List<GroupBuildingRegister> groupBuildingRegisterList) {
		return sqlSession.update("groupBuildingRegisterBase.update_groupBuildingRegister_Batch", groupBuildingRegisterList);
	}
	
	/**
	 * 根据序列号删除(注册的小区信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteGroupBuildingRegisterLogic(java.math.BigInteger id){
		GroupBuildingRegister groupBuildingRegister = new GroupBuildingRegister();
		groupBuildingRegister.setId(id);
		groupBuildingRegister.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("groupBuildingRegisterBase.delete_groupBuildingRegister_Logic",groupBuildingRegister);
	}
	
	/**
	 * 根据Id 批量删除(注册的小区信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteGroupBuildingRegisterLogicBatch(List<java.math.BigInteger> idList) {
		List<GroupBuildingRegister> delList = new java.util.ArrayList<GroupBuildingRegister>();
		for(java.math.BigInteger id:idList){
			GroupBuildingRegister groupBuildingRegister = new GroupBuildingRegister();
			groupBuildingRegister.setId(id);
			groupBuildingRegister.setSys0DelState(RecordState_DELETED);
			delList.add(groupBuildingRegister);
		}
		return sqlSession.update("groupBuildingRegisterBase.delete_groupBuildingRegister_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(注册的小区信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteGroupBuildingRegister(java.math.BigInteger id){
//		return sqlSession.delete("groupBuildingRegisterBase.delete_groupBuildingRegister", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(注册的小区信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteGroupBuildingRegisterBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("groupBuildingRegisterBase.delete_groupBuildingRegister_Batch", idList);
//	}
	
	
}
