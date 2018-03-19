package com.cnfantasia.server.domainbase.messageToBeSend.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.messageToBeSend.entity.MessageToBeSend;

/**
 * 描述:(待发送短信或待推送消息) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class MessageToBeSendBaseDao extends AbstractBaseDao implements IMessageToBeSendBaseDao{
	/**
	 * 根据条件查询(待发送短信或待推送消息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<MessageToBeSend> selectMessageToBeSendByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("messageToBeSendBase.select_messageToBeSend",paramMap);
	}
	/**
	 * 按条件分页查询(待发送短信或待推送消息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<MessageToBeSend> selectMessageToBeSendByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectMessageToBeSendCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<MessageToBeSend> resMap= sqlSession.selectList("messageToBeSendBase.select_messageToBeSend_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(待发送短信或待推送消息)信息
	 * @param id
	 * @return
	 */
	@Override
	public MessageToBeSend selectMessageToBeSendBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("messageToBeSendBase.select_messageToBeSend_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(待发送短信或待推送消息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectMessageToBeSendCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("messageToBeSendBase.select_messageToBeSend_count", paramMap);
	}
	/**
	 * 往(待发送短信或待推送消息)新增一条记录
	 * @param messageToBeSend
	 * @return
	 */
	@Override
	public int insertMessageToBeSend(MessageToBeSend messageToBeSend){
		return sqlSession.insert("messageToBeSendBase.insert_messageToBeSend",messageToBeSend);
	}
	/**
	 * 批量新增(待发送短信或待推送消息)信息
	 * @param messageToBeSendList
	 * @return
	 */
	@Override
	public int insertMessageToBeSendBatch(List<MessageToBeSend> messageToBeSendList) {
		if (messageToBeSendList == null || messageToBeSendList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = messageToBeSendList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<MessageToBeSend> batchList = messageToBeSendList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("messageToBeSendBase.insert_messageToBeSend_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(待发送短信或待推送消息)信息
	 * @param messageToBeSend
	 * @return
	 */
	@Override
	public int updateMessageToBeSend(MessageToBeSend messageToBeSend){
		return sqlSession.update("messageToBeSendBase.update_messageToBeSend", messageToBeSend);
	}
	/**
	 * 批量更新(待发送短信或待推送消息)信息
	 * @param messageToBeSendList
	 * @return
	 */
	@Override
	public int updateMessageToBeSendBatch(List<MessageToBeSend> messageToBeSendList) {
		if (messageToBeSendList == null || messageToBeSendList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("messageToBeSendBase.update_messageToBeSend_Batch", messageToBeSendList);
	}
	
	/**
	 * 根据序列号删除(待发送短信或待推送消息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteMessageToBeSendLogic(java.math.BigInteger id){
		MessageToBeSend messageToBeSend = new MessageToBeSend();
		messageToBeSend.setId(id);
		messageToBeSend.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("messageToBeSendBase.delete_messageToBeSend_Logic",messageToBeSend);
	}
	
	/**
	 * 根据Id 批量删除(待发送短信或待推送消息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteMessageToBeSendLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<MessageToBeSend> delList = new java.util.ArrayList<MessageToBeSend>();
		for(java.math.BigInteger id:idList){
			MessageToBeSend messageToBeSend = new MessageToBeSend();
			messageToBeSend.setId(id);
			messageToBeSend.setSys0DelState(RecordState_DELETED);
			delList.add(messageToBeSend);
		}
		return sqlSession.update("messageToBeSendBase.delete_messageToBeSend_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(待发送短信或待推送消息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteMessageToBeSend(java.math.BigInteger id){
//		return sqlSession.delete("messageToBeSendBase.delete_messageToBeSend", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(待发送短信或待推送消息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteMessageToBeSendBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("messageToBeSendBase.delete_messageToBeSend_Batch", idList);
//	}
	
	
}
