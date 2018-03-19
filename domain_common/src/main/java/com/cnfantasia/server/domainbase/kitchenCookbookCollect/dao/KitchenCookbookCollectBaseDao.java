package com.cnfantasia.server.domainbase.kitchenCookbookCollect.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.kitchenCookbookCollect.entity.KitchenCookbookCollect;

/**
 * 描述:(菜谱收藏) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class KitchenCookbookCollectBaseDao extends AbstractBaseDao implements IKitchenCookbookCollectBaseDao{
	/**
	 * 根据条件查询(菜谱收藏)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<KitchenCookbookCollect> selectKitchenCookbookCollectByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("kitchenCookbookCollectBase.select_kitchenCookbookCollect",paramMap);
	}
	/**
	 * 按条件分页查询(菜谱收藏)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<KitchenCookbookCollect> selectKitchenCookbookCollectByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectKitchenCookbookCollectCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<KitchenCookbookCollect> resMap= sqlSession.selectList("kitchenCookbookCollectBase.select_kitchenCookbookCollect_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(菜谱收藏)信息
	 * @param id
	 * @return
	 */
	@Override
	public KitchenCookbookCollect selectKitchenCookbookCollectBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("kitchenCookbookCollectBase.select_kitchenCookbookCollect_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(菜谱收藏)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectKitchenCookbookCollectCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("kitchenCookbookCollectBase.select_kitchenCookbookCollect_count", paramMap);
	}
	/**
	 * 往(菜谱收藏)新增一条记录
	 * @param kitchenCookbookCollect
	 * @return
	 */
	@Override
	public int insertKitchenCookbookCollect(KitchenCookbookCollect kitchenCookbookCollect){
		return sqlSession.insert("kitchenCookbookCollectBase.insert_kitchenCookbookCollect",kitchenCookbookCollect);
	}
	/**
	 * 批量新增(菜谱收藏)信息
	 * @param kitchenCookbookCollectList
	 * @return
	 */
	@Override
	public int insertKitchenCookbookCollectBatch(List<KitchenCookbookCollect> kitchenCookbookCollectList) {
		return sqlSession.insert("kitchenCookbookCollectBase.insert_kitchenCookbookCollect_Batch",kitchenCookbookCollectList);
	}
	
	/**
	 * 更新(菜谱收藏)信息
	 * @param kitchenCookbookCollect
	 * @return
	 */
	@Override
	public int updateKitchenCookbookCollect(KitchenCookbookCollect kitchenCookbookCollect){
		return sqlSession.update("kitchenCookbookCollectBase.update_kitchenCookbookCollect", kitchenCookbookCollect);
	}
	/**
	 * 批量更新(菜谱收藏)信息
	 * @param kitchenCookbookCollectList
	 * @return
	 */
	@Override
	public int updateKitchenCookbookCollectBatch(List<KitchenCookbookCollect> kitchenCookbookCollectList) {
		return sqlSession.update("kitchenCookbookCollectBase.update_kitchenCookbookCollect_Batch", kitchenCookbookCollectList);
	}
	
	/**
	 * 根据序列号删除(菜谱收藏)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteKitchenCookbookCollectLogic(java.math.BigInteger id){
		KitchenCookbookCollect kitchenCookbookCollect = new KitchenCookbookCollect();
		kitchenCookbookCollect.setId(id);
		kitchenCookbookCollect.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("kitchenCookbookCollectBase.delete_kitchenCookbookCollect_Logic",kitchenCookbookCollect);
	}
	
	/**
	 * 根据Id 批量删除(菜谱收藏)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteKitchenCookbookCollectLogicBatch(List<java.math.BigInteger> idList) {
		List<KitchenCookbookCollect> delList = new java.util.ArrayList<KitchenCookbookCollect>();
		for(java.math.BigInteger id:idList){
			KitchenCookbookCollect kitchenCookbookCollect = new KitchenCookbookCollect();
			kitchenCookbookCollect.setId(id);
			kitchenCookbookCollect.setSys0DelState(RecordState_DELETED);
			delList.add(kitchenCookbookCollect);
		}
		return sqlSession.update("kitchenCookbookCollectBase.delete_kitchenCookbookCollect_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(菜谱收藏)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenCookbookCollect(java.math.BigInteger id){
//		return sqlSession.delete("kitchenCookbookCollectBase.delete_kitchenCookbookCollect", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(菜谱收藏)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenCookbookCollectBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("kitchenCookbookCollectBase.delete_kitchenCookbookCollect_Batch", idList);
//	}
	
	
}
