package com.cnfantasia.server.domainbase.livingFeeItem.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.livingFeeItem.entity.LivingFeeItem;

/**
 * 描述:(生活缴费支持的项目类别) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class LivingFeeItemBaseDao extends AbstractBaseDao implements ILivingFeeItemBaseDao{
	/**
	 * 根据条件查询(生活缴费支持的项目类别)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<LivingFeeItem> selectLivingFeeItemByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("livingFeeItemBase.select_livingFeeItem",paramMap);
	}
	/**
	 * 按条件分页查询(生活缴费支持的项目类别)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<LivingFeeItem> selectLivingFeeItemByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectLivingFeeItemCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<LivingFeeItem> resMap= sqlSession.selectList("livingFeeItemBase.select_livingFeeItem_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(生活缴费支持的项目类别)信息
	 * @param id
	 * @return
	 */
	@Override
	public LivingFeeItem selectLivingFeeItemBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("livingFeeItemBase.select_livingFeeItem_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(生活缴费支持的项目类别)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectLivingFeeItemCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("livingFeeItemBase.select_livingFeeItem_count", paramMap);
	}
	/**
	 * 往(生活缴费支持的项目类别)新增一条记录
	 * @param livingFeeItem
	 * @return
	 */
	@Override
	public int insertLivingFeeItem(LivingFeeItem livingFeeItem){
		return sqlSession.insert("livingFeeItemBase.insert_livingFeeItem",livingFeeItem);
	}
	/**
	 * 批量新增(生活缴费支持的项目类别)信息
	 * @param livingFeeItemList
	 * @return
	 */
	@Override
	public int insertLivingFeeItemBatch(List<LivingFeeItem> livingFeeItemList) {
		if (livingFeeItemList == null || livingFeeItemList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = livingFeeItemList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<LivingFeeItem> batchList = livingFeeItemList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("livingFeeItemBase.insert_livingFeeItem_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(生活缴费支持的项目类别)信息
	 * @param livingFeeItem
	 * @return
	 */
	@Override
	public int updateLivingFeeItem(LivingFeeItem livingFeeItem){
		return sqlSession.update("livingFeeItemBase.update_livingFeeItem", livingFeeItem);
	}
	/**
	 * 批量更新(生活缴费支持的项目类别)信息
	 * @param livingFeeItemList
	 * @return
	 */
	@Override
	public int updateLivingFeeItemBatch(List<LivingFeeItem> livingFeeItemList) {
		if (livingFeeItemList == null || livingFeeItemList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("livingFeeItemBase.update_livingFeeItem_Batch", livingFeeItemList);
	}
	
	/**
	 * 根据序列号删除(生活缴费支持的项目类别)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteLivingFeeItemLogic(java.math.BigInteger id){
		LivingFeeItem livingFeeItem = new LivingFeeItem();
		livingFeeItem.setId(id);
		livingFeeItem.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("livingFeeItemBase.delete_livingFeeItem_Logic",livingFeeItem);
	}
	
	/**
	 * 根据Id 批量删除(生活缴费支持的项目类别)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteLivingFeeItemLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<LivingFeeItem> delList = new java.util.ArrayList<LivingFeeItem>();
		for(java.math.BigInteger id:idList){
			LivingFeeItem livingFeeItem = new LivingFeeItem();
			livingFeeItem.setId(id);
			livingFeeItem.setSys0DelState(RecordState_DELETED);
			delList.add(livingFeeItem);
		}
		return sqlSession.update("livingFeeItemBase.delete_livingFeeItem_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(生活缴费支持的项目类别)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteLivingFeeItem(java.math.BigInteger id){
//		return sqlSession.delete("livingFeeItemBase.delete_livingFeeItem", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(生活缴费支持的项目类别)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteLivingFeeItemBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("livingFeeItemBase.delete_livingFeeItem_Batch", idList);
//	}
	
	
}
