package com.cnfantasia.server.domainbase.ebuyHomeTypeHasProduct.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyHomeTypeHasProduct.entity.EbuyHomeTypeHasProduct;

/**
 * 描述:(首页分类名称关联产品分类) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EbuyHomeTypeHasProductBaseDao extends AbstractBaseDao implements IEbuyHomeTypeHasProductBaseDao{
	/**
	 * 根据条件查询(首页分类名称关联产品分类)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyHomeTypeHasProduct> selectEbuyHomeTypeHasProductByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("ebuyHomeTypeHasProductBase.select_ebuyHomeTypeHasProduct",paramMap);
	}
	/**
	 * 按条件分页查询(首页分类名称关联产品分类)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyHomeTypeHasProduct> selectEbuyHomeTypeHasProductByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEbuyHomeTypeHasProductCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EbuyHomeTypeHasProduct> resMap= sqlSession.selectList("ebuyHomeTypeHasProductBase.select_ebuyHomeTypeHasProduct_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(首页分类名称关联产品分类)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyHomeTypeHasProduct selectEbuyHomeTypeHasProductBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("ebuyHomeTypeHasProductBase.select_ebuyHomeTypeHasProduct_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(首页分类名称关联产品分类)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEbuyHomeTypeHasProductCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("ebuyHomeTypeHasProductBase.select_ebuyHomeTypeHasProduct_count", paramMap);
	}
	/**
	 * 往(首页分类名称关联产品分类)新增一条记录
	 * @param ebuyHomeTypeHasProduct
	 * @return
	 */
	@Override
	public int insertEbuyHomeTypeHasProduct(EbuyHomeTypeHasProduct ebuyHomeTypeHasProduct){
		return sqlSession.insert("ebuyHomeTypeHasProductBase.insert_ebuyHomeTypeHasProduct",ebuyHomeTypeHasProduct);
	}
	/**
	 * 批量新增(首页分类名称关联产品分类)信息
	 * @param ebuyHomeTypeHasProductList
	 * @return
	 */
	@Override
	public int insertEbuyHomeTypeHasProductBatch(List<EbuyHomeTypeHasProduct> ebuyHomeTypeHasProductList) {
		return sqlSession.insert("ebuyHomeTypeHasProductBase.insert_ebuyHomeTypeHasProduct_Batch",ebuyHomeTypeHasProductList);
	}
	
	/**
	 * 更新(首页分类名称关联产品分类)信息
	 * @param ebuyHomeTypeHasProduct
	 * @return
	 */
	@Override
	public int updateEbuyHomeTypeHasProduct(EbuyHomeTypeHasProduct ebuyHomeTypeHasProduct){
		return sqlSession.update("ebuyHomeTypeHasProductBase.update_ebuyHomeTypeHasProduct", ebuyHomeTypeHasProduct);
	}
	/**
	 * 批量更新(首页分类名称关联产品分类)信息
	 * @param ebuyHomeTypeHasProductList
	 * @return
	 */
	@Override
	public int updateEbuyHomeTypeHasProductBatch(List<EbuyHomeTypeHasProduct> ebuyHomeTypeHasProductList) {
		return sqlSession.update("ebuyHomeTypeHasProductBase.update_ebuyHomeTypeHasProduct_Batch", ebuyHomeTypeHasProductList);
	}
	
	/**
	 * 根据序列号删除(首页分类名称关联产品分类)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyHomeTypeHasProductLogic(java.math.BigInteger id){
		EbuyHomeTypeHasProduct ebuyHomeTypeHasProduct = new EbuyHomeTypeHasProduct();
		ebuyHomeTypeHasProduct.setId(id);
		ebuyHomeTypeHasProduct.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("ebuyHomeTypeHasProductBase.delete_ebuyHomeTypeHasProduct_Logic",ebuyHomeTypeHasProduct);
	}
	
	/**
	 * 根据Id 批量删除(首页分类名称关联产品分类)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyHomeTypeHasProductLogicBatch(List<java.math.BigInteger> idList) {
		List<EbuyHomeTypeHasProduct> delList = new java.util.ArrayList<EbuyHomeTypeHasProduct>();
		for(java.math.BigInteger id:idList){
			EbuyHomeTypeHasProduct ebuyHomeTypeHasProduct = new EbuyHomeTypeHasProduct();
			ebuyHomeTypeHasProduct.setId(id);
			ebuyHomeTypeHasProduct.setSys0DelState(RecordState_DELETED);
			delList.add(ebuyHomeTypeHasProduct);
		}
		return sqlSession.update("ebuyHomeTypeHasProductBase.delete_ebuyHomeTypeHasProduct_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(首页分类名称关联产品分类)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyHomeTypeHasProduct(java.math.BigInteger id){
//		return sqlSession.delete("ebuyHomeTypeHasProductBase.delete_ebuyHomeTypeHasProduct", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(首页分类名称关联产品分类)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyHomeTypeHasProductBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("ebuyHomeTypeHasProductBase.delete_ebuyHomeTypeHasProduct_Batch", idList);
//	}
	
	
}
