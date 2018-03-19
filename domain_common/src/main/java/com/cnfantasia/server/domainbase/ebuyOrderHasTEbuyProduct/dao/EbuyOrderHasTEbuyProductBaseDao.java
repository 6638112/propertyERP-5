package com.cnfantasia.server.domainbase.ebuyOrderHasTEbuyProduct.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyOrderHasTEbuyProduct.entity.EbuyOrderHasTEbuyProduct;

/**
 * 描述:(订单商品关系) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EbuyOrderHasTEbuyProductBaseDao extends AbstractBaseDao implements IEbuyOrderHasTEbuyProductBaseDao{
	/**
	 * 根据条件查询(订单商品关系)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyOrderHasTEbuyProduct> selectEbuyOrderHasTEbuyProductByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("ebuyOrderHasTEbuyProductBase.select_ebuyOrderHasTEbuyProduct",paramMap);
	}
	/**
	 * 按条件分页查询(订单商品关系)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyOrderHasTEbuyProduct> selectEbuyOrderHasTEbuyProductByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEbuyOrderHasTEbuyProductCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EbuyOrderHasTEbuyProduct> resMap= sqlSession.selectList("ebuyOrderHasTEbuyProductBase.select_ebuyOrderHasTEbuyProduct_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(订单商品关系)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyOrderHasTEbuyProduct selectEbuyOrderHasTEbuyProductBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("ebuyOrderHasTEbuyProductBase.select_ebuyOrderHasTEbuyProduct_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(订单商品关系)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEbuyOrderHasTEbuyProductCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("ebuyOrderHasTEbuyProductBase.select_ebuyOrderHasTEbuyProduct_count", paramMap);
	}
	/**
	 * 往(订单商品关系)新增一条记录
	 * @param ebuyOrderHasTEbuyProduct
	 * @return
	 */
	@Override
	public int insertEbuyOrderHasTEbuyProduct(EbuyOrderHasTEbuyProduct ebuyOrderHasTEbuyProduct){
		return sqlSession.insert("ebuyOrderHasTEbuyProductBase.insert_ebuyOrderHasTEbuyProduct",ebuyOrderHasTEbuyProduct);
	}
	/**
	 * 批量新增(订单商品关系)信息
	 * @param ebuyOrderHasTEbuyProductList
	 * @return
	 */
	@Override
	public int insertEbuyOrderHasTEbuyProductBatch(List<EbuyOrderHasTEbuyProduct> ebuyOrderHasTEbuyProductList) {
		return sqlSession.insert("ebuyOrderHasTEbuyProductBase.insert_ebuyOrderHasTEbuyProduct_Batch",ebuyOrderHasTEbuyProductList);
	}
	
	/**
	 * 更新(订单商品关系)信息
	 * @param ebuyOrderHasTEbuyProduct
	 * @return
	 */
	@Override
	public int updateEbuyOrderHasTEbuyProduct(EbuyOrderHasTEbuyProduct ebuyOrderHasTEbuyProduct){
		return sqlSession.update("ebuyOrderHasTEbuyProductBase.update_ebuyOrderHasTEbuyProduct", ebuyOrderHasTEbuyProduct);
	}
	/**
	 * 批量更新(订单商品关系)信息
	 * @param ebuyOrderHasTEbuyProductList
	 * @return
	 */
	@Override
	public int updateEbuyOrderHasTEbuyProductBatch(List<EbuyOrderHasTEbuyProduct> ebuyOrderHasTEbuyProductList) {
		return sqlSession.update("ebuyOrderHasTEbuyProductBase.update_ebuyOrderHasTEbuyProduct_Batch", ebuyOrderHasTEbuyProductList);
	}
	
	/**
	 * 根据序列号删除(订单商品关系)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyOrderHasTEbuyProductLogic(java.math.BigInteger id){
		EbuyOrderHasTEbuyProduct ebuyOrderHasTEbuyProduct = new EbuyOrderHasTEbuyProduct();
		ebuyOrderHasTEbuyProduct.setId(id);
		ebuyOrderHasTEbuyProduct.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("ebuyOrderHasTEbuyProductBase.delete_ebuyOrderHasTEbuyProduct_Logic",ebuyOrderHasTEbuyProduct);
	}
	
	/**
	 * 根据Id 批量删除(订单商品关系)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyOrderHasTEbuyProductLogicBatch(List<java.math.BigInteger> idList) {
		List<EbuyOrderHasTEbuyProduct> delList = new java.util.ArrayList<EbuyOrderHasTEbuyProduct>();
		for(java.math.BigInteger id:idList){
			EbuyOrderHasTEbuyProduct ebuyOrderHasTEbuyProduct = new EbuyOrderHasTEbuyProduct();
			ebuyOrderHasTEbuyProduct.setId(id);
			ebuyOrderHasTEbuyProduct.setSys0DelState(RecordState_DELETED);
			delList.add(ebuyOrderHasTEbuyProduct);
		}
		return sqlSession.update("ebuyOrderHasTEbuyProductBase.delete_ebuyOrderHasTEbuyProduct_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(订单商品关系)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyOrderHasTEbuyProduct(java.math.BigInteger id){
//		return sqlSession.delete("ebuyOrderHasTEbuyProductBase.delete_ebuyOrderHasTEbuyProduct", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(订单商品关系)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyOrderHasTEbuyProductBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("ebuyOrderHasTEbuyProductBase.delete_ebuyOrderHasTEbuyProduct_Batch", idList);
//	}
	
	
}
