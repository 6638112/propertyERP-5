package com.cnfantasia.server.domainbase.ebuySupplyMerchantBankAccount.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuySupplyMerchantBankAccount.entity.EbuySupplyMerchantBankAccount;

/**
 * 描述:(楼下店等电商供应商银行卡信息) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EbuySupplyMerchantBankAccountBaseDao extends AbstractBaseDao implements IEbuySupplyMerchantBankAccountBaseDao{
	/**
	 * 根据条件查询(楼下店等电商供应商银行卡信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuySupplyMerchantBankAccount> selectEbuySupplyMerchantBankAccountByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("ebuySupplyMerchantBankAccountBase.select_ebuySupplyMerchantBankAccount",paramMap);
	}
	/**
	 * 按条件分页查询(楼下店等电商供应商银行卡信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuySupplyMerchantBankAccount> selectEbuySupplyMerchantBankAccountByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEbuySupplyMerchantBankAccountCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EbuySupplyMerchantBankAccount> resMap= sqlSession.selectList("ebuySupplyMerchantBankAccountBase.select_ebuySupplyMerchantBankAccount_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(楼下店等电商供应商银行卡信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuySupplyMerchantBankAccount selectEbuySupplyMerchantBankAccountBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("ebuySupplyMerchantBankAccountBase.select_ebuySupplyMerchantBankAccount_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(楼下店等电商供应商银行卡信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEbuySupplyMerchantBankAccountCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("ebuySupplyMerchantBankAccountBase.select_ebuySupplyMerchantBankAccount_count", paramMap);
	}
	/**
	 * 往(楼下店等电商供应商银行卡信息)新增一条记录
	 * @param ebuySupplyMerchantBankAccount
	 * @return
	 */
	@Override
	public int insertEbuySupplyMerchantBankAccount(EbuySupplyMerchantBankAccount ebuySupplyMerchantBankAccount){
		return sqlSession.insert("ebuySupplyMerchantBankAccountBase.insert_ebuySupplyMerchantBankAccount",ebuySupplyMerchantBankAccount);
	}
	/**
	 * 批量新增(楼下店等电商供应商银行卡信息)信息
	 * @param ebuySupplyMerchantBankAccountList
	 * @return
	 */
	@Override
	public int insertEbuySupplyMerchantBankAccountBatch(List<EbuySupplyMerchantBankAccount> ebuySupplyMerchantBankAccountList) {
		return sqlSession.insert("ebuySupplyMerchantBankAccountBase.insert_ebuySupplyMerchantBankAccount_Batch",ebuySupplyMerchantBankAccountList);
	}
	
	/**
	 * 更新(楼下店等电商供应商银行卡信息)信息
	 * @param ebuySupplyMerchantBankAccount
	 * @return
	 */
	@Override
	public int updateEbuySupplyMerchantBankAccount(EbuySupplyMerchantBankAccount ebuySupplyMerchantBankAccount){
		return sqlSession.update("ebuySupplyMerchantBankAccountBase.update_ebuySupplyMerchantBankAccount", ebuySupplyMerchantBankAccount);
	}
	/**
	 * 批量更新(楼下店等电商供应商银行卡信息)信息
	 * @param ebuySupplyMerchantBankAccountList
	 * @return
	 */
	@Override
	public int updateEbuySupplyMerchantBankAccountBatch(List<EbuySupplyMerchantBankAccount> ebuySupplyMerchantBankAccountList) {
		return sqlSession.update("ebuySupplyMerchantBankAccountBase.update_ebuySupplyMerchantBankAccount_Batch", ebuySupplyMerchantBankAccountList);
	}
	
	/**
	 * 根据序列号删除(楼下店等电商供应商银行卡信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuySupplyMerchantBankAccountLogic(java.math.BigInteger id){
		EbuySupplyMerchantBankAccount ebuySupplyMerchantBankAccount = new EbuySupplyMerchantBankAccount();
		ebuySupplyMerchantBankAccount.setId(id);
		ebuySupplyMerchantBankAccount.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("ebuySupplyMerchantBankAccountBase.delete_ebuySupplyMerchantBankAccount_Logic",ebuySupplyMerchantBankAccount);
	}
	
	/**
	 * 根据Id 批量删除(楼下店等电商供应商银行卡信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuySupplyMerchantBankAccountLogicBatch(List<java.math.BigInteger> idList) {
		List<EbuySupplyMerchantBankAccount> delList = new java.util.ArrayList<EbuySupplyMerchantBankAccount>();
		for(java.math.BigInteger id:idList){
			EbuySupplyMerchantBankAccount ebuySupplyMerchantBankAccount = new EbuySupplyMerchantBankAccount();
			ebuySupplyMerchantBankAccount.setId(id);
			ebuySupplyMerchantBankAccount.setSys0DelState(RecordState_DELETED);
			delList.add(ebuySupplyMerchantBankAccount);
		}
		return sqlSession.update("ebuySupplyMerchantBankAccountBase.delete_ebuySupplyMerchantBankAccount_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(楼下店等电商供应商银行卡信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuySupplyMerchantBankAccount(java.math.BigInteger id){
//		return sqlSession.delete("ebuySupplyMerchantBankAccountBase.delete_ebuySupplyMerchantBankAccount", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(楼下店等电商供应商银行卡信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuySupplyMerchantBankAccountBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("ebuySupplyMerchantBankAccountBase.delete_ebuySupplyMerchantBankAccount_Batch", idList);
//	}
	
	
}
