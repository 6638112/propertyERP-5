package com.cnfantasia.server.domainbase.fixedFeeItem.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.fixedFeeItem.entity.FixedFeeItem;

/**
 * 描述:(固定收费项) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class FixedFeeItemBaseDao extends AbstractBaseDao implements IFixedFeeItemBaseDao{
	/**
	 * 根据条件查询(固定收费项)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<FixedFeeItem> selectFixedFeeItemByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("fixedFeeItemBase.select_fixedFeeItem",paramMap);
	}
	/**
	 * 按条件分页查询(固定收费项)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<FixedFeeItem> selectFixedFeeItemByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectFixedFeeItemCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<FixedFeeItem> resMap= sqlSession.selectList("fixedFeeItemBase.select_fixedFeeItem_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(固定收费项)信息
	 * @param id
	 * @return
	 */
	@Override
	public FixedFeeItem selectFixedFeeItemBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("fixedFeeItemBase.select_fixedFeeItem_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(固定收费项)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectFixedFeeItemCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("fixedFeeItemBase.select_fixedFeeItem_count", paramMap);
	}
	/**
	 * 往(固定收费项)新增一条记录
	 * @param fixedFeeItem
	 * @return
	 */
	@Override
	public int insertFixedFeeItem(FixedFeeItem fixedFeeItem){
		return sqlSession.insert("fixedFeeItemBase.insert_fixedFeeItem",fixedFeeItem);
	}
	/**
	 * 批量新增(固定收费项)信息
	 * @param fixedFeeItemList
	 * @return
	 */
	@Override
	public int insertFixedFeeItemBatch(List<FixedFeeItem> fixedFeeItemList) {
		if (fixedFeeItemList == null || fixedFeeItemList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = fixedFeeItemList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<FixedFeeItem> batchList = fixedFeeItemList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("fixedFeeItemBase.insert_fixedFeeItem_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(固定收费项)信息
	 * @param fixedFeeItem
	 * @return
	 */
	@Override
	public int updateFixedFeeItem(FixedFeeItem fixedFeeItem){
		return sqlSession.update("fixedFeeItemBase.update_fixedFeeItem", fixedFeeItem);
	}
	/**
	 * 批量更新(固定收费项)信息
	 * @param fixedFeeItemList
	 * @return
	 */
	@Override
	public int updateFixedFeeItemBatch(List<FixedFeeItem> fixedFeeItemList) {
		if (fixedFeeItemList == null || fixedFeeItemList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("fixedFeeItemBase.update_fixedFeeItem_Batch", fixedFeeItemList);
	}
	
	/**
	 * 根据序列号删除(固定收费项)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteFixedFeeItemLogic(java.math.BigInteger id){
		FixedFeeItem fixedFeeItem = new FixedFeeItem();
		fixedFeeItem.setId(id);
		fixedFeeItem.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("fixedFeeItemBase.delete_fixedFeeItem_Logic",fixedFeeItem);
	}
	
	/**
	 * 根据Id 批量删除(固定收费项)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteFixedFeeItemLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<FixedFeeItem> delList = new java.util.ArrayList<FixedFeeItem>();
		for(java.math.BigInteger id:idList){
			FixedFeeItem fixedFeeItem = new FixedFeeItem();
			fixedFeeItem.setId(id);
			fixedFeeItem.setSys0DelState(RecordState_DELETED);
			delList.add(fixedFeeItem);
		}
		return sqlSession.update("fixedFeeItemBase.delete_fixedFeeItem_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(固定收费项)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteFixedFeeItem(java.math.BigInteger id){
//		return sqlSession.delete("fixedFeeItemBase.delete_fixedFeeItem", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(固定收费项)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteFixedFeeItemBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("fixedFeeItemBase.delete_fixedFeeItem_Batch", idList);
//	}
	
	
}
