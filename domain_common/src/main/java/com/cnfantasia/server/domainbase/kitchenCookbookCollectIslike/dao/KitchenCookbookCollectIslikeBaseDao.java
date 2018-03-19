package com.cnfantasia.server.domainbase.kitchenCookbookCollectIslike.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.kitchenCookbookCollectIslike.entity.KitchenCookbookCollectIslike;

/**
 * 描述:(家庭成员是否喜欢吃对应菜谱) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class KitchenCookbookCollectIslikeBaseDao extends AbstractBaseDao implements IKitchenCookbookCollectIslikeBaseDao{
	/**
	 * 根据条件查询(家庭成员是否喜欢吃对应菜谱)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<KitchenCookbookCollectIslike> selectKitchenCookbookCollectIslikeByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("kitchenCookbookCollectIslikeBase.select_kitchenCookbookCollectIslike",paramMap);
	}
	/**
	 * 按条件分页查询(家庭成员是否喜欢吃对应菜谱)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<KitchenCookbookCollectIslike> selectKitchenCookbookCollectIslikeByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectKitchenCookbookCollectIslikeCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<KitchenCookbookCollectIslike> resMap= sqlSession.selectList("kitchenCookbookCollectIslikeBase.select_kitchenCookbookCollectIslike_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(家庭成员是否喜欢吃对应菜谱)信息
	 * @param id
	 * @return
	 */
	@Override
	public KitchenCookbookCollectIslike selectKitchenCookbookCollectIslikeBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("kitchenCookbookCollectIslikeBase.select_kitchenCookbookCollectIslike_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(家庭成员是否喜欢吃对应菜谱)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectKitchenCookbookCollectIslikeCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("kitchenCookbookCollectIslikeBase.select_kitchenCookbookCollectIslike_count", paramMap);
	}
	/**
	 * 往(家庭成员是否喜欢吃对应菜谱)新增一条记录
	 * @param kitchenCookbookCollectIslike
	 * @return
	 */
	@Override
	public int insertKitchenCookbookCollectIslike(KitchenCookbookCollectIslike kitchenCookbookCollectIslike){
		return sqlSession.insert("kitchenCookbookCollectIslikeBase.insert_kitchenCookbookCollectIslike",kitchenCookbookCollectIslike);
	}
	/**
	 * 批量新增(家庭成员是否喜欢吃对应菜谱)信息
	 * @param kitchenCookbookCollectIslikeList
	 * @return
	 */
	@Override
	public int insertKitchenCookbookCollectIslikeBatch(List<KitchenCookbookCollectIslike> kitchenCookbookCollectIslikeList) {
		return sqlSession.insert("kitchenCookbookCollectIslikeBase.insert_kitchenCookbookCollectIslike_Batch",kitchenCookbookCollectIslikeList);
	}
	
	/**
	 * 更新(家庭成员是否喜欢吃对应菜谱)信息
	 * @param kitchenCookbookCollectIslike
	 * @return
	 */
	@Override
	public int updateKitchenCookbookCollectIslike(KitchenCookbookCollectIslike kitchenCookbookCollectIslike){
		return sqlSession.update("kitchenCookbookCollectIslikeBase.update_kitchenCookbookCollectIslike", kitchenCookbookCollectIslike);
	}
	/**
	 * 批量更新(家庭成员是否喜欢吃对应菜谱)信息
	 * @param kitchenCookbookCollectIslikeList
	 * @return
	 */
	@Override
	public int updateKitchenCookbookCollectIslikeBatch(List<KitchenCookbookCollectIslike> kitchenCookbookCollectIslikeList) {
		return sqlSession.update("kitchenCookbookCollectIslikeBase.update_kitchenCookbookCollectIslike_Batch", kitchenCookbookCollectIslikeList);
	}
	
	/**
	 * 根据序列号删除(家庭成员是否喜欢吃对应菜谱)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteKitchenCookbookCollectIslikeLogic(java.math.BigInteger id){
		KitchenCookbookCollectIslike kitchenCookbookCollectIslike = new KitchenCookbookCollectIslike();
		kitchenCookbookCollectIslike.setId(id);
		kitchenCookbookCollectIslike.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("kitchenCookbookCollectIslikeBase.delete_kitchenCookbookCollectIslike_Logic",kitchenCookbookCollectIslike);
	}
	
	/**
	 * 根据Id 批量删除(家庭成员是否喜欢吃对应菜谱)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteKitchenCookbookCollectIslikeLogicBatch(List<java.math.BigInteger> idList) {
		List<KitchenCookbookCollectIslike> delList = new java.util.ArrayList<KitchenCookbookCollectIslike>();
		for(java.math.BigInteger id:idList){
			KitchenCookbookCollectIslike kitchenCookbookCollectIslike = new KitchenCookbookCollectIslike();
			kitchenCookbookCollectIslike.setId(id);
			kitchenCookbookCollectIslike.setSys0DelState(RecordState_DELETED);
			delList.add(kitchenCookbookCollectIslike);
		}
		return sqlSession.update("kitchenCookbookCollectIslikeBase.delete_kitchenCookbookCollectIslike_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(家庭成员是否喜欢吃对应菜谱)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenCookbookCollectIslike(java.math.BigInteger id){
//		return sqlSession.delete("kitchenCookbookCollectIslikeBase.delete_kitchenCookbookCollectIslike", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(家庭成员是否喜欢吃对应菜谱)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenCookbookCollectIslikeBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("kitchenCookbookCollectIslikeBase.delete_kitchenCookbookCollectIslike_Batch", idList);
//	}
	
	
}
