package com.cnfantasia.server.domainbase.ebuyProductTemp.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyProductTemp.entity.EbuyProductTemp;

/**
 * 描述:(商品表，临时存储从外部抓取的数据) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EbuyProductTempBaseDao extends AbstractBaseDao implements IEbuyProductTempBaseDao{
	/**
	 * 根据条件查询(商品表，临时存储从外部抓取的数据)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyProductTemp> selectEbuyProductTempByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("ebuyProductTempBase.select_ebuyProductTemp",paramMap);
	}
	/**
	 * 按条件分页查询(商品表，临时存储从外部抓取的数据)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyProductTemp> selectEbuyProductTempByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEbuyProductTempCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EbuyProductTemp> resMap= sqlSession.selectList("ebuyProductTempBase.select_ebuyProductTemp_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(商品表，临时存储从外部抓取的数据)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyProductTemp selectEbuyProductTempBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("ebuyProductTempBase.select_ebuyProductTemp_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(商品表，临时存储从外部抓取的数据)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEbuyProductTempCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("ebuyProductTempBase.select_ebuyProductTemp_count", paramMap);
	}
	/**
	 * 往(商品表，临时存储从外部抓取的数据)新增一条记录
	 * @param ebuyProductTemp
	 * @return
	 */
	@Override
	public int insertEbuyProductTemp(EbuyProductTemp ebuyProductTemp){
		return sqlSession.insert("ebuyProductTempBase.insert_ebuyProductTemp",ebuyProductTemp);
	}
	/**
	 * 批量新增(商品表，临时存储从外部抓取的数据)信息
	 * @param ebuyProductTempList
	 * @return
	 */
	@Override
	public int insertEbuyProductTempBatch(List<EbuyProductTemp> ebuyProductTempList) {
		return sqlSession.insert("ebuyProductTempBase.insert_ebuyProductTemp_Batch",ebuyProductTempList);
	}
	
	/**
	 * 更新(商品表，临时存储从外部抓取的数据)信息
	 * @param ebuyProductTemp
	 * @return
	 */
	@Override
	public int updateEbuyProductTemp(EbuyProductTemp ebuyProductTemp){
		return sqlSession.update("ebuyProductTempBase.update_ebuyProductTemp", ebuyProductTemp);
	}
	/**
	 * 批量更新(商品表，临时存储从外部抓取的数据)信息
	 * @param ebuyProductTempList
	 * @return
	 */
	@Override
	public int updateEbuyProductTempBatch(List<EbuyProductTemp> ebuyProductTempList) {
		return sqlSession.update("ebuyProductTempBase.update_ebuyProductTemp_Batch", ebuyProductTempList);
	}
	
	/**
	 * 根据序列号删除(商品表，临时存储从外部抓取的数据)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyProductTempLogic(java.math.BigInteger id){
		EbuyProductTemp ebuyProductTemp = new EbuyProductTemp();
		ebuyProductTemp.setId(id);
		ebuyProductTemp.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("ebuyProductTempBase.delete_ebuyProductTemp_Logic",ebuyProductTemp);
	}
	
	/**
	 * 根据Id 批量删除(商品表，临时存储从外部抓取的数据)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyProductTempLogicBatch(List<java.math.BigInteger> idList) {
		List<EbuyProductTemp> delList = new java.util.ArrayList<EbuyProductTemp>();
		for(java.math.BigInteger id:idList){
			EbuyProductTemp ebuyProductTemp = new EbuyProductTemp();
			ebuyProductTemp.setId(id);
			ebuyProductTemp.setSys0DelState(RecordState_DELETED);
			delList.add(ebuyProductTemp);
		}
		return sqlSession.update("ebuyProductTempBase.delete_ebuyProductTemp_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(商品表，临时存储从外部抓取的数据)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductTemp(java.math.BigInteger id){
//		return sqlSession.delete("ebuyProductTempBase.delete_ebuyProductTemp", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(商品表，临时存储从外部抓取的数据)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductTempBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("ebuyProductTempBase.delete_ebuyProductTemp_Batch", idList);
//	}
	
	
}
