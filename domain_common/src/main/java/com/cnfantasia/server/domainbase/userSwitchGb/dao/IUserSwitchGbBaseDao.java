package com.cnfantasia.server.domainbase.userSwitchGb.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.userSwitchGb.entity.UserSwitchGb;

/**
 * 描述:(用户切换小区表) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IUserSwitchGbBaseDao {
	/**
	 * 根据条件查询(用户切换小区表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<UserSwitchGb> selectUserSwitchGbByCondition(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 按条件分页查询(用户切换小区表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<UserSwitchGb> selectUserSwitchGbByCondition(Map<String, Object> paramMap, PageModel pageModel, boolean isDim);
	/**
	 * 根据序列号查询(用户切换小区表)信息
	 * @param id
	 * @return
	 */
	public UserSwitchGb selectUserSwitchGbBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(用户切换小区表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectUserSwitchGbCount(Map<String, Object> paramMap, boolean isDim);
	/**
	 * 往(用户切换小区表)新增一条记录
	 * @param userSwitchGb
	 * @return
	 */
	public int insertUserSwitchGb(UserSwitchGb userSwitchGb);
	
	/**
	 * 批量新增(用户切换小区表)信息
	 * @param userSwitchGbList
	 * @return
	 */
	public int insertUserSwitchGbBatch(List<UserSwitchGb> userSwitchGbList);
	
	/**
	 * 更新(用户切换小区表)信息
	 * @param userSwitchGb
	 * @return
	 */
	public int updateUserSwitchGb(UserSwitchGb userSwitchGb);
	
	/**
	 * 批量更新(用户切换小区表)信息
	 * @param userSwitchGbList
	 * @return
	 */
	public int updateUserSwitchGbBatch(List<UserSwitchGb> userSwitchGbList);
	
	/**
	 * 根据序列号删除(用户切换小区表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteUserSwitchGbLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(用户切换小区表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteUserSwitchGbLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(用户切换小区表)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteUserSwitchGb(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(用户切换小区表)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteUserSwitchGbBatch(List<java.math.BigInteger> idList);
	
	
}
