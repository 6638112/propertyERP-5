package com.cnfantasia.server.domainbase.bindPhoneLog.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.bindPhoneLog.entity.BindPhoneLog;

/**
 * 描述:(绑定手机记录表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class BindPhoneLogBaseDao extends AbstractBaseDao implements IBindPhoneLogBaseDao{
	/**
	 * 根据条件查询(绑定手机记录表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<BindPhoneLog> selectBindPhoneLogByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("bindPhoneLogBase.select_bindPhoneLog",paramMap);
	}
	/**
	 * 按条件分页查询(绑定手机记录表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<BindPhoneLog> selectBindPhoneLogByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectBindPhoneLogCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<BindPhoneLog> resMap= sqlSession.selectList("bindPhoneLogBase.select_bindPhoneLog_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(绑定手机记录表)信息
	 * @param id
	 * @return
	 */
	@Override
	public BindPhoneLog selectBindPhoneLogBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("bindPhoneLogBase.select_bindPhoneLog_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(绑定手机记录表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectBindPhoneLogCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("bindPhoneLogBase.select_bindPhoneLog_count", paramMap);
	}
	/**
	 * 往(绑定手机记录表)新增一条记录
	 * @param bindPhoneLog
	 * @return
	 */
	@Override
	public int insertBindPhoneLog(BindPhoneLog bindPhoneLog){
		return sqlSession.insert("bindPhoneLogBase.insert_bindPhoneLog",bindPhoneLog);
	}
	/**
	 * 批量新增(绑定手机记录表)信息
	 * @param bindPhoneLogList
	 * @return
	 */
	@Override
	public int insertBindPhoneLogBatch(List<BindPhoneLog> bindPhoneLogList) {
		return sqlSession.insert("bindPhoneLogBase.insert_bindPhoneLog_Batch",bindPhoneLogList);
	}
	
	/**
	 * 更新(绑定手机记录表)信息
	 * @param bindPhoneLog
	 * @return
	 */
	@Override
	public int updateBindPhoneLog(BindPhoneLog bindPhoneLog){
		return sqlSession.update("bindPhoneLogBase.update_bindPhoneLog", bindPhoneLog);
	}
	/**
	 * 批量更新(绑定手机记录表)信息
	 * @param bindPhoneLogList
	 * @return
	 */
	@Override
	public int updateBindPhoneLogBatch(List<BindPhoneLog> bindPhoneLogList) {
		return sqlSession.update("bindPhoneLogBase.update_bindPhoneLog_Batch", bindPhoneLogList);
	}
	
	/**
	 * 根据序列号删除(绑定手机记录表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteBindPhoneLogLogic(java.math.BigInteger id){
		BindPhoneLog bindPhoneLog = new BindPhoneLog();
		bindPhoneLog.setId(id);
		bindPhoneLog.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("bindPhoneLogBase.delete_bindPhoneLog_Logic",bindPhoneLog);
	}
	
	/**
	 * 根据Id 批量删除(绑定手机记录表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteBindPhoneLogLogicBatch(List<java.math.BigInteger> idList) {
		List<BindPhoneLog> delList = new java.util.ArrayList<BindPhoneLog>();
		for(java.math.BigInteger id:idList){
			BindPhoneLog bindPhoneLog = new BindPhoneLog();
			bindPhoneLog.setId(id);
			bindPhoneLog.setSys0DelState(RecordState_DELETED);
			delList.add(bindPhoneLog);
		}
		return sqlSession.update("bindPhoneLogBase.delete_bindPhoneLog_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(绑定手机记录表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteBindPhoneLog(java.math.BigInteger id){
//		return sqlSession.delete("bindPhoneLogBase.delete_bindPhoneLog", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(绑定手机记录表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteBindPhoneLogBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("bindPhoneLogBase.delete_bindPhoneLog_Batch", idList);
//	}
	
	
}
