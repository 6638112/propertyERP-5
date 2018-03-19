package com.cnfantasia.server.domainbase.communityExchangeContent.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communityExchangeContent.entity.CommunityExchangeContent;

/**
 * 描述:(换物信息表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CommunityExchangeContentBaseDao extends AbstractBaseDao implements ICommunityExchangeContentBaseDao{
	/**
	 * 根据条件查询(换物信息表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommunityExchangeContent> selectCommunityExchangeContentByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("communityExchangeContentBase.select_communityExchangeContent",paramMap);
	}
	/**
	 * 按条件分页查询(换物信息表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommunityExchangeContent> selectCommunityExchangeContentByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCommunityExchangeContentCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<CommunityExchangeContent> resMap= sqlSession.selectList("communityExchangeContentBase.select_communityExchangeContent_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(换物信息表)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommunityExchangeContent selectCommunityExchangeContentBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("communityExchangeContentBase.select_communityExchangeContent_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(换物信息表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCommunityExchangeContentCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("communityExchangeContentBase.select_communityExchangeContent_count", paramMap);
	}
	/**
	 * 往(换物信息表)新增一条记录
	 * @param communityExchangeContent
	 * @return
	 */
	@Override
	public int insertCommunityExchangeContent(CommunityExchangeContent communityExchangeContent){
		return sqlSession.insert("communityExchangeContentBase.insert_communityExchangeContent",communityExchangeContent);
	}
	/**
	 * 批量新增(换物信息表)信息
	 * @param communityExchangeContentList
	 * @return
	 */
	@Override
	public int insertCommunityExchangeContentBatch(List<CommunityExchangeContent> communityExchangeContentList) {
		return sqlSession.insert("communityExchangeContentBase.insert_communityExchangeContent_Batch",communityExchangeContentList);
	}
	
	/**
	 * 更新(换物信息表)信息
	 * @param communityExchangeContent
	 * @return
	 */
	@Override
	public int updateCommunityExchangeContent(CommunityExchangeContent communityExchangeContent){
		return sqlSession.update("communityExchangeContentBase.update_communityExchangeContent", communityExchangeContent);
	}
	/**
	 * 批量更新(换物信息表)信息
	 * @param communityExchangeContentList
	 * @return
	 */
	@Override
	public int updateCommunityExchangeContentBatch(List<CommunityExchangeContent> communityExchangeContentList) {
		return sqlSession.update("communityExchangeContentBase.update_communityExchangeContent_Batch", communityExchangeContentList);
	}
	
	/**
	 * 根据序列号删除(换物信息表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommunityExchangeContentLogic(java.math.BigInteger id){
		CommunityExchangeContent communityExchangeContent = new CommunityExchangeContent();
		communityExchangeContent.setId(id);
		communityExchangeContent.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("communityExchangeContentBase.delete_communityExchangeContent_Logic",communityExchangeContent);
	}
	
	/**
	 * 根据Id 批量删除(换物信息表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommunityExchangeContentLogicBatch(List<java.math.BigInteger> idList) {
		List<CommunityExchangeContent> delList = new java.util.ArrayList<CommunityExchangeContent>();
		for(java.math.BigInteger id:idList){
			CommunityExchangeContent communityExchangeContent = new CommunityExchangeContent();
			communityExchangeContent.setId(id);
			communityExchangeContent.setSys0DelState(RecordState_DELETED);
			delList.add(communityExchangeContent);
		}
		return sqlSession.update("communityExchangeContentBase.delete_communityExchangeContent_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(换物信息表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommunityExchangeContent(java.math.BigInteger id){
//		return sqlSession.delete("communityExchangeContentBase.delete_communityExchangeContent", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(换物信息表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommunityExchangeContentBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("communityExchangeContentBase.delete_communityExchangeContent_Batch", idList);
//	}
	
	
}
