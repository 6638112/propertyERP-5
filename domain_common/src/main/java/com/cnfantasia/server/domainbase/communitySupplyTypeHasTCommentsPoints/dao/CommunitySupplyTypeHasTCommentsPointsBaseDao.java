package com.cnfantasia.server.domainbase.communitySupplyTypeHasTCommentsPoints.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.communitySupplyTypeHasTCommentsPoints.entity.CommunitySupplyTypeHasTCommentsPoints;

/**
 * 描述:(商家类别支持哪些评分) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CommunitySupplyTypeHasTCommentsPointsBaseDao extends AbstractBaseDao implements ICommunitySupplyTypeHasTCommentsPointsBaseDao{
	/**
	 * 根据条件查询(商家类别支持哪些评分)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommunitySupplyTypeHasTCommentsPoints> selectCommunitySupplyTypeHasTCommentsPointsByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("communitySupplyTypeHasTCommentsPointsBase.select_communitySupplyTypeHasTCommentsPoints",paramMap);
	}
	/**
	 * 按条件分页查询(商家类别支持哪些评分)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommunitySupplyTypeHasTCommentsPoints> selectCommunitySupplyTypeHasTCommentsPointsByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCommunitySupplyTypeHasTCommentsPointsCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<CommunitySupplyTypeHasTCommentsPoints> resMap= sqlSession.selectList("communitySupplyTypeHasTCommentsPointsBase.select_communitySupplyTypeHasTCommentsPoints_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(商家类别支持哪些评分)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommunitySupplyTypeHasTCommentsPoints selectCommunitySupplyTypeHasTCommentsPointsBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("communitySupplyTypeHasTCommentsPointsBase.select_communitySupplyTypeHasTCommentsPoints_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(商家类别支持哪些评分)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCommunitySupplyTypeHasTCommentsPointsCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("communitySupplyTypeHasTCommentsPointsBase.select_communitySupplyTypeHasTCommentsPoints_count", paramMap);
	}
	/**
	 * 往(商家类别支持哪些评分)新增一条记录
	 * @param communitySupplyTypeHasTCommentsPoints
	 * @return
	 */
	@Override
	public int insertCommunitySupplyTypeHasTCommentsPoints(CommunitySupplyTypeHasTCommentsPoints communitySupplyTypeHasTCommentsPoints){
		return sqlSession.insert("communitySupplyTypeHasTCommentsPointsBase.insert_communitySupplyTypeHasTCommentsPoints",communitySupplyTypeHasTCommentsPoints);
	}
	/**
	 * 批量新增(商家类别支持哪些评分)信息
	 * @param communitySupplyTypeHasTCommentsPointsList
	 * @return
	 */
	@Override
	public int insertCommunitySupplyTypeHasTCommentsPointsBatch(List<CommunitySupplyTypeHasTCommentsPoints> communitySupplyTypeHasTCommentsPointsList) {
		return sqlSession.insert("communitySupplyTypeHasTCommentsPointsBase.insert_communitySupplyTypeHasTCommentsPoints_Batch",communitySupplyTypeHasTCommentsPointsList);
	}
	
	/**
	 * 更新(商家类别支持哪些评分)信息
	 * @param communitySupplyTypeHasTCommentsPoints
	 * @return
	 */
	@Override
	public int updateCommunitySupplyTypeHasTCommentsPoints(CommunitySupplyTypeHasTCommentsPoints communitySupplyTypeHasTCommentsPoints){
		return sqlSession.update("communitySupplyTypeHasTCommentsPointsBase.update_communitySupplyTypeHasTCommentsPoints", communitySupplyTypeHasTCommentsPoints);
	}
	/**
	 * 批量更新(商家类别支持哪些评分)信息
	 * @param communitySupplyTypeHasTCommentsPointsList
	 * @return
	 */
	@Override
	public int updateCommunitySupplyTypeHasTCommentsPointsBatch(List<CommunitySupplyTypeHasTCommentsPoints> communitySupplyTypeHasTCommentsPointsList) {
		return sqlSession.update("communitySupplyTypeHasTCommentsPointsBase.update_communitySupplyTypeHasTCommentsPoints_Batch", communitySupplyTypeHasTCommentsPointsList);
	}
	
	/**
	 * 根据序列号删除(商家类别支持哪些评分)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommunitySupplyTypeHasTCommentsPointsLogic(java.math.BigInteger id){
		CommunitySupplyTypeHasTCommentsPoints communitySupplyTypeHasTCommentsPoints = new CommunitySupplyTypeHasTCommentsPoints();
		communitySupplyTypeHasTCommentsPoints.setId(id);
		communitySupplyTypeHasTCommentsPoints.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("communitySupplyTypeHasTCommentsPointsBase.delete_communitySupplyTypeHasTCommentsPoints_Logic",communitySupplyTypeHasTCommentsPoints);
	}
	
	/**
	 * 根据Id 批量删除(商家类别支持哪些评分)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommunitySupplyTypeHasTCommentsPointsLogicBatch(List<java.math.BigInteger> idList) {
		List<CommunitySupplyTypeHasTCommentsPoints> delList = new java.util.ArrayList<CommunitySupplyTypeHasTCommentsPoints>();
		for(java.math.BigInteger id:idList){
			CommunitySupplyTypeHasTCommentsPoints communitySupplyTypeHasTCommentsPoints = new CommunitySupplyTypeHasTCommentsPoints();
			communitySupplyTypeHasTCommentsPoints.setId(id);
			communitySupplyTypeHasTCommentsPoints.setSys0DelState(RecordState_DELETED);
			delList.add(communitySupplyTypeHasTCommentsPoints);
		}
		return sqlSession.update("communitySupplyTypeHasTCommentsPointsBase.delete_communitySupplyTypeHasTCommentsPoints_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(商家类别支持哪些评分)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommunitySupplyTypeHasTCommentsPoints(java.math.BigInteger id){
//		return sqlSession.delete("communitySupplyTypeHasTCommentsPointsBase.delete_communitySupplyTypeHasTCommentsPoints", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(商家类别支持哪些评分)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommunitySupplyTypeHasTCommentsPointsBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("communitySupplyTypeHasTCommentsPointsBase.delete_communitySupplyTypeHasTCommentsPoints_Batch", idList);
//	}
	
	
}
