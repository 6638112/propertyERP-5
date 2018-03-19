package com.cnfantasia.server.domainbase.propertyService.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyService.entity.PropertyService;

/**
 * 描述:(物业服务信息) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PropertyServiceBaseDao extends AbstractBaseDao implements IPropertyServiceBaseDao{
	/**
	 * 根据条件查询(物业服务信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PropertyService> selectPropertyServiceByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("propertyServiceBase.select_propertyService",paramMap);
	}
	/**
	 * 按条件分页查询(物业服务信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PropertyService> selectPropertyServiceByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPropertyServiceCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PropertyService> resMap= sqlSession.selectList("propertyServiceBase.select_propertyService_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(物业服务信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertyService selectPropertyServiceBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("propertyServiceBase.select_propertyService_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(物业服务信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPropertyServiceCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("propertyServiceBase.select_propertyService_count", paramMap);
	}
	/**
	 * 往(物业服务信息)新增一条记录
	 * @param propertyService
	 * @return
	 */
	@Override
	public int insertPropertyService(PropertyService propertyService){
		return sqlSession.insert("propertyServiceBase.insert_propertyService",propertyService);
	}
	/**
	 * 批量新增(物业服务信息)信息
	 * @param propertyServiceList
	 * @return
	 */
	@Override
	public int insertPropertyServiceBatch(List<PropertyService> propertyServiceList) {
		return sqlSession.insert("propertyServiceBase.insert_propertyService_Batch",propertyServiceList);
	}
	
	/**
	 * 更新(物业服务信息)信息
	 * @param propertyService
	 * @return
	 */
	@Override
	public int updatePropertyService(PropertyService propertyService){
		return sqlSession.update("propertyServiceBase.update_propertyService", propertyService);
	}
	/**
	 * 批量更新(物业服务信息)信息
	 * @param propertyServiceList
	 * @return
	 */
	@Override
	public int updatePropertyServiceBatch(List<PropertyService> propertyServiceList) {
		return sqlSession.update("propertyServiceBase.update_propertyService_Batch", propertyServiceList);
	}
	
	/**
	 * 根据序列号删除(物业服务信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePropertyServiceLogic(java.math.BigInteger id){
		PropertyService propertyService = new PropertyService();
		propertyService.setId(id);
		propertyService.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("propertyServiceBase.delete_propertyService_Logic",propertyService);
	}
	
	/**
	 * 根据Id 批量删除(物业服务信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePropertyServiceLogicBatch(List<java.math.BigInteger> idList) {
		List<PropertyService> delList = new java.util.ArrayList<PropertyService>();
		for(java.math.BigInteger id:idList){
			PropertyService propertyService = new PropertyService();
			propertyService.setId(id);
			propertyService.setSys0DelState(RecordState_DELETED);
			delList.add(propertyService);
		}
		return sqlSession.update("propertyServiceBase.delete_propertyService_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(物业服务信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertyService(java.math.BigInteger id){
//		return sqlSession.delete("propertyServiceBase.delete_propertyService", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业服务信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertyServiceBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("propertyServiceBase.delete_propertyService_Batch", idList);
//	}
	
	
}
