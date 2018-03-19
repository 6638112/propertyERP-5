package com.cnfantasia.server.msbase.omsUserHasTOmsPermiRole.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.domainbase.omsUserHasTOmsPermiRole.dao.IOmsUserHasTOmsPermiRoleBaseDao;
import org.springframework.stereotype.Repository;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.domainbase.omsUserHasTOmsPermiRole.entity.OmsUserHasTOmsPermiRole;

/**
 * 描述:(OMS用户角色关系) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class OmsUserHasTOmsPermiRoleBaseDao extends AbstractBaseDao implements IOmsUserHasTOmsPermiRoleBaseDao {
	/**
	 * 根据条件查询(OMS用户角色关系)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<OmsUserHasTOmsPermiRole> selectOmsUserHasTOmsPermiRoleByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("omsUserHasTOmsPermiRoleBase.select_omsUserHasTOmsPermiRole",paramMap);
	}
	/**
	 * 按条件分页查询(OMS用户角色关系)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<OmsUserHasTOmsPermiRole> selectOmsUserHasTOmsPermiRoleByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectOmsUserHasTOmsPermiRoleCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<OmsUserHasTOmsPermiRole> resMap= sqlSession.selectList("omsUserHasTOmsPermiRoleBase.select_omsUserHasTOmsPermiRole_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(OMS用户角色关系)信息
	 * @param id
	 * @return
	 */
	@Override
	public OmsUserHasTOmsPermiRole selectOmsUserHasTOmsPermiRoleBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("omsUserHasTOmsPermiRoleBase.select_omsUserHasTOmsPermiRole_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(OMS用户角色关系)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectOmsUserHasTOmsPermiRoleCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("omsUserHasTOmsPermiRoleBase.select_omsUserHasTOmsPermiRole_count", paramMap);
	}
	/**
	 * 往(OMS用户角色关系)新增一条记录
	 * @param omsUserHasTOmsPermiRole
	 * @return
	 */
	@Override
	public int insertOmsUserHasTOmsPermiRole(OmsUserHasTOmsPermiRole omsUserHasTOmsPermiRole){
		return sqlSession.insert("omsUserHasTOmsPermiRoleBase.insert_omsUserHasTOmsPermiRole",omsUserHasTOmsPermiRole);
	}
	/**
	 * 批量新增(OMS用户角色关系)信息
	 * @param omsUserHasTOmsPermiRoleList
	 * @return
	 */
	@Override
	public int insertOmsUserHasTOmsPermiRoleBatch(List<OmsUserHasTOmsPermiRole> omsUserHasTOmsPermiRoleList) {
		return sqlSession.insert("omsUserHasTOmsPermiRoleBase.insert_omsUserHasTOmsPermiRole_Batch",omsUserHasTOmsPermiRoleList);
	}
	
	/**
	 * 更新(OMS用户角色关系)信息
	 * @param omsUserHasTOmsPermiRole
	 * @return
	 */
	@Override
	public int updateOmsUserHasTOmsPermiRole(OmsUserHasTOmsPermiRole omsUserHasTOmsPermiRole){
		return sqlSession.update("omsUserHasTOmsPermiRoleBase.update_omsUserHasTOmsPermiRole", omsUserHasTOmsPermiRole);
	}
	/**
	 * 批量更新(OMS用户角色关系)信息
	 * @param omsUserHasTOmsPermiRoleList
	 * @return
	 */
	@Override
	public int updateOmsUserHasTOmsPermiRoleBatch(List<OmsUserHasTOmsPermiRole> omsUserHasTOmsPermiRoleList) {
		return sqlSession.update("omsUserHasTOmsPermiRoleBase.update_omsUserHasTOmsPermiRole_Batch", omsUserHasTOmsPermiRoleList);
	}
	
	/**
	 * 根据序列号删除(OMS用户角色关系)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteOmsUserHasTOmsPermiRoleLogic(java.math.BigInteger id){
		OmsUserHasTOmsPermiRole omsUserHasTOmsPermiRole = new OmsUserHasTOmsPermiRole();
		omsUserHasTOmsPermiRole.setId(id);
		omsUserHasTOmsPermiRole.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("omsUserHasTOmsPermiRoleBase.delete_omsUserHasTOmsPermiRole_Logic",omsUserHasTOmsPermiRole);
	}
	
	/**
	 * 根据Id 批量删除(OMS用户角色关系)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteOmsUserHasTOmsPermiRoleLogicBatch(List<java.math.BigInteger> idList) {
		List<OmsUserHasTOmsPermiRole> delList = new java.util.ArrayList<OmsUserHasTOmsPermiRole>();
		for(java.math.BigInteger id:idList){
			OmsUserHasTOmsPermiRole omsUserHasTOmsPermiRole = new OmsUserHasTOmsPermiRole();
			omsUserHasTOmsPermiRole.setId(id);
			omsUserHasTOmsPermiRole.setSys0DelState(RecordState_DELETED);
			delList.add(omsUserHasTOmsPermiRole);
		}
		return sqlSession.update("omsUserHasTOmsPermiRoleBase.delete_omsUserHasTOmsPermiRole_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(OMS用户角色关系)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteOmsUserHasTOmsPermiRole(java.math.BigInteger id){
//		return sqlSession.delete("omsUserHasTOmsPermiRoleBase.delete_omsUserHasTOmsPermiRole", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(OMS用户角色关系)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteOmsUserHasTOmsPermiRoleBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("omsUserHasTOmsPermiRoleBase.delete_omsUserHasTOmsPermiRole_Batch", idList);
//	}
	
	
}
