package com.cnfantasia.server.domainbase.bindPhoneLog.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.bindPhoneLog.entity.BindPhoneLog;

/**
 * 描述:(绑定手机记录表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IBindPhoneLogBaseDao {
	/**
	 * 根据条件查询(绑定手机记录表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<BindPhoneLog> selectBindPhoneLogByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(绑定手机记录表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<BindPhoneLog> selectBindPhoneLogByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(绑定手机记录表)信息
	 * @param id
	 * @return
	 */
	public BindPhoneLog selectBindPhoneLogBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(绑定手机记录表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectBindPhoneLogCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(绑定手机记录表)新增一条记录
	 * @param bindPhoneLog
	 * @return
	 */
	public int insertBindPhoneLog(BindPhoneLog bindPhoneLog);
	
	/**
	 * 批量新增(绑定手机记录表)信息
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
	 * 根据Id 批量删除(绑定手机记录表)信息_逻辑删除
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
