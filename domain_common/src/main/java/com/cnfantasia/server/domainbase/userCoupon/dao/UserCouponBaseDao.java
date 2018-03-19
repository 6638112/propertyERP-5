package com.cnfantasia.server.domainbase.userCoupon.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.userCoupon.entity.UserCoupon;

/**
 * 描述:(用户所持消费券表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class UserCouponBaseDao extends AbstractBaseDao implements IUserCouponBaseDao{
	/**
	 * 根据条件查询(用户所持消费券表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<UserCoupon> selectUserCouponByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("userCouponBase.select_userCoupon",paramMap);
	}
	/**
	 * 按条件分页查询(用户所持消费券表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<UserCoupon> selectUserCouponByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectUserCouponCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<UserCoupon> resMap= sqlSession.selectList("userCouponBase.select_userCoupon_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(用户所持消费券表)信息
	 * @param id
	 * @return
	 */
	@Override
	public UserCoupon selectUserCouponBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("userCouponBase.select_userCoupon_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(用户所持消费券表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectUserCouponCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("userCouponBase.select_userCoupon_count", paramMap);
	}
	/**
	 * 往(用户所持消费券表)新增一条记录
	 * @param userCoupon
	 * @return
	 */
	@Override
	public int insertUserCoupon(UserCoupon userCoupon){
		return sqlSession.insert("userCouponBase.insert_userCoupon",userCoupon);
	}
	/**
	 * 批量新增(用户所持消费券表)信息
	 * @param userCouponList
	 * @return
	 */
	@Override
	public int insertUserCouponBatch(List<UserCoupon> userCouponList) {
		return sqlSession.insert("userCouponBase.insert_userCoupon_Batch",userCouponList);
	}
	
	/**
	 * 更新(用户所持消费券表)信息
	 * @param userCoupon
	 * @return
	 */
	@Override
	public int updateUserCoupon(UserCoupon userCoupon){
		return sqlSession.update("userCouponBase.update_userCoupon", userCoupon);
	}
	/**
	 * 批量更新(用户所持消费券表)信息
	 * @param userCouponList
	 * @return
	 */
	@Override
	public int updateUserCouponBatch(List<UserCoupon> userCouponList) {
		return sqlSession.update("userCouponBase.update_userCoupon_Batch", userCouponList);
	}
	
	/**
	 * 根据序列号删除(用户所持消费券表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteUserCouponLogic(java.math.BigInteger id){
		UserCoupon userCoupon = new UserCoupon();
		userCoupon.setId(id);
		userCoupon.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("userCouponBase.delete_userCoupon_Logic",userCoupon);
	}
	
	/**
	 * 根据Id 批量删除(用户所持消费券表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteUserCouponLogicBatch(List<java.math.BigInteger> idList) {
		List<UserCoupon> delList = new java.util.ArrayList<UserCoupon>();
		for(java.math.BigInteger id:idList){
			UserCoupon userCoupon = new UserCoupon();
			userCoupon.setId(id);
			userCoupon.setSys0DelState(RecordState_DELETED);
			delList.add(userCoupon);
		}
		return sqlSession.update("userCouponBase.delete_userCoupon_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(用户所持消费券表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteUserCoupon(java.math.BigInteger id){
//		return sqlSession.delete("userCouponBase.delete_userCoupon", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(用户所持消费券表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteUserCouponBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("userCouponBase.delete_userCoupon_Batch", idList);
//	}
	
	
}
