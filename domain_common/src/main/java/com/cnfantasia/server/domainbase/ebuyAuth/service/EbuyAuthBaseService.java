package com.cnfantasia.server.domainbase.ebuyAuth.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuyAuth.dao.IEbuyAuthBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyAuth.entity.EbuyAuth;

/**
 * 描述:(认证类别信息) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuyAuthBaseService implements IEbuyAuthBaseService{
	
	private IEbuyAuthBaseDao ebuyAuthBaseDao;
	public void setEbuyAuthBaseDao(IEbuyAuthBaseDao ebuyAuthBaseDao) {
		this.ebuyAuthBaseDao = ebuyAuthBaseDao;
	}
	/**
	 * 根据条件查询(认证类别信息)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyAuth> getEbuyAuthByCondition(Map<String,Object> paramMap){
		return ebuyAuthBaseDao.selectEbuyAuthByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(认证类别信息)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyAuth> getEbuyAuthByConditionDim(Map<String,Object> paramMap){
		return ebuyAuthBaseDao.selectEbuyAuthByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(认证类别信息)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyAuth> getEbuyAuthByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyAuthBaseDao.selectEbuyAuthByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(认证类别信息)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyAuth> getEbuyAuthByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyAuthBaseDao.selectEbuyAuthByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(认证类别信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyAuth getEbuyAuthBySeqId(java.math.BigInteger id){
		return ebuyAuthBaseDao.selectEbuyAuthBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(认证类别信息)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyAuthCount(Map<String,Object> paramMap){
		return ebuyAuthBaseDao.selectEbuyAuthCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(认证类别信息)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyAuthCountDim(Map<String,Object> paramMap){
		return ebuyAuthBaseDao.selectEbuyAuthCount(paramMap,true);
	}
	/**
	 * 往(认证类别信息)新增一条记录
	 * @param ebuyAuth
	 * @return
	 */
	@Override
	public int insertEbuyAuth(EbuyAuth ebuyAuth){
		return ebuyAuthBaseDao.insertEbuyAuth(ebuyAuth);
	}
	/**
	 * 批量新增(认证类别信息)
	 * @param ebuyAuthList
	 * @return
	 */
	@Override
	public int insertEbuyAuthBatch(List<EbuyAuth> ebuyAuthList){
		return ebuyAuthBaseDao.insertEbuyAuthBatch(ebuyAuthList);
	}
	/**
	 * 更新(认证类别信息)信息
	 * @param ebuyAuth
	 * @return
	 */
	@Override
	public int updateEbuyAuth(EbuyAuth ebuyAuth){
		return ebuyAuthBaseDao.updateEbuyAuth(ebuyAuth);
	}
	/**
	 * 批量更新(认证类别信息)信息
	 * @param ebuyAuthList
	 * @return
	 */
	@Override
	public int updateEbuyAuthBatch(List<EbuyAuth> ebuyAuthList){
		return ebuyAuthBaseDao.updateEbuyAuthBatch(ebuyAuthList);
	}
	/**
	 * 根据序列号删除(认证类别信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyAuthLogic(java.math.BigInteger id){
		return ebuyAuthBaseDao.deleteEbuyAuthLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(认证类别信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyAuthLogicBatch(List<java.math.BigInteger> idList){
		return ebuyAuthBaseDao.deleteEbuyAuthLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(认证类别信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyAuth(java.math.BigInteger id){
//		return ebuyAuthBaseDao.deleteEbuyAuth(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(认证类别信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyAuthBatch(List<java.math.BigInteger> idList){
//		return ebuyAuthBaseDao.deleteEbuyAuthBatch(idList);
//	}
	
}
