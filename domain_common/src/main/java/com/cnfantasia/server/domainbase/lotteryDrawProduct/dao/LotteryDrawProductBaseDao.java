package com.cnfantasia.server.domainbase.lotteryDrawProduct.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.lotteryDrawProduct.entity.LotteryDrawProduct;

/**
 * 描述:(幸运抽奖奖品表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class LotteryDrawProductBaseDao extends AbstractBaseDao implements ILotteryDrawProductBaseDao{
	/**
	 * 根据条件查询(幸运抽奖奖品表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<LotteryDrawProduct> selectLotteryDrawProductByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("lotteryDrawProductBase.select_lotteryDrawProduct",paramMap);
	}
	/**
	 * 按条件分页查询(幸运抽奖奖品表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<LotteryDrawProduct> selectLotteryDrawProductByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectLotteryDrawProductCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<LotteryDrawProduct> resMap= sqlSession.selectList("lotteryDrawProductBase.select_lotteryDrawProduct_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(幸运抽奖奖品表)信息
	 * @param id
	 * @return
	 */
	@Override
	public LotteryDrawProduct selectLotteryDrawProductBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("lotteryDrawProductBase.select_lotteryDrawProduct_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(幸运抽奖奖品表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectLotteryDrawProductCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("lotteryDrawProductBase.select_lotteryDrawProduct_count", paramMap);
	}
	/**
	 * 往(幸运抽奖奖品表)新增一条记录
	 * @param lotteryDrawProduct
	 * @return
	 */
	@Override
	public int insertLotteryDrawProduct(LotteryDrawProduct lotteryDrawProduct){
		return sqlSession.insert("lotteryDrawProductBase.insert_lotteryDrawProduct",lotteryDrawProduct);
	}
	/**
	 * 批量新增(幸运抽奖奖品表)信息
	 * @param lotteryDrawProductList
	 * @return
	 */
	@Override
	public int insertLotteryDrawProductBatch(List<LotteryDrawProduct> lotteryDrawProductList) {
		return sqlSession.insert("lotteryDrawProductBase.insert_lotteryDrawProduct_Batch",lotteryDrawProductList);
	}
	
	/**
	 * 更新(幸运抽奖奖品表)信息
	 * @param lotteryDrawProduct
	 * @return
	 */
	@Override
	public int updateLotteryDrawProduct(LotteryDrawProduct lotteryDrawProduct){
		return sqlSession.update("lotteryDrawProductBase.update_lotteryDrawProduct", lotteryDrawProduct);
	}
	/**
	 * 批量更新(幸运抽奖奖品表)信息
	 * @param lotteryDrawProductList
	 * @return
	 */
	@Override
	public int updateLotteryDrawProductBatch(List<LotteryDrawProduct> lotteryDrawProductList) {
		return sqlSession.update("lotteryDrawProductBase.update_lotteryDrawProduct_Batch", lotteryDrawProductList);
	}
	
	/**
	 * 根据序列号删除(幸运抽奖奖品表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteLotteryDrawProductLogic(java.math.BigInteger id){
		LotteryDrawProduct lotteryDrawProduct = new LotteryDrawProduct();
		lotteryDrawProduct.setId(id);
		lotteryDrawProduct.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("lotteryDrawProductBase.delete_lotteryDrawProduct_Logic",lotteryDrawProduct);
	}
	
	/**
	 * 根据Id 批量删除(幸运抽奖奖品表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteLotteryDrawProductLogicBatch(List<java.math.BigInteger> idList) {
		List<LotteryDrawProduct> delList = new java.util.ArrayList<LotteryDrawProduct>();
		for(java.math.BigInteger id:idList){
			LotteryDrawProduct lotteryDrawProduct = new LotteryDrawProduct();
			lotteryDrawProduct.setId(id);
			lotteryDrawProduct.setSys0DelState(RecordState_DELETED);
			delList.add(lotteryDrawProduct);
		}
		return sqlSession.update("lotteryDrawProductBase.delete_lotteryDrawProduct_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(幸运抽奖奖品表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteLotteryDrawProduct(java.math.BigInteger id){
//		return sqlSession.delete("lotteryDrawProductBase.delete_lotteryDrawProduct", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(幸运抽奖奖品表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteLotteryDrawProductBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("lotteryDrawProductBase.delete_lotteryDrawProduct_Batch", idList);
//	}
	
	
}
