package com.cnfantasia.server.domainbase.permiRoleHasTPermiFunction.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.permiRoleHasTPermiFunction.entity.PermiRoleHasTPermiFunction;

/**
 * 描述:(角色功能关系) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PermiRoleHasTPermiFunctionBaseDao extends AbstractBaseDao implements IPermiRoleHasTPermiFunctionBaseDao{
	/**
	 * 根据条件查询(角色功能关系)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PermiRoleHasTPermiFunction> selectPermiRoleHasTPermiFunctionByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("permiRoleHasTPermiFunctionBase.select_permiRoleHasTPermiFunction",paramMap);
	}
	/**
	 * 按条件分页查询(角色功能关系)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PermiRoleHasTPermiFunction> selectPermiRoleHasTPermiFunctionByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPermiRoleHasTPermiFunctionCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PermiRoleHasTPermiFunction> resMap= sqlSession.selectList("permiRoleHasTPermiFunctionBase.select_permiRoleHasTPermiFunction_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(角色功能关系)信息
	 * @param id
	 * @return
	 */
	@Override
	public PermiRoleHasTPermiFunction selectPermiRoleHasTPermiFunctionBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("permiRoleHasTPermiFunctionBase.select_permiRoleHasTPermiFunction_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(角色功能关系)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPermiRoleHasTPermiFunctionCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("permiRoleHasTPermiFunctionBase.select_permiRoleHasTPermiFunction_count", paramMap);
	}
	/**
	 * 往(角色功能关系)新增一条记录
	 * @param permiRoleHasTPermiFunction
	 * @return
	 */
	@Override
	public int insertPermiRoleHasTPermiFunction(PermiRoleHasTPermiFunction permiRoleHasTPermiFunction){
		return sqlSession.insert("permiRoleHasTPermiFunctionBase.insert_permiRoleHasTPermiFunction",permiRoleHasTPermiFunction);
	}
	/**
	 * 批量新增(角色功能关系)信息
	 * @param permiRoleHasTPermiFunctionList
	 * @return
	 */
	@Override
	public int insertPermiRoleHasTPermiFunctionBatch(List<PermiRoleHasTPermiFunction> permiRoleHasTPermiFunctionList) {
		return sqlSession.insert("permiRoleHasTPermiFunctionBase.insert_permiRoleHasTPermiFunction_Batch",permiRoleHasTPermiFunctionList);
	}
	
	/**
	 * 更新(角色功能关系)信息
	 * @param permiRoleHasTPermiFunction
	 * @return
	 */
	@Override
	public int updatePermiRoleHasTPermiFunction(PermiRoleHasTPermiFunction permiRoleHasTPermiFunction){
		return sqlSession.update("permiRoleHasTPermiFunctionBase.update_permiRoleHasTPermiFunction", permiRoleHasTPermiFunction);
	}
	/**
	 * 批量更新(角色功能关系)信息
	 * @param permiRoleHasTPermiFunctionList
	 * @return
	 */
	@Override
	public int updatePermiRoleHasTPermiFunctionBatch(List<PermiRoleHasTPermiFunction> permiRoleHasTPermiFunctionList) {
		return sqlSession.update("permiRoleHasTPermiFunctionBase.update_permiRoleHasTPermiFunction_Batch", permiRoleHasTPermiFunctionList);
	}
	
	/**
	 * 根据序列号删除(角色功能关系)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePermiRoleHasTPermiFunctionLogic(java.math.BigInteger id){
		PermiRoleHasTPermiFunction permiRoleHasTPermiFunction = new PermiRoleHasTPermiFunction();
		permiRoleHasTPermiFunction.setId(id);
		permiRoleHasTPermiFunction.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("permiRoleHasTPermiFunctionBase.delete_permiRoleHasTPermiFunction_Logic",permiRoleHasTPermiFunction);
	}
	
	/**
	 * 根据Id 批量删除(角色功能关系)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePermiRoleHasTPermiFunctionLogicBatch(List<java.math.BigInteger> idList) {
		List<PermiRoleHasTPermiFunction> delList = new java.util.ArrayList<PermiRoleHasTPermiFunction>();
		for(java.math.BigInteger id:idList){
			PermiRoleHasTPermiFunction permiRoleHasTPermiFunction = new PermiRoleHasTPermiFunction();
			permiRoleHasTPermiFunction.setId(id);
			permiRoleHasTPermiFunction.setSys0DelState(RecordState_DELETED);
			delList.add(permiRoleHasTPermiFunction);
		}
		return sqlSession.update("permiRoleHasTPermiFunctionBase.delete_permiRoleHasTPermiFunction_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(角色功能关系)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePermiRoleHasTPermiFunction(java.math.BigInteger id){
//		return sqlSession.delete("permiRoleHasTPermiFunctionBase.delete_permiRoleHasTPermiFunction", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(角色功能关系)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePermiRoleHasTPermiFunctionBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("permiRoleHasTPermiFunctionBase.delete_permiRoleHasTPermiFunction_Batch", idList);
//	}
	
	
}
