package com.cnfantasia.server.domainbase.ebuySupplyMerchantHasUser.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuySupplyMerchantHasUser.entity.EbuySupplyMerchantHasUser;

/**
 * 描述:(用户供应商关系表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EbuySupplyMerchantHasUserBaseDao extends AbstractBaseDao implements IEbuySupplyMerchantHasUserBaseDao{
	/**
	 * 根据条件查询(用户供应商关系表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuySupplyMerchantHasUser> selectEbuySupplyMerchantHasUserByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("ebuySupplyMerchantHasUserBase.select_ebuySupplyMerchantHasUser",paramMap);
	}
	/**
	 * 按条件分页查询(用户供应商关系表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuySupplyMerchantHasUser> selectEbuySupplyMerchantHasUserByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEbuySupplyMerchantHasUserCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EbuySupplyMerchantHasUser> resMap= sqlSession.selectList("ebuySupplyMerchantHasUserBase.select_ebuySupplyMerchantHasUser_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(用户供应商关系表)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuySupplyMerchantHasUser selectEbuySupplyMerchantHasUserBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("ebuySupplyMerchantHasUserBase.select_ebuySupplyMerchantHasUser_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(用户供应商关系表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEbuySupplyMerchantHasUserCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("ebuySupplyMerchantHasUserBase.select_ebuySupplyMerchantHasUser_count", paramMap);
	}
	/**
	 * 往(用户供应商关系表)新增一条记录
	 * @param ebuySupplyMerchantHasUser
	 * @return
	 */
	@Override
	public int insertEbuySupplyMerchantHasUser(EbuySupplyMerchantHasUser ebuySupplyMerchantHasUser){
		return sqlSession.insert("ebuySupplyMerchantHasUserBase.insert_ebuySupplyMerchantHasUser",ebuySupplyMerchantHasUser);
	}
	/**
	 * 批量新增(用户供应商关系表)信息
	 * @param ebuySupplyMerchantHasUserList
	 * @return
	 */
	@Override
	public int insertEbuySupplyMerchantHasUserBatch(List<EbuySupplyMerchantHasUser> ebuySupplyMerchantHasUserList) {
		return sqlSession.insert("ebuySupplyMerchantHasUserBase.insert_ebuySupplyMerchantHasUser_Batch",ebuySupplyMerchantHasUserList);
	}
	
	/**
	 * 更新(用户供应商关系表)信息
	 * @param ebuySupplyMerchantHasUser
	 * @return
	 */
	@Override
	public int updateEbuySupplyMerchantHasUser(EbuySupplyMerchantHasUser ebuySupplyMerchantHasUser){
		return sqlSession.update("ebuySupplyMerchantHasUserBase.update_ebuySupplyMerchantHasUser", ebuySupplyMerchantHasUser);
	}
	/**
	 * 批量更新(用户供应商关系表)信息
	 * @param ebuySupplyMerchantHasUserList
	 * @return
	 */
	@Override
	public int updateEbuySupplyMerchantHasUserBatch(List<EbuySupplyMerchantHasUser> ebuySupplyMerchantHasUserList) {
		return sqlSession.update("ebuySupplyMerchantHasUserBase.update_ebuySupplyMerchantHasUser_Batch", ebuySupplyMerchantHasUserList);
	}
	
	/**
	 * 根据序列号删除(用户供应商关系表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuySupplyMerchantHasUserLogic(java.math.BigInteger id){
		EbuySupplyMerchantHasUser ebuySupplyMerchantHasUser = new EbuySupplyMerchantHasUser();
		ebuySupplyMerchantHasUser.setId(id);
		ebuySupplyMerchantHasUser.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("ebuySupplyMerchantHasUserBase.delete_ebuySupplyMerchantHasUser_Logic",ebuySupplyMerchantHasUser);
	}
	
	/**
	 * 根据Id 批量删除(用户供应商关系表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuySupplyMerchantHasUserLogicBatch(List<java.math.BigInteger> idList) {
		List<EbuySupplyMerchantHasUser> delList = new java.util.ArrayList<EbuySupplyMerchantHasUser>();
		for(java.math.BigInteger id:idList){
			EbuySupplyMerchantHasUser ebuySupplyMerchantHasUser = new EbuySupplyMerchantHasUser();
			ebuySupplyMerchantHasUser.setId(id);
			ebuySupplyMerchantHasUser.setSys0DelState(RecordState_DELETED);
			delList.add(ebuySupplyMerchantHasUser);
		}
		return sqlSession.update("ebuySupplyMerchantHasUserBase.delete_ebuySupplyMerchantHasUser_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(用户供应商关系表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuySupplyMerchantHasUser(java.math.BigInteger id){
//		return sqlSession.delete("ebuySupplyMerchantHasUserBase.delete_ebuySupplyMerchantHasUser", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(用户供应商关系表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuySupplyMerchantHasUserBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("ebuySupplyMerchantHasUserBase.delete_ebuySupplyMerchantHasUser_Batch", idList);
//	}
	
	
}
