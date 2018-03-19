package com.cnfantasia.server.domainbase.loginNoBindRelation.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.loginNoBindRelation.entity.LoginNoBindRelation;

/**
 * 描述:(登录账号绑定关系) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ILoginNoBindRelationBaseDao {
	/**
	 * 根据条件查询(登录账号绑定关系)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<LoginNoBindRelation> selectLoginNoBindRelationByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(登录账号绑定关系)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<LoginNoBindRelation> selectLoginNoBindRelationByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(登录账号绑定关系)信息
	 * @param id
	 * @return
	 */
	public LoginNoBindRelation selectLoginNoBindRelationBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(登录账号绑定关系)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectLoginNoBindRelationCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(登录账号绑定关系)新增一条记录
	 * @param loginNoBindRelation
	 * @return
	 */
	public int insertLoginNoBindRelation(LoginNoBindRelation loginNoBindRelation);
	
	/**
	 * 批量新增(登录账号绑定关系)信息
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
	 * 根据Id 批量删除(登录账号绑定关系)信息_逻辑删除
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
