package com.cnfantasia.server.domainbase.communitySupplyExtendsRelationship.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communitySupplyExtendsRelationship.entity.CommunitySupplyExtendsRelationship;

/**
 * 描述:(社区商家拓展对应信息) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CommunitySupplyExtendsRelationshipBaseDao extends AbstractBaseDao implements ICommunitySupplyExtendsRelationshipBaseDao{
	/**
	 * 根据条件查询(社区商家拓展对应信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommunitySupplyExtendsRelationship> selectCommunitySupplyExtendsRelationshipByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("communitySupplyExtendsRelationshipBase.select_communitySupplyExtendsRelationship",paramMap);
	}
	/**
	 * 按条件分页查询(社区商家拓展对应信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommunitySupplyExtendsRelationship> selectCommunitySupplyExtendsRelationshipByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCommunitySupplyExtendsRelationshipCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<CommunitySupplyExtendsRelationship> resMap= sqlSession.selectList("communitySupplyExtendsRelationshipBase.select_communitySupplyExtendsRelationship_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(社区商家拓展对应信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommunitySupplyExtendsRelationship selectCommunitySupplyExtendsRelationshipBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("communitySupplyExtendsRelationshipBase.select_communitySupplyExtendsRelationship_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(社区商家拓展对应信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCommunitySupplyExtendsRelationshipCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("communitySupplyExtendsRelationshipBase.select_communitySupplyExtendsRelationship_count", paramMap);
	}
	/**
	 * 往(社区商家拓展对应信息)新增一条记录
	 * @param communitySupplyExtendsRelationship
	 * @return
	 */
	@Override
	public int insertCommunitySupplyExtendsRelationship(CommunitySupplyExtendsRelationship communitySupplyExtendsRelationship){
		return sqlSession.insert("communitySupplyExtendsRelationshipBase.insert_communitySupplyExtendsRelationship",communitySupplyExtendsRelationship);
	}
	/**
	 * 批量新增(社区商家拓展对应信息)信息
	 * @param communitySupplyExtendsRelationshipList
	 * @return
	 */
	@Override
	public int insertCommunitySupplyExtendsRelationshipBatch(List<CommunitySupplyExtendsRelationship> communitySupplyExtendsRelationshipList) {
		return sqlSession.insert("communitySupplyExtendsRelationshipBase.insert_communitySupplyExtendsRelationship_Batch",communitySupplyExtendsRelationshipList);
	}
	
	/**
	 * 更新(社区商家拓展对应信息)信息
	 * @param communitySupplyExtendsRelationship
	 * @return
	 */
	@Override
	public int updateCommunitySupplyExtendsRelationship(CommunitySupplyExtendsRelationship communitySupplyExtendsRelationship){
		return sqlSession.update("communitySupplyExtendsRelationshipBase.update_communitySupplyExtendsRelationship", communitySupplyExtendsRelationship);
	}
	/**
	 * 批量更新(社区商家拓展对应信息)信息
	 * @param communitySupplyExtendsRelationshipList
	 * @return
	 */
	@Override
	public int updateCommunitySupplyExtendsRelationshipBatch(List<CommunitySupplyExtendsRelationship> communitySupplyExtendsRelationshipList) {
		return sqlSession.update("communitySupplyExtendsRelationshipBase.update_communitySupplyExtendsRelationship_Batch", communitySupplyExtendsRelationshipList);
	}
	
	/**
	 * 根据序列号删除(社区商家拓展对应信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommunitySupplyExtendsRelationshipLogic(java.math.BigInteger id){
		CommunitySupplyExtendsRelationship communitySupplyExtendsRelationship = new CommunitySupplyExtendsRelationship();
		communitySupplyExtendsRelationship.setId(id);
		communitySupplyExtendsRelationship.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("communitySupplyExtendsRelationshipBase.delete_communitySupplyExtendsRelationship_Logic",communitySupplyExtendsRelationship);
	}
	
	/**
	 * 根据Id 批量删除(社区商家拓展对应信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommunitySupplyExtendsRelationshipLogicBatch(List<java.math.BigInteger> idList) {
		List<CommunitySupplyExtendsRelationship> delList = new java.util.ArrayList<CommunitySupplyExtendsRelationship>();
		for(java.math.BigInteger id:idList){
			CommunitySupplyExtendsRelationship communitySupplyExtendsRelationship = new CommunitySupplyExtendsRelationship();
			communitySupplyExtendsRelationship.setId(id);
			communitySupplyExtendsRelationship.setSys0DelState(RecordState_DELETED);
			delList.add(communitySupplyExtendsRelationship);
		}
		return sqlSession.update("communitySupplyExtendsRelationshipBase.delete_communitySupplyExtendsRelationship_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(社区商家拓展对应信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommunitySupplyExtendsRelationship(java.math.BigInteger id){
//		return sqlSession.delete("communitySupplyExtendsRelationshipBase.delete_communitySupplyExtendsRelationship", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(社区商家拓展对应信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommunitySupplyExtendsRelationshipBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("communitySupplyExtendsRelationshipBase.delete_communitySupplyExtendsRelationship_Batch", idList);
//	}
	
	
}
