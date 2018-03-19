package com.cnfantasia.server.domainbase.propertyCard.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyCard.entity.PropertyCard;

/**
 * 描述:(物业代扣卡) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PropertyCardBaseDao extends AbstractBaseDao implements IPropertyCardBaseDao{
	/**
	 * 根据条件查询(物业代扣卡)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PropertyCard> selectPropertyCardByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("propertyCardBase.select_propertyCard",paramMap);
	}
	/**
	 * 按条件分页查询(物业代扣卡)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PropertyCard> selectPropertyCardByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPropertyCardCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PropertyCard> resMap= sqlSession.selectList("propertyCardBase.select_propertyCard_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(物业代扣卡)信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertyCard selectPropertyCardBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("propertyCardBase.select_propertyCard_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(物业代扣卡)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPropertyCardCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("propertyCardBase.select_propertyCard_count", paramMap);
	}
	/**
	 * 往(物业代扣卡)新增一条记录
	 * @param propertyCard
	 * @return
	 */
	@Override
	public int insertPropertyCard(PropertyCard propertyCard){
		return sqlSession.insert("propertyCardBase.insert_propertyCard",propertyCard);
	}
	/**
	 * 批量新增(物业代扣卡)信息
	 * @param propertyCardList
	 * @return
	 */
	@Override
	public int insertPropertyCardBatch(List<PropertyCard> propertyCardList) {
		return sqlSession.insert("propertyCardBase.insert_propertyCard_Batch",propertyCardList);
	}
	
	/**
	 * 更新(物业代扣卡)信息
	 * @param propertyCard
	 * @return
	 */
	@Override
	public int updatePropertyCard(PropertyCard propertyCard){
		return sqlSession.update("propertyCardBase.update_propertyCard", propertyCard);
	}
	/**
	 * 批量更新(物业代扣卡)信息
	 * @param propertyCardList
	 * @return
	 */
	@Override
	public int updatePropertyCardBatch(List<PropertyCard> propertyCardList) {
		return sqlSession.update("propertyCardBase.update_propertyCard_Batch", propertyCardList);
	}
	
	/**
	 * 根据序列号删除(物业代扣卡)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePropertyCardLogic(java.math.BigInteger id){
		PropertyCard propertyCard = new PropertyCard();
		propertyCard.setId(id);
		propertyCard.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("propertyCardBase.delete_propertyCard_Logic",propertyCard);
	}
	
	/**
	 * 根据Id 批量删除(物业代扣卡)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePropertyCardLogicBatch(List<java.math.BigInteger> idList) {
		List<PropertyCard> delList = new java.util.ArrayList<PropertyCard>();
		for(java.math.BigInteger id:idList){
			PropertyCard propertyCard = new PropertyCard();
			propertyCard.setId(id);
			propertyCard.setSys0DelState(RecordState_DELETED);
			delList.add(propertyCard);
		}
		return sqlSession.update("propertyCardBase.delete_propertyCard_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(物业代扣卡)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertyCard(java.math.BigInteger id){
//		return sqlSession.delete("propertyCardBase.delete_propertyCard", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业代扣卡)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertyCardBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("propertyCardBase.delete_propertyCard_Batch", idList);
//	}
	
	
}
