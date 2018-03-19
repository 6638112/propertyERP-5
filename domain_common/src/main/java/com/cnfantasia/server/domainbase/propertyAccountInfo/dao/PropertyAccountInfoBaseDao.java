package com.cnfantasia.server.domainbase.propertyAccountInfo.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.propertyAccountInfo.entity.PropertyAccountInfo;

/**
 * 描述:(用户物业账户信息) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PropertyAccountInfoBaseDao extends AbstractBaseDao implements IPropertyAccountInfoBaseDao{
	/**
	 * 根据条件查询(用户物业账户信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PropertyAccountInfo> selectPropertyAccountInfoByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("propertyAccountInfoBase.select_propertyAccountInfo",paramMap);
	}
	/**
	 * 按条件分页查询(用户物业账户信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PropertyAccountInfo> selectPropertyAccountInfoByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPropertyAccountInfoCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PropertyAccountInfo> resMap= sqlSession.selectList("propertyAccountInfoBase.select_propertyAccountInfo_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(用户物业账户信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public PropertyAccountInfo selectPropertyAccountInfoBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("propertyAccountInfoBase.select_propertyAccountInfo_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(用户物业账户信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPropertyAccountInfoCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("propertyAccountInfoBase.select_propertyAccountInfo_count", paramMap);
	}
	/**
	 * 往(用户物业账户信息)新增一条记录
	 * @param propertyAccountInfo
	 * @return
	 */
	@Override
	public int insertPropertyAccountInfo(PropertyAccountInfo propertyAccountInfo){
		return sqlSession.insert("propertyAccountInfoBase.insert_propertyAccountInfo",propertyAccountInfo);
	}
	/**
	 * 批量新增(用户物业账户信息)信息
	 * @param propertyAccountInfoList
	 * @return
	 */
	@Override
	public int insertPropertyAccountInfoBatch(List<PropertyAccountInfo> propertyAccountInfoList) {
		return sqlSession.insert("propertyAccountInfoBase.insert_propertyAccountInfo_Batch",propertyAccountInfoList);
	}
	
	/**
	 * 更新(用户物业账户信息)信息
	 * @param propertyAccountInfo
	 * @return
	 */
	@Override
	public int updatePropertyAccountInfo(PropertyAccountInfo propertyAccountInfo){
		return sqlSession.update("propertyAccountInfoBase.update_propertyAccountInfo", propertyAccountInfo);
	}
	/**
	 * 批量更新(用户物业账户信息)信息
	 * @param propertyAccountInfoList
	 * @return
	 */
	@Override
	public int updatePropertyAccountInfoBatch(List<PropertyAccountInfo> propertyAccountInfoList) {
		return sqlSession.update("propertyAccountInfoBase.update_propertyAccountInfo_Batch", propertyAccountInfoList);
	}
	
	/**
	 * 根据序列号删除(用户物业账户信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePropertyAccountInfoLogic(java.math.BigInteger id){
		PropertyAccountInfo propertyAccountInfo = new PropertyAccountInfo();
		propertyAccountInfo.setId(id);
		propertyAccountInfo.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("propertyAccountInfoBase.delete_propertyAccountInfo_Logic",propertyAccountInfo);
	}
	
	/**
	 * 根据Id 批量删除(用户物业账户信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePropertyAccountInfoLogicBatch(List<java.math.BigInteger> idList) {
		List<PropertyAccountInfo> delList = new java.util.ArrayList<PropertyAccountInfo>();
		for(java.math.BigInteger id:idList){
			PropertyAccountInfo propertyAccountInfo = new PropertyAccountInfo();
			propertyAccountInfo.setId(id);
			propertyAccountInfo.setSys0DelState(RecordState_DELETED);
			delList.add(propertyAccountInfo);
		}
		return sqlSession.update("propertyAccountInfoBase.delete_propertyAccountInfo_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(用户物业账户信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePropertyAccountInfo(java.math.BigInteger id){
//		return sqlSession.delete("propertyAccountInfoBase.delete_propertyAccountInfo", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(用户物业账户信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePropertyAccountInfoBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("propertyAccountInfoBase.delete_propertyAccountInfo_Batch", idList);
//	}
	
	
}
