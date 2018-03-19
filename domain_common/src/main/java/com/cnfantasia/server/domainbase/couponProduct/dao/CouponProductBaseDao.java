package com.cnfantasia.server.domainbase.couponProduct.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.couponProduct.entity.CouponProduct;

/**
 * 描述:(消费券可抵扣商品) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CouponProductBaseDao extends AbstractBaseDao implements ICouponProductBaseDao{
	/**
	 * 根据条件查询(消费券可抵扣商品)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CouponProduct> selectCouponProductByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("couponProductBase.select_couponProduct",paramMap);
	}
	/**
	 * 按条件分页查询(消费券可抵扣商品)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CouponProduct> selectCouponProductByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCouponProductCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<CouponProduct> resMap= sqlSession.selectList("couponProductBase.select_couponProduct_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(消费券可抵扣商品)信息
	 * @param id
	 * @return
	 */
	@Override
	public CouponProduct selectCouponProductBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("couponProductBase.select_couponProduct_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(消费券可抵扣商品)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCouponProductCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("couponProductBase.select_couponProduct_count", paramMap);
	}
	/**
	 * 往(消费券可抵扣商品)新增一条记录
	 * @param couponProduct
	 * @return
	 */
	@Override
	public int insertCouponProduct(CouponProduct couponProduct){
		return sqlSession.insert("couponProductBase.insert_couponProduct",couponProduct);
	}
	/**
	 * 批量新增(消费券可抵扣商品)信息
	 * @param couponProductList
	 * @return
	 */
	@Override
	public int insertCouponProductBatch(List<CouponProduct> couponProductList) {
		if (couponProductList == null || couponProductList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = couponProductList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<CouponProduct> batchList = couponProductList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("couponProductBase.insert_couponProduct_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(消费券可抵扣商品)信息
	 * @param couponProduct
	 * @return
	 */
	@Override
	public int updateCouponProduct(CouponProduct couponProduct){
		return sqlSession.update("couponProductBase.update_couponProduct", couponProduct);
	}
	/**
	 * 批量更新(消费券可抵扣商品)信息
	 * @param couponProductList
	 * @return
	 */
	@Override
	public int updateCouponProductBatch(List<CouponProduct> couponProductList) {
		if (couponProductList == null || couponProductList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("couponProductBase.update_couponProduct_Batch", couponProductList);
	}
	
	/**
	 * 根据序列号删除(消费券可抵扣商品)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCouponProductLogic(java.math.BigInteger id){
		CouponProduct couponProduct = new CouponProduct();
		couponProduct.setId(id);
		couponProduct.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("couponProductBase.delete_couponProduct_Logic",couponProduct);
	}
	
	/**
	 * 根据Id 批量删除(消费券可抵扣商品)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCouponProductLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<CouponProduct> delList = new java.util.ArrayList<CouponProduct>();
		for(java.math.BigInteger id:idList){
			CouponProduct couponProduct = new CouponProduct();
			couponProduct.setId(id);
			couponProduct.setSys0DelState(RecordState_DELETED);
			delList.add(couponProduct);
		}
		return sqlSession.update("couponProductBase.delete_couponProduct_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(消费券可抵扣商品)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCouponProduct(java.math.BigInteger id){
//		return sqlSession.delete("couponProductBase.delete_couponProduct", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(消费券可抵扣商品)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCouponProductBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("couponProductBase.delete_couponProduct_Batch", idList);
//	}
	
	
}
