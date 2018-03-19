package com.cnfantasia.server.domainbase.inviteRewardRecord.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.inviteRewardRecord.entity.InviteRewardRecord;

/**
 * 描述:(邀请奖励纪录表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IInviteRewardRecordBaseDao {
	/**
	 * 根据条件查询(邀请奖励纪录表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<InviteRewardRecord> selectInviteRewardRecordByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(邀请奖励纪录表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<InviteRewardRecord> selectInviteRewardRecordByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(邀请奖励纪录表)信息
	 * @param id
	 * @return
	 */
	public InviteRewardRecord selectInviteRewardRecordBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(邀请奖励纪录表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectInviteRewardRecordCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(邀请奖励纪录表)新增一条记录
	 * @param inviteRewardRecord
	 * @return
	 */
	public int insertInviteRewardRecord(InviteRewardRecord inviteRewardRecord);
	
	/**
	 * 批量新增(邀请奖励纪录表)信息
	 * @param inviteRewardRecordList
	 * @return
	 */
	public int insertInviteRewardRecordBatch(List<InviteRewardRecord> inviteRewardRecordList);
	
	/**
	 * 更新(邀请奖励纪录表)信息
	 * @param inviteRewardRecord
	 * @return
	 */
	public int updateInviteRewardRecord(InviteRewardRecord inviteRewardRecord);
	
	/**
	 * 批量更新(邀请奖励纪录表)信息
	 * @param inviteRewardRecordList
	 * @return
	 */
	public int updateInviteRewardRecordBatch(List<InviteRewardRecord> inviteRewardRecordList);
	
	/**
	 * 根据序列号删除(邀请奖励纪录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteInviteRewardRecordLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(邀请奖励纪录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteInviteRewardRecordLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(邀请奖励纪录表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteInviteRewardRecord(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(邀请奖励纪录表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteInviteRewardRecordBatch(List<java.math.BigInteger> idList);
	
	
}
