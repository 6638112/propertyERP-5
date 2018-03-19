package com.cnfantasia.server.domainbase.propertyDistrict.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyDistrict.entity.PropertyDistrict;

/**
 * 描述:(物业片区) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PropertyDistrictBaseDao extends AbstractBaseDao implements IPropertyDistrictBaseDao{
	/**
	 * 根据条件查询(物业片区)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PropertyDistrict> selectPropertyDistrictByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("propertyDistrictBase.select_propertyDistrict",paramMap);
	}
	/**
	 * 按条件分页查询(物业片区)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PropertyDistrict> selectPropertyDistrictByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPropertyDistrictCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PropertyDistrict> resMap= sqlSession.selectList("propertyDistrictBase.select_propertyDistrict_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(物业片区)信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertyDistrict selectPropertyDistrictBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("propertyDistrictBase.select_propertyDistrict_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(物业片区)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPropertyDistrictCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("propertyDistrictBase.select_propertyDistrict_count", paramMap);
	}
	/**
	 * 往(物业片区)新增一条记录
	 * @param propertyDistrict
	 * @return
	 */
	@Override
	public int insertPropertyDistrict(PropertyDistrict propertyDistrict){
		return sqlSession.insert("propertyDistrictBase.insert_propertyDistrict",propertyDistrict);
	}
	/**
	 * 批量新增(物业片区)信息
	 * @param propertyDistrictList
	 * @return
	 */
	@Override
	public int insertPropertyDistrictBatch(List<PropertyDistrict> propertyDistrictList) {
		if (propertyDistrictList == null || propertyDistrictList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = propertyDistrictList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<PropertyDistrict> batchList = propertyDistrictList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("propertyDistrictBase.insert_propertyDistrict_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(物业片区)信息
	 * @param propertyDistrict
	 * @return
	 */
	@Override
	public int updatePropertyDistrict(PropertyDistrict propertyDistrict){
		return sqlSession.update("propertyDistrictBase.update_propertyDistrict", propertyDistrict);
	}
	/**
	 * 批量更新(物业片区)信息
	 * @param propertyDistrictList
	 * @return
	 */
	@Override
	public int updatePropertyDistrictBatch(List<PropertyDistrict> propertyDistrictList) {
		if (propertyDistrictList == null || propertyDistrictList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("propertyDistrictBase.update_propertyDistrict_Batch", propertyDistrictList);
	}
	
	/**
	 * 根据序列号删除(物业片区)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePropertyDistrictLogic(java.math.BigInteger id){
		PropertyDistrict propertyDistrict = new PropertyDistrict();
		propertyDistrict.setId(id);
		propertyDistrict.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("propertyDistrictBase.delete_propertyDistrict_Logic",propertyDistrict);
	}
	
	/**
	 * 根据Id 批量删除(物业片区)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePropertyDistrictLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<PropertyDistrict> delList = new java.util.ArrayList<PropertyDistrict>();
		for(java.math.BigInteger id:idList){
			PropertyDistrict propertyDistrict = new PropertyDistrict();
			propertyDistrict.setId(id);
			propertyDistrict.setSys0DelState(RecordState_DELETED);
			delList.add(propertyDistrict);
		}
		return sqlSession.update("propertyDistrictBase.delete_propertyDistrict_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(物业片区)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertyDistrict(java.math.BigInteger id){
//		return sqlSession.delete("propertyDistrictBase.delete_propertyDistrict", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业片区)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertyDistrictBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("propertyDistrictBase.delete_propertyDistrict_Batch", idList);
//	}
	
	
}
