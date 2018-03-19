package com.cnfantasia.server.domainbase.user.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.user.entity.User;

/**
 * 描述:(用户表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class UserBaseDao extends AbstractBaseDao implements IUserBaseDao{
	/**
	 * 根据条件查询(用户表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<User> selectUserByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("userBase.select_user",paramMap);
	}
	/**
	 * 按条件分页查询(用户表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<User> selectUserByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectUserCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<User> resMap= sqlSession.selectList("userBase.select_user_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(用户表)信息
	 * @param id
	 * @return
	 */
	@Override
	public User selectUserBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("userBase.select_user_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(用户表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectUserCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("userBase.select_user_count", paramMap);
	}
	/**
	 * 往(用户表)新增一条记录
	 * @param user
	 * @return
	 */
	@Override
	public int insertUser(User user){
		return sqlSession.insert("userBase.insert_user",user);
	}
	/**
	 * 批量新增(用户表)信息
	 * @param userList
	 * @return
	 */
	@Override
	public int insertUserBatch(List<User> userList) {
		return sqlSession.insert("userBase.insert_user_Batch",userList);
	}
	
	/**
	 * 更新(用户表)信息
	 * @param user
	 * @return
	 */
	@Override
	public int updateUser(User user){
		return sqlSession.update("userBase.update_user", user);
	}
	/**
	 * 批量更新(用户表)信息
	 * @param userList
	 * @return
	 */
	@Override
	public int updateUserBatch(List<User> userList) {
		return sqlSession.update("userBase.update_user_Batch", userList);
	}
	
	/**
	 * 根据序列号删除(用户表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteUserLogic(java.math.BigInteger id){
		User user = new User();
		user.setId(id);
		user.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("userBase.delete_user_Logic",user);
	}
	
	/**
	 * 根据Id 批量删除(用户表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteUserLogicBatch(List<java.math.BigInteger> idList) {
		List<User> delList = new java.util.ArrayList<User>();
		for(java.math.BigInteger id:idList){
			User user = new User();
			user.setId(id);
			user.setSys0DelState(RecordState_DELETED);
			delList.add(user);
		}
		return sqlSession.update("userBase.delete_user_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(用户表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteUser(java.math.BigInteger id){
//		return sqlSession.delete("userBase.delete_user", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(用户表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteUserBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("userBase.delete_user_Batch", idList);
//	}
	
	
}
