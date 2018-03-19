package com.cnfantasia.server.domainbase.microblogPushType.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.microblogPushType.entity.MicroblogPushType;

/**
 * 描述:(街坊消息推送类别表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class MicroblogPushTypeBaseDao extends AbstractBaseDao implements IMicroblogPushTypeBaseDao{
	/**
	 * 根据条件查询(街坊消息推送类别表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<MicroblogPushType> selectMicroblogPushTypeByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("microblogPushTypeBase.select_microblogPushType",paramMap);
	}
	/**
	 * 按条件分页查询(街坊消息推送类别表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<MicroblogPushType> selectMicroblogPushTypeByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectMicroblogPushTypeCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<MicroblogPushType> resMap= sqlSession.selectList("microblogPushTypeBase.select_microblogPushType_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(街坊消息推送类别表)信息
	 * @param id
	 * @return
	 */
	@Override
	public MicroblogPushType selectMicroblogPushTypeBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("microblogPushTypeBase.select_microblogPushType_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(街坊消息推送类别表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectMicroblogPushTypeCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("microblogPushTypeBase.select_microblogPushType_count", paramMap);
	}
	/**
	 * 往(街坊消息推送类别表)新增一条记录
	 * @param microblogPushType
	 * @return
	 */
	@Override
	public int insertMicroblogPushType(MicroblogPushType microblogPushType){
		return sqlSession.insert("microblogPushTypeBase.insert_microblogPushType",microblogPushType);
	}
	/**
	 * 批量新增(街坊消息推送类别表)信息
	 * @param microblogPushTypeList
	 * @return
	 */
	@Override
	public int insertMicroblogPushTypeBatch(List<MicroblogPushType> microblogPushTypeList) {
		return sqlSession.insert("microblogPushTypeBase.insert_microblogPushType_Batch",microblogPushTypeList);
	}
	
	/**
	 * 更新(街坊消息推送类别表)信息
	 * @param microblogPushType
	 * @return
	 */
	@Override
	public int updateMicroblogPushType(MicroblogPushType microblogPushType){
		return sqlSession.update("microblogPushTypeBase.update_microblogPushType", microblogPushType);
	}
	/**
	 * 批量更新(街坊消息推送类别表)信息
	 * @param microblogPushTypeList
	 * @return
	 */
	@Override
	public int updateMicroblogPushTypeBatch(List<MicroblogPushType> microblogPushTypeList) {
		return sqlSession.update("microblogPushTypeBase.update_microblogPushType_Batch", microblogPushTypeList);
	}
	
	/**
	 * 根据序列号删除(街坊消息推送类别表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteMicroblogPushTypeLogic(java.math.BigInteger id){
		MicroblogPushType microblogPushType = new MicroblogPushType();
		microblogPushType.setId(id);
		microblogPushType.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("microblogPushTypeBase.delete_microblogPushType_Logic",microblogPushType);
	}
	
	/**
	 * 根据Id 批量删除(街坊消息推送类别表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteMicroblogPushTypeLogicBatch(List<java.math.BigInteger> idList) {
		List<MicroblogPushType> delList = new java.util.ArrayList<MicroblogPushType>();
		for(java.math.BigInteger id:idList){
			MicroblogPushType microblogPushType = new MicroblogPushType();
			microblogPushType.setId(id);
			microblogPushType.setSys0DelState(RecordState_DELETED);
			delList.add(microblogPushType);
		}
		return sqlSession.update("microblogPushTypeBase.delete_microblogPushType_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(街坊消息推送类别表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteMicroblogPushType(java.math.BigInteger id){
//		return sqlSession.delete("microblogPushTypeBase.delete_microblogPushType", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(街坊消息推送类别表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteMicroblogPushTypeBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("microblogPushTypeBase.delete_microblogPushType_Batch", idList);
//	}
	
	
}
