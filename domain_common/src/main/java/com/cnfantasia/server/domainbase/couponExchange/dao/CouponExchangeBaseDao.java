package com.cnfantasia.server.domainbase.couponExchange.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.couponExchange.entity.CouponExchange;

/**
 * 描述:(消费券兑换表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CouponExchangeBaseDao extends AbstractBaseDao implements ICouponExchangeBaseDao{
	/**
	 * 根据条件查询(消费券兑换表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CouponExchange> selectCouponExchangeByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("couponExchangeBase.select_couponExchange",paramMap);
	}
	/**
	 * 按条件分页查询(消费券兑换表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CouponExchange> selectCouponExchangeByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCouponExchangeCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<CouponExchange> resMap= sqlSession.selectList("couponExchangeBase.select_couponExchange_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(消费券兑换表)信息
	 * @param id
	 * @return
	 */
	@Override
	public CouponExchange selectCouponExchangeBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("couponExchangeBase.select_couponExchange_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(消费券兑换表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCouponExchangeCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("couponExchangeBase.select_couponExchange_count", paramMap);
	}
	/**
	 * 往(消费券兑换表)新增一条记录
	 * @param couponExchange
	 * @return
	 */
	@Override
	public int insertCouponExchange(CouponExchange couponExchange){
		return sqlSession.insert("couponExchangeBase.insert_couponExchange",couponExchange);
	}
	/**
	 * 批量新增(消费券兑换表)信息
	 * @param couponExchangeList
	 * @return
	 */
	@Override
	public int insertCouponExchangeBatch(List<CouponExchange> couponExchangeList) {
		if (couponExchangeList == null || couponExchangeList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = couponExchangeList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<CouponExchange> batchList = couponExchangeList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("couponExchangeBase.insert_couponExchange_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(消费券兑换表)信息
	 * @param couponExchange
	 * @return
	 */
	@Override
	public int updateCouponExchange(CouponExchange couponExchange){
		return sqlSession.update("couponExchangeBase.update_couponExchange", couponExchange);
	}
	/**
	 * 批量更新(消费券兑换表)信息
	 * @param couponExchangeList
	 * @return
	 */
	@Override
	public int updateCouponExchangeBatch(List<CouponExchange> couponExchangeList) {
		if (couponExchangeList == null || couponExchangeList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("couponExchangeBase.update_couponExchange_Batch", couponExchangeList);
	}
	
	/**
	 * 根据序列号删除(消费券兑换表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCouponExchangeLogic(java.math.BigInteger id){
		CouponExchange couponExchange = new CouponExchange();
		couponExchange.setId(id);
		couponExchange.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("couponExchangeBase.delete_couponExchange_Logic",couponExchange);
	}
	
	/**
	 * 根据Id 批量删除(消费券兑换表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCouponExchangeLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<CouponExchange> delList = new java.util.ArrayList<CouponExchange>();
		for(java.math.BigInteger id:idList){
			CouponExchange couponExchange = new CouponExchange();
			couponExchange.setId(id);
			couponExchange.setSys0DelState(RecordState_DELETED);
			delList.add(couponExchange);
		}
		return sqlSession.update("couponExchangeBase.delete_couponExchange_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(消费券兑换表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCouponExchange(java.math.BigInteger id){
//		return sqlSession.delete("couponExchangeBase.delete_couponExchange", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(消费券兑换表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCouponExchangeBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("couponExchangeBase.delete_couponExchange_Batch", idList);
//	}
	
	
}
