package com.cnfantasia.server.domainbase.ebuyOrderHasCoupon.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyOrderHasCoupon.entity.EbuyOrderHasCoupon;

/**
 * 描述:(订单使用消费券表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EbuyOrderHasCouponBaseDao extends AbstractBaseDao implements IEbuyOrderHasCouponBaseDao{
	/**
	 * 根据条件查询(订单使用消费券表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyOrderHasCoupon> selectEbuyOrderHasCouponByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("ebuyOrderHasCouponBase.select_ebuyOrderHasCoupon",paramMap);
	}
	/**
	 * 按条件分页查询(订单使用消费券表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyOrderHasCoupon> selectEbuyOrderHasCouponByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEbuyOrderHasCouponCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EbuyOrderHasCoupon> resMap= sqlSession.selectList("ebuyOrderHasCouponBase.select_ebuyOrderHasCoupon_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(订单使用消费券表)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyOrderHasCoupon selectEbuyOrderHasCouponBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("ebuyOrderHasCouponBase.select_ebuyOrderHasCoupon_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(订单使用消费券表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEbuyOrderHasCouponCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("ebuyOrderHasCouponBase.select_ebuyOrderHasCoupon_count", paramMap);
	}
	/**
	 * 往(订单使用消费券表)新增一条记录
	 * @param ebuyOrderHasCoupon
	 * @return
	 */
	@Override
	public int insertEbuyOrderHasCoupon(EbuyOrderHasCoupon ebuyOrderHasCoupon){
		return sqlSession.insert("ebuyOrderHasCouponBase.insert_ebuyOrderHasCoupon",ebuyOrderHasCoupon);
	}
	/**
	 * 批量新增(订单使用消费券表)信息
	 * @param ebuyOrderHasCouponList
	 * @return
	 */
	@Override
	public int insertEbuyOrderHasCouponBatch(List<EbuyOrderHasCoupon> ebuyOrderHasCouponList) {
		return sqlSession.insert("ebuyOrderHasCouponBase.insert_ebuyOrderHasCoupon_Batch",ebuyOrderHasCouponList);
	}
	
	/**
	 * 更新(订单使用消费券表)信息
	 * @param ebuyOrderHasCoupon
	 * @return
	 */
	@Override
	public int updateEbuyOrderHasCoupon(EbuyOrderHasCoupon ebuyOrderHasCoupon){
		return sqlSession.update("ebuyOrderHasCouponBase.update_ebuyOrderHasCoupon", ebuyOrderHasCoupon);
	}
	/**
	 * 批量更新(订单使用消费券表)信息
	 * @param ebuyOrderHasCouponList
	 * @return
	 */
	@Override
	public int updateEbuyOrderHasCouponBatch(List<EbuyOrderHasCoupon> ebuyOrderHasCouponList) {
		return sqlSession.update("ebuyOrderHasCouponBase.update_ebuyOrderHasCoupon_Batch", ebuyOrderHasCouponList);
	}
	
	/**
	 * 根据序列号删除(订单使用消费券表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyOrderHasCouponLogic(java.math.BigInteger id){
		EbuyOrderHasCoupon ebuyOrderHasCoupon = new EbuyOrderHasCoupon();
		ebuyOrderHasCoupon.setId(id);
		ebuyOrderHasCoupon.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("ebuyOrderHasCouponBase.delete_ebuyOrderHasCoupon_Logic",ebuyOrderHasCoupon);
	}
	
	/**
	 * 根据Id 批量删除(订单使用消费券表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyOrderHasCouponLogicBatch(List<java.math.BigInteger> idList) {
		List<EbuyOrderHasCoupon> delList = new java.util.ArrayList<EbuyOrderHasCoupon>();
		for(java.math.BigInteger id:idList){
			EbuyOrderHasCoupon ebuyOrderHasCoupon = new EbuyOrderHasCoupon();
			ebuyOrderHasCoupon.setId(id);
			ebuyOrderHasCoupon.setSys0DelState(RecordState_DELETED);
			delList.add(ebuyOrderHasCoupon);
		}
		return sqlSession.update("ebuyOrderHasCouponBase.delete_ebuyOrderHasCoupon_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(订单使用消费券表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyOrderHasCoupon(java.math.BigInteger id){
//		return sqlSession.delete("ebuyOrderHasCouponBase.delete_ebuyOrderHasCoupon", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(订单使用消费券表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyOrderHasCouponBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("ebuyOrderHasCouponBase.delete_ebuyOrderHasCoupon_Batch", idList);
//	}
	
	
}
