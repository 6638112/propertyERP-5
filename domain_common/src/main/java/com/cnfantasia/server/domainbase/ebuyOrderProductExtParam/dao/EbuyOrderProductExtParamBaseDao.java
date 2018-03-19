package com.cnfantasia.server.domainbase.ebuyOrderProductExtParam.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyOrderProductExtParam.entity.EbuyOrderProductExtParam;

/**
 * 描述:(订单商品关系附件参数信息) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EbuyOrderProductExtParamBaseDao extends AbstractBaseDao implements IEbuyOrderProductExtParamBaseDao{
	/**
	 * 根据条件查询(订单商品关系附件参数信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyOrderProductExtParam> selectEbuyOrderProductExtParamByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("ebuyOrderProductExtParamBase.select_ebuyOrderProductExtParam",paramMap);
	}
	/**
	 * 按条件分页查询(订单商品关系附件参数信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyOrderProductExtParam> selectEbuyOrderProductExtParamByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEbuyOrderProductExtParamCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EbuyOrderProductExtParam> resMap= sqlSession.selectList("ebuyOrderProductExtParamBase.select_ebuyOrderProductExtParam_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(订单商品关系附件参数信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyOrderProductExtParam selectEbuyOrderProductExtParamBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("ebuyOrderProductExtParamBase.select_ebuyOrderProductExtParam_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(订单商品关系附件参数信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEbuyOrderProductExtParamCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("ebuyOrderProductExtParamBase.select_ebuyOrderProductExtParam_count", paramMap);
	}
	/**
	 * 往(订单商品关系附件参数信息)新增一条记录
	 * @param ebuyOrderProductExtParam
	 * @return
	 */
	@Override
	public int insertEbuyOrderProductExtParam(EbuyOrderProductExtParam ebuyOrderProductExtParam){
		return sqlSession.insert("ebuyOrderProductExtParamBase.insert_ebuyOrderProductExtParam",ebuyOrderProductExtParam);
	}
	/**
	 * 批量新增(订单商品关系附件参数信息)信息
	 * @param ebuyOrderProductExtParamList
	 * @return
	 */
	@Override
	public int insertEbuyOrderProductExtParamBatch(List<EbuyOrderProductExtParam> ebuyOrderProductExtParamList) {
		return sqlSession.insert("ebuyOrderProductExtParamBase.insert_ebuyOrderProductExtParam_Batch",ebuyOrderProductExtParamList);
	}
	
	/**
	 * 更新(订单商品关系附件参数信息)信息
	 * @param ebuyOrderProductExtParam
	 * @return
	 */
	@Override
	public int updateEbuyOrderProductExtParam(EbuyOrderProductExtParam ebuyOrderProductExtParam){
		return sqlSession.update("ebuyOrderProductExtParamBase.update_ebuyOrderProductExtParam", ebuyOrderProductExtParam);
	}
	/**
	 * 批量更新(订单商品关系附件参数信息)信息
	 * @param ebuyOrderProductExtParamList
	 * @return
	 */
	@Override
	public int updateEbuyOrderProductExtParamBatch(List<EbuyOrderProductExtParam> ebuyOrderProductExtParamList) {
		return sqlSession.update("ebuyOrderProductExtParamBase.update_ebuyOrderProductExtParam_Batch", ebuyOrderProductExtParamList);
	}
	
	/**
	 * 根据序列号删除(订单商品关系附件参数信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyOrderProductExtParamLogic(java.math.BigInteger id){
		EbuyOrderProductExtParam ebuyOrderProductExtParam = new EbuyOrderProductExtParam();
		ebuyOrderProductExtParam.setId(id);
		ebuyOrderProductExtParam.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("ebuyOrderProductExtParamBase.delete_ebuyOrderProductExtParam_Logic",ebuyOrderProductExtParam);
	}
	
	/**
	 * 根据Id 批量删除(订单商品关系附件参数信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyOrderProductExtParamLogicBatch(List<java.math.BigInteger> idList) {
		List<EbuyOrderProductExtParam> delList = new java.util.ArrayList<EbuyOrderProductExtParam>();
		for(java.math.BigInteger id:idList){
			EbuyOrderProductExtParam ebuyOrderProductExtParam = new EbuyOrderProductExtParam();
			ebuyOrderProductExtParam.setId(id);
			ebuyOrderProductExtParam.setSys0DelState(RecordState_DELETED);
			delList.add(ebuyOrderProductExtParam);
		}
		return sqlSession.update("ebuyOrderProductExtParamBase.delete_ebuyOrderProductExtParam_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(订单商品关系附件参数信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyOrderProductExtParam(java.math.BigInteger id){
//		return sqlSession.delete("ebuyOrderProductExtParamBase.delete_ebuyOrderProductExtParam", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(订单商品关系附件参数信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyOrderProductExtParamBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("ebuyOrderProductExtParamBase.delete_ebuyOrderProductExtParam_Batch", idList);
//	}
	
	
}
