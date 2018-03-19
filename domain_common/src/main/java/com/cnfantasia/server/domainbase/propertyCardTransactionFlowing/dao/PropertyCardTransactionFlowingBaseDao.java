package com.cnfantasia.server.domainbase.propertyCardTransactionFlowing.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyCardTransactionFlowing.entity.PropertyCardTransactionFlowing;

/**
 * 描述:(物业代扣卡交易流水) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PropertyCardTransactionFlowingBaseDao extends AbstractBaseDao implements IPropertyCardTransactionFlowingBaseDao{
	/**
	 * 根据条件查询(物业代扣卡交易流水)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PropertyCardTransactionFlowing> selectPropertyCardTransactionFlowingByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("propertyCardTransactionFlowingBase.select_propertyCardTransactionFlowing",paramMap);
	}
	/**
	 * 按条件分页查询(物业代扣卡交易流水)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PropertyCardTransactionFlowing> selectPropertyCardTransactionFlowingByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPropertyCardTransactionFlowingCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PropertyCardTransactionFlowing> resMap= sqlSession.selectList("propertyCardTransactionFlowingBase.select_propertyCardTransactionFlowing_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(物业代扣卡交易流水)信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertyCardTransactionFlowing selectPropertyCardTransactionFlowingBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("propertyCardTransactionFlowingBase.select_propertyCardTransactionFlowing_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(物业代扣卡交易流水)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPropertyCardTransactionFlowingCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("propertyCardTransactionFlowingBase.select_propertyCardTransactionFlowing_count", paramMap);
	}
	/**
	 * 往(物业代扣卡交易流水)新增一条记录
	 * @param propertyCardTransactionFlowing
	 * @return
	 */
	@Override
	public int insertPropertyCardTransactionFlowing(PropertyCardTransactionFlowing propertyCardTransactionFlowing){
		return sqlSession.insert("propertyCardTransactionFlowingBase.insert_propertyCardTransactionFlowing",propertyCardTransactionFlowing);
	}
	/**
	 * 批量新增(物业代扣卡交易流水)信息
	 * @param propertyCardTransactionFlowingList
	 * @return
	 */
	@Override
	public int insertPropertyCardTransactionFlowingBatch(List<PropertyCardTransactionFlowing> propertyCardTransactionFlowingList) {
		return sqlSession.insert("propertyCardTransactionFlowingBase.insert_propertyCardTransactionFlowing_Batch",propertyCardTransactionFlowingList);
	}
	
	/**
	 * 更新(物业代扣卡交易流水)信息
	 * @param propertyCardTransactionFlowing
	 * @return
	 */
	@Override
	public int updatePropertyCardTransactionFlowing(PropertyCardTransactionFlowing propertyCardTransactionFlowing){
		return sqlSession.update("propertyCardTransactionFlowingBase.update_propertyCardTransactionFlowing", propertyCardTransactionFlowing);
	}
	/**
	 * 批量更新(物业代扣卡交易流水)信息
	 * @param propertyCardTransactionFlowingList
	 * @return
	 */
	@Override
	public int updatePropertyCardTransactionFlowingBatch(List<PropertyCardTransactionFlowing> propertyCardTransactionFlowingList) {
		return sqlSession.update("propertyCardTransactionFlowingBase.update_propertyCardTransactionFlowing_Batch", propertyCardTransactionFlowingList);
	}
	
	/**
	 * 根据序列号删除(物业代扣卡交易流水)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePropertyCardTransactionFlowingLogic(java.math.BigInteger id){
		PropertyCardTransactionFlowing propertyCardTransactionFlowing = new PropertyCardTransactionFlowing();
		propertyCardTransactionFlowing.setId(id);
		propertyCardTransactionFlowing.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("propertyCardTransactionFlowingBase.delete_propertyCardTransactionFlowing_Logic",propertyCardTransactionFlowing);
	}
	
	/**
	 * 根据Id 批量删除(物业代扣卡交易流水)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePropertyCardTransactionFlowingLogicBatch(List<java.math.BigInteger> idList) {
		List<PropertyCardTransactionFlowing> delList = new java.util.ArrayList<PropertyCardTransactionFlowing>();
		for(java.math.BigInteger id:idList){
			PropertyCardTransactionFlowing propertyCardTransactionFlowing = new PropertyCardTransactionFlowing();
			propertyCardTransactionFlowing.setId(id);
			propertyCardTransactionFlowing.setSys0DelState(RecordState_DELETED);
			delList.add(propertyCardTransactionFlowing);
		}
		return sqlSession.update("propertyCardTransactionFlowingBase.delete_propertyCardTransactionFlowing_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(物业代扣卡交易流水)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertyCardTransactionFlowing(java.math.BigInteger id){
//		return sqlSession.delete("propertyCardTransactionFlowingBase.delete_propertyCardTransactionFlowing", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业代扣卡交易流水)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertyCardTransactionFlowingBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("propertyCardTransactionFlowingBase.delete_propertyCardTransactionFlowing_Batch", idList);
//	}
	
	
}
