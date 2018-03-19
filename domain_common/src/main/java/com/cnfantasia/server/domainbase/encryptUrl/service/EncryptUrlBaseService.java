package com.cnfantasia.server.domainbase.encryptUrl.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.encryptUrl.dao.IEncryptUrlBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.encryptUrl.entity.EncryptUrl;

/**
 * 描述:(加密url) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EncryptUrlBaseService implements IEncryptUrlBaseService{
	
	private IEncryptUrlBaseDao encryptUrlBaseDao;
	public void setEncryptUrlBaseDao(IEncryptUrlBaseDao encryptUrlBaseDao) {
		this.encryptUrlBaseDao = encryptUrlBaseDao;
	}
	/**
	 * 根据条件查询(加密url)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EncryptUrl> getEncryptUrlByCondition(Map<String,Object> paramMap){
		return encryptUrlBaseDao.selectEncryptUrlByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(加密url)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EncryptUrl> getEncryptUrlByConditionDim(Map<String,Object> paramMap){
		return encryptUrlBaseDao.selectEncryptUrlByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(加密url)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EncryptUrl> getEncryptUrlByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return encryptUrlBaseDao.selectEncryptUrlByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(加密url)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EncryptUrl> getEncryptUrlByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return encryptUrlBaseDao.selectEncryptUrlByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(加密url)信息
	 * @param id
	 * @return
	 */
	@Override
	public EncryptUrl getEncryptUrlBySeqId(java.math.BigInteger id){
		return encryptUrlBaseDao.selectEncryptUrlBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(加密url)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEncryptUrlCount(Map<String,Object> paramMap){
		return encryptUrlBaseDao.selectEncryptUrlCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(加密url)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEncryptUrlCountDim(Map<String,Object> paramMap){
		return encryptUrlBaseDao.selectEncryptUrlCount(paramMap,true);
	}
	/**
	 * 往(加密url)新增一条记录
	 * @param encryptUrl
	 * @return
	 */
	@Override
	public int insertEncryptUrl(EncryptUrl encryptUrl){
		return encryptUrlBaseDao.insertEncryptUrl(encryptUrl);
	}
	/**
	 * 批量新增(加密url)
	 * @param encryptUrlList
	 * @return
	 */
	@Override
	public int insertEncryptUrlBatch(List<EncryptUrl> encryptUrlList){
		return encryptUrlBaseDao.insertEncryptUrlBatch(encryptUrlList);
	}
	/**
	 * 更新(加密url)信息
	 * @param encryptUrl
	 * @return
	 */
	@Override
	public int updateEncryptUrl(EncryptUrl encryptUrl){
		return encryptUrlBaseDao.updateEncryptUrl(encryptUrl);
	}
	/**
	 * 批量更新(加密url)信息
	 * @param encryptUrlList
	 * @return
	 */
	@Override
	public int updateEncryptUrlBatch(List<EncryptUrl> encryptUrlList){
		return encryptUrlBaseDao.updateEncryptUrlBatch(encryptUrlList);
	}
	/**
	 * 根据序列号删除(加密url)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEncryptUrlLogic(java.math.BigInteger id){
		return encryptUrlBaseDao.deleteEncryptUrlLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(加密url)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEncryptUrlLogicBatch(List<java.math.BigInteger> idList){
		return encryptUrlBaseDao.deleteEncryptUrlLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(加密url)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEncryptUrl(java.math.BigInteger id){
//		return encryptUrlBaseDao.deleteEncryptUrl(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(加密url)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEncryptUrlBatch(List<java.math.BigInteger> idList){
//		return encryptUrlBaseDao.deleteEncryptUrlBatch(idList);
//	}
	
}
