package com.cnfantasia.server.domainbase.userPushInfo.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.userPushInfo.entity.UserPushInfo;

/**
 * 描述:(用户推送配置消息) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class UserPushInfoBaseDao extends AbstractBaseDao implements IUserPushInfoBaseDao{
	/**
	 * 根据条件查询(用户推送配置消息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<UserPushInfo> selectUserPushInfoByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("userPushInfoBase.select_userPushInfo",paramMap);
	}
	/**
	 * 按条件分页查询(用户推送配置消息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<UserPushInfo> selectUserPushInfoByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectUserPushInfoCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<UserPushInfo> resMap= sqlSession.selectList("userPushInfoBase.select_userPushInfo_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(用户推送配置消息)信息
	 * @param id
	 * @return
	 */
	@Override
	public UserPushInfo selectUserPushInfoBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("userPushInfoBase.select_userPushInfo_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(用户推送配置消息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectUserPushInfoCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("userPushInfoBase.select_userPushInfo_count", paramMap);
	}
	/**
	 * 往(用户推送配置消息)新增一条记录
	 * @param userPushInfo
	 * @return
	 */
	@Override
	public int insertUserPushInfo(UserPushInfo userPushInfo){
		return sqlSession.insert("userPushInfoBase.insert_userPushInfo",userPushInfo);
	}
	/**
	 * 批量新增(用户推送配置消息)信息
	 * @param userPushInfoList
	 * @return
	 */
	@Override
	public int insertUserPushInfoBatch(List<UserPushInfo> userPushInfoList) {
		return sqlSession.insert("userPushInfoBase.insert_userPushInfo_Batch",userPushInfoList);
	}
	
	/**
	 * 更新(用户推送配置消息)信息
	 * @param userPushInfo
	 * @return
	 */
	@Override
	public int updateUserPushInfo(UserPushInfo userPushInfo){
		return sqlSession.update("userPushInfoBase.update_userPushInfo", userPushInfo);
	}
	/**
	 * 批量更新(用户推送配置消息)信息
	 * @param userPushInfoList
	 * @return
	 */
	@Override
	public int updateUserPushInfoBatch(List<UserPushInfo> userPushInfoList) {
		return sqlSession.update("userPushInfoBase.update_userPushInfo_Batch", userPushInfoList);
	}
	
	/**
	 * 根据序列号删除(用户推送配置消息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteUserPushInfoLogic(java.math.BigInteger id){
		UserPushInfo userPushInfo = new UserPushInfo();
		userPushInfo.setId(id);
		userPushInfo.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("userPushInfoBase.delete_userPushInfo_Logic",userPushInfo);
	}
	
	/**
	 * 根据Id 批量删除(用户推送配置消息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteUserPushInfoLogicBatch(List<java.math.BigInteger> idList) {
		List<UserPushInfo> delList = new java.util.ArrayList<UserPushInfo>();
		for(java.math.BigInteger id:idList){
			UserPushInfo userPushInfo = new UserPushInfo();
			userPushInfo.setId(id);
			userPushInfo.setSys0DelState(RecordState_DELETED);
			delList.add(userPushInfo);
		}
		return sqlSession.update("userPushInfoBase.delete_userPushInfo_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(用户推送配置消息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteUserPushInfo(java.math.BigInteger id){
//		return sqlSession.delete("userPushInfoBase.delete_userPushInfo", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(用户推送配置消息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteUserPushInfoBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("userPushInfoBase.delete_userPushInfo_Batch", idList);
//	}
	
	
}
