package com.cnfantasia.server.domainbase.achievement.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.achievement.entity.Achievement;

/**
 * 描述:(成就表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class AchievementBaseDao extends AbstractBaseDao implements IAchievementBaseDao{
	/**
	 * 根据条件查询(成就表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<Achievement> selectAchievementByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("achievementBase.select_achievement",paramMap);
	}
	/**
	 * 按条件分页查询(成就表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<Achievement> selectAchievementByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectAchievementCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<Achievement> resMap= sqlSession.selectList("achievementBase.select_achievement_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(成就表)信息
	 * @param id
	 * @return
	 */
	@Override
	public Achievement selectAchievementBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("achievementBase.select_achievement_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(成就表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectAchievementCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("achievementBase.select_achievement_count", paramMap);
	}
	/**
	 * 往(成就表)新增一条记录
	 * @param achievement
	 * @return
	 */
	@Override
	public int insertAchievement(Achievement achievement){
		return sqlSession.insert("achievementBase.insert_achievement",achievement);
	}
	/**
	 * 批量新增(成就表)信息
	 * @param achievementList
	 * @return
	 */
	@Override
	public int insertAchievementBatch(List<Achievement> achievementList) {
		return sqlSession.insert("achievementBase.insert_achievement_Batch",achievementList);
	}
	
	/**
	 * 更新(成就表)信息
	 * @param achievement
	 * @return
	 */
	@Override
	public int updateAchievement(Achievement achievement){
		return sqlSession.update("achievementBase.update_achievement", achievement);
	}
	/**
	 * 批量更新(成就表)信息
	 * @param achievementList
	 * @return
	 */
	@Override
	public int updateAchievementBatch(List<Achievement> achievementList) {
		return sqlSession.update("achievementBase.update_achievement_Batch", achievementList);
	}
	
	/**
	 * 根据序列号删除(成就表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteAchievementLogic(java.math.BigInteger id){
		Achievement achievement = new Achievement();
		achievement.setId(id);
		achievement.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("achievementBase.delete_achievement_Logic",achievement);
	}
	
	/**
	 * 根据Id 批量删除(成就表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteAchievementLogicBatch(List<java.math.BigInteger> idList) {
		List<Achievement> delList = new java.util.ArrayList<Achievement>();
		for(java.math.BigInteger id:idList){
			Achievement achievement = new Achievement();
			achievement.setId(id);
			achievement.setSys0DelState(RecordState_DELETED);
			delList.add(achievement);
		}
		return sqlSession.update("achievementBase.delete_achievement_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(成就表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteAchievement(java.math.BigInteger id){
//		return sqlSession.delete("achievementBase.delete_achievement", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(成就表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteAchievementBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("achievementBase.delete_achievement_Batch", idList);
//	}
	
	
}
