package com.cnfantasia.server.domainbase.loginNo.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.loginNo.entity.LoginNo;

/**
 * 描述:(用户登录账号) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ILoginNoBaseService {
	
	/**
	 * 根据条件查询(用户登录账号)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<LoginNo> getLoginNoByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(用户登录账号)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<LoginNo> getLoginNoByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(用户登录账号)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<LoginNo> getLoginNoByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(用户登录账号)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<LoginNo> getLoginNoByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(用户登录账号)信息
	 * @param id
	 * @return
	 */
	public LoginNo getLoginNoBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(用户登录账号)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getLoginNoCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(用户登录账号)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getLoginNoCountDim(Map<String,Object> paramMap);
	/**
	 * 往(用户登录账号)新增一条记录
	 * @param loginNo
	 * @return
	 */
	public int insertLoginNo(LoginNo loginNo);
	/**
	 * 批量新增(用户登录账号)
	 * @param loginNoList
	 * @return
	 */
	public int insertLoginNoBatch(List<LoginNo> loginNoList);
	/**
	 * 更新(用户登录账号)信息
	 * @param loginNo
	 * @return
	 */
	public int updateLoginNo(LoginNo loginNo);
	/**
	 * 批量更新(用户登录账号)信息
	 * @param loginNoList
	 * @return
	 */
	public int updateLoginNoBatch(List<LoginNo> loginNoList);
	/**
	 * 根据序列号删除(用户登录账号)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteLoginNoLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(用户登录账号)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteLoginNoLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(用户登录账号)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteLoginNo(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(用户登录账号)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteLoginNoBatch(List<java.math.BigInteger> idList);
	
}
