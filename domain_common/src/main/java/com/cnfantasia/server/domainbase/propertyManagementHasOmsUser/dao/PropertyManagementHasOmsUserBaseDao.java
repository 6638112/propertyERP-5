package com.cnfantasia.server.domainbase.propertyManagementHasOmsUser.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyManagementHasOmsUser.entity.PropertyManagementHasOmsUser;

/**
 * 描述:(物业管理与后账号关系) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PropertyManagementHasOmsUserBaseDao extends AbstractBaseDao implements IPropertyManagementHasOmsUserBaseDao{
	/**
	 * 根据条件查询(物业管理与后账号关系)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PropertyManagementHasOmsUser> selectPropertyManagementHasOmsUserByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("propertyManagementHasOmsUserBase.select_propertyManagementHasOmsUser",paramMap);
	}
	/**
	 * 按条件分页查询(物业管理与后账号关系)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PropertyManagementHasOmsUser> selectPropertyManagementHasOmsUserByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPropertyManagementHasOmsUserCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PropertyManagementHasOmsUser> resMap= sqlSession.selectList("propertyManagementHasOmsUserBase.select_propertyManagementHasOmsUser_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(物业管理与后账号关系)信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertyManagementHasOmsUser selectPropertyManagementHasOmsUserBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("propertyManagementHasOmsUserBase.select_propertyManagementHasOmsUser_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(物业管理与后账号关系)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPropertyManagementHasOmsUserCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("propertyManagementHasOmsUserBase.select_propertyManagementHasOmsUser_count", paramMap);
	}
	/**
	 * 往(物业管理与后账号关系)新增一条记录
	 * @param propertyManagementHasOmsUser
	 * @return
	 */
	@Override
	public int insertPropertyManagementHasOmsUser(PropertyManagementHasOmsUser propertyManagementHasOmsUser){
		return sqlSession.insert("propertyManagementHasOmsUserBase.insert_propertyManagementHasOmsUser",propertyManagementHasOmsUser);
	}
	/**
	 * 批量新增(物业管理与后账号关系)信息
	 * @param propertyManagementHasOmsUserList
	 * @return
	 */
	@Override
	public int insertPropertyManagementHasOmsUserBatch(List<PropertyManagementHasOmsUser> propertyManagementHasOmsUserList) {
		return sqlSession.insert("propertyManagementHasOmsUserBase.insert_propertyManagementHasOmsUser_Batch",propertyManagementHasOmsUserList);
	}
	
	/**
	 * 更新(物业管理与后账号关系)信息
	 * @param propertyManagementHasOmsUser
	 * @return
	 */
	@Override
	public int updatePropertyManagementHasOmsUser(PropertyManagementHasOmsUser propertyManagementHasOmsUser){
		return sqlSession.update("propertyManagementHasOmsUserBase.update_propertyManagementHasOmsUser", propertyManagementHasOmsUser);
	}
	/**
	 * 批量更新(物业管理与后账号关系)信息
	 * @param propertyManagementHasOmsUserList
	 * @return
	 */
	@Override
	public int updatePropertyManagementHasOmsUserBatch(List<PropertyManagementHasOmsUser> propertyManagementHasOmsUserList) {
		return sqlSession.update("propertyManagementHasOmsUserBase.update_propertyManagementHasOmsUser_Batch", propertyManagementHasOmsUserList);
	}
	
	/**
	 * 根据序列号删除(物业管理与后账号关系)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePropertyManagementHasOmsUserLogic(java.math.BigInteger id){
		PropertyManagementHasOmsUser propertyManagementHasOmsUser = new PropertyManagementHasOmsUser();
		propertyManagementHasOmsUser.setId(id);
		propertyManagementHasOmsUser.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("propertyManagementHasOmsUserBase.delete_propertyManagementHasOmsUser_Logic",propertyManagementHasOmsUser);
	}
	
	/**
	 * 根据Id 批量删除(物业管理与后账号关系)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePropertyManagementHasOmsUserLogicBatch(List<java.math.BigInteger> idList) {
		List<PropertyManagementHasOmsUser> delList = new java.util.ArrayList<PropertyManagementHasOmsUser>();
		for(java.math.BigInteger id:idList){
			PropertyManagementHasOmsUser propertyManagementHasOmsUser = new PropertyManagementHasOmsUser();
			propertyManagementHasOmsUser.setId(id);
			propertyManagementHasOmsUser.setSys0DelState(RecordState_DELETED);
			delList.add(propertyManagementHasOmsUser);
		}
		return sqlSession.update("propertyManagementHasOmsUserBase.delete_propertyManagementHasOmsUser_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(物业管理与后账号关系)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertyManagementHasOmsUser(java.math.BigInteger id){
//		return sqlSession.delete("propertyManagementHasOmsUserBase.delete_propertyManagementHasOmsUser", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业管理与后账号关系)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertyManagementHasOmsUserBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("propertyManagementHasOmsUserBase.delete_propertyManagementHasOmsUser_Batch", idList);
//	}
	
	
}
