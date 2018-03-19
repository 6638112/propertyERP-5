package com.cnfantasia.server.domainbase.userTmp.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.userTmp.entity.UserTmp;

/**
 * 描述:(临时用户) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class UserTmpBaseDao extends AbstractBaseDao implements IUserTmpBaseDao{
	/**
	 * 根据条件查询(临时用户)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<UserTmp> selectUserTmpByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("userTmpBase.select_userTmp",paramMap);
	}
	/**
	 * 按条件分页查询(临时用户)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<UserTmp> selectUserTmpByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectUserTmpCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<UserTmp> resMap= sqlSession.selectList("userTmpBase.select_userTmp_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(临时用户)信息
	 * @param id
	 * @return
	 */
	@Override
	public UserTmp selectUserTmpBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("userTmpBase.select_userTmp_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(临时用户)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectUserTmpCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("userTmpBase.select_userTmp_count", paramMap);
	}
	/**
	 * 往(临时用户)新增一条记录
	 * @param userTmp
	 * @return
	 */
	@Override
	public int insertUserTmp(UserTmp userTmp){
		return sqlSession.insert("userTmpBase.insert_userTmp",userTmp);
	}
	/**
	 * 批量新增(临时用户)信息
	 * @param userTmpList
	 * @return
	 */
	@Override
	public int insertUserTmpBatch(List<UserTmp> userTmpList) {
		return sqlSession.insert("userTmpBase.insert_userTmp_Batch",userTmpList);
	}
	
	/**
	 * 更新(临时用户)信息
	 * @param userTmp
	 * @return
	 */
	@Override
	public int updateUserTmp(UserTmp userTmp){
		return sqlSession.update("userTmpBase.update_userTmp", userTmp);
	}
	/**
	 * 批量更新(临时用户)信息
	 * @param userTmpList
	 * @return
	 */
	@Override
	public int updateUserTmpBatch(List<UserTmp> userTmpList) {
		return sqlSession.update("userTmpBase.update_userTmp_Batch", userTmpList);
	}
	
	/**
	 * 根据序列号删除(临时用户)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteUserTmpLogic(java.math.BigInteger id){
		UserTmp userTmp = new UserTmp();
		userTmp.setId(id);
		userTmp.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("userTmpBase.delete_userTmp_Logic",userTmp);
	}
	
	/**
	 * 根据Id 批量删除(临时用户)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteUserTmpLogicBatch(List<java.math.BigInteger> idList) {
		List<UserTmp> delList = new java.util.ArrayList<UserTmp>();
		for(java.math.BigInteger id:idList){
			UserTmp userTmp = new UserTmp();
			userTmp.setId(id);
			userTmp.setSys0DelState(RecordState_DELETED);
			delList.add(userTmp);
		}
		return sqlSession.update("userTmpBase.delete_userTmp_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(临时用户)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteUserTmp(java.math.BigInteger id){
//		return sqlSession.delete("userTmpBase.delete_userTmp", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(临时用户)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteUserTmpBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("userTmpBase.delete_userTmp_Batch", idList);
//	}
	
	
}
