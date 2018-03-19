package com.cnfantasia.server.domainbase.ebuyAdvertiseHasPromoteShelfProduct.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyAdvertiseHasPromoteShelfProduct.entity.EbuyAdvertiseHasPromoteShelfProduct;

/**
 * 描述:(商品推广广告对应商品表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EbuyAdvertiseHasPromoteShelfProductBaseDao extends AbstractBaseDao implements IEbuyAdvertiseHasPromoteShelfProductBaseDao{
	/**
	 * 根据条件查询(商品推广广告对应商品表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyAdvertiseHasPromoteShelfProduct> selectEbuyAdvertiseHasPromoteShelfProductByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("ebuyAdvertiseHasPromoteShelfProductBase.select_ebuyAdvertiseHasPromoteShelfProduct",paramMap);
	}
	/**
	 * 按条件分页查询(商品推广广告对应商品表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyAdvertiseHasPromoteShelfProduct> selectEbuyAdvertiseHasPromoteShelfProductByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEbuyAdvertiseHasPromoteShelfProductCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EbuyAdvertiseHasPromoteShelfProduct> resMap= sqlSession.selectList("ebuyAdvertiseHasPromoteShelfProductBase.select_ebuyAdvertiseHasPromoteShelfProduct_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(商品推广广告对应商品表)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyAdvertiseHasPromoteShelfProduct selectEbuyAdvertiseHasPromoteShelfProductBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("ebuyAdvertiseHasPromoteShelfProductBase.select_ebuyAdvertiseHasPromoteShelfProduct_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(商品推广广告对应商品表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEbuyAdvertiseHasPromoteShelfProductCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("ebuyAdvertiseHasPromoteShelfProductBase.select_ebuyAdvertiseHasPromoteShelfProduct_count", paramMap);
	}
	/**
	 * 往(商品推广广告对应商品表)新增一条记录
	 * @param ebuyAdvertiseHasPromoteShelfProduct
	 * @return
	 */
	@Override
	public int insertEbuyAdvertiseHasPromoteShelfProduct(EbuyAdvertiseHasPromoteShelfProduct ebuyAdvertiseHasPromoteShelfProduct){
		return sqlSession.insert("ebuyAdvertiseHasPromoteShelfProductBase.insert_ebuyAdvertiseHasPromoteShelfProduct",ebuyAdvertiseHasPromoteShelfProduct);
	}
	/**
	 * 批量新增(商品推广广告对应商品表)信息
	 * @param ebuyAdvertiseHasPromoteShelfProductList
	 * @return
	 */
	@Override
	public int insertEbuyAdvertiseHasPromoteShelfProductBatch(List<EbuyAdvertiseHasPromoteShelfProduct> ebuyAdvertiseHasPromoteShelfProductList) {
		return sqlSession.insert("ebuyAdvertiseHasPromoteShelfProductBase.insert_ebuyAdvertiseHasPromoteShelfProduct_Batch",ebuyAdvertiseHasPromoteShelfProductList);
	}
	
	/**
	 * 更新(商品推广广告对应商品表)信息
	 * @param ebuyAdvertiseHasPromoteShelfProduct
	 * @return
	 */
	@Override
	public int updateEbuyAdvertiseHasPromoteShelfProduct(EbuyAdvertiseHasPromoteShelfProduct ebuyAdvertiseHasPromoteShelfProduct){
		return sqlSession.update("ebuyAdvertiseHasPromoteShelfProductBase.update_ebuyAdvertiseHasPromoteShelfProduct", ebuyAdvertiseHasPromoteShelfProduct);
	}
	/**
	 * 批量更新(商品推广广告对应商品表)信息
	 * @param ebuyAdvertiseHasPromoteShelfProductList
	 * @return
	 */
	@Override
	public int updateEbuyAdvertiseHasPromoteShelfProductBatch(List<EbuyAdvertiseHasPromoteShelfProduct> ebuyAdvertiseHasPromoteShelfProductList) {
		return sqlSession.update("ebuyAdvertiseHasPromoteShelfProductBase.update_ebuyAdvertiseHasPromoteShelfProduct_Batch", ebuyAdvertiseHasPromoteShelfProductList);
	}
	
	/**
	 * 根据序列号删除(商品推广广告对应商品表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyAdvertiseHasPromoteShelfProductLogic(java.math.BigInteger id){
		EbuyAdvertiseHasPromoteShelfProduct ebuyAdvertiseHasPromoteShelfProduct = new EbuyAdvertiseHasPromoteShelfProduct();
		ebuyAdvertiseHasPromoteShelfProduct.setId(id);
		ebuyAdvertiseHasPromoteShelfProduct.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("ebuyAdvertiseHasPromoteShelfProductBase.delete_ebuyAdvertiseHasPromoteShelfProduct_Logic",ebuyAdvertiseHasPromoteShelfProduct);
	}
	
	/**
	 * 根据Id 批量删除(商品推广广告对应商品表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyAdvertiseHasPromoteShelfProductLogicBatch(List<java.math.BigInteger> idList) {
		List<EbuyAdvertiseHasPromoteShelfProduct> delList = new java.util.ArrayList<EbuyAdvertiseHasPromoteShelfProduct>();
		for(java.math.BigInteger id:idList){
			EbuyAdvertiseHasPromoteShelfProduct ebuyAdvertiseHasPromoteShelfProduct = new EbuyAdvertiseHasPromoteShelfProduct();
			ebuyAdvertiseHasPromoteShelfProduct.setId(id);
			ebuyAdvertiseHasPromoteShelfProduct.setSys0DelState(RecordState_DELETED);
			delList.add(ebuyAdvertiseHasPromoteShelfProduct);
		}
		return sqlSession.update("ebuyAdvertiseHasPromoteShelfProductBase.delete_ebuyAdvertiseHasPromoteShelfProduct_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(商品推广广告对应商品表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyAdvertiseHasPromoteShelfProduct(java.math.BigInteger id){
//		return sqlSession.delete("ebuyAdvertiseHasPromoteShelfProductBase.delete_ebuyAdvertiseHasPromoteShelfProduct", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(商品推广广告对应商品表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyAdvertiseHasPromoteShelfProductBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("ebuyAdvertiseHasPromoteShelfProductBase.delete_ebuyAdvertiseHasPromoteShelfProduct_Batch", idList);
//	}
	
	
}
