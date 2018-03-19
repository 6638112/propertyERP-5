package com.cnfantasia.server.domainbase.prizeRuleDrawOnlinedays.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.prizeRuleDrawOnlinedays.entity.PrizeRuleDrawOnlinedays;

/**
 * 描述:(折扣抽奖规则-用户在线时间) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PrizeRuleDrawOnlinedaysBaseDao extends AbstractBaseDao implements IPrizeRuleDrawOnlinedaysBaseDao{
	/**
	 * 根据条件查询(折扣抽奖规则-用户在线时间)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PrizeRuleDrawOnlinedays> selectPrizeRuleDrawOnlinedaysByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("prizeRuleDrawOnlinedaysBase.select_prizeRuleDrawOnlinedays",paramMap);
	}
	/**
	 * 按条件分页查询(折扣抽奖规则-用户在线时间)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PrizeRuleDrawOnlinedays> selectPrizeRuleDrawOnlinedaysByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPrizeRuleDrawOnlinedaysCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PrizeRuleDrawOnlinedays> resMap= sqlSession.selectList("prizeRuleDrawOnlinedaysBase.select_prizeRuleDrawOnlinedays_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(折扣抽奖规则-用户在线时间)信息
	 * @param id
	 * @return
	 */
	@Override
	public PrizeRuleDrawOnlinedays selectPrizeRuleDrawOnlinedaysBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("prizeRuleDrawOnlinedaysBase.select_prizeRuleDrawOnlinedays_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(折扣抽奖规则-用户在线时间)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPrizeRuleDrawOnlinedaysCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("prizeRuleDrawOnlinedaysBase.select_prizeRuleDrawOnlinedays_count", paramMap);
	}
	/**
	 * 往(折扣抽奖规则-用户在线时间)新增一条记录
	 * @param prizeRuleDrawOnlinedays
	 * @return
	 */
	@Override
	public int insertPrizeRuleDrawOnlinedays(PrizeRuleDrawOnlinedays prizeRuleDrawOnlinedays){
		return sqlSession.insert("prizeRuleDrawOnlinedaysBase.insert_prizeRuleDrawOnlinedays",prizeRuleDrawOnlinedays);
	}
	/**
	 * 批量新增(折扣抽奖规则-用户在线时间)信息
	 * @param prizeRuleDrawOnlinedaysList
	 * @return
	 */
	@Override
	public int insertPrizeRuleDrawOnlinedaysBatch(List<PrizeRuleDrawOnlinedays> prizeRuleDrawOnlinedaysList) {
		return sqlSession.insert("prizeRuleDrawOnlinedaysBase.insert_prizeRuleDrawOnlinedays_Batch",prizeRuleDrawOnlinedaysList);
	}
	
	/**
	 * 更新(折扣抽奖规则-用户在线时间)信息
	 * @param prizeRuleDrawOnlinedays
	 * @return
	 */
	@Override
	public int updatePrizeRuleDrawOnlinedays(PrizeRuleDrawOnlinedays prizeRuleDrawOnlinedays){
		return sqlSession.update("prizeRuleDrawOnlinedaysBase.update_prizeRuleDrawOnlinedays", prizeRuleDrawOnlinedays);
	}
	/**
	 * 批量更新(折扣抽奖规则-用户在线时间)信息
	 * @param prizeRuleDrawOnlinedaysList
	 * @return
	 */
	@Override
	public int updatePrizeRuleDrawOnlinedaysBatch(List<PrizeRuleDrawOnlinedays> prizeRuleDrawOnlinedaysList) {
		return sqlSession.update("prizeRuleDrawOnlinedaysBase.update_prizeRuleDrawOnlinedays_Batch", prizeRuleDrawOnlinedaysList);
	}
	
	/**
	 * 根据序列号删除(折扣抽奖规则-用户在线时间)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePrizeRuleDrawOnlinedaysLogic(java.math.BigInteger id){
		PrizeRuleDrawOnlinedays prizeRuleDrawOnlinedays = new PrizeRuleDrawOnlinedays();
		prizeRuleDrawOnlinedays.setId(id);
		prizeRuleDrawOnlinedays.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("prizeRuleDrawOnlinedaysBase.delete_prizeRuleDrawOnlinedays_Logic",prizeRuleDrawOnlinedays);
	}
	
	/**
	 * 根据Id 批量删除(折扣抽奖规则-用户在线时间)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePrizeRuleDrawOnlinedaysLogicBatch(List<java.math.BigInteger> idList) {
		List<PrizeRuleDrawOnlinedays> delList = new java.util.ArrayList<PrizeRuleDrawOnlinedays>();
		for(java.math.BigInteger id:idList){
			PrizeRuleDrawOnlinedays prizeRuleDrawOnlinedays = new PrizeRuleDrawOnlinedays();
			prizeRuleDrawOnlinedays.setId(id);
			prizeRuleDrawOnlinedays.setSys0DelState(RecordState_DELETED);
			delList.add(prizeRuleDrawOnlinedays);
		}
		return sqlSession.update("prizeRuleDrawOnlinedaysBase.delete_prizeRuleDrawOnlinedays_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(折扣抽奖规则-用户在线时间)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePrizeRuleDrawOnlinedays(java.math.BigInteger id){
//		return sqlSession.delete("prizeRuleDrawOnlinedaysBase.delete_prizeRuleDrawOnlinedays", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(折扣抽奖规则-用户在线时间)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePrizeRuleDrawOnlinedaysBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("prizeRuleDrawOnlinedaysBase.delete_prizeRuleDrawOnlinedays_Batch", idList);
//	}
	
	
}
