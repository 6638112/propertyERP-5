package com.cnfantasia.server.domainbase.ebuyBuyCar.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyBuyCar.entity.EbuyBuyCar;

/**
 * 描述:(购物车) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EbuyBuyCarBaseDao extends AbstractBaseDao implements IEbuyBuyCarBaseDao{
	/**
	 * 根据条件查询(购物车)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyBuyCar> selectEbuyBuyCarByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("ebuyBuyCarBase.select_ebuyBuyCar",paramMap);
	}
	/**
	 * 按条件分页查询(购物车)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyBuyCar> selectEbuyBuyCarByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEbuyBuyCarCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EbuyBuyCar> resMap= sqlSession.selectList("ebuyBuyCarBase.select_ebuyBuyCar_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(购物车)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyBuyCar selectEbuyBuyCarBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("ebuyBuyCarBase.select_ebuyBuyCar_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(购物车)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEbuyBuyCarCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("ebuyBuyCarBase.select_ebuyBuyCar_count", paramMap);
	}
	/**
	 * 往(购物车)新增一条记录
	 * @param ebuyBuyCar
	 * @return
	 */
	@Override
	public int insertEbuyBuyCar(EbuyBuyCar ebuyBuyCar){
		return sqlSession.insert("ebuyBuyCarBase.insert_ebuyBuyCar",ebuyBuyCar);
	}
	/**
	 * 批量新增(购物车)信息
	 * @param ebuyBuyCarList
	 * @return
	 */
	@Override
	public int insertEbuyBuyCarBatch(List<EbuyBuyCar> ebuyBuyCarList) {
		return sqlSession.insert("ebuyBuyCarBase.insert_ebuyBuyCar_Batch",ebuyBuyCarList);
	}
	
	/**
	 * 更新(购物车)信息
	 * @param ebuyBuyCar
	 * @return
	 */
	@Override
	public int updateEbuyBuyCar(EbuyBuyCar ebuyBuyCar){
		return sqlSession.update("ebuyBuyCarBase.update_ebuyBuyCar", ebuyBuyCar);
	}
	/**
	 * 批量更新(购物车)信息
	 * @param ebuyBuyCarList
	 * @return
	 */
	@Override
	public int updateEbuyBuyCarBatch(List<EbuyBuyCar> ebuyBuyCarList) {
		return sqlSession.update("ebuyBuyCarBase.update_ebuyBuyCar_Batch", ebuyBuyCarList);
	}
	
	/**
	 * 根据序列号删除(购物车)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyBuyCarLogic(java.math.BigInteger id){
		EbuyBuyCar ebuyBuyCar = new EbuyBuyCar();
		ebuyBuyCar.setId(id);
		ebuyBuyCar.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("ebuyBuyCarBase.delete_ebuyBuyCar_Logic",ebuyBuyCar);
	}
	
	/**
	 * 根据Id 批量删除(购物车)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyBuyCarLogicBatch(List<java.math.BigInteger> idList) {
		List<EbuyBuyCar> delList = new java.util.ArrayList<EbuyBuyCar>();
		for(java.math.BigInteger id:idList){
			EbuyBuyCar ebuyBuyCar = new EbuyBuyCar();
			ebuyBuyCar.setId(id);
			ebuyBuyCar.setSys0DelState(RecordState_DELETED);
			delList.add(ebuyBuyCar);
		}
		return sqlSession.update("ebuyBuyCarBase.delete_ebuyBuyCar_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(购物车)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyBuyCar(java.math.BigInteger id){
//		return sqlSession.delete("ebuyBuyCarBase.delete_ebuyBuyCar", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(购物车)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyBuyCarBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("ebuyBuyCarBase.delete_ebuyBuyCar_Batch", idList);
//	}
	
	
}
