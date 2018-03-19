package com.cnfantasia.server.domainbase.ebuyOrderDeliveryType.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyOrderDeliveryType.entity.EbuyOrderDeliveryType;

/**
 * 描述:(订单的配送设置) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EbuyOrderDeliveryTypeBaseDao extends AbstractBaseDao implements IEbuyOrderDeliveryTypeBaseDao{
	/**
	 * 根据条件查询(订单的配送设置)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyOrderDeliveryType> selectEbuyOrderDeliveryTypeByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("ebuyOrderDeliveryTypeBase.select_ebuyOrderDeliveryType",paramMap);
	}
	/**
	 * 按条件分页查询(订单的配送设置)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyOrderDeliveryType> selectEbuyOrderDeliveryTypeByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEbuyOrderDeliveryTypeCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EbuyOrderDeliveryType> resMap= sqlSession.selectList("ebuyOrderDeliveryTypeBase.select_ebuyOrderDeliveryType_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(订单的配送设置)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyOrderDeliveryType selectEbuyOrderDeliveryTypeBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("ebuyOrderDeliveryTypeBase.select_ebuyOrderDeliveryType_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(订单的配送设置)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEbuyOrderDeliveryTypeCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("ebuyOrderDeliveryTypeBase.select_ebuyOrderDeliveryType_count", paramMap);
	}
	/**
	 * 往(订单的配送设置)新增一条记录
	 * @param ebuyOrderDeliveryType
	 * @return
	 */
	@Override
	public int insertEbuyOrderDeliveryType(EbuyOrderDeliveryType ebuyOrderDeliveryType){
		return sqlSession.insert("ebuyOrderDeliveryTypeBase.insert_ebuyOrderDeliveryType",ebuyOrderDeliveryType);
	}
	/**
	 * 批量新增(订单的配送设置)信息
	 * @param ebuyOrderDeliveryTypeList
	 * @return
	 */
	@Override
	public int insertEbuyOrderDeliveryTypeBatch(List<EbuyOrderDeliveryType> ebuyOrderDeliveryTypeList) {
		if (ebuyOrderDeliveryTypeList == null || ebuyOrderDeliveryTypeList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = ebuyOrderDeliveryTypeList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<EbuyOrderDeliveryType> batchList = ebuyOrderDeliveryTypeList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("ebuyOrderDeliveryTypeBase.insert_ebuyOrderDeliveryType_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(订单的配送设置)信息
	 * @param ebuyOrderDeliveryType
	 * @return
	 */
	@Override
	public int updateEbuyOrderDeliveryType(EbuyOrderDeliveryType ebuyOrderDeliveryType){
		return sqlSession.update("ebuyOrderDeliveryTypeBase.update_ebuyOrderDeliveryType", ebuyOrderDeliveryType);
	}
	/**
	 * 批量更新(订单的配送设置)信息
	 * @param ebuyOrderDeliveryTypeList
	 * @return
	 */
	@Override
	public int updateEbuyOrderDeliveryTypeBatch(List<EbuyOrderDeliveryType> ebuyOrderDeliveryTypeList) {
		if (ebuyOrderDeliveryTypeList == null || ebuyOrderDeliveryTypeList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("ebuyOrderDeliveryTypeBase.update_ebuyOrderDeliveryType_Batch", ebuyOrderDeliveryTypeList);
	}
	
	/**
	 * 根据序列号删除(订单的配送设置)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	@Override
	public int deleteEbuyOrderDeliveryTypeLogic(java.math.BigInteger id){
		EbuyOrderDeliveryType ebuyOrderDeliveryType = new EbuyOrderDeliveryType();
		ebuyOrderDeliveryType.setId(id);
		ebuyOrderDeliveryType.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("ebuyOrderDeliveryTypeBase.delete_ebuyOrderDeliveryType_Logic",ebuyOrderDeliveryType);
	}
	 */
	/**
	 * 根据Id 批量删除(订单的配送设置)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	@Override
	public int deleteEbuyOrderDeliveryTypeLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<EbuyOrderDeliveryType> delList = new java.util.ArrayList<EbuyOrderDeliveryType>();
		for(java.math.BigInteger id:idList){
			EbuyOrderDeliveryType ebuyOrderDeliveryType = new EbuyOrderDeliveryType();
			ebuyOrderDeliveryType.setId(id);
			ebuyOrderDeliveryType.setSys0DelState(RecordState_DELETED);
			delList.add(ebuyOrderDeliveryType);
		}
		return sqlSession.update("ebuyOrderDeliveryTypeBase.delete_ebuyOrderDeliveryType_Logic_Batch",delList);
	}
	 */
//	/**
//	 * 根据序列号删除(订单的配送设置)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyOrderDeliveryType(java.math.BigInteger id){
//		return sqlSession.delete("ebuyOrderDeliveryTypeBase.delete_ebuyOrderDeliveryType", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(订单的配送设置)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyOrderDeliveryTypeBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("ebuyOrderDeliveryTypeBase.delete_ebuyOrderDeliveryType_Batch", idList);
//	}
	
	
}
