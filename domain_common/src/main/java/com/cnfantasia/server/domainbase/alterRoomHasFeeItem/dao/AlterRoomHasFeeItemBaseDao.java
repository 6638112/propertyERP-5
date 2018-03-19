package com.cnfantasia.server.domainbase.alterRoomHasFeeItem.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.alterRoomHasFeeItem.entity.AlterRoomHasFeeItem;

/**
 * 描述:(房间收费项表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class AlterRoomHasFeeItemBaseDao extends AbstractBaseDao implements IAlterRoomHasFeeItemBaseDao{
	/**
	 * 根据条件查询(房间收费项表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<AlterRoomHasFeeItem> selectAlterRoomHasFeeItemByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("alterRoomHasFeeItemBase.select_alterRoomHasFeeItem",paramMap);
	}
	/**
	 * 按条件分页查询(房间收费项表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<AlterRoomHasFeeItem> selectAlterRoomHasFeeItemByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectAlterRoomHasFeeItemCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<AlterRoomHasFeeItem> resMap= sqlSession.selectList("alterRoomHasFeeItemBase.select_alterRoomHasFeeItem_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(房间收费项表)信息
	 * @param id
	 * @return
	 */
	@Override
	public AlterRoomHasFeeItem selectAlterRoomHasFeeItemBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("alterRoomHasFeeItemBase.select_alterRoomHasFeeItem_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(房间收费项表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectAlterRoomHasFeeItemCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("alterRoomHasFeeItemBase.select_alterRoomHasFeeItem_count", paramMap);
	}
	/**
	 * 往(房间收费项表)新增一条记录
	 * @param alterRoomHasFeeItem
	 * @return
	 */
	@Override
	public int insertAlterRoomHasFeeItem(AlterRoomHasFeeItem alterRoomHasFeeItem){
		return sqlSession.insert("alterRoomHasFeeItemBase.insert_alterRoomHasFeeItem",alterRoomHasFeeItem);
	}
	/**
	 * 批量新增(房间收费项表)信息
	 * @param alterRoomHasFeeItemList
	 * @return
	 */
	@Override
	public int insertAlterRoomHasFeeItemBatch(List<AlterRoomHasFeeItem> alterRoomHasFeeItemList) {
		return sqlSession.insert("alterRoomHasFeeItemBase.insert_alterRoomHasFeeItem_Batch",alterRoomHasFeeItemList);
	}
	
	/**
	 * 更新(房间收费项表)信息
	 * @param alterRoomHasFeeItem
	 * @return
	 */
	@Override
	public int updateAlterRoomHasFeeItem(AlterRoomHasFeeItem alterRoomHasFeeItem){
		return sqlSession.update("alterRoomHasFeeItemBase.update_alterRoomHasFeeItem", alterRoomHasFeeItem);
	}
	/**
	 * 批量更新(房间收费项表)信息
	 * @param alterRoomHasFeeItemList
	 * @return
	 */
	@Override
	public int updateAlterRoomHasFeeItemBatch(List<AlterRoomHasFeeItem> alterRoomHasFeeItemList) {
		return sqlSession.update("alterRoomHasFeeItemBase.update_alterRoomHasFeeItem_Batch", alterRoomHasFeeItemList);
	}
	
	/**
	 * 根据序列号删除(房间收费项表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteAlterRoomHasFeeItemLogic(java.math.BigInteger id){
		AlterRoomHasFeeItem alterRoomHasFeeItem = new AlterRoomHasFeeItem();
		alterRoomHasFeeItem.setId(id);
		alterRoomHasFeeItem.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("alterRoomHasFeeItemBase.delete_alterRoomHasFeeItem_Logic",alterRoomHasFeeItem);
	}
	
	/**
	 * 根据Id 批量删除(房间收费项表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteAlterRoomHasFeeItemLogicBatch(List<java.math.BigInteger> idList) {
		List<AlterRoomHasFeeItem> delList = new java.util.ArrayList<AlterRoomHasFeeItem>();
		for(java.math.BigInteger id:idList){
			AlterRoomHasFeeItem alterRoomHasFeeItem = new AlterRoomHasFeeItem();
			alterRoomHasFeeItem.setId(id);
			alterRoomHasFeeItem.setSys0DelState(RecordState_DELETED);
			delList.add(alterRoomHasFeeItem);
		}
		return sqlSession.update("alterRoomHasFeeItemBase.delete_alterRoomHasFeeItem_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(房间收费项表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteAlterRoomHasFeeItem(java.math.BigInteger id){
//		return sqlSession.delete("alterRoomHasFeeItemBase.delete_alterRoomHasFeeItem", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(房间收费项表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteAlterRoomHasFeeItemBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("alterRoomHasFeeItemBase.delete_alterRoomHasFeeItem_Batch", idList);
//	}
	
	
}
