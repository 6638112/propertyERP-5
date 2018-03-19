package com.cnfantasia.server.ms.omsPermiRole.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.ms.omsPermiRole.entity.OmsPermiFunctionEntity;
import com.cnfantasia.server.ms.pub.dao.AbstractBaseDao;
import com.cnfantasia.server.domainbase.omsPermiRole.entity.OmsPermiRole;

/**
 * 描述:(OMS角色表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class OmsPermiRoleDao extends AbstractBaseDao implements IOmsPermiRoleDao {
	/**
	 * 根据条件查询(OMS角色表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<OmsPermiRole> selectOmsPermiRoleByCondition(Map<String,Object> paramMap,boolean isDim){
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
	public List<OmsPermiRole> selectOmsPermiRoleByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectOmsPermiRoleCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("参数合并发生信息丢失。");}
		
		List<OmsPermiRole> resMap= sqlSession.selectList("omsPermiRoleBase.select_omsPermiRole_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(OMS角色表)信息
	 * @param seqId
	 * @return
	 */
	public OmsPermiRole selectOmsPermiRoleBySeqId(String seqId){
		return sqlSession.selectOne("omsPermiRoleBase.select_omsPermiRole_bySeqId",seqId);
	}
	/**
	 * 根据条件查询满足条件的(OMS角色表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectOmsPermiRoleCount(Map<String,Object> paramMap,boolean isDim){
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("omsPermiRoleBase.select_omsPermiRole_count", paramMap);
	}
	/**
	 * 往(OMS角色表)新增一条记录
	 * @param paramMap
	 * @return
	 */
	public int insertOmsPermiRole(Map<String,Object> paramMap){
		return sqlSession.insert("omsPermiRoleBase.insert_omsPermiRole",paramMap);
	}
	/**
	 * 更新(OMS角色表)信息
	 * @param paramMap
	 * @return
	 */
	public int updateOmsPermiRole(Map<String,Object> paramMap){
		return sqlSession.update("omsPermiRoleBase.update_omsPermiRole", paramMap);
	}
	/**
	 * 根据序列号删除(OMS角色表)信息
	 * @param seqId
	 * @return
	 */
	public int deleteOmsPermiRole(String seqId){
		return sqlSession.delete("omsPermiRoleBase.delete_omsPermiRole", seqId);
	}
	
	/**
	 * 根据条件查询(OMS功能表)信息
	 * 
	 * @param paramMap
	 * @param isDim
	 *            true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<OmsPermiFunctionEntity> selectOmsPermiFunctionByCondition(Map<String, Object> paramMap, boolean isDim) {
		if (paramMap != null) {
			paramMap.put(QUERY_TYPE_IF_DIM, isDim);
		}
		return sqlSession.selectList("omsPermiRole.select_permiFunction_orderByLN", paramMap);
	}

	@Override
	public void delete_roleFunction_byRoleId(Map<String, Object> paramMap) {
		sqlSession.update("omsPermiRole.delete_roleFunction_byRoleId", paramMap);
	}

	@Override
	public List<BigInteger> selectOmsPermiFunctionByRoleId(BigInteger roleId) {
		return sqlSession.selectList("omsPermiRole.select_functionIds_byRoleId", roleId);
	}
}
