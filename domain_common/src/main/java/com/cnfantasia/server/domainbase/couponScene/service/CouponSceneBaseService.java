package com.cnfantasia.server.domainbase.couponScene.service;

import java.util.Map;
import java.util.List;

import org.springframework.stereotype.Service;

import  com.cnfantasia.server.domainbase.couponScene.dao.ICouponSceneBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.couponScene.entity.CouponScene;

/**
 * 描述:(券场景) 服务类
 * 
 * @version 1.00
 * @author null
 * 
 */
@Service
public class CouponSceneBaseService implements ICouponSceneBaseService{
	
	private ICouponSceneBaseDao couponSceneBaseDao;
	public void setCouponSceneBaseDao(ICouponSceneBaseDao couponSceneBaseDao) {
		this.couponSceneBaseDao = couponSceneBaseDao;
	}
	/**
	 * 根据条件查询(券场景)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CouponScene> getCouponSceneByCondition(Map<String,Object> paramMap){
		return couponSceneBaseDao.selectCouponSceneByCondition(paramMap,false);
	}
	/**
	 * 根据条件查询(券场景)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	@Override
	public List<CouponScene> getCouponSceneByConditionDim(Map<String,Object> paramMap){
		return couponSceneBaseDao.selectCouponSceneByCondition(paramMap,true);
	}
	/**
	 * 按条件分页查询(券场景)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CouponScene> getCouponSceneByCondition(Map<String,Object> paramMap,PageModel pageModel) {
		return couponSceneBaseDao.selectCouponSceneByCondition(paramMap, pageModel,false);
	}
	/**
	 * 按条件分页查询(券场景)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	@Override
	public List<CouponScene> getCouponSceneByConditionDim(Map<String,Object> paramMap,PageModel pageModel) {
		return couponSceneBaseDao.selectCouponSceneByCondition(paramMap, pageModel,true);
	}
	/**
	 * 根据序列号查询(券场景)信息
	 * @param id
	 * @return
	 */
	@Override
	public CouponScene getCouponSceneBySeqId(java.math.BigInteger id){
		return couponSceneBaseDao.selectCouponSceneBySeqId(id);
	}
	/**
	 * 根据条件查询满足条件的(券场景)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCouponSceneCount(Map<String,Object> paramMap){
		return couponSceneBaseDao.selectCouponSceneCount(paramMap,false);
	}
	/**
	 * 根据条件查询满足条件的(券场景)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	@Override
	public int getCouponSceneCountDim(Map<String,Object> paramMap){
		return couponSceneBaseDao.selectCouponSceneCount(paramMap,true);
	}
	/**
	 * 往(券场景)新增一条记录
	 * @param couponScene
	 * @return
	 */
	@Override
	public int insertCouponScene(CouponScene couponScene){
		return couponSceneBaseDao.insertCouponScene(couponScene);
	}
	/**
	 * 批量新增(券场景)
	 * @param couponSceneList
	 * @return
	 */
	@Override
	public int insertCouponSceneBatch(List<CouponScene> couponSceneList){
		return couponSceneBaseDao.insertCouponSceneBatch(couponSceneList);
	}
	/**
	 * 更新(券场景)信息
	 * @param couponScene
	 * @return
	 */
	@Override
	public int updateCouponScene(CouponScene couponScene){
		return couponSceneBaseDao.updateCouponScene(couponScene);
	}
	/**
	 * 批量更新(券场景)信息
	 * @param couponSceneList
	 * @return
	 */
	@Override
	public int updateCouponSceneBatch(List<CouponScene> couponSceneList){
		return couponSceneBaseDao.updateCouponSceneBatch(couponSceneList);
	}
	/**
	 * 根据序列号删除(券场景)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCouponSceneLogic(java.math.BigInteger id){
		return couponSceneBaseDao.deleteCouponSceneLogic(id);
	}
	
	/**
	 * 根据序列号批量删除(券场景)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCouponSceneLogicBatch(List<java.math.BigInteger> idList){
		return couponSceneBaseDao.deleteCouponSceneLogicBatch(idList);
	}
	
//	/**
//	 * 根据序列号删除(券场景)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCouponScene(java.math.BigInteger id){
//		return couponSceneBaseDao.deleteCouponScene(id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(券场景)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCouponSceneBatch(List<java.math.BigInteger> idList){
//		return couponSceneBaseDao.deleteCouponSceneBatch(idList);
//	}
	
}
