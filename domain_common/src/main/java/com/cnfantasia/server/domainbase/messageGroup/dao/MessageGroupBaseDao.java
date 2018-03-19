package com.cnfantasia.server.domainbase.messageGroup.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.messageGroup.entity.MessageGroup;

/**
 * 描述:(消息小区关联表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class MessageGroupBaseDao extends AbstractBaseDao implements IMessageGroupBaseDao{
	/**
	 * 根据条件查询(消息小区关联表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<MessageGroup> selectMessageGroupByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("messageGroupBase.select_messageGroup",paramMap);
	}
	/**
	 * 按条件分页查询(消息小区关联表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<MessageGroup> selectMessageGroupByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectMessageGroupCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<MessageGroup> resMap= sqlSession.selectList("messageGroupBase.select_messageGroup_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(消息小区关联表)信息
	 * @param id
	 * @return
	 */
	@Override
	public MessageGroup selectMessageGroupBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("messageGroupBase.select_messageGroup_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(消息小区关联表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectMessageGroupCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("messageGroupBase.select_messageGroup_count", paramMap);
	}
	/**
	 * 往(消息小区关联表)新增一条记录
	 * @param messageGroup
	 * @return
	 */
	@Override
	public int insertMessageGroup(MessageGroup messageGroup){
		return sqlSession.insert("messageGroupBase.insert_messageGroup",messageGroup);
	}
	/**
	 * 批量新增(消息小区关联表)信息
	 * @param messageGroupList
	 * @return
	 */
	@Override
	public int insertMessageGroupBatch(List<MessageGroup> messageGroupList) {
		return sqlSession.insert("messageGroupBase.insert_messageGroup_Batch",messageGroupList);
	}
	
	/**
	 * 更新(消息小区关联表)信息
	 * @param messageGroup
	 * @return
	 */
	@Override
	public int updateMessageGroup(MessageGroup messageGroup){
		return sqlSession.update("messageGroupBase.update_messageGroup", messageGroup);
	}
	/**
	 * 批量更新(消息小区关联表)信息
	 * @param messageGroupList
	 * @return
	 */
	@Override
	public int updateMessageGroupBatch(List<MessageGroup> messageGroupList) {
		return sqlSession.update("messageGroupBase.update_messageGroup_Batch", messageGroupList);
	}
	
	/**
	 * 根据序列号删除(消息小区关联表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteMessageGroupLogic(java.math.BigInteger id){
		MessageGroup messageGroup = new MessageGroup();
		messageGroup.setId(id);
		messageGroup.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("messageGroupBase.delete_messageGroup_Logic",messageGroup);
	}
	
	/**
	 * 根据Id 批量删除(消息小区关联表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteMessageGroupLogicBatch(List<java.math.BigInteger> idList) {
		List<MessageGroup> delList = new java.util.ArrayList<MessageGroup>();
		for(java.math.BigInteger id:idList){
			MessageGroup messageGroup = new MessageGroup();
			messageGroup.setId(id);
			messageGroup.setSys0DelState(RecordState_DELETED);
			delList.add(messageGroup);
		}
		return sqlSession.update("messageGroupBase.delete_messageGroup_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(消息小区关联表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteMessageGroup(java.math.BigInteger id){
//		return sqlSession.delete("messageGroupBase.delete_messageGroup", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(消息小区关联表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteMessageGroupBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("messageGroupBase.delete_messageGroup_Batch", idList);
//	}
	
	
}
