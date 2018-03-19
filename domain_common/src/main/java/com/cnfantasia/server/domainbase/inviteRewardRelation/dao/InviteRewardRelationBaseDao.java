package com.cnfantasia.server.domainbase.inviteRewardRelation.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.inviteRewardRelation.entity.InviteRewardRelation;

/**
 * 描述:(邀请奖励关系纪录表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class InviteRewardRelationBaseDao extends AbstractBaseDao implements IInviteRewardRelationBaseDao{
	/**
	 * 根据条件查询(邀请奖励关系纪录表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<InviteRewardRelation> selectInviteRewardRelationByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("inviteRewardRelationBase.select_inviteRewardRelation",paramMap);
	}
	/**
	 * 按条件分页查询(邀请奖励关系纪录表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<InviteRewardRelation> selectInviteRewardRelationByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectInviteRewardRelationCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<InviteRewardRelation> resMap= sqlSession.selectList("inviteRewardRelationBase.select_inviteRewardRelation_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(邀请奖励关系纪录表)信息
	 * @param id
	 * @return
	 */
	@Override
	public InviteRewardRelation selectInviteRewardRelationBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("inviteRewardRelationBase.select_inviteRewardRelation_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(邀请奖励关系纪录表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectInviteRewardRelationCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("inviteRewardRelationBase.select_inviteRewardRelation_count", paramMap);
	}
	/**
	 * 往(邀请奖励关系纪录表)新增一条记录
	 * @param inviteRewardRelation
	 * @return
	 */
	@Override
	public int insertInviteRewardRelation(InviteRewardRelation inviteRewardRelation){
		return sqlSession.insert("inviteRewardRelationBase.insert_inviteRewardRelation",inviteRewardRelation);
	}
	/**
	 * 批量新增(邀请奖励关系纪录表)信息
	 * @param inviteRewardRelationList
	 * @return
	 */
	@Override
	public int insertInviteRewardRelationBatch(List<InviteRewardRelation> inviteRewardRelationList) {
		return sqlSession.insert("inviteRewardRelationBase.insert_inviteRewardRelation_Batch",inviteRewardRelationList);
	}
	
	/**
	 * 更新(邀请奖励关系纪录表)信息
	 * @param inviteRewardRelation
	 * @return
	 */
	@Override
	public int updateInviteRewardRelation(InviteRewardRelation inviteRewardRelation){
		return sqlSession.update("inviteRewardRelationBase.update_inviteRewardRelation", inviteRewardRelation);
	}
	/**
	 * 批量更新(邀请奖励关系纪录表)信息
	 * @param inviteRewardRelationList
	 * @return
	 */
	@Override
	public int updateInviteRewardRelationBatch(List<InviteRewardRelation> inviteRewardRelationList) {
		return sqlSession.update("inviteRewardRelationBase.update_inviteRewardRelation_Batch", inviteRewardRelationList);
	}
	
	/**
	 * 根据序列号删除(邀请奖励关系纪录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteInviteRewardRelationLogic(java.math.BigInteger id){
		InviteRewardRelation inviteRewardRelation = new InviteRewardRelation();
		inviteRewardRelation.setId(id);
		inviteRewardRelation.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("inviteRewardRelationBase.delete_inviteRewardRelation_Logic",inviteRewardRelation);
	}
	
	/**
	 * 根据Id 批量删除(邀请奖励关系纪录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteInviteRewardRelationLogicBatch(List<java.math.BigInteger> idList) {
		List<InviteRewardRelation> delList = new java.util.ArrayList<InviteRewardRelation>();
		for(java.math.BigInteger id:idList){
			InviteRewardRelation inviteRewardRelation = new InviteRewardRelation();
			inviteRewardRelation.setId(id);
			inviteRewardRelation.setSys0DelState(RecordState_DELETED);
			delList.add(inviteRewardRelation);
		}
		return sqlSession.update("inviteRewardRelationBase.delete_inviteRewardRelation_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(邀请奖励关系纪录表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteInviteRewardRelation(java.math.BigInteger id){
//		return sqlSession.delete("inviteRewardRelationBase.delete_inviteRewardRelation", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(邀请奖励关系纪录表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteInviteRewardRelationBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("inviteRewardRelationBase.delete_inviteRewardRelation_Batch", idList);
//	}
	
	
}
