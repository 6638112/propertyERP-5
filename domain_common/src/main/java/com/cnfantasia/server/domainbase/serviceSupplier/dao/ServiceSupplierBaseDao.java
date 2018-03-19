package com.cnfantasia.server.domainbase.serviceSupplier.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.serviceSupplier.entity.ServiceSupplier;

/**
 * 描述:(上门维修第三方服务供应商) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class ServiceSupplierBaseDao extends AbstractBaseDao implements IServiceSupplierBaseDao{
	/**
	 * 根据条件查询(上门维修第三方服务供应商)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<ServiceSupplier> selectServiceSupplierByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("serviceSupplierBase.select_serviceSupplier",paramMap);
	}
	/**
	 * 按条件分页查询(上门维修第三方服务供应商)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<ServiceSupplier> selectServiceSupplierByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectServiceSupplierCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<ServiceSupplier> resMap= sqlSession.selectList("serviceSupplierBase.select_serviceSupplier_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(上门维修第三方服务供应商)信息
	 * @param id
	 * @return
	 */
	@Override
	public ServiceSupplier selectServiceSupplierBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("serviceSupplierBase.select_serviceSupplier_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(上门维修第三方服务供应商)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectServiceSupplierCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("serviceSupplierBase.select_serviceSupplier_count", paramMap);
	}
	/**
	 * 往(上门维修第三方服务供应商)新增一条记录
	 * @param serviceSupplier
	 * @return
	 */
	@Override
	public int insertServiceSupplier(ServiceSupplier serviceSupplier){
		return sqlSession.insert("serviceSupplierBase.insert_serviceSupplier",serviceSupplier);
	}
	/**
	 * 批量新增(上门维修第三方服务供应商)信息
	 * @param serviceSupplierList
	 * @return
	 */
	@Override
	public int insertServiceSupplierBatch(List<ServiceSupplier> serviceSupplierList) {
		return sqlSession.insert("serviceSupplierBase.insert_serviceSupplier_Batch",serviceSupplierList);
	}
	
	/**
	 * 更新(上门维修第三方服务供应商)信息
	 * @param serviceSupplier
	 * @return
	 */
	@Override
	public int updateServiceSupplier(ServiceSupplier serviceSupplier){
		return sqlSession.update("serviceSupplierBase.update_serviceSupplier", serviceSupplier);
	}
	/**
	 * 批量更新(上门维修第三方服务供应商)信息
	 * @param serviceSupplierList
	 * @return
	 */
	@Override
	public int updateServiceSupplierBatch(List<ServiceSupplier> serviceSupplierList) {
		return sqlSession.update("serviceSupplierBase.update_serviceSupplier_Batch", serviceSupplierList);
	}
	
	/**
	 * 根据序列号删除(上门维修第三方服务供应商)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteServiceSupplierLogic(java.math.BigInteger id){
		ServiceSupplier serviceSupplier = new ServiceSupplier();
		serviceSupplier.setId(id);
		serviceSupplier.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("serviceSupplierBase.delete_serviceSupplier_Logic",serviceSupplier);
	}
	
	/**
	 * 根据Id 批量删除(上门维修第三方服务供应商)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteServiceSupplierLogicBatch(List<java.math.BigInteger> idList) {
		List<ServiceSupplier> delList = new java.util.ArrayList<ServiceSupplier>();
		for(java.math.BigInteger id:idList){
			ServiceSupplier serviceSupplier = new ServiceSupplier();
			serviceSupplier.setId(id);
			serviceSupplier.setSys0DelState(RecordState_DELETED);
			delList.add(serviceSupplier);
		}
		return sqlSession.update("serviceSupplierBase.delete_serviceSupplier_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(上门维修第三方服务供应商)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteServiceSupplier(java.math.BigInteger id){
//		return sqlSession.delete("serviceSupplierBase.delete_serviceSupplier", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(上门维修第三方服务供应商)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteServiceSupplierBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("serviceSupplierBase.delete_serviceSupplier_Batch", idList);
//	}
	
	
}
