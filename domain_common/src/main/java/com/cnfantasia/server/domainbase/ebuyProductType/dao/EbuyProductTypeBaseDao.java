package com.cnfantasia.server.domainbase.ebuyProductType.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyProductType.entity.EbuyProductType;

/**
 * 描述:(商品类别) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EbuyProductTypeBaseDao extends AbstractBaseDao implements IEbuyProductTypeBaseDao{
	/**
	 * 根据条件查询(商品类别)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyProductType> selectEbuyProductTypeByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("ebuyProductTypeBase.select_ebuyProductType",paramMap);
	}
	/**
	 * 按条件分页查询(商品类别)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyProductType> selectEbuyProductTypeByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEbuyProductTypeCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EbuyProductType> resMap= sqlSession.selectList("ebuyProductTypeBase.select_ebuyProductType_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(商品类别)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyProductType selectEbuyProductTypeBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("ebuyProductTypeBase.select_ebuyProductType_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(商品类别)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEbuyProductTypeCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("ebuyProductTypeBase.select_ebuyProductType_count", paramMap);
	}
	/**
	 * 往(商品类别)新增一条记录
	 * @param ebuyProductType
	 * @return
	 */
	@Override
	public int insertEbuyProductType(EbuyProductType ebuyProductType){
		return sqlSession.insert("ebuyProductTypeBase.insert_ebuyProductType",ebuyProductType);
	}
	/**
	 * 批量新增(商品类别)信息
	 * @param ebuyProductTypeList
	 * @return
	 */
	@Override
	public int insertEbuyProductTypeBatch(List<EbuyProductType> ebuyProductTypeList) {
		return sqlSession.insert("ebuyProductTypeBase.insert_ebuyProductType_Batch",ebuyProductTypeList);
	}
	
	/**
	 * 更新(商品类别)信息
	 * @param ebuyProductType
	 * @return
	 */
	@Override
	public int updateEbuyProductType(EbuyProductType ebuyProductType){
		return sqlSession.update("ebuyProductTypeBase.update_ebuyProductType", ebuyProductType);
	}
	/**
	 * 批量更新(商品类别)信息
	 * @param ebuyProductTypeList
	 * @return
	 */
	@Override
	public int updateEbuyProductTypeBatch(List<EbuyProductType> ebuyProductTypeList) {
		return sqlSession.update("ebuyProductTypeBase.update_ebuyProductType_Batch", ebuyProductTypeList);
	}
	
	/**
	 * 根据序列号删除(商品类别)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyProductTypeLogic(java.math.BigInteger id){
		EbuyProductType ebuyProductType = new EbuyProductType();
		ebuyProductType.setId(id);
		ebuyProductType.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("ebuyProductTypeBase.delete_ebuyProductType_Logic",ebuyProductType);
	}
	
	/**
	 * 根据Id 批量删除(商品类别)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyProductTypeLogicBatch(List<java.math.BigInteger> idList) {
		List<EbuyProductType> delList = new java.util.ArrayList<EbuyProductType>();
		for(java.math.BigInteger id:idList){
			EbuyProductType ebuyProductType = new EbuyProductType();
			ebuyProductType.setId(id);
			ebuyProductType.setSys0DelState(RecordState_DELETED);
			delList.add(ebuyProductType);
		}
		return sqlSession.update("ebuyProductTypeBase.delete_ebuyProductType_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(商品类别)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductType(java.math.BigInteger id){
//		return sqlSession.delete("ebuyProductTypeBase.delete_ebuyProductType", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(商品类别)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductTypeBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("ebuyProductTypeBase.delete_ebuyProductType_Batch", idList);
//	}
	
	
}
