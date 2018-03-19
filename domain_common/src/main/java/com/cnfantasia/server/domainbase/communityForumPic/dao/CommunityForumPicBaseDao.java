package com.cnfantasia.server.domainbase.communityForumPic.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communityForumPic.entity.CommunityForumPic;

/**
 * 描述:(社区论坛的帖子图片) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CommunityForumPicBaseDao extends AbstractBaseDao implements ICommunityForumPicBaseDao{
	/**
	 * 根据条件查询(社区论坛的帖子图片)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommunityForumPic> selectCommunityForumPicByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("communityForumPicBase.select_communityForumPic",paramMap);
	}
	/**
	 * 按条件分页查询(社区论坛的帖子图片)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommunityForumPic> selectCommunityForumPicByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCommunityForumPicCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<CommunityForumPic> resMap= sqlSession.selectList("communityForumPicBase.select_communityForumPic_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(社区论坛的帖子图片)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommunityForumPic selectCommunityForumPicBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("communityForumPicBase.select_communityForumPic_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(社区论坛的帖子图片)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCommunityForumPicCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("communityForumPicBase.select_communityForumPic_count", paramMap);
	}
	/**
	 * 往(社区论坛的帖子图片)新增一条记录
	 * @param communityForumPic
	 * @return
	 */
	@Override
	public int insertCommunityForumPic(CommunityForumPic communityForumPic){
		return sqlSession.insert("communityForumPicBase.insert_communityForumPic",communityForumPic);
	}
	/**
	 * 批量新增(社区论坛的帖子图片)信息
	 * @param communityForumPicList
	 * @return
	 */
	@Override
	public int insertCommunityForumPicBatch(List<CommunityForumPic> communityForumPicList) {
		return sqlSession.insert("communityForumPicBase.insert_communityForumPic_Batch",communityForumPicList);
	}
	
	/**
	 * 更新(社区论坛的帖子图片)信息
	 * @param communityForumPic
	 * @return
	 */
	@Override
	public int updateCommunityForumPic(CommunityForumPic communityForumPic){
		return sqlSession.update("communityForumPicBase.update_communityForumPic", communityForumPic);
	}
	/**
	 * 批量更新(社区论坛的帖子图片)信息
	 * @param communityForumPicList
	 * @return
	 */
	@Override
	public int updateCommunityForumPicBatch(List<CommunityForumPic> communityForumPicList) {
		return sqlSession.update("communityForumPicBase.update_communityForumPic_Batch", communityForumPicList);
	}
	
	/**
	 * 根据序列号删除(社区论坛的帖子图片)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommunityForumPicLogic(java.math.BigInteger id){
		CommunityForumPic communityForumPic = new CommunityForumPic();
		communityForumPic.setId(id);
		communityForumPic.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("communityForumPicBase.delete_communityForumPic_Logic",communityForumPic);
	}
	
	/**
	 * 根据Id 批量删除(社区论坛的帖子图片)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommunityForumPicLogicBatch(List<java.math.BigInteger> idList) {
		List<CommunityForumPic> delList = new java.util.ArrayList<CommunityForumPic>();
		for(java.math.BigInteger id:idList){
			CommunityForumPic communityForumPic = new CommunityForumPic();
			communityForumPic.setId(id);
			communityForumPic.setSys0DelState(RecordState_DELETED);
			delList.add(communityForumPic);
		}
		return sqlSession.update("communityForumPicBase.delete_communityForumPic_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(社区论坛的帖子图片)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommunityForumPic(java.math.BigInteger id){
//		return sqlSession.delete("communityForumPicBase.delete_communityForumPic", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(社区论坛的帖子图片)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommunityForumPicBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("communityForumPicBase.delete_communityForumPic_Batch", idList);
//	}
	
	
}
