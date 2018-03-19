package com.cnfantasia.server.domainbase.userTmp.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.userTmp.entity.UserTmp;

/**
 * 描述:(临时用户) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IUserTmpBaseService {
	
	/**
	 * 根据条件查询(临时用户)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<UserTmp> getUserTmpByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(临时用户)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<UserTmp> getUserTmpByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(临时用户)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<UserTmp> getUserTmpByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(临时用户)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<UserTmp> getUserTmpByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(临时用户)信息
	 * @param id
	 * @return
	 */
	public UserTmp getUserTmpBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(临时用户)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getUserTmpCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(临时用户)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getUserTmpCountDim(Map<String,Object> paramMap);
	/**
	 * 往(临时用户)新增一条记录
	 * @param userTmp
	 * @return
	 */
	public int insertUserTmp(UserTmp userTmp);
	/**
	 * 批量新增(临时用户)
	 * @param userTmpList
	 * @return
	 */
	public int insertUserTmpBatch(List<UserTmp> userTmpList);
	/**
	 * 更新(临时用户)信息
	 * @param userTmp
	 * @return
	 */
	public int updateUserTmp(UserTmp userTmp);
	/**
	 * 批量更新(临时用户)信息
	 * @param userTmpList
	 * @return
	 */
	public int updateUserTmpBatch(List<UserTmp> userTmpList);
	/**
	 * 根据序列号删除(临时用户)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteUserTmpLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(临时用户)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteUserTmpLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(临时用户)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteUserTmp(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(临时用户)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteUserTmpBatch(List<java.math.BigInteger> idList);
	
}
