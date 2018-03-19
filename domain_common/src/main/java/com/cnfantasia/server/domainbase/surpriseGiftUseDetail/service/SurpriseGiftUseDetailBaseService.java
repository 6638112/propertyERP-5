package com.cnfantasia.server.domainbase.surpriseGiftUseDetail.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.surpriseGiftUseDetail.dao.ISurpriseGiftUseDetailBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.surpriseGiftUseDetail.entity.SurpriseGiftUseDetail;

/**
 * 描述:(意外惊喜的优惠券使用详情) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class SurpriseGiftUseDetailBaseService implements ISurpriseGiftUseDetailBaseService{
	
	private ISurpriseGiftUseDetailBaseDao surpriseGiftUseDetailBaseDao;
	public void setSurpriseGiftUseDetailBaseDao(ISurpriseGiftUseDetailBaseDao surpriseGiftUseDetailBaseDao) {
		this.surpriseGiftUseDetailBaseDao = surpriseGiftUseDetailBaseDao;
	}
	/**
	 * 根据条件查询(意外惊喜的优惠券使用详情)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<SurpriseGiftUseDetail> getSurpriseGiftUseDetailByCondition(Map<String,Object> paramMap){
		return surpriseGiftUseDetailBaseDao.selectSurpriseGiftUseDetailByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(意外惊喜的优惠券使用详情)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<SurpriseGiftUseDetail> getSurpriseGiftUseDetailByConditionDim(Map<String,Object> paramMap){
		return surpriseGiftUseDetailBaseDao.selectSurpriseGiftUseDetailByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(意外惊喜的优惠券使用详情)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<SurpriseGiftUseDetail> getSurpriseGiftUseDetailByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return surpriseGiftUseDetailBaseDao.selectSurpriseGiftUseDetailByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(意外惊喜的优惠券使用详情)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<SurpriseGiftUseDetail> getSurpriseGiftUseDetailByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return surpriseGiftUseDetailBaseDao.selectSurpriseGiftUseDetailByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(意外惊喜的优惠券使用详情)信息
	 * @param id
	 * @return
	 */
	@Override
	public SurpriseGiftUseDetail getSurpriseGiftUseDetailBySeqId(java.math.BigInteger id){
		return surpriseGiftUseDetailBaseDao.selectSurpriseGiftUseDetailBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(意外惊喜的优惠券使用详情)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getSurpriseGiftUseDetailCount(Map<String,Object> paramMap){
		return surpriseGiftUseDetailBaseDao.selectSurpriseGiftUseDetailCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(意外惊喜的优惠券使用详情)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getSurpriseGiftUseDetailCountDim(Map<String,Object> paramMap){
		return surpriseGiftUseDetailBaseDao.selectSurpriseGiftUseDetailCount(paramMap,true);
	}
	/**
	 * 往(意外惊喜的优惠券使用详情)新增一条记录
	 * @param surpriseGiftUseDetail
	 * @return
	 */
	@Override
	public int insertSurpriseGiftUseDetail(SurpriseGiftUseDetail surpriseGiftUseDetail){
		return surpriseGiftUseDetailBaseDao.insertSurpriseGiftUseDetail(surpriseGiftUseDetail);
	}
	/**
	 * 批量新增(意外惊喜的优惠券使用详情)
	 * @param surpriseGiftUseDetailList
	 * @return
	 */
	@Override
	public int insertSurpriseGiftUseDetailBatch(List<SurpriseGiftUseDetail> surpriseGiftUseDetailList){
		return surpriseGiftUseDetailBaseDao.insertSurpriseGiftUseDetailBatch(surpriseGiftUseDetailList);
	}
	/**
	 * 更新(意外惊喜的优惠券使用详情)信息
	 * @param surpriseGiftUseDetail
	 * @return
	 */
	@Override
	public int updateSurpriseGiftUseDetail(SurpriseGiftUseDetail surpriseGiftUseDetail){
		return surpriseGiftUseDetailBaseDao.updateSurpriseGiftUseDetail(surpriseGiftUseDetail);
	}
	/**
	 * 批量更新(意外惊喜的优惠券使用详情)信息
	 * @param surpriseGiftUseDetailList
	 * @return
	 */
	@Override
	public int updateSurpriseGiftUseDetailBatch(List<SurpriseGiftUseDetail> surpriseGiftUseDetailList){
		return surpriseGiftUseDetailBaseDao.updateSurpriseGiftUseDetailBatch(surpriseGiftUseDetailList);
	}
	/**
	 * 根据序列号删除(意外惊喜的优惠券使用详情)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteSurpriseGiftUseDetailLogic(java.math.BigInteger id){
		return surpriseGiftUseDetailBaseDao.deleteSurpriseGiftUseDetailLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(意外惊喜的优惠券使用详情)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteSurpriseGiftUseDetailLogicBatch(List<java.math.BigInteger> idList){
		return surpriseGiftUseDetailBaseDao.deleteSurpriseGiftUseDetailLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(意外惊喜的优惠券使用详情)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteSurpriseGiftUseDetail(java.math.BigInteger id){
//		return surpriseGiftUseDetailBaseDao.deleteSurpriseGiftUseDetail(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(意外惊喜的优惠券使用详情)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteSurpriseGiftUseDetailBatch(List<java.math.BigInteger> idList){
//		return surpriseGiftUseDetailBaseDao.deleteSurpriseGiftUseDetailBatch(idList);
//	}
	
}
