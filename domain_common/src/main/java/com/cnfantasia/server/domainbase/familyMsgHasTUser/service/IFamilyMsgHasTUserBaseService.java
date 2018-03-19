package com.cnfantasia.server.domainbase.familyMsgHasTUser.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.familyMsgHasTUser.entity.FamilyMsgHasTUser;

/**
 * 描述:(家庭留言艾特的用户列表) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IFamilyMsgHasTUserBaseService {
	
	/**
	 * 根据条件查询(家庭留言艾特的用户列表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<FamilyMsgHasTUser> getFamilyMsgHasTUserByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(家庭留言艾特的用户列表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<FamilyMsgHasTUser> getFamilyMsgHasTUserByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(家庭留言艾特的用户列表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<FamilyMsgHasTUser> getFamilyMsgHasTUserByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(家庭留言艾特的用户列表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<FamilyMsgHasTUser> getFamilyMsgHasTUserByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(家庭留言艾特的用户列表)信息
	 * @param id
	 * @return
	 */
	public FamilyMsgHasTUser getFamilyMsgHasTUserBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(家庭留言艾特的用户列表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getFamilyMsgHasTUserCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(家庭留言艾特的用户列表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getFamilyMsgHasTUserCountDim(Map<String,Object> paramMap);
	/**
	 * 往(家庭留言艾特的用户列表)新增一条记录
	 * @param familyMsgHasTUser
	 * @return
	 */
	public int insertFamilyMsgHasTUser(FamilyMsgHasTUser familyMsgHasTUser);
	/**
	 * 批量新增(家庭留言艾特的用户列表)
	 * @param familyMsgHasTUserList
	 * @return
	 */
	public int insertFamilyMsgHasTUserBatch(List<FamilyMsgHasTUser> familyMsgHasTUserList);
	/**
	 * 更新(家庭留言艾特的用户列表)信息
	 * @param familyMsgHasTUser
	 * @return
	 */
	public int updateFamilyMsgHasTUser(FamilyMsgHasTUser familyMsgHasTUser);
	/**
	 * 批量更新(家庭留言艾特的用户列表)信息
	 * @param familyMsgHasTUserList
	 * @return
	 */
	public int updateFamilyMsgHasTUserBatch(List<FamilyMsgHasTUser> familyMsgHasTUserList);
	/**
	 * 根据序列号删除(家庭留言艾特的用户列表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteFamilyMsgHasTUserLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(家庭留言艾特的用户列表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteFamilyMsgHasTUserLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(家庭留言艾特的用户列表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteFamilyMsgHasTUser(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(家庭留言艾特的用户列表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteFamilyMsgHasTUserBatch(List<java.math.BigInteger> idList);
	
}
