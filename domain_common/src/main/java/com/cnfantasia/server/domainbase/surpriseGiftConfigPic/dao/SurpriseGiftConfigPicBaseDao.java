package com.cnfantasia.server.domainbase.surpriseGiftConfigPic.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.surpriseGiftConfigPic.entity.SurpriseGiftConfigPic;

/**
 * 描述:(意外惊喜配置图标) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class SurpriseGiftConfigPicBaseDao extends AbstractBaseDao implements ISurpriseGiftConfigPicBaseDao{
	/**
	 * 根据条件查询(意外惊喜配置图标)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<SurpriseGiftConfigPic> selectSurpriseGiftConfigPicByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("surpriseGiftConfigPicBase.select_surpriseGiftConfigPic",paramMap);
	}
	/**
	 * 按条件分页查询(意外惊喜配置图标)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<SurpriseGiftConfigPic> selectSurpriseGiftConfigPicByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectSurpriseGiftConfigPicCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<SurpriseGiftConfigPic> resMap= sqlSession.selectList("surpriseGiftConfigPicBase.select_surpriseGiftConfigPic_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(意外惊喜配置图标)信息
	 * @param id
	 * @return
	 */
	@Override
	public SurpriseGiftConfigPic selectSurpriseGiftConfigPicBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("surpriseGiftConfigPicBase.select_surpriseGiftConfigPic_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(意外惊喜配置图标)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectSurpriseGiftConfigPicCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("surpriseGiftConfigPicBase.select_surpriseGiftConfigPic_count", paramMap);
	}
	/**
	 * 往(意外惊喜配置图标)新增一条记录
	 * @param surpriseGiftConfigPic
	 * @return
	 */
	@Override
	public int insertSurpriseGiftConfigPic(SurpriseGiftConfigPic surpriseGiftConfigPic){
		return sqlSession.insert("surpriseGiftConfigPicBase.insert_surpriseGiftConfigPic",surpriseGiftConfigPic);
	}
	/**
	 * 批量新增(意外惊喜配置图标)信息
	 * @param surpriseGiftConfigPicList
	 * @return
	 */
	@Override
	public int insertSurpriseGiftConfigPicBatch(List<SurpriseGiftConfigPic> surpriseGiftConfigPicList) {
		return sqlSession.insert("surpriseGiftConfigPicBase.insert_surpriseGiftConfigPic_Batch",surpriseGiftConfigPicList);
	}
	
	/**
	 * 更新(意外惊喜配置图标)信息
	 * @param surpriseGiftConfigPic
	 * @return
	 */
	@Override
	public int updateSurpriseGiftConfigPic(SurpriseGiftConfigPic surpriseGiftConfigPic){
		return sqlSession.update("surpriseGiftConfigPicBase.update_surpriseGiftConfigPic", surpriseGiftConfigPic);
	}
	/**
	 * 批量更新(意外惊喜配置图标)信息
	 * @param surpriseGiftConfigPicList
	 * @return
	 */
	@Override
	public int updateSurpriseGiftConfigPicBatch(List<SurpriseGiftConfigPic> surpriseGiftConfigPicList) {
		return sqlSession.update("surpriseGiftConfigPicBase.update_surpriseGiftConfigPic_Batch", surpriseGiftConfigPicList);
	}
	
	/**
	 * 根据序列号删除(意外惊喜配置图标)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteSurpriseGiftConfigPicLogic(java.math.BigInteger id){
		SurpriseGiftConfigPic surpriseGiftConfigPic = new SurpriseGiftConfigPic();
		surpriseGiftConfigPic.setId(id);
		surpriseGiftConfigPic.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("surpriseGiftConfigPicBase.delete_surpriseGiftConfigPic_Logic",surpriseGiftConfigPic);
	}
	
	/**
	 * 根据Id 批量删除(意外惊喜配置图标)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteSurpriseGiftConfigPicLogicBatch(List<java.math.BigInteger> idList) {
		List<SurpriseGiftConfigPic> delList = new java.util.ArrayList<SurpriseGiftConfigPic>();
		for(java.math.BigInteger id:idList){
			SurpriseGiftConfigPic surpriseGiftConfigPic = new SurpriseGiftConfigPic();
			surpriseGiftConfigPic.setId(id);
			surpriseGiftConfigPic.setSys0DelState(RecordState_DELETED);
			delList.add(surpriseGiftConfigPic);
		}
		return sqlSession.update("surpriseGiftConfigPicBase.delete_surpriseGiftConfigPic_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(意外惊喜配置图标)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteSurpriseGiftConfigPic(java.math.BigInteger id){
//		return sqlSession.delete("surpriseGiftConfigPicBase.delete_surpriseGiftConfigPic", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(意外惊喜配置图标)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteSurpriseGiftConfigPicBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("surpriseGiftConfigPicBase.delete_surpriseGiftConfigPic_Batch", idList);
//	}
	
	
}
