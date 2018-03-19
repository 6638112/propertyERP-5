package com.cnfantasia.server.domainbase.propertyProprietor.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyProprietor.entity.PropertyProprietor;

/**
 * 描述:(业主信息表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PropertyProprietorBaseDao extends AbstractBaseDao implements IPropertyProprietorBaseDao{
	/**
	 * 根据条件查询(业主信息表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PropertyProprietor> selectPropertyProprietorByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("propertyProprietorBase.select_propertyProprietor",paramMap);
	}
	/**
	 * 按条件分页查询(业主信息表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PropertyProprietor> selectPropertyProprietorByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPropertyProprietorCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PropertyProprietor> resMap= sqlSession.selectList("propertyProprietorBase.select_propertyProprietor_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(业主信息表)信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertyProprietor selectPropertyProprietorBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("propertyProprietorBase.select_propertyProprietor_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(业主信息表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPropertyProprietorCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("propertyProprietorBase.select_propertyProprietor_count", paramMap);
	}
	/**
	 * 往(业主信息表)新增一条记录
	 * @param propertyProprietor
	 * @return
	 */
	@Override
	public int insertPropertyProprietor(PropertyProprietor propertyProprietor){
		return sqlSession.insert("propertyProprietorBase.insert_propertyProprietor",propertyProprietor);
	}
	/**
	 * 批量新增(业主信息表)信息
	 * @param propertyProprietorList
	 * @return
	 */
	@Override
	public int insertPropertyProprietorBatch(List<PropertyProprietor> propertyProprietorList) {
		return sqlSession.insert("propertyProprietorBase.insert_propertyProprietor_Batch",propertyProprietorList);
	}
	
	/**
	 * 更新(业主信息表)信息
	 * @param propertyProprietor
	 * @return
	 */
	@Override
	public int updatePropertyProprietor(PropertyProprietor propertyProprietor){
		return sqlSession.update("propertyProprietorBase.update_propertyProprietor", propertyProprietor);
	}
	/**
	 * 批量更新(业主信息表)信息
	 * @param propertyProprietorList
	 * @return
	 */
	@Override
	public int updatePropertyProprietorBatch(List<PropertyProprietor> propertyProprietorList) {
		return sqlSession.update("propertyProprietorBase.update_propertyProprietor_Batch", propertyProprietorList);
	}
	
	/**
	 * 根据序列号删除(业主信息表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePropertyProprietorLogic(java.math.BigInteger id){
		PropertyProprietor propertyProprietor = new PropertyProprietor();
		propertyProprietor.setId(id);
		propertyProprietor.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("propertyProprietorBase.delete_propertyProprietor_Logic",propertyProprietor);
	}
	
	/**
	 * 根据Id 批量删除(业主信息表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePropertyProprietorLogicBatch(List<java.math.BigInteger> idList) {
		List<PropertyProprietor> delList = new java.util.ArrayList<PropertyProprietor>();
		for(java.math.BigInteger id:idList){
			PropertyProprietor propertyProprietor = new PropertyProprietor();
			propertyProprietor.setId(id);
			propertyProprietor.setSys0DelState(RecordState_DELETED);
			delList.add(propertyProprietor);
		}
		return sqlSession.update("propertyProprietorBase.delete_propertyProprietor_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(业主信息表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertyProprietor(java.math.BigInteger id){
//		return sqlSession.delete("propertyProprietorBase.delete_propertyProprietor", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(业主信息表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertyProprietorBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("propertyProprietorBase.delete_propertyProprietor_Batch", idList);
//	}
	
	
}
