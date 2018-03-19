package com.cnfantasia.server.domainbase.userQuestion.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.userQuestion.entity.UserQuestion;

/**
 * 描述:(用户提问信息表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class UserQuestionBaseDao extends AbstractBaseDao implements IUserQuestionBaseDao{
	/**
	 * 根据条件查询(用户提问信息表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<UserQuestion> selectUserQuestionByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("userQuestionBase.select_userQuestion",paramMap);
	}
	/**
	 * 按条件分页查询(用户提问信息表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<UserQuestion> selectUserQuestionByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectUserQuestionCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<UserQuestion> resMap= sqlSession.selectList("userQuestionBase.select_userQuestion_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(用户提问信息表)信息
	 * @param id
	 * @return
	 */
	@Override
	public UserQuestion selectUserQuestionBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("userQuestionBase.select_userQuestion_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(用户提问信息表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectUserQuestionCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("userQuestionBase.select_userQuestion_count", paramMap);
	}
	/**
	 * 往(用户提问信息表)新增一条记录
	 * @param userQuestion
	 * @return
	 */
	@Override
	public int insertUserQuestion(UserQuestion userQuestion){
		return sqlSession.insert("userQuestionBase.insert_userQuestion",userQuestion);
	}
	/**
	 * 批量新增(用户提问信息表)信息
	 * @param userQuestionList
	 * @return
	 */
	@Override
	public int insertUserQuestionBatch(List<UserQuestion> userQuestionList) {
		if (userQuestionList == null || userQuestionList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = userQuestionList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<UserQuestion> batchList = userQuestionList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("userQuestionBase.insert_userQuestion_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(用户提问信息表)信息
	 * @param userQuestion
	 * @return
	 */
	@Override
	public int updateUserQuestion(UserQuestion userQuestion){
		return sqlSession.update("userQuestionBase.update_userQuestion", userQuestion);
	}
	/**
	 * 批量更新(用户提问信息表)信息
	 * @param userQuestionList
	 * @return
	 */
	@Override
	public int updateUserQuestionBatch(List<UserQuestion> userQuestionList) {
		if (userQuestionList == null || userQuestionList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("userQuestionBase.update_userQuestion_Batch", userQuestionList);
	}
	
	/**
	 * 根据序列号删除(用户提问信息表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteUserQuestionLogic(java.math.BigInteger id){
		UserQuestion userQuestion = new UserQuestion();
		userQuestion.setId(id);
		userQuestion.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("userQuestionBase.delete_userQuestion_Logic",userQuestion);
	}
	
	/**
	 * 根据Id 批量删除(用户提问信息表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteUserQuestionLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<UserQuestion> delList = new java.util.ArrayList<UserQuestion>();
		for(java.math.BigInteger id:idList){
			UserQuestion userQuestion = new UserQuestion();
			userQuestion.setId(id);
			userQuestion.setSys0DelState(RecordState_DELETED);
			delList.add(userQuestion);
		}
		return sqlSession.update("userQuestionBase.delete_userQuestion_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(用户提问信息表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteUserQuestion(java.math.BigInteger id){
//		return sqlSession.delete("userQuestionBase.delete_userQuestion", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(用户提问信息表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteUserQuestionBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("userQuestionBase.delete_userQuestion_Batch", idList);
//	}
	
	
}
