package com.cnfantasia.server.domainbase.communityPinyipinContent.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communityPinyipinContent.entity.CommunityPinyipinContent;

/**
 * 描述:(拼一拼内容表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CommunityPinyipinContentBaseDao extends AbstractBaseDao implements ICommunityPinyipinContentBaseDao{
	/**
	 * 根据条件查询(拼一拼内容表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommunityPinyipinContent> selectCommunityPinyipinContentByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("communityPinyipinContentBase.select_communityPinyipinContent",paramMap);
	}
	/**
	 * 按条件分页查询(拼一拼内容表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommunityPinyipinContent> selectCommunityPinyipinContentByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCommunityPinyipinContentCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<CommunityPinyipinContent> resMap= sqlSession.selectList("communityPinyipinContentBase.select_communityPinyipinContent_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(拼一拼内容表)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommunityPinyipinContent selectCommunityPinyipinContentBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("communityPinyipinContentBase.select_communityPinyipinContent_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(拼一拼内容表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCommunityPinyipinContentCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("communityPinyipinContentBase.select_communityPinyipinContent_count", paramMap);
	}
	/**
	 * 往(拼一拼内容表)新增一条记录
	 * @param communityPinyipinContent
	 * @return
	 */
	@Override
	public int insertCommunityPinyipinContent(CommunityPinyipinContent communityPinyipinContent){
		return sqlSession.insert("communityPinyipinContentBase.insert_communityPinyipinContent",communityPinyipinContent);
	}
	/**
	 * 批量新增(拼一拼内容表)信息
	 * @param communityPinyipinContentList
	 * @return
	 */
	@Override
	public int insertCommunityPinyipinContentBatch(List<CommunityPinyipinContent> communityPinyipinContentList) {
		return sqlSession.insert("communityPinyipinContentBase.insert_communityPinyipinContent_Batch",communityPinyipinContentList);
	}
	
	/**
	 * 更新(拼一拼内容表)信息
	 * @param communityPinyipinContent
	 * @return
	 */
	@Override
	public int updateCommunityPinyipinContent(CommunityPinyipinContent communityPinyipinContent){
		return sqlSession.update("communityPinyipinContentBase.update_communityPinyipinContent", communityPinyipinContent);
	}
	/**
	 * 批量更新(拼一拼内容表)信息
	 * @param communityPinyipinContentList
	 * @return
	 */
	@Override
	public int updateCommunityPinyipinContentBatch(List<CommunityPinyipinContent> communityPinyipinContentList) {
		return sqlSession.update("communityPinyipinContentBase.update_communityPinyipinContent_Batch", communityPinyipinContentList);
	}
	
	/**
	 * 根据序列号删除(拼一拼内容表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommunityPinyipinContentLogic(java.math.BigInteger id){
		CommunityPinyipinContent communityPinyipinContent = new CommunityPinyipinContent();
		communityPinyipinContent.setId(id);
		communityPinyipinContent.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("communityPinyipinContentBase.delete_communityPinyipinContent_Logic",communityPinyipinContent);
	}
	
	/**
	 * 根据Id 批量删除(拼一拼内容表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommunityPinyipinContentLogicBatch(List<java.math.BigInteger> idList) {
		List<CommunityPinyipinContent> delList = new java.util.ArrayList<CommunityPinyipinContent>();
		for(java.math.BigInteger id:idList){
			CommunityPinyipinContent communityPinyipinContent = new CommunityPinyipinContent();
			communityPinyipinContent.setId(id);
			communityPinyipinContent.setSys0DelState(RecordState_DELETED);
			delList.add(communityPinyipinContent);
		}
		return sqlSession.update("communityPinyipinContentBase.delete_communityPinyipinContent_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(拼一拼内容表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommunityPinyipinContent(java.math.BigInteger id){
//		return sqlSession.delete("communityPinyipinContentBase.delete_communityPinyipinContent", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(拼一拼内容表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommunityPinyipinContentBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("communityPinyipinContentBase.delete_communityPinyipinContent_Batch", idList);
//	}
	
	
}
