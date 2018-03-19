package com.cnfantasia.server.domainbase.microblogPic.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.microblogPic.entity.MicroblogPic;

/**
 * 描述:(微博图片信息) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class MicroblogPicBaseDao extends AbstractBaseDao implements IMicroblogPicBaseDao{
	/**
	 * 根据条件查询(微博图片信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<MicroblogPic> selectMicroblogPicByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("microblogPicBase.select_microblogPic",paramMap);
	}
	/**
	 * 按条件分页查询(微博图片信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<MicroblogPic> selectMicroblogPicByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectMicroblogPicCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<MicroblogPic> resMap= sqlSession.selectList("microblogPicBase.select_microblogPic_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(微博图片信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public MicroblogPic selectMicroblogPicBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("microblogPicBase.select_microblogPic_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(微博图片信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectMicroblogPicCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("microblogPicBase.select_microblogPic_count", paramMap);
	}
	/**
	 * 往(微博图片信息)新增一条记录
	 * @param microblogPic
	 * @return
	 */
	@Override
	public int insertMicroblogPic(MicroblogPic microblogPic){
		return sqlSession.insert("microblogPicBase.insert_microblogPic",microblogPic);
	}
	/**
	 * 批量新增(微博图片信息)信息
	 * @param microblogPicList
	 * @return
	 */
	@Override
	public int insertMicroblogPicBatch(List<MicroblogPic> microblogPicList) {
		return sqlSession.insert("microblogPicBase.insert_microblogPic_Batch",microblogPicList);
	}
	
	/**
	 * 更新(微博图片信息)信息
	 * @param microblogPic
	 * @return
	 */
	@Override
	public int updateMicroblogPic(MicroblogPic microblogPic){
		return sqlSession.update("microblogPicBase.update_microblogPic", microblogPic);
	}
	/**
	 * 批量更新(微博图片信息)信息
	 * @param microblogPicList
	 * @return
	 */
	@Override
	public int updateMicroblogPicBatch(List<MicroblogPic> microblogPicList) {
		return sqlSession.update("microblogPicBase.update_microblogPic_Batch", microblogPicList);
	}
	
	/**
	 * 根据序列号删除(微博图片信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteMicroblogPicLogic(java.math.BigInteger id){
		MicroblogPic microblogPic = new MicroblogPic();
		microblogPic.setId(id);
		microblogPic.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("microblogPicBase.delete_microblogPic_Logic",microblogPic);
	}
	
	/**
	 * 根据Id 批量删除(微博图片信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteMicroblogPicLogicBatch(List<java.math.BigInteger> idList) {
		List<MicroblogPic> delList = new java.util.ArrayList<MicroblogPic>();
		for(java.math.BigInteger id:idList){
			MicroblogPic microblogPic = new MicroblogPic();
			microblogPic.setId(id);
			microblogPic.setSys0DelState(RecordState_DELETED);
			delList.add(microblogPic);
		}
		return sqlSession.update("microblogPicBase.delete_microblogPic_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(微博图片信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteMicroblogPic(java.math.BigInteger id){
//		return sqlSession.delete("microblogPicBase.delete_microblogPic", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(微博图片信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteMicroblogPicBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("microblogPicBase.delete_microblogPic_Batch", idList);
//	}
	
	
}
