package com.cnfantasia.server.domainbase.propertyGbAd.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyGbAd.entity.PropertyGbAd;

/**
 * 描述:(小区物业打印广告配置表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PropertyGbAdBaseDao extends AbstractBaseDao implements IPropertyGbAdBaseDao{
	/**
	 * 根据条件查询(小区物业打印广告配置表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PropertyGbAd> selectPropertyGbAdByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("propertyGbAdBase.select_propertyGbAd",paramMap);
	}
	/**
	 * 按条件分页查询(小区物业打印广告配置表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PropertyGbAd> selectPropertyGbAdByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPropertyGbAdCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PropertyGbAd> resMap= sqlSession.selectList("propertyGbAdBase.select_propertyGbAd_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(小区物业打印广告配置表)信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertyGbAd selectPropertyGbAdBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("propertyGbAdBase.select_propertyGbAd_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(小区物业打印广告配置表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPropertyGbAdCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("propertyGbAdBase.select_propertyGbAd_count", paramMap);
	}
	/**
	 * 往(小区物业打印广告配置表)新增一条记录
	 * @param propertyGbAd
	 * @return
	 */
	@Override
	public int insertPropertyGbAd(PropertyGbAd propertyGbAd){
		return sqlSession.insert("propertyGbAdBase.insert_propertyGbAd",propertyGbAd);
	}
	/**
	 * 批量新增(小区物业打印广告配置表)信息
	 * @param propertyGbAdList
	 * @return
	 */
	@Override
	public int insertPropertyGbAdBatch(List<PropertyGbAd> propertyGbAdList) {
		return sqlSession.insert("propertyGbAdBase.insert_propertyGbAd_Batch",propertyGbAdList);
	}
	
	/**
	 * 更新(小区物业打印广告配置表)信息
	 * @param propertyGbAd
	 * @return
	 */
	@Override
	public int updatePropertyGbAd(PropertyGbAd propertyGbAd){
		return sqlSession.update("propertyGbAdBase.update_propertyGbAd", propertyGbAd);
	}
	/**
	 * 批量更新(小区物业打印广告配置表)信息
	 * @param propertyGbAdList
	 * @return
	 */
	@Override
	public int updatePropertyGbAdBatch(List<PropertyGbAd> propertyGbAdList) {
		return sqlSession.update("propertyGbAdBase.update_propertyGbAd_Batch", propertyGbAdList);
	}
	
	/**
	 * 根据序列号删除(小区物业打印广告配置表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePropertyGbAdLogic(java.math.BigInteger id){
		PropertyGbAd propertyGbAd = new PropertyGbAd();
		propertyGbAd.setId(id);
		propertyGbAd.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("propertyGbAdBase.delete_propertyGbAd_Logic",propertyGbAd);
	}
	
	/**
	 * 根据Id 批量删除(小区物业打印广告配置表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePropertyGbAdLogicBatch(List<java.math.BigInteger> idList) {
		List<PropertyGbAd> delList = new java.util.ArrayList<PropertyGbAd>();
		for(java.math.BigInteger id:idList){
			PropertyGbAd propertyGbAd = new PropertyGbAd();
			propertyGbAd.setId(id);
			propertyGbAd.setSys0DelState(RecordState_DELETED);
			delList.add(propertyGbAd);
		}
		return sqlSession.update("propertyGbAdBase.delete_propertyGbAd_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(小区物业打印广告配置表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertyGbAd(java.math.BigInteger id){
//		return sqlSession.delete("propertyGbAdBase.delete_propertyGbAd", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(小区物业打印广告配置表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertyGbAdBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("propertyGbAdBase.delete_propertyGbAd_Batch", idList);
//	}
	
	
}
