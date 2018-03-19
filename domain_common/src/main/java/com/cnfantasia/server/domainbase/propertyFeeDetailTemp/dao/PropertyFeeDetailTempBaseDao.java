package com.cnfantasia.server.domainbase.propertyFeeDetailTemp.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyFeeDetailTemp.entity.PropertyFeeDetailTemp;

/**
 * 描述:(物业收费项费用明细临时表（生成账单使用）) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PropertyFeeDetailTempBaseDao extends AbstractBaseDao implements IPropertyFeeDetailTempBaseDao{
	/**
	 * 根据条件查询(物业收费项费用明细临时表（生成账单使用）)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PropertyFeeDetailTemp> selectPropertyFeeDetailTempByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("propertyFeeDetailTempBase.select_propertyFeeDetailTemp",paramMap);
	}
	/**
	 * 按条件分页查询(物业收费项费用明细临时表（生成账单使用）)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PropertyFeeDetailTemp> selectPropertyFeeDetailTempByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPropertyFeeDetailTempCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PropertyFeeDetailTemp> resMap= sqlSession.selectList("propertyFeeDetailTempBase.select_propertyFeeDetailTemp_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(物业收费项费用明细临时表（生成账单使用）)信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertyFeeDetailTemp selectPropertyFeeDetailTempBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("propertyFeeDetailTempBase.select_propertyFeeDetailTemp_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(物业收费项费用明细临时表（生成账单使用）)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPropertyFeeDetailTempCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("propertyFeeDetailTempBase.select_propertyFeeDetailTemp_count", paramMap);
	}
	/**
	 * 往(物业收费项费用明细临时表（生成账单使用）)新增一条记录
	 * @param propertyFeeDetailTemp
	 * @return
	 */
	@Override
	public int insertPropertyFeeDetailTemp(PropertyFeeDetailTemp propertyFeeDetailTemp){
		return sqlSession.insert("propertyFeeDetailTempBase.insert_propertyFeeDetailTemp",propertyFeeDetailTemp);
	}
	/**
	 * 批量新增(物业收费项费用明细临时表（生成账单使用）)信息
	 * @param propertyFeeDetailTempList
	 * @return
	 */
	@Override
	public int insertPropertyFeeDetailTempBatch(List<PropertyFeeDetailTemp> propertyFeeDetailTempList) {
		if (propertyFeeDetailTempList == null || propertyFeeDetailTempList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = propertyFeeDetailTempList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<PropertyFeeDetailTemp> batchList = propertyFeeDetailTempList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("propertyFeeDetailTempBase.insert_propertyFeeDetailTemp_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(物业收费项费用明细临时表（生成账单使用）)信息
	 * @param propertyFeeDetailTemp
	 * @return
	 */
	@Override
	public int updatePropertyFeeDetailTemp(PropertyFeeDetailTemp propertyFeeDetailTemp){
		return sqlSession.update("propertyFeeDetailTempBase.update_propertyFeeDetailTemp", propertyFeeDetailTemp);
	}
	/**
	 * 批量更新(物业收费项费用明细临时表（生成账单使用）)信息
	 * @param propertyFeeDetailTempList
	 * @return
	 */
	@Override
	public int updatePropertyFeeDetailTempBatch(List<PropertyFeeDetailTemp> propertyFeeDetailTempList) {
		if (propertyFeeDetailTempList == null || propertyFeeDetailTempList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("propertyFeeDetailTempBase.update_propertyFeeDetailTemp_Batch", propertyFeeDetailTempList);
	}
	
	/**
	 * 根据序列号删除(物业收费项费用明细临时表（生成账单使用）)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePropertyFeeDetailTempLogic(java.math.BigInteger id){
		PropertyFeeDetailTemp propertyFeeDetailTemp = new PropertyFeeDetailTemp();
		propertyFeeDetailTemp.setId(id);
		propertyFeeDetailTemp.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("propertyFeeDetailTempBase.delete_propertyFeeDetailTemp_Logic",propertyFeeDetailTemp);
	}
	
	/**
	 * 根据Id 批量删除(物业收费项费用明细临时表（生成账单使用）)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePropertyFeeDetailTempLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<PropertyFeeDetailTemp> delList = new java.util.ArrayList<PropertyFeeDetailTemp>();
		for(java.math.BigInteger id:idList){
			PropertyFeeDetailTemp propertyFeeDetailTemp = new PropertyFeeDetailTemp();
			propertyFeeDetailTemp.setId(id);
			propertyFeeDetailTemp.setSys0DelState(RecordState_DELETED);
			delList.add(propertyFeeDetailTemp);
		}
		return sqlSession.update("propertyFeeDetailTempBase.delete_propertyFeeDetailTemp_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(物业收费项费用明细临时表（生成账单使用）)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertyFeeDetailTemp(java.math.BigInteger id){
//		return sqlSession.delete("propertyFeeDetailTempBase.delete_propertyFeeDetailTemp", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业收费项费用明细临时表（生成账单使用）)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertyFeeDetailTempBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("propertyFeeDetailTempBase.delete_propertyFeeDetailTemp_Batch", idList);
//	}
	
	
}
