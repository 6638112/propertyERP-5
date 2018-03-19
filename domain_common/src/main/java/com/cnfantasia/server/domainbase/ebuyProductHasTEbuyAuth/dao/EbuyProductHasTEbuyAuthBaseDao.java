package com.cnfantasia.server.domainbase.ebuyProductHasTEbuyAuth.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyProductHasTEbuyAuth.entity.EbuyProductHasTEbuyAuth;

/**
 * 描述:() DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EbuyProductHasTEbuyAuthBaseDao extends AbstractBaseDao implements IEbuyProductHasTEbuyAuthBaseDao{
	/**
	 * 根据条件查询()信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyProductHasTEbuyAuth> selectEbuyProductHasTEbuyAuthByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("ebuyProductHasTEbuyAuthBase.select_ebuyProductHasTEbuyAuth",paramMap);
	}
	/**
	 * 按条件分页查询()信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyProductHasTEbuyAuth> selectEbuyProductHasTEbuyAuthByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEbuyProductHasTEbuyAuthCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EbuyProductHasTEbuyAuth> resMap= sqlSession.selectList("ebuyProductHasTEbuyAuthBase.select_ebuyProductHasTEbuyAuth_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询()信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyProductHasTEbuyAuth selectEbuyProductHasTEbuyAuthBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("ebuyProductHasTEbuyAuthBase.select_ebuyProductHasTEbuyAuth_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的()记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEbuyProductHasTEbuyAuthCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("ebuyProductHasTEbuyAuthBase.select_ebuyProductHasTEbuyAuth_count", paramMap);
	}
	/**
	 * 往()新增一条记录
	 * @param ebuyProductHasTEbuyAuth
	 * @return
	 */
	@Override
	public int insertEbuyProductHasTEbuyAuth(EbuyProductHasTEbuyAuth ebuyProductHasTEbuyAuth){
		return sqlSession.insert("ebuyProductHasTEbuyAuthBase.insert_ebuyProductHasTEbuyAuth",ebuyProductHasTEbuyAuth);
	}
	/**
	 * 批量新增()信息
	 * @param ebuyProductHasTEbuyAuthList
	 * @return
	 */
	@Override
	public int insertEbuyProductHasTEbuyAuthBatch(List<EbuyProductHasTEbuyAuth> ebuyProductHasTEbuyAuthList) {
		return sqlSession.insert("ebuyProductHasTEbuyAuthBase.insert_ebuyProductHasTEbuyAuth_Batch",ebuyProductHasTEbuyAuthList);
	}
	
	/**
	 * 更新()信息
	 * @param ebuyProductHasTEbuyAuth
	 * @return
	 */
	@Override
	public int updateEbuyProductHasTEbuyAuth(EbuyProductHasTEbuyAuth ebuyProductHasTEbuyAuth){
		return sqlSession.update("ebuyProductHasTEbuyAuthBase.update_ebuyProductHasTEbuyAuth", ebuyProductHasTEbuyAuth);
	}
	/**
	 * 批量更新()信息
	 * @param ebuyProductHasTEbuyAuthList
	 * @return
	 */
	@Override
	public int updateEbuyProductHasTEbuyAuthBatch(List<EbuyProductHasTEbuyAuth> ebuyProductHasTEbuyAuthList) {
		return sqlSession.update("ebuyProductHasTEbuyAuthBase.update_ebuyProductHasTEbuyAuth_Batch", ebuyProductHasTEbuyAuthList);
	}
	
	/**
	 * 根据序列号删除()信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyProductHasTEbuyAuthLogic(java.math.BigInteger id){
		EbuyProductHasTEbuyAuth ebuyProductHasTEbuyAuth = new EbuyProductHasTEbuyAuth();
		ebuyProductHasTEbuyAuth.setId(id);
		ebuyProductHasTEbuyAuth.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("ebuyProductHasTEbuyAuthBase.delete_ebuyProductHasTEbuyAuth_Logic",ebuyProductHasTEbuyAuth);
	}
	
	/**
	 * 根据Id 批量删除()信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyProductHasTEbuyAuthLogicBatch(List<java.math.BigInteger> idList) {
		List<EbuyProductHasTEbuyAuth> delList = new java.util.ArrayList<EbuyProductHasTEbuyAuth>();
		for(java.math.BigInteger id:idList){
			EbuyProductHasTEbuyAuth ebuyProductHasTEbuyAuth = new EbuyProductHasTEbuyAuth();
			ebuyProductHasTEbuyAuth.setId(id);
			ebuyProductHasTEbuyAuth.setSys0DelState(RecordState_DELETED);
			delList.add(ebuyProductHasTEbuyAuth);
		}
		return sqlSession.update("ebuyProductHasTEbuyAuthBase.delete_ebuyProductHasTEbuyAuth_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除()信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductHasTEbuyAuth(java.math.BigInteger id){
//		return sqlSession.delete("ebuyProductHasTEbuyAuthBase.delete_ebuyProductHasTEbuyAuth", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除()信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductHasTEbuyAuthBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("ebuyProductHasTEbuyAuthBase.delete_ebuyProductHasTEbuyAuth_Batch", idList);
//	}
	
	
}
