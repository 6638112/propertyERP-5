package com.cnfantasia.server.domainbase.alterPeriodFeeItem.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.alterPeriodFeeItem.entity.AlterPeriodFeeItem;

/**
 * 描述:(选择周期收费项) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class AlterPeriodFeeItemBaseDao extends AbstractBaseDao implements IAlterPeriodFeeItemBaseDao{
	/**
	 * 根据条件查询(选择周期收费项)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<AlterPeriodFeeItem> selectAlterPeriodFeeItemByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("alterPeriodFeeItemBase.select_alterPeriodFeeItem",paramMap);
	}
	/**
	 * 按条件分页查询(选择周期收费项)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<AlterPeriodFeeItem> selectAlterPeriodFeeItemByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectAlterPeriodFeeItemCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<AlterPeriodFeeItem> resMap= sqlSession.selectList("alterPeriodFeeItemBase.select_alterPeriodFeeItem_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(选择周期收费项)信息
	 * @param id
	 * @return
	 */
	@Override
	public AlterPeriodFeeItem selectAlterPeriodFeeItemBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("alterPeriodFeeItemBase.select_alterPeriodFeeItem_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(选择周期收费项)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectAlterPeriodFeeItemCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("alterPeriodFeeItemBase.select_alterPeriodFeeItem_count", paramMap);
	}
	/**
	 * 往(选择周期收费项)新增一条记录
	 * @param alterPeriodFeeItem
	 * @return
	 */
	@Override
	public int insertAlterPeriodFeeItem(AlterPeriodFeeItem alterPeriodFeeItem){
		return sqlSession.insert("alterPeriodFeeItemBase.insert_alterPeriodFeeItem",alterPeriodFeeItem);
	}
	/**
	 * 批量新增(选择周期收费项)信息
	 * @param alterPeriodFeeItemList
	 * @return
	 */
	@Override
	public int insertAlterPeriodFeeItemBatch(List<AlterPeriodFeeItem> alterPeriodFeeItemList) {
		return sqlSession.insert("alterPeriodFeeItemBase.insert_alterPeriodFeeItem_Batch",alterPeriodFeeItemList);
	}
	
	/**
	 * 更新(选择周期收费项)信息
	 * @param alterPeriodFeeItem
	 * @return
	 */
	@Override
	public int updateAlterPeriodFeeItem(AlterPeriodFeeItem alterPeriodFeeItem){
		return sqlSession.update("alterPeriodFeeItemBase.update_alterPeriodFeeItem", alterPeriodFeeItem);
	}
	/**
	 * 批量更新(选择周期收费项)信息
	 * @param alterPeriodFeeItemList
	 * @return
	 */
	@Override
	public int updateAlterPeriodFeeItemBatch(List<AlterPeriodFeeItem> alterPeriodFeeItemList) {
		return sqlSession.update("alterPeriodFeeItemBase.update_alterPeriodFeeItem_Batch", alterPeriodFeeItemList);
	}
	
	/**
	 * 根据序列号删除(选择周期收费项)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteAlterPeriodFeeItemLogic(java.math.BigInteger id){
		AlterPeriodFeeItem alterPeriodFeeItem = new AlterPeriodFeeItem();
		alterPeriodFeeItem.setId(id);
		alterPeriodFeeItem.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("alterPeriodFeeItemBase.delete_alterPeriodFeeItem_Logic",alterPeriodFeeItem);
	}
	
	/**
	 * 根据Id 批量删除(选择周期收费项)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteAlterPeriodFeeItemLogicBatch(List<java.math.BigInteger> idList) {
		List<AlterPeriodFeeItem> delList = new java.util.ArrayList<AlterPeriodFeeItem>();
		for(java.math.BigInteger id:idList){
			AlterPeriodFeeItem alterPeriodFeeItem = new AlterPeriodFeeItem();
			alterPeriodFeeItem.setId(id);
			alterPeriodFeeItem.setSys0DelState(RecordState_DELETED);
			delList.add(alterPeriodFeeItem);
		}
		return sqlSession.update("alterPeriodFeeItemBase.delete_alterPeriodFeeItem_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(选择周期收费项)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteAlterPeriodFeeItem(java.math.BigInteger id){
//		return sqlSession.delete("alterPeriodFeeItemBase.delete_alterPeriodFeeItem", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(选择周期收费项)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteAlterPeriodFeeItemBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("alterPeriodFeeItemBase.delete_alterPeriodFeeItem_Batch", idList);
//	}
	
	
}
