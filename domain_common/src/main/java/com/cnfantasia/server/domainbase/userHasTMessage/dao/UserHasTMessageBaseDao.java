package com.cnfantasia.server.domainbase.userHasTMessage.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.userHasTMessage.entity.UserHasTMessage;

/**
 * 描述:(用户消息关系) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class UserHasTMessageBaseDao extends AbstractBaseDao implements IUserHasTMessageBaseDao{
	/**
	 * 根据条件查询(用户消息关系)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<UserHasTMessage> selectUserHasTMessageByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("userHasTMessageBase.select_userHasTMessage",paramMap);
	}
	/**
	 * 按条件分页查询(用户消息关系)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<UserHasTMessage> selectUserHasTMessageByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectUserHasTMessageCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<UserHasTMessage> resMap= sqlSession.selectList("userHasTMessageBase.select_userHasTMessage_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(用户消息关系)信息
	 * @param id
	 * @return
	 */
	@Override
	public UserHasTMessage selectUserHasTMessageBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("userHasTMessageBase.select_userHasTMessage_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(用户消息关系)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectUserHasTMessageCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("userHasTMessageBase.select_userHasTMessage_count", paramMap);
	}
	/**
	 * 往(用户消息关系)新增一条记录
	 * @param userHasTMessage
	 * @return
	 */
	@Override
	public int insertUserHasTMessage(UserHasTMessage userHasTMessage){
		return sqlSession.insert("userHasTMessageBase.insert_userHasTMessage",userHasTMessage);
	}
	/**
	 * 批量新增(用户消息关系)信息
	 * @param userHasTMessageList
	 * @return
	 */
	@Override
	public int insertUserHasTMessageBatch(List<UserHasTMessage> userHasTMessageList) {
		return sqlSession.insert("userHasTMessageBase.insert_userHasTMessage_Batch",userHasTMessageList);
	}
	
	/**
	 * 更新(用户消息关系)信息
	 * @param userHasTMessage
	 * @return
	 */
	@Override
	public int updateUserHasTMessage(UserHasTMessage userHasTMessage){
		return sqlSession.update("userHasTMessageBase.update_userHasTMessage", userHasTMessage);
	}
	/**
	 * 批量更新(用户消息关系)信息
	 * @param userHasTMessageList
	 * @return
	 */
	@Override
	public int updateUserHasTMessageBatch(List<UserHasTMessage> userHasTMessageList) {
		return sqlSession.update("userHasTMessageBase.update_userHasTMessage_Batch", userHasTMessageList);
	}
	
	/**
	 * 根据序列号删除(用户消息关系)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteUserHasTMessageLogic(java.math.BigInteger id){
		UserHasTMessage userHasTMessage = new UserHasTMessage();
		userHasTMessage.setId(id);
		userHasTMessage.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("userHasTMessageBase.delete_userHasTMessage_Logic",userHasTMessage);
	}
	
	/**
	 * 根据Id 批量删除(用户消息关系)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteUserHasTMessageLogicBatch(List<java.math.BigInteger> idList) {
		List<UserHasTMessage> delList = new java.util.ArrayList<UserHasTMessage>();
		for(java.math.BigInteger id:idList){
			UserHasTMessage userHasTMessage = new UserHasTMessage();
			userHasTMessage.setId(id);
			userHasTMessage.setSys0DelState(RecordState_DELETED);
			delList.add(userHasTMessage);
		}
		return sqlSession.update("userHasTMessageBase.delete_userHasTMessage_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(用户消息关系)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteUserHasTMessage(java.math.BigInteger id){
//		return sqlSession.delete("userHasTMessageBase.delete_userHasTMessage", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(用户消息关系)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteUserHasTMessageBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("userHasTMessageBase.delete_userHasTMessage_Batch", idList);
//	}
	
	
}
