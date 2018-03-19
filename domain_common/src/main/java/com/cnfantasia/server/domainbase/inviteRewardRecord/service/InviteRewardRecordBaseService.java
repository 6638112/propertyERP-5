package com.cnfantasia.server.domainbase.inviteRewardRecord.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.inviteRewardRecord.dao.IInviteRewardRecordBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.inviteRewardRecord.entity.InviteRewardRecord;

/**
 * 描述:(邀请奖励纪录表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class InviteRewardRecordBaseService implements IInviteRewardRecordBaseService{
	
	private IInviteRewardRecordBaseDao inviteRewardRecordBaseDao;
	public void setInviteRewardRecordBaseDao(IInviteRewardRecordBaseDao inviteRewardRecordBaseDao) {
		this.inviteRewardRecordBaseDao = inviteRewardRecordBaseDao;
	}
	/**
	 * 根据条件查询(邀请奖励纪录表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<InviteRewardRecord> getInviteRewardRecordByCondition(Map<String,Object> paramMap){
		return inviteRewardRecordBaseDao.selectInviteRewardRecordByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(邀请奖励纪录表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<InviteRewardRecord> getInviteRewardRecordByConditionDim(Map<String,Object> paramMap){
		return inviteRewardRecordBaseDao.selectInviteRewardRecordByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(邀请奖励纪录表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<InviteRewardRecord> getInviteRewardRecordByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return inviteRewardRecordBaseDao.selectInviteRewardRecordByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(邀请奖励纪录表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<InviteRewardRecord> getInviteRewardRecordByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return inviteRewardRecordBaseDao.selectInviteRewardRecordByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(邀请奖励纪录表)信息
	 * @param id
	 * @return
	 */
	@Override
	public InviteRewardRecord getInviteRewardRecordBySeqId(java.math.BigInteger id){
		return inviteRewardRecordBaseDao.selectInviteRewardRecordBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(邀请奖励纪录表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getInviteRewardRecordCount(Map<String,Object> paramMap){
		return inviteRewardRecordBaseDao.selectInviteRewardRecordCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(邀请奖励纪录表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getInviteRewardRecordCountDim(Map<String,Object> paramMap){
		return inviteRewardRecordBaseDao.selectInviteRewardRecordCount(paramMap,true);
	}
	/**
	 * 往(邀请奖励纪录表)新增一条记录
	 * @param inviteRewardRecord
	 * @return
	 */
	@Override
	public int insertInviteRewardRecord(InviteRewardRecord inviteRewardRecord){
		return inviteRewardRecordBaseDao.insertInviteRewardRecord(inviteRewardRecord);
	}
	/**
	 * 批量新增(邀请奖励纪录表)
	 * @param inviteRewardRecordList
	 * @return
	 */
	@Override
	public int insertInviteRewardRecordBatch(List<InviteRewardRecord> inviteRewardRecordList){
		return inviteRewardRecordBaseDao.insertInviteRewardRecordBatch(inviteRewardRecordList);
	}
	/**
	 * 更新(邀请奖励纪录表)信息
	 * @param inviteRewardRecord
	 * @return
	 */
	@Override
	public int updateInviteRewardRecord(InviteRewardRecord inviteRewardRecord){
		return inviteRewardRecordBaseDao.updateInviteRewardRecord(inviteRewardRecord);
	}
	/**
	 * 批量更新(邀请奖励纪录表)信息
	 * @param inviteRewardRecordList
	 * @return
	 */
	@Override
	public int updateInviteRewardRecordBatch(List<InviteRewardRecord> inviteRewardRecordList){
		return inviteRewardRecordBaseDao.updateInviteRewardRecordBatch(inviteRewardRecordList);
	}
	/**
	 * 根据序列号删除(邀请奖励纪录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteInviteRewardRecordLogic(java.math.BigInteger id){
		return inviteRewardRecordBaseDao.deleteInviteRewardRecordLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(邀请奖励纪录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteInviteRewardRecordLogicBatch(List<java.math.BigInteger> idList){
		return inviteRewardRecordBaseDao.deleteInviteRewardRecordLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(邀请奖励纪录表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteInviteRewardRecord(java.math.BigInteger id){
//		return inviteRewardRecordBaseDao.deleteInviteRewardRecord(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(邀请奖励纪录表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteInviteRewardRecordBatch(List<java.math.BigInteger> idList){
//		return inviteRewardRecordBaseDao.deleteInviteRewardRecordBatch(idList);
//	}
	
}
