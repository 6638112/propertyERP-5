package com.cnfantasia.server.domainbase.commUuid.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.commUuid.entity.CommUuid;

/**
 * 描述:(唯一编号) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CommUuidBaseDao extends AbstractBaseDao implements ICommUuidBaseDao{
	/**
	 * 根据条件查询(唯一编号)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommUuid> selectCommUuidByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("commUuidBase.select_commUuid",paramMap);
	}
	/**
	 * 按条件分页查询(唯一编号)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommUuid> selectCommUuidByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCommUuidCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<CommUuid> resMap= sqlSession.selectList("commUuidBase.select_commUuid_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(唯一编号)信息
	 * @param tableName
	 * @return
	 */
	@Override
	public CommUuid selectCommUuidBySeqId(java.lang.String tableName){
		return sqlSession.selectOne("commUuidBase.select_commUuid_bySeqId",tableName);
	}
	/**
	 * 根据条件查询满足条件的(唯一编号)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCommUuidCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("commUuidBase.select_commUuid_count", paramMap);
	}
	/**
	 * 往(唯一编号)新增一条记录
	 * @param commUuid
	 * @return
	 */
	@Override
	public int insertCommUuid(CommUuid commUuid){
		return sqlSession.insert("commUuidBase.insert_commUuid",commUuid);
	}
	/**
	 * 批量新增(唯一编号)信息
	 * @param commUuidList
	 * @return
	 */
	@Override
	public int insertCommUuidBatch(List<CommUuid> commUuidList) {
		return sqlSession.insert("commUuidBase.insert_commUuid_Batch",commUuidList);
	}
	
	/**
	 * 更新(唯一编号)信息
	 * @param commUuid
	 * @return
	 */
	@Override
	public int updateCommUuid(CommUuid commUuid){
		return sqlSession.update("commUuidBase.update_commUuid", commUuid);
	}
	/**
	 * 批量更新(唯一编号)信息
	 * @param commUuidList
	 * @return
	 */
	@Override
	public int updateCommUuidBatch(List<CommUuid> commUuidList) {
		return sqlSession.update("commUuidBase.update_commUuid_Batch", commUuidList);
	}
	
	/**
	 * 根据序列号删除(唯一编号)信息_逻辑删除
	 * @param tableName
	 * @return
	 */
	
	@Override
	public int deleteCommUuidLogic(java.lang.String tableName){
		CommUuid commUuid = new CommUuid();
		commUuid.setTableName(tableName);
		commUuid.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("commUuidBase.delete_commUuid_Logic",commUuid);
	}
	
	/**
	 * 根据Id 批量删除(唯一编号)信息_逻辑删除
	 * @param tableNameList
	 * @return
	 */
	
	@Override
	public int deleteCommUuidLogicBatch(List<java.lang.String> tableNameList) {
		List<CommUuid> delList = new java.util.ArrayList<CommUuid>();
		for(java.lang.String tableName:tableNameList){
			CommUuid commUuid = new CommUuid();
			commUuid.setTableName(tableName);
			commUuid.setSys0DelState(RecordState_DELETED);
			delList.add(commUuid);
		}
		return sqlSession.update("commUuidBase.delete_commUuid_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(唯一编号)信息
//	 * @param tableName
//	 * @return
//	 */
//	@Override
//	public int deleteCommUuid(java.lang.String tableName){
//		return sqlSession.delete("commUuidBase.delete_commUuid", tableName);
//	}
//	
//	/**
//	 * 根据序列号批量删除(唯一编号)信息
//	 * @param tableNameList
//	 * @return
//	 */
//	@Override
//	public int deleteCommUuidBatch(List<java.lang.String> tableNameList) {
//		return sqlSession.delete("commUuidBase.delete_commUuid_Batch", tableNameList);
//	}
	
	
}
