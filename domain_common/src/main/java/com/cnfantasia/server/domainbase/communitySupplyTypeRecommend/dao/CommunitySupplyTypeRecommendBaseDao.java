package com.cnfantasia.server.domainbase.communitySupplyTypeRecommend.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communitySupplyTypeRecommend.entity.CommunitySupplyTypeRecommend;

/**
 * 描述:(推荐的商家类别) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CommunitySupplyTypeRecommendBaseDao extends AbstractBaseDao implements ICommunitySupplyTypeRecommendBaseDao{
	/**
	 * 根据条件查询(推荐的商家类别)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommunitySupplyTypeRecommend> selectCommunitySupplyTypeRecommendByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("communitySupplyTypeRecommendBase.select_communitySupplyTypeRecommend",paramMap);
	}
	/**
	 * 按条件分页查询(推荐的商家类别)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommunitySupplyTypeRecommend> selectCommunitySupplyTypeRecommendByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCommunitySupplyTypeRecommendCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<CommunitySupplyTypeRecommend> resMap= sqlSession.selectList("communitySupplyTypeRecommendBase.select_communitySupplyTypeRecommend_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(推荐的商家类别)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommunitySupplyTypeRecommend selectCommunitySupplyTypeRecommendBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("communitySupplyTypeRecommendBase.select_communitySupplyTypeRecommend_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(推荐的商家类别)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCommunitySupplyTypeRecommendCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("communitySupplyTypeRecommendBase.select_communitySupplyTypeRecommend_count", paramMap);
	}
	/**
	 * 往(推荐的商家类别)新增一条记录
	 * @param communitySupplyTypeRecommend
	 * @return
	 */
	@Override
	public int insertCommunitySupplyTypeRecommend(CommunitySupplyTypeRecommend communitySupplyTypeRecommend){
		return sqlSession.insert("communitySupplyTypeRecommendBase.insert_communitySupplyTypeRecommend",communitySupplyTypeRecommend);
	}
	/**
	 * 批量新增(推荐的商家类别)信息
	 * @param communitySupplyTypeRecommendList
	 * @return
	 */
	@Override
	public int insertCommunitySupplyTypeRecommendBatch(List<CommunitySupplyTypeRecommend> communitySupplyTypeRecommendList) {
		return sqlSession.insert("communitySupplyTypeRecommendBase.insert_communitySupplyTypeRecommend_Batch",communitySupplyTypeRecommendList);
	}
	
	/**
	 * 更新(推荐的商家类别)信息
	 * @param communitySupplyTypeRecommend
	 * @return
	 */
	@Override
	public int updateCommunitySupplyTypeRecommend(CommunitySupplyTypeRecommend communitySupplyTypeRecommend){
		return sqlSession.update("communitySupplyTypeRecommendBase.update_communitySupplyTypeRecommend", communitySupplyTypeRecommend);
	}
	/**
	 * 批量更新(推荐的商家类别)信息
	 * @param communitySupplyTypeRecommendList
	 * @return
	 */
	@Override
	public int updateCommunitySupplyTypeRecommendBatch(List<CommunitySupplyTypeRecommend> communitySupplyTypeRecommendList) {
		return sqlSession.update("communitySupplyTypeRecommendBase.update_communitySupplyTypeRecommend_Batch", communitySupplyTypeRecommendList);
	}
	
	/**
	 * 根据序列号删除(推荐的商家类别)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommunitySupplyTypeRecommendLogic(java.math.BigInteger id){
		CommunitySupplyTypeRecommend communitySupplyTypeRecommend = new CommunitySupplyTypeRecommend();
		communitySupplyTypeRecommend.setId(id);
		communitySupplyTypeRecommend.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("communitySupplyTypeRecommendBase.delete_communitySupplyTypeRecommend_Logic",communitySupplyTypeRecommend);
	}
	
	/**
	 * 根据Id 批量删除(推荐的商家类别)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommunitySupplyTypeRecommendLogicBatch(List<java.math.BigInteger> idList) {
		List<CommunitySupplyTypeRecommend> delList = new java.util.ArrayList<CommunitySupplyTypeRecommend>();
		for(java.math.BigInteger id:idList){
			CommunitySupplyTypeRecommend communitySupplyTypeRecommend = new CommunitySupplyTypeRecommend();
			communitySupplyTypeRecommend.setId(id);
			communitySupplyTypeRecommend.setSys0DelState(RecordState_DELETED);
			delList.add(communitySupplyTypeRecommend);
		}
		return sqlSession.update("communitySupplyTypeRecommendBase.delete_communitySupplyTypeRecommend_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(推荐的商家类别)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommunitySupplyTypeRecommend(java.math.BigInteger id){
//		return sqlSession.delete("communitySupplyTypeRecommendBase.delete_communitySupplyTypeRecommend", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(推荐的商家类别)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommunitySupplyTypeRecommendBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("communitySupplyTypeRecommendBase.delete_communitySupplyTypeRecommend_Batch", idList);
//	}
	
	
}
