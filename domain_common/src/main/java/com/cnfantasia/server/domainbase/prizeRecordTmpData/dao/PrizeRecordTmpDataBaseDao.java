package com.cnfantasia.server.domainbase.prizeRecordTmpData.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.prizeRecordTmpData.entity.PrizeRecordTmpData;

/**
 * 描述:() DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PrizeRecordTmpDataBaseDao extends AbstractBaseDao implements IPrizeRecordTmpDataBaseDao{
	/**
	 * 根据条件查询()信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PrizeRecordTmpData> selectPrizeRecordTmpDataByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("prizeRecordTmpDataBase.select_prizeRecordTmpData",paramMap);
	}
	/**
	 * 按条件分页查询()信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PrizeRecordTmpData> selectPrizeRecordTmpDataByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPrizeRecordTmpDataCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PrizeRecordTmpData> resMap= sqlSession.selectList("prizeRecordTmpDataBase.select_prizeRecordTmpData_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询()信息
	 * @param id
	 * @return
	 */
	@Override
	public PrizeRecordTmpData selectPrizeRecordTmpDataBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("prizeRecordTmpDataBase.select_prizeRecordTmpData_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的()记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPrizeRecordTmpDataCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("prizeRecordTmpDataBase.select_prizeRecordTmpData_count", paramMap);
	}
	/**
	 * 往()新增一条记录
	 * @param prizeRecordTmpData
	 * @return
	 */
	@Override
	public int insertPrizeRecordTmpData(PrizeRecordTmpData prizeRecordTmpData){
		return sqlSession.insert("prizeRecordTmpDataBase.insert_prizeRecordTmpData",prizeRecordTmpData);
	}
	/**
	 * 批量新增()信息
	 * @param prizeRecordTmpDataList
	 * @return
	 */
	@Override
	public int insertPrizeRecordTmpDataBatch(List<PrizeRecordTmpData> prizeRecordTmpDataList) {
		return sqlSession.insert("prizeRecordTmpDataBase.insert_prizeRecordTmpData_Batch",prizeRecordTmpDataList);
	}
	
	/**
	 * 更新()信息
	 * @param prizeRecordTmpData
	 * @return
	 */
	@Override
	public int updatePrizeRecordTmpData(PrizeRecordTmpData prizeRecordTmpData){
		return sqlSession.update("prizeRecordTmpDataBase.update_prizeRecordTmpData", prizeRecordTmpData);
	}
	/**
	 * 批量更新()信息
	 * @param prizeRecordTmpDataList
	 * @return
	 */
	@Override
	public int updatePrizeRecordTmpDataBatch(List<PrizeRecordTmpData> prizeRecordTmpDataList) {
		return sqlSession.update("prizeRecordTmpDataBase.update_prizeRecordTmpData_Batch", prizeRecordTmpDataList);
	}
	
	/**
	 * 根据序列号删除()信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePrizeRecordTmpDataLogic(java.math.BigInteger id){
		PrizeRecordTmpData prizeRecordTmpData = new PrizeRecordTmpData();
		prizeRecordTmpData.setId(id);
		prizeRecordTmpData.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("prizeRecordTmpDataBase.delete_prizeRecordTmpData_Logic",prizeRecordTmpData);
	}
	
	/**
	 * 根据Id 批量删除()信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePrizeRecordTmpDataLogicBatch(List<java.math.BigInteger> idList) {
		List<PrizeRecordTmpData> delList = new java.util.ArrayList<PrizeRecordTmpData>();
		for(java.math.BigInteger id:idList){
			PrizeRecordTmpData prizeRecordTmpData = new PrizeRecordTmpData();
			prizeRecordTmpData.setId(id);
			prizeRecordTmpData.setSys0DelState(RecordState_DELETED);
			delList.add(prizeRecordTmpData);
		}
		return sqlSession.update("prizeRecordTmpDataBase.delete_prizeRecordTmpData_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除()信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePrizeRecordTmpData(java.math.BigInteger id){
//		return sqlSession.delete("prizeRecordTmpDataBase.delete_prizeRecordTmpData", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除()信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePrizeRecordTmpDataBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("prizeRecordTmpDataBase.delete_prizeRecordTmpData_Batch", idList);
//	}
	
	
}
