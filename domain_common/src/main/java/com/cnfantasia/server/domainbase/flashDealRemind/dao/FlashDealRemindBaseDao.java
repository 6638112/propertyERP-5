package com.cnfantasia.server.domainbase.flashDealRemind.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.flashDealRemind.entity.FlashDealRemind;

/**
 * 描述:(一元Go提醒) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class FlashDealRemindBaseDao extends AbstractBaseDao implements IFlashDealRemindBaseDao{
	/**
	 * 根据条件查询(一元Go提醒)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<FlashDealRemind> selectFlashDealRemindByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("flashDealRemindBase.select_flashDealRemind",paramMap);
	}
	/**
	 * 按条件分页查询(一元Go提醒)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<FlashDealRemind> selectFlashDealRemindByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectFlashDealRemindCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<FlashDealRemind> resMap= sqlSession.selectList("flashDealRemindBase.select_flashDealRemind_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(一元Go提醒)信息
	 * @param id
	 * @return
	 */
	@Override
	public FlashDealRemind selectFlashDealRemindBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("flashDealRemindBase.select_flashDealRemind_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(一元Go提醒)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectFlashDealRemindCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("flashDealRemindBase.select_flashDealRemind_count", paramMap);
	}
	/**
	 * 往(一元Go提醒)新增一条记录
	 * @param flashDealRemind
	 * @return
	 */
	@Override
	public int insertFlashDealRemind(FlashDealRemind flashDealRemind){
		return sqlSession.insert("flashDealRemindBase.insert_flashDealRemind",flashDealRemind);
	}
	/**
	 * 批量新增(一元Go提醒)信息
	 * @param flashDealRemindList
	 * @return
	 */
	@Override
	public int insertFlashDealRemindBatch(List<FlashDealRemind> flashDealRemindList) {
		return sqlSession.insert("flashDealRemindBase.insert_flashDealRemind_Batch",flashDealRemindList);
	}
	
	/**
	 * 更新(一元Go提醒)信息
	 * @param flashDealRemind
	 * @return
	 */
	@Override
	public int updateFlashDealRemind(FlashDealRemind flashDealRemind){
		return sqlSession.update("flashDealRemindBase.update_flashDealRemind", flashDealRemind);
	}
	/**
	 * 批量更新(一元Go提醒)信息
	 * @param flashDealRemindList
	 * @return
	 */
	@Override
	public int updateFlashDealRemindBatch(List<FlashDealRemind> flashDealRemindList) {
		return sqlSession.update("flashDealRemindBase.update_flashDealRemind_Batch", flashDealRemindList);
	}
	
	/**
	 * 根据序列号删除(一元Go提醒)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteFlashDealRemindLogic(java.math.BigInteger id){
		FlashDealRemind flashDealRemind = new FlashDealRemind();
		flashDealRemind.setId(id);
		flashDealRemind.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("flashDealRemindBase.delete_flashDealRemind_Logic",flashDealRemind);
	}
	
	/**
	 * 根据Id 批量删除(一元Go提醒)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteFlashDealRemindLogicBatch(List<java.math.BigInteger> idList) {
		List<FlashDealRemind> delList = new java.util.ArrayList<FlashDealRemind>();
		for(java.math.BigInteger id:idList){
			FlashDealRemind flashDealRemind = new FlashDealRemind();
			flashDealRemind.setId(id);
			flashDealRemind.setSys0DelState(RecordState_DELETED);
			delList.add(flashDealRemind);
		}
		return sqlSession.update("flashDealRemindBase.delete_flashDealRemind_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(一元Go提醒)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteFlashDealRemind(java.math.BigInteger id){
//		return sqlSession.delete("flashDealRemindBase.delete_flashDealRemind", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(一元Go提醒)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteFlashDealRemindBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("flashDealRemindBase.delete_flashDealRemind_Batch", idList);
//	}
	
	
}
