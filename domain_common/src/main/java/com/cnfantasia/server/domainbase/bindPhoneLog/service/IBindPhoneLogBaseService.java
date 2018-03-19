package com.cnfantasia.server.domainbase.bindPhoneLog.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.bindPhoneLog.entity.BindPhoneLog;

/**
 * 描述:(绑定手机记录表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IBindPhoneLogBaseService {
	
	/**
	 * 根据条件查询(绑定手机记录表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<BindPhoneLog> getBindPhoneLogByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(绑定手机记录表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<BindPhoneLog> getBindPhoneLogByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(绑定手机记录表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<BindPhoneLog> getBindPhoneLogByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(绑定手机记录表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<BindPhoneLog> getBindPhoneLogByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(绑定手机记录表)信息
	 * @param id
	 * @return
	 */
	public BindPhoneLog getBindPhoneLogBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(绑定手机记录表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getBindPhoneLogCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(绑定手机记录表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getBindPhoneLogCountDim(Map<String,Object> paramMap);
	/**
	 * 往(绑定手机记录表)新增一条记录
	 * @param bindPhoneLog
	 * @return
	 */
	public int insertBindPhoneLog(BindPhoneLog bindPhoneLog);
	/**
	 * 批量新增(绑定手机记录表)
	 * @param bindPhoneLogList
	 * @return
	 */
	public int insertBindPhoneLogBatch(List<BindPhoneLog> bindPhoneLogList);
	/**
	 * 更新(绑定手机记录表)信息
	 * @param bindPhoneLog
	 * @return
	 */
	public int updateBindPhoneLog(BindPhoneLog bindPhoneLog);
	/**
	 * 批量更新(绑定手机记录表)信息
	 * @param bindPhoneLogList
	 * @return
	 */
	public int updateBindPhoneLogBatch(List<BindPhoneLog> bindPhoneLogList);
	/**
	 * 根据序列号删除(绑定手机记录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteBindPhoneLogLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(绑定手机记录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteBindPhoneLogLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(绑定手机记录表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteBindPhoneLog(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(绑定手机记录表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteBindPhoneLogBatch(List<java.math.BigInteger> idList);
	
}
