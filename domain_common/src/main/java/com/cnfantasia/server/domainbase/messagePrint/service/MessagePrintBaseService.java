package com.cnfantasia.server.domainbase.messagePrint.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.messagePrint.dao.IMessagePrintBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.messagePrint.entity.MessagePrint;

/**
 * 描述:(消息打印) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class MessagePrintBaseService implements IMessagePrintBaseService{
	
	private IMessagePrintBaseDao messagePrintBaseDao;
	public void setMessagePrintBaseDao(IMessagePrintBaseDao messagePrintBaseDao) {
		this.messagePrintBaseDao = messagePrintBaseDao;
	}
	/**
	 * 根据条件查询(消息打印)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<MessagePrint> getMessagePrintByCondition(Map<String,Object> paramMap){
		return messagePrintBaseDao.selectMessagePrintByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(消息打印)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<MessagePrint> getMessagePrintByConditionDim(Map<String,Object> paramMap){
		return messagePrintBaseDao.selectMessagePrintByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(消息打印)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<MessagePrint> getMessagePrintByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return messagePrintBaseDao.selectMessagePrintByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(消息打印)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<MessagePrint> getMessagePrintByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return messagePrintBaseDao.selectMessagePrintByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(消息打印)信息
	 * @param id
	 * @return
	 */
	@Override
	public MessagePrint getMessagePrintBySeqId(java.math.BigInteger id){
		return messagePrintBaseDao.selectMessagePrintBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(消息打印)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getMessagePrintCount(Map<String,Object> paramMap){
		return messagePrintBaseDao.selectMessagePrintCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(消息打印)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getMessagePrintCountDim(Map<String,Object> paramMap){
		return messagePrintBaseDao.selectMessagePrintCount(paramMap,true);
	}
	/**
	 * 往(消息打印)新增一条记录
	 * @param messagePrint
	 * @return
	 */
	@Override
	public int insertMessagePrint(MessagePrint messagePrint){
		return messagePrintBaseDao.insertMessagePrint(messagePrint);
	}
	/**
	 * 批量新增(消息打印)
	 * @param messagePrintList
	 * @return
	 */
	@Override
	public int insertMessagePrintBatch(List<MessagePrint> messagePrintList){
		return messagePrintBaseDao.insertMessagePrintBatch(messagePrintList);
	}
	/**
	 * 更新(消息打印)信息
	 * @param messagePrint
	 * @return
	 */
	@Override
	public int updateMessagePrint(MessagePrint messagePrint){
		return messagePrintBaseDao.updateMessagePrint(messagePrint);
	}
	/**
	 * 批量更新(消息打印)信息
	 * @param messagePrintList
	 * @return
	 */
	@Override
	public int updateMessagePrintBatch(List<MessagePrint> messagePrintList){
		return messagePrintBaseDao.updateMessagePrintBatch(messagePrintList);
	}
	/**
	 * 根据序列号删除(消息打印)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteMessagePrintLogic(java.math.BigInteger id){
		return messagePrintBaseDao.deleteMessagePrintLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(消息打印)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteMessagePrintLogicBatch(List<java.math.BigInteger> idList){
		return messagePrintBaseDao.deleteMessagePrintLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(消息打印)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteMessagePrint(java.math.BigInteger id){
//		return messagePrintBaseDao.deleteMessagePrint(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(消息打印)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteMessagePrintBatch(List<java.math.BigInteger> idList){
//		return messagePrintBaseDao.deleteMessagePrintBatch(idList);
//	}
	
}
