package com.cnfantasia.server.domainbase.omsUser.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.omsUser.entity.OmsUser;

/**
 * 描述:(OMS用户表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IOmsUserBaseDao {
	/**
	 * 根据条件查询(OMS用户表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<OmsUser> selectOmsUserByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(OMS用户表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<OmsUser> selectOmsUserByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(OMS用户表)信息
	 * @param id
	 * @return
	 */
	public OmsUser selectOmsUserBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(OMS用户表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectOmsUserCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(OMS用户表)新增一条记录
	 * @param omsUser
	 * @return
	 */
	public int insertOmsUser(OmsUser omsUser);
	
	/**
	 * 批量新增(OMS用户表)信息
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
	 * 根据Id 批量删除(OMS用户表)信息_逻辑删除
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
