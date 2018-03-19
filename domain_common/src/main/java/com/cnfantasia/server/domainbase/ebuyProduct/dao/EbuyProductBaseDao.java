package com.cnfantasia.server.domainbase.ebuyProduct.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyProduct.entity.EbuyProduct;

/**
 * 描述:(商品表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EbuyProductBaseDao extends AbstractBaseDao implements IEbuyProductBaseDao{
	/**
	 * 根据条件查询(商品表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyProduct> selectEbuyProductByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("ebuyProductBase.select_ebuyProduct",paramMap);
	}
	/**
	 * 按条件分页查询(商品表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyProduct> selectEbuyProductByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEbuyProductCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EbuyProduct> resMap= sqlSession.selectList("ebuyProductBase.select_ebuyProduct_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(商品表)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyProduct selectEbuyProductBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("ebuyProductBase.select_ebuyProduct_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(商品表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEbuyProductCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("ebuyProductBase.select_ebuyProduct_count", paramMap);
	}
	/**
	 * 往(商品表)新增一条记录
	 * @param ebuyProduct
	 * @return
	 */
	@Override
	public int insertEbuyProduct(EbuyProduct ebuyProduct){
		return sqlSession.insert("ebuyProductBase.insert_ebuyProduct",ebuyProduct);
	}
	/**
	 * 批量新增(商品表)信息
	 * @param ebuyProductList
	 * @return
	 */
	@Override
	public int insertEbuyProductBatch(List<EbuyProduct> ebuyProductList) {
		if (ebuyProductList == null || ebuyProductList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = ebuyProductList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<EbuyProduct> batchList = ebuyProductList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("ebuyProductBase.insert_ebuyProduct_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(商品表)信息
	 * @param ebuyProduct
	 * @return
	 */
	@Override
	public int updateEbuyProduct(EbuyProduct ebuyProduct){
		return sqlSession.update("ebuyProductBase.update_ebuyProduct", ebuyProduct);
	}
	/**
	 * 批量更新(商品表)信息
	 * @param ebuyProductList
	 * @return
	 */
	@Override
	public int updateEbuyProductBatch(List<EbuyProduct> ebuyProductList) {
		if (ebuyProductList == null || ebuyProductList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("ebuyProductBase.update_ebuyProduct_Batch", ebuyProductList);
	}
	
	/**
	 * 根据序列号删除(商品表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyProductLogic(java.math.BigInteger id){
		EbuyProduct ebuyProduct = new EbuyProduct();
		ebuyProduct.setId(id);
		ebuyProduct.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("ebuyProductBase.delete_ebuyProduct_Logic",ebuyProduct);
	}
	
	/**
	 * 根据Id 批量删除(商品表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyProductLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<EbuyProduct> delList = new java.util.ArrayList<EbuyProduct>();
		for(java.math.BigInteger id:idList){
			EbuyProduct ebuyProduct = new EbuyProduct();
			ebuyProduct.setId(id);
			ebuyProduct.setSys0DelState(RecordState_DELETED);
			delList.add(ebuyProduct);
		}
		return sqlSession.update("ebuyProductBase.delete_ebuyProduct_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(商品表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProduct(java.math.BigInteger id){
//		return sqlSession.delete("ebuyProductBase.delete_ebuyProduct", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(商品表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("ebuyProductBase.delete_ebuyProduct_Batch", idList);
//	}
	
	
}
