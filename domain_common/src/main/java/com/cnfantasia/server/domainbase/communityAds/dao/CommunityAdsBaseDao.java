package com.cnfantasia.server.domainbase.communityAds.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communityAds.entity.CommunityAds;

/**
 * 描述:(街坊广告信息表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CommunityAdsBaseDao extends AbstractBaseDao implements ICommunityAdsBaseDao{
	/**
	 * 根据条件查询(街坊广告信息表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommunityAds> selectCommunityAdsByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("communityAdsBase.select_communityAds",paramMap);
	}
	/**
	 * 按条件分页查询(街坊广告信息表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommunityAds> selectCommunityAdsByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCommunityAdsCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<CommunityAds> resMap= sqlSession.selectList("communityAdsBase.select_communityAds_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(街坊广告信息表)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommunityAds selectCommunityAdsBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("communityAdsBase.select_communityAds_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(街坊广告信息表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCommunityAdsCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("communityAdsBase.select_communityAds_count", paramMap);
	}
	/**
	 * 往(街坊广告信息表)新增一条记录
	 * @param communityAds
	 * @return
	 */
	@Override
	public int insertCommunityAds(CommunityAds communityAds){
		return sqlSession.insert("communityAdsBase.insert_communityAds",communityAds);
	}
	/**
	 * 批量新增(街坊广告信息表)信息
	 * @param communityAdsList
	 * @return
	 */
	@Override
	public int insertCommunityAdsBatch(List<CommunityAds> communityAdsList) {
		return sqlSession.insert("communityAdsBase.insert_communityAds_Batch",communityAdsList);
	}
	
	/**
	 * 更新(街坊广告信息表)信息
	 * @param communityAds
	 * @return
	 */
	@Override
	public int updateCommunityAds(CommunityAds communityAds){
		return sqlSession.update("communityAdsBase.update_communityAds", communityAds);
	}
	/**
	 * 批量更新(街坊广告信息表)信息
	 * @param communityAdsList
	 * @return
	 */
	@Override
	public int updateCommunityAdsBatch(List<CommunityAds> communityAdsList) {
		return sqlSession.update("communityAdsBase.update_communityAds_Batch", communityAdsList);
	}
	
	/**
	 * 根据序列号删除(街坊广告信息表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommunityAdsLogic(java.math.BigInteger id){
		CommunityAds communityAds = new CommunityAds();
		communityAds.setId(id);
		communityAds.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("communityAdsBase.delete_communityAds_Logic",communityAds);
	}
	
	/**
	 * 根据Id 批量删除(街坊广告信息表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommunityAdsLogicBatch(List<java.math.BigInteger> idList) {
		List<CommunityAds> delList = new java.util.ArrayList<CommunityAds>();
		for(java.math.BigInteger id:idList){
			CommunityAds communityAds = new CommunityAds();
			communityAds.setId(id);
			communityAds.setSys0DelState(RecordState_DELETED);
			delList.add(communityAds);
		}
		return sqlSession.update("communityAdsBase.delete_communityAds_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(街坊广告信息表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommunityAds(java.math.BigInteger id){
//		return sqlSession.delete("communityAdsBase.delete_communityAds", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(街坊广告信息表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommunityAdsBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("communityAdsBase.delete_communityAds_Batch", idList);
//	}
	
	
}
