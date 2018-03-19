package com.cnfantasia.server.domainbase.cloudKeyUserAudit.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.cloudKeyUserAudit.entity.CloudKeyUserAudit;

/**
 * 描述:(业主门禁认证信息) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CloudKeyUserAuditBaseDao extends AbstractBaseDao implements ICloudKeyUserAuditBaseDao{
	/**
	 * 根据条件查询(业主门禁认证信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CloudKeyUserAudit> selectCloudKeyUserAuditByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("cloudKeyUserAuditBase.select_cloudKeyUserAudit",paramMap);
	}
	/**
	 * 按条件分页查询(业主门禁认证信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CloudKeyUserAudit> selectCloudKeyUserAuditByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCloudKeyUserAuditCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<CloudKeyUserAudit> resMap= sqlSession.selectList("cloudKeyUserAuditBase.select_cloudKeyUserAudit_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(业主门禁认证信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public CloudKeyUserAudit selectCloudKeyUserAuditBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("cloudKeyUserAuditBase.select_cloudKeyUserAudit_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(业主门禁认证信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCloudKeyUserAuditCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("cloudKeyUserAuditBase.select_cloudKeyUserAudit_count", paramMap);
	}
	/**
	 * 往(业主门禁认证信息)新增一条记录
	 * @param cloudKeyUserAudit
	 * @return
	 */
	@Override
	public int insertCloudKeyUserAudit(CloudKeyUserAudit cloudKeyUserAudit){
		return sqlSession.insert("cloudKeyUserAuditBase.insert_cloudKeyUserAudit",cloudKeyUserAudit);
	}
	/**
	 * 批量新增(业主门禁认证信息)信息
	 * @param cloudKeyUserAuditList
	 * @return
	 */
	@Override
	public int insertCloudKeyUserAuditBatch(List<CloudKeyUserAudit> cloudKeyUserAuditList) {
		return sqlSession.insert("cloudKeyUserAuditBase.insert_cloudKeyUserAudit_Batch",cloudKeyUserAuditList);
	}
	
	/**
	 * 更新(业主门禁认证信息)信息
	 * @param cloudKeyUserAudit
	 * @return
	 */
	@Override
	public int updateCloudKeyUserAudit(CloudKeyUserAudit cloudKeyUserAudit){
		return sqlSession.update("cloudKeyUserAuditBase.update_cloudKeyUserAudit", cloudKeyUserAudit);
	}
	/**
	 * 批量更新(业主门禁认证信息)信息
	 * @param cloudKeyUserAuditList
	 * @return
	 */
	@Override
	public int updateCloudKeyUserAuditBatch(List<CloudKeyUserAudit> cloudKeyUserAuditList) {
		return sqlSession.update("cloudKeyUserAuditBase.update_cloudKeyUserAudit_Batch", cloudKeyUserAuditList);
	}
	
	/**
	 * 根据序列号删除(业主门禁认证信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCloudKeyUserAuditLogic(java.math.BigInteger id){
		CloudKeyUserAudit cloudKeyUserAudit = new CloudKeyUserAudit();
		cloudKeyUserAudit.setId(id);
		cloudKeyUserAudit.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("cloudKeyUserAuditBase.delete_cloudKeyUserAudit_Logic",cloudKeyUserAudit);
	}
	
	/**
	 * 根据Id 批量删除(业主门禁认证信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCloudKeyUserAuditLogicBatch(List<java.math.BigInteger> idList) {
		List<CloudKeyUserAudit> delList = new java.util.ArrayList<CloudKeyUserAudit>();
		for(java.math.BigInteger id:idList){
			CloudKeyUserAudit cloudKeyUserAudit = new CloudKeyUserAudit();
			cloudKeyUserAudit.setId(id);
			cloudKeyUserAudit.setSys0DelState(RecordState_DELETED);
			delList.add(cloudKeyUserAudit);
		}
		return sqlSession.update("cloudKeyUserAuditBase.delete_cloudKeyUserAudit_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(业主门禁认证信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCloudKeyUserAudit(java.math.BigInteger id){
//		return sqlSession.delete("cloudKeyUserAuditBase.delete_cloudKeyUserAudit", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(业主门禁认证信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCloudKeyUserAuditBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("cloudKeyUserAuditBase.delete_cloudKeyUserAudit_Batch", idList);
//	}
	
	
}
