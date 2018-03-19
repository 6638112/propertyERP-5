package com.cnfantasia.server.domainbase.loginNoBindRelation.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.loginNoBindRelation.dao.ILoginNoBindRelationBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.loginNoBindRelation.entity.LoginNoBindRelation;

/**
 * 描述:(登录账号绑定关系) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class LoginNoBindRelationBaseService implements ILoginNoBindRelationBaseService{
	
	private ILoginNoBindRelationBaseDao loginNoBindRelationBaseDao;
	public void setLoginNoBindRelationBaseDao(ILoginNoBindRelationBaseDao loginNoBindRelationBaseDao) {
		this.loginNoBindRelationBaseDao = loginNoBindRelationBaseDao;
	}
	/**
	 * 根据条件查询(登录账号绑定关系)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<LoginNoBindRelation> getLoginNoBindRelationByCondition(Map<String,Object> paramMap){
		return loginNoBindRelationBaseDao.selectLoginNoBindRelationByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(登录账号绑定关系)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<LoginNoBindRelation> getLoginNoBindRelationByConditionDim(Map<String,Object> paramMap){
		return loginNoBindRelationBaseDao.selectLoginNoBindRelationByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(登录账号绑定关系)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<LoginNoBindRelation> getLoginNoBindRelationByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return loginNoBindRelationBaseDao.selectLoginNoBindRelationByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(登录账号绑定关系)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<LoginNoBindRelation> getLoginNoBindRelationByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return loginNoBindRelationBaseDao.selectLoginNoBindRelationByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(登录账号绑定关系)信息
	 * @param id
	 * @return
	 */
	@Override
	public LoginNoBindRelation getLoginNoBindRelationBySeqId(java.math.BigInteger id){
		return loginNoBindRelationBaseDao.selectLoginNoBindRelationBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(登录账号绑定关系)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getLoginNoBindRelationCount(Map<String,Object> paramMap){
		return loginNoBindRelationBaseDao.selectLoginNoBindRelationCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(登录账号绑定关系)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getLoginNoBindRelationCountDim(Map<String,Object> paramMap){
		return loginNoBindRelationBaseDao.selectLoginNoBindRelationCount(paramMap,true);
	}
	/**
	 * 往(登录账号绑定关系)新增一条记录
	 * @param loginNoBindRelation
	 * @return
	 */
	@Override
	public int insertLoginNoBindRelation(LoginNoBindRelation loginNoBindRelation){
		return loginNoBindRelationBaseDao.insertLoginNoBindRelation(loginNoBindRelation);
	}
	/**
	 * 批量新增(登录账号绑定关系)
	 * @param loginNoBindRelationList
	 * @return
	 */
	@Override
	public int insertLoginNoBindRelationBatch(List<LoginNoBindRelation> loginNoBindRelationList){
		return loginNoBindRelationBaseDao.insertLoginNoBindRelationBatch(loginNoBindRelationList);
	}
	/**
	 * 更新(登录账号绑定关系)信息
	 * @param loginNoBindRelation
	 * @return
	 */
	@Override
	public int updateLoginNoBindRelation(LoginNoBindRelation loginNoBindRelation){
		return loginNoBindRelationBaseDao.updateLoginNoBindRelation(loginNoBindRelation);
	}
	/**
	 * 批量更新(登录账号绑定关系)信息
	 * @param loginNoBindRelationList
	 * @return
	 */
	@Override
	public int updateLoginNoBindRelationBatch(List<LoginNoBindRelation> loginNoBindRelationList){
		return loginNoBindRelationBaseDao.updateLoginNoBindRelationBatch(loginNoBindRelationList);
	}
	/**
	 * 根据序列号删除(登录账号绑定关系)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteLoginNoBindRelationLogic(java.math.BigInteger id){
		return loginNoBindRelationBaseDao.deleteLoginNoBindRelationLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(登录账号绑定关系)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteLoginNoBindRelationLogicBatch(List<java.math.BigInteger> idList){
		return loginNoBindRelationBaseDao.deleteLoginNoBindRelationLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(登录账号绑定关系)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteLoginNoBindRelation(java.math.BigInteger id){
//		return loginNoBindRelationBaseDao.deleteLoginNoBindRelation(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(登录账号绑定关系)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteLoginNoBindRelationBatch(List<java.math.BigInteger> idList){
//		return loginNoBindRelationBaseDao.deleteLoginNoBindRelationBatch(idList);
//	}
	
}
