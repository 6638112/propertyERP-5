package com.cnfantasia.server.domainbase.communityExchangeRelation.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communityExchangeRelation.entity.CommunityExchangeRelation;

/**
 * 描述:(换物关系表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CommunityExchangeRelationBaseDao extends AbstractBaseDao implements ICommunityExchangeRelationBaseDao{
	/**
	 * 根据条件查询(换物关系表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommunityExchangeRelation> selectCommunityExchangeRelationByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("communityExchangeRelationBase.select_communityExchangeRelation",paramMap);
	}
	/**
	 * 按条件分页查询(换物关系表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommunityExchangeRelation> selectCommunityExchangeRelationByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCommunityExchangeRelationCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<CommunityExchangeRelation> resMap= sqlSession.selectList("communityExchangeRelationBase.select_communityExchangeRelation_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(换物关系表)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommunityExchangeRelation selectCommunityExchangeRelationBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("communityExchangeRelationBase.select_communityExchangeRelation_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(换物关系表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCommunityExchangeRelationCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("communityExchangeRelationBase.select_communityExchangeRelation_count", paramMap);
	}
	/**
	 * 往(换物关系表)新增一条记录
	 * @param communityExchangeRelation
	 * @return
	 */
	@Override
	public int insertCommunityExchangeRelation(CommunityExchangeRelation communityExchangeRelation){
		return sqlSession.insert("communityExchangeRelationBase.insert_communityExchangeRelation",communityExchangeRelation);
	}
	/**
	 * 批量新增(换物关系表)信息
	 * @param communityExchangeRelationList
	 * @return
	 */
	@Override
	public int insertCommunityExchangeRelationBatch(List<CommunityExchangeRelation> communityExchangeRelationList) {
		return sqlSession.insert("communityExchangeRelationBase.insert_communityExchangeRelation_Batch",communityExchangeRelationList);
	}
	
	/**
	 * 更新(换物关系表)信息
	 * @param communityExchangeRelation
	 * @return
	 */
	@Override
	public int updateCommunityExchangeRelation(CommunityExchangeRelation communityExchangeRelation){
		return sqlSession.update("communityExchangeRelationBase.update_communityExchangeRelation", communityExchangeRelation);
	}
	/**
	 * 批量更新(换物关系表)信息
	 * @param communityExchangeRelationList
	 * @return
	 */
	@Override
	public int updateCommunityExchangeRelationBatch(List<CommunityExchangeRelation> communityExchangeRelationList) {
		return sqlSession.update("communityExchangeRelationBase.update_communityExchangeRelation_Batch", communityExchangeRelationList);
	}
	
	/**
	 * 根据序列号删除(换物关系表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommunityExchangeRelationLogic(java.math.BigInteger id){
		CommunityExchangeRelation communityExchangeRelation = new CommunityExchangeRelation();
		communityExchangeRelation.setId(id);
		communityExchangeRelation.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("communityExchangeRelationBase.delete_communityExchangeRelation_Logic",communityExchangeRelation);
	}
	
	/**
	 * 根据Id 批量删除(换物关系表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommunityExchangeRelationLogicBatch(List<java.math.BigInteger> idList) {
		List<CommunityExchangeRelation> delList = new java.util.ArrayList<CommunityExchangeRelation>();
		for(java.math.BigInteger id:idList){
			CommunityExchangeRelation communityExchangeRelation = new CommunityExchangeRelation();
			communityExchangeRelation.setId(id);
			communityExchangeRelation.setSys0DelState(RecordState_DELETED);
			delList.add(communityExchangeRelation);
		}
		return sqlSession.update("communityExchangeRelationBase.delete_communityExchangeRelation_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(换物关系表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommunityExchangeRelation(java.math.BigInteger id){
//		return sqlSession.delete("communityExchangeRelationBase.delete_communityExchangeRelation", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(换物关系表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommunityExchangeRelationBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("communityExchangeRelationBase.delete_communityExchangeRelation_Batch", idList);
//	}
	
	
}
