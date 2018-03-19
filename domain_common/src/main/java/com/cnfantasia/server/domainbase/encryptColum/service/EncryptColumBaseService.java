package com.cnfantasia.server.domainbase.encryptColum.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.encryptColum.dao.IEncryptColumBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.encryptColum.entity.EncryptColum;

/**
 * 描述:(加密字段) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EncryptColumBaseService implements IEncryptColumBaseService{
	
	private IEncryptColumBaseDao encryptColumBaseDao;
	public void setEncryptColumBaseDao(IEncryptColumBaseDao encryptColumBaseDao) {
		this.encryptColumBaseDao = encryptColumBaseDao;
	}
	/**
	 * 根据条件查询(加密字段)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EncryptColum> getEncryptColumByCondition(Map<String,Object> paramMap){
		return encryptColumBaseDao.selectEncryptColumByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(加密字段)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EncryptColum> getEncryptColumByConditionDim(Map<String,Object> paramMap){
		return encryptColumBaseDao.selectEncryptColumByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(加密字段)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EncryptColum> getEncryptColumByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return encryptColumBaseDao.selectEncryptColumByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(加密字段)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EncryptColum> getEncryptColumByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return encryptColumBaseDao.selectEncryptColumByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(加密字段)信息
	 * @param id
	 * @return
	 */
	@Override
	public EncryptColum getEncryptColumBySeqId(java.math.BigInteger id){
		return encryptColumBaseDao.selectEncryptColumBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(加密字段)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEncryptColumCount(Map<String,Object> paramMap){
		return encryptColumBaseDao.selectEncryptColumCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(加密字段)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEncryptColumCountDim(Map<String,Object> paramMap){
		return encryptColumBaseDao.selectEncryptColumCount(paramMap,true);
	}
	/**
	 * 往(加密字段)新增一条记录
	 * @param encryptColum
	 * @return
	 */
	@Override
	public int insertEncryptColum(EncryptColum encryptColum){
		return encryptColumBaseDao.insertEncryptColum(encryptColum);
	}
	/**
	 * 批量新增(加密字段)
	 * @param encryptColumList
	 * @return
	 */
	@Override
	public int insertEncryptColumBatch(List<EncryptColum> encryptColumList){
		return encryptColumBaseDao.insertEncryptColumBatch(encryptColumList);
	}
	/**
	 * 更新(加密字段)信息
	 * @param encryptColum
	 * @return
	 */
	@Override
	public int updateEncryptColum(EncryptColum encryptColum){
		return encryptColumBaseDao.updateEncryptColum(encryptColum);
	}
	/**
	 * 批量更新(加密字段)信息
	 * @param encryptColumList
	 * @return
	 */
	@Override
	public int updateEncryptColumBatch(List<EncryptColum> encryptColumList){
		return encryptColumBaseDao.updateEncryptColumBatch(encryptColumList);
	}
	/**
	 * 根据序列号删除(加密字段)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEncryptColumLogic(java.math.BigInteger id){
		return encryptColumBaseDao.deleteEncryptColumLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(加密字段)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEncryptColumLogicBatch(List<java.math.BigInteger> idList){
		return encryptColumBaseDao.deleteEncryptColumLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(加密字段)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEncryptColum(java.math.BigInteger id){
//		return encryptColumBaseDao.deleteEncryptColum(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(加密字段)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEncryptColumBatch(List<java.math.BigInteger> idList){
//		return encryptColumBaseDao.deleteEncryptColumBatch(idList);
//	}
	
}
