package com.cnfantasia.server.domainbase.communityExchangeWanted.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communityExchangeWanted.entity.CommunityExchangeWanted;

/**
 * 描述:(换一换之跪求换) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CommunityExchangeWantedBaseDao extends AbstractBaseDao implements ICommunityExchangeWantedBaseDao{
	/**
	 * 根据条件查询(换一换之跪求换)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommunityExchangeWanted> selectCommunityExchangeWantedByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("communityExchangeWantedBase.select_communityExchangeWanted",paramMap);
	}
	/**
	 * 按条件分页查询(换一换之跪求换)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommunityExchangeWanted> selectCommunityExchangeWantedByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCommunityExchangeWantedCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<CommunityExchangeWanted> resMap= sqlSession.selectList("communityExchangeWantedBase.select_communityExchangeWanted_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(换一换之跪求换)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommunityExchangeWanted selectCommunityExchangeWantedBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("communityExchangeWantedBase.select_communityExchangeWanted_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(换一换之跪求换)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCommunityExchangeWantedCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("communityExchangeWantedBase.select_communityExchangeWanted_count", paramMap);
	}
	/**
	 * 往(换一换之跪求换)新增一条记录
	 * @param communityExchangeWanted
	 * @return
	 */
	@Override
	public int insertCommunityExchangeWanted(CommunityExchangeWanted communityExchangeWanted){
		return sqlSession.insert("communityExchangeWantedBase.insert_communityExchangeWanted",communityExchangeWanted);
	}
	/**
	 * 批量新增(换一换之跪求换)信息
	 * @param communityExchangeWantedList
	 * @return
	 */
	@Override
	public int insertCommunityExchangeWantedBatch(List<CommunityExchangeWanted> communityExchangeWantedList) {
		return sqlSession.insert("communityExchangeWantedBase.insert_communityExchangeWanted_Batch",communityExchangeWantedList);
	}
	
	/**
	 * 更新(换一换之跪求换)信息
	 * @param communityExchangeWanted
	 * @return
	 */
	@Override
	public int updateCommunityExchangeWanted(CommunityExchangeWanted communityExchangeWanted){
		return sqlSession.update("communityExchangeWantedBase.update_communityExchangeWanted", communityExchangeWanted);
	}
	/**
	 * 批量更新(换一换之跪求换)信息
	 * @param communityExchangeWantedList
	 * @return
	 */
	@Override
	public int updateCommunityExchangeWantedBatch(List<CommunityExchangeWanted> communityExchangeWantedList) {
		return sqlSession.update("communityExchangeWantedBase.update_communityExchangeWanted_Batch", communityExchangeWantedList);
	}
	
	/**
	 * 根据序列号删除(换一换之跪求换)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommunityExchangeWantedLogic(java.math.BigInteger id){
		CommunityExchangeWanted communityExchangeWanted = new CommunityExchangeWanted();
		communityExchangeWanted.setId(id);
		communityExchangeWanted.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("communityExchangeWantedBase.delete_communityExchangeWanted_Logic",communityExchangeWanted);
	}
	
	/**
	 * 根据Id 批量删除(换一换之跪求换)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommunityExchangeWantedLogicBatch(List<java.math.BigInteger> idList) {
		List<CommunityExchangeWanted> delList = new java.util.ArrayList<CommunityExchangeWanted>();
		for(java.math.BigInteger id:idList){
			CommunityExchangeWanted communityExchangeWanted = new CommunityExchangeWanted();
			communityExchangeWanted.setId(id);
			communityExchangeWanted.setSys0DelState(RecordState_DELETED);
			delList.add(communityExchangeWanted);
		}
		return sqlSession.update("communityExchangeWantedBase.delete_communityExchangeWanted_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(换一换之跪求换)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommunityExchangeWanted(java.math.BigInteger id){
//		return sqlSession.delete("communityExchangeWantedBase.delete_communityExchangeWanted", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(换一换之跪求换)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommunityExchangeWantedBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("communityExchangeWantedBase.delete_communityExchangeWanted_Batch", idList);
//	}
	
	
}
