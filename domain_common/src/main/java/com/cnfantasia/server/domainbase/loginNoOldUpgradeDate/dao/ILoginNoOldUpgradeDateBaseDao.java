package com.cnfantasia.server.domainbase.loginNoOldUpgradeDate.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.loginNoOldUpgradeDate.entity.LoginNoOldUpgradeDate;

/**
 * 描述:(微信升级老数据) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ILoginNoOldUpgradeDateBaseDao {
	/**
	 * 根据条件查询(微信升级老数据)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<LoginNoOldUpgradeDate> selectLoginNoOldUpgradeDateByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(微信升级老数据)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<LoginNoOldUpgradeDate> selectLoginNoOldUpgradeDateByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(微信升级老数据)信息
	 * @param id
	 * @return
	 */
	public LoginNoOldUpgradeDate selectLoginNoOldUpgradeDateBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(微信升级老数据)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectLoginNoOldUpgradeDateCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(微信升级老数据)新增一条记录
	 * @param loginNoOldUpgradeDate
	 * @return
	 */
	public int insertLoginNoOldUpgradeDate(LoginNoOldUpgradeDate loginNoOldUpgradeDate);
	
	/**
	 * 批量新增(微信升级老数据)信息
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
	 * 根据Id 批量删除(微信升级老数据)信息_逻辑删除
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
