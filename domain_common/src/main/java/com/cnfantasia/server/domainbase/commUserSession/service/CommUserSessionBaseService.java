package com.cnfantasia.server.domainbase.commUserSession.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.commUserSession.dao.ICommUserSessionBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.commUserSession.entity.CommUserSession;

/**
 * 描述:(用户登录session表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CommUserSessionBaseService implements ICommUserSessionBaseService{
	
	private ICommUserSessionBaseDao commUserSessionBaseDao;
	public void setCommUserSessionBaseDao(ICommUserSessionBaseDao commUserSessionBaseDao) {
		this.commUserSessionBaseDao = commUserSessionBaseDao;
	}
	/**
	 * 根据条件查询(用户登录session表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommUserSession> getCommUserSessionByCondition(Map<String,Object> paramMap){
		return commUserSessionBaseDao.selectCommUserSessionByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(用户登录session表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CommUserSession> getCommUserSessionByConditionDim(Map<String,Object> paramMap){
		return commUserSessionBaseDao.selectCommUserSessionByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(用户登录session表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommUserSession> getCommUserSessionByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return commUserSessionBaseDao.selectCommUserSessionByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(用户登录session表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CommUserSession> getCommUserSessionByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return commUserSessionBaseDao.selectCommUserSessionByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(用户登录session表)信息
	 * @param id
	 * @return
	 */
	@Override
	public CommUserSession getCommUserSessionBySeqId(java.math.BigInteger id){
		return commUserSessionBaseDao.selectCommUserSessionBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(用户登录session表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommUserSessionCount(Map<String,Object> paramMap){
		return commUserSessionBaseDao.selectCommUserSessionCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(用户登录session表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCommUserSessionCountDim(Map<String,Object> paramMap){
		return commUserSessionBaseDao.selectCommUserSessionCount(paramMap,true);
	}
	/**
	 * 往(用户登录session表)新增一条记录
	 * @param commUserSession
	 * @return
	 */
	@Override
	public int insertCommUserSession(CommUserSession commUserSession){
		return commUserSessionBaseDao.insertCommUserSession(commUserSession);
	}
	/**
	 * 批量新增(用户登录session表)
	 * @param commUserSessionList
	 * @return
	 */
	@Override
	public int insertCommUserSessionBatch(List<CommUserSession> commUserSessionList){
		return commUserSessionBaseDao.insertCommUserSessionBatch(commUserSessionList);
	}
	/**
	 * 更新(用户登录session表)信息
	 * @param commUserSession
	 * @return
	 */
	@Override
	public int updateCommUserSession(CommUserSession commUserSession){
		return commUserSessionBaseDao.updateCommUserSession(commUserSession);
	}
	/**
	 * 批量更新(用户登录session表)信息
	 * @param commUserSessionList
	 * @return
	 */
	@Override
	public int updateCommUserSessionBatch(List<CommUserSession> commUserSessionList){
		return commUserSessionBaseDao.updateCommUserSessionBatch(commUserSessionList);
	}
	/**
	 * 根据序列号删除(用户登录session表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCommUserSessionLogic(java.math.BigInteger id){
		return commUserSessionBaseDao.deleteCommUserSessionLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(用户登录session表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCommUserSessionLogicBatch(List<java.math.BigInteger> idList){
		return commUserSessionBaseDao.deleteCommUserSessionLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(用户登录session表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCommUserSession(java.math.BigInteger id){
//		return commUserSessionBaseDao.deleteCommUserSession(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(用户登录session表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCommUserSessionBatch(List<java.math.BigInteger> idList){
//		return commUserSessionBaseDao.deleteCommUserSessionBatch(idList);
//	}
	
}
