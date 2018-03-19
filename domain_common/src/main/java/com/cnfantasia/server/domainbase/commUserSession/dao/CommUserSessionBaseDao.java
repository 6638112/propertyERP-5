package com.cnfantasia.server.domainbase.commUserSession.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.commUserSession.entity.CommUserSession;

/**
 * 描述:(用户登录session表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CommUserSessionBaseDao extends AbstractBaseDao implements ICommUserSessionBaseDao{
	/**
	 * 根据条件查询(用户登录session表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommUserSession> selectCommUserSessionByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("commUserSessionBase.select_commUserSession",paramMap);
	}
	/**
	 * 按条件分页查询(用户登录session表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CommUserSession> selectCommUserSessionByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCommUserSessionCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<CommUserSession> resMap= sqlSession.selectList("commUserSessionBase.select_commUserSession_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(用户登录session表)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommUserSession selectCommUserSessionBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("commUserSessionBase.select_commUserSession_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(用户登录session表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCommUserSessionCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("commUserSessionBase.select_commUserSession_count", paramMap);
	}
	/**
	 * 往(用户登录session表)新增一条记录
	 * @param commUserSession
	 * @return
	 */
	@Override
	public int insertCommUserSession(CommUserSession commUserSession){
		return sqlSession.insert("commUserSessionBase.insert_commUserSession",commUserSession);
	}
	/**
	 * 批量新增(用户登录session表)信息
	 * @param commUserSessionList
	 * @return
	 */
	@Override
	public int insertCommUserSessionBatch(List<CommUserSession> commUserSessionList) {
		return sqlSession.insert("commUserSessionBase.insert_commUserSession_Batch",commUserSessionList);
	}
	
	/**
	 * 更新(用户登录session表)信息
	 * @param commUserSession
	 * @return
	 */
	@Override
	public int updateCommUserSession(CommUserSession commUserSession){
		return sqlSession.update("commUserSessionBase.update_commUserSession", commUserSession);
	}
	/**
	 * 批量更新(用户登录session表)信息
	 * @param commUserSessionList
	 * @return
	 */
	@Override
	public int updateCommUserSessionBatch(List<CommUserSession> commUserSessionList) {
		return sqlSession.update("commUserSessionBase.update_commUserSession_Batch", commUserSessionList);
	}
	
	/**
	 * 根据序列号删除(用户登录session表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommUserSessionLogic(java.math.BigInteger id){
		CommUserSession commUserSession = new CommUserSession();
		commUserSession.setId(id);
		commUserSession.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("commUserSessionBase.delete_commUserSession_Logic",commUserSession);
	}
	
	/**
	 * 根据Id 批量删除(用户登录session表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommUserSessionLogicBatch(List<java.math.BigInteger> idList) {
		List<CommUserSession> delList = new java.util.ArrayList<CommUserSession>();
		for(java.math.BigInteger id:idList){
			CommUserSession commUserSession = new CommUserSession();
			commUserSession.setId(id);
			commUserSession.setSys0DelState(RecordState_DELETED);
			delList.add(commUserSession);
		}
		return sqlSession.update("commUserSessionBase.delete_commUserSession_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(用户登录session表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommUserSession(java.math.BigInteger id){
//		return sqlSession.delete("commUserSessionBase.delete_commUserSession", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(用户登录session表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommUserSessionBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("commUserSessionBase.delete_commUserSession_Batch", idList);
//	}
	
	
}
