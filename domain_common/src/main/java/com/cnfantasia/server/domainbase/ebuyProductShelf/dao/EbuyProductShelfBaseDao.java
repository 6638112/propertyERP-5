package com.cnfantasia.server.domainbase.ebuyProductShelf.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.ebuyProductShelf.entity.EbuyProductShelf;

/**
 * 描述:(货架商品表) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class EbuyProductShelfBaseDao extends AbstractBaseDao implements IEbuyProductShelfBaseDao{
	/**
	 * 根据条件查询(货架商品表)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyProductShelf> selectEbuyProductShelfByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("ebuyProductShelfBase.select_ebuyProductShelf",paramMap);
	}
	/**
	 * 按条件分页查询(货架商品表)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<EbuyProductShelf> selectEbuyProductShelfByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectEbuyProductShelfCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<EbuyProductShelf> resMap= sqlSession.selectList("ebuyProductShelfBase.select_ebuyProductShelf_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(货架商品表)信息
	 * @param id
	 * @return
	 */
	@Override
	public EbuyProductShelf selectEbuyProductShelfBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("ebuyProductShelfBase.select_ebuyProductShelf_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(货架商品表)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectEbuyProductShelfCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("ebuyProductShelfBase.select_ebuyProductShelf_count", paramMap);
	}
	/**
	 * 往(货架商品表)新增一条记录
	 * @param ebuyProductShelf
	 * @return
	 */
	@Override
	public int insertEbuyProductShelf(EbuyProductShelf ebuyProductShelf){
		return sqlSession.insert("ebuyProductShelfBase.insert_ebuyProductShelf",ebuyProductShelf);
	}
	/**
	 * 批量新增(货架商品表)信息
	 * @param ebuyProductShelfList
	 * @return
	 */
	@Override
	public int insertEbuyProductShelfBatch(List<EbuyProductShelf> ebuyProductShelfList) {
		return sqlSession.insert("ebuyProductShelfBase.insert_ebuyProductShelf_Batch",ebuyProductShelfList);
	}
	
	/**
	 * 更新(货架商品表)信息
	 * @param ebuyProductShelf
	 * @return
	 */
	@Override
	public int updateEbuyProductShelf(EbuyProductShelf ebuyProductShelf){
		return sqlSession.update("ebuyProductShelfBase.update_ebuyProductShelf", ebuyProductShelf);
	}
	/**
	 * 批量更新(货架商品表)信息
	 * @param ebuyProductShelfList
	 * @return
	 */
	@Override
	public int updateEbuyProductShelfBatch(List<EbuyProductShelf> ebuyProductShelfList) {
		return sqlSession.update("ebuyProductShelfBase.update_ebuyProductShelf_Batch", ebuyProductShelfList);
	}
	
	/**
	 * 根据序列号删除(货架商品表)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteEbuyProductShelfLogic(java.math.BigInteger id){
		EbuyProductShelf ebuyProductShelf = new EbuyProductShelf();
		ebuyProductShelf.setId(id);
		ebuyProductShelf.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("ebuyProductShelfBase.delete_ebuyProductShelf_Logic",ebuyProductShelf);
	}
	
	/**
	 * 根据Id 批量删除(货架商品表)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteEbuyProductShelfLogicBatch(List<java.math.BigInteger> idList) {
		List<EbuyProductShelf> delList = new java.util.ArrayList<EbuyProductShelf>();
		for(java.math.BigInteger id:idList){
			EbuyProductShelf ebuyProductShelf = new EbuyProductShelf();
			ebuyProductShelf.setId(id);
			ebuyProductShelf.setSys0DelState(RecordState_DELETED);
			delList.add(ebuyProductShelf);
		}
		return sqlSession.update("ebuyProductShelfBase.delete_ebuyProductShelf_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(货架商品表)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductShelf(java.math.BigInteger id){
//		return sqlSession.delete("ebuyProductShelfBase.delete_ebuyProductShelf", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(货架商品表)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteEbuyProductShelfBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("ebuyProductShelfBase.delete_ebuyProductShelf_Batch", idList);
//	}
	
	
}
