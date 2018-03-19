package com.cnfantasia.server.domainbase.propertyRepairType.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyRepairType.entity.PropertyRepairType;

/**
 * 描述:(物业报修类型) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PropertyRepairTypeBaseDao extends AbstractBaseDao implements IPropertyRepairTypeBaseDao{
	/**
	 * 根据条件查询(物业报修类型)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PropertyRepairType> selectPropertyRepairTypeByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("propertyRepairTypeBase.select_propertyRepairType",paramMap);
	}
	/**
	 * 按条件分页查询(物业报修类型)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PropertyRepairType> selectPropertyRepairTypeByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPropertyRepairTypeCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PropertyRepairType> resMap= sqlSession.selectList("propertyRepairTypeBase.select_propertyRepairType_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(物业报修类型)信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertyRepairType selectPropertyRepairTypeBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("propertyRepairTypeBase.select_propertyRepairType_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(物业报修类型)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPropertyRepairTypeCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("propertyRepairTypeBase.select_propertyRepairType_count", paramMap);
	}
	/**
	 * 往(物业报修类型)新增一条记录
	 * @param propertyRepairType
	 * @return
	 */
	@Override
	public int insertPropertyRepairType(PropertyRepairType propertyRepairType){
		return sqlSession.insert("propertyRepairTypeBase.insert_propertyRepairType",propertyRepairType);
	}
	/**
	 * 批量新增(物业报修类型)信息
	 * @param propertyRepairTypeList
	 * @return
	 */
	@Override
	public int insertPropertyRepairTypeBatch(List<PropertyRepairType> propertyRepairTypeList) {
		return sqlSession.insert("propertyRepairTypeBase.insert_propertyRepairType_Batch",propertyRepairTypeList);
	}
	
	/**
	 * 更新(物业报修类型)信息
	 * @param propertyRepairType
	 * @return
	 */
	@Override
	public int updatePropertyRepairType(PropertyRepairType propertyRepairType){
		return sqlSession.update("propertyRepairTypeBase.update_propertyRepairType", propertyRepairType);
	}
	/**
	 * 批量更新(物业报修类型)信息
	 * @param propertyRepairTypeList
	 * @return
	 */
	@Override
	public int updatePropertyRepairTypeBatch(List<PropertyRepairType> propertyRepairTypeList) {
		return sqlSession.update("propertyRepairTypeBase.update_propertyRepairType_Batch", propertyRepairTypeList);
	}
	
	/**
	 * 根据序列号删除(物业报修类型)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePropertyRepairTypeLogic(java.math.BigInteger id){
		PropertyRepairType propertyRepairType = new PropertyRepairType();
		propertyRepairType.setId(id);
		propertyRepairType.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("propertyRepairTypeBase.delete_propertyRepairType_Logic",propertyRepairType);
	}
	
	/**
	 * 根据Id 批量删除(物业报修类型)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePropertyRepairTypeLogicBatch(List<java.math.BigInteger> idList) {
		List<PropertyRepairType> delList = new java.util.ArrayList<PropertyRepairType>();
		for(java.math.BigInteger id:idList){
			PropertyRepairType propertyRepairType = new PropertyRepairType();
			propertyRepairType.setId(id);
			propertyRepairType.setSys0DelState(RecordState_DELETED);
			delList.add(propertyRepairType);
		}
		return sqlSession.update("propertyRepairTypeBase.delete_propertyRepairType_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(物业报修类型)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertyRepairType(java.math.BigInteger id){
//		return sqlSession.delete("propertyRepairTypeBase.delete_propertyRepairType", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业报修类型)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertyRepairTypeBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("propertyRepairTypeBase.delete_propertyRepairType_Batch", idList);
//	}
	
	
}
