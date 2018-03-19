package com.cnfantasia.server.domainbase.ebuyAuth.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyAuth.entity.EbuyAuth;

/**
 * 描述:(认证类别信息) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EbuyAuthBaseDao extends AbstractBaseDao implements IEbuyAuthBaseDao{
	/**
	 * 根据条件查询(认证类别信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyAuth> selectEbuyAuthByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("ebuyAuthBase.select_ebuyAuth",paramMap);
	}
	/**
	 * 按条件分页查询(认证类别信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyAuth> selectEbuyAuthByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEbuyAuthCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EbuyAuth> resMap= sqlSession.selectList("ebuyAuthBase.select_ebuyAuth_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(认证类别信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyAuth selectEbuyAuthBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("ebuyAuthBase.select_ebuyAuth_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(认证类别信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEbuyAuthCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("ebuyAuthBase.select_ebuyAuth_count", paramMap);
	}
	/**
	 * 往(认证类别信息)新增一条记录
	 * @param ebuyAuth
	 * @return
	 */
	@Override
	public int insertEbuyAuth(EbuyAuth ebuyAuth){
		return sqlSession.insert("ebuyAuthBase.insert_ebuyAuth",ebuyAuth);
	}
	/**
	 * 批量新增(认证类别信息)信息
	 * @param ebuyAuthList
	 * @return
	 */
	@Override
	public int insertEbuyAuthBatch(List<EbuyAuth> ebuyAuthList) {
		return sqlSession.insert("ebuyAuthBase.insert_ebuyAuth_Batch",ebuyAuthList);
	}
	
	/**
	 * 更新(认证类别信息)信息
	 * @param ebuyAuth
	 * @return
	 */
	@Override
	public int updateEbuyAuth(EbuyAuth ebuyAuth){
		return sqlSession.update("ebuyAuthBase.update_ebuyAuth", ebuyAuth);
	}
	/**
	 * 批量更新(认证类别信息)信息
	 * @param ebuyAuthList
	 * @return
	 */
	@Override
	public int updateEbuyAuthBatch(List<EbuyAuth> ebuyAuthList) {
		return sqlSession.update("ebuyAuthBase.update_ebuyAuth_Batch", ebuyAuthList);
	}
	
	/**
	 * 根据序列号删除(认证类别信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyAuthLogic(java.math.BigInteger id){
		EbuyAuth ebuyAuth = new EbuyAuth();
		ebuyAuth.setId(id);
		ebuyAuth.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("ebuyAuthBase.delete_ebuyAuth_Logic",ebuyAuth);
	}
	
	/**
	 * 根据Id 批量删除(认证类别信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyAuthLogicBatch(List<java.math.BigInteger> idList) {
		List<EbuyAuth> delList = new java.util.ArrayList<EbuyAuth>();
		for(java.math.BigInteger id:idList){
			EbuyAuth ebuyAuth = new EbuyAuth();
			ebuyAuth.setId(id);
			ebuyAuth.setSys0DelState(RecordState_DELETED);
			delList.add(ebuyAuth);
		}
		return sqlSession.update("ebuyAuthBase.delete_ebuyAuth_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(认证类别信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyAuth(java.math.BigInteger id){
//		return sqlSession.delete("ebuyAuthBase.delete_ebuyAuth", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(认证类别信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyAuthBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("ebuyAuthBase.delete_ebuyAuth_Batch", idList);
//	}
	
	
}
