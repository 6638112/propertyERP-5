package com.cnfantasia.server.domainbase.encryptUrl.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.encryptUrl.entity.EncryptUrl;

/**
 * 描述:(加密url) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EncryptUrlBaseDao extends AbstractBaseDao implements IEncryptUrlBaseDao{
	/**
	 * 根据条件查询(加密url)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EncryptUrl> selectEncryptUrlByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("encryptUrlBase.select_encryptUrl",paramMap);
	}
	/**
	 * 按条件分页查询(加密url)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EncryptUrl> selectEncryptUrlByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEncryptUrlCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EncryptUrl> resMap= sqlSession.selectList("encryptUrlBase.select_encryptUrl_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(加密url)信息
	 * @param id
	 * @return
	 */
	@Override
	public EncryptUrl selectEncryptUrlBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("encryptUrlBase.select_encryptUrl_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(加密url)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEncryptUrlCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("encryptUrlBase.select_encryptUrl_count", paramMap);
	}
	/**
	 * 往(加密url)新增一条记录
	 * @param encryptUrl
	 * @return
	 */
	@Override
	public int insertEncryptUrl(EncryptUrl encryptUrl){
		return sqlSession.insert("encryptUrlBase.insert_encryptUrl",encryptUrl);
	}
	/**
	 * 批量新增(加密url)信息
	 * @param encryptUrlList
	 * @return
	 */
	@Override
	public int insertEncryptUrlBatch(List<EncryptUrl> encryptUrlList) {
		return sqlSession.insert("encryptUrlBase.insert_encryptUrl_Batch",encryptUrlList);
	}
	
	/**
	 * 更新(加密url)信息
	 * @param encryptUrl
	 * @return
	 */
	@Override
	public int updateEncryptUrl(EncryptUrl encryptUrl){
		return sqlSession.update("encryptUrlBase.update_encryptUrl", encryptUrl);
	}
	/**
	 * 批量更新(加密url)信息
	 * @param encryptUrlList
	 * @return
	 */
	@Override
	public int updateEncryptUrlBatch(List<EncryptUrl> encryptUrlList) {
		return sqlSession.update("encryptUrlBase.update_encryptUrl_Batch", encryptUrlList);
	}
	
	/**
	 * 根据序列号删除(加密url)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEncryptUrlLogic(java.math.BigInteger id){
		EncryptUrl encryptUrl = new EncryptUrl();
		encryptUrl.setId(id);
		encryptUrl.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("encryptUrlBase.delete_encryptUrl_Logic",encryptUrl);
	}
	
	/**
	 * 根据Id 批量删除(加密url)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEncryptUrlLogicBatch(List<java.math.BigInteger> idList) {
		List<EncryptUrl> delList = new java.util.ArrayList<EncryptUrl>();
		for(java.math.BigInteger id:idList){
			EncryptUrl encryptUrl = new EncryptUrl();
			encryptUrl.setId(id);
			encryptUrl.setSys0DelState(RecordState_DELETED);
			delList.add(encryptUrl);
		}
		return sqlSession.update("encryptUrlBase.delete_encryptUrl_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(加密url)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEncryptUrl(java.math.BigInteger id){
//		return sqlSession.delete("encryptUrlBase.delete_encryptUrl", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(加密url)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEncryptUrlBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("encryptUrlBase.delete_encryptUrl_Batch", idList);
//	}
	
	
}
