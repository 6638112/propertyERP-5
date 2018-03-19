package com.cnfantasia.server.domainbase.encryptColum.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.encryptColum.entity.EncryptColum;

/**
 * 描述:(加密字段) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface IEncryptColumBaseDao {
	/**
	 * 根据条件查询(加密字段)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EncryptColum> selectEncryptColumByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(加密字段)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<EncryptColum> selectEncryptColumByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(加密字段)信息
	 * @param id
	 * @return
	 */
	public EncryptColum selectEncryptColumBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(加密字段)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectEncryptColumCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(加密字段)新增一条记录
	 * @param encryptColum
	 * @return
	 */
	public int insertEncryptColum(EncryptColum encryptColum);
	
	/**
	 * 批量新增(加密字段)信息
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
	 * 根据Id 批量删除(加密字段)信息_逻辑删除
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
