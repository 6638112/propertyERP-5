package com.cnfantasia.server.domainbase.ebuyAdvertise.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.ebuyAdvertise.dao.IEbuyAdvertiseBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyAdvertise.entity.EbuyAdvertise;

/**
 * 描述:() 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class EbuyAdvertiseBaseService implements IEbuyAdvertiseBaseService{
	
	private IEbuyAdvertiseBaseDao ebuyAdvertiseBaseDao;
	public void setEbuyAdvertiseBaseDao(IEbuyAdvertiseBaseDao ebuyAdvertiseBaseDao) {
		this.ebuyAdvertiseBaseDao = ebuyAdvertiseBaseDao;
	}
	/**
	 * 根据条件查询()信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyAdvertise> getEbuyAdvertiseByCondition(Map<String,Object> paramMap){
		return ebuyAdvertiseBaseDao.selectEbuyAdvertiseByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询()信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<EbuyAdvertise> getEbuyAdvertiseByConditionDim(Map<String,Object> paramMap){
		return ebuyAdvertiseBaseDao.selectEbuyAdvertiseByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询()信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyAdvertise> getEbuyAdvertiseByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyAdvertiseBaseDao.selectEbuyAdvertiseByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询()信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<EbuyAdvertise> getEbuyAdvertiseByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return ebuyAdvertiseBaseDao.selectEbuyAdvertiseByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询()信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyAdvertise getEbuyAdvertiseBySeqId(java.math.BigInteger id){
		return ebuyAdvertiseBaseDao.selectEbuyAdvertiseBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的()记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyAdvertiseCount(Map<String,Object> paramMap){
		return ebuyAdvertiseBaseDao.selectEbuyAdvertiseCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的()记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getEbuyAdvertiseCountDim(Map<String,Object> paramMap){
		return ebuyAdvertiseBaseDao.selectEbuyAdvertiseCount(paramMap,true);
	}
	/**
	 * 往()新增一条记录
	 * @param ebuyAdvertise
	 * @return
	 */
	@Override
	public int insertEbuyAdvertise(EbuyAdvertise ebuyAdvertise){
		return ebuyAdvertiseBaseDao.insertEbuyAdvertise(ebuyAdvertise);
	}
	/**
	 * 批量新增()
	 * @param ebuyAdvertiseList
	 * @return
	 */
	@Override
	public int insertEbuyAdvertiseBatch(List<EbuyAdvertise> ebuyAdvertiseList){
		return ebuyAdvertiseBaseDao.insertEbuyAdvertiseBatch(ebuyAdvertiseList);
	}
	/**
	 * 更新()信息
	 * @param ebuyAdvertise
	 * @return
	 */
	@Override
	public int updateEbuyAdvertise(EbuyAdvertise ebuyAdvertise){
		return ebuyAdvertiseBaseDao.updateEbuyAdvertise(ebuyAdvertise);
	}
	/**
	 * 批量更新()信息
	 * @param ebuyAdvertiseList
	 * @return
	 */
	@Override
	public int updateEbuyAdvertiseBatch(List<EbuyAdvertise> ebuyAdvertiseList){
		return ebuyAdvertiseBaseDao.updateEbuyAdvertiseBatch(ebuyAdvertiseList);
	}
	/**
	 * 根据序列号删除()信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyAdvertiseLogic(java.math.BigInteger id){
		return ebuyAdvertiseBaseDao.deleteEbuyAdvertiseLogic(id);
	}
	
	/**
	 * 根据序列号批量删除()信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyAdvertiseLogicBatch(List<java.math.BigInteger> idList){
		return ebuyAdvertiseBaseDao.deleteEbuyAdvertiseLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除()信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyAdvertise(java.math.BigInteger id){
//		return ebuyAdvertiseBaseDao.deleteEbuyAdvertise(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除()信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyAdvertiseBatch(List<java.math.BigInteger> idList){
//		return ebuyAdvertiseBaseDao.deleteEbuyAdvertiseBatch(idList);
//	}
	
}
