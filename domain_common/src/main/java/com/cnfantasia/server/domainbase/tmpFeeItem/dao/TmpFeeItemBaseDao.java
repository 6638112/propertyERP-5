package com.cnfantasia.server.domainbase.tmpFeeItem.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.tmpFeeItem.entity.TmpFeeItem;

/**
 * 描述:(临时收费项) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class TmpFeeItemBaseDao extends AbstractBaseDao implements ITmpFeeItemBaseDao{
	/**
	 * 根据条件查询(临时收费项)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<TmpFeeItem> selectTmpFeeItemByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("tmpFeeItemBase.select_tmpFeeItem",paramMap);
	}
	/**
	 * 按条件分页查询(临时收费项)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<TmpFeeItem> selectTmpFeeItemByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectTmpFeeItemCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<TmpFeeItem> resMap= sqlSession.selectList("tmpFeeItemBase.select_tmpFeeItem_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(临时收费项)信息
	 * @param id
	 * @return
	 */
	@Override
	public TmpFeeItem selectTmpFeeItemBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("tmpFeeItemBase.select_tmpFeeItem_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(临时收费项)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectTmpFeeItemCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("tmpFeeItemBase.select_tmpFeeItem_count", paramMap);
	}
	/**
	 * 往(临时收费项)新增一条记录
	 * @param tmpFeeItem
	 * @return
	 */
	@Override
	public int insertTmpFeeItem(TmpFeeItem tmpFeeItem){
		return sqlSession.insert("tmpFeeItemBase.insert_tmpFeeItem",tmpFeeItem);
	}
	/**
	 * 批量新增(临时收费项)信息
	 * @param tmpFeeItemList
	 * @return
	 */
	@Override
	public int insertTmpFeeItemBatch(List<TmpFeeItem> tmpFeeItemList) {
		if (tmpFeeItemList == null || tmpFeeItemList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = tmpFeeItemList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<TmpFeeItem> batchList = tmpFeeItemList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("tmpFeeItemBase.insert_tmpFeeItem_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(临时收费项)信息
	 * @param tmpFeeItem
	 * @return
	 */
	@Override
	public int updateTmpFeeItem(TmpFeeItem tmpFeeItem){
		return sqlSession.update("tmpFeeItemBase.update_tmpFeeItem", tmpFeeItem);
	}
	/**
	 * 批量更新(临时收费项)信息
	 * @param tmpFeeItemList
	 * @return
	 */
	@Override
	public int updateTmpFeeItemBatch(List<TmpFeeItem> tmpFeeItemList) {
		if (tmpFeeItemList == null || tmpFeeItemList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("tmpFeeItemBase.update_tmpFeeItem_Batch", tmpFeeItemList);
	}
	
	/**
	 * 根据序列号删除(临时收费项)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteTmpFeeItemLogic(java.math.BigInteger id){
		TmpFeeItem tmpFeeItem = new TmpFeeItem();
		tmpFeeItem.setId(id);
		tmpFeeItem.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("tmpFeeItemBase.delete_tmpFeeItem_Logic",tmpFeeItem);
	}
	
	/**
	 * 根据Id 批量删除(临时收费项)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteTmpFeeItemLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<TmpFeeItem> delList = new java.util.ArrayList<TmpFeeItem>();
		for(java.math.BigInteger id:idList){
			TmpFeeItem tmpFeeItem = new TmpFeeItem();
			tmpFeeItem.setId(id);
			tmpFeeItem.setSys0DelState(RecordState_DELETED);
			delList.add(tmpFeeItem);
		}
		return sqlSession.update("tmpFeeItemBase.delete_tmpFeeItem_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(临时收费项)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteTmpFeeItem(java.math.BigInteger id){
//		return sqlSession.delete("tmpFeeItemBase.delete_tmpFeeItem", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(临时收费项)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteTmpFeeItemBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("tmpFeeItemBase.delete_tmpFeeItem_Batch", idList);
//	}
	
	
}
