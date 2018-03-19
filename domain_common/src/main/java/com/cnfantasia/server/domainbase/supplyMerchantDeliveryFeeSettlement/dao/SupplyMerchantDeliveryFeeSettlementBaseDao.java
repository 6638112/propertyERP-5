package com.cnfantasia.server.domainbase.supplyMerchantDeliveryFeeSettlement.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.supplyMerchantDeliveryFeeSettlement.entity.SupplyMerchantDeliveryFeeSettlement;

/**
 * 描述:(与供应商结算邮费规则) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class SupplyMerchantDeliveryFeeSettlementBaseDao extends AbstractBaseDao implements ISupplyMerchantDeliveryFeeSettlementBaseDao{
	/**
	 * 根据条件查询(与供应商结算邮费规则)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<SupplyMerchantDeliveryFeeSettlement> selectSupplyMerchantDeliveryFeeSettlementByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("supplyMerchantDeliveryFeeSettlementBase.select_supplyMerchantDeliveryFeeSettlement",paramMap);
	}
	/**
	 * 按条件分页查询(与供应商结算邮费规则)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<SupplyMerchantDeliveryFeeSettlement> selectSupplyMerchantDeliveryFeeSettlementByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectSupplyMerchantDeliveryFeeSettlementCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<SupplyMerchantDeliveryFeeSettlement> resMap= sqlSession.selectList("supplyMerchantDeliveryFeeSettlementBase.select_supplyMerchantDeliveryFeeSettlement_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(与供应商结算邮费规则)信息
	 * @param id
	 * @return
	 */
	@Override
	public SupplyMerchantDeliveryFeeSettlement selectSupplyMerchantDeliveryFeeSettlementBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("supplyMerchantDeliveryFeeSettlementBase.select_supplyMerchantDeliveryFeeSettlement_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(与供应商结算邮费规则)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectSupplyMerchantDeliveryFeeSettlementCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("supplyMerchantDeliveryFeeSettlementBase.select_supplyMerchantDeliveryFeeSettlement_count", paramMap);
	}
	/**
	 * 往(与供应商结算邮费规则)新增一条记录
	 * @param supplyMerchantDeliveryFeeSettlement
	 * @return
	 */
	@Override
	public int insertSupplyMerchantDeliveryFeeSettlement(SupplyMerchantDeliveryFeeSettlement supplyMerchantDeliveryFeeSettlement){
		return sqlSession.insert("supplyMerchantDeliveryFeeSettlementBase.insert_supplyMerchantDeliveryFeeSettlement",supplyMerchantDeliveryFeeSettlement);
	}
	/**
	 * 批量新增(与供应商结算邮费规则)信息
	 * @param supplyMerchantDeliveryFeeSettlementList
	 * @return
	 */
	@Override
	public int insertSupplyMerchantDeliveryFeeSettlementBatch(List<SupplyMerchantDeliveryFeeSettlement> supplyMerchantDeliveryFeeSettlementList) {
		return sqlSession.insert("supplyMerchantDeliveryFeeSettlementBase.insert_supplyMerchantDeliveryFeeSettlement_Batch",supplyMerchantDeliveryFeeSettlementList);
	}
	
	/**
	 * 更新(与供应商结算邮费规则)信息
	 * @param supplyMerchantDeliveryFeeSettlement
	 * @return
	 */
	@Override
	public int updateSupplyMerchantDeliveryFeeSettlement(SupplyMerchantDeliveryFeeSettlement supplyMerchantDeliveryFeeSettlement){
		return sqlSession.update("supplyMerchantDeliveryFeeSettlementBase.update_supplyMerchantDeliveryFeeSettlement", supplyMerchantDeliveryFeeSettlement);
	}
	/**
	 * 批量更新(与供应商结算邮费规则)信息
	 * @param supplyMerchantDeliveryFeeSettlementList
	 * @return
	 */
	@Override
	public int updateSupplyMerchantDeliveryFeeSettlementBatch(List<SupplyMerchantDeliveryFeeSettlement> supplyMerchantDeliveryFeeSettlementList) {
		return sqlSession.update("supplyMerchantDeliveryFeeSettlementBase.update_supplyMerchantDeliveryFeeSettlement_Batch", supplyMerchantDeliveryFeeSettlementList);
	}
	
	/**
	 * 根据序列号删除(与供应商结算邮费规则)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteSupplyMerchantDeliveryFeeSettlementLogic(java.math.BigInteger id){
		SupplyMerchantDeliveryFeeSettlement supplyMerchantDeliveryFeeSettlement = new SupplyMerchantDeliveryFeeSettlement();
		supplyMerchantDeliveryFeeSettlement.setId(id);
		supplyMerchantDeliveryFeeSettlement.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("supplyMerchantDeliveryFeeSettlementBase.delete_supplyMerchantDeliveryFeeSettlement_Logic",supplyMerchantDeliveryFeeSettlement);
	}
	
	/**
	 * 根据Id 批量删除(与供应商结算邮费规则)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteSupplyMerchantDeliveryFeeSettlementLogicBatch(List<java.math.BigInteger> idList) {
		List<SupplyMerchantDeliveryFeeSettlement> delList = new java.util.ArrayList<SupplyMerchantDeliveryFeeSettlement>();
		for(java.math.BigInteger id:idList){
			SupplyMerchantDeliveryFeeSettlement supplyMerchantDeliveryFeeSettlement = new SupplyMerchantDeliveryFeeSettlement();
			supplyMerchantDeliveryFeeSettlement.setId(id);
			supplyMerchantDeliveryFeeSettlement.setSys0DelState(RecordState_DELETED);
			delList.add(supplyMerchantDeliveryFeeSettlement);
		}
		return sqlSession.update("supplyMerchantDeliveryFeeSettlementBase.delete_supplyMerchantDeliveryFeeSettlement_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(与供应商结算邮费规则)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteSupplyMerchantDeliveryFeeSettlement(java.math.BigInteger id){
//		return sqlSession.delete("supplyMerchantDeliveryFeeSettlementBase.delete_supplyMerchantDeliveryFeeSettlement", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(与供应商结算邮费规则)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteSupplyMerchantDeliveryFeeSettlementBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("supplyMerchantDeliveryFeeSettlementBase.delete_supplyMerchantDeliveryFeeSettlement_Batch", idList);
//	}
	
	
}
