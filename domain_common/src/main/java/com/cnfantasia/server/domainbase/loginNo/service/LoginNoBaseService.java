package com.cnfantasia.server.domainbase.loginNo.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.loginNo.dao.ILoginNoBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.loginNo.entity.LoginNo;

/**
 * 描述:(用户登录账号) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class LoginNoBaseService implements ILoginNoBaseService{
	
	private ILoginNoBaseDao loginNoBaseDao;
	public void setLoginNoBaseDao(ILoginNoBaseDao loginNoBaseDao) {
		this.loginNoBaseDao = loginNoBaseDao;
	}
	/**
	 * 根据条件查询(用户登录账号)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<LoginNo> getLoginNoByCondition(Map<String,Object> paramMap){
		return loginNoBaseDao.selectLoginNoByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(用户登录账号)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<LoginNo> getLoginNoByConditionDim(Map<String,Object> paramMap){
		return loginNoBaseDao.selectLoginNoByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(用户登录账号)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<LoginNo> getLoginNoByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return loginNoBaseDao.selectLoginNoByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(用户登录账号)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<LoginNo> getLoginNoByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return loginNoBaseDao.selectLoginNoByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(用户登录账号)信息
	 * @param id
	 * @return
	 */
	@Override
	public LoginNo getLoginNoBySeqId(java.math.BigInteger id){
		return loginNoBaseDao.selectLoginNoBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(用户登录账号)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getLoginNoCount(Map<String,Object> paramMap){
		return loginNoBaseDao.selectLoginNoCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(用户登录账号)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getLoginNoCountDim(Map<String,Object> paramMap){
		return loginNoBaseDao.selectLoginNoCount(paramMap,true);
	}
	/**
	 * 往(用户登录账号)新增一条记录
	 * @param loginNo
	 * @return
	 */
	@Override
	public int insertLoginNo(LoginNo loginNo){
		return loginNoBaseDao.insertLoginNo(loginNo);
	}
	/**
	 * 批量新增(用户登录账号)
	 * @param loginNoList
	 * @return
	 */
	@Override
	public int insertLoginNoBatch(List<LoginNo> loginNoList){
		return loginNoBaseDao.insertLoginNoBatch(loginNoList);
	}
	/**
	 * 更新(用户登录账号)信息
	 * @param loginNo
	 * @return
	 */
	@Override
	public int updateLoginNo(LoginNo loginNo){
		return loginNoBaseDao.updateLoginNo(loginNo);
	}
	/**
	 * 批量更新(用户登录账号)信息
	 * @param loginNoList
	 * @return
	 */
	@Override
	public int updateLoginNoBatch(List<LoginNo> loginNoList){
		return loginNoBaseDao.updateLoginNoBatch(loginNoList);
	}
	/**
	 * 根据序列号删除(用户登录账号)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteLoginNoLogic(java.math.BigInteger id){
		return loginNoBaseDao.deleteLoginNoLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(用户登录账号)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteLoginNoLogicBatch(List<java.math.BigInteger> idList){
		return loginNoBaseDao.deleteLoginNoLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(用户登录账号)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteLoginNo(java.math.BigInteger id){
//		return loginNoBaseDao.deleteLoginNo(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(用户登录账号)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteLoginNoBatch(List<java.math.BigInteger> idList){
//		return loginNoBaseDao.deleteLoginNoBatch(idList);
//	}
	
}
