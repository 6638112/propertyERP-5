package com.cnfantasia.server.domainbase.userHasTHobby.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.userHasTHobby.entity.UserHasTHobby;

/**
 * 描述:(用户爱好关系表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class UserHasTHobbyBaseDao extends AbstractBaseDao implements IUserHasTHobbyBaseDao{
	/**
	 * 根据条件查询(用户爱好关系表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<UserHasTHobby> selectUserHasTHobbyByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("userHasTHobbyBase.select_userHasTHobby",paramMap);
	}
	/**
	 * 按条件分页查询(用户爱好关系表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<UserHasTHobby> selectUserHasTHobbyByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectUserHasTHobbyCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<UserHasTHobby> resMap= sqlSession.selectList("userHasTHobbyBase.select_userHasTHobby_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(用户爱好关系表)信息
	 * @param id
	 * @return
	 */
	@Override
	public UserHasTHobby selectUserHasTHobbyBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("userHasTHobbyBase.select_userHasTHobby_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(用户爱好关系表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectUserHasTHobbyCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("userHasTHobbyBase.select_userHasTHobby_count", paramMap);
	}
	/**
	 * 往(用户爱好关系表)新增一条记录
	 * @param userHasTHobby
	 * @return
	 */
	@Override
	public int insertUserHasTHobby(UserHasTHobby userHasTHobby){
		return sqlSession.insert("userHasTHobbyBase.insert_userHasTHobby",userHasTHobby);
	}
	/**
	 * 批量新增(用户爱好关系表)信息
	 * @param userHasTHobbyList
	 * @return
	 */
	@Override
	public int insertUserHasTHobbyBatch(List<UserHasTHobby> userHasTHobbyList) {
		return sqlSession.insert("userHasTHobbyBase.insert_userHasTHobby_Batch",userHasTHobbyList);
	}
	
	/**
	 * 更新(用户爱好关系表)信息
	 * @param userHasTHobby
	 * @return
	 */
	@Override
	public int updateUserHasTHobby(UserHasTHobby userHasTHobby){
		return sqlSession.update("userHasTHobbyBase.update_userHasTHobby", userHasTHobby);
	}
	/**
	 * 批量更新(用户爱好关系表)信息
	 * @param userHasTHobbyList
	 * @return
	 */
	@Override
	public int updateUserHasTHobbyBatch(List<UserHasTHobby> userHasTHobbyList) {
		return sqlSession.update("userHasTHobbyBase.update_userHasTHobby_Batch", userHasTHobbyList);
	}
	
	/**
	 * 根据序列号删除(用户爱好关系表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteUserHasTHobbyLogic(java.math.BigInteger id){
		UserHasTHobby userHasTHobby = new UserHasTHobby();
		userHasTHobby.setId(id);
		userHasTHobby.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("userHasTHobbyBase.delete_userHasTHobby_Logic",userHasTHobby);
	}
	
	/**
	 * 根据Id 批量删除(用户爱好关系表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteUserHasTHobbyLogicBatch(List<java.math.BigInteger> idList) {
		List<UserHasTHobby> delList = new java.util.ArrayList<UserHasTHobby>();
		for(java.math.BigInteger id:idList){
			UserHasTHobby userHasTHobby = new UserHasTHobby();
			userHasTHobby.setId(id);
			userHasTHobby.setSys0DelState(RecordState_DELETED);
			delList.add(userHasTHobby);
		}
		return sqlSession.update("userHasTHobbyBase.delete_userHasTHobby_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(用户爱好关系表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteUserHasTHobby(java.math.BigInteger id){
//		return sqlSession.delete("userHasTHobbyBase.delete_userHasTHobby", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(用户爱好关系表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteUserHasTHobbyBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("userHasTHobbyBase.delete_userHasTHobby_Batch", idList);
//	}
	
	
}
