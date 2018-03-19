package com.cnfantasia.server.domainbase.permiRole.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.permiRole.entity.PermiRole;

/**
 * 描述:(角色表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PermiRoleBaseDao extends AbstractBaseDao implements IPermiRoleBaseDao{
	/**
	 * 根据条件查询(角色表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PermiRole> selectPermiRoleByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("permiRoleBase.select_permiRole",paramMap);
	}
	/**
	 * 按条件分页查询(角色表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PermiRole> selectPermiRoleByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPermiRoleCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PermiRole> resMap= sqlSession.selectList("permiRoleBase.select_permiRole_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(角色表)信息
	 * @param id
	 * @return
	 */
	@Override
	public PermiRole selectPermiRoleBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("permiRoleBase.select_permiRole_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(角色表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPermiRoleCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("permiRoleBase.select_permiRole_count", paramMap);
	}
	/**
	 * 往(角色表)新增一条记录
	 * @param permiRole
	 * @return
	 */
	@Override
	public int insertPermiRole(PermiRole permiRole){
		return sqlSession.insert("permiRoleBase.insert_permiRole",permiRole);
	}
	/**
	 * 批量新增(角色表)信息
	 * @param permiRoleList
	 * @return
	 */
	@Override
	public int insertPermiRoleBatch(List<PermiRole> permiRoleList) {
		return sqlSession.insert("permiRoleBase.insert_permiRole_Batch",permiRoleList);
	}
	
	/**
	 * 更新(角色表)信息
	 * @param permiRole
	 * @return
	 */
	@Override
	public int updatePermiRole(PermiRole permiRole){
		return sqlSession.update("permiRoleBase.update_permiRole", permiRole);
	}
	/**
	 * 批量更新(角色表)信息
	 * @param permiRoleList
	 * @return
	 */
	@Override
	public int updatePermiRoleBatch(List<PermiRole> permiRoleList) {
		return sqlSession.update("permiRoleBase.update_permiRole_Batch", permiRoleList);
	}
	
	/**
	 * 根据序列号删除(角色表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePermiRoleLogic(java.math.BigInteger id){
		PermiRole permiRole = new PermiRole();
		permiRole.setId(id);
		permiRole.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("permiRoleBase.delete_permiRole_Logic",permiRole);
	}
	
	/**
	 * 根据Id 批量删除(角色表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePermiRoleLogicBatch(List<java.math.BigInteger> idList) {
		List<PermiRole> delList = new java.util.ArrayList<PermiRole>();
		for(java.math.BigInteger id:idList){
			PermiRole permiRole = new PermiRole();
			permiRole.setId(id);
			permiRole.setSys0DelState(RecordState_DELETED);
			delList.add(permiRole);
		}
		return sqlSession.update("permiRoleBase.delete_permiRole_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(角色表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePermiRole(java.math.BigInteger id){
//		return sqlSession.delete("permiRoleBase.delete_permiRole", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(角色表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePermiRoleBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("permiRoleBase.delete_permiRole_Batch", idList);
//	}
	
	
}
