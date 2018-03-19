package com.cnfantasia.server.domainbase.loginLog.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.loginLog.entity.LoginLog;

/**
 * 描述:(登录历史) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class LoginLogBaseDao extends AbstractBaseDao implements ILoginLogBaseDao{
	/**
	 * 根据条件查询(登录历史)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<LoginLog> selectLoginLogByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("loginLogBase.select_loginLog",paramMap);
	}
	/**
	 * 按条件分页查询(登录历史)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<LoginLog> selectLoginLogByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectLoginLogCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<LoginLog> resMap= sqlSession.selectList("loginLogBase.select_loginLog_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(登录历史)信息
	 * @param id
	 * @return
	 */
	@Override
	public LoginLog selectLoginLogBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("loginLogBase.select_loginLog_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(登录历史)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectLoginLogCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("loginLogBase.select_loginLog_count", paramMap);
	}
	/**
	 * 往(登录历史)新增一条记录
	 * @param loginLog
	 * @return
	 */
	@Override
	public int insertLoginLog(LoginLog loginLog){
		return sqlSession.insert("loginLogBase.insert_loginLog",loginLog);
	}
	/**
	 * 批量新增(登录历史)信息
	 * @param loginLogList
	 * @return
	 */
	@Override
	public int insertLoginLogBatch(List<LoginLog> loginLogList) {
		return sqlSession.insert("loginLogBase.insert_loginLog_Batch",loginLogList);
	}
	
	/**
	 * 更新(登录历史)信息
	 * @param loginLog
	 * @return
	 */
	@Override
	public int updateLoginLog(LoginLog loginLog){
		return sqlSession.update("loginLogBase.update_loginLog", loginLog);
	}
	/**
	 * 批量更新(登录历史)信息
	 * @param loginLogList
	 * @return
	 */
	@Override
	public int updateLoginLogBatch(List<LoginLog> loginLogList) {
		return sqlSession.update("loginLogBase.update_loginLog_Batch", loginLogList);
	}
	
	/**
	 * 根据序列号删除(登录历史)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteLoginLogLogic(java.math.BigInteger id){
		LoginLog loginLog = new LoginLog();
		loginLog.setId(id);
		loginLog.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("loginLogBase.delete_loginLog_Logic",loginLog);
	}
	
	/**
	 * 根据Id 批量删除(登录历史)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteLoginLogLogicBatch(List<java.math.BigInteger> idList) {
		List<LoginLog> delList = new java.util.ArrayList<LoginLog>();
		for(java.math.BigInteger id:idList){
			LoginLog loginLog = new LoginLog();
			loginLog.setId(id);
			loginLog.setSys0DelState(RecordState_DELETED);
			delList.add(loginLog);
		}
		return sqlSession.update("loginLogBase.delete_loginLog_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(登录历史)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteLoginLog(java.math.BigInteger id){
//		return sqlSession.delete("loginLogBase.delete_loginLog", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(登录历史)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteLoginLogBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("loginLogBase.delete_loginLog_Batch", idList);
//	}
	
	
}
