package com.cnfantasia.server.domainbase.supportBank.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.supportBank.entity.SupportBank;

/**
 * 描述:(支持的转账银行) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class SupportBankBaseDao extends AbstractBaseDao implements ISupportBankBaseDao{
	/**
	 * 根据条件查询(支持的转账银行)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<SupportBank> selectSupportBankByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("supportBankBase.select_supportBank",paramMap);
	}
	/**
	 * 按条件分页查询(支持的转账银行)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<SupportBank> selectSupportBankByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectSupportBankCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<SupportBank> resMap= sqlSession.selectList("supportBankBase.select_supportBank_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(支持的转账银行)信息
	 * @param id
	 * @return
	 */
	@Override
	public SupportBank selectSupportBankBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("supportBankBase.select_supportBank_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(支持的转账银行)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectSupportBankCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("supportBankBase.select_supportBank_count", paramMap);
	}
	/**
	 * 往(支持的转账银行)新增一条记录
	 * @param supportBank
	 * @return
	 */
	@Override
	public int insertSupportBank(SupportBank supportBank){
		return sqlSession.insert("supportBankBase.insert_supportBank",supportBank);
	}
	/**
	 * 批量新增(支持的转账银行)信息
	 * @param supportBankList
	 * @return
	 */
	@Override
	public int insertSupportBankBatch(List<SupportBank> supportBankList) {
		return sqlSession.insert("supportBankBase.insert_supportBank_Batch",supportBankList);
	}
	
	/**
	 * 更新(支持的转账银行)信息
	 * @param supportBank
	 * @return
	 */
	@Override
	public int updateSupportBank(SupportBank supportBank){
		return sqlSession.update("supportBankBase.update_supportBank", supportBank);
	}
	/**
	 * 批量更新(支持的转账银行)信息
	 * @param supportBankList
	 * @return
	 */
	@Override
	public int updateSupportBankBatch(List<SupportBank> supportBankList) {
		return sqlSession.update("supportBankBase.update_supportBank_Batch", supportBankList);
	}
	
	/**
	 * 根据序列号删除(支持的转账银行)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	@Override
	public int deleteSupportBankLogic(java.math.BigInteger id){
		SupportBank supportBank = new SupportBank();
		supportBank.setId(id);
		supportBank.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("supportBankBase.delete_supportBank_Logic",supportBank);
	}
	 */
	/**
	 * 根据Id 批量删除(支持的转账银行)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	@Override
	public int deleteSupportBankLogicBatch(List<java.math.BigInteger> idList) {
		List<SupportBank> delList = new java.util.ArrayList<SupportBank>();
		for(java.math.BigInteger id:idList){
			SupportBank supportBank = new SupportBank();
			supportBank.setId(id);
			supportBank.setSys0DelState(RecordState_DELETED);
			delList.add(supportBank);
		}
		return sqlSession.update("supportBankBase.delete_supportBank_Logic_Batch",delList);
	}
	 */
//	/**
//	 * 根据序列号删除(支持的转账银行)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteSupportBank(java.math.BigInteger id){
//		return sqlSession.delete("supportBankBase.delete_supportBank", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(支持的转账银行)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteSupportBankBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("supportBankBase.delete_supportBank_Batch", idList);
//	}
	
	
}
