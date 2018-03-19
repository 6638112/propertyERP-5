package com.cnfantasia.server.domainbase.livingFeeSp.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.livingFeeSp.entity.LivingFeeSp;

/**
 * 描述:(宽带运营商) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class LivingFeeSpBaseDao extends AbstractBaseDao implements ILivingFeeSpBaseDao{
	/**
	 * 根据条件查询(宽带运营商)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<LivingFeeSp> selectLivingFeeSpByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("livingFeeSpBase.select_livingFeeSp",paramMap);
	}
	/**
	 * 按条件分页查询(宽带运营商)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<LivingFeeSp> selectLivingFeeSpByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectLivingFeeSpCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<LivingFeeSp> resMap= sqlSession.selectList("livingFeeSpBase.select_livingFeeSp_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(宽带运营商)信息
	 * @param id
	 * @return
	 */
	@Override
	public LivingFeeSp selectLivingFeeSpBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("livingFeeSpBase.select_livingFeeSp_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(宽带运营商)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectLivingFeeSpCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("livingFeeSpBase.select_livingFeeSp_count", paramMap);
	}
	/**
	 * 往(宽带运营商)新增一条记录
	 * @param livingFeeSp
	 * @return
	 */
	@Override
	public int insertLivingFeeSp(LivingFeeSp livingFeeSp){
		return sqlSession.insert("livingFeeSpBase.insert_livingFeeSp",livingFeeSp);
	}
	/**
	 * 批量新增(宽带运营商)信息
	 * @param livingFeeSpList
	 * @return
	 */
	@Override
	public int insertLivingFeeSpBatch(List<LivingFeeSp> livingFeeSpList) {
		if (livingFeeSpList == null || livingFeeSpList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = livingFeeSpList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<LivingFeeSp> batchList = livingFeeSpList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("livingFeeSpBase.insert_livingFeeSp_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(宽带运营商)信息
	 * @param livingFeeSp
	 * @return
	 */
	@Override
	public int updateLivingFeeSp(LivingFeeSp livingFeeSp){
		return sqlSession.update("livingFeeSpBase.update_livingFeeSp", livingFeeSp);
	}
	/**
	 * 批量更新(宽带运营商)信息
	 * @param livingFeeSpList
	 * @return
	 */
	@Override
	public int updateLivingFeeSpBatch(List<LivingFeeSp> livingFeeSpList) {
		if (livingFeeSpList == null || livingFeeSpList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("livingFeeSpBase.update_livingFeeSp_Batch", livingFeeSpList);
	}
	
	/**
	 * 根据序列号删除(宽带运营商)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteLivingFeeSpLogic(java.math.BigInteger id){
		LivingFeeSp livingFeeSp = new LivingFeeSp();
		livingFeeSp.setId(id);
		livingFeeSp.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("livingFeeSpBase.delete_livingFeeSp_Logic",livingFeeSp);
	}
	
	/**
	 * 根据Id 批量删除(宽带运营商)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteLivingFeeSpLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<LivingFeeSp> delList = new java.util.ArrayList<LivingFeeSp>();
		for(java.math.BigInteger id:idList){
			LivingFeeSp livingFeeSp = new LivingFeeSp();
			livingFeeSp.setId(id);
			livingFeeSp.setSys0DelState(RecordState_DELETED);
			delList.add(livingFeeSp);
		}
		return sqlSession.update("livingFeeSpBase.delete_livingFeeSp_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(宽带运营商)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteLivingFeeSp(java.math.BigInteger id){
//		return sqlSession.delete("livingFeeSpBase.delete_livingFeeSp", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(宽带运营商)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteLivingFeeSpBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("livingFeeSpBase.delete_livingFeeSp_Batch", idList);
//	}
	
	
}
