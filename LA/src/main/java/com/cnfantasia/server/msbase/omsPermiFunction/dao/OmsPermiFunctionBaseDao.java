package com.cnfantasia.server.msbase.omsPermiFunction.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.msbase.omsPermiFunction.entity.OmsPermiFunction;

/**
 * 描述:(OMS功能表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class OmsPermiFunctionBaseDao extends AbstractBaseDao implements IOmsPermiFunctionBaseDao{
	/**
	 * 根据条件查询(OMS功能表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<OmsPermiFunction> selectOmsPermiFunctionByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("omsPermiFunctionBase.select_omsPermiFunction",paramMap);
	}
	/**
	 * 按条件分页查询(OMS功能表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<OmsPermiFunction> selectOmsPermiFunctionByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectOmsPermiFunctionCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<OmsPermiFunction> resMap= sqlSession.selectList("omsPermiFunctionBase.select_omsPermiFunction_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(OMS功能表)信息
	 * @param id
	 * @return
	 */
	@Override
	public OmsPermiFunction selectOmsPermiFunctionBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("omsPermiFunctionBase.select_omsPermiFunction_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(OMS功能表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectOmsPermiFunctionCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("omsPermiFunctionBase.select_omsPermiFunction_count", paramMap);
	}
	/**
	 * 往(OMS功能表)新增一条记录
	 * @param omsPermiFunction
	 * @return
	 */
	@Override
	public int insertOmsPermiFunction(OmsPermiFunction omsPermiFunction){
		return sqlSession.insert("omsPermiFunctionBase.insert_omsPermiFunction",omsPermiFunction);
	}
	/**
	 * 批量新增(OMS功能表)信息
	 * @param omsPermiFunctionList
	 * @return
	 */
	@Override
	public int insertOmsPermiFunctionBatch(List<OmsPermiFunction> omsPermiFunctionList) {
		return sqlSession.insert("omsPermiFunctionBase.insert_omsPermiFunction_Batch",omsPermiFunctionList);
	}
	
	/**
	 * 更新(OMS功能表)信息
	 * @param omsPermiFunction
	 * @return
	 */
	@Override
	public int updateOmsPermiFunction(OmsPermiFunction omsPermiFunction){
		return sqlSession.update("omsPermiFunctionBase.update_omsPermiFunction", omsPermiFunction);
	}
	/**
	 * 批量更新(OMS功能表)信息
	 * @param omsPermiFunctionList
	 * @return
	 */
	@Override
	public int updateOmsPermiFunctionBatch(List<OmsPermiFunction> omsPermiFunctionList) {
		return sqlSession.update("omsPermiFunctionBase.update_omsPermiFunction_Batch", omsPermiFunctionList);
	}
	
	/**
	 * 根据序列号删除(OMS功能表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteOmsPermiFunctionLogic(java.math.BigInteger id){
		OmsPermiFunction omsPermiFunction = new OmsPermiFunction();
		omsPermiFunction.setId(id);
		omsPermiFunction.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("omsPermiFunctionBase.delete_omsPermiFunction_Logic",omsPermiFunction);
	}
	
	/**
	 * 根据Id 批量删除(OMS功能表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteOmsPermiFunctionLogicBatch(List<java.math.BigInteger> idList) {
		List<OmsPermiFunction> delList = new java.util.ArrayList<OmsPermiFunction>();
		for(java.math.BigInteger id:idList){
			OmsPermiFunction omsPermiFunction = new OmsPermiFunction();
			omsPermiFunction.setId(id);
			omsPermiFunction.setSys0DelState(RecordState_DELETED);
			delList.add(omsPermiFunction);
		}
		return sqlSession.update("omsPermiFunctionBase.delete_omsPermiFunction_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(OMS功能表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteOmsPermiFunction(java.math.BigInteger id){
//		return sqlSession.delete("omsPermiFunctionBase.delete_omsPermiFunction", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(OMS功能表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteOmsPermiFunctionBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("omsPermiFunctionBase.delete_omsPermiFunction_Batch", idList);
//	}
	
	
}
