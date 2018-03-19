package com.cnfantasia.server.domainbase.coupon.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.coupon.entity.Coupon;

/**
 * 描述:(消费券表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CouponBaseDao extends AbstractBaseDao implements ICouponBaseDao{
	/**
	 * 根据条件查询(消费券表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<Coupon> selectCouponByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("couponBase.select_coupon",paramMap);
	}
	/**
	 * 按条件分页查询(消费券表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<Coupon> selectCouponByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCouponCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<Coupon> resMap= sqlSession.selectList("couponBase.select_coupon_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(消费券表)信息
	 * @param id
	 * @return
	 */
	@Override
	public Coupon selectCouponBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("couponBase.select_coupon_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(消费券表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCouponCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("couponBase.select_coupon_count", paramMap);
	}
	/**
	 * 往(消费券表)新增一条记录
	 * @param coupon
	 * @return
	 */
	@Override
	public int insertCoupon(Coupon coupon){
		return sqlSession.insert("couponBase.insert_coupon",coupon);
	}
	/**
	 * 批量新增(消费券表)信息
	 * @param couponList
	 * @return
	 */
	@Override
	public int insertCouponBatch(List<Coupon> couponList) {
		return sqlSession.insert("couponBase.insert_coupon_Batch",couponList);
	}
	
	/**
	 * 更新(消费券表)信息
	 * @param coupon
	 * @return
	 */
	@Override
	public int updateCoupon(Coupon coupon){
		return sqlSession.update("couponBase.update_coupon", coupon);
	}
	/**
	 * 批量更新(消费券表)信息
	 * @param couponList
	 * @return
	 */
	@Override
	public int updateCouponBatch(List<Coupon> couponList) {
		return sqlSession.update("couponBase.update_coupon_Batch", couponList);
	}
	
	/**
	 * 根据序列号删除(消费券表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	@Override
	public int deleteCouponLogic(java.math.BigInteger id){
		Coupon coupon = new Coupon();
		coupon.setId(id);
		coupon.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("couponBase.delete_coupon_Logic",coupon);
	}
	 */
	/**
	 * 根据Id 批量删除(消费券表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	@Override
	public int deleteCouponLogicBatch(List<java.math.BigInteger> idList) {
		List<Coupon> delList = new java.util.ArrayList<Coupon>();
		for(java.math.BigInteger id:idList){
			Coupon coupon = new Coupon();
			coupon.setId(id);
			coupon.setSys0DelState(RecordState_DELETED);
			delList.add(coupon);
		}
		return sqlSession.update("couponBase.delete_coupon_Logic_Batch",delList);
	}
	 */
//	/**
//	 * 根据序列号删除(消费券表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCoupon(java.math.BigInteger id){
//		return sqlSession.delete("couponBase.delete_coupon", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(消费券表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCouponBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("couponBase.delete_coupon_Batch", idList);
//	}
	
	
}
