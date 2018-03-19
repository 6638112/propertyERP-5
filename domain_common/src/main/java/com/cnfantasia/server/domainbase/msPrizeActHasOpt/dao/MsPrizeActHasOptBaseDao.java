package com.cnfantasia.server.domainbase.msPrizeActHasOpt.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.msPrizeActHasOpt.entity.MsPrizeActHasOpt;

/**
 * 描述:(抽奖活动与奖项关系表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class MsPrizeActHasOptBaseDao extends AbstractBaseDao implements IMsPrizeActHasOptBaseDao{
	/**
	 * 根据条件查询(抽奖活动与奖项关系表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<MsPrizeActHasOpt> selectMsPrizeActHasOptByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("msPrizeActHasOptBase.select_msPrizeActHasOpt",paramMap);
	}
	/**
	 * 按条件分页查询(抽奖活动与奖项关系表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<MsPrizeActHasOpt> selectMsPrizeActHasOptByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectMsPrizeActHasOptCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<MsPrizeActHasOpt> resMap= sqlSession.selectList("msPrizeActHasOptBase.select_msPrizeActHasOpt_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(抽奖活动与奖项关系表)信息
	 * @param id
	 * @return
	 */
	@Override
	public MsPrizeActHasOpt selectMsPrizeActHasOptBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("msPrizeActHasOptBase.select_msPrizeActHasOpt_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(抽奖活动与奖项关系表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectMsPrizeActHasOptCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("msPrizeActHasOptBase.select_msPrizeActHasOpt_count", paramMap);
	}
	/**
	 * 往(抽奖活动与奖项关系表)新增一条记录
	 * @param msPrizeActHasOpt
	 * @return
	 */
	@Override
	public int insertMsPrizeActHasOpt(MsPrizeActHasOpt msPrizeActHasOpt){
		return sqlSession.insert("msPrizeActHasOptBase.insert_msPrizeActHasOpt",msPrizeActHasOpt);
	}
	/**
	 * 批量新增(抽奖活动与奖项关系表)信息
	 * @param msPrizeActHasOptList
	 * @return
	 */
	@Override
	public int insertMsPrizeActHasOptBatch(List<MsPrizeActHasOpt> msPrizeActHasOptList) {
		return sqlSession.insert("msPrizeActHasOptBase.insert_msPrizeActHasOpt_Batch",msPrizeActHasOptList);
	}
	
	/**
	 * 更新(抽奖活动与奖项关系表)信息
	 * @param msPrizeActHasOpt
	 * @return
	 */
	@Override
	public int updateMsPrizeActHasOpt(MsPrizeActHasOpt msPrizeActHasOpt){
		return sqlSession.update("msPrizeActHasOptBase.update_msPrizeActHasOpt", msPrizeActHasOpt);
	}
	/**
	 * 批量更新(抽奖活动与奖项关系表)信息
	 * @param msPrizeActHasOptList
	 * @return
	 */
	@Override
	public int updateMsPrizeActHasOptBatch(List<MsPrizeActHasOpt> msPrizeActHasOptList) {
		return sqlSession.update("msPrizeActHasOptBase.update_msPrizeActHasOpt_Batch", msPrizeActHasOptList);
	}
	
	/**
	 * 根据序列号删除(抽奖活动与奖项关系表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteMsPrizeActHasOptLogic(java.math.BigInteger id){
		MsPrizeActHasOpt msPrizeActHasOpt = new MsPrizeActHasOpt();
		msPrizeActHasOpt.setId(id);
		msPrizeActHasOpt.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("msPrizeActHasOptBase.delete_msPrizeActHasOpt_Logic",msPrizeActHasOpt);
	}
	
	/**
	 * 根据Id 批量删除(抽奖活动与奖项关系表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteMsPrizeActHasOptLogicBatch(List<java.math.BigInteger> idList) {
		List<MsPrizeActHasOpt> delList = new java.util.ArrayList<MsPrizeActHasOpt>();
		for(java.math.BigInteger id:idList){
			MsPrizeActHasOpt msPrizeActHasOpt = new MsPrizeActHasOpt();
			msPrizeActHasOpt.setId(id);
			msPrizeActHasOpt.setSys0DelState(RecordState_DELETED);
			delList.add(msPrizeActHasOpt);
		}
		return sqlSession.update("msPrizeActHasOptBase.delete_msPrizeActHasOpt_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(抽奖活动与奖项关系表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteMsPrizeActHasOpt(java.math.BigInteger id){
//		return sqlSession.delete("msPrizeActHasOptBase.delete_msPrizeActHasOpt", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(抽奖活动与奖项关系表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteMsPrizeActHasOptBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("msPrizeActHasOptBase.delete_msPrizeActHasOpt_Batch", idList);
//	}
	
	
}
