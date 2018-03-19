package com.cnfantasia.server.domainbase.commLoggerControl.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.commLoggerControl.entity.CommLoggerControl;

/**
 * 描述:(公共日志记录控制表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICommLoggerControlBaseService {
	
	/**
	 * 根据条件查询(公共日志记录控制表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommLoggerControl> getCommLoggerControlByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(公共日志记录控制表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<CommLoggerControl> getCommLoggerControlByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(公共日志记录控制表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommLoggerControl> getCommLoggerControlByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(公共日志记录控制表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CommLoggerControl> getCommLoggerControlByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(公共日志记录控制表)信息
	 * @param id
	 * @return
	 */
	public CommLoggerControl getCommLoggerControlBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(公共日志记录控制表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCommLoggerControlCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(公共日志记录控制表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCommLoggerControlCountDim(Map<String,Object> paramMap);
	/**
	 * 往(公共日志记录控制表)新增一条记录
	 * @param commLoggerControl
	 * @return
	 */
	public int insertCommLoggerControl(CommLoggerControl commLoggerControl);
	/**
	 * 批量新增(公共日志记录控制表)
	 * @param commLoggerControlList
	 * @return
	 */
	public int insertCommLoggerControlBatch(List<CommLoggerControl> commLoggerControlList);
	/**
	 * 更新(公共日志记录控制表)信息
	 * @param commLoggerControl
	 * @return
	 */
	public int updateCommLoggerControl(CommLoggerControl commLoggerControl);
	/**
	 * 批量更新(公共日志记录控制表)信息
	 * @param commLoggerControlList
	 * @return
	 */
	public int updateCommLoggerControlBatch(List<CommLoggerControl> commLoggerControlList);
	/**
	 * 根据序列号删除(公共日志记录控制表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCommLoggerControlLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(公共日志记录控制表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCommLoggerControlLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(公共日志记录控制表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCommLoggerControl(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(公共日志记录控制表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCommLoggerControlBatch(List<java.math.BigInteger> idList);
	
}
