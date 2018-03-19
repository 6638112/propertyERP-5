package com.cnfantasia.server.domainbase.bcFileDefine.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.bcFileDefine.entity.BcFileDefine;

/**
 * 描述:(出盘回盘文件格式定义) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IBcFileDefineBaseService {
	
	/**
	 * 根据条件查询(出盘回盘文件格式定义)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<BcFileDefine> getBcFileDefineByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(出盘回盘文件格式定义)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<BcFileDefine> getBcFileDefineByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(出盘回盘文件格式定义)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<BcFileDefine> getBcFileDefineByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(出盘回盘文件格式定义)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<BcFileDefine> getBcFileDefineByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(出盘回盘文件格式定义)信息
	 * @param id
	 * @return
	 */
	public BcFileDefine getBcFileDefineBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(出盘回盘文件格式定义)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getBcFileDefineCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(出盘回盘文件格式定义)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getBcFileDefineCountDim(Map<String,Object> paramMap);
	/**
	 * 往(出盘回盘文件格式定义)新增一条记录
	 * @param bcFileDefine
	 * @return
	 */
	public int insertBcFileDefine(BcFileDefine bcFileDefine);
	/**
	 * 批量新增(出盘回盘文件格式定义)
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
	 * 根据序列号批量删除(出盘回盘文件格式定义)信息_逻辑删除
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
