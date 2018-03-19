package com.cnfantasia.server.domainbase.ebuyProductParameters.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyProductParameters.entity.EbuyProductParameters;

/**
 * 描述:(产品参数) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EbuyProductParametersBaseDao extends AbstractBaseDao implements IEbuyProductParametersBaseDao{
	/**
	 * 根据条件查询(产品参数)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyProductParameters> selectEbuyProductParametersByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("ebuyProductParametersBase.select_ebuyProductParameters",paramMap);
	}
	/**
	 * 按条件分页查询(产品参数)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyProductParameters> selectEbuyProductParametersByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEbuyProductParametersCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EbuyProductParameters> resMap= sqlSession.selectList("ebuyProductParametersBase.select_ebuyProductParameters_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(产品参数)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyProductParameters selectEbuyProductParametersBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("ebuyProductParametersBase.select_ebuyProductParameters_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(产品参数)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEbuyProductParametersCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("ebuyProductParametersBase.select_ebuyProductParameters_count", paramMap);
	}
	/**
	 * 往(产品参数)新增一条记录
	 * @param ebuyProductParameters
	 * @return
	 */
	@Override
	public int insertEbuyProductParameters(EbuyProductParameters ebuyProductParameters){
		return sqlSession.insert("ebuyProductParametersBase.insert_ebuyProductParameters",ebuyProductParameters);
	}
	/**
	 * 批量新增(产品参数)信息
	 * @param ebuyProductParametersList
	 * @return
	 */
	@Override
	public int insertEbuyProductParametersBatch(List<EbuyProductParameters> ebuyProductParametersList) {
		return sqlSession.insert("ebuyProductParametersBase.insert_ebuyProductParameters_Batch",ebuyProductParametersList);
	}
	
	/**
	 * 更新(产品参数)信息
	 * @param ebuyProductParameters
	 * @return
	 */
	@Override
	public int updateEbuyProductParameters(EbuyProductParameters ebuyProductParameters){
		return sqlSession.update("ebuyProductParametersBase.update_ebuyProductParameters", ebuyProductParameters);
	}
	/**
	 * 批量更新(产品参数)信息
	 * @param ebuyProductParametersList
	 * @return
	 */
	@Override
	public int updateEbuyProductParametersBatch(List<EbuyProductParameters> ebuyProductParametersList) {
		return sqlSession.update("ebuyProductParametersBase.update_ebuyProductParameters_Batch", ebuyProductParametersList);
	}
	
	/**
	 * 根据序列号删除(产品参数)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyProductParametersLogic(java.math.BigInteger id){
		EbuyProductParameters ebuyProductParameters = new EbuyProductParameters();
		ebuyProductParameters.setId(id);
		ebuyProductParameters.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("ebuyProductParametersBase.delete_ebuyProductParameters_Logic",ebuyProductParameters);
	}
	
	/**
	 * 根据Id 批量删除(产品参数)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyProductParametersLogicBatch(List<java.math.BigInteger> idList) {
		List<EbuyProductParameters> delList = new java.util.ArrayList<EbuyProductParameters>();
		for(java.math.BigInteger id:idList){
			EbuyProductParameters ebuyProductParameters = new EbuyProductParameters();
			ebuyProductParameters.setId(id);
			ebuyProductParameters.setSys0DelState(RecordState_DELETED);
			delList.add(ebuyProductParameters);
		}
		return sqlSession.update("ebuyProductParametersBase.delete_ebuyProductParameters_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(产品参数)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductParameters(java.math.BigInteger id){
//		return sqlSession.delete("ebuyProductParametersBase.delete_ebuyProductParameters", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(产品参数)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductParametersBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("ebuyProductParametersBase.delete_ebuyProductParameters_Batch", idList);
//	}
	
	
}
