package com.cnfantasia.server.domainbase.operationConstantData.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.operationConstantData.entity.OperationConstantData;

/**
 * 描述:(运维相关的数据) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class OperationConstantDataBaseDao extends AbstractBaseDao implements IOperationConstantDataBaseDao{
	/**
	 * 根据条件查询(运维相关的数据)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<OperationConstantData> selectOperationConstantDataByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("operationConstantDataBase.select_operationConstantData",paramMap);
	}
	/**
	 * 按条件分页查询(运维相关的数据)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<OperationConstantData> selectOperationConstantDataByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectOperationConstantDataCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<OperationConstantData> resMap= sqlSession.selectList("operationConstantDataBase.select_operationConstantData_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(运维相关的数据)信息
	 * @param id
	 * @return
	 */
	@Override
	public OperationConstantData selectOperationConstantDataBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("operationConstantDataBase.select_operationConstantData_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(运维相关的数据)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectOperationConstantDataCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("operationConstantDataBase.select_operationConstantData_count", paramMap);
	}
	/**
	 * 往(运维相关的数据)新增一条记录
	 * @param operationConstantData
	 * @return
	 */
	@Override
	public int insertOperationConstantData(OperationConstantData operationConstantData){
		return sqlSession.insert("operationConstantDataBase.insert_operationConstantData",operationConstantData);
	}
	/**
	 * 批量新增(运维相关的数据)信息
	 * @param operationConstantDataList
	 * @return
	 */
	@Override
	public int insertOperationConstantDataBatch(List<OperationConstantData> operationConstantDataList) {
		return sqlSession.insert("operationConstantDataBase.insert_operationConstantData_Batch",operationConstantDataList);
	}
	
	/**
	 * 更新(运维相关的数据)信息
	 * @param operationConstantData
	 * @return
	 */
	@Override
	public int updateOperationConstantData(OperationConstantData operationConstantData){
		return sqlSession.update("operationConstantDataBase.update_operationConstantData", operationConstantData);
	}
	/**
	 * 批量更新(运维相关的数据)信息
	 * @param operationConstantDataList
	 * @return
	 */
	@Override
	public int updateOperationConstantDataBatch(List<OperationConstantData> operationConstantDataList) {
		return sqlSession.update("operationConstantDataBase.update_operationConstantData_Batch", operationConstantDataList);
	}
	
	/**
	 * 根据序列号删除(运维相关的数据)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteOperationConstantDataLogic(java.math.BigInteger id){
		OperationConstantData operationConstantData = new OperationConstantData();
		operationConstantData.setId(id);
		operationConstantData.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("operationConstantDataBase.delete_operationConstantData_Logic",operationConstantData);
	}
	
	/**
	 * 根据Id 批量删除(运维相关的数据)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteOperationConstantDataLogicBatch(List<java.math.BigInteger> idList) {
		List<OperationConstantData> delList = new java.util.ArrayList<OperationConstantData>();
		for(java.math.BigInteger id:idList){
			OperationConstantData operationConstantData = new OperationConstantData();
			operationConstantData.setId(id);
			operationConstantData.setSys0DelState(RecordState_DELETED);
			delList.add(operationConstantData);
		}
		return sqlSession.update("operationConstantDataBase.delete_operationConstantData_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(运维相关的数据)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteOperationConstantData(java.math.BigInteger id){
//		return sqlSession.delete("operationConstantDataBase.delete_operationConstantData", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(运维相关的数据)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteOperationConstantDataBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("operationConstantDataBase.delete_operationConstantData_Batch", idList);
//	}
	
	
}
