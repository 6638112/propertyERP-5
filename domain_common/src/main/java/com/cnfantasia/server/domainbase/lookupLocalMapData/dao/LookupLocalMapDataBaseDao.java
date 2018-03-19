package com.cnfantasia.server.domainbase.lookupLocalMapData.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.lookupLocalMapData.entity.LookupLocalMapData;

/**
 * 描述:(本地地址信息数据) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class LookupLocalMapDataBaseDao extends AbstractBaseDao implements ILookupLocalMapDataBaseDao{
	/**
	 * 根据条件查询(本地地址信息数据)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<LookupLocalMapData> selectLookupLocalMapDataByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("lookupLocalMapDataBase.select_lookupLocalMapData",paramMap);
	}
	/**
	 * 按条件分页查询(本地地址信息数据)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<LookupLocalMapData> selectLookupLocalMapDataByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectLookupLocalMapDataCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<LookupLocalMapData> resMap= sqlSession.selectList("lookupLocalMapDataBase.select_lookupLocalMapData_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(本地地址信息数据)信息
	 * @param id
	 * @return
	 */
	@Override
	public LookupLocalMapData selectLookupLocalMapDataBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("lookupLocalMapDataBase.select_lookupLocalMapData_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(本地地址信息数据)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectLookupLocalMapDataCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("lookupLocalMapDataBase.select_lookupLocalMapData_count", paramMap);
	}
	/**
	 * 往(本地地址信息数据)新增一条记录
	 * @param lookupLocalMapData
	 * @return
	 */
	@Override
	public int insertLookupLocalMapData(LookupLocalMapData lookupLocalMapData){
		return sqlSession.insert("lookupLocalMapDataBase.insert_lookupLocalMapData",lookupLocalMapData);
	}
	/**
	 * 批量新增(本地地址信息数据)信息
	 * @param lookupLocalMapDataList
	 * @return
	 */
	@Override
	public int insertLookupLocalMapDataBatch(List<LookupLocalMapData> lookupLocalMapDataList) {
		return sqlSession.insert("lookupLocalMapDataBase.insert_lookupLocalMapData_Batch",lookupLocalMapDataList);
	}
	
	/**
	 * 更新(本地地址信息数据)信息
	 * @param lookupLocalMapData
	 * @return
	 */
	@Override
	public int updateLookupLocalMapData(LookupLocalMapData lookupLocalMapData){
		return sqlSession.update("lookupLocalMapDataBase.update_lookupLocalMapData", lookupLocalMapData);
	}
	/**
	 * 批量更新(本地地址信息数据)信息
	 * @param lookupLocalMapDataList
	 * @return
	 */
	@Override
	public int updateLookupLocalMapDataBatch(List<LookupLocalMapData> lookupLocalMapDataList) {
		return sqlSession.update("lookupLocalMapDataBase.update_lookupLocalMapData_Batch", lookupLocalMapDataList);
	}
	
	/**
	 * 根据序列号删除(本地地址信息数据)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	@Override
	public int deleteLookupLocalMapDataLogic(java.math.BigInteger id){
		LookupLocalMapData lookupLocalMapData = new LookupLocalMapData();
		lookupLocalMapData.setId(id);
		lookupLocalMapData.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("lookupLocalMapDataBase.delete_lookupLocalMapData_Logic",lookupLocalMapData);
	}
	 */
	/**
	 * 根据Id 批量删除(本地地址信息数据)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	@Override
	public int deleteLookupLocalMapDataLogicBatch(List<java.math.BigInteger> idList) {
		List<LookupLocalMapData> delList = new java.util.ArrayList<LookupLocalMapData>();
		for(java.math.BigInteger id:idList){
			LookupLocalMapData lookupLocalMapData = new LookupLocalMapData();
			lookupLocalMapData.setId(id);
			lookupLocalMapData.setSys0DelState(RecordState_DELETED);
			delList.add(lookupLocalMapData);
		}
		return sqlSession.update("lookupLocalMapDataBase.delete_lookupLocalMapData_Logic_Batch",delList);
	}
	 */
//	/**
//	 * 根据序列号删除(本地地址信息数据)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteLookupLocalMapData(java.math.BigInteger id){
//		return sqlSession.delete("lookupLocalMapDataBase.delete_lookupLocalMapData", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(本地地址信息数据)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteLookupLocalMapDataBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("lookupLocalMapDataBase.delete_lookupLocalMapData_Batch", idList);
//	}
	
	
}
