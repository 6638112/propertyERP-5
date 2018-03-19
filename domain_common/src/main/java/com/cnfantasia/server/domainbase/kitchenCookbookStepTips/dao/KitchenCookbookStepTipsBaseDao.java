package com.cnfantasia.server.domainbase.kitchenCookbookStepTips.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.kitchenCookbookStepTips.entity.KitchenCookbookStepTips;

/**
 * 描述:(菜谱步骤tips信息) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class KitchenCookbookStepTipsBaseDao extends AbstractBaseDao implements IKitchenCookbookStepTipsBaseDao{
	/**
	 * 根据条件查询(菜谱步骤tips信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<KitchenCookbookStepTips> selectKitchenCookbookStepTipsByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("kitchenCookbookStepTipsBase.select_kitchenCookbookStepTips",paramMap);
	}
	/**
	 * 按条件分页查询(菜谱步骤tips信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<KitchenCookbookStepTips> selectKitchenCookbookStepTipsByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectKitchenCookbookStepTipsCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<KitchenCookbookStepTips> resMap= sqlSession.selectList("kitchenCookbookStepTipsBase.select_kitchenCookbookStepTips_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(菜谱步骤tips信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public KitchenCookbookStepTips selectKitchenCookbookStepTipsBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("kitchenCookbookStepTipsBase.select_kitchenCookbookStepTips_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(菜谱步骤tips信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectKitchenCookbookStepTipsCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("kitchenCookbookStepTipsBase.select_kitchenCookbookStepTips_count", paramMap);
	}
	/**
	 * 往(菜谱步骤tips信息)新增一条记录
	 * @param kitchenCookbookStepTips
	 * @return
	 */
	@Override
	public int insertKitchenCookbookStepTips(KitchenCookbookStepTips kitchenCookbookStepTips){
		return sqlSession.insert("kitchenCookbookStepTipsBase.insert_kitchenCookbookStepTips",kitchenCookbookStepTips);
	}
	/**
	 * 批量新增(菜谱步骤tips信息)信息
	 * @param kitchenCookbookStepTipsList
	 * @return
	 */
	@Override
	public int insertKitchenCookbookStepTipsBatch(List<KitchenCookbookStepTips> kitchenCookbookStepTipsList) {
		return sqlSession.insert("kitchenCookbookStepTipsBase.insert_kitchenCookbookStepTips_Batch",kitchenCookbookStepTipsList);
	}
	
	/**
	 * 更新(菜谱步骤tips信息)信息
	 * @param kitchenCookbookStepTips
	 * @return
	 */
	@Override
	public int updateKitchenCookbookStepTips(KitchenCookbookStepTips kitchenCookbookStepTips){
		return sqlSession.update("kitchenCookbookStepTipsBase.update_kitchenCookbookStepTips", kitchenCookbookStepTips);
	}
	/**
	 * 批量更新(菜谱步骤tips信息)信息
	 * @param kitchenCookbookStepTipsList
	 * @return
	 */
	@Override
	public int updateKitchenCookbookStepTipsBatch(List<KitchenCookbookStepTips> kitchenCookbookStepTipsList) {
		return sqlSession.update("kitchenCookbookStepTipsBase.update_kitchenCookbookStepTips_Batch", kitchenCookbookStepTipsList);
	}
	
	/**
	 * 根据序列号删除(菜谱步骤tips信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteKitchenCookbookStepTipsLogic(java.math.BigInteger id){
		KitchenCookbookStepTips kitchenCookbookStepTips = new KitchenCookbookStepTips();
		kitchenCookbookStepTips.setId(id);
		kitchenCookbookStepTips.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("kitchenCookbookStepTipsBase.delete_kitchenCookbookStepTips_Logic",kitchenCookbookStepTips);
	}
	
	/**
	 * 根据Id 批量删除(菜谱步骤tips信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteKitchenCookbookStepTipsLogicBatch(List<java.math.BigInteger> idList) {
		List<KitchenCookbookStepTips> delList = new java.util.ArrayList<KitchenCookbookStepTips>();
		for(java.math.BigInteger id:idList){
			KitchenCookbookStepTips kitchenCookbookStepTips = new KitchenCookbookStepTips();
			kitchenCookbookStepTips.setId(id);
			kitchenCookbookStepTips.setSys0DelState(RecordState_DELETED);
			delList.add(kitchenCookbookStepTips);
		}
		return sqlSession.update("kitchenCookbookStepTipsBase.delete_kitchenCookbookStepTips_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(菜谱步骤tips信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenCookbookStepTips(java.math.BigInteger id){
//		return sqlSession.delete("kitchenCookbookStepTipsBase.delete_kitchenCookbookStepTips", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(菜谱步骤tips信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteKitchenCookbookStepTipsBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("kitchenCookbookStepTipsBase.delete_kitchenCookbookStepTips_Batch", idList);
//	}
	
	
}
