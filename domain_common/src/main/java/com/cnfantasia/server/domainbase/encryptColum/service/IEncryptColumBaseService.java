package com.cnfantasia.server.domainbase.encryptColum.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.encryptColum.entity.EncryptColum;

/**
 * 描述:(加密字段) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEncryptColumBaseService {
	
	/**
	 * 根据条件查询(加密字段)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<EncryptColum> getEncryptColumByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(加密字段)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<EncryptColum> getEncryptColumByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(加密字段)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EncryptColum> getEncryptColumByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(加密字段)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<EncryptColum> getEncryptColumByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(加密字段)信息
	 * @param id
	 * @return
	 */
	public EncryptColum getEncryptColumBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(加密字段)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getEncryptColumCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(加密字段)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getEncryptColumCountDim(Map<String,Object> paramMap);
	/**
	 * 往(加密字段)新增一条记录
	 * @param encryptColum
	 * @return
	 */
	public int insertEncryptColum(EncryptColum encryptColum);
	/**
	 * 批量新增(加密字段)
	 * @param encryptColumList
	 * @return
	 */
	public int insertEncryptColumBatch(List<EncryptColum> encryptColumList);
	/**
	 * 更新(加密字段)信息
	 * @param encryptColum
	 * @return
	 */
	public int updateEncryptColum(EncryptColum encryptColum);
	/**
	 * 批量更新(加密字段)信息
	 * @param encryptColumList
	 * @return
	 */
	public int updateEncryptColumBatch(List<EncryptColum> encryptColumList);
	/**
	 * 根据序列号删除(加密字段)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteEncryptColumLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(加密字段)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteEncryptColumLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(加密字段)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteEncryptColum(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(加密字段)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteEncryptColumBatch(List<java.math.BigInteger> idList);
	
}
