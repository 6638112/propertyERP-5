package com.cnfantasia.server.domainbase.kitchenCookbookTypeCollect.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.kitchenCookbookTypeCollect.entity.KitchenCookbookTypeCollect;

/**
 * 描述:(菜谱类别收藏) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class KitchenCookbookTypeCollectBaseDao extends AbstractBaseDao implements IKitchenCookbookTypeCollectBaseDao{
	/**
	 * 根据条件查询(菜谱类别收藏)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<KitchenCookbookTypeCollect> selectKitchenCookbookTypeCollectByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("kitchenCookbookTypeCollectBase.select_kitchenCookbookTypeCollect",paramMap);
	}
	/**
	 * 按条件分页查询(菜谱类别收藏)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<KitchenCookbookTypeCollect> selectKitchenCookbookTypeCollectByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectKitchenCookbookTypeCollectCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<KitchenCookbookTypeCollect> resMap= sqlSession.selectList("kitchenCookbookTypeCollectBase.select_kitchenCookbookTypeCollect_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(菜谱类别收藏)信息
	 * @param id
	 * @return
	 */
	@Override
	public KitchenCookbookTypeCollect selectKitchenCookbookTypeCollectBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("kitchenCookbookTypeCollectBase.select_kitchenCookbookTypeCollect_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(菜谱类别收藏)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectKitchenCookbookTypeCollectCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("kitchenCookbookTypeCollectBase.select_kitchenCookbookTypeCollect_count", paramMap);
	}
	/**
	 * 往(菜谱类别收藏)新增一条记录
	 * @param kitchenCookbookTypeCollect
	 * @return
	 */
	@Override
	public int insertKitchenCookbookTypeCollect(KitchenCookbookTypeCollect kitchenCookbookTypeCollect){
		return sqlSession.insert("kitchenCookbookTypeCollectBase.insert_kitchenCookbookTypeCollect",kitchenCookbookTypeCollect);
	}
	/**
	 * 批量新增(菜谱类别收藏)信息
	 * @param kitchenCookbookTypeCollectList
	 * @return
	 */
	@Override
	public int insertKitchenCookbookTypeCollectBatch(List<KitchenCookbookTypeCollect> kitchenCookbookTypeCollectList) {
		return sqlSession.insert("kitchenCookbookTypeCollectBase.insert_kitchenCookbookTypeCollect_Batch",kitchenCookbookTypeCollectList);
	}
	
	/**
	 * 更新(菜谱类别收藏)信息
	 * @param kitchenCookbookTypeCollect
	 * @return
	 */
	@Override
	public int updateKitchenCookbookTypeCollect(KitchenCookbookTypeCollect kitchenCookbookTypeCollect){
		return sqlSession.update("kitchenCookbookTypeCollectBase.update_kitchenCookbookTypeCollect", kitchenCookbookTypeCollect);
	}
	/**
	 * 批量更新(菜谱类别收藏)信息
	 * @param kitchenCookbookTypeCollectList
	 * @return
	 */
	@Override
	public int updateKitchenCookbookTypeCollectBatch(List<KitchenCookbookTypeCollect> kitchenCookbookTypeCollectList) {
		return sqlSession.update("kitchenCookbookTypeCollectBase.update_kitchenCookbookTypeCollect_Batch", kitchenCookbookTypeCollectList);
	}
	
	/**
	 * 根据序列号删除(菜谱类别收藏)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteKitchenCookbookTypeCollectLogic(java.math.BigInteger id){
		KitchenCookbookTypeCollect kitchenCookbookTypeCollect = new KitchenCookbookTypeCollect();
		kitchenCookbookTypeCollect.setId(id);
		kitchenCookbookTypeCollect.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("kitchenCookbookTypeCollectBase.delete_kitchenCookbookTypeCollect_Logic",kitchenCookbookTypeCollect);
	}
	
	/**
	 * 根据Id 批量删除(菜谱类别收藏)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteKitchenCookbookTypeCollectLogicBatch(List<java.math.BigInteger> idList) {
		List<KitchenCookbookTypeCollect> delList = new java.util.ArrayList<KitchenCookbookTypeCollect>();
		for(java.math.BigInteger id:idList){
			KitchenCookbookTypeCollect kitchenCookbookTypeCollect = new KitchenCookbookTypeCollect();
			kitchenCookbookTypeCollect.setId(id);
			kitchenCookbookTypeCollect.setSys0DelState(RecordState_DELETED);
			delList.add(kitchenCookbookTypeCollect);
		}
		return sqlSession.update("kitchenCookbookTypeCollectBase.delete_kitchenCookbookTypeCollect_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(菜谱类别收藏)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenCookbookTypeCollect(java.math.BigInteger id){
//		return sqlSession.delete("kitchenCookbookTypeCollectBase.delete_kitchenCookbookTypeCollect", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(菜谱类别收藏)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenCookbookTypeCollectBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("kitchenCookbookTypeCollectBase.delete_kitchenCookbookTypeCollect_Batch", idList);
//	}
	
	
}
