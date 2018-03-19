package com.cnfantasia.server.domainbase.messageView.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.messageView.entity.MessageView;

/**
 * 描述:(消息推送的视图配置) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class MessageViewBaseDao extends AbstractBaseDao implements IMessageViewBaseDao{
	/**
	 * 根据条件查询(消息推送的视图配置)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<MessageView> selectMessageViewByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("messageViewBase.select_messageView",paramMap);
	}
	/**
	 * 按条件分页查询(消息推送的视图配置)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<MessageView> selectMessageViewByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectMessageViewCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<MessageView> resMap= sqlSession.selectList("messageViewBase.select_messageView_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(消息推送的视图配置)信息
	 * @param id
	 * @return
	 */
	@Override
	public MessageView selectMessageViewBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("messageViewBase.select_messageView_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(消息推送的视图配置)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectMessageViewCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("messageViewBase.select_messageView_count", paramMap);
	}
	/**
	 * 往(消息推送的视图配置)新增一条记录
	 * @param messageView
	 * @return
	 */
	@Override
	public int insertMessageView(MessageView messageView){
		return sqlSession.insert("messageViewBase.insert_messageView",messageView);
	}
	/**
	 * 批量新增(消息推送的视图配置)信息
	 * @param messageViewList
	 * @return
	 */
	@Override
	public int insertMessageViewBatch(List<MessageView> messageViewList) {
		return sqlSession.insert("messageViewBase.insert_messageView_Batch",messageViewList);
	}
	
	/**
	 * 更新(消息推送的视图配置)信息
	 * @param messageView
	 * @return
	 */
	@Override
	public int updateMessageView(MessageView messageView){
		return sqlSession.update("messageViewBase.update_messageView", messageView);
	}
	/**
	 * 批量更新(消息推送的视图配置)信息
	 * @param messageViewList
	 * @return
	 */
	@Override
	public int updateMessageViewBatch(List<MessageView> messageViewList) {
		return sqlSession.update("messageViewBase.update_messageView_Batch", messageViewList);
	}
	
	/**
	 * 根据序列号删除(消息推送的视图配置)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteMessageViewLogic(java.math.BigInteger id){
		MessageView messageView = new MessageView();
		messageView.setId(id);
		messageView.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("messageViewBase.delete_messageView_Logic",messageView);
	}
	
	/**
	 * 根据Id 批量删除(消息推送的视图配置)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteMessageViewLogicBatch(List<java.math.BigInteger> idList) {
		List<MessageView> delList = new java.util.ArrayList<MessageView>();
		for(java.math.BigInteger id:idList){
			MessageView messageView = new MessageView();
			messageView.setId(id);
			messageView.setSys0DelState(RecordState_DELETED);
			delList.add(messageView);
		}
		return sqlSession.update("messageViewBase.delete_messageView_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(消息推送的视图配置)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteMessageView(java.math.BigInteger id){
//		return sqlSession.delete("messageViewBase.delete_messageView", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(消息推送的视图配置)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteMessageViewBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("messageViewBase.delete_messageView_Batch", idList);
//	}
	
	
}
