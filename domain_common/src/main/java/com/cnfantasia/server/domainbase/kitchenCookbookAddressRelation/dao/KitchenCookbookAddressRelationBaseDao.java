package com.cnfantasia.server.domainbase.kitchenCookbookAddressRelation.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.kitchenCookbookAddressRelation.entity.KitchenCookbookAddressRelation;

/**
 * 描述:(菜谱地址关系表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class KitchenCookbookAddressRelationBaseDao extends AbstractBaseDao implements IKitchenCookbookAddressRelationBaseDao{
	/**
	 * 根据条件查询(菜谱地址关系表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<KitchenCookbookAddressRelation> selectKitchenCookbookAddressRelationByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("kitchenCookbookAddressRelationBase.select_kitchenCookbookAddressRelation",paramMap);
	}
	/**
	 * 按条件分页查询(菜谱地址关系表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<KitchenCookbookAddressRelation> selectKitchenCookbookAddressRelationByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectKitchenCookbookAddressRelationCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<KitchenCookbookAddressRelation> resMap= sqlSession.selectList("kitchenCookbookAddressRelationBase.select_kitchenCookbookAddressRelation_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(菜谱地址关系表)信息
	 * @param id
	 * @return
	 */
	@Override
	public KitchenCookbookAddressRelation selectKitchenCookbookAddressRelationBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("kitchenCookbookAddressRelationBase.select_kitchenCookbookAddressRelation_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(菜谱地址关系表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectKitchenCookbookAddressRelationCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("kitchenCookbookAddressRelationBase.select_kitchenCookbookAddressRelation_count", paramMap);
	}
	/**
	 * 往(菜谱地址关系表)新增一条记录
	 * @param kitchenCookbookAddressRelation
	 * @return
	 */
	@Override
	public int insertKitchenCookbookAddressRelation(KitchenCookbookAddressRelation kitchenCookbookAddressRelation){
		return sqlSession.insert("kitchenCookbookAddressRelationBase.insert_kitchenCookbookAddressRelation",kitchenCookbookAddressRelation);
	}
	/**
	 * 批量新增(菜谱地址关系表)信息
	 * @param kitchenCookbookAddressRelationList
	 * @return
	 */
	@Override
	public int insertKitchenCookbookAddressRelationBatch(List<KitchenCookbookAddressRelation> kitchenCookbookAddressRelationList) {
		return sqlSession.insert("kitchenCookbookAddressRelationBase.insert_kitchenCookbookAddressRelation_Batch",kitchenCookbookAddressRelationList);
	}
	
	/**
	 * 更新(菜谱地址关系表)信息
	 * @param kitchenCookbookAddressRelation
	 * @return
	 */
	@Override
	public int updateKitchenCookbookAddressRelation(KitchenCookbookAddressRelation kitchenCookbookAddressRelation){
		return sqlSession.update("kitchenCookbookAddressRelationBase.update_kitchenCookbookAddressRelation", kitchenCookbookAddressRelation);
	}
	/**
	 * 批量更新(菜谱地址关系表)信息
	 * @param kitchenCookbookAddressRelationList
	 * @return
	 */
	@Override
	public int updateKitchenCookbookAddressRelationBatch(List<KitchenCookbookAddressRelation> kitchenCookbookAddressRelationList) {
		return sqlSession.update("kitchenCookbookAddressRelationBase.update_kitchenCookbookAddressRelation_Batch", kitchenCookbookAddressRelationList);
	}
	
	/**
	 * 根据序列号删除(菜谱地址关系表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteKitchenCookbookAddressRelationLogic(java.math.BigInteger id){
		KitchenCookbookAddressRelation kitchenCookbookAddressRelation = new KitchenCookbookAddressRelation();
		kitchenCookbookAddressRelation.setId(id);
		kitchenCookbookAddressRelation.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("kitchenCookbookAddressRelationBase.delete_kitchenCookbookAddressRelation_Logic",kitchenCookbookAddressRelation);
	}
	
	/**
	 * 根据Id 批量删除(菜谱地址关系表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteKitchenCookbookAddressRelationLogicBatch(List<java.math.BigInteger> idList) {
		List<KitchenCookbookAddressRelation> delList = new java.util.ArrayList<KitchenCookbookAddressRelation>();
		for(java.math.BigInteger id:idList){
			KitchenCookbookAddressRelation kitchenCookbookAddressRelation = new KitchenCookbookAddressRelation();
			kitchenCookbookAddressRelation.setId(id);
			kitchenCookbookAddressRelation.setSys0DelState(RecordState_DELETED);
			delList.add(kitchenCookbookAddressRelation);
		}
		return sqlSession.update("kitchenCookbookAddressRelationBase.delete_kitchenCookbookAddressRelation_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(菜谱地址关系表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenCookbookAddressRelation(java.math.BigInteger id){
//		return sqlSession.delete("kitchenCookbookAddressRelationBase.delete_kitchenCookbookAddressRelation", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(菜谱地址关系表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenCookbookAddressRelationBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("kitchenCookbookAddressRelationBase.delete_kitchenCookbookAddressRelation_Batch", idList);
//	}
	
	
}
