package com.cnfantasia.server.domainbase.appVersionChannel.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.appVersionChannel.entity.AppVersionChannel;

/**
 * 描述:(应用版本不同渠道信息) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class AppVersionChannelBaseDao extends AbstractBaseDao implements IAppVersionChannelBaseDao{
	/**
	 * 根据条件查询(应用版本不同渠道信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<AppVersionChannel> selectAppVersionChannelByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("appVersionChannelBase.select_appVersionChannel",paramMap);
	}
	/**
	 * 按条件分页查询(应用版本不同渠道信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<AppVersionChannel> selectAppVersionChannelByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectAppVersionChannelCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<AppVersionChannel> resMap= sqlSession.selectList("appVersionChannelBase.select_appVersionChannel_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(应用版本不同渠道信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public AppVersionChannel selectAppVersionChannelBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("appVersionChannelBase.select_appVersionChannel_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(应用版本不同渠道信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectAppVersionChannelCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("appVersionChannelBase.select_appVersionChannel_count", paramMap);
	}
	/**
	 * 往(应用版本不同渠道信息)新增一条记录
	 * @param appVersionChannel
	 * @return
	 */
	@Override
	public int insertAppVersionChannel(AppVersionChannel appVersionChannel){
		return sqlSession.insert("appVersionChannelBase.insert_appVersionChannel",appVersionChannel);
	}
	/**
	 * 批量新增(应用版本不同渠道信息)信息
	 * @param appVersionChannelList
	 * @return
	 */
	@Override
	public int insertAppVersionChannelBatch(List<AppVersionChannel> appVersionChannelList) {
		return sqlSession.insert("appVersionChannelBase.insert_appVersionChannel_Batch",appVersionChannelList);
	}
	
	/**
	 * 更新(应用版本不同渠道信息)信息
	 * @param appVersionChannel
	 * @return
	 */
	@Override
	public int updateAppVersionChannel(AppVersionChannel appVersionChannel){
		return sqlSession.update("appVersionChannelBase.update_appVersionChannel", appVersionChannel);
	}
	/**
	 * 批量更新(应用版本不同渠道信息)信息
	 * @param appVersionChannelList
	 * @return
	 */
	@Override
	public int updateAppVersionChannelBatch(List<AppVersionChannel> appVersionChannelList) {
		return sqlSession.update("appVersionChannelBase.update_appVersionChannel_Batch", appVersionChannelList);
	}
	
	/**
	 * 根据序列号删除(应用版本不同渠道信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteAppVersionChannelLogic(java.math.BigInteger id){
		AppVersionChannel appVersionChannel = new AppVersionChannel();
		appVersionChannel.setId(id);
		appVersionChannel.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("appVersionChannelBase.delete_appVersionChannel_Logic",appVersionChannel);
	}
	
	/**
	 * 根据Id 批量删除(应用版本不同渠道信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteAppVersionChannelLogicBatch(List<java.math.BigInteger> idList) {
		List<AppVersionChannel> delList = new java.util.ArrayList<AppVersionChannel>();
		for(java.math.BigInteger id:idList){
			AppVersionChannel appVersionChannel = new AppVersionChannel();
			appVersionChannel.setId(id);
			appVersionChannel.setSys0DelState(RecordState_DELETED);
			delList.add(appVersionChannel);
		}
		return sqlSession.update("appVersionChannelBase.delete_appVersionChannel_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(应用版本不同渠道信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteAppVersionChannel(java.math.BigInteger id){
//		return sqlSession.delete("appVersionChannelBase.delete_appVersionChannel", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(应用版本不同渠道信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteAppVersionChannelBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("appVersionChannelBase.delete_appVersionChannel_Batch", idList);
//	}
	
	
}
