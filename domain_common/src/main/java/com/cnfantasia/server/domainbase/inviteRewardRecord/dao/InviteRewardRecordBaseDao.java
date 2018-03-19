package com.cnfantasia.server.domainbase.inviteRewardRecord.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.inviteRewardRecord.entity.InviteRewardRecord;

/**
 * 描述:(邀请奖励纪录表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class InviteRewardRecordBaseDao extends AbstractBaseDao implements IInviteRewardRecordBaseDao{
	/**
	 * 根据条件查询(邀请奖励纪录表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<InviteRewardRecord> selectInviteRewardRecordByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("inviteRewardRecordBase.select_inviteRewardRecord",paramMap);
	}
	/**
	 * 按条件分页查询(邀请奖励纪录表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<InviteRewardRecord> selectInviteRewardRecordByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectInviteRewardRecordCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<InviteRewardRecord> resMap= sqlSession.selectList("inviteRewardRecordBase.select_inviteRewardRecord_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(邀请奖励纪录表)信息
	 * @param id
	 * @return
	 */
	@Override
	public InviteRewardRecord selectInviteRewardRecordBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("inviteRewardRecordBase.select_inviteRewardRecord_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(邀请奖励纪录表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectInviteRewardRecordCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("inviteRewardRecordBase.select_inviteRewardRecord_count", paramMap);
	}
	/**
	 * 往(邀请奖励纪录表)新增一条记录
	 * @param inviteRewardRecord
	 * @return
	 */
	@Override
	public int insertInviteRewardRecord(InviteRewardRecord inviteRewardRecord){
		return sqlSession.insert("inviteRewardRecordBase.insert_inviteRewardRecord",inviteRewardRecord);
	}
	/**
	 * 批量新增(邀请奖励纪录表)信息
	 * @param inviteRewardRecordList
	 * @return
	 */
	@Override
	public int insertInviteRewardRecordBatch(List<InviteRewardRecord> inviteRewardRecordList) {
		return sqlSession.insert("inviteRewardRecordBase.insert_inviteRewardRecord_Batch",inviteRewardRecordList);
	}
	
	/**
	 * 更新(邀请奖励纪录表)信息
	 * @param inviteRewardRecord
	 * @return
	 */
	@Override
	public int updateInviteRewardRecord(InviteRewardRecord inviteRewardRecord){
		return sqlSession.update("inviteRewardRecordBase.update_inviteRewardRecord", inviteRewardRecord);
	}
	/**
	 * 批量更新(邀请奖励纪录表)信息
	 * @param inviteRewardRecordList
	 * @return
	 */
	@Override
	public int updateInviteRewardRecordBatch(List<InviteRewardRecord> inviteRewardRecordList) {
		return sqlSession.update("inviteRewardRecordBase.update_inviteRewardRecord_Batch", inviteRewardRecordList);
	}
	
	/**
	 * 根据序列号删除(邀请奖励纪录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteInviteRewardRecordLogic(java.math.BigInteger id){
		InviteRewardRecord inviteRewardRecord = new InviteRewardRecord();
		inviteRewardRecord.setId(id);
		inviteRewardRecord.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("inviteRewardRecordBase.delete_inviteRewardRecord_Logic",inviteRewardRecord);
	}
	
	/**
	 * 根据Id 批量删除(邀请奖励纪录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteInviteRewardRecordLogicBatch(List<java.math.BigInteger> idList) {
		List<InviteRewardRecord> delList = new java.util.ArrayList<InviteRewardRecord>();
		for(java.math.BigInteger id:idList){
			InviteRewardRecord inviteRewardRecord = new InviteRewardRecord();
			inviteRewardRecord.setId(id);
			inviteRewardRecord.setSys0DelState(RecordState_DELETED);
			delList.add(inviteRewardRecord);
		}
		return sqlSession.update("inviteRewardRecordBase.delete_inviteRewardRecord_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(邀请奖励纪录表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteInviteRewardRecord(java.math.BigInteger id){
//		return sqlSession.delete("inviteRewardRecordBase.delete_inviteRewardRecord", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(邀请奖励纪录表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteInviteRewardRecordBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("inviteRewardRecordBase.delete_inviteRewardRecord_Batch", idList);
//	}
	
	
}
