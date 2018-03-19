package com.cnfantasia.server.domainbase.userHasPropertyCard.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.userHasPropertyCard.entity.UserHasPropertyCard;

/**
 * 描述:(用户购买物业代扣卡) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class UserHasPropertyCardBaseDao extends AbstractBaseDao implements IUserHasPropertyCardBaseDao{
	/**
	 * 根据条件查询(用户购买物业代扣卡)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<UserHasPropertyCard> selectUserHasPropertyCardByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("userHasPropertyCardBase.select_userHasPropertyCard",paramMap);
	}
	/**
	 * 按条件分页查询(用户购买物业代扣卡)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<UserHasPropertyCard> selectUserHasPropertyCardByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectUserHasPropertyCardCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<UserHasPropertyCard> resMap= sqlSession.selectList("userHasPropertyCardBase.select_userHasPropertyCard_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(用户购买物业代扣卡)信息
	 * @param id
	 * @return
	 */
	@Override
	public UserHasPropertyCard selectUserHasPropertyCardBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("userHasPropertyCardBase.select_userHasPropertyCard_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(用户购买物业代扣卡)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectUserHasPropertyCardCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("userHasPropertyCardBase.select_userHasPropertyCard_count", paramMap);
	}
	/**
	 * 往(用户购买物业代扣卡)新增一条记录
	 * @param userHasPropertyCard
	 * @return
	 */
	@Override
	public int insertUserHasPropertyCard(UserHasPropertyCard userHasPropertyCard){
		return sqlSession.insert("userHasPropertyCardBase.insert_userHasPropertyCard",userHasPropertyCard);
	}
	/**
	 * 批量新增(用户购买物业代扣卡)信息
	 * @param userHasPropertyCardList
	 * @return
	 */
	@Override
	public int insertUserHasPropertyCardBatch(List<UserHasPropertyCard> userHasPropertyCardList) {
		if (userHasPropertyCardList == null || userHasPropertyCardList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = userHasPropertyCardList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<UserHasPropertyCard> batchList = userHasPropertyCardList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("userHasPropertyCardBase.insert_userHasPropertyCard_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(用户购买物业代扣卡)信息
	 * @param userHasPropertyCard
	 * @return
	 */
	@Override
	public int updateUserHasPropertyCard(UserHasPropertyCard userHasPropertyCard){
		return sqlSession.update("userHasPropertyCardBase.update_userHasPropertyCard", userHasPropertyCard);
	}
	/**
	 * 批量更新(用户购买物业代扣卡)信息
	 * @param userHasPropertyCardList
	 * @return
	 */
	@Override
	public int updateUserHasPropertyCardBatch(List<UserHasPropertyCard> userHasPropertyCardList) {
		if (userHasPropertyCardList == null || userHasPropertyCardList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("userHasPropertyCardBase.update_userHasPropertyCard_Batch", userHasPropertyCardList);
	}
	
	/**
	 * 根据序列号删除(用户购买物业代扣卡)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteUserHasPropertyCardLogic(java.math.BigInteger id){
		UserHasPropertyCard userHasPropertyCard = new UserHasPropertyCard();
		userHasPropertyCard.setId(id);
		userHasPropertyCard.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("userHasPropertyCardBase.delete_userHasPropertyCard_Logic",userHasPropertyCard);
	}
	
	/**
	 * 根据Id 批量删除(用户购买物业代扣卡)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteUserHasPropertyCardLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<UserHasPropertyCard> delList = new java.util.ArrayList<UserHasPropertyCard>();
		for(java.math.BigInteger id:idList){
			UserHasPropertyCard userHasPropertyCard = new UserHasPropertyCard();
			userHasPropertyCard.setId(id);
			userHasPropertyCard.setSys0DelState(RecordState_DELETED);
			delList.add(userHasPropertyCard);
		}
		return sqlSession.update("userHasPropertyCardBase.delete_userHasPropertyCard_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(用户购买物业代扣卡)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteUserHasPropertyCard(java.math.BigInteger id){
//		return sqlSession.delete("userHasPropertyCardBase.delete_userHasPropertyCard", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(用户购买物业代扣卡)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteUserHasPropertyCardBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("userHasPropertyCardBase.delete_userHasPropertyCard_Batch", idList);
//	}
	
	
}
