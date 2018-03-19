package com.cnfantasia.server.domainbase.loginLog.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.loginLog.dao.ILoginLogBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.loginLog.entity.LoginLog;

/**
 * 描述:(登录历史) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class LoginLogBaseService implements ILoginLogBaseService{
	
	private ILoginLogBaseDao loginLogBaseDao;
	public void setLoginLogBaseDao(ILoginLogBaseDao loginLogBaseDao) {
		this.loginLogBaseDao = loginLogBaseDao;
	}
	/**
	 * 根据条件查询(登录历史)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<LoginLog> getLoginLogByCondition(Map<String,Object> paramMap){
		return loginLogBaseDao.selectLoginLogByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(登录历史)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<LoginLog> getLoginLogByConditionDim(Map<String,Object> paramMap){
		return loginLogBaseDao.selectLoginLogByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(登录历史)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<LoginLog> getLoginLogByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return loginLogBaseDao.selectLoginLogByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(登录历史)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<LoginLog> getLoginLogByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return loginLogBaseDao.selectLoginLogByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(登录历史)信息
	 * @param id
	 * @return
	 */
	@Override
	public LoginLog getLoginLogBySeqId(java.math.BigInteger id){
		return loginLogBaseDao.selectLoginLogBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(登录历史)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getLoginLogCount(Map<String,Object> paramMap){
		return loginLogBaseDao.selectLoginLogCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(登录历史)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getLoginLogCountDim(Map<String,Object> paramMap){
		return loginLogBaseDao.selectLoginLogCount(paramMap,true);
	}
	/**
	 * 往(登录历史)新增一条记录
	 * @param loginLog
	 * @return
	 */
	@Override
	public int insertLoginLog(LoginLog loginLog){
		return loginLogBaseDao.insertLoginLog(loginLog);
	}
	/**
	 * 批量新增(登录历史)
	 * @param loginLogList
	 * @return
	 */
	@Override
	public int insertLoginLogBatch(List<LoginLog> loginLogList){
		return loginLogBaseDao.insertLoginLogBatch(loginLogList);
	}
	/**
	 * 更新(登录历史)信息
	 * @param loginLog
	 * @return
	 */
	@Override
	public int updateLoginLog(LoginLog loginLog){
		return loginLogBaseDao.updateLoginLog(loginLog);
	}
	/**
	 * 批量更新(登录历史)信息
	 * @param loginLogList
	 * @return
	 */
	@Override
	public int updateLoginLogBatch(List<LoginLog> loginLogList){
		return loginLogBaseDao.updateLoginLogBatch(loginLogList);
	}
	/**
	 * 根据序列号删除(登录历史)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteLoginLogLogic(java.math.BigInteger id){
		return loginLogBaseDao.deleteLoginLogLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(登录历史)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteLoginLogLogicBatch(List<java.math.BigInteger> idList){
		return loginLogBaseDao.deleteLoginLogLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(登录历史)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteLoginLog(java.math.BigInteger id){
//		return loginLogBaseDao.deleteLoginLog(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(登录历史)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteLoginLogBatch(List<java.math.BigInteger> idList){
//		return loginLogBaseDao.deleteLoginLogBatch(idList);
//	}
	
}
