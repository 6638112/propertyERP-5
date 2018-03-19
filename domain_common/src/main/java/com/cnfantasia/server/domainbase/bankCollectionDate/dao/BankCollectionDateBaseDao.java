package com.cnfantasia.server.domainbase.bankCollectionDate.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.bankCollectionDate.entity.BankCollectionDate;

/**
 * 描述:(物业公司银行托收日期配置) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class BankCollectionDateBaseDao extends AbstractBaseDao implements IBankCollectionDateBaseDao{
	/**
	 * 根据条件查询(物业公司银行托收日期配置)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<BankCollectionDate> selectBankCollectionDateByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("bankCollectionDateBase.select_bankCollectionDate",paramMap);
	}
	/**
	 * 按条件分页查询(物业公司银行托收日期配置)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<BankCollectionDate> selectBankCollectionDateByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectBankCollectionDateCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<BankCollectionDate> resMap= sqlSession.selectList("bankCollectionDateBase.select_bankCollectionDate_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(物业公司银行托收日期配置)信息
	 * @param id
	 * @return
	 */
	@Override
	public BankCollectionDate selectBankCollectionDateBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("bankCollectionDateBase.select_bankCollectionDate_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(物业公司银行托收日期配置)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectBankCollectionDateCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("bankCollectionDateBase.select_bankCollectionDate_count", paramMap);
	}
	/**
	 * 往(物业公司银行托收日期配置)新增一条记录
	 * @param bankCollectionDate
	 * @return
	 */
	@Override
	public int insertBankCollectionDate(BankCollectionDate bankCollectionDate){
		return sqlSession.insert("bankCollectionDateBase.insert_bankCollectionDate",bankCollectionDate);
	}
	/**
	 * 批量新增(物业公司银行托收日期配置)信息
	 * @param bankCollectionDateList
	 * @return
	 */
	@Override
	public int insertBankCollectionDateBatch(List<BankCollectionDate> bankCollectionDateList) {
		if (bankCollectionDateList == null || bankCollectionDateList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = bankCollectionDateList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<BankCollectionDate> batchList = bankCollectionDateList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("bankCollectionDateBase.insert_bankCollectionDate_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(物业公司银行托收日期配置)信息
	 * @param bankCollectionDate
	 * @return
	 */
	@Override
	public int updateBankCollectionDate(BankCollectionDate bankCollectionDate){
		return sqlSession.update("bankCollectionDateBase.update_bankCollectionDate", bankCollectionDate);
	}
	/**
	 * 批量更新(物业公司银行托收日期配置)信息
	 * @param bankCollectionDateList
	 * @return
	 */
	@Override
	public int updateBankCollectionDateBatch(List<BankCollectionDate> bankCollectionDateList) {
		if (bankCollectionDateList == null || bankCollectionDateList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("bankCollectionDateBase.update_bankCollectionDate_Batch", bankCollectionDateList);
	}
	
	/**
	 * 根据序列号删除(物业公司银行托收日期配置)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteBankCollectionDateLogic(java.math.BigInteger id){
		BankCollectionDate bankCollectionDate = new BankCollectionDate();
		bankCollectionDate.setId(id);
		bankCollectionDate.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("bankCollectionDateBase.delete_bankCollectionDate_Logic",bankCollectionDate);
	}
	
	/**
	 * 根据Id 批量删除(物业公司银行托收日期配置)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteBankCollectionDateLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<BankCollectionDate> delList = new java.util.ArrayList<BankCollectionDate>();
		for(java.math.BigInteger id:idList){
			BankCollectionDate bankCollectionDate = new BankCollectionDate();
			bankCollectionDate.setId(id);
			bankCollectionDate.setSys0DelState(RecordState_DELETED);
			delList.add(bankCollectionDate);
		}
		return sqlSession.update("bankCollectionDateBase.delete_bankCollectionDate_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(物业公司银行托收日期配置)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteBankCollectionDate(java.math.BigInteger id){
//		return sqlSession.delete("bankCollectionDateBase.delete_bankCollectionDate", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业公司银行托收日期配置)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteBankCollectionDateBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("bankCollectionDateBase.delete_bankCollectionDate_Batch", idList);
//	}
	
	
}
