package com.cnfantasia.server.domainbase.kitchenCookbookTypeRecommend.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.kitchenCookbookTypeRecommend.entity.KitchenCookbookTypeRecommend;

/**
 * 描述:(厨房菜品类别推荐) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class KitchenCookbookTypeRecommendBaseDao extends AbstractBaseDao implements IKitchenCookbookTypeRecommendBaseDao{
	/**
	 * 根据条件查询(厨房菜品类别推荐)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<KitchenCookbookTypeRecommend> selectKitchenCookbookTypeRecommendByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("kitchenCookbookTypeRecommendBase.select_kitchenCookbookTypeRecommend",paramMap);
	}
	/**
	 * 按条件分页查询(厨房菜品类别推荐)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<KitchenCookbookTypeRecommend> selectKitchenCookbookTypeRecommendByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectKitchenCookbookTypeRecommendCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<KitchenCookbookTypeRecommend> resMap= sqlSession.selectList("kitchenCookbookTypeRecommendBase.select_kitchenCookbookTypeRecommend_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(厨房菜品类别推荐)信息
	 * @param id
	 * @return
	 */
	@Override
	public KitchenCookbookTypeRecommend selectKitchenCookbookTypeRecommendBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("kitchenCookbookTypeRecommendBase.select_kitchenCookbookTypeRecommend_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(厨房菜品类别推荐)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectKitchenCookbookTypeRecommendCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("kitchenCookbookTypeRecommendBase.select_kitchenCookbookTypeRecommend_count", paramMap);
	}
	/**
	 * 往(厨房菜品类别推荐)新增一条记录
	 * @param kitchenCookbookTypeRecommend
	 * @return
	 */
	@Override
	public int insertKitchenCookbookTypeRecommend(KitchenCookbookTypeRecommend kitchenCookbookTypeRecommend){
		return sqlSession.insert("kitchenCookbookTypeRecommendBase.insert_kitchenCookbookTypeRecommend",kitchenCookbookTypeRecommend);
	}
	/**
	 * 批量新增(厨房菜品类别推荐)信息
	 * @param kitchenCookbookTypeRecommendList
	 * @return
	 */
	@Override
	public int insertKitchenCookbookTypeRecommendBatch(List<KitchenCookbookTypeRecommend> kitchenCookbookTypeRecommendList) {
		return sqlSession.insert("kitchenCookbookTypeRecommendBase.insert_kitchenCookbookTypeRecommend_Batch",kitchenCookbookTypeRecommendList);
	}
	
	/**
	 * 更新(厨房菜品类别推荐)信息
	 * @param kitchenCookbookTypeRecommend
	 * @return
	 */
	@Override
	public int updateKitchenCookbookTypeRecommend(KitchenCookbookTypeRecommend kitchenCookbookTypeRecommend){
		return sqlSession.update("kitchenCookbookTypeRecommendBase.update_kitchenCookbookTypeRecommend", kitchenCookbookTypeRecommend);
	}
	/**
	 * 批量更新(厨房菜品类别推荐)信息
	 * @param kitchenCookbookTypeRecommendList
	 * @return
	 */
	@Override
	public int updateKitchenCookbookTypeRecommendBatch(List<KitchenCookbookTypeRecommend> kitchenCookbookTypeRecommendList) {
		return sqlSession.update("kitchenCookbookTypeRecommendBase.update_kitchenCookbookTypeRecommend_Batch", kitchenCookbookTypeRecommendList);
	}
	
	/**
	 * 根据序列号删除(厨房菜品类别推荐)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteKitchenCookbookTypeRecommendLogic(java.math.BigInteger id){
		KitchenCookbookTypeRecommend kitchenCookbookTypeRecommend = new KitchenCookbookTypeRecommend();
		kitchenCookbookTypeRecommend.setId(id);
		kitchenCookbookTypeRecommend.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("kitchenCookbookTypeRecommendBase.delete_kitchenCookbookTypeRecommend_Logic",kitchenCookbookTypeRecommend);
	}
	
	/**
	 * 根据Id 批量删除(厨房菜品类别推荐)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteKitchenCookbookTypeRecommendLogicBatch(List<java.math.BigInteger> idList) {
		List<KitchenCookbookTypeRecommend> delList = new java.util.ArrayList<KitchenCookbookTypeRecommend>();
		for(java.math.BigInteger id:idList){
			KitchenCookbookTypeRecommend kitchenCookbookTypeRecommend = new KitchenCookbookTypeRecommend();
			kitchenCookbookTypeRecommend.setId(id);
			kitchenCookbookTypeRecommend.setSys0DelState(RecordState_DELETED);
			delList.add(kitchenCookbookTypeRecommend);
		}
		return sqlSession.update("kitchenCookbookTypeRecommendBase.delete_kitchenCookbookTypeRecommend_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(厨房菜品类别推荐)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenCookbookTypeRecommend(java.math.BigInteger id){
//		return sqlSession.delete("kitchenCookbookTypeRecommendBase.delete_kitchenCookbookTypeRecommend", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(厨房菜品类别推荐)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenCookbookTypeRecommendBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("kitchenCookbookTypeRecommendBase.delete_kitchenCookbookTypeRecommend_Batch", idList);
//	}
	
	
}
