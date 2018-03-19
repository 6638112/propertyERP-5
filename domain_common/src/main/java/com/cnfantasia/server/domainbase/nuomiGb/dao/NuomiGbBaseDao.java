package com.cnfantasia.server.domainbase.nuomiGb.dao;

//import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cnfantasia.server.domain.pub.dao.AbstractBaseDao;

import com.cnfantasia.server.business.pub.entity.PageModel;

import com.cnfantasia.server.domainbase.nuomiGb.entity.NuomiGb;

/**
 * 描述:(百度糯米对接小区信息) DAO层
 * 
 * @version 1.00
 * @author null
 * 
 */
@Repository
public class NuomiGbBaseDao extends AbstractBaseDao implements INuomiGbBaseDao{
	/**
	 * 根据条件查询(百度糯米对接小区信息)信息
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<NuomiGb> selectNuomiGbByCondition(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectList("nuomiGbBase.select_nuomiGb",paramMap);
	}
	/**
	 * 按条件分页查询(百度糯米对接小区信息)信息
	 * @param paramMap
	 * @param pageModel
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public List<NuomiGb> selectNuomiGbByCondition(Map<String,Object> paramMap,PageModel pageModel,boolean isDim) {
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		else{paramMap = new java.util.HashMap<String,Object>();}
		Integer totalCount=null;
		if(null==pageModel.isCount||pageModel.isCount){totalCount=selectNuomiGbCount(paramMap,isDim);}//为null,默认统计分页信息
		pageModel.validate(totalCount);
		
		int old=paramMap.size();
		Map<String,Object> pageMap=pageModel.toMap();
		paramMap.putAll(pageMap);//此句代码需要确保传入的paramMap不包含分页信息的key，否则传入的paramMap的相应信息会被覆盖。
		if(old+pageMap.size()!=paramMap.size()){throw new RuntimeException("The merge of params cause some lose ,the paramMap is:"+paramMap);}
		
		List<NuomiGb> resMap= sqlSession.selectList("nuomiGbBase.select_nuomiGb_withPage",paramMap);
		
		pageModel.freshPageModel(resMap.size(),totalCount);//pageModel信息的更新通过引用实现
		return resMap;
	}
	/**
	 * 根据序列号查询(百度糯米对接小区信息)信息
	 * @param id
	 * @return
	 */
	@Override
	public NuomiGb selectNuomiGbBySeqId(java.math.BigInteger id){
		return sqlSession.selectOne("nuomiGbBase.select_nuomiGb_bySeqId",id);
	}
	/**
	 * 根据条件查询满足条件的(百度糯米对接小区信息)记录数
	 * @param paramMap
	 * @param isDim true表示模糊查询,false表示精确查询
	 * @return
	 */
	@Override
	public int selectNuomiGbCount(Map<String,Object> paramMap,boolean isDim){
		//if(paramMap!=null){paramMap = new HashMap<String, Object>();}
		if(paramMap!=null){paramMap.put(QUERY_TYPE_IF_DIM, isDim);}
		return sqlSession.selectOne("nuomiGbBase.select_nuomiGb_count", paramMap);
	}
	/**
	 * 往(百度糯米对接小区信息)新增一条记录
	 * @param nuomiGb
	 * @return
	 */
	@Override
	public int insertNuomiGb(NuomiGb nuomiGb){
		return sqlSession.insert("nuomiGbBase.insert_nuomiGb",nuomiGb);
	}
	/**
	 * 批量新增(百度糯米对接小区信息)信息
	 * @param nuomiGbList
	 * @return
	 */
	@Override
	public int insertNuomiGbBatch(List<NuomiGb> nuomiGbList) {
		if (nuomiGbList == null || nuomiGbList.isEmpty()) {
			return 0;
		}
		int total = 0;
		int size = nuomiGbList.size();
		int batch = size / batchSize + (size % batchSize == 0 ? 0 : 1);
		for (int i = 0; i < batch; i++) {
			List<NuomiGb> batchList = nuomiGbList.subList(i * batchSize, (i == batch - 1) ? size : i * batchSize + batchSize);
			total += sqlSession.insert("nuomiGbBase.insert_nuomiGb_Batch", batchList);
		}
		return total;
	}
	
	/**
	 * 更新(百度糯米对接小区信息)信息
	 * @param nuomiGb
	 * @return
	 */
	@Override
	public int updateNuomiGb(NuomiGb nuomiGb){
		return sqlSession.update("nuomiGbBase.update_nuomiGb", nuomiGb);
	}
	/**
	 * 批量更新(百度糯米对接小区信息)信息
	 * @param nuomiGbList
	 * @return
	 */
	@Override
	public int updateNuomiGbBatch(List<NuomiGb> nuomiGbList) {
		if (nuomiGbList == null || nuomiGbList.isEmpty()) {
			return 0;
		}
		return sqlSession.update("nuomiGbBase.update_nuomiGb_Batch", nuomiGbList);
	}
	
	/**
	 * 根据序列号删除(百度糯米对接小区信息)信息_逻辑删除
	 * @param id
	 * @return
	 */
	
	@Override
	public int deleteNuomiGbLogic(java.math.BigInteger id){
		NuomiGb nuomiGb = new NuomiGb();
		nuomiGb.setId(id);
		nuomiGb.setSys0DelState(RecordState_DELETED);
		return sqlSession.update("nuomiGbBase.delete_nuomiGb_Logic",nuomiGb);
	}
	
	/**
	 * 根据Id 批量删除(百度糯米对接小区信息)信息_逻辑删除
	 * @param idList
	 * @return
	 */
	
	@Override
	public int deleteNuomiGbLogicBatch(List<java.math.BigInteger> idList) {
		if (idList == null || idList.isEmpty()) {
			return 0;
		}
		
		List<NuomiGb> delList = new java.util.ArrayList<NuomiGb>();
		for(java.math.BigInteger id:idList){
			NuomiGb nuomiGb = new NuomiGb();
			nuomiGb.setId(id);
			nuomiGb.setSys0DelState(RecordState_DELETED);
			delList.add(nuomiGb);
		}
		return sqlSession.update("nuomiGbBase.delete_nuomiGb_Logic_Batch",delList);
	}
	
//	/**
//	 * 根据序列号删除(百度糯米对接小区信息)信息
//	 * @param id
//	 * @return
//	 */
//	@Override
//	public int deleteNuomiGb(java.math.BigInteger id){
//		return sqlSession.delete("nuomiGbBase.delete_nuomiGb", id);
//	}
//	
//	/**
//	 * 根据序列号批量删除(百度糯米对接小区信息)信息
//	 * @param idList
//	 * @return
//	 */
//	@Override
//	public int deleteNuomiGbBatch(List<java.math.BigInteger> idList) {
//		return sqlSession.delete("nuomiGbBase.delete_nuomiGb_Batch", idList);
//	}
	
	
}
