package com.cnfantasia.server.domainbase.commUserSession.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.commUserSession.entity.CommUserSession;

/**
 * 描述:(用户登录session表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommUserSessionBaseDao {
	/**
	 * 根据条件查询(用户登录session表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommUserSession> selectCommUserSessionByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(用户登录session表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CommUserSession> selectCommUserSessionByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(用户登录session表)信息
	 * @param id
	 * @return
	 */
	public CommUserSession selectCommUserSessionBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(用户登录session表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectCommUserSessionCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(用户登录session表)新增一条记录
	 * @param commUserSession
	 * @return
	 */
	public int insertCommUserSession(CommUserSession commUserSession);
	
	/**
	 * 批量新增(用户登录session表)信息
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
	 * 根据Id 批量删除(用户登录session表)信息_逻辑删除
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
