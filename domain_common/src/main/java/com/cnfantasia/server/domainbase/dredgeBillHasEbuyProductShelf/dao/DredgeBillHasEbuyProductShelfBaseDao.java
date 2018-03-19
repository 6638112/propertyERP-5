package com.cnfantasia.server.domainbase.dredgeBillHasEbuyProductShelf.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeBillHasEbuyProductShelf.entity.DredgeBillHasEbuyProductShelf;

/**
 * 描述:(用户自选耗材明细) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class DredgeBillHasEbuyProductShelfBaseDao extends AbstractBaseDao implements IDredgeBillHasEbuyProductShelfBaseDao{
	/**
	 * 根据条件查询(用户自选耗材明细)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<DredgeBillHasEbuyProductShelf> selectDredgeBillHasEbuyProductShelfByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("dredgeBillHasEbuyProductShelfBase.select_dredgeBillHasEbuyProductShelf",paramMap);
	}
	/**
	 * 按条件分页查询(用户自选耗材明细)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<DredgeBillHasEbuyProductShelf> selectDredgeBillHasEbuyProductShelfByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectDredgeBillHasEbuyProductShelfCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<DredgeBillHasEbuyProductShelf> resMap= sqlSession.selectList("dredgeBillHasEbuyProductShelfBase.select_dredgeBillHasEbuyProductShelf_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(用户自选耗材明细)信息
	 * @param id
	 * @return
	 */
	@Override
	public DredgeBillHasEbuyProductShelf selectDredgeBillHasEbuyProductShelfBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("dredgeBillHasEbuyProductShelfBase.select_dredgeBillHasEbuyProductShelf_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(用户自选耗材明细)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectDredgeBillHasEbuyProductShelfCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("dredgeBillHasEbuyProductShelfBase.select_dredgeBillHasEbuyProductShelf_count", paramMap);
	}
	/**
	 * 往(用户自选耗材明细)新增一条记录
	 * @param dredgeBillHasEbuyProductShelf
	 * @return
	 */
	@Override
	public int insertDredgeBillHasEbuyProductShelf(DredgeBillHasEbuyProductShelf dredgeBillHasEbuyProductShelf){
		return sqlSession.insert("dredgeBillHasEbuyProductShelfBase.insert_dredgeBillHasEbuyProductShelf",dredgeBillHasEbuyProductShelf);
	}
	/**
	 * 批量新增(用户自选耗材明细)信息
	 * @param dredgeBillHasEbuyProductShelfList
	 * @return
	 */
	@Override
	public int insertDredgeBillHasEbuyProductShelfBatch(List<DredgeBillHasEbuyProductShelf> dredgeBillHasEbuyProductShelfList) {
		return sqlSession.insert("dredgeBillHasEbuyProductShelfBase.insert_dredgeBillHasEbuyProductShelf_Batch",dredgeBillHasEbuyProductShelfList);
	}
	
	/**
	 * 更新(用户自选耗材明细)信息
	 * @param dredgeBillHasEbuyProductShelf
	 * @return
	 */
	@Override
	public int updateDredgeBillHasEbuyProductShelf(DredgeBillHasEbuyProductShelf dredgeBillHasEbuyProductShelf){
		return sqlSession.update("dredgeBillHasEbuyProductShelfBase.update_dredgeBillHasEbuyProductShelf", dredgeBillHasEbuyProductShelf);
	}
	/**
	 * 批量更新(用户自选耗材明细)信息
	 * @param dredgeBillHasEbuyProductShelfList
	 * @return
	 */
	@Override
	public int updateDredgeBillHasEbuyProductShelfBatch(List<DredgeBillHasEbuyProductShelf> dredgeBillHasEbuyProductShelfList) {
		return sqlSession.update("dredgeBillHasEbuyProductShelfBase.update_dredgeBillHasEbuyProductShelf_Batch", dredgeBillHasEbuyProductShelfList);
	}
	
	/**
	 * 根据序列号删除(用户自选耗材明细)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteDredgeBillHasEbuyProductShelfLogic(java.math.BigInteger id){
		DredgeBillHasEbuyProductShelf dredgeBillHasEbuyProductShelf = new DredgeBillHasEbuyProductShelf();
		dredgeBillHasEbuyProductShelf.setId(id);
		dredgeBillHasEbuyProductShelf.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("dredgeBillHasEbuyProductShelfBase.delete_dredgeBillHasEbuyProductShelf_Logic",dredgeBillHasEbuyProductShelf);
	}
	
	/**
	 * 根据Id 批量删除(用户自选耗材明细)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteDredgeBillHasEbuyProductShelfLogicBatch(List<java.math.BigInteger> idList) {
		List<DredgeBillHasEbuyProductShelf> delList = new java.util.ArrayList<DredgeBillHasEbuyProductShelf>();
		for(java.math.BigInteger id:idList){
			DredgeBillHasEbuyProductShelf dredgeBillHasEbuyProductShelf = new DredgeBillHasEbuyProductShelf();
			dredgeBillHasEbuyProductShelf.setId(id);
			dredgeBillHasEbuyProductShelf.setSys0DelState(RecordState_DELETED);
			delList.add(dredgeBillHasEbuyProductShelf);
		}
		return sqlSession.update("dredgeBillHasEbuyProductShelfBase.delete_dredgeBillHasEbuyProductShelf_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(用户自选耗材明细)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeBillHasEbuyProductShelf(java.math.BigInteger id){
//		return sqlSession.delete("dredgeBillHasEbuyProductShelfBase.delete_dredgeBillHasEbuyProductShelf", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(用户自选耗材明细)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeBillHasEbuyProductShelfBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("dredgeBillHasEbuyProductShelfBase.delete_dredgeBillHasEbuyProductShelf_Batch", idList);
//	}
	
	
}
