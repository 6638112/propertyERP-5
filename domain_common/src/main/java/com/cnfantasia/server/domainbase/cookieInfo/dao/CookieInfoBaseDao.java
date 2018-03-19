package com.cnfantasia.server.domainbase.cookieInfo.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.cookieInfo.entity.CookieInfo;

/**
 * 描述:(记录用户的Cookie) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class CookieInfoBaseDao extends AbstractBaseDao implements ICookieInfoBaseDao{
	/**
	 * 根据条件查询(记录用户的Cookie)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CookieInfo> selectCookieInfoByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("cookieInfoBase.select_cookieInfo",paramMap);
	}
	/**
	 * 按条件分页查询(记录用户的Cookie)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<CookieInfo> selectCookieInfoByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectCookieInfoCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<CookieInfo> resMap= sqlSession.selectList("cookieInfoBase.select_cookieInfo_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(记录用户的Cookie)信息
	 * @param id
	 * @return
	 */
	@Override
	public CookieInfo selectCookieInfoBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("cookieInfoBase.select_cookieInfo_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(记录用户的Cookie)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectCookieInfoCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("cookieInfoBase.select_cookieInfo_count", paramMap);
	}
	/**
	 * 往(记录用户的Cookie)新增一条记录
	 * @param cookieInfo
	 * @return
	 */
	@Override
	public int insertCookieInfo(CookieInfo cookieInfo){
		return sqlSession.insert("cookieInfoBase.insert_cookieInfo",cookieInfo);
	}
	/**
	 * 批量新增(记录用户的Cookie)信息
	 * @param cookieInfoList
	 * @return
	 */
	@Override
	public int insertCookieInfoBatch(List<CookieInfo> cookieInfoList) {
		return sqlSession.insert("cookieInfoBase.insert_cookieInfo_Batch",cookieInfoList);
	}
	
	/**
	 * 更新(记录用户的Cookie)信息
	 * @param cookieInfo
	 * @return
	 */
	@Override
	public int updateCookieInfo(CookieInfo cookieInfo){
		return sqlSession.update("cookieInfoBase.update_cookieInfo", cookieInfo);
	}
	/**
	 * 批量更新(记录用户的Cookie)信息
	 * @param cookieInfoList
	 * @return
	 */
	@Override
	public int updateCookieInfoBatch(List<CookieInfo> cookieInfoList) {
		return sqlSession.update("cookieInfoBase.update_cookieInfo_Batch", cookieInfoList);
	}
	
	/**
	 * 根据序列号删除(记录用户的Cookie)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteCookieInfoLogic(java.math.BigInteger id){
		CookieInfo cookieInfo = new CookieInfo();
		cookieInfo.setId(id);
		cookieInfo.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("cookieInfoBase.delete_cookieInfo_Logic",cookieInfo);
	}
	
	/**
	 * 根据Id 批量删除(记录用户的Cookie)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteCookieInfoLogicBatch(List<java.math.BigInteger> idList) {
		List<CookieInfo> delList = new java.util.ArrayList<CookieInfo>();
		for(java.math.BigInteger id:idList){
			CookieInfo cookieInfo = new CookieInfo();
			cookieInfo.setId(id);
			cookieInfo.setSys0DelState(RecordState_DELETED);
			delList.add(cookieInfo);
		}
		return sqlSession.update("cookieInfoBase.delete_cookieInfo_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(记录用户的Cookie)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteCookieInfo(java.math.BigInteger id){
//		return sqlSession.delete("cookieInfoBase.delete_cookieInfo", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(记录用户的Cookie)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteCookieInfoBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("cookieInfoBase.delete_cookieInfo_Batch", idList);
//	}
	
	
}
