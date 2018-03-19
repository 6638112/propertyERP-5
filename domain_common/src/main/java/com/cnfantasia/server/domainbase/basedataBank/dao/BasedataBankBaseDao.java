package com.cnfantasia.server.domainbase.basedataBank.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.basedataBank.entity.BasedataBank;

/**
 * 描述:(银行基础资料) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class BasedataBankBaseDao extends AbstractBaseDao implements IBasedataBankBaseDao{
	/**
	 * 根据条件查询(银行基础资料)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<BasedataBank> selectBasedataBankByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("basedataBankBase.select_basedataBank",paramMap);
	}
	/**
	 * 按条件分页查询(银行基础资料)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<BasedataBank> selectBasedataBankByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectBasedataBankCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<BasedataBank> resMap= sqlSession.selectList("basedataBankBase.select_basedataBank_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(银行基础资料)信息
	 * @param id
	 * @return
	 */
	@Override
	public BasedataBank selectBasedataBankBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("basedataBankBase.select_basedataBank_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(银行基础资料)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectBasedataBankCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("basedataBankBase.select_basedataBank_count", paramMap);
	}
	/**
	 * 往(银行基础资料)新增一条记录
	 * @param basedataBank
	 * @return
	 */
	@Override
	public int insertBasedataBank(BasedataBank basedataBank){
		return sqlSession.insert("basedataBankBase.insert_basedataBank",basedataBank);
	}
	/**
	 * 批量新增(银行基础资料)信息
	 * @param basedataBankList
	 * @return
	 */
	@Override
	public int insertBasedataBankBatch(List<BasedataBank> basedataBankList) {
		if (basedataBankList == null || basedataBankList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = basedataBankList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<BasedataBank> batchList = basedataBankList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("basedataBankBase.insert_basedataBank_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(银行基础资料)信息
	 * @param basedataBank
	 * @return
	 */
	@Override
	public int updateBasedataBank(BasedataBank basedataBank){
		return sqlSession.update("basedataBankBase.update_basedataBank", basedataBank);
	}
	/**
	 * 批量更新(银行基础资料)信息
	 * @param basedataBankList
	 * @return
	 */
	@Override
	public int updateBasedataBankBatch(List<BasedataBank> basedataBankList) {
		if (basedataBankList == null || basedataBankList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("basedataBankBase.update_basedataBank_Batch", basedataBankList);
	}
	
	/**
	 * 根据序列号删除(银行基础资料)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	@Override
	public int deleteBasedataBankLogic(java.math.BigInteger id){
		BasedataBank basedataBank = new BasedataBank();
		basedataBank.setId(id);
		basedataBank.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("basedataBankBase.delete_basedataBank_Logic",basedataBank);
	}
	 */
	/**
	 * 根据Id 批量删除(银行基础资料)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	@Override
	public int deleteBasedataBankLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<BasedataBank> delList = new java.util.ArrayList<BasedataBank>();
		for(java.math.BigInteger id:idList){
			BasedataBank basedataBank = new BasedataBank();
			basedataBank.setId(id);
			basedataBank.setSys0DelState(RecordState_DELETED);
			delList.add(basedataBank);
		}
		return sqlSession.update("basedataBankBase.delete_basedataBank_Logic_Batch",delList);
	}
	 */
//	/**
//	 * 根据序列号删除(银行基础资料)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteBasedataBank(java.math.BigInteger id){
//		return sqlSession.delete("basedataBankBase.delete_basedataBank", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(银行基础资料)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteBasedataBankBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("basedataBankBase.delete_basedataBank_Batch", idList);
//	}
	
	
}
