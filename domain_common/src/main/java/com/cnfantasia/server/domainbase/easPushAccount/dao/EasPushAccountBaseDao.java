package com.cnfantasia.server.domainbase.easPushAccount.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.easPushAccount.entity.EasPushAccount;

/**
 * 描述:(EAS推送账户信息配置表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EasPushAccountBaseDao extends AbstractBaseDao implements IEasPushAccountBaseDao{
	/**
	 * 根据条件查询(EAS推送账户信息配置表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EasPushAccount> selectEasPushAccountByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("easPushAccountBase.select_easPushAccount",paramMap);
	}
	/**
	 * 按条件分页查询(EAS推送账户信息配置表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EasPushAccount> selectEasPushAccountByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEasPushAccountCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EasPushAccount> resMap= sqlSession.selectList("easPushAccountBase.select_easPushAccount_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(EAS推送账户信息配置表)信息
	 * @param id
	 * @return
	 */
	@Override
	public EasPushAccount selectEasPushAccountBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("easPushAccountBase.select_easPushAccount_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(EAS推送账户信息配置表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEasPushAccountCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("easPushAccountBase.select_easPushAccount_count", paramMap);
	}
	/**
	 * 往(EAS推送账户信息配置表)新增一条记录
	 * @param easPushAccount
	 * @return
	 */
	@Override
	public int insertEasPushAccount(EasPushAccount easPushAccount){
		return sqlSession.insert("easPushAccountBase.insert_easPushAccount",easPushAccount);
	}
	/**
	 * 批量新增(EAS推送账户信息配置表)信息
	 * @param easPushAccountList
	 * @return
	 */
	@Override
	public int insertEasPushAccountBatch(List<EasPushAccount> easPushAccountList) {
		return sqlSession.insert("easPushAccountBase.insert_easPushAccount_Batch",easPushAccountList);
	}
	
	/**
	 * 更新(EAS推送账户信息配置表)信息
	 * @param easPushAccount
	 * @return
	 */
	@Override
	public int updateEasPushAccount(EasPushAccount easPushAccount){
		return sqlSession.update("easPushAccountBase.update_easPushAccount", easPushAccount);
	}
	/**
	 * 批量更新(EAS推送账户信息配置表)信息
	 * @param easPushAccountList
	 * @return
	 */
	@Override
	public int updateEasPushAccountBatch(List<EasPushAccount> easPushAccountList) {
		return sqlSession.update("easPushAccountBase.update_easPushAccount_Batch", easPushAccountList);
	}
	
	/**
	 * 根据序列号删除(EAS推送账户信息配置表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEasPushAccountLogic(java.math.BigInteger id){
		EasPushAccount easPushAccount = new EasPushAccount();
		easPushAccount.setId(id);
		easPushAccount.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("easPushAccountBase.delete_easPushAccount_Logic",easPushAccount);
	}
	
	/**
	 * 根据Id 批量删除(EAS推送账户信息配置表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEasPushAccountLogicBatch(List<java.math.BigInteger> idList) {
		List<EasPushAccount> delList = new java.util.ArrayList<EasPushAccount>();
		for(java.math.BigInteger id:idList){
			EasPushAccount easPushAccount = new EasPushAccount();
			easPushAccount.setId(id);
			easPushAccount.setSys0DelState(RecordState_DELETED);
			delList.add(easPushAccount);
		}
		return sqlSession.update("easPushAccountBase.delete_easPushAccount_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(EAS推送账户信息配置表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEasPushAccount(java.math.BigInteger id){
//		return sqlSession.delete("easPushAccountBase.delete_easPushAccount", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(EAS推送账户信息配置表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEasPushAccountBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("easPushAccountBase.delete_easPushAccount_Batch", idList);
//	}
	
	
}
