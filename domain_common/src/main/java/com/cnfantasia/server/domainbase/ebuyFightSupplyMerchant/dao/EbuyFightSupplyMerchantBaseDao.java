package com.cnfantasia.server.domainbase.ebuyFightSupplyMerchant.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyFightSupplyMerchant.entity.EbuyFightSupplyMerchant;

/**
 * 描述:(自提点表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EbuyFightSupplyMerchantBaseDao extends AbstractBaseDao implements IEbuyFightSupplyMerchantBaseDao{
	/**
	 * 根据条件查询(自提点表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyFightSupplyMerchant> selectEbuyFightSupplyMerchantByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("ebuyFightSupplyMerchantBase.select_ebuyFightSupplyMerchant",paramMap);
	}
	/**
	 * 按条件分页查询(自提点表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyFightSupplyMerchant> selectEbuyFightSupplyMerchantByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEbuyFightSupplyMerchantCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EbuyFightSupplyMerchant> resMap= sqlSession.selectList("ebuyFightSupplyMerchantBase.select_ebuyFightSupplyMerchant_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(自提点表)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyFightSupplyMerchant selectEbuyFightSupplyMerchantBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("ebuyFightSupplyMerchantBase.select_ebuyFightSupplyMerchant_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(自提点表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEbuyFightSupplyMerchantCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("ebuyFightSupplyMerchantBase.select_ebuyFightSupplyMerchant_count", paramMap);
	}
	/**
	 * 往(自提点表)新增一条记录
	 * @param ebuyFightSupplyMerchant
	 * @return
	 */
	@Override
	public int insertEbuyFightSupplyMerchant(EbuyFightSupplyMerchant ebuyFightSupplyMerchant){
		return sqlSession.insert("ebuyFightSupplyMerchantBase.insert_ebuyFightSupplyMerchant",ebuyFightSupplyMerchant);
	}
	/**
	 * 批量新增(自提点表)信息
	 * @param ebuyFightSupplyMerchantList
	 * @return
	 */
	@Override
	public int insertEbuyFightSupplyMerchantBatch(List<EbuyFightSupplyMerchant> ebuyFightSupplyMerchantList) {
		return sqlSession.insert("ebuyFightSupplyMerchantBase.insert_ebuyFightSupplyMerchant_Batch",ebuyFightSupplyMerchantList);
	}
	
	/**
	 * 更新(自提点表)信息
	 * @param ebuyFightSupplyMerchant
	 * @return
	 */
	@Override
	public int updateEbuyFightSupplyMerchant(EbuyFightSupplyMerchant ebuyFightSupplyMerchant){
		return sqlSession.update("ebuyFightSupplyMerchantBase.update_ebuyFightSupplyMerchant", ebuyFightSupplyMerchant);
	}
	/**
	 * 批量更新(自提点表)信息
	 * @param ebuyFightSupplyMerchantList
	 * @return
	 */
	@Override
	public int updateEbuyFightSupplyMerchantBatch(List<EbuyFightSupplyMerchant> ebuyFightSupplyMerchantList) {
		return sqlSession.update("ebuyFightSupplyMerchantBase.update_ebuyFightSupplyMerchant_Batch", ebuyFightSupplyMerchantList);
	}
	
	/**
	 * 根据序列号删除(自提点表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyFightSupplyMerchantLogic(java.math.BigInteger id){
		EbuyFightSupplyMerchant ebuyFightSupplyMerchant = new EbuyFightSupplyMerchant();
		ebuyFightSupplyMerchant.setId(id);
		ebuyFightSupplyMerchant.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("ebuyFightSupplyMerchantBase.delete_ebuyFightSupplyMerchant_Logic",ebuyFightSupplyMerchant);
	}
	
	/**
	 * 根据Id 批量删除(自提点表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyFightSupplyMerchantLogicBatch(List<java.math.BigInteger> idList) {
		List<EbuyFightSupplyMerchant> delList = new java.util.ArrayList<EbuyFightSupplyMerchant>();
		for(java.math.BigInteger id:idList){
			EbuyFightSupplyMerchant ebuyFightSupplyMerchant = new EbuyFightSupplyMerchant();
			ebuyFightSupplyMerchant.setId(id);
			ebuyFightSupplyMerchant.setSys0DelState(RecordState_DELETED);
			delList.add(ebuyFightSupplyMerchant);
		}
		return sqlSession.update("ebuyFightSupplyMerchantBase.delete_ebuyFightSupplyMerchant_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(自提点表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyFightSupplyMerchant(java.math.BigInteger id){
//		return sqlSession.delete("ebuyFightSupplyMerchantBase.delete_ebuyFightSupplyMerchant", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(自提点表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyFightSupplyMerchantBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("ebuyFightSupplyMerchantBase.delete_ebuyFightSupplyMerchant_Batch", idList);
//	}
	
	
}
