package com.cnfantasia.server.domainbase.cloudKeyUserData.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.cloudKeyUserData.entity.CloudKeyUserData;

/**
 * 描述:(业主门禁认证（可配置）信息) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CloudKeyUserDataBaseDao extends AbstractBaseDao implements ICloudKeyUserDataBaseDao{
	/**
	 * 根据条件查询(业主门禁认证（可配置）信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CloudKeyUserData> selectCloudKeyUserDataByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("cloudKeyUserDataBase.select_cloudKeyUserData",paramMap);
	}
	/**
	 * 按条件分页查询(业主门禁认证（可配置）信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CloudKeyUserData> selectCloudKeyUserDataByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCloudKeyUserDataCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<CloudKeyUserData> resMap= sqlSession.selectList("cloudKeyUserDataBase.select_cloudKeyUserData_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(业主门禁认证（可配置）信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public CloudKeyUserData selectCloudKeyUserDataBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("cloudKeyUserDataBase.select_cloudKeyUserData_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(业主门禁认证（可配置）信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCloudKeyUserDataCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("cloudKeyUserDataBase.select_cloudKeyUserData_count", paramMap);
	}
	/**
	 * 往(业主门禁认证（可配置）信息)新增一条记录
	 * @param cloudKeyUserData
	 * @return
	 */
	@Override
	public int insertCloudKeyUserData(CloudKeyUserData cloudKeyUserData){
		return sqlSession.insert("cloudKeyUserDataBase.insert_cloudKeyUserData",cloudKeyUserData);
	}
	/**
	 * 批量新增(业主门禁认证（可配置）信息)信息
	 * @param cloudKeyUserDataList
	 * @return
	 */
	@Override
	public int insertCloudKeyUserDataBatch(List<CloudKeyUserData> cloudKeyUserDataList) {
		return sqlSession.insert("cloudKeyUserDataBase.insert_cloudKeyUserData_Batch",cloudKeyUserDataList);
	}
	
	/**
	 * 更新(业主门禁认证（可配置）信息)信息
	 * @param cloudKeyUserData
	 * @return
	 */
	@Override
	public int updateCloudKeyUserData(CloudKeyUserData cloudKeyUserData){
		return sqlSession.update("cloudKeyUserDataBase.update_cloudKeyUserData", cloudKeyUserData);
	}
	/**
	 * 批量更新(业主门禁认证（可配置）信息)信息
	 * @param cloudKeyUserDataList
	 * @return
	 */
	@Override
	public int updateCloudKeyUserDataBatch(List<CloudKeyUserData> cloudKeyUserDataList) {
		return sqlSession.update("cloudKeyUserDataBase.update_cloudKeyUserData_Batch", cloudKeyUserDataList);
	}
	
	/**
	 * 根据序列号删除(业主门禁认证（可配置）信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	@Override
	public int deleteCloudKeyUserDataLogic(java.math.BigInteger id){
		CloudKeyUserData cloudKeyUserData = new CloudKeyUserData();
		cloudKeyUserData.setId(id);
		cloudKeyUserData.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("cloudKeyUserDataBase.delete_cloudKeyUserData_Logic",cloudKeyUserData);
	}
	 */
	/**
	 * 根据Id 批量删除(业主门禁认证（可配置）信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	@Override
	public int deleteCloudKeyUserDataLogicBatch(List<java.math.BigInteger> idList) {
		List<CloudKeyUserData> delList = new java.util.ArrayList<CloudKeyUserData>();
		for(java.math.BigInteger id:idList){
			CloudKeyUserData cloudKeyUserData = new CloudKeyUserData();
			cloudKeyUserData.setId(id);
			cloudKeyUserData.setSys0DelState(RecordState_DELETED);
			delList.add(cloudKeyUserData);
		}
		return sqlSession.update("cloudKeyUserDataBase.delete_cloudKeyUserData_Logic_Batch",delList);
	}
	 */
//	/**
//	 * 根据序列号删除(业主门禁认证（可配置）信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCloudKeyUserData(java.math.BigInteger id){
//		return sqlSession.delete("cloudKeyUserDataBase.delete_cloudKeyUserData", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(业主门禁认证（可配置）信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCloudKeyUserDataBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("cloudKeyUserDataBase.delete_cloudKeyUserData_Batch", idList);
//	}
	
	
}
