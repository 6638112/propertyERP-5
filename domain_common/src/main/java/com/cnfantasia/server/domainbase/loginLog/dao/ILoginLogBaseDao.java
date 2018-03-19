package com.cnfantasia.server.domainbase.loginLog.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.loginLog.entity.LoginLog;

/**
 * 描述:(登录历史) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ILoginLogBaseDao {
	/**
	 * 根据条件查询(登录历史)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<LoginLog> selectLoginLogByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(登录历史)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<LoginLog> selectLoginLogByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(登录历史)信息
	 * @param id
	 * @return
	 */
	public LoginLog selectLoginLogBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(登录历史)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectLoginLogCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(登录历史)新增一条记录
	 * @param loginLog
	 * @return
	 */
	public int insertLoginLog(LoginLog loginLog);
	
	/**
	 * 批量新增(登录历史)信息
	 * @param loginLogList
	 * @return
	 */
	public int insertLoginLogBatch(List<LoginLog> loginLogList);
	
	/**
	 * 更新(登录历史)信息
	 * @param loginLog
	 * @return
	 */
	public int updateLoginLog(LoginLog loginLog);
	
	/**
	 * 批量更新(登录历史)信息
	 * @param loginLogList
	 * @return
	 */
	public int updateLoginLogBatch(List<LoginLog> loginLogList);
	
	/**
	 * 根据序列号删除(登录历史)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteLoginLogLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(登录历史)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteLoginLogLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(登录历史)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteLoginLog(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(登录历史)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteLoginLogBatch(List<java.math.BigInteger> idList);
	
	
}
