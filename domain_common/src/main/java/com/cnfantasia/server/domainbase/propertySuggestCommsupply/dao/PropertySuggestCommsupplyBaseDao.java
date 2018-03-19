package com.cnfantasia.server.domainbase.propertySuggestCommsupply.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertySuggestCommsupply.entity.PropertySuggestCommsupply;

/**
 * 描述:(物业推荐信息表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PropertySuggestCommsupplyBaseDao extends AbstractBaseDao implements IPropertySuggestCommsupplyBaseDao{
	/**
	 * 根据条件查询(物业推荐信息表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PropertySuggestCommsupply> selectPropertySuggestCommsupplyByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("propertySuggestCommsupplyBase.select_propertySuggestCommsupply",paramMap);
	}
	/**
	 * 按条件分页查询(物业推荐信息表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PropertySuggestCommsupply> selectPropertySuggestCommsupplyByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPropertySuggestCommsupplyCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PropertySuggestCommsupply> resMap= sqlSession.selectList("propertySuggestCommsupplyBase.select_propertySuggestCommsupply_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(物业推荐信息表)信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertySuggestCommsupply selectPropertySuggestCommsupplyBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("propertySuggestCommsupplyBase.select_propertySuggestCommsupply_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(物业推荐信息表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPropertySuggestCommsupplyCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("propertySuggestCommsupplyBase.select_propertySuggestCommsupply_count", paramMap);
	}
	/**
	 * 往(物业推荐信息表)新增一条记录
	 * @param propertySuggestCommsupply
	 * @return
	 */
	@Override
	public int insertPropertySuggestCommsupply(PropertySuggestCommsupply propertySuggestCommsupply){
		return sqlSession.insert("propertySuggestCommsupplyBase.insert_propertySuggestCommsupply",propertySuggestCommsupply);
	}
	/**
	 * 批量新增(物业推荐信息表)信息
	 * @param propertySuggestCommsupplyList
	 * @return
	 */
	@Override
	public int insertPropertySuggestCommsupplyBatch(List<PropertySuggestCommsupply> propertySuggestCommsupplyList) {
		return sqlSession.insert("propertySuggestCommsupplyBase.insert_propertySuggestCommsupply_Batch",propertySuggestCommsupplyList);
	}
	
	/**
	 * 更新(物业推荐信息表)信息
	 * @param propertySuggestCommsupply
	 * @return
	 */
	@Override
	public int updatePropertySuggestCommsupply(PropertySuggestCommsupply propertySuggestCommsupply){
		return sqlSession.update("propertySuggestCommsupplyBase.update_propertySuggestCommsupply", propertySuggestCommsupply);
	}
	/**
	 * 批量更新(物业推荐信息表)信息
	 * @param propertySuggestCommsupplyList
	 * @return
	 */
	@Override
	public int updatePropertySuggestCommsupplyBatch(List<PropertySuggestCommsupply> propertySuggestCommsupplyList) {
		return sqlSession.update("propertySuggestCommsupplyBase.update_propertySuggestCommsupply_Batch", propertySuggestCommsupplyList);
	}
	
	/**
	 * 根据序列号删除(物业推荐信息表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePropertySuggestCommsupplyLogic(java.math.BigInteger id){
		PropertySuggestCommsupply propertySuggestCommsupply = new PropertySuggestCommsupply();
		propertySuggestCommsupply.setId(id);
		propertySuggestCommsupply.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("propertySuggestCommsupplyBase.delete_propertySuggestCommsupply_Logic",propertySuggestCommsupply);
	}
	
	/**
	 * 根据Id 批量删除(物业推荐信息表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePropertySuggestCommsupplyLogicBatch(List<java.math.BigInteger> idList) {
		List<PropertySuggestCommsupply> delList = new java.util.ArrayList<PropertySuggestCommsupply>();
		for(java.math.BigInteger id:idList){
			PropertySuggestCommsupply propertySuggestCommsupply = new PropertySuggestCommsupply();
			propertySuggestCommsupply.setId(id);
			propertySuggestCommsupply.setSys0DelState(RecordState_DELETED);
			delList.add(propertySuggestCommsupply);
		}
		return sqlSession.update("propertySuggestCommsupplyBase.delete_propertySuggestCommsupply_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(物业推荐信息表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertySuggestCommsupply(java.math.BigInteger id){
//		return sqlSession.delete("propertySuggestCommsupplyBase.delete_propertySuggestCommsupply", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业推荐信息表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertySuggestCommsupplyBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("propertySuggestCommsupplyBase.delete_propertySuggestCommsupply_Batch", idList);
//	}
	
	
}
