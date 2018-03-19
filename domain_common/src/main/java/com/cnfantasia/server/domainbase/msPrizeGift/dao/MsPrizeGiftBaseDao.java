package com.cnfantasia.server.domainbase.msPrizeGift.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.msPrizeGift.entity.MsPrizeGift;

/**
 * 描述:(奖品详情表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class MsPrizeGiftBaseDao extends AbstractBaseDao implements IMsPrizeGiftBaseDao{
	/**
	 * 根据条件查询(奖品详情表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<MsPrizeGift> selectMsPrizeGiftByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("msPrizeGiftBase.select_msPrizeGift",paramMap);
	}
	/**
	 * 按条件分页查询(奖品详情表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<MsPrizeGift> selectMsPrizeGiftByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectMsPrizeGiftCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<MsPrizeGift> resMap= sqlSession.selectList("msPrizeGiftBase.select_msPrizeGift_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(奖品详情表)信息
	 * @param id
	 * @return
	 */
	@Override
	public MsPrizeGift selectMsPrizeGiftBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("msPrizeGiftBase.select_msPrizeGift_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(奖品详情表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectMsPrizeGiftCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("msPrizeGiftBase.select_msPrizeGift_count", paramMap);
	}
	/**
	 * 往(奖品详情表)新增一条记录
	 * @param msPrizeGift
	 * @return
	 */
	@Override
	public int insertMsPrizeGift(MsPrizeGift msPrizeGift){
		return sqlSession.insert("msPrizeGiftBase.insert_msPrizeGift",msPrizeGift);
	}
	/**
	 * 批量新增(奖品详情表)信息
	 * @param msPrizeGiftList
	 * @return
	 */
	@Override
	public int insertMsPrizeGiftBatch(List<MsPrizeGift> msPrizeGiftList) {
		return sqlSession.insert("msPrizeGiftBase.insert_msPrizeGift_Batch",msPrizeGiftList);
	}
	
	/**
	 * 更新(奖品详情表)信息
	 * @param msPrizeGift
	 * @return
	 */
	@Override
	public int updateMsPrizeGift(MsPrizeGift msPrizeGift){
		return sqlSession.update("msPrizeGiftBase.update_msPrizeGift", msPrizeGift);
	}
	/**
	 * 批量更新(奖品详情表)信息
	 * @param msPrizeGiftList
	 * @return
	 */
	@Override
	public int updateMsPrizeGiftBatch(List<MsPrizeGift> msPrizeGiftList) {
		return sqlSession.update("msPrizeGiftBase.update_msPrizeGift_Batch", msPrizeGiftList);
	}
	
	/**
	 * 根据序列号删除(奖品详情表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteMsPrizeGiftLogic(java.math.BigInteger id){
		MsPrizeGift msPrizeGift = new MsPrizeGift();
		msPrizeGift.setId(id);
		msPrizeGift.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("msPrizeGiftBase.delete_msPrizeGift_Logic",msPrizeGift);
	}
	
	/**
	 * 根据Id 批量删除(奖品详情表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteMsPrizeGiftLogicBatch(List<java.math.BigInteger> idList) {
		List<MsPrizeGift> delList = new java.util.ArrayList<MsPrizeGift>();
		for(java.math.BigInteger id:idList){
			MsPrizeGift msPrizeGift = new MsPrizeGift();
			msPrizeGift.setId(id);
			msPrizeGift.setSys0DelState(RecordState_DELETED);
			delList.add(msPrizeGift);
		}
		return sqlSession.update("msPrizeGiftBase.delete_msPrizeGift_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(奖品详情表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteMsPrizeGift(java.math.BigInteger id){
//		return sqlSession.delete("msPrizeGiftBase.delete_msPrizeGift", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(奖品详情表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteMsPrizeGiftBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("msPrizeGiftBase.delete_msPrizeGift_Batch", idList);
//	}
	
	
}
