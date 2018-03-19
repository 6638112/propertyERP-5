package com.cnfantasia.server.domainbase.bcFileDefine.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.bcFileDefine.entity.BcFileDefine;

/**
 * 描述:(出盘回盘文件格式定义) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IBcFileDefineBaseDao {
	/**
	 * 根据条件查询(出盘回盘文件格式定义)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<BcFileDefine> selectBcFileDefineByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(出盘回盘文件格式定义)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<BcFileDefine> selectBcFileDefineByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(出盘回盘文件格式定义)信息
	 * @param id
	 * @return
	 */
	public BcFileDefine selectBcFileDefineBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(出盘回盘文件格式定义)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectBcFileDefineCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(出盘回盘文件格式定义)新增一条记录
	 * @param bcFileDefine
	 * @return
	 */
	public int insertBcFileDefine(BcFileDefine bcFileDefine);
	
	/**
	 * 批量新增(出盘回盘文件格式定义)信息
	 * @param bcFileDefineList
	 * @return
	 */
	public int insertBcFileDefineBatch(List<BcFileDefine> bcFileDefineList);
	
	/**
	 * 更新(出盘回盘文件格式定义)信息
	 * @param bcFileDefine
	 * @return
	 */
	public int updateBcFileDefine(BcFileDefine bcFileDefine);
	
	/**
	 * 批量更新(出盘回盘文件格式定义)信息
	 * @param bcFileDefineList
	 * @return
	 */
	public int updateBcFileDefineBatch(List<BcFileDefine> bcFileDefineList);
	
	/**
	 * 根据序列号删除(出盘回盘文件格式定义)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteBcFileDefineLogic(java.math.BigInteger id);
	
	/**
	 * 根据Id 批量删除(出盘回盘文件格式定义)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteBcFileDefineLogicBatch(List<java.math.BigInteger> idList);
	
	
//	/**
//	 * 根据序列号删除(出盘回盘文件格式定义)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteBcFileDefine(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(出盘回盘文件格式定义)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteBcFileDefineBatch(List<java.math.BigInteger> idList);
	
	
}
