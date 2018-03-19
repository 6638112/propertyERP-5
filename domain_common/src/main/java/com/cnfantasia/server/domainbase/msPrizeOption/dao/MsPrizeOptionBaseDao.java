package com.cnfantasia.server.domainbase.msPrizeOption.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.msPrizeOption.entity.MsPrizeOption;

/**
 * 描述:(奖项表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class MsPrizeOptionBaseDao extends AbstractBaseDao implements IMsPrizeOptionBaseDao{
	/**
	 * 根据条件查询(奖项表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<MsPrizeOption> selectMsPrizeOptionByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("msPrizeOptionBase.select_msPrizeOption",paramMap);
	}
	/**
	 * 按条件分页查询(奖项表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<MsPrizeOption> selectMsPrizeOptionByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectMsPrizeOptionCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<MsPrizeOption> resMap= sqlSession.selectList("msPrizeOptionBase.select_msPrizeOption_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(奖项表)信息
	 * @param id
	 * @return
	 */
	@Override
	public MsPrizeOption selectMsPrizeOptionBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("msPrizeOptionBase.select_msPrizeOption_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(奖项表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectMsPrizeOptionCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("msPrizeOptionBase.select_msPrizeOption_count", paramMap);
	}
	/**
	 * 往(奖项表)新增一条记录
	 * @param msPrizeOption
	 * @return
	 */
	@Override
	public int insertMsPrizeOption(MsPrizeOption msPrizeOption){
		return sqlSession.insert("msPrizeOptionBase.insert_msPrizeOption",msPrizeOption);
	}
	/**
	 * 批量新增(奖项表)信息
	 * @param msPrizeOptionList
	 * @return
	 */
	@Override
	public int insertMsPrizeOptionBatch(List<MsPrizeOption> msPrizeOptionList) {
		return sqlSession.insert("msPrizeOptionBase.insert_msPrizeOption_Batch",msPrizeOptionList);
	}
	
	/**
	 * 更新(奖项表)信息
	 * @param msPrizeOption
	 * @return
	 */
	@Override
	public int updateMsPrizeOption(MsPrizeOption msPrizeOption){
		return sqlSession.update("msPrizeOptionBase.update_msPrizeOption", msPrizeOption);
	}
	/**
	 * 批量更新(奖项表)信息
	 * @param msPrizeOptionList
	 * @return
	 */
	@Override
	public int updateMsPrizeOptionBatch(List<MsPrizeOption> msPrizeOptionList) {
		return sqlSession.update("msPrizeOptionBase.update_msPrizeOption_Batch", msPrizeOptionList);
	}
	
	/**
	 * 根据序列号删除(奖项表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteMsPrizeOptionLogic(java.math.BigInteger id){
		MsPrizeOption msPrizeOption = new MsPrizeOption();
		msPrizeOption.setId(id);
		msPrizeOption.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("msPrizeOptionBase.delete_msPrizeOption_Logic",msPrizeOption);
	}
	
	/**
	 * 根据Id 批量删除(奖项表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteMsPrizeOptionLogicBatch(List<java.math.BigInteger> idList) {
		List<MsPrizeOption> delList = new java.util.ArrayList<MsPrizeOption>();
		for(java.math.BigInteger id:idList){
			MsPrizeOption msPrizeOption = new MsPrizeOption();
			msPrizeOption.setId(id);
			msPrizeOption.setSys0DelState(RecordState_DELETED);
			delList.add(msPrizeOption);
		}
		return sqlSession.update("msPrizeOptionBase.delete_msPrizeOption_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(奖项表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteMsPrizeOption(java.math.BigInteger id){
//		return sqlSession.delete("msPrizeOptionBase.delete_msPrizeOption", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(奖项表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteMsPrizeOptionBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("msPrizeOptionBase.delete_msPrizeOption_Batch", idList);
//	}
	
	
}
