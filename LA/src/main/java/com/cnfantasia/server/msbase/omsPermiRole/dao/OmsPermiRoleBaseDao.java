package com.cnfantasia.server.msbase.omsPermiRole.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.msbase.omsPermiRole.entity.OmsPermiRole;

/**
 * 描述:(OMS角色表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class OmsPermiRoleBaseDao extends AbstractBaseDao implements IOmsPermiRoleBaseDao{
	/**
	 * 根据条件查询(OMS角色表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<OmsPermiRole> selectOmsPermiRoleByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("omsPermiRoleBase.select_omsPermiRole",paramMap);
	}
	/**
	 * 按条件分页查询(OMS角色表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<OmsPermiRole> selectOmsPermiRoleByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectOmsPermiRoleCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<OmsPermiRole> resMap= sqlSession.selectList("omsPermiRoleBase.select_omsPermiRole_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(OMS角色表)信息
	 * @param id
	 * @return
	 */
	@Override
	public OmsPermiRole selectOmsPermiRoleBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("omsPermiRoleBase.select_omsPermiRole_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(OMS角色表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectOmsPermiRoleCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("omsPermiRoleBase.select_omsPermiRole_count", paramMap);
	}
	/**
	 * 往(OMS角色表)新增一条记录
	 * @param omsPermiRole
	 * @return
	 */
	@Override
	public int insertOmsPermiRole(OmsPermiRole omsPermiRole){
		return sqlSession.insert("omsPermiRoleBase.insert_omsPermiRole",omsPermiRole);
	}
	/**
	 * 批量新增(OMS角色表)信息
	 * @param omsPermiRoleList
	 * @return
	 */
	@Override
	public int insertOmsPermiRoleBatch(List<OmsPermiRole> omsPermiRoleList) {
		return sqlSession.insert("omsPermiRoleBase.insert_omsPermiRole_Batch",omsPermiRoleList);
	}
	
	/**
	 * 更新(OMS角色表)信息
	 * @param omsPermiRole
	 * @return
	 */
	@Override
	public int updateOmsPermiRole(OmsPermiRole omsPermiRole){
		return sqlSession.update("omsPermiRoleBase.update_omsPermiRole", omsPermiRole);
	}
	/**
	 * 批量更新(OMS角色表)信息
	 * @param omsPermiRoleList
	 * @return
	 */
	@Override
	public int updateOmsPermiRoleBatch(List<OmsPermiRole> omsPermiRoleList) {
		return sqlSession.update("omsPermiRoleBase.update_omsPermiRole_Batch", omsPermiRoleList);
	}
	
	/**
	 * 根据序列号删除(OMS角色表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteOmsPermiRoleLogic(java.math.BigInteger id){
		OmsPermiRole omsPermiRole = new OmsPermiRole();
		omsPermiRole.setId(id);
		omsPermiRole.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("omsPermiRoleBase.delete_omsPermiRole_Logic",omsPermiRole);
	}
	
	/**
	 * 根据Id 批量删除(OMS角色表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteOmsPermiRoleLogicBatch(List<java.math.BigInteger> idList) {
		List<OmsPermiRole> delList = new java.util.ArrayList<OmsPermiRole>();
		for(java.math.BigInteger id:idList){
			OmsPermiRole omsPermiRole = new OmsPermiRole();
			omsPermiRole.setId(id);
			omsPermiRole.setSys0DelState(RecordState_DELETED);
			delList.add(omsPermiRole);
		}
		return sqlSession.update("omsPermiRoleBase.delete_omsPermiRole_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(OMS角色表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteOmsPermiRole(java.math.BigInteger id){
//		return sqlSession.delete("omsPermiRoleBase.delete_omsPermiRole", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(OMS角色表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteOmsPermiRoleBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("omsPermiRoleBase.delete_omsPermiRole_Batch", idList);
//	}
	
	
}
