package com.cnfantasia.server.domainbase.userHasTAchievement.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.userHasTAchievement.entity.UserHasTAchievement;

/**
 * 描述:(用户成就关系表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class UserHasTAchievementBaseDao extends AbstractBaseDao implements IUserHasTAchievementBaseDao{
	/**
	 * 根据条件查询(用户成就关系表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<UserHasTAchievement> selectUserHasTAchievementByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("userHasTAchievementBase.select_userHasTAchievement",paramMap);
	}
	/**
	 * 按条件分页查询(用户成就关系表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<UserHasTAchievement> selectUserHasTAchievementByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectUserHasTAchievementCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<UserHasTAchievement> resMap= sqlSession.selectList("userHasTAchievementBase.select_userHasTAchievement_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(用户成就关系表)信息
	 * @param id
	 * @return
	 */
	@Override
	public UserHasTAchievement selectUserHasTAchievementBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("userHasTAchievementBase.select_userHasTAchievement_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(用户成就关系表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectUserHasTAchievementCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("userHasTAchievementBase.select_userHasTAchievement_count", paramMap);
	}
	/**
	 * 往(用户成就关系表)新增一条记录
	 * @param userHasTAchievement
	 * @return
	 */
	@Override
	public int insertUserHasTAchievement(UserHasTAchievement userHasTAchievement){
		return sqlSession.insert("userHasTAchievementBase.insert_userHasTAchievement",userHasTAchievement);
	}
	/**
	 * 批量新增(用户成就关系表)信息
	 * @param userHasTAchievementList
	 * @return
	 */
	@Override
	public int insertUserHasTAchievementBatch(List<UserHasTAchievement> userHasTAchievementList) {
		return sqlSession.insert("userHasTAchievementBase.insert_userHasTAchievement_Batch",userHasTAchievementList);
	}
	
	/**
	 * 更新(用户成就关系表)信息
	 * @param userHasTAchievement
	 * @return
	 */
	@Override
	public int updateUserHasTAchievement(UserHasTAchievement userHasTAchievement){
		return sqlSession.update("userHasTAchievementBase.update_userHasTAchievement", userHasTAchievement);
	}
	/**
	 * 批量更新(用户成就关系表)信息
	 * @param userHasTAchievementList
	 * @return
	 */
	@Override
	public int updateUserHasTAchievementBatch(List<UserHasTAchievement> userHasTAchievementList) {
		return sqlSession.update("userHasTAchievementBase.update_userHasTAchievement_Batch", userHasTAchievementList);
	}
	
	/**
	 * 根据序列号删除(用户成就关系表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteUserHasTAchievementLogic(java.math.BigInteger id){
		UserHasTAchievement userHasTAchievement = new UserHasTAchievement();
		userHasTAchievement.setId(id);
		userHasTAchievement.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("userHasTAchievementBase.delete_userHasTAchievement_Logic",userHasTAchievement);
	}
	
	/**
	 * 根据Id 批量删除(用户成就关系表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteUserHasTAchievementLogicBatch(List<java.math.BigInteger> idList) {
		List<UserHasTAchievement> delList = new java.util.ArrayList<UserHasTAchievement>();
		for(java.math.BigInteger id:idList){
			UserHasTAchievement userHasTAchievement = new UserHasTAchievement();
			userHasTAchievement.setId(id);
			userHasTAchievement.setSys0DelState(RecordState_DELETED);
			delList.add(userHasTAchievement);
		}
		return sqlSession.update("userHasTAchievementBase.delete_userHasTAchievement_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(用户成就关系表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteUserHasTAchievement(java.math.BigInteger id){
//		return sqlSession.delete("userHasTAchievementBase.delete_userHasTAchievement", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(用户成就关系表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteUserHasTAchievementBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("userHasTAchievementBase.delete_userHasTAchievement_Batch", idList);
//	}
	
	
}
