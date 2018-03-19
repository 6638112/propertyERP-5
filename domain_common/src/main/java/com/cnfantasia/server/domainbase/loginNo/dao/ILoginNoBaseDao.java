package com.cnfantasia.server.domainbase.loginNo.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.loginNo.entity.LoginNo;

/**
 * 描述:(用户登录账号) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ILoginNoBaseDao {
	/**
	 * 根据条件查询(用户登录账号)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<LoginNo> selectLoginNoByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(用户登录账号)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<LoginNo> selectLoginNoByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(用户登录账号)信息
	 * @param id
	 * @return
	 */
	public LoginNo selectLoginNoBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(用户登录账号)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectLoginNoCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(用户登录账号)新增一条记录
	 * @param loginNo
	 * @return
	 */
	public int insertLoginNo(LoginNo loginNo);
	
	/**
	 * 批量新增(用户登录账号)信息
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
	 * 根据Id 批量删除(用户登录账号)信息_逻辑删除
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
