package com.cnfantasia.server.domainbase.cloudKeyUserList.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.cloudKeyUserList.entity.CloudKeyUserList;

/**
 * 描述:(业主门禁密钥表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CloudKeyUserListBaseDao extends AbstractBaseDao implements ICloudKeyUserListBaseDao{
	/**
	 * 根据条件查询(业主门禁密钥表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CloudKeyUserList> selectCloudKeyUserListByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("cloudKeyUserListBase.select_cloudKeyUserList",paramMap);
	}
	/**
	 * 按条件分页查询(业主门禁密钥表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CloudKeyUserList> selectCloudKeyUserListByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCloudKeyUserListCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<CloudKeyUserList> resMap= sqlSession.selectList("cloudKeyUserListBase.select_cloudKeyUserList_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(业主门禁密钥表)信息
	 * @param id
	 * @return
	 */
	@Override
	public CloudKeyUserList selectCloudKeyUserListBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("cloudKeyUserListBase.select_cloudKeyUserList_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(业主门禁密钥表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCloudKeyUserListCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("cloudKeyUserListBase.select_cloudKeyUserList_count", paramMap);
	}
	/**
	 * 往(业主门禁密钥表)新增一条记录
	 * @param cloudKeyUserList
	 * @return
	 */
	@Override
	public int insertCloudKeyUserList(CloudKeyUserList cloudKeyUserList){
		return sqlSession.insert("cloudKeyUserListBase.insert_cloudKeyUserList",cloudKeyUserList);
	}
	/**
	 * 批量新增(业主门禁密钥表)信息
	 * @param cloudKeyUserListList
	 * @return
	 */
	@Override
	public int insertCloudKeyUserListBatch(List<CloudKeyUserList> cloudKeyUserListList) {
		return sqlSession.insert("cloudKeyUserListBase.insert_cloudKeyUserList_Batch",cloudKeyUserListList);
	}
	
	/**
	 * 更新(业主门禁密钥表)信息
	 * @param cloudKeyUserList
	 * @return
	 */
	@Override
	public int updateCloudKeyUserList(CloudKeyUserList cloudKeyUserList){
		return sqlSession.update("cloudKeyUserListBase.update_cloudKeyUserList", cloudKeyUserList);
	}
	/**
	 * 批量更新(业主门禁密钥表)信息
	 * @param cloudKeyUserListList
	 * @return
	 */
	@Override
	public int updateCloudKeyUserListBatch(List<CloudKeyUserList> cloudKeyUserListList) {
		return sqlSession.update("cloudKeyUserListBase.update_cloudKeyUserList_Batch", cloudKeyUserListList);
	}
	
	/**
	 * 根据序列号删除(业主门禁密钥表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCloudKeyUserListLogic(java.math.BigInteger id){
		CloudKeyUserList cloudKeyUserList = new CloudKeyUserList();
		cloudKeyUserList.setId(id);
		cloudKeyUserList.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("cloudKeyUserListBase.delete_cloudKeyUserList_Logic",cloudKeyUserList);
	}
	
	/**
	 * 根据Id 批量删除(业主门禁密钥表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCloudKeyUserListLogicBatch(List<java.math.BigInteger> idList) {
		List<CloudKeyUserList> delList = new java.util.ArrayList<CloudKeyUserList>();
		for(java.math.BigInteger id:idList){
			CloudKeyUserList cloudKeyUserList = new CloudKeyUserList();
			cloudKeyUserList.setId(id);
			cloudKeyUserList.setSys0DelState(RecordState_DELETED);
			delList.add(cloudKeyUserList);
		}
		return sqlSession.update("cloudKeyUserListBase.delete_cloudKeyUserList_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(业主门禁密钥表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCloudKeyUserList(java.math.BigInteger id){
//		return sqlSession.delete("cloudKeyUserListBase.delete_cloudKeyUserList", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(业主门禁密钥表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCloudKeyUserListBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("cloudKeyUserListBase.delete_cloudKeyUserList_Batch", idList);
//	}
	
	
}
