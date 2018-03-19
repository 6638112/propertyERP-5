package com.cnfantasia.server.domainbase.msPrizeGiftCode.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.msPrizeGiftCode.entity.MsPrizeGiftCode;

/**
 * 描述:(抽奖奖品编码表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class MsPrizeGiftCodeBaseDao extends AbstractBaseDao implements IMsPrizeGiftCodeBaseDao{
	/**
	 * 根据条件查询(抽奖奖品编码表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<MsPrizeGiftCode> selectMsPrizeGiftCodeByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("msPrizeGiftCodeBase.select_msPrizeGiftCode",paramMap);
	}
	/**
	 * 按条件分页查询(抽奖奖品编码表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<MsPrizeGiftCode> selectMsPrizeGiftCodeByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectMsPrizeGiftCodeCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<MsPrizeGiftCode> resMap= sqlSession.selectList("msPrizeGiftCodeBase.select_msPrizeGiftCode_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(抽奖奖品编码表)信息
	 * @param id
	 * @return
	 */
	@Override
	public MsPrizeGiftCode selectMsPrizeGiftCodeBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("msPrizeGiftCodeBase.select_msPrizeGiftCode_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(抽奖奖品编码表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectMsPrizeGiftCodeCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("msPrizeGiftCodeBase.select_msPrizeGiftCode_count", paramMap);
	}
	/**
	 * 往(抽奖奖品编码表)新增一条记录
	 * @param msPrizeGiftCode
	 * @return
	 */
	@Override
	public int insertMsPrizeGiftCode(MsPrizeGiftCode msPrizeGiftCode){
		return sqlSession.insert("msPrizeGiftCodeBase.insert_msPrizeGiftCode",msPrizeGiftCode);
	}
	/**
	 * 批量新增(抽奖奖品编码表)信息
	 * @param msPrizeGiftCodeList
	 * @return
	 */
	@Override
	public int insertMsPrizeGiftCodeBatch(List<MsPrizeGiftCode> msPrizeGiftCodeList) {
		return sqlSession.insert("msPrizeGiftCodeBase.insert_msPrizeGiftCode_Batch",msPrizeGiftCodeList);
	}
	
	/**
	 * 更新(抽奖奖品编码表)信息
	 * @param msPrizeGiftCode
	 * @return
	 */
	@Override
	public int updateMsPrizeGiftCode(MsPrizeGiftCode msPrizeGiftCode){
		return sqlSession.update("msPrizeGiftCodeBase.update_msPrizeGiftCode", msPrizeGiftCode);
	}
	/**
	 * 批量更新(抽奖奖品编码表)信息
	 * @param msPrizeGiftCodeList
	 * @return
	 */
	@Override
	public int updateMsPrizeGiftCodeBatch(List<MsPrizeGiftCode> msPrizeGiftCodeList) {
		return sqlSession.update("msPrizeGiftCodeBase.update_msPrizeGiftCode_Batch", msPrizeGiftCodeList);
	}
	
	/**
	 * 根据序列号删除(抽奖奖品编码表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteMsPrizeGiftCodeLogic(java.math.BigInteger id){
		MsPrizeGiftCode msPrizeGiftCode = new MsPrizeGiftCode();
		msPrizeGiftCode.setId(id);
		msPrizeGiftCode.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("msPrizeGiftCodeBase.delete_msPrizeGiftCode_Logic",msPrizeGiftCode);
	}
	
	/**
	 * 根据Id 批量删除(抽奖奖品编码表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteMsPrizeGiftCodeLogicBatch(List<java.math.BigInteger> idList) {
		List<MsPrizeGiftCode> delList = new java.util.ArrayList<MsPrizeGiftCode>();
		for(java.math.BigInteger id:idList){
			MsPrizeGiftCode msPrizeGiftCode = new MsPrizeGiftCode();
			msPrizeGiftCode.setId(id);
			msPrizeGiftCode.setSys0DelState(RecordState_DELETED);
			delList.add(msPrizeGiftCode);
		}
		return sqlSession.update("msPrizeGiftCodeBase.delete_msPrizeGiftCode_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(抽奖奖品编码表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteMsPrizeGiftCode(java.math.BigInteger id){
//		return sqlSession.delete("msPrizeGiftCodeBase.delete_msPrizeGiftCode", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(抽奖奖品编码表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteMsPrizeGiftCodeBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("msPrizeGiftCodeBase.delete_msPrizeGiftCode_Batch", idList);
//	}
	
	
}
