package com.cnfantasia.server.domainbase.ebuyDeliveryOrderProduct.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyDeliveryOrderProduct.entity.EbuyDeliveryOrderProduct;

/**
 * 描述:(供应商配送包含的商品信息) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EbuyDeliveryOrderProductBaseDao extends AbstractBaseDao implements IEbuyDeliveryOrderProductBaseDao{
	/**
	 * 根据条件查询(供应商配送包含的商品信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyDeliveryOrderProduct> selectEbuyDeliveryOrderProductByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("ebuyDeliveryOrderProductBase.select_ebuyDeliveryOrderProduct",paramMap);
	}
	/**
	 * 按条件分页查询(供应商配送包含的商品信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyDeliveryOrderProduct> selectEbuyDeliveryOrderProductByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEbuyDeliveryOrderProductCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EbuyDeliveryOrderProduct> resMap= sqlSession.selectList("ebuyDeliveryOrderProductBase.select_ebuyDeliveryOrderProduct_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(供应商配送包含的商品信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyDeliveryOrderProduct selectEbuyDeliveryOrderProductBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("ebuyDeliveryOrderProductBase.select_ebuyDeliveryOrderProduct_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(供应商配送包含的商品信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEbuyDeliveryOrderProductCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("ebuyDeliveryOrderProductBase.select_ebuyDeliveryOrderProduct_count", paramMap);
	}
	/**
	 * 往(供应商配送包含的商品信息)新增一条记录
	 * @param ebuyDeliveryOrderProduct
	 * @return
	 */
	@Override
	public int insertEbuyDeliveryOrderProduct(EbuyDeliveryOrderProduct ebuyDeliveryOrderProduct){
		return sqlSession.insert("ebuyDeliveryOrderProductBase.insert_ebuyDeliveryOrderProduct",ebuyDeliveryOrderProduct);
	}
	/**
	 * 批量新增(供应商配送包含的商品信息)信息
	 * @param ebuyDeliveryOrderProductList
	 * @return
	 */
	@Override
	public int insertEbuyDeliveryOrderProductBatch(List<EbuyDeliveryOrderProduct> ebuyDeliveryOrderProductList) {
		return sqlSession.insert("ebuyDeliveryOrderProductBase.insert_ebuyDeliveryOrderProduct_Batch",ebuyDeliveryOrderProductList);
	}
	
	/**
	 * 更新(供应商配送包含的商品信息)信息
	 * @param ebuyDeliveryOrderProduct
	 * @return
	 */
	@Override
	public int updateEbuyDeliveryOrderProduct(EbuyDeliveryOrderProduct ebuyDeliveryOrderProduct){
		return sqlSession.update("ebuyDeliveryOrderProductBase.update_ebuyDeliveryOrderProduct", ebuyDeliveryOrderProduct);
	}
	/**
	 * 批量更新(供应商配送包含的商品信息)信息
	 * @param ebuyDeliveryOrderProductList
	 * @return
	 */
	@Override
	public int updateEbuyDeliveryOrderProductBatch(List<EbuyDeliveryOrderProduct> ebuyDeliveryOrderProductList) {
		return sqlSession.update("ebuyDeliveryOrderProductBase.update_ebuyDeliveryOrderProduct_Batch", ebuyDeliveryOrderProductList);
	}
	
	/**
	 * 根据序列号删除(供应商配送包含的商品信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyDeliveryOrderProductLogic(java.math.BigInteger id){
		EbuyDeliveryOrderProduct ebuyDeliveryOrderProduct = new EbuyDeliveryOrderProduct();
		ebuyDeliveryOrderProduct.setId(id);
		ebuyDeliveryOrderProduct.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("ebuyDeliveryOrderProductBase.delete_ebuyDeliveryOrderProduct_Logic",ebuyDeliveryOrderProduct);
	}
	
	/**
	 * 根据Id 批量删除(供应商配送包含的商品信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyDeliveryOrderProductLogicBatch(List<java.math.BigInteger> idList) {
		List<EbuyDeliveryOrderProduct> delList = new java.util.ArrayList<EbuyDeliveryOrderProduct>();
		for(java.math.BigInteger id:idList){
			EbuyDeliveryOrderProduct ebuyDeliveryOrderProduct = new EbuyDeliveryOrderProduct();
			ebuyDeliveryOrderProduct.setId(id);
			ebuyDeliveryOrderProduct.setSys0DelState(RecordState_DELETED);
			delList.add(ebuyDeliveryOrderProduct);
		}
		return sqlSession.update("ebuyDeliveryOrderProductBase.delete_ebuyDeliveryOrderProduct_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(供应商配送包含的商品信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyDeliveryOrderProduct(java.math.BigInteger id){
//		return sqlSession.delete("ebuyDeliveryOrderProductBase.delete_ebuyDeliveryOrderProduct", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(供应商配送包含的商品信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyDeliveryOrderProductBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("ebuyDeliveryOrderProductBase.delete_ebuyDeliveryOrderProduct_Batch", idList);
//	}
	
	
}
