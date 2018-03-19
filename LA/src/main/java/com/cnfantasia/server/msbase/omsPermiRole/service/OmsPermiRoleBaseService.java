package com.cnfantasia.server.msbase.omsPermiRole.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.msbase.omsPermiRole.dao.IOmsPermiRoleBaseDao;
import com.cnfantasia.server.msbase.omsPermiRole.entity.OmsPermiRole;

/**
 * 描述:(OMS角色表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class OmsPermiRoleBaseService implements IOmsPermiRoleBaseService{
	
	private IOmsPermiRoleBaseDao omsPermiRoleBaseDao;
	public void setOmsPermiRoleBaseDao(IOmsPermiRoleBaseDao omsPermiRoleBaseDao) {
		this.omsPermiRoleBaseDao = omsPermiRoleBaseDao;
	}
	/**
	 * 根据条件查询(OMS角色表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<OmsPermiRole> getOmsPermiRoleByCondition(Map<String,Object> paramMap){
		return omsPermiRoleBaseDao.selectOmsPermiRoleByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(OMS角色表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<OmsPermiRole> getOmsPermiRoleByConditionDim(Map<String,Object> paramMap){
		return omsPermiRoleBaseDao.selectOmsPermiRoleByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(OMS角色表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<OmsPermiRole> getOmsPermiRoleByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return omsPermiRoleBaseDao.selectOmsPermiRoleByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(OMS角色表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<OmsPermiRole> getOmsPermiRoleByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return omsPermiRoleBaseDao.selectOmsPermiRoleByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(OMS角色表)信息
	 * @param id
	 * @return
	 */
	@Override
	public OmsPermiRole getOmsPermiRoleBySeqId(java.math.BigInteger id){
		return omsPermiRoleBaseDao.selectOmsPermiRoleBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(OMS角色表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getOmsPermiRoleCount(Map<String,Object> paramMap){
		return omsPermiRoleBaseDao.selectOmsPermiRoleCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(OMS角色表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getOmsPermiRoleCountDim(Map<String,Object> paramMap){
		return omsPermiRoleBaseDao.selectOmsPermiRoleCount(paramMap,true);
	}
	/**
	 * 往(OMS角色表)新增一条记录
	 * @param omsPermiRole
	 * @return
	 */
	@Override
	public int insertOmsPermiRole(OmsPermiRole omsPermiRole){
		return omsPermiRoleBaseDao.insertOmsPermiRole(omsPermiRole);
	}
	/**
	 * 批量新增(OMS角色表)
	 * @param omsPermiRoleList
	 * @return
	 */
	@Override
	public int insertOmsPermiRoleBatch(List<OmsPermiRole> omsPermiRoleList){
		return omsPermiRoleBaseDao.insertOmsPermiRoleBatch(omsPermiRoleList);
	}
	/**
	 * 更新(OMS角色表)信息
	 * @param omsPermiRole
	 * @return
	 */
	@Override
	public int updateOmsPermiRole(OmsPermiRole omsPermiRole){
		return omsPermiRoleBaseDao.updateOmsPermiRole(omsPermiRole);
	}
	/**
	 * 批量更新(OMS角色表)信息
	 * @param omsPermiRoleList
	 * @return
	 */
	@Override
	public int updateOmsPermiRoleBatch(List<OmsPermiRole> omsPermiRoleList){
		return omsPermiRoleBaseDao.updateOmsPermiRoleBatch(omsPermiRoleList);
	}
	/**
	 * 根据序列号删除(OMS角色表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteOmsPermiRoleLogic(java.math.BigInteger id){
		return omsPermiRoleBaseDao.deleteOmsPermiRoleLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(OMS角色表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteOmsPermiRoleLogicBatch(List<java.math.BigInteger> idList){
		return omsPermiRoleBaseDao.deleteOmsPermiRoleLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(OMS角色表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteOmsPermiRole(java.math.BigInteger id){
//		return omsPermiRoleBaseDao.deleteOmsPermiRole(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(OMS角色表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteOmsPermiRoleBatch(List<java.math.BigInteger> idList){
//		return omsPermiRoleBaseDao.deleteOmsPermiRoleBatch(idList);
//	}
	
}
