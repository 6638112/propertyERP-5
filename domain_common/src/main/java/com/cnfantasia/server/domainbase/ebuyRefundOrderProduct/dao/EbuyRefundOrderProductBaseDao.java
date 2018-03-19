package com.cnfantasia.server.domainbase.ebuyRefundOrderProduct.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyRefundOrderProduct.entity.EbuyRefundOrderProduct;

/**
 * 描述:(退货订单信息) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EbuyRefundOrderProductBaseDao extends AbstractBaseDao implements IEbuyRefundOrderProductBaseDao{
	/**
	 * 根据条件查询(退货订单信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyRefundOrderProduct> selectEbuyRefundOrderProductByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("ebuyRefundOrderProductBase.select_ebuyRefundOrderProduct",paramMap);
	}
	/**
	 * 按条件分页查询(退货订单信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyRefundOrderProduct> selectEbuyRefundOrderProductByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEbuyRefundOrderProductCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EbuyRefundOrderProduct> resMap= sqlSession.selectList("ebuyRefundOrderProductBase.select_ebuyRefundOrderProduct_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(退货订单信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyRefundOrderProduct selectEbuyRefundOrderProductBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("ebuyRefundOrderProductBase.select_ebuyRefundOrderProduct_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(退货订单信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEbuyRefundOrderProductCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("ebuyRefundOrderProductBase.select_ebuyRefundOrderProduct_count", paramMap);
	}
	/**
	 * 往(退货订单信息)新增一条记录
	 * @param ebuyRefundOrderProduct
	 * @return
	 */
	@Override
	public int insertEbuyRefundOrderProduct(EbuyRefundOrderProduct ebuyRefundOrderProduct){
		return sqlSession.insert("ebuyRefundOrderProductBase.insert_ebuyRefundOrderProduct",ebuyRefundOrderProduct);
	}
	/**
	 * 批量新增(退货订单信息)信息
	 * @param ebuyRefundOrderProductList
	 * @return
	 */
	@Override
	public int insertEbuyRefundOrderProductBatch(List<EbuyRefundOrderProduct> ebuyRefundOrderProductList) {
		return sqlSession.insert("ebuyRefundOrderProductBase.insert_ebuyRefundOrderProduct_Batch",ebuyRefundOrderProductList);
	}
	
	/**
	 * 更新(退货订单信息)信息
	 * @param ebuyRefundOrderProduct
	 * @return
	 */
	@Override
	public int updateEbuyRefundOrderProduct(EbuyRefundOrderProduct ebuyRefundOrderProduct){
		return sqlSession.update("ebuyRefundOrderProductBase.update_ebuyRefundOrderProduct", ebuyRefundOrderProduct);
	}
	/**
	 * 批量更新(退货订单信息)信息
	 * @param ebuyRefundOrderProductList
	 * @return
	 */
	@Override
	public int updateEbuyRefundOrderProductBatch(List<EbuyRefundOrderProduct> ebuyRefundOrderProductList) {
		return sqlSession.update("ebuyRefundOrderProductBase.update_ebuyRefundOrderProduct_Batch", ebuyRefundOrderProductList);
	}
	
	/**
	 * 根据序列号删除(退货订单信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyRefundOrderProductLogic(java.math.BigInteger id){
		EbuyRefundOrderProduct ebuyRefundOrderProduct = new EbuyRefundOrderProduct();
		ebuyRefundOrderProduct.setId(id);
		ebuyRefundOrderProduct.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("ebuyRefundOrderProductBase.delete_ebuyRefundOrderProduct_Logic",ebuyRefundOrderProduct);
	}
	
	/**
	 * 根据Id 批量删除(退货订单信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyRefundOrderProductLogicBatch(List<java.math.BigInteger> idList) {
		List<EbuyRefundOrderProduct> delList = new java.util.ArrayList<EbuyRefundOrderProduct>();
		for(java.math.BigInteger id:idList){
			EbuyRefundOrderProduct ebuyRefundOrderProduct = new EbuyRefundOrderProduct();
			ebuyRefundOrderProduct.setId(id);
			ebuyRefundOrderProduct.setSys0DelState(RecordState_DELETED);
			delList.add(ebuyRefundOrderProduct);
		}
		return sqlSession.update("ebuyRefundOrderProductBase.delete_ebuyRefundOrderProduct_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(退货订单信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyRefundOrderProduct(java.math.BigInteger id){
//		return sqlSession.delete("ebuyRefundOrderProductBase.delete_ebuyRefundOrderProduct", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(退货订单信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyRefundOrderProductBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("ebuyRefundOrderProductBase.delete_ebuyRefundOrderProduct_Batch", idList);
//	}
	
	
}
