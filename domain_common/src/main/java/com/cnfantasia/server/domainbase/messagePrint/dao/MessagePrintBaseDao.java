package com.cnfantasia.server.domainbase.messagePrint.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.messagePrint.entity.MessagePrint;

/**
 * 描述:(消息打印) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class MessagePrintBaseDao extends AbstractBaseDao implements IMessagePrintBaseDao{
	/**
	 * 根据条件查询(消息打印)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<MessagePrint> selectMessagePrintByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("messagePrintBase.select_messagePrint",paramMap);
	}
	/**
	 * 按条件分页查询(消息打印)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<MessagePrint> selectMessagePrintByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectMessagePrintCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<MessagePrint> resMap= sqlSession.selectList("messagePrintBase.select_messagePrint_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(消息打印)信息
	 * @param id
	 * @return
	 */
	@Override
	public MessagePrint selectMessagePrintBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("messagePrintBase.select_messagePrint_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(消息打印)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectMessagePrintCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("messagePrintBase.select_messagePrint_count", paramMap);
	}
	/**
	 * 往(消息打印)新增一条记录
	 * @param messagePrint
	 * @return
	 */
	@Override
	public int insertMessagePrint(MessagePrint messagePrint){
		return sqlSession.insert("messagePrintBase.insert_messagePrint",messagePrint);
	}
	/**
	 * 批量新增(消息打印)信息
	 * @param messagePrintList
	 * @return
	 */
	@Override
	public int insertMessagePrintBatch(List<MessagePrint> messagePrintList) {
		if (messagePrintList == null || messagePrintList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = messagePrintList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<MessagePrint> batchList = messagePrintList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("messagePrintBase.insert_messagePrint_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(消息打印)信息
	 * @param messagePrint
	 * @return
	 */
	@Override
	public int updateMessagePrint(MessagePrint messagePrint){
		return sqlSession.update("messagePrintBase.update_messagePrint", messagePrint);
	}
	/**
	 * 批量更新(消息打印)信息
	 * @param messagePrintList
	 * @return
	 */
	@Override
	public int updateMessagePrintBatch(List<MessagePrint> messagePrintList) {
		if (messagePrintList == null || messagePrintList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("messagePrintBase.update_messagePrint_Batch", messagePrintList);
	}
	
	/**
	 * 根据序列号删除(消息打印)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteMessagePrintLogic(java.math.BigInteger id){
		MessagePrint messagePrint = new MessagePrint();
		messagePrint.setId(id);
		messagePrint.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("messagePrintBase.delete_messagePrint_Logic",messagePrint);
	}
	
	/**
	 * 根据Id 批量删除(消息打印)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteMessagePrintLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<MessagePrint> delList = new java.util.ArrayList<MessagePrint>();
		for(java.math.BigInteger id:idList){
			MessagePrint messagePrint = new MessagePrint();
			messagePrint.setId(id);
			messagePrint.setSys0DelState(RecordState_DELETED);
			delList.add(messagePrint);
		}
		return sqlSession.update("messagePrintBase.delete_messagePrint_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(消息打印)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteMessagePrint(java.math.BigInteger id){
//		return sqlSession.delete("messagePrintBase.delete_messagePrint", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(消息打印)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteMessagePrintBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("messagePrintBase.delete_messagePrint_Batch", idList);
//	}
	
	
}
