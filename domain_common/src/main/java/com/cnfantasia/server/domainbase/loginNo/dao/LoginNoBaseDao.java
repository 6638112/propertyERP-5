package com.cnfantasia.server.domainbase.loginNo.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.loginNo.entity.LoginNo;

/**
 * 描述:(用户登录账号) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class LoginNoBaseDao extends AbstractBaseDao implements ILoginNoBaseDao{
	/**
	 * 根据条件查询(用户登录账号)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<LoginNo> selectLoginNoByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("loginNoBase.select_loginNo",paramMap);
	}
	/**
	 * 按条件分页查询(用户登录账号)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<LoginNo> selectLoginNoByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectLoginNoCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<LoginNo> resMap= sqlSession.selectList("loginNoBase.select_loginNo_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(用户登录账号)信息
	 * @param id
	 * @return
	 */
	@Override
	public LoginNo selectLoginNoBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("loginNoBase.select_loginNo_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(用户登录账号)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectLoginNoCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("loginNoBase.select_loginNo_count", paramMap);
	}
	/**
	 * 往(用户登录账号)新增一条记录
	 * @param loginNo
	 * @return
	 */
	@Override
	public int insertLoginNo(LoginNo loginNo){
		return sqlSession.insert("loginNoBase.insert_loginNo",loginNo);
	}
	/**
	 * 批量新增(用户登录账号)信息
	 * @param loginNoList
	 * @return
	 */
	@Override
	public int insertLoginNoBatch(List<LoginNo> loginNoList) {
		return sqlSession.insert("loginNoBase.insert_loginNo_Batch",loginNoList);
	}
	
	/**
	 * 更新(用户登录账号)信息
	 * @param loginNo
	 * @return
	 */
	@Override
	public int updateLoginNo(LoginNo loginNo){
		return sqlSession.update("loginNoBase.update_loginNo", loginNo);
	}
	/**
	 * 批量更新(用户登录账号)信息
	 * @param loginNoList
	 * @return
	 */
	@Override
	public int updateLoginNoBatch(List<LoginNo> loginNoList) {
		return sqlSession.update("loginNoBase.update_loginNo_Batch", loginNoList);
	}
	
	/**
	 * 根据序列号删除(用户登录账号)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteLoginNoLogic(java.math.BigInteger id){
		LoginNo loginNo = new LoginNo();
		loginNo.setId(id);
		loginNo.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("loginNoBase.delete_loginNo_Logic",loginNo);
	}
	
	/**
	 * 根据Id 批量删除(用户登录账号)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteLoginNoLogicBatch(List<java.math.BigInteger> idList) {
		List<LoginNo> delList = new java.util.ArrayList<LoginNo>();
		for(java.math.BigInteger id:idList){
			LoginNo loginNo = new LoginNo();
			loginNo.setId(id);
			loginNo.setSys0DelState(RecordState_DELETED);
			delList.add(loginNo);
		}
		return sqlSession.update("loginNoBase.delete_loginNo_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(用户登录账号)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteLoginNo(java.math.BigInteger id){
//		return sqlSession.delete("loginNoBase.delete_loginNo", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(用户登录账号)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteLoginNoBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("loginNoBase.delete_loginNo_Batch", idList);
//	}
	
	
}
