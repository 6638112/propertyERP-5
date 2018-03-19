package com.cnfantasia.server.domainbase.appBaseInfo.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.appBaseInfo.entity.AppBaseInfo;

/**
 * 描述:(应用信息) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class AppBaseInfoBaseDao extends AbstractBaseDao implements IAppBaseInfoBaseDao{
	/**
	 * 根据条件查询(应用信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<AppBaseInfo> selectAppBaseInfoByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("appBaseInfoBase.select_appBaseInfo",paramMap);
	}
	/**
	 * 按条件分页查询(应用信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<AppBaseInfo> selectAppBaseInfoByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectAppBaseInfoCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<AppBaseInfo> resMap= sqlSession.selectList("appBaseInfoBase.select_appBaseInfo_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(应用信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public AppBaseInfo selectAppBaseInfoBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("appBaseInfoBase.select_appBaseInfo_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(应用信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectAppBaseInfoCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("appBaseInfoBase.select_appBaseInfo_count", paramMap);
	}
	/**
	 * 往(应用信息)新增一条记录
	 * @param appBaseInfo
	 * @return
	 */
	@Override
	public int insertAppBaseInfo(AppBaseInfo appBaseInfo){
		return sqlSession.insert("appBaseInfoBase.insert_appBaseInfo",appBaseInfo);
	}
	/**
	 * 批量新增(应用信息)信息
	 * @param appBaseInfoList
	 * @return
	 */
	@Override
	public int insertAppBaseInfoBatch(List<AppBaseInfo> appBaseInfoList) {
		return sqlSession.insert("appBaseInfoBase.insert_appBaseInfo_Batch",appBaseInfoList);
	}
	
	/**
	 * 更新(应用信息)信息
	 * @param appBaseInfo
	 * @return
	 */
	@Override
	public int updateAppBaseInfo(AppBaseInfo appBaseInfo){
		return sqlSession.update("appBaseInfoBase.update_appBaseInfo", appBaseInfo);
	}
	/**
	 * 批量更新(应用信息)信息
	 * @param appBaseInfoList
	 * @return
	 */
	@Override
	public int updateAppBaseInfoBatch(List<AppBaseInfo> appBaseInfoList) {
		return sqlSession.update("appBaseInfoBase.update_appBaseInfo_Batch", appBaseInfoList);
	}
	
	/**
	 * 根据序列号删除(应用信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteAppBaseInfoLogic(java.math.BigInteger id){
		AppBaseInfo appBaseInfo = new AppBaseInfo();
		appBaseInfo.setId(id);
		appBaseInfo.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("appBaseInfoBase.delete_appBaseInfo_Logic",appBaseInfo);
	}
	
	/**
	 * 根据Id 批量删除(应用信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteAppBaseInfoLogicBatch(List<java.math.BigInteger> idList) {
		List<AppBaseInfo> delList = new java.util.ArrayList<AppBaseInfo>();
		for(java.math.BigInteger id:idList){
			AppBaseInfo appBaseInfo = new AppBaseInfo();
			appBaseInfo.setId(id);
			appBaseInfo.setSys0DelState(RecordState_DELETED);
			delList.add(appBaseInfo);
		}
		return sqlSession.update("appBaseInfoBase.delete_appBaseInfo_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(应用信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteAppBaseInfo(java.math.BigInteger id){
//		return sqlSession.delete("appBaseInfoBase.delete_appBaseInfo", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(应用信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteAppBaseInfoBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("appBaseInfoBase.delete_appBaseInfo_Batch", idList);
//	}
	
	
}
