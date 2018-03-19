package com.cnfantasia.server.domainbase.remoteUser.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.remoteUser.entity.RemoteUser;

/**
 * 描述:(远程用户信息) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class RemoteUserBaseDao extends AbstractBaseDao implements IRemoteUserBaseDao{
	/**
	 * 根据条件查询(远程用户信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<RemoteUser> selectRemoteUserByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("remoteUserBase.select_remoteUser",paramMap);
	}
	/**
	 * 按条件分页查询(远程用户信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<RemoteUser> selectRemoteUserByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectRemoteUserCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<RemoteUser> resMap= sqlSession.selectList("remoteUserBase.select_remoteUser_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(远程用户信息)信息
	 * @param uid
	 * @return
	 */
	@Override
	public RemoteUser selectRemoteUserBySeqId(java.lang.Long uid){
		return sqlSession.selectOne("remoteUserBase.select_remoteUser_bySeqId",uid);
	}
	/**
	 * 根据条件查询满足条件的(远程用户信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectRemoteUserCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("remoteUserBase.select_remoteUser_count", paramMap);
	}
	/**
	 * 往(远程用户信息)新增一条记录
	 * @param remoteUser
	 * @return
	 */
	@Override
	public int insertRemoteUser(RemoteUser remoteUser){
		return sqlSession.insert("remoteUserBase.insert_remoteUser",remoteUser);
	}
	/**
	 * 批量新增(远程用户信息)信息
	 * @param remoteUserList
	 * @return
	 */
	@Override
	public int insertRemoteUserBatch(List<RemoteUser> remoteUserList) {
		return sqlSession.insert("remoteUserBase.insert_remoteUser_Batch",remoteUserList);
	}
	
	/**
	 * 更新(远程用户信息)信息
	 * @param remoteUser
	 * @return
	 */
	@Override
	public int updateRemoteUser(RemoteUser remoteUser){
		return sqlSession.update("remoteUserBase.update_remoteUser", remoteUser);
	}
	/**
	 * 批量更新(远程用户信息)信息
	 * @param remoteUserList
	 * @return
	 */
	@Override
	public int updateRemoteUserBatch(List<RemoteUser> remoteUserList) {
		return sqlSession.update("remoteUserBase.update_remoteUser_Batch", remoteUserList);
	}
	
	/**
	 * 根据序列号删除(远程用户信息)信息_逻辑删除
	 * @param uid
	 * @return
	 */
	/* 
	@Override
	public int deleteRemoteUserLogic(java.lang.Long uid){
		RemoteUser remoteUser = new RemoteUser();
		remoteUser.setUid(uid);
		remoteUser.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("remoteUserBase.delete_remoteUser_Logic",remoteUser);
	}
	 */
	/**
	 * 根据Id 批量删除(远程用户信息)信息_逻辑删除
	 * @param uidList
	 * @return
	 */
	/* 
	@Override
	public int deleteRemoteUserLogicBatch(List<java.lang.Long> uidList) {
		List<RemoteUser> delList = new java.util.ArrayList<RemoteUser>();
		for(java.lang.Long uid:uidList){
			RemoteUser remoteUser = new RemoteUser();
			remoteUser.setUid(uid);
			remoteUser.setSys0DelState(RecordState_DELETED);
			delList.add(remoteUser);
		}
		return sqlSession.update("remoteUserBase.delete_remoteUser_Logic_Batch",delList);
	}
	 */
//	/**
//	 * 根据序列号删除(远程用户信息)信息
//	 * @param uid
//	 * @return
//	 */
//	@Override
//	public int deleteRemoteUser(java.lang.Long uid){
//		return sqlSession.delete("remoteUserBase.delete_remoteUser", uid);
//	}
//	
//	/**
//	 * 根据序列号批量删除(远程用户信息)信息
//	 * @param uidList
//	 * @return
//	 */
//	@Override
//	public int deleteRemoteUserBatch(List<java.lang.Long> uidList) {
//		return sqlSession.delete("remoteUserBase.delete_remoteUser_Batch", uidList);
//	}
	
	
}
