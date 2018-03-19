package com.cnfantasia.server.domainbase.omsUser.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.omsUser.entity.OmsUser;

/**
 * 描述:(OMS用户表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IOmsUserBaseService {
	
	/**
	 * 根据条件查询(OMS用户表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<OmsUser> getOmsUserByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(OMS用户表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<OmsUser> getOmsUserByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(OMS用户表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<OmsUser> getOmsUserByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(OMS用户表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<OmsUser> getOmsUserByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(OMS用户表)信息
	 * @param id
	 * @return
	 */
	public OmsUser getOmsUserBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(OMS用户表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getOmsUserCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(OMS用户表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getOmsUserCountDim(Map<String,Object> paramMap);
	/**
	 * 往(OMS用户表)新增一条记录
	 * @param omsUser
	 * @return
	 */
	public int insertOmsUser(OmsUser omsUser);
	/**
	 * 批量新增(OMS用户表)
	 * @param omsUserList
	 * @return
	 */
	public int insertOmsUserBatch(List<OmsUser> omsUserList);
	/**
	 * 更新(OMS用户表)信息
	 * @param omsUser
	 * @return
	 */
	public int updateOmsUser(OmsUser omsUser);
	/**
	 * 批量更新(OMS用户表)信息
	 * @param omsUserList
	 * @return
	 */
	public int updateOmsUserBatch(List<OmsUser> omsUserList);
	/**
	 * 根据序列号删除(OMS用户表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteOmsUserLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(OMS用户表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteOmsUserLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(OMS用户表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteOmsUser(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(OMS用户表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteOmsUserBatch(List<java.math.BigInteger> idList);
	
}
