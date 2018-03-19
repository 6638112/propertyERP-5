package com.cnfantasia.server.domainbase.communityForumType.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communityForumType.entity.CommunityForumType;

/**
 * 描述:(社区论坛的帖子类别) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CommunityForumTypeBaseDao extends AbstractBaseDao implements ICommunityForumTypeBaseDao{
	/**
	 * 根据条件查询(社区论坛的帖子类别)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommunityForumType> selectCommunityForumTypeByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("communityForumTypeBase.select_communityForumType",paramMap);
	}
	/**
	 * 按条件分页查询(社区论坛的帖子类别)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommunityForumType> selectCommunityForumTypeByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCommunityForumTypeCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<CommunityForumType> resMap= sqlSession.selectList("communityForumTypeBase.select_communityForumType_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(社区论坛的帖子类别)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommunityForumType selectCommunityForumTypeBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("communityForumTypeBase.select_communityForumType_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(社区论坛的帖子类别)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCommunityForumTypeCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("communityForumTypeBase.select_communityForumType_count", paramMap);
	}
	/**
	 * 往(社区论坛的帖子类别)新增一条记录
	 * @param communityForumType
	 * @return
	 */
	@Override
	public int insertCommunityForumType(CommunityForumType communityForumType){
		return sqlSession.insert("communityForumTypeBase.insert_communityForumType",communityForumType);
	}
	/**
	 * 批量新增(社区论坛的帖子类别)信息
	 * @param communityForumTypeList
	 * @return
	 */
	@Override
	public int insertCommunityForumTypeBatch(List<CommunityForumType> communityForumTypeList) {
		return sqlSession.insert("communityForumTypeBase.insert_communityForumType_Batch",communityForumTypeList);
	}
	
	/**
	 * 更新(社区论坛的帖子类别)信息
	 * @param communityForumType
	 * @return
	 */
	@Override
	public int updateCommunityForumType(CommunityForumType communityForumType){
		return sqlSession.update("communityForumTypeBase.update_communityForumType", communityForumType);
	}
	/**
	 * 批量更新(社区论坛的帖子类别)信息
	 * @param communityForumTypeList
	 * @return
	 */
	@Override
	public int updateCommunityForumTypeBatch(List<CommunityForumType> communityForumTypeList) {
		return sqlSession.update("communityForumTypeBase.update_communityForumType_Batch", communityForumTypeList);
	}
	
	/**
	 * 根据序列号删除(社区论坛的帖子类别)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommunityForumTypeLogic(java.math.BigInteger id){
		CommunityForumType communityForumType = new CommunityForumType();
		communityForumType.setId(id);
		communityForumType.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("communityForumTypeBase.delete_communityForumType_Logic",communityForumType);
	}
	
	/**
	 * 根据Id 批量删除(社区论坛的帖子类别)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommunityForumTypeLogicBatch(List<java.math.BigInteger> idList) {
		List<CommunityForumType> delList = new java.util.ArrayList<CommunityForumType>();
		for(java.math.BigInteger id:idList){
			CommunityForumType communityForumType = new CommunityForumType();
			communityForumType.setId(id);
			communityForumType.setSys0DelState(RecordState_DELETED);
			delList.add(communityForumType);
		}
		return sqlSession.update("communityForumTypeBase.delete_communityForumType_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(社区论坛的帖子类别)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommunityForumType(java.math.BigInteger id){
//		return sqlSession.delete("communityForumTypeBase.delete_communityForumType", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(社区论坛的帖子类别)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommunityForumTypeBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("communityForumTypeBase.delete_communityForumType_Batch", idList);
//	}
	
	
}
