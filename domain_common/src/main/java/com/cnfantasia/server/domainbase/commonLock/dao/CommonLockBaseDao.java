package com.cnfantasia.server.domainbase.commonLock.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.commonLock.entity.CommonLock;

/**
 * 描述:(公用的锁表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CommonLockBaseDao extends AbstractBaseDao implements ICommonLockBaseDao{
	/**
	 * 根据条件查询(公用的锁表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommonLock> selectCommonLockByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("commonLockBase.select_commonLock",paramMap);
	}
	/**
	 * 按条件分页查询(公用的锁表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommonLock> selectCommonLockByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCommonLockCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<CommonLock> resMap= sqlSession.selectList("commonLockBase.select_commonLock_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(公用的锁表)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommonLock selectCommonLockBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("commonLockBase.select_commonLock_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(公用的锁表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCommonLockCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("commonLockBase.select_commonLock_count", paramMap);
	}
	/**
	 * 往(公用的锁表)新增一条记录
	 * @param commonLock
	 * @return
	 */
	@Override
	public int insertCommonLock(CommonLock commonLock){
		return sqlSession.insert("commonLockBase.insert_commonLock",commonLock);
	}
	/**
	 * 批量新增(公用的锁表)信息
	 * @param commonLockList
	 * @return
	 */
	@Override
	public int insertCommonLockBatch(List<CommonLock> commonLockList) {
		return sqlSession.insert("commonLockBase.insert_commonLock_Batch",commonLockList);
	}
	
	/**
	 * 更新(公用的锁表)信息
	 * @param commonLock
	 * @return
	 */
	@Override
	public int updateCommonLock(CommonLock commonLock){
		return sqlSession.update("commonLockBase.update_commonLock", commonLock);
	}
	/**
	 * 批量更新(公用的锁表)信息
	 * @param commonLockList
	 * @return
	 */
	@Override
	public int updateCommonLockBatch(List<CommonLock> commonLockList) {
		return sqlSession.update("commonLockBase.update_commonLock_Batch", commonLockList);
	}
	
	/**
	 * 根据序列号删除(公用的锁表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	@Override
	public int deleteCommonLockLogic(java.math.BigInteger id){
		CommonLock commonLock = new CommonLock();
		commonLock.setId(id);
		commonLock.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("commonLockBase.delete_commonLock_Logic",commonLock);
	}
	 */
	/**
	 * 根据Id 批量删除(公用的锁表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	@Override
	public int deleteCommonLockLogicBatch(List<java.math.BigInteger> idList) {
		List<CommonLock> delList = new java.util.ArrayList<CommonLock>();
		for(java.math.BigInteger id:idList){
			CommonLock commonLock = new CommonLock();
			commonLock.setId(id);
			commonLock.setSys0DelState(RecordState_DELETED);
			delList.add(commonLock);
		}
		return sqlSession.update("commonLockBase.delete_commonLock_Logic_Batch",delList);
	}
	 */
//	/**
//	 * 根据序列号删除(公用的锁表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommonLock(java.math.BigInteger id){
//		return sqlSession.delete("commonLockBase.delete_commonLock", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(公用的锁表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommonLockBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("commonLockBase.delete_commonLock_Batch", idList);
//	}
	
	
}
