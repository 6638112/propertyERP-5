package com.cnfantasia.server.domainbase.propertyCompany.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyCompany.entity.PropertyCompany;

/**
 * 描述:(物业公司) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PropertyCompanyBaseDao extends AbstractBaseDao implements IPropertyCompanyBaseDao{
	/**
	 * 根据条件查询(物业公司)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PropertyCompany> selectPropertyCompanyByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("propertyCompanyBase.select_propertyCompany",paramMap);
	}
	/**
	 * 按条件分页查询(物业公司)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PropertyCompany> selectPropertyCompanyByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPropertyCompanyCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PropertyCompany> resMap= sqlSession.selectList("propertyCompanyBase.select_propertyCompany_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(物业公司)信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertyCompany selectPropertyCompanyBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("propertyCompanyBase.select_propertyCompany_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(物业公司)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPropertyCompanyCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("propertyCompanyBase.select_propertyCompany_count", paramMap);
	}
	/**
	 * 往(物业公司)新增一条记录
	 * @param propertyCompany
	 * @return
	 */
	@Override
	public int insertPropertyCompany(PropertyCompany propertyCompany){
		return sqlSession.insert("propertyCompanyBase.insert_propertyCompany",propertyCompany);
	}
	/**
	 * 批量新增(物业公司)信息
	 * @param propertyCompanyList
	 * @return
	 */
	@Override
	public int insertPropertyCompanyBatch(List<PropertyCompany> propertyCompanyList) {
		return sqlSession.insert("propertyCompanyBase.insert_propertyCompany_Batch",propertyCompanyList);
	}
	
	/**
	 * 更新(物业公司)信息
	 * @param propertyCompany
	 * @return
	 */
	@Override
	public int updatePropertyCompany(PropertyCompany propertyCompany){
		return sqlSession.update("propertyCompanyBase.update_propertyCompany", propertyCompany);
	}
	/**
	 * 批量更新(物业公司)信息
	 * @param propertyCompanyList
	 * @return
	 */
	@Override
	public int updatePropertyCompanyBatch(List<PropertyCompany> propertyCompanyList) {
		return sqlSession.update("propertyCompanyBase.update_propertyCompany_Batch", propertyCompanyList);
	}
	
	/**
	 * 根据序列号删除(物业公司)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePropertyCompanyLogic(java.math.BigInteger id){
		PropertyCompany propertyCompany = new PropertyCompany();
		propertyCompany.setId(id);
		propertyCompany.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("propertyCompanyBase.delete_propertyCompany_Logic",propertyCompany);
	}
	
	/**
	 * 根据Id 批量删除(物业公司)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePropertyCompanyLogicBatch(List<java.math.BigInteger> idList) {
		List<PropertyCompany> delList = new java.util.ArrayList<PropertyCompany>();
		for(java.math.BigInteger id:idList){
			PropertyCompany propertyCompany = new PropertyCompany();
			propertyCompany.setId(id);
			propertyCompany.setSys0DelState(RecordState_DELETED);
			delList.add(propertyCompany);
		}
		return sqlSession.update("propertyCompanyBase.delete_propertyCompany_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(物业公司)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertyCompany(java.math.BigInteger id){
//		return sqlSession.delete("propertyCompanyBase.delete_propertyCompany", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业公司)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertyCompanyBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("propertyCompanyBase.delete_propertyCompany_Batch", idList);
//	}
	
	
}
