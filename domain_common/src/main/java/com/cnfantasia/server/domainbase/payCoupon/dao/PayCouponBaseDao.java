package com.cnfantasia.server.domainbase.payCoupon.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.payCoupon.entity.PayCoupon;

/**
 * 描述:(优惠表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PayCouponBaseDao extends AbstractBaseDao implements IPayCouponBaseDao{
	/**
	 * 根据条件查询(优惠表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PayCoupon> selectPayCouponByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("payCouponBase.select_payCoupon",paramMap);
	}
	/**
	 * 按条件分页查询(优惠表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PayCoupon> selectPayCouponByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPayCouponCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PayCoupon> resMap= sqlSession.selectList("payCouponBase.select_payCoupon_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(优惠表)信息
	 * @param id
	 * @return
	 */
	@Override
	public PayCoupon selectPayCouponBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("payCouponBase.select_payCoupon_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(优惠表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPayCouponCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("payCouponBase.select_payCoupon_count", paramMap);
	}
	/**
	 * 往(优惠表)新增一条记录
	 * @param payCoupon
	 * @return
	 */
	@Override
	public int insertPayCoupon(PayCoupon payCoupon){
		return sqlSession.insert("payCouponBase.insert_payCoupon",payCoupon);
	}
	/**
	 * 批量新增(优惠表)信息
	 * @param payCouponList
	 * @return
	 */
	@Override
	public int insertPayCouponBatch(List<PayCoupon> payCouponList) {
		return sqlSession.insert("payCouponBase.insert_payCoupon_Batch",payCouponList);
	}
	
	/**
	 * 更新(优惠表)信息
	 * @param payCoupon
	 * @return
	 */
	@Override
	public int updatePayCoupon(PayCoupon payCoupon){
		return sqlSession.update("payCouponBase.update_payCoupon", payCoupon);
	}
	/**
	 * 批量更新(优惠表)信息
	 * @param payCouponList
	 * @return
	 */
	@Override
	public int updatePayCouponBatch(List<PayCoupon> payCouponList) {
		return sqlSession.update("payCouponBase.update_payCoupon_Batch", payCouponList);
	}
	
	/**
	 * 根据序列号删除(优惠表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePayCouponLogic(java.math.BigInteger id){
		PayCoupon payCoupon = new PayCoupon();
		payCoupon.setId(id);
		payCoupon.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("payCouponBase.delete_payCoupon_Logic",payCoupon);
	}
	
	/**
	 * 根据Id 批量删除(优惠表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePayCouponLogicBatch(List<java.math.BigInteger> idList) {
		List<PayCoupon> delList = new java.util.ArrayList<PayCoupon>();
		for(java.math.BigInteger id:idList){
			PayCoupon payCoupon = new PayCoupon();
			payCoupon.setId(id);
			payCoupon.setSys0DelState(RecordState_DELETED);
			delList.add(payCoupon);
		}
		return sqlSession.update("payCouponBase.delete_payCoupon_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(优惠表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePayCoupon(java.math.BigInteger id){
//		return sqlSession.delete("payCouponBase.delete_payCoupon", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(优惠表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePayCouponBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("payCouponBase.delete_payCoupon_Batch", idList);
//	}
	
	
}
