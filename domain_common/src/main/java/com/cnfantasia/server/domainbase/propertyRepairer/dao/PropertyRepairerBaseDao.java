package com.cnfantasia.server.domainbase.propertyRepairer.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyRepairer.entity.PropertyRepairer;

/**
 * 描述:(管理处维修工) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PropertyRepairerBaseDao extends AbstractBaseDao implements IPropertyRepairerBaseDao{
	/**
	 * 根据条件查询(管理处维修工)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PropertyRepairer> selectPropertyRepairerByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("propertyRepairerBase.select_propertyRepairer",paramMap);
	}
	/**
	 * 按条件分页查询(管理处维修工)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PropertyRepairer> selectPropertyRepairerByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPropertyRepairerCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PropertyRepairer> resMap= sqlSession.selectList("propertyRepairerBase.select_propertyRepairer_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(管理处维修工)信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertyRepairer selectPropertyRepairerBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("propertyRepairerBase.select_propertyRepairer_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(管理处维修工)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPropertyRepairerCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("propertyRepairerBase.select_propertyRepairer_count", paramMap);
	}
	/**
	 * 往(管理处维修工)新增一条记录
	 * @param propertyRepairer
	 * @return
	 */
	@Override
	public int insertPropertyRepairer(PropertyRepairer propertyRepairer){
		return sqlSession.insert("propertyRepairerBase.insert_propertyRepairer",propertyRepairer);
	}
	/**
	 * 批量新增(管理处维修工)信息
	 * @param propertyRepairerList
	 * @return
	 */
	@Override
	public int insertPropertyRepairerBatch(List<PropertyRepairer> propertyRepairerList) {
		return sqlSession.insert("propertyRepairerBase.insert_propertyRepairer_Batch",propertyRepairerList);
	}
	
	/**
	 * 更新(管理处维修工)信息
	 * @param propertyRepairer
	 * @return
	 */
	@Override
	public int updatePropertyRepairer(PropertyRepairer propertyRepairer){
		return sqlSession.update("propertyRepairerBase.update_propertyRepairer", propertyRepairer);
	}
	/**
	 * 批量更新(管理处维修工)信息
	 * @param propertyRepairerList
	 * @return
	 */
	@Override
	public int updatePropertyRepairerBatch(List<PropertyRepairer> propertyRepairerList) {
		return sqlSession.update("propertyRepairerBase.update_propertyRepairer_Batch", propertyRepairerList);
	}
	
	/**
	 * 根据序列号删除(管理处维修工)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePropertyRepairerLogic(java.math.BigInteger id){
		PropertyRepairer propertyRepairer = new PropertyRepairer();
		propertyRepairer.setId(id);
		propertyRepairer.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("propertyRepairerBase.delete_propertyRepairer_Logic",propertyRepairer);
	}
	
	/**
	 * 根据Id 批量删除(管理处维修工)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePropertyRepairerLogicBatch(List<java.math.BigInteger> idList) {
		List<PropertyRepairer> delList = new java.util.ArrayList<PropertyRepairer>();
		for(java.math.BigInteger id:idList){
			PropertyRepairer propertyRepairer = new PropertyRepairer();
			propertyRepairer.setId(id);
			propertyRepairer.setSys0DelState(RecordState_DELETED);
			delList.add(propertyRepairer);
		}
		return sqlSession.update("propertyRepairerBase.delete_propertyRepairer_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(管理处维修工)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertyRepairer(java.math.BigInteger id){
//		return sqlSession.delete("propertyRepairerBase.delete_propertyRepairer", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(管理处维修工)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertyRepairerBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("propertyRepairerBase.delete_propertyRepairer_Batch", idList);
//	}
	
	
}
