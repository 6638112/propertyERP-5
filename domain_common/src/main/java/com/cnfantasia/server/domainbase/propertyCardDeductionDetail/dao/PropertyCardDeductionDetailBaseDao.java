package com.cnfantasia.server.domainbase.propertyCardDeductionDetail.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyCardDeductionDetail.entity.PropertyCardDeductionDetail;

/**
 * 描述:(物业代扣卡划扣详情) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PropertyCardDeductionDetailBaseDao extends AbstractBaseDao implements IPropertyCardDeductionDetailBaseDao{
	/**
	 * 根据条件查询(物业代扣卡划扣详情)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PropertyCardDeductionDetail> selectPropertyCardDeductionDetailByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("propertyCardDeductionDetailBase.select_propertyCardDeductionDetail",paramMap);
	}
	/**
	 * 按条件分页查询(物业代扣卡划扣详情)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PropertyCardDeductionDetail> selectPropertyCardDeductionDetailByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPropertyCardDeductionDetailCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PropertyCardDeductionDetail> resMap= sqlSession.selectList("propertyCardDeductionDetailBase.select_propertyCardDeductionDetail_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(物业代扣卡划扣详情)信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertyCardDeductionDetail selectPropertyCardDeductionDetailBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("propertyCardDeductionDetailBase.select_propertyCardDeductionDetail_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(物业代扣卡划扣详情)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPropertyCardDeductionDetailCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("propertyCardDeductionDetailBase.select_propertyCardDeductionDetail_count", paramMap);
	}
	/**
	 * 往(物业代扣卡划扣详情)新增一条记录
	 * @param propertyCardDeductionDetail
	 * @return
	 */
	@Override
	public int insertPropertyCardDeductionDetail(PropertyCardDeductionDetail propertyCardDeductionDetail){
		return sqlSession.insert("propertyCardDeductionDetailBase.insert_propertyCardDeductionDetail",propertyCardDeductionDetail);
	}
	/**
	 * 批量新增(物业代扣卡划扣详情)信息
	 * @param propertyCardDeductionDetailList
	 * @return
	 */
	@Override
	public int insertPropertyCardDeductionDetailBatch(List<PropertyCardDeductionDetail> propertyCardDeductionDetailList) {
		if (propertyCardDeductionDetailList == null || propertyCardDeductionDetailList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = propertyCardDeductionDetailList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<PropertyCardDeductionDetail> batchList = propertyCardDeductionDetailList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("propertyCardDeductionDetailBase.insert_propertyCardDeductionDetail_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(物业代扣卡划扣详情)信息
	 * @param propertyCardDeductionDetail
	 * @return
	 */
	@Override
	public int updatePropertyCardDeductionDetail(PropertyCardDeductionDetail propertyCardDeductionDetail){
		return sqlSession.update("propertyCardDeductionDetailBase.update_propertyCardDeductionDetail", propertyCardDeductionDetail);
	}
	/**
	 * 批量更新(物业代扣卡划扣详情)信息
	 * @param propertyCardDeductionDetailList
	 * @return
	 */
	@Override
	public int updatePropertyCardDeductionDetailBatch(List<PropertyCardDeductionDetail> propertyCardDeductionDetailList) {
		if (propertyCardDeductionDetailList == null || propertyCardDeductionDetailList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("propertyCardDeductionDetailBase.update_propertyCardDeductionDetail_Batch", propertyCardDeductionDetailList);
	}
	
	/**
	 * 根据序列号删除(物业代扣卡划扣详情)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePropertyCardDeductionDetailLogic(java.math.BigInteger id){
		PropertyCardDeductionDetail propertyCardDeductionDetail = new PropertyCardDeductionDetail();
		propertyCardDeductionDetail.setId(id);
		propertyCardDeductionDetail.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("propertyCardDeductionDetailBase.delete_propertyCardDeductionDetail_Logic",propertyCardDeductionDetail);
	}
	
	/**
	 * 根据Id 批量删除(物业代扣卡划扣详情)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePropertyCardDeductionDetailLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<PropertyCardDeductionDetail> delList = new java.util.ArrayList<PropertyCardDeductionDetail>();
		for(java.math.BigInteger id:idList){
			PropertyCardDeductionDetail propertyCardDeductionDetail = new PropertyCardDeductionDetail();
			propertyCardDeductionDetail.setId(id);
			propertyCardDeductionDetail.setSys0DelState(RecordState_DELETED);
			delList.add(propertyCardDeductionDetail);
		}
		return sqlSession.update("propertyCardDeductionDetailBase.delete_propertyCardDeductionDetail_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(物业代扣卡划扣详情)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertyCardDeductionDetail(java.math.BigInteger id){
//		return sqlSession.delete("propertyCardDeductionDetailBase.delete_propertyCardDeductionDetail", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业代扣卡划扣详情)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertyCardDeductionDetailBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("propertyCardDeductionDetailBase.delete_propertyCardDeductionDetail_Batch", idList);
//	}
	
	
}
