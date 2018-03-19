package com.cnfantasia.server.domainbase.loanThird.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.loanThird.entity.LoanThird;

/**
 * 描述:(借贷第三方对接信息表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class LoanThirdBaseDao extends AbstractBaseDao implements ILoanThirdBaseDao{
	/**
	 * 根据条件查询(借贷第三方对接信息表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<LoanThird> selectLoanThirdByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("loanThirdBase.select_loanThird",paramMap);
	}
	/**
	 * 按条件分页查询(借贷第三方对接信息表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<LoanThird> selectLoanThirdByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectLoanThirdCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<LoanThird> resMap= sqlSession.selectList("loanThirdBase.select_loanThird_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(借贷第三方对接信息表)信息
	 * @param id
	 * @return
	 */
	@Override
	public LoanThird selectLoanThirdBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("loanThirdBase.select_loanThird_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(借贷第三方对接信息表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectLoanThirdCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("loanThirdBase.select_loanThird_count", paramMap);
	}
	/**
	 * 往(借贷第三方对接信息表)新增一条记录
	 * @param loanThird
	 * @return
	 */
	@Override
	public int insertLoanThird(LoanThird loanThird){
		return sqlSession.insert("loanThirdBase.insert_loanThird",loanThird);
	}
	/**
	 * 批量新增(借贷第三方对接信息表)信息
	 * @param loanThirdList
	 * @return
	 */
	@Override
	public int insertLoanThirdBatch(List<LoanThird> loanThirdList) {
		if (loanThirdList == null || loanThirdList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = loanThirdList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<LoanThird> batchList = loanThirdList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("loanThirdBase.insert_loanThird_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(借贷第三方对接信息表)信息
	 * @param loanThird
	 * @return
	 */
	@Override
	public int updateLoanThird(LoanThird loanThird){
		return sqlSession.update("loanThirdBase.update_loanThird", loanThird);
	}
	/**
	 * 批量更新(借贷第三方对接信息表)信息
	 * @param loanThirdList
	 * @return
	 */
	@Override
	public int updateLoanThirdBatch(List<LoanThird> loanThirdList) {
		if (loanThirdList == null || loanThirdList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("loanThirdBase.update_loanThird_Batch", loanThirdList);
	}
	
	/**
	 * 根据序列号删除(借贷第三方对接信息表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteLoanThirdLogic(java.math.BigInteger id){
		LoanThird loanThird = new LoanThird();
		loanThird.setId(id);
		loanThird.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("loanThirdBase.delete_loanThird_Logic",loanThird);
	}
	
	/**
	 * 根据Id 批量删除(借贷第三方对接信息表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteLoanThirdLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<LoanThird> delList = new java.util.ArrayList<LoanThird>();
		for(java.math.BigInteger id:idList){
			LoanThird loanThird = new LoanThird();
			loanThird.setId(id);
			loanThird.setSys0DelState(RecordState_DELETED);
			delList.add(loanThird);
		}
		return sqlSession.update("loanThirdBase.delete_loanThird_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(借贷第三方对接信息表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteLoanThird(java.math.BigInteger id){
//		return sqlSession.delete("loanThirdBase.delete_loanThird", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(借贷第三方对接信息表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteLoanThirdBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("loanThirdBase.delete_loanThird_Batch", idList);
//	}
	
	
}
