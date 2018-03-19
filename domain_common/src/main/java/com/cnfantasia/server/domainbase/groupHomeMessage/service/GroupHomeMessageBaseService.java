package com.cnfantasia.server.domainbase.groupHomeMessage.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.groupHomeMessage.dao.IGroupHomeMessageBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.groupHomeMessage.entity.GroupHomeMessage;

/**
 * 描述:(首页公共消息表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class GroupHomeMessageBaseService implements IGroupHomeMessageBaseService{
	
	private IGroupHomeMessageBaseDao groupHomeMessageBaseDao;
	public void setGroupHomeMessageBaseDao(IGroupHomeMessageBaseDao groupHomeMessageBaseDao) {
		this.groupHomeMessageBaseDao = groupHomeMessageBaseDao;
	}
	/**
	 * 根据条件查询(首页公共消息表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<GroupHomeMessage> getGroupHomeMessageByCondition(Map<String,Object> paramMap){
		return groupHomeMessageBaseDao.selectGroupHomeMessageByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(首页公共消息表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<GroupHomeMessage> getGroupHomeMessageByConditionDim(Map<String,Object> paramMap){
		return groupHomeMessageBaseDao.selectGroupHomeMessageByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(首页公共消息表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<GroupHomeMessage> getGroupHomeMessageByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return groupHomeMessageBaseDao.selectGroupHomeMessageByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(首页公共消息表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<GroupHomeMessage> getGroupHomeMessageByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return groupHomeMessageBaseDao.selectGroupHomeMessageByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(首页公共消息表)信息
	 * @param id
	 * @return
	 */
	@Override
	public GroupHomeMessage getGroupHomeMessageBySeqId(java.math.BigInteger id){
		return groupHomeMessageBaseDao.selectGroupHomeMessageBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(首页公共消息表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getGroupHomeMessageCount(Map<String,Object> paramMap){
		return groupHomeMessageBaseDao.selectGroupHomeMessageCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(首页公共消息表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getGroupHomeMessageCountDim(Map<String,Object> paramMap){
		return groupHomeMessageBaseDao.selectGroupHomeMessageCount(paramMap,true);
	}
	/**
	 * 往(首页公共消息表)新增一条记录
	 * @param groupHomeMessage
	 * @return
	 */
	@Override
	public int insertGroupHomeMessage(GroupHomeMessage groupHomeMessage){
		return groupHomeMessageBaseDao.insertGroupHomeMessage(groupHomeMessage);
	}
	/**
	 * 批量新增(首页公共消息表)
	 * @param groupHomeMessageList
	 * @return
	 */
	@Override
	public int insertGroupHomeMessageBatch(List<GroupHomeMessage> groupHomeMessageList){
		return groupHomeMessageBaseDao.insertGroupHomeMessageBatch(groupHomeMessageList);
	}
	/**
	 * 更新(首页公共消息表)信息
	 * @param groupHomeMessage
	 * @return
	 */
	@Override
	public int updateGroupHomeMessage(GroupHomeMessage groupHomeMessage){
		return groupHomeMessageBaseDao.updateGroupHomeMessage(groupHomeMessage);
	}
	/**
	 * 批量更新(首页公共消息表)信息
	 * @param groupHomeMessageList
	 * @return
	 */
	@Override
	public int updateGroupHomeMessageBatch(List<GroupHomeMessage> groupHomeMessageList){
		return groupHomeMessageBaseDao.updateGroupHomeMessageBatch(groupHomeMessageList);
	}
	/**
	 * 根据序列号删除(首页公共消息表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteGroupHomeMessageLogic(java.math.BigInteger id){
		return groupHomeMessageBaseDao.deleteGroupHomeMessageLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(首页公共消息表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteGroupHomeMessageLogicBatch(List<java.math.BigInteger> idList){
		return groupHomeMessageBaseDao.deleteGroupHomeMessageLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(首页公共消息表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteGroupHomeMessage(java.math.BigInteger id){
//		return groupHomeMessageBaseDao.deleteGroupHomeMessage(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(首页公共消息表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteGroupHomeMessageBatch(List<java.math.BigInteger> idList){
//		return groupHomeMessageBaseDao.deleteGroupHomeMessageBatch(idList);
//	}
	
}
