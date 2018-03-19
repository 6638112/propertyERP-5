package com.cnfantasia.server.domainbase.appVersion.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.appVersion.entity.AppVersion;

/**
 * 描述:(应用版本信息) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class AppVersionBaseDao extends AbstractBaseDao implements IAppVersionBaseDao{
	/**
	 * 根据条件查询(应用版本信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<AppVersion> selectAppVersionByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("appVersionBase.select_appVersion",paramMap);
	}
	/**
	 * 按条件分页查询(应用版本信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<AppVersion> selectAppVersionByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectAppVersionCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<AppVersion> resMap= sqlSession.selectList("appVersionBase.select_appVersion_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(应用版本信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public AppVersion selectAppVersionBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("appVersionBase.select_appVersion_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(应用版本信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectAppVersionCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("appVersionBase.select_appVersion_count", paramMap);
	}
	/**
	 * 往(应用版本信息)新增一条记录
	 * @param appVersion
	 * @return
	 */
	@Override
	public int insertAppVersion(AppVersion appVersion){
		return sqlSession.insert("appVersionBase.insert_appVersion",appVersion);
	}
	/**
	 * 批量新增(应用版本信息)信息
	 * @param appVersionList
	 * @return
	 */
	@Override
	public int insertAppVersionBatch(List<AppVersion> appVersionList) {
		return sqlSession.insert("appVersionBase.insert_appVersion_Batch",appVersionList);
	}
	
	/**
	 * 更新(应用版本信息)信息
	 * @param appVersion
	 * @return
	 */
	@Override
	public int updateAppVersion(AppVersion appVersion){
		return sqlSession.update("appVersionBase.update_appVersion", appVersion);
	}
	/**
	 * 批量更新(应用版本信息)信息
	 * @param appVersionList
	 * @return
	 */
	@Override
	public int updateAppVersionBatch(List<AppVersion> appVersionList) {
		return sqlSession.update("appVersionBase.update_appVersion_Batch", appVersionList);
	}
	
	/**
	 * 根据序列号删除(应用版本信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteAppVersionLogic(java.math.BigInteger id){
		AppVersion appVersion = new AppVersion();
		appVersion.setId(id);
		appVersion.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("appVersionBase.delete_appVersion_Logic",appVersion);
	}
	
	/**
	 * 根据Id 批量删除(应用版本信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteAppVersionLogicBatch(List<java.math.BigInteger> idList) {
		List<AppVersion> delList = new java.util.ArrayList<AppVersion>();
		for(java.math.BigInteger id:idList){
			AppVersion appVersion = new AppVersion();
			appVersion.setId(id);
			appVersion.setSys0DelState(RecordState_DELETED);
			delList.add(appVersion);
		}
		return sqlSession.update("appVersionBase.delete_appVersion_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(应用版本信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteAppVersion(java.math.BigInteger id){
//		return sqlSession.delete("appVersionBase.delete_appVersion", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(应用版本信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteAppVersionBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("appVersionBase.delete_appVersion_Batch", idList);
//	}
	
	
}
