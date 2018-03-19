package com.cnfantasia.server.domainbase.message.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.message.entity.Message;

/**
 * 描述:(消息表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class MessageBaseDao extends AbstractBaseDao implements IMessageBaseDao{
	/**
	 * 根据条件查询(消息表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<Message> selectMessageByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("messageBase.select_message",paramMap);
	}
	/**
	 * 按条件分页查询(消息表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<Message> selectMessageByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectMessageCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<Message> resMap= sqlSession.selectList("messageBase.select_message_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(消息表)信息
	 * @param id
	 * @return
	 */
	@Override
	public Message selectMessageBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("messageBase.select_message_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(消息表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectMessageCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("messageBase.select_message_count", paramMap);
	}
	/**
	 * 往(消息表)新增一条记录
	 * @param message
	 * @return
	 */
	@Override
	public int insertMessage(Message message){
		return sqlSession.insert("messageBase.insert_message",message);
	}
	/**
	 * 批量新增(消息表)信息
	 * @param messageList
	 * @return
	 */
	@Override
	public int insertMessageBatch(List<Message> messageList) {
		return sqlSession.insert("messageBase.insert_message_Batch",messageList);
	}
	
	/**
	 * 更新(消息表)信息
	 * @param message
	 * @return
	 */
	@Override
	public int updateMessage(Message message){
		return sqlSession.update("messageBase.update_message", message);
	}
	/**
	 * 批量更新(消息表)信息
	 * @param messageList
	 * @return
	 */
	@Override
	public int updateMessageBatch(List<Message> messageList) {
		return sqlSession.update("messageBase.update_message_Batch", messageList);
	}
	
	/**
	 * 根据序列号删除(消息表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteMessageLogic(java.math.BigInteger id){
		Message message = new Message();
		message.setId(id);
		message.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("messageBase.delete_message_Logic",message);
	}
	
	/**
	 * 根据Id 批量删除(消息表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteMessageLogicBatch(List<java.math.BigInteger> idList) {
		List<Message> delList = new java.util.ArrayList<Message>();
		for(java.math.BigInteger id:idList){
			Message message = new Message();
			message.setId(id);
			message.setSys0DelState(RecordState_DELETED);
			delList.add(message);
		}
		return sqlSession.update("messageBase.delete_message_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(消息表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteMessage(java.math.BigInteger id){
//		return sqlSession.delete("messageBase.delete_message", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(消息表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteMessageBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("messageBase.delete_message_Batch", idList);
//	}
	
	
}
