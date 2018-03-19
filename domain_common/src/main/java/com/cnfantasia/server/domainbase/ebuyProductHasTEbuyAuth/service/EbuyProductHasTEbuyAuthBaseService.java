package com.cnfantasia.server.domainbase.ebuyProductHasTEbuyAuth.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuyProductHasTEbuyAuth.dao.IEbuyProductHasTEbuyAuthBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyProductHasTEbuyAuth.entity.EbuyProductHasTEbuyAuth;

/**
 * 描述:() 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuyProductHasTEbuyAuthBaseService implements IEbuyProductHasTEbuyAuthBaseService{
	
	private IEbuyProductHasTEbuyAuthBaseDao ebuyProductHasTEbuyAuthBaseDao;
	public void setEbuyProductHasTEbuyAuthBaseDao(IEbuyProductHasTEbuyAuthBaseDao ebuyProductHasTEbuyAuthBaseDao) {
		this.ebuyProductHasTEbuyAuthBaseDao = ebuyProductHasTEbuyAuthBaseDao;
	}
	/**
	 * 根据条件查询()信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyProductHasTEbuyAuth> getEbuyProductHasTEbuyAuthByCondition(Map<String,Object> paramMap){
		return ebuyProductHasTEbuyAuthBaseDao.selectEbuyProductHasTEbuyAuthByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询()信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyProductHasTEbuyAuth> getEbuyProductHasTEbuyAuthByConditionDim(Map<String,Object> paramMap){
		return ebuyProductHasTEbuyAuthBaseDao.selectEbuyProductHasTEbuyAuthByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询()信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyProductHasTEbuyAuth> getEbuyProductHasTEbuyAuthByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyProductHasTEbuyAuthBaseDao.selectEbuyProductHasTEbuyAuthByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询()信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyProductHasTEbuyAuth> getEbuyProductHasTEbuyAuthByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyProductHasTEbuyAuthBaseDao.selectEbuyProductHasTEbuyAuthByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询()信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyProductHasTEbuyAuth getEbuyProductHasTEbuyAuthBySeqId(java.math.BigInteger id){
		return ebuyProductHasTEbuyAuthBaseDao.selectEbuyProductHasTEbuyAuthBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的()记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyProductHasTEbuyAuthCount(Map<String,Object> paramMap){
		return ebuyProductHasTEbuyAuthBaseDao.selectEbuyProductHasTEbuyAuthCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的()记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyProductHasTEbuyAuthCountDim(Map<String,Object> paramMap){
		return ebuyProductHasTEbuyAuthBaseDao.selectEbuyProductHasTEbuyAuthCount(paramMap,true);
	}
	/**
	 * 往()新增一条记录
	 * @param ebuyProductHasTEbuyAuth
	 * @return
	 */
	@Override
	public int insertEbuyProductHasTEbuyAuth(EbuyProductHasTEbuyAuth ebuyProductHasTEbuyAuth){
		return ebuyProductHasTEbuyAuthBaseDao.insertEbuyProductHasTEbuyAuth(ebuyProductHasTEbuyAuth);
	}
	/**
	 * 批量新增()
	 * @param ebuyProductHasTEbuyAuthList
	 * @return
	 */
	@Override
	public int insertEbuyProductHasTEbuyAuthBatch(List<EbuyProductHasTEbuyAuth> ebuyProductHasTEbuyAuthList){
		return ebuyProductHasTEbuyAuthBaseDao.insertEbuyProductHasTEbuyAuthBatch(ebuyProductHasTEbuyAuthList);
	}
	/**
	 * 更新()信息
	 * @param ebuyProductHasTEbuyAuth
	 * @return
	 */
	@Override
	public int updateEbuyProductHasTEbuyAuth(EbuyProductHasTEbuyAuth ebuyProductHasTEbuyAuth){
		return ebuyProductHasTEbuyAuthBaseDao.updateEbuyProductHasTEbuyAuth(ebuyProductHasTEbuyAuth);
	}
	/**
	 * 批量更新()信息
	 * @param ebuyProductHasTEbuyAuthList
	 * @return
	 */
	@Override
	public int updateEbuyProductHasTEbuyAuthBatch(List<EbuyProductHasTEbuyAuth> ebuyProductHasTEbuyAuthList){
		return ebuyProductHasTEbuyAuthBaseDao.updateEbuyProductHasTEbuyAuthBatch(ebuyProductHasTEbuyAuthList);
	}
	/**
	 * 根据序列号删除()信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyProductHasTEbuyAuthLogic(java.math.BigInteger id){
		return ebuyProductHasTEbuyAuthBaseDao.deleteEbuyProductHasTEbuyAuthLogic(id);
	}
	
	/**
	 * 根据序列号批量删除()信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyProductHasTEbuyAuthLogicBatch(List<java.math.BigInteger> idList){
		return ebuyProductHasTEbuyAuthBaseDao.deleteEbuyProductHasTEbuyAuthLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除()信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductHasTEbuyAuth(java.math.BigInteger id){
//		return ebuyProductHasTEbuyAuthBaseDao.deleteEbuyProductHasTEbuyAuth(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除()信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductHasTEbuyAuthBatch(List<java.math.BigInteger> idList){
//		return ebuyProductHasTEbuyAuthBaseDao.deleteEbuyProductHasTEbuyAuthBatch(idList);
//	}
	
}
