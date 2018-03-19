package com.cnfantasia.server.domainbase.revenueSignalAmountEbuy.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.revenueSignalAmountEbuy.entity.RevenueSignalAmountEbuy;

/**
 * 描述:(楼下店收益明细补充表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class RevenueSignalAmountEbuyBaseDao extends AbstractBaseDao implements IRevenueSignalAmountEbuyBaseDao{
	/**
	 * 根据条件查询(楼下店收益明细补充表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<RevenueSignalAmountEbuy> selectRevenueSignalAmountEbuyByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("revenueSignalAmountEbuyBase.select_revenueSignalAmountEbuy",paramMap);
	}
	/**
	 * 按条件分页查询(楼下店收益明细补充表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<RevenueSignalAmountEbuy> selectRevenueSignalAmountEbuyByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectRevenueSignalAmountEbuyCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<RevenueSignalAmountEbuy> resMap= sqlSession.selectList("revenueSignalAmountEbuyBase.select_revenueSignalAmountEbuy_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(楼下店收益明细补充表)信息
	 * @param id
	 * @return
	 */
	@Override
	public RevenueSignalAmountEbuy selectRevenueSignalAmountEbuyBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("revenueSignalAmountEbuyBase.select_revenueSignalAmountEbuy_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(楼下店收益明细补充表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectRevenueSignalAmountEbuyCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("revenueSignalAmountEbuyBase.select_revenueSignalAmountEbuy_count", paramMap);
	}
	/**
	 * 往(楼下店收益明细补充表)新增一条记录
	 * @param revenueSignalAmountEbuy
	 * @return
	 */
	@Override
	public int insertRevenueSignalAmountEbuy(RevenueSignalAmountEbuy revenueSignalAmountEbuy){
		return sqlSession.insert("revenueSignalAmountEbuyBase.insert_revenueSignalAmountEbuy",revenueSignalAmountEbuy);
	}
	/**
	 * 批量新增(楼下店收益明细补充表)信息
	 * @param revenueSignalAmountEbuyList
	 * @return
	 */
	@Override
	public int insertRevenueSignalAmountEbuyBatch(List<RevenueSignalAmountEbuy> revenueSignalAmountEbuyList) {
		return sqlSession.insert("revenueSignalAmountEbuyBase.insert_revenueSignalAmountEbuy_Batch",revenueSignalAmountEbuyList);
	}
	
	/**
	 * 更新(楼下店收益明细补充表)信息
	 * @param revenueSignalAmountEbuy
	 * @return
	 */
	@Override
	public int updateRevenueSignalAmountEbuy(RevenueSignalAmountEbuy revenueSignalAmountEbuy){
		return sqlSession.update("revenueSignalAmountEbuyBase.update_revenueSignalAmountEbuy", revenueSignalAmountEbuy);
	}
	/**
	 * 批量更新(楼下店收益明细补充表)信息
	 * @param revenueSignalAmountEbuyList
	 * @return
	 */
	@Override
	public int updateRevenueSignalAmountEbuyBatch(List<RevenueSignalAmountEbuy> revenueSignalAmountEbuyList) {
		return sqlSession.update("revenueSignalAmountEbuyBase.update_revenueSignalAmountEbuy_Batch", revenueSignalAmountEbuyList);
	}
	
	/**
	 * 根据序列号删除(楼下店收益明细补充表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteRevenueSignalAmountEbuyLogic(java.math.BigInteger id){
		RevenueSignalAmountEbuy revenueSignalAmountEbuy = new RevenueSignalAmountEbuy();
		revenueSignalAmountEbuy.setId(id);
		revenueSignalAmountEbuy.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("revenueSignalAmountEbuyBase.delete_revenueSignalAmountEbuy_Logic",revenueSignalAmountEbuy);
	}
	
	/**
	 * 根据Id 批量删除(楼下店收益明细补充表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteRevenueSignalAmountEbuyLogicBatch(List<java.math.BigInteger> idList) {
		List<RevenueSignalAmountEbuy> delList = new java.util.ArrayList<RevenueSignalAmountEbuy>();
		for(java.math.BigInteger id:idList){
			RevenueSignalAmountEbuy revenueSignalAmountEbuy = new RevenueSignalAmountEbuy();
			revenueSignalAmountEbuy.setId(id);
			revenueSignalAmountEbuy.setSys0DelState(RecordState_DELETED);
			delList.add(revenueSignalAmountEbuy);
		}
		return sqlSession.update("revenueSignalAmountEbuyBase.delete_revenueSignalAmountEbuy_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(楼下店收益明细补充表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteRevenueSignalAmountEbuy(java.math.BigInteger id){
//		return sqlSession.delete("revenueSignalAmountEbuyBase.delete_revenueSignalAmountEbuy", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(楼下店收益明细补充表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteRevenueSignalAmountEbuyBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("revenueSignalAmountEbuyBase.delete_revenueSignalAmountEbuy_Batch", idList);
//	}
	
	
}
