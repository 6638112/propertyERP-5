package com.cnfantasia.server.domainbase.dredgeType.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeType.entity.DredgeType;

/**
 * 描述:(疏通类型) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class DredgeTypeBaseDao extends AbstractBaseDao implements IDredgeTypeBaseDao{
	/**
	 * 根据条件查询(疏通类型)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<DredgeType> selectDredgeTypeByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("dredgeTypeBase.select_dredgeType",paramMap);
	}
	/**
	 * 按条件分页查询(疏通类型)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<DredgeType> selectDredgeTypeByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectDredgeTypeCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<DredgeType> resMap= sqlSession.selectList("dredgeTypeBase.select_dredgeType_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(疏通类型)信息
	 * @param id
	 * @return
	 */
	@Override
	public DredgeType selectDredgeTypeBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("dredgeTypeBase.select_dredgeType_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(疏通类型)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectDredgeTypeCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("dredgeTypeBase.select_dredgeType_count", paramMap);
	}
	/**
	 * 往(疏通类型)新增一条记录
	 * @param dredgeType
	 * @return
	 */
	@Override
	public int insertDredgeType(DredgeType dredgeType){
		return sqlSession.insert("dredgeTypeBase.insert_dredgeType",dredgeType);
	}
	/**
	 * 批量新增(疏通类型)信息
	 * @param dredgeTypeList
	 * @return
	 */
	@Override
	public int insertDredgeTypeBatch(List<DredgeType> dredgeTypeList) {
		return sqlSession.insert("dredgeTypeBase.insert_dredgeType_Batch",dredgeTypeList);
	}
	
	/**
	 * 更新(疏通类型)信息
	 * @param dredgeType
	 * @return
	 */
	@Override
	public int updateDredgeType(DredgeType dredgeType){
		return sqlSession.update("dredgeTypeBase.update_dredgeType", dredgeType);
	}
	/**
	 * 批量更新(疏通类型)信息
	 * @param dredgeTypeList
	 * @return
	 */
	@Override
	public int updateDredgeTypeBatch(List<DredgeType> dredgeTypeList) {
		return sqlSession.update("dredgeTypeBase.update_dredgeType_Batch", dredgeTypeList);
	}
	
	/**
	 * 根据序列号删除(疏通类型)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteDredgeTypeLogic(java.math.BigInteger id){
		DredgeType dredgeType = new DredgeType();
		dredgeType.setId(id);
		dredgeType.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("dredgeTypeBase.delete_dredgeType_Logic",dredgeType);
	}
	
	/**
	 * 根据Id 批量删除(疏通类型)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteDredgeTypeLogicBatch(List<java.math.BigInteger> idList) {
		List<DredgeType> delList = new java.util.ArrayList<DredgeType>();
		for(java.math.BigInteger id:idList){
			DredgeType dredgeType = new DredgeType();
			dredgeType.setId(id);
			dredgeType.setSys0DelState(RecordState_DELETED);
			delList.add(dredgeType);
		}
		return sqlSession.update("dredgeTypeBase.delete_dredgeType_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(疏通类型)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeType(java.math.BigInteger id){
//		return sqlSession.delete("dredgeTypeBase.delete_dredgeType", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(疏通类型)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeTypeBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("dredgeTypeBase.delete_dredgeType_Batch", idList);
//	}
	
	
}
