package com.cnfantasia.server.domainbase.jfqStoreProductTypeInfo.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.jfqStoreProductTypeInfo.entity.JfqStoreProductTypeInfo;

/**
 * 描述:(解放区体验店商品类型信息) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class JfqStoreProductTypeInfoBaseDao extends AbstractBaseDao implements IJfqStoreProductTypeInfoBaseDao{
	/**
	 * 根据条件查询(解放区体验店商品类型信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<JfqStoreProductTypeInfo> selectJfqStoreProductTypeInfoByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("jfqStoreProductTypeInfoBase.select_jfqStoreProductTypeInfo",paramMap);
	}
	/**
	 * 按条件分页查询(解放区体验店商品类型信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<JfqStoreProductTypeInfo> selectJfqStoreProductTypeInfoByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectJfqStoreProductTypeInfoCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<JfqStoreProductTypeInfo> resMap= sqlSession.selectList("jfqStoreProductTypeInfoBase.select_jfqStoreProductTypeInfo_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(解放区体验店商品类型信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public JfqStoreProductTypeInfo selectJfqStoreProductTypeInfoBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("jfqStoreProductTypeInfoBase.select_jfqStoreProductTypeInfo_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(解放区体验店商品类型信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectJfqStoreProductTypeInfoCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("jfqStoreProductTypeInfoBase.select_jfqStoreProductTypeInfo_count", paramMap);
	}
	/**
	 * 往(解放区体验店商品类型信息)新增一条记录
	 * @param jfqStoreProductTypeInfo
	 * @return
	 */
	@Override
	public int insertJfqStoreProductTypeInfo(JfqStoreProductTypeInfo jfqStoreProductTypeInfo){
		return sqlSession.insert("jfqStoreProductTypeInfoBase.insert_jfqStoreProductTypeInfo",jfqStoreProductTypeInfo);
	}
	/**
	 * 批量新增(解放区体验店商品类型信息)信息
	 * @param jfqStoreProductTypeInfoList
	 * @return
	 */
	@Override
	public int insertJfqStoreProductTypeInfoBatch(List<JfqStoreProductTypeInfo> jfqStoreProductTypeInfoList) {
		if (jfqStoreProductTypeInfoList == null || jfqStoreProductTypeInfoList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = jfqStoreProductTypeInfoList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<JfqStoreProductTypeInfo> batchList = jfqStoreProductTypeInfoList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("jfqStoreProductTypeInfoBase.insert_jfqStoreProductTypeInfo_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(解放区体验店商品类型信息)信息
	 * @param jfqStoreProductTypeInfo
	 * @return
	 */
	@Override
	public int updateJfqStoreProductTypeInfo(JfqStoreProductTypeInfo jfqStoreProductTypeInfo){
		return sqlSession.update("jfqStoreProductTypeInfoBase.update_jfqStoreProductTypeInfo", jfqStoreProductTypeInfo);
	}
	/**
	 * 批量更新(解放区体验店商品类型信息)信息
	 * @param jfqStoreProductTypeInfoList
	 * @return
	 */
	@Override
	public int updateJfqStoreProductTypeInfoBatch(List<JfqStoreProductTypeInfo> jfqStoreProductTypeInfoList) {
		if (jfqStoreProductTypeInfoList == null || jfqStoreProductTypeInfoList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("jfqStoreProductTypeInfoBase.update_jfqStoreProductTypeInfo_Batch", jfqStoreProductTypeInfoList);
	}
	
	/**
	 * 根据序列号删除(解放区体验店商品类型信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteJfqStoreProductTypeInfoLogic(java.math.BigInteger id){
		JfqStoreProductTypeInfo jfqStoreProductTypeInfo = new JfqStoreProductTypeInfo();
		jfqStoreProductTypeInfo.setId(id);
		jfqStoreProductTypeInfo.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("jfqStoreProductTypeInfoBase.delete_jfqStoreProductTypeInfo_Logic",jfqStoreProductTypeInfo);
	}
	
	/**
	 * 根据Id 批量删除(解放区体验店商品类型信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteJfqStoreProductTypeInfoLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<JfqStoreProductTypeInfo> delList = new java.util.ArrayList<JfqStoreProductTypeInfo>();
		for(java.math.BigInteger id:idList){
			JfqStoreProductTypeInfo jfqStoreProductTypeInfo = new JfqStoreProductTypeInfo();
			jfqStoreProductTypeInfo.setId(id);
			jfqStoreProductTypeInfo.setSys0DelState(RecordState_DELETED);
			delList.add(jfqStoreProductTypeInfo);
		}
		return sqlSession.update("jfqStoreProductTypeInfoBase.delete_jfqStoreProductTypeInfo_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(解放区体验店商品类型信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteJfqStoreProductTypeInfo(java.math.BigInteger id){
//		return sqlSession.delete("jfqStoreProductTypeInfoBase.delete_jfqStoreProductTypeInfo", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(解放区体验店商品类型信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteJfqStoreProductTypeInfoBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("jfqStoreProductTypeInfoBase.delete_jfqStoreProductTypeInfo_Batch", idList);
//	}
	
	
}
