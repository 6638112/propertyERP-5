package com.cnfantasia.server.domainbase.dredgeWorkerServiceAddressBlock.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.dredgeWorkerServiceAddressBlock.entity.DredgeWorkerServiceAddressBlock;

/**
 * 描述:(疏通师傅服务行政区) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class DredgeWorkerServiceAddressBlockBaseDao extends AbstractBaseDao implements IDredgeWorkerServiceAddressBlockBaseDao{
	/**
	 * 根据条件查询(疏通师傅服务行政区)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<DredgeWorkerServiceAddressBlock> selectDredgeWorkerServiceAddressBlockByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("dredgeWorkerServiceAddressBlockBase.select_dredgeWorkerServiceAddressBlock",paramMap);
	}
	/**
	 * 按条件分页查询(疏通师傅服务行政区)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<DredgeWorkerServiceAddressBlock> selectDredgeWorkerServiceAddressBlockByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectDredgeWorkerServiceAddressBlockCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<DredgeWorkerServiceAddressBlock> resMap= sqlSession.selectList("dredgeWorkerServiceAddressBlockBase.select_dredgeWorkerServiceAddressBlock_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(疏通师傅服务行政区)信息
	 * @param id
	 * @return
	 */
	@Override
	public DredgeWorkerServiceAddressBlock selectDredgeWorkerServiceAddressBlockBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("dredgeWorkerServiceAddressBlockBase.select_dredgeWorkerServiceAddressBlock_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(疏通师傅服务行政区)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectDredgeWorkerServiceAddressBlockCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("dredgeWorkerServiceAddressBlockBase.select_dredgeWorkerServiceAddressBlock_count", paramMap);
	}
	/**
	 * 往(疏通师傅服务行政区)新增一条记录
	 * @param dredgeWorkerServiceAddressBlock
	 * @return
	 */
	@Override
	public int insertDredgeWorkerServiceAddressBlock(DredgeWorkerServiceAddressBlock dredgeWorkerServiceAddressBlock){
		return sqlSession.insert("dredgeWorkerServiceAddressBlockBase.insert_dredgeWorkerServiceAddressBlock",dredgeWorkerServiceAddressBlock);
	}
	/**
	 * 批量新增(疏通师傅服务行政区)信息
	 * @param dredgeWorkerServiceAddressBlockList
	 * @return
	 */
	@Override
	public int insertDredgeWorkerServiceAddressBlockBatch(List<DredgeWorkerServiceAddressBlock> dredgeWorkerServiceAddressBlockList) {
		return sqlSession.insert("dredgeWorkerServiceAddressBlockBase.insert_dredgeWorkerServiceAddressBlock_Batch",dredgeWorkerServiceAddressBlockList);
	}
	
	/**
	 * 更新(疏通师傅服务行政区)信息
	 * @param dredgeWorkerServiceAddressBlock
	 * @return
	 */
	@Override
	public int updateDredgeWorkerServiceAddressBlock(DredgeWorkerServiceAddressBlock dredgeWorkerServiceAddressBlock){
		return sqlSession.update("dredgeWorkerServiceAddressBlockBase.update_dredgeWorkerServiceAddressBlock", dredgeWorkerServiceAddressBlock);
	}
	/**
	 * 批量更新(疏通师傅服务行政区)信息
	 * @param dredgeWorkerServiceAddressBlockList
	 * @return
	 */
	@Override
	public int updateDredgeWorkerServiceAddressBlockBatch(List<DredgeWorkerServiceAddressBlock> dredgeWorkerServiceAddressBlockList) {
		return sqlSession.update("dredgeWorkerServiceAddressBlockBase.update_dredgeWorkerServiceAddressBlock_Batch", dredgeWorkerServiceAddressBlockList);
	}
	
	/**
	 * 根据序列号删除(疏通师傅服务行政区)信息_逻辑删除
	 * @param id
	 * @return
	 */
	/* 
	@Override
	public int deleteDredgeWorkerServiceAddressBlockLogic(java.math.BigInteger id){
		DredgeWorkerServiceAddressBlock dredgeWorkerServiceAddressBlock = new DredgeWorkerServiceAddressBlock();
		dredgeWorkerServiceAddressBlock.setId(id);
		dredgeWorkerServiceAddressBlock.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("dredgeWorkerServiceAddressBlockBase.delete_dredgeWorkerServiceAddressBlock_Logic",dredgeWorkerServiceAddressBlock);
	}
	 */
	/**
	 * 根据Id 批量删除(疏通师傅服务行政区)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	/* 
	@Override
	public int deleteDredgeWorkerServiceAddressBlockLogicBatch(List<java.math.BigInteger> idList) {
		List<DredgeWorkerServiceAddressBlock> delList = new java.util.ArrayList<DredgeWorkerServiceAddressBlock>();
		for(java.math.BigInteger id:idList){
			DredgeWorkerServiceAddressBlock dredgeWorkerServiceAddressBlock = new DredgeWorkerServiceAddressBlock();
			dredgeWorkerServiceAddressBlock.setId(id);
			dredgeWorkerServiceAddressBlock.setSys0DelState(RecordState_DELETED);
			delList.add(dredgeWorkerServiceAddressBlock);
		}
		return sqlSession.update("dredgeWorkerServiceAddressBlockBase.delete_dredgeWorkerServiceAddressBlock_Logic_Batch",delList);
	}
	 */
//	/**
//	 * 根据序列号删除(疏通师傅服务行政区)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeWorkerServiceAddressBlock(java.math.BigInteger id){
//		return sqlSession.delete("dredgeWorkerServiceAddressBlockBase.delete_dredgeWorkerServiceAddressBlock", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(疏通师傅服务行政区)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteDredgeWorkerServiceAddressBlockBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("dredgeWorkerServiceAddressBlockBase.delete_dredgeWorkerServiceAddressBlock_Batch", idList);
//	}
	
	
}
