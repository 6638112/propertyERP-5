package com.cnfantasia.server.domainbase.couponArea.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.couponArea.dao.ICouponAreaBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.couponArea.entity.CouponArea;

/**
 * 描述:(消费券发放区域表) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CouponAreaBaseService implements ICouponAreaBaseService{
	
	private ICouponAreaBaseDao couponAreaBaseDao;
	public void setCouponAreaBaseDao(ICouponAreaBaseDao couponAreaBaseDao) {
		this.couponAreaBaseDao = couponAreaBaseDao;
	}
	/**
	 * 根据条件查询(消费券发放区域表)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CouponArea> getCouponAreaByCondition(Map<String,Object> paramMap){
		return couponAreaBaseDao.selectCouponAreaByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(消费券发放区域表)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CouponArea> getCouponAreaByConditionDim(Map<String,Object> paramMap){
		return couponAreaBaseDao.selectCouponAreaByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(消费券发放区域表)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CouponArea> getCouponAreaByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return couponAreaBaseDao.selectCouponAreaByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(消费券发放区域表)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CouponArea> getCouponAreaByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return couponAreaBaseDao.selectCouponAreaByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(消费券发放区域表)信息
	 * @param id
	 * @return
	 */
	@Override
	public CouponArea getCouponAreaBySeqId(java.math.BigInteger id){
		return couponAreaBaseDao.selectCouponAreaBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(消费券发放区域表)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCouponAreaCount(Map<String,Object> paramMap){
		return couponAreaBaseDao.selectCouponAreaCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(消费券发放区域表)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCouponAreaCountDim(Map<String,Object> paramMap){
		return couponAreaBaseDao.selectCouponAreaCount(paramMap,true);
	}
	/**
	 * 往(消费券发放区域表)新增一条记录
	 * @param couponArea
	 * @return
	 */
	@Override
	public int insertCouponArea(CouponArea couponArea){
		return couponAreaBaseDao.insertCouponArea(couponArea);
	}
	/**
	 * 批量新增(消费券发放区域表)
	 * @param couponAreaList
	 * @return
	 */
	@Override
	public int insertCouponAreaBatch(List<CouponArea> couponAreaList){
		return couponAreaBaseDao.insertCouponAreaBatch(couponAreaList);
	}
	/**
	 * 更新(消费券发放区域表)信息
	 * @param couponArea
	 * @return
	 */
	@Override
	public int updateCouponArea(CouponArea couponArea){
		return couponAreaBaseDao.updateCouponArea(couponArea);
	}
	/**
	 * 批量更新(消费券发放区域表)信息
	 * @param couponAreaList
	 * @return
	 */
	@Override
	public int updateCouponAreaBatch(List<CouponArea> couponAreaList){
		return couponAreaBaseDao.updateCouponAreaBatch(couponAreaList);
	}
	/**
	 * 根据序列号删除(消费券发放区域表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	@Override
	public int deleteCouponAreaLogic(java.math.BigInteger id){
		return couponAreaBaseDao.deleteCouponAreaLogic(id);
	}
	 */
	/**
	 * 根据序列号批量删除(消费券发放区域表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	@Override
	public int deleteCouponAreaLogicBatch(List<java.math.BigInteger> idList){
		return couponAreaBaseDao.deleteCouponAreaLogicBatch(idList);
	}
	 */
//	/**
//	 * 根据序列号删除(消费券发放区域表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCouponArea(java.math.BigInteger id){
//		return couponAreaBaseDao.deleteCouponArea(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(消费券发放区域表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCouponAreaBatch(List<java.math.BigInteger> idList){
//		return couponAreaBaseDao.deleteCouponAreaBatch(idList);
//	}
	
}
