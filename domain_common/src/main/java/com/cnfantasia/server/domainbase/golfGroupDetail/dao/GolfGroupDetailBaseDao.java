package com.cnfantasia.server.domainbase.golfGroupDetail.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.golfGroupDetail.entity.GolfGroupDetail;

/**
 * 描述:(高尔夫组团详细表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class GolfGroupDetailBaseDao extends AbstractBaseDao implements IGolfGroupDetailBaseDao{
	/**
	 * 根据条件查询(高尔夫组团详细表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<GolfGroupDetail> selectGolfGroupDetailByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("golfGroupDetailBase.select_golfGroupDetail",paramMap);
	}
	/**
	 * 按条件分页查询(高尔夫组团详细表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<GolfGroupDetail> selectGolfGroupDetailByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectGolfGroupDetailCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<GolfGroupDetail> resMap= sqlSession.selectList("golfGroupDetailBase.select_golfGroupDetail_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(高尔夫组团详细表)信息
	 * @param id
	 * @return
	 */
	@Override
	public GolfGroupDetail selectGolfGroupDetailBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("golfGroupDetailBase.select_golfGroupDetail_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(高尔夫组团详细表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectGolfGroupDetailCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("golfGroupDetailBase.select_golfGroupDetail_count", paramMap);
	}
	/**
	 * 往(高尔夫组团详细表)新增一条记录
	 * @param golfGroupDetail
	 * @return
	 */
	@Override
	public int insertGolfGroupDetail(GolfGroupDetail golfGroupDetail){
		return sqlSession.insert("golfGroupDetailBase.insert_golfGroupDetail",golfGroupDetail);
	}
	/**
	 * 批量新增(高尔夫组团详细表)信息
	 * @param golfGroupDetailList
	 * @return
	 */
	@Override
	public int insertGolfGroupDetailBatch(List<GolfGroupDetail> golfGroupDetailList) {
		return sqlSession.insert("golfGroupDetailBase.insert_golfGroupDetail_Batch",golfGroupDetailList);
	}
	
	/**
	 * 更新(高尔夫组团详细表)信息
	 * @param golfGroupDetail
	 * @return
	 */
	@Override
	public int updateGolfGroupDetail(GolfGroupDetail golfGroupDetail){
		return sqlSession.update("golfGroupDetailBase.update_golfGroupDetail", golfGroupDetail);
	}
	/**
	 * 批量更新(高尔夫组团详细表)信息
	 * @param golfGroupDetailList
	 * @return
	 */
	@Override
	public int updateGolfGroupDetailBatch(List<GolfGroupDetail> golfGroupDetailList) {
		return sqlSession.update("golfGroupDetailBase.update_golfGroupDetail_Batch", golfGroupDetailList);
	}
	
	/**
	 * 根据序列号删除(高尔夫组团详细表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteGolfGroupDetailLogic(java.math.BigInteger id){
		GolfGroupDetail golfGroupDetail = new GolfGroupDetail();
		golfGroupDetail.setId(id);
		golfGroupDetail.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("golfGroupDetailBase.delete_golfGroupDetail_Logic",golfGroupDetail);
	}
	
	/**
	 * 根据Id 批量删除(高尔夫组团详细表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteGolfGroupDetailLogicBatch(List<java.math.BigInteger> idList) {
		List<GolfGroupDetail> delList = new java.util.ArrayList<GolfGroupDetail>();
		for(java.math.BigInteger id:idList){
			GolfGroupDetail golfGroupDetail = new GolfGroupDetail();
			golfGroupDetail.setId(id);
			golfGroupDetail.setSys0DelState(RecordState_DELETED);
			delList.add(golfGroupDetail);
		}
		return sqlSession.update("golfGroupDetailBase.delete_golfGroupDetail_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(高尔夫组团详细表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteGolfGroupDetail(java.math.BigInteger id){
//		return sqlSession.delete("golfGroupDetailBase.delete_golfGroupDetail", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(高尔夫组团详细表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteGolfGroupDetailBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("golfGroupDetailBase.delete_golfGroupDetail_Batch", idList);
//	}
	
	
}
