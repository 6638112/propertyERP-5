package com.cnfantasia.server.domainbase.propertyDistrictHasGroupBuilding.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyDistrictHasGroupBuilding.entity.PropertyDistrictHasGroupBuilding;

/**
 * 描述:(物业片区与小区关联) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PropertyDistrictHasGroupBuildingBaseDao extends AbstractBaseDao implements IPropertyDistrictHasGroupBuildingBaseDao{
	/**
	 * 根据条件查询(物业片区与小区关联)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PropertyDistrictHasGroupBuilding> selectPropertyDistrictHasGroupBuildingByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("propertyDistrictHasGroupBuildingBase.select_propertyDistrictHasGroupBuilding",paramMap);
	}
	/**
	 * 按条件分页查询(物业片区与小区关联)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PropertyDistrictHasGroupBuilding> selectPropertyDistrictHasGroupBuildingByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPropertyDistrictHasGroupBuildingCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PropertyDistrictHasGroupBuilding> resMap= sqlSession.selectList("propertyDistrictHasGroupBuildingBase.select_propertyDistrictHasGroupBuilding_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(物业片区与小区关联)信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertyDistrictHasGroupBuilding selectPropertyDistrictHasGroupBuildingBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("propertyDistrictHasGroupBuildingBase.select_propertyDistrictHasGroupBuilding_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(物业片区与小区关联)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPropertyDistrictHasGroupBuildingCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("propertyDistrictHasGroupBuildingBase.select_propertyDistrictHasGroupBuilding_count", paramMap);
	}
	/**
	 * 往(物业片区与小区关联)新增一条记录
	 * @param propertyDistrictHasGroupBuilding
	 * @return
	 */
	@Override
	public int insertPropertyDistrictHasGroupBuilding(PropertyDistrictHasGroupBuilding propertyDistrictHasGroupBuilding){
		return sqlSession.insert("propertyDistrictHasGroupBuildingBase.insert_propertyDistrictHasGroupBuilding",propertyDistrictHasGroupBuilding);
	}
	/**
	 * 批量新增(物业片区与小区关联)信息
	 * @param propertyDistrictHasGroupBuildingList
	 * @return
	 */
	@Override
	public int insertPropertyDistrictHasGroupBuildingBatch(List<PropertyDistrictHasGroupBuilding> propertyDistrictHasGroupBuildingList) {
		if (propertyDistrictHasGroupBuildingList == null || propertyDistrictHasGroupBuildingList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = propertyDistrictHasGroupBuildingList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<PropertyDistrictHasGroupBuilding> batchList = propertyDistrictHasGroupBuildingList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("propertyDistrictHasGroupBuildingBase.insert_propertyDistrictHasGroupBuilding_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(物业片区与小区关联)信息
	 * @param propertyDistrictHasGroupBuilding
	 * @return
	 */
	@Override
	public int updatePropertyDistrictHasGroupBuilding(PropertyDistrictHasGroupBuilding propertyDistrictHasGroupBuilding){
		return sqlSession.update("propertyDistrictHasGroupBuildingBase.update_propertyDistrictHasGroupBuilding", propertyDistrictHasGroupBuilding);
	}
	/**
	 * 批量更新(物业片区与小区关联)信息
	 * @param propertyDistrictHasGroupBuildingList
	 * @return
	 */
	@Override
	public int updatePropertyDistrictHasGroupBuildingBatch(List<PropertyDistrictHasGroupBuilding> propertyDistrictHasGroupBuildingList) {
		if (propertyDistrictHasGroupBuildingList == null || propertyDistrictHasGroupBuildingList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("propertyDistrictHasGroupBuildingBase.update_propertyDistrictHasGroupBuilding_Batch", propertyDistrictHasGroupBuildingList);
	}
	
	/**
	 * 根据序列号删除(物业片区与小区关联)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePropertyDistrictHasGroupBuildingLogic(java.math.BigInteger id){
		PropertyDistrictHasGroupBuilding propertyDistrictHasGroupBuilding = new PropertyDistrictHasGroupBuilding();
		propertyDistrictHasGroupBuilding.setId(id);
		propertyDistrictHasGroupBuilding.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("propertyDistrictHasGroupBuildingBase.delete_propertyDistrictHasGroupBuilding_Logic",propertyDistrictHasGroupBuilding);
	}
	
	/**
	 * 根据Id 批量删除(物业片区与小区关联)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePropertyDistrictHasGroupBuildingLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<PropertyDistrictHasGroupBuilding> delList = new java.util.ArrayList<PropertyDistrictHasGroupBuilding>();
		for(java.math.BigInteger id:idList){
			PropertyDistrictHasGroupBuilding propertyDistrictHasGroupBuilding = new PropertyDistrictHasGroupBuilding();
			propertyDistrictHasGroupBuilding.setId(id);
			propertyDistrictHasGroupBuilding.setSys0DelState(RecordState_DELETED);
			delList.add(propertyDistrictHasGroupBuilding);
		}
		return sqlSession.update("propertyDistrictHasGroupBuildingBase.delete_propertyDistrictHasGroupBuilding_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(物业片区与小区关联)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertyDistrictHasGroupBuilding(java.math.BigInteger id){
//		return sqlSession.delete("propertyDistrictHasGroupBuildingBase.delete_propertyDistrictHasGroupBuilding", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业片区与小区关联)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertyDistrictHasGroupBuildingBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("propertyDistrictHasGroupBuildingBase.delete_propertyDistrictHasGroupBuilding_Batch", idList);
//	}
	
	
}
