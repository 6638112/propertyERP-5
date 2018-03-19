package com.cnfantasia.server.domainbase.ebuyAdvertise.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyAdvertise.entity.EbuyAdvertise;

/**
 * 描述:() DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EbuyAdvertiseBaseDao extends AbstractBaseDao implements IEbuyAdvertiseBaseDao{
	/**
	 * 根据条件查询()信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyAdvertise> selectEbuyAdvertiseByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("ebuyAdvertiseBase.select_ebuyAdvertise",paramMap);
	}
	/**
	 * 按条件分页查询()信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyAdvertise> selectEbuyAdvertiseByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEbuyAdvertiseCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EbuyAdvertise> resMap= sqlSession.selectList("ebuyAdvertiseBase.select_ebuyAdvertise_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询()信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyAdvertise selectEbuyAdvertiseBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("ebuyAdvertiseBase.select_ebuyAdvertise_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的()记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEbuyAdvertiseCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("ebuyAdvertiseBase.select_ebuyAdvertise_count", paramMap);
	}
	/**
	 * 往()新增一条记录
	 * @param ebuyAdvertise
	 * @return
	 */
	@Override
	public int insertEbuyAdvertise(EbuyAdvertise ebuyAdvertise){
		return sqlSession.insert("ebuyAdvertiseBase.insert_ebuyAdvertise",ebuyAdvertise);
	}
	/**
	 * 批量新增()信息
	 * @param ebuyAdvertiseList
	 * @return
	 */
	@Override
	public int insertEbuyAdvertiseBatch(List<EbuyAdvertise> ebuyAdvertiseList) {
		return sqlSession.insert("ebuyAdvertiseBase.insert_ebuyAdvertise_Batch",ebuyAdvertiseList);
	}
	
	/**
	 * 更新()信息
	 * @param ebuyAdvertise
	 * @return
	 */
	@Override
	public int updateEbuyAdvertise(EbuyAdvertise ebuyAdvertise){
		return sqlSession.update("ebuyAdvertiseBase.update_ebuyAdvertise", ebuyAdvertise);
	}
	/**
	 * 批量更新()信息
	 * @param ebuyAdvertiseList
	 * @return
	 */
	@Override
	public int updateEbuyAdvertiseBatch(List<EbuyAdvertise> ebuyAdvertiseList) {
		return sqlSession.update("ebuyAdvertiseBase.update_ebuyAdvertise_Batch", ebuyAdvertiseList);
	}
	
	/**
	 * 根据序列号删除()信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyAdvertiseLogic(java.math.BigInteger id){
		EbuyAdvertise ebuyAdvertise = new EbuyAdvertise();
		ebuyAdvertise.setId(id);
		ebuyAdvertise.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("ebuyAdvertiseBase.delete_ebuyAdvertise_Logic",ebuyAdvertise);
	}
	
	/**
	 * 根据Id 批量删除()信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyAdvertiseLogicBatch(List<java.math.BigInteger> idList) {
		List<EbuyAdvertise> delList = new java.util.ArrayList<EbuyAdvertise>();
		for(java.math.BigInteger id:idList){
			EbuyAdvertise ebuyAdvertise = new EbuyAdvertise();
			ebuyAdvertise.setId(id);
			ebuyAdvertise.setSys0DelState(RecordState_DELETED);
			delList.add(ebuyAdvertise);
		}
		return sqlSession.update("ebuyAdvertiseBase.delete_ebuyAdvertise_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除()信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyAdvertise(java.math.BigInteger id){
//		return sqlSession.delete("ebuyAdvertiseBase.delete_ebuyAdvertise", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除()信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyAdvertiseBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("ebuyAdvertiseBase.delete_ebuyAdvertise_Batch", idList);
//	}
	
	
}
