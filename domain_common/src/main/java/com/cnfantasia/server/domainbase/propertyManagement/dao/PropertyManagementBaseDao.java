package com.cnfantasia.server.domainbase.propertyManagement.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyManagement.entity.PropertyManagement;

/**
 * 描述:(物业管理) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PropertyManagementBaseDao extends AbstractBaseDao implements IPropertyManagementBaseDao{
	/**
	 * 根据条件查询(物业管理)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PropertyManagement> selectPropertyManagementByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("propertyManagementBase.select_propertyManagement",paramMap);
	}
	/**
	 * 按条件分页查询(物业管理)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PropertyManagement> selectPropertyManagementByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPropertyManagementCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PropertyManagement> resMap= sqlSession.selectList("propertyManagementBase.select_propertyManagement_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(物业管理)信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertyManagement selectPropertyManagementBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("propertyManagementBase.select_propertyManagement_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(物业管理)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPropertyManagementCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("propertyManagementBase.select_propertyManagement_count", paramMap);
	}
	/**
	 * 往(物业管理)新增一条记录
	 * @param propertyManagement
	 * @return
	 */
	@Override
	public int insertPropertyManagement(PropertyManagement propertyManagement){
		return sqlSession.insert("propertyManagementBase.insert_propertyManagement",propertyManagement);
	}
	/**
	 * 批量新增(物业管理)信息
	 * @param propertyManagementList
	 * @return
	 */
	@Override
	public int insertPropertyManagementBatch(List<PropertyManagement> propertyManagementList) {
		return sqlSession.insert("propertyManagementBase.insert_propertyManagement_Batch",propertyManagementList);
	}
	
	/**
	 * 更新(物业管理)信息
	 * @param propertyManagement
	 * @return
	 */
	@Override
	public int updatePropertyManagement(PropertyManagement propertyManagement){
		return sqlSession.update("propertyManagementBase.update_propertyManagement", propertyManagement);
	}
	/**
	 * 批量更新(物业管理)信息
	 * @param propertyManagementList
	 * @return
	 */
	@Override
	public int updatePropertyManagementBatch(List<PropertyManagement> propertyManagementList) {
		return sqlSession.update("propertyManagementBase.update_propertyManagement_Batch", propertyManagementList);
	}
	
	/**
	 * 根据序列号删除(物业管理)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePropertyManagementLogic(java.math.BigInteger id){
		PropertyManagement propertyManagement = new PropertyManagement();
		propertyManagement.setId(id);
		propertyManagement.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("propertyManagementBase.delete_propertyManagement_Logic",propertyManagement);
	}
	
	/**
	 * 根据Id 批量删除(物业管理)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePropertyManagementLogicBatch(List<java.math.BigInteger> idList) {
		List<PropertyManagement> delList = new java.util.ArrayList<PropertyManagement>();
		for(java.math.BigInteger id:idList){
			PropertyManagement propertyManagement = new PropertyManagement();
			propertyManagement.setId(id);
			propertyManagement.setSys0DelState(RecordState_DELETED);
			delList.add(propertyManagement);
		}
		return sqlSession.update("propertyManagementBase.delete_propertyManagement_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(物业管理)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertyManagement(java.math.BigInteger id){
//		return sqlSession.delete("propertyManagementBase.delete_propertyManagement", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业管理)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertyManagementBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("propertyManagementBase.delete_propertyManagement_Batch", idList);
//	}
	
	
}
