package com.cnfantasia.server.domainbase.shortUrl.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.shortUrl.entity.ShortUrl;

/**
 * 描述:(短链接映射表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class ShortUrlBaseDao extends AbstractBaseDao implements IShortUrlBaseDao{
	/**
	 * 根据条件查询(短链接映射表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<ShortUrl> selectShortUrlByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("shortUrlBase.select_shortUrl",paramMap);
	}
	/**
	 * 按条件分页查询(短链接映射表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<ShortUrl> selectShortUrlByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectShortUrlCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<ShortUrl> resMap= sqlSession.selectList("shortUrlBase.select_shortUrl_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(短链接映射表)信息
	 * @param id
	 * @return
	 */
	@Override
	public ShortUrl selectShortUrlBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("shortUrlBase.select_shortUrl_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(短链接映射表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectShortUrlCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("shortUrlBase.select_shortUrl_count", paramMap);
	}
	/**
	 * 往(短链接映射表)新增一条记录
	 * @param shortUrl
	 * @return
	 */
	@Override
	public int insertShortUrl(ShortUrl shortUrl){
		return sqlSession.insert("shortUrlBase.insert_shortUrl",shortUrl);
	}
	/**
	 * 批量新增(短链接映射表)信息
	 * @param shortUrlList
	 * @return
	 */
	@Override
	public int insertShortUrlBatch(List<ShortUrl> shortUrlList) {
		if (shortUrlList == null || shortUrlList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = shortUrlList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<ShortUrl> batchList = shortUrlList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("shortUrlBase.insert_shortUrl_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(短链接映射表)信息
	 * @param shortUrl
	 * @return
	 */
	@Override
	public int updateShortUrl(ShortUrl shortUrl){
		return sqlSession.update("shortUrlBase.update_shortUrl", shortUrl);
	}
	/**
	 * 批量更新(短链接映射表)信息
	 * @param shortUrlList
	 * @return
	 */
	@Override
	public int updateShortUrlBatch(List<ShortUrl> shortUrlList) {
		if (shortUrlList == null || shortUrlList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("shortUrlBase.update_shortUrl_Batch", shortUrlList);
	}
	
	/**
	 * 根据序列号删除(短链接映射表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteShortUrlLogic(java.math.BigInteger id){
		ShortUrl shortUrl = new ShortUrl();
		shortUrl.setId(id);
		shortUrl.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("shortUrlBase.delete_shortUrl_Logic",shortUrl);
	}
	
	/**
	 * 根据Id 批量删除(短链接映射表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteShortUrlLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<ShortUrl> delList = new java.util.ArrayList<ShortUrl>();
		for(java.math.BigInteger id:idList){
			ShortUrl shortUrl = new ShortUrl();
			shortUrl.setId(id);
			shortUrl.setSys0DelState(RecordState_DELETED);
			delList.add(shortUrl);
		}
		return sqlSession.update("shortUrlBase.delete_shortUrl_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(短链接映射表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteShortUrl(java.math.BigInteger id){
//		return sqlSession.delete("shortUrlBase.delete_shortUrl", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(短链接映射表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteShortUrlBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("shortUrlBase.delete_shortUrl_Batch", idList);
//	}
	
	
}
