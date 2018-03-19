package com.cnfantasia.server.domainbase.couponScene.service;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;
import com.cnfantasia.server.domainbase.couponScene.entity.CouponScene;

/**
 * 描述:(券场景) 服务类接口定义
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICouponSceneBaseService {
	
	/**
	 * 根据条件查询(券场景)信息,精确查询
	 * @param paramMap 
	 * @return
	 */
	public List<CouponScene> getCouponSceneByCondition(Map<String,Object> paramMap);
	/**
	 * 根据条件查询(券场景)信息,模糊查询
	 * @param paramMap 
	 * @return
	 */
	public List<CouponScene> getCouponSceneByConditionDim(Map<String,Object> paramMap);
	/**
	 * 按条件分页查询(券场景)信息,精确查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CouponScene> getCouponSceneByCondition(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 按条件分页查询(券场景)信息,模糊查询
	 * @param paramMap
	 * @param pageModel
	 * @return
	 */
	public List<CouponScene> getCouponSceneByConditionDim(Map<String,Object> paramMap,PageModel pageModel);
	/**
	 * 根据序列号查询(券场景)信息
	 * @param id
	 * @return
	 */
	public CouponScene getCouponSceneBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(券场景)记录数,精确查询
	 * @param paramMap
	 * @return
	 */
	public int getCouponSceneCount(Map<String,Object> paramMap);
	/**
	 * 根据条件查询满足条件的(券场景)记录数,模糊查询
	 * @param paramMap
	 * @return
	 */
	public int getCouponSceneCountDim(Map<String,Object> paramMap);
	/**
	 * 往(券场景)新增一条记录
	 * @param couponScene
	 * @return
	 */
	public int insertCouponScene(CouponScene couponScene);
	/**
	 * 批量新增(券场景)
	 * @param couponSceneList
	 * @return
	 */
	public int insertCouponSceneBatch(List<CouponScene> couponSceneList);
	/**
	 * 更新(券场景)信息
	 * @param couponScene
	 * @return
	 */
	public int updateCouponScene(CouponScene couponScene);
	/**
	 * 批量更新(券场景)信息
	 * @param couponSceneList
	 * @return
	 */
	public int updateCouponSceneBatch(List<CouponScene> couponSceneList);
	/**
	 * 根据序列号删除(券场景)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	public int deleteCouponSceneLogic(java.math.BigInteger id);
	
	/**
	 * 根据序列号批量删除(券场景)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	public int deleteCouponSceneLogicBatch(List<java.math.BigInteger> idList);
	
//	/**
//	 * 根据序列号删除(券场景)信息
//	 * @param id
//	 * @return
//	 */
//	public int deleteCouponScene(java.math.BigInteger id);
//	
//	/**
//	 * 根据序列号批量删除(券场景)信息
//	 * @param idList
//	 * @return
//	 */
//	public int deleteCouponSceneBatch(List<java.math.BigInteger> idList);
	
}
