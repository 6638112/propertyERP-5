package com.cnfantasia.server.domainbase.permiFunction.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.permiFunction.entity.PermiFunction;

/**
 * 描述:(功能表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PermiFunctionBaseDao extends AbstractBaseDao implements IPermiFunctionBaseDao{
	/**
	 * 根据条件查询(功能表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PermiFunction> selectPermiFunctionByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("permiFunctionBase.select_permiFunction",paramMap);
	}
	/**
	 * 按条件分页查询(功能表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PermiFunction> selectPermiFunctionByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPermiFunctionCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PermiFunction> resMap= sqlSession.selectList("permiFunctionBase.select_permiFunction_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(功能表)信息
	 * @param id
	 * @return
	 */
	@Override
	public PermiFunction selectPermiFunctionBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("permiFunctionBase.select_permiFunction_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(功能表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPermiFunctionCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("permiFunctionBase.select_permiFunction_count", paramMap);
	}
	/**
	 * 往(功能表)新增一条记录
	 * @param permiFunction
	 * @return
	 */
	@Override
	public int insertPermiFunction(PermiFunction permiFunction){
		return sqlSession.insert("permiFunctionBase.insert_permiFunction",permiFunction);
	}
	/**
	 * 批量新增(功能表)信息
	 * @param permiFunctionList
	 * @return
	 */
	@Override
	public int insertPermiFunctionBatch(List<PermiFunction> permiFunctionList) {
		return sqlSession.insert("permiFunctionBase.insert_permiFunction_Batch",permiFunctionList);
	}
	
	/**
	 * 更新(功能表)信息
	 * @param permiFunction
	 * @return
	 */
	@Override
	public int updatePermiFunction(PermiFunction permiFunction){
		return sqlSession.update("permiFunctionBase.update_permiFunction", permiFunction);
	}
	/**
	 * 批量更新(功能表)信息
	 * @param permiFunctionList
	 * @return
	 */
	@Override
	public int updatePermiFunctionBatch(List<PermiFunction> permiFunctionList) {
		return sqlSession.update("permiFunctionBase.update_permiFunction_Batch", permiFunctionList);
	}
	
	/**
	 * 根据序列号删除(功能表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePermiFunctionLogic(java.math.BigInteger id){
		PermiFunction permiFunction = new PermiFunction();
		permiFunction.setId(id);
		permiFunction.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("permiFunctionBase.delete_permiFunction_Logic",permiFunction);
	}
	
	/**
	 * 根据Id 批量删除(功能表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePermiFunctionLogicBatch(List<java.math.BigInteger> idList) {
		List<PermiFunction> delList = new java.util.ArrayList<PermiFunction>();
		for(java.math.BigInteger id:idList){
			PermiFunction permiFunction = new PermiFunction();
			permiFunction.setId(id);
			permiFunction.setSys0DelState(RecordState_DELETED);
			delList.add(permiFunction);
		}
		return sqlSession.update("permiFunctionBase.delete_permiFunction_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(功能表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePermiFunction(java.math.BigInteger id){
//		return sqlSession.delete("permiFunctionBase.delete_permiFunction", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(功能表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePermiFunctionBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("permiFunctionBase.delete_permiFunction_Batch", idList);
//	}
	
	
}
