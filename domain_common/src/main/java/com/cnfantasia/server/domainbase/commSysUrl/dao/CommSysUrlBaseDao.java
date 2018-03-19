package com.cnfantasia.server.domainbase.commSysUrl.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.commSysUrl.entity.CommSysUrl;

/**
 * 描述:(基础的url信息) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CommSysUrlBaseDao extends AbstractBaseDao implements ICommSysUrlBaseDao{
	/**
	 * 根据条件查询(基础的url信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommSysUrl> selectCommSysUrlByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("commSysUrlBase.select_commSysUrl",paramMap);
	}
	/**
	 * 按条件分页查询(基础的url信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommSysUrl> selectCommSysUrlByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCommSysUrlCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<CommSysUrl> resMap= sqlSession.selectList("commSysUrlBase.select_commSysUrl_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(基础的url信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommSysUrl selectCommSysUrlBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("commSysUrlBase.select_commSysUrl_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(基础的url信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCommSysUrlCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("commSysUrlBase.select_commSysUrl_count", paramMap);
	}
	/**
	 * 往(基础的url信息)新增一条记录
	 * @param commSysUrl
	 * @return
	 */
	@Override
	public int insertCommSysUrl(CommSysUrl commSysUrl){
		return sqlSession.insert("commSysUrlBase.insert_commSysUrl",commSysUrl);
	}
	/**
	 * 批量新增(基础的url信息)信息
	 * @param commSysUrlList
	 * @return
	 */
	@Override
	public int insertCommSysUrlBatch(List<CommSysUrl> commSysUrlList) {
		return sqlSession.insert("commSysUrlBase.insert_commSysUrl_Batch",commSysUrlList);
	}
	
	/**
	 * 更新(基础的url信息)信息
	 * @param commSysUrl
	 * @return
	 */
	@Override
	public int updateCommSysUrl(CommSysUrl commSysUrl){
		return sqlSession.update("commSysUrlBase.update_commSysUrl", commSysUrl);
	}
	/**
	 * 批量更新(基础的url信息)信息
	 * @param commSysUrlList
	 * @return
	 */
	@Override
	public int updateCommSysUrlBatch(List<CommSysUrl> commSysUrlList) {
		return sqlSession.update("commSysUrlBase.update_commSysUrl_Batch", commSysUrlList);
	}
	
	/**
	 * 根据序列号删除(基础的url信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommSysUrlLogic(java.math.BigInteger id){
		CommSysUrl commSysUrl = new CommSysUrl();
		commSysUrl.setId(id);
		commSysUrl.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("commSysUrlBase.delete_commSysUrl_Logic",commSysUrl);
	}
	
	/**
	 * 根据Id 批量删除(基础的url信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommSysUrlLogicBatch(List<java.math.BigInteger> idList) {
		List<CommSysUrl> delList = new java.util.ArrayList<CommSysUrl>();
		for(java.math.BigInteger id:idList){
			CommSysUrl commSysUrl = new CommSysUrl();
			commSysUrl.setId(id);
			commSysUrl.setSys0DelState(RecordState_DELETED);
			delList.add(commSysUrl);
		}
		return sqlSession.update("commSysUrlBase.delete_commSysUrl_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(基础的url信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommSysUrl(java.math.BigInteger id){
//		return sqlSession.delete("commSysUrlBase.delete_commSysUrl", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(基础的url信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommSysUrlBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("commSysUrlBase.delete_commSysUrl_Batch", idList);
//	}
	
	
}
