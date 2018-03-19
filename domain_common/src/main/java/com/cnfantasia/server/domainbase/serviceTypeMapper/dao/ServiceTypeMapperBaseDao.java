package com.cnfantasia.server.domainbase.serviceTypeMapper.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.serviceTypeMapper.entity.ServiceTypeMapper;

/**
 * 描述:(与第三方供应商类型对应关系) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class ServiceTypeMapperBaseDao extends AbstractBaseDao implements IServiceTypeMapperBaseDao{
	/**
	 * 根据条件查询(与第三方供应商类型对应关系)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<ServiceTypeMapper> selectServiceTypeMapperByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("serviceTypeMapperBase.select_serviceTypeMapper",paramMap);
	}
	/**
	 * 按条件分页查询(与第三方供应商类型对应关系)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<ServiceTypeMapper> selectServiceTypeMapperByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectServiceTypeMapperCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<ServiceTypeMapper> resMap= sqlSession.selectList("serviceTypeMapperBase.select_serviceTypeMapper_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(与第三方供应商类型对应关系)信息
	 * @param id
	 * @return
	 */
	@Override
	public ServiceTypeMapper selectServiceTypeMapperBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("serviceTypeMapperBase.select_serviceTypeMapper_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(与第三方供应商类型对应关系)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectServiceTypeMapperCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("serviceTypeMapperBase.select_serviceTypeMapper_count", paramMap);
	}
	/**
	 * 往(与第三方供应商类型对应关系)新增一条记录
	 * @param serviceTypeMapper
	 * @return
	 */
	@Override
	public int insertServiceTypeMapper(ServiceTypeMapper serviceTypeMapper){
		return sqlSession.insert("serviceTypeMapperBase.insert_serviceTypeMapper",serviceTypeMapper);
	}
	/**
	 * 批量新增(与第三方供应商类型对应关系)信息
	 * @param serviceTypeMapperList
	 * @return
	 */
	@Override
	public int insertServiceTypeMapperBatch(List<ServiceTypeMapper> serviceTypeMapperList) {
		return sqlSession.insert("serviceTypeMapperBase.insert_serviceTypeMapper_Batch",serviceTypeMapperList);
	}
	
	/**
	 * 更新(与第三方供应商类型对应关系)信息
	 * @param serviceTypeMapper
	 * @return
	 */
	@Override
	public int updateServiceTypeMapper(ServiceTypeMapper serviceTypeMapper){
		return sqlSession.update("serviceTypeMapperBase.update_serviceTypeMapper", serviceTypeMapper);
	}
	/**
	 * 批量更新(与第三方供应商类型对应关系)信息
	 * @param serviceTypeMapperList
	 * @return
	 */
	@Override
	public int updateServiceTypeMapperBatch(List<ServiceTypeMapper> serviceTypeMapperList) {
		return sqlSession.update("serviceTypeMapperBase.update_serviceTypeMapper_Batch", serviceTypeMapperList);
	}
	
	/**
	 * 根据序列号删除(与第三方供应商类型对应关系)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteServiceTypeMapperLogic(java.math.BigInteger id){
		ServiceTypeMapper serviceTypeMapper = new ServiceTypeMapper();
		serviceTypeMapper.setId(id);
		serviceTypeMapper.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("serviceTypeMapperBase.delete_serviceTypeMapper_Logic",serviceTypeMapper);
	}
	
	/**
	 * 根据Id 批量删除(与第三方供应商类型对应关系)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteServiceTypeMapperLogicBatch(List<java.math.BigInteger> idList) {
		List<ServiceTypeMapper> delList = new java.util.ArrayList<ServiceTypeMapper>();
		for(java.math.BigInteger id:idList){
			ServiceTypeMapper serviceTypeMapper = new ServiceTypeMapper();
			serviceTypeMapper.setId(id);
			serviceTypeMapper.setSys0DelState(RecordState_DELETED);
			delList.add(serviceTypeMapper);
		}
		return sqlSession.update("serviceTypeMapperBase.delete_serviceTypeMapper_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(与第三方供应商类型对应关系)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteServiceTypeMapper(java.math.BigInteger id){
//		return sqlSession.delete("serviceTypeMapperBase.delete_serviceTypeMapper", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(与第三方供应商类型对应关系)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteServiceTypeMapperBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("serviceTypeMapperBase.delete_serviceTypeMapper_Batch", idList);
//	}
	
	
}
