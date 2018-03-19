package com.cnfantasia.server.domainbase.loanType.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.loanType.entity.LoanType;

/**
 * 描述:(借贷类型表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class LoanTypeBaseDao extends AbstractBaseDao implements ILoanTypeBaseDao{
	/**
	 * 根据条件查询(借贷类型表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<LoanType> selectLoanTypeByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("loanTypeBase.select_loanType",paramMap);
	}
	/**
	 * 按条件分页查询(借贷类型表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<LoanType> selectLoanTypeByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectLoanTypeCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<LoanType> resMap= sqlSession.selectList("loanTypeBase.select_loanType_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(借贷类型表)信息
	 * @param id
	 * @return
	 */
	@Override
	public LoanType selectLoanTypeBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("loanTypeBase.select_loanType_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(借贷类型表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectLoanTypeCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("loanTypeBase.select_loanType_count", paramMap);
	}
	/**
	 * 往(借贷类型表)新增一条记录
	 * @param loanType
	 * @return
	 */
	@Override
	public int insertLoanType(LoanType loanType){
		return sqlSession.insert("loanTypeBase.insert_loanType",loanType);
	}
	/**
	 * 批量新增(借贷类型表)信息
	 * @param loanTypeList
	 * @return
	 */
	@Override
	public int insertLoanTypeBatch(List<LoanType> loanTypeList) {
		if (loanTypeList == null || loanTypeList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = loanTypeList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<LoanType> batchList = loanTypeList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("loanTypeBase.insert_loanType_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(借贷类型表)信息
	 * @param loanType
	 * @return
	 */
	@Override
	public int updateLoanType(LoanType loanType){
		return sqlSession.update("loanTypeBase.update_loanType", loanType);
	}
	/**
	 * 批量更新(借贷类型表)信息
	 * @param loanTypeList
	 * @return
	 */
	@Override
	public int updateLoanTypeBatch(List<LoanType> loanTypeList) {
		if (loanTypeList == null || loanTypeList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("loanTypeBase.update_loanType_Batch", loanTypeList);
	}
	
	/**
	 * 根据序列号删除(借贷类型表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteLoanTypeLogic(java.math.BigInteger id){
		LoanType loanType = new LoanType();
		loanType.setId(id);
		loanType.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("loanTypeBase.delete_loanType_Logic",loanType);
	}
	
	/**
	 * 根据Id 批量删除(借贷类型表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteLoanTypeLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<LoanType> delList = new java.util.ArrayList<LoanType>();
		for(java.math.BigInteger id:idList){
			LoanType loanType = new LoanType();
			loanType.setId(id);
			loanType.setSys0DelState(RecordState_DELETED);
			delList.add(loanType);
		}
		return sqlSession.update("loanTypeBase.delete_loanType_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(借贷类型表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteLoanType(java.math.BigInteger id){
//		return sqlSession.delete("loanTypeBase.delete_loanType", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(借贷类型表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteLoanTypeBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("loanTypeBase.delete_loanType_Batch", idList);
//	}
	
	
}
