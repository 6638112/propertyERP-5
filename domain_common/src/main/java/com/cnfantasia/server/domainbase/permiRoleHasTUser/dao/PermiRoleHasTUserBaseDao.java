package com.cnfantasia.server.domainbase.permiRoleHasTUser.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.permiRoleHasTUser.entity.PermiRoleHasTUser;

/**
 * 描述:(用户角色关系) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PermiRoleHasTUserBaseDao extends AbstractBaseDao implements IPermiRoleHasTUserBaseDao{
	/**
	 * 根据条件查询(用户角色关系)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PermiRoleHasTUser> selectPermiRoleHasTUserByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("permiRoleHasTUserBase.select_permiRoleHasTUser",paramMap);
	}
	/**
	 * 按条件分页查询(用户角色关系)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PermiRoleHasTUser> selectPermiRoleHasTUserByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPermiRoleHasTUserCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PermiRoleHasTUser> resMap= sqlSession.selectList("permiRoleHasTUserBase.select_permiRoleHasTUser_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(用户角色关系)信息
	 * @param id
	 * @return
	 */
	@Override
	public PermiRoleHasTUser selectPermiRoleHasTUserBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("permiRoleHasTUserBase.select_permiRoleHasTUser_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(用户角色关系)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPermiRoleHasTUserCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("permiRoleHasTUserBase.select_permiRoleHasTUser_count", paramMap);
	}
	/**
	 * 往(用户角色关系)新增一条记录
	 * @param permiRoleHasTUser
	 * @return
	 */
	@Override
	public int insertPermiRoleHasTUser(PermiRoleHasTUser permiRoleHasTUser){
		return sqlSession.insert("permiRoleHasTUserBase.insert_permiRoleHasTUser",permiRoleHasTUser);
	}
	/**
	 * 批量新增(用户角色关系)信息
	 * @param permiRoleHasTUserList
	 * @return
	 */
	@Override
	public int insertPermiRoleHasTUserBatch(List<PermiRoleHasTUser> permiRoleHasTUserList) {
		return sqlSession.insert("permiRoleHasTUserBase.insert_permiRoleHasTUser_Batch",permiRoleHasTUserList);
	}
	
	/**
	 * 更新(用户角色关系)信息
	 * @param permiRoleHasTUser
	 * @return
	 */
	@Override
	public int updatePermiRoleHasTUser(PermiRoleHasTUser permiRoleHasTUser){
		return sqlSession.update("permiRoleHasTUserBase.update_permiRoleHasTUser", permiRoleHasTUser);
	}
	/**
	 * 批量更新(用户角色关系)信息
	 * @param permiRoleHasTUserList
	 * @return
	 */
	@Override
	public int updatePermiRoleHasTUserBatch(List<PermiRoleHasTUser> permiRoleHasTUserList) {
		return sqlSession.update("permiRoleHasTUserBase.update_permiRoleHasTUser_Batch", permiRoleHasTUserList);
	}
	
	/**
	 * 根据序列号删除(用户角色关系)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePermiRoleHasTUserLogic(java.math.BigInteger id){
		PermiRoleHasTUser permiRoleHasTUser = new PermiRoleHasTUser();
		permiRoleHasTUser.setId(id);
		permiRoleHasTUser.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("permiRoleHasTUserBase.delete_permiRoleHasTUser_Logic",permiRoleHasTUser);
	}
	
	/**
	 * 根据Id 批量删除(用户角色关系)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePermiRoleHasTUserLogicBatch(List<java.math.BigInteger> idList) {
		List<PermiRoleHasTUser> delList = new java.util.ArrayList<PermiRoleHasTUser>();
		for(java.math.BigInteger id:idList){
			PermiRoleHasTUser permiRoleHasTUser = new PermiRoleHasTUser();
			permiRoleHasTUser.setId(id);
			permiRoleHasTUser.setSys0DelState(RecordState_DELETED);
			delList.add(permiRoleHasTUser);
		}
		return sqlSession.update("permiRoleHasTUserBase.delete_permiRoleHasTUser_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(用户角色关系)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePermiRoleHasTUser(java.math.BigInteger id){
//		return sqlSession.delete("permiRoleHasTUserBase.delete_permiRoleHasTUser", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(用户角色关系)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePermiRoleHasTUserBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("permiRoleHasTUserBase.delete_permiRoleHasTUser_Batch", idList);
//	}
	
	
}
