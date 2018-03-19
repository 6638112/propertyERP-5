package com.cnfantasia.server.domainbase.propertyFeeDetail.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyFeeDetail.entity.PropertyFeeDetail;

/**
 * 描述:(物业费费用清单详情) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PropertyFeeDetailBaseDao extends AbstractBaseDao implements IPropertyFeeDetailBaseDao{
	/**
	 * 根据条件查询(物业费费用清单详情)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PropertyFeeDetail> selectPropertyFeeDetailByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("propertyFeeDetailBase.select_propertyFeeDetail",paramMap);
	}
	/**
	 * 按条件分页查询(物业费费用清单详情)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PropertyFeeDetail> selectPropertyFeeDetailByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPropertyFeeDetailCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PropertyFeeDetail> resMap= sqlSession.selectList("propertyFeeDetailBase.select_propertyFeeDetail_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(物业费费用清单详情)信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertyFeeDetail selectPropertyFeeDetailBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("propertyFeeDetailBase.select_propertyFeeDetail_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(物业费费用清单详情)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPropertyFeeDetailCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("propertyFeeDetailBase.select_propertyFeeDetail_count", paramMap);
	}
	/**
	 * 往(物业费费用清单详情)新增一条记录
	 * @param propertyFeeDetail
	 * @return
	 */
	@Override
	public int insertPropertyFeeDetail(PropertyFeeDetail propertyFeeDetail){
		return sqlSession.insert("propertyFeeDetailBase.insert_propertyFeeDetail",propertyFeeDetail);
	}
	/**
	 * 批量新增(物业费费用清单详情)信息
	 * @param propertyFeeDetailList
	 * @return
	 */
	@Override
	public int insertPropertyFeeDetailBatch(List<PropertyFeeDetail> propertyFeeDetailList) {
		if (propertyFeeDetailList == null || propertyFeeDetailList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = propertyFeeDetailList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<PropertyFeeDetail> batchList = propertyFeeDetailList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("propertyFeeDetailBase.insert_propertyFeeDetail_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(物业费费用清单详情)信息
	 * @param propertyFeeDetail
	 * @return
	 */
	@Override
	public int updatePropertyFeeDetail(PropertyFeeDetail propertyFeeDetail){
		return sqlSession.update("propertyFeeDetailBase.update_propertyFeeDetail", propertyFeeDetail);
	}
	/**
	 * 批量更新(物业费费用清单详情)信息
	 * @param propertyFeeDetailList
	 * @return
	 */
	@Override
	public int updatePropertyFeeDetailBatch(List<PropertyFeeDetail> propertyFeeDetailList) {
		if (propertyFeeDetailList == null || propertyFeeDetailList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("propertyFeeDetailBase.update_propertyFeeDetail_Batch", propertyFeeDetailList);
	}
	
	/**
	 * 根据序列号删除(物业费费用清单详情)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePropertyFeeDetailLogic(java.math.BigInteger id){
		PropertyFeeDetail propertyFeeDetail = new PropertyFeeDetail();
		propertyFeeDetail.setId(id);
		propertyFeeDetail.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("propertyFeeDetailBase.delete_propertyFeeDetail_Logic",propertyFeeDetail);
	}
	
	/**
	 * 根据Id 批量删除(物业费费用清单详情)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePropertyFeeDetailLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<PropertyFeeDetail> delList = new java.util.ArrayList<PropertyFeeDetail>();
		for(java.math.BigInteger id:idList){
			PropertyFeeDetail propertyFeeDetail = new PropertyFeeDetail();
			propertyFeeDetail.setId(id);
			propertyFeeDetail.setSys0DelState(RecordState_DELETED);
			delList.add(propertyFeeDetail);
		}
		return sqlSession.update("propertyFeeDetailBase.delete_propertyFeeDetail_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(物业费费用清单详情)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertyFeeDetail(java.math.BigInteger id){
//		return sqlSession.delete("propertyFeeDetailBase.delete_propertyFeeDetail", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业费费用清单详情)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertyFeeDetailBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("propertyFeeDetailBase.delete_propertyFeeDetail_Batch", idList);
//	}
	
	
}
