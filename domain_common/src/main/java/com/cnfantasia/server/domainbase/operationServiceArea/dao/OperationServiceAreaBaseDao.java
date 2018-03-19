package com.cnfantasia.server.domainbase.operationServiceArea.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.operationServiceArea.entity.OperationServiceArea;

/**
 * 描述:(服务范围的运营数据表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class OperationServiceAreaBaseDao extends AbstractBaseDao implements IOperationServiceAreaBaseDao{
	/**
	 * 根据条件查询(服务范围的运营数据表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<OperationServiceArea> selectOperationServiceAreaByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("operationServiceAreaBase.select_operationServiceArea",paramMap);
	}
	/**
	 * 按条件分页查询(服务范围的运营数据表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<OperationServiceArea> selectOperationServiceAreaByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectOperationServiceAreaCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<OperationServiceArea> resMap= sqlSession.selectList("operationServiceAreaBase.select_operationServiceArea_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(服务范围的运营数据表)信息
	 * @param id
	 * @return
	 */
	@Override
	public OperationServiceArea selectOperationServiceAreaBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("operationServiceAreaBase.select_operationServiceArea_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(服务范围的运营数据表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectOperationServiceAreaCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("operationServiceAreaBase.select_operationServiceArea_count", paramMap);
	}
	/**
	 * 往(服务范围的运营数据表)新增一条记录
	 * @param operationServiceArea
	 * @return
	 */
	@Override
	public int insertOperationServiceArea(OperationServiceArea operationServiceArea){
		return sqlSession.insert("operationServiceAreaBase.insert_operationServiceArea",operationServiceArea);
	}
	/**
	 * 批量新增(服务范围的运营数据表)信息
	 * @param operationServiceAreaList
	 * @return
	 */
	@Override
	public int insertOperationServiceAreaBatch(List<OperationServiceArea> operationServiceAreaList) {
		return sqlSession.insert("operationServiceAreaBase.insert_operationServiceArea_Batch",operationServiceAreaList);
	}
	
	/**
	 * 更新(服务范围的运营数据表)信息
	 * @param operationServiceArea
	 * @return
	 */
	@Override
	public int updateOperationServiceArea(OperationServiceArea operationServiceArea){
		return sqlSession.update("operationServiceAreaBase.update_operationServiceArea", operationServiceArea);
	}
	/**
	 * 批量更新(服务范围的运营数据表)信息
	 * @param operationServiceAreaList
	 * @return
	 */
	@Override
	public int updateOperationServiceAreaBatch(List<OperationServiceArea> operationServiceAreaList) {
		return sqlSession.update("operationServiceAreaBase.update_operationServiceArea_Batch", operationServiceAreaList);
	}
	
	/**
	 * 根据序列号删除(服务范围的运营数据表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteOperationServiceAreaLogic(java.math.BigInteger id){
		OperationServiceArea operationServiceArea = new OperationServiceArea();
		operationServiceArea.setId(id);
		operationServiceArea.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("operationServiceAreaBase.delete_operationServiceArea_Logic",operationServiceArea);
	}
	
	/**
	 * 根据Id 批量删除(服务范围的运营数据表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteOperationServiceAreaLogicBatch(List<java.math.BigInteger> idList) {
		List<OperationServiceArea> delList = new java.util.ArrayList<OperationServiceArea>();
		for(java.math.BigInteger id:idList){
			OperationServiceArea operationServiceArea = new OperationServiceArea();
			operationServiceArea.setId(id);
			operationServiceArea.setSys0DelState(RecordState_DELETED);
			delList.add(operationServiceArea);
		}
		return sqlSession.update("operationServiceAreaBase.delete_operationServiceArea_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(服务范围的运营数据表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteOperationServiceArea(java.math.BigInteger id){
//		return sqlSession.delete("operationServiceAreaBase.delete_operationServiceArea", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(服务范围的运营数据表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteOperationServiceAreaBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("operationServiceAreaBase.delete_operationServiceArea_Batch", idList);
//	}
	
	
}
