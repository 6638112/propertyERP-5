package com.cnfantasia.server.domainbase.propertyRechargePreferGroupbuilding.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyRechargePreferGroupbuilding.entity.PropertyRechargePreferGroupbuilding;

/**
 * 描述:(物业预充值随机立减小区规则关联表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PropertyRechargePreferGroupbuildingBaseDao extends AbstractBaseDao implements IPropertyRechargePreferGroupbuildingBaseDao{
	/**
	 * 根据条件查询(物业预充值随机立减小区规则关联表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PropertyRechargePreferGroupbuilding> selectPropertyRechargePreferGroupbuildingByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("propertyRechargePreferGroupbuildingBase.select_propertyRechargePreferGroupbuilding",paramMap);
	}
	/**
	 * 按条件分页查询(物业预充值随机立减小区规则关联表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PropertyRechargePreferGroupbuilding> selectPropertyRechargePreferGroupbuildingByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPropertyRechargePreferGroupbuildingCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PropertyRechargePreferGroupbuilding> resMap= sqlSession.selectList("propertyRechargePreferGroupbuildingBase.select_propertyRechargePreferGroupbuilding_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(物业预充值随机立减小区规则关联表)信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertyRechargePreferGroupbuilding selectPropertyRechargePreferGroupbuildingBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("propertyRechargePreferGroupbuildingBase.select_propertyRechargePreferGroupbuilding_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(物业预充值随机立减小区规则关联表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPropertyRechargePreferGroupbuildingCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("propertyRechargePreferGroupbuildingBase.select_propertyRechargePreferGroupbuilding_count", paramMap);
	}
	/**
	 * 往(物业预充值随机立减小区规则关联表)新增一条记录
	 * @param propertyRechargePreferGroupbuilding
	 * @return
	 */
	@Override
	public int insertPropertyRechargePreferGroupbuilding(PropertyRechargePreferGroupbuilding propertyRechargePreferGroupbuilding){
		return sqlSession.insert("propertyRechargePreferGroupbuildingBase.insert_propertyRechargePreferGroupbuilding",propertyRechargePreferGroupbuilding);
	}
	/**
	 * 批量新增(物业预充值随机立减小区规则关联表)信息
	 * @param propertyRechargePreferGroupbuildingList
	 * @return
	 */
	@Override
	public int insertPropertyRechargePreferGroupbuildingBatch(List<PropertyRechargePreferGroupbuilding> propertyRechargePreferGroupbuildingList) {
		if (propertyRechargePreferGroupbuildingList == null || propertyRechargePreferGroupbuildingList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = propertyRechargePreferGroupbuildingList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<PropertyRechargePreferGroupbuilding> batchList = propertyRechargePreferGroupbuildingList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("propertyRechargePreferGroupbuildingBase.insert_propertyRechargePreferGroupbuilding_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(物业预充值随机立减小区规则关联表)信息
	 * @param propertyRechargePreferGroupbuilding
	 * @return
	 */
	@Override
	public int updatePropertyRechargePreferGroupbuilding(PropertyRechargePreferGroupbuilding propertyRechargePreferGroupbuilding){
		return sqlSession.update("propertyRechargePreferGroupbuildingBase.update_propertyRechargePreferGroupbuilding", propertyRechargePreferGroupbuilding);
	}
	/**
	 * 批量更新(物业预充值随机立减小区规则关联表)信息
	 * @param propertyRechargePreferGroupbuildingList
	 * @return
	 */
	@Override
	public int updatePropertyRechargePreferGroupbuildingBatch(List<PropertyRechargePreferGroupbuilding> propertyRechargePreferGroupbuildingList) {
		if (propertyRechargePreferGroupbuildingList == null || propertyRechargePreferGroupbuildingList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("propertyRechargePreferGroupbuildingBase.update_propertyRechargePreferGroupbuilding_Batch", propertyRechargePreferGroupbuildingList);
	}
	
	/**
	 * 根据序列号删除(物业预充值随机立减小区规则关联表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	@Override
	public int deletePropertyRechargePreferGroupbuildingLogic(java.math.BigInteger id){
		PropertyRechargePreferGroupbuilding propertyRechargePreferGroupbuilding = new PropertyRechargePreferGroupbuilding();
		propertyRechargePreferGroupbuilding.setId(id);
		propertyRechargePreferGroupbuilding.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("propertyRechargePreferGroupbuildingBase.delete_propertyRechargePreferGroupbuilding_Logic",propertyRechargePreferGroupbuilding);
	}
	 */
	/**
	 * 根据Id 批量删除(物业预充值随机立减小区规则关联表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	@Override
	public int deletePropertyRechargePreferGroupbuildingLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<PropertyRechargePreferGroupbuilding> delList = new java.util.ArrayList<PropertyRechargePreferGroupbuilding>();
		for(java.math.BigInteger id:idList){
			PropertyRechargePreferGroupbuilding propertyRechargePreferGroupbuilding = new PropertyRechargePreferGroupbuilding();
			propertyRechargePreferGroupbuilding.setId(id);
			propertyRechargePreferGroupbuilding.setSys0DelState(RecordState_DELETED);
			delList.add(propertyRechargePreferGroupbuilding);
		}
		return sqlSession.update("propertyRechargePreferGroupbuildingBase.delete_propertyRechargePreferGroupbuilding_Logic_Batch",delList);
	}
	 */
//	/**
//	 * 根据序列号删除(物业预充值随机立减小区规则关联表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertyRechargePreferGroupbuilding(java.math.BigInteger id){
//		return sqlSession.delete("propertyRechargePreferGroupbuildingBase.delete_propertyRechargePreferGroupbuilding", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业预充值随机立减小区规则关联表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertyRechargePreferGroupbuildingBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("propertyRechargePreferGroupbuildingBase.delete_propertyRechargePreferGroupbuilding_Batch", idList);
//	}
	
	
}
