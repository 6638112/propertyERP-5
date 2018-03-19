package com.cnfantasia.server.domainbase.messagePrint.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.messagePrint.entity.MessagePrint;

/**
 * 描述:(消息打印) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IMessagePrintBaseDao {
	/**
	 * 根据条件查询(消息打印)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<MessagePrint> selectMessagePrintByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(消息打印)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<MessagePrint> selectMessagePrintByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(消息打印)信息
	 * @param id
	 * @return
	 */
	public MessagePrint selectMessagePrintBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(消息打印)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectMessagePrintCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(消息打印)新增一条记录
	 * @param messagePrint
	 * @return
	 */
	public int insertMessagePrint(MessagePrint messagePrint);
	
	/**
	 * 批量新增(消息打印)信息
	 * @param messagePrintList
	 * @return
	 */
	public int insertMessagePrintBatch(List<MessagePrint> messagePrintList);
	
	/**
	 * 更新(消息打印)信息
	 * @param messagePrint
	 * @return
	 */
	public int updateMessagePrint(MessagePrint messagePrint);
	
	/**
	 * 批量更新(消息打印)信息
	 * @param messagePrintList
	 * @return
	 */
	public int updateMessagePrintBatch(List<MessagePrint> messagePrintList);
	
	/**
	 * 根据序列号删除(消息打印)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteMessagePrintLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(消息打印)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteMessagePrintLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(消息打印)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteMessagePrint(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(消息打印)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteMessagePrintBatch(List<java.math.BigInteger> idList);
	
	
}
