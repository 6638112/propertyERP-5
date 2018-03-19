package com.cnfantasia.server.domainbase.dredgeProduct.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeProduct.entity.DredgeProduct;

/**
 * 描述:(维修服务商品表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class DredgeProductBaseDao extends AbstractBaseDao implements IDredgeProductBaseDao{
	/**
	 * 根据条件查询(维修服务商品表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<DredgeProduct> selectDredgeProductByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("dredgeProductBase.select_dredgeProduct",paramMap);
	}
	/**
	 * 按条件分页查询(维修服务商品表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<DredgeProduct> selectDredgeProductByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectDredgeProductCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<DredgeProduct> resMap= sqlSession.selectList("dredgeProductBase.select_dredgeProduct_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(维修服务商品表)信息
	 * @param id
	 * @return
	 */
	@Override
	public DredgeProduct selectDredgeProductBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("dredgeProductBase.select_dredgeProduct_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(维修服务商品表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectDredgeProductCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("dredgeProductBase.select_dredgeProduct_count", paramMap);
	}
	/**
	 * 往(维修服务商品表)新增一条记录
	 * @param dredgeProduct
	 * @return
	 */
	@Override
	public int insertDredgeProduct(DredgeProduct dredgeProduct){
		return sqlSession.insert("dredgeProductBase.insert_dredgeProduct",dredgeProduct);
	}
	/**
	 * 批量新增(维修服务商品表)信息
	 * @param dredgeProductList
	 * @return
	 */
	@Override
	public int insertDredgeProductBatch(List<DredgeProduct> dredgeProductList) {
		if (dredgeProductList == null || dredgeProductList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = dredgeProductList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<DredgeProduct> batchList = dredgeProductList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("dredgeProductBase.insert_dredgeProduct_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(维修服务商品表)信息
	 * @param dredgeProduct
	 * @return
	 */
	@Override
	public int updateDredgeProduct(DredgeProduct dredgeProduct){
		return sqlSession.update("dredgeProductBase.update_dredgeProduct", dredgeProduct);
	}
	/**
	 * 批量更新(维修服务商品表)信息
	 * @param dredgeProductList
	 * @return
	 */
	@Override
	public int updateDredgeProductBatch(List<DredgeProduct> dredgeProductList) {
		if (dredgeProductList == null || dredgeProductList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("dredgeProductBase.update_dredgeProduct_Batch", dredgeProductList);
	}
	
	/**
	 * 根据序列号删除(维修服务商品表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteDredgeProductLogic(java.math.BigInteger id){
		DredgeProduct dredgeProduct = new DredgeProduct();
		dredgeProduct.setId(id);
		dredgeProduct.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("dredgeProductBase.delete_dredgeProduct_Logic",dredgeProduct);
	}
	
	/**
	 * 根据Id 批量删除(维修服务商品表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteDredgeProductLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<DredgeProduct> delList = new java.util.ArrayList<DredgeProduct>();
		for(java.math.BigInteger id:idList){
			DredgeProduct dredgeProduct = new DredgeProduct();
			dredgeProduct.setId(id);
			dredgeProduct.setSys0DelState(RecordState_DELETED);
			delList.add(dredgeProduct);
		}
		return sqlSession.update("dredgeProductBase.delete_dredgeProduct_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(维修服务商品表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeProduct(java.math.BigInteger id){
//		return sqlSession.delete("dredgeProductBase.delete_dredgeProduct", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(维修服务商品表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeProductBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("dredgeProductBase.delete_dredgeProduct_Batch", idList);
//	}
	
	
}
