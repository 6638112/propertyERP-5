package com.cnfantasia.server.domainbase.dredgeWorkerHasDredgeType.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeWorkerHasDredgeType.entity.DredgeWorkerHasDredgeType;

/**
 * 描述:(疏通师傅支持的疏通类型) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class DredgeWorkerHasDredgeTypeBaseDao extends AbstractBaseDao implements IDredgeWorkerHasDredgeTypeBaseDao{
	/**
	 * 根据条件查询(疏通师傅支持的疏通类型)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<DredgeWorkerHasDredgeType> selectDredgeWorkerHasDredgeTypeByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("dredgeWorkerHasDredgeTypeBase.select_dredgeWorkerHasDredgeType",paramMap);
	}
	/**
	 * 按条件分页查询(疏通师傅支持的疏通类型)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<DredgeWorkerHasDredgeType> selectDredgeWorkerHasDredgeTypeByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectDredgeWorkerHasDredgeTypeCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<DredgeWorkerHasDredgeType> resMap= sqlSession.selectList("dredgeWorkerHasDredgeTypeBase.select_dredgeWorkerHasDredgeType_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(疏通师傅支持的疏通类型)信息
	 * @param id
	 * @return
	 */
	@Override
	public DredgeWorkerHasDredgeType selectDredgeWorkerHasDredgeTypeBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("dredgeWorkerHasDredgeTypeBase.select_dredgeWorkerHasDredgeType_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(疏通师傅支持的疏通类型)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectDredgeWorkerHasDredgeTypeCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("dredgeWorkerHasDredgeTypeBase.select_dredgeWorkerHasDredgeType_count", paramMap);
	}
	/**
	 * 往(疏通师傅支持的疏通类型)新增一条记录
	 * @param dredgeWorkerHasDredgeType
	 * @return
	 */
	@Override
	public int insertDredgeWorkerHasDredgeType(DredgeWorkerHasDredgeType dredgeWorkerHasDredgeType){
		return sqlSession.insert("dredgeWorkerHasDredgeTypeBase.insert_dredgeWorkerHasDredgeType",dredgeWorkerHasDredgeType);
	}
	/**
	 * 批量新增(疏通师傅支持的疏通类型)信息
	 * @param dredgeWorkerHasDredgeTypeList
	 * @return
	 */
	@Override
	public int insertDredgeWorkerHasDredgeTypeBatch(List<DredgeWorkerHasDredgeType> dredgeWorkerHasDredgeTypeList) {
		return sqlSession.insert("dredgeWorkerHasDredgeTypeBase.insert_dredgeWorkerHasDredgeType_Batch",dredgeWorkerHasDredgeTypeList);
	}
	
	/**
	 * 更新(疏通师傅支持的疏通类型)信息
	 * @param dredgeWorkerHasDredgeType
	 * @return
	 */
	@Override
	public int updateDredgeWorkerHasDredgeType(DredgeWorkerHasDredgeType dredgeWorkerHasDredgeType){
		return sqlSession.update("dredgeWorkerHasDredgeTypeBase.update_dredgeWorkerHasDredgeType", dredgeWorkerHasDredgeType);
	}
	/**
	 * 批量更新(疏通师傅支持的疏通类型)信息
	 * @param dredgeWorkerHasDredgeTypeList
	 * @return
	 */
	@Override
	public int updateDredgeWorkerHasDredgeTypeBatch(List<DredgeWorkerHasDredgeType> dredgeWorkerHasDredgeTypeList) {
		return sqlSession.update("dredgeWorkerHasDredgeTypeBase.update_dredgeWorkerHasDredgeType_Batch", dredgeWorkerHasDredgeTypeList);
	}
	
	/**
	 * 根据序列号删除(疏通师傅支持的疏通类型)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	@Override
	public int deleteDredgeWorkerHasDredgeTypeLogic(java.math.BigInteger id){
		DredgeWorkerHasDredgeType dredgeWorkerHasDredgeType = new DredgeWorkerHasDredgeType();
		dredgeWorkerHasDredgeType.setId(id);
		dredgeWorkerHasDredgeType.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("dredgeWorkerHasDredgeTypeBase.delete_dredgeWorkerHasDredgeType_Logic",dredgeWorkerHasDredgeType);
	}
	 */
	/**
	 * 根据Id 批量删除(疏通师傅支持的疏通类型)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	@Override
	public int deleteDredgeWorkerHasDredgeTypeLogicBatch(List<java.math.BigInteger> idList) {
		List<DredgeWorkerHasDredgeType> delList = new java.util.ArrayList<DredgeWorkerHasDredgeType>();
		for(java.math.BigInteger id:idList){
			DredgeWorkerHasDredgeType dredgeWorkerHasDredgeType = new DredgeWorkerHasDredgeType();
			dredgeWorkerHasDredgeType.setId(id);
			dredgeWorkerHasDredgeType.setSys0DelState(RecordState_DELETED);
			delList.add(dredgeWorkerHasDredgeType);
		}
		return sqlSession.update("dredgeWorkerHasDredgeTypeBase.delete_dredgeWorkerHasDredgeType_Logic_Batch",delList);
	}
	 */
//	/**
//	 * 根据序列号删除(疏通师傅支持的疏通类型)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeWorkerHasDredgeType(java.math.BigInteger id){
//		return sqlSession.delete("dredgeWorkerHasDredgeTypeBase.delete_dredgeWorkerHasDredgeType", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(疏通师傅支持的疏通类型)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeWorkerHasDredgeTypeBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("dredgeWorkerHasDredgeTypeBase.delete_dredgeWorkerHasDredgeType_Batch", idList);
//	}
	
	
}
