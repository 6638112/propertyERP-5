package com.cnfantasia.server.domainbase.eatMenuRecommend.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.eatMenuRecommend.entity.EatMenuRecommend;

/**
 * 描述:(推荐菜谱) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EatMenuRecommendBaseDao extends AbstractBaseDao implements IEatMenuRecommendBaseDao{
	/**
	 * 根据条件查询(推荐菜谱)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EatMenuRecommend> selectEatMenuRecommendByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("eatMenuRecommendBase.select_eatMenuRecommend",paramMap);
	}
	/**
	 * 按条件分页查询(推荐菜谱)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EatMenuRecommend> selectEatMenuRecommendByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEatMenuRecommendCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EatMenuRecommend> resMap= sqlSession.selectList("eatMenuRecommendBase.select_eatMenuRecommend_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(推荐菜谱)信息
	 * @param id
	 * @return
	 */
	@Override
	public EatMenuRecommend selectEatMenuRecommendBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("eatMenuRecommendBase.select_eatMenuRecommend_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(推荐菜谱)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEatMenuRecommendCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("eatMenuRecommendBase.select_eatMenuRecommend_count", paramMap);
	}
	/**
	 * 往(推荐菜谱)新增一条记录
	 * @param eatMenuRecommend
	 * @return
	 */
	@Override
	public int insertEatMenuRecommend(EatMenuRecommend eatMenuRecommend){
		return sqlSession.insert("eatMenuRecommendBase.insert_eatMenuRecommend",eatMenuRecommend);
	}
	/**
	 * 批量新增(推荐菜谱)信息
	 * @param eatMenuRecommendList
	 * @return
	 */
	@Override
	public int insertEatMenuRecommendBatch(List<EatMenuRecommend> eatMenuRecommendList) {
		return sqlSession.insert("eatMenuRecommendBase.insert_eatMenuRecommend_Batch",eatMenuRecommendList);
	}
	
	/**
	 * 更新(推荐菜谱)信息
	 * @param eatMenuRecommend
	 * @return
	 */
	@Override
	public int updateEatMenuRecommend(EatMenuRecommend eatMenuRecommend){
		return sqlSession.update("eatMenuRecommendBase.update_eatMenuRecommend", eatMenuRecommend);
	}
	/**
	 * 批量更新(推荐菜谱)信息
	 * @param eatMenuRecommendList
	 * @return
	 */
	@Override
	public int updateEatMenuRecommendBatch(List<EatMenuRecommend> eatMenuRecommendList) {
		return sqlSession.update("eatMenuRecommendBase.update_eatMenuRecommend_Batch", eatMenuRecommendList);
	}
	
	/**
	 * 根据序列号删除(推荐菜谱)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEatMenuRecommendLogic(java.math.BigInteger id){
		EatMenuRecommend eatMenuRecommend = new EatMenuRecommend();
		eatMenuRecommend.setId(id);
		eatMenuRecommend.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("eatMenuRecommendBase.delete_eatMenuRecommend_Logic",eatMenuRecommend);
	}
	
	/**
	 * 根据Id 批量删除(推荐菜谱)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEatMenuRecommendLogicBatch(List<java.math.BigInteger> idList) {
		List<EatMenuRecommend> delList = new java.util.ArrayList<EatMenuRecommend>();
		for(java.math.BigInteger id:idList){
			EatMenuRecommend eatMenuRecommend = new EatMenuRecommend();
			eatMenuRecommend.setId(id);
			eatMenuRecommend.setSys0DelState(RecordState_DELETED);
			delList.add(eatMenuRecommend);
		}
		return sqlSession.update("eatMenuRecommendBase.delete_eatMenuRecommend_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(推荐菜谱)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEatMenuRecommend(java.math.BigInteger id){
//		return sqlSession.delete("eatMenuRecommendBase.delete_eatMenuRecommend", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(推荐菜谱)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEatMenuRecommendBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("eatMenuRecommendBase.delete_eatMenuRecommend_Batch", idList);
//	}
	
	
}
