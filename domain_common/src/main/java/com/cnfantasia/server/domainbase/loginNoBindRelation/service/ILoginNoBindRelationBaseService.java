package com.cnfantasia.server.domainbase.loginNoBindRelation.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.loginNoBindRelation.entity.LoginNoBindRelation;

/**
 * 描述:(登录账号绑定关系) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ILoginNoBindRelationBaseService {
	
	/**
	 * 根据条件查询(登录账号绑定关系)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<LoginNoBindRelation> getLoginNoBindRelationByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(登录账号绑定关系)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<LoginNoBindRelation> getLoginNoBindRelationByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(登录账号绑定关系)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<LoginNoBindRelation> getLoginNoBindRelationByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(登录账号绑定关系)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<LoginNoBindRelation> getLoginNoBindRelationByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(登录账号绑定关系)信息
	 * @param id
	 * @return
	 */
	public LoginNoBindRelation getLoginNoBindRelationBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(登录账号绑定关系)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getLoginNoBindRelationCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(登录账号绑定关系)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getLoginNoBindRelationCountDim(Map<String,Object> paramMap);
	/**
	 * 往(登录账号绑定关系)新增一条记录
	 * @param loginNoBindRelation
	 * @return
	 */
	public int insertLoginNoBindRelation(LoginNoBindRelation loginNoBindRelation);
	/**
	 * 批量新增(登录账号绑定关系)
	 * @param loginNoBindRelationList
	 * @return
	 */
	public int insertLoginNoBindRelationBatch(List<LoginNoBindRelation> loginNoBindRelationList);
	/**
	 * 更新(登录账号绑定关系)信息
	 * @param loginNoBindRelation
	 * @return
	 */
	public int updateLoginNoBindRelation(LoginNoBindRelation loginNoBindRelation);
	/**
	 * 批量更新(登录账号绑定关系)信息
	 * @param loginNoBindRelationList
	 * @return
	 */
	public int updateLoginNoBindRelationBatch(List<LoginNoBindRelation> loginNoBindRelationList);
	/**
	 * 根据序列号删除(登录账号绑定关系)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteLoginNoBindRelationLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(登录账号绑定关系)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteLoginNoBindRelationLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(登录账号绑定关系)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteLoginNoBindRelation(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(登录账号绑定关系)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteLoginNoBindRelationBatch(List<java.math.BigInteger> idList);
	
}
