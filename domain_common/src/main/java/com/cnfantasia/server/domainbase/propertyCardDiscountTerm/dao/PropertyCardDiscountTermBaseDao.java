package com.cnfantasia.server.domainbase.propertyCardDiscountTerm.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyCardDiscountTerm.entity.PropertyCardDiscountTerm;

/**
 * 描述:(物业代扣卡优惠方案) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PropertyCardDiscountTermBaseDao extends AbstractBaseDao implements IPropertyCardDiscountTermBaseDao{
	/**
	 * 根据条件查询(物业代扣卡优惠方案)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PropertyCardDiscountTerm> selectPropertyCardDiscountTermByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("propertyCardDiscountTermBase.select_propertyCardDiscountTerm",paramMap);
	}
	/**
	 * 按条件分页查询(物业代扣卡优惠方案)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PropertyCardDiscountTerm> selectPropertyCardDiscountTermByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPropertyCardDiscountTermCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PropertyCardDiscountTerm> resMap= sqlSession.selectList("propertyCardDiscountTermBase.select_propertyCardDiscountTerm_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(物业代扣卡优惠方案)信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertyCardDiscountTerm selectPropertyCardDiscountTermBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("propertyCardDiscountTermBase.select_propertyCardDiscountTerm_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(物业代扣卡优惠方案)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPropertyCardDiscountTermCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("propertyCardDiscountTermBase.select_propertyCardDiscountTerm_count", paramMap);
	}
	/**
	 * 往(物业代扣卡优惠方案)新增一条记录
	 * @param propertyCardDiscountTerm
	 * @return
	 */
	@Override
	public int insertPropertyCardDiscountTerm(PropertyCardDiscountTerm propertyCardDiscountTerm){
		return sqlSession.insert("propertyCardDiscountTermBase.insert_propertyCardDiscountTerm",propertyCardDiscountTerm);
	}
	/**
	 * 批量新增(物业代扣卡优惠方案)信息
	 * @param propertyCardDiscountTermList
	 * @return
	 */
	@Override
	public int insertPropertyCardDiscountTermBatch(List<PropertyCardDiscountTerm> propertyCardDiscountTermList) {
		return sqlSession.insert("propertyCardDiscountTermBase.insert_propertyCardDiscountTerm_Batch",propertyCardDiscountTermList);
	}
	
	/**
	 * 更新(物业代扣卡优惠方案)信息
	 * @param propertyCardDiscountTerm
	 * @return
	 */
	@Override
	public int updatePropertyCardDiscountTerm(PropertyCardDiscountTerm propertyCardDiscountTerm){
		return sqlSession.update("propertyCardDiscountTermBase.update_propertyCardDiscountTerm", propertyCardDiscountTerm);
	}
	/**
	 * 批量更新(物业代扣卡优惠方案)信息
	 * @param propertyCardDiscountTermList
	 * @return
	 */
	@Override
	public int updatePropertyCardDiscountTermBatch(List<PropertyCardDiscountTerm> propertyCardDiscountTermList) {
		return sqlSession.update("propertyCardDiscountTermBase.update_propertyCardDiscountTerm_Batch", propertyCardDiscountTermList);
	}
	
	/**
	 * 根据序列号删除(物业代扣卡优惠方案)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePropertyCardDiscountTermLogic(java.math.BigInteger id){
		PropertyCardDiscountTerm propertyCardDiscountTerm = new PropertyCardDiscountTerm();
		propertyCardDiscountTerm.setId(id);
		propertyCardDiscountTerm.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("propertyCardDiscountTermBase.delete_propertyCardDiscountTerm_Logic",propertyCardDiscountTerm);
	}
	
	/**
	 * 根据Id 批量删除(物业代扣卡优惠方案)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePropertyCardDiscountTermLogicBatch(List<java.math.BigInteger> idList) {
		List<PropertyCardDiscountTerm> delList = new java.util.ArrayList<PropertyCardDiscountTerm>();
		for(java.math.BigInteger id:idList){
			PropertyCardDiscountTerm propertyCardDiscountTerm = new PropertyCardDiscountTerm();
			propertyCardDiscountTerm.setId(id);
			propertyCardDiscountTerm.setSys0DelState(RecordState_DELETED);
			delList.add(propertyCardDiscountTerm);
		}
		return sqlSession.update("propertyCardDiscountTermBase.delete_propertyCardDiscountTerm_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(物业代扣卡优惠方案)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertyCardDiscountTerm(java.math.BigInteger id){
//		return sqlSession.delete("propertyCardDiscountTermBase.delete_propertyCardDiscountTerm", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业代扣卡优惠方案)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertyCardDiscountTermBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("propertyCardDiscountTermBase.delete_propertyCardDiscountTerm_Batch", idList);
//	}
	
	
}
