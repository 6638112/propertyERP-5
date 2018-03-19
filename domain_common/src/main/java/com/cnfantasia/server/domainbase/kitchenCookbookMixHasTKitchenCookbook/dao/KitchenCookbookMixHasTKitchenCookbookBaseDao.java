package com.cnfantasia.server.domainbase.kitchenCookbookMixHasTKitchenCookbook.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.kitchenCookbookMixHasTKitchenCookbook.entity.KitchenCookbookMixHasTKitchenCookbook;

/**
 * 描述:(组合菜谱所包含的菜谱) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class KitchenCookbookMixHasTKitchenCookbookBaseDao extends AbstractBaseDao implements IKitchenCookbookMixHasTKitchenCookbookBaseDao{
	/**
	 * 根据条件查询(组合菜谱所包含的菜谱)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<KitchenCookbookMixHasTKitchenCookbook> selectKitchenCookbookMixHasTKitchenCookbookByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("kitchenCookbookMixHasTKitchenCookbookBase.select_kitchenCookbookMixHasTKitchenCookbook",paramMap);
	}
	/**
	 * 按条件分页查询(组合菜谱所包含的菜谱)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<KitchenCookbookMixHasTKitchenCookbook> selectKitchenCookbookMixHasTKitchenCookbookByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectKitchenCookbookMixHasTKitchenCookbookCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<KitchenCookbookMixHasTKitchenCookbook> resMap= sqlSession.selectList("kitchenCookbookMixHasTKitchenCookbookBase.select_kitchenCookbookMixHasTKitchenCookbook_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(组合菜谱所包含的菜谱)信息
	 * @param id
	 * @return
	 */
	@Override
	public KitchenCookbookMixHasTKitchenCookbook selectKitchenCookbookMixHasTKitchenCookbookBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("kitchenCookbookMixHasTKitchenCookbookBase.select_kitchenCookbookMixHasTKitchenCookbook_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(组合菜谱所包含的菜谱)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectKitchenCookbookMixHasTKitchenCookbookCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("kitchenCookbookMixHasTKitchenCookbookBase.select_kitchenCookbookMixHasTKitchenCookbook_count", paramMap);
	}
	/**
	 * 往(组合菜谱所包含的菜谱)新增一条记录
	 * @param kitchenCookbookMixHasTKitchenCookbook
	 * @return
	 */
	@Override
	public int insertKitchenCookbookMixHasTKitchenCookbook(KitchenCookbookMixHasTKitchenCookbook kitchenCookbookMixHasTKitchenCookbook){
		return sqlSession.insert("kitchenCookbookMixHasTKitchenCookbookBase.insert_kitchenCookbookMixHasTKitchenCookbook",kitchenCookbookMixHasTKitchenCookbook);
	}
	/**
	 * 批量新增(组合菜谱所包含的菜谱)信息
	 * @param kitchenCookbookMixHasTKitchenCookbookList
	 * @return
	 */
	@Override
	public int insertKitchenCookbookMixHasTKitchenCookbookBatch(List<KitchenCookbookMixHasTKitchenCookbook> kitchenCookbookMixHasTKitchenCookbookList) {
		return sqlSession.insert("kitchenCookbookMixHasTKitchenCookbookBase.insert_kitchenCookbookMixHasTKitchenCookbook_Batch",kitchenCookbookMixHasTKitchenCookbookList);
	}
	
	/**
	 * 更新(组合菜谱所包含的菜谱)信息
	 * @param kitchenCookbookMixHasTKitchenCookbook
	 * @return
	 */
	@Override
	public int updateKitchenCookbookMixHasTKitchenCookbook(KitchenCookbookMixHasTKitchenCookbook kitchenCookbookMixHasTKitchenCookbook){
		return sqlSession.update("kitchenCookbookMixHasTKitchenCookbookBase.update_kitchenCookbookMixHasTKitchenCookbook", kitchenCookbookMixHasTKitchenCookbook);
	}
	/**
	 * 批量更新(组合菜谱所包含的菜谱)信息
	 * @param kitchenCookbookMixHasTKitchenCookbookList
	 * @return
	 */
	@Override
	public int updateKitchenCookbookMixHasTKitchenCookbookBatch(List<KitchenCookbookMixHasTKitchenCookbook> kitchenCookbookMixHasTKitchenCookbookList) {
		return sqlSession.update("kitchenCookbookMixHasTKitchenCookbookBase.update_kitchenCookbookMixHasTKitchenCookbook_Batch", kitchenCookbookMixHasTKitchenCookbookList);
	}
	
	/**
	 * 根据序列号删除(组合菜谱所包含的菜谱)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteKitchenCookbookMixHasTKitchenCookbookLogic(java.math.BigInteger id){
		KitchenCookbookMixHasTKitchenCookbook kitchenCookbookMixHasTKitchenCookbook = new KitchenCookbookMixHasTKitchenCookbook();
		kitchenCookbookMixHasTKitchenCookbook.setId(id);
		kitchenCookbookMixHasTKitchenCookbook.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("kitchenCookbookMixHasTKitchenCookbookBase.delete_kitchenCookbookMixHasTKitchenCookbook_Logic",kitchenCookbookMixHasTKitchenCookbook);
	}
	
	/**
	 * 根据Id 批量删除(组合菜谱所包含的菜谱)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteKitchenCookbookMixHasTKitchenCookbookLogicBatch(List<java.math.BigInteger> idList) {
		List<KitchenCookbookMixHasTKitchenCookbook> delList = new java.util.ArrayList<KitchenCookbookMixHasTKitchenCookbook>();
		for(java.math.BigInteger id:idList){
			KitchenCookbookMixHasTKitchenCookbook kitchenCookbookMixHasTKitchenCookbook = new KitchenCookbookMixHasTKitchenCookbook();
			kitchenCookbookMixHasTKitchenCookbook.setId(id);
			kitchenCookbookMixHasTKitchenCookbook.setSys0DelState(RecordState_DELETED);
			delList.add(kitchenCookbookMixHasTKitchenCookbook);
		}
		return sqlSession.update("kitchenCookbookMixHasTKitchenCookbookBase.delete_kitchenCookbookMixHasTKitchenCookbook_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(组合菜谱所包含的菜谱)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenCookbookMixHasTKitchenCookbook(java.math.BigInteger id){
//		return sqlSession.delete("kitchenCookbookMixHasTKitchenCookbookBase.delete_kitchenCookbookMixHasTKitchenCookbook", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(组合菜谱所包含的菜谱)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenCookbookMixHasTKitchenCookbookBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("kitchenCookbookMixHasTKitchenCookbookBase.delete_kitchenCookbookMixHasTKitchenCookbook_Batch", idList);
//	}
	
	
}
