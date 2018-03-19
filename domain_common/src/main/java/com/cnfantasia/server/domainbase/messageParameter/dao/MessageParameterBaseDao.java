package com.cnfantasia.server.domainbase.messageParameter.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.messageParameter.entity.MessageParameter;

/**
 * 描述:(消息参数表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class MessageParameterBaseDao extends AbstractBaseDao implements IMessageParameterBaseDao{
	/**
	 * 根据条件查询(消息参数表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<MessageParameter> selectMessageParameterByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("messageParameterBase.select_messageParameter",paramMap);
	}
	/**
	 * 按条件分页查询(消息参数表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<MessageParameter> selectMessageParameterByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectMessageParameterCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<MessageParameter> resMap= sqlSession.selectList("messageParameterBase.select_messageParameter_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(消息参数表)信息
	 * @param id
	 * @return
	 */
	@Override
	public MessageParameter selectMessageParameterBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("messageParameterBase.select_messageParameter_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(消息参数表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectMessageParameterCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("messageParameterBase.select_messageParameter_count", paramMap);
	}
	/**
	 * 往(消息参数表)新增一条记录
	 * @param messageParameter
	 * @return
	 */
	@Override
	public int insertMessageParameter(MessageParameter messageParameter){
		return sqlSession.insert("messageParameterBase.insert_messageParameter",messageParameter);
	}
	/**
	 * 批量新增(消息参数表)信息
	 * @param messageParameterList
	 * @return
	 */
	@Override
	public int insertMessageParameterBatch(List<MessageParameter> messageParameterList) {
		return sqlSession.insert("messageParameterBase.insert_messageParameter_Batch",messageParameterList);
	}
	
	/**
	 * 更新(消息参数表)信息
	 * @param messageParameter
	 * @return
	 */
	@Override
	public int updateMessageParameter(MessageParameter messageParameter){
		return sqlSession.update("messageParameterBase.update_messageParameter", messageParameter);
	}
	/**
	 * 批量更新(消息参数表)信息
	 * @param messageParameterList
	 * @return
	 */
	@Override
	public int updateMessageParameterBatch(List<MessageParameter> messageParameterList) {
		return sqlSession.update("messageParameterBase.update_messageParameter_Batch", messageParameterList);
	}
	
	/**
	 * 根据序列号删除(消息参数表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteMessageParameterLogic(java.math.BigInteger id){
		MessageParameter messageParameter = new MessageParameter();
		messageParameter.setId(id);
		messageParameter.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("messageParameterBase.delete_messageParameter_Logic",messageParameter);
	}
	
	/**
	 * 根据Id 批量删除(消息参数表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteMessageParameterLogicBatch(List<java.math.BigInteger> idList) {
		List<MessageParameter> delList = new java.util.ArrayList<MessageParameter>();
		for(java.math.BigInteger id:idList){
			MessageParameter messageParameter = new MessageParameter();
			messageParameter.setId(id);
			messageParameter.setSys0DelState(RecordState_DELETED);
			delList.add(messageParameter);
		}
		return sqlSession.update("messageParameterBase.delete_messageParameter_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(消息参数表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteMessageParameter(java.math.BigInteger id){
//		return sqlSession.delete("messageParameterBase.delete_messageParameter", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(消息参数表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteMessageParameterBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("messageParameterBase.delete_messageParameter_Batch", idList);
//	}
	
	
}
