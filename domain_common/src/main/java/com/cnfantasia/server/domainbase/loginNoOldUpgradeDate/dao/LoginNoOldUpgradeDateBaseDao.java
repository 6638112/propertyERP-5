package com.cnfantasia.server.domainbase.loginNoOldUpgradeDate.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.loginNoOldUpgradeDate.entity.LoginNoOldUpgradeDate;

/**
 * 描述:(微信升级老数据) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class LoginNoOldUpgradeDateBaseDao extends AbstractBaseDao implements ILoginNoOldUpgradeDateBaseDao{
	/**
	 * 根据条件查询(微信升级老数据)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<LoginNoOldUpgradeDate> selectLoginNoOldUpgradeDateByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("loginNoOldUpgradeDateBase.select_loginNoOldUpgradeDate",paramMap);
	}
	/**
	 * 按条件分页查询(微信升级老数据)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<LoginNoOldUpgradeDate> selectLoginNoOldUpgradeDateByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectLoginNoOldUpgradeDateCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<LoginNoOldUpgradeDate> resMap= sqlSession.selectList("loginNoOldUpgradeDateBase.select_loginNoOldUpgradeDate_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(微信升级老数据)信息
	 * @param id
	 * @return
	 */
	@Override
	public LoginNoOldUpgradeDate selectLoginNoOldUpgradeDateBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("loginNoOldUpgradeDateBase.select_loginNoOldUpgradeDate_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(微信升级老数据)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectLoginNoOldUpgradeDateCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("loginNoOldUpgradeDateBase.select_loginNoOldUpgradeDate_count", paramMap);
	}
	/**
	 * 往(微信升级老数据)新增一条记录
	 * @param loginNoOldUpgradeDate
	 * @return
	 */
	@Override
	public int insertLoginNoOldUpgradeDate(LoginNoOldUpgradeDate loginNoOldUpgradeDate){
		return sqlSession.insert("loginNoOldUpgradeDateBase.insert_loginNoOldUpgradeDate",loginNoOldUpgradeDate);
	}
	/**
	 * 批量新增(微信升级老数据)信息
	 * @param loginNoOldUpgradeDateList
	 * @return
	 */
	@Override
	public int insertLoginNoOldUpgradeDateBatch(List<LoginNoOldUpgradeDate> loginNoOldUpgradeDateList) {
		return sqlSession.insert("loginNoOldUpgradeDateBase.insert_loginNoOldUpgradeDate_Batch",loginNoOldUpgradeDateList);
	}
	
	/**
	 * 更新(微信升级老数据)信息
	 * @param loginNoOldUpgradeDate
	 * @return
	 */
	@Override
	public int updateLoginNoOldUpgradeDate(LoginNoOldUpgradeDate loginNoOldUpgradeDate){
		return sqlSession.update("loginNoOldUpgradeDateBase.update_loginNoOldUpgradeDate", loginNoOldUpgradeDate);
	}
	/**
	 * 批量更新(微信升级老数据)信息
	 * @param loginNoOldUpgradeDateList
	 * @return
	 */
	@Override
	public int updateLoginNoOldUpgradeDateBatch(List<LoginNoOldUpgradeDate> loginNoOldUpgradeDateList) {
		return sqlSession.update("loginNoOldUpgradeDateBase.update_loginNoOldUpgradeDate_Batch", loginNoOldUpgradeDateList);
	}
	
	/**
	 * 根据序列号删除(微信升级老数据)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteLoginNoOldUpgradeDateLogic(java.math.BigInteger id){
		LoginNoOldUpgradeDate loginNoOldUpgradeDate = new LoginNoOldUpgradeDate();
		loginNoOldUpgradeDate.setId(id);
		loginNoOldUpgradeDate.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("loginNoOldUpgradeDateBase.delete_loginNoOldUpgradeDate_Logic",loginNoOldUpgradeDate);
	}
	
	/**
	 * 根据Id 批量删除(微信升级老数据)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteLoginNoOldUpgradeDateLogicBatch(List<java.math.BigInteger> idList) {
		List<LoginNoOldUpgradeDate> delList = new java.util.ArrayList<LoginNoOldUpgradeDate>();
		for(java.math.BigInteger id:idList){
			LoginNoOldUpgradeDate loginNoOldUpgradeDate = new LoginNoOldUpgradeDate();
			loginNoOldUpgradeDate.setId(id);
			loginNoOldUpgradeDate.setSys0DelState(RecordState_DELETED);
			delList.add(loginNoOldUpgradeDate);
		}
		return sqlSession.update("loginNoOldUpgradeDateBase.delete_loginNoOldUpgradeDate_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(微信升级老数据)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteLoginNoOldUpgradeDate(java.math.BigInteger id){
//		return sqlSession.delete("loginNoOldUpgradeDateBase.delete_loginNoOldUpgradeDate", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(微信升级老数据)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteLoginNoOldUpgradeDateBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("loginNoOldUpgradeDateBase.delete_loginNoOldUpgradeDate_Batch", idList);
//	}
	
	
}
