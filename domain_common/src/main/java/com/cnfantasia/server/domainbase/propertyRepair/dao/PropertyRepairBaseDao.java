package com.cnfantasia.server.domainbase.propertyRepair.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyRepair.entity.PropertyRepair;

/**
 * 描述:(物业报修单) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PropertyRepairBaseDao extends AbstractBaseDao implements IPropertyRepairBaseDao{
	/**
	 * 根据条件查询(物业报修单)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PropertyRepair> selectPropertyRepairByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("propertyRepairBase.select_propertyRepair",paramMap);
	}
	/**
	 * 按条件分页查询(物业报修单)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PropertyRepair> selectPropertyRepairByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPropertyRepairCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PropertyRepair> resMap= sqlSession.selectList("propertyRepairBase.select_propertyRepair_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(物业报修单)信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertyRepair selectPropertyRepairBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("propertyRepairBase.select_propertyRepair_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(物业报修单)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPropertyRepairCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("propertyRepairBase.select_propertyRepair_count", paramMap);
	}
	/**
	 * 往(物业报修单)新增一条记录
	 * @param propertyRepair
	 * @return
	 */
	@Override
	public int insertPropertyRepair(PropertyRepair propertyRepair){
		return sqlSession.insert("propertyRepairBase.insert_propertyRepair",propertyRepair);
	}
	/**
	 * 批量新增(物业报修单)信息
	 * @param propertyRepairList
	 * @return
	 */
	@Override
	public int insertPropertyRepairBatch(List<PropertyRepair> propertyRepairList) {
		return sqlSession.insert("propertyRepairBase.insert_propertyRepair_Batch",propertyRepairList);
	}
	
	/**
	 * 更新(物业报修单)信息
	 * @param propertyRepair
	 * @return
	 */
	@Override
	public int updatePropertyRepair(PropertyRepair propertyRepair){
		return sqlSession.update("propertyRepairBase.update_propertyRepair", propertyRepair);
	}
	/**
	 * 批量更新(物业报修单)信息
	 * @param propertyRepairList
	 * @return
	 */
	@Override
	public int updatePropertyRepairBatch(List<PropertyRepair> propertyRepairList) {
		return sqlSession.update("propertyRepairBase.update_propertyRepair_Batch", propertyRepairList);
	}
	
	/**
	 * 根据序列号删除(物业报修单)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePropertyRepairLogic(java.math.BigInteger id){
		PropertyRepair propertyRepair = new PropertyRepair();
		propertyRepair.setId(id);
		propertyRepair.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("propertyRepairBase.delete_propertyRepair_Logic",propertyRepair);
	}
	
	/**
	 * 根据Id 批量删除(物业报修单)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePropertyRepairLogicBatch(List<java.math.BigInteger> idList) {
		List<PropertyRepair> delList = new java.util.ArrayList<PropertyRepair>();
		for(java.math.BigInteger id:idList){
			PropertyRepair propertyRepair = new PropertyRepair();
			propertyRepair.setId(id);
			propertyRepair.setSys0DelState(RecordState_DELETED);
			delList.add(propertyRepair);
		}
		return sqlSession.update("propertyRepairBase.delete_propertyRepair_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(物业报修单)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertyRepair(java.math.BigInteger id){
//		return sqlSession.delete("propertyRepairBase.delete_propertyRepair", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业报修单)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertyRepairBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("propertyRepairBase.delete_propertyRepair_Batch", idList);
//	}
	
	
}
