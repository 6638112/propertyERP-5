package com.cnfantasia.server.domainbase.microblogContent.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.microblogContent.entity.MicroblogContent;

/**
 * 描述:(微博信息表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class MicroblogContentBaseDao extends AbstractBaseDao implements IMicroblogContentBaseDao{
	/**
	 * 根据条件查询(微博信息表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<MicroblogContent> selectMicroblogContentByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("microblogContentBase.select_microblogContent",paramMap);
	}
	/**
	 * 按条件分页查询(微博信息表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<MicroblogContent> selectMicroblogContentByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectMicroblogContentCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<MicroblogContent> resMap= sqlSession.selectList("microblogContentBase.select_microblogContent_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(微博信息表)信息
	 * @param id
	 * @return
	 */
	@Override
	public MicroblogContent selectMicroblogContentBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("microblogContentBase.select_microblogContent_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(微博信息表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectMicroblogContentCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("microblogContentBase.select_microblogContent_count", paramMap);
	}
	/**
	 * 往(微博信息表)新增一条记录
	 * @param microblogContent
	 * @return
	 */
	@Override
	public int insertMicroblogContent(MicroblogContent microblogContent){
		return sqlSession.insert("microblogContentBase.insert_microblogContent",microblogContent);
	}
	/**
	 * 批量新增(微博信息表)信息
	 * @param microblogContentList
	 * @return
	 */
	@Override
	public int insertMicroblogContentBatch(List<MicroblogContent> microblogContentList) {
		return sqlSession.insert("microblogContentBase.insert_microblogContent_Batch",microblogContentList);
	}
	
	/**
	 * 更新(微博信息表)信息
	 * @param microblogContent
	 * @return
	 */
	@Override
	public int updateMicroblogContent(MicroblogContent microblogContent){
		return sqlSession.update("microblogContentBase.update_microblogContent", microblogContent);
	}
	/**
	 * 批量更新(微博信息表)信息
	 * @param microblogContentList
	 * @return
	 */
	@Override
	public int updateMicroblogContentBatch(List<MicroblogContent> microblogContentList) {
		return sqlSession.update("microblogContentBase.update_microblogContent_Batch", microblogContentList);
	}
	
	/**
	 * 根据序列号删除(微博信息表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteMicroblogContentLogic(java.math.BigInteger id){
		MicroblogContent microblogContent = new MicroblogContent();
		microblogContent.setId(id);
		microblogContent.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("microblogContentBase.delete_microblogContent_Logic",microblogContent);
	}
	
	/**
	 * 根据Id 批量删除(微博信息表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteMicroblogContentLogicBatch(List<java.math.BigInteger> idList) {
		List<MicroblogContent> delList = new java.util.ArrayList<MicroblogContent>();
		for(java.math.BigInteger id:idList){
			MicroblogContent microblogContent = new MicroblogContent();
			microblogContent.setId(id);
			microblogContent.setSys0DelState(RecordState_DELETED);
			delList.add(microblogContent);
		}
		return sqlSession.update("microblogContentBase.delete_microblogContent_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(微博信息表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteMicroblogContent(java.math.BigInteger id){
//		return sqlSession.delete("microblogContentBase.delete_microblogContent", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(微博信息表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteMicroblogContentBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("microblogContentBase.delete_microblogContent_Batch", idList);
//	}
	
	
}
