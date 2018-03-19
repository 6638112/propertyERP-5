package com.cnfantasia.server.domainbase.bcInfoHasTPropertyProprietor.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.bcInfoHasTPropertyProprietor.entity.BcInfoHasTPropertyProprietor;

/**
 * 描述:(物业公司托收银行信息包含的业主) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class BcInfoHasTPropertyProprietorBaseDao extends AbstractBaseDao implements IBcInfoHasTPropertyProprietorBaseDao{
	/**
	 * 根据条件查询(物业公司托收银行信息包含的业主)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<BcInfoHasTPropertyProprietor> selectBcInfoHasTPropertyProprietorByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("bcInfoHasTPropertyProprietorBase.select_bcInfoHasTPropertyProprietor",paramMap);
	}
	/**
	 * 按条件分页查询(物业公司托收银行信息包含的业主)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<BcInfoHasTPropertyProprietor> selectBcInfoHasTPropertyProprietorByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectBcInfoHasTPropertyProprietorCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<BcInfoHasTPropertyProprietor> resMap= sqlSession.selectList("bcInfoHasTPropertyProprietorBase.select_bcInfoHasTPropertyProprietor_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(物业公司托收银行信息包含的业主)信息
	 * @param id
	 * @return
	 */
	@Override
	public BcInfoHasTPropertyProprietor selectBcInfoHasTPropertyProprietorBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("bcInfoHasTPropertyProprietorBase.select_bcInfoHasTPropertyProprietor_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(物业公司托收银行信息包含的业主)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectBcInfoHasTPropertyProprietorCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("bcInfoHasTPropertyProprietorBase.select_bcInfoHasTPropertyProprietor_count", paramMap);
	}
	/**
	 * 往(物业公司托收银行信息包含的业主)新增一条记录
	 * @param bcInfoHasTPropertyProprietor
	 * @return
	 */
	@Override
	public int insertBcInfoHasTPropertyProprietor(BcInfoHasTPropertyProprietor bcInfoHasTPropertyProprietor){
		return sqlSession.insert("bcInfoHasTPropertyProprietorBase.insert_bcInfoHasTPropertyProprietor",bcInfoHasTPropertyProprietor);
	}
	/**
	 * 批量新增(物业公司托收银行信息包含的业主)信息
	 * @param bcInfoHasTPropertyProprietorList
	 * @return
	 */
	@Override
	public int insertBcInfoHasTPropertyProprietorBatch(List<BcInfoHasTPropertyProprietor> bcInfoHasTPropertyProprietorList) {
		if (bcInfoHasTPropertyProprietorList == null || bcInfoHasTPropertyProprietorList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = bcInfoHasTPropertyProprietorList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<BcInfoHasTPropertyProprietor> batchList = bcInfoHasTPropertyProprietorList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("bcInfoHasTPropertyProprietorBase.insert_bcInfoHasTPropertyProprietor_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(物业公司托收银行信息包含的业主)信息
	 * @param bcInfoHasTPropertyProprietor
	 * @return
	 */
	@Override
	public int updateBcInfoHasTPropertyProprietor(BcInfoHasTPropertyProprietor bcInfoHasTPropertyProprietor){
		return sqlSession.update("bcInfoHasTPropertyProprietorBase.update_bcInfoHasTPropertyProprietor", bcInfoHasTPropertyProprietor);
	}
	/**
	 * 批量更新(物业公司托收银行信息包含的业主)信息
	 * @param bcInfoHasTPropertyProprietorList
	 * @return
	 */
	@Override
	public int updateBcInfoHasTPropertyProprietorBatch(List<BcInfoHasTPropertyProprietor> bcInfoHasTPropertyProprietorList) {
		if (bcInfoHasTPropertyProprietorList == null || bcInfoHasTPropertyProprietorList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("bcInfoHasTPropertyProprietorBase.update_bcInfoHasTPropertyProprietor_Batch", bcInfoHasTPropertyProprietorList);
	}
	
	/**
	 * 根据序列号删除(物业公司托收银行信息包含的业主)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteBcInfoHasTPropertyProprietorLogic(java.math.BigInteger id){
		BcInfoHasTPropertyProprietor bcInfoHasTPropertyProprietor = new BcInfoHasTPropertyProprietor();
		bcInfoHasTPropertyProprietor.setId(id);
		bcInfoHasTPropertyProprietor.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("bcInfoHasTPropertyProprietorBase.delete_bcInfoHasTPropertyProprietor_Logic",bcInfoHasTPropertyProprietor);
	}
	
	/**
	 * 根据Id 批量删除(物业公司托收银行信息包含的业主)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteBcInfoHasTPropertyProprietorLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<BcInfoHasTPropertyProprietor> delList = new java.util.ArrayList<BcInfoHasTPropertyProprietor>();
		for(java.math.BigInteger id:idList){
			BcInfoHasTPropertyProprietor bcInfoHasTPropertyProprietor = new BcInfoHasTPropertyProprietor();
			bcInfoHasTPropertyProprietor.setId(id);
			bcInfoHasTPropertyProprietor.setSys0DelState(RecordState_DELETED);
			delList.add(bcInfoHasTPropertyProprietor);
		}
		return sqlSession.update("bcInfoHasTPropertyProprietorBase.delete_bcInfoHasTPropertyProprietor_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(物业公司托收银行信息包含的业主)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteBcInfoHasTPropertyProprietor(java.math.BigInteger id){
//		return sqlSession.delete("bcInfoHasTPropertyProprietorBase.delete_bcInfoHasTPropertyProprietor", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(物业公司托收银行信息包含的业主)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteBcInfoHasTPropertyProprietorBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("bcInfoHasTPropertyProprietorBase.delete_bcInfoHasTPropertyProprietor_Batch", idList);
//	}
	
	
}
