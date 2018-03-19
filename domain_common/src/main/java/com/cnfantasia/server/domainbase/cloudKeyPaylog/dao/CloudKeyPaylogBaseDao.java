package com.cnfantasia.server.domainbase.cloudKeyPaylog.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.cloudKeyPaylog.entity.CloudKeyPaylog;

/**
 * 描述:(云钥匙付款记录) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CloudKeyPaylogBaseDao extends AbstractBaseDao implements ICloudKeyPaylogBaseDao{
	/**
	 * 根据条件查询(云钥匙付款记录)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CloudKeyPaylog> selectCloudKeyPaylogByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("cloudKeyPaylogBase.select_cloudKeyPaylog",paramMap);
	}
	/**
	 * 按条件分页查询(云钥匙付款记录)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CloudKeyPaylog> selectCloudKeyPaylogByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCloudKeyPaylogCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<CloudKeyPaylog> resMap= sqlSession.selectList("cloudKeyPaylogBase.select_cloudKeyPaylog_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(云钥匙付款记录)信息
	 * @param id
	 * @return
	 */
	@Override
	public CloudKeyPaylog selectCloudKeyPaylogBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("cloudKeyPaylogBase.select_cloudKeyPaylog_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(云钥匙付款记录)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCloudKeyPaylogCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("cloudKeyPaylogBase.select_cloudKeyPaylog_count", paramMap);
	}
	/**
	 * 往(云钥匙付款记录)新增一条记录
	 * @param cloudKeyPaylog
	 * @return
	 */
	@Override
	public int insertCloudKeyPaylog(CloudKeyPaylog cloudKeyPaylog){
		return sqlSession.insert("cloudKeyPaylogBase.insert_cloudKeyPaylog",cloudKeyPaylog);
	}
	/**
	 * 批量新增(云钥匙付款记录)信息
	 * @param cloudKeyPaylogList
	 * @return
	 */
	@Override
	public int insertCloudKeyPaylogBatch(List<CloudKeyPaylog> cloudKeyPaylogList) {
		return sqlSession.insert("cloudKeyPaylogBase.insert_cloudKeyPaylog_Batch",cloudKeyPaylogList);
	}
	
	/**
	 * 更新(云钥匙付款记录)信息
	 * @param cloudKeyPaylog
	 * @return
	 */
	@Override
	public int updateCloudKeyPaylog(CloudKeyPaylog cloudKeyPaylog){
		return sqlSession.update("cloudKeyPaylogBase.update_cloudKeyPaylog", cloudKeyPaylog);
	}
	/**
	 * 批量更新(云钥匙付款记录)信息
	 * @param cloudKeyPaylogList
	 * @return
	 */
	@Override
	public int updateCloudKeyPaylogBatch(List<CloudKeyPaylog> cloudKeyPaylogList) {
		return sqlSession.update("cloudKeyPaylogBase.update_cloudKeyPaylog_Batch", cloudKeyPaylogList);
	}
	
	/**
	 * 根据序列号删除(云钥匙付款记录)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCloudKeyPaylogLogic(java.math.BigInteger id){
		CloudKeyPaylog cloudKeyPaylog = new CloudKeyPaylog();
		cloudKeyPaylog.setId(id);
		cloudKeyPaylog.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("cloudKeyPaylogBase.delete_cloudKeyPaylog_Logic",cloudKeyPaylog);
	}
	
	/**
	 * 根据Id 批量删除(云钥匙付款记录)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCloudKeyPaylogLogicBatch(List<java.math.BigInteger> idList) {
		List<CloudKeyPaylog> delList = new java.util.ArrayList<CloudKeyPaylog>();
		for(java.math.BigInteger id:idList){
			CloudKeyPaylog cloudKeyPaylog = new CloudKeyPaylog();
			cloudKeyPaylog.setId(id);
			cloudKeyPaylog.setSys0DelState(RecordState_DELETED);
			delList.add(cloudKeyPaylog);
		}
		return sqlSession.update("cloudKeyPaylogBase.delete_cloudKeyPaylog_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(云钥匙付款记录)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCloudKeyPaylog(java.math.BigInteger id){
//		return sqlSession.delete("cloudKeyPaylogBase.delete_cloudKeyPaylog", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(云钥匙付款记录)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCloudKeyPaylogBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("cloudKeyPaylogBase.delete_cloudKeyPaylog_Batch", idList);
//	}
	
	
}
