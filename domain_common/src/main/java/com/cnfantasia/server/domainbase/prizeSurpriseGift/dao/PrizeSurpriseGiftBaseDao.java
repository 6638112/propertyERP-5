package com.cnfantasia.server.domainbase.prizeSurpriseGift.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.prizeSurpriseGift.entity.PrizeSurpriseGift;

/**
 * 描述:(抽奖结果的意外惊喜) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class PrizeSurpriseGiftBaseDao extends AbstractBaseDao implements IPrizeSurpriseGiftBaseDao{
	/**
	 * 根据条件查询(抽奖结果的意外惊喜)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PrizeSurpriseGift> selectPrizeSurpriseGiftByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("prizeSurpriseGiftBase.select_prizeSurpriseGift",paramMap);
	}
	/**
	 * 按条件分页查询(抽奖结果的意外惊喜)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<PrizeSurpriseGift> selectPrizeSurpriseGiftByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectPrizeSurpriseGiftCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<PrizeSurpriseGift> resMap= sqlSession.selectList("prizeSurpriseGiftBase.select_prizeSurpriseGift_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(抽奖结果的意外惊喜)信息
	 * @param id
	 * @return
	 */
	@Override
	public PrizeSurpriseGift selectPrizeSurpriseGiftBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("prizeSurpriseGiftBase.select_prizeSurpriseGift_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(抽奖结果的意外惊喜)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectPrizeSurpriseGiftCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("prizeSurpriseGiftBase.select_prizeSurpriseGift_count", paramMap);
	}
	/**
	 * 往(抽奖结果的意外惊喜)新增一条记录
	 * @param prizeSurpriseGift
	 * @return
	 */
	@Override
	public int insertPrizeSurpriseGift(PrizeSurpriseGift prizeSurpriseGift){
		return sqlSession.insert("prizeSurpriseGiftBase.insert_prizeSurpriseGift",prizeSurpriseGift);
	}
	/**
	 * 批量新增(抽奖结果的意外惊喜)信息
	 * @param prizeSurpriseGiftList
	 * @return
	 */
	@Override
	public int insertPrizeSurpriseGiftBatch(List<PrizeSurpriseGift> prizeSurpriseGiftList) {
		return sqlSession.insert("prizeSurpriseGiftBase.insert_prizeSurpriseGift_Batch",prizeSurpriseGiftList);
	}
	
	/**
	 * 更新(抽奖结果的意外惊喜)信息
	 * @param prizeSurpriseGift
	 * @return
	 */
	@Override
	public int updatePrizeSurpriseGift(PrizeSurpriseGift prizeSurpriseGift){
		return sqlSession.update("prizeSurpriseGiftBase.update_prizeSurpriseGift", prizeSurpriseGift);
	}
	/**
	 * 批量更新(抽奖结果的意外惊喜)信息
	 * @param prizeSurpriseGiftList
	 * @return
	 */
	@Override
	public int updatePrizeSurpriseGiftBatch(List<PrizeSurpriseGift> prizeSurpriseGiftList) {
		return sqlSession.update("prizeSurpriseGiftBase.update_prizeSurpriseGift_Batch", prizeSurpriseGiftList);
	}
	
	/**
	 * 根据序列号删除(抽奖结果的意外惊喜)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deletePrizeSurpriseGiftLogic(java.math.BigInteger id){
		PrizeSurpriseGift prizeSurpriseGift = new PrizeSurpriseGift();
		prizeSurpriseGift.setId(id);
		prizeSurpriseGift.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("prizeSurpriseGiftBase.delete_prizeSurpriseGift_Logic",prizeSurpriseGift);
	}
	
	/**
	 * 根据Id 批量删除(抽奖结果的意外惊喜)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deletePrizeSurpriseGiftLogicBatch(List<java.math.BigInteger> idList) {
		List<PrizeSurpriseGift> delList = new java.util.ArrayList<PrizeSurpriseGift>();
		for(java.math.BigInteger id:idList){
			PrizeSurpriseGift prizeSurpriseGift = new PrizeSurpriseGift();
			prizeSurpriseGift.setId(id);
			prizeSurpriseGift.setSys0DelState(RecordState_DELETED);
			delList.add(prizeSurpriseGift);
		}
		return sqlSession.update("prizeSurpriseGiftBase.delete_prizeSurpriseGift_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(抽奖结果的意外惊喜)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deletePrizeSurpriseGift(java.math.BigInteger id){
//		return sqlSession.delete("prizeSurpriseGiftBase.delete_prizeSurpriseGift", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(抽奖结果的意外惊喜)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deletePrizeSurpriseGiftBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("prizeSurpriseGiftBase.delete_prizeSurpriseGift_Batch", idList);
//	}
	
	
}
