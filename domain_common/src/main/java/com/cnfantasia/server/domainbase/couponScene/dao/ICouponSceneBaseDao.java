package com.cnfantasia.server.domainbase.couponScene.dao;

import java.util.Map;
import java.util.List;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.couponScene.entity.CouponScene;

/**
 * 描述:(券场景) DAO层接口
 * 
 * @version 1.00
 * @author null
 * 
 */
public interface ICouponSceneBaseDao {
	/**
	 * 根据条件查询(券场景)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CouponScene> selectCouponSceneByCondition(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 按条件分页查询(券场景)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public List<CouponScene> selectCouponSceneByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim);
	/**
	 * 根据序列号查询(券场景)信息
	 * @param id
	 * @return
	 */
	public CouponScene selectCouponSceneBySeqId(java.math.BigInteger id);
	/**
	 * 根据条件查询满足条件的(券场景)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	public int selectCouponSceneCount(Map<String,Object> paramMap,boolean isDim);
	/**
	 * 往(券场景)新增一条记录
	 * @param couponScene
	 * @return
	 */
	public int insertCouponScene(CouponScene couponScene);
	
	/**
	 * 批量新增(券场景)信息
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
	 * 根据Id 批量删除(券场景)信息_逻辑删除
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
