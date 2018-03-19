package com.cnfantasia.server.domainbase.userXanaduRecord.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.userXanaduRecord.entity.UserXanaduRecord;

/**
 * 描述:(用户世外桃源状态信息) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class UserXanaduRecordBaseDao extends AbstractBaseDao implements IUserXanaduRecordBaseDao{
	/**
	 * 根据条件查询(用户世外桃源状态信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<UserXanaduRecord> selectUserXanaduRecordByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("userXanaduRecordBase.select_userXanaduRecord",paramMap);
	}
	/**
	 * 按条件分页查询(用户世外桃源状态信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<UserXanaduRecord> selectUserXanaduRecordByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectUserXanaduRecordCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<UserXanaduRecord> resMap= sqlSession.selectList("userXanaduRecordBase.select_userXanaduRecord_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(用户世外桃源状态信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public UserXanaduRecord selectUserXanaduRecordBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("userXanaduRecordBase.select_userXanaduRecord_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(用户世外桃源状态信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectUserXanaduRecordCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("userXanaduRecordBase.select_userXanaduRecord_count", paramMap);
	}
	/**
	 * 往(用户世外桃源状态信息)新增一条记录
	 * @param userXanaduRecord
	 * @return
	 */
	@Override
	public int insertUserXanaduRecord(UserXanaduRecord userXanaduRecord){
		return sqlSession.insert("userXanaduRecordBase.insert_userXanaduRecord",userXanaduRecord);
	}
	/**
	 * 批量新增(用户世外桃源状态信息)信息
	 * @param userXanaduRecordList
	 * @return
	 */
	@Override
	public int insertUserXanaduRecordBatch(List<UserXanaduRecord> userXanaduRecordList) {
		return sqlSession.insert("userXanaduRecordBase.insert_userXanaduRecord_Batch",userXanaduRecordList);
	}
	
	/**
	 * 更新(用户世外桃源状态信息)信息
	 * @param userXanaduRecord
	 * @return
	 */
	@Override
	public int updateUserXanaduRecord(UserXanaduRecord userXanaduRecord){
		return sqlSession.update("userXanaduRecordBase.update_userXanaduRecord", userXanaduRecord);
	}
	/**
	 * 批量更新(用户世外桃源状态信息)信息
	 * @param userXanaduRecordList
	 * @return
	 */
	@Override
	public int updateUserXanaduRecordBatch(List<UserXanaduRecord> userXanaduRecordList) {
		return sqlSession.update("userXanaduRecordBase.update_userXanaduRecord_Batch", userXanaduRecordList);
	}
	
	/**
	 * 根据序列号删除(用户世外桃源状态信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteUserXanaduRecordLogic(java.math.BigInteger id){
		UserXanaduRecord userXanaduRecord = new UserXanaduRecord();
		userXanaduRecord.setId(id);
		userXanaduRecord.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("userXanaduRecordBase.delete_userXanaduRecord_Logic",userXanaduRecord);
	}
	
	/**
	 * 根据Id 批量删除(用户世外桃源状态信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteUserXanaduRecordLogicBatch(List<java.math.BigInteger> idList) {
		List<UserXanaduRecord> delList = new java.util.ArrayList<UserXanaduRecord>();
		for(java.math.BigInteger id:idList){
			UserXanaduRecord userXanaduRecord = new UserXanaduRecord();
			userXanaduRecord.setId(id);
			userXanaduRecord.setSys0DelState(RecordState_DELETED);
			delList.add(userXanaduRecord);
		}
		return sqlSession.update("userXanaduRecordBase.delete_userXanaduRecord_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(用户世外桃源状态信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteUserXanaduRecord(java.math.BigInteger id){
//		return sqlSession.delete("userXanaduRecordBase.delete_userXanaduRecord", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(用户世外桃源状态信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteUserXanaduRecordBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("userXanaduRecordBase.delete_userXanaduRecord_Batch", idList);
//	}
	
	
}
