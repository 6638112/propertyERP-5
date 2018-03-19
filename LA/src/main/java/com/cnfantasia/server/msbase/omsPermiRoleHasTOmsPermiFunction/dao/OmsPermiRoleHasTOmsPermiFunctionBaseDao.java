package com.cnfantasia.server.msbase.omsPermiRoleHasTOmsPermiFunction.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.msbase.omsPermiRoleHasTOmsPermiFunction.entity.OmsPermiRoleHasTOmsPermiFunction;

/**
 * 描述:(OMS角色功能关系) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class OmsPermiRoleHasTOmsPermiFunctionBaseDao extends AbstractBaseDao implements IOmsPermiRoleHasTOmsPermiFunctionBaseDao{
	/**
	 * 根据条件查询(OMS角色功能关系)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<OmsPermiRoleHasTOmsPermiFunction> selectOmsPermiRoleHasTOmsPermiFunctionByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("omsPermiRoleHasTOmsPermiFunctionBase.select_omsPermiRoleHasTOmsPermiFunction",paramMap);
	}
	/**
	 * 按条件分页查询(OMS角色功能关系)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<OmsPermiRoleHasTOmsPermiFunction> selectOmsPermiRoleHasTOmsPermiFunctionByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectOmsPermiRoleHasTOmsPermiFunctionCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<OmsPermiRoleHasTOmsPermiFunction> resMap= sqlSession.selectList("omsPermiRoleHasTOmsPermiFunctionBase.select_omsPermiRoleHasTOmsPermiFunction_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(OMS角色功能关系)信息
	 * @param id
	 * @return
	 */
	@Override
	public OmsPermiRoleHasTOmsPermiFunction selectOmsPermiRoleHasTOmsPermiFunctionBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("omsPermiRoleHasTOmsPermiFunctionBase.select_omsPermiRoleHasTOmsPermiFunction_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(OMS角色功能关系)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectOmsPermiRoleHasTOmsPermiFunctionCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("omsPermiRoleHasTOmsPermiFunctionBase.select_omsPermiRoleHasTOmsPermiFunction_count", paramMap);
	}
	/**
	 * 往(OMS角色功能关系)新增一条记录
	 * @param omsPermiRoleHasTOmsPermiFunction
	 * @return
	 */
	@Override
	public int insertOmsPermiRoleHasTOmsPermiFunction(OmsPermiRoleHasTOmsPermiFunction omsPermiRoleHasTOmsPermiFunction){
		return sqlSession.insert("omsPermiRoleHasTOmsPermiFunctionBase.insert_omsPermiRoleHasTOmsPermiFunction",omsPermiRoleHasTOmsPermiFunction);
	}
	/**
	 * 批量新增(OMS角色功能关系)信息
	 * @param omsPermiRoleHasTOmsPermiFunctionList
	 * @return
	 */
	@Override
	public int insertOmsPermiRoleHasTOmsPermiFunctionBatch(List<OmsPermiRoleHasTOmsPermiFunction> omsPermiRoleHasTOmsPermiFunctionList) {
		return sqlSession.insert("omsPermiRoleHasTOmsPermiFunctionBase.insert_omsPermiRoleHasTOmsPermiFunction_Batch",omsPermiRoleHasTOmsPermiFunctionList);
	}
	
	/**
	 * 更新(OMS角色功能关系)信息
	 * @param omsPermiRoleHasTOmsPermiFunction
	 * @return
	 */
	@Override
	public int updateOmsPermiRoleHasTOmsPermiFunction(OmsPermiRoleHasTOmsPermiFunction omsPermiRoleHasTOmsPermiFunction){
		return sqlSession.update("omsPermiRoleHasTOmsPermiFunctionBase.update_omsPermiRoleHasTOmsPermiFunction", omsPermiRoleHasTOmsPermiFunction);
	}
	/**
	 * 批量更新(OMS角色功能关系)信息
	 * @param omsPermiRoleHasTOmsPermiFunctionList
	 * @return
	 */
	@Override
	public int updateOmsPermiRoleHasTOmsPermiFunctionBatch(List<OmsPermiRoleHasTOmsPermiFunction> omsPermiRoleHasTOmsPermiFunctionList) {
		return sqlSession.update("omsPermiRoleHasTOmsPermiFunctionBase.update_omsPermiRoleHasTOmsPermiFunction_Batch", omsPermiRoleHasTOmsPermiFunctionList);
	}
	
	/**
	 * 根据序列号删除(OMS角色功能关系)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteOmsPermiRoleHasTOmsPermiFunctionLogic(java.math.BigInteger id){
		OmsPermiRoleHasTOmsPermiFunction omsPermiRoleHasTOmsPermiFunction = new OmsPermiRoleHasTOmsPermiFunction();
		omsPermiRoleHasTOmsPermiFunction.setId(id);
		omsPermiRoleHasTOmsPermiFunction.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("omsPermiRoleHasTOmsPermiFunctionBase.delete_omsPermiRoleHasTOmsPermiFunction_Logic",omsPermiRoleHasTOmsPermiFunction);
	}
	
	/**
	 * 根据Id 批量删除(OMS角色功能关系)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteOmsPermiRoleHasTOmsPermiFunctionLogicBatch(List<java.math.BigInteger> idList) {
		List<OmsPermiRoleHasTOmsPermiFunction> delList = new java.util.ArrayList<OmsPermiRoleHasTOmsPermiFunction>();
		for(java.math.BigInteger id:idList){
			OmsPermiRoleHasTOmsPermiFunction omsPermiRoleHasTOmsPermiFunction = new OmsPermiRoleHasTOmsPermiFunction();
			omsPermiRoleHasTOmsPermiFunction.setId(id);
			omsPermiRoleHasTOmsPermiFunction.setSys0DelState(RecordState_DELETED);
			delList.add(omsPermiRoleHasTOmsPermiFunction);
		}
		return sqlSession.update("omsPermiRoleHasTOmsPermiFunctionBase.delete_omsPermiRoleHasTOmsPermiFunction_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(OMS角色功能关系)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteOmsPermiRoleHasTOmsPermiFunction(java.math.BigInteger id){
//		return sqlSession.delete("omsPermiRoleHasTOmsPermiFunctionBase.delete_omsPermiRoleHasTOmsPermiFunction", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(OMS角色功能关系)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteOmsPermiRoleHasTOmsPermiFunctionBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("omsPermiRoleHasTOmsPermiFunctionBase.delete_omsPermiRoleHasTOmsPermiFunction_Batch", idList);
//	}
	
	
}
