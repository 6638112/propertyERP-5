package com.cnfantasia.server.domainbase.loanProduct.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.loanProduct.entity.LoanProduct;

/**
 * 描述:(借贷产品表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class LoanProductBaseDao extends AbstractBaseDao implements ILoanProductBaseDao{
	/**
	 * 根据条件查询(借贷产品表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<LoanProduct> selectLoanProductByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("loanProductBase.select_loanProduct",paramMap);
	}
	/**
	 * 按条件分页查询(借贷产品表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<LoanProduct> selectLoanProductByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectLoanProductCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<LoanProduct> resMap= sqlSession.selectList("loanProductBase.select_loanProduct_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(借贷产品表)信息
	 * @param id
	 * @return
	 */
	@Override
	public LoanProduct selectLoanProductBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("loanProductBase.select_loanProduct_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(借贷产品表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectLoanProductCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("loanProductBase.select_loanProduct_count", paramMap);
	}
	/**
	 * 往(借贷产品表)新增一条记录
	 * @param loanProduct
	 * @return
	 */
	@Override
	public int insertLoanProduct(LoanProduct loanProduct){
		return sqlSession.insert("loanProductBase.insert_loanProduct",loanProduct);
	}
	/**
	 * 批量新增(借贷产品表)信息
	 * @param loanProductList
	 * @return
	 */
	@Override
	public int insertLoanProductBatch(List<LoanProduct> loanProductList) {
		if (loanProductList == null || loanProductList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = loanProductList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<LoanProduct> batchList = loanProductList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("loanProductBase.insert_loanProduct_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(借贷产品表)信息
	 * @param loanProduct
	 * @return
	 */
	@Override
	public int updateLoanProduct(LoanProduct loanProduct){
		return sqlSession.update("loanProductBase.update_loanProduct", loanProduct);
	}
	/**
	 * 批量更新(借贷产品表)信息
	 * @param loanProductList
	 * @return
	 */
	@Override
	public int updateLoanProductBatch(List<LoanProduct> loanProductList) {
		if (loanProductList == null || loanProductList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("loanProductBase.update_loanProduct_Batch", loanProductList);
	}
	
	/**
	 * 根据序列号删除(借贷产品表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteLoanProductLogic(java.math.BigInteger id){
		LoanProduct loanProduct = new LoanProduct();
		loanProduct.setId(id);
		loanProduct.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("loanProductBase.delete_loanProduct_Logic",loanProduct);
	}
	
	/**
	 * 根据Id 批量删除(借贷产品表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteLoanProductLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<LoanProduct> delList = new java.util.ArrayList<LoanProduct>();
		for(java.math.BigInteger id:idList){
			LoanProduct loanProduct = new LoanProduct();
			loanProduct.setId(id);
			loanProduct.setSys0DelState(RecordState_DELETED);
			delList.add(loanProduct);
		}
		return sqlSession.update("loanProductBase.delete_loanProduct_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(借贷产品表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteLoanProduct(java.math.BigInteger id){
//		return sqlSession.delete("loanProductBase.delete_loanProduct", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(借贷产品表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteLoanProductBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("loanProductBase.delete_loanProduct_Batch", idList);
//	}
	
	
}
