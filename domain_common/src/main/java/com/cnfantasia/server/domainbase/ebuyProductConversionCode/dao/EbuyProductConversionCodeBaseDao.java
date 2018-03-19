package com.cnfantasia.server.domainbase.ebuyProductConversionCode.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyProductConversionCode.entity.EbuyProductConversionCode;

/**
 * 描述:(商品兑换码表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EbuyProductConversionCodeBaseDao extends AbstractBaseDao implements IEbuyProductConversionCodeBaseDao{
	/**
	 * 根据条件查询(商品兑换码表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyProductConversionCode> selectEbuyProductConversionCodeByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("ebuyProductConversionCodeBase.select_ebuyProductConversionCode",paramMap);
	}
	/**
	 * 按条件分页查询(商品兑换码表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyProductConversionCode> selectEbuyProductConversionCodeByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEbuyProductConversionCodeCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EbuyProductConversionCode> resMap= sqlSession.selectList("ebuyProductConversionCodeBase.select_ebuyProductConversionCode_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(商品兑换码表)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyProductConversionCode selectEbuyProductConversionCodeBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("ebuyProductConversionCodeBase.select_ebuyProductConversionCode_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(商品兑换码表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEbuyProductConversionCodeCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("ebuyProductConversionCodeBase.select_ebuyProductConversionCode_count", paramMap);
	}
	/**
	 * 往(商品兑换码表)新增一条记录
	 * @param ebuyProductConversionCode
	 * @return
	 */
	@Override
	public int insertEbuyProductConversionCode(EbuyProductConversionCode ebuyProductConversionCode){
		return sqlSession.insert("ebuyProductConversionCodeBase.insert_ebuyProductConversionCode",ebuyProductConversionCode);
	}
	/**
	 * 批量新增(商品兑换码表)信息
	 * @param ebuyProductConversionCodeList
	 * @return
	 */
	@Override
	public int insertEbuyProductConversionCodeBatch(List<EbuyProductConversionCode> ebuyProductConversionCodeList) {
		return sqlSession.insert("ebuyProductConversionCodeBase.insert_ebuyProductConversionCode_Batch",ebuyProductConversionCodeList);
	}
	
	/**
	 * 更新(商品兑换码表)信息
	 * @param ebuyProductConversionCode
	 * @return
	 */
	@Override
	public int updateEbuyProductConversionCode(EbuyProductConversionCode ebuyProductConversionCode){
		return sqlSession.update("ebuyProductConversionCodeBase.update_ebuyProductConversionCode", ebuyProductConversionCode);
	}
	/**
	 * 批量更新(商品兑换码表)信息
	 * @param ebuyProductConversionCodeList
	 * @return
	 */
	@Override
	public int updateEbuyProductConversionCodeBatch(List<EbuyProductConversionCode> ebuyProductConversionCodeList) {
		return sqlSession.update("ebuyProductConversionCodeBase.update_ebuyProductConversionCode_Batch", ebuyProductConversionCodeList);
	}
	
	/**
	 * 根据序列号删除(商品兑换码表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyProductConversionCodeLogic(java.math.BigInteger id){
		EbuyProductConversionCode ebuyProductConversionCode = new EbuyProductConversionCode();
		ebuyProductConversionCode.setId(id);
		ebuyProductConversionCode.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("ebuyProductConversionCodeBase.delete_ebuyProductConversionCode_Logic",ebuyProductConversionCode);
	}
	
	/**
	 * 根据Id 批量删除(商品兑换码表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyProductConversionCodeLogicBatch(List<java.math.BigInteger> idList) {
		List<EbuyProductConversionCode> delList = new java.util.ArrayList<EbuyProductConversionCode>();
		for(java.math.BigInteger id:idList){
			EbuyProductConversionCode ebuyProductConversionCode = new EbuyProductConversionCode();
			ebuyProductConversionCode.setId(id);
			ebuyProductConversionCode.setSys0DelState(RecordState_DELETED);
			delList.add(ebuyProductConversionCode);
		}
		return sqlSession.update("ebuyProductConversionCodeBase.delete_ebuyProductConversionCode_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(商品兑换码表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductConversionCode(java.math.BigInteger id){
//		return sqlSession.delete("ebuyProductConversionCodeBase.delete_ebuyProductConversionCode", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(商品兑换码表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductConversionCodeBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("ebuyProductConversionCodeBase.delete_ebuyProductConversionCode_Batch", idList);
//	}
	
	
}
