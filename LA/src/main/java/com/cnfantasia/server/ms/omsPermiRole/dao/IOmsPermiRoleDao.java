package com.cnfantasia.server.ms.omsPermiRole.dao;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.ms.omsPermiRole.entity.OmsPermiFunctionEntity;
import com.cnfantasia.server.domainbase.omsPermiRole.entity.OmsPermiRole;

/**
 * 描述:(OMS角色表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IOmsPermiRoleDao {
	/**
	 * 根据条件查询(OMS角色表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<OmsPermiRole> selectOmsPermiRoleByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(OMS角色表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<OmsPermiRole> selectOmsPermiRoleByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(OMS角色表)信息
	 * @param seqId
	 * @return
	 */
	public OmsPermiRole selectOmsPermiRoleBySeqId(String seqId);
	/**
	 * 根据条件查询满足条件的(OMS角色表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectOmsPermiRoleCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(OMS角色表)新增一条记录
	 * @param paramMap
	 * @return
	 */
	public int insertOmsPermiRole(Map<String,Object> paramMap);
	/**
	 * 更新(OMS角色表)信息
	 * @param paramMap
	 * @return
	 */
	public int updateOmsPermiRole(Map<String,Object> paramMap);
	/**
	 * 根据序列号删除(OMS角色表)信息
	 * @param seqId
	 * @return
	 */
	public int deleteOmsPermiRole(String seqId);

	public List<OmsPermiFunctionEntity> selectOmsPermiFunctionByCondition(Map<String, Object> paramMap, boolean isDim);

	/**
	 * 根据角色ID，删除角色权限中间表中记录
	 * 
	 * @author wenfq
	 */
	public void delete_roleFunction_byRoleId(Map<String, Object> paramMap);
	
	public List<BigInteger> selectOmsPermiFunctionByRoleId(BigInteger roleId);

	//TODO ____续写...IOmsPermiRoleDao
	
}
