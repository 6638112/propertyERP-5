package com.cnfantasia.server.domainbase.loginNoOldUpgradeDate.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.loginNoOldUpgradeDate.entity.LoginNoOldUpgradeDate;

/**
 * 描述:(微信升级老数据) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ILoginNoOldUpgradeDateBaseService {
	
	/**
	 * 根据条件查询(微信升级老数据)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<LoginNoOldUpgradeDate> getLoginNoOldUpgradeDateByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(微信升级老数据)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<LoginNoOldUpgradeDate> getLoginNoOldUpgradeDateByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(微信升级老数据)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<LoginNoOldUpgradeDate> getLoginNoOldUpgradeDateByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(微信升级老数据)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<LoginNoOldUpgradeDate> getLoginNoOldUpgradeDateByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(微信升级老数据)信息
	 * @param id
	 * @return
	 */
	public LoginNoOldUpgradeDate getLoginNoOldUpgradeDateBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(微信升级老数据)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getLoginNoOldUpgradeDateCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(微信升级老数据)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getLoginNoOldUpgradeDateCountDim(Map<String,Object> paramMap);
	/**
	 * 往(微信升级老数据)新增一条记录
	 * @param loginNoOldUpgradeDate
	 * @return
	 */
	public int insertLoginNoOldUpgradeDate(LoginNoOldUpgradeDate loginNoOldUpgradeDate);
	/**
	 * 批量新增(微信升级老数据)
	 * @param loginNoOldUpgradeDateList
	 * @return
	 */
	public int insertLoginNoOldUpgradeDateBatch(List<LoginNoOldUpgradeDate> loginNoOldUpgradeDateList);
	/**
	 * 更新(微信升级老数据)信息
	 * @param loginNoOldUpgradeDate
	 * @return
	 */
	public int updateLoginNoOldUpgradeDate(LoginNoOldUpgradeDate loginNoOldUpgradeDate);
	/**
	 * 批量更新(微信升级老数据)信息
	 * @param loginNoOldUpgradeDateList
	 * @return
	 */
	public int updateLoginNoOldUpgradeDateBatch(List<LoginNoOldUpgradeDate> loginNoOldUpgradeDateList);
	/**
	 * 根据序列号删除(微信升级老数据)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteLoginNoOldUpgradeDateLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(微信升级老数据)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteLoginNoOldUpgradeDateLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(微信升级老数据)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteLoginNoOldUpgradeDate(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(微信升级老数据)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteLoginNoOldUpgradeDateBatch(List<java.math.BigInteger> idList);
	
}
