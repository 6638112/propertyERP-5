package com.cnfantasia.server.domainbase.userHasHomeMessage.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.userHasHomeMessage.entity.UserHasHomeMessage;

/**
 * 描述:(用户首页消息表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class UserHasHomeMessageBaseDao extends AbstractBaseDao implements IUserHasHomeMessageBaseDao{
	/**
	 * 根据条件查询(用户首页消息表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<UserHasHomeMessage> selectUserHasHomeMessageByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("userHasHomeMessageBase.select_userHasHomeMessage",paramMap);
	}
	/**
	 * 按条件分页查询(用户首页消息表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<UserHasHomeMessage> selectUserHasHomeMessageByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectUserHasHomeMessageCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<UserHasHomeMessage> resMap= sqlSession.selectList("userHasHomeMessageBase.select_userHasHomeMessage_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(用户首页消息表)信息
	 * @param id
	 * @return
	 */
	@Override
	public UserHasHomeMessage selectUserHasHomeMessageBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("userHasHomeMessageBase.select_userHasHomeMessage_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(用户首页消息表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectUserHasHomeMessageCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("userHasHomeMessageBase.select_userHasHomeMessage_count", paramMap);
	}
	/**
	 * 往(用户首页消息表)新增一条记录
	 * @param userHasHomeMessage
	 * @return
	 */
	@Override
	public int insertUserHasHomeMessage(UserHasHomeMessage userHasHomeMessage){
		return sqlSession.insert("userHasHomeMessageBase.insert_userHasHomeMessage",userHasHomeMessage);
	}
	/**
	 * 批量新增(用户首页消息表)信息
	 * @param userHasHomeMessageList
	 * @return
	 */
	@Override
	public int insertUserHasHomeMessageBatch(List<UserHasHomeMessage> userHasHomeMessageList) {
		if (userHasHomeMessageList == null || userHasHomeMessageList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = userHasHomeMessageList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<UserHasHomeMessage> batchList = userHasHomeMessageList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("userHasHomeMessageBase.insert_userHasHomeMessage_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(用户首页消息表)信息
	 * @param userHasHomeMessage
	 * @return
	 */
	@Override
	public int updateUserHasHomeMessage(UserHasHomeMessage userHasHomeMessage){
		return sqlSession.update("userHasHomeMessageBase.update_userHasHomeMessage", userHasHomeMessage);
	}
	/**
	 * 批量更新(用户首页消息表)信息
	 * @param userHasHomeMessageList
	 * @return
	 */
	@Override
	public int updateUserHasHomeMessageBatch(List<UserHasHomeMessage> userHasHomeMessageList) {
		if (userHasHomeMessageList == null || userHasHomeMessageList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("userHasHomeMessageBase.update_userHasHomeMessage_Batch", userHasHomeMessageList);
	}
	
	/**
	 * 根据序列号删除(用户首页消息表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteUserHasHomeMessageLogic(java.math.BigInteger id){
		UserHasHomeMessage userHasHomeMessage = new UserHasHomeMessage();
		userHasHomeMessage.setId(id);
		userHasHomeMessage.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("userHasHomeMessageBase.delete_userHasHomeMessage_Logic",userHasHomeMessage);
	}
	
	/**
	 * 根据Id 批量删除(用户首页消息表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteUserHasHomeMessageLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<UserHasHomeMessage> delList = new java.util.ArrayList<UserHasHomeMessage>();
		for(java.math.BigInteger id:idList){
			UserHasHomeMessage userHasHomeMessage = new UserHasHomeMessage();
			userHasHomeMessage.setId(id);
			userHasHomeMessage.setSys0DelState(RecordState_DELETED);
			delList.add(userHasHomeMessage);
		}
		return sqlSession.update("userHasHomeMessageBase.delete_userHasHomeMessage_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(用户首页消息表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteUserHasHomeMessage(java.math.BigInteger id){
//		return sqlSession.delete("userHasHomeMessageBase.delete_userHasHomeMessage", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(用户首页消息表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteUserHasHomeMessageBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("userHasHomeMessageBase.delete_userHasHomeMessage_Batch", idList);
//	}
	
	
}
