package com.cnfantasia.server.domainbase.userPayCount.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.userPayCount.entity.UserPayCount;

/**
 * 描述:() DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class UserPayCountBaseDao extends AbstractBaseDao implements IUserPayCountBaseDao{
	/**
	 * 根据条件查询()信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<UserPayCount> selectUserPayCountByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("userPayCountBase.select_userPayCount",paramMap);
	}
	/**
	 * 按条件分页查询()信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<UserPayCount> selectUserPayCountByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectUserPayCountCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<UserPayCount> resMap= sqlSession.selectList("userPayCountBase.select_userPayCount_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询()信息
	 * @param id
	 * @return
	 */
	@Override
	public UserPayCount selectUserPayCountBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("userPayCountBase.select_userPayCount_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的()记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectUserPayCountCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("userPayCountBase.select_userPayCount_count", paramMap);
	}
	/**
	 * 往()新增一条记录
	 * @param userPayCount
	 * @return
	 */
	@Override
	public int insertUserPayCount(UserPayCount userPayCount){
		return sqlSession.insert("userPayCountBase.insert_userPayCount",userPayCount);
	}
	/**
	 * 批量新增()信息
	 * @param userPayCountList
	 * @return
	 */
	@Override
	public int insertUserPayCountBatch(List<UserPayCount> userPayCountList) {
		return sqlSession.insert("userPayCountBase.insert_userPayCount_Batch",userPayCountList);
	}
	
	/**
	 * 更新()信息
	 * @param userPayCount
	 * @return
	 */
	@Override
	public int updateUserPayCount(UserPayCount userPayCount){
		return sqlSession.update("userPayCountBase.update_userPayCount", userPayCount);
	}
	/**
	 * 批量更新()信息
	 * @param userPayCountList
	 * @return
	 */
	@Override
	public int updateUserPayCountBatch(List<UserPayCount> userPayCountList) {
		return sqlSession.update("userPayCountBase.update_userPayCount_Batch", userPayCountList);
	}
	
	/**
	 * 根据序列号删除()信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteUserPayCountLogic(java.math.BigInteger id){
		UserPayCount userPayCount = new UserPayCount();
		userPayCount.setId(id);
		userPayCount.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("userPayCountBase.delete_userPayCount_Logic",userPayCount);
	}
	
	/**
	 * 根据Id 批量删除()信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteUserPayCountLogicBatch(List<java.math.BigInteger> idList) {
		List<UserPayCount> delList = new java.util.ArrayList<UserPayCount>();
		for(java.math.BigInteger id:idList){
			UserPayCount userPayCount = new UserPayCount();
			userPayCount.setId(id);
			userPayCount.setSys0DelState(RecordState_DELETED);
			delList.add(userPayCount);
		}
		return sqlSession.update("userPayCountBase.delete_userPayCount_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除()信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteUserPayCount(java.math.BigInteger id){
//		return sqlSession.delete("userPayCountBase.delete_userPayCount", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除()信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteUserPayCountBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("userPayCountBase.delete_userPayCount_Batch", idList);
//	}
	
	
}
