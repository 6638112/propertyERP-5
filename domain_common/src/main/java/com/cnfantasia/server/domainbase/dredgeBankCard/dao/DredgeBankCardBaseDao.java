package com.cnfantasia.server.domainbase.dredgeBankCard.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeBankCard.entity.DredgeBankCard;

/**
 * 描述:(用户绑定的银行卡信息) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class DredgeBankCardBaseDao extends AbstractBaseDao implements IDredgeBankCardBaseDao{
	/**
	 * 根据条件查询(用户绑定的银行卡信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<DredgeBankCard> selectDredgeBankCardByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("dredgeBankCardBase.select_dredgeBankCard",paramMap);
	}
	/**
	 * 按条件分页查询(用户绑定的银行卡信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<DredgeBankCard> selectDredgeBankCardByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectDredgeBankCardCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<DredgeBankCard> resMap= sqlSession.selectList("dredgeBankCardBase.select_dredgeBankCard_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(用户绑定的银行卡信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public DredgeBankCard selectDredgeBankCardBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("dredgeBankCardBase.select_dredgeBankCard_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(用户绑定的银行卡信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectDredgeBankCardCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("dredgeBankCardBase.select_dredgeBankCard_count", paramMap);
	}
	/**
	 * 往(用户绑定的银行卡信息)新增一条记录
	 * @param dredgeBankCard
	 * @return
	 */
	@Override
	public int insertDredgeBankCard(DredgeBankCard dredgeBankCard){
		return sqlSession.insert("dredgeBankCardBase.insert_dredgeBankCard",dredgeBankCard);
	}
	/**
	 * 批量新增(用户绑定的银行卡信息)信息
	 * @param dredgeBankCardList
	 * @return
	 */
	@Override
	public int insertDredgeBankCardBatch(List<DredgeBankCard> dredgeBankCardList) {
		return sqlSession.insert("dredgeBankCardBase.insert_dredgeBankCard_Batch",dredgeBankCardList);
	}
	
	/**
	 * 更新(用户绑定的银行卡信息)信息
	 * @param dredgeBankCard
	 * @return
	 */
	@Override
	public int updateDredgeBankCard(DredgeBankCard dredgeBankCard){
		return sqlSession.update("dredgeBankCardBase.update_dredgeBankCard", dredgeBankCard);
	}
	/**
	 * 批量更新(用户绑定的银行卡信息)信息
	 * @param dredgeBankCardList
	 * @return
	 */
	@Override
	public int updateDredgeBankCardBatch(List<DredgeBankCard> dredgeBankCardList) {
		return sqlSession.update("dredgeBankCardBase.update_dredgeBankCard_Batch", dredgeBankCardList);
	}
	
	/**
	 * 根据序列号删除(用户绑定的银行卡信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	@Override
	public int deleteDredgeBankCardLogic(java.math.BigInteger id){
		DredgeBankCard dredgeBankCard = new DredgeBankCard();
		dredgeBankCard.setId(id);
		dredgeBankCard.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("dredgeBankCardBase.delete_dredgeBankCard_Logic",dredgeBankCard);
	}
	 */
	/**
	 * 根据Id 批量删除(用户绑定的银行卡信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	@Override
	public int deleteDredgeBankCardLogicBatch(List<java.math.BigInteger> idList) {
		List<DredgeBankCard> delList = new java.util.ArrayList<DredgeBankCard>();
		for(java.math.BigInteger id:idList){
			DredgeBankCard dredgeBankCard = new DredgeBankCard();
			dredgeBankCard.setId(id);
			dredgeBankCard.setSys0DelState(RecordState_DELETED);
			delList.add(dredgeBankCard);
		}
		return sqlSession.update("dredgeBankCardBase.delete_dredgeBankCard_Logic_Batch",delList);
	}
	 */
//	/**
//	 * 根据序列号删除(用户绑定的银行卡信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeBankCard(java.math.BigInteger id){
//		return sqlSession.delete("dredgeBankCardBase.delete_dredgeBankCard", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(用户绑定的银行卡信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeBankCardBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("dredgeBankCardBase.delete_dredgeBankCard_Batch", idList);
//	}
	
	
}
