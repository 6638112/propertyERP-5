package com.cnfantasia.server.domainbase.ebuyBuyCarHasTEbuyProduct.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyBuyCarHasTEbuyProduct.entity.EbuyBuyCarHasTEbuyProduct;

/**
 * 描述:() DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EbuyBuyCarHasTEbuyProductBaseDao extends AbstractBaseDao implements IEbuyBuyCarHasTEbuyProductBaseDao{
	/**
	 * 根据条件查询()信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyBuyCarHasTEbuyProduct> selectEbuyBuyCarHasTEbuyProductByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("ebuyBuyCarHasTEbuyProductBase.select_ebuyBuyCarHasTEbuyProduct",paramMap);
	}
	/**
	 * 按条件分页查询()信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyBuyCarHasTEbuyProduct> selectEbuyBuyCarHasTEbuyProductByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEbuyBuyCarHasTEbuyProductCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EbuyBuyCarHasTEbuyProduct> resMap= sqlSession.selectList("ebuyBuyCarHasTEbuyProductBase.select_ebuyBuyCarHasTEbuyProduct_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询()信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyBuyCarHasTEbuyProduct selectEbuyBuyCarHasTEbuyProductBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("ebuyBuyCarHasTEbuyProductBase.select_ebuyBuyCarHasTEbuyProduct_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的()记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEbuyBuyCarHasTEbuyProductCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("ebuyBuyCarHasTEbuyProductBase.select_ebuyBuyCarHasTEbuyProduct_count", paramMap);
	}
	/**
	 * 往()新增一条记录
	 * @param ebuyBuyCarHasTEbuyProduct
	 * @return
	 */
	@Override
	public int insertEbuyBuyCarHasTEbuyProduct(EbuyBuyCarHasTEbuyProduct ebuyBuyCarHasTEbuyProduct){
		return sqlSession.insert("ebuyBuyCarHasTEbuyProductBase.insert_ebuyBuyCarHasTEbuyProduct",ebuyBuyCarHasTEbuyProduct);
	}
	/**
	 * 批量新增()信息
	 * @param ebuyBuyCarHasTEbuyProductList
	 * @return
	 */
	@Override
	public int insertEbuyBuyCarHasTEbuyProductBatch(List<EbuyBuyCarHasTEbuyProduct> ebuyBuyCarHasTEbuyProductList) {
		return sqlSession.insert("ebuyBuyCarHasTEbuyProductBase.insert_ebuyBuyCarHasTEbuyProduct_Batch",ebuyBuyCarHasTEbuyProductList);
	}
	
	/**
	 * 更新()信息
	 * @param ebuyBuyCarHasTEbuyProduct
	 * @return
	 */
	@Override
	public int updateEbuyBuyCarHasTEbuyProduct(EbuyBuyCarHasTEbuyProduct ebuyBuyCarHasTEbuyProduct){
		return sqlSession.update("ebuyBuyCarHasTEbuyProductBase.update_ebuyBuyCarHasTEbuyProduct", ebuyBuyCarHasTEbuyProduct);
	}
	/**
	 * 批量更新()信息
	 * @param ebuyBuyCarHasTEbuyProductList
	 * @return
	 */
	@Override
	public int updateEbuyBuyCarHasTEbuyProductBatch(List<EbuyBuyCarHasTEbuyProduct> ebuyBuyCarHasTEbuyProductList) {
		return sqlSession.update("ebuyBuyCarHasTEbuyProductBase.update_ebuyBuyCarHasTEbuyProduct_Batch", ebuyBuyCarHasTEbuyProductList);
	}
	
	/**
	 * 根据序列号删除()信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyBuyCarHasTEbuyProductLogic(java.math.BigInteger id){
		EbuyBuyCarHasTEbuyProduct ebuyBuyCarHasTEbuyProduct = new EbuyBuyCarHasTEbuyProduct();
		ebuyBuyCarHasTEbuyProduct.setId(id);
		ebuyBuyCarHasTEbuyProduct.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("ebuyBuyCarHasTEbuyProductBase.delete_ebuyBuyCarHasTEbuyProduct_Logic",ebuyBuyCarHasTEbuyProduct);
	}
	
	/**
	 * 根据Id 批量删除()信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyBuyCarHasTEbuyProductLogicBatch(List<java.math.BigInteger> idList) {
		List<EbuyBuyCarHasTEbuyProduct> delList = new java.util.ArrayList<EbuyBuyCarHasTEbuyProduct>();
		for(java.math.BigInteger id:idList){
			EbuyBuyCarHasTEbuyProduct ebuyBuyCarHasTEbuyProduct = new EbuyBuyCarHasTEbuyProduct();
			ebuyBuyCarHasTEbuyProduct.setId(id);
			ebuyBuyCarHasTEbuyProduct.setSys0DelState(RecordState_DELETED);
			delList.add(ebuyBuyCarHasTEbuyProduct);
		}
		return sqlSession.update("ebuyBuyCarHasTEbuyProductBase.delete_ebuyBuyCarHasTEbuyProduct_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除()信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyBuyCarHasTEbuyProduct(java.math.BigInteger id){
//		return sqlSession.delete("ebuyBuyCarHasTEbuyProductBase.delete_ebuyBuyCarHasTEbuyProduct", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除()信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyBuyCarHasTEbuyProductBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("ebuyBuyCarHasTEbuyProductBase.delete_ebuyBuyCarHasTEbuyProduct_Batch", idList);
//	}
	
	
}
