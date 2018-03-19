package com.cnfantasia.server.domainbase.groupHomeMessage.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.groupHomeMessage.entity.GroupHomeMessage;

/**
 * 描述:(首页公共消息表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class GroupHomeMessageBaseDao extends AbstractBaseDao implements IGroupHomeMessageBaseDao{
	/**
	 * 根据条件查询(首页公共消息表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<GroupHomeMessage> selectGroupHomeMessageByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("groupHomeMessageBase.select_groupHomeMessage",paramMap);
	}
	/**
	 * 按条件分页查询(首页公共消息表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<GroupHomeMessage> selectGroupHomeMessageByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectGroupHomeMessageCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<GroupHomeMessage> resMap= sqlSession.selectList("groupHomeMessageBase.select_groupHomeMessage_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(首页公共消息表)信息
	 * @param id
	 * @return
	 */
	@Override
	public GroupHomeMessage selectGroupHomeMessageBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("groupHomeMessageBase.select_groupHomeMessage_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(首页公共消息表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectGroupHomeMessageCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("groupHomeMessageBase.select_groupHomeMessage_count", paramMap);
	}
	/**
	 * 往(首页公共消息表)新增一条记录
	 * @param groupHomeMessage
	 * @return
	 */
	@Override
	public int insertGroupHomeMessage(GroupHomeMessage groupHomeMessage){
		return sqlSession.insert("groupHomeMessageBase.insert_groupHomeMessage",groupHomeMessage);
	}
	/**
	 * 批量新增(首页公共消息表)信息
	 * @param groupHomeMessageList
	 * @return
	 */
	@Override
	public int insertGroupHomeMessageBatch(List<GroupHomeMessage> groupHomeMessageList) {
		if (groupHomeMessageList == null || groupHomeMessageList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = groupHomeMessageList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<GroupHomeMessage> batchList = groupHomeMessageList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("groupHomeMessageBase.insert_groupHomeMessage_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(首页公共消息表)信息
	 * @param groupHomeMessage
	 * @return
	 */
	@Override
	public int updateGroupHomeMessage(GroupHomeMessage groupHomeMessage){
		return sqlSession.update("groupHomeMessageBase.update_groupHomeMessage", groupHomeMessage);
	}
	/**
	 * 批量更新(首页公共消息表)信息
	 * @param groupHomeMessageList
	 * @return
	 */
	@Override
	public int updateGroupHomeMessageBatch(List<GroupHomeMessage> groupHomeMessageList) {
		if (groupHomeMessageList == null || groupHomeMessageList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("groupHomeMessageBase.update_groupHomeMessage_Batch", groupHomeMessageList);
	}
	
	/**
	 * 根据序列号删除(首页公共消息表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteGroupHomeMessageLogic(java.math.BigInteger id){
		GroupHomeMessage groupHomeMessage = new GroupHomeMessage();
		groupHomeMessage.setId(id);
		groupHomeMessage.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("groupHomeMessageBase.delete_groupHomeMessage_Logic",groupHomeMessage);
	}
	
	/**
	 * 根据Id 批量删除(首页公共消息表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteGroupHomeMessageLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<GroupHomeMessage> delList = new java.util.ArrayList<GroupHomeMessage>();
		for(java.math.BigInteger id:idList){
			GroupHomeMessage groupHomeMessage = new GroupHomeMessage();
			groupHomeMessage.setId(id);
			groupHomeMessage.setSys0DelState(RecordState_DELETED);
			delList.add(groupHomeMessage);
		}
		return sqlSession.update("groupHomeMessageBase.delete_groupHomeMessage_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(首页公共消息表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteGroupHomeMessage(java.math.BigInteger id){
//		return sqlSession.delete("groupHomeMessageBase.delete_groupHomeMessage", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(首页公共消息表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteGroupHomeMessageBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("groupHomeMessageBase.delete_groupHomeMessage_Batch", idList);
//	}
	
	
}
