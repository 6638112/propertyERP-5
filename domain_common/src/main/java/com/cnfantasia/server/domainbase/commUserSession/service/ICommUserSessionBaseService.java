package com.cnfantasia.server.domainbase.commUserSession.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.commUserSession.entity.CommUserSession;

/**
 * 描述:(用户登录session表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommUserSessionBaseService {
	
	/**
	 * 根据条件查询(用户登录session表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommUserSession> getCommUserSessionByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(用户登录session表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommUserSession> getCommUserSessionByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(用户登录session表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommUserSession> getCommUserSessionByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(用户登录session表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommUserSession> getCommUserSessionByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(用户登录session表)信息
	 * @param id
	 * @return
	 */
	public CommUserSession getCommUserSessionBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(用户登录session表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCommUserSessionCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(用户登录session表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCommUserSessionCountDim(Map<String,Object> paramMap);
	/**
	 * 往(用户登录session表)新增一条记录
	 * @param commUserSession
	 * @return
	 */
	public int insertCommUserSession(CommUserSession commUserSession);
	/**
	 * 批量新增(用户登录session表)
	 * @param commUserSessionList
	 * @return
	 */
	public int insertCommUserSessionBatch(List<CommUserSession> commUserSessionList);
	/**
	 * 更新(用户登录session表)信息
	 * @param commUserSession
	 * @return
	 */
	public int updateCommUserSession(CommUserSession commUserSession);
	/**
	 * 批量更新(用户登录session表)信息
	 * @param commUserSessionList
	 * @return
	 */
	public int updateCommUserSessionBatch(List<CommUserSession> commUserSessionList);
	/**
	 * 根据序列号删除(用户登录session表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCommUserSessionLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(用户登录session表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCommUserSessionLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(用户登录session表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCommUserSession(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(用户登录session表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCommUserSessionBatch(List<java.math.BigInteger> idList);
	
}
