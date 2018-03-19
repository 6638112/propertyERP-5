package com.cnfantasia.server.domainbase.prizeRecordTmp.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.prizeRecordTmp.entity.PrizeRecordTmp;

/**
 * 描述:(临时用户中奖记录) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PrizeRecordTmpBaseDao extends AbstractBaseDao implements IPrizeRecordTmpBaseDao{
	/**
	 * 根据条件查询(临时用户中奖记录)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PrizeRecordTmp> selectPrizeRecordTmpByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("prizeRecordTmpBase.select_prizeRecordTmp",paramMap);
	}
	/**
	 * 按条件分页查询(临时用户中奖记录)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PrizeRecordTmp> selectPrizeRecordTmpByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPrizeRecordTmpCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PrizeRecordTmp> resMap= sqlSession.selectList("prizeRecordTmpBase.select_prizeRecordTmp_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(临时用户中奖记录)信息
	 * @param id
	 * @return
	 */
	@Override
	public PrizeRecordTmp selectPrizeRecordTmpBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("prizeRecordTmpBase.select_prizeRecordTmp_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(临时用户中奖记录)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPrizeRecordTmpCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("prizeRecordTmpBase.select_prizeRecordTmp_count", paramMap);
	}
	/**
	 * 往(临时用户中奖记录)新增一条记录
	 * @param prizeRecordTmp
	 * @return
	 */
	@Override
	public int insertPrizeRecordTmp(PrizeRecordTmp prizeRecordTmp){
		return sqlSession.insert("prizeRecordTmpBase.insert_prizeRecordTmp",prizeRecordTmp);
	}
	/**
	 * 批量新增(临时用户中奖记录)信息
	 * @param prizeRecordTmpList
	 * @return
	 */
	@Override
	public int insertPrizeRecordTmpBatch(List<PrizeRecordTmp> prizeRecordTmpList) {
		return sqlSession.insert("prizeRecordTmpBase.insert_prizeRecordTmp_Batch",prizeRecordTmpList);
	}
	
	/**
	 * 更新(临时用户中奖记录)信息
	 * @param prizeRecordTmp
	 * @return
	 */
	@Override
	public int updatePrizeRecordTmp(PrizeRecordTmp prizeRecordTmp){
		return sqlSession.update("prizeRecordTmpBase.update_prizeRecordTmp", prizeRecordTmp);
	}
	/**
	 * 批量更新(临时用户中奖记录)信息
	 * @param prizeRecordTmpList
	 * @return
	 */
	@Override
	public int updatePrizeRecordTmpBatch(List<PrizeRecordTmp> prizeRecordTmpList) {
		return sqlSession.update("prizeRecordTmpBase.update_prizeRecordTmp_Batch", prizeRecordTmpList);
	}
	
	/**
	 * 根据序列号删除(临时用户中奖记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePrizeRecordTmpLogic(java.math.BigInteger id){
		PrizeRecordTmp prizeRecordTmp = new PrizeRecordTmp();
		prizeRecordTmp.setId(id);
		prizeRecordTmp.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("prizeRecordTmpBase.delete_prizeRecordTmp_Logic",prizeRecordTmp);
	}
	
	/**
	 * 根据Id 批量删除(临时用户中奖记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePrizeRecordTmpLogicBatch(List<java.math.BigInteger> idList) {
		List<PrizeRecordTmp> delList = new java.util.ArrayList<PrizeRecordTmp>();
		for(java.math.BigInteger id:idList){
			PrizeRecordTmp prizeRecordTmp = new PrizeRecordTmp();
			prizeRecordTmp.setId(id);
			prizeRecordTmp.setSys0DelState(RecordState_DELETED);
			delList.add(prizeRecordTmp);
		}
		return sqlSession.update("prizeRecordTmpBase.delete_prizeRecordTmp_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(临时用户中奖记录)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePrizeRecordTmp(java.math.BigInteger id){
//		return sqlSession.delete("prizeRecordTmpBase.delete_prizeRecordTmp", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(临时用户中奖记录)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePrizeRecordTmpBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("prizeRecordTmpBase.delete_prizeRecordTmp_Batch", idList);
//	}
	
	
}
