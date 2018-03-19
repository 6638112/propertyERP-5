package com.cnfantasia.server.domainbase.communityForumContent.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communityForumContent.entity.CommunityForumContent;

/**
 * 描述:(社区论坛的帖子内容) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CommunityForumContentBaseDao extends AbstractBaseDao implements ICommunityForumContentBaseDao{
	/**
	 * 根据条件查询(社区论坛的帖子内容)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommunityForumContent> selectCommunityForumContentByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("communityForumContentBase.select_communityForumContent",paramMap);
	}
	/**
	 * 按条件分页查询(社区论坛的帖子内容)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommunityForumContent> selectCommunityForumContentByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCommunityForumContentCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<CommunityForumContent> resMap= sqlSession.selectList("communityForumContentBase.select_communityForumContent_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(社区论坛的帖子内容)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommunityForumContent selectCommunityForumContentBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("communityForumContentBase.select_communityForumContent_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(社区论坛的帖子内容)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCommunityForumContentCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("communityForumContentBase.select_communityForumContent_count", paramMap);
	}
	/**
	 * 往(社区论坛的帖子内容)新增一条记录
	 * @param communityForumContent
	 * @return
	 */
	@Override
	public int insertCommunityForumContent(CommunityForumContent communityForumContent){
		return sqlSession.insert("communityForumContentBase.insert_communityForumContent",communityForumContent);
	}
	/**
	 * 批量新增(社区论坛的帖子内容)信息
	 * @param communityForumContentList
	 * @return
	 */
	@Override
	public int insertCommunityForumContentBatch(List<CommunityForumContent> communityForumContentList) {
		return sqlSession.insert("communityForumContentBase.insert_communityForumContent_Batch",communityForumContentList);
	}
	
	/**
	 * 更新(社区论坛的帖子内容)信息
	 * @param communityForumContent
	 * @return
	 */
	@Override
	public int updateCommunityForumContent(CommunityForumContent communityForumContent){
		return sqlSession.update("communityForumContentBase.update_communityForumContent", communityForumContent);
	}
	/**
	 * 批量更新(社区论坛的帖子内容)信息
	 * @param communityForumContentList
	 * @return
	 */
	@Override
	public int updateCommunityForumContentBatch(List<CommunityForumContent> communityForumContentList) {
		return sqlSession.update("communityForumContentBase.update_communityForumContent_Batch", communityForumContentList);
	}
	
	/**
	 * 根据序列号删除(社区论坛的帖子内容)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommunityForumContentLogic(java.math.BigInteger id){
		CommunityForumContent communityForumContent = new CommunityForumContent();
		communityForumContent.setId(id);
		communityForumContent.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("communityForumContentBase.delete_communityForumContent_Logic",communityForumContent);
	}
	
	/**
	 * 根据Id 批量删除(社区论坛的帖子内容)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommunityForumContentLogicBatch(List<java.math.BigInteger> idList) {
		List<CommunityForumContent> delList = new java.util.ArrayList<CommunityForumContent>();
		for(java.math.BigInteger id:idList){
			CommunityForumContent communityForumContent = new CommunityForumContent();
			communityForumContent.setId(id);
			communityForumContent.setSys0DelState(RecordState_DELETED);
			delList.add(communityForumContent);
		}
		return sqlSession.update("communityForumContentBase.delete_communityForumContent_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(社区论坛的帖子内容)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommunityForumContent(java.math.BigInteger id){
//		return sqlSession.delete("communityForumContentBase.delete_communityForumContent", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(社区论坛的帖子内容)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommunityForumContentBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("communityForumContentBase.delete_communityForumContent_Batch", idList);
//	}
	
	
}
